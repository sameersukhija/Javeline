package com.lumata.e4o.schema.configuration;

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
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.e4o.schema.tenant.CatalogOffers;
import com.lumata.e4o.schema.tenant.FilesData;
import com.lumata.e4o.schema.tenant.FilesMeta;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

public class ConfigureOfferEligibilityCriteria {
	
	private static final Logger logger = LoggerFactory.getLogger( ConfigureOfferEligibilityCriteria.class );
	
	NetworkEnvironment env;	
	
//	public TestDMNotificationEnabled( NetworkEnvironment env ) {
//		this.env = env;
//	}
//	
//	public static TestDMNotificationEnabled getInstance( NetworkEnvironment env ) {
//		return new TestDMNotificationEnabled( env );
//	}
 	
	/* 	Initialize Environment */
	@Parameters({"environment"})
	@BeforeSuite
	public void init( @Optional("E4O_VM") String environment ) throws NetworkEnvironmentException {		
		
		logger.debug( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
					
	}
	
	@Parameters({"tenant"})
	@Test
	public void configureDMNotifications( @Optional("tenant") String tenant ) throws SQLException, IOFileException {
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
		
		CatalogOffers co = new CatalogOffers();
		
		String query = select().from( co ).limit( 1 ).build();
		
		ResultSet rs = mysql.execQuery( query );
		
		while( rs.next() ) { 
			
			co = new CatalogOffers( rs ); 
		
		}
		
		String offerEligibilityCriteria = IOFileUtils.loadResourceAsString( "input/catalogue/offers", "offer_eligibility_criteria.xml" );
		
		offerEligibilityCriteria = offerEligibilityCriteria.replaceAll("(\r\n|\n)", "").replaceAll( "\"" , "'");
			
		co.setEligibilityCriteria( offerEligibilityCriteria );
		
		query = update( co ).set( op( CatalogOffers.Fields.eligibility_criteria ).eq( co.getEligibilityCriteria() ) ).where( op( CatalogOffers.Fields.offer_id ).eq() ).build();
		
		System.out.println( query );
		
		mysql.execUpdate( query );						
		
	}
	
}
