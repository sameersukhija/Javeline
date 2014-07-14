package com.lumata.e4o.system.cdr.types;

import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.fields.*;
import com.lumata.e4o.exceptions.FieldException;
import java.util.Calendar;
import com.lumata.e4o.system.fields.FieldDateIncrement;
import com.lumata.e4o.system.fields.IFieldEnum;

public class CDRCallMultitenant extends CDR { 

	private final int FIELDS = 7;

	public CDRCallMultitenant() {
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

	@Duration( position = 2 )
	public String getDuration() throws FieldException  {
		return this.duration.getLong();
	}

	public void setDurationStrategyFixed( final Long value ) throws FieldException  {
		if( this.duration != null ) { this.duration.setLongStrategyFixed( value ); }
	}

	public void cleanDurationStrategyIncrement()  {
		if( this.duration != null ) { this.duration.cleanLongStrategyIncrement(); }
	}

	public void cleanDurationStrategyRandom()  {
		if( this.duration != null ) { this.duration.cleanLongStrategyRandom(); }
	}

	public void setDurationStrategyIncrement( final Long value, final Integer increment ) throws FieldException  {
		if( this.duration != null ) { this.duration.setLongStrategyIncrement( value, increment ); }
	}

	public void setDurationStrategyRandom( final Long min_value, final Long max_value ) throws FieldException  {
		if( this.duration != null ) { this.duration.setLongStrategyRandom( min_value, max_value ); }
	}

	public void cleanDurationStrategyFixed()  {
		if( this.duration != null ) { this.duration.cleanLongStrategyFixed(); }
	}

	@Amount( position = 3 )
	public String getAmount() throws FieldException  {
		return this.amount.getLong();
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

	@Balance( position = 4 )
	public String getBalance() throws FieldException  {
		return this.balance.getLong();
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

	public void cleanTerminatingStrategyRandom()  {
		if( this.terminating != null ) { this.terminating.cleanEnumStrategyRandom(); }
	}

	public void setTerminatingStrategyIncrement( final Enum<? extends IFieldEnum> value, final Integer increment ) throws FieldException  {
		if( this.terminating != null ) { this.terminating.setEnumStrategyIncrement( value, increment ); }
	}

	public void setTerminatingStrategyRandom() throws FieldException  {
		if( this.terminating != null ) { this.terminating.setEnumStrategyRandom(); }
	}

	public void cleanTerminatingStrategyFixed()  {
		if( this.terminating != null ) { this.terminating.cleanEnumStrategyFixed(); }
	}

	@Terminating( position = 5 )
	public String getTerminating() throws FieldException  {
		return this.terminating.getEnum();
	}

	public void setTerminatingStrategyFixed( final Enum<? extends IFieldEnum> value ) throws FieldException  {
		if( this.terminating != null ) { this.terminating.setEnumStrategyFixed( value ); }
	}

	public void cleanTerminatingStrategyIncrement()  {
		if( this.terminating != null ) { this.terminating.cleanEnumStrategyIncrement(); }
	}

	@TenantId( position = 6 )
	public String getTenantId() throws FieldException  {
		return this.tenant_id.getLong();
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

}