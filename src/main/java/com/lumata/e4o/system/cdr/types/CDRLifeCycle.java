package com.lumata.e4o.system.cdr.types;

import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.fields.*;
import com.lumata.e4o.exceptions.CDRException;
import java.util.Calendar;
import com.lumata.e4o.system.fields.FieldDateIncrement;
import com.lumata.e4o.system.fields.IFieldEnum;
import java.util.Set;

public class CDRLifeCycle extends CDR { 

	private final int FIELDS = 26;

	public CDRLifeCycle() {
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

	public void setNewImeiStrategyFixed( final Long value ) throws CDRException  {
		if( this.new_imei != null ) { this.new_imei.setLongStrategyFixed( value ); }
	}

	public void cleanNewImeiStrategyIncrement()  {
		if( this.new_imei != null ) { this.new_imei.cleanLongStrategyIncrement(); }
	}

	public void cleanNewImeiStrategyRandom()  {
		if( this.new_imei != null ) { this.new_imei.cleanLongStrategyRandom(); }
	}

	public void setNewImeiStrategyIncrement( final Long value, final Integer increment ) throws CDRException  {
		if( this.new_imei != null ) { this.new_imei.setLongStrategyIncrement( value, increment ); }
	}

	public void setNewImeiStrategyRandom( final Long min_value, final Long max_value ) throws CDRException  {
		if( this.new_imei != null ) { this.new_imei.setLongStrategyRandom( min_value, max_value ); }
	}

	public void cleanNewImeiStrategyFixed()  {
		if( this.new_imei != null ) { this.new_imei.cleanLongStrategyFixed(); }
	}

	@NewImei( position = 2 )
	public String getNewImei() throws CDRException  {
		return this.new_imei.getLong();
	}

	public void setOldImeiStrategyFixed( final Long value ) throws CDRException  {
		if( this.old_imei != null ) { this.old_imei.setLongStrategyFixed( value ); }
	}

	public void cleanOldImeiStrategyIncrement()  {
		if( this.old_imei != null ) { this.old_imei.cleanLongStrategyIncrement(); }
	}

	public void cleanOldImeiStrategyRandom()  {
		if( this.old_imei != null ) { this.old_imei.cleanLongStrategyRandom(); }
	}

	public void setOldImeiStrategyIncrement( final Long value, final Integer increment ) throws CDRException  {
		if( this.old_imei != null ) { this.old_imei.setLongStrategyIncrement( value, increment ); }
	}

	public void setOldImeiStrategyRandom( final Long min_value, final Long max_value ) throws CDRException  {
		if( this.old_imei != null ) { this.old_imei.setLongStrategyRandom( min_value, max_value ); }
	}

	public void cleanOldImeiStrategyFixed()  {
		if( this.old_imei != null ) { this.old_imei.cleanLongStrategyFixed(); }
	}

	@OldImei( position = 3 )
	public String getOldImei() throws CDRException  {
		return this.old_imei.getLong();
	}

	public void setNewImsiStrategyFixed( final Long value ) throws CDRException  {
		if( this.new_imsi != null ) { this.new_imsi.setLongStrategyFixed( value ); }
	}

	public void cleanNewImsiStrategyIncrement()  {
		if( this.new_imsi != null ) { this.new_imsi.cleanLongStrategyIncrement(); }
	}

	public void cleanNewImsiStrategyRandom()  {
		if( this.new_imsi != null ) { this.new_imsi.cleanLongStrategyRandom(); }
	}

	public void setNewImsiStrategyIncrement( final Long value, final Integer increment ) throws CDRException  {
		if( this.new_imsi != null ) { this.new_imsi.setLongStrategyIncrement( value, increment ); }
	}

	public void setNewImsiStrategyRandom( final Long min_value, final Long max_value ) throws CDRException  {
		if( this.new_imsi != null ) { this.new_imsi.setLongStrategyRandom( min_value, max_value ); }
	}

	public void cleanNewImsiStrategyFixed()  {
		if( this.new_imsi != null ) { this.new_imsi.cleanLongStrategyFixed(); }
	}

	@NewImsi( position = 4 )
	public String getNewImsi() throws CDRException  {
		return this.new_imsi.getLong();
	}

	public void setOldImsiStrategyFixed( final Long value ) throws CDRException  {
		if( this.old_imsi != null ) { this.old_imsi.setLongStrategyFixed( value ); }
	}

	public void cleanOldImsiStrategyIncrement()  {
		if( this.old_imsi != null ) { this.old_imsi.cleanLongStrategyIncrement(); }
	}

	public void cleanOldImsiStrategyRandom()  {
		if( this.old_imsi != null ) { this.old_imsi.cleanLongStrategyRandom(); }
	}

	public void setOldImsiStrategyIncrement( final Long value, final Integer increment ) throws CDRException  {
		if( this.old_imsi != null ) { this.old_imsi.setLongStrategyIncrement( value, increment ); }
	}

	public void setOldImsiStrategyRandom( final Long min_value, final Long max_value ) throws CDRException  {
		if( this.old_imsi != null ) { this.old_imsi.setLongStrategyRandom( min_value, max_value ); }
	}

	public void cleanOldImsiStrategyFixed()  {
		if( this.old_imsi != null ) { this.old_imsi.cleanLongStrategyFixed(); }
	}

	@OldImsi( position = 5 )
	public String getOldImsi() throws CDRException  {
		return this.old_imsi.getLong();
	}

	public void setNewSubscriptionDateStrategyFixed( final Calendar date ) throws CDRException  {
		if( this.new_subscription_date != null ) { this.new_subscription_date.setDateStrategyFixed( date ); }
	}

	public void cleanNewSubscriptionDateStrategyIncrement()  {
		if( this.new_subscription_date != null ) { this.new_subscription_date.cleanDateStrategyIncrement(); }
	}

	public void cleanNewSubscriptionDateStrategyRandom()  {
		if( this.new_subscription_date != null ) { this.new_subscription_date.cleanDateStrategyRandom(); }
	}

	public void setNewSubscriptionDateFormat( String format ) throws CDRException  {
		if( this.new_subscription_date != null ) { this.new_subscription_date.setDateFormat( format ); }
	}

	public void setNewSubscriptionDateStrategyIncrement( final Calendar date, final FieldDateIncrement increment ) throws CDRException  {
		if( this.new_subscription_date != null ) { this.new_subscription_date.setDateStrategyIncrement( date, increment ); }
	}

	public void setNewSubscriptionDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws CDRException  {
		if( this.new_subscription_date != null ) { this.new_subscription_date.setDateStrategyRandom( date_left, date_right ); }
	}

	public void cleanNewSubscriptionDateStrategyFixed()  {
		if( this.new_subscription_date != null ) { this.new_subscription_date.cleanDateStrategyFixed(); }
	}

	@NewSubscriptionDate( position = 6 )
	public String getNewSubscriptionDate()  {
		return this.new_subscription_date.getDate();
	}

	public void setOldSubscriptionDateStrategyFixed( final Calendar date ) throws CDRException  {
		if( this.old_subscription_date != null ) { this.old_subscription_date.setDateStrategyFixed( date ); }
	}

	public void cleanOldSubscriptionDateStrategyIncrement()  {
		if( this.old_subscription_date != null ) { this.old_subscription_date.cleanDateStrategyIncrement(); }
	}

	public void cleanOldSubscriptionDateStrategyRandom()  {
		if( this.old_subscription_date != null ) { this.old_subscription_date.cleanDateStrategyRandom(); }
	}

	public void setOldSubscriptionDateFormat( String format ) throws CDRException  {
		if( this.old_subscription_date != null ) { this.old_subscription_date.setDateFormat( format ); }
	}

	public void setOldSubscriptionDateStrategyIncrement( final Calendar date, final FieldDateIncrement increment ) throws CDRException  {
		if( this.old_subscription_date != null ) { this.old_subscription_date.setDateStrategyIncrement( date, increment ); }
	}

	public void setOldSubscriptionDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws CDRException  {
		if( this.old_subscription_date != null ) { this.old_subscription_date.setDateStrategyRandom( date_left, date_right ); }
	}

	public void cleanOldSubscriptionDateStrategyFixed()  {
		if( this.old_subscription_date != null ) { this.old_subscription_date.cleanDateStrategyFixed(); }
	}

	@OldSubscriptionDate( position = 7 )
	public String getOldSubscriptionDate()  {
		return this.old_subscription_date.getDate();
	}

	public void setNewProfileLength( final Integer length ) throws CDRException  {
		if( this.new_profile != null ) { this.new_profile.setStringLength( length ); }
	}

	public void setNewProfileStrategyFixed( final String value ) throws CDRException  {
		if( this.new_profile != null ) { this.new_profile.setStringStrategyFixed( value ); }
	}

	public void cleanNewProfileStrategyIncrement()  {
		if( this.new_profile != null ) { this.new_profile.cleanStringStrategyIncrement(); }
	}

	public void cleanNewProfileStrategyRandom()  {
		if( this.new_profile != null ) { this.new_profile.cleanStringStrategyRandom(); }
	}

	public void setNewProfileStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.new_profile != null ) { this.new_profile.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setNewProfileStrategyRandom( final Integer length ) throws CDRException  {
		if( this.new_profile != null ) { this.new_profile.setStringStrategyRandom( length ); }
	}

	public void cleanNewProfile()  {
		if( this.new_profile != null ) { this.new_profile.cleanString(); }
	}

	public void cleanNewProfileLength()  {
		if( this.new_profile != null ) { this.new_profile.cleanStringLength(); }
	}

	@NewProfile( position = 8 )
	public String getNewProfile() throws CDRException  {
		return this.new_profile.getString();
	}

	public void setOldProfileLength( final Integer length ) throws CDRException  {
		if( this.old_profile != null ) { this.old_profile.setStringLength( length ); }
	}

	public void setOldProfileStrategyFixed( final String value ) throws CDRException  {
		if( this.old_profile != null ) { this.old_profile.setStringStrategyFixed( value ); }
	}

	public void cleanOldProfileStrategyIncrement()  {
		if( this.old_profile != null ) { this.old_profile.cleanStringStrategyIncrement(); }
	}

	public void cleanOldProfileStrategyRandom()  {
		if( this.old_profile != null ) { this.old_profile.cleanStringStrategyRandom(); }
	}

	public void setOldProfileStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.old_profile != null ) { this.old_profile.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setOldProfileStrategyRandom( final Integer length ) throws CDRException  {
		if( this.old_profile != null ) { this.old_profile.setStringStrategyRandom( length ); }
	}

	public void cleanOldProfile()  {
		if( this.old_profile != null ) { this.old_profile.cleanString(); }
	}

	public void cleanOldProfileLength()  {
		if( this.old_profile != null ) { this.old_profile.cleanStringLength(); }
	}

	@OldProfile( position = 9 )
	public String getOldProfile() throws CDRException  {
		return this.old_profile.getString();
	}

	public void setNewRatePlanLength( final Integer length ) throws CDRException  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.setStringLength( length ); }
	}

	public void setNewRatePlanStrategyFixed( final String value ) throws CDRException  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.setStringStrategyFixed( value ); }
	}

	public void cleanNewRatePlanStrategyIncrement()  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.cleanStringStrategyIncrement(); }
	}

	public void cleanNewRatePlanStrategyRandom()  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.cleanStringStrategyRandom(); }
	}

	public void setNewRatePlanStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setNewRatePlanStrategyRandom( final Integer length ) throws CDRException  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.setStringStrategyRandom( length ); }
	}

	public void cleanNewRatePlan()  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.cleanString(); }
	}

	public void cleanNewRatePlanLength()  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.cleanStringLength(); }
	}

	@NewRatePlan( position = 10 )
	public String getNewRatePlan() throws CDRException  {
		return this.new_rate_plan.getString();
	}

	public void setOldRatePlanLength( final Integer length ) throws CDRException  {
		if( this.old_rate_plan != null ) { this.old_rate_plan.setStringLength( length ); }
	}

	public void setOldRatePlanStrategyFixed( final String value ) throws CDRException  {
		if( this.old_rate_plan != null ) { this.old_rate_plan.setStringStrategyFixed( value ); }
	}

	public void cleanOldRatePlanStrategyIncrement()  {
		if( this.old_rate_plan != null ) { this.old_rate_plan.cleanStringStrategyIncrement(); }
	}

	public void cleanOldRatePlanStrategyRandom()  {
		if( this.old_rate_plan != null ) { this.old_rate_plan.cleanStringStrategyRandom(); }
	}

	public void setOldRatePlanStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.old_rate_plan != null ) { this.old_rate_plan.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setOldRatePlanStrategyRandom( final Integer length ) throws CDRException  {
		if( this.old_rate_plan != null ) { this.old_rate_plan.setStringStrategyRandom( length ); }
	}

	public void cleanOldRatePlan()  {
		if( this.old_rate_plan != null ) { this.old_rate_plan.cleanString(); }
	}

	public void cleanOldRatePlanLength()  {
		if( this.old_rate_plan != null ) { this.old_rate_plan.cleanStringLength(); }
	}

	@OldRatePlan( position = 11 )
	public String getOldRatePlan() throws CDRException  {
		return this.old_rate_plan.getString();
	}

	@NewStatus( position = 12 )
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

	@OldStatus( position = 13 )
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

	public void setNewNetworkLength( final Integer length ) throws CDRException  {
		if( this.new_network != null ) { this.new_network.setStringLength( length ); }
	}

	public void setNewNetworkStrategyFixed( final String value ) throws CDRException  {
		if( this.new_network != null ) { this.new_network.setStringStrategyFixed( value ); }
	}

	public void cleanNewNetworkStrategyIncrement()  {
		if( this.new_network != null ) { this.new_network.cleanStringStrategyIncrement(); }
	}

	public void cleanNewNetworkStrategyRandom()  {
		if( this.new_network != null ) { this.new_network.cleanStringStrategyRandom(); }
	}

	public void setNewNetworkStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.new_network != null ) { this.new_network.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setNewNetworkStrategyRandom( final Integer length ) throws CDRException  {
		if( this.new_network != null ) { this.new_network.setStringStrategyRandom( length ); }
	}

	public void cleanNewNetwork()  {
		if( this.new_network != null ) { this.new_network.cleanString(); }
	}

	public void cleanNewNetworkLength()  {
		if( this.new_network != null ) { this.new_network.cleanStringLength(); }
	}

	@NewNetwork( position = 14 )
	public String getNewNetwork() throws CDRException  {
		return this.new_network.getString();
	}

	public void setOldNetworkLength( final Integer length ) throws CDRException  {
		if( this.old_network != null ) { this.old_network.setStringLength( length ); }
	}

	public void setOldNetworkStrategyFixed( final String value ) throws CDRException  {
		if( this.old_network != null ) { this.old_network.setStringStrategyFixed( value ); }
	}

	public void cleanOldNetworkStrategyIncrement()  {
		if( this.old_network != null ) { this.old_network.cleanStringStrategyIncrement(); }
	}

	public void cleanOldNetworkStrategyRandom()  {
		if( this.old_network != null ) { this.old_network.cleanStringStrategyRandom(); }
	}

	public void setOldNetworkStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.old_network != null ) { this.old_network.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setOldNetworkStrategyRandom( final Integer length ) throws CDRException  {
		if( this.old_network != null ) { this.old_network.setStringStrategyRandom( length ); }
	}

	public void cleanOldNetwork()  {
		if( this.old_network != null ) { this.old_network.cleanString(); }
	}

	public void cleanOldNetworkLength()  {
		if( this.old_network != null ) { this.old_network.cleanStringLength(); }
	}

	@OldNetwork( position = 15 )
	public String getOldNetwork() throws CDRException  {
		return this.old_network.getString();
	}

	public void setNewTongueLength( final Integer length ) throws CDRException  {
		if( this.new_tongue != null ) { this.new_tongue.setStringLength( length ); }
	}

	public void setNewTongueStrategyFixed( final String value ) throws CDRException  {
		if( this.new_tongue != null ) { this.new_tongue.setStringStrategyFixed( value ); }
	}

	public void cleanNewTongueStrategyIncrement()  {
		if( this.new_tongue != null ) { this.new_tongue.cleanStringStrategyIncrement(); }
	}

	public void cleanNewTongueStrategyRandom()  {
		if( this.new_tongue != null ) { this.new_tongue.cleanStringStrategyRandom(); }
	}

	public void setNewTongueStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.new_tongue != null ) { this.new_tongue.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setNewTongueStrategyRandom( final Integer length ) throws CDRException  {
		if( this.new_tongue != null ) { this.new_tongue.setStringStrategyRandom( length ); }
	}

	public void cleanNewTongue()  {
		if( this.new_tongue != null ) { this.new_tongue.cleanString(); }
	}

	public void cleanNewTongueLength()  {
		if( this.new_tongue != null ) { this.new_tongue.cleanStringLength(); }
	}

	@NewTongue( position = 16 )
	public String getNewTongue() throws CDRException  {
		return this.new_tongue.getString();
	}

	public void setOldTongueLength( final Integer length ) throws CDRException  {
		if( this.old_tongue != null ) { this.old_tongue.setStringLength( length ); }
	}

	public void setOldTongueStrategyFixed( final String value ) throws CDRException  {
		if( this.old_tongue != null ) { this.old_tongue.setStringStrategyFixed( value ); }
	}

	public void cleanOldTongueStrategyIncrement()  {
		if( this.old_tongue != null ) { this.old_tongue.cleanStringStrategyIncrement(); }
	}

	public void cleanOldTongueStrategyRandom()  {
		if( this.old_tongue != null ) { this.old_tongue.cleanStringStrategyRandom(); }
	}

	public void setOldTongueStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.old_tongue != null ) { this.old_tongue.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setOldTongueStrategyRandom( final Integer length ) throws CDRException  {
		if( this.old_tongue != null ) { this.old_tongue.setStringStrategyRandom( length ); }
	}

	public void cleanOldTongue()  {
		if( this.old_tongue != null ) { this.old_tongue.cleanString(); }
	}

	public void cleanOldTongueLength()  {
		if( this.old_tongue != null ) { this.old_tongue.cleanStringLength(); }
	}

	@OldTongue( position = 17 )
	public String getOldTongue() throws CDRException  {
		return this.old_tongue.getString();
	}

	public void setNewInTagLength( final Integer length ) throws CDRException  {
		if( this.new_in_tag != null ) { this.new_in_tag.setStringLength( length ); }
	}

	public void setNewInTagStrategyFixed( final String value ) throws CDRException  {
		if( this.new_in_tag != null ) { this.new_in_tag.setStringStrategyFixed( value ); }
	}

	public void cleanNewInTagStrategyIncrement()  {
		if( this.new_in_tag != null ) { this.new_in_tag.cleanStringStrategyIncrement(); }
	}

	public void cleanNewInTagStrategyRandom()  {
		if( this.new_in_tag != null ) { this.new_in_tag.cleanStringStrategyRandom(); }
	}

	public void setNewInTagStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.new_in_tag != null ) { this.new_in_tag.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setNewInTagStrategyRandom( final Integer length ) throws CDRException  {
		if( this.new_in_tag != null ) { this.new_in_tag.setStringStrategyRandom( length ); }
	}

	public void cleanNewInTag()  {
		if( this.new_in_tag != null ) { this.new_in_tag.cleanString(); }
	}

	public void cleanNewInTagLength()  {
		if( this.new_in_tag != null ) { this.new_in_tag.cleanStringLength(); }
	}

	@NewInTag( position = 18 )
	public String getNewInTag() throws CDRException  {
		return this.new_in_tag.getString();
	}

	public void setOldInTagLength( final Integer length ) throws CDRException  {
		if( this.old_in_tag != null ) { this.old_in_tag.setStringLength( length ); }
	}

	public void setOldInTagStrategyFixed( final String value ) throws CDRException  {
		if( this.old_in_tag != null ) { this.old_in_tag.setStringStrategyFixed( value ); }
	}

	public void cleanOldInTagStrategyIncrement()  {
		if( this.old_in_tag != null ) { this.old_in_tag.cleanStringStrategyIncrement(); }
	}

	public void cleanOldInTagStrategyRandom()  {
		if( this.old_in_tag != null ) { this.old_in_tag.cleanStringStrategyRandom(); }
	}

	public void setOldInTagStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.old_in_tag != null ) { this.old_in_tag.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setOldInTagStrategyRandom( final Integer length ) throws CDRException  {
		if( this.old_in_tag != null ) { this.old_in_tag.setStringStrategyRandom( length ); }
	}

	public void cleanOldInTag()  {
		if( this.old_in_tag != null ) { this.old_in_tag.cleanString(); }
	}

	public void cleanOldInTagLength()  {
		if( this.old_in_tag != null ) { this.old_in_tag.cleanStringLength(); }
	}

	@OldInTag( position = 19 )
	public String getOldInTag() throws CDRException  {
		return this.old_in_tag.getString();
	}

	@NewHobbies( position = 20 )
	public String getNewHobbies() throws CDRException  {
		return this.new_hobbies.getSet();
	}

	public void setNewHobbiesOptions( final String separator ) throws CDRException  {
		if( this.new_hobbies != null ) { this.new_hobbies.setSetOptions( separator ); }
	}

	public void setNewHobbiesStrategyFixed( final Set<String> values ) throws CDRException  {
		if( this.new_hobbies != null ) { this.new_hobbies.setSetStrategyFixed( values ); }
	}

	public void setNewHobbiesStrategyFixed( final Enum<?>[] values ) throws CDRException  {
		if( this.new_hobbies != null ) { this.new_hobbies.setSetStrategyFixed( values ); }
	}

	public void setNewHobbiesStrategyFixed( final String... values ) throws CDRException  {
		if( this.new_hobbies != null ) { this.new_hobbies.setSetStrategyFixed( values ); }
	}

	public void setNewHobbiesStrategyFixed() throws CDRException  {
		if( this.new_hobbies != null ) { this.new_hobbies.setSetStrategyFixed(); }
	}

	public void cleanNewHobbiesStrategyIncrement()  {
		if( this.new_hobbies != null ) { this.new_hobbies.cleanSetStrategyIncrement(); }
	}

	public void cleanNewHobbiesStrategyRandom()  {
		if( this.new_hobbies != null ) { this.new_hobbies.cleanSetStrategyRandom(); }
	}

	public void setNewHobbiesStrategyIncrement( final String startValue, final Integer increment, final Integer range ) throws CDRException  {
		if( this.new_hobbies != null ) { this.new_hobbies.setSetStrategyIncrement( startValue, increment, range ); }
	}

	public void setNewHobbiesStrategyIncrement( final Enum<?> startValue, final Integer increment, final Integer range ) throws CDRException  {
		if( this.new_hobbies != null ) { this.new_hobbies.setSetStrategyIncrement( startValue, increment, range ); }
	}

	public void setNewHobbiesStrategyRandom() throws CDRException  {
		if( this.new_hobbies != null ) { this.new_hobbies.setSetStrategyRandom(); }
	}

	public void setNewHobbiesStrategyRandom( final Integer range ) throws CDRException  {
		if( this.new_hobbies != null ) { this.new_hobbies.setSetStrategyRandom( range ); }
	}

	public void cleanNewHobbiesStrategyFixed()  {
		if( this.new_hobbies != null ) { this.new_hobbies.cleanSetStrategyFixed(); }
	}

	@OldHobbies( position = 21 )
	public String getOldHobbies() throws CDRException  {
		return this.old_hobbies.getSet();
	}

	public void setOldHobbiesOptions( final String separator ) throws CDRException  {
		if( this.old_hobbies != null ) { this.old_hobbies.setSetOptions( separator ); }
	}

	public void setOldHobbiesStrategyFixed( final Set<String> values ) throws CDRException  {
		if( this.old_hobbies != null ) { this.old_hobbies.setSetStrategyFixed( values ); }
	}

	public void setOldHobbiesStrategyFixed( final Enum<?>[] values ) throws CDRException  {
		if( this.old_hobbies != null ) { this.old_hobbies.setSetStrategyFixed( values ); }
	}

	public void setOldHobbiesStrategyFixed( final String... values ) throws CDRException  {
		if( this.old_hobbies != null ) { this.old_hobbies.setSetStrategyFixed( values ); }
	}

	public void setOldHobbiesStrategyFixed() throws CDRException  {
		if( this.old_hobbies != null ) { this.old_hobbies.setSetStrategyFixed(); }
	}

	public void cleanOldHobbiesStrategyIncrement()  {
		if( this.old_hobbies != null ) { this.old_hobbies.cleanSetStrategyIncrement(); }
	}

	public void cleanOldHobbiesStrategyRandom()  {
		if( this.old_hobbies != null ) { this.old_hobbies.cleanSetStrategyRandom(); }
	}

	public void setOldHobbiesStrategyIncrement( final String startValue, final Integer increment, final Integer range ) throws CDRException  {
		if( this.old_hobbies != null ) { this.old_hobbies.setSetStrategyIncrement( startValue, increment, range ); }
	}

	public void setOldHobbiesStrategyIncrement( final Enum<?> startValue, final Integer increment, final Integer range ) throws CDRException  {
		if( this.old_hobbies != null ) { this.old_hobbies.setSetStrategyIncrement( startValue, increment, range ); }
	}

	public void setOldHobbiesStrategyRandom() throws CDRException  {
		if( this.old_hobbies != null ) { this.old_hobbies.setSetStrategyRandom(); }
	}

	public void setOldHobbiesStrategyRandom( final Integer range ) throws CDRException  {
		if( this.old_hobbies != null ) { this.old_hobbies.setSetStrategyRandom( range ); }
	}

	public void cleanOldHobbiesStrategyFixed()  {
		if( this.old_hobbies != null ) { this.old_hobbies.cleanSetStrategyFixed(); }
	}

	@NewGender( position = 22 )
	public String getNewGender() throws CDRException  {
		return this.new_gender.getEnum();
	}

	public void setNewGenderStrategyFixed( final Enum<? extends IFieldEnum> value ) throws CDRException  {
		if( this.new_gender != null ) { this.new_gender.setEnumStrategyFixed( value ); }
	}

	public void cleanNewGenderStrategyIncrement()  {
		if( this.new_gender != null ) { this.new_gender.cleanEnumStrategyIncrement(); }
	}

	public void cleanNewGenderStrategyRandom()  {
		if( this.new_gender != null ) { this.new_gender.cleanEnumStrategyRandom(); }
	}

	public void setNewGenderStrategyIncrement( final Enum<? extends IFieldEnum> value, final Integer increment ) throws CDRException  {
		if( this.new_gender != null ) { this.new_gender.setEnumStrategyIncrement( value, increment ); }
	}

	public void setNewGenderStrategyRandom() throws CDRException  {
		if( this.new_gender != null ) { this.new_gender.setEnumStrategyRandom(); }
	}

	public void cleanNewGenderStrategyFixed()  {
		if( this.new_gender != null ) { this.new_gender.cleanEnumStrategyFixed(); }
	}

	@OldGender( position = 23 )
	public String getOldGender() throws CDRException  {
		return this.old_gender.getEnum();
	}

	public void setOldGenderStrategyFixed( final Enum<? extends IFieldEnum> value ) throws CDRException  {
		if( this.old_gender != null ) { this.old_gender.setEnumStrategyFixed( value ); }
	}

	public void cleanOldGenderStrategyIncrement()  {
		if( this.old_gender != null ) { this.old_gender.cleanEnumStrategyIncrement(); }
	}

	public void cleanOldGenderStrategyRandom()  {
		if( this.old_gender != null ) { this.old_gender.cleanEnumStrategyRandom(); }
	}

	public void setOldGenderStrategyIncrement( final Enum<? extends IFieldEnum> value, final Integer increment ) throws CDRException  {
		if( this.old_gender != null ) { this.old_gender.setEnumStrategyIncrement( value, increment ); }
	}

	public void setOldGenderStrategyRandom() throws CDRException  {
		if( this.old_gender != null ) { this.old_gender.setEnumStrategyRandom(); }
	}

	public void cleanOldGenderStrategyFixed()  {
		if( this.old_gender != null ) { this.old_gender.cleanEnumStrategyFixed(); }
	}

	public void setNewSalaryLength( final Integer length ) throws CDRException  {
		if( this.new_salary != null ) { this.new_salary.setStringLength( length ); }
	}

	public void setNewSalaryStrategyFixed( final String value ) throws CDRException  {
		if( this.new_salary != null ) { this.new_salary.setStringStrategyFixed( value ); }
	}

	public void cleanNewSalaryStrategyIncrement()  {
		if( this.new_salary != null ) { this.new_salary.cleanStringStrategyIncrement(); }
	}

	public void cleanNewSalaryStrategyRandom()  {
		if( this.new_salary != null ) { this.new_salary.cleanStringStrategyRandom(); }
	}

	public void setNewSalaryStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.new_salary != null ) { this.new_salary.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setNewSalaryStrategyRandom( final Integer length ) throws CDRException  {
		if( this.new_salary != null ) { this.new_salary.setStringStrategyRandom( length ); }
	}

	public void cleanNewSalary()  {
		if( this.new_salary != null ) { this.new_salary.cleanString(); }
	}

	public void cleanNewSalaryLength()  {
		if( this.new_salary != null ) { this.new_salary.cleanStringLength(); }
	}

	@NewSalary( position = 24 )
	public String getNewSalary() throws CDRException  {
		return this.new_salary.getString();
	}

	public void setOldSalaryLength( final Integer length ) throws CDRException  {
		if( this.old_salary != null ) { this.old_salary.setStringLength( length ); }
	}

	public void setOldSalaryStrategyFixed( final String value ) throws CDRException  {
		if( this.old_salary != null ) { this.old_salary.setStringStrategyFixed( value ); }
	}

	public void cleanOldSalaryStrategyIncrement()  {
		if( this.old_salary != null ) { this.old_salary.cleanStringStrategyIncrement(); }
	}

	public void cleanOldSalaryStrategyRandom()  {
		if( this.old_salary != null ) { this.old_salary.cleanStringStrategyRandom(); }
	}

	public void setOldSalaryStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.old_salary != null ) { this.old_salary.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setOldSalaryStrategyRandom( final Integer length ) throws CDRException  {
		if( this.old_salary != null ) { this.old_salary.setStringStrategyRandom( length ); }
	}

	public void cleanOldSalary()  {
		if( this.old_salary != null ) { this.old_salary.cleanString(); }
	}

	public void cleanOldSalaryLength()  {
		if( this.old_salary != null ) { this.old_salary.cleanStringLength(); }
	}

	@OldSalary( position = 25 )
	public String getOldSalary() throws CDRException  {
		return this.old_salary.getString();
	}

}