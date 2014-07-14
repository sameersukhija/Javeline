package com.lumata.e4o.system.fields;

import com.lumata.e4o.exceptions.FieldException;
import com.lumata.e4o.system.field.types.FieldMethod;
import com.lumata.e4o.system.field.types.FieldTypeBoolean;

@FieldTypeBoolean
public class FieldBoolean {

	private Boolean boolean_current_value;
	private Integer boolean_increment;
	private Boolean boolean_random;
	private Boolean is_boolean_incremented;
	
	public FieldBoolean() {
		
		boolean_current_value = null;
		boolean_increment = null;
		boolean_random = false;
		is_boolean_incremented = false;
		
	}

	@FieldMethod
	public String getBoolean() throws FieldException {
		
		if( this.boolean_current_value == null && !this.boolean_random ) { return ""; } 
		else {
			
			if( !this.is_boolean_incremented && this.boolean_increment != null ) {
				
				this.is_boolean_incremented = true;
				
			} else {
				
				if( this.is_boolean_incremented && this.boolean_increment != null ) {
					
					this.setNextBoolean();
					
				} else {
					
					if( this.boolean_random ) {
						
						this.boolean_current_value = this.generateRandomBoolean();
						
					}
					
				}
				
			}		
						
		}
		
		return String.valueOf( this.boolean_current_value ); 
		
	}

	@FieldMethod
	public void setBooleanStrategyFixed( final Boolean value ) throws FieldException {	
					
		this.cleanBooleanStrategyIncrement();
		
		this.cleanBooleanStrategyRandom();
		
		this.boolean_current_value = value;
			
	}
	
	@FieldMethod
	public void setBooleanStrategyIncrement( final Boolean start_value, final Integer increment ) throws FieldException {	
		
		this.cleanBooleanStrategyRandom();
		
		this.boolean_current_value = start_value;
		
		this.boolean_increment = Math.abs( increment );
						
	}
	
	@FieldMethod
	public void setBooleanStrategyRandom() throws FieldException {
		
		this.cleanBooleanStrategyIncrement();
		
		this.boolean_random = true;
				
	}
	
	@FieldMethod
	public void cleanBooleanStrategyFixed() {
		
		this.boolean_current_value = null;
					
	}
	
	@FieldMethod
	public void cleanBooleanStrategyIncrement() {
		
		this.cleanBooleanStrategyFixed();
				
		this.boolean_increment = null;
		
	}
	
	@FieldMethod
	public void cleanBooleanStrategyRandom() {
		
		this.cleanBooleanStrategyFixed();
		
		this.boolean_random = false;
		
	}
	
	private Boolean generateRandomBoolean() {
		
		return Math.random() < 0.5;
				
	}
	
	private void setNextBoolean() {
		
		Integer curr_boolean = ( this.boolean_current_value ? 0 : 1 );
		
		curr_boolean = ( curr_boolean + this.boolean_increment ) % 2;
		
		this.boolean_current_value = ( curr_boolean == 0 ? true : false );
		
	}
	
}
