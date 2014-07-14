package com.lumata.e4o.system.fields;

import com.lumata.e4o.exceptions.FieldException;
import com.lumata.e4o.system.field.types.FieldMethod;
import com.lumata.e4o.system.field.types.FieldTypeEnum;

@FieldTypeEnum
public class FieldEnum {

	private Enum<? extends IFieldEnum>[] enumeration;
	private String enum_current_value;
	private Integer enum_curr_pos;
	private String enum_next_value;
	private Integer enum_next_pos;
	private Integer enum_increment;
	private Boolean enum_random;
		
	@SafeVarargs
	public FieldEnum( Enum<? extends IFieldEnum>... enumeration ) {
			
		this.enumeration = enumeration;
		
		enum_current_value = null;
		enum_random = false;
		enum_next_value = null;
		enum_next_pos = null;
		enum_increment = null;
						
	}

	@FieldMethod
	public String getEnum() throws FieldException {
						
		if( this.enum_current_value == null && this.enum_random == false ) { return ""; } 
		else {
			
			if( this.enum_next_value != null && this.enum_increment != null ) {
				
				this.enum_current_value = this.enum_next_value;
				
				this.enum_curr_pos = this.enum_next_pos;
				
				this.setNextEnum();
				
			} else {
				
				if( this.enum_increment != null ) {
					
					this.setNextEnum();
					
				} else {
					
					if( this.enum_random ) {
						
						this.enum_current_value = this.generateRandomEnum();
						
					}
					
				}
				
			}			
			
		}
		
		return String.valueOf( this.enum_current_value ); 
		
	}

	@FieldMethod
	public void setEnumStrategyFixed( final Enum<? extends IFieldEnum> value ) throws FieldException {	
		
		this.enum_current_value = ( value != null && enumeration != null ? ((IFieldEnum)value).value() : null );
		
		this.cleanEnumStrategyIncrement();
		
		this.cleanEnumStrategyRandom();
		
	}
	
	@FieldMethod
	public void setEnumStrategyIncrement( final Enum<? extends IFieldEnum> value, final Integer increment ) throws FieldException {
		
		if( value == null ) { throw new FieldException( "The field cannot be null." ); }
		
		if( increment == null ) { throw new FieldException( "The field increment cannot be null." ); }
		
		this.enum_current_value = ((IFieldEnum)value).value();
		
		this.enum_increment = Math.abs( increment );
		
		this.cleanEnumStrategyRandom();
		
	}
	
	@FieldMethod
	public void setEnumStrategyRandom() throws FieldException {
		
		this.enum_random = true;
		
		this.cleanEnumStrategyIncrement();
		
	}
	
	@FieldMethod
	public void cleanEnumStrategyFixed() {
		
		this.enum_current_value = null;
			
	}
		
	@FieldMethod
	public void cleanEnumStrategyIncrement() {
		
		this.enum_increment = null;
		
		this.enum_next_value = null;
		
	}
	
	@FieldMethod
	public void cleanEnumStrategyRandom() {
		
		this.enum_random = false;
		
	}

	private String generateRandomEnum() {
		
		int enum_pos = (int)( Math.random() * enumeration.length );
				
		return ((IFieldEnum)enumeration[enum_pos]).value();
								
	}
	
	private void setNextEnum() {
		
		this.enum_next_pos = ( ( this.enum_curr_pos != null ? this.enum_curr_pos : 0 ) + this.enum_increment ) % this.enumeration.length;
		
		this.enum_next_value = ((IFieldEnum)this.enumeration[ this.enum_next_pos ]).value();
		
	}
	
}
