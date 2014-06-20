package com.lumata.e4o.system.cdr.types;

import com.lumata.e4o.exceptions.CDRException;
import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.fields.*;

import java.util.Calendar;

import com.lumata.e4o.system.fields.FieldDateIncrement;
import com.lumata.e4o.system.fields.IFieldEnum;

public class CDRLifeCycle extends CDR { 

	private final int FIELDS = 14;

	public CDRLifeCycle() {
		super();
	} 

	public int getFieldsCount() {
		return this.FIELDS;
	}

	public void setMsisdnStrategyFixed( final Long value ) throws CDRException  {
		if( this.msisdn != null ) { this.msisdn.setMsisdnStrategyFixed( value ); }
	}

	public void setMsisdnStrategyIncrement( final Long value, final Integer increment ) throws CDRException  {
		if( this.msisdn != null ) { this.msisdn.setMsisdnStrategyIncrement( value, increment ); }
	}

	public void setMsisdnStrategyRandom( final Long min_value, final Long max_value ) throws CDRException  {
		if( this.msisdn != null ) { this.msisdn.setMsisdnStrategyRandom( min_value, max_value ); }
	}

	public void setMsisdnOptions( final Integer prefix, final Integer length ) throws CDRException  {
		if( this.msisdn != null ) { this.msisdn.setMsisdnOptions( prefix, length ); }
	}

