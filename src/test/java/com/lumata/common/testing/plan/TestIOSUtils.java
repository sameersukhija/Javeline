package com.lumata.common.testing.plan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOSException;
import com.lumata.common.testing.io.IOSUtils;


public class TestIOSUtils {
	
	private static final  Logger logger = LoggerFactory.getLogger( TestIOSUtils.class );
	
	/** CHECK RESOURCE */
	/** The path is empty */
	@Test(expectedExceptions = IOSException.class)
	public void buildResourcePath_1() throws IOSException {		
		IOSUtils.buildResourcePath( "" );		
	}
	
	/** The path is null */
	@Test(expectedExceptions = IOSException.class)
	public void buildResourcePath_2() throws IOSException {		
		IOSUtils.buildResourcePath( null );		
	}
	
	/** The resource has not a valid format */
	@Test(expectedExceptions = IOSException.class)
	public void buildResourcePath_3() throws IOSException {		
		IOSUtils.buildResourcePath( "a." );		
	}
	
	/** The resource has not a valid format */
	@Test(expectedExceptions = IOSException.class)
	public void buildResourcePath_4() throws IOSException {		
		IOSUtils.buildResourcePath( "." );		
	}
	
	/** The resource has not a valid format */
	@Test(expectedExceptions = IOSException.class)
	public void buildResourcePath_5() throws IOSException {		
		IOSUtils.buildResourcePath( ".a" );		
	}
	
	/** The resource has not a valid format 
	 *  Note: all characters are valid except /"'?$&*| */
	@Test(expectedExceptions = IOSException.class)
	public void buildResourcePath_6() throws IOSException {		
		IOSUtils.buildResourcePath( "/.txt" );
	}
	
	/** The resource has not a valid format 
	 *  Note: all characters are valid except /"'?$&*| */
	@Test(expectedExceptions = IOSException.class)
	public void buildResourcePath_7() throws IOSException {		
		IOSUtils.buildResourcePath( "\".txt" );		
	}
	
	/** The resource has not a valid format 
	 *  Note: all characters are valid except /"'?$&*| */
	@Test(expectedExceptions = IOSException.class)
	public void buildResourcePath_8() throws IOSException {		
		IOSUtils.buildResourcePath( "*.txt" );		
	}
	
	/** The resource has not a valid format 
	 *  Note: all characters are valid except /"'?$&*| */
	@Test(expectedExceptions = IOSException.class)
	public void buildResourcePath_9() throws IOSException {		
		IOSUtils.buildResourcePath( "folder/file.txt" );		
	}
	
	/** The resource exists and is valid
	 *  Note: "src/main/resources/lumata-common-testing/" is the default resource root */
	@Test()
	public void buildResourcePath_10() throws IOSException {		
		String resource = "1! #°a()ç@.txt";
		Assert.assertEquals(IOSUtils.buildResourcePath( resource ), "lumata-common-testing/" + resource);
	}
		
	/** The folder is empty and the resource has a valid format 
	 *  Note: all characters are valid except /"'?$&*| 
	 *  	  "src/main/resources/lumata-common-testing/" is the default resource root 
	 */
	@Test()
	public void buildResourcePath_11() throws IOSException {		
		Assert.assertEquals( IOSUtils.buildResourcePath( "", "file.txt" ), "lumata-common-testing/file.txt" );		
	}
	
	/** The folder is null and the resource has a valid format 
	 *  Note: all characters are valid except /"'?$&*| 
	 *  	  "src/main/resources/lumata-common-testing/" is the default resource root 
	 */
	@Test(expectedExceptions = IOSException.class)
	public void buildResourcePath_12() throws IOSException {		
		IOSUtils.buildResourcePath( null, "file.txt" );		
	}
	
	/** The folder is valid and the resource is empty 
	 *  Note: all characters are valid except /"'?$&*| 
	 *  	  "src/main/resources/lumata-common-testing/" is the default resource root 
	 */
	@Test(expectedExceptions = IOSException.class)
	public void buildResourcePath_13() throws IOSException {		
		IOSUtils.buildResourcePath( "tmp/", "" );		
	}
	
