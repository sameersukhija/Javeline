package com.lumata.common.testing.plan;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOSException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.io.JSONUtils;


public class TestJSONUtils {
	
	private static final  Logger logger = LoggerFactory.getLogger( TestJSONUtils.class );
	
	/** CHECK RESOURCE */
	/** The resource exists */
	@Test()
	public void loadJSONResource_1() throws JSONSException, IOSException {		
		Assert.assertNotNull( JSONUtils.loadJSONResource( "e4b_qa.json" ));
	}
	
	/** The resource not exists */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONResource_2() throws JSONSException, IOSException {		
		JSONUtils.loadJSONResource( "not exists.json" );
	}
	
	/** The resource is null */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONResource_3() throws JSONSException, IOSException {		
		JSONUtils.loadJSONResource( null );
	}
	
	/** The resource is malformed */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONResource_4() throws JSONSException, IOSException {		
		JSONUtils.loadJSONResource( "e4b_qa_malformed.json" );
	}
	
	/** The folder exists and the resource exists */
	@Test()
	public void loadJSONResource_5() throws JSONSException, IOSException {		
		Assert.assertNotNull( JSONUtils.loadJSONResource( "examples", "e4b_qa.json" ));
	}
	
	/** The folder exists and the resource not exists */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONResource_6() throws JSONSException, IOSException {		
		JSONUtils.loadJSONResource( "examples", "not exists.json" );
	}
	
	/** The folder exists and the folder exists and the resource is null */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONResource_7() throws JSONSException, IOSException {		
		JSONUtils.loadJSONResource( "examples", null );
	}
	
	/** The folder exists and the resource is malformed */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONResource_8() throws JSONSException, IOSException {		
		JSONUtils.loadJSONResource( "examples", "e4b_qa_malformed.json" );
	}
	
	/** The folder not exists and the resource is valid */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONResource_9() throws JSONSException, IOSException {		
		JSONUtils.loadJSONResource( "not exists/", "e4b_qa.json" );
	}
	
	/** The folder is null and the resource is valid */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONResource_10() throws JSONSException, IOSException {		
		JSONUtils.loadJSONResource( null, "e4b_qa.json" );
	}
	
	/** The resource exists */
	@Test()
	public void loadJSONFile_1() throws JSONSException, IOSException {		
		Assert.assertNotNull( JSONUtils.loadJSONFile( "e4b_qa.json" ));
	}
	
	/** The File not exists */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONFile_2() throws JSONSException, IOSException {		
		JSONUtils.loadJSONFile( "not exists.json" );
	}
	
	/** The File is null */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONFile_3() throws JSONSException, IOSException {		
		JSONUtils.loadJSONFile( null );
	}
	
	/** The File is malformed */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONFile_4() throws JSONSException, IOSException {		
		JSONUtils.loadJSONFile( "e4b_qa_malformed.json" );
	}
	
	/** The folder exists and the File exists */
	@Test()
	public void loadJSONFile_5() throws JSONSException, IOSException {		
		Assert.assertNotNull( JSONUtils.loadJSONFile( "examples", "e4b_qa.json" ));
	}
	
	/** The folder exists and the File not exists */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONFile_6() throws JSONSException, IOSException {		
		JSONUtils.loadJSONFile( "examples", "not exists.json" );
	}
	
	/** The folder exists and the folder exists and the File is null */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONFile_7() throws JSONSException, IOSException {		
		JSONUtils.loadJSONFile( "examples", null );
	}
	
	/** The folder exists and the File is malformed */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONFile_8() throws JSONSException, IOSException {		
		JSONUtils.loadJSONFile( "examples", "e4b_qa_malformed.json" );
	}
	
	/** The folder not exists and the File is valid */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONFile_9() throws JSONSException, IOSException {		
		JSONUtils.loadJSONFile( "not exists/", "e4b_qa.json" );
	}
	
	/** The folder is null and the File is valid */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONFile_10() throws JSONSException, IOSException {		
		JSONUtils.loadJSONFile( null, "e4b_qa.json" );
	}
	
	/** The resource exists */
	@Test()
	public void saveJSONResource_1() throws JSONSException, IOSException {		
		JSONObject jo = JSONUtils.loadJSONResource( "e4b_qa.json" );
		JSONUtils.saveJSONResource( jo, "e4b_qa.json" );
	}
	
	/** The resource not exists */
	@Test()
	public void saveJSONResource_2() throws JSONSException, IOSException {		
		JSONObject jo = JSONUtils.loadJSONResource( "e4b_qa.json" );
		JSONUtils.saveJSONResource( jo, "new.json" );
	}
	
	/** The resource is null */
	@Test( expectedExceptions = JSONSException.class )
	public void saveJSONResource_3() throws JSONSException, IOSException {		
		JSONUtils.saveJSONResource( new JSONObject(), null );
	}
	
	/** The folder exists and the resource exists */
	@Test()
	public void saveJSONResource_5() throws JSONSException, IOSException {		
		JSONObject jo = JSONUtils.loadJSONResource( "e4b_qa.json" );
		JSONUtils.saveJSONResource( jo, "examples", "e4b_qa.json" );
	}
	
	/** The folder exists and the resource not exists */
	@Test()
	public void saveJSONResource_6() throws JSONSException, IOSException {		
		JSONObject jo = JSONUtils.loadJSONResource( "e4b_qa.json" );
		JSONUtils.saveJSONResource( jo, "examples", "new.json" );
	}
	
	/** The folder exists and the resource is null */
	@Test( expectedExceptions = JSONSException.class )
	public void saveJSONResource_7() throws JSONSException, IOSException {		
		JSONUtils.saveJSONResource( new JSONObject(), "examples", null );
	}
	
	/** The folder not exists and the resource is valid */
	@Test()
	public void saveJSONResource_9() throws JSONSException, IOSException {		
		JSONUtils.saveJSONResource( new JSONObject(), "new/", "e4b_qa.json" );
	}
	
	/** The folder is null and the resource is valid */
	@Test( expectedExceptions = JSONSException.class )
	public void saveJSONResource_10() throws JSONSException, IOSException {		
		JSONUtils.saveJSONResource( new JSONObject(), null, "e4b_qa.json" );
	}
	
	/** The resource exists */
	@Test()
	public void saveJSONFile_1() throws JSONSException, IOSException {		
		JSONObject jo = JSONUtils.loadJSONResource( "e4b_qa.json" );
		JSONUtils.saveJSONFile( jo, "e4b_qa.json" );
	}
	
	/** The File not exists */
	@Test()
	public void saveJSONFile_2() throws JSONSException, IOSException {		
		JSONObject jo = JSONUtils.loadJSONResource( "e4b_qa.json" );
		JSONUtils.saveJSONFile( jo, "new.json" );
	}
	
	/** The File is null */
	@Test( expectedExceptions = JSONSException.class )
	public void saveJSONFile_3() throws JSONSException, IOSException {		
		JSONUtils.saveJSONFile( new JSONObject(), null );
	}
	
	/** The folder exists and the File exists */
	@Test()
	public void saveJSONFile_5() throws JSONSException, IOSException {		
		JSONObject jo = JSONUtils.loadJSONResource( "e4b_qa.json" );
		JSONUtils.saveJSONFile( jo, "examples", "e4b_qa.json" );
	}
	
	/** The folder exists and the File not exists */
	@Test()
	public void saveJSONFile_6() throws JSONSException, IOSException {		
		JSONObject jo = JSONUtils.loadJSONResource( "e4b_qa.json" );
		JSONUtils.saveJSONFile( jo, "examples", "new.json" );
	}
	
	/** The folder exists and the File is null */
	@Test( expectedExceptions = JSONSException.class )
	public void saveJSONFile_7() throws JSONSException, IOSException {		
		JSONUtils.saveJSONFile( new JSONObject(), "examples", null );
	}
	
	/** The folder not exists and the File is valid */
	@Test()
	public void saveJSONFile_9() throws JSONSException, IOSException {		
		JSONObject jo = JSONUtils.loadJSONResource( "e4b_qa.json" );
		JSONUtils.saveJSONFile( jo, "new/", "e4b_qa.json" );
	}
	
	/** The folder is null and the File is valid */
	@Test( expectedExceptions = JSONSException.class )
	public void saveJSONFile_10() throws JSONSException, IOSException {		
		JSONUtils.saveJSONFile( new JSONObject(), null, "e4b_qa.json" );
	}
}
