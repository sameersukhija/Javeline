package com.lumata.e4o.testing.common;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestNGException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.selenium.SeleniumWebDriver.SeleniumDriverType;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.gui.security.Authorization;

/**
 * This is the parent class for all test cases in E4OSystemTest. It provides the follow facilities :
 * 
 * <li> initialize environment
 * <li> initialize Selenium web driver
 * <li> initialize databases
 * 
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * @author <a href="mailto:vincenzo.marrazzo@lumatagroup.com">Vincenzo Marrazzo</a>
 * 
 */
public abstract class ParentTestCase {

	/**
	 * 	Print to standard output during execution
	 */
	protected Boolean LOG_TO_STD_OUT = Boolean.TRUE;
	
	/**
	 * 	Enable test case execution
	 */	
	protected final boolean TEST_ENABLED = true;
	
	/**
	 * 	Default sources folder
	 */
	protected final String DEFAULT_RESOURCE_FOLDER_ROOT = System.getProperty( "user.dir" ) + "/src/test/resources/";
	
	/**
	 * 	Default environment resource folder
	 */
	protected final String DEFAULT_RESOURCE_FOLDER_ENVIRONMENTS = DEFAULT_RESOURCE_FOLDER_ROOT + "/environments/";
		
	/**
	 * 	Default selenium web driver 
	 */
	protected final SeleniumDriverType DEFAULT_SELENIUM_DRIVER_TYPE = SeleniumDriverType.local;
	
	/**
	 * 	Default Network Environment json file
	 */
	protected final String DEFAULT_ENV_FILE = "E4O_VM";

	/**
	 * 	Default e4o gui server
	 */
	protected final String DEFAULT_GUI_SERVER = "actrule";
	
	/**
	 * 	Default e4o gui user
	 */
	protected final String DEFAULT_GUI_USER = "superman";
	
	/**
	 * 	Default e4o global schema
	 */
	protected final String DEFAULT_SCHEMA_GLOBAL = "global";
	
	/**
	 * 	Default e4o master schema
	 */
	protected final String DEFAULT_SCHEMA_MASTER = "tenantMaster";

	/**
	 * 	Default e4o report schema
	 */
	protected final String DEFAULT_SCHEMA_REPORT = "tenantReport";
	
	/**
	 * 	Default browser
	 */
	protected final String DEFAULT_BROWSER = "FIREFOX";
	
	/**
	 * 	Default timeout before a test failure 
	 * 	in case the selenium web driver 
	 * 	doesn't discover an element
	 */
	protected final Integer TIMEOUT = 60000;
		
	/**
	 * 	Default timeout for each attempt 
	 * 	of the selenium web driver 
	 * 	to discover a web element 
	 */
	protected final Integer ATTEMPT_TIMEOUT = 200;	
	
	/**
	 * 	Selenium Web Driver instance 
	 */
	protected SeleniumWebDriver seleniumWebDriver;
		
	/**
	 * 	Map the information about the environment used 
	 * 	to run the test cases 
	 */
	protected NetworkEnvironment env;

	/**
	 * 	Allow to manage the Mysql Global instance 	 
	 */
	protected Mysql mysqlGlobal;

	/**
	 * 	Allow to manage the Mysql Master instance 	 
	 */
	protected Mysql mysqlMaster;
	
	/**
	 * 	Allow to manage the Mysql Report instance 	 
	 */
	protected Mysql mysqlReport;
	
	/**
	 * 	Enable the test case to use selenium web driver 
	 * 	( local or remote )  	 
	 */
	protected SeleniumDriverType seleniumDriverType;
	
	/**
	 * 	Current gui server  	 
	 */
	protected Server guiServer;

	/**
	 * 	Current gui user  	 
	 */
	protected User user;

	
	/**
	 * 	Enable the test case to use mysql global	 
	 */
	protected String mysqlGlobalName = "";

	/**
	 * 	Enable the test case to use mysql master	 
	 */
	protected String mysqlMasterName = "";

	/**
	 * 	Enable the test case to use mysql report 	 
	 */
	protected String mysqlReportName = "";

