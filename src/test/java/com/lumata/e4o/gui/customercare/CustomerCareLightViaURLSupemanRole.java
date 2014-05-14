package com.lumata.e4o.gui.customercare;

import java.io.StringReader;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import org.jboss.resteasy.client.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.network.RestClient;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.Service;
import com.lumata.common.testing.system.User;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.e4o.exceptions.CommoditiesException;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.customercare.CustomerCareLightForm.CustomerCareLightFormTab;
import com.lumata.e4o.gui.security.Authorization;
import com.lumata.e4o.schema.tenant.Conf;
import com.lumata.e4o.system.environment.ExpressionKernelCommands;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

public class CustomerCareLightViaURLSupemanRole {

	private static final Logger logger = LoggerFactory.getLogger( CustomerCareLightViaURLSupemanRole.class );
	
	private long TIMEOUT = 10000;
	private long ATTEMPT_TIMEOUT = 100;
	
	private SeleniumWebDriver seleniumWebDriver;
	private String browser;
	private NetworkEnvironment env;
	private Server gui;
	@SuppressWarnings("unused")
	private User guiUser;
	private Service actruleServer;
	private RestClient restClient;
	private Mysql mysqlTenant;
	private ExpressionKernelCommands ekc;
	
	private final boolean testsEnabled = true;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "gui_server", "tenant", "user"})
	@BeforeClass
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_VM") String environment, @Optional("actrule") String gui_server, @Optional("tenant") String tenant, @Optional("superman") String user ) throws CommoditiesException, JSONSException, IOFileException, NetworkEnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		/** Create Selenium WebDriver instance */
		gui = env.getServer( gui_server );
		
		guiUser = gui.getUser( user );
		
		this.browser = browser;
				
		/** create rest client */
		restClient = new RestClient();
		
		/** Create actrule server instance */
		actruleServer = env.getService( Service.Type.ssh, gui_server );
				
		/** create mysql tenant connection */
		mysqlTenant = new Mysql( env.getDataSource( tenant ) );
				
		/** get ssh connection with actrule server */
		ekc = new ExpressionKernelCommands( actruleServer, "root" );
	
	}
	
	/* 	Initialize TestCase Name */
	@BeforeMethod
	protected void startSession(Method method) throws Exception {
		//seleniumWebDriver.setTestName( method.getName() ); 	
	}
	
	@Test(enabled=testsEnabled, priority = 1 )
	public void checkMD5Disable() throws Exception {
	
		Boolean md5Enabled = this.getMD5LoginConfguration();
		
		if( md5Enabled ) { 
			
			this.enableMD5LoginConfguration( !md5Enabled );	
			
		}
		
	}
	
	@Test(enabled=testsEnabled, dependsOnMethods = "checkMD5Disable" )
	public void checkCustomerCareCredentialWithoutMD5TogenTab() throws Exception {
		
		String msisdn = "393669393643";
		
		User supermanUser = gui.getUser( "superman" );
		
		seleniumWebDriver = new SeleniumWebDriver( gui.getBrowser( browser ), gui.getLink() );
		
		CustomerCareLightForm ccl = new CustomerCareLightForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		
		/** access with wrong credential */		
		User wrongUser = new User();
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn, CustomerCareLightFormTab.Tokens ).open().isNotAuthenticated() );
		
		
		/** access with wrong user and empty password */		
		wrongUser.setUsername( "wrong" );
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn, CustomerCareLightFormTab.Tokens ).open().isNotAuthenticated() );
				
		
		/** access with wrong user and password */		
		wrongUser.setUsername( "wrong" );
		wrongUser.setSecurityPassword( "wrong" );
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn, CustomerCareLightFormTab.Tokens ).open().isNotAuthenticated() );
		
		
		/** access with correct user and wrong password */		
		wrongUser.setUsername( "superman" );
		wrongUser.setSecurityPassword( "wrong" );
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn, CustomerCareLightFormTab.Tokens ).open().isNotAuthenticated() );
		
		
		/** access with wrong user and correct password */		
		wrongUser.setUsername( "wrong" );
		wrongUser.setSecurityPassword( "super2010Man" );
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn, CustomerCareLightFormTab.Tokens ).open().isNotAuthenticated() );
		
		
		/** access with correct credential */				
		Assert.assertTrue( ccl.buildUrl( supermanUser, msisdn, CustomerCareLightFormTab.Tokens ).open().isAuthenticated() );
		
		/** access with correct credential and wrong msisdn */				
		String wrongMsisdn = "wrong";
		
		Assert.assertTrue( ccl.buildUrl( supermanUser, wrongMsisdn, CustomerCareLightFormTab.Tokens ).open().isResultNotFound() );
		
		
		/** access with correct credential and msisdn without tokens */
		String msisdnWithoutTokens = "00000";
				
		Assert.assertTrue( ccl.buildUrl( supermanUser, msisdnWithoutTokens, CustomerCareLightFormTab.Tokens ).open().isResultNotFound() );
		
		
		/** access with correct credential and msisdn with tokens ( < 10 ) */
		Assert.assertTrue( ccl.buildUrl( supermanUser, msisdn, CustomerCareLightFormTab.Tokens ).open().isResultFound() );
		
		/** close browser */
		seleniumWebDriver.close();
				
	}
	
	@Test(enabled=testsEnabled, dependsOnMethods = "checkCustomerCareCredentialWithoutMD5TogenTab" )
	public void checkCustomerCareCredentialWithoutMD5LoyaltyTab() throws Exception {
		
		String msisdn = "393669393643";
		
		User supermanUser = gui.getUser( "superman" );
		
		seleniumWebDriver = new SeleniumWebDriver( gui.getBrowser( browser ), gui.getLink() );
		
		CustomerCareLightForm ccl = new CustomerCareLightForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		
		/** access with wrong credential */		
		User wrongUser = new User();
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn, CustomerCareLightFormTab.Loyalty ).open().isNotAuthenticated() );
		
		
		/** access with wrong user and empty password */		
		wrongUser.setUsername( "wrong" );
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn, CustomerCareLightFormTab.Loyalty ).open().isNotAuthenticated() );
				
		
		/** access with wrong user and password */		
		wrongUser.setUsername( "wrong" );
		wrongUser.setSecurityPassword( "wrong" );
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn, CustomerCareLightFormTab.Loyalty ).open().isNotAuthenticated() );
		
		
		/** access with correct user and wrong password */		
		wrongUser.setUsername( "superman" );
		wrongUser.setSecurityPassword( "wrong" );
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn, CustomerCareLightFormTab.Loyalty ).open().isNotAuthenticated() );
		
		
		/** access with wrong user and correct password */		
		wrongUser.setUsername( "wrong" );
		wrongUser.setSecurityPassword( "super2010Man" );
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn, CustomerCareLightFormTab.Loyalty ).open().isNotAuthenticated() );
		
		
		/** access with correct credential */				
		Assert.assertTrue( ccl.buildUrl( supermanUser, msisdn, CustomerCareLightFormTab.Loyalty ).open().isAuthenticated() );
		
		/** access with correct credential and wrong msisdn */				
		String wrongMsisdn = "wrong";
		
		Assert.assertTrue( ccl.buildUrl( supermanUser, wrongMsisdn, CustomerCareLightFormTab.Loyalty ).open().isResultNotFound() );
		
		
		/** access with correct credential and msisdn without tokens */
		String msisdnWithoutTokens = "00000";
				
		Assert.assertTrue( ccl.buildUrl( supermanUser, msisdnWithoutTokens, CustomerCareLightFormTab.Loyalty ).open().isResultNotFound() );
		
		
		/** access with correct credential and msisdn with tokens ( < 10 ) */
		Assert.assertTrue( ccl.buildUrl( supermanUser, msisdn, CustomerCareLightFormTab.Loyalty ).open().isResultFound() );
		
		/** close browser */
		seleniumWebDriver.close();
				
	}
	
	@Test(enabled=testsEnabled, dependsOnMethods = { "checkCustomerCareCredentialWithoutMD5LoyaltyTab" } )
	public void checkMD5Enable() throws Exception {
	
		Boolean md5Enabled = this.getMD5LoginConfguration();
		
		if( !md5Enabled ) { 
			
			this.enableMD5LoginConfguration( !md5Enabled );	
			
		}
		
	}
	
	@Test(enabled=testsEnabled, dependsOnMethods = "checkMD5Enable" )
	public void checkCustomerCareCredentialWithMD5TokenTab() throws FormException {
		
		String msisdn = "393669393643";
		
		User supermanUser = gui.getUser( "superman" );
		
		seleniumWebDriver = new SeleniumWebDriver( gui.getBrowser( browser ), gui.getLink() );
		
		CustomerCareLightForm ccl = new CustomerCareLightForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		
		/** access with wrong credential */		
		User wrongUser = new User();
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn, CustomerCareLightFormTab.Tokens ).open().isNotAuthenticated() );
		
		
		/** access with wrong user and empty password */		
		wrongUser.setUsername( "wrong" );
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn, CustomerCareLightFormTab.Tokens ).open().isNotAuthenticated() );
				
		
		/** access with wrong user and password */		
		wrongUser.setUsername( "wrong" );
		wrongUser.setSecurityPassword( "wrong" );
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn, CustomerCareLightFormTab.Tokens ).open().isNotAuthenticated() );
		
		
		/** access with correct user and wrong password */		
		wrongUser.setUsername( "superman" );
		wrongUser.setSecurityPassword( "wrong" );
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn, CustomerCareLightFormTab.Tokens ).open().isNotAuthenticated() );
		
		
		/** access with wrong user and correct password without md5 */		
		wrongUser.setUsername( "wrong" );
		wrongUser.setSecurityPassword( "super2010Man" );
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn, CustomerCareLightFormTab.Tokens ).open().isNotAuthenticated() );
		
		
		/** access with correct credential */
		supermanUser.setSecurityMD5Password( "super2010Man" );
		
		Assert.assertTrue( ccl.buildUrl( supermanUser, msisdn, CustomerCareLightFormTab.Tokens ).open().isAuthenticated() );
		
		/** access with correct credential and wrong msisdn */				
		String wrongMsisdn = "wrong";
		
		Assert.assertTrue( ccl.buildUrl( supermanUser, wrongMsisdn, CustomerCareLightFormTab.Tokens ).open().isResultNotFound() );
		
		
		/** access with correct credential and msisdn without tokens */
		String msisdnWithoutTokens = "00000";
				
		Assert.assertTrue( ccl.buildUrl( supermanUser, msisdnWithoutTokens, CustomerCareLightFormTab.Tokens ).open().isResultNotFound() );
		
		
		/** access with correct user and wrong password ( without MD5 ) and msisdn with tokens */
		supermanUser.setSecurityPassword( "super2010Man" );
		
		Assert.assertTrue( ccl.buildUrl( supermanUser, msisdn, CustomerCareLightFormTab.Tokens ).open().isResultNotFound() );
		

		/** access with correct user and correct password ( with MD5 ) and msisdn with tokens */
		supermanUser.setSecurityMD5Password( "super2010Man" );
		
		Assert.assertTrue( ccl.buildUrl( supermanUser, msisdn, CustomerCareLightFormTab.Tokens ).open().isResultFound() );
			
		/** close browser */
		seleniumWebDriver.close();
		
	}
	
	@Test(enabled=testsEnabled, dependsOnMethods = "checkMD5Enable" )
	public void checkCustomerCareCredentialWithMD5LoyaltyTab() throws FormException {
		
		String msisdn = "393669393643";
		
		User supermanUser = gui.getUser( "superman" );
		
		seleniumWebDriver = new SeleniumWebDriver( gui.getBrowser( browser ), gui.getLink() );
		
		CustomerCareLightForm ccl = new CustomerCareLightForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		
		/** access with wrong credential */		
		User wrongUser = new User();
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn, CustomerCareLightFormTab.Loyalty ).open().isNotAuthenticated() );
		
		
		/** access with wrong user and empty password */		
		wrongUser.setUsername( "wrong" );
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn, CustomerCareLightFormTab.Loyalty ).open().isNotAuthenticated() );
				
		
		/** access with wrong user and password */		
		wrongUser.setUsername( "wrong" );
		wrongUser.setSecurityPassword( "wrong" );
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn, CustomerCareLightFormTab.Loyalty ).open().isNotAuthenticated() );
		
		
		/** access with correct user and wrong password */		
		wrongUser.setUsername( "superman" );
		wrongUser.setSecurityPassword( "wrong" );
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn, CustomerCareLightFormTab.Loyalty ).open().isNotAuthenticated() );
		
		
		/** access with wrong user and correct password without md5 */		
		wrongUser.setUsername( "wrong" );
		wrongUser.setSecurityPassword( "super2010Man" );
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn, CustomerCareLightFormTab.Loyalty ).open().isNotAuthenticated() );
		
		
		/** access with correct credential */
		supermanUser.setSecurityMD5Password( "super2010Man" );
		
		Assert.assertTrue( ccl.buildUrl( supermanUser, msisdn, CustomerCareLightFormTab.Loyalty ).open().isAuthenticated() );
		
		/** access with correct credential and wrong msisdn */				
		String wrongMsisdn = "wrong";
		
		Assert.assertTrue( ccl.buildUrl( supermanUser, wrongMsisdn, CustomerCareLightFormTab.Loyalty ).open().isResultNotFound() );
		
		
		/** access with correct credential and msisdn without tokens */
		String msisdnWithoutTokens = "00000";
				
		Assert.assertTrue( ccl.buildUrl( supermanUser, msisdnWithoutTokens, CustomerCareLightFormTab.Loyalty ).open().isResultNotFound() );
		
		
		/** access with correct user and wrong password ( without MD5 ) and msisdn with tokens */
		supermanUser.setSecurityPassword( "super2010Man" );
		
		Assert.assertTrue( ccl.buildUrl( supermanUser, msisdn, CustomerCareLightFormTab.Loyalty ).open().isResultNotFound() );
		

		/** access with correct user and correct password ( with MD5 ) and msisdn with tokens */
		supermanUser.setSecurityMD5Password( "super2010Man" );
		
		Assert.assertTrue( ccl.buildUrl( supermanUser, msisdn, CustomerCareLightFormTab.Loyalty ).open().isResultFound() );
			
		/** close browser */
		seleniumWebDriver.close();
		
	}
	
	/** 
	 * 
	 *  Note: all test cases using "wrong link" are not validated yet
	 *  
	 * @throws Exception */
	@Test(enabled=false, priority = 10 )
	public void loginWithWrongLinks() throws Exception {
		
		String wrongLink = gui.getLink() + "angular/customerCare/";
		
		logger.info( Log.CHECKING.createMessage( "Access via url: " + wrongLink ) );
		
		ClientResponse<String> response = restClient.get( wrongLink );
		
		Assert.assertEquals( response.getStatus(), 403 );
		
		
		logger.info( "Result ( loginWithWrongLinks ): " + getHtmlTitle( response.getEntity().toString() ) );
		
		wrongLink = wrongLink + "index.html?lang=ENG";
		
		
		logger.info( Log.CHECKING.createMessage( "Access via url: " + wrongLink ) );
		
		response = restClient.get( wrongLink );
		
		Assert.assertEquals( response.getStatus(), 401 );
		
		
		logger.info( "Result ( loginWithWrongLinks ): " + getHtmlTitle( response.getEntity().toString() ) );
		
	}
	
	private String getHtmlTitle( String htmlPage ) {
		
		String htmlTitle = "";
		
		HTMLEditorKit kit = new HTMLEditorKit();
		
		try {
	         
			HTMLDocument doc = new HTMLDocument();
			
			doc.putProperty("IgnoreCharsetDirective", new Boolean(true));
	          
			kit.read(new StringReader( htmlPage ), doc, 0);
	          
			htmlTitle = (String)doc.getProperty( Document.TitleProperty );
	          
		} catch( Exception e ) {
			  
			logger.error( e.getMessage(), e );
		  
		}
		
		return htmlTitle;
		
	} 

	private Boolean getMD5LoginConfguration() throws SQLException {
		
		Conf confTable = new Conf();
		
		confTable.setName( "md5_authentication" );
		confTable.setSection( "system" );
		
		String query = select().
						from( confTable ).
						where( 
								op( Conf.Fields.name ).eq(),
								and( op( Conf.Fields.section ).eq() )
						).
						build();
		
		ResultSet rs = mysqlTenant.execQuery( query );
		
		while( rs.next() ) {
			
			confTable.setCurrent( rs.getString( Conf.Fields.current.name() ) );
		
		}
		
		logger.info( "MD5 access is " + ( Boolean.valueOf( confTable.getCurrent() ) ? "enabled" : "disabled"  ) );
		
		return Boolean.valueOf( confTable.getCurrent() );
		
	}
	
	private void enableMD5LoginConfguration( Boolean currentValue ) throws SQLException, FormException {
		
		Conf confTable = new Conf();
		
		confTable.setName( "md5_authentication" );
		confTable.setSection( "system" );
		confTable.setCurrent( String.valueOf( currentValue ) );
			
		String query = update( confTable ).
						set( op( Conf.Fields.current ).eq() ).
						where( 
								op( Conf.Fields.name ).eq(),
								and( op( Conf.Fields.section ).eq() )
						).
						build();
				
		mysqlTenant.execUpdate( query );
		
		Assert.assertTrue( ekc.expressionRestart() );
		
		seleniumWebDriver = new SeleniumWebDriver( gui.getBrowser( browser ), gui.getLink() );		
				
		Assert.assertTrue( Authorization.getInstance( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT ).refresh() );
		
		seleniumWebDriver.close();
		
	}
	
	@AfterClass
	public void end() {
		
		seleniumWebDriver.close();
		
		mysqlTenant.close();
		
	}
		
}
