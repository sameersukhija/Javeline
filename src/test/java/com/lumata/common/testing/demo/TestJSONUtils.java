package com.lumata.common.testing.demo;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;


public class TestJSONUtils {

	private static final Logger logger = LoggerFactory.getLogger( TestJSONUtils.class );
	
	Environment env;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment"})
	@BeforeClass
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
						
	}
	
	@Parameters({"tenant"})
	@Test()
	public void createDAO( @Optional("qa") String tenant ) throws JSONSException, IOFileException  {
		
		JSONObject json = JSONUtils.loadJSONResource( "input/environments" , "e4o_qa.json" ); 
				
		System.out.println( json );
				
	}		
	
	/** CHECK RESOURCE */
	/** The resource exists */
	@Test()
	public void loadJSONResource_01() throws JSONSException, IOFileException {		
		Assert.assertNotNull( JSONUtils.loadJSONResource( "input/environments", "e4b_qa.json" ));
	}
	
	/** The resource not exists */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONResource_02() throws JSONSException, IOFileException {		
		JSONUtils.loadJSONResource( "input/environments", "not_exists.json" );
	}
	
	/** The resource is null */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONResource_03() throws JSONSException, IOFileException {		
		JSONUtils.loadJSONResource( null );
	}
	
	/** The resource is malformed */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONResource_04() throws JSONSException, IOFileException {		
		JSONUtils.loadJSONResource( "e4b_qa_malformed.json" );
	}
	
	/** The folder exists and the resource exists */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONResource_05() throws JSONSException, IOFileException {		
		Assert.assertNotNull( JSONUtils.loadJSONResource( "path/not/exist", "e4b_qa.json" ));
	}
	
	/** The folder exists and the resource not exists */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONResource_06() throws JSONSException, IOFileException {		
		JSONUtils.loadJSONResource( "examples", "not exists.json" );
	}
	
	/** The folder exists and the folder exists and the resource is null */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONResource_07() throws JSONSException, IOFileException {		
		JSONUtils.loadJSONResource( "examples", null );
	}
	
	/** The folder exists and the resource is malformed */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONResource_08() throws JSONSException, IOFileException {		
		JSONUtils.loadJSONResource( "examples", "e4b_qa_malformed.json" );
	}
	
	/** The folder not exists and the resource is valid */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONResource_09() throws JSONSException, IOFileException {		
		JSONUtils.loadJSONResource( "not exists/", "e4b_qa.json" );
	}
	
	/** The folder is null and the resource is valid */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONResource_10() throws JSONSException, IOFileException {		
		JSONUtils.loadJSONResource( null, "e4b_qa.json" );
	}
	
	@Test()
	public void loadJSONResource_11() throws JSONSException, IOFileException {		
		Assert.assertNotNull( JSONUtils.loadJSONResource( "test_special.json" ));
	}	
	
	/** The resource exists */
	@Test(enabled=false)
	public void loadJSONFile_01() throws JSONSException, IOFileException {		
		Assert.assertNotNull( JSONUtils.loadJSONFile( "./target/test-classes/test_special.json" ));
	}
	
	/** The File not exists */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONFile_02() throws JSONSException, IOFileException {		
		JSONUtils.loadJSONFile( "not exists.json" );
	}
	
	/** The File is null */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONFile_03() throws JSONSException, IOFileException {		
		JSONUtils.loadJSONFile( null );
	}
	
	/** The File is malformed */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONFile_04() throws JSONSException, IOFileException {		
		JSONUtils.loadJSONFile( "e4b_qa_malformed.json" );
	}
	
	/** The folder exists and the File exists */
	@Test(enabled=false)
	public void loadJSONFile_05() throws JSONSException, IOFileException {		
		Assert.assertNotNull( JSONUtils.loadJSONFile( "./target/test-classes/input/environments", "e4b_qa.json" ));
	}
	
	/** The folder exists and the File not exists */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONFile_06() throws JSONSException, IOFileException {		
		JSONUtils.loadJSONFile( "examples", "not exists.json" );
	}
	
	/** The folder exists and the folder exists and the File is null */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONFile_07() throws JSONSException, IOFileException {		
		JSONUtils.loadJSONFile( "examples", null );
	}
	
	/** The folder exists and the File is malformed */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONFile_08() throws JSONSException, IOFileException {		
		JSONUtils.loadJSONFile( "examples", "e4b_qa_malformed.json" );
	}
	
	/** The folder not exists and the File is valid */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONFile_09() throws JSONSException, IOFileException {		
		JSONUtils.loadJSONFile( "not exists/", "e4b_qa.json" );
	}
	
	/** The folder is null and the File is valid */
	@Test( expectedExceptions = JSONSException.class )
	public void loadJSONFile_10() throws JSONSException, IOFileException {		
		JSONUtils.loadJSONFile( null, "e4b_qa.json" );
	}
	
	/** The resource exists */
	@Test(enabled=false)
	public void saveJSONResource_01() throws JSONSException, IOFileException {		
		JSONObject jo = JSONUtils.loadJSONResource( "e4b_qa.json" );
		JSONUtils.saveJSONResource( jo, "e4b_qa.json" );
	}
	
	/** The resource not exists */
	@Test(enabled=false)
	public void saveJSONResource_02() throws JSONSException, IOFileException {		
		JSONObject jo = JSONUtils.loadJSONResource( "e4b_qa.json" );
		JSONUtils.saveJSONResource( jo, "new.json" );
	}
	
	/** The resource is null */
	@Test( expectedExceptions = JSONSException.class )
	public void saveJSONResource_03() throws JSONSException, IOFileException {		
		JSONUtils.saveJSONResource( new JSONObject(), null );
	}
	
	/** The folder exists and the resource exists */
	@Test(enabled=false)
	public void saveJSONResource_05() throws JSONSException, IOFileException {		
		JSONObject jo = JSONUtils.loadJSONResource( "e4b_qa.json" );
		JSONUtils.saveJSONResource( jo, "examples", "e4b_qa.json" );
	}
	
	/** The folder exists and the resource not exists */
	@Test(enabled=false)
	public void saveJSONResource_06() throws JSONSException, IOFileException {		
		JSONObject jo = JSONUtils.loadJSONResource( "e4b_qa.json" );
		JSONUtils.saveJSONResource( jo, "examples", "new.json" );
	}
	
	/** The folder exists and the resource is null */
	@Test( expectedExceptions = JSONSException.class, enabled=false )
	public void saveJSONResource_07() throws JSONSException, IOFileException {		
		JSONUtils.saveJSONResource( new JSONObject(), "examples", null );
	}
	
	/** The folder not exists and the resource is valid */
	@Test()
	public void saveJSONResource_09() throws JSONSException, IOFileException {		
		JSONUtils.saveJSONResource( new JSONObject(), "new/", "saved.json" );
	}
	
	/** The folder is null and the resource is valid */
	@Test( expectedExceptions = JSONSException.class )
	public void saveJSONResource_10() throws JSONSException, IOFileException {		
		JSONUtils.saveJSONResource( new JSONObject(), null, "e4b_qa.json" );
	}
	
	/** The resource exists */
	@Test()
	public void saveJSONFile_01() throws JSONSException, IOFileException {		
		JSONObject jo = JSONUtils.loadJSONResource( "test_special.json" );
		JSONUtils.saveJSONFile( jo, "just_saved.json" );
	}
	
	/** The File not exists */
	@Test( expectedExceptions = JSONSException.class )
	public void saveJSONFile_02() throws JSONSException, IOFileException {		
		JSONObject jo = JSONUtils.loadJSONResource( "e4b_never_ending_story.json" );
		JSONUtils.saveJSONFile( jo, "new.json" );
	}
	
	/** The File is null */
	@Test( expectedExceptions = JSONSException.class )
	public void saveJSONFile_03() throws JSONSException, IOFileException {		
		JSONUtils.saveJSONFile( new JSONObject(), null );
	}
	
	/** The folder exists and the File exists */
	@Test()
	public void saveJSONFile_04() throws JSONSException, IOFileException {		
		JSONObject jo = JSONUtils.loadJSONResource( "test_special.json" );
		JSONUtils.saveJSONFile( jo, "target/test-classes/", "e4b_qa.json" );
	}
	
	/** The folder exists and the File not exists */
	@Test(enabled=false)
	public void saveJSONFile_05() throws JSONSException, IOFileException {		
		JSONObject jo = JSONUtils.loadJSONResource( "e4b_qa.json" );
		JSONUtils.saveJSONFile( jo, "target/test-classes/", "new.json" );
	}
	
	/** The folder exists and the File is null */
	@Test( expectedExceptions = JSONSException.class )
	public void saveJSONFile_06() throws JSONSException, IOFileException {		
		JSONUtils.saveJSONFile( new JSONObject(), "examples", null );
	}
	
	/** The folder not exists and the File is valid */
	@Test(enabled=false)
	public void saveJSONFile_07() throws JSONSException, IOFileException {		
		JSONObject jo = JSONUtils.loadJSONResource( "e4b_qa.json" );
		JSONUtils.saveJSONFile( jo, "new/", "e4b_qa.json" );
	}
	
	/** The folder is null and the File is valid */
	@Test( expectedExceptions = JSONSException.class )
	public void saveJSONFile_08() throws JSONSException, IOFileException {		
		JSONUtils.saveJSONFile( new JSONObject(), null, "e4b_qa.json" );
	}	
	
}
