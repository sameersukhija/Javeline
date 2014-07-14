package com.lumata.e4o.system.fields;

import org.apache.commons.lang3.RandomStringUtils;

import com.lumata.e4o.exceptions.FieldException;
import com.lumata.e4o.system.field.types.FieldMethod;
import com.lumata.e4o.system.field.types.FieldTypeString;

@FieldTypeString
public class FieldString {

	private String string_current_value;
	private Integer string_increment_current_value;
	private Integer string_increment;
	private Boolean string_random;
	private Integer string_length;
	
	private final Integer DEFAULT_STRING_LENGTH = 0;
	
	public FieldString() {
		
		string_current_value = null;
		string_increment_current_value = null;
		string_increment = null;
		string_random = false;
		string_length = DEFAULT_STRING_LENGTH;
		
	}

	@FieldMethod
	public String getString() throws FieldException {
		
		if( this.string_current_value == null && !this.string_random ) { return ""; } 
		else {
			
			if( this.string_increment_current_value != null && this.string_increment != null ) {
				
				this.string_increment_current_value = ( this.string_increment_current_value != null ? this.string_increment_current_value : 0 )  + ( this.string_increment != null ? this.string_increment : 0 );
						
			} else {
								
				if( this.string_random ) {
					
					this.string_current_value = this.generateRandomString();
					
				}
									
			}			
						
		}
		
		return this.generateString(); 
		
	}
	
	@FieldMethod
	public void setStringLength( final Integer length ) throws FieldException {
		
		this.string_length = ( length != null ? Math.abs( length ) : DEFAULT_STRING_LENGTH );
		
	}

	@FieldMethod
	public void setStringStrategyFixed( final String value ) throws FieldException {	
					
		this.cleanStringStrategyIncrement();
		
		this.cleanStringStrategyRandom();
		
		this.string_current_value = value;
		
		this.string_length = value.length();
			
	}
	
	@FieldMethod
	public void setStringStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException {	
		
		this.cleanStringStrategyRandom();
		
		this.string_current_value = value;
		
		this.string_length = value.length();
		
		this.string_increment_current_value = Math.abs( start_value );
		
		this.string_increment = Math.abs( increment );
				
	}
	
	@FieldMethod
	public void setStringStrategyRandom( final Integer length ) throws FieldException {
		
		this.cleanStringStrategyIncrement();
		
		this.string_length = Math.abs( length );
		
		this.string_random = true;
				
	}
	
	@FieldMethod
	public void cleanString() {
		
		this.string_current_value = null;
		
		this.cleanStringLength();
			
	}
	
	@FieldMethod
	public void cleanStringLength() {
		
		if( this.string_current_value != null ) { this.string_length = this.string_current_value.length(); }
		else { this.string_length = DEFAULT_STRING_LENGTH; }
				
	}
	
	@FieldMethod
	public void cleanStringStrategyIncrement() {
		
		this.string_current_value = null;
		
		this.cleanStringLength();
				
		this.string_increment_current_value = null;
		
		this.string_increment = null;
		
	}
	
	@FieldMethod
	public void cleanStringStrategyRandom() {
		
		this.string_current_value = null;
		
		this.cleanStringLength();
		
		this.string_random = false;
		
	}
	
	private String generateString() throws FieldException {
		
		String value = ( this.string_current_value != null ? this.string_current_value : "" );
				
		if( this.string_increment_current_value != null && this.string_increment != null ) {
					
			final int STRING_INCREMENT_DIGITS = ( this.string_increment_current_value > 0 ? (int)( Math.log10( this.string_increment_current_value ) + 1 ) : 0 );
			
			final int STRING_TO_GENERATE_DIGITS = this.string_current_value.length();
			
			final int STRING_LENGTH = STRING_INCREMENT_DIGITS + STRING_TO_GENERATE_DIGITS;
			
			if( this.string_length == null || this.string_length < STRING_LENGTH ) { this.string_length = STRING_LENGTH; }
			
			StringBuilder new_value = new StringBuilder();
		
			new_value.append( this.string_current_value ).append( String.format( "%0" + ( this.string_length - STRING_LENGTH + STRING_INCREMENT_DIGITS ) + "d" ,  this.string_increment_current_value ) );
			
			value = new_value.toString().trim();
			
		} else {
			
			if( this.string_length > this.string_current_value.length() ) { 
				
				this.string_length = this.string_current_value.length();
			
			}
			
		}
		
		return value.substring( 0, this.string_length );
		
	}
	
	private String generateRandomString() {
		
		return RandomStringUtils.randomAlphanumeric( ( this.string_length != null ? this.string_length : DEFAULT_STRING_LENGTH ) );
				
	}
	
}
