package com.lumata.e4o.gui.campaignmanager;

import java.util.Calendar;

public class TestMonths {

	public static void main(String [] args) {
		
		Calendar currDate = Calendar.getInstance();
		currDate.set( Calendar.YEAR , 2013 );
		currDate.set( Calendar.MONTH , Calendar.MARCH );		
		
		
		Calendar date = Calendar.getInstance();
		date.set( Calendar.YEAR , 2012 );
		date.set( Calendar.MONTH , Calendar.AUGUST );
		
				
		int diffYears = date.get( Calendar.YEAR ) - currDate.get( Calendar.YEAR );
		
		int diffMonths = ( 12 * diffYears ) + ( date.get( Calendar.MONTH ) - currDate.get( Calendar.MONTH ) );
		
		System.out.println( diffYears );
		
		System.out.println( diffMonths );
				
	}
	
}
