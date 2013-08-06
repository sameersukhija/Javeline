package com.lumata.expression.operators.testing.general;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.lumata.common.testing.log.Log;
import com.lumata.expression.operators.exceptions.TrafficGeneratorEventException;
import com.lumata.expression.operators.gui.operations.TrafficGeneratorEvent;
import com.lumata.expression.operators.gui.operations.TrafficGeneratorEvent.TGELoadingType;



public class TestTrafficGeneratorEvent {

	private static final Logger logger = LoggerFactory.getLogger(TestTrafficGeneratorEvent.class);
	
	TrafficGeneratorEvent tge;
	
	/* 	Initialize TrafficGeneratorEvent */
	@BeforeSuite
	public void init() throws TrafficGeneratorEventException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "traffic generator event" ) );
		
		tge = new TrafficGeneratorEvent( "input/traffic_generator", "tg_event_recharge_1", TGELoadingType.RESOURCE  );
		
		Assert.assertNotNull( tge );
		
	}
	
	@Test()
	public void get_properties() {

		Assert.assertEquals( tge.getSubscriberID() , "331234567" );
		
		Assert.assertEquals( tge.getSource() , "HISTORY" );
		
		Assert.assertEquals( tge.getInterpretor() , "HISTORY" );
		
		Assert.assertEquals( tge.getEventClass() , "revenue" );
		
		JSONArray event_parameters;
		
		try {
			
			event_parameters = new JSONArray( "[{ \"name\": \"recharge\", \"value\": \"1\" }]" );
			Assert.assertEquals( tge.getParameters() , event_parameters.toString() );
		
		} catch ( JSONException e ) { logger.error( e.getMessage(), e ); }
		
    }
	
}
