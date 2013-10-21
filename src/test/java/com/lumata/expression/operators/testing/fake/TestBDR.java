package com.lumata.expression.operators.testing.fake;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.system.RemoteService;
import com.lumata.expression.operators.bdr.BDR;
import com.lumata.expression.operators.bdr.BDRList;


public class TestBDR {

	private static final Logger logger = LoggerFactory.getLogger( TestBDR.class );
	
	@Parameters({"environment"})
	@Test
	public static void bdr( @Optional("E4O_QA") String environment ) throws EnvironmentException {
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		Environment env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		BDRList bdrList = new BDRList( new RemoteService( env.getServiceType( Environment.ServicesType.SSH )), new Date() );
		
		System.out.println( "All bdr: " + bdrList.size() );
		
		ArrayList<BDR> bdrs = bdrList.getBy( "format_id" , "test" );
		
		System.out.println( "BDR by type: " + bdrs.size() );
		
		
		
		
		/*
		BDR bdr = new BDR( "1;;3;5;;6" );
		
		System.out.println( "BDR: " + bdr.getFormatID() );
		System.out.println( "BDR: " + bdr.getModule() );
		
		//Assert.assertTrue( false );
		*/
		
		/*
		BDRList bdrList = new BDRList(); 
		
		Date date = new Date();
		
		System.out.println( bdrList.generateBDRFileName( date ) );
		*/
		
	}
	
}
