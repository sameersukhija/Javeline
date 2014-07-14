package com.lumata.e4o.system.fields;

import com.lumata.e4o.exceptions.FieldException;
import com.lumata.e4o.system.field.types.FieldMethod;
import com.lumata.e4o.system.field.types.FieldTypeLong;

@FieldTypeLong
public class FieldLong {

	private Long long_current_value;
	private Long long_left_value;
	private Long long_right_value;
	private Long long_next_value;
	private Integer long_increment;
	
	public FieldLong() {
			
		long_current_value = null;
		long_left_value = null;
		long_right_value = null;
		long_next_value = null;
		long_increment = null;
				
	}

	@FieldMethod
	public String getLong() throws FieldException {
		
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

	@FieldMethod
	public void setLongStrategyFixed( final Long value ) throws FieldException {	
		
		this.long_current_value = ( value != null ? Math.abs( value ) : null );
		
		this.cleanLongStrategyIncrement();
		
		this.cleanLongStrategyRandom();
		
	}
	
	@FieldMethod
	public void setLongStrategyIncrement( final Long value, final Integer increment ) throws FieldException {
		
		if( value == null ) { throw new FieldException( "The field cannot be null." ); }
		
		if( increment == null ) { throw new FieldException( "The field increment cannot be null." ); }
		
		this.long_current_value = Math.abs( value );
		
		this.long_increment = Math.abs( increment );
		
		this.cleanLongStrategyRandom();
		
	}
	
	@FieldMethod
	public void setLongStrategyRandom( final Long min_value, final Long max_value ) throws FieldException {
		
		if( min_value == null ) { throw new FieldException( "The min field value cannot be null." ); }
		
		if( max_value == null ) { throw new FieldException( "The max field value cannot be null." ); }
		
		this.long_left_value = Math.abs( min_value );
		
		this.long_right_value = Math.abs( max_value );
		
		this.cleanLongStrategyIncrement();
		
	}
	
	@FieldMethod
	public void cleanLongStrategyFixed() {
		
		this.long_current_value = null;
			
	}
		
	@FieldMethod
	public void cleanLongStrategyIncrement() {
		
		this.long_increment = null;
		
		this.long_next_value = null;
		
	}
	
	@FieldMethod
	public void cleanLongStrategyRandom() {
		
		this.long_left_value = null;
		
		this.long_right_value = null;
		
	}

	private Long generateRandomLong( final Long min_value, final Long max_value ) {
		
		return min_value + Long.valueOf( (int)( Math.random() * ( max_value - min_value ) ) );
				
	}
	
}
