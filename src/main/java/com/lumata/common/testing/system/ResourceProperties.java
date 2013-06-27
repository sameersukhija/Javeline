package com.lumata.common.testing.system;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.ResourcePropertiesException;
import com.lumata.common.testing.io.IOFileUtils;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class ResourceProperties {

	private static final  Logger logger = LoggerFactory.getLogger( ResourceProperties.class );
		
	public static int[] load( String resource ) throws ResourcePropertiesException {
		
		int[] result = new int[2];
		result[ 0 ] = 0;
		result[ 1 ] = 0;
		
		try {
			
			BufferedReader br = IOFileUtils.loadResourceAsBufferedReader( resource );
			
			String line;
			Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}");
						
			while (( line = br.readLine()) != null) {
				
				if( line.length() > 0 ) {
										
					try {
						
						String[] property = line.split("=");
						
						String propertyName = property[ 0 ];
						String propertyValue = property[ 1 ];
															
						Matcher matcher = pattern.matcher(line);
					    
					    while( matcher.find() ) {
					        
					    	propertyValue = propertyValue.replaceAll("\\$\\{" + matcher.group(1) + "\\}", System.getProperty(matcher.group(1)));
					    	
					    }
					    
					    System.setProperty(propertyName, propertyValue);
					   
					    /* success */
					    result[ 0 ]++;
					    
					    logger.debug( "The property has been loaded ( " + propertyName + ": " + propertyValue + " )");
					    
					} catch( Exception e ) {
						
						logger.error( e.getMessage(), e );
						
						/* skipped */
					    result[ 1 ]++;
						
					}
					
				} else { result[ 1 ]++; }
				
			}
			
		} catch( IOException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new ResourcePropertiesException(e.getMessage(), e);
			
		} catch( IOFileException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new ResourcePropertiesException(e.getMessage(), e);
						
		}
		
		return result;
		
	}
		
	public static void printAllGuaranteedProperties() {
       
		 printProperty ("java.version", "Java version number");
		 printProperty ("java.vendor", "Java vendor specific string");
		 printProperty ("java.vendor.url", "Java vendor URL");
		 printProperty ("java.home", "Java installation directory");
		 printProperty ("java.class.version", "Java class version number");
		 printProperty ("java.class.path", "Java classpath");
		 printProperty ("os.name", "Operating System Name");
		 printProperty ("os.arch", "Operating System Architecture");
		 printProperty ("os.version", "Operating System Version");
		 printProperty ("file.separator", "File separator");
		 printProperty ("path.separator", "Path separator");
		 printProperty ("line.separator", "Line separator");
		 printProperty ("user.name", "User account name");
		 printProperty ("user.home", "User home directory");
		 printProperty ("user.dir", "User's current working directory");
		 
	}
    
	public static void printProperty (String propName, String desc) {
       
		logger.info("Value for '" + desc + "' is '" + System.getProperty(propName) + "'.");
    
	}
	 
}
