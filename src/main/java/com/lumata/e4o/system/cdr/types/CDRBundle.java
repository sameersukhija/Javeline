package com.lumata.e4o.system.cdr.types;

import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.annotations.*;
import com.lumata.expression.operators.exceptions.CDRException;
import java.util.Calendar;
import com.lumata.e4o.system.csv.types.CSVDateIncrement;

public class CDRBundle extends CDR { 

	private final int FIELDS = 5;

	public CDRBundle() {
		super();
	} 

	public int getFieldsCount() {
		return this.FIELDS;
	}

	public void setMsisdnStrategyFixed( final Long value ) throws CDRException  {
		if( this.msisdn != null ) { this.msisdn.setMsisdnStrategyFixed( value ); }
	}

	public void setMsisdnStrategyIncrement( final Long value, final Integer increment ) throws CDRException  {
		if( this.msisdn != null ) { this.msisdn.setMsisdnStrategyIncrement( value, increment ); }
	}

	public void setMsisdnStrategyRandom( final Long min_value, final Long max_value ) throws CDRException  {
		if( this.msisdn != null ) { this.msisdn.setMsisdnStrategyRandom( min_value, max_value ); }
	}

	public void setMsisdnOptions( final Integer prefix, final Integer length ) throws CDRException  {
		if( this.msisdn != null ) { this.msisdn.setMsisdnOptions( prefix, length ); }
	}

	public void cleanMsisdnFixedStrategy()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnFixedStrategy() ; }
	}

	public void cleanMsisdnOptions()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnOptions() ; }
	}

	@Msisdn( position = 0 )
	public String getMsisdn() throws CDRException  {
		return this.msisdn.getMsisdn();
	}

	public void cleanMsisdnStrategyIncrement()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnStrategyIncrement() ; }
	}

	public void cleanMsisdnStrategyRandom()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnStrategyRandom() ; }
	}

	@Date( position = 1 )
	public String getDate()  {
		return this.date.getDate() ;
	}

	public void setDateStrategyFixed( final Calendar date ) throws CDRException  {
		if( this.date != null ) { this.date.setDateStrategyFixed( date ); }
	}

	public void setDateStrategyIncrement( final Calendar date, final CSVDateIncrement increment ) throws CDRException  {
		if( this.date != null ) { this.date.setDateStrategyIncrement( date, increment ); }
	}

	public void setDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws CDRException  {
		if( this.date != null ) { this.date.setDateStrategyRandom( date_left, date_right ); }
	}

	public void setDateFormat( String format ) throws CDRException  {
		if( this.date != null ) { this.date.setDateFormat( format ); }
	}

	public void cleanDateStrategyIncrement()  {
		if( this.date != null ) { this.date.cleanDateStrategyIncrement() ; }
	}

	public void cleanDateStrategyRandom()  {
		if( this.date != null ) { this.date.cleanDateStrategyRandom() ; }
	}

	public void cleanDateStrategyFixed()  {
		if( this.date != null ) { this.date.cleanDateStrategyFixed() ; }
	}

	@BundleName( position = 2 )
	public String getBundleName() throws CDRException  {
		return this.bundle_name.getString();
	}

	public void setBundleNameStrategyFixed( final String value ) throws CDRException  {
		if( this.bundle_name != null ) { this.bundle_name.setStringStrategyFixed( value ); }
	}

	public void setBundleNameLength( final Integer length ) throws CDRException  {
		if( this.bundle_name != null ) { this.bundle_name.setStringLength( length ); }
	}

	public void cleanBundleNameLength()  {
		if( this.bundle_name != null ) { this.bundle_name.cleanStringLength() ; }
	}

	public void setBundleNameStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.bundle_name != null ) { this.bundle_name.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setBundleNameStrategyRandom( int length ) throws CDRException  {
		if( this.bundle_name != null ) { this.bundle_name.setStringStrategyRandom( length ); }
	}

	public void cleanBundleNameStrategyIncrement()  {
		if( this.bundle_name != null ) { this.bundle_name.cleanStringStrategyIncrement() ; }
	}

	public void cleanBundleNameStrategyRandom()  {
		if( this.bundle_name != null ) { this.bundle_name.cleanStringStrategyRandom() ; }
	}

	public void cleanBundleName()  {
		if( this.bundle_name != null ) { this.bundle_name.cleanString() ; }
	}

	@BundleBalance( position = 3 )
	public String getBundleBalance() throws CDRException  {
		return this.bundle_balance.getLong();
	}

	public void setBundleBalanceStrategyFixed( final Long value ) throws CDRException  {
		if( this.bundle_balance != null ) { this.bundle_balance.setLongStrategyFixed( value ); }
	}

	public void cleanBundleBalanceStrategyIncrement()  {
		if( this.bundle_balance != null ) { this.bundle_balance.cleanLongStrategyIncrement() ; }
	}

	public void cleanBundleBalanceStrategyRandom()  {
		if( this.bundle_balance != null ) { this.bundle_balance.cleanLongStrategyRandom() ; }
	}

	public void setBundleBalanceStrategyIncrement( final Long value, final Integer increment ) throws CDRException  {
		if( this.bundle_balance != null ) { this.bundle_balance.setLongStrategyIncrement( value, increment ); }
	}

	public void setBundleBalanceStrategyRandom( final Long min_value, final Long max_value ) throws CDRException  {
		if( this.bundle_balance != null ) { this.bundle_balance.setLongStrategyRandom( min_value, max_value ); }
	}

	public void cleanBundleBalanceStrategyFixed()  {
		if( this.bundle_balance != null ) { this.bundle_balance.cleanLongStrategyFixed() ; }
	}

	@BundlePurchased( position = 4 )
	public String getBoolean() throws CDRException  {
		return this.bundle_purchased.getBoolean();
	}

	public void setBooleanStrategyFixed( final Boolean value ) throws CDRException  {
		if( this.bundle_purchased != null ) { this.bundle_purchased.setBooleanStrategyFixed( value ); }
	}

	public void cleanBooleanStrategyIncrement()  {
		if( this.bundle_purchased != null ) { this.bundle_purchased.cleanBooleanStrategyIncrement() ; }
	}

	public void cleanBooleanStrategyRandom()  {
		if( this.bundle_purchased != null ) { this.bundle_purchased.cleanBooleanStrategyRandom() ; }
	}

	public void setBooleanStrategyIncrement( final Boolean start_value, final Integer increment ) throws CDRException  {
		if( this.bundle_purchased != null ) { this.bundle_purchased.setBooleanStrategyIncrement( start_value, increment ); }
	}

	public void setBooleanStrategyRandom() throws CDRException  {
		if( this.bundle_purchased != null ) { this.bundle_purchased.setBooleanStrategyRandom(); }
	}

	public void cleanBooleanStrategyFixed()  {
		if( this.bundle_purchased != null ) { this.bundle_purchased.cleanBooleanStrategyFixed() ; }
	}

}