package com.lumata.common.testing.plan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.database.MysqlUtils;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.system.Environment.EnvLoadingType;

public class TestMysqlUtils {
	
	private static final  Logger logger = LoggerFactory.getLogger( TestMysqlUtils.class );
					
	@Parameters({"environment", "tenant1"})
	@Test()
	public void mysql_isTable( @Optional("E4O_QA") String environment, @Optional("qa") String tenant1  ) throws EnvironmentException {		
		
		Environment env = new Environment( "lumata-common-testing/examples/", environment, EnvLoadingType.RESOURCE );
		Assert.assertNotNull( env );
		
		Mysql mysql = new Mysql( env.getDataSource( tenant1 ) );
		
		Assert.assertTrue( MysqlUtils.isTable( "subscribers", mysql ) );
		
		Assert.assertFalse( MysqlUtils.isTable( "subscribers_wrong", mysql ) );
		
		mysql.close();
		
	}	

}
