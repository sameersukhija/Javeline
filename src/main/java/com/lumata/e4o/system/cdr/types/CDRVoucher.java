package com.lumata.e4o.system.cdr.types;

import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.fields.*;
import com.lumata.e4o.exceptions.FieldException;
import java.util.Calendar;
import com.lumata.e4o.system.fields.FieldDateIncrement;

public class CDRVoucher extends CDR { 

	private final int FIELDS = 4;

	public CDRVoucher() {
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

	public void setVoucherCodeLength( final Integer length ) throws FieldException  {
		if( this.voucherCode != null ) { this.voucherCode.setStringLength( length ); }
	}

	public void setVoucherCodeStrategyFixed( final String value ) throws FieldException  {
		if( this.voucherCode != null ) { this.voucherCode.setStringStrategyFixed( value ); }
	}

	public void cleanVoucherCodeStrategyIncrement()  {
		if( this.voucherCode != null ) { this.voucherCode.cleanStringStrategyIncrement(); }
	}

	public void cleanVoucherCodeStrategyRandom()  {
		if( this.voucherCode != null ) { this.voucherCode.cleanStringStrategyRandom(); }
	}

	public void setVoucherCodeStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.voucherCode != null ) { this.voucherCode.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setVoucherCodeStrategyRandom( final Integer length ) throws FieldException  {
		if( this.voucherCode != null ) { this.voucherCode.setStringStrategyRandom( length ); }
	}

	public void cleanVoucherCode()  {
		if( this.voucherCode != null ) { this.voucherCode.cleanString(); }
	}

	public void cleanVoucherCodeLength()  {
		if( this.voucherCode != null ) { this.voucherCode.cleanStringLength(); }
	}

	@VoucherCode( position = 1 )
	public String getVoucherCode() throws FieldException  {
		return this.voucherCode.getString();
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

	@Date( position = 2 )
	public String getDate()  {
		return this.date.getDate();
	}

	public void setLocationLength( final Integer length ) throws FieldException  {
		if( this.location != null ) { this.location.setStringLength( length ); }
	}

	public void setLocationStrategyFixed( final String value ) throws FieldException  {
		if( this.location != null ) { this.location.setStringStrategyFixed( value ); }
	}

	public void cleanLocationStrategyIncrement()  {
		if( this.location != null ) { this.location.cleanStringStrategyIncrement(); }
	}

	public void cleanLocationStrategyRandom()  {
		if( this.location != null ) { this.location.cleanStringStrategyRandom(); }
	}

	public void setLocationStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.location != null ) { this.location.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setLocationStrategyRandom( final Integer length ) throws FieldException  {
		if( this.location != null ) { this.location.setStringStrategyRandom( length ); }
	}

	public void cleanLocation()  {
		if( this.location != null ) { this.location.cleanString(); }
	}

	public void cleanLocationLength()  {
		if( this.location != null ) { this.location.cleanStringLength(); }
	}

	@Location( position = 3 )
	public String getLocation() throws FieldException  {
		return this.location.getString();
	}

}