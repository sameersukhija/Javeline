package com.lumata.common.testing.system;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class KernelCommands {

	public static String getDate() {
		
		StringBuilder dateCommand = new StringBuilder();
		
		dateCommand.append( "date +'%Y-%m-%d'" );
		
		return dateCommand.toString();
		
	}
	
	public static String getTime() {
		
		StringBuilder timeCommand = new StringBuilder();
		
		timeCommand.append( "date +%T" );
		
		return timeCommand.toString();
		
	}

	public static String getDateTime() {
		
		StringBuilder dateTimeCommand = new StringBuilder();
		
		dateTimeCommand.append( "date +'%Y-%m-%d %T'" );
		
		return dateTimeCommand.toString();
	
	}
	
	public static String getSetDate( Calendar date ) {
		
		StringBuilder dateCommand = new StringBuilder();
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd");
		
		dateCommand.append( KernelCommands.getDate() ).append( " -s \" " ).append( sdf.format( date.getTime() ) ).append( "\"" );
		
		return dateCommand.toString();
		
	}
	
	public static String getSetTime( Calendar date ) {
		
		StringBuilder timeCommand = new StringBuilder();
		
		SimpleDateFormat sdf = new SimpleDateFormat( "HH:mm:ss");
		
		timeCommand.append( KernelCommands.getTime() ).append( " -s \" " ).append( sdf.format( date.getTime() ) ).append( "\"" );
		
		return timeCommand.toString();
		
	}

	public static String getSetDateTime( Calendar date ) {
		
		StringBuilder dateTimeCommand = new StringBuilder();
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
		
		dateTimeCommand.append( KernelCommands.getDateTime() ).append( " -s \"" ).append( sdf.format( date.getTime() ) ).append( "\"" );
		
		return dateTimeCommand.toString();
	
	}
	
}
