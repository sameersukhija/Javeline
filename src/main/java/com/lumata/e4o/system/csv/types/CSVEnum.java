package com.lumata.e4o.system.csv.types;

import com.lumata.e4o.system.csv.annotations.CSVFieldEnum;
import com.lumata.e4o.system.csv.annotations.CSVMethod;
import com.lumata.expression.operators.exceptions.CDRException;

@CSVFieldEnum
public class CSVEnum {

	private Enum<? extends ICSVEnum>[] enumeration;
	private String enum_current_value;
	private Integer enum_curr_pos;
	private String enum_next_value;
	private Integer enum_next_pos;
	private Integer enum_increment;
	private Boolean enum_random;
		
	@SafeVarargs
	public CSVEnum( Enum<? extends ICSVEnum>... enumeration ) {
			
		this.enumeration = enumeration;
		
		enum_current_value = null;
		enum_random = false;
		enum_next_value = null;
		enum_next_pos = null;
		enum_increment = null;
						
	}

	@CSVMethod
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

	@CSVMethod
	public void setEnumStrategyFixed( final Enum<? extends ICSVEnum> value ) throws CDRException {	
		
		this.enum_current_value = ( value != null && enumeration != null ? ((ICSVEnum)value).value() : null );
		
		this.cleanEnumStrategyIncrement();
		
		this.cleanEnumStrategyRandom();
		
	}
	
	@CSVMethod
	public void setEnumStrategyIncrement( final Enum<? extends ICSVEnum> value, final Integer increment ) throws CDRException {
		
		if( value == null ) { throw new CDRException( "The field cannot be null." ); }
		
		if( increment == null ) { throw new CDRException( "The field increment cannot be null." ); }
		
		this.enum_current_value = ((ICSVEnum)value).value();
		
		this.enum_increment = Math.abs( increment );
		
		this.cleanEnumStrategyRandom();
		
	}
	
	@CSVMethod
	public void setEnumStrategyRandom() throws CDRException {
		
		this.enum_random = true;
		
		this.cleanEnumStrategyIncrement();
		
	}
	
	@CSVMethod
	public void cleanEnumStrategyFixed() {
		
		this.enum_current_value = null;
			
	}
		
	@CSVMethod
	public void cleanEnumStrategyIncrement() {
		
		this.enum_increment = null;
		
		this.enum_next_value = null;
		
	}
	
	@CSVMethod
	public void cleanEnumStrategyRandom() {
		
		this.enum_random = false;
		
	}

	private String generateRandomEnum() {
		
		int enum_pos = (int)( Math.random() * enumeration.length );
				
		return ((ICSVEnum)enumeration[enum_pos]).value();
								
	}
	
	private void setNextEnum() {
		
		this.enum_next_pos = ( ( this.enum_curr_pos != null ? this.enum_curr_pos : 0 ) + this.enum_increment ) % this.enumeration.length;
		
		this.enum_next_value = ((ICSVEnum)this.enumeration[ this.enum_next_pos ]).value();
		
	}
	
}