	/**
	 * 	Contains the network environment parameters configured in the testng file 	 
	 */
	private String networkEnvironmentParams;
	
	/**
	 * 	Contains the selenium web driver parameters configured in the testng file 	 
	 */
	private String seleniumWebDriverParams;
	
	/**
	 * 	Contains the network environment parameters configured in the testng file 	 
	 */
	private JSONObject jsonNetworkEnvironmentParams;
	
	/**
	 * 	Contains the selenium web driver parameters configured in the testng file 	 
	 */
	private JSONObject jsonSeleniumWebDriverParams;

	@Parameters({
		"networkEnvironmentParams", 
		"seleniumWebDriverParams"
	})
	@BeforeClass
	public void init(
		@Optional("") String networkEnvironmentParams, 
		@Optional("") String seleniumWebDriverParams
	) {
		
		configure();
		
		initNetworkEnvironment( networkEnvironmentParams );
		
		initDatabases();
		
		initSeleniumWebDriver( seleniumWebDriverParams );
		
	}
	
	private void configure() {
		
		Reporter.log( Log.LOADING.createMessage( "configure" , "configuration" ), LOG_TO_STD_OUT );
		
		Annotation[] annotations = this.getClass().getAnnotations();
		
		for( Annotation annotation : annotations ) {
			
			switch( annotation.annotationType().getSimpleName() ) {
			
				case "TCSeleniumWebDriver": {
					
					Reporter.log( Log.ENABLING.createMessage( "configure" , "selenium web driver" ), LOG_TO_STD_OUT );
					
					seleniumDriverType = DEFAULT_SELENIUM_DRIVER_TYPE;
					
					break;
					
				}
				case "TCMysqlGlobal": {
					
					Reporter.log( Log.ENABLING.createMessage( "configure" , "mysql global" ), LOG_TO_STD_OUT );
					
					mysqlGlobalName = DEFAULT_SCHEMA_GLOBAL;
					
					break;
					
				}
				case "TCMysqlMaster": {
					
					Reporter.log( Log.ENABLING.createMessage( "configure" , "mysql master" ), LOG_TO_STD_OUT );
					
					mysqlMasterName = DEFAULT_SCHEMA_MASTER;
					
					break;
					
				}
				case "TCMysqlReport": {
					
					Reporter.log( Log.ENABLING.createMessage( "configure" , "mysql report" ), LOG_TO_STD_OUT );
					
					mysqlReportName = DEFAULT_SCHEMA_REPORT;
					
					break;
					
				}
			
			}			
			
		}		
		
	}
	
	private void initNetworkEnvironment( String networkEnvironmentParams ) throws TestNGException {
				
		Reporter.log( Log.LOADING.createMessage( "initNetworkEnvironment" , "environment" ), LOG_TO_STD_OUT );
				
		try {
			
			if( networkEnvironmentParams.isEmpty() ) {
				
				Reporter.log( Log.LOADING.createMessage( "initNetworkEnvironment" , "default network environment configuration ( " + DEFAULT_ENV_FILE + " )" ), LOG_TO_STD_OUT );
								
				env = new NetworkEnvironment( DEFAULT_RESOURCE_FOLDER_ENVIRONMENTS, DEFAULT_ENV_FILE, IOFileUtils.IOLoadingType.FILE );
				
			} else {
				
				this.networkEnvironmentParams = networkEnvironmentParams;
				
				jsonNetworkEnvironmentParams = new JSONObject( this.networkEnvironmentParams );
				
				Reporter.log( Log.LOADING.createMessage( "initNetworkEnvironment" , "custom network environment configuration ( " + jsonNetworkEnvironmentParams.getString( "envFile" ) + " )" ), LOG_TO_STD_OUT );
								
				env = new NetworkEnvironment( DEFAULT_RESOURCE_FOLDER_ENVIRONMENTS, jsonNetworkEnvironmentParams.getString( "envFile" ), IOFileUtils.IOLoadingType.FILE );
			
			}
			
			initGUIServer();
			
			initGUIUser();
	
		} catch( Exception e ) {
			
			throw new TestNGException( e.getMessage(), e );
			
		}
		
	}
	
