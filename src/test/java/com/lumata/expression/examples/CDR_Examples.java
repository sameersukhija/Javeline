package com.lumata.expression.examples;

import java.util.Calendar;

import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.expression.operators.exceptions.CDRException;
import com.lumata.expression.operators.system.cdr.CDRClassGenerator;
import com.lumata.expression.operators.system.cdr.CDRDate;
import com.lumata.expression.operators.system.cdr.CDRDate.CDRDateFormat;
import com.lumata.expression.operators.system.cdr.CDRDateIncrement;
import com.lumata.expression.operators.system.cdr.types.CDRHistory;
import com.lumata.expression.operators.system.cdr.types.CDRRevenue;

public class CDR_Examples {
	
	private final boolean generate_cdr_classes = false;
	private final boolean generate_cdr = false;
	
	// CDR Types generation
	@Test( enabled = generate_cdr_classes )
	public void create_cdr_subclasses() throws IOFileException {
		
		CDRClassGenerator cdr_generator = new CDRClassGenerator();
		
		cdr_generator.generate();	

	}
	
	// CDR using
	@Test( enabled = generate_cdr )
	public void cdr_history_strategies() throws IOFileException, CDRException {
		
		CDRHistory c = new CDRHistory();
		
		System.out.println( "MSISDN Fixed Strategy" );
		c.setMsisdnStrategyFixed( 3399900001L );
		c.addLines( 5 );
		c.print();
		c.clean();
		
		System.out.println( "MSISDN Increment Strategy" );
		c.setMsisdnStrategyIncrement( 3399900001L, 20 );
		c.addLines( 5 );
		c.print();
		c.clean();
		
		System.out.println( "MSISDN Random Strategy" );
		c.setMsisdnStrategyRandom( 3399900010L, 3399910000L );
		c.addLines( 5 );
		c.print();
		c.clean();
		
		System.out.println( "MSISDN options setting" );
		c.setMsisdnOptions( 39, 19 );
		
		System.out.println( "MSISDN Fixed Strategy with options" );
		c.setMsisdnStrategyFixed( 3399900001L );
		c.addLines( 5 );
		c.print();
		c.clean();
		
		System.out.println( "MSISDN Increment Strategy with options" );
		c.setMsisdnStrategyIncrement( 3399900001L, 20 );
		c.addLines( 5 );
		c.print();
		c.clean();
		
		System.out.println( "MSISDN Random Strategy with options" );
		c.setMsisdnStrategyRandom( 3399900010L, 3399910000L );
		c.addLines( 5 );
		c.print();
		c.clean();		
				
	}
	
	//@Test( enabled = generate_cdr )
	@Test( enabled = true )
	public void cdr_revenue_strategies() throws IOFileException, CDRException {
		
		CDRRevenue c = new CDRRevenue();
		Calendar date = Calendar.getInstance();
		CDRDateIncrement increment = new CDRDateIncrement();
		increment.setDayIncrement( 1 );
		
		System.out.println( "Fixed Strategy" );
		c.setMsisdnStrategyFixed( 3399900001L );
		c.setDateStrategyFixed( date );
		c.addLines( 5 );
		c.print();
		c.clean();
		
		System.out.println( "Increment Strategy" );
		c.setMsisdnStrategyIncrement( 3399900001L, 20 );
		c.setDateStrategyIncrement( date, increment );
		c.addLines( 5 );
		c.print();
		c.clean();
		
		System.out.println( "Random Strategy" );
		Calendar max_date = Calendar.getInstance();
		System.out.println( date.get( Calendar.YEAR ) + 1 );
		max_date.set( Calendar.YEAR , date.get( Calendar.YEAR ) + 1 );
		c.setMsisdnStrategyRandom( 3399900010L, 3399910000L );
		c.setDateStrategyRandom( date, max_date );
		c.addLines( 5 );
		c.print();
		c.clean();
		
		System.out.println( "Options setting" );
		c.setMsisdnOptions( 39, 19 );
		c.setDateFormat( CDRDateFormat.SQL_DATE_TIME.getFormat() );
		
		System.out.println( "Fixed Strategy with options" );
		c.setMsisdnStrategyFixed( 3399900001L );
		c.setDateStrategyFixed( date );
		c.addLines( 5 );
		c.print();
		c.clean();
		
		System.out.println( "Increment Strategy with options" );
		increment.setYearIncrement( 10 );
		increment.setMonthIncrement( 3 );
		c.setMsisdnStrategyIncrement( 3399900001L, 20 );
		c.setDateStrategyIncrement( date, increment );
		c.addLines( 5 );
		c.print();
		c.clean();
		
		System.out.println( "Random Strategy with options" );
		c.setMsisdnStrategyRandom( 3399900010L, 3399910000L );
		c.setDateStrategyRandom( date, max_date );
		c.addLines( 5 );
		c.print();
		c.clean();		
				
	}
	
	
	
	@Test( enabled = false )
	public void set() {
		
		
		
	}
	
}
