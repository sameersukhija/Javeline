package com.lumata.e4o.system.cdr.types;

import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.fields.*;
import com.lumata.e4o.exceptions.FieldException;
import java.util.Calendar;
import com.lumata.e4o.system.fields.FieldDateIncrement;

public class CDRDialogSMS extends CDR { 

	private final int FIELDS = 4;

	public CDRDialogSMS() {
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

	@Date( position = 1 )
	public String getDate()  {
		return this.date.getDate();
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

	@ShortCode( position = 2 )
	public String getShortCode() throws FieldException  {
		return this.short_code.getString();
	}

	public void setShortCodeLength( final Integer length ) throws FieldException  {
		if( this.short_code != null ) { this.short_code.setStringLength( length ); }
	}

	public void setShortCodeStrategyFixed( final String value ) throws FieldException  {
		if( this.short_code != null ) { this.short_code.setStringStrategyFixed( value ); }
	}

	public void setShortCodeStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.short_code != null ) { this.short_code.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setShortCodeStrategyRandom( final Integer length ) throws FieldException  {
		if( this.short_code != null ) { this.short_code.setStringStrategyRandom( length ); }
	}

	public void cleanShortCode()  {
		if( this.short_code != null ) { this.short_code.cleanString(); }
	}

	public void cleanShortCodeLength()  {
		if( this.short_code != null ) { this.short_code.cleanStringLength(); }
	}

	public void cleanShortCodeStrategyIncrement()  {
		if( this.short_code != null ) { this.short_code.cleanStringStrategyIncrement(); }
	}

	public void cleanShortCodeStrategyRandom()  {
		if( this.short_code != null ) { this.short_code.cleanStringStrategyRandom(); }
	}

	@Text( position = 3 )
	public String getText() throws FieldException  {
		return this.text.getString();
	}

	public void setTextLength( final Integer length ) throws FieldException  {
		if( this.text != null ) { this.text.setStringLength( length ); }
	}

	public void setTextStrategyFixed( final String value ) throws FieldException  {
		if( this.text != null ) { this.text.setStringStrategyFixed( value ); }
	}

	public void setTextStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.text != null ) { this.text.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setTextStrategyRandom( final Integer length ) throws FieldException  {
		if( this.text != null ) { this.text.setStringStrategyRandom( length ); }
	}

	public void cleanText()  {
		if( this.text != null ) { this.text.cleanString(); }
	}

	public void cleanTextLength()  {
		if( this.text != null ) { this.text.cleanStringLength(); }
	}

	public void cleanTextStrategyIncrement()  {
		if( this.text != null ) { this.text.cleanStringStrategyIncrement(); }
	}

	public void cleanTextStrategyRandom()  {
		if( this.text != null ) { this.text.cleanStringStrategyRandom(); }
	}

}