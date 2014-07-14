package com.lumata.e4o.cdrinterface;

import java.util.Calendar;

import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.Environment;
import com.lumata.e4o.exceptions.FieldException;
import com.lumata.e4o.schema.tenant.Profiles;
import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.CDRClassGenerator;
import com.lumata.e4o.system.cdr.types.CDRBundle;
import com.lumata.e4o.system.cdr.types.CDRCall;
import com.lumata.e4o.system.cdr.types.CDRHistory;
import com.lumata.e4o.system.cdr.types.CDRRevenueRecharge;
import com.lumata.e4o.system.cdr.types.CDRVoucher;
import com.lumata.e4o.system.fields.FieldBoolean;
import com.lumata.e4o.system.fields.FieldDateIncrement;
import com.lumata.e4o.system.fields.FieldSchemaTable;
import com.lumata.e4o.system.fields.FieldString;
import com.lumata.e4o.system.fields.FieldDate.FieldDateFormat;

public class CDRExamples {
	
	private final boolean generate_cdr_classes = false;
	private final boolean generate_cdr = true;
	
	// CDR Types generation
	@Test( enabled = generate_cdr_classes )
	public void create_cdr_subclasses() throws IOFileException, ClassNotFoundException {

		System.out.println( "-----------------------------" );
		System.out.println( "create_cdr_subclasses" );

		CDRClassGenerator cdr_generator = new CDRClassGenerator();
		
		cdr_generator.generate();	

	}

