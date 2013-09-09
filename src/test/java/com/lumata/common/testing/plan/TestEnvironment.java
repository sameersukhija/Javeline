package com.lumata.common.testing.plan;

import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.ResourcePropertiesException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.system.ResourceProperties;

public class TestEnvironment {
	
	private static final  Logger logger = LoggerFactory.getLogger( TestEnvironment.class );
		
	Environment env;
	
	/** Load custom properties */
	@BeforeClass
	public void init() throws ResourcePropertiesException {		
		
		ResourceProperties.load( "system.properties" );
		
	}
	
	/** Load environment from the default resource folder ( src/main/resources/lumata-common-testing ) 
	 *  The resource file is correct 
	 */
	@Parameters({"browser", "environment"})
	@Test()
	public void loadEnvironmentFromResource_1( @Optional("FIREFOX") String browser, @Optional("E4B_QA") String environment ) throws EnvironmentException {		
		env = new Environment( environment, IOFileUtils.IOLoadingType.RESOURCE );
		Assert.assertNotNull( env );
	}
	
	/** Load environment from the default resource folder ( src/main/resources/lumata-common-testing ) 
	 *  The resource file not exists 
	 */
	@Parameters({"browser", "environment"})
	@Test( expectedExceptions = EnvironmentException.class )
	public void loadEnvironmentFromResource_2( @Optional("FIREFOX") String browser, @Optional("NOT_EXISTS") String environment ) throws EnvironmentException {		
		env = new Environment( environment, IOFileUtils.IOLoadingType.RESOURCE );
	}
	
	/** Load environment from the custom resource folder ( src/main/resources/lumata-common-testing/examples ) 
	 *  The resource file is correct 
	 */
	@Parameters({"browser", "environment"})
	@Test()
	public void loadEnvironmentFromResource_3( @Optional("FIREFOX") String browser, @Optional("E4B_QA") String environment ) throws EnvironmentException {		
		env = new Environment( "examples", environment, IOFileUtils.IOLoadingType.RESOURCE );
		Assert.assertNotNull( env );
	}
	
	/** Load environment from the custom resource folder ( src/main/resources/lumata-common-testing/examples ) defined in the system.properties file 
	 *  The resource file is correct 
	 */
	@Parameters({"browser", "environment"})
	@Test()
	public void loadEnvironmentFromResource_4( @Optional("FIREFOX") String browser, @Optional("E4B_QA") String environment ) throws EnvironmentException {		
		env = new Environment( System.getProperty("project.resource.examples"), environment, IOFileUtils.IOLoadingType.RESOURCE );
		Assert.assertNotNull( env );
	}
	
	/** Load environment from the default resource folder ( src/main/resources/lumata-common-testing ) 
	 *  The resource file exist and is insensitive ( Note: the file name must have all lower case characters ) 
	 */
	@Parameters({"browser", "environment"})
	@Test()
	public void loadEnvironmentFromResource_5( @Optional("FIREFOX") String browser, @Optional("E4b_qA") String environment ) throws EnvironmentException {		
		env = new Environment( environment, IOFileUtils.IOLoadingType.RESOURCE );
		Assert.assertNotNull( env );
	}
	
	/** Load environment from the default folder ( <home project>/ ) 
	 *  The resource file is correct 
	 */
	@Parameters({"browser", "environment"})
	@Test()
	public void loadEnvironmentFromFile_1( @Optional("FIREFOX") String browser, @Optional("E4B_QA") String environment ) throws EnvironmentException {		
		env = new Environment( environment, IOFileUtils.IOLoadingType.FILE );
		Assert.assertNotNull( env );
	}
	
	/** Load environment from the default folder ( <home project>/ ) 
	 *  The resource file not exists 
	 */
	@Parameters({"browser", "environment"})
	@Test( expectedExceptions = EnvironmentException.class )
	public void loadEnvironmentFromFile_2( @Optional("FIREFOX") String browser, @Optional("NOT_EXISTS") String environment ) throws EnvironmentException {		
		env = new Environment( environment, IOFileUtils.IOLoadingType.FILE );
	}
	
	/** Load environment from the custom folder ( <home project>/examples ) 
	 *  The resource file is correct 
	 */
	@Parameters({"browser", "environment"})
	@Test()
	public void loadEnvironmentFromFile_3( @Optional("FIREFOX") String browser, @Optional("E4B_QA") String environment ) throws EnvironmentException {		
		env = new Environment( "examples", environment, IOFileUtils.IOLoadingType.FILE );
		Assert.assertNotNull( env );
	}
	
	/** Load environment from the custom folder ( <home project>/examples ) defined in the system.properties file 
	 *  The resource file is correct 
	 */
	@Parameters({"browser", "environment"})
	@Test()
	public void loadEnvironmentFromFile_4( @Optional("FIREFOX") String browser, @Optional("E4B_QA") String environment ) throws EnvironmentException {		
		env = new Environment( System.getProperty("project.document.examples") , environment, IOFileUtils.IOLoadingType.FILE );
		Assert.assertNotNull( env );
	}
	
	/** Load environment from the custom folder ( <home project>/examples ) 
	 *  The resource file is correct 
	 */
	@Parameters({"browser", "environment"})
	@Test()
	public void loadEnvironmentFromFile_5( @Optional("FIREFOX") String browser, @Optional("E4B_QA") String environment ) throws EnvironmentException {		
		env = new Environment( System.getProperty("user.dir") + System.getProperty("project.resource.examples"), environment, IOFileUtils.IOLoadingType.FILE );
		Assert.assertNotNull( env );
	}
	
