package com.lumata.expression.operators.testing.fake;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.lumata.expression.operators.bdr.BDR;
import com.lumata.expression.operators.gui.campaigns.CampaignModelForm.CMAction;
import com.lumata.expression.operators.gui.campaigns.CampaignModelForm.CMEventType;
import com.lumata.expression.operators.testing.general.TestCampaignModel;

public class TestFake {

	private static final Logger logger = LoggerFactory.getLogger( TestFake.class );
	
	/*
	@Test
	public static void now() {
		
		Date now = new Date();    
	    SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-mm-dd HH:mm:ss" );   
	    String sdate=sdf.format(now).toString();  
		System.out.println( sdate );
		
		Assert.assertTrue( false );
		
	}
	*/
	
	@Test
	public static void bdr() {
		
		//BDR bdr = new BDR( "1;;3;" );
		logger.info( CMEventType.REVENUE.getID() );
		
		CMAction.COMMODITIES.setValue( false );
		
		logger.info( String.valueOf( CMAction.COMMODITIES.getValue() ) );
				
		logger.info( CMAction.TOKENS.getID() );
		//Assert.assertTrue( false );
		
	}
	
	/*
	@Test
	public void loadConfiguration() throws EnvironmentException {
		
		Environment env = new Environment( "input/environments", "E4O_QA", EnvLoadingType.RESOURCE );
		//Mysql mysql = new Mysql( env.getDataSource( "qa" ) );
		
	}
	*/
	
}
