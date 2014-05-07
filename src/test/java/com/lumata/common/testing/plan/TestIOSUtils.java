package com.lumata.common.testing.plan;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;


public class TestIOSUtils {
	
//	private static final  Logger logger = LoggerFactory.getLogger( TestIOSUtils.class );
	
	/** CHECK RESOURCE */
	/** The path is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void buildResourcePath_01() throws IOFileException {		
		IOFileUtils.buildResourcePath( "" );		
	}
	
	/** The path is null */
	@Test(expectedExceptions = IOFileException.class)
	public void buildResourcePath_02() throws IOFileException {		
		IOFileUtils.buildResourcePath( null );		
	}
	
	/** The resource has not a valid format */
	@Test(expectedExceptions = IOFileException.class)
	public void buildResourcePath_03() throws IOFileException {		
		IOFileUtils.buildResourcePath( "a." );		
	}
	
	/** The resource has not a valid format */
	@Test(expectedExceptions = IOFileException.class)
	public void buildResourcePath_04() throws IOFileException {		
		IOFileUtils.buildResourcePath( "." );		
	}
	
	/** The resource has not a valid format */
	@Test(expectedExceptions = IOFileException.class)
	public void buildResourcePath_05() throws IOFileException {		
		IOFileUtils.buildResourcePath( ".a" );		
	}
	
	/** The resource has not a valid format 
	 *  Note: all characters are valid except /"'?$&*| */
	@Test(expectedExceptions = IOFileException.class)
	public void buildResourcePath_06() throws IOFileException {		
		IOFileUtils.buildResourcePath( "/.txt" );
	}
	
	/** The resource has not a valid format 
	 *  Note: all characters are valid except /"'?$&*| */
	@Test(expectedExceptions = IOFileException.class)
	public void buildResourcePath_07() throws IOFileException {		
		IOFileUtils.buildResourcePath( "\".txt" );		
	}
	
	/** The resource has not a valid format 
	 *  Note: all characters are valid except /"'?$&*| */
	@Test(expectedExceptions = IOFileException.class)
	public void buildResourcePath_08() throws IOFileException {		
		IOFileUtils.buildResourcePath( "*.txt" );		
	}
	
	/** The resource has not a valid format 
	 *  Note: all characters are valid except /"'?$&*| */
	@Test(expectedExceptions = IOFileException.class)
	public void buildResourcePath_09() throws IOFileException {		
		IOFileUtils.buildResourcePath( "folder/file.txt" );		
	}
	
	/** The resource exists and is valid
	 *  Note: "src/main/resources/lumata-common-testing/" is the default resource root */
	@Test(enabled=false)
	public void buildResourcePath_10() throws IOFileException {		
		String resource = "1! #°a()ç@.txt";
		Assert.assertEquals(IOFileUtils.buildResourcePath( resource ), "lumata-common-testing/" + resource);
	}
		
	/** The folder is empty and the resource has a valid format 
	 *  Note: all characters are valid except /"'?$&*| 
	 *  	  "src/main/resources/lumata-common-testing/" is the default resource root 
	 */
	@Test(enabled=false)
	public void buildResourcePath_11() throws IOFileException {		
		Assert.assertEquals( IOFileUtils.buildResourcePath( "", "file.txt" ), "lumata-common-testing/file.txt" );		
	}
	
	/** The folder is null and the resource has a valid format 
	 *  Note: all characters are valid except /"'?$&*| 
	 *  	  "src/main/resources/lumata-common-testing/" is the default resource root 
	 */
	@Test(expectedExceptions = IOFileException.class)
	public void buildResourcePath_12() throws IOFileException {		
		IOFileUtils.buildResourcePath( null, "file.txt" );		
	}
	
	/** The folder is valid and the resource is empty 
	 *  Note: all characters are valid except /"'?$&*| 
	 *  	  "src/main/resources/lumata-common-testing/" is the default resource root 
	 */
	@Test(expectedExceptions = IOFileException.class)
	public void buildResourcePath_13() throws IOFileException {		
		IOFileUtils.buildResourcePath( "tmp/", "" );		
	}
	
