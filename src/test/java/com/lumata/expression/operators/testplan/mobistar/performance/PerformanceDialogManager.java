package com.lumata.expression.operators.testplan.mobistar.performance;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;


public class PerformanceDialogManager {

	private static final Logger logger = LoggerFactory.getLogger( PerformanceDialogManager.class );
	
	Environment env;	
	Connection connection = null;
	Session session = null;
	Destination destination = null;
	MessageProducer producer = null;
	
	/* 	Initialize System */
	@Parameters({"environment", "tenant"})
	@BeforeClass
	public void init( @Optional("E4O_DEV") String environment, @Optional("tenant") String tenant ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		//env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
						
		
	
	
	}
	
	
}
