package com.lumata.e4o.system.cdr.types;

import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.annotations.*;
import com.lumata.expression.operators.exceptions.CDRException;
import java.util.Calendar;
import com.lumata.e4o.system.csv.types.CSVDateIncrement;
import org.json.*;

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

	public void cleanMsisdnFixedStrategy()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnFixedStrategy() ; }
	}

	public void cleanMsisdnOptions()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnOptions() ; }
	}

	@Msisdn( position = 0 )
	public String getMsisdn() throws CDRException  {
		return this.msisdn.getMsisdn();
	}

	public void cleanMsisdnStrategyIncrement()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnStrategyIncrement() ; }
	}

	public void cleanMsisdnStrategyRandom()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnStrategyRandom() ; }
	}

	@Date( position = 1 )
	public String getDate()  {
		return this.date.getDate() ;
	}

	public void setDateStrategyFixed( final Calendar date ) throws CDRException  {
		if( this.date != null ) { this.date.setDateStrategyFixed( date ); }
	}

	public void setDateStrategyIncrement( final Calendar date, final CSVDateIncrement increment ) throws CDRException  {
		if( this.date != null ) { this.date.setDateStrategyIncrement( date, increment ); }
	}

	public void setDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws CDRException  {
		if( this.date != null ) { this.date.setDateStrategyRandom( date_left, date_right ); }
	}

	public void setDateFormat( String format ) throws CDRException  {
		if( this.date != null ) { this.date.setDateFormat( format ); }
	}

	public void cleanDateStrategyIncrement()  {
		if( this.date != null ) { this.date.cleanDateStrategyIncrement() ; }
	}

	public void cleanDateStrategyRandom()  {
		if( this.date != null ) { this.date.cleanDateStrategyRandom() ; }
	}

	public void cleanDateStrategyFixed()  {
		if( this.date != null ) { this.date.cleanDateStrategyFixed() ; }
	}

	@NewRatePlan( position = 2 )
	public String getNewRatePlan() throws CDRException  {
		return this.new_rate_plan.getSchemaTable();
	}

	public void setNewRatePlanValues( final JSONObject dataSource ) throws CDRException  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.setSchemaTableValues( dataSource ); }
	}

	public void setNewRatePlanStrategyFixed( final String field_value ) throws CDRException  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.setSchemaTableStrategyFixed( field_value ); }
	}

	public void setNewRatePlanStrategyFixed( final int row ) throws CDRException  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.setSchemaTableStrategyFixed( row ); }
	}

	public void cleanNewRatePlanStrategyIncrement()  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.cleanSchemaTableStrategyIncrement() ; }
	}

	public void cleanNewRatePlanStrategyRandom()  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.cleanSchemaTableStrategyRandom() ; }
	}

	public void setNewRatePlanStrategyIncrement( final Integer start_row, final Integer increment ) throws CDRException  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.setSchemaTableStrategyIncrement( start_row, increment ); }
	}

	public void setNewRatePlanStrategyRandom() throws CDRException  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.setSchemaTableStrategyRandom(); }
	}

	public void cleanNewRatePlanOptions()  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.cleanSchemaTableOptions() ; }
	}

	public void cleanNewRatePlanStrategyFixed()  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.cleanSchemaTableStrategyFixed() ; }
	}

	@OldRatePlan( position = 3 )
	public String getOldRatePlan() throws CDRException  {
		return this.old_rate_plan.getSchemaTable();
	}

	public void setOldRatePlanValues( final JSONObject dataSource ) throws CDRException  {
		if( this.old_rate_plan != null ) { this.old_rate_plan.setSchemaTableValues( dataSource ); }
	}

	public void setOldRatePlanStrategyFixed( final String field_value ) throws CDRException  {
		if( this.old_rate_plan != null ) { this.old_rate_plan.setSchemaTableStrategyFixed( field_value ); }
	}

	public void setOldRatePlanStrategyFixed( final int row ) throws CDRException  {
		if( this.old_rate_plan != null ) { this.old_rate_plan.setSchemaTableStrategyFixed( row ); }
	}

	public void cleanOldRatePlanStrategyIncrement()  {
		if( this.old_rate_plan != null ) { this.old_rate_plan.cleanSchemaTableStrategyIncrement() ; }
	}

	public void cleanOldRatePlanStrategyRandom()  {
		if( this.old_rate_plan != null ) { this.old_rate_plan.cleanSchemaTableStrategyRandom() ; }
	}

	public void setOldRatePlanStrategyIncrement( final Integer start_row, final Integer increment ) throws CDRException  {
		if( this.old_rate_plan != null ) { this.old_rate_plan.setSchemaTableStrategyIncrement( start_row, increment ); }
	}

	public void setOldRatePlanStrategyRandom() throws CDRException  {
		if( this.old_rate_plan != null ) { this.old_rate_plan.setSchemaTableStrategyRandom(); }
	}

	public void cleanOldRatePlanOptions()  {
		if( this.old_rate_plan != null ) { this.old_rate_plan.cleanSchemaTableOptions() ; }
	}

	public void cleanOldRatePlanStrategyFixed()  {
		if( this.old_rate_plan != null ) { this.old_rate_plan.cleanSchemaTableStrategyFixed() ; }
	}

	@NewProfile( position = 4 )
	public String getNewProfile() throws CDRException  {
		return this.new_profile.getSchemaTable();
	}

	public void setNewProfileValues( final JSONObject dataSource ) throws CDRException  {
		if( this.new_profile != null ) { this.new_profile.setSchemaTableValues( dataSource ); }
	}

	public void setNewProfileStrategyFixed( final String field_value ) throws CDRException  {
		if( this.new_profile != null ) { this.new_profile.setSchemaTableStrategyFixed( field_value ); }
	}

	public void setNewProfileStrategyFixed( final int row ) throws CDRException  {
		if( this.new_profile != null ) { this.new_profile.setSchemaTableStrategyFixed( row ); }
	}

	public void cleanNewProfileStrategyIncrement()  {
		if( this.new_profile != null ) { this.new_profile.cleanSchemaTableStrategyIncrement() ; }
	}

	public void cleanNewProfileStrategyRandom()  {
		if( this.new_profile != null ) { this.new_profile.cleanSchemaTableStrategyRandom() ; }
	}

	public void setNewProfileStrategyIncrement( final Integer start_row, final Integer increment ) throws CDRException  {
		if( this.new_profile != null ) { this.new_profile.setSchemaTableStrategyIncrement( start_row, increment ); }
	}

	public void setNewProfileStrategyRandom() throws CDRException  {
		if( this.new_profile != null ) { this.new_profile.setSchemaTableStrategyRandom(); }
	}

	public void cleanNewProfileOptions()  {
		if( this.new_profile != null ) { this.new_profile.cleanSchemaTableOptions() ; }
	}

	public void cleanNewProfileStrategyFixed()  {
		if( this.new_profile != null ) { this.new_profile.cleanSchemaTableStrategyFixed() ; }
	}

	@OldProfile( position = 5 )
	public String getOldProfile() throws CDRException  {
		return this.old_profile.getSchemaTable();
	}

	public void setOldProfileValues( final JSONObject dataSource ) throws CDRException  {
		if( this.old_profile != null ) { this.old_profile.setSchemaTableValues( dataSource ); }
	}

	public void setOldProfileStrategyFixed( final String field_value ) throws CDRException  {
		if( this.old_profile != null ) { this.old_profile.setSchemaTableStrategyFixed( field_value ); }
	}

	public void setOldProfileStrategyFixed( final int row ) throws CDRException  {
		if( this.old_profile != null ) { this.old_profile.setSchemaTableStrategyFixed( row ); }
	}

	public void cleanOldProfileStrategyIncrement()  {
		if( this.old_profile != null ) { this.old_profile.cleanSchemaTableStrategyIncrement() ; }
	}

	public void cleanOldProfileStrategyRandom()  {
		if( this.old_profile != null ) { this.old_profile.cleanSchemaTableStrategyRandom() ; }
	}

	public void setOldProfileStrategyIncrement( final Integer start_row, final Integer increment ) throws CDRException  {
		if( this.old_profile != null ) { this.old_profile.setSchemaTableStrategyIncrement( start_row, increment ); }
	}

	public void setOldProfileStrategyRandom() throws CDRException  {
		if( this.old_profile != null ) { this.old_profile.setSchemaTableStrategyRandom(); }
	}

	public void cleanOldProfileOptions()  {
		if( this.old_profile != null ) { this.old_profile.cleanSchemaTableOptions() ; }
	}

	public void cleanOldProfileStrategyFixed()  {
		if( this.old_profile != null ) { this.old_profile.cleanSchemaTableStrategyFixed() ; }
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
		if( this.new_subprofile != null ) { this.new_subprofile.cleanStringLength() ; }
	}

	public void setNewSubProfileStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.new_subprofile != null ) { this.new_subprofile.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setNewSubProfileStrategyRandom( int length ) throws CDRException  {
		if( this.new_subprofile != null ) { this.new_subprofile.setStringStrategyRandom( length ); }
	}

	public void cleanNewSubProfileStrategyIncrement()  {
		if( this.new_subprofile != null ) { this.new_subprofile.cleanStringStrategyIncrement() ; }
	}

	public void cleanNewSubProfileStrategyRandom()  {
		if( this.new_subprofile != null ) { this.new_subprofile.cleanStringStrategyRandom() ; }
	}

	public void cleanNewSubProfile()  {
		if( this.new_subprofile != null ) { this.new_subprofile.cleanString() ; }
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
		if( this.old_subprofile != null ) { this.old_subprofile.cleanStringLength() ; }
	}

	public void setOldSubProfileStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.old_subprofile != null ) { this.old_subprofile.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setOldSubProfileStrategyRandom( int length ) throws CDRException  {
		if( this.old_subprofile != null ) { this.old_subprofile.setStringStrategyRandom( length ); }
	}

	public void cleanOldSubProfileStrategyIncrement()  {
		if( this.old_subprofile != null ) { this.old_subprofile.cleanStringStrategyIncrement() ; }
	}

	public void cleanOldSubProfileStrategyRandom()  {
		if( this.old_subprofile != null ) { this.old_subprofile.cleanStringStrategyRandom() ; }
	}

	public void cleanOldSubProfile()  {
		if( this.old_subprofile != null ) { this.old_subprofile.cleanString() ; }
	}

	@NewStatus( position = 8 )
	public String getNewStatus() throws CDRException  {
		return this.new_status.getSchemaTable();
	}

	public void setNewStatusValues( final JSONObject dataSource ) throws CDRException  {
		if( this.new_status != null ) { this.new_status.setSchemaTableValues( dataSource ); }
	}

	public void setNewStatusStrategyFixed( final String field_value ) throws CDRException  {
		if( this.new_status != null ) { this.new_status.setSchemaTableStrategyFixed( field_value ); }
	}

	public void setNewStatusStrategyFixed( final int row ) throws CDRException  {
		if( this.new_status != null ) { this.new_status.setSchemaTableStrategyFixed( row ); }
	}

	public void cleanNewStatusStrategyIncrement()  {
		if( this.new_status != null ) { this.new_status.cleanSchemaTableStrategyIncrement() ; }
	}

	public void cleanNewStatusStrategyRandom()  {
		if( this.new_status != null ) { this.new_status.cleanSchemaTableStrategyRandom() ; }
	}

	public void setNewStatusStrategyIncrement( final Integer start_row, final Integer increment ) throws CDRException  {
		if( this.new_status != null ) { this.new_status.setSchemaTableStrategyIncrement( start_row, increment ); }
	}

	public void setNewStatusStrategyRandom() throws CDRException  {
		if( this.new_status != null ) { this.new_status.setSchemaTableStrategyRandom(); }
	}

	public void cleanNewStatusOptions()  {
		if( this.new_status != null ) { this.new_status.cleanSchemaTableOptions() ; }
	}

	public void cleanNewStatusStrategyFixed()  {
		if( this.new_status != null ) { this.new_status.cleanSchemaTableStrategyFixed() ; }
	}

	@OldStatus( position = 9 )
	public String getOldStatus() throws CDRException  {
		return this.old_status.getSchemaTable();
	}

	public void setOldStatusValues( final JSONObject dataSource ) throws CDRException  {
		if( this.old_status != null ) { this.old_status.setSchemaTableValues( dataSource ); }
	}

	public void setOldStatusStrategyFixed( final String field_value ) throws CDRException  {
		if( this.old_status != null ) { this.old_status.setSchemaTableStrategyFixed( field_value ); }
	}

	public void setOldStatusStrategyFixed( final int row ) throws CDRException  {
		if( this.old_status != null ) { this.old_status.setSchemaTableStrategyFixed( row ); }
	}

	public void cleanOldStatusStrategyIncrement()  {
		if( this.old_status != null ) { this.old_status.cleanSchemaTableStrategyIncrement() ; }
	}

	public void cleanOldStatusStrategyRandom()  {
		if( this.old_status != null ) { this.old_status.cleanSchemaTableStrategyRandom() ; }
	}

	public void setOldStatusStrategyIncrement( final Integer start_row, final Integer increment ) throws CDRException  {
		if( this.old_status != null ) { this.old_status.setSchemaTableStrategyIncrement( start_row, increment ); }
	}

	public void setOldStatusStrategyRandom() throws CDRException  {
		if( this.old_status != null ) { this.old_status.setSchemaTableStrategyRandom(); }
	}

	public void cleanOldStatusOptions()  {
		if( this.old_status != null ) { this.old_status.cleanSchemaTableOptions() ; }
	}

	public void cleanOldStatusStrategyFixed()  {
		if( this.old_status != null ) { this.old_status.cleanSchemaTableStrategyFixed() ; }
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
		if( this.new_network != null ) { this.new_network.cleanStringLength() ; }
	}

	public void setNewNetworkStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.new_network != null ) { this.new_network.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setNewNetworkStrategyRandom( int length ) throws CDRException  {
		if( this.new_network != null ) { this.new_network.setStringStrategyRandom( length ); }
	}

	public void cleanNewNetworkStrategyIncrement()  {
		if( this.new_network != null ) { this.new_network.cleanStringStrategyIncrement() ; }
	}

	public void cleanNewNetworkStrategyRandom()  {
		if( this.new_network != null ) { this.new_network.cleanStringStrategyRandom() ; }
	}

	public void cleanNewNetwork()  {
		if( this.new_network != null ) { this.new_network.cleanString() ; }
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
		if( this.old_network != null ) { this.old_network.cleanStringLength() ; }
	}

	public void setOldNetworkStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws CDRException  {
		if( this.old_network != null ) { this.old_network.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setOldNetworkStrategyRandom( int length ) throws CDRException  {
		if( this.old_network != null ) { this.old_network.setStringStrategyRandom( length ); }
	}

	public void cleanOldNetworkStrategyIncrement()  {
		if( this.old_network != null ) { this.old_network.cleanStringStrategyIncrement() ; }
	}

	public void cleanOldNetworkStrategyRandom()  {
		if( this.old_network != null ) { this.old_network.cleanStringStrategyRandom() ; }
	}

	public void cleanOldNetwork()  {
		if( this.old_network != null ) { this.old_network.cleanString() ; }
	}

	@NewSubscriptionDate( position = 12 )
	public String getNewSubscriptionDate()  {
		return this.new_subscription_date.getDate() ;
	}

	public void setNewSubscriptionDateStrategyFixed( final Calendar date ) throws CDRException  {
		if( this.new_subscription_date != null ) { this.new_subscription_date.setDateStrategyFixed( date ); }
	}

	public void setNewSubscriptionDateStrategyIncrement( final Calendar date, final CSVDateIncrement increment ) throws CDRException  {
		if( this.new_subscription_date != null ) { this.new_subscription_date.setDateStrategyIncrement( date, increment ); }
	}

	public void setNewSubscriptionDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws CDRException  {
		if( this.new_subscription_date != null ) { this.new_subscription_date.setDateStrategyRandom( date_left, date_right ); }
	}

	public void setNewSubscriptionDateFormat( String format ) throws CDRException  {
		if( this.new_subscription_date != null ) { this.new_subscription_date.setDateFormat( format ); }
	}

	public void cleanNewSubscriptionDateStrategyIncrement()  {
		if( this.new_subscription_date != null ) { this.new_subscription_date.cleanDateStrategyIncrement() ; }
	}

	public void cleanNewSubscriptionDateStrategyRandom()  {
		if( this.new_subscription_date != null ) { this.new_subscription_date.cleanDateStrategyRandom() ; }
	}

	public void cleanNewSubscriptionDateStrategyFixed()  {
		if( this.new_subscription_date != null ) { this.new_subscription_date.cleanDateStrategyFixed() ; }
	}

	@OldSubscriptionDate( position = 13 )
	public String getOldSubscriptionDate()  {
		return this.old_subscription_date.getDate() ;
	}

	public void setOldSubscriptionDateStrategyFixed( final Calendar date ) throws CDRException  {
		if( this.old_subscription_date != null ) { this.old_subscription_date.setDateStrategyFixed( date ); }
	}

	public void setOldSubscriptionDateStrategyIncrement( final Calendar date, final CSVDateIncrement increment ) throws CDRException  {
		if( this.old_subscription_date != null ) { this.old_subscription_date.setDateStrategyIncrement( date, increment ); }
	}

	public void setOldSubscriptionDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws CDRException  {
		if( this.old_subscription_date != null ) { this.old_subscription_date.setDateStrategyRandom( date_left, date_right ); }
	}

	public void setOldSubscriptionDateFormat( String format ) throws CDRException  {
		if( this.old_subscription_date != null ) { this.old_subscription_date.setDateFormat( format ); }
	}

	public void cleanOldSubscriptionDateStrategyIncrement()  {
		if( this.old_subscription_date != null ) { this.old_subscription_date.cleanDateStrategyIncrement() ; }
	}

	public void cleanOldSubscriptionDateStrategyRandom()  {
		if( this.old_subscription_date != null ) { this.old_subscription_date.cleanDateStrategyRandom() ; }
	}

	public void cleanOldSubscriptionDateStrategyFixed()  {
		if( this.old_subscription_date != null ) { this.old_subscription_date.cleanDateStrategyFixed() ; }
	}

}