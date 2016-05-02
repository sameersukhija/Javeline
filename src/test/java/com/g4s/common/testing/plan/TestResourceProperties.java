package com.g4s.common.testing.plan;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.g4s.common.testing.exceptions.IOFileException;
import com.g4s.common.testing.exceptions.JSONSException;
import com.g4s.common.testing.exceptions.ResourcePropertiesException;
import com.g4s.common.testing.io.IOFileUtils;
import com.g4s.common.testing.io.JSONUtils;
import com.g4s.common.testing.system.ResourceProperties;


public class TestResourceProperties {
	
	private static final  Logger logger = LoggerFactory.getLogger( TestResourceProperties.class );
	
	/** All properties exist in the resource file ( success: 3 )*/
	@Test()
	public void loadSystemProperties_1() throws ResourcePropertiesException {		
		int[] result = ResourceProperties.load( "system.properties" );
		Assert.assertEquals(System.getProperty("project.name"), "E4B");
		Assert.assertEquals(System.getProperty("project.document"), System.getProperty("user.home") + "/Documents/" + System.getProperty("project.name") );
		Assert.assertEquals(System.getProperty("project.resource.examples"), "/examples");
		Assert.assertEquals(result[ 0 ], 3);
		Assert.assertEquals(result[ 1 ], 0);
		logger.info( "Success: " + result[ 0 ] + " - Failed: " + result[ 1 ] );
	}
	
	/** One property not exists in the resource file */
	@Test()
	public void loadSystemProperties_2() throws ResourcePropertiesException {		
		int[] result = ResourceProperties.load( "system.properties" );
		Assert.assertEquals(System.getProperty("project.name"), "E4B");
		Assert.assertEquals(System.getProperty("project.document"), System.getProperty("user.home") + "/Documents/" + System.getProperty("project.name") );
		Assert.assertEquals(System.getProperty("project.resource.examples"), "/examples");
		Assert.assertNull(System.getProperty("not.exists"));
		Assert.assertEquals(result[ 0 ], 3);
		Assert.assertEquals(result[ 1 ], 0);
		logger.info( "Success: " + result[ 0 ] + " - Failed: " + result[ 1 ] );
	}
	
	/** The resource file has a not valid format*/
	@Test()
	public void loadSystemProperties_3() throws ResourcePropertiesException {		
		int[] result = ResourceProperties.load( "e4b_qa.json" );
		Assert.assertEquals(result[ 0 ], 0);
		Assert.assertEquals(result[ 1 ], 1);
		logger.info( "Success: " + result[ 0 ] + " - Failed: " + result[ 1 ] );
	}
	
	/** The resource file is null */
	@Test( expectedExceptions = ResourcePropertiesException.class )
	public void loadSystemProperties_4() throws ResourcePropertiesException {		
		int[] result = ResourceProperties.load( null );
		Assert.assertEquals(result[ 0 ], 0);
		Assert.assertEquals(result[ 1 ], 0);
		logger.info( "Success: " + result[ 0 ] + " - Failed: " + result[ 1 ] );
	}
	
	/** The resource file has some wrong row */
	@Test()
	public void loadSystemProperties_5() throws ResourcePropertiesException {		
		int[] result = ResourceProperties.load( "system_1_row_wrong_1_row_empty.properties" );
		Assert.assertEquals(result[ 0 ], 3);
		Assert.assertEquals(result[ 1 ], 2);
		logger.info( "Success: " + result[ 0 ] + " - Failed: " + result[ 1 ] );
	}
	
}
