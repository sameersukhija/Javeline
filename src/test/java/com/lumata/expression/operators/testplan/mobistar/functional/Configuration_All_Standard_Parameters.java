package com.lumata.expression.operators.testplan.mobistar.functional;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.dao.configuration.ConfigurationDAO;
import com.lumata.expression.operators.pojo.configuration.ConfigurationTypes;

public class Configuration_All_Standard_Parameters {

	private static final Logger logger = LoggerFactory.getLogger( Configuration_All_Standard_Parameters.class );
	
	Environment env;
	Mysql mysql;
	int user_id = 0;
	
	@Parameters({"environment", "tenant", "user"})
	@BeforeClass
	public void init( @Optional("E4O_QA") String environment, @Optional("qa") String tenant, @Optional("superman") String user ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		mysql = new Mysql( env.getDataSource( tenant ) );
				
	}
	
	@Parameters({"tenant"})
	@Test()
	public void setCfg( @Optional("qa") String tenant ) {
		
		ConfigurationDAO allStandardParameters = new ConfigurationDAO( ConfigurationTypes.ALL_STANDARD_PARAMETERS_FROM_FILE, null );
		
		allStandardParameters.checkAll( mysql );
					
	}
	
	@AfterClass
	public void end() {		
		
		mysql.close();
				
	}
		
}
