package com.lumata.expression.operators.testing.dao.catalogue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.dao.catalog.CatalogProductSpecificCharacteristicsDAOList;

public class TestCatalogProductSpecificCharacteristics {

	private static final Logger logger = LoggerFactory.getLogger( TestCatalogProductSpecificCharacteristics.class );
	private int TIMEOUT = 600000;
	private int ATTEMPT_INTERVAL = 500;
	
	Environment env;
	CatalogProductSpecificCharacteristicsDAOList catalogProductSpecificCharacteristicsList;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "user"})
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("superman") String user ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
						
	}
	
	@Parameters({"qa"})
	@Test( priority = 1 )
	public void loadCatalogProductTypes( @Optional("qa") String tenant ) {
		
		logger.info( Log.LOADING.createMessage( "loadCatalogProductSpecificCharacteristicsList" , "Catalog Product Specific Characteristics List" ) );	
		
		catalogProductSpecificCharacteristicsList = new CatalogProductSpecificCharacteristicsDAOList( env, tenant, null );
				
	}	

	@Parameters({"qa"})
	@Test( priority = 2 )
	public void checkCatalogProductTypes( @Optional("qa") String tenant ) {
		
		logger.info( Log.CHECKING.createMessage( "checkCatalogProductTypes" , "Catalog Product Types" ) );	
		
		Assert.assertEquals( catalogProductSpecificCharacteristicsList.size(), 1 );
		
		//Assert.assertEquals( catalogProductTypesList.get( 0 ).getProductTypeID(), 1 );
		
		//Assert.assertEquals( catalogProductTypesList.get( 0 ).getTypeName(), "Physical" );
		
		//Assert.assertEquals( catalogProductTypesList.get( 0 ).getDescription(), "Physical Product" );
				
	}
	
}