	private void initGUIServer() {
		
		if( 
			null == jsonNetworkEnvironmentParams || 
			jsonNetworkEnvironmentParams.length() == 0 ||
			!jsonNetworkEnvironmentParams.has( "guiServer" ) || 
			jsonNetworkEnvironmentParams.getString( "guiServer" ).isEmpty()													
		) {
			
			Reporter.log( Log.LOADING.createMessage( "initSeleniumWebDriver" , "default gui server ( " + DEFAULT_GUI_SERVER + " )" ), LOG_TO_STD_OUT );
			
			guiServer = env.getServer( DEFAULT_GUI_SERVER );
			
						
		} else {
			
			Reporter.log( Log.LOADING.createMessage( "initSeleniumWebDriver" , "default gui server ( " + jsonNetworkEnvironmentParams.getString( "guiServer" ) + " )" ), LOG_TO_STD_OUT );
				
			guiServer = env.getServer( jsonNetworkEnvironmentParams.getString( "guiServer" ) );	
				
		}
		
	}
	
	private void initGUIUser() {
		
		if( 
			null == jsonNetworkEnvironmentParams || 
			jsonNetworkEnvironmentParams.length() == 0 ||
			!jsonNetworkEnvironmentParams.has( "guiUser" ) || 
			jsonNetworkEnvironmentParams.getString( "guiUser" ).isEmpty()			
		) {
			
			Reporter.log( Log.LOADING.createMessage( "initSeleniumWebDriver" , "default gui user ( " + DEFAULT_GUI_USER + " )" ), LOG_TO_STD_OUT );
			
			user = guiServer.getUser( DEFAULT_GUI_USER );
						
		} else {
			
			Reporter.log( Log.LOADING.createMessage( "initSeleniumWebDriver" , "default gui server ( " + jsonNetworkEnvironmentParams.getString( "guiUser" ) + " )" ), LOG_TO_STD_OUT );
				
			user = guiServer.getUser( jsonNetworkEnvironmentParams.getString( "guiUser" ) );	

		}
		
	}
	
	private void initDatabases() throws TestNGException {
		
		try {
			
			mysqlGlobal = getMysqlInstance( mysqlGlobalName, "schemaGlobal", DEFAULT_SCHEMA_GLOBAL );
			
			mysqlMaster = getMysqlInstance( mysqlMasterName, "schemaMaster", DEFAULT_SCHEMA_MASTER );
			
			mysqlReport = getMysqlInstance( mysqlReportName, "schemaReport", DEFAULT_SCHEMA_REPORT );			
						
		} catch( Exception e ) {
			
			throw new TestNGException( e.getMessage(), e );
			
		}
		
	}
	
	private Mysql getMysqlInstance( String mysqlName, String jsonSchemaKey, String defaultSchema ) {
		
		Mysql mysql = null;
		
		if( !mysqlName.isEmpty() ) {
			
			if( 	null == jsonNetworkEnvironmentParams || 
					jsonNetworkEnvironmentParams.length() == 0 || 
					!jsonNetworkEnvironmentParams.has( jsonSchemaKey ) || 
					jsonNetworkEnvironmentParams.getString( jsonSchemaKey ).isEmpty()
			) {
				
				Reporter.log( Log.LOADING.createMessage( "initDatabases" , "default configuration ( " + defaultSchema + " )" ), LOG_TO_STD_OUT );
				
				mysql = new Mysql( env.getDataSource( defaultSchema ) ); 
								
			} else {
				
				Reporter.log( Log.LOADING.createMessage( "initDatabases" , "custom configuration ( " + jsonNetworkEnvironmentParams.getString( jsonSchemaKey ) + " )" ), LOG_TO_STD_OUT );
					
				mysql = new Mysql( env.getDataSource( jsonNetworkEnvironmentParams.getString( jsonSchemaKey ) ) );
				
			}
			
			Assert.assertNotNull( mysql, "the mysql instance ( " + mysqlName + " ) cannot be null" );
			
			Assert.assertNotNull( mysql.getConnection(), "the mysql connection ( " + mysqlName + " ) cannot be null" );
										
		} 
		
		return mysql;
		
	}
	
