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
	 * This object describe the origin of time for current test execution
	 */
	protected static Date startOfTime4Suite = null;
	
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
	 * Print to standard output during execution
	 */
	protected static final Boolean PRINT2STDOUT__ = Boolean.TRUE;
	
	private static String envPath = null;
	private static String envFile = null;

	/**
	 * 
	 * @param browser
	 * @param environment
	 * @param tenant
	 * @param gui_server
	 * @param user_name
	 * @throws NetworkEnvironmentException
	 */
	@Parameters({ "browser", "environment", "tenant", "gui_server", "user_name" })
	@BeforeSuite
	public void init(	@Optional("FIREFOX") String browser,
						@Optional("E4O_QA") String environment,
						@Optional("qa") String tenant, 
						@Optional("actrule") String gui_server,
						@Optional("superman") String user_name	) throws NetworkEnvironmentException {
		
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
	}
	
	@BeforeMethod
	protected void testSetup() {

		/**
		 * Common suite section
		 */
		
		if ( startOfTime4Suite == null )
			startOfTime4Suite = new Date(Calendar.getInstance().getTimeInMillis() - 60_000);
		
		if ( xmlrpcLogFolder == null ) {
			xmlrpcLogFolder = baseXmlrpcLogFolder + File.separator + "execution_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(startOfTime4Suite) + File.separator;
			
			Reporter.log( "###############", PRINT2STDOUT__);
			Reporter.log( "##### XMLRPC response message are stored into folder "+ "execution_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(startOfTime4Suite), PRINT2STDOUT__);
			Reporter.log( "###############", PRINT2STDOUT__);
		}

		/**
		 * Specific test method section
		 */
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		testTime = sdf.format(new Date());
	}	
	
}
