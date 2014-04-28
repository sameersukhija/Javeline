package com.lumata.common.testing.plan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.DataModelException;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.generators.ClassGenerator;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;

@Test( enabled = false )
public class TestClassGenerator {

	private static final Logger logger = LoggerFactory.getLogger( TestClassGenerator.class );
	
	Environment env;
	ClassGenerator generatorDAO;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment"})
	@BeforeClass
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		System.out.println( environment );
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		generatorDAO = new ClassGenerator();
						
	}
	
	@Parameters({"tenant"})
	@Test( enabled = false )
	public void createDAO( @Optional("qa") String tenant ) throws DataModelException, IOFileException {
		
		logger.info( Log.PUTTING.createMessage( "createDAO" , "Create DAO Classes" ) );
				
		generatorDAO.createDAO( env, tenant, "com.lumata.common.testing.generators.container" );
				
	}		
	
}
