package com.lumata.e4o.gui.customercare;

import java.io.StringReader;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.ws.rs.core.MultivaluedMap;

import org.jboss.resteasy.client.ClientResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.network.RestClient;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.e4o.exceptions.CommoditiesException;
import com.lumata.e4o.json.common.JsonConfig;

public class CustomerCareLightViaURL {

	private class JSONResponse extends JsonConfig {
		
		private Integer errorCode;
		private String token;
		private String message;
				
		public JSONResponse( JSONObject response ) {
			
			super( response );
			
		}

		public String getStatus() {
			return getStringFromPath("status");
		}
		
		public Integer getStatusCode() {
			return getIntegerFromPath("statusCode");
		}
		
		public String getException() {
			return getStringFromPath("exception");
		}
				
		public JSONArray getValidationErrors() throws JSONException {
			return getJSONArrayFromPath("exception");
		}
		
		public Integer getErrorCode() {
			return getIntegerFromPath("businessError.errorCode");
		}

		public String getToken() {
			return getStringFromPath("businessError.token");
		}

		public String getMessage() {
			return getStringFromPath("businessError.message");
		}

		public Integer getPage() {
			return getIntegerFromPath("value.errorCode");
		}
		
		public Integer getCount() {
			return getIntegerFromPath("value.errorCode");
		}
		
		public JSONArray getEntities() throws JSONException {
			return getJSONArrayFromPath("value.errorCode");
		}
		
	}
	
	private static final Logger logger = LoggerFactory.getLogger( CustomerCareLightViaURL.class );
	
	private int TIMEOUT = 60000;
	private int ATTEMPT_TIMEOUT = 100;
	
	private enum RestParams { login, password, msisdn, startDate, endDate, page }
	
