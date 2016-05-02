package com.g4s.common.testing.generators;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


public class Generate {

	private static final Logger logger = LoggerFactory.getLogger( Generate.class );
	
	@Test(enabled=false)
	public void getClasses() {
		
		ArrayList<Class<?>> classes = Generate.getClassesInPackage( "com.lumata.common.testing.generators.container" );
		
	}
	
	private static ArrayList<Class<?>> getClassesInPackage(String packageName) {  
	    
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
				
		String packageNameSlashed = "/" + packageName.replace(".", "/");
		
	    URL directoryURL = Thread.currentThread().getContextClassLoader().getResource(packageNameSlashed);  
	    
	    if( directoryURL == null) {  
	        logger.info("Could not retrieve URL resource: " + packageNameSlashed);  
	        return classes;  
	    }  
	  
	    String directoryString = directoryURL.getFile();  
	    
	    if (directoryString == null) {  
	    	logger.info("Could not find directory for URL resource: " + packageNameSlashed);  
	        return classes;  
	    }  
	  
	    File directory = new File(directoryString);  
	    
	    if (directory.exists()) {  
	        
	    	// Get the list of the files contained in the package  
	        String[] files = directory.list();  
	        for (String fileName : files) {  
	            // We are only interested in .class files  
	            if (fileName.endsWith(".class")) {  
	                // Remove the .class extension  
	                fileName = fileName.substring(0, fileName.length() - 6);  
	                try {  
	                    classes.add(Class.forName(packageName + "." + fileName));  
	                } catch (ClassNotFoundException e) {  
	                	logger.info(packageName + "." + fileName + " does not appear to be a valid class.", e);  
	                }  
	            }
	            
	        }
	        
	    } else {  
	    	logger.info(packageName + " does not appear to exist as a valid package on the file system.");  
	    }  
	    
	    return classes; 
	    
	} 
	
}
