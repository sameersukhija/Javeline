package com.lumata.e4o.utils.generators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.dao.tenant.DAOSetHobbies;
import com.lumata.e4o.exceptions.FieldException;
import com.lumata.e4o.system.cdr.CDR;

public class SubscriberHobbies {
	
	private static final Logger logger = LoggerFactory.getLogger( SubscriberHobbies.class );
	
	NetworkEnvironment env;	
	Server actruleServer;
	Mysql mysql;
		
	/* 	Initialize Environment */
	@Parameters({"environment", "tenant" })
	@BeforeSuite
	public void init( @Optional("E4O_QA") String environment, @Optional("tenant") String tenant ) throws NetworkEnvironmentException {		
		
		logger.debug( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
			
		actruleServer = env.getServer( "actrule" );
		
		mysql = new Mysql( env.getDataSource( tenant ) );
				
	}
	
	@Test( enabled = true )
	//@Test( enabled = true )
	public void generateHobbies() throws IOFileException, FieldException {

		DAOSetHobbies.getInstance( mysql ).insertHobbies( CDR.HOBBIES.values() );
		
	}
	
	@AfterSuite
	public void end() {
		if( null != mysql ) { mysql.close(); }
	}

}
