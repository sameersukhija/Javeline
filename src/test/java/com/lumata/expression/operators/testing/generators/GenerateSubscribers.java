package com.lumata.expression.operators.testing.generators;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;

public class GenerateSubscribers {

	private static final Logger logger = LoggerFactory.getLogger( GenerateSubscribers.class );
	
	Environment env;
	Mysql mysql;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "tenant" })
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("qa") String tenant ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		mysql = new Mysql( env.getDataSource( tenant ) );
		System.out.println( "TENANT: " + env.getDataSource( tenant ).toString() );				
	}
	
	@Test( priority = 1, enabled = true )
	public void generateSubscribers() {
		
		// Number of subscribers to generate
		final int SUBSCRIBERS_TO_GENERATE = 2;
		
		// Max MSISDN length
		int MSISDN_MAX_LENGTH = 10;
		
		// MSISDN PREFIX
		final String SUBSCRIBER_PREFIX = "33123";
		
		logger.info( Log.PUTTING.createMessage( "generateSubscribers" , "Insert Subscribers" ) );
			
		final int SUBSCRIBERS_PREFIX_DIGITS = (int)( Math.log10( Integer.valueOf( SUBSCRIBER_PREFIX ) ) + 1 );
		final int SUBSCRIBERS_TO_GENERATE_DIGITS = (int)( Math.log10( SUBSCRIBERS_TO_GENERATE ) + 1 );
		
		final int MSISDN_LENGTH = SUBSCRIBERS_PREFIX_DIGITS + SUBSCRIBERS_TO_GENERATE_DIGITS;
		
		if( MSISDN_MAX_LENGTH < MSISDN_LENGTH ) { MSISDN_MAX_LENGTH = MSISDN_LENGTH; }
		
		StringBuilder query = new StringBuilder();
				
		for( int i = 1; i <= SUBSCRIBERS_TO_GENERATE; i++ ) {
		
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	        String subscription_date = DATE_FORMAT.format( new Date() );
			
			String msisdn = SUBSCRIBER_PREFIX + String.format( "%0" + ( MSISDN_MAX_LENGTH - SUBSCRIBERS_PREFIX_DIGITS - (int)( Math.log10( i ) ) ) + "d" , i );
			
			query = GenerateSubscribers.getInsertSubscriberQuery( msisdn, subscription_date);
			mysql.execUpdate( query.toString() );
			
			query = GenerateSubscribers.getInsertSubsNotifQuery( msisdn, 1 );
			mysql.execUpdate( query.toString() );
			
		}
				
	}
	
	@AfterSuite
	public void end() throws EnvironmentException {		
		
		mysql.close();
						
	}
	
	public static StringBuilder getInsertSubscriberQuery( String msisdn, String subscription_date ) {
		
		StringBuilder query = new StringBuilder();
			
		query.append( "insert into subscribers (" )
				.append( "msisdn, " )
				.append( "subscription_date, " )
				.append( "profile_id, " )
				.append( "rate_plan_id, " )
				.append( "status_id, " )
				.append( "service_id_list, " )
				.append( "channel_id_list, " )
				.append( "network_id, " )
				.append( "tongue, " )
				.append( "ucg, " )
				.append( "ucg_start_date, " )
				.append( "in_tag, " )
				.append( "update_time ) " )
				.append( "value (" )
				.append( "'" ).append( msisdn ).append( "', " )
				.append( "'" ).append( subscription_date ).append( "', " )
				.append( "2, " )
				.append( "1, " )
				.append( "1, " )
				.append( "NULL, " )
				.append( "1, " )
				.append( "NULL, " )
				.append( "'ENG', " )
				.append( "NULL, " )
				.append( "NULL, " )
				.append( "'QAIN', " )
				.append( "NOW() );" );
		
		return query;		
		
	}
	
	public static StringBuilder getInsertSubsNotifQuery( String msisdn, int channel_id ) {
		
		StringBuilder query = new StringBuilder();
			
		query.append( "insert into subs_notif (" )
				.append( "msisdn, " )
				.append( "channel_id, " )
				.append( "address ) " )
				.append( "value (" )
				.append( msisdn ).append( ", " )
				.append( channel_id ).append( ", " )
				.append( "'" ).append( msisdn ).append( "' );" );
		
		return query;
	
	}	
	
}