	public void cleanMsisdnOptions()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnOptions(); }
	}

	@Msisdn( position = 0 )
	public String getMsisdn() throws CDRException  {
		return this.msisdn.getMsisdn();
	}

	public void cleanMsisdnStrategyIncrement()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnStrategyIncrement(); }
	}

	public void cleanMsisdnStrategyRandom()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnStrategyRandom(); }
	}

	public void cleanMsisdnFixedStrategy()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnFixedStrategy(); }
	}

	@Date( position = 1 )
	public String getDate()  {
		return this.date.getDate();
	}

	public void setDateFormat( String format ) throws CDRException  {
		if( this.date != null ) { this.date.setDateFormat( format ); }
	}

	public void setDateStrategyFixed( final Calendar date ) throws CDRException  {
		if( this.date != null ) { this.date.setDateStrategyFixed( date ); }
	}

	public void setDateStrategyIncrement( final Calendar date, final FieldDateIncrement increment ) throws CDRException  {
		if( this.date != null ) { this.date.setDateStrategyIncrement( date, increment ); }
	}

	public void setDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws CDRException  {
		if( this.date != null ) { this.date.setDateStrategyRandom( date_left, date_right ); }
	}

	public void cleanDateStrategyIncrement()  {
		if( this.date != null ) { this.date.cleanDateStrategyIncrement(); }
	}

	public void cleanDateStrategyRandom()  {
		if( this.date != null ) { this.date.cleanDateStrategyRandom(); }
	}

	public void cleanDateStrategyFixed()  {
		if( this.date != null ) { this.date.cleanDateStrategyFixed(); }
	}

	@NewRatePlan( position = 2 )
	public String getNewRatePlan() throws CDRException  {
		return this.new_rate_plan.getString();
	}

	public void setNewRatePlanStrategyFixed( final String value ) throws CDRException  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.setStringStrategyFixed( value ); }
	}

	public void setNewRatePlanLength( final Integer length ) throws CDRException  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.setStringLength( length ); }
	}

	public void cleanNewRatePlanLength()  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.cleanStringLength(); }
	}

	public void setNewRatePlanStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setNewRatePlanStrategyRandom( final Integer length ) throws CDRException  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.setStringStrategyRandom( length ); }
	}

	public void cleanNewRatePlanStrategyIncrement()  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.cleanStringStrategyIncrement(); }
	}

	public void cleanNewRatePlanStrategyRandom()  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.cleanStringStrategyRandom(); }
	}

	public void cleanNewRatePlan()  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.cleanString(); }
	}

	@OldRatePlan( position = 3 )
	public String getOldRatePlan() throws CDRException  {
		return this.old_rate_plan.getString();
	}

	public void setOldRatePlanStrategyFixed( final String value ) throws CDRException  {
		if( this.old_rate_plan != null ) { this.old_rate_plan.setStringStrategyFixed( value ); }
	}

	public void setOldRatePlanLength( final Integer length ) throws CDRException  {
		if( this.old_rate_plan != null ) { this.old_rate_plan.setStringLength( length ); }
	}

	public void cleanOldRatePlanLength()  {
		if( this.old_rate_plan != null ) { this.old_rate_plan.cleanStringLength(); }
	}

	public void setOldRatePlanStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.old_rate_plan != null ) { this.old_rate_plan.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setOldRatePlanStrategyRandom( final Integer length ) throws CDRException  {
		if( this.old_rate_plan != null ) { this.old_rate_plan.setStringStrategyRandom( length ); }
	}

	public void cleanOldRatePlanStrategyIncrement()  {
		if( this.old_rate_plan != null ) { this.old_rate_plan.cleanStringStrategyIncrement(); }
	}

	public void cleanOldRatePlanStrategyRandom()  {
		if( this.old_rate_plan != null ) { this.old_rate_plan.cleanStringStrategyRandom(); }
	}

	public void cleanOldRatePlan()  {
		if( this.old_rate_plan != null ) { this.old_rate_plan.cleanString(); }
	}

	@NewProfile( position = 4 )
	public String getNewProfile() throws CDRException  {
		return this.new_profile.getString();
	}

	public void setNewProfileStrategyFixed( final String value ) throws CDRException  {
		if( this.new_profile != null ) { this.new_profile.setStringStrategyFixed( value ); }
	}

	public void setNewProfileLength( final Integer length ) throws CDRException  {
		if( this.new_profile != null ) { this.new_profile.setStringLength( length ); }
	}

	public void cleanNewProfileLength()  {
		if( this.new_profile != null ) { this.new_profile.cleanStringLength(); }
	}

	public void setNewProfileStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.new_profile != null ) { this.new_profile.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setNewProfileStrategyRandom( final Integer length ) throws CDRException  {
		if( this.new_profile != null ) { this.new_profile.setStringStrategyRandom( length ); }
	}

	public void cleanNewProfileStrategyIncrement()  {
		if( this.new_profile != null ) { this.new_profile.cleanStringStrategyIncrement(); }
	}

	public void cleanNewProfileStrategyRandom()  {
		if( this.new_profile != null ) { this.new_profile.cleanStringStrategyRandom(); }
	}

	public void cleanNewProfile()  {
		if( this.new_profile != null ) { this.new_profile.cleanString(); }
	}

	@OldProfile( position = 5 )
	public String getOldProfile() throws CDRException  {
		return this.old_profile.getString();
	}

	public void setOldProfileStrategyFixed( final String value ) throws CDRException  {
		if( this.old_profile != null ) { this.old_profile.setStringStrategyFixed( value ); }
	}

	public void setOldProfileLength( final Integer length ) throws CDRException  {
		if( this.old_profile != null ) { this.old_profile.setStringLength( length ); }
	}

	public void cleanOldProfileLength()  {
		if( this.old_profile != null ) { this.old_profile.cleanStringLength(); }
	}

	public void setOldProfileStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.old_profile != null ) { this.old_profile.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setOldProfileStrategyRandom( final Integer length ) throws CDRException  {
		if( this.old_profile != null ) { this.old_profile.setStringStrategyRandom( length ); }
	}

	public void cleanOldProfileStrategyIncrement()  {
		if( this.old_profile != null ) { this.old_profile.cleanStringStrategyIncrement(); }
	}

	public void cleanOldProfileStrategyRandom()  {
		if( this.old_profile != null ) { this.old_profile.cleanStringStrategyRandom(); }
	}

	public void cleanOldProfile()  {
		if( this.old_profile != null ) { this.old_profile.cleanString(); }
	}

	@NewSubProfile( position = 6 )
	public String getNewSubProfile() throws CDRException  {
		return this.new_subprofile.getString();
	}

	public void setNewSubProfileStrategyFixed( final String value ) throws CDRException  {
		if( this.new_subprofile != null ) { this.new_subprofile.setStringStrategyFixed( value ); }
	}

	public void setNewSubProfileLength( final Integer length ) throws CDRException  {
		if( this.new_subprofile != null ) { this.new_subprofile.setStringLength( length ); }
	}

	public void cleanNewSubProfileLength()  {
		if( this.new_subprofile != null ) { this.new_subprofile.cleanStringLength(); }
	}

	public void setNewSubProfileStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.new_subprofile != null ) { this.new_subprofile.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setNewSubProfileStrategyRandom( final Integer length ) throws CDRException  {
		if( this.new_subprofile != null ) { this.new_subprofile.setStringStrategyRandom( length ); }
	}

	public void cleanNewSubProfileStrategyIncrement()  {
		if( this.new_subprofile != null ) { this.new_subprofile.cleanStringStrategyIncrement(); }
	}

	public void cleanNewSubProfileStrategyRandom()  {
		if( this.new_subprofile != null ) { this.new_subprofile.cleanStringStrategyRandom(); }
	}

	public void cleanNewSubProfile()  {
		if( this.new_subprofile != null ) { this.new_subprofile.cleanString(); }
	}

	@OldSubProfile( position = 7 )
	public String getOldSubProfile() throws CDRException  {
		return this.old_subprofile.getString();
	}

	public void setOldSubProfileStrategyFixed( final String value ) throws CDRException  {
		if( this.old_subprofile != null ) { this.old_subprofile.setStringStrategyFixed( value ); }
	}

	public void setOldSubProfileLength( final Integer length ) throws CDRException  {
		if( this.old_subprofile != null ) { this.old_subprofile.setStringLength( length ); }
	}

	public void cleanOldSubProfileLength()  {
		if( this.old_subprofile != null ) { this.old_subprofile.cleanStringLength(); }
	}

	public void setOldSubProfileStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.old_subprofile != null ) { this.old_subprofile.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setOldSubProfileStrategyRandom( final Integer length ) throws CDRException  {
		if( this.old_subprofile != null ) { this.old_subprofile.setStringStrategyRandom( length ); }
	}

	public void cleanOldSubProfileStrategyIncrement()  {
		if( this.old_subprofile != null ) { this.old_subprofile.cleanStringStrategyIncrement(); }
	}

	public void cleanOldSubProfileStrategyRandom()  {
		if( this.old_subprofile != null ) { this.old_subprofile.cleanStringStrategyRandom(); }
	}

	public void cleanOldSubProfile()  {
		if( this.old_subprofile != null ) { this.old_subprofile.cleanString(); }
	}

	@NewStatus( position = 8 )
	public String getNewStatus() throws CDRException  {
		return this.new_status.getEnum();
	}

	public void setNewStatusStrategyFixed( final Enum<? extends IFieldEnum> value ) throws CDRException  {
		if( this.new_status != null ) { this.new_status.setEnumStrategyFixed( value ); }
	}

	public void cleanNewStatusStrategyIncrement()  {
		if( this.new_status != null ) { this.new_status.cleanEnumStrategyIncrement(); }
	}

	public void cleanNewStatusStrategyRandom()  {
		if( this.new_status != null ) { this.new_status.cleanEnumStrategyRandom(); }
	}

	public void setNewStatusStrategyIncrement( final Enum<? extends IFieldEnum> value, final Integer increment ) throws CDRException  {
		if( this.new_status != null ) { this.new_status.setEnumStrategyIncrement( value, increment ); }
	}

	public void setNewStatusStrategyRandom() throws CDRException  {
		if( this.new_status != null ) { this.new_status.setEnumStrategyRandom(); }
	}

	public void cleanNewStatusStrategyFixed()  {
		if( this.new_status != null ) { this.new_status.cleanEnumStrategyFixed(); }
	}

	@OldStatus( position = 9 )
	public String getOldStatus() throws CDRException  {
		return this.old_status.getEnum();
	}

	public void setOldStatusStrategyFixed( final Enum<? extends IFieldEnum> value ) throws CDRException  {
		if( this.old_status != null ) { this.old_status.setEnumStrategyFixed( value ); }
	}

	public void cleanOldStatusStrategyIncrement()  {
		if( this.old_status != null ) { this.old_status.cleanEnumStrategyIncrement(); }
	}

	public void cleanOldStatusStrategyRandom()  {
		if( this.old_status != null ) { this.old_status.cleanEnumStrategyRandom(); }
	}

	public void setOldStatusStrategyIncrement( final Enum<? extends IFieldEnum> value, final Integer increment ) throws CDRException  {
		if( this.old_status != null ) { this.old_status.setEnumStrategyIncrement( value, increment ); }
	}

	public void setOldStatusStrategyRandom() throws CDRException  {
		if( this.old_status != null ) { this.old_status.setEnumStrategyRandom(); }
	}

	public void cleanOldStatusStrategyFixed()  {
		if( this.old_status != null ) { this.old_status.cleanEnumStrategyFixed(); }
	}

	@NewNetwork( position = 10 )
	public String getNewNetwork() throws CDRException  {
		return this.new_network.getString();
	}

	public void setNewNetworkStrategyFixed( final String value ) throws CDRException  {
		if( this.new_network != null ) { this.new_network.setStringStrategyFixed( value ); }
	}

	public void setNewNetworkLength( final Integer length ) throws CDRException  {
		if( this.new_network != null ) { this.new_network.setStringLength( length ); }
	}

	public void cleanNewNetworkLength()  {
		if( this.new_network != null ) { this.new_network.cleanStringLength(); }
	}

	public void setNewNetworkStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.new_network != null ) { this.new_network.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setNewNetworkStrategyRandom( final Integer length ) throws CDRException  {
		if( this.new_network != null ) { this.new_network.setStringStrategyRandom( length ); }
	}

	public void cleanNewNetworkStrategyIncrement()  {
		if( this.new_network != null ) { this.new_network.cleanStringStrategyIncrement(); }
	}

	public void cleanNewNetworkStrategyRandom()  {
		if( this.new_network != null ) { this.new_network.cleanStringStrategyRandom(); }
	}

	public void cleanNewNetwork()  {
		if( this.new_network != null ) { this.new_network.cleanString(); }
	}

	@OldNetwork( position = 11 )
	public String getOldNetwork() throws CDRException  {
		return this.old_network.getString();
	}

	public void setOldNetworkStrategyFixed( final String value ) throws CDRException  {
		if( this.old_network != null ) { this.old_network.setStringStrategyFixed( value ); }
	}

	public void setOldNetworkLength( final Integer length ) throws CDRException  {
		if( this.old_network != null ) { this.old_network.setStringLength( length ); }
	}

	public void cleanOldNetworkLength()  {
		if( this.old_network != null ) { this.old_network.cleanStringLength(); }
	}

	public void setOldNetworkStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.old_network != null ) { this.old_network.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setOldNetworkStrategyRandom( final Integer length ) throws CDRException  {
		if( this.old_network != null ) { this.old_network.setStringStrategyRandom( length ); }
	}

	public void cleanOldNetworkStrategyIncrement()  {
		if( this.old_network != null ) { this.old_network.cleanStringStrategyIncrement(); }
	}

	public void cleanOldNetworkStrategyRandom()  {
		if( this.old_network != null ) { this.old_network.cleanStringStrategyRandom(); }
	}

	public void cleanOldNetwork()  {
		if( this.old_network != null ) { this.old_network.cleanString(); }
	}

	@NewSubscriptionDate( position = 12 )
	public String getNewSubscriptionDate()  {
		return this.new_subscription_date.getDate();
	}

	public void setNewSubscriptionDateFormat( String format ) throws CDRException  {
		if( this.new_subscription_date != null ) { this.new_subscription_date.setDateFormat( format ); }
	}

	public void setNewSubscriptionDateStrategyFixed( final Calendar date ) throws CDRException  {
		if( this.new_subscription_date != null ) { this.new_subscription_date.setDateStrategyFixed( date ); }
	}

	public void setNewSubscriptionDateStrategyIncrement( final Calendar date, final FieldDateIncrement increment ) throws CDRException  {
		if( this.new_subscription_date != null ) { this.new_subscription_date.setDateStrategyIncrement( date, increment ); }
	}

	public void setNewSubscriptionDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws CDRException  {
		if( this.new_subscription_date != null ) { this.new_subscription_date.setDateStrategyRandom( date_left, date_right ); }
	}

	public void cleanNewSubscriptionDateStrategyIncrement()  {
		if( this.new_subscription_date != null ) { this.new_subscription_date.cleanDateStrategyIncrement(); }
	}

	public void cleanNewSubscriptionDateStrategyRandom()  {
		if( this.new_subscription_date != null ) { this.new_subscription_date.cleanDateStrategyRandom(); }
	}

	public void cleanNewSubscriptionDateStrategyFixed()  {
		if( this.new_subscription_date != null ) { this.new_subscription_date.cleanDateStrategyFixed(); }
	}

	@OldSubscriptionDate( position = 13 )
	public String getOldSubscriptionDate()  {
		return this.old_subscription_date.getDate();
	}

	public void setOldSubscriptionDateFormat( String format ) throws CDRException  {
		if( this.old_subscription_date != null ) { this.old_subscription_date.setDateFormat( format ); }
	}

	public void setOldSubscriptionDateStrategyFixed( final Calendar date ) throws CDRException  {
		if( this.old_subscription_date != null ) { this.old_subscription_date.setDateStrategyFixed( date ); }
	}

	public void setOldSubscriptionDateStrategyIncrement( final Calendar date, final FieldDateIncrement increment ) throws CDRException  {
		if( this.old_subscription_date != null ) { this.old_subscription_date.setDateStrategyIncrement( date, increment ); }
	}

	public void setOldSubscriptionDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws CDRException  {
		if( this.old_subscription_date != null ) { this.old_subscription_date.setDateStrategyRandom( date_left, date_right ); }
	}

	public void cleanOldSubscriptionDateStrategyIncrement()  {
		if( this.old_subscription_date != null ) { this.old_subscription_date.cleanDateStrategyIncrement(); }
	}

	public void cleanOldSubscriptionDateStrategyRandom()  {
		if( this.old_subscription_date != null ) { this.old_subscription_date.cleanDateStrategyRandom(); }
	}

	public void cleanOldSubscriptionDateStrategyFixed()  {
		if( this.old_subscription_date != null ) { this.old_subscription_date.cleanDateStrategyFixed(); }
	}

}