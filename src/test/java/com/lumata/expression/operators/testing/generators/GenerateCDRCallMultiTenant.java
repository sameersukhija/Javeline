package com.lumata.expression.operators.testing.generators;

import static com.lumata.common.testing.orm.Query.select;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.entities.Subscribers;
import com.lumata.expression.operators.exceptions.CDR;

public class GenerateCDRCallMultiTenant {

	private static final Logger logger = LoggerFactory.getLogger( GenerateCDRCallMultiTenant.class );
	
	Environment env;
	Mysql mysql_tenant1;
	Mysql mysql_tenant2;
	
	final String DATE_FORMAT = "yyyy-MM-dd";
	final Calendar DATE = Calendar.getInstance();
	Calendar MAX_VALIDITY_DATE = Calendar.getInstance();
	Calendar MAX_DEACTIVATION_DATE = Calendar.getInstance();
		
	final String EXTENSION = ".csv";
	final String CDR_FILE = "MULTITENANT_CALL_CDR" + EXTENSION;
	final String CDR_FOLDER = "input/cdr/";
	
	final String REMOTE_PATH = "/nfsdata/files/cdr/deposit/MULTITENANT_CALL_CDR";

	final int MIN_DURATION = 10;
	final int MAX_DURATION = 100;
	
	final int MIN_AMOUNT = 10;
	final int MAX_AMOUNT = 100;
			
	final int MIN_BALANCE = 1;
	final int MAX_BALANCE = 1000;
	
	final int MIN_DELAY = 100;
	final int MAX_DELAY = 1000;
	
	final int MIN_TENANT_ID = 1;
	final int MAX_TENANT_ID = 5;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "tenant1", "tenant2" })
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("tenant") String tenant1, @Optional("tenant") String tenant2 ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		mysql_tenant1 = new Mysql( env.getDataSource( tenant1 ) );
		mysql_tenant2 = new Mysql( env.getDataSource( tenant2 ) );
				
	}

	@Test( priority = 1, enabled = true )
	public void generateCDRMSISDN() throws IOFileException {
					
		CDR cdr = new CDR( CDR.Types.CALL_MULTI_TENANT );
		
		cdr.setDate( DATE, DATE_FORMAT );
		
		// MIN and MAX values are used to generate random values between MIN and MAX 
		cdr.setRandomDuration( MIN_DURATION, MAX_DURATION );		
		cdr.setMinAmount( MIN_AMOUNT );
		cdr.setMaxAmount( MAX_AMOUNT );		
		cdr.setMinBalance( MIN_BALANCE );
		cdr.setMaxBalance( MAX_BALANCE );
		cdr.setMinTenantId( MIN_TENANT_ID );
		cdr.setMaxTenantId( MAX_TENANT_ID );
				
		// Set property to store and upload file
		cdr.setPath( CDR_FOLDER );
		cdr.setFileName( CDR_FILE );
		
		Subscribers subscribersTable = new Subscribers();
		
		String query = select( Subscribers.Fields.msisdn ).from( subscribersTable ).orderBy( Subscribers.Fields.msisdn ).build();
		
		ResultSet rs = mysql_tenant1.execQuery( query );
		
		try {
			
			while( rs.next() ) {
				
				Long msisdn = rs.getLong( Subscribers.Fields.msisdn.name() );
				
				cdr.setMsisdn( msisdn );
				cdr.setRandomDuration();
				cdr.setRandomAmount();
				cdr.setRandomBalance();
				cdr.setRandomTerminating();
				cdr.setRandomTenantId();
								
				// Add line with CDR Revenue format
				cdr.add();
				
			}
				
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		// Save CDR file in the local host 
		cdr.save();
		
		// Send CDR file to remote host
		cdr.send( env, REMOTE_PATH );
				
	}
		
	@AfterSuite
	public void end() throws EnvironmentException {		
		
		mysql_tenant1.close();
		mysql_tenant2.close();
						
	}
	
}
