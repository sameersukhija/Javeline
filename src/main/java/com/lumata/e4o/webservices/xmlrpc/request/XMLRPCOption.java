package com.lumata.e4o.webservices.xmlrpc.request;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.e4o.exceptions.XMLRPCException;

public class XMLRPCOption {
	
	private static final Logger logger = LoggerFactory.getLogger( XMLRPCOption.class );
	
	public enum XMLRPCOptionType { sleep, repeat, average }
	
	Object value;
	
	ArrayList<Object> values;
	
	XMLRPCOptionType optionType;
	
	XMLRPCOption() {}
	
	XMLRPCOption( XMLRPCOptionType optionType, Object value ) {
		
		this.optionType = optionType;
		
		this.value = value; 
	
	}
	
	XMLRPCOption( XMLRPCOptionType optionType, ArrayList<Object> values ) {
		
		this.optionType = optionType;
		
		this.values = values; 
	
	}
	
	public static XMLRPCOption sleep( Long time ) {
				
		return new XMLRPCOption( XMLRPCOptionType.sleep, time );
		
	}
	
	public static XMLRPCOption repeat( Integer times ) {
		
		return new XMLRPCOption( XMLRPCOptionType.repeat, times );
		
	}
	
	public static XMLRPCOption average() {
		
		ArrayList<Integer> values = new ArrayList<Integer>();
		values.add( 1 );
		values.add( null );
		
		return new XMLRPCOption( XMLRPCOptionType.average, null );
		
	}
	
	public static XMLRPCOption average( Integer startSample, Integer endSample ) throws XMLRPCException {
				
		if( startSample <= 0 || endSample <= 0 ) {
			
			throw new XMLRPCException( "the left and the right values must be positive integer" );
			
		}
		
		if( endSample < startSample ) {
			
			throw new XMLRPCException( "the right value must be greater than left value in average function" );
			
		}
				
		ArrayList<Integer> values = new ArrayList<Integer>();
		values.add( startSample );
		values.add( endSample );
				
		return new XMLRPCOption( XMLRPCOptionType.average, values );
		
	}	
	
	public XMLRPCOptionType getOptionType() {
		return optionType;
	}
	
	public Object getOptionValue() {
		return value;
	}
	
	public ArrayList<Object> getOptionValues() {
		return values;
	}
	
	public void setOptionValues( ArrayList<Object> values ) {
		this.values = values;
	}

}
