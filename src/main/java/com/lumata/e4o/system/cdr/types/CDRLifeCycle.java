package com.lumata.e4o.system.cdr.types;

import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.fields.*;
import com.lumata.e4o.exceptions.FieldException;
import java.util.Calendar;
import com.lumata.e4o.system.fields.FieldDateIncrement;
import com.lumata.e4o.system.fields.IFieldEnum;
import java.util.Set;

public class CDRLifeCycle extends CDR { 

	private final int FIELDS = 30;

	public CDRLifeCycle() {
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

	@NewImei( position = 2 )
	public String getNewImei() throws FieldException  {
		return this.newImei.getLong();
	}

	public void setNewImeiStrategyFixed( final Long value ) throws FieldException  {
		if( this.newImei != null ) { this.newImei.setLongStrategyFixed( value ); }
	}

	public void setNewImeiStrategyIncrement( final Long value, final Integer increment ) throws FieldException  {
		if( this.newImei != null ) { this.newImei.setLongStrategyIncrement( value, increment ); }
	}

	public void setNewImeiStrategyRandom( final Long min_value, final Long max_value ) throws FieldException  {
		if( this.newImei != null ) { this.newImei.setLongStrategyRandom( min_value, max_value ); }
	}

	public void cleanNewImeiStrategyFixed()  {
		if( this.newImei != null ) { this.newImei.cleanLongStrategyFixed(); }
	}

	public void cleanNewImeiStrategyIncrement()  {
		if( this.newImei != null ) { this.newImei.cleanLongStrategyIncrement(); }
	}

	public void cleanNewImeiStrategyRandom()  {
		if( this.newImei != null ) { this.newImei.cleanLongStrategyRandom(); }
	}

	@OldImei( position = 3 )
	public String getOldImei() throws FieldException  {
		return this.oldImei.getLong();
	}

	public void setOldImeiStrategyFixed( final Long value ) throws FieldException  {
		if( this.oldImei != null ) { this.oldImei.setLongStrategyFixed( value ); }
	}

	public void setOldImeiStrategyIncrement( final Long value, final Integer increment ) throws FieldException  {
		if( this.oldImei != null ) { this.oldImei.setLongStrategyIncrement( value, increment ); }
	}

	public void setOldImeiStrategyRandom( final Long min_value, final Long max_value ) throws FieldException  {
		if( this.oldImei != null ) { this.oldImei.setLongStrategyRandom( min_value, max_value ); }
	}

	public void cleanOldImeiStrategyFixed()  {
		if( this.oldImei != null ) { this.oldImei.cleanLongStrategyFixed(); }
	}

	public void cleanOldImeiStrategyIncrement()  {
		if( this.oldImei != null ) { this.oldImei.cleanLongStrategyIncrement(); }
	}

	public void cleanOldImeiStrategyRandom()  {
		if( this.oldImei != null ) { this.oldImei.cleanLongStrategyRandom(); }
	}

	@NewImsi( position = 4 )
	public String getNewImsi() throws FieldException  {
		return this.newImsi.getLong();
	}

	public void setNewImsiStrategyFixed( final Long value ) throws FieldException  {
		if( this.newImsi != null ) { this.newImsi.setLongStrategyFixed( value ); }
	}

	public void setNewImsiStrategyIncrement( final Long value, final Integer increment ) throws FieldException  {
		if( this.newImsi != null ) { this.newImsi.setLongStrategyIncrement( value, increment ); }
	}

	public void setNewImsiStrategyRandom( final Long min_value, final Long max_value ) throws FieldException  {
		if( this.newImsi != null ) { this.newImsi.setLongStrategyRandom( min_value, max_value ); }
	}

	public void cleanNewImsiStrategyFixed()  {
		if( this.newImsi != null ) { this.newImsi.cleanLongStrategyFixed(); }
	}

	public void cleanNewImsiStrategyIncrement()  {
		if( this.newImsi != null ) { this.newImsi.cleanLongStrategyIncrement(); }
	}

	public void cleanNewImsiStrategyRandom()  {
		if( this.newImsi != null ) { this.newImsi.cleanLongStrategyRandom(); }
	}

	@OldImsi( position = 5 )
	public String getOldImsi() throws FieldException  {
		return this.oldImsi.getLong();
	}

	public void setOldImsiStrategyFixed( final Long value ) throws FieldException  {
		if( this.oldImsi != null ) { this.oldImsi.setLongStrategyFixed( value ); }
	}

	public void setOldImsiStrategyIncrement( final Long value, final Integer increment ) throws FieldException  {
		if( this.oldImsi != null ) { this.oldImsi.setLongStrategyIncrement( value, increment ); }
	}

	public void setOldImsiStrategyRandom( final Long min_value, final Long max_value ) throws FieldException  {
		if( this.oldImsi != null ) { this.oldImsi.setLongStrategyRandom( min_value, max_value ); }
	}

	public void cleanOldImsiStrategyFixed()  {
		if( this.oldImsi != null ) { this.oldImsi.cleanLongStrategyFixed(); }
	}

	public void cleanOldImsiStrategyIncrement()  {
		if( this.oldImsi != null ) { this.oldImsi.cleanLongStrategyIncrement(); }
	}

	public void cleanOldImsiStrategyRandom()  {
		if( this.oldImsi != null ) { this.oldImsi.cleanLongStrategyRandom(); }
	}

	@NewSubscriptionDate( position = 6 )
	public String getNewSubscriptionDate()  {
		return this.newSubscriptionDate.getDate();
	}

	public void setNewSubscriptionDateStrategyFixed( final Calendar date ) throws FieldException  {
		if( this.newSubscriptionDate != null ) { this.newSubscriptionDate.setDateStrategyFixed( date ); }
	}

	public void setNewSubscriptionDateFormat( String format ) throws FieldException  {
		if( this.newSubscriptionDate != null ) { this.newSubscriptionDate.setDateFormat( format ); }
	}

	public void setNewSubscriptionDateStrategyIncrement( final Calendar date, final FieldDateIncrement increment ) throws FieldException  {
		if( this.newSubscriptionDate != null ) { this.newSubscriptionDate.setDateStrategyIncrement( date, increment ); }
	}

	public void setNewSubscriptionDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws FieldException  {
		if( this.newSubscriptionDate != null ) { this.newSubscriptionDate.setDateStrategyRandom( date_left, date_right ); }
	}

	public void cleanNewSubscriptionDateStrategyFixed()  {
		if( this.newSubscriptionDate != null ) { this.newSubscriptionDate.cleanDateStrategyFixed(); }
	}

	public void cleanNewSubscriptionDateStrategyIncrement()  {
		if( this.newSubscriptionDate != null ) { this.newSubscriptionDate.cleanDateStrategyIncrement(); }
	}

	public void cleanNewSubscriptionDateStrategyRandom()  {
		if( this.newSubscriptionDate != null ) { this.newSubscriptionDate.cleanDateStrategyRandom(); }
	}

	@OldSubscriptionDate( position = 7 )
	public String getOldSubscriptionDate()  {
		return this.oldSubscriptionDate.getDate();
	}

	public void setOldSubscriptionDateStrategyFixed( final Calendar date ) throws FieldException  {
		if( this.oldSubscriptionDate != null ) { this.oldSubscriptionDate.setDateStrategyFixed( date ); }
	}

	public void setOldSubscriptionDateFormat( String format ) throws FieldException  {
		if( this.oldSubscriptionDate != null ) { this.oldSubscriptionDate.setDateFormat( format ); }
	}

	public void setOldSubscriptionDateStrategyIncrement( final Calendar date, final FieldDateIncrement increment ) throws FieldException  {
		if( this.oldSubscriptionDate != null ) { this.oldSubscriptionDate.setDateStrategyIncrement( date, increment ); }
	}

	public void setOldSubscriptionDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws FieldException  {
		if( this.oldSubscriptionDate != null ) { this.oldSubscriptionDate.setDateStrategyRandom( date_left, date_right ); }
	}

	public void cleanOldSubscriptionDateStrategyFixed()  {
		if( this.oldSubscriptionDate != null ) { this.oldSubscriptionDate.cleanDateStrategyFixed(); }
	}

	public void cleanOldSubscriptionDateStrategyIncrement()  {
		if( this.oldSubscriptionDate != null ) { this.oldSubscriptionDate.cleanDateStrategyIncrement(); }
	}

	public void cleanOldSubscriptionDateStrategyRandom()  {
		if( this.oldSubscriptionDate != null ) { this.oldSubscriptionDate.cleanDateStrategyRandom(); }
	}

	@NewProfile( position = 8 )
	public String getNewProfile() throws FieldException  {
		return this.newProfile.getString();
	}

	public void setNewProfileLength( final Integer length ) throws FieldException  {
		if( this.newProfile != null ) { this.newProfile.setStringLength( length ); }
	}

	public void setNewProfileStrategyFixed( final String value ) throws FieldException  {
		if( this.newProfile != null ) { this.newProfile.setStringStrategyFixed( value ); }
	}

	public void setNewProfileStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.newProfile != null ) { this.newProfile.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setNewProfileStrategyRandom( final Integer length ) throws FieldException  {
		if( this.newProfile != null ) { this.newProfile.setStringStrategyRandom( length ); }
	}

	public void cleanNewProfile()  {
		if( this.newProfile != null ) { this.newProfile.cleanString(); }
	}

	public void cleanNewProfileLength()  {
		if( this.newProfile != null ) { this.newProfile.cleanStringLength(); }
	}

	public void cleanNewProfileStrategyIncrement()  {
		if( this.newProfile != null ) { this.newProfile.cleanStringStrategyIncrement(); }
	}

	public void cleanNewProfileStrategyRandom()  {
		if( this.newProfile != null ) { this.newProfile.cleanStringStrategyRandom(); }
	}

	@OldProfile( position = 9 )
	public String getOldProfile() throws FieldException  {
		return this.oldProfile.getString();
	}

	public void setOldProfileLength( final Integer length ) throws FieldException  {
		if( this.oldProfile != null ) { this.oldProfile.setStringLength( length ); }
	}

	public void setOldProfileStrategyFixed( final String value ) throws FieldException  {
		if( this.oldProfile != null ) { this.oldProfile.setStringStrategyFixed( value ); }
	}

	public void setOldProfileStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.oldProfile != null ) { this.oldProfile.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setOldProfileStrategyRandom( final Integer length ) throws FieldException  {
		if( this.oldProfile != null ) { this.oldProfile.setStringStrategyRandom( length ); }
	}

	public void cleanOldProfile()  {
		if( this.oldProfile != null ) { this.oldProfile.cleanString(); }
	}

	public void cleanOldProfileLength()  {
		if( this.oldProfile != null ) { this.oldProfile.cleanStringLength(); }
	}

	public void cleanOldProfileStrategyIncrement()  {
		if( this.oldProfile != null ) { this.oldProfile.cleanStringStrategyIncrement(); }
	}

	public void cleanOldProfileStrategyRandom()  {
		if( this.oldProfile != null ) { this.oldProfile.cleanStringStrategyRandom(); }
	}

	@NewRatePlan( position = 10 )
	public String getNewRatePlan() throws FieldException  {
		return this.newRatePlan.getString();
	}

	public void setNewRatePlanLength( final Integer length ) throws FieldException  {
		if( this.newRatePlan != null ) { this.newRatePlan.setStringLength( length ); }
	}

	public void setNewRatePlanStrategyFixed( final String value ) throws FieldException  {
		if( this.newRatePlan != null ) { this.newRatePlan.setStringStrategyFixed( value ); }
	}

	public void setNewRatePlanStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.newRatePlan != null ) { this.newRatePlan.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setNewRatePlanStrategyRandom( final Integer length ) throws FieldException  {
		if( this.newRatePlan != null ) { this.newRatePlan.setStringStrategyRandom( length ); }
	}

	public void cleanNewRatePlan()  {
		if( this.newRatePlan != null ) { this.newRatePlan.cleanString(); }
	}

	public void cleanNewRatePlanLength()  {
		if( this.newRatePlan != null ) { this.newRatePlan.cleanStringLength(); }
	}

	public void cleanNewRatePlanStrategyIncrement()  {
		if( this.newRatePlan != null ) { this.newRatePlan.cleanStringStrategyIncrement(); }
	}

	public void cleanNewRatePlanStrategyRandom()  {
		if( this.newRatePlan != null ) { this.newRatePlan.cleanStringStrategyRandom(); }
	}

	@OldRatePlan( position = 11 )
	public String getOldRatePlan() throws FieldException  {
		return this.oldRatePlan.getString();
	}

	public void setOldRatePlanLength( final Integer length ) throws FieldException  {
		if( this.oldRatePlan != null ) { this.oldRatePlan.setStringLength( length ); }
	}

	public void setOldRatePlanStrategyFixed( final String value ) throws FieldException  {
		if( this.oldRatePlan != null ) { this.oldRatePlan.setStringStrategyFixed( value ); }
	}

	public void setOldRatePlanStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.oldRatePlan != null ) { this.oldRatePlan.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setOldRatePlanStrategyRandom( final Integer length ) throws FieldException  {
		if( this.oldRatePlan != null ) { this.oldRatePlan.setStringStrategyRandom( length ); }
	}

	public void cleanOldRatePlan()  {
		if( this.oldRatePlan != null ) { this.oldRatePlan.cleanString(); }
	}

	public void cleanOldRatePlanLength()  {
		if( this.oldRatePlan != null ) { this.oldRatePlan.cleanStringLength(); }
	}

	public void cleanOldRatePlanStrategyIncrement()  {
		if( this.oldRatePlan != null ) { this.oldRatePlan.cleanStringStrategyIncrement(); }
	}

	public void cleanOldRatePlanStrategyRandom()  {
		if( this.oldRatePlan != null ) { this.oldRatePlan.cleanStringStrategyRandom(); }
	}

	@NewStatus( position = 12 )
	public String getNewStatus() throws FieldException  {
		return this.newStatus.getEnum();
	}

	public void setNewStatusStrategyFixed( final Enum<? extends IFieldEnum> value ) throws FieldException  {
		if( this.newStatus != null ) { this.newStatus.setEnumStrategyFixed( value ); }
	}

	public void setNewStatusStrategyIncrement( final Enum<? extends IFieldEnum> value, final Integer increment ) throws FieldException  {
		if( this.newStatus != null ) { this.newStatus.setEnumStrategyIncrement( value, increment ); }
	}

	public void setNewStatusStrategyRandom() throws FieldException  {
		if( this.newStatus != null ) { this.newStatus.setEnumStrategyRandom(); }
	}

	public void cleanNewStatusStrategyFixed()  {
		if( this.newStatus != null ) { this.newStatus.cleanEnumStrategyFixed(); }
	}

	public void cleanNewStatusStrategyIncrement()  {
		if( this.newStatus != null ) { this.newStatus.cleanEnumStrategyIncrement(); }
	}

	public void cleanNewStatusStrategyRandom()  {
		if( this.newStatus != null ) { this.newStatus.cleanEnumStrategyRandom(); }
	}

	@OldStatus( position = 13 )
	public String getOldStatus() throws FieldException  {
		return this.oldStatus.getEnum();
	}

	public void setOldStatusStrategyFixed( final Enum<? extends IFieldEnum> value ) throws FieldException  {
		if( this.oldStatus != null ) { this.oldStatus.setEnumStrategyFixed( value ); }
	}

	public void setOldStatusStrategyIncrement( final Enum<? extends IFieldEnum> value, final Integer increment ) throws FieldException  {
		if( this.oldStatus != null ) { this.oldStatus.setEnumStrategyIncrement( value, increment ); }
	}

	public void setOldStatusStrategyRandom() throws FieldException  {
		if( this.oldStatus != null ) { this.oldStatus.setEnumStrategyRandom(); }
	}

	public void cleanOldStatusStrategyFixed()  {
		if( this.oldStatus != null ) { this.oldStatus.cleanEnumStrategyFixed(); }
	}

	public void cleanOldStatusStrategyIncrement()  {
		if( this.oldStatus != null ) { this.oldStatus.cleanEnumStrategyIncrement(); }
	}

	public void cleanOldStatusStrategyRandom()  {
		if( this.oldStatus != null ) { this.oldStatus.cleanEnumStrategyRandom(); }
	}

	@NewNetwork( position = 14 )
	public String getNewNetwork() throws FieldException  {
		return this.newNetwork.getString();
	}

	public void setNewNetworkLength( final Integer length ) throws FieldException  {
		if( this.newNetwork != null ) { this.newNetwork.setStringLength( length ); }
	}

	public void setNewNetworkStrategyFixed( final String value ) throws FieldException  {
		if( this.newNetwork != null ) { this.newNetwork.setStringStrategyFixed( value ); }
	}

	public void setNewNetworkStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.newNetwork != null ) { this.newNetwork.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setNewNetworkStrategyRandom( final Integer length ) throws FieldException  {
		if( this.newNetwork != null ) { this.newNetwork.setStringStrategyRandom( length ); }
	}

	public void cleanNewNetwork()  {
		if( this.newNetwork != null ) { this.newNetwork.cleanString(); }
	}

	public void cleanNewNetworkLength()  {
		if( this.newNetwork != null ) { this.newNetwork.cleanStringLength(); }
	}

	public void cleanNewNetworkStrategyIncrement()  {
		if( this.newNetwork != null ) { this.newNetwork.cleanStringStrategyIncrement(); }
	}

	public void cleanNewNetworkStrategyRandom()  {
		if( this.newNetwork != null ) { this.newNetwork.cleanStringStrategyRandom(); }
	}

	@OldNetwork( position = 15 )
	public String getOldNetwork() throws FieldException  {
		return this.oldNetwork.getString();
	}

	public void setOldNetworkLength( final Integer length ) throws FieldException  {
		if( this.oldNetwork != null ) { this.oldNetwork.setStringLength( length ); }
	}

	public void setOldNetworkStrategyFixed( final String value ) throws FieldException  {
		if( this.oldNetwork != null ) { this.oldNetwork.setStringStrategyFixed( value ); }
	}

	public void setOldNetworkStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.oldNetwork != null ) { this.oldNetwork.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setOldNetworkStrategyRandom( final Integer length ) throws FieldException  {
		if( this.oldNetwork != null ) { this.oldNetwork.setStringStrategyRandom( length ); }
	}

	public void cleanOldNetwork()  {
		if( this.oldNetwork != null ) { this.oldNetwork.cleanString(); }
	}

	public void cleanOldNetworkLength()  {
		if( this.oldNetwork != null ) { this.oldNetwork.cleanStringLength(); }
	}

	public void cleanOldNetworkStrategyIncrement()  {
		if( this.oldNetwork != null ) { this.oldNetwork.cleanStringStrategyIncrement(); }
	}

	public void cleanOldNetworkStrategyRandom()  {
		if( this.oldNetwork != null ) { this.oldNetwork.cleanStringStrategyRandom(); }
	}

	@NewTongue( position = 16 )
	public String getNewTongue() throws FieldException  {
		return this.newTongue.getString();
	}

	public void setNewTongueLength( final Integer length ) throws FieldException  {
		if( this.newTongue != null ) { this.newTongue.setStringLength( length ); }
	}

	public void setNewTongueStrategyFixed( final String value ) throws FieldException  {
		if( this.newTongue != null ) { this.newTongue.setStringStrategyFixed( value ); }
	}

	public void setNewTongueStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.newTongue != null ) { this.newTongue.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setNewTongueStrategyRandom( final Integer length ) throws FieldException  {
		if( this.newTongue != null ) { this.newTongue.setStringStrategyRandom( length ); }
	}

	public void cleanNewTongue()  {
		if( this.newTongue != null ) { this.newTongue.cleanString(); }
	}

	public void cleanNewTongueLength()  {
		if( this.newTongue != null ) { this.newTongue.cleanStringLength(); }
	}

	public void cleanNewTongueStrategyIncrement()  {
		if( this.newTongue != null ) { this.newTongue.cleanStringStrategyIncrement(); }
	}

	public void cleanNewTongueStrategyRandom()  {
		if( this.newTongue != null ) { this.newTongue.cleanStringStrategyRandom(); }
	}

	@OldTongue( position = 17 )
	public String getOldTongue() throws FieldException  {
		return this.oldTongue.getString();
	}

	public void setOldTongueLength( final Integer length ) throws FieldException  {
		if( this.oldTongue != null ) { this.oldTongue.setStringLength( length ); }
	}

	public void setOldTongueStrategyFixed( final String value ) throws FieldException  {
		if( this.oldTongue != null ) { this.oldTongue.setStringStrategyFixed( value ); }
	}

	public void setOldTongueStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.oldTongue != null ) { this.oldTongue.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setOldTongueStrategyRandom( final Integer length ) throws FieldException  {
		if( this.oldTongue != null ) { this.oldTongue.setStringStrategyRandom( length ); }
	}

	public void cleanOldTongue()  {
		if( this.oldTongue != null ) { this.oldTongue.cleanString(); }
	}

	public void cleanOldTongueLength()  {
		if( this.oldTongue != null ) { this.oldTongue.cleanStringLength(); }
	}

	public void cleanOldTongueStrategyIncrement()  {
		if( this.oldTongue != null ) { this.oldTongue.cleanStringStrategyIncrement(); }
	}

	public void cleanOldTongueStrategyRandom()  {
		if( this.oldTongue != null ) { this.oldTongue.cleanStringStrategyRandom(); }
	}

	@NewInTag( position = 18 )
	public String getNewInTag() throws FieldException  {
		return this.newInTag.getString();
	}

	public void setNewInTagLength( final Integer length ) throws FieldException  {
		if( this.newInTag != null ) { this.newInTag.setStringLength( length ); }
	}

	public void setNewInTagStrategyFixed( final String value ) throws FieldException  {
		if( this.newInTag != null ) { this.newInTag.setStringStrategyFixed( value ); }
	}

	public void setNewInTagStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.newInTag != null ) { this.newInTag.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setNewInTagStrategyRandom( final Integer length ) throws FieldException  {
		if( this.newInTag != null ) { this.newInTag.setStringStrategyRandom( length ); }
	}

	public void cleanNewInTag()  {
		if( this.newInTag != null ) { this.newInTag.cleanString(); }
	}

	public void cleanNewInTagLength()  {
		if( this.newInTag != null ) { this.newInTag.cleanStringLength(); }
	}

	public void cleanNewInTagStrategyIncrement()  {
		if( this.newInTag != null ) { this.newInTag.cleanStringStrategyIncrement(); }
	}

	public void cleanNewInTagStrategyRandom()  {
		if( this.newInTag != null ) { this.newInTag.cleanStringStrategyRandom(); }
	}

	@OldInTag( position = 19 )
	public String getOldInTag() throws FieldException  {
		return this.oldInTag.getString();
	}

	public void setOldInTagLength( final Integer length ) throws FieldException  {
		if( this.oldInTag != null ) { this.oldInTag.setStringLength( length ); }
	}

	public void setOldInTagStrategyFixed( final String value ) throws FieldException  {
		if( this.oldInTag != null ) { this.oldInTag.setStringStrategyFixed( value ); }
	}

	public void setOldInTagStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.oldInTag != null ) { this.oldInTag.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setOldInTagStrategyRandom( final Integer length ) throws FieldException  {
		if( this.oldInTag != null ) { this.oldInTag.setStringStrategyRandom( length ); }
	}

	public void cleanOldInTag()  {
		if( this.oldInTag != null ) { this.oldInTag.cleanString(); }
	}

	public void cleanOldInTagLength()  {
		if( this.oldInTag != null ) { this.oldInTag.cleanStringLength(); }
	}

	public void cleanOldInTagStrategyIncrement()  {
		if( this.oldInTag != null ) { this.oldInTag.cleanStringStrategyIncrement(); }
	}

	public void cleanOldInTagStrategyRandom()  {
		if( this.oldInTag != null ) { this.oldInTag.cleanStringStrategyRandom(); }
	}

	@NewHobbies( position = 20 )
	public String getNewHobbies() throws FieldException  {
		return this.newHobbies.getSet();
	}

	public void setNewHobbiesOptions( final Set<String> values ) throws FieldException  {
		if( this.newHobbies != null ) { this.newHobbies.setSetOptions( values ); }
	}

	public void setNewHobbiesOptions( final Enum<?>... values ) throws FieldException  {
		if( this.newHobbies != null ) { this.newHobbies.setSetOptions( values ); }
	}

	public void setNewHobbiesOptions( final String... values ) throws FieldException  {
		if( this.newHobbies != null ) { this.newHobbies.setSetOptions( values ); }
	}

	public void setNewHobbiesOptions( final String separator ) throws FieldException  {
		if( this.newHobbies != null ) { this.newHobbies.setSetOptions( separator ); }
	}

	public void setNewHobbiesStrategyFixed( final Enum<?>[] values ) throws FieldException  {
		if( this.newHobbies != null ) { this.newHobbies.setSetStrategyFixed( values ); }
	}

	public void setNewHobbiesStrategyFixed( final String... values ) throws FieldException  {
		if( this.newHobbies != null ) { this.newHobbies.setSetStrategyFixed( values ); }
	}

	public void setNewHobbiesStrategyFixed() throws FieldException  {
		if( this.newHobbies != null ) { this.newHobbies.setSetStrategyFixed(); }
	}

	public void setNewHobbiesStrategyFixed( final Set<String> values ) throws FieldException  {
		if( this.newHobbies != null ) { this.newHobbies.setSetStrategyFixed( values ); }
	}

	public void setNewHobbiesStrategyIncrement( final Enum<?> startValue, final Integer increment, final Integer range ) throws FieldException  {
		if( this.newHobbies != null ) { this.newHobbies.setSetStrategyIncrement( startValue, increment, range ); }
	}

	public void setNewHobbiesStrategyIncrement( final String startValue, final Integer increment, final Integer range ) throws FieldException  {
		if( this.newHobbies != null ) { this.newHobbies.setSetStrategyIncrement( startValue, increment, range ); }
	}

	public void setNewHobbiesStrategyRandom() throws FieldException  {
		if( this.newHobbies != null ) { this.newHobbies.setSetStrategyRandom(); }
	}

	public void setNewHobbiesStrategyRandom( final Integer range ) throws FieldException  {
		if( this.newHobbies != null ) { this.newHobbies.setSetStrategyRandom( range ); }
	}

	public void cleanNewHobbiesStrategyFixed()  {
		if( this.newHobbies != null ) { this.newHobbies.cleanSetStrategyFixed(); }
	}

	public void cleanNewHobbiesStrategyIncrement()  {
		if( this.newHobbies != null ) { this.newHobbies.cleanSetStrategyIncrement(); }
	}

	public void cleanNewHobbiesStrategyRandom()  {
		if( this.newHobbies != null ) { this.newHobbies.cleanSetStrategyRandom(); }
	}

	@OldHobbies( position = 21 )
	public String getOldHobbies() throws FieldException  {
		return this.oldHobbies.getSet();
	}

	public void setOldHobbiesOptions( final Set<String> values ) throws FieldException  {
		if( this.oldHobbies != null ) { this.oldHobbies.setSetOptions( values ); }
	}

	public void setOldHobbiesOptions( final Enum<?>... values ) throws FieldException  {
		if( this.oldHobbies != null ) { this.oldHobbies.setSetOptions( values ); }
	}

	public void setOldHobbiesOptions( final String... values ) throws FieldException  {
		if( this.oldHobbies != null ) { this.oldHobbies.setSetOptions( values ); }
	}

	public void setOldHobbiesOptions( final String separator ) throws FieldException  {
		if( this.oldHobbies != null ) { this.oldHobbies.setSetOptions( separator ); }
	}

	public void setOldHobbiesStrategyFixed( final Enum<?>[] values ) throws FieldException  {
		if( this.oldHobbies != null ) { this.oldHobbies.setSetStrategyFixed( values ); }
	}

	public void setOldHobbiesStrategyFixed( final String... values ) throws FieldException  {
		if( this.oldHobbies != null ) { this.oldHobbies.setSetStrategyFixed( values ); }
	}

	public void setOldHobbiesStrategyFixed() throws FieldException  {
		if( this.oldHobbies != null ) { this.oldHobbies.setSetStrategyFixed(); }
	}

	public void setOldHobbiesStrategyFixed( final Set<String> values ) throws FieldException  {
		if( this.oldHobbies != null ) { this.oldHobbies.setSetStrategyFixed( values ); }
	}

	public void setOldHobbiesStrategyIncrement( final Enum<?> startValue, final Integer increment, final Integer range ) throws FieldException  {
		if( this.oldHobbies != null ) { this.oldHobbies.setSetStrategyIncrement( startValue, increment, range ); }
	}

	public void setOldHobbiesStrategyIncrement( final String startValue, final Integer increment, final Integer range ) throws FieldException  {
		if( this.oldHobbies != null ) { this.oldHobbies.setSetStrategyIncrement( startValue, increment, range ); }
	}

	public void setOldHobbiesStrategyRandom() throws FieldException  {
		if( this.oldHobbies != null ) { this.oldHobbies.setSetStrategyRandom(); }
	}

	public void setOldHobbiesStrategyRandom( final Integer range ) throws FieldException  {
		if( this.oldHobbies != null ) { this.oldHobbies.setSetStrategyRandom( range ); }
	}

	public void cleanOldHobbiesStrategyFixed()  {
		if( this.oldHobbies != null ) { this.oldHobbies.cleanSetStrategyFixed(); }
	}

	public void cleanOldHobbiesStrategyIncrement()  {
		if( this.oldHobbies != null ) { this.oldHobbies.cleanSetStrategyIncrement(); }
	}

	public void cleanOldHobbiesStrategyRandom()  {
		if( this.oldHobbies != null ) { this.oldHobbies.cleanSetStrategyRandom(); }
	}

	@NewGender( position = 22 )
	public String getNewGender() throws FieldException  {
		return this.newGender.getEnum();
	}

	public void setNewGenderStrategyFixed( final Enum<? extends IFieldEnum> value ) throws FieldException  {
		if( this.newGender != null ) { this.newGender.setEnumStrategyFixed( value ); }
	}

	public void setNewGenderStrategyIncrement( final Enum<? extends IFieldEnum> value, final Integer increment ) throws FieldException  {
		if( this.newGender != null ) { this.newGender.setEnumStrategyIncrement( value, increment ); }
	}

	public void setNewGenderStrategyRandom() throws FieldException  {
		if( this.newGender != null ) { this.newGender.setEnumStrategyRandom(); }
	}

	public void cleanNewGenderStrategyFixed()  {
		if( this.newGender != null ) { this.newGender.cleanEnumStrategyFixed(); }
	}

	public void cleanNewGenderStrategyIncrement()  {
		if( this.newGender != null ) { this.newGender.cleanEnumStrategyIncrement(); }
	}

	public void cleanNewGenderStrategyRandom()  {
		if( this.newGender != null ) { this.newGender.cleanEnumStrategyRandom(); }
	}

	@OldGender( position = 23 )
	public String getOldGender() throws FieldException  {
		return this.oldGender.getEnum();
	}

	public void setOldGenderStrategyFixed( final Enum<? extends IFieldEnum> value ) throws FieldException  {
		if( this.oldGender != null ) { this.oldGender.setEnumStrategyFixed( value ); }
	}

	public void setOldGenderStrategyIncrement( final Enum<? extends IFieldEnum> value, final Integer increment ) throws FieldException  {
		if( this.oldGender != null ) { this.oldGender.setEnumStrategyIncrement( value, increment ); }
	}

	public void setOldGenderStrategyRandom() throws FieldException  {
		if( this.oldGender != null ) { this.oldGender.setEnumStrategyRandom(); }
	}

	public void cleanOldGenderStrategyFixed()  {
		if( this.oldGender != null ) { this.oldGender.cleanEnumStrategyFixed(); }
	}

	public void cleanOldGenderStrategyIncrement()  {
		if( this.oldGender != null ) { this.oldGender.cleanEnumStrategyIncrement(); }
	}

	public void cleanOldGenderStrategyRandom()  {
		if( this.oldGender != null ) { this.oldGender.cleanEnumStrategyRandom(); }
	}

	@NewSalary( position = 24 )
	public String getNewSalary() throws FieldException  {
		return this.newSalary.getString();
	}

	public void setNewSalaryLength( final Integer length ) throws FieldException  {
		if( this.newSalary != null ) { this.newSalary.setStringLength( length ); }
	}

	public void setNewSalaryStrategyFixed( final String value ) throws FieldException  {
		if( this.newSalary != null ) { this.newSalary.setStringStrategyFixed( value ); }
	}

	public void setNewSalaryStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.newSalary != null ) { this.newSalary.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setNewSalaryStrategyRandom( final Integer length ) throws FieldException  {
		if( this.newSalary != null ) { this.newSalary.setStringStrategyRandom( length ); }
	}

	public void cleanNewSalary()  {
		if( this.newSalary != null ) { this.newSalary.cleanString(); }
	}

	public void cleanNewSalaryLength()  {
		if( this.newSalary != null ) { this.newSalary.cleanStringLength(); }
	}

	public void cleanNewSalaryStrategyIncrement()  {
		if( this.newSalary != null ) { this.newSalary.cleanStringStrategyIncrement(); }
	}

	public void cleanNewSalaryStrategyRandom()  {
		if( this.newSalary != null ) { this.newSalary.cleanStringStrategyRandom(); }
	}

	@OldSalary( position = 25 )
	public String getOldSalary() throws FieldException  {
		return this.oldSalary.getString();
	}

	public void setOldSalaryLength( final Integer length ) throws FieldException  {
		if( this.oldSalary != null ) { this.oldSalary.setStringLength( length ); }
	}

	public void setOldSalaryStrategyFixed( final String value ) throws FieldException  {
		if( this.oldSalary != null ) { this.oldSalary.setStringStrategyFixed( value ); }
	}

	public void setOldSalaryStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.oldSalary != null ) { this.oldSalary.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setOldSalaryStrategyRandom( final Integer length ) throws FieldException  {
		if( this.oldSalary != null ) { this.oldSalary.setStringStrategyRandom( length ); }
	}

	public void cleanOldSalary()  {
		if( this.oldSalary != null ) { this.oldSalary.cleanString(); }
	}

	public void cleanOldSalaryLength()  {
		if( this.oldSalary != null ) { this.oldSalary.cleanStringLength(); }
	}

	public void cleanOldSalaryStrategyIncrement()  {
		if( this.oldSalary != null ) { this.oldSalary.cleanStringStrategyIncrement(); }
	}

	public void cleanOldSalaryStrategyRandom()  {
		if( this.oldSalary != null ) { this.oldSalary.cleanStringStrategyRandom(); }
	}

	@NewAddress( position = 26 )
	public String getNewAddress() throws FieldException  {
		return this.newAddress.getString();
	}

	public void setNewAddressLength( final Integer length ) throws FieldException  {
		if( this.newAddress != null ) { this.newAddress.setStringLength( length ); }
	}

	public void setNewAddressStrategyFixed( final String value ) throws FieldException  {
		if( this.newAddress != null ) { this.newAddress.setStringStrategyFixed( value ); }
	}

	public void setNewAddressStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.newAddress != null ) { this.newAddress.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setNewAddressStrategyRandom( final Integer length ) throws FieldException  {
		if( this.newAddress != null ) { this.newAddress.setStringStrategyRandom( length ); }
	}

	public void cleanNewAddress()  {
		if( this.newAddress != null ) { this.newAddress.cleanString(); }
	}

	public void cleanNewAddressLength()  {
		if( this.newAddress != null ) { this.newAddress.cleanStringLength(); }
	}

	public void cleanNewAddressStrategyIncrement()  {
		if( this.newAddress != null ) { this.newAddress.cleanStringStrategyIncrement(); }
	}

	public void cleanNewAddressStrategyRandom()  {
		if( this.newAddress != null ) { this.newAddress.cleanStringStrategyRandom(); }
	}

	@OldAddress( position = 27 )
	public String getOldAddress() throws FieldException  {
		return this.oldAddress.getString();
	}

	public void setOldAddressLength( final Integer length ) throws FieldException  {
		if( this.oldAddress != null ) { this.oldAddress.setStringLength( length ); }
	}

	public void setOldAddressStrategyFixed( final String value ) throws FieldException  {
		if( this.oldAddress != null ) { this.oldAddress.setStringStrategyFixed( value ); }
	}

	public void setOldAddressStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.oldAddress != null ) { this.oldAddress.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setOldAddressStrategyRandom( final Integer length ) throws FieldException  {
		if( this.oldAddress != null ) { this.oldAddress.setStringStrategyRandom( length ); }
	}

	public void cleanOldAddress()  {
		if( this.oldAddress != null ) { this.oldAddress.cleanString(); }
	}

	public void cleanOldAddressLength()  {
		if( this.oldAddress != null ) { this.oldAddress.cleanStringLength(); }
	}

	public void cleanOldAddressStrategyIncrement()  {
		if( this.oldAddress != null ) { this.oldAddress.cleanStringStrategyIncrement(); }
	}

	public void cleanOldAddressStrategyRandom()  {
		if( this.oldAddress != null ) { this.oldAddress.cleanStringStrategyRandom(); }
	}

	@NewSponsor( position = 28 )
	public String getNewSponsor() throws FieldException  {
		return this.newSponsor.getString();
	}

	public void setNewSponsorLength( final Integer length ) throws FieldException  {
		if( this.newSponsor != null ) { this.newSponsor.setStringLength( length ); }
	}

	public void setNewSponsorStrategyFixed( final String value ) throws FieldException  {
		if( this.newSponsor != null ) { this.newSponsor.setStringStrategyFixed( value ); }
	}

	public void setNewSponsorStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.newSponsor != null ) { this.newSponsor.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setNewSponsorStrategyRandom( final Integer length ) throws FieldException  {
		if( this.newSponsor != null ) { this.newSponsor.setStringStrategyRandom( length ); }
	}

	public void cleanNewSponsor()  {
		if( this.newSponsor != null ) { this.newSponsor.cleanString(); }
	}

	public void cleanNewSponsorLength()  {
		if( this.newSponsor != null ) { this.newSponsor.cleanStringLength(); }
	}

	public void cleanNewSponsorStrategyIncrement()  {
		if( this.newSponsor != null ) { this.newSponsor.cleanStringStrategyIncrement(); }
	}

	public void cleanNewSponsorStrategyRandom()  {
		if( this.newSponsor != null ) { this.newSponsor.cleanStringStrategyRandom(); }
	}

	@OldSponsor( position = 29 )
	public String getOldSponsor() throws FieldException  {
		return this.oldSponsor.getString();
	}

	public void setOldSponsorLength( final Integer length ) throws FieldException  {
		if( this.oldSponsor != null ) { this.oldSponsor.setStringLength( length ); }
	}

	public void setOldSponsorStrategyFixed( final String value ) throws FieldException  {
		if( this.oldSponsor != null ) { this.oldSponsor.setStringStrategyFixed( value ); }
	}

	public void setOldSponsorStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.oldSponsor != null ) { this.oldSponsor.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setOldSponsorStrategyRandom( final Integer length ) throws FieldException  {
		if( this.oldSponsor != null ) { this.oldSponsor.setStringStrategyRandom( length ); }
	}

	public void cleanOldSponsor()  {
		if( this.oldSponsor != null ) { this.oldSponsor.cleanString(); }
	}

	public void cleanOldSponsorLength()  {
		if( this.oldSponsor != null ) { this.oldSponsor.cleanStringLength(); }
	}

	public void cleanOldSponsorStrategyIncrement()  {
		if( this.oldSponsor != null ) { this.oldSponsor.cleanStringStrategyIncrement(); }
	}

	public void cleanOldSponsorStrategyRandom()  {
		if( this.oldSponsor != null ) { this.oldSponsor.cleanStringStrategyRandom(); }
	}

}