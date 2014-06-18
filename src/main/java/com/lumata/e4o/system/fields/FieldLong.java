package com.lumata.e4o.system.csv.types;

import com.lumata.e4o.exceptions.CDRException;
import com.lumata.e4o.system.csv.annotations.CSVFieldLong;
import com.lumata.e4o.system.csv.annotations.CSVMethod;

@CSVFieldLong
public class CSVLong {

	private Long long_current_value;
	private Long long_left_value;
	private Long long_right_value;
	private Long long_next_value;
	private Integer long_increment;
	
	public CSVLong() {
			
		long_current_value = null;
		long_left_value = null;
		long_right_value = null;
		long_next_value = null;
		long_increment = null;
				
	}

	@CSVMethod
	public String getLong() throws CDRException {
		
		if( this.long_current_value == null && ( this.long_left_value == null || this.long_right_value == null )) { return ""; } 
		else {
			
			if( this.long_next_value != null && this.long_increment != null ) {
				
				this.long_current_value = this.long_next_value;
				
				this.long_next_value = Long.valueOf( this.long_next_value + this.long_increment );
				
			} else {
				
				if( this.long_increment != null ) {
					
					this.long_next_value = Long.valueOf( this.long_current_value + this.long_increment );
				
				} else {
					
					if( this.long_left_value != null && this.long_right_value != null ) {
						
						this.long_current_value = this.generateRandomLong( this.long_left_value, this.long_right_value );
						
					}
					
				}
				
			}			
			
		}
		
		return String.valueOf( this.long_current_value ); 
		
	}

	@CSVMethod
	public void setLongStrategyFixed( final Long value ) throws CDRException {	
		
		this.long_current_value = ( value != null ? Math.abs( value ) : null );
		
		this.cleanLongStrategyIncrement();
		
		this.cleanLongStrategyRandom();
		
	}
	
	@CSVMethod
	public void setLongStrategyIncrement( final Long value, final Integer increment ) throws CDRException {
		
		if( value == null ) { throw new CDRException( "The field cannot be null." ); }
		
		if( increment == null ) { throw new CDRException( "The field increment cannot be null." ); }
		
		this.long_current_value = Math.abs( value );
		
		this.long_increment = Math.abs( increment );
		
		this.cleanLongStrategyRandom();
		
	}
	
	@CSVMethod
	public void setLongStrategyRandom( final Long min_value, final Long max_value ) throws CDRException {
		
		if( min_value == null ) { throw new CDRException( "The min field value cannot be null." ); }
		
		if( max_value == null ) { throw new CDRException( "The max field value cannot be null." ); }
		
		this.long_left_value = Math.abs( min_value );
		
		this.long_right_value = Math.abs( max_value );
		
		this.cleanLongStrategyIncrement();
		
	}
	
	@CSVMethod
	public void cleanLongStrategyFixed() {
		
		this.long_current_value = null;
			
	}
		
	@CSVMethod
	public void cleanLongStrategyIncrement() {
		
		this.long_increment = null;
		
		this.long_next_value = null;
		
	}
	
	@CSVMethod
	public void cleanLongStrategyRandom() {
		
		this.long_left_value = null;
		
		this.long_right_value = null;
		
	}

	private Long generateRandomLong( final Long min_value, final Long max_value ) {
		
		return min_value + Long.valueOf( (int)( Math.random() * ( max_value - min_value ) ) );
				
	}
	
}
