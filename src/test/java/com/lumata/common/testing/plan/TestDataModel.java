package com.lumata.common.testing.plan;

import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.DataModelException;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.ResourcePropertiesException;
import com.lumata.common.testing.model.DataModel;
import com.lumata.common.testing.model.DataModel.DMLoadingType;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.system.Environment.EnvLoadingType;
import com.lumata.common.testing.system.ResourceProperties;

public class TestDataModel {
	
	private static final  Logger logger = LoggerFactory.getLogger( TestDataModel.class );
	
	/** Load custom properties */
	@BeforeClass
	public void init() throws ResourcePropertiesException {		
		
		ResourceProperties.load( "system.properties" );
		
	}
	
	/** Load datamodel from the default resource folder ( src/main/resources/lumata-common-testing ) 
	 *  The resource file is correct 
	 */
	@Test()
	public void loadDataModelFromResource_1() throws DataModelException {		
		DataModel dm = new DataModel("datamodel.json", DMLoadingType.RESOURCE );
		Assert.assertNotNull( dm );
	}
	
	/** Load datamodel from the default resource folder ( src/main/resources/lumata-common-testing ) 
	 *  The resource file not exists
	 */
	@Test( expectedExceptions = DataModelException.class )
	public void loadDataModelFromResource_2() throws DataModelException {		
		DataModel dm = new DataModel("not_exists_datamodel.json", DMLoadingType.RESOURCE );
	}
	
	/** Load datamodel from the custom resource folder ( src/main/resources/lumata-common-testing/examples ) 
	 *  The resource file is correct 
	 */
	@Test()
	public void loadDataModelFromResource_3() throws DataModelException {		
		DataModel dm = new DataModel("examples", "datamodel.json", DMLoadingType.RESOURCE );
		Assert.assertNotNull( dm );
	}
	
	/** Load datamodel from the custom resource folder ( src/main/resources/lumata-common-testing/examples ) defined in the system.properties file 
	 *  The resource file is correct 
	 */
	@Test()
	public void loadDataModelFromResource_4() throws DataModelException {		
		DataModel dm = new DataModel(System.getProperty("project.resource.examples"), "datamodel.json", DMLoadingType.RESOURCE );
		Assert.assertNotNull( dm );
	}
	
	/** Load datamodel from the default folder ( <home project>/ ) 
	 *  The resource file is correct 
	 */
	@Test()
	public void loadDataModelFromFile_1() throws DataModelException {		
		DataModel dm = new DataModel("datamodel.json", DMLoadingType.FILE );
		Assert.assertNotNull( dm );
	}
	
	/** Load datamodel from the default folder ( <home project>/ ) 
	 *  The resource file not exists 
	 */
	@Test( expectedExceptions = DataModelException.class )
	public void loadDataModelFromFile_2() throws DataModelException {		
		DataModel dm = new DataModel("not_exists_datamodel.json", DMLoadingType.FILE );
		Assert.assertNotNull( dm );
	}
	
	/** Load datamodel from the custom folder ( <home project>/examples ) 
	 *  The resource file is correct 
	 */
	@Test()
	public void loadDataModelFromFile_3() throws DataModelException {		
		DataModel dm = new DataModel( "examples", "datamodel.json", DMLoadingType.FILE );
		Assert.assertNotNull( dm );
	}
	
	/** Load datamodel from the custom folder ( <home project>/examples ) defined in the system.properties file 
	 *  The resource file is correct 
	 */
	@Test()
	public void loadDataModelFromFile_4() throws DataModelException {		
		DataModel dm = new DataModel( System.getProperty("project.resource.examples"), "datamodel.json", DMLoadingType.FILE );
		Assert.assertNotNull( dm );
	}
	
	/** Load datamodel from the custom folder ( <home project>/examples ) 
	 *  The resource file is correct 
	 */
	@Test()
	public void loadDataModelFromFile_5() throws DataModelException {		
		DataModel dm = new DataModel( System.getProperty("user.dir") + System.getProperty("project.resource.examples"), "datamodel.json", DMLoadingType.FILE );
		Assert.assertNotNull( dm );
	}
	
	/** Load model */
	@Test()
	public void loadDataModelGetModel() throws DataModelException {		
		DataModel dm = new DataModel( "datamodel.json", DMLoadingType.RESOURCE );
		Assert.assertNotNull( dm.getModel("subscribers") );
	}
	
	/** Load model label */
	@Test()
	public void loadDataModelGetModelLabel() throws DataModelException {		
		DataModel dm = new DataModel( "datamodel.json", DMLoadingType.RESOURCE );
		Assert.assertEquals( dm.getModelLabel("subscribers", 0), "subscription_date" );
	}
	
	/** Load model validator */
	@Test()
	public void loadDataModelGetModelValidator() throws DataModelException {		
		DataModel dm = new DataModel( "datamodel.json", DMLoadingType.RESOURCE );
		Assert.assertNotNull( dm.getModelValidator("subscribers", 0) );
	}
	
}
