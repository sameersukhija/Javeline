package com.lumata.e4o.system.fields;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lumata.e4o.exceptions.CDRException;
import com.lumata.e4o.system.cdr.CDRClassGenerator;
import com.lumata.e4o.system.field.types.FieldMethod;

public class testSet {

	public enum HOBBIES {
		
		Cycling, Football, Baseball, Golfing;
		
	}
	
	public enum OTHER_HOBBIES {
		
		Cycling, Baseball, Golfing, Volley;
		
	}
	
	public static void main( String [] args ) throws CDRException, ClassNotFoundException {
				
		FieldSet set = new FieldSet( HOBBIES.values() );
		
		//set.setSetStrategyFixed();
		
		//set.setSetStrategyFixed( new LinkedHashSet<String>( Arrays.asList(new String[] { "Cycling", "Football", "Baseball" }))   );
				
		//set.setSetStrategyFixed( OTHER_HOBBIES.values() );
		
		//set.setSetStrategyFixed( "Cycling", "Golfing", "Baseball" );
		
		//set.setSetStrategyIncrement( HOBBIES.Golfing, 2, 5 );
		
		//set.setSetStrategyIncrement( "Golfing", 1, 3 );
		
		//set.setSetStrategyRandom( 2 );
		/*
		for( int i = 0; i < 5; i++ ) {
		
			System.out.println( set.getSet() );
			
		}
		*/
		
		//String r = "public void setSetStrategyFixed[ ]*[(].*Enum[ \\[\\]_<>?a-zA-Z0-9]+[)][ _0-9a-zA-Z]*";
		
		//String method = "	public void setSetStrategyFixed( final Enum<?>[] values ) throws CDRException {"  +	
		
				
		//Pattern pattern_csv_type_method = Pattern.compile( r );
		//Matcher matcher_csv_type_method = pattern_csv_type_method.matcher( method );
		
		//System.out.println( matcher_csv_type_method.find() );
		
		
		/*
		
		try {
			
			CDRClassGenerator ccg = new CDRClassGenerator();
			
			Map<String, String> classes = ccg.loadCSVTypeClasses();
			
			//System.out.println( classes.get( "FieldSet" ) );
			
			Pattern pattern_csv_type_method = Pattern.compile( "public void setSetStrategyFixed[ ]*[(].*Enum[ \\[\\]_<>?a-zA-Z0-9]+[)][ _0-9a-zA-Z]*" );
			
			Matcher matcher_csv_type_method = pattern_csv_type_method.matcher( classes.get( "FieldSet" ) );
			
			System.out.println( matcher_csv_type_method.find() );
			
		
		} catch (Exception e) {
			
			e.printStackTrace();
		
		
		}
		*/
		
		/*
		String s = "[Ljava.lang.String;";
		
		System.out.println( s.replaceAll( ".+[.].+[.](.+)[;]", "$1..." ) );
		
		
		String r = "[Ljava";
		
		System.out.println( r.replaceAll( ".+", "" ) );
		
		*/
		
		final Class<?> fieldTypeClass = Class.forName( "com.lumata.e4o.system.fields.FieldSet" );
		
		/** Get CSV Type methods */
		for( final Method fieldTypeMethod : fieldTypeClass.getDeclaredMethods() ) {
			
			System.out.println( "##########################" );
			
			System.out.println( fieldTypeMethod.getName() );
			
			StringBuilder parameterRegex = new StringBuilder();
			
			for( Class<?> parameter : fieldTypeMethod.getParameterTypes() ) {
						
				System.out.println( parameter.getName() );
				System.out.println( parameter.getName().replace( ";", "" ) );
				
				Boolean isArray = parameter.getName().startsWith( "[" );
				System.out.println( isArray );
							
				parameterRegex.append( ".*" ).append( parameter.getName().replace( ";", "" ).replaceAll( "[\\[\\]0-9a-zA-Z]+[.]", "" ) ).append( "[ _<>?a-zA-Z0-9]+" ).append( ( isArray ? "[.\\\\[\\\\]]+" : "" ) ).append( "," ) ;
			
				System.out.println( parameterRegex.toString() );
				
			}
			
			
		}
		
		System.out.println( "------------------" );		
				
	}
	
}
