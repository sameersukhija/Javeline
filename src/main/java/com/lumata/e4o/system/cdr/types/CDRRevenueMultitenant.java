package com.lumata.e4o.system.cdr.types;

import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.fields.*;
import com.lumata.e4o.exceptions.FieldException;
import java.util.Calendar;
import com.lumata.e4o.system.fields.FieldDateIncrement;
import com.lumata.e4o.system.fields.IFieldEnum;

public class CDRRevenueMultitenant extends CDR { 

	private final int FIELDS = 9;

	public CDRRevenueMultitenant() {
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

	@Date( position = 1 )
	public String getDate()  {
		return this.date.getDate();
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

	public void setAmountStrategyFixed( final Long value ) throws FieldException  {
		if( this.amount != null ) { this.amount.setLongStrategyFixed( value ); }
	}

	public void cleanAmountStrategyIncrement()  {
		if( this.amount != null ) { this.amount.cleanLongStrategyIncrement(); }
	}

	public void cleanAmountStrategyRandom()  {
		if( this.amount != null ) { this.amount.cleanLongStrategyRandom(); }
	}

	public void setAmountStrategyIncrement( final Long value, final Integer increment ) throws FieldException  {
		if( this.amount != null ) { this.amount.setLongStrategyIncrement( value, increment ); }
	}

	public void setAmountStrategyRandom( final Long min_value, final Long max_value ) throws FieldException  {
		if( this.amount != null ) { this.amount.setLongStrategyRandom( min_value, max_value ); }
	}

	public void cleanAmountStrategyFixed()  {
		if( this.amount != null ) { this.amount.cleanLongStrategyFixed(); }
	}

	@Amount( position = 2 )
	public String getAmount() throws FieldException  {
		return this.amount.getLong();
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

	@ValidityDate( position = 4 )
	public String getValidityDate()  {
		return this.validityDate.getDate();
	}

	public void setValidityDateStrategyFixed( final Calendar date ) throws FieldException  {
		if( this.validityDate != null ) { this.validityDate.setDateStrategyFixed( date ); }
	}

	public void cleanValidityDateStrategyIncrement()  {
		if( this.validityDate != null ) { this.validityDate.cleanDateStrategyIncrement(); }
	}

	public void cleanValidityDateStrategyRandom()  {
		if( this.validityDate != null ) { this.validityDate.cleanDateStrategyRandom(); }
	}

	public void setValidityDateFormat( String format ) throws FieldException  {
		if( this.validityDate != null ) { this.validityDate.setDateFormat( format ); }
	}

	public void setValidityDateStrategyIncrement( final Calendar date, final FieldDateIncrement increment ) throws FieldException  {
		if( this.validityDate != null ) { this.validityDate.setDateStrategyIncrement( date, increment ); }
	}

	public void setValidityDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws FieldException  {
		if( this.validityDate != null ) { this.validityDate.setDateStrategyRandom( date_left, date_right ); }
	}

	public void cleanValidityDateStrategyFixed()  {
		if( this.validityDate != null ) { this.validityDate.cleanDateStrategyFixed(); }
	}

	@DeactivationDate( position = 5 )
	public String getDeactivationDate()  {
		return this.deactivationDate.getDate();
	}

	public void setDeactivationDateStrategyFixed( final Calendar date ) throws FieldException  {
		if( this.deactivationDate != null ) { this.deactivationDate.setDateStrategyFixed( date ); }
	}

	public void cleanDeactivationDateStrategyIncrement()  {
		if( this.deactivationDate != null ) { this.deactivationDate.cleanDateStrategyIncrement(); }
	}

	public void cleanDeactivationDateStrategyRandom()  {
		if( this.deactivationDate != null ) { this.deactivationDate.cleanDateStrategyRandom(); }
	}

	public void setDeactivationDateFormat( String format ) throws FieldException  {
		if( this.deactivationDate != null ) { this.deactivationDate.setDateFormat( format ); }
	}

	public void setDeactivationDateStrategyIncrement( final Calendar date, final FieldDateIncrement increment ) throws FieldException  {
		if( this.deactivationDate != null ) { this.deactivationDate.setDateStrategyIncrement( date, increment ); }
	}

	public void setDeactivationDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws FieldException  {
		if( this.deactivationDate != null ) { this.deactivationDate.setDateStrategyRandom( date_left, date_right ); }
	}

	public void cleanDeactivationDateStrategyFixed()  {
		if( this.deactivationDate != null ) { this.deactivationDate.cleanDateStrategyFixed(); }
	}

	@Type( position = 6 )
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

	public void setTypeStrategyIncrement( final Enum<? extends IFieldEnum> value, final Integer increment ) throws FieldException  {
		if( this.type != null ) { this.type.setEnumStrategyIncrement( value, increment ); }
	}

	public void setTypeStrategyRandom() throws FieldException  {
		if( this.type != null ) { this.type.setEnumStrategyRandom(); }
	}

	public void cleanTypeStrategyFixed()  {
		if( this.type != null ) { this.type.cleanEnumStrategyFixed(); }
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

	@Delay( position = 7 )
	public String getDelay() throws FieldException  {
		return this.delay.getLong();
	}

	public void setTenantIdStrategyFixed( final Long value ) throws FieldException  {
		if( this.tenant_id != null ) { this.tenant_id.setLongStrategyFixed( value ); }
	}

	public void cleanTenantIdStrategyIncrement()  {
		if( this.tenant_id != null ) { this.tenant_id.cleanLongStrategyIncrement(); }
	}

	public void cleanTenantIdStrategyRandom()  {
		if( this.tenant_id != null ) { this.tenant_id.cleanLongStrategyRandom(); }
	}

	public void setTenantIdStrategyIncrement( final Long value, final Integer increment ) throws FieldException  {
		if( this.tenant_id != null ) { this.tenant_id.setLongStrategyIncrement( value, increment ); }
	}

	public void setTenantIdStrategyRandom( final Long min_value, final Long max_value ) throws FieldException  {
		if( this.tenant_id != null ) { this.tenant_id.setLongStrategyRandom( min_value, max_value ); }
	}

	public void cleanTenantIdStrategyFixed()  {
		if( this.tenant_id != null ) { this.tenant_id.cleanLongStrategyFixed(); }
	}

	@TenantId( position = 8 )
	public String getTenantId() throws FieldException  {
		return this.tenant_id.getLong();
	}

}