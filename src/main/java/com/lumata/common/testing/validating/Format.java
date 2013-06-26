package com.lumata.common.testing.validating;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author <a href="mailto:arcangelo.dipasquale@gmail.com">Arcangelo Di Pasquale</a>
 * 
 */
public class Format {

	public enum Operators { 
		
		MORE_THAN(">"),
		MORE_OR_EQUAL_THAN(">="),
		LESS_THAN("<"),
		LESS_OR_EQUAL_THAN("<="),
		EQUAL("=");
		
		private String value;
		
		Operators( String value ) {
			this.value = value;
		}
		
		public String getValue() {
			return this.value;
		}		
		
	}
	
	public static boolean isLength( String value, Integer length, Operators op, boolean allowBlank ) {
		
		if( value.isEmpty() ) {
			if( allowBlank ) return true;
			else return false; 
		}
				
		switch( op ) {
		
			case MORE_THAN: {
				if( value.length() >  length ) return true;
				else return false;
			}
			case MORE_OR_EQUAL_THAN: {
				if( value.length() >=  length ) return true;
				else return false;
			}
			case LESS_THAN: {
				if( value.length() <  length ) return true;
				else return false;
			}
			case LESS_OR_EQUAL_THAN: {
				if( value.length() <=  length ) return true;
				else return false;
			}
			case EQUAL: {
				if( value.length() ==  length ) return true;
				else return false;
			}
		
		}
				
		return false;
		
	}
	
	public static boolean isDate(String dateStr, String dateFormat, boolean allowBlank ){
		 
		if(dateStr == null || dateStr.length() <= 0 ) {
			if( allowBlank ) return true;
			else return false;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat( dateFormat );
		 
		Date date = null; 
		
		try { date = sdf.parse(dateStr); } 
		catch ( ParseException e ) { return false; }	
		
		if (!sdf.format(date).equals(dateStr)) return false;
		
		return true;
				
	}
	
	public static boolean isEmail( String value, String format, boolean allowBlank ) {
		
		if( value.isEmpty() ) {
			if( allowBlank ) return true;
			else return false; 
		}
		
		if(value.matches(format)) return true;
				
		return false;
		
	}
	
	public static boolean isMSISDN( String value, String format, String filter, boolean allowBlank ) {
		
		if( value.isEmpty() ) {
			if( allowBlank ) return true;
			else return false; 
		}
		
		value = value.replaceAll(filter,"");
		
		if( value.matches(format)) return true;
				
		return false;
		
	}
	
	public static boolean isEnum( String value, String type, boolean allowBlank ) {
		
		if( value.isEmpty() ) {
			if( allowBlank ) return true;
			else return false; 
		}
		
		try {
			
			Class<Enum> E = (Class<Enum>)Class.forName(type);
			
			Enum.valueOf( E, value );
			
			return true;
		
		} catch( Exception e ) { return false; }		
		
	}
	
	public static boolean isMapKey( String value, String type, Object obj, boolean allowBlank ) {
		
		if( value.isEmpty() ) {
			if( allowBlank ) return true;
			else return false; 
		}
		
		Map map;
		
		try {
			
			Field f = obj.getClass().getField(type);
			map = (Map)f.get(obj);
		
		} catch( Exception e ) { return false; }
		
		if( map.containsKey(value) ) return true;
		
		return false;		
		
	}

	public static boolean isFile( String value ) {
		
		final String FILE_PATTERN = "(^[^./\"'?$&*|]+(\\.(?i)[^./\"'?$&*|]+)$)";
		
		Pattern pattern = Pattern.compile( FILE_PATTERN );
				
		Matcher matcher = pattern.matcher( value );
		
		return matcher.matches();
		
	}
	
}
