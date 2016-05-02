package com.g4s.common.testing.utils;

import java.util.ArrayList;

import org.apache.commons.lang3.RandomStringUtils;

public class Arithmetic {

	public static Long average( final ArrayList<Long> samples ) {

		return ( null != samples ? Arithmetic.average( samples, 1, samples.size() ) : 0L );
		
	}

	public static Long average( final ArrayList<Long> samples, final Integer startValue, final Integer endValue ) {
		
		if( null == samples ) { return 0L; }
		
		Long average = 0L;
		String a = "";		
		Integer startFromSample = Math.max( startValue, 1 );
		
		System.out.println( "startFromSample: " + startFromSample );
		System.out.println( "endValue: " + endValue );
		System.out.println( "samplesSize: " + samples.size() );
		
		Integer endToSample = Math.min( endValue, Integer.valueOf( samples.size() ) );
		System.out.println( "endToSample: " + endToSample );
		Integer samplesCalculated = 0;
		
		for( Integer s = startFromSample - 1; s < endToSample; s++ ) {
			average = average + samples.get( s );
			samplesCalculated++;
			a = a + samples.get( s ) + " ";
		}
		System.out.println( "samplesCalculated: " + samplesCalculated );
		System.out.println( "average: " + average );
		System.out.println( a );
		return ( average / Math.max( samplesCalculated, 1 ) );
		
	}
	
	public static Long random( Long startValue, Long endValue ) {
		
		return ( startValue + (long)( Math.random() * ( endValue - startValue ) ) ); 
		
	}
	
	public static Long randomImei() {
		
		return Long.valueOf( RandomStringUtils.randomNumeric( 15 ) );
		
	}
	
	public static Integer random( Integer startValue, Integer endValue ) {
		
		return (int)( Math.random() * ( startValue - endValue ) + endValue ); 
		
	}

}
