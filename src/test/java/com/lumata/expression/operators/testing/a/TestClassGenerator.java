package com.lumata.expression.operators.testing.a;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.DataModelException;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.a.ClassGenerator;


public class TestClassGenerator {

	private static final Logger logger = LoggerFactory.getLogger( TestClassGenerator.class );
	
	Environment env;
	ClassGenerator generatorDAO;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment"})
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		generatorDAO = new ClassGenerator();
						
	}
	
	@Parameters({"qa"})
	@Test()
	public void createDAO( @Optional("qa") String tenant ) throws DataModelException, IOFileException {
		
		logger.info( Log.PUTTING.createMessage( "createDAO" , "Create DAO Classes" ) );
				
		generatorDAO.createDAO( env, tenant );
				
	}		
	
}
