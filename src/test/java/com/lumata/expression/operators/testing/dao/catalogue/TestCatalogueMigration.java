package com.lumata.expression.operators.testing.dao.catalogue;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.DataModelException;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.model.DataModel;
import com.lumata.common.testing.model.DataModel.DataModelOptions;
import com.lumata.common.testing.model.DataModelCompare;
import com.lumata.common.testing.system.Environment;


public class TestCatalogueMigration {

	private static final  Logger logger = LoggerFactory.getLogger( TestCatalogueMigration.class );
	Environment env;
	
	@BeforeClass
	public void init() throws EnvironmentException {		
		
		env = new Environment( "lumata-common-testing/examples", "e4o_qa", IOFileUtils.IOLoadingType.RESOURCE );
		
	}
	
	@Test
	public void checkCatalogueMigration() throws DataModelException {
		
		System.out.println( env.getDataSource( "qa" ).toString() );
		
		Map<String, Object> options = new HashMap<String, Object>();
		options.put( DataModelOptions.VALIDATOR.toString(), true );
		
		DataModel currentDM = new DataModel( "qa", env.getDataSource( "qa" ), options );
		
		DataModel expectedDM = new DataModel( "lumata-common-testing/examples", "catalogue", IOFileUtils.IOLoadingType.RESOURCE );
		
		logger.info( DataModelCompare.compare( currentDM, expectedDM, env.getDataSource( "qa" ), env.getDataSource( "qa" ), null ).toString() );
		
		//logger.info( currentDM.getDataModel().toString()  );
		//logger.info( expectedDM.getDataModel().toString()  );
		
	}
	
	
}
