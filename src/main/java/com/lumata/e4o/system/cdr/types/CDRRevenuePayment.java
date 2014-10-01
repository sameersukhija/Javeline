package com.lumata.e4o.system.cdr.types;

import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.fields.*;
import com.lumata.e4o.exceptions.FieldException;
import java.util.Calendar;
import com.lumata.e4o.system.fields.FieldDateIncrement;
import com.lumata.e4o.system.fields.IFieldEnum;

public class CDRRevenuePayment extends CDR { 

	private final int FIELDS = 6;

	public CDRRevenuePayment() {
		super();
	} 

	public int getFieldsCount() {
		return this.FIELDS;
	}

	@Msisdn( position = 0 )
	public String getMsisdn() throws FieldException  {
		return this.msisdn.getMsisdn();
	}

	public void setMsisdnOptions( final Integer prefix, final Integer length ) throws FieldException  {
		if( this.msisdn != null ) { this.msisdn.setMsisdnOptions( prefix, length ); }
	}

	public void setMsisdnStrategyFixed( final Long value ) throws FieldException  {
		if( this.msisdn != null ) { this.msisdn.setMsisdnStrategyFixed( value ); }
	}

	public void cleanMsisdnStrategyIncrement()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnStrategyIncrement(); }
	}

	public void cleanMsisdnStrategyRandom()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnStrategyRandom(); }
	}

	public void setMsisdnStrategyIncrement( final Long value, final Integer increment ) throws FieldException  {
		if( this.msisdn != null ) { this.msisdn.setMsisdnStrategyIncrement( value, increment ); }
	}

	public void setMsisdnStrategyRandom( final Long min_value, final Long max_value ) throws FieldException  {
		if( this.msisdn != null ) { this.msisdn.setMsisdnStrategyRandom( min_value, max_value ); }
	}

	public void cleanMsisdnFixedStrategy()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnFixedStrategy(); }
	}

	public void cleanMsisdnOptions()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnOptions(); }
	}

	public void setDateStrategyFixed( final Calendar date ) throws FieldException  {
		if( this.date != null ) { this.date.setDateStrategyFixed( date ); }
	}

	public void cleanDateStrategyIncrement()  {
		if( this.date != null ) { this.date.cleanDateStrategyIncrement(); }
	}

	public void cleanDateStrategyRandom()  {
		if( this.date != null ) { this.date.cleanDateStrategyRandom(); }
	}

	public void setDateFormat( String format ) throws FieldException  {
		if( this.date != null ) { this.date.setDateFormat( format ); }
	}

	public void setDateStrategyIncrement( final Calendar date, final FieldDateIncrement increment ) throws FieldException  {
		if( this.date != null ) { this.date.setDateStrategyIncrement( date, increment ); }
	}

	public void setDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws FieldException  {
		if( this.date != null ) { this.date.setDateStrategyRandom( date_left, date_right ); }
	}

	public void cleanDateStrategyFixed()  {
		if( this.date != null ) { this.date.cleanDateStrategyFixed(); }
	}

	@Date( position = 1 )
	public String getDate()  {
		return this.date.getDate();
	}

	public void setAmountPaymentStrategyFixed( final Long value ) throws FieldException  {
		if( this.amountPayment != null ) { this.amountPayment.setLongStrategyFixed( value ); }
	}

	public void cleanAmountPaymentStrategyIncrement()  {
		if( this.amountPayment != null ) { this.amountPayment.cleanLongStrategyIncrement(); }
	}

	public void cleanAmountPaymentStrategyRandom()  {
		if( this.amountPayment != null ) { this.amountPayment.cleanLongStrategyRandom(); }
	}

	public void setAmountPaymentStrategyIncrement( final Long value, final Integer increment ) throws FieldException  {
		if( this.amountPayment != null ) { this.amountPayment.setLongStrategyIncrement( value, increment ); }
	}

	public void setAmountPaymentStrategyRandom( final Long min_value, final Long max_value ) throws FieldException  {
		if( this.amountPayment != null ) { this.amountPayment.setLongStrategyRandom( min_value, max_value ); }
	}

	public void cleanAmountPaymentStrategyFixed()  {
		if( this.amountPayment != null ) { this.amountPayment.cleanLongStrategyFixed(); }
	}

	@AmountPayment( position = 2 )
	public String getAmountPayment() throws FieldException  {
		return this.amountPayment.getLong();
	}

	public void setBalanceStrategyFixed( final Long value ) throws FieldException  {
		if( this.balance != null ) { this.balance.setLongStrategyFixed( value ); }
	}

	public void cleanBalanceStrategyIncrement()  {
		if( this.balance != null ) { this.balance.cleanLongStrategyIncrement(); }
	}

	public void cleanBalanceStrategyRandom()  {
		if( this.balance != null ) { this.balance.cleanLongStrategyRandom(); }
	}

	public void setBalanceStrategyIncrement( final Long value, final Integer increment ) throws FieldException  {
		if( this.balance != null ) { this.balance.setLongStrategyIncrement( value, increment ); }
	}

	public void setBalanceStrategyRandom( final Long min_value, final Long max_value ) throws FieldException  {
		if( this.balance != null ) { this.balance.setLongStrategyRandom( min_value, max_value ); }
	}

	public void cleanBalanceStrategyFixed()  {
		if( this.balance != null ) { this.balance.cleanLongStrategyFixed(); }
	}

	@Balance( position = 3 )
	public String getBalance() throws FieldException  {
		return this.balance.getLong();
	}

	public void setTypeStrategyIncrement( final Enum<? extends IFieldEnum> value, final Integer increment ) throws FieldException  {
		if( this.type != null ) { this.type.setEnumStrategyIncrement( value, increment ); }
	}

	public void setTypeStrategyRandom() throws FieldException  {
		if( this.type != null ) { this.type.setEnumStrategyRandom(); }
	}

	public void cleanTypeStrategyFixed()  {
		if( this.type != null ) { this.type.cleanEnumStrategyFixed(); }
	}

	@Type( position = 4 )
	public String getType() throws FieldException  {
		return this.type.getEnum();
	}

	public void setTypeStrategyFixed( final Enum<? extends IFieldEnum> value ) throws FieldException  {
		if( this.type != null ) { this.type.setEnumStrategyFixed( value ); }
	}

	public void cleanTypeStrategyIncrement()  {
		if( this.type != null ) { this.type.cleanEnumStrategyIncrement(); }
	}

	public void cleanTypeStrategyRandom()  {
		if( this.type != null ) { this.type.cleanEnumStrategyRandom(); }
	}

	public void setDelayStrategyFixed( final Long value ) throws FieldException  {
		if( this.delay != null ) { this.delay.setLongStrategyFixed( value ); }
	}

	public void cleanDelayStrategyIncrement()  {
		if( this.delay != null ) { this.delay.cleanLongStrategyIncrement(); }
	}

	public void cleanDelayStrategyRandom()  {
		if( this.delay != null ) { this.delay.cleanLongStrategyRandom(); }
	}

	public void setDelayStrategyIncrement( final Long value, final Integer increment ) throws FieldException  {
		if( this.delay != null ) { this.delay.setLongStrategyIncrement( value, increment ); }
	}

	public void setDelayStrategyRandom( final Long min_value, final Long max_value ) throws FieldException  {
		if( this.delay != null ) { this.delay.setLongStrategyRandom( min_value, max_value ); }
	}

	public void cleanDelayStrategyFixed()  {
		if( this.delay != null ) { this.delay.cleanLongStrategyFixed(); }
	}

	@Delay( position = 5 )
	public String getDelay() throws FieldException  {
		return this.delay.getLong();
	}

}