package com.lumata.e4o.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;

import static com.lumata.common.testing.orm.Query.select;

import com.lumata.common.testing.system.Environment;
import com.lumata.e4o.system.cdr.types.CDRRevenue;
import com.lumata.e4o_tenant.schema.Subscribers;
import com.lumata.expression.operators.exceptions.CDRException;

public class GenerateCDRRevenue {
	
	private static final Logger logger = LoggerFactory.getLogger( GenerateCDRRevenue.class );
	
	private final boolean generate_cdr = true;
		
	Environment env;	
	String tenant;
	Mysql mysql;
	ArrayList<Long> subscribers;
	
	final Integer CDR_LINES = 10;
	
	/* 	Initialize Environment */
	@Parameters({"environment", "tenant"})
	@BeforeSuite
	public void init( @Optional("E4O_VM") String environment, @Optional("tenant") String tenant  ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		this.tenant = tenant;
		
		mysql = new Mysql( env.getDataSource( tenant ) );
						
	}
	
	/**
	 * load subscribers from customer base	 * 
	 * 
	 * @throws SQLException
	 */
	@Test( priority = 1, enabled = generate_cdr )
	public void load_subscribers() throws SQLException  {
	
		logger.info( Log.GETTING.createMessage( "subscribers from customer base" ) );
		
		subscribers = new ArrayList<Long>();
		
		Subscribers subscribersTable = new Subscribers();
		
		String query = select( Subscribers.Fields.msisdn ).from( subscribersTable ).build();
		
		ResultSet rs = mysql.execQuery( query );
		
		while( rs.next() ) {
			
			subscribers.add( rs.getLong( Subscribers.Fields.msisdn.name() ) );
			
		}
		
		Assert.assertTrue( subscribers.size() > 0 );
		
	}
	
	/**
	 * cdr file creation strategy
	 * 
	 * msisdn: random from customer base
	 * date: fixed equals to today
	 * amount: random
	 * balance: random
	 * validity_date: fixed equals to today
	 * deactivation_date: fixed equals to today
	 * type: random
	 * delay: random
	 * 
	 * @throws IOFileException
	 * @throws CDRException
	 */	
	@Test( priority = 2, enabled = generate_cdr )
	public void cdr_revenue_generation_with_random_msisdn() throws IOFileException, CDRException {
		
		logger.info( Log.PUTTING.createMessage( "New cdr revenue csv file" ) );
						
		CDRRevenue cdrRevenue = new CDRRevenue();
		
		// today
		Calendar date = Calendar.getInstance();		
		
		Calendar validity_date = Calendar.getInstance();
		validity_date.set( Calendar.YEAR, ( date.get( Calendar.YEAR ) + 2 ) );
		
		Calendar deactivation_date = Calendar.getInstance();
		deactivation_date.set( Calendar.YEAR, ( date.get( Calendar.YEAR ) + 3 ) );
		
		logger.info( Log.PUTTING.createMessage( "CDR Revenue configuration" ) );
		cdrRevenue.setDateStrategyFixed( date );
		cdrRevenue.setAmountStrategyRandom( 100L, 1000L );
		cdrRevenue.setBalanceStrategyRandom( 300L, 3000L );
		cdrRevenue.setValidityDateStrategyFixed( validity_date );
		cdrRevenue.setDeactivationDateStrategyFixed( deactivation_date );
		cdrRevenue.setTypeStrategyRandom();
		cdrRevenue.setDelayStrategyRandom( 500L, 5000L );
		
		for( int s = 0; s < CDR_LINES; s++ ) {
			
			int subscriber_index = (int)(Math.random() * subscribers.size() );
			
			long subscriber = subscribers.get( subscriber_index );
			
			cdrRevenue.setMsisdnStrategyFixed( subscriber );
			
			cdrRevenue.addLine();
			
		}
		
		//cdrRevenue.print();
		
		store_file( cdrRevenue );
		
		send_file( cdrRevenue );
		
	}
	
	/**
	 * cdr file creation strategy
	 * 
	 * msisdn: fixed from customer base
	 * date: fixed equals to today
	 * amount: random
	 * balance: random
	 * type: random
	 * delay: random
	 * 
	 * @throws IOFileException
	 * @throws CDRException
	 */	
	@Test( priority = 3, enabled = false /*generate_cdr*/ )
	public void cdr_revenue_generation_with_fixed_msisdn() throws IOFileException, CDRException {
		
		logger.info( Log.PUTTING.createMessage( "New cdr revenue csv file" ) );
						
		CDRRevenue cdrRevenue = new CDRRevenue();
		
		// today
		Calendar date = Calendar.getInstance();
		
		logger.info( Log.PUTTING.createMessage( "CDR Revenue configuration" ) );
		cdrRevenue.setMsisdnStrategyFixed( 3399900001L );
		cdrRevenue.setDateStrategyFixed( date );
		cdrRevenue.setAmountStrategyRandom( 100L, 1000L );
		cdrRevenue.setBalanceStrategyRandom( 300L, 3000L );
		cdrRevenue.setValidityDateStrategyFixed( date );
		cdrRevenue.setDeactivationDateStrategyFixed( date );
		cdrRevenue.setTypeStrategyRandom();
		cdrRevenue.setDelayStrategyRandom( 500L, 5000L );
		
		cdrRevenue.addLines( CDR_LINES );
				
		//cdrRevenue.print();
		
		store_file( cdrRevenue );
		
		send_file( cdrRevenue );
				
	}
	
	private void store_file( CDRRevenue cdrRevenue ) throws IOFileException {
		
		cdrRevenue.setPath( "output/demo/cdr", cdrRevenue.generateFileName() );
		
		cdrRevenue.save();
		
		logger.info( Log.PUTTING.createMessage( "The cdr file " + cdrRevenue.generateFileName() + " has been stored in " + cdrRevenue.getDir() + " folder" ) );
		
	}
	
	private void send_file( CDRRevenue cdrRevenue ) {
		
		cdrRevenue.send( env, "/nfsdata/files/cdr/deposit/REVENUE_CDR/" );
		
		logger.info( Log.PUTTING.createMessage( "The cdr file " + cdrRevenue.generateFileName() + " has been sent to remote host" ) );
		
	}
	
}
