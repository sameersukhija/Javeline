package com.lumata.e4o.generators.subscribers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.exceptions.GeneratorException;
import com.lumata.e4o.generators.common.Generator;


public class GenerateSubscribersOptions {

	private static final Logger logger = LoggerFactory.getLogger( GenerateSubscribersOptions.class );
	
	final boolean GENERATE_OPTIONS = true;
	
	NetworkEnvironment env;	
	Server guiServer;
	User superman;
	Mysql mysql;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "tenant"})
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("tenant") String tenant ) throws NetworkEnvironmentException {		
		
		logger.debug( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
			
		guiServer = env.getServer( "actrule" );
		
		superman = guiServer.getUser( "superman" );
		
		mysql = new Mysql( env.getDataSource( tenant ) );
			
	}

	@Test( enabled = GENERATE_OPTIONS )
	public void generateOptions() throws GeneratorException {
		
		final Long SUBSCRIBERS_OPTIONS_TO_GENERATE = 2000L;		
		
		Generator.subscribers()
					.environment( env )
					.mysql( mysql )
					.insertOptions( "option_", SUBSCRIBERS_OPTIONS_TO_GENERATE );
		
	}
	
	@AfterSuite
	public void end() {
		mysql.close();
	}
	
}