	// CDR using
	@Test( enabled = generate_cdr )
	public void cdr_history_strategies() throws IOFileException, FieldException {

		System.out.println( "-----------------------------" );
		System.out.println( "cdr_history_strategies" );

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
		c.setMsisdnOptions( 39555, 19 );
		
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
	
	@Test( enabled = generate_cdr )
	//@Test( enabled = true )
	public void cdr_revenue_strategies() throws IOFileException, FieldException {

		System.out.println( "-----------------------------" );
		System.out.println( "cdr_revenue_strategies" );

		CDRRevenueRecharge cdrRevenue = new CDRRevenueRecharge();
		
		// today
		Calendar date = Calendar.getInstance();
		
		// add 1 day to date
		FieldDateIncrement increment = new FieldDateIncrement();
		increment.setDayIncrement( 1 );
		
		System.out.println( "Fixed Strategy" );
		cdrRevenue.setMsisdnStrategyFixed( 3399900001L );
		cdrRevenue.setDateStrategyFixed( date );
		cdrRevenue.setAmountStrategyFixed( 100L );
		cdrRevenue.setBalanceStrategyFixed( 300L );
		cdrRevenue.setValidityDateStrategyFixed( date );
		cdrRevenue.setDeactivationDateStrategyFixed( date );
		cdrRevenue.setTypeStrategyFixed( CDR.TYPE.PAIEMENT );
		cdrRevenue.addLines( 2 );
		cdrRevenue.print();
		cdrRevenue.clean();
		
		System.out.println( "Increment Strategy" );
		cdrRevenue.setMsisdnStrategyIncrement( 3399900001L, 20 );
		cdrRevenue.setDateStrategyIncrement( date, increment );
		cdrRevenue.setAmountStrategyIncrement( 100L, 10 );
		cdrRevenue.setBalanceStrategyIncrement( 300L, 30 );
		cdrRevenue.setValidityDateStrategyIncrement( date, increment );
		cdrRevenue.setDeactivationDateStrategyIncrement( date, increment );
		cdrRevenue.setTypeStrategyIncrement( CDR.TYPE.PAIEMENT, 1 );
		cdrRevenue.addLines( 3 );
		cdrRevenue.print();
		cdrRevenue.clean();
		
		System.out.println( "Random Strategy" );
		Calendar max_date = Calendar.getInstance();
		System.out.println( date.get( Calendar.YEAR ) + 1 );
		max_date.set( Calendar.YEAR , date.get( Calendar.YEAR ) + 1 );
		cdrRevenue.setMsisdnStrategyRandom( 3399900010L, 3399910000L );
		cdrRevenue.setDateStrategyRandom( date, max_date );
		cdrRevenue.setAmountStrategyRandom( 100L, 1000L );
		cdrRevenue.setBalanceStrategyRandom( 300L, 3000L );
		cdrRevenue.setValidityDateStrategyRandom( date, max_date );
		cdrRevenue.setDeactivationDateStrategyRandom( date, max_date );
		cdrRevenue.setTypeStrategyRandom();
		cdrRevenue.addLines( 3 );
		cdrRevenue.print();
		cdrRevenue.clean();
		
		System.out.println( "Options setting" );
		cdrRevenue.setMsisdnOptions( 39, 19 );
		cdrRevenue.setDateFormat( FieldDateFormat.SQL_DATE_TIME.getFormat() );
		cdrRevenue.setValidityDateFormat( FieldDateFormat.SQL_DATE_TIME.getFormat() );
		cdrRevenue.setDeactivationDateFormat( FieldDateFormat.SQL_DATE_TIME.getFormat() );
		
		System.out.println( "Increment Strategy with options" );
		cdrRevenue.setMsisdnStrategyFixed( 3399900001L );
		cdrRevenue.setDateStrategyFixed( date );
		cdrRevenue.setAmountStrategyFixed( 100L );
		cdrRevenue.setBalanceStrategyFixed( 300L );
		cdrRevenue.setValidityDateStrategyFixed( date );
		cdrRevenue.setDeactivationDateStrategyFixed( date );
		cdrRevenue.setTypeStrategyFixed( CDR.TYPE.PAIEMENT );
		cdrRevenue.addLines( 2 );
		cdrRevenue.print();
		cdrRevenue.clean();
		
		System.out.println( "Increment Strategy with options" );
		cdrRevenue.setMsisdnStrategyIncrement( 3399900001L, 20 );
		cdrRevenue.setDateStrategyIncrement( date, increment );
		cdrRevenue.setAmountStrategyIncrement( 100L, 10 );
		cdrRevenue.setBalanceStrategyIncrement( 300L, 30 );
		cdrRevenue.setValidityDateStrategyIncrement( date, increment );
		cdrRevenue.setDeactivationDateStrategyIncrement( date, increment );
		cdrRevenue.setTypeStrategyIncrement( CDR.TYPE.PAIEMENT, 1 );
		cdrRevenue.addLines( 3 );
		cdrRevenue.print();
		cdrRevenue.clean();
		
		System.out.println( "Random Strategy with options" );
		cdrRevenue.setMsisdnStrategyRandom( 3399900010L, 3399910000L );
		cdrRevenue.setDateStrategyRandom( date, max_date );
		cdrRevenue.setAmountStrategyRandom( 100L, 1000L );
		cdrRevenue.setBalanceStrategyRandom( 300L, 3000L );
		cdrRevenue.setValidityDateStrategyRandom( date, max_date );
		cdrRevenue.setDeactivationDateStrategyRandom( date, max_date );
		cdrRevenue.setTypeStrategyRandom();
		cdrRevenue.addLines( 3 );
		cdrRevenue.print();
		cdrRevenue.clean();	
				
	}
		
	@Test( enabled = generate_cdr )
	public void cdr_call_strategies() throws FieldException {

		System.out.println( "-----------------------------" );
		System.out.println( "cdr_call_strategies" );

		CDRCall c = new CDRCall();
		Calendar date = Calendar.getInstance();
		FieldDateIncrement increment = new FieldDateIncrement();
		increment.setDayIncrement( 1 );
				
		System.out.println( "Fixed Strategy" );
		c.setMsisdnStrategyFixed( 3399900001L );
		c.setDateStrategyFixed( date );
		c.setDurationStrategyFixed( 100L );
		c.setAmountStrategyFixed( 200L );
		c.setBalanceStrategyFixed( 300L );
		c.setTerminatingStrategyFixed( CDR.TERMINATING.YES );
		c.addLines( 5 );
		c.print();
		c.clean();
		
		System.out.println( "Increment Strategy" );
		c.setMsisdnStrategyIncrement( 3399900001L, 20 );
		c.setDateStrategyIncrement( date, increment );
		c.setDurationStrategyIncrement( 100L, 10 );
		c.setAmountStrategyIncrement( 200L, 20 );
		c.setBalanceStrategyIncrement( 300L, 30 );
		c.setTerminatingStrategyIncrement( CDR.TERMINATING.YES, 1 );
		c.addLines( 5 );
		c.print();
		c.clean();
		
		System.out.println( "Random Strategy" );
		Calendar max_date = Calendar.getInstance();
		System.out.println( date.get( Calendar.YEAR ) + 1 );
		max_date.set( Calendar.YEAR , date.get( Calendar.YEAR ) + 1 );
		c.setMsisdnStrategyRandom( 3399900010L, 3399910000L );
		c.setDateStrategyRandom( date, max_date );
		c.setDurationStrategyRandom( 100L, 500L );
		c.setAmountStrategyRandom( 200L, 2000L );
		c.setBalanceStrategyRandom( 300L, 3000L );
		c.setTerminatingStrategyRandom();
		c.addLines( 5 );
		c.print();
		c.clean();
		
		System.out.println( "Options setting" );
		c.setMsisdnOptions( 39, 19 );
		c.setDateFormat( FieldDateFormat.SQL_DATE_TIME.getFormat() );
		
		System.out.println( "Fixed Strategy with options" );
		c.setMsisdnStrategyFixed( 3399900001L );
		c.setDateStrategyFixed( date );
		c.setDurationStrategyFixed( 100L );
		c.setAmountStrategyFixed( 200L );
		c.setBalanceStrategyFixed( 300L );
		c.setTerminatingStrategyFixed( CDR.TERMINATING.YES );
		c.addLines( 5 );
		c.print();
		c.clean();
		
		System.out.println( "Increment Strategy with options" );
		increment.setYearIncrement( 10 );
		increment.setMonthIncrement( 3 );
		c.setMsisdnStrategyIncrement( 3399900001L, 20 );
		c.setDateStrategyIncrement( date, increment );
		c.setDurationStrategyIncrement( 100L, 10 );
		c.setAmountStrategyIncrement( 200L, 20 );
		c.setBalanceStrategyIncrement( 300L, 30 );
		c.setTerminatingStrategyIncrement( CDR.TERMINATING.YES, 3 );
		c.addLines( 5 );
		c.print();
		c.clean();
		
		System.out.println( "Random Strategy with options" );
		c.setMsisdnStrategyRandom( 3399900010L, 3399910000L );
		c.setDateStrategyRandom( date, max_date );
		c.setDurationStrategyRandom( 100L, 500L );
		c.setAmountStrategyRandom( 200L, 2000L );
		c.setBalanceStrategyRandom( 300L, 3000L );
		c.setTerminatingStrategyRandom();
		c.addLines( 5 );
		c.print();
		c.clean();	
		
	}
	
	@Test( enabled = generate_cdr )
	//@Test( enabled = true )
	public void cdr_bundle_strategies() throws FieldException {

		System.out.println( "-----------------------------" );
		System.out.println( "cdr_bundle_strategies" );

		CDRBundle cdrBundle = new CDRBundle();
		
		Calendar date = Calendar.getInstance();
		
		Calendar max_date = Calendar.getInstance();
		max_date.set( Calendar.YEAR , date.get( Calendar.YEAR ) + 1 );
		
		FieldDateIncrement increment = new FieldDateIncrement();
		increment.setDayIncrement( 1 );
				
		System.out.println( "Fixed Strategy" );
		cdrBundle.setMsisdnStrategyFixed( 3399900001L );
		cdrBundle.setDateStrategyFixed( date );
		cdrBundle.setBundleNameStrategyFixed( "bundle" );
		cdrBundle.setBundleBalanceStrategyFixed( 100L );
		cdrBundle.setBundlePurchasedStrategyFixed( true );
		cdrBundle.addLines( 2 );
		cdrBundle.print();
		cdrBundle.clean();
		
		System.out.println( "Increment Strategy" );
		cdrBundle.setMsisdnStrategyIncrement( 3399900001L, 1 );
		cdrBundle.setDateStrategyIncrement( date, increment );
		cdrBundle.setBundleNameStrategyIncrement( "bundle", 0, 1 );
		cdrBundle.setBundleBalanceStrategyIncrement( 100L, 100 );
		cdrBundle.setBundlePurchasedStrategyIncrement( true, 1 );
		cdrBundle.addLines( 3 );
		cdrBundle.print();
		cdrBundle.clean();

		System.out.println( "Random Strategy" );
		cdrBundle.setMsisdnStrategyRandom( 3399900001L, 3399910000L );
		cdrBundle.setDateStrategyRandom( date, max_date );
		cdrBundle.setBundleNameStrategyRandom( 7 );
		cdrBundle.setBundleBalanceStrategyRandom( 100L, 500L );
		cdrBundle.setBundlePurchasedStrategyRandom();
		cdrBundle.addLines( 3 );
		cdrBundle.print();
		cdrBundle.clean();
		
		System.out.println( "Options setting" );
		cdrBundle.setMsisdnOptions( 39, 19 );
		cdrBundle.setDateFormat( FieldDateFormat.SQL_DATE_TIME.getFormat() );
		
		System.out.println( "Fixed Strategy" );
		cdrBundle.setMsisdnStrategyFixed( 3399900001L );
		cdrBundle.setDateStrategyFixed( date );
		cdrBundle.setBundleNameStrategyFixed( "bundle" );
		cdrBundle.setBundleNameLength( 10 );
		cdrBundle.setBundleBalanceStrategyFixed( 100L );
		cdrBundle.setBundlePurchasedStrategyFixed( true );
		cdrBundle.addLines( 2 );
		cdrBundle.print();
		cdrBundle.clean();
		
		System.out.println( "Increment Strategy" );
		cdrBundle.setMsisdnStrategyIncrement( 3399900001L, 1 );
		cdrBundle.setDateStrategyIncrement( date, increment );
		cdrBundle.setBundleNameStrategyIncrement( "bundle", 0, 1 );
		cdrBundle.setBundleNameLength( 11 );
		cdrBundle.setBundleBalanceStrategyIncrement( 100L, 100 );
		cdrBundle.setBundlePurchasedStrategyIncrement( true, 1 );
		cdrBundle.addLines( 3 );
		cdrBundle.print();
		cdrBundle.clean();

		System.out.println( "Random Strategy" );
		cdrBundle.setMsisdnStrategyRandom( 3399900001L, 3399910000L );
		cdrBundle.setDateStrategyRandom( date, max_date );
		cdrBundle.setBundleNameStrategyRandom( 7 );
		cdrBundle.setBundleNameLength( 13 );
		cdrBundle.setBundleBalanceStrategyRandom( 100L, 500L );
		cdrBundle.setBundlePurchasedStrategyRandom();
		cdrBundle.addLines( 3 );
		cdrBundle.print();
		cdrBundle.clean();

	}
	
	@Test( enabled = generate_cdr )
	//@Test( enabled = true )
	public void cdr_voucher_strategies() throws FieldException {

		System.out.println( "-----------------------------" );
		System.out.println( "cdr_voucher_strategies" );

		CDRVoucher cdrBundle = new CDRVoucher();
		
		Calendar date = Calendar.getInstance();
		
		Calendar max_date = Calendar.getInstance();
		max_date.set( Calendar.YEAR , date.get( Calendar.YEAR ) + 1 );
		
		FieldDateIncrement increment = new FieldDateIncrement();
		increment.setDayIncrement( 1 );
		
		
		/*
		System.out.println( "Fixed Strategy" );
		cdrBundle.setMsisdnStrategyFixed( 3399900001L );
		cdrBundle.setDateStrategyFixed( date );
		cdrBundle.setBundleNameStrategyFixed( "bundle" );
		cdrBundle.setBundleBalanceStrategyFixed( 100L );
		cdrBundle.setBundlePurchasedStrategyFixed( true );
		cdrBundle.addLines( 2 );
		cdrBundle.print();
		cdrBundle.clean();
		
		System.out.println( "Increment Strategy" );
		cdrBundle.setMsisdnStrategyIncrement( 3399900001L, 1 );
		cdrBundle.setDateStrategyIncrement( date, increment );
		cdrBundle.setBundleNameStrategyIncrement( "bundle", 0, 1 );
		cdrBundle.setBundleBalanceStrategyIncrement( 100L, 100 );
		cdrBundle.setBundlePurchasedStrategyIncrement( true, 1 );
		cdrBundle.addLines( 3 );
		cdrBundle.print();
		cdrBundle.clean();

		System.out.println( "Random Strategy" );
		cdrBundle.setMsisdnStrategyRandom( 3399900001L, 3399910000L );
		cdrBundle.setDateStrategyRandom( date, max_date );
		cdrBundle.setBundleNameStrategyRandom( 7 );
		cdrBundle.setBundleBalanceStrategyRandom( 100L, 500L );
		cdrBundle.setBundlePurchasedStrategyRandom();
		cdrBundle.addLines( 3 );
		cdrBundle.print();
		cdrBundle.clean();
		
		System.out.println( "Options setting" );
		cdrBundle.setMsisdnOptions( 39, 19 );
		cdrBundle.setDateFormat( CDRDateFormat.SQL_DATE_TIME.getFormat() );
		
		System.out.println( "Fixed Strategy" );
		cdrBundle.setMsisdnStrategyFixed( 3399900001L );
		cdrBundle.setDateStrategyFixed( date );
		cdrBundle.setBundleNameStrategyFixed( "bundle" );
		cdrBundle.setBundleNameLength( 10 );
		cdrBundle.setBundleBalanceStrategyFixed( 100L );
		cdrBundle.setBundlePurchasedStrategyFixed( true );
		cdrBundle.addLines( 2 );
		cdrBundle.print();
		cdrBundle.clean();
		
		System.out.println( "Increment Strategy" );
		cdrBundle.setMsisdnStrategyIncrement( 3399900001L, 1 );
		cdrBundle.setDateStrategyIncrement( date, increment );
		cdrBundle.setBundleNameStrategyIncrement( "bundle", 0, 1 );
		cdrBundle.setBundleNameLength( 11 );
		cdrBundle.setBundleBalanceStrategyIncrement( 100L, 100 );
		cdrBundle.setBundlePurchasedStrategyIncrement( true, 1 );
		cdrBundle.addLines( 3 );
		cdrBundle.print();
		cdrBundle.clean();

		System.out.println( "Random Strategy" );
		cdrBundle.setMsisdnStrategyRandom( 3399900001L, 3399910000L );
		cdrBundle.setDateStrategyRandom( date, max_date );
		cdrBundle.setBundleNameStrategyRandom( 7 );
		cdrBundle.setBundleNameLength( 13 );
		cdrBundle.setBundleBalanceStrategyRandom( 100L, 500L );
		cdrBundle.setBundlePurchasedStrategyRandom();
		cdrBundle.addLines( 3 );
		cdrBundle.print();
		cdrBundle.clean();
*/
	}
	
	@Test( enabled = generate_cdr )
	public void csv_string() throws FieldException {

		System.out.println( "-----------------------------" );
		System.out.println( "csv_string" );

		FieldString string = new FieldString();
		
		System.out.println( "Fixed Strategy" );
		string.setStringStrategyFixed( "1234567890" );
		string.setStringLength( 5 );
		string.cleanStringLength();
		
		for( int i = 0; i < 5; i++ ) {
			System.out.println( string.getString() );
		}

		System.out.println( "\nIncrement Strategy" );
		string.setStringStrategyIncrement( "voucher", 0, 5);
		//string.setStringLength( 5 );
		
		for( int i = 0; i < 5; i++ ) {
			System.out.println( string.getString() );
		}
		
		System.out.println( "\nRandom Strategy" );
		string.setStringStrategyRandom(5);
		
		for( int i = 0; i < 5; i++ ) {
			System.out.println( string.getString() );
		}
		
	}
	
	@Test( enabled = generate_cdr )
	public void csv_boolean() throws FieldException {

		System.out.println( "-----------------------------" );
		System.out.println( "csv_string" );

		FieldBoolean csvBoolean = new FieldBoolean();
		
		System.out.println( "Fixed Strategy" );
		csvBoolean.setBooleanStrategyFixed( true);
		
		for( int i = 0; i < 2; i++ ) {
			System.out.println( csvBoolean.getBoolean() );
		}

		System.out.println( "Incremental Strategy" );
		csvBoolean.setBooleanStrategyIncrement( true, 1 );
		
		for( int i = 0; i < 3; i++ ) {
			System.out.println( csvBoolean.getBoolean() );
		}
		/*
		System.out.println( "\nIncrement Strategy" );
		string.setStringStrategyIncrement( "voucher", 0, 5);
		//string.setStringLength( 5 );
		
		for( int i = 0; i < 5; i++ ) {
			System.out.println( string.getString() );
		}
		
		System.out.println( "\nRandom Strategy" );
		string.setStringStrategyRandom(5);
		
		for( int i = 0; i < 5; i++ ) {
			System.out.println( string.getString() );
		}
		*/
	}
	
	
	@Test( enabled = false )
	public void csv_schema() throws FieldException, EnvironmentException {
		
		System.out.println( "-----------------------------" );
		System.out.println( "csv_schema" );
		
		Environment env = new Environment( "input/environments", "E4O_QA", IOFileUtils.IOLoadingType.RESOURCE );
		
		FieldSchemaTable csv_table = new FieldSchemaTable( new Profiles(), Profiles.Fields.profile );
		
		csv_table.setSchemaTableValues( env.getDataSource( "qa" ) );
		
		System.out.println( "Fixed Strategy" );
		csv_table.setSchemaTableStrategyFixed( 0 );
				
		for( int i = 0; i < 2; i++ ) { System.out.println( csv_table.getSchemaTable() ); }

		csv_table.setSchemaTableStrategyFixed( "postpaid" );
		
		for( int i = 0; i < 3; i++ ) { System.out.println( csv_table.getSchemaTable() ); }

		csv_table.setSchemaTableStrategyFixed( "wrong value" );
		
		for( int i = 0; i < 3; i++ ) { System.out.println( csv_table.getSchemaTable() ); }
		
		
		System.out.println( "\nIncrement Strategy" );
		csv_table.setSchemaTableStrategyIncrement( 0, 5 );
		
		for( int i = 0; i < 3; i++ ) { System.out.println( csv_table.getSchemaTable() ); }
		
		
		System.out.println( "\nRandom Strategy" );
		csv_table.setSchemaTableStrategyRandom();
		
		for( int i = 0; i < 3; i++ ) { System.out.println( csv_table.getSchemaTable() ); }
		
	}

}
