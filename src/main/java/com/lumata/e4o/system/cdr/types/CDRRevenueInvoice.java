package com.lumata.e4o.system.cdr.types;

import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.fields.*;
import com.lumata.e4o.exceptions.FieldException;
import java.util.Calendar;
import com.lumata.e4o.system.fields.FieldDateIncrement;
import com.lumata.e4o.system.fields.IFieldEnum;

public class CDRRevenueInvoice extends CDR { 

	private final int FIELDS = 5;

	public CDRRevenueInvoice() {
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

	public void cleanMsisdnStrategyIncrement()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnStrategyIncrement(); }
	}

	public void cleanMsisdnStrategyRandom()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnStrategyRandom(); }
	}

	public void setDateStrategyFixed( final Calendar date ) throws FieldException  {
		if( this.date != null ) { this.date.setDateStrategyFixed( date ); }
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

	public void cleanDateStrategyIncrement()  {
		if( this.date != null ) { this.date.cleanDateStrategyIncrement(); }
	}

	public void cleanDateStrategyRandom()  {
		if( this.date != null ) { this.date.cleanDateStrategyRandom(); }
	}

	@Date( position = 1 )
	public String getDate()  {
		return this.date.getDate();
	}

	public void setAmountInvoiceStrategyFixed( final Long value ) throws FieldException  {
		if( this.amountInvoice != null ) { this.amountInvoice.setLongStrategyFixed( value ); }
	}

	public void setAmountInvoiceStrategyIncrement( final Long value, final Integer increment ) throws FieldException  {
		if( this.amountInvoice != null ) { this.amountInvoice.setLongStrategyIncrement( value, increment ); }
	}

	public void setAmountInvoiceStrategyRandom( final Long min_value, final Long max_value ) throws FieldException  {
		if( this.amountInvoice != null ) { this.amountInvoice.setLongStrategyRandom( min_value, max_value ); }
	}

	public void cleanAmountInvoiceStrategyFixed()  {
		if( this.amountInvoice != null ) { this.amountInvoice.cleanLongStrategyFixed(); }
	}

	public void cleanAmountInvoiceStrategyIncrement()  {
		if( this.amountInvoice != null ) { this.amountInvoice.cleanLongStrategyIncrement(); }
	}

	public void cleanAmountInvoiceStrategyRandom()  {
		if( this.amountInvoice != null ) { this.amountInvoice.cleanLongStrategyRandom(); }
	}

	@AmountInvoice( position = 2 )
	public String getAmountInvoice() throws FieldException  {
		return this.amountInvoice.getLong();
	}

	public void setBalanceStrategyFixed( final Long value ) throws FieldException  {
		if( this.balance != null ) { this.balance.setLongStrategyFixed( value ); }
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

	public void cleanBalanceStrategyIncrement()  {
		if( this.balance != null ) { this.balance.cleanLongStrategyIncrement(); }
	}

	public void cleanBalanceStrategyRandom()  {
		if( this.balance != null ) { this.balance.cleanLongStrategyRandom(); }
	}

	@Balance( position = 3 )
	public String getBalance() throws FieldException  {
		return this.balance.getLong();
	}

	@Type( position = 4 )
	public String getType() throws FieldException  {
		return this.type.getEnum();
	}

	public void setTypeStrategyFixed( final Enum<? extends IFieldEnum> value ) throws FieldException  {
		if( this.type != null ) { this.type.setEnumStrategyFixed( value ); }
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

	public void cleanTypeStrategyIncrement()  {
		if( this.type != null ) { this.type.cleanEnumStrategyIncrement(); }
	}

	public void cleanTypeStrategyRandom()  {
		if( this.type != null ) { this.type.cleanEnumStrategyRandom(); }
	}

}