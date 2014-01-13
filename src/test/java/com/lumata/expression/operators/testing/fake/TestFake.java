package com.lumata.expression.operators.testing.fake;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
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
	
	@Test()
	public void method1() {
		System.out.println( 1 );		
	}
	
	@Test()
	public void method2() {
		
		try {
			Thread.sleep( 2000 );
		} catch(  InterruptedException e ) {}
		System.out.println( 2 );		
	}
	
	@Test()
	public void method3() {
		System.out.println( 3 );		
	}
	
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
	
	/*
	@Test
	public static void bdr() {
		
		
		String s = "NULL";
		
		Integer.valueOf( (( s != "NULL" && s != "" ) ? s: "-1" ) );
		
		/*
		JSONArray ja = new JSONArray();
		
		ja.put( 1 );
		
		System.out.println(ja.toString());
		ArrayList<Integer> a = new ArrayList<Integer>( );
		*/
		
		
		//BDR bdr = new BDR( "1;;3;" );
		/*
		logger.info( CMEventType.REVENUE.getID() );
		
		CMAction.COMMODITIES.setValue( false );
		
		logger.info( String.valueOf( CMAction.COMMODITIES.getValue() ) );
				
		logger.info( CMAction.TOKENS.getID() );
		*/
		//Assert.assertTrue( false );
		
	//}
	
	/*
	@Test
	public void loadConfiguration() throws EnvironmentException {
		
		Environment env = new Environment( "input/environments", "E4O_QA", EnvLoadingType.RESOURCE );
		//Mysql mysql = new Mysql( env.getDataSource( "qa" ) );
		
	}
	*/
	
}
