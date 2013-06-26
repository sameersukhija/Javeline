package com.lumata.common.testing.plan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.system.Environment.EnvLoadingType;

public class TestMysql {
	
	private static final  Logger logger = LoggerFactory.getLogger( TestMysql.class );
					
	@Parameters({"browser", "environment"})
	@Test()
	public void mysql_1( @Optional("FIREFOX") String browser, @Optional("E4B_QA") String environment ) throws EnvironmentException {		
		
		Environment env = new Environment( environment, EnvLoadingType.RESOURCE );
		Assert.assertNotNull( env );
		
		Mysql mysql = new Mysql( env.getDataSource( "user_datas" ) );
		
		mysql.connect();
		
		mysql.execQuery( "SELECT * FROM subscribers;" );
		
		mysql.close();
		
	}	

}
