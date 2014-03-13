package com.lumata.expression.operators.system.cdr;

import com.lumata.expression.operators.exceptions.CDRException;

public class CDRMsisdn {

	private Integer msisdn_prefix;
	private Long msisdn_current_value;
	private Long msisdn_left_value;
	private Long msisdn_right_value;
	private Long msisdn_next_value;
	private Integer msisdn_increment;
	private Integer msisdn_length;
	
	public CDRMsisdn() {
		
		msisdn_prefix = null;
		msisdn_current_value = null;
		msisdn_left_value = null;
		msisdn_right_value = null;
		msisdn_next_value = null;
		msisdn_increment = null;
		msisdn_length = null;
		
	}

	public String getMsisdn() throws CDRException {
		
		if( this.msisdn_current_value == null && ( this.msisdn_left_value == null || this.msisdn_right_value == null )) { return ""; } 
		else {
			
			if( this.msisdn_next_value != null && this.msisdn_increment != null ) {
				
				this.msisdn_current_value = this.msisdn_next_value;
				
				this.msisdn_next_value = Long.valueOf( this.msisdn_next_value + this.msisdn_increment );
				
			} else {
				
				if( this.msisdn_increment != null ) {
					
					this.msisdn_next_value = Long.valueOf( this.msisdn_current_value + this.msisdn_increment );
				
				} else {
					
					if( this.msisdn_left_value != null && this.msisdn_right_value != null ) {
						
						this.msisdn_current_value = this.generateRandomLong( this.msisdn_left_value, this.msisdn_right_value );
						
					}
					
				}
				
			}			
			
		}
		
		return String.valueOf( this.generateMSISDN() ); 
		
	}
	
	public void setMsisdnOptions( final Integer prefix, final Integer length ) throws CDRException {
		
		if( length != null && length > 19 ) { throw new CDRException( "The msisdn length must be less than 20." ); }
		
		this.msisdn_prefix = ( prefix != null ? Math.abs( prefix ) : null );
				
		this.msisdn_length = ( length != null ? Math.abs( length ) : null );
		
	}

	public void setMsisdnStrategyFixed( final Long value ) throws CDRException {	
		
		this.msisdn_current_value = ( value != null ? Math.abs( value ) : null );
		
		this.cleanMsisdnStrategyIncrement();
		
		this.cleanMsisdnStrategyRandom();
		
	}
	
	public void setMsisdnStrategyIncrement( final Long value, final Integer increment ) throws CDRException {
		
		if( value == null ) { throw new CDRException( "The msisdn cannot be null." ); }
		
		if( increment == null ) { throw new CDRException( "The msisdn increment cannot be null." ); }
		
		this.msisdn_current_value = Math.abs( value );
		
		this.msisdn_increment = Math.abs( increment );
		
		this.cleanMsisdnStrategyRandom();
		
	}
	
	public void setMsisdnStrategyRandom( final Long min_value, final Long max_value ) throws CDRException {
		
		if( min_value == null ) { throw new CDRException( "The min msisdn value cannot be null." ); }
		
		if( max_value == null ) { throw new CDRException( "The max msisdn value cannot be null." ); }
		
		this.msisdn_left_value = Math.abs( min_value );
		
		this.msisdn_right_value = Math.abs( max_value );
		
		this.cleanMsisdnStrategyIncrement();
		
	}
	
	public void cleanMsisdn() {
		
		this.msisdn_current_value = null;
			
	}
	
	public void cleanMsisdnOptions() {
				
		this.msisdn_prefix = null;
		
		this.msisdn_current_value = null;
		
		this.msisdn_length = null;
				
	}
	
	public void cleanMsisdnStrategyIncrement() {
		
		this.msisdn_increment = null;
		
		this.msisdn_next_value = null;
		
	}
	
	public void cleanMsisdnStrategyRandom() {
		
		this.msisdn_left_value = null;
		
		this.msisdn_right_value = null;
		
	}
	
	private Long generateMSISDN() throws CDRException {
		
		Long msisdn = this.msisdn_current_value;
				
		if( this.msisdn_prefix != null ) {
			
			final int SUBSCRIBERS_PREFIX_DIGITS = ( this.msisdn_prefix > 0 ? (int)( Math.log10( this.msisdn_prefix ) + 1 ) : 0 );
			
			final int SUBSCRIBERS_TO_GENERATE_DIGITS = ( this.msisdn_current_value > 0 ? (int)( Math.log10( this.msisdn_current_value ) + 1 ) : 0 );
			
			final int MSISDN_LENGTH = SUBSCRIBERS_PREFIX_DIGITS + SUBSCRIBERS_TO_GENERATE_DIGITS;
			
			if( this.msisdn_length == null || this.msisdn_length < MSISDN_LENGTH ) { this.msisdn_length = MSISDN_LENGTH; }
			
			StringBuilder msisdn_str = new StringBuilder();
			
			msisdn_str.append( this.msisdn_prefix ).append( String.format( "%0" + ( this.msisdn_length - MSISDN_LENGTH + SUBSCRIBERS_TO_GENERATE_DIGITS ) + "d" ,  this.msisdn_current_value ) );
						
			try {
				
				msisdn = Long.valueOf( msisdn_str.toString().trim() );
						
			} catch( NumberFormatException ne ) {
				
				throw new CDRException( ne.getMessage(), ne );
								
			}
			
		} 
		
		return msisdn;
		
	}
	
	private Long generateRandomLong( final Long min_value, final Long max_value ) {
		
		return min_value + Long.valueOf( (int)( Math.random() * ( max_value - min_value ) ) );
				
	}
	
}
