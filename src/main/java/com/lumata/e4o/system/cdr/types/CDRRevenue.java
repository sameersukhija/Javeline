package com.lumata.e4o.system.cdr.types;

import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.annotations.*;
import com.lumata.expression.operators.exceptions.CDRException;
import java.util.Calendar;
import com.lumata.e4o.system.csv.types.CSVDateIncrement;
import com.lumata.e4o.system.csv.types.ICSVEnum;

public class CDRRevenue extends CDR { 

	private final int FIELDS = 8;

	public CDRRevenue() {
		super();
	} 

	public int getFieldsCount() {
		return this.FIELDS;
	}

	public void cleanMsisdnStrategyIncrement()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnStrategyIncrement() ; }
	}

	public void cleanMsisdnStrategyRandom()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnStrategyRandom() ; }
	}

	public void setMsisdnStrategyIncrement( final Long value, final Integer increment ) throws CDRException  {
		if( this.msisdn != null ) { this.msisdn.setMsisdnStrategyIncrement( value, increment ); }
	}

	public void setMsisdnStrategyRandom( final Long min_value, final Long max_value ) throws CDRException  {
		if( this.msisdn != null ) { this.msisdn.setMsisdnStrategyRandom( min_value, max_value ); }
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

	public void setMsisdnOptions( final Integer prefix, final Integer length ) throws CDRException  {
		if( this.msisdn != null ) { this.msisdn.setMsisdnOptions( prefix, length ); }
	}

	public void setMsisdnStrategyFixed( final Long value ) throws CDRException  {
		if( this.msisdn != null ) { this.msisdn.setMsisdnStrategyFixed( value ); }
	}

	public void setDateStrategyFixed( final Calendar date ) throws CDRException  {
		if( this.date != null ) { this.date.setDateStrategyFixed( date ); }
	}

	public void cleanDateStrategyIncrement()  {
		if( this.date != null ) { this.date.cleanDateStrategyIncrement() ; }
	}

	public void cleanDateStrategyRandom()  {
		if( this.date != null ) { this.date.cleanDateStrategyRandom() ; }
	}

	public void setDateFormat( String format ) throws CDRException  {
		if( this.date != null ) { this.date.setDateFormat( format ); }
	}

	public void setDateStrategyIncrement( final Calendar date, final CSVDateIncrement increment ) throws CDRException  {
		if( this.date != null ) { this.date.setDateStrategyIncrement( date, increment ); }
	}

	public void setDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws CDRException  {
		if( this.date != null ) { this.date.setDateStrategyRandom( date_left, date_right ); }
	}

	public void cleanDateStrategyFixed()  {
		if( this.date != null ) { this.date.cleanDateStrategyFixed() ; }
	}

	@Date( position = 1 )
	public String getDate()  {
		return this.date.getDate() ;
	}

	public void setAmountStrategyFixed( final Long value ) throws CDRException  {
		if( this.amount != null ) { this.amount.setLongStrategyFixed( value ); }
	}

	public void cleanAmountStrategyIncrement()  {
		if( this.amount != null ) { this.amount.cleanLongStrategyIncrement() ; }
	}

	public void cleanAmountStrategyRandom()  {
		if( this.amount != null ) { this.amount.cleanLongStrategyRandom() ; }
	}

	public void setAmountStrategyIncrement( final Long value, final Integer increment ) throws CDRException  {
		if( this.amount != null ) { this.amount.setLongStrategyIncrement( value, increment ); }
	}

	public void setAmountStrategyRandom( final Long min_value, final Long max_value ) throws CDRException  {
		if( this.amount != null ) { this.amount.setLongStrategyRandom( min_value, max_value ); }
	}

	public void cleanAmountStrategyFixed()  {
		if( this.amount != null ) { this.amount.cleanLongStrategyFixed() ; }
	}

	@Amount( position = 2 )
	public String getAmount() throws CDRException  {
		return this.amount.getLong();
	}

	public void setBalanceStrategyFixed( final Long value ) throws CDRException  {
		if( this.balance != null ) { this.balance.setLongStrategyFixed( value ); }
	}

	public void cleanBalanceStrategyIncrement()  {
		if( this.balance != null ) { this.balance.cleanLongStrategyIncrement() ; }
	}

	public void cleanBalanceStrategyRandom()  {
		if( this.balance != null ) { this.balance.cleanLongStrategyRandom() ; }
	}

	public void setBalanceStrategyIncrement( final Long value, final Integer increment ) throws CDRException  {
		if( this.balance != null ) { this.balance.setLongStrategyIncrement( value, increment ); }
	}

	public void setBalanceStrategyRandom( final Long min_value, final Long max_value ) throws CDRException  {
		if( this.balance != null ) { this.balance.setLongStrategyRandom( min_value, max_value ); }
	}

	public void cleanBalanceStrategyFixed()  {
		if( this.balance != null ) { this.balance.cleanLongStrategyFixed() ; }
	}

	@Balance( position = 3 )
	public String getBalance() throws CDRException  {
		return this.balance.getLong();
	}

	public void setValidityDateStrategyFixed( final Calendar date ) throws CDRException  {
		if( this.validity_date != null ) { this.validity_date.setDateStrategyFixed( date ); }
	}

	public void cleanValidityDateStrategyIncrement()  {
		if( this.validity_date != null ) { this.validity_date.cleanDateStrategyIncrement() ; }
	}

	public void cleanValidityDateStrategyRandom()  {
		if( this.validity_date != null ) { this.validity_date.cleanDateStrategyRandom() ; }
	}

	public void setValidityDateFormat( String format ) throws CDRException  {
		if( this.validity_date != null ) { this.validity_date.setDateFormat( format ); }
	}

	public void setValidityDateStrategyIncrement( final Calendar date, final CSVDateIncrement increment ) throws CDRException  {
		if( this.validity_date != null ) { this.validity_date.setDateStrategyIncrement( date, increment ); }
	}

	public void setValidityDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws CDRException  {
		if( this.validity_date != null ) { this.validity_date.setDateStrategyRandom( date_left, date_right ); }
	}

	public void cleanValidityDateStrategyFixed()  {
		if( this.validity_date != null ) { this.validity_date.cleanDateStrategyFixed() ; }
	}

	@ValidityDate( position = 4 )
	public String getValidityDate()  {
		return this.validity_date.getDate() ;
	}

	public void setDeactivationDateStrategyFixed( final Calendar date ) throws CDRException  {
		if( this.deactivation_date != null ) { this.deactivation_date.setDateStrategyFixed( date ); }
	}

	public void cleanDeactivationDateStrategyIncrement()  {
		if( this.deactivation_date != null ) { this.deactivation_date.cleanDateStrategyIncrement() ; }
	}

	public void cleanDeactivationDateStrategyRandom()  {
		if( this.deactivation_date != null ) { this.deactivation_date.cleanDateStrategyRandom() ; }
	}

	public void setDeactivationDateFormat( String format ) throws CDRException  {
		if( this.deactivation_date != null ) { this.deactivation_date.setDateFormat( format ); }
	}

	public void setDeactivationDateStrategyIncrement( final Calendar date, final CSVDateIncrement increment ) throws CDRException  {
		if( this.deactivation_date != null ) { this.deactivation_date.setDateStrategyIncrement( date, increment ); }
	}

	public void setDeactivationDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws CDRException  {
		if( this.deactivation_date != null ) { this.deactivation_date.setDateStrategyRandom( date_left, date_right ); }
	}

	public void cleanDeactivationDateStrategyFixed()  {
		if( this.deactivation_date != null ) { this.deactivation_date.cleanDateStrategyFixed() ; }
	}

	@DeactivationDate( position = 5 )
	public String getDeactivationDate()  {
		return this.deactivation_date.getDate() ;
	}

	@Type( position = 6 )
	public String getType() throws CDRException  {
		return this.type.getEnum();
	}

	public void setTypeStrategyFixed( final Enum<? extends ICSVEnum> value ) throws CDRException  {
		if( this.type != null ) { this.type.setEnumStrategyFixed( value ); }
	}

	public void cleanTypeStrategyIncrement()  {
		if( this.type != null ) { this.type.cleanEnumStrategyIncrement() ; }
	}

	public void cleanTypeStrategyRandom()  {
		if( this.type != null ) { this.type.cleanEnumStrategyRandom() ; }
	}

	public void setTypeStrategyIncrement( final Enum<? extends ICSVEnum> value, final Integer increment ) throws CDRException  {
		if( this.type != null ) { this.type.setEnumStrategyIncrement( value, increment ); }
	}

	public void setTypeStrategyRandom() throws CDRException  {
		if( this.type != null ) { this.type.setEnumStrategyRandom(); }
	}

	public void cleanTypeStrategyFixed()  {
		if( this.type != null ) { this.type.cleanEnumStrategyFixed() ; }
	}

	public void setDelayStrategyFixed( final Long value ) throws CDRException  {
		if( this.delay != null ) { this.delay.setLongStrategyFixed( value ); }
	}

	public void cleanDelayStrategyIncrement()  {
		if( this.delay != null ) { this.delay.cleanLongStrategyIncrement() ; }
	}

	public void cleanDelayStrategyRandom()  {
		if( this.delay != null ) { this.delay.cleanLongStrategyRandom() ; }
	}

	public void setDelayStrategyIncrement( final Long value, final Integer increment ) throws CDRException  {
		if( this.delay != null ) { this.delay.setLongStrategyIncrement( value, increment ); }
	}

	public void setDelayStrategyRandom( final Long min_value, final Long max_value ) throws CDRException  {
		if( this.delay != null ) { this.delay.setLongStrategyRandom( min_value, max_value ); }
	}

	public void cleanDelayStrategyFixed()  {
		if( this.delay != null ) { this.delay.cleanLongStrategyFixed() ; }
	}

	@Delay( position = 7 )
	public String getDelay() throws CDRException  {
		return this.delay.getLong();
	}

}