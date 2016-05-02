package com.g4s.common.testing.system;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.g4s.common.testing.exceptions.IOFileException;
import com.g4s.common.testing.io.IOFileUtils;

public class Packages {

	private static final Logger logger = LoggerFactory.getLogger( Packages.class );
	
	public static List<Class<?>> getClassList( String rootDirName, String fileName, String rootPackageName ) throws IOFileException {
				
		List<String> files = new ArrayList<String>();
    	
		IOFileUtils.searchFile( rootDirName, fileName, files );
    	   		
		StringBuilder packageRegex = new StringBuilder();
		
		packageRegex.append( ".*(" ).append( rootPackageName ).append( ".*)[.]class" );
		
		List<Class<?>> classList = new ArrayList<Class<?>>();
		
		for( int i = 0; i < files.size(); i++ ) {
		
			try {
			
				String packageClass = files.get( 0 ).replaceAll( packageRegex.toString(), "$1" ).replaceAll( "/" , ".");
						
				classList.add( Class.forName( packageClass ) );
			
			} catch( ClassNotFoundException e ) {}
		
		}   	
		
		return classList;
		
	}
	
}
