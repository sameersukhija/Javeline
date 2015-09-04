package com.lumata.e4o.system.cdr.types;

import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.fields.*;
import com.lumata.e4o.exceptions.FieldException;

public class CDRRevenueO2 extends CDR { 

	private final int FIELDS = 2;

	public CDRRevenueO2() {
		super();
	} 

	public int getFieldsCount() {
		return this.FIELDS;
	}

	@RawData( position = 0 )
	public String getRawData() throws FieldException  {
		return this.rawData.getString();
	}

	public void setRawDataLength( final Integer length ) throws FieldException  {
		if( this.rawData != null ) { this.rawData.setStringLength( length ); }
	}

	public void setRawDataStrategyFixed( final String value ) throws FieldException  {
		if( this.rawData != null ) { this.rawData.setStringStrategyFixed( value ); }
	}

	public void cleanRawDataStrategyIncrement()  {
		if( this.rawData != null ) { this.rawData.cleanStringStrategyIncrement(); }
	}

	public void cleanRawDataStrategyRandom()  {
		if( this.rawData != null ) { this.rawData.cleanStringStrategyRandom(); }
	}

	public void setRawDataStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.rawData != null ) { this.rawData.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setRawDataStrategyRandom( final Integer length ) throws FieldException  {
		if( this.rawData != null ) { this.rawData.setStringStrategyRandom( length ); }
	}

	public void cleanRawData()  {
		if( this.rawData != null ) { this.rawData.cleanString(); }
	}

	public void cleanRawDataLength()  {
		if( this.rawData != null ) { this.rawData.cleanStringLength(); }
	}

	public void setRechargeAmountStrategyFixed( final Long value ) throws FieldException  {
		if( this.recharge_amount != null ) { this.recharge_amount.setLongStrategyFixed( value ); }
	}

	public void cleanRechargeAmountStrategyIncrement()  {
		if( this.recharge_amount != null ) { this.recharge_amount.cleanLongStrategyIncrement(); }
	}

	public void cleanRechargeAmountStrategyRandom()  {
		if( this.recharge_amount != null ) { this.recharge_amount.cleanLongStrategyRandom(); }
	}

	public void setRechargeAmountStrategyIncrement( final Long value, final Integer increment ) throws FieldException  {
		if( this.recharge_amount != null ) { this.recharge_amount.setLongStrategyIncrement( value, increment ); }
	}

	public void setRechargeAmountStrategyRandom( final Long min_value, final Long max_value ) throws FieldException  {
		if( this.recharge_amount != null ) { this.recharge_amount.setLongStrategyRandom( min_value, max_value ); }
	}

	public void cleanRechargeAmountStrategyFixed()  {
		if( this.recharge_amount != null ) { this.recharge_amount.cleanLongStrategyFixed(); }
	}

	@RechargeAmount( position = 1 )
	public String getRechargeAmount() throws FieldException  {
		return this.recharge_amount.getLong();
	}

}