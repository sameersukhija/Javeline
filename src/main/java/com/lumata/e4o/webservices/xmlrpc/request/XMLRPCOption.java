package com.lumata.e4o.webservices.xmlrpc.request;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.e4o.exceptions.XMLRPCException;

public class XMLRPCOption {
	
	private static final Logger logger = LoggerFactory.getLogger( XMLRPCOption.class );
	
	public enum XMLRPCOptionType { sleep, repeat, average, storeRequestAsResource, storeResponseAsResource, storeRequestAsFile, storeResponseAsFile  }
	
	Object value;
	
	Object[] values;
	
	XMLRPCOptionType optionType;
	
	XMLRPCOption() {}
	
	public XMLRPCOption( XMLRPCOptionType optionType, Object value ) {
		
		this.optionType = optionType;
		
		this.value = value; 
		
	}
	
	public XMLRPCOption( XMLRPCOptionType optionType, Object... values ) {
		
		this.optionType = optionType;
		
		this.values = values; 
		
	}
	
	public static XMLRPCOption sleep( Long time ) {
				
		return new XMLRPCOption( XMLRPCOptionType.sleep, time );
		
	}
	
	public static XMLRPCOption repeat( Integer times ) {
		
		logger.debug( Log.SAVED.createMessage( "option " + XMLRPCOptionType.repeat.name() ) );
		
		return new XMLRPCOption( XMLRPCOptionType.repeat, times );
		
	}
	
	public static XMLRPCOption average() {
		
		ArrayList<Integer> values = new ArrayList<Integer>();
		values.add( 1 );
		values.add( null );
		
		logger.debug( Log.SAVED.createMessage( "option " + XMLRPCOptionType.average.name() ) );
				
		return new XMLRPCOption( XMLRPCOptionType.average, new Object[0] );
		
	}
	
	public static XMLRPCOption average( Integer startSample, Integer endSample ) throws XMLRPCException {
				
		if( startSample <= 0 || endSample <= 0 ) {
			
			throw new XMLRPCException( "the left and the right values must be positive integer" );
			
		}
		
		if( endSample < startSample ) {
			
			throw new XMLRPCException( "the right value must be greater than left value in average function" );
			
		}
				
		logger.debug( Log.SAVED.createMessage( "option " + XMLRPCOptionType.average.name() ) );
				
		return new XMLRPCOption( XMLRPCOptionType.average, startSample, endSample );
		
	}	

	public static XMLRPCOption storeRequestAsResource( String folderName, String fileName ) throws XMLRPCException {
		
		logger.debug( Log.SAVED.createMessage( "option " + XMLRPCOptionType.storeRequestAsResource.name() ) );
				
		return new XMLRPCOption( XMLRPCOptionType.storeRequestAsResource, folderName, fileName );
		
	}
	
	public static XMLRPCOption storeResponseAsResource( String folderName, String fileName ) throws XMLRPCException {
						
		logger.debug( Log.SAVED.createMessage( "option " + XMLRPCOptionType.storeResponseAsResource.name() ) );
				
		return new XMLRPCOption( XMLRPCOptionType.storeResponseAsResource, folderName, fileName );
		
	}

	public static XMLRPCOption storeRequestAsFile( String folderName, String fileName ) throws XMLRPCException {
		
		logger.debug( Log.SAVED.createMessage( "option " + XMLRPCOptionType.storeRequestAsFile.name() ) );
				
		return new XMLRPCOption( XMLRPCOptionType.storeRequestAsFile, folderName, fileName );
		
	}
	
	public static XMLRPCOption storeResponseAsFile( String folderName, String fileName ) throws XMLRPCException {
		
		logger.debug( Log.SAVED.createMessage( "option " + XMLRPCOptionType.storeResponseAsFile.name() ) );
				
		return new XMLRPCOption( XMLRPCOptionType.storeResponseAsFile, folderName, fileName );
		
	}
	
	public XMLRPCOptionType getOptionType() {
		return optionType;
	}
	
	public Object getOptionValue() {
		return value;
	}
	
	public Object[] getOptionValues() {
		return values;
	}
	
	public void setOptionValues( Object[] values ) {
		this.values = values;
	}

}
