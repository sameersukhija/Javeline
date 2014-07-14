package com.lumata.e4o.system.cdr.types;

import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.fields.*;
import com.lumata.e4o.exceptions.CDRException;
import java.util.Calendar;
import com.lumata.e4o.system.fields.FieldDateIncrement;

public class CDRBundleMultitenant extends CDR { 

	private final int FIELDS = 6;

	public CDRBundleMultitenant() {
		super();
	} 

	public int getFieldsCount() {
		return this.FIELDS;
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

	public void cleanMsisdnStrategyIncrement()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnStrategyIncrement(); }
	}

	public void cleanMsisdnStrategyRandom()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnStrategyRandom(); }
	}

	public void setMsisdnStrategyIncrement( final Long value, final Integer increment ) throws CDRException  {
		if( this.msisdn != null ) { this.msisdn.setMsisdnStrategyIncrement( value, increment ); }
	}

	public void setMsisdnStrategyRandom( final Long min_value, final Long max_value ) throws CDRException  {
		if( this.msisdn != null ) { this.msisdn.setMsisdnStrategyRandom( min_value, max_value ); }
	}

	public void cleanMsisdnFixedStrategy()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnFixedStrategy(); }
	}

	public void cleanMsisdnOptions()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnOptions(); }
	}

	public void setDateStrategyFixed( final Calendar date ) throws CDRException  {
		if( this.date != null ) { this.date.setDateStrategyFixed( date ); }
	}

	public void cleanDateStrategyIncrement()  {
		if( this.date != null ) { this.date.cleanDateStrategyIncrement(); }
	}

	public void cleanDateStrategyRandom()  {
		if( this.date != null ) { this.date.cleanDateStrategyRandom(); }
	}

	public void setDateFormat( String format ) throws CDRException  {
		if( this.date != null ) { this.date.setDateFormat( format ); }
	}

	public void setDateStrategyIncrement( final Calendar date, final FieldDateIncrement increment ) throws CDRException  {
		if( this.date != null ) { this.date.setDateStrategyIncrement( date, increment ); }
	}

	public void setDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws CDRException  {
		if( this.date != null ) { this.date.setDateStrategyRandom( date_left, date_right ); }
	}

	public void cleanDateStrategyFixed()  {
		if( this.date != null ) { this.date.cleanDateStrategyFixed(); }
	}

	@Date( position = 1 )
	public String getDate()  {
		return this.date.getDate();
	}

	public void setBundleNameLength( final Integer length ) throws CDRException  {
		if( this.bundle_name != null ) { this.bundle_name.setStringLength( length ); }
	}

	public void setBundleNameStrategyFixed( final String value ) throws CDRException  {
		if( this.bundle_name != null ) { this.bundle_name.setStringStrategyFixed( value ); }
	}

	public void cleanBundleNameStrategyIncrement()  {
		if( this.bundle_name != null ) { this.bundle_name.cleanStringStrategyIncrement(); }
	}

	public void cleanBundleNameStrategyRandom()  {
		if( this.bundle_name != null ) { this.bundle_name.cleanStringStrategyRandom(); }
	}

	public void setBundleNameStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.bundle_name != null ) { this.bundle_name.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setBundleNameStrategyRandom( final Integer length ) throws CDRException  {
		if( this.bundle_name != null ) { this.bundle_name.setStringStrategyRandom( length ); }
	}

	public void cleanBundleName()  {
		if( this.bundle_name != null ) { this.bundle_name.cleanString(); }
	}

	public void cleanBundleNameLength()  {
		if( this.bundle_name != null ) { this.bundle_name.cleanStringLength(); }
	}

	@BundleName( position = 2 )
	public String getBundleName() throws CDRException  {
		return this.bundle_name.getString();
	}

	public void setBundleBalanceStrategyFixed( final Long value ) throws CDRException  {
		if( this.bundle_balance != null ) { this.bundle_balance.setLongStrategyFixed( value ); }
	}

	public void cleanBundleBalanceStrategyIncrement()  {
		if( this.bundle_balance != null ) { this.bundle_balance.cleanLongStrategyIncrement(); }
	}

	public void cleanBundleBalanceStrategyRandom()  {
		if( this.bundle_balance != null ) { this.bundle_balance.cleanLongStrategyRandom(); }
	}

	public void setBundleBalanceStrategyIncrement( final Long value, final Integer increment ) throws CDRException  {
		if( this.bundle_balance != null ) { this.bundle_balance.setLongStrategyIncrement( value, increment ); }
	}

	public void setBundleBalanceStrategyRandom( final Long min_value, final Long max_value ) throws CDRException  {
		if( this.bundle_balance != null ) { this.bundle_balance.setLongStrategyRandom( min_value, max_value ); }
	}

	public void cleanBundleBalanceStrategyFixed()  {
		if( this.bundle_balance != null ) { this.bundle_balance.cleanLongStrategyFixed(); }
	}

	@BundleBalance( position = 3 )
	public String getBundleBalance() throws CDRException  {
		return this.bundle_balance.getLong();
	}

	public void setBundlePurchasedStrategyFixed( final Boolean value ) throws CDRException  {
		if( this.bundle_purchased != null ) { this.bundle_purchased.setBooleanStrategyFixed( value ); }
	}

	public void cleanBundlePurchasedStrategyIncrement()  {
		if( this.bundle_purchased != null ) { this.bundle_purchased.cleanBooleanStrategyIncrement(); }
	}

	public void cleanBundlePurchasedStrategyRandom()  {
		if( this.bundle_purchased != null ) { this.bundle_purchased.cleanBooleanStrategyRandom(); }
	}

	public void setBundlePurchasedStrategyIncrement( final Boolean start_value, final Integer increment ) throws CDRException  {
		if( this.bundle_purchased != null ) { this.bundle_purchased.setBooleanStrategyIncrement( start_value, increment ); }
	}

	public void setBundlePurchasedStrategyRandom() throws CDRException  {
		if( this.bundle_purchased != null ) { this.bundle_purchased.setBooleanStrategyRandom(); }
	}

	public void cleanBundlePurchasedStrategyFixed()  {
		if( this.bundle_purchased != null ) { this.bundle_purchased.cleanBooleanStrategyFixed(); }
	}

	@BundlePurchased( position = 4 )
	public String getBundlePurchased() throws CDRException  {
		return this.bundle_purchased.getBoolean();
	}

	public void setTenantIdStrategyFixed( final Long value ) throws CDRException  {
		if( this.tenant_id != null ) { this.tenant_id.setLongStrategyFixed( value ); }
	}

	public void cleanTenantIdStrategyIncrement()  {
		if( this.tenant_id != null ) { this.tenant_id.cleanLongStrategyIncrement(); }
	}

	public void cleanTenantIdStrategyRandom()  {
		if( this.tenant_id != null ) { this.tenant_id.cleanLongStrategyRandom(); }
	}

	public void setTenantIdStrategyIncrement( final Long value, final Integer increment ) throws CDRException  {
		if( this.tenant_id != null ) { this.tenant_id.setLongStrategyIncrement( value, increment ); }
	}

	public void setTenantIdStrategyRandom( final Long min_value, final Long max_value ) throws CDRException  {
		if( this.tenant_id != null ) { this.tenant_id.setLongStrategyRandom( min_value, max_value ); }
	}

	public void cleanTenantIdStrategyFixed()  {
		if( this.tenant_id != null ) { this.tenant_id.cleanLongStrategyFixed(); }
	}

	@TenantId( position = 5 )
	public String getTenantId() throws CDRException  {
		return this.tenant_id.getLong();
	}

}