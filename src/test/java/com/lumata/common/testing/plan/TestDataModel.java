package com.lumata.common.testing.plan;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.DataModelException;
import com.lumata.common.testing.exceptions.ResourcePropertiesException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.model.DataModel;
import com.lumata.common.testing.system.ResourceProperties;

public class TestDataModel {
	
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
		DataModel dm = new DataModel("datamodel.json", IOFileUtils.IOLoadingType.RESOURCE );
		Assert.assertNotNull( dm );
	}
	
	/** Load datamodel from the default resource folder ( src/main/resources/lumata-common-testing ) 
	 *  The resource file not exists
	 */
	@Test( expectedExceptions = DataModelException.class )
	public void loadDataModelFromResource_2() throws DataModelException {		
		DataModel dm = new DataModel("not_exists_datamodel.json", IOFileUtils.IOLoadingType.RESOURCE );
	}
	
	/** Load datamodel from the custom resource folder ( src/main/resources/lumata-common-testing/examples ) 
	 *  The resource file is correct 
	 */
	@Test()
	public void loadDataModelFromResource_3() throws DataModelException {		
		DataModel dm = new DataModel("examples", "datamodel.json", IOFileUtils.IOLoadingType.RESOURCE );
		Assert.assertNotNull( dm );
	}
	
	/** Load datamodel from the custom resource folder ( src/main/resources/lumata-common-testing/examples ) defined in the system.properties file 
	 *  The resource file is correct 
	 */
	@Test()
	public void loadDataModelFromResource_4() throws DataModelException {		
		DataModel dm = new DataModel(System.getProperty("project.resource.examples"), "datamodel.json", IOFileUtils.IOLoadingType.RESOURCE );
		Assert.assertNotNull( dm );
	}
	
	/** Load datamodel from the default folder ( <home project>/ ) 
	 *  The resource file is correct 
	 */
	@Test()
	public void loadDataModelFromFile_1() throws DataModelException {		
		DataModel dm = new DataModel("datamodel.json", IOFileUtils.IOLoadingType.FILE );
		Assert.assertNotNull( dm );
	}
	
	/** Load datamodel from the default folder ( <home project>/ ) 
	 *  The resource file not exists 
	 */
	@Test( expectedExceptions = DataModelException.class )
	public void loadDataModelFromFile_2() throws DataModelException {		
		DataModel dm = new DataModel("not_exists_datamodel.json", IOFileUtils.IOLoadingType.FILE );
		Assert.assertNotNull( dm );
	}
	
	/** Load datamodel from the custom folder ( <home project>/examples ) 
	 *  The resource file is correct 
	 */
	@Test()
	public void loadDataModelFromFile_3() throws DataModelException {		
		DataModel dm = new DataModel( "examples", "datamodel.json", IOFileUtils.IOLoadingType.FILE );
		Assert.assertNotNull( dm );
	}
	
	/** Load datamodel from the custom folder ( <home project>/examples ) defined in the system.properties file 
	 *  The resource file is correct 
	 */
	@Test()
	public void loadDataModelFromFile_4() throws DataModelException {		
		DataModel dm = new DataModel( System.getProperty("project.resource.examples"), "datamodel.json", IOFileUtils.IOLoadingType.FILE );
		Assert.assertNotNull( dm );
	}
	
	/** Load datamodel from the custom folder ( <home project>/examples ) 
	 *  The resource file is correct 
	 */
	@Test()
	public void loadDataModelFromFile_5() throws DataModelException {		
		DataModel dm = new DataModel( System.getProperty("user.dir") + System.getProperty("project.resource.examples"), "datamodel.json", IOFileUtils.IOLoadingType.FILE );
		Assert.assertNotNull( dm );
	}
	
	/** Load model */
	@Test()
	public void loadDataModelGetModel() throws DataModelException {		
		DataModel dm = new DataModel( "datamodel.json", IOFileUtils.IOLoadingType.RESOURCE );
		Assert.assertNotNull( dm.getModel("subscribers") );
	}
	
	/** Load model label */
	@Test()
	public void loadDataModelGetModelLabel() throws DataModelException {		
		DataModel dm = new DataModel( "datamodel.json", IOFileUtils.IOLoadingType.RESOURCE );
		Assert.assertEquals( dm.getModelLabel("subscribers", 0), "subscription_date" );
	}
	
	/** Load model validator */
	@Test()
	public void loadDataModelGetModelValidator() throws DataModelException {		
		DataModel dm = new DataModel( "datamodel.json", IOFileUtils.IOLoadingType.RESOURCE );
		Assert.assertNotNull( dm.getModelValidator("subscribers", 0) );
	}
	
}
