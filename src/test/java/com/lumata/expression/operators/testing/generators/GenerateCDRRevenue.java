package com.lumata.expression.operators.testing.generators;

import static com.lumata.common.testing.orm.Query.select;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.lumata.e4o_tenant.schema.Subscribers;
import com.lumata.expression.operators.exceptions.CDR;

public class GenerateCDRRevenue {

	private static final Logger logger = LoggerFactory.getLogger( GenerateCDRRevenue.class );
	
	Environment env;
	Mysql mysql;	
	
	final String DATE_FORMAT = "yyyy-MM-dd";
	final Calendar DATE = Calendar.getInstance();
	Calendar MAX_VALIDITY_DATE = Calendar.getInstance();
	Calendar MAX_DEACTIVATION_DATE = Calendar.getInstance();
		
	final String EXTENSION = ".csv";
	final String CDR_FILE = "REVENUE_CDR" + EXTENSION;
	final String CDR_FOLDER = "input/cdr/";
	
	final String REMOTE_PATH = "/nfsdata/files/cdr/deposit/REVENUE_CDR/";
	
	final int MIN_AMOUNT = 10;
	final int MAX_AMOUNT = 100;
			
	final int MIN_BALANCE = 1;
	final int MAX_BALANCE = 1000;
	
	final int MIN_DELAY = 100;
	final int MAX_DELAY = 1000;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "tenant" })
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("qa") String tenant ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		mysql = new Mysql( env.getDataSource( tenant ) );
				
	}

	@Test( priority = 1, enabled = true )
	public void generateCDRMSISDN() throws IOFileException {
					
		CDR cdr = new CDR( CDR.Types.REVENUE );
		
		cdr.setDate( DATE, DATE_FORMAT );
		
		// MIN and MAX values are used to generate random values between MIN and MAX 
		cdr.setMinValidityDate( DATE.get( Calendar.YEAR ) + 1, DATE.get( Calendar.MONTH ), DATE.get( Calendar.DAY_OF_MONTH ) );
		cdr.setMaxValidityDate( cdr.getMinValidityDate().get( Calendar.YEAR ) + 5, cdr.getMinValidityDate().get( Calendar.MONTH ), cdr.getMinValidityDate().get( Calendar.DAY_OF_MONTH ) );
		cdr.setMinDeactivationDate( cdr.getMaxValidityDate().get( Calendar.YEAR ), cdr.getMaxValidityDate().get( Calendar.MONTH ), cdr.getMaxValidityDate().get( Calendar.DAY_OF_MONTH ) );
		cdr.setMaxDeactivationDate( cdr.getMinDeactivationDate().get( Calendar.YEAR ), cdr.getMinDeactivationDate().get( Calendar.MONTH ) + 2, cdr.getMinDeactivationDate().get( Calendar.DAY_OF_MONTH ) );
		cdr.setMinAmount( MIN_AMOUNT );
		cdr.setMaxAmount( MAX_AMOUNT );		
		cdr.setMinBalance( MIN_BALANCE );
		cdr.setMaxBalance( MAX_BALANCE );
		cdr.setMinDelay( MIN_DELAY );
		cdr.setMaxDelay( MAX_DELAY );
		
		// Set property to store and upload file
		cdr.setPath( CDR_FOLDER );
		cdr.setFileName( CDR_FILE );
		
		Subscribers subscribersTable = new Subscribers();
		
		String query = select( Subscribers.Fields.msisdn ).from( subscribersTable ).orderBy( Subscribers.Fields.msisdn ).build();
		
		ResultSet rs = mysql.execQuery( query );
		
		try {
			
			while( rs.next() ) {
				
				Long msisdn = rs.getLong( Subscribers.Fields.msisdn.name() );
				
				cdr.setMsisdn( msisdn );
				cdr.setRandomAmount();
				cdr.setRandomBalance();
				cdr.setRandomValidityDate( DATE_FORMAT );
				cdr.setRandomDeactivationDate( DATE_FORMAT );
				cdr.setRandomType();
				cdr.setRandomDelay();
								
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
			
}
