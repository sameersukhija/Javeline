package com.lumata.e4o.utils.generators;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;


public class RandomSMS {

	@Test
	public void generateRandomSMS() {
		
		Integer smsLength = 500;
		
		Integer minWordLength = 5;
		
		Integer maxWordLength = 20;
		
		StringBuilder sms = new StringBuilder();
		
		while ( smsLength > 0 ) {
		
			Integer currentWordLength = ( smsLength - ( maxWordLength + 1 ) > 0 ? minWordLength + (int)(Math.random() * maxWordLength ) : smsLength );
		
			smsLength = smsLength - currentWordLength - 1;
				
			sms.append( RandomStringUtils.randomAlphanumeric( currentWordLength ) ).append( " " );
		
		}
		
		System.out.println( sms.toString() );
		
	}
	
}
