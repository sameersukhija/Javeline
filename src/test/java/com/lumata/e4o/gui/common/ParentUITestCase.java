package com.lumata.e4o.gui.common;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils.IOLoadingType;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.Browser;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.security.Authorization;

/**
 * This is the parent class for test case for UI for E4O. It provides the follow facilities :
 * 
 * <li> handle the input environment configuration file
 * <li> initialize Selenium driver according configuration
 * <li> facilities like login and logout into UI
 * 
 * @author <a href="mailto:vincenzo.marrazzo@lumatagroup.com">Vincenzo Marrazzo</a>
 */
public abstract class ParentUITestCase {
	
	/**
	 * Print to standard output during execution
	 */
	protected static final Boolean PRINT2STDOUT__ = Boolean.TRUE;
	
	/**
	 * Default resource path
	 */
	private static final String DEFAULT_RESOURCE_START_PATH__ = "input";
	private static final String DEFAULT_LOADING_TYPE__ = "RESOURCE";
	
	/**
	 * Default value blocks the invocation of selenium grid
	 */
	private static final String DEFAULT_GRID_CONF__ = "http://no.selenium.grid:5555/wd/hub";
	
	protected final Integer TIMEOUT = 60000;
	protected final Integer ATTEMPT_TIMEOUT = 50;
	
	static protected NetworkEnvironment env = null;
	static protected Server gui = null;
	static protected String gridAddress = null;
	static protected SeleniumWebDriver seleniumWebDriver = null;
	
	static private Authorization auth = null;
	
	/**
	 * Session static configuration
	 */
	static private String browserSession = null;
	static private String userSession = null;
	
	/**
	 * Used to retrieve configuration resource during execution
	 */
	static protected String currentResourceStartPath;
	
	/* 	Initialize Environment */
	@BeforeSuite
	@Parameters({"browser", "environment", "loadingType", "resourceStartPath", "gui_server", "seleniumGrid"})
	public void setUp( 	@Optional("FIREFOX") String browser, 
						@Optional("E4O_VM_NE") String environment, 
						@Optional(DEFAULT_LOADING_TYPE__) String loadingType,
						@Optional(DEFAULT_RESOURCE_START_PATH__) String resourceStartPath,
						@Optional("actrule") String gui_server,
						@Optional(DEFAULT_GRID_CONF__) String seleniumGrid
					) throws JSONSException, IOFileException, NetworkEnvironmentException {		
		
		currentResourceStartPath = resourceStartPath;
	
		Reporter.log("Starting path for looking \"resource\" will be -> " + currentResourceStartPath);
		Reporter.log("Loading type for \"resource\" will be -> " + loadingType);
		
		Reporter.log( Log.LOADING.createMessage( "init" , "environment" ), PRINT2STDOUT__);
	
		String envPath = currentResourceStartPath + "/environments";
		String envFile = environment;
		
		Reporter.log( "Init testing environment with reosurce file : ", PRINT2STDOUT__);
		Reporter.log( "Resource path -> " + envPath, PRINT2STDOUT__);
		Reporter.log( "Resource file -> " + envFile, PRINT2STDOUT__);		
		
		/** Create environment configuration */
		env = new NetworkEnvironment( envPath, envFile, IOLoadingType.valueOf(loadingType) );

		browserSession = browser;
		
		gridAddress = seleniumGrid;
		
		/** Create Selenium WebDriver instance */
		gui = env.getServer( gui_server );
		
		seleniumWebDriver = initSeleniumDriver( gui.getBrowser( browserSession ), gui.getLink(), gridAddress);

		auth = new Authorization(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);
	}	

	/**
	 * According passed parameter this method inizialize a local or remote web driver
	 * 
	 * @param browser
	 * @param baseUrl
	 * @param gridUrl
	 */
	private SeleniumWebDriver initSeleniumDriver(Browser browser, String baseUrl, String gridUrl) {

		SeleniumWebDriver resp = null;
		
		if ( gridUrl.equals(DEFAULT_GRID_CONF__) ) {
			
			Reporter.log( "Startup Selenium driver with \""+browserSession+"\".", PRINT2STDOUT__);
			
			resp = new SeleniumWebDriver( browser, baseUrl);
		}
		else {
			
			Reporter.log( "Startup RemoteWebDriver with \""+browserSession+"\".", PRINT2STDOUT__);
			
			resp = new SeleniumWebDriver( browser, baseUrl, gridUrl);
		}
		
		return resp;
	}

	@BeforeTest
	@Parameters({"user"})
	public void executeLogin(@Optional("superman") String user) throws FormException {
	
		userSession = user;
		
		Reporter.log( "Perform login procedure with user "+userSession+".", PRINT2STDOUT__);
	
		auth.login(gui.getUser( userSession ));
	}
	
	@AfterTest
	public void executeLogout() throws FormException {
	
		Reporter.log( "Perform logout procedure.", PRINT2STDOUT__);
	
		auth.logout();
	}
	
	@AfterSuite
	public void tearDown() {
		
		Reporter.log( "Close Selenium driver.", PRINT2STDOUT__);
		
		if (seleniumWebDriver != null)
			seleniumWebDriver.close();
	}	
	
	@AfterMethod
	protected void tearDown(ITestResult result) throws FormException {
		
	    if (result.getStatus() == ITestResult.FAILURE) {
	        
	    	Reporter.log( "Class " + result.getClass().getSimpleName() + " method " + result.getName() + " has failed!", PRINT2STDOUT__);
	    	
	    	Reporter.log( "Recover UI interface with restart browser.", PRINT2STDOUT__);
	    	
	    	seleniumWebDriver.close();
	    	
			seleniumWebDriver = initSeleniumDriver( gui.getBrowser( browserSession ), gui.getLink(), gridAddress);
			
	    	auth = new Authorization(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);
	    	
	    	auth.login(gui.getUser( userSession ));
	    }
	}	
}

