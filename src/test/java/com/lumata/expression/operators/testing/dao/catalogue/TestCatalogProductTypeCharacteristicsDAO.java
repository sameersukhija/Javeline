package com.lumata.expression.operators.testing.dao.catalogue;

import java.util.ArrayList;
import java.util.Arrays;

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
import com.lumata.expression.operators.dao.catalog.CatalogProductTypeCharacteristicsDAOList;

public class TestCatalogProductTypeCharacteristicsDAO {

	private static final Logger logger = LoggerFactory.getLogger( TestCatalogProductTypeCharacteristicsDAO.class );
	private int TIMEOUT = 600000;
	private int ATTEMPT_INTERVAL = 500;
	
	Environment env;
	CatalogProductTypeCharacteristicsDAOList catalogProductTypeCharacteristicsDAOList;
	
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
		
		catalogProductTypeCharacteristicsDAOList = new CatalogProductTypeCharacteristicsDAOList( env, tenant, null );
				
	}	

	@Parameters({"qa"})
	@Test( priority = 2 )
	public void checkCatalogProductTypeCharacteristicsDAOList( @Optional("qa") String tenant ) {
		
		logger.info( Log.CHECKING.createMessage( "checkCatalogProductTypeCharacteristicsDAOList" , "Catalog Product Type Characteristics DAO List" ) );	
		
		Assert.assertEquals( catalogProductTypeCharacteristicsDAOList.size(), 1 );
		
		logger.info( catalogProductTypeCharacteristicsDAOList.get( 0 ).getCharacteristicDetails() );
		
		catalogProductTypeCharacteristicsDAOList = new CatalogProductTypeCharacteristicsDAOList( env, tenant, new ArrayList<Integer>(Arrays.asList(1)) );
		
		Assert.assertEquals( catalogProductTypeCharacteristicsDAOList.size(), 0 );
		
		catalogProductTypeCharacteristicsDAOList = new CatalogProductTypeCharacteristicsDAOList( env, tenant, new ArrayList<Integer>(Arrays.asList(4)) );
		
		Assert.assertEquals( catalogProductTypeCharacteristicsDAOList.size(), 1 );
		
	}
	
	@Parameters({"qa"})
	@Test( priority = 3 )
	public void printCatalogProductTypeCharacteristicsDAOList( @Optional("qa") String tenant ) {
		
		logger.info( Log.CHECKING.createMessage( "printCatalogProductTypeCharacteristicsDAOList" , "Catalog Product Type Characteristics DAO List" ) );	
		
		catalogProductTypeCharacteristicsDAOList = new CatalogProductTypeCharacteristicsDAOList( env, tenant, null );
		
		logger.info( catalogProductTypeCharacteristicsDAOList.toString() );
		
	}
	
}
