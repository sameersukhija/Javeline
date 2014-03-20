package com.lumata.e4o.system.cdr.types;

import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.annotations.*;
import com.lumata.expression.operators.exceptions.CDRException;
import java.util.Calendar;
import com.lumata.e4o.system.csv.types.CSVDateIncrement;
import org.json.*;

public class CDRLifeCycle extends CDR { 

	private final int FIELDS = 3;

	public CDRLifeCycle() {
		super();
	} 

	public int getFieldsCount() {
		return this.FIELDS;
	}

	public void cleanMsisdnStrategyIncrement()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnStrategyIncrement() ; }
	}

	public void cleanMsisdnStrategyRandom()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnStrategyRandom() ; }
	}

	public void setMsisdnStrategyIncrement( final Long value, final Integer increment ) throws CDRException  {
		if( this.msisdn != null ) { this.msisdn.setMsisdnStrategyIncrement( value, increment ); }
	}

	public void setMsisdnStrategyRandom( final Long min_value, final Long max_value ) throws CDRException  {
		if( this.msisdn != null ) { this.msisdn.setMsisdnStrategyRandom( min_value, max_value ); }
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

	public void setMsisdnOptions( final Integer prefix, final Integer length ) throws CDRException  {
		if( this.msisdn != null ) { this.msisdn.setMsisdnOptions( prefix, length ); }
	}

	public void setMsisdnStrategyFixed( final Long value ) throws CDRException  {
		if( this.msisdn != null ) { this.msisdn.setMsisdnStrategyFixed( value ); }
	}

	public void setDateStrategyFixed( final Calendar date ) throws CDRException  {
		if( this.date != null ) { this.date.setDateStrategyFixed( date ); }
	}

	public void cleanDateStrategyIncrement()  {
		if( this.date != null ) { this.date.cleanDateStrategyIncrement() ; }
	}

	public void cleanDateStrategyRandom()  {
		if( this.date != null ) { this.date.cleanDateStrategyRandom() ; }
	}

	public void setDateFormat( String format ) throws CDRException  {
		if( this.date != null ) { this.date.setDateFormat( format ); }
	}

	public void setDateStrategyIncrement( final Calendar date, final CSVDateIncrement increment ) throws CDRException  {
		if( this.date != null ) { this.date.setDateStrategyIncrement( date, increment ); }
	}

	public void setDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws CDRException  {
		if( this.date != null ) { this.date.setDateStrategyRandom( date_left, date_right ); }
	}

	public void cleanDateStrategyFixed()  {
		if( this.date != null ) { this.date.cleanDateStrategyFixed() ; }
	}

	@Date( position = 1 )
	public String getDate()  {
		return this.date.getDate() ;
	}

	@NewRatePlan( position = 2 )
	public String getNewRatePlan() throws CDRException  {
		return this.new_rate_plan.getSchemaTable();
	}

	public void setNewRatePlanOptions( final JSONObject dataSource, final Object entity, final Enum<?> field ) throws CDRException  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.setSchemaTableOptions( dataSource, entity, field ); }
	}

	public void setNewRatePlanStrategyFixed( final int row ) throws CDRException  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.setSchemaTableStrategyFixed( row ); }
	}

	public void setNewRatePlanStrategyFixed( final String field_value ) throws CDRException  {
		if( this.new_rate_plan != null ) { this.new_rate_plan.setSchemaTableStrategyFixed( field_value ); }
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

}