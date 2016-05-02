package com.g4s.common.testing.plan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.g4s.common.testing.database.SQLite;
import com.g4s.common.testing.exceptions.NetworkEnvironmentException;
import com.g4s.common.testing.io.IOFileUtils;
import com.g4s.common.testing.orm.IMysqlDump;
import com.g4s.common.testing.orm.IMysqlOption;
import com.g4s.common.testing.orm.MysqlDump;
import com.g4s.common.testing.system.NetworkEnvironment;

import static com.g4s.common.testing.orm.Query.*;

public class TestMysqlDump {
	
	private NetworkEnvironment env = null;	
	
	@BeforeClass
	public void init() throws NetworkEnvironmentException 
	{		
		env = new NetworkEnvironment( "input/environments/", "local_ne", IOFileUtils.IOLoadingType.RESOURCE );
		
		Assert.assertNotNull( env , "NetworkEnvironment is null during init phase!");
	
	}
	
	@Test( enabled = true, priority = 1 )
	public void mysqlDumpStatement() throws NetworkEnvironmentException, SQLException {		
		
		IMysqlOption mysqldump = mysqldump().user( "root" ).password().host( "127.0.0.1" ).port( "3302" ); 
		
	}
	
}
