package com.lumata.e4o.schema.utils;

import java.sql.SQLException;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.e4o.dao.tenant.DAOOfferStock;
import com.lumata.e4o.dao.tenant.DAOStats;
import com.lumata.e4o.dao.tenant.DAOVoucher;
import com.lumata.e4o.exceptions.FormException;

public class ResetExpiredVoucherTask {

	private static final Logger logger = LoggerFactory.getLogger( ResetExpiredVoucherTask.class );
	
	private final boolean testEnabled = true;
	
	private NetworkEnvironment env;
	private Mysql mysql;	
	
	/* 	Initialize Environment */
	@Parameters({"environment", "tenant"})
	@BeforeClass
	public void init( @Optional("E4O_VM") String environment, @Optional("tenant") String tenant ) throws NetworkEnvironmentException, FormException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		mysql = new Mysql( env.getDataSource( tenant ) );
		
	}
	
	@Test( enabled=testEnabled, priority = 1 )
	public void configureGUI() throws FormException, JSONException, JSONSException, SQLException {
		
		DAOStats.getInstance( mysql ).delStatsByName( "ExpiredVoucher" );
		
	}
	
}