	private void initSeleniumWebDriver( String seleniumWebDriverParams ) throws TestNGException {
						
		try {
			
			if( null != seleniumDriverType ) {
								
				if( seleniumWebDriverParams.isEmpty() ) {
					
					Reporter.log( Log.LOADING.createMessage( "initSeleniumWebDriver" , "default selenium web driver configuration ( local )" ), LOG_TO_STD_OUT );
					
					seleniumWebDriver = new SeleniumWebDriver( guiServer.getBrowser( DEFAULT_BROWSER ), guiServer.getLink() );
					
				} else {
					
					this.seleniumWebDriverParams = seleniumWebDriverParams;
					
					jsonSeleniumWebDriverParams = new JSONObject( seleniumWebDriverParams );
					
					Reporter.log( Log.LOADING.createMessage( "initSeleniumWebDriver" , "custom selenium web driver configuration ( " + jsonSeleniumWebDriverParams.toString() + " )" ), LOG_TO_STD_OUT );
						
					seleniumWebDriver = SeleniumWebDriver.getInstance( jsonSeleniumWebDriverParams ).openBrowser( guiServer.getLink() );
					
				}
				
				Assert.assertNotNull( seleniumWebDriver );
				
				seleniumWebDriver.setTestName( "login" );
				
				/** Login */
				Assert.assertTrue( Authorization.getInstance( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT).login( user ).navigate() );
				
			}
	
		} catch( Exception e ) {
			
			throw new TestNGException( e.getMessage(), e );
			
		}
		
	}
	
	@BeforeMethod
	protected void startSession(Method method) throws Exception {
		
		if( null != seleniumDriverType ) {
		
			seleniumWebDriver.setTestName( method.getName() );
			
		}
		 	
	}
	
	@AfterMethod
	protected void tearDown( ITestResult result ) throws TestNGException {
		
	    if( result.getStatus() == ITestResult.FAILURE ) {
	        
	    	if( null != seleniumDriverType ) {
		    	
	    		Reporter.log( "Class " + result.getClass().getSimpleName() + " method " + result.getName() + " has failed!", LOG_TO_STD_OUT);
		    	
		    	Reporter.log( "Recover UI interface with restart browser.", LOG_TO_STD_OUT);
		    	
		    	seleniumWebDriver.close();
		    	
		    	initSeleniumWebDriver( seleniumWebDriverParams );
				
	    	}
	    	
	    }
	    
	}
	
	@AfterClass
	protected void end() throws TestNGException {
		
		Reporter.log( Log.CLOSING.createMessage( "end" , this.getClass().getSimpleName() + " execution end" ), LOG_TO_STD_OUT );
		
		if( null != seleniumWebDriver ) { 
		
			try {
			
				/** Logout */
				Assert.assertTrue( Authorization.getInstance( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT).quit().navigate() );
			
			} catch ( Exception e ) {
				
				throw new TestNGException( e.getMessage(), e );
				
			}
			
			seleniumWebDriver.close(); 
			
		}
		
		if( null != mysqlGlobal && null != mysqlGlobal.getConnection() ) { 
			
			Reporter.log(  Log.CLOSING.createMessage( "mysql global instance"  ) );
			
			mysqlGlobal.close(); 
			
		}
		
		if( null != mysqlMaster && null != mysqlMaster.getConnection() ) { 
			
			Reporter.log( Log.CLOSING.createMessage( "mysql master instance"  ) );
			
			mysqlMaster.close(); 
			
		}
		
		if( null != mysqlReport && null != mysqlReport.getConnection() ) { 
		
			Reporter.log( Log.CLOSING.createMessage( "mysql report instance"  ) );
			
			mysqlReport.close(); 
			
		}
		
		seleniumDriverType = null;
		
		mysqlGlobalName = "";		
		
		mysqlMasterName = "";
		
		mysqlReportName = "";
		
	}
	
}

