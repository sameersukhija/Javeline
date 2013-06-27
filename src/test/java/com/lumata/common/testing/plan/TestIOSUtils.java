package com.lumata.common.testing.plan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;


public class TestIOSUtils {
	
	private static final  Logger logger = LoggerFactory.getLogger( TestIOSUtils.class );
	
	/** CHECK RESOURCE */
	/** The path is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void buildResourcePath_1() throws IOFileException {		
		IOFileUtils.buildResourcePath( "" );		
	}
	
	/** The path is null */
	@Test(expectedExceptions = IOFileException.class)
	public void buildResourcePath_2() throws IOFileException {		
		IOFileUtils.buildResourcePath( null );		
	}
	
	/** The resource has not a valid format */
	@Test(expectedExceptions = IOFileException.class)
	public void buildResourcePath_3() throws IOFileException {		
		IOFileUtils.buildResourcePath( "a." );		
	}
	
	/** The resource has not a valid format */
	@Test(expectedExceptions = IOFileException.class)
	public void buildResourcePath_4() throws IOFileException {		
		IOFileUtils.buildResourcePath( "." );		
	}
	
	/** The resource has not a valid format */
	@Test(expectedExceptions = IOFileException.class)
	public void buildResourcePath_5() throws IOFileException {		
		IOFileUtils.buildResourcePath( ".a" );		
	}
	
	/** The resource has not a valid format 
	 *  Note: all characters are valid except /"'?$&*| */
	@Test(expectedExceptions = IOFileException.class)
	public void buildResourcePath_6() throws IOFileException {		
		IOFileUtils.buildResourcePath( "/.txt" );
	}
	
	/** The resource has not a valid format 
	 *  Note: all characters are valid except /"'?$&*| */
	@Test(expectedExceptions = IOFileException.class)
	public void buildResourcePath_7() throws IOFileException {		
		IOFileUtils.buildResourcePath( "\".txt" );		
	}
	
	/** The resource has not a valid format 
	 *  Note: all characters are valid except /"'?$&*| */
	@Test(expectedExceptions = IOFileException.class)
	public void buildResourcePath_8() throws IOFileException {		
		IOFileUtils.buildResourcePath( "*.txt" );		
	}
	
	/** The resource has not a valid format 
	 *  Note: all characters are valid except /"'?$&*| */
	@Test(expectedExceptions = IOFileException.class)
	public void buildResourcePath_9() throws IOFileException {		
		IOFileUtils.buildResourcePath( "folder/file.txt" );		
	}
	
	/** The resource exists and is valid
	 *  Note: "src/main/resources/lumata-common-testing/" is the default resource root */
	@Test()
	public void buildResourcePath_10() throws IOFileException {		
		String resource = "1! #°a()ç@.txt";
		Assert.assertEquals(IOFileUtils.buildResourcePath( resource ), "lumata-common-testing/" + resource);
	}
		
	/** The folder is empty and the resource has a valid format 
	 *  Note: all characters are valid except /"'?$&*| 
	 *  	  "src/main/resources/lumata-common-testing/" is the default resource root 
	 */
	@Test()
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
	@Test()
	public void buildResourcePath_15() throws IOFileException {		
		Assert.assertEquals( IOFileUtils.buildResourcePath( "tmp/", "system.properties" ), "lumata-common-testing/tmp/system.properties" ) ;		
	}
	
