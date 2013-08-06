package com.lumata.expression.operators.testing.general;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.system.Environment.EnvLoadingType;
import com.lumata.expression.operators.offermanagement.OfferOptimReservation;
import com.lumata.expression.operators.offermanagement.OfferOptimReservationList;

public class TestOfferReservation {

private static final Logger logger = LoggerFactory.getLogger(TestOfferReservation.class);
	
	SeleniumWebDriver seleniumWebDriver; 
	Environment env;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "user"})
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("superman") String user ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, EnvLoadingType.RESOURCE );
		
	}
	
	@Parameters({"tenant1"})
	@Test()
	public void getOfferReservationList( @Optional("qa") String tenant1 ) {

		OfferOptimReservation or = new OfferOptimReservation( -1, 5 , 0, 0, 0);
		
		or.insert(env, tenant1);
		
		//OfferOptimReservationList offerResList = new OfferOptimReservationList(env, tenant1, null);

		//OfferOptimReservationList offerResList = new OfferOptimReservationList(env, tenant1, null);
		
		//logger.info( offerResList.toString() );
		
		/*
		ArrayList<OfferOptimReservation> offReservationList = OfferOptimReservation.getOfferReservationList(env, tenant1, null); 
		
		for( int i = 0; i < offReservationList.size(); i++ ) {
			
			logger.info( offReservationList.get( i ).toString() );
			
		}
		*/
		
		
    }
	
}