	private SeleniumWebDriver seleniumWebDriver;
	private String browser;
	private NetworkEnvironment env;
	private Server gui;
	private RestClient restClient;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "gui_server", "tenant", "user"})
	@BeforeClass
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_VM") String environment, @Optional("actrule") String gui_server, @Optional("tenant") String tenant, @Optional("superman") String user ) throws CommoditiesException, JSONSException, IOFileException, NetworkEnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		/** Create Selenium WebDriver instance */
		gui = env.getServer( gui_server );
		
		this.browser = browser;
		
		/** create rest client */
		restClient = new RestClient();
		
		/** Login */
		//Assert.assertTrue(Authorization.login(seleniumWebDriver, gui.getUser( user ), TIMEOUT, ATTEMPT_TIMEOUT));
		
	}
	
	/* 	Initialize TestCase Name */
	@BeforeMethod
	protected void startSession(Method method) throws Exception {
		//seleniumWebDriver.setTestName( method.getName() ); 	
	}
	
	@Test(enabled=false, priority = 1 )
	public void checkGetTokenRestCall() throws Exception {
		
		String msisdn = "393669393643";
		
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		
		
		logger.info( Log.CHECKING.createMessage( "without user and password" ) );
		
		ClientResponse<String> response = restClient.get( getCCTokenPageLink( msisdn, startDate, endDate, 1 ) );
		
		JSONResponse jsonResponse = new JSONResponse( new JSONObject( response.getEntity().toString() ) );

		Assert.assertEquals( jsonResponse.getStatus(), "NO_ACCESS_GRANT" );
		Assert.assertEquals( (int)jsonResponse.getStatusCode(), 401 );
		
		
		logger.info( Log.CHECKING.createMessage( "with empty user and without password" ) );
		
		User user = new User();
		user.setUsername( "" );
		
		response = restClient.get( getCCTokenPageLink( msisdn, startDate, endDate, 1 ) );
		
		jsonResponse = new JSONResponse( new JSONObject( response.getEntity().toString() ) );

		Assert.assertEquals( jsonResponse.getStatus(), "NO_ACCESS_GRANT" );
		Assert.assertEquals( (int)jsonResponse.getStatusCode(), 401 );
	
		
		logger.info( Log.CHECKING.createMessage( "with correct user and without password" ) );
		
		user.setUsername( "superman" );
		
		this.setRequestHeader( user );
		
		response = restClient.get( getCCTokenPageLink( msisdn, startDate, endDate, 1 ) );
		
		jsonResponse = new JSONResponse( new JSONObject( response.getEntity().toString() ) );

		Assert.assertEquals( jsonResponse.getStatus(), "NO_ACCESS_GRANT" );
		Assert.assertEquals( (int)jsonResponse.getStatusCode(), 401 );
		
		
		logger.info( Log.CHECKING.createMessage( "with correct user and empty password" ) );
		
		user.setUsername( "superman" );
		user.setPassword( "" );
		
		this.setRequestHeader( user );
		
		response = restClient.get( getCCTokenPageLink( msisdn, startDate, endDate, 1 ) );
		
		jsonResponse = new JSONResponse( new JSONObject( response.getEntity().toString() ) );

		Assert.assertEquals( jsonResponse.getStatus(), "NO_ACCESS_GRANT" );
		Assert.assertEquals( (int)jsonResponse.getStatusCode(), 401 );
		
		
		logger.info( Log.CHECKING.createMessage( "with wrong user and wrong password" ) );
		
		user.setUsername( "wrong" );
		user.setPassword( "wrong" );
		
		this.setRequestHeader( user );
		
		response = restClient.get( getCCTokenPageLink( msisdn, startDate, endDate, 1 ) );
		
		jsonResponse = new JSONResponse( new JSONObject( response.getEntity().toString() ) );

		Assert.assertEquals( jsonResponse.getStatus(), "NO_ACCESS_GRANT" );
		Assert.assertEquals( (int)jsonResponse.getStatusCode(), 401 );
		
		
		logger.info( Log.CHECKING.createMessage( "with correct user and password but empty msisdn" ) );
		
		user.setUsername( "superman" );
		user.setPassword( "super2010Man" );
		
		this.setRequestHeader( user );
		
		response = restClient.get( getCCTokenPageLink( "", startDate, endDate, 1 ) );
		
		Assert.assertEquals( response.getStatus(), 404 );
		
		
		logger.info( Log.CHECKING.createMessage( "with correct user and password but wrong msisdn" ) );
		
		user.setUsername( "superman" );
		user.setPassword( "super2010Man" );
		
		this.setRequestHeader( user );
		
		response = restClient.get( getCCTokenPageLink( "test", startDate, endDate, 1 ) );
		
		jsonResponse = new JSONResponse( new JSONObject( response.getEntity().toString() ) );
		
		Assert.assertEquals( jsonResponse.getStatus(), "UNKNOW_ERROR" );
		Assert.assertEquals( (int)jsonResponse.getStatusCode(), 500 );
		
		
		logger.info( Log.CHECKING.createMessage( "with correct user and password but not existing msisdn" ) );
		
		user.setUsername( "superman" );
		user.setPassword( "super2010Man" );
		
		this.setRequestHeader( user );
		
		response = restClient.get( getCCTokenPageLink( "0000", startDate, endDate, 1 ) );
		
		jsonResponse = new JSONResponse( new JSONObject( response.getEntity().toString() ) );
		
		Assert.assertEquals( jsonResponse.getStatus(), "OK" );
		Assert.assertEquals( (int)jsonResponse.getStatusCode(), 200 );
		
		
		/** # The min pag value is -1 but in the message is reported 0 */
		logger.info( Log.CHECKING.createMessage( "with correct user, password and msisdn but wrong page" ) );
		
		response = restClient.get( getCCTokenPageLink( msisdn, startDate, endDate, 0 ) );
		
		jsonResponse = new JSONResponse( new JSONObject( response.getEntity().toString() ) );
		
		Assert.assertEquals( jsonResponse.getStatus(), "WRONG_ARGS" );
		Assert.assertEquals( (int)jsonResponse.getStatusCode(), 400 );
		
		
		logger.info( Log.CHECKING.createMessage( "with correct user, password, msisdn, page, but wrong dateRange startDate > endDate" ) );
		
		startDate.add( Calendar.DATE , 1 );
			
		response = restClient.get( getCCTokenPageLink( msisdn, startDate, endDate, 1 ) );
		
		jsonResponse = new JSONResponse( new JSONObject( response.getEntity().toString() ) );
		
		Assert.assertEquals( jsonResponse.getStatus(), "OK" );
		Assert.assertEquals( (int)jsonResponse.getStatusCode(), 200 );
	
		
		logger.info( Log.CHECKING.createMessage( "with correct user, password, msisdn, page, but empty startDate and endDate" ) );
		
		response = restClient.get( getCCTokenPageLink( msisdn, "", "", "1" ) );
			
		Assert.assertEquals( response.getStatus(), 404 );

		
		logger.info( Log.CHECKING.createMessage( "with correct user, password, msisdn, page, but wrong format for startDate and endDate" ) );
		
		response = restClient.get( getCCTokenPageLink( msisdn, "2014-04-01", "2014-04-01", "1" ) );
		 
		Assert.assertEquals( response.getStatus(), 404 );
				
		
		logger.info( Log.CHECKING.createMessage( "with correct user, password, msisdn, page, but wrong format for startDate and endDate" ) );
		
		response = restClient.get( getCCTokenPageLink( msisdn, "startDate", "endDate", "1" ) );
		 
		Assert.assertEquals( response.getStatus(), 404 );
			
		
		logger.info( Log.CHECKING.createMessage( "with correct user, password, msisdn, page, and dateRange" ) );
		
		startDate.add( Calendar.MONTH, -3 );
		
		endDate.set( endDate.get( Calendar.YEAR ), endDate.get( Calendar.DATE ), endDate.get( Calendar.DATE ), 23, 59, 59 );
		
		response = restClient.get( getCCTokenPageLink( msisdn, startDate, endDate, 1 ) );
		
		jsonResponse = new JSONResponse( new JSONObject( response.getEntity().toString() ) );
		
		Assert.assertEquals( jsonResponse.getStatus(), "OK" );
		Assert.assertEquals( (int)jsonResponse.getStatusCode(), 200 );
	
	}
	
	@Test(enabled=false, priority = 3 )
	public void checkCustomerCareCredential() throws Exception {
		
		String msisdn = "393669393643";
		
		User supermanUser = gui.getUser( "superman" );
		
		seleniumWebDriver = new SeleniumWebDriver( gui.getBrowser( browser ), gui.getLink() );
		
		CustomerCareLightForm ccl = new CustomerCareLightForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		
		/** access with wrong credential */		
		User wrongUser = new User();
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn ).open().isNotAuthenticated() );
		
		
		/** access with wrong user and empty password */		
		wrongUser.setUsername( "wrong" );
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn ).open().isNotAuthenticated() );
				
		
		/** access with wrong user and password */		
		wrongUser.setUsername( "wrong" );
		wrongUser.setSecurityPassword( "wrong" );
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn ).open().isNotAuthenticated() );
		
		
		/** access with correct user and wrong password */		
		wrongUser.setUsername( "superman" );
		wrongUser.setSecurityPassword( "wrong" );
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn ).open().isNotAuthenticated() );
		
		
		/** access with wrong user and correct password */		
		wrongUser.setUsername( "wrong" );
		wrongUser.setSecurityPassword( "super2010Man" );
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn ).open().isNotAuthenticated() );
		
		
		/** access with correct credential */				
		Assert.assertTrue( ccl.buildUrl( supermanUser, msisdn ).open().isAuthenticated() );
		
		/** access with correct credential and wrong msisdn */				
		String wrongMsisdn = "wrong";
		
		Assert.assertTrue( ccl.buildUrl( supermanUser, wrongMsisdn ).open().isResultNotFound() );
		
		
		/** access with correct credential and msisdn without tokens */
		String msisdnWithoutTokens = "00000";
				
		Assert.assertTrue( ccl.buildUrl( supermanUser, msisdnWithoutTokens ).open().isResultNotFound() );
		
		
		/** access with correct credential and msisdn with tokens ( < 10 ) */
		Assert.assertTrue( ccl.buildUrl( supermanUser, msisdn ).open().isResultFound() );
				
	}
	
	@Test(enabled=true, priority = 4 )
	public void checkCustomerCareCredentialWithMD5() throws Exception {
		
		String msisdn = "393669393643";
		
		User supermanUser = gui.getUser( "superman" );
		
		seleniumWebDriver = new SeleniumWebDriver( gui.getBrowser( browser ), gui.getLink() );
		
		CustomerCareLightForm ccl = new CustomerCareLightForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		
		/** access with wrong credential */		
		User wrongUser = new User();
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn ).open().isNotAuthenticated() );
		
		
		/** access with wrong user and empty password */		
		wrongUser.setUsername( "wrong" );
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn ).open().isNotAuthenticated() );
				
		
		/** access with wrong user and password */		
		wrongUser.setUsername( "wrong" );
		wrongUser.setSecurityPassword( "wrong" );
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn ).open().isNotAuthenticated() );
		
		
		/** access with correct user and wrong password */		
		wrongUser.setUsername( "superman" );
		wrongUser.setSecurityPassword( "wrong" );
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn ).open().isNotAuthenticated() );
		
		
		/** access with wrong user and correct password without md5 */		
		wrongUser.setUsername( "wrong" );
		wrongUser.setSecurityPassword( "super2010Man" );
		
		Assert.assertTrue( ccl.buildUrl( wrongUser, msisdn ).open().isNotAuthenticated() );
		
		
		/** access with correct credential */				
		Assert.assertTrue( ccl.buildUrl( supermanUser, msisdn ).open().isAuthenticated() );
		
		/** access with correct credential and wrong msisdn */				
		String wrongMsisdn = "wrong";
		
		Assert.assertTrue( ccl.buildUrl( supermanUser, wrongMsisdn ).open().isResultNotFound() );
		
		
		/** access with correct credential and msisdn without tokens */
		String msisdnWithoutTokens = "00000";
				
		Assert.assertTrue( ccl.buildUrl( supermanUser, msisdnWithoutTokens ).open().isResultNotFound() );
		
		
		/** access with correct user and wrong password ( without MD5 ) and msisdn with tokens */
		Assert.assertTrue( ccl.buildUrl( supermanUser, msisdn ).open().isResultNotFound() );
		

		/** access with correct user and correct password ( with MD5 ) and msisdn with tokens */
		supermanUser.setSecurityMD5Password( "super2010Man" );
		
		Assert.assertTrue( ccl.buildUrl( supermanUser, msisdn ).open().isResultFound() );
						
	}
	
	
	
	
	
	
	
	
	/** access with correct credential and msisdn with tokens ( < 10 ) */
	//ccl.buildUrl( supermanUser, msisdn ).open().list();
	
	
	
	
	
	
	
	
	
	
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
		
		// ClientResponse<String> response = RestClient.get( "http://10.120.38.27:7070/angular/customerCare/index.html?lang=ENG&login=superman&pwd=supera2010Man#/tokens/123" );
		
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
	
	private String getAngularPageLink( String login, String password, String msisdn ) {
		
		Map<RestParams, String> options = new HashMap<RestParams, String>();
		options.put( RestParams.login , ( null != login ? login : "" ) );
		options.put( RestParams.password , ( null != password ? password : "" ) );
		options.put( RestParams.msisdn , ( null != msisdn ? msisdn : "" ) );
		
		return getAngularPageLink( options );
		
	}
	
	private String getAngularPageLink( Map<RestParams, String> options ) {
		
		String link = gui.getLink() + "angular/customerCare/index.html?lang=ENG&login=${login}&pwd=${pwd}#/tokens/${msisdn}";
		
		if( options != null ) {
		
			if( options.containsKey( RestParams.login ) ) { link = link.replace( "${login}" , options.get( RestParams.login ) ); }
			if( options.containsKey( RestParams.password ) ) { link = link.replace( "${pwd}" , options.get( RestParams.password ) ); }
			if( options.containsKey( RestParams.msisdn ) ) { link = link.replace( "${msisdn}" , options.get( RestParams.msisdn ) ); }
		
		}
		
		return link;
		
	}
	
	private String getCCTokenPageLink( String msisdn, Calendar startDate, Calendar endDate, Integer page ) {
		
		return getCCTokenPageLink( msisdn, String.valueOf( startDate.getTimeInMillis() ), String.valueOf( endDate.getTimeInMillis() ), String.valueOf( page ) );
		
	}
	
	private String getCCTokenPageLink( String msisdn, String startDate, String endDate, String page ) {
		
		Map<RestParams, String> options = new HashMap<RestParams, String>();
		options.put( RestParams.msisdn , ( null != msisdn ? msisdn : "" ) );
		options.put( RestParams.startDate , ( null != startDate ? startDate : "" ) );
		options.put( RestParams.endDate , ( null != endDate ? endDate : "" ) );
		options.put( RestParams.page , ( null != page ? page : "" ) );
				
		return getCCTokenPageLink( options );
		
	}
	
	private String getCCTokenPageLink( Map<RestParams, String> options ) {
		
		String link = gui.getLink() + "rest/customercare/${msisdn}/token?end=${endDate}&page=${page}&start=${startDate}"; 
							
		if( options != null ) {
		
			if( options.containsKey( RestParams.msisdn ) ) { link = link.replace( "${msisdn}" , options.get( RestParams.msisdn ) ); }
			if( options.containsKey( RestParams.startDate ) ) { link = link.replace( "${startDate}" , options.get( RestParams.startDate ) ); }
			if( options.containsKey( RestParams.endDate ) ) { link = link.replace( "${endDate}" , options.get( RestParams.endDate ) ); }
			if( options.containsKey( RestParams.page ) ) { link = link.replace( "${page}" , options.get( RestParams.page ) ); }
			
		}
		
		return link;
		
	}
	
	private void setRequestHeader( User user ) throws Exception {
		
		restClient.cleanHeader();
		
		if( user != null ) {
			
			//restClient.header( "Accept", "application/json" );
			//restClient.header( "Content-Type", "application/json;charset=UTF-8" );
			restClient.header( RestParams.login.name(), user.getUsername() );
			restClient.header( RestParams.password.name(), user.getPassword() );
						
		}
		
	}
	
	@AfterClass
	public void end() {
		
		//Authorization.logout(seleniumWebDriver);	
		
	}
		
}
