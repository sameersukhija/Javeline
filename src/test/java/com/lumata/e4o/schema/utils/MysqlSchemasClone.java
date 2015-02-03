package com.lumata.e4o.schema.utils;

import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.database.MysqlUtils;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.e4o.schema.tenant.Agencies;
import com.lumata.e4o.schema.tenant.CampaignTypes;
import com.lumata.e4o.schema.tenant.Campaigns;
import com.lumata.e4o.schema.tenant.CatalogOffers;
import com.lumata.e4o.schema.tenant.Subscribers;

public class MysqlSchemasClone {
	
	private static final Logger logger = LoggerFactory.getLogger( MysqlSchemasClone.class );
	
	NetworkEnvironment envOrigin;	
	NetworkEnvironment envDestination;	
	
	Mysql mysqlOrigin;
	Mysql mysqlDestination;
	
	final List<Class<?>> tablesToClone = Arrays.asList ( 
		CampaignTypes.class,
		Campaigns.class
	);
	
	/* 	Initialize Environment */
	@Parameters({"env_orig", "env_dest", "tenant_orig", "tenant_dest"})
	@BeforeSuite
	public void init( @Optional("E4O_VM") String env_orig, @Optional("E4O_VM") String env_dest, @Optional("tenant") String tenant_orig, @Optional("tenant") String tenant_dest ) throws NetworkEnvironmentException {		
		
		logger.debug( Log.LOADING.createMessage( "init" , "environment" ) );

		env_orig = "E4O_O2_STG_NE";
		
		//env_orig = "E4O_QA2_NE";
		
		env_dest = "E4O_QA3_NE";
		
		tenant_orig = "tenant";
		
		tenant_dest = "tenant";
				
		envOrigin = new NetworkEnvironment( "input/environments", env_orig, IOFileUtils.IOLoadingType.RESOURCE );
		
		envDestination = new NetworkEnvironment( "input/environments", env_dest, IOFileUtils.IOLoadingType.RESOURCE );
					
		mysqlOrigin = new Mysql( envOrigin.getDataSource( tenant_orig ) );
		
		mysqlDestination = new Mysql( envDestination.getDataSource( tenant_dest ) );
	
	}

	@Test( enabled = true )
	public void cloneDatabase() throws SQLException, IOFileException {
	
		ArrayList<Object> tables = new ArrayList<Object>();
		
		//tables.add( new Agencies() );
		//tables.add( new Subscribers() );	
		tables.add( new CatalogOffers() );
		
		MysqlUtils.clone( mysqlOrigin, mysqlDestination, "backup.sql", tables );	

	}
	
	@AfterSuite
	public void end() {
		
		mysqlOrigin.close();
		
		mysqlDestination.close();
		
	}
	
}
