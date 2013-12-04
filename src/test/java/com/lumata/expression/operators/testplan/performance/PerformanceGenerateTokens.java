package com.lumata.expression.operators.testplan.performance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.exceptions.CommoditiesException;
import com.lumata.expression.operators.performance.GenerateTokenThread;

public class PerformanceGenerateTokens {

	private static final Logger logger = LoggerFactory.getLogger( PerformanceGenerateTokens.class );
	
	private int TIMEOUT = 60000;
	private int ATTEMPT_TIMEOUT = 500;
	
	Environment env;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "tenant", "user"})
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("qa") String tenant, @Optional("superman") String user ) throws EnvironmentException, CommoditiesException, JSONSException, IOFileException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		// Create environment configuration
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
	}	
	
	@Parameters({"tenant"})
	@Test(enabled=true, priority = 1)
	public void generateTokens( @Optional("qa") String tenant ) {

		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		
		GenerateTokenThread one = new GenerateTokenThread(Thread.MAX_PRIORITY, env);
			
		GenerateTokenThread two = new GenerateTokenThread(Thread.MAX_PRIORITY, env);
		
		one.startThread();
		
		two.startThread();
		
		long startTime = System.currentTimeMillis();
		
		try {
		
			Thread.sleep(10000);
		
		} catch (Exception e){}
		
		one.stopThread();
		two.stopThread();
				
        try {
    		
			Thread.sleep(5000);
		
		} catch (Exception e){}
        
        long diffTime = System.currentTimeMillis() - startTime;  
        int decSeconds = (int)(diffTime % 1000 / 100);  
        int seconds = (int)(diffTime / 1000 % 60);  
        int minutes = (int)(diffTime / 60000 % 60);  
        int hours = (int)(diffTime / 3600000);
        String s = String.format("%d:%02d:%02d.%d", hours, minutes, seconds, decSeconds);
        
        logger.info( s );
        int total = one.getRequestCount() + two.getRequestCount();
        logger.info( "Total: " + total + " - Requests (1): " + one.getRequestCount() + " - Requests (2): " + two.getRequestCount() );
		
    }
	
}