	/** The resource exists */
	@Test()
	public void loadResourceAsInputStream_1() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadResourceAsInputStream( "system.properties" ));
	}
	
	/** The resource not exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsInputStream_2() throws IOFileException {		
		IOFileUtils.loadResourceAsInputStream( "not exist.properties" );
	}
	
	/** The resource is null */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsInputStream_3() throws IOFileException {		
		String resource = null;
		IOFileUtils.loadResourceAsInputStream(resource);
	}
	
	/** The folder and the resource exist */
	@Test()
	public void loadResourceAsInputStream_4() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadResourceAsInputStream( "examples", "e4b_qa.json" ));
	}
	
	/** The folder not exist and the resource exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsInputStream_5() throws IOFileException {		
		IOFileUtils.loadResourceAsInputStream( "not exist", "e4b_qa.json" );
	}
	
	/** The folder exists and the resource not exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsInputStream_6() throws IOFileException {		
		IOFileUtils.loadResourceAsInputStream( "examples", "not exist.json" );
	}
	
	/** The folder is null and the resource exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsInputStream_7() throws IOFileException {		
		IOFileUtils.loadResourceAsInputStream( null, "e4b_qa.json" );
	}
	
	/** The folder exists and the resource exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsInputStream_8() throws IOFileException {		
		IOFileUtils.loadResourceAsInputStream( "examples", null );
	}
		
	/** The resource exists */
	@Test()
	public void loadResourceAsInputStreamReader_1() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadResourceAsInputStreamReader( "system.properties" ));
	}
	
	/** The resource not exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsInputStreamReader_2() throws IOFileException {		
		IOFileUtils.loadResourceAsInputStreamReader( "not exist.properties" );
	}
	
	/** The resource is null */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsInputStreamReader_3() throws IOFileException {		
		IOFileUtils.loadResourceAsInputStreamReader( null );
	}
	
	/** The folder and the resource exist */
	@Test()
	public void loadResourceAsInputStreamReader_4() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadResourceAsInputStreamReader( "examples", "e4b_qa.json" ));
	}
	
	/** The folder not exist and the resource exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsInputStreamReader_5() throws IOFileException {		
		IOFileUtils.loadResourceAsInputStreamReader( "not exist", "e4b_qa.json" );
	}
	
	/** The folder exists and the resource not exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsInputStreamReader_6() throws IOFileException {		
		IOFileUtils.loadResourceAsInputStreamReader( "examples", "not exist.json" );
	}
	
	/** The folder is null and the resource exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsInputStreamReader_7() throws IOFileException {		
		IOFileUtils.loadResourceAsInputStreamReader( null, "e4b_qa.json" );
	}
	
	/** The folder exists and the resource exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsInputStreamReader_8() throws IOFileException {		
		IOFileUtils.loadResourceAsInputStreamReader( "examples", null );
	}
	
	/** The resource exists */
	@Test()
	public void loadResourceAsString_1() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadResourceAsString( "system.properties" ));
	}
	
	/** The resource not exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsString_2() throws IOFileException {		
		IOFileUtils.loadResourceAsString( "not exist.properties" );
	}
	
	/** The resource is null */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsString_3() throws IOFileException {		
		IOFileUtils.loadResourceAsString( null );
	}
	
	/** The folder and the resource exist */
	@Test()
	public void loadResourceAsString_4() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadResourceAsString( "examples", "e4b_qa.json" ));
	}
	
	/** The folder not exist and the resource exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsString_5() throws IOFileException {		
		IOFileUtils.loadResourceAsString( "not exist", "e4b_qa.json" );
	}
	
	/** The folder exists and the resource not exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsString_6() throws IOFileException {		
		IOFileUtils.loadResourceAsString( "examples", "not exist.json" );
	}
	
	/** The folder is null and the resource exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsString_7() throws IOFileException {		
		IOFileUtils.loadResourceAsString( null, "e4b_qa.json" );
	}
	
	/** The folder exists and the resource exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsString_8() throws IOFileException {		
		IOFileUtils.loadResourceAsString( "examples", null );
	}
	
	/** The resource exists */
	@Test()
	public void loadResourceAsBufferedReader_1() throws IOFileException {		
		String resource = "system.properties";
		Assert.assertNotNull( IOFileUtils.loadResourceAsBufferedReader(resource));
	}
	
	/** The resource not exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsBufferedReader_2() throws IOFileException {		
		String resource = "not exist.properties";
		IOFileUtils.loadResourceAsBufferedReader(resource);
	}
	
	/** The resource is null */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsBufferedReader_3() throws IOFileException {		
		String resource = null;
		IOFileUtils.loadResourceAsBufferedReader(resource);
	}
	
	/** The folder and the resource exist */
	@Test()
	public void loadResourceAsBufferedReader_4() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadResourceAsBufferedReader( "examples", "e4b_qa.json" ));
	}
	
	/** The folder not exist and the resource exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsBufferedReader_5() throws IOFileException {		
		IOFileUtils.loadResourceAsBufferedReader( "not exist", "e4b_qa.json" );
	}
	
	/** The folder exists and the resource not exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsBufferedReader_6() throws IOFileException {		
		IOFileUtils.loadResourceAsBufferedReader( "examples", "not exist.json" );
	}
	
	/** The folder is null and the resource exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsBufferedReader_7() throws IOFileException {		
		IOFileUtils.loadResourceAsBufferedReader( null, "e4b_qa.json" );
	}
	
	/** The folder exists and the resource exists */
	@Test( expectedExceptions = IOFileException.class )
	public void loadResourceAsBufferedReader_8() throws IOFileException {		
		IOFileUtils.loadResourceAsBufferedReader( "examples", null );
	}
	
	/** CHECK FILE */
	/** The file is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void buildPath_1() throws IOFileException {		
		IOFileUtils.buildPath( "" );		
	}
	
	/** The file is null */
	@Test(expectedExceptions = IOFileException.class)
	public void buildPath_2() throws IOFileException {		
		IOFileUtils.buildPath( null );		
	}
	
	/** The file has not a valid format */
	@Test(expectedExceptions = IOFileException.class)
	public void buildPath_3() throws IOFileException {		
		IOFileUtils.buildPath( "a/b.txt" );		
	}
	
	/** The file has a valid format */
	@Test()
	public void buildPath_4() throws IOFileException {		
		Assert.assertEquals( IOFileUtils.buildPath( "file!ç°;@ù.txt" ), "file!ç°;@ù.txt" );		
	}
	
	/** The folder is empty */
	@Test()
	public void buildPath_5() throws IOFileException {		
		Assert.assertEquals( IOFileUtils.buildPath( "", "file.txt" ), "file.txt" );		
	}
	
	/** The file is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void buildPath_6() throws IOFileException {		
		IOFileUtils.buildPath( "folder/", "" );		
	}
	
	/** The folder is null */
	@Test(expectedExceptions = IOFileException.class)
	public void buildPath_7() throws IOFileException {		
		IOFileUtils.buildPath( null, "file.txt" );		
	}
	
	/** The file is null */
	@Test(expectedExceptions = IOFileException.class)
	public void buildPath_8() throws IOFileException {		
		IOFileUtils.buildPath( "folder", null );		
	}
	
	/** The folder is equals to / */
	@Test()
	public void buildPath_9() throws IOFileException {		
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
	public void loadFileAsInputStream_1() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStream( "system.properties" ));
	}
	
	/** The file is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsInputStream_2() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStream( "" ));
	}
	
	/** The file is null */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsInputStream_3() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStream( null ));
	}
	
	/** The file is not valid */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsInputStream_4() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStream( "." ));
	}
	
	/** The folder and file exist */
	@Test()
	public void loadFileAsInputStream_5() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStream( "/tmp", "system.properties" ));
	}
	
	/** The folder not exists and file exist */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsInputStream_6() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStream( "wrong", "system.properties" ));
	}
	
	/** The folder and file exist */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsInputStream_7() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStream( "/tmp", "wrong.properties" ));
	}
	
	/** The file exist */
	@Test()
	public void loadFileAsInputStreamReader_1() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStreamReader( "system.properties" ));
	}
	
	/** The file is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsInputStreamReader_2() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStreamReader( "" ));
	}
	
	/** The file is null */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsInputStreamReader_3() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStreamReader( null ));
	}
	
	/** The file is not valid */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsInputStreamReader_4() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStreamReader( "." ));
	}
	
	/** The folder and file exist */
	@Test()
	public void loadFileAsInputStreamReader_5() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStreamReader( "/tmp", "system.properties" ));
	}
	
	/** The folder not exists and file exist */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsInputStreamReader_6() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStreamReader( "wrong", "system.properties" ));
	}
	
	/** The folder and file exist */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsInputStreamReader_7() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsInputStreamReader( "/tmp", "wrong.properties" ));
	}
			
	/** The file exist */
	@Test()
	public void loadFileAsString_1() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsString( "system.properties" ));
	}
	
	/** The file is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsString_2() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsString( "" ));
	}
	
	/** The file is null */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsString_3() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsString( null ));
	}
	
	/** The file is not valid */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsString_4() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsString( "." ));
	}
	
	/** The folder and file exist */
	@Test()
	public void loadFileAsString_5() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsString( "/tmp", "system.properties" ));
	}
	
	/** The folder not exists and file exist */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsString_6() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsString( "wrong", "system.properties" ));
	}
	
	/** The folder and file exist */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsString_7() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsString( "/tmp", "wrong.properties" ));
	}
	
	/** The file exist */
	@Test()
	public void loadFileAsBufferedReader_1() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsBufferedReader( "system.properties" ));
	}
	
	/** The file is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsBufferedReader_2() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsBufferedReader( "" ));
	}
	
	/** The file is null */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsBufferedReader_3() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsBufferedReader( null ));
	}
	
	/** The file is not valid */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsBufferedReader_4() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsBufferedReader( "." ));
	}
	
	/** The folder and file exist */
	@Test()
	public void loadFileAsBufferedReader_5() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsBufferedReader( "/tmp", "system.properties" ));
	}
	
	/** The folder not exists and file exist */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsBufferedReader_6() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsBufferedReader( "wrong", "system.properties" ));
	}
	
	/** The folder and file exist */
	@Test(expectedExceptions = IOFileException.class)
	public void loadFileAsBufferedReader_7() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.loadFileAsBufferedReader( "/tmp", "wrong.properties" ));
	}
		
	/** The file is valid and has not a parent directory */
	@Test()
	public void createPath_1() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.createPath( "system.properties" ));
	}
	
	/** The file is not valid because contains a folder */
	@Test(expectedExceptions = IOFileException.class)
	public void createPath_2() throws IOFileException {		
		IOFileUtils.createPath( "/tmp/system.properties" );
	}
	
	/** The folder and the file are valid */
	@Test()
	public void createPath_3() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.createPath( "/tmp", "system.properties" ));
	}
	
	/** The folder is empty and the file is valid */
	@Test()
	public void createPath_4() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.createPath( "", "system.properties" ));
	}
	
	/** The folder is null and the file is valid */
	@Test(expectedExceptions = IOFileException.class)
	public void createPath_5() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.createPath( null, "system.properties" ));
	}
	
	/** The folder is valid and the file is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void createPath_6() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.createPath( "/tmp", "" ));
	}
	
	/** The folder is valid and the file is null */
	@Test(expectedExceptions = IOFileException.class)
	public void createPath_7() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.createPath( "/tmp", null ));
	}
	
	/** The folder is valid and the file has not a valid format */
	@Test(expectedExceptions = IOFileException.class)
	public void createPath_8() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.createPath( "/tmp", "." ));
	}
	
	/** The folder is not valid and the file has a valid format */
	@Test()
	public void createPath_9() throws IOFileException {		
		Assert.assertNotNull( IOFileUtils.createPath( "//", "system.properties" ));
	}
	
	/** The content and the resource are valid */
	@Test()
	public void saveResource_1() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveResource( content, "save.properties" );
	}
	
	/** The content is empty and the resource is valid */
	@Test()
	public void saveResource_2() throws IOFileException {		
		String content = "";
		IOFileUtils.saveResource( content, "save_empty.properties" );
	}
	
	/** The content is null and the resource is valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResource_3() throws IOFileException {		
		String content = null;
		IOFileUtils.saveResource( content, "save.properties" );
	}
	
	/** The content is valid and the resource is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResource_4() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveResource( content, "" );
	}
	
	/** The content is valid and the resource is null */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResource_5() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveResource( content, null );
	}
	
	/** The content is valid and the resource is not valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResource_6() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveResource( content, "." );
	}
	
	/** The content, folder and resource are valid */
	@Test()
	public void saveResource_7() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveResource( content, "examples", "save.properties" );
	}
	
	/** The content is null and the folder and resource are valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResource_8() throws IOFileException {		
		String content = null;
		IOFileUtils.saveResource( content, "examples", "save.properties" );
	}
	
	/** The folder is null and the content and resource are valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResource_9() throws IOFileException {		
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
	public void saveResourceBuffer_1() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveResource( content, "save_buffer.properties" );
	}
	
	/** The content is empty and the resource is valid */
	@Test()
	public void saveResourceBuffer_2() throws IOFileException {		
		StringBuffer content = new StringBuffer("");
		IOFileUtils.saveResource( content, "save_buffer_empty.properties" );
	}
	
	/** The content is null and the resource is valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResourceBuffer_3() throws IOFileException {		
		StringBuffer content = null;
		IOFileUtils.saveResource( content, "save_buffer.properties" );
	}
	
	/** The content is valid and the resource is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResourceBuffer_4() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveResource( content, "" );
	}
	
	/** The content is valid and the resource is null */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResourceBuffer_5() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveResource( content, null );
	}
	
	/** The content is valid and the resource is not valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResourceBuffer_6() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveResource( content, "." );
	}
	
	/** The content, folder and resource are valid */
	@Test()
	public void saveResourceBuffer_7() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveResource( content, "examples", "save_buffer.properties" );
	}
	
	/** The content is null and the folder and resource are valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResourceBuffer_8() throws IOFileException {		
		StringBuffer content = null;
		IOFileUtils.saveResource( content, "examples", "save_buffer.properties" );
	}
	
	/** The folder is null and the content and resource are valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveResourceBuffer_9() throws IOFileException {		
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
	public void saveFile_1() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveFile( content, "save_file.properties" );
	}
	
	/** The content is empty and the File is valid */
	@Test()
	public void saveFile_2() throws IOFileException {		
		String content = "";
		IOFileUtils.saveFile( content, "save_file_empty.properties" );
	}
	
	/** The content is null and the File is valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFile_3() throws IOFileException {		
		String content = null;
		IOFileUtils.saveFile( content, "save_file.properties" );
	}
	
	/** The content is valid and the File is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFile_4() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveFile( content, "" );
	}
	
	/** The content is valid and the File is null */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFile_5() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveFile( content, null );
	}
	
	/** The content is valid and the File is not valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFile_6() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveFile( content, "." );
	}
	
	/** The content, folder and File are valid */
	@Test()
	public void saveFile_7() throws IOFileException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOFileUtils.saveFile( content, "examples", "save_file.properties" );
	}
	
	/** The content is null and the folder and File are valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFile_8() throws IOFileException {		
		String content = null;
		IOFileUtils.saveFile( content, "examples", "save_file.properties" );
	}
	
	/** The folder is null and the content and File are valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFile_9() throws IOFileException {		
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
	public void saveFileBuffer_1() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveFile( content, "save_file_buffer.properties" );
	}
	
	/** The content is empty and the File is valid */
	@Test()
	public void saveFileBuffer_2() throws IOFileException {		
		StringBuffer content = new StringBuffer("");
		IOFileUtils.saveFile( content, "save_file_buffer_empty.properties" );
	}
	
	/** The content is null and the File is valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFileBuffer_3() throws IOFileException {		
		StringBuffer content = null;
		IOFileUtils.saveFile( content, "save_file_buffer.properties" );
	}
	
	/** The content is valid and the File is empty */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFileBuffer_4() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveFile( content, "" );
	}
	
	/** The content is valid and the File is null */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFileBuffer_5() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveFile( content, null );
	}
	
	/** The content is valid and the File is not valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFileBuffer_6() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveFile( content, "." );
	}
	
	/** The content, folder and File are valid */
	@Test()
	public void saveFileBuffer_7() throws IOFileException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOFileUtils.saveFile( content, "examples", "save_file_buffer.properties" );
	}
	
	/** The content is null and the folder and File are valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFileBuffer_8() throws IOFileException {		
		StringBuffer content = null;
		IOFileUtils.saveFile( content, "examples", "save_file_buffer.properties" );
	}
	
	/** The folder is null and the content and File are valid */
	@Test(expectedExceptions = IOFileException.class)
	public void saveFileBuffer_9() throws IOFileException {		
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
