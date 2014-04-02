package com.lumata.e4o.system.cdr.types;

import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.annotations.*;
import com.lumata.expression.operators.exceptions.CDRException;
import java.util.Calendar;
import com.lumata.e4o.system.csv.types.CSVDateIncrement;

public class CDRLifeCycle extends CDR { 

	private final int FIELDS = 14;

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

	public void setDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws CDRException  {
		if( this.date != null ) { this.date.setDateStrategyRandom( date_left, date_right ); }
	}

	public void cleanDateStrategyFixed()  {
		if( this.date != null ) { this.date.cleanDateStrategyFixed(); }
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

	public void setDateStrategyIncrement( final Calendar date, final CSVDateIncrement increment ) throws CDRException  {
		if( this.date != null ) { this.date.setDateStrategyIncrement( date, increment ); }
	}

	@Date( position = 1 )
	public String getDate()  {
		return this.date.getDate();
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

	@NewRatePlan( position = 2 )
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

	@OldRatePlan( position = 3 )
	public String getOldRatePlan() throws CDRException  {
		return this.old_rate_plan.getString();
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

	@NewProfile( position = 4 )
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

	@OldProfile( position = 5 )
	public String getOldProfile() throws CDRException  {
		return this.old_profile.getString();
	}

	public void setNewSubProfileLength( final Integer length ) throws CDRException  {
		if( this.new_subprofile != null ) { this.new_subprofile.setStringLength( length ); }
	}

	public void setNewSubProfileStrategyFixed( final String value ) throws CDRException  {
		if( this.new_subprofile != null ) { this.new_subprofile.setStringStrategyFixed( value ); }
	}

	public void cleanNewSubProfileStrategyIncrement()  {
		if( this.new_subprofile != null ) { this.new_subprofile.cleanStringStrategyIncrement(); }
	}

	public void cleanNewSubProfileStrategyRandom()  {
		if( this.new_subprofile != null ) { this.new_subprofile.cleanStringStrategyRandom(); }
	}

	public void setNewSubProfileStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.new_subprofile != null ) { this.new_subprofile.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setNewSubProfileStrategyRandom( final Integer length ) throws CDRException  {
		if( this.new_subprofile != null ) { this.new_subprofile.setStringStrategyRandom( length ); }
	}

	public void cleanNewSubProfile()  {
		if( this.new_subprofile != null ) { this.new_subprofile.cleanString(); }
	}

	public void cleanNewSubProfileLength()  {
		if( this.new_subprofile != null ) { this.new_subprofile.cleanStringLength(); }
	}

	@NewSubProfile( position = 6 )
	public String getNewSubProfile() throws CDRException  {
		return this.new_subprofile.getString();
	}

	public void setOldSubProfileLength( final Integer length ) throws CDRException  {
		if( this.old_subprofile != null ) { this.old_subprofile.setStringLength( length ); }
	}

	public void setOldSubProfileStrategyFixed( final String value ) throws CDRException  {
		if( this.old_subprofile != null ) { this.old_subprofile.setStringStrategyFixed( value ); }
	}

	public void cleanOldSubProfileStrategyIncrement()  {
		if( this.old_subprofile != null ) { this.old_subprofile.cleanStringStrategyIncrement(); }
	}

	public void cleanOldSubProfileStrategyRandom()  {
		if( this.old_subprofile != null ) { this.old_subprofile.cleanStringStrategyRandom(); }
	}

	public void setOldSubProfileStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.old_subprofile != null ) { this.old_subprofile.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setOldSubProfileStrategyRandom( final Integer length ) throws CDRException  {
		if( this.old_subprofile != null ) { this.old_subprofile.setStringStrategyRandom( length ); }
	}

	public void cleanOldSubProfile()  {
		if( this.old_subprofile != null ) { this.old_subprofile.cleanString(); }
	}

	public void cleanOldSubProfileLength()  {
		if( this.old_subprofile != null ) { this.old_subprofile.cleanStringLength(); }
	}

	@OldSubProfile( position = 7 )
	public String getOldSubProfile() throws CDRException  {
		return this.old_subprofile.getString();
	}

	public void setNewStatusLength( final Integer length ) throws CDRException  {
		if( this.new_status != null ) { this.new_status.setStringLength( length ); }
	}

	public void setNewStatusStrategyFixed( final String value ) throws CDRException  {
		if( this.new_status != null ) { this.new_status.setStringStrategyFixed( value ); }
	}

	public void cleanNewStatusStrategyIncrement()  {
		if( this.new_status != null ) { this.new_status.cleanStringStrategyIncrement(); }
	}

	public void cleanNewStatusStrategyRandom()  {
		if( this.new_status != null ) { this.new_status.cleanStringStrategyRandom(); }
	}

	public void setNewStatusStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.new_status != null ) { this.new_status.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setNewStatusStrategyRandom( final Integer length ) throws CDRException  {
		if( this.new_status != null ) { this.new_status.setStringStrategyRandom( length ); }
	}

	public void cleanNewStatus()  {
		if( this.new_status != null ) { this.new_status.cleanString(); }
	}

	public void cleanNewStatusLength()  {
		if( this.new_status != null ) { this.new_status.cleanStringLength(); }
	}

	@NewStatus( position = 8 )
	public String getNewStatus() throws CDRException  {
		return this.new_status.getString();
	}

	public void setOldStatusLength( final Integer length ) throws CDRException  {
		if( this.old_status != null ) { this.old_status.setStringLength( length ); }
	}

	public void setOldStatusStrategyFixed( final String value ) throws CDRException  {
		if( this.old_status != null ) { this.old_status.setStringStrategyFixed( value ); }
	}

	public void cleanOldStatusStrategyIncrement()  {
		if( this.old_status != null ) { this.old_status.cleanStringStrategyIncrement(); }
	}

	public void cleanOldStatusStrategyRandom()  {
		if( this.old_status != null ) { this.old_status.cleanStringStrategyRandom(); }
	}

	public void setOldStatusStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.old_status != null ) { this.old_status.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setOldStatusStrategyRandom( final Integer length ) throws CDRException  {
		if( this.old_status != null ) { this.old_status.setStringStrategyRandom( length ); }
	}

	public void cleanOldStatus()  {
		if( this.old_status != null ) { this.old_status.cleanString(); }
	}

	public void cleanOldStatusLength()  {
		if( this.old_status != null ) { this.old_status.cleanStringLength(); }
	}

	@OldStatus( position = 9 )
	public String getOldStatus() throws CDRException  {
		return this.old_status.getString();
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

	@NewNetwork( position = 10 )
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

	@OldNetwork( position = 11 )
	public String getOldNetwork() throws CDRException  {
		return this.old_network.getString();
	}

	public void setNewSubscriptionDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws CDRException  {
		if( this.new_subscription_date != null ) { this.new_subscription_date.setDateStrategyRandom( date_left, date_right ); }
	}

	public void cleanNewSubscriptionDateStrategyFixed()  {
		if( this.new_subscription_date != null ) { this.new_subscription_date.cleanDateStrategyFixed(); }
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

	public void setNewSubscriptionDateStrategyIncrement( final Calendar date, final CSVDateIncrement increment ) throws CDRException  {
		if( this.new_subscription_date != null ) { this.new_subscription_date.setDateStrategyIncrement( date, increment ); }
	}

	@NewSubscriptionDate( position = 12 )
	public String getNewSubscriptionDate()  {
		return this.new_subscription_date.getDate();
	}

	public void setOldSubscriptionDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws CDRException  {
		if( this.old_subscription_date != null ) { this.old_subscription_date.setDateStrategyRandom( date_left, date_right ); }
	}

	public void cleanOldSubscriptionDateStrategyFixed()  {
		if( this.old_subscription_date != null ) { this.old_subscription_date.cleanDateStrategyFixed(); }
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

	public void setOldSubscriptionDateStrategyIncrement( final Calendar date, final CSVDateIncrement increment ) throws CDRException  {
		if( this.old_subscription_date != null ) { this.old_subscription_date.setDateStrategyIncrement( date, increment ); }
	}

	@OldSubscriptionDate( position = 13 )
	public String getOldSubscriptionDate()  {
		return this.old_subscription_date.getDate();
	}

}