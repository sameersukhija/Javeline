package com.lumata.expression.operators.testing.fake;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.lumata.expression.operators.bdr.BDR;

public class TestFake {

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
		
		BDR bdr = new BDR( "1;;3;" );
		
		
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