	/** The folder is valid and the resource is null 
	 *  Note: all characters are valid except /"'?$&*| 
	 *  	  "src/main/resources/lumata-common-testing/" is the default resource root 
	 */
	@Test(expectedExceptions = IOFileException.class)
	public void buildResourcePath_14() throws IOFileException {		
		IOFileUtils.buildResourcePath( "tmp/", null );		
	}
	
	/** The folder and the resource are corrects 
	 *  Note: all characters are valid except /"'?$&*| 
	 *  	  "src/main/resources/lumata-common-testing/" is the default resource root 
	 */
	@Test(enabled=false)
	public void buildResourcePath_15() throws IOFileException {		
		Assert.assertEquals( IOFileUtils.buildResourcePath( "tmp/", "sample.properties" ), "lumata-common-testing/tmp/sample.properties" ) ;		
	}
	
	/** The resource exists */
	@Test()
	public void loadResourceAsInputStream_01() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadResourceAsInputStream( "log4j.properties" ));
	}
	
	/** The resource not exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsInputStream_02() throws IOFileException {		
		IOFileUtils.loadResourceAsInputStream( "not exist.properties" );
	}
	
	/** The resource is null */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsInputStream_03() throws IOFileException {		
		String resource = null;
		IOFileUtils.loadResourceAsInputStream(resource);
	}
	
	/** The folder and the resource exist */
	@Test()
	public void loadResourceAsInputStream_04() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadResourceAsInputStream( "input/environments", "e4b_qa.json" ));
	}
	
	/** The folder not exist and the resource exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsInputStream_05() throws IOFileException {		
		IOFileUtils.loadResourceAsInputStream( "not exist", "e4b_qa.json" );
	}
	
	/** The folder exists and the resource not exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsInputStream_06() throws IOFileException {		
		IOFileUtils.loadResourceAsInputStream( "examples", "not exist.json" );
	}
	
	/** The folder is null and the resource exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsInputStream_07() throws IOFileException {		
		IOFileUtils.loadResourceAsInputStream( null, "e4b_qa.json" );
	}
	
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsInputStream_08() throws IOFileException {		
		IOFileUtils.loadResourceAsInputStream( "examples", null );
	}
	
	/** The folder exists and the resource exists */	
	@Test(enabled=false)
	public void loadResourceAsInputStream_09() throws IOFileException {		
		IOFileUtils.loadResourceAsInputStream( "target/test-classes", "log4j.properties" );
	}	
		
	/** The resource exists */
	@Test()
	public void loadResourceAsInputStreamReader_01() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadResourceAsInputStreamReader( "log4j.properties" ));
	}
	
	/** The resource not exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsInputStreamReader_02() throws IOFileException {		
		IOFileUtils.loadResourceAsInputStreamReader( "/not/exist.properties" );
	}
	
	/** The resource is null */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsInputStreamReader_03() throws IOFileException {		
		IOFileUtils.loadResourceAsInputStreamReader( null );
	}
	
	/** The folder and the resource exist */
	@Test()
	public void loadResourceAsInputStreamReader_04() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadResourceAsInputStreamReader( "input/environments", "e4b_qa.json" ));
	}
	
	/** The folder not exist and the resource exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsInputStreamReader_05() throws IOFileException {		
		IOFileUtils.loadResourceAsInputStreamReader( "not exist", "e4b_qa.json" );
	}
	
	/** The folder exists and the resource not exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsInputStreamReader_06() throws IOFileException {		
		IOFileUtils.loadResourceAsInputStreamReader( "examples", "not exist.json" );
	}
	
	/** The folder is null and the resource exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsInputStreamReader_07() throws IOFileException {		
		IOFileUtils.loadResourceAsInputStreamReader( null, "e4b_qa.json" );
	}
	
	/** The folder exists and the resource exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsInputStreamReader_08() throws IOFileException {		
		IOFileUtils.loadResourceAsInputStreamReader( "examples", null );
	}
	
	/** The resource exists */
	@Test()
	public void loadResourceAsString_01() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadResourceAsString( "log4j.properties" ));
	}
	
	/** The resource not exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsString_02() throws IOFileException {		
		IOFileUtils.loadResourceAsString( "not exist.properties" );
	}
	
	/** The resource is null */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsString_03() throws IOFileException {		
		IOFileUtils.loadResourceAsString( null );
	}
	
	/** The folder and the resource exist */
	@Test()
	public void loadResourceAsString_04() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadResourceAsString( "input/environments", "e4b_qa.json" ));
	}
	
	/** The folder not exist and the resource exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsString_05() throws IOFileException {		
		IOFileUtils.loadResourceAsString( "not exist", "e4b_qa.json" );
	}
	
	/** The folder exists and the resource not exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsString_06() throws IOFileException {		
		IOFileUtils.loadResourceAsString( "examples", "not exist.json" );
	}
	
	/** The folder is null and the resource exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsString_07() throws IOFileException {		
		IOFileUtils.loadResourceAsString( null, "e4b_qa.json" );
	}
	
	/** The folder exists and the resource exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsString_08() throws IOFileException {		
		IOFileUtils.loadResourceAsString( "examples", null );
	}
	
	/** The resource exists */
	@Test()
	public void loadResourceAsBufferedReader_01() throws IOFileException {		
		String resource = "log4j.properties";
		Assert.assertNotNull( IOFileUtils.loadResourceAsBufferedReader(resource));
	}
	
	/** The resource not exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsBufferedReader_02() throws IOFileException {		
		String resource = "/not/exist.properties";
		IOFileUtils.loadResourceAsBufferedReader(resource);
	}
	
	/** The resource is null */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsBufferedReader_03() throws IOFileException {		
		String resource = null;
		IOFileUtils.loadResourceAsBufferedReader(resource);
	}
	
	/** The folder and the resource exist */
	@Test()
	public void loadResourceAsBufferedReader_04() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadResourceAsBufferedReader( "input/environments", "e4b_qa.json" ));
	}
	
	/** The folder not exist and the resource exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsBufferedReader_05() throws IOFileException {		
		IOFileUtils.loadResourceAsBufferedReader( "not exist", "e4b_qa.json" );
	}
	
	/** The folder exists and the resource not exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsBufferedReader_06() throws IOFileException {		
		IOFileUtils.loadResourceAsBufferedReader( "examples", "not exist.json" );
	}
	
	/** The folder is null and the resource exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsBufferedReader_07() throws IOFileException {		
		IOFileUtils.loadResourceAsBufferedReader( null, "e4b_qa.json" );
	}
	
	/** The folder exists and the resource exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsBufferedReader_08() throws IOFileException {		
		IOFileUtils.loadResourceAsBufferedReader( "examples", null );
	}
	
	/** CHECK FILE */
	/** The file is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void buildPath_01() throws IOFileException {		
		IOFileUtils.buildPath( "" );		
	}
	
	/** The file is null */
	@Test(expectedExceptions = IOFileException.class)
	public void buildPath_02() throws IOFileException {		
		IOFileUtils.buildPath( null );		
	}
	
	/** The file has not a valid format */
	@Test(expectedExceptions = IOFileException.class)
	public void buildPath_03() throws IOFileException {		
		IOFileUtils.buildPath( "a/b.txt" );		
	}
	
	/** The file has a valid format */
	@Test()
	public void buildPath_04() throws IOFileException {		
		Assert.assertEquals( IOFileUtils.buildPath( "file!ç°;@ù.txt" ), "file!ç°;@ù.txt" );		
	}
	
	/** The folder is empty */
	@Test()
	public void buildPath_05() throws IOFileException {		
		Assert.assertEquals( IOFileUtils.buildPath( "", "file.txt" ), "file.txt" );		
	}
	
	/** The file is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void buildPath_06() throws IOFileException {		
		IOFileUtils.buildPath( "folder/", "" );		
	}
	
	/** The folder is null */
	@Test(expectedExceptions = IOFileException.class)
	public void buildPath_07() throws IOFileException {		
		IOFileUtils.buildPath( null, "file.txt" );		
	}
	
	/** The file is null */
	@Test(expectedExceptions = IOFileException.class)
	public void buildPath_08() throws IOFileException {		
		IOFileUtils.buildPath( "folder", null );		
	}
	
	/** The folder is equals to / */
	@Test()
	public void buildPath_09() throws IOFileException {		
		Assert.assertEquals( IOFileUtils.buildPath( "/", "file.txt" ), "/file.txt" );	
	}
	
	/** The folder is empty and the file is equals to /file.txt 
	 *  Note: in this case the file format is not correct because contains the / character */
	@Test(expectedExceptions = IOFileException.class)
	public void buildPath_10() throws IOFileException {		
		IOFileUtils.buildPath( "", "/file.txt" );	
	}
	
	/** The folder not have the / character */
	@Test()
	public void buildPath_11() throws IOFileException {		
		Assert.assertEquals( IOFileUtils.buildPath( "folder", "file.txt" ), "folder/file.txt" );	
	}
	
	/** The file exist */
	@Test()
	public void loadFileAsInputStream_01() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStream( "sample.properties" ));
	}
	
	/** The file is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsInputStream_02() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStream( "" ));
	}
	
	/** The file is null */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsInputStream_03() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStream( null ));
	}
	
	/** The file is not valid */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsInputStream_04() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStream( "." ));
	}
	
	/** The folder and file exist */
	@Test(enabled=false)
	public void loadFileAsInputStream_05() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStream( "src/test/resources", "log4j.properties" ));
	}
	
	/** The folder not exists and file exist */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsInputStream_06() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStream( "wrong", "sample.properties" ));
	}
	
	/** The folder and file exist */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsInputStream_07() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStream( "src/test/resources", "wrong.properties" ));
	}
	
	/** The file exist */
	@Test()
	public void loadFileAsInputStreamReader_01() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStreamReader( "sample.properties" ));
	}
	
	/** The file is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsInputStreamReader_02() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStreamReader( "" ));
	}
	
	/** The file is null */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsInputStreamReader_03() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStreamReader( null ));
	}
	
	/** The file is not valid */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsInputStreamReader_04() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStreamReader( "." ));
	}
	
	/** The folder and file exist */
	@Test(enabled=false)
	public void loadFileAsInputStreamReader_05() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStreamReader( "src/test/resources", "log4j.properties" ));
	}
	
	/** The folder not exists and file exist */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsInputStreamReader_06() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStreamReader( "wrong", "sample.properties" ));
	}
	
	/** The folder and file exist */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsInputStreamReader_07() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStreamReader( "/tmp", "wrong.properties" ));
	}
			
	/** The file exist */
	@Test()
	public void loadFileAsString_01() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsString( "sample.properties" ));
	}
	
	/** The file is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsString_02() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsString( "" ));
	}
	
	/** The file is null */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsString_03() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsString( null ));
	}
	
	/** The file is not valid */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsString_04() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsString( "." ));
	}
	
	/** The folder and file exist */
	@Test(enabled=false)
	public void loadFileAsString_05() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsString( "src/test/resources", "log4j.properties" ));
	}
	
	/** The folder not exists and file exist */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsString_06() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsString( "wrong", "sample.properties" ));
	}
	
	/** The folder and file exist */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsString_07() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsString( "/tmp", "wrong.properties" ));
	}
	
	/** The file exist */
	@Test()
	public void loadFileAsBufferedReader_01() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsBufferedReader( "sample.properties" ));
	}
	
	/** The file is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsBufferedReader_02() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsBufferedReader( "" ));
	}
	
	/** The file is null */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsBufferedReader_03() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsBufferedReader( null ));
	}
	
	/** The file is not valid */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsBufferedReader_04() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsBufferedReader( "." ));
	}
	
	/** The folder and file exist */
	@Test(enabled=false)
	public void loadFileAsBufferedReader_05() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsBufferedReader( "src/test/resources", "log4j.properties" ));
	}
	
	/** The folder not exists and file exist */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsBufferedReader_06() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsBufferedReader( "wrong", "sample.properties" ));
	}
	
	/** The folder and file exist */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsBufferedReader_07() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsBufferedReader( "/tmp", "wrong.properties" ));
	}
		
	/** The file is valid and has not a parent directory */
	@Test()
	public void createPath_01() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.createPath( "sample.properties" ));
	}
	
	/** The file is not valid because contains a folder */
	@Test(expectedExceptions = IOFileException.class)
	public void createPath_02() throws IOFileException {		
		IOFileUtils.createPath( "/tmp/sample.properties" );
	}
	
	/** The folder and the file are valid */
	@Test()
	public void createPath_03() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.createPath( "src/test/resources", "sample.properties" ));
	}
	
	/** The folder is empty and the file is valid */
	@Test()
	public void createPath_04() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.createPath( "", "sample.properties" ));
	}
	
	/** The folder is null and the file is valid */
	@Test(expectedExceptions = IOFileException.class)
	public void createPath_05() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.createPath( null, "sample.properties" ));
	}
	
	/** The folder is valid and the file is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void createPath_06() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.createPath( "./target/tmp", "" ));
	}
	
	/** The folder is valid and the file is null */
	@Test(expectedExceptions = IOFileException.class)
	public void createPath_07() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.createPath( "./target/tmp", null ));
	}
	
	/** The folder is valid and the file has not a valid format */
	@Test(expectedExceptions = IOFileException.class)
	public void createPath_08() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.createPath( "./target/tmp", "." ));
	}
	
	/** The folder is not valid and the file has a valid format */
	@Test()
	public void createPath_09() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.createPath( "./target/tmp", "sample.properties" ));
	}
	
	/** The content and the resource are valid */
	@Test()
	public void saveResource_01() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveResource( content, "save.properties" );
	}
	
	/** The content is empty and the resource is valid */
	@Test()
	public void saveResource_02() throws IOFileException {		
		String content = "";
		IOFileUtils.saveResource( content, "save_empty.properties" );
	}
	
	/** The content is null and the resource is valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResource_03() throws IOFileException {		
		String content = null;
		IOFileUtils.saveResource( content, "save.properties" );
	}
	
	/** The content is valid and the resource is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResource_04() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveResource( content, "" );
	}
	
	/** The content is valid and the resource is null */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResource_05() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveResource( content, null );
	}
	
	/** The content is valid and the resource is not valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResource_06() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveResource( content, "." );
	}
	
	/** The content, folder and resource are valid */
	@Test()
	public void saveResource_07() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveResource( content, "examples", "save.properties" );
	}
	
	/** The content is null and the folder and resource are valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResource_08() throws IOFileException {		
		String content = null;
		IOFileUtils.saveResource( content, "examples", "save.properties" );
	}
	
	/** The folder is null and the content and resource are valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResource_09() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveResource( content, null, "save.properties" );
	}
	
	/** The content and folder are valid and the resource is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResource_10() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveResource( content, "examples", "" );
	}
	
	/** The content is valid and the resource is null */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResource_11() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveResource( content, "examples", null );
	}
	
	/** The content is valid and the resource is not valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResource_12() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveResource( content, "examples", "." );
	}
	
	/** The content and the resource are valid and create a new folder */
	@Test()
	public void saveResource_13() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveResource( content, "examples2", "save.properties" );
	}
	
	/** The content and the resource are valid */
	@Test()
	public void saveResourceBuffer_01() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveResource( content, "save_buffer.properties" );
	}
	
	/** The content is empty and the resource is valid */
	@Test()
	public void saveResourceBuffer_02() throws IOFileException {		
		StringBuffer content = new StringBuffer("");
		IOFileUtils.saveResource( content, "save_buffer_empty.properties" );
	}
	
	/** The content is null and the resource is valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResourceBuffer_03() throws IOFileException {		
		StringBuffer content = null;
		IOFileUtils.saveResource( content, "save_buffer.properties" );
	}
	
	/** The content is valid and the resource is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResourceBuffer_04() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveResource( content, "" );
	}
	
	/** The content is valid and the resource is null */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResourceBuffer_05() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveResource( content, null );
	}
	
	/** The content is valid and the resource is not valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResourceBuffer_06() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveResource( content, "." );
	}
	
	/** The content, folder and resource are valid */
	@Test()
	public void saveResourceBuffer_07() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveResource( content, "examples", "save_buffer.properties" );
	}
	
	/** The content is null and the folder and resource are valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResourceBuffer_08() throws IOFileException {		
		StringBuffer content = null;
		IOFileUtils.saveResource( content, "examples", "save_buffer.properties" );
	}
	
	/** The folder is null and the content and resource are valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResourceBuffer_09() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveResource( content, null, "save_buffer.properties" );
	}
	
	/** The content and folder are valid and the resource is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResourceBuffer_10() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveResource( content, "examples", "" );
	}
	
	/** The content is valid and the resource is null */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResourceBuffer_11() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveResource( content, "examples", null );
	}
	
	/** The content is valid and the resource is not valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResourceBuffer_12() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveResource( content, "examples", "." );
	}
	
	/** The content and the resource are valid and create a new folder */
	@Test()
	public void saveResourceBuffer_13() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveResource( content, "examples2", "save_buffer.properties" );
	}
	
	/** The content and the resource are valid */
	@Test()
	public void saveFile_01() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveFile( content, "save_file.properties" );
	}
	
	/** The content is empty and the File is valid */
	@Test()
	public void saveFile_02() throws IOFileException {		
		String content = "";
		IOFileUtils.saveFile( content, "save_file_empty.properties" );
	}
	
	/** The content is null and the File is valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFile_03() throws IOFileException {		
		String content = null;
		IOFileUtils.saveFile( content, "save_file.properties" );
	}
	
	/** The content is valid and the File is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFile_04() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveFile( content, "" );
	}
	
	/** The content is valid and the File is null */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFile_05() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveFile( content, null );
	}
	
	/** The content is valid and the File is not valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFile_06() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveFile( content, "." );
	}
	
	/** The content, folder and File are valid */
	@Test()
	public void saveFile_07() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveFile( content, "examples", "save_file.properties" );
	}
	
	/** The content is null and the folder and File are valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFile_08() throws IOFileException {		
		String content = null;
		IOFileUtils.saveFile( content, "examples", "save_file.properties" );
	}
	
	/** The folder is null and the content and File are valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFile_09() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveFile( content, null, "save_file.properties" );
	}
	
	/** The content and folder are valid and the File is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFile_10() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveFile( content, "examples", "" );
	}
	
	/** The content is valid and the File is null */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFile_11() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveFile( content, "examples", null );
	}
	
	/** The content is valid and the File is not valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFile_12() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveFile( content, "examples", "." );
	}
	
	/** The content and the File are valid and create a new folder */
	@Test()
	public void saveFile_13() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveFile( content, "examples2", "save_file.properties" );
	}
	
	/** The content and the File are valid */
	@Test()
	public void saveFileBuffer_01() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveFile( content, "save_file_buffer.properties" );
	}
	
	/** The content is empty and the File is valid */
	@Test()
	public void saveFileBuffer_02() throws IOFileException {		
		StringBuffer content = new StringBuffer("");
		IOFileUtils.saveFile( content, "save_file_buffer_empty.properties" );
	}
	
	/** The content is null and the File is valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFileBuffer_03() throws IOFileException {		
		StringBuffer content = null;
		IOFileUtils.saveFile( content, "save_file_buffer.properties" );
	}
	
	/** The content is valid and the File is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFileBuffer_04() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveFile( content, "" );
	}
	
	/** The content is valid and the File is null */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFileBuffer_05() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveFile( content, null );
	}
	
	/** The content is valid and the File is not valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFileBuffer_06() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveFile( content, "." );
	}
	
	/** The content, folder and File are valid */
	@Test()
	public void saveFileBuffer_07() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveFile( content, "examples", "save_file_buffer.properties" );
	}
	
	/** The content is null and the folder and File are valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFileBuffer_08() throws IOFileException {		
		StringBuffer content = null;
		IOFileUtils.saveFile( content, "examples", "save_file_buffer.properties" );
	}
	
	/** The folder is null and the content and File are valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFileBuffer_09() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveFile( content, null, "save_file_buffer.properties" );
	}
	
	/** The content and folder are valid and the File is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFileBuffer_10() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveFile( content, "examples", "" );
	}
	
	/** The content is valid and the File is null */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFileBuffer_11() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveFile( content, "examples", null );
	}
	
	/** The content is valid and the File is not valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFileBuffer_12() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveFile( content, "examples", "." );
	}
	
	/** The content and the File are valid and create a new folder */
	@Test()
	public void saveFileBuffer_13() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveFile( content, "examples2", "save_file_buffer.properties" );
	}
	
}
