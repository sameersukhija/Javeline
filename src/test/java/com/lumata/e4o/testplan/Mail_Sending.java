package com.lumata.e4o.testplan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
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

public class Mail_Sending {
	
	private static final Logger logger = LoggerFactory.getLogger( Mail_Sending.class );
	
	Environment env;
	String tenant;
	Mysql mysql_e4o_global;
	Mysql mysql_e4o_tenant;
	Mysql mysql_dm;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "tenant", "user"})
	@BeforeSuite( enabled = false )
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("tenant") String tenant, @Optional("superman") String user ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		System.out.println( environment );
				
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		/*
		mysql_e4o_global = new Mysql( env.getDataSource( tenant ) );
		
		mysql_e4o_tenant = new Mysql( env.getDataSource( tenant ) );
		
		mysql_dm = new Mysql( env.getDataSource( "dm" ) );
		*/
	}
	
	@Parameters({"environment"})
	@Test( enabled = true )
	public void test1( @Optional("E4O_QA") String environment ) {
		System.out.println( "ENVVVVVVV: " + environment );
		Assert.assertTrue( true );
	}	
	
	
	/*
	// CDR using
	@Test( enabled = generate_cdr )
	public void cdr_history_strategies() throws IOFileException, CDRException {
		
		CDRHistory c = new CDRHistory();
		
		System.out.println( "MSISDN Fixed Strategy" );
		c.setMsisdnStrategyFixed( 3399900001L );
		c.add( 5 );
		c.print();
		c.clean();
		
		System.out.println( "MSISDN Increment Strategy" );
		c.setMsisdnStrategyIncrement( 3399900001L, 20 );
		c.add( 5 );
		c.print();
		c.clean();
		
		System.out.println( "MSISDN Random Strategy" );
		c.setMsisdnStrategyRandom( 3399900010L, 3399910000L );
		c.add( 5 );
		c.print();
		c.clean();
		
		System.out.println( "MSISDN options setting" );
		c.setMsisdnOptions( 39, 19 );
		
		System.out.println( "MSISDN Fixed Strategy with options" );
		c.setMsisdnStrategyFixed( 3399900001L );
		c.add( 5 );
		c.print();
		c.clean();
		
		System.out.println( "MSISDN Increment Strategy with options" );
		c.setMsisdnStrategyIncrement( 3399900001L, 20 );
		c.add( 5 );
		c.print();
		c.clean();
		
		System.out.println( "MSISDN Random Strategy with options" );
		c.setMsisdnStrategyRandom( 3399900010L, 3399910000L );
		c.add( 5 );
		c.print();
		c.clean();		
				
	}
	
	//@Test( enabled = generate_cdr )
	@Test( enabled = false )
	public void cdr_revenue_strategies() throws IOFileException, CDRException {
		
		CDRRevenue c = new CDRRevenue();
		Calendar d = Calendar.getInstance();
		
		System.out.println( "MSISDN Fixed Strategy" );
		c.setMsisdnStrategyFixed( 3399900001L );
		
		c.add( 5 );
		c.print();
		c.clean();
		
		System.out.println( "MSISDN Increment Strategy" );
		c.setMsisdnStrategyIncrement( 3399900001L, 20 );
		c.add( 5 );
		c.print();
		c.clean();
		
		System.out.println( "MSISDN Random Strategy" );
		c.setMsisdnStrategyRandom( 3399900010L, 3399910000L );
		c.add( 5 );
		c.print();
		c.clean();
		
		System.out.println( "MSISDN options setting" );
		c.setMsisdnOptions( 39, 19 );
		
		System.out.println( "MSISDN Fixed Strategy with options" );
		c.setMsisdnStrategyFixed( 3399900001L );
		c.add( 5 );
		c.print();
		c.clean();
		
		System.out.println( "MSISDN Increment Strategy with options" );
		c.setMsisdnStrategyIncrement( 3399900001L, 20 );
		c.add( 5 );
		c.print();
		c.clean();
		
		System.out.println( "MSISDN Random Strategy with options" );
		c.setMsisdnStrategyRandom( 3399900010L, 3399910000L );
		c.add( 5 );
		c.print();
		c.clean();		
				
	}
	
	
	
	@Test( enabled = true )
	public void set() {
		
		
		
	}
	*/
	
	@AfterSuite
	public void end() {
		
		mysql_e4o_global.close();
		
		mysql_e4o_tenant.close();
		
		mysql_dm.close();
		
	}	
	
}
