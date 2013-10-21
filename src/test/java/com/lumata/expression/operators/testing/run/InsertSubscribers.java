package com.lumata.expression.operators.testing.run;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.DataModelException;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.testing.a.TestClassGenerator;

public class InsertSubscribers {

private static final Logger logger = LoggerFactory.getLogger( InsertSubscribers.class );
	
	Environment env;
	Mysql mysql;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "tenant" })
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("qa") String tenant ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		mysql = new Mysql( env.getDataSource( tenant ) );
		
	}
		
	@Test( priority = 2, enabled = false )
	public void insertSubscribers() throws DataModelException, IOFileException, SQLException {
		
		logger.info( Log.PUTTING.createMessage( "insertSubscribers" , "Insert Subscribers" ) );

		StringBuilder query = new StringBuilder();
		
		Map<String, String> subscribers = new HashMap<String, String>() {
			
			{
				put( "331234511", "2012-10-01" );
				put( "331234512", "2012-10-01" );
				put( "331234513", "2012-10-01" );
				put( "331234501", "2013-01-01" );
				put( "331234502", "2013-01-01" );
				put( "331234503", "2013-01-01" );
				put( "331234551", "2013-05-01" );
				put( "331234552", "2013-05-01" );
				put( "331234553", "2013-05-01" );
				put( "331234571", "2013-09-01" );
				put( "331234572", "2013-09-01" );
				put( "331234573", "2013-09-01" );
				put( "331234581", "2013-10-01" );
				put( "331234582", "2013-10-01" );
				put( "331234583", "2013-10-01" );
				put( "331234591", "2013-10-04" );
								
			}			
			
		};

		
		
		for (Map.Entry<String, String> subscriber : subscribers.entrySet()) {
		    
		    query = InsertSubscribers.getInsertSubscriberQuery( subscriber.getKey(), subscriber.getValue() );
			mysql.execUpdate( query.toString() );
		    
		    query = InsertSubscribers.getDailyPrepaidQuery( subscriber.getKey(), subscriber.getValue() );
		    mysql.execUpdate( query.toString() );
		    
		    query = InsertSubscribers.getDailySubsQuery( subscriber.getKey(), subscriber.getValue() );
		    mysql.execUpdate( query.toString() );
		    
		}
		
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
				.append( "NULL, " )
				.append( "NULL, " )
				.append( "'ENG', " )
				.append( "NULL, " )
				.append( "NULL, " )
				.append( "'QAIN', " )
				.append( "NOW() );" );
		
		return query;		
		
	}
	
	public static StringBuilder getDailyPrepaidQuery( String msisdn, String subscription_date ) {
		
		StringBuilder query = new StringBuilder();
			
		query.append( "insert into daily_prepaid (" )
				.append( "msisdn, " )
				.append( "amount_usage, " )
				.append( "balance_main_account, " )
				.append( "qty_recharge, " )
				.append( "amount_recharge, " )
				.append( "validity_date, " )
				.append( "deactivation_date, " )
				.append( "qty_rate_plan_id_change, " )
				.append( "qty_status_id_change, " )
				.append( "agg_date, " )
				.append( "update_time ) " )
				.append( "value (" )
				.append( "'" ).append( msisdn ).append( "', " )
				.append( "NULL, " )
				.append( "NULL, " )
				.append( "NULL, " )
				.append( "NULL, " )
				.append( "NULL, " )
				.append( "NULL, " )
				.append( "1, " )
				.append( "1, " )
				.append( "'" ).append( subscription_date ).append( "', " )
				.append( "NOW() );" );
		
		return query;		
		
	}
	
	public static StringBuilder getDailySubsQuery( String msisdn, String subscription_date ) {
		
		StringBuilder query = new StringBuilder();
			
		query.append( "insert into daily_subs (" )
				.append( "msisdn, " )
				.append( "profile_id, " )
				.append( "rate_plan_id, " )
				.append( "status_id, " )
				.append( "network_id, " )
				.append( "ucg, " )
				.append( "agg_date, " )
				.append( "update_time ) " )
				.append( "value (" )
				.append( "'" ).append( msisdn ).append( "', " )
				.append( "2, " )
				.append( "1, " )
				.append( "1, " )
				.append( "NULL, " )
				.append( "NULL, " )
				.append( "'" ).append( subscription_date ).append( "', " )
				.append( "NOW() );" );
		
		return query;		
		
	}
	
	@Test( priority = 3, enabled = true )
	public void createCDR() throws IOFileException {
		
		StringBuilder cdr = new StringBuilder();
		
		cdr.append( createCDRByMonthAndYear( "33123451", ( Calendar.getInstance().get(Calendar.YEAR) - 1 ), Calendar.NOVEMBER, true ) )
			.append( createCDRByMonthAndYear( "33123455", Calendar.getInstance().get(Calendar.YEAR), Calendar.MAY, true ) )
			.append( createCDRByMonthAndYear( "33123457", Calendar.getInstance().get(Calendar.YEAR), Calendar.SEPTEMBER, true ) )
			.append( createCDRByMonthAndYear( "33123458", Calendar.getInstance().get(Calendar.YEAR), Calendar.OCTOBER, true ) )
			.append( createCDRByMonthAndYear( "33123459", Calendar.getInstance().get(Calendar.YEAR), Calendar.OCTOBER, true ) )
			.append( createCDRByMonthAndYear( "33123450", Calendar.getInstance().get(Calendar.YEAR), Calendar.JANUARY, false ) )
			.append( createCDRByMonthAndYear( "33123450", Calendar.getInstance().get(Calendar.YEAR), Calendar.FEBRUARY, false ) )
			.append( createCDRByMonthAndYear( "33123450", Calendar.getInstance().get(Calendar.YEAR), Calendar.MARCH, false ) )
			.append( createCDRByMonthAndYear( "33123450", Calendar.getInstance().get(Calendar.YEAR), Calendar.APRIL, false ) )
			.append( createCDRByMonthAndYear( "33123450", Calendar.getInstance().get(Calendar.YEAR), Calendar.MAY, false ) )
			.append( createCDRByMonthAndYear( "33123450", Calendar.getInstance().get(Calendar.YEAR), Calendar.JUNE, false ) )
			.append( createCDRByMonthAndYear( "33123450", Calendar.getInstance().get(Calendar.YEAR), Calendar.JULY, false ) )
			.append( createCDRByMonthAndYear( "33123450", Calendar.getInstance().get(Calendar.YEAR), Calendar.AUGUST, false ) )
			.append( createCDRByMonthAndYear( "33123450", Calendar.getInstance().get(Calendar.YEAR), Calendar.SEPTEMBER, false ) )
			.append( createCDRByMonthAndYear( "33123450", Calendar.getInstance().get(Calendar.YEAR), Calendar.OCTOBER, false ) )
			;
				
		IOFileUtils.saveResource( cdr.toString(), "output/cdr", "cdr_test_2.txt" );
		
	}
	
	@Test( enabled = false )
	public void test() {
		
		Calendar today = Calendar.getInstance();
		today.setTime( new Date() );
		System.out.println( today.get( Calendar.DAY_OF_MONTH ) );
		
		Calendar reference_date = Calendar.getInstance();
		int year = 2009;
		int month = Calendar.FEBRUARY;
		int date = 1;
		
		reference_date.set(year, month, date);
		
		int maxDay = reference_date.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		System.out.println( maxDay );
		
		System.out.println( reference_date.get( Calendar.YEAR ) );
		
		//createCDRByMonthAndYear( "33123451", Calendar.YEAR, Calendar.SEPTEMBER, true );
		
		/*
		Calendar calendar = Calendar.getInstance();

		int year = 2009;
		int month = Calendar.FEBRUARY;
		int date = 1;
		
		calendar.set(year, month, date);
		
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println("Max Day: " + maxDay);
		
		calendar.set(2004, Calendar.FEBRUARY, 1);
		maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println("Max Day: " + maxDay);

		System.out.println( "### Months" );
		System.out.println( Calendar.JANUARY );
		System.out.println( Calendar.FEBRUARY );
		System.out.println( Calendar.MARCH );
		System.out.println( Calendar.APRIL );
		System.out.println( Calendar.MAY );
		System.out.println( Calendar.JUNE );
		System.out.println( Calendar.JULY );
		System.out.println( Calendar.AUGUST );
		System.out.println( Calendar.SEPTEMBER );
		System.out.println( Calendar.OCTOBER );
		System.out.println( Calendar.NOVEMBER );
		System.out.println( Calendar.DECEMBER );
		*/
	}
	
	public StringBuilder createCDRByMonthAndYear( String msisdn_prefix, int year, int month, boolean use_current_day ) {
		
		final int MILLISECONDS_IN_DAY = 1000 * 60 * 60 * 24;
		
		final String[] bundles = { "SMS_1000", "DATA_3GB"};
				
		StringBuilder cdr = new StringBuilder();
		
		Calendar today = Calendar.getInstance();
		today.setTime( new Date() );
		int current_day = today.get( Calendar.DAY_OF_MONTH );
		
		Calendar computation_date = Calendar.getInstance();
		computation_date.set( year, month, 1 );
		int computation_day = computation_date.getActualMaximum(Calendar.DAY_OF_MONTH);
			
		if( use_current_day || ( computation_date.get( Calendar.MONTH ) == today.get( Calendar.MONTH ) ) ) {
			
			computation_day = current_day;
					
		}
		
		computation_date.set( year, month, computation_day );
		
		month++;
						
		for( int i = 1; i <= computation_date.get( Calendar.DAY_OF_MONTH ); i++ ) {
						
			int[] bundle_balance = new int[ 3 ];
			bundle_balance[ 0 ] = 10;
			bundle_balance[ 1 ] = 10 * i;
			bundle_balance[ 2 ] = 100;
					
			for( int j = 1; j <= 3; j++ ) {
							
				StringBuilder msisdn = new StringBuilder(); 
						
				msisdn.append( msisdn_prefix ).append( j );
				
				for( int b = 0; b < ( j == 3 ? 1 : bundles.length ); b++ ) {
					
					int random_value = (int)(Math.random() * 5) + 1;
					
					for( int r = 1; r <= random_value; r++ ) {
						
						cdr.append( msisdn )
							.append("|")
							.append( computation_date.get( Calendar.YEAR ) )
							.append( "-" ).append( ( month < 10 ? "0" + month : month ) )
							.append( "-" ).append( ( i < 10 ? "0" + i : i ))
							.append( "|")
							.append( bundles[ b ] )
							.append( "|" )
							.append( ( bundle_balance[ j - 1 ] * random_value )  )
							.append( "|TRUE\n" );
					
					}	
						
				}
			
			}
			
		}
		
		return cdr;
		
	}
	
	@AfterSuite
	public void end() {
		
		mysql.close();
		
	}
	
}
