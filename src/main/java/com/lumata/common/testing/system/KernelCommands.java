package com.lumata.common.testing.system;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class KernelCommands {

	public static String getDate( Calendar date ) {
		
		StringBuilder dateCommand = new StringBuilder();
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd");
		
		dateCommand.append( "date +%D -s \" " ).append( sdf.format( date.getTime() ) ).append( "\"" );
		
		return dateCommand.toString();
		
	}
	
	public static String getTime( Calendar date ) {
		
		StringBuilder timeCommand = new StringBuilder();
		
		SimpleDateFormat sdf = new SimpleDateFormat( "HH:mm:ss");
		
		timeCommand.append( "date +%T -s \" " ).append( sdf.format( date.getTime() ) ).append( "\"" );
		
		return timeCommand.toString();
		
	}

	public static String getDateTime( Calendar date ) {
		
		StringBuilder dateTimeCommand = new StringBuilder();
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
		
		dateTimeCommand.append( "date +%D%T -s \"" ).append( sdf.format( date.getTime() ) ).append( "\"" );
		
		return dateTimeCommand.toString();
	
	}
	
}
