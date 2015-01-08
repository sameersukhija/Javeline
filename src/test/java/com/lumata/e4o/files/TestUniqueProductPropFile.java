package com.lumata.e4o.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;


public class TestUniqueProductPropFile {
	
	private static final Logger logger = LoggerFactory.getLogger( TestUniqueProductPropFile.class );
	
	private final boolean testEnabled = true;
	
	private Map<String, ArrayList<String>> confFiles;
	
	private final ArrayList<String> files = new ArrayList<String>( Arrays.asList("product.prop", "devel.prop", "prod.prop", "preprod.prop", "test.prop") );
	
	
	@Test( enabled = testEnabled, priority = 1 )
	public void loadFiles() throws IOFileException, IOException {
		
		confFiles = new LinkedHashMap<String, ArrayList<String>>();
		
		for( String file : files ) {
			
			BufferedReader br = IOFileUtils.loadResourceAsBufferedReader( "files", file );
			
			String line;
			
			ArrayList<String> confFile = new ArrayList<String>();
			
			while ((line = br.readLine()) != null ) {
			
				confFile.add( line );
				
			}
			
			confFiles.put( file, confFile );
			
		}
				
	}

	
	@Test( enabled = testEnabled, priority = 2 )
	public void compareFiles() throws IOFileException, IOException {
	
		checkFilesDifferences( "product.prop", "prod.prop" );
		checkFilesDifferences( "prod.prop", "product.prop" );
		
		System.out.println( "###########################################" );
		
		checkFilesDifferences( "product.prop", "preprod.prop" );
		checkFilesDifferences( "preprod.prop", "product.prop" );
		
		System.out.println( "###########################################" );
		
		checkFilesDifferences( "product.prop", "test.prop" );
		checkFilesDifferences( "test.prop", "product.prop"  );
		
		System.out.println( "###########################################" );
		
		checkFilesDifferences( "product.prop", "devel.prop" );
		checkFilesDifferences( "devel.prop", "product.prop" );
		
		System.out.println( "###########################################" );
		
	}
	
	private void checkFilesDifferences( String originFileName, String destFileName ) {
		
		Pattern pattern = Pattern.compile( "^([0-9a-zA-Z_.-]+)[=].+$" );
		
		System.out.println( "### ORIGIN ( " + originFileName + " ) - " + "DEST ( " + destFileName + " ) ###" );
		
		ArrayList<String> originFile = confFiles.get( originFileName );
		ArrayList<String> destFile = confFiles.get( destFileName );
		
		int countOriginLine = 0;
		
		for( String originLine : originFile ) {
		
			countOriginLine++;			
			
			if( originLine.length() > 0 ) {

				int countDestLine = 0;			
				
				int countDestDiffLine = countDestLine;
				
				String destDiffLine = null;
				
				boolean found = false;
				
				boolean difference = false;
				
				boolean validation = false;
							
				Matcher matcherOriginKey = pattern.matcher( originLine );
				
				String originKey = null;
				
				while( matcherOriginKey.find() ) {
					
					originKey = matcherOriginKey.group( 1 );
				
				}
				
				if( null != originKey ) {
										
					for( String destLine : destFile ) {
						
						countDestLine++;
						
						Matcher matcherDestKey = pattern.matcher( destLine );
						
						String destKey = null;
						
						while( matcherDestKey.find() ) {
							
							destKey = matcherDestKey.group( 1 );
							
						}
						
						if( null != destKey ) {
							
							if( destKey.equals( originKey ) ) {
							
								found = true;
								
								difference = !( destLine.equals( originLine ) );
								
								if( difference ) { 
									countDestDiffLine = countDestLine; 
									destDiffLine = destLine;
								} 
								
								validation = ( found && !difference );
																
							}
																			
						}
						
						if( validation ) { break; }
						
					}				
									
				}
							
				if( !validation ) { 
					
					if( !found ) { System.out.println( "MISSING: " + originKey ); } 
					else { 
						System.out.println( "DIFFERENCE ( " + countOriginLine + " - " + countDestDiffLine + " ): " + originKey );						
						System.out.println( originLine );
						System.out.println( destDiffLine );
					} 
				
				}
								
			}
		
		}
		
		System.out.println( "---------------------------------------------" );
		
		
	}
	
	@Test( enabled = testEnabled, priority = 2 )
	public void getUniqCommands() throws IOFileException, IOException {
		
		Pattern pattern = Pattern.compile( ".*([|]uniq[|]).*" );
		
		ArrayList<String> originFile = confFiles.get( "product.prop" );
		
		int countOriginLine = 0;
		
		for( String line : originFile ) {
		
			countOriginLine++;			
			
			if( line.length() > 0 ) {
							
				Matcher matcherProductKey = pattern.matcher( line );
				
				String productKey = null;
				
				while( matcherProductKey.find() ) {
					
					productKey = matcherProductKey.group( 0 );
				
				}
				
				if( null != productKey ) {
					
					System.out.println( productKey );
				
				}
				
			}
			
		}
		
	}
	
	
	
	// 

	
}
