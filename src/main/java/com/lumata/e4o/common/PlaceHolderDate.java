package com.lumata.e4o.common;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceHolderDate {

	private final String PLACE_HOLDER_REGEX_ = "@(current|[0-9]{4}-[0-9]{2}-[0-9]{2})|([+-]{1}[0-9]+)(YEAR|Year|year|MONTH|Month|month|DAY|Day|day)+";
	private final Pattern PLACE_HOLDER_PATTERN_ = Pattern.compile( PLACE_HOLDER_REGEX_ ); 
	private Matcher placeHolderDateMatcher;		
	private Calendar date;
	private StringBuilder placeHolderDate;
	
	private enum PlaceHolderDateTag {
		current, year, month, day;
	}

	private PlaceHolderDate( String placeHolderDate ) {
		
		this.placeHolderDate = new StringBuilder();
		
		this.placeHolderDate.append( placeHolderDate );
		
		date = Calendar.getInstance();
	
	}

	public static PlaceHolderDate getInstance( String placeHolderDate ) {
		
		return new PlaceHolderDate( placeHolderDate );
	
	}
	
	public Boolean isPlaceHolderDate() {
		
		return isPlaceHolderDate( placeHolderDate.toString() );
	}
	
	public Boolean isPlaceHolderDate( String placeHolderDate ) {
		
		placeHolderDateMatcher = PLACE_HOLDER_PATTERN_.matcher( placeHolderDate );
		
		return ( null != placeHolderDate ? placeHolderDateMatcher.find() : false );
	}
	
	public Calendar parse() {
		
		placeHolderDateMatcher = PLACE_HOLDER_PATTERN_.matcher( placeHolderDate.toString() );
		
		this.date = null;
		
		while( placeHolderDateMatcher.find() ) {
			
			String date = placeHolderDateMatcher.group( 1 );
			String value = placeHolderDateMatcher.group( 2 );
			String unit = placeHolderDateMatcher.group( 3 );
			
			if( null != date ) {
				
				this.date = Calendar.getInstance();
				
				if( !date.toLowerCase().equals( PlaceHolderDateTag.current.name() ) ) {
					
					String[] newDate = date.split( "-" );
					
					this.date.set( Integer.valueOf( newDate[ 0 ] ), Integer.valueOf( newDate[ 1 ] ), Integer.valueOf( newDate[ 2 ] ) );
					
				}
				
			} else {
				
				Integer increment = Integer.valueOf( value );
				
				switch( PlaceHolderDateTag.valueOf( unit.toLowerCase() ) ) {
				
					case year: {
						
						this.date.add( Calendar.YEAR, increment );
						
						break;
						
					}
					case month: {
						
						this.date.add( Calendar.MONTH, increment );
						
						break;
						
					}
					case day: {
						
						this.date.add( Calendar.DATE, increment );
						
						break;
						
					}
					default: {}
				
				}
				
			}
				
		}
		
		return this.date;
		
	}
	
}
