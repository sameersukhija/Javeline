package com.g4s.common.testing.plan;

import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.g4s.common.testing.database.Mysql;
import com.g4s.common.testing.database.MysqlKeys;
import com.g4s.common.testing.database.MysqlUtils;
import com.g4s.common.testing.exceptions.NetworkEnvironmentException;
import com.g4s.common.testing.io.IOFileUtils;
import com.g4s.common.testing.system.NetworkEnvironment;


public class TestMysqlUtils {
	
	@Parameters({"environment", "tenant1"})
	@Test( enabled = false )
	public void mysql_isTable( @Optional("E4O_QA") String environment, @Optional("qa") String tenant1  ) throws NetworkEnvironmentException {		
		
		NetworkEnvironment env = new NetworkEnvironment( "lumata-common-testing/examples/", environment, IOFileUtils.IOLoadingType.RESOURCE );
		Assert.assertNotNull( env );
		
		Mysql mysql = new Mysql( env.getDataSource( tenant1 ) );
		
		Assert.assertTrue( MysqlUtils.isTable( "subscribers", mysql ) );
		
		Assert.assertFalse( MysqlUtils.isTable( "subscribers_wrong", mysql ) );
		
		mysql.close();
		
	}	
	
	@Parameters({"environment", "tenant"})
	@Test( enabled = false )
	public void mysql_getKeys( @Optional("E4O_QA") String environment, @Optional("qa") String tenant  ) throws NetworkEnvironmentException, SQLException {		
		
		NetworkEnvironment env = new NetworkEnvironment( "input/environments/", environment, IOFileUtils.IOLoadingType.RESOURCE );
		Assert.assertNotNull( env );
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
		
		ArrayList<MysqlKeys> keys = MysqlUtils.getKeys( "conf", mysql );
		
		Assert.assertTrue( keys.size() == 4 );
				
		mysql.close();
		
	}	

}
