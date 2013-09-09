package com.lumata.common.testing.plan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.Environment;

public class TestMysql {
	
	private static final  Logger logger = LoggerFactory.getLogger( TestMysql.class );
					
	@Parameters({"browser", "environment"})
	@Test( enabled=false )
	public void mysql_select( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment ) throws EnvironmentException {		
		
		Environment env = new Environment( "lumata-common-testing/examples/", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		Assert.assertNotNull( env );
		
		Mysql mysql = new Mysql( env.getDataSource( "qa" ) );
		
		mysql.execQuery( "SELECT * FROM subscribers;" );
		
		mysql.close();
		
	}	
	
	@Parameters({"browser", "environment"})
	@Test()
	public void mysql_insert( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment ) throws EnvironmentException {		
		
		Environment env = new Environment( "lumata-common-testing/examples/", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		Assert.assertNotNull( env );
		
		Mysql mysql = new Mysql( env.getDataSource( "qa" ) );
		
		int index = mysql.execUpdate( "INSERT INTO token_label ( label ) VALUES( 'Test' );" );
		
		System.out.println( index );
		
		mysql.close();
		
	}

}
