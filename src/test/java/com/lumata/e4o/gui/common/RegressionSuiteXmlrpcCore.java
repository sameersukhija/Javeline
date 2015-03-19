package com.lumata.e4o.gui.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;

public class RegressionSuiteXmlrpcCore {

	/**
	 * Base folder where XML file are stored
	 */
	protected static String baseXmlrpcLogFolder = "xmlrpc";
	
	/**
	 * Current log folder
	 */
	protected static String xmlrpcLogFolder = null;
	
	/**
	 * This object will be used to generate an unique log folder
	 */
	private static Date start4LogFolder = null;
	
	/**
	 * It describes the execution time for single test for log purpose
	 */
	protected String testTime = null;
	
	/**
	 * Default sleep time for XMLPRC message
	 */
	protected static Long XMLRPC_CALL_DELAY = 100L;	

	/**
	 * 
	 */
	protected static NetworkEnvironment env;
	protected static Mysql mysql;

	/**
	 * Intermediate object to handle XMLRPC init
	 */
	protected static Server gui = null;
	protected static User user = null;
	
	/**
	 * Path to environment file
	 */
	private static String envPath = null;
	
	/**
	 * Environment file name
	 */
	private static String envFile = null;
	
	/**
	 * Print to standard output during execution
	 */
	protected static final Boolean PRINT2STDOUT__ = Boolean.TRUE;

	/**
	 * This object describe the origin of time for current test execution
	 * This object will be used to define start time into request (see @wherePlaceT0 )
	 */
	protected static Date startOfTime4Request = null;
	
	/**
	 * This delta in minutes is applied to suite start time of observation
	 * (start time for xmlrpc query) = (suite start time) - wherePlaceT0
	 */
	protected static Integer wherePlaceT0 = null;
	
	/**
	 * 
	 * @param browser
	 * @param environment
	 * @param tenant
	 * @param gui_server
	 * @param user_name
	 * @throws NetworkEnvironmentException
	 */
	@Parameters({ "browser", "environment", "tenant", "gui_server", "user_name", "wherePlaceZero" })
	@BeforeSuite
	public void init(	@Optional("FIREFOX") String browser,
						@Optional("E4O_QA") String environment,
						@Optional("qa") String tenant, 
						@Optional("actrule") String gui_server,
						@Optional("superman") String user_name,
						@Optional("2") String wherePlaceZero) throws NetworkEnvironmentException {
		
		envPath = "input/environments";
		envFile = environment;
		
		Reporter.log( "Init testing environment with reosurce file : ", PRINT2STDOUT__);
		Reporter.log( "Resource path -> " + envPath, PRINT2STDOUT__);
		Reporter.log( "Resource file -> " + envFile, PRINT2STDOUT__);		

		env = new NetworkEnvironment( envPath, envFile, IOFileUtils.IOLoadingType.RESOURCE);

		Reporter.log( "Startup MySql driver with schema \""+tenant+"\".", PRINT2STDOUT__);
		
		mysql = new Mysql(env.getDataSource(tenant));

		gui = env.getServer(gui_server);
		user = gui.getUser(user_name);	
		
		try {
			wherePlaceT0 = Integer.parseInt(wherePlaceZero);
		} catch ( NumberFormatException r ) {
			// bin value
		}
	}
	
	@BeforeSuite
	protected void testSetupSuite() {

		/**
		 * Common suite section
		 */
		
		if ( startOfTime4Request == null ) {
			startOfTime4Request = new Date(Calendar.getInstance().getTimeInMillis() - wherePlaceT0 * 60_000);
		
			Reporter.log( "###############", PRINT2STDOUT__);
			Reporter.log( "##### XMLRPC request start time is " + new SimpleDateFormat("yyyy-MM-dd @ HH:mm").format(startOfTime4Request), PRINT2STDOUT__);
			Reporter.log( "###############", PRINT2STDOUT__);
		}
		
		if ( xmlrpcLogFolder == null ) {
			
			start4LogFolder = new Date(Calendar.getInstance().getTimeInMillis());
			
			String executionFolder = "execution_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(start4LogFolder);
			
			xmlrpcLogFolder = baseXmlrpcLogFolder + File.separator + executionFolder + File.separator;
			
			Reporter.log( "###############", PRINT2STDOUT__);
			Reporter.log( "##### XMLRPC response message are stored into folder "+ executionFolder, PRINT2STDOUT__);
			Reporter.log( "###############", PRINT2STDOUT__);
		}

	}	
	
	@BeforeMethod
	protected void testSetup() {

		/**
		 * Specific test method section
		 */
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		testTime = sdf.format(new Date());
	}
	
}
