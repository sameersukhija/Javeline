package com.lumata.e4o.alfa;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.database.MysqlUtils;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Service;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.exceptions.FieldException;
import com.lumata.e4o.schema.tenant.Agencies;
import com.lumata.e4o.schema.tenant.CatalogOffers;
import com.lumata.e4o.schema.tenant.Subscribers;

public class MysqlDump {
	
	NetworkEnvironment env;
	Service sshService;
	String sshUser = "root";
	User superman;
	Mysql mysql;
			
	/* 	Initialize Environment */
	@Parameters({"environment", "nfsdataServer", "user", "tenant"})
	@BeforeClass
	public void init( @Optional("E4O_VM") String environment, @Optional("collector") String nfsdataServer, @Optional("superman") String user, @Optional("tenant") String tenant ) throws NetworkEnvironmentException {		
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );

		sshService = env.getService( Service.Type.ssh, nfsdataServer );
		
		sshUser = "root";
		
		mysql = new Mysql( env.getDataSource( tenant ) );
		
	}
	
	@Test( enabled = true )
	public void mysqldump() throws IOFileException, FieldException, IOException, InterruptedException {
		
		ArrayList<Object> tables = new ArrayList<Object>();
		
		tables.add( new Agencies() );
		tables.add( new Subscribers() );	
		tables.add( new CatalogOffers() );			
		
		MysqlUtils.dump( mysql, "backup.sql", tables );
			
	}

}
