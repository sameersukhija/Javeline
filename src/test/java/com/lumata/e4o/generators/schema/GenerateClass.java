package com.lumata.e4o.generators.schema;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.DataModelException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.generators.ClassGenerator;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.NetworkEnvironment;


public class GenerateClass {

	private static final Logger logger = LoggerFactory.getLogger( GenerateClass.class );
	
	//Environment env;
	NetworkEnvironment env;
	ClassGenerator generatorDAO;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment"})
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_VM") String environment ) throws NetworkEnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		//env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		generatorDAO = new ClassGenerator();
						
	}
	
	@Parameters({"tenant"})
	@Test()
	public void createDAO( @Optional("tenant") String tenant ) throws DataModelException, IOFileException {
		
		logger.info( Log.PUTTING.createMessage( "createDAO" , "Create DAO Classes" ) );
				
		generatorDAO.createDAO( env, tenant, "com.lumata.e4o.schema.tenant" );
				
	}		
	
}