	/** The folder is valid and the resource is null 
	 *  Note: all characters are valid except /"'?$&*| 
	 *  	  "src/main/resources/lumata-common-testing/" is the default resource root 
	 */
	@Test(expectedExceptions = IOSException.class)
	public void buildResourcePath_14() throws IOSException {		
		IOSUtils.buildResourcePath( "tmp/", null );		
	}
	
	/** The folder and the resource are corrects 
	 *  Note: all characters are valid except /"'?$&*| 
	 *  	  "src/main/resources/lumata-common-testing/" is the default resource root 
	 */
	@Test()
	public void buildResourcePath_15() throws IOSException {		
		Assert.assertEquals( IOSUtils.buildResourcePath( "tmp/", "system.properties" ), "lumata-common-testing/tmp/system.properties" ) ;		
	}
	
	/** The resource exists */
	@Test()
	public void loadResourceAsInputStream_1() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadResourceAsInputStream( "system.properties" ));
	}
	
	/** The resource not exists */
	@Test( expectedExceptions = IOSException.class )
	public void loadResourceAsInputStream_2() throws IOSException {		
		IOSUtils.loadResourceAsInputStream( "not exist.properties" );
	}
	
	/** The resource is null */
	@Test( expectedExceptions = IOSException.class )
	public void loadResourceAsInputStream_3() throws IOSException {		
		String resource = null;
		IOSUtils.loadResourceAsInputStream(resource);
	}
	
	/** The folder and the resource exist */
	@Test()
	public void loadResourceAsInputStream_4() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadResourceAsInputStream( "examples", "e4b_qa.json" ));
	}
	
	/** The folder not exist and the resource exists */
	@Test( expectedExceptions = IOSException.class )
	public void loadResourceAsInputStream_5() throws IOSException {		
		IOSUtils.loadResourceAsInputStream( "not exist", "e4b_qa.json" );
	}
	
	/** The folder exists and the resource not exists */
	@Test( expectedExceptions = IOSException.class )
	public void loadResourceAsInputStream_6() throws IOSException {		
		IOSUtils.loadResourceAsInputStream( "examples", "not exist.json" );
	}
	
	/** The folder is null and the resource exists */
	@Test( expectedExceptions = IOSException.class )
	public void loadResourceAsInputStream_7() throws IOSException {		
		IOSUtils.loadResourceAsInputStream( null, "e4b_qa.json" );
	}
	
	/** The folder exists and the resource exists */
	@Test( expectedExceptions = IOSException.class )
	public void loadResourceAsInputStream_8() throws IOSException {		
		IOSUtils.loadResourceAsInputStream( "examples", null );
	}
		
	/** The resource exists */
	@Test()
	public void loadResourceAsInputStreamReader_1() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadResourceAsInputStreamReader( "system.properties" ));
	}
	
	/** The resource not exists */
	@Test( expectedExceptions = IOSException.class )
	public void loadResourceAsInputStreamReader_2() throws IOSException {		
		IOSUtils.loadResourceAsInputStreamReader( "not exist.properties" );
	}
	
	/** The resource is null */
	@Test( expectedExceptions = IOSException.class )
	public void loadResourceAsInputStreamReader_3() throws IOSException {		
		IOSUtils.loadResourceAsInputStreamReader( null );
	}
	
	/** The folder and the resource exist */
	@Test()
	public void loadResourceAsInputStreamReader_4() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadResourceAsInputStreamReader( "examples", "e4b_qa.json" ));
	}
	
	/** The folder not exist and the resource exists */
	@Test( expectedExceptions = IOSException.class )
	public void loadResourceAsInputStreamReader_5() throws IOSException {		
		IOSUtils.loadResourceAsInputStreamReader( "not exist", "e4b_qa.json" );
	}
	
	/** The folder exists and the resource not exists */
	@Test( expectedExceptions = IOSException.class )
	public void loadResourceAsInputStreamReader_6() throws IOSException {		
		IOSUtils.loadResourceAsInputStreamReader( "examples", "not exist.json" );
	}
	
	/** The folder is null and the resource exists */
	@Test( expectedExceptions = IOSException.class )
	public void loadResourceAsInputStreamReader_7() throws IOSException {		
		IOSUtils.loadResourceAsInputStreamReader( null, "e4b_qa.json" );
	}
	
	/** The folder exists and the resource exists */
	@Test( expectedExceptions = IOSException.class )
	public void loadResourceAsInputStreamReader_8() throws IOSException {		
		IOSUtils.loadResourceAsInputStreamReader( "examples", null );
	}
	
	/** The resource exists */
	@Test()
	public void loadResourceAsString_1() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadResourceAsString( "system.properties" ));
	}
	
	/** The resource not exists */
	@Test( expectedExceptions = IOSException.class )
	public void loadResourceAsString_2() throws IOSException {		
		IOSUtils.loadResourceAsString( "not exist.properties" );
	}
	
	/** The resource is null */
	@Test( expectedExceptions = IOSException.class )
	public void loadResourceAsString_3() throws IOSException {		
		IOSUtils.loadResourceAsString( null );
	}
	
	/** The folder and the resource exist */
	@Test()
	public void loadResourceAsString_4() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadResourceAsString( "examples", "e4b_qa.json" ));
	}
	
	/** The folder not exist and the resource exists */
	@Test( expectedExceptions = IOSException.class )
	public void loadResourceAsString_5() throws IOSException {		
		IOSUtils.loadResourceAsString( "not exist", "e4b_qa.json" );
	}
	
	/** The folder exists and the resource not exists */
	@Test( expectedExceptions = IOSException.class )
	public void loadResourceAsString_6() throws IOSException {		
		IOSUtils.loadResourceAsString( "examples", "not exist.json" );
	}
	
	/** The folder is null and the resource exists */
	@Test( expectedExceptions = IOSException.class )
	public void loadResourceAsString_7() throws IOSException {		
		IOSUtils.loadResourceAsString( null, "e4b_qa.json" );
	}
	
	/** The folder exists and the resource exists */
	@Test( expectedExceptions = IOSException.class )
	public void loadResourceAsString_8() throws IOSException {		
		IOSUtils.loadResourceAsString( "examples", null );
	}
	
	/** The resource exists */
	@Test()
	public void loadResourceAsBufferedReader_1() throws IOSException {		
		String resource = "system.properties";
		Assert.assertNotNull( IOSUtils.loadResourceAsBufferedReader(resource));
	}
	
	/** The resource not exists */
	@Test( expectedExceptions = IOSException.class )
	public void loadResourceAsBufferedReader_2() throws IOSException {		
		String resource = "not exist.properties";
		IOSUtils.loadResourceAsBufferedReader(resource);
	}
	
	/** The resource is null */
	@Test( expectedExceptions = IOSException.class )
	public void loadResourceAsBufferedReader_3() throws IOSException {		
		String resource = null;
		IOSUtils.loadResourceAsBufferedReader(resource);
	}
	
	/** The folder and the resource exist */
	@Test()
	public void loadResourceAsBufferedReader_4() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadResourceAsBufferedReader( "examples", "e4b_qa.json" ));
	}
	
	/** The folder not exist and the resource exists */
	@Test( expectedExceptions = IOSException.class )
	public void loadResourceAsBufferedReader_5() throws IOSException {		
		IOSUtils.loadResourceAsBufferedReader( "not exist", "e4b_qa.json" );
	}
	
	/** The folder exists and the resource not exists */
	@Test( expectedExceptions = IOSException.class )
	public void loadResourceAsBufferedReader_6() throws IOSException {		
		IOSUtils.loadResourceAsBufferedReader( "examples", "not exist.json" );
	}
	
	/** The folder is null and the resource exists */
	@Test( expectedExceptions = IOSException.class )
	public void loadResourceAsBufferedReader_7() throws IOSException {		
		IOSUtils.loadResourceAsBufferedReader( null, "e4b_qa.json" );
	}
	
	/** The folder exists and the resource exists */
	@Test( expectedExceptions = IOSException.class )
	public void loadResourceAsBufferedReader_8() throws IOSException {		
		IOSUtils.loadResourceAsBufferedReader( "examples", null );
	}
	
	/** CHECK FILE */
	/** The file is empty */
	@Test(expectedExceptions = IOSException.class)
	public void buildPath_1() throws IOSException {		
		IOSUtils.buildPath( "" );		
	}
	
	/** The file is null */
	@Test(expectedExceptions = IOSException.class)
	public void buildPath_2() throws IOSException {		
		IOSUtils.buildPath( null );		
	}
	
	/** The file has not a valid format */
	@Test(expectedExceptions = IOSException.class)
	public void buildPath_3() throws IOSException {		
		IOSUtils.buildPath( "a/b.txt" );		
	}
	
	/** The file has a valid format */
	@Test()
	public void buildPath_4() throws IOSException {		
		Assert.assertEquals( IOSUtils.buildPath( "file!ç°;@ù.txt" ), "file!ç°;@ù.txt" );		
	}
	
	/** The folder is empty */
	@Test()
	public void buildPath_5() throws IOSException {		
		Assert.assertEquals( IOSUtils.buildPath( "", "file.txt" ), "file.txt" );		
	}
	
	/** The file is empty */
	@Test(expectedExceptions = IOSException.class)
	public void buildPath_6() throws IOSException {		
		IOSUtils.buildPath( "folder/", "" );		
	}
	
	/** The folder is null */
	@Test(expectedExceptions = IOSException.class)
	public void buildPath_7() throws IOSException {		
		IOSUtils.buildPath( null, "file.txt" );		
	}
	
	/** The file is null */
	@Test(expectedExceptions = IOSException.class)
	public void buildPath_8() throws IOSException {		
		IOSUtils.buildPath( "folder", null );		
	}
	
	/** The folder is equals to / */
	@Test()
	public void buildPath_9() throws IOSException {		
		Assert.assertEquals( IOSUtils.buildPath( "/", "file.txt" ), "/file.txt" );	
	}
	
	/** The folder is empty and the file is equals to /file.txt 
	 *  Note: in this case the file format is not correct because contains the / character */
	@Test(expectedExceptions = IOSException.class)
	public void buildPath_10() throws IOSException {		
		IOSUtils.buildPath( "", "/file.txt" );	
	}
	
	/** The folder not have the / character */
	@Test()
	public void buildPath_11() throws IOSException {		
		Assert.assertEquals( IOSUtils.buildPath( "folder", "file.txt" ), "folder/file.txt" );	
	}
	
	/** The file exist */
	@Test()
	public void loadFileAsInputStream_1() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsInputStream( "system.properties" ));
	}
	
	/** The file is empty */
	@Test(expectedExceptions = IOSException.class)
	public void loadFileAsInputStream_2() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsInputStream( "" ));
	}
	
	/** The file is null */
	@Test(expectedExceptions = IOSException.class)
	public void loadFileAsInputStream_3() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsInputStream( null ));
	}
	
	/** The file is not valid */
	@Test(expectedExceptions = IOSException.class)
	public void loadFileAsInputStream_4() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsInputStream( "." ));
	}
	
	/** The folder and file exist */
	@Test()
	public void loadFileAsInputStream_5() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsInputStream( "/tmp", "system.properties" ));
	}
	
	/** The folder not exists and file exist */
	@Test(expectedExceptions = IOSException.class)
	public void loadFileAsInputStream_6() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsInputStream( "wrong", "system.properties" ));
	}
	
	/** The folder and file exist */
	@Test(expectedExceptions = IOSException.class)
	public void loadFileAsInputStream_7() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsInputStream( "/tmp", "wrong.properties" ));
	}
	
	/** The file exist */
	@Test()
	public void loadFileAsInputStreamReader_1() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsInputStreamReader( "system.properties" ));
	}
	
	/** The file is empty */
	@Test(expectedExceptions = IOSException.class)
	public void loadFileAsInputStreamReader_2() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsInputStreamReader( "" ));
	}
	
	/** The file is null */
	@Test(expectedExceptions = IOSException.class)
	public void loadFileAsInputStreamReader_3() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsInputStreamReader( null ));
	}
	
	/** The file is not valid */
	@Test(expectedExceptions = IOSException.class)
	public void loadFileAsInputStreamReader_4() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsInputStreamReader( "." ));
	}
	
	/** The folder and file exist */
	@Test()
	public void loadFileAsInputStreamReader_5() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsInputStreamReader( "/tmp", "system.properties" ));
	}
	
	/** The folder not exists and file exist */
	@Test(expectedExceptions = IOSException.class)
	public void loadFileAsInputStreamReader_6() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsInputStreamReader( "wrong", "system.properties" ));
	}
	
	/** The folder and file exist */
	@Test(expectedExceptions = IOSException.class)
	public void loadFileAsInputStreamReader_7() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsInputStreamReader( "/tmp", "wrong.properties" ));
	}
			
	/** The file exist */
	@Test()
	public void loadFileAsString_1() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsString( "system.properties" ));
	}
	
	/** The file is empty */
	@Test(expectedExceptions = IOSException.class)
	public void loadFileAsString_2() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsString( "" ));
	}
	
	/** The file is null */
	@Test(expectedExceptions = IOSException.class)
	public void loadFileAsString_3() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsString( null ));
	}
	
	/** The file is not valid */
	@Test(expectedExceptions = IOSException.class)
	public void loadFileAsString_4() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsString( "." ));
	}
	
	/** The folder and file exist */
	@Test()
	public void loadFileAsString_5() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsString( "/tmp", "system.properties" ));
	}
	
	/** The folder not exists and file exist */
	@Test(expectedExceptions = IOSException.class)
	public void loadFileAsString_6() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsString( "wrong", "system.properties" ));
	}
	
	/** The folder and file exist */
	@Test(expectedExceptions = IOSException.class)
	public void loadFileAsString_7() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsString( "/tmp", "wrong.properties" ));
	}
	
	/** The file exist */
	@Test()
	public void loadFileAsBufferedReader_1() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsBufferedReader( "system.properties" ));
	}
	
	/** The file is empty */
	@Test(expectedExceptions = IOSException.class)
	public void loadFileAsBufferedReader_2() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsBufferedReader( "" ));
	}
	
	/** The file is null */
	@Test(expectedExceptions = IOSException.class)
	public void loadFileAsBufferedReader_3() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsBufferedReader( null ));
	}
	
	/** The file is not valid */
	@Test(expectedExceptions = IOSException.class)
	public void loadFileAsBufferedReader_4() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsBufferedReader( "." ));
	}
	
	/** The folder and file exist */
	@Test()
	public void loadFileAsBufferedReader_5() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsBufferedReader( "/tmp", "system.properties" ));
	}
	
	/** The folder not exists and file exist */
	@Test(expectedExceptions = IOSException.class)
	public void loadFileAsBufferedReader_6() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsBufferedReader( "wrong", "system.properties" ));
	}
	
	/** The folder and file exist */
	@Test(expectedExceptions = IOSException.class)
	public void loadFileAsBufferedReader_7() throws IOSException {		
		Assert.assertNotNull( IOSUtils.loadFileAsBufferedReader( "/tmp", "wrong.properties" ));
	}
		
	/** The file is valid and has not a parent directory */
	@Test()
	public void createPath_1() throws IOSException {		
		Assert.assertNotNull( IOSUtils.createPath( "system.properties" ));
	}
	
	/** The file is not valid because contains a folder */
	@Test(expectedExceptions = IOSException.class)
	public void createPath_2() throws IOSException {		
		IOSUtils.createPath( "/tmp/system.properties" );
	}
	
	/** The folder and the file are valid */
	@Test()
	public void createPath_3() throws IOSException {		
		Assert.assertNotNull( IOSUtils.createPath( "/tmp", "system.properties" ));
	}
	
	/** The folder is empty and the file is valid */
	@Test()
	public void createPath_4() throws IOSException {		
		Assert.assertNotNull( IOSUtils.createPath( "", "system.properties" ));
	}
	
	/** The folder is null and the file is valid */
	@Test(expectedExceptions = IOSException.class)
	public void createPath_5() throws IOSException {		
		Assert.assertNotNull( IOSUtils.createPath( null, "system.properties" ));
	}
	
	/** The folder is valid and the file is empty */
	@Test(expectedExceptions = IOSException.class)
	public void createPath_6() throws IOSException {		
		Assert.assertNotNull( IOSUtils.createPath( "/tmp", "" ));
	}
	
	/** The folder is valid and the file is null */
	@Test(expectedExceptions = IOSException.class)
	public void createPath_7() throws IOSException {		
		Assert.assertNotNull( IOSUtils.createPath( "/tmp", null ));
	}
	
	/** The folder is valid and the file has not a valid format */
	@Test(expectedExceptions = IOSException.class)
	public void createPath_8() throws IOSException {		
		Assert.assertNotNull( IOSUtils.createPath( "/tmp", "." ));
	}
	
	/** The folder is not valid and the file has a valid format */
	@Test()
	public void createPath_9() throws IOSException {		
		Assert.assertNotNull( IOSUtils.createPath( "//", "system.properties" ));
	}
	
	/** The content and the resource are valid */
	@Test()
	public void saveResource_1() throws IOSException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOSUtils.saveResource( content, "save.properties" );
	}
	
	/** The content is empty and the resource is valid */
	@Test()
	public void saveResource_2() throws IOSException {		
		String content = "";
		IOSUtils.saveResource( content, "save_empty.properties" );
	}
	
	/** The content is null and the resource is valid */
	@Test(expectedExceptions = IOSException.class)
	public void saveResource_3() throws IOSException {		
		String content = null;
		IOSUtils.saveResource( content, "save.properties" );
	}
	
	/** The content is valid and the resource is empty */
	@Test(expectedExceptions = IOSException.class)
	public void saveResource_4() throws IOSException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOSUtils.saveResource( content, "" );
	}
	
	/** The content is valid and the resource is null */
	@Test(expectedExceptions = IOSException.class)
	public void saveResource_5() throws IOSException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOSUtils.saveResource( content, null );
	}
	
	/** The content is valid and the resource is not valid */
	@Test(expectedExceptions = IOSException.class)
	public void saveResource_6() throws IOSException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOSUtils.saveResource( content, "." );
	}
	
	/** The content, folder and resource are valid */
	@Test()
	public void saveResource_7() throws IOSException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOSUtils.saveResource( content, "examples", "save.properties" );
	}
	
	/** The content is null and the folder and resource are valid */
	@Test(expectedExceptions = IOSException.class)
	public void saveResource_8() throws IOSException {		
		String content = null;
		IOSUtils.saveResource( content, "examples", "save.properties" );
	}
	
	/** The folder is null and the content and resource are valid */
	@Test(expectedExceptions = IOSException.class)
	public void saveResource_9() throws IOSException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOSUtils.saveResource( content, null, "save.properties" );
	}
	
	/** The content and folder are valid and the resource is empty */
	@Test(expectedExceptions = IOSException.class)
	public void saveResource_10() throws IOSException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOSUtils.saveResource( content, "examples", "" );
	}
	
	/** The content is valid and the resource is null */
	@Test(expectedExceptions = IOSException.class)
	public void saveResource_11() throws IOSException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOSUtils.saveResource( content, "examples", null );
	}
	
	/** The content is valid and the resource is not valid */
	@Test(expectedExceptions = IOSException.class)
	public void saveResource_12() throws IOSException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOSUtils.saveResource( content, "examples", "." );
	}
	
	/** The content and the resource are valid and create a new folder */
	@Test()
	public void saveResource_13() throws IOSException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOSUtils.saveResource( content, "examples2", "save.properties" );
	}
	
	/** The content and the resource are valid */
	@Test()
	public void saveResourceBuffer_1() throws IOSException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOSUtils.saveResource( content, "save_buffer.properties" );
	}
	
	/** The content is empty and the resource is valid */
	@Test()
	public void saveResourceBuffer_2() throws IOSException {		
		StringBuffer content = new StringBuffer("");
		IOSUtils.saveResource( content, "save_buffer_empty.properties" );
	}
	
	/** The content is null and the resource is valid */
	@Test(expectedExceptions = IOSException.class)
	public void saveResourceBuffer_3() throws IOSException {		
		StringBuffer content = null;
		IOSUtils.saveResource( content, "save_buffer.properties" );
	}
	
	/** The content is valid and the resource is empty */
	@Test(expectedExceptions = IOSException.class)
	public void saveResourceBuffer_4() throws IOSException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOSUtils.saveResource( content, "" );
	}
	
	/** The content is valid and the resource is null */
	@Test(expectedExceptions = IOSException.class)
	public void saveResourceBuffer_5() throws IOSException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOSUtils.saveResource( content, null );
	}
	
	/** The content is valid and the resource is not valid */
	@Test(expectedExceptions = IOSException.class)
	public void saveResourceBuffer_6() throws IOSException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOSUtils.saveResource( content, "." );
	}
	
	/** The content, folder and resource are valid */
	@Test()
	public void saveResourceBuffer_7() throws IOSException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOSUtils.saveResource( content, "examples", "save_buffer.properties" );
	}
	
	/** The content is null and the folder and resource are valid */
	@Test(expectedExceptions = IOSException.class)
	public void saveResourceBuffer_8() throws IOSException {		
		StringBuffer content = null;
		IOSUtils.saveResource( content, "examples", "save_buffer.properties" );
	}
	
	/** The folder is null and the content and resource are valid */
	@Test(expectedExceptions = IOSException.class)
	public void saveResourceBuffer_9() throws IOSException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOSUtils.saveResource( content, null, "save_buffer.properties" );
	}
	
	/** The content and folder are valid and the resource is empty */
	@Test(expectedExceptions = IOSException.class)
	public void saveResourceBuffer_10() throws IOSException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOSUtils.saveResource( content, "examples", "" );
	}
	
	/** The content is valid and the resource is null */
	@Test(expectedExceptions = IOSException.class)
	public void saveResourceBuffer_11() throws IOSException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOSUtils.saveResource( content, "examples", null );
	}
	
	/** The content is valid and the resource is not valid */
	@Test(expectedExceptions = IOSException.class)
	public void saveResourceBuffer_12() throws IOSException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOSUtils.saveResource( content, "examples", "." );
	}
	
	/** The content and the resource are valid and create a new folder */
	@Test()
	public void saveResourceBuffer_13() throws IOSException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOSUtils.saveResource( content, "examples2", "save_buffer.properties" );
	}
	
	/** The content and the resource are valid */
	@Test()
	public void saveFile_1() throws IOSException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOSUtils.saveFile( content, "save_file.properties" );
	}
	
	/** The content is empty and the File is valid */
	@Test()
	public void saveFile_2() throws IOSException {		
		String content = "";
		IOSUtils.saveFile( content, "save_file_empty.properties" );
	}
	
	/** The content is null and the File is valid */
	@Test(expectedExceptions = IOSException.class)
	public void saveFile_3() throws IOSException {		
		String content = null;
		IOSUtils.saveFile( content, "save_file.properties" );
	}
	
	/** The content is valid and the File is empty */
	@Test(expectedExceptions = IOSException.class)
	public void saveFile_4() throws IOSException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOSUtils.saveFile( content, "" );
	}
	
	/** The content is valid and the File is null */
	@Test(expectedExceptions = IOSException.class)
	public void saveFile_5() throws IOSException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOSUtils.saveFile( content, null );
	}
	
	/** The content is valid and the File is not valid */
	@Test(expectedExceptions = IOSException.class)
	public void saveFile_6() throws IOSException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOSUtils.saveFile( content, "." );
	}
	
	/** The content, folder and File are valid */
	@Test()
	public void saveFile_7() throws IOSException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOSUtils.saveFile( content, "examples", "save_file.properties" );
	}
	
	/** The content is null and the folder and File are valid */
	@Test(expectedExceptions = IOSException.class)
	public void saveFile_8() throws IOSException {		
		String content = null;
		IOSUtils.saveFile( content, "examples", "save_file.properties" );
	}
	
	/** The folder is null and the content and File are valid */
	@Test(expectedExceptions = IOSException.class)
	public void saveFile_9() throws IOSException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOSUtils.saveFile( content, null, "save_file.properties" );
	}
	
	/** The content and folder are valid and the File is empty */
	@Test(expectedExceptions = IOSException.class)
	public void saveFile_10() throws IOSException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOSUtils.saveFile( content, "examples", "" );
	}
	
	/** The content is valid and the File is null */
	@Test(expectedExceptions = IOSException.class)
	public void saveFile_11() throws IOSException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOSUtils.saveFile( content, "examples", null );
	}
	
	/** The content is valid and the File is not valid */
	@Test(expectedExceptions = IOSException.class)
	public void saveFile_12() throws IOSException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOSUtils.saveFile( content, "examples", "." );
	}
	
	/** The content and the File are valid and create a new folder */
	@Test()
	public void saveFile_13() throws IOSException {		
		String content = "project.name=TEST";
		content = content + "\nproject.document=${user.home}/Documents/${project.name}";
		IOSUtils.saveFile( content, "examples2", "save_file.properties" );
	}
	
	/** The content and the File are valid */
	@Test()
	public void saveFileBuffer_1() throws IOSException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOSUtils.saveFile( content, "save_file_buffer.properties" );
	}
	
	/** The content is empty and the File is valid */
	@Test()
	public void saveFileBuffer_2() throws IOSException {		
		StringBuffer content = new StringBuffer("");
		IOSUtils.saveFile( content, "save_file_buffer_empty.properties" );
	}
	
	/** The content is null and the File is valid */
	@Test(expectedExceptions = IOSException.class)
	public void saveFileBuffer_3() throws IOSException {		
		StringBuffer content = null;
		IOSUtils.saveFile( content, "save_file_buffer.properties" );
	}
	
	/** The content is valid and the File is empty */
	@Test(expectedExceptions = IOSException.class)
	public void saveFileBuffer_4() throws IOSException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOSUtils.saveFile( content, "" );
	}
	
	/** The content is valid and the File is null */
	@Test(expectedExceptions = IOSException.class)
	public void saveFileBuffer_5() throws IOSException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOSUtils.saveFile( content, null );
	}
	
	/** The content is valid and the File is not valid */
	@Test(expectedExceptions = IOSException.class)
	public void saveFileBuffer_6() throws IOSException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOSUtils.saveFile( content, "." );
	}
	
	/** The content, folder and File are valid */
	@Test()
	public void saveFileBuffer_7() throws IOSException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOSUtils.saveFile( content, "examples", "save_file_buffer.properties" );
	}
	
	/** The content is null and the folder and File are valid */
	@Test(expectedExceptions = IOSException.class)
	public void saveFileBuffer_8() throws IOSException {		
		StringBuffer content = null;
		IOSUtils.saveFile( content, "examples", "save_file_buffer.properties" );
	}
	
	/** The folder is null and the content and File are valid */
	@Test(expectedExceptions = IOSException.class)
	public void saveFileBuffer_9() throws IOSException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOSUtils.saveFile( content, null, "save_file_buffer.properties" );
	}
	
	/** The content and folder are valid and the File is empty */
	@Test(expectedExceptions = IOSException.class)
	public void saveFileBuffer_10() throws IOSException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOSUtils.saveFile( content, "examples", "" );
	}
	
	/** The content is valid and the File is null */
	@Test(expectedExceptions = IOSException.class)
	public void saveFileBuffer_11() throws IOSException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOSUtils.saveFile( content, "examples", null );
	}
	
	/** The content is valid and the File is not valid */
	@Test(expectedExceptions = IOSException.class)
	public void saveFileBuffer_12() throws IOSException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOSUtils.saveFile( content, "examples", "." );
	}
	
	/** The content and the File are valid and create a new folder */
	@Test()
	public void saveFileBuffer_13() throws IOSException {		
		StringBuffer content = new StringBuffer().append("project.name=TEST").append("\nproject.document=${user.home}/Documents/${project.name}");
		IOSUtils.saveFile( content, "examples2", "save_file_buffer.properties" );
	}
	
}
