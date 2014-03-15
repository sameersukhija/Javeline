package com.lumata.e4o.system.cdr;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.lumata.expression.operators.exceptions.CDRException;

public class CDRDate {

	private Calendar date_current;
	private SimpleDateFormat date_format;
	private Calendar date_left;
	private Calendar date_right;
	private Calendar date_next;
	private CDRDateIncrement date_increment;
	
	public enum CDRDateFormat {
		
		SQL_DATE( "YYYY-MM-dd" ),
		SQL_DATE_TIME( "YYYY-MM-dd hh:mm:ss" );
		
		String value;
		
		CDRDateFormat(String value ) {
			this.value = value;
		}
		
		public String getFormat() {
			return value;
		}
		
	}
	
	public CDRDate() {
		
		date_current = null;
		date_format = new SimpleDateFormat("yyyy-MM-dd");
		date_left = null;
		date_right = null;
		date_next = null;
		date_increment = null;
				
	}

	public String getDate() {
		
		if( this.date_current == null && ( this.date_left == null || this.date_right == null )) { return ""; } 
		else {
			
			if( this.date_next != null && this.date_increment != null ) {
				
				this.date_current = this.date_next;
				
				this.getDateIncrement();
				
			} else {
				
				if( this.date_increment != null ) {
					
					this.date_next = this.date_current;
										
					this.getDateIncrement();
					
				} else {
					
					if( this.date_left != null && this.date_right != null ) {

						this.date_current = Calendar.getInstance();

						long timestamp_left = this.date_left.getTimeInMillis();

						long timestamp_right = this.date_right.getTimeInMillis();
						
						if( timestamp_left > timestamp_right ) {
							
							long timestamp_temp = timestamp_left;
							
							timestamp_left = timestamp_right;
							
							timestamp_right = timestamp_temp;
						
						}

						long random_timestamp = timestamp_left + (long)( Math.random() * ( timestamp_right - timestamp_left ) );
						
						this.date_current.setTimeInMillis( random_timestamp );
		
					}
					
				}
				
			}			
			
		}
		
		return ( this.date_current != null ? this.date_format.format( this.date_current.getTime() ) : "" );
		
	}

	public void setDateStrategyFixed( final Calendar date ) throws CDRException {
		
		this.date_current = date;
		
		this.cleanDateStrategyIncrement();
		
		this.cleanDateStrategyRandom();
		
	}

	public void setDateFormat( String format ) throws CDRException {
		
		this.date_format = new SimpleDateFormat( format );
						
	}	
	
	public void setDateStrategyIncrement( final Calendar date, final CDRDateIncrement increment ) throws CDRException {
		
		if( date == null ) { throw new CDRException( "The date cannot be null." ); }
		
		if( increment == null ) { throw new CDRException( "The date increment cannot be null." ); }
		
		this.date_current = date;
		
		this.date_increment = increment;
			
		this.cleanDateStrategyRandom();
		
	}
	
	public void setDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws CDRException {
		
		if( date_left == null ) { throw new CDRException( "The min date cannot be null." ); }
		
		if( date_right == null ) { throw new CDRException( "The max date cannot be null." ); }
		
		this.date_left = date_left;
		
		this.date_right = date_right;
		
		this.cleanDateStrategyIncrement();
		
	}
	
	public void cleanDateStrategyFixed() {
		
		this.date_current = null;
					
	}
	
	public void cleanDateStrategyIncrement() {
		
		this.date_next = null;
		
		this.date_increment = null;
					
	}
	
	public void cleanDateStrategyRandom() {
		
		this.date_left = null;
		
		this.date_right = null;
					
	}
	
	private void getDateIncrement() {
		
		this.date_next.add( Calendar.YEAR, this.date_increment.getYearIncrement() );
		this.date_next.add( Calendar.MONTH, this.date_increment.getMonthIncrement() );
		this.date_next.add( Calendar.DATE, this.date_increment.getDayIncrement() );
		this.date_next.add( Calendar.HOUR_OF_DAY, this.date_increment.getHourIncrement() );
		this.date_next.add( Calendar.MINUTE, this.date_increment.getMinuteIncrement() );
		this.date_next.add( Calendar.SECOND, this.date_increment.getSecondIncrement() );
				
	}
	
}
