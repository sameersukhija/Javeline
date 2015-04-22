package com.lumata.e4o.utils.generators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;

public class GenerateSubscribersToImport {

	private static final Logger logger = LoggerFactory.getLogger( GenerateSubscribersToImport.class );
	
	@Test( priority = 1, enabled = true )
	public void generateSubscribers() throws IOFileException {
		
		// Number of subscribers to generate
		final int SUBSCRIBERS_TO_GENERATE = 1000000;
		
		// Max MSISDN length
		int MSISDN_MAX_LENGTH = 10;
		
		// MSISDN PREFIX
		final String SUBSCRIBER_PREFIX = "33999";
		
		logger.info( Log.PUTTING.createMessage( "generateSubscribers" , "Subscribers to import" ) );
			
		final int SUBSCRIBERS_PREFIX_DIGITS = (int)( Math.log10( Integer.valueOf( SUBSCRIBER_PREFIX ) ) + 1 );
		final int SUBSCRIBERS_TO_GENERATE_DIGITS = (int)( Math.log10( SUBSCRIBERS_TO_GENERATE ) + 1 );
		
		final int MSISDN_LENGTH = SUBSCRIBERS_PREFIX_DIGITS + SUBSCRIBERS_TO_GENERATE_DIGITS;
		
		if( MSISDN_MAX_LENGTH < MSISDN_LENGTH ) { MSISDN_MAX_LENGTH = MSISDN_LENGTH; }
		
		StringBuilder subscribers = new StringBuilder();
		
		for( int i = 1; i <= SUBSCRIBERS_TO_GENERATE; i++ ) {
			
	        String format = "%0" + ( MSISDN_MAX_LENGTH - SUBSCRIBERS_PREFIX_DIGITS ) + "d";
			
	        String msisdn = SUBSCRIBER_PREFIX + String.format( format, i );
			
	        subscribers.append( msisdn ).append( "\n" );
	        
		}
		
		IOFileUtils.saveResource( subscribers.toString(), "subscribers", "subscribers.csv" );	
				
	}
	
}
