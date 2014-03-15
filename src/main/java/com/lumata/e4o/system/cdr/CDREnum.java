package com.lumata.e4o.system.cdr;

import com.lumata.expression.operators.exceptions.CDRException;

public class CDREnum {

	private Enum<? extends ICDREnum>[] enumeration;
	private String enum_current_value;
	private Integer enum_curr_pos;
	private String enum_next_value;
	private Integer enum_next_pos;
	private Integer enum_increment;
	private Boolean enum_random;
		
	@SafeVarargs
	public CDREnum( Enum<? extends ICDREnum>... enumeration ) {
			
		this.enumeration = enumeration;
		
		enum_current_value = null;
		enum_random = false;
		enum_next_value = null;
		enum_next_pos = null;
		enum_increment = null;
						
	}

	public String getEnum() throws CDRException {
						
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

	public void setEnumStrategyFixed( final Enum<? extends ICDREnum> value ) throws CDRException {	
		
		this.enum_current_value = ( value != null && enumeration != null ? ((ICDREnum)value).value() : null );
		
		this.cleanEnumStrategyIncrement();
		
		this.cleanEnumStrategyRandom();
		
	}
	
	public void setEnumStrategyIncrement( final Enum<? extends ICDREnum> value, final Integer increment ) throws CDRException {
		
		if( value == null ) { throw new CDRException( "The field cannot be null." ); }
		
		if( increment == null ) { throw new CDRException( "The field increment cannot be null." ); }
		
		this.enum_current_value = ((ICDREnum)value).value();
		
		this.enum_increment = Math.abs( increment );
		
		this.cleanEnumStrategyRandom();
		
	}
	
	public void setEnumStrategyRandom() throws CDRException {
		
		this.enum_random = true;
		
		this.cleanEnumStrategyIncrement();
		
	}
	
	public void cleanEnumStrategyFixed() {
		
		this.enum_current_value = null;
			
	}
		
	public void cleanEnumStrategyIncrement() {
		
		this.enum_increment = null;
		
		this.enum_next_value = null;
		
	}
	
	public void cleanEnumStrategyRandom() {
		
		this.enum_random = false;
		
	}

	private String generateRandomEnum() {
		
		int enum_pos = (int)( Math.random() * enumeration.length );
				
		return ((ICDREnum)enumeration[enum_pos]).value();
								
	}
	
	private int getEnumPosition() {
		
		return 0;
		
	}
	
	private void setNextEnum() {
		
		this.enum_next_pos = ( ( this.enum_curr_pos != null ? this.enum_curr_pos : 0 ) + this.enum_increment ) % this.enumeration.length;
		
		this.enum_next_value = ((ICDREnum)this.enumeration[ this.enum_next_pos ]).value();
		
	}
	
}
