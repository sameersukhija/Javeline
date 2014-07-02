package com.lumata.common.testing.validating;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public final class Format {

    public final static String JSON_EXTENSION = ".json";
    public final static String EXCEL_XLS_EXTENSION = ".xls";
    public final static String EXCEL_XLSX_EXTENSION = ".xlsx";

    private Format() {}
	
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
		
		if( value.isEmpty() ) { return allowBlank; } 
				
		switch( op ) {
		
			case MORE_THAN: {
				return ( value.length() >  length );
			}
			case MORE_OR_EQUAL_THAN: {
				return ( value.length() >=  length );
			}
			case LESS_THAN: {
				return( value.length() <  length );
			}
			case LESS_OR_EQUAL_THAN: {
				return( value.length() <=  length );
			}
			case EQUAL: {
				return ( value.length() ==  length );
			}
		
		}
				
		return false;
		
	}
	
	public static boolean isDate(String dateStr, String dateFormat, boolean allowBlank ){
		 
		if(dateStr == null || dateStr.length() <= 0 ) { return allowBlank; } 
		
		SimpleDateFormat sdf = new SimpleDateFormat( dateFormat );
		 
		Date date = null; 
		
		try { date = sdf.parse(dateStr); } 
		catch ( ParseException e ) { return false; }	
		
		if (!sdf.format(date).equals(dateStr)) { return false; }
		
		return true;
				
	}
	
	public static boolean isEmail( String value, String format, boolean allowBlank ) {
		
		if( value.isEmpty() ) { return allowBlank; }
		
		if(value.matches(format)) { return true; }
				
		return false;
		
	}
	
	public static boolean isMSISDN( String value, String format, String filter, boolean allowBlank ) {
		
		if( value.isEmpty() ) { return allowBlank; }
		
		value = value.replaceAll(filter,"");
		
		if( value.matches(format)) { return true; }
				
		return false;
		
	}
	
	public static boolean isEnum( String value, String type, boolean allowBlank ) {
		
		if( value.isEmpty() ) { return allowBlank; }
		
		try {
			
			Class<Enum> E = (Class<Enum>)Class.forName(type);
			
			Enum.valueOf( E, value );
			
			return true;
		
		} catch( Exception e ) { return false; }		
		
	}
	
	public static boolean isMapKey( String value, String type, Object obj, boolean allowBlank ) {
		
		if( value.isEmpty() ) { return allowBlank; }
		
		Map map;
		
		try {
			
			Field f = obj.getClass().getField(type);
			map = (Map)f.get(obj);
		
		} catch( NoSuchFieldException e ) { return false; }
		  catch( IllegalAccessException e ) { return false; }
	
		
		if( map.containsKey(value) ) { return true; }
		
		return false;		
		
	}

	public static boolean isFile( String value ) {
		
		final String FILE_PATTERN = "(^[^./\"'?$&*|]+(\\.(?i)[^./\"'?$&*|]+)$)";
		
		Pattern pattern = Pattern.compile( FILE_PATTERN );
				
		Matcher matcher = pattern.matcher( value );
		
		return matcher.matches();
		
	}
	
	public static Date getMysqlDateTime( String datetime ) throws ParseException {
		
		DateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		
		Date date = formatter.parse( datetime );
		
		return date;
		
	}
	
	public static String getSystemTimestamp() {
		
		Calendar currentDate = Calendar.getInstance();
		
		return String.valueOf( currentDate.getTimeInMillis() );
		
	}
	
	public static boolean isNumeric( String value ) {
		
		try {  
			
			double d = Double.parseDouble( value );  
		
		} catch( NumberFormatException nfe ) {  
			
			return false;  
		
		}  
		  
		return true;  
		
	}

	public static String toNumeric( String value ) {
		
		String number = null;
		
		if( null != value ) {
		
			number = value.replaceAll("[^\\d.]", "");
		
		}
		
		return number;  
		
	}

	
	
}