	/** Load environment from the default resource folder ( <home project>/ ) 
	 *  The resource file exist and is insensitive ( Note: the file name must have all lower case characters ) 
	 */
	@Parameters({"browser", "environment"})
	@Test()
	public void loadEnvironmentFromFile_6( @Optional("FIREFOX") String browser, @Optional("E4b_qA") String environment ) throws EnvironmentException {		
		env = new Environment( environment, IOFileUtils.IOLoadingType.FILE );
		Assert.assertNotNull( env );
	}
	
	@Parameters({"browser", "environment"})
	@Test()
	public void loadEnvironmentGetName( @Optional("FIREFOX") String browser, @Optional("E4B_QA") String environment ) throws EnvironmentException {		
		env = new Environment( System.getProperty("project.resource.examples"), environment, IOFileUtils.IOLoadingType.RESOURCE );
		Assert.assertEquals( env.getName(), "e4b_qa" );
	}
	
	@Parameters({"browser", "environment"})
	@Test()
	public void loadEnvironmentGetHost( @Optional("FIREFOX") String browser, @Optional("E4B_QA") String environment ) throws EnvironmentException {		
		env = new Environment( System.getProperty("project.resource.examples"), environment, IOFileUtils.IOLoadingType.RESOURCE );
		Assert.assertEquals( env.getHost(), "e4b_qa.lumata.com" );
	}
	
	@Parameters({"browser", "environment"})
	@Test()
	public void loadEnvironmentGetIPAddress( @Optional("FIREFOX") String browser, @Optional("E4B_QA") String environment ) throws EnvironmentException {		
		env = new Environment( System.getProperty("project.resource.examples"), environment, IOFileUtils.IOLoadingType.RESOURCE );
		Assert.assertEquals( env.getIPAddress(), "127.0.0.1" );
	}
	
	@Parameters({"browser", "environment"})
	@Test()
	public void loadEnvironmentGetLink( @Optional("FIREFOX") String browser, @Optional("E4B_QA") String environment ) throws EnvironmentException {		
		env = new Environment( System.getProperty("project.resource.examples"), environment, IOFileUtils.IOLoadingType.RESOURCE );
		Assert.assertEquals( env.getLink(), "http://expression-dev.lumata.int" );
	}
	
	@Parameters({"browser", "environment"})
	@Test()
	public void loadEnvironmentGetUser( @Optional("FIREFOX") String browser, @Optional("E4B_QA") String environment ) throws EnvironmentException, JSONException {		
		env = new Environment( System.getProperty("project.resource.examples"), environment, IOFileUtils.IOLoadingType.RESOURCE );
		JSONObject jo = new JSONObject("{\"username\":\"arcangelo.dipasquale@lumatagroup.com\",\"password\":\"LPwkr5aX\"}");
		Assert.assertEquals( env.getUser("arcangelo").toString(), jo.toString()) ;
	}
	
	@Parameters({"browser", "environment"})
	@Test()
	public void loadEnvironmentGetOptions( @Optional("FIREFOX") String browser, @Optional("E4B_QA") String environment ) throws EnvironmentException, JSONException {		
		env = new Environment( System.getProperty("project.resource.examples"), environment, IOFileUtils.IOLoadingType.RESOURCE );
		JSONObject jo = new JSONObject("{\"multi_tenant\":true}");
		Assert.assertEquals( env.getOptions().toString(), jo.toString()) ;
	}
	
	@Parameters({"browser", "environment"})
	@Test()
	public void loadEnvironmentGetBrowser( @Optional("FIREFOX") String browser, @Optional("E4B_QA") String environment ) throws EnvironmentException, JSONException {		
		env = new Environment( System.getProperty("project.resource.examples"), environment, IOFileUtils.IOLoadingType.RESOURCE );
		Assert.assertNotNull( env.getBrowser( browser ) );
	}
	
	@Parameters({"browser", "environment"})
	@Test()
	public void loadEnvironmentGetWrongBrowser( @Optional("WRONG_FIREFOX") String browser, @Optional("E4B_QA") String environment ) throws EnvironmentException, JSONException {		
		env = new Environment( System.getProperty("project.resource.examples"), environment, IOFileUtils.IOLoadingType.RESOURCE );
		Assert.assertNull( env.getBrowser( browser ) );
	}
	
	@Parameters({"browser", "environment"})
	@Test()
	public void loadEnvironmentGetDataSource( @Optional("FIREFOX") String browser, @Optional("E4B_QA") String environment ) throws EnvironmentException, JSONException {		
		env = new Environment( System.getProperty("project.resource.examples"), environment, IOFileUtils.IOLoadingType.RESOURCE );
		JSONObject jo = env.getDataSource( "e4b" );
		Properties props = env.getDataSourceAsProps( "e4b" );
		Assert.assertEquals( jo.getString("host"), props.getProperty("dbHost"));
		Assert.assertEquals( jo.getString("port"), props.getProperty("dbPort"));
		Assert.assertEquals( jo.getString("name"), props.getProperty("dbName"));
		Assert.assertEquals( jo.getString("user"), props.getProperty("dbUser"));
		Assert.assertEquals( jo.getString("password"), props.getProperty("dbPasswd"));
	}

}
