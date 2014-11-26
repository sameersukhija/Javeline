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
		checkFilesDifferences( "product.prop", "preprod.prop" );
		checkFilesDifferences( "product.prop", "test.prop" );
		checkFilesDifferences( "product.prop", "devel.prop" );
		checkFilesDifferences( "prod.prop", "product.prop" );
		checkFilesDifferences( "preprod.prop", "product.prop" );
		checkFilesDifferences( "test.prop", "product.prop"  );
		checkFilesDifferences( "devel.prop", "product.prop" );
		
//		Pattern pattern = Pattern.compile( "^([0-9a-zA-Z_.-]+)[=].+$" );
//				
//		ArrayList<String> productFile = confFiles.get( "product.prop" );
//		ArrayList<String> prodFile = confFiles.get( "prod.prop" );
//		
//		
//		for( String productLine : productFile ) {
//			
//			Matcher matcherProductKey = pattern.matcher( productLine );
//			
//			while( matcherProductKey.find() ) {
//				
//				String productKey = matcherProductKey.group( 1 );
//				
//				boolean found = false;
//				boolean difference = false;
//				
//				for( String prodLine : prodFile ) {
//					
//					Matcher matcherProdKey = pattern.matcher( prodLine );
//					
//					while( matcherProdKey.find() ) {
//						
//						String prodKey = matcherProdKey.group( 1 );
//						
//						if( prodKey.equals( productKey ) ) {
//							
//							found = true;
//							
//							difference = !prodLine.equals( productLine );
//							
//						}
//						
//					}
//					
//				}				
//				
//				if( !found ) { 
//					
//					System.out.println( "MISSING: " + productKey );
//				
//				}
//				
//				if( difference ) { 
//					
//					System.out.println( "DIFFERENCE: " + productKey );
//				
//				}
//				
//			}
//			
//		} 
		
	}
	
	private void checkFilesDifferences( String originFileName, String destFileName ) {
		
		Pattern pattern = Pattern.compile( "^([0-9a-zA-Z_.-]+)[=].+$" );
		
		System.out.println( "### ORIGIN ( " + originFileName + " ) - " + "DEST ( " + destFileName + " ) ###" );
		
		ArrayList<String> originFile = confFiles.get( originFileName );
		ArrayList<String> destFile = confFiles.get( "prod.prop" );
				
		for( String originLine : originFile ) {
			
			Matcher matcherOriginKey = pattern.matcher( originLine );
			
			while( matcherOriginKey.find() ) {
				
				String originKey = matcherOriginKey.group( 1 );
				
				boolean found = false;
				boolean difference = false;
				
				for( String destLine : destFile ) {
					
					Matcher matcherDestKey = pattern.matcher( destLine );
					
					while( matcherDestKey.find() ) {
						
						String destKey = matcherDestKey.group( 1 );
						
						if( destKey.equals( originKey ) ) {
							
							found = true;
							
							difference = !destLine.equals( originLine );
							
						}
						
					}
					
				}				
				
				if( !found ) { 
					
					System.out.println( "MISSING: " + originKey );
				
				}
				
				if( difference ) { 
					
					System.out.println( "DIFFERENCE: " + originKey );
				
				}
				
			}
			
		}
		
		System.out.println( "---------------------------------------------" );
		
		
	}
	
}
