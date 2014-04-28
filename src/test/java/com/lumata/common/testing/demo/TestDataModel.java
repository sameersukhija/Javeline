package com.lumata.common.testing.demo;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.DataModelException;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.model.DataModel;
import com.lumata.common.testing.system.Environment;


public class TestDataModel {

	private static final Logger logger = LoggerFactory.getLogger( TestDataModel.class );
	
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
	public void createDAO( @Optional("qa") String tenant ) throws IOFileException, DataModelException {
		
		DataModel dataModel = new DataModel( tenant, env.getDataSource( tenant ), null );
		
		JSONObject tenant_schema = dataModel.getDataModel();
		
		System.out.println( tenant_schema );
				
	}		
	
}
