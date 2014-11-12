package com.lumata.e4o.system.cdr.types;

import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.fields.*;
import com.lumata.e4o.exceptions.FieldException;

public class CDRDailogSMS extends CDR { 

	private final int FIELDS = 5;

	public CDRDailogSMS() {
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

	public void setAddressLength( final Integer length ) throws FieldException  {
		if( this.address != null ) { this.address.setStringLength( length ); }
	}

	public void setAddressStrategyFixed( final String value ) throws FieldException  {
		if( this.address != null ) { this.address.setStringStrategyFixed( value ); }
	}

	public void setAddressStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.address != null ) { this.address.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setAddressStrategyRandom( final Integer length ) throws FieldException  {
		if( this.address != null ) { this.address.setStringStrategyRandom( length ); }
	}

	public void cleanAddress()  {
		if( this.address != null ) { this.address.cleanString(); }
	}

	public void cleanAddressLength()  {
		if( this.address != null ) { this.address.cleanStringLength(); }
	}

	public void cleanAddressStrategyIncrement()  {
		if( this.address != null ) { this.address.cleanStringStrategyIncrement(); }
	}

	public void cleanAddressStrategyRandom()  {
		if( this.address != null ) { this.address.cleanStringStrategyRandom(); }
	}

	@Address( position = 1 )
	public String getAddress() throws FieldException  {
		return this.address.getString();
	}

	public void setTimeLength( final Integer length ) throws FieldException  {
		if( this.time != null ) { this.time.setStringLength( length ); }
	}

	public void setTimeStrategyFixed( final String value ) throws FieldException  {
		if( this.time != null ) { this.time.setStringStrategyFixed( value ); }
	}

	public void setTimeStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.time != null ) { this.time.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setTimeStrategyRandom( final Integer length ) throws FieldException  {
		if( this.time != null ) { this.time.setStringStrategyRandom( length ); }
	}

	public void cleanTime()  {
		if( this.time != null ) { this.time.cleanString(); }
	}

	public void cleanTimeLength()  {
		if( this.time != null ) { this.time.cleanStringLength(); }
	}

	public void cleanTimeStrategyIncrement()  {
		if( this.time != null ) { this.time.cleanStringStrategyIncrement(); }
	}

	public void cleanTimeStrategyRandom()  {
		if( this.time != null ) { this.time.cleanStringStrategyRandom(); }
	}

	@Time( position = 2 )
	public String getTime() throws FieldException  {
		return this.time.getString();
	}

	public void setChannelNameLength( final Integer length ) throws FieldException  {
		if( this.channel_name != null ) { this.channel_name.setStringLength( length ); }
	}

	public void setChannelNameStrategyFixed( final String value ) throws FieldException  {
		if( this.channel_name != null ) { this.channel_name.setStringStrategyFixed( value ); }
	}

	public void setChannelNameStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.channel_name != null ) { this.channel_name.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setChannelNameStrategyRandom( final Integer length ) throws FieldException  {
		if( this.channel_name != null ) { this.channel_name.setStringStrategyRandom( length ); }
	}

	public void cleanChannelName()  {
		if( this.channel_name != null ) { this.channel_name.cleanString(); }
	}

	public void cleanChannelNameLength()  {
		if( this.channel_name != null ) { this.channel_name.cleanStringLength(); }
	}

	public void cleanChannelNameStrategyIncrement()  {
		if( this.channel_name != null ) { this.channel_name.cleanStringStrategyIncrement(); }
	}

	public void cleanChannelNameStrategyRandom()  {
		if( this.channel_name != null ) { this.channel_name.cleanStringStrategyRandom(); }
	}

	@ChannelName( position = 3 )
	public String getChannelName() throws FieldException  {
		return this.channel_name.getString();
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

	@Text( position = 4 )
	public String getText() throws FieldException  {
		return this.text.getString();
	}

}