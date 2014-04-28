package com.lumata.e4o.schema;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.testng.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.database.MysqlUtils;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.system.NetworkEnvironment;

public class TestSchemaContentSizes {

	private static final Logger logger = LoggerFactory.getLogger( TestSchemaContentSizes.class );
	
	NetworkEnvironment env;
	
	@Parameters({"environment"})
	@BeforeMethod
	public void init( @Optional("E4O_QA") String environment ) throws NetworkEnvironmentException {
		
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
	}
	
	@Parameters({"tenant"})
	@Test( priority = 1, enabled = true )
	public void getSchemaContentSizes( @Optional("qa") String tenant ) throws SQLException, IOFileException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date now = new Date();
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
		
		ArrayList<String> schema = MysqlUtils.getSchema( mysql );
		
		StringBuffer str = new StringBuffer();
		
		for( int i = 0; i < schema.size(); i++ ) {
			
			String table_name = schema.get( i );
			
			int table_size = MysqlUtils.getTableSize( table_name, mysql );
			
			str.append( table_name ).append( " ( " ).append( table_size ).append( " ) \n" );
			
		}
		
		IOFileUtils.saveResource( str, "output/schema", "schema_table_sizes_" + sdf.format(now) + ".txt" );
		
	}
	
}
