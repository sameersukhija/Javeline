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
import com.lumata.expression.operators.dao.catalog.CatalogProductTypeCharacteristicsDAOList;
import com.lumata.expression.operators.dao.catalog.CatalogProductTypesDAOList;

public class TestCatalogProductTypes {

	private static final Logger logger = LoggerFactory.getLogger( TestCatalogProductTypes.class );
	private int TIMEOUT = 600000;
	private int ATTEMPT_INTERVAL = 500;
	
	Environment env;
	CatalogProductTypesDAOList catalogProductTypesDAOList;
	
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
		
		logger.info( Log.LOADING.createMessage( "loadCatalogProductTypes" , "Catalog Product Types" ) );	
		
		catalogProductTypesDAOList = new CatalogProductTypesDAOList( env, tenant, null );
		
		logger.info( catalogProductTypesDAOList.toString() );
				
	}	

	@Parameters({"qa"})
	@Test( priority = 2 )
	public void deleteAllCatalogProductTypesDAOList( @Optional("qa") String tenant ) {
		
		logger.info( Log.CHECKING.createMessage( "checkCatalogProductTypesDAOList" , "Catalog Product Types" ) );	
		
		catalogProductTypesDAOList.deleteAll( env, tenant, null );
		
		Assert.assertEquals( catalogProductTypesDAOList.size(), 0 );
		
		logger.info( catalogProductTypesDAOList.toString() );
		
	}
	
	@Parameters({"qa"})
	@Test( priority = 3 )
	public void createFromJSONCatalogProductTypesDAOList( @Optional("qa") String tenant ) {
		
		logger.info( Log.CHECKING.createMessage( "checkCatalogProductTypesDAOList" , "Catalog Product Types" ) );	
		
		catalogProductTypesDAOList = new CatalogProductTypesDAOList( env, tenant, null, "input/catalogue/product_types", "product_types_2.json", IOFileUtils.IOLoadingType.RESOURCE );
		
		Assert.assertEquals( catalogProductTypesDAOList.size(), 2 );
		
		logger.info( catalogProductTypesDAOList.toString() );
		
	}
	
	
	/*
	@Parameters({"qa"})
	@Test( priority = 2 )
	public void checkCatalogProductTypesDAOList( @Optional("qa") String tenant ) {
		
		logger.info( Log.CHECKING.createMessage( "checkCatalogProductTypesDAOList" , "Catalog Product Types" ) );	
		
		Assert.assertEquals( catalogProductTypesDAOList.size(), 1 );
		
		logger.info( catalogProductTypesDAOList.get( 0 ).getTypeName() );
		
		catalogProductTypesDAOList = new CatalogProductTypesDAOList( env, tenant, new ArrayList<Integer>(Arrays.asList(1)) );
		
		Assert.assertEquals( catalogProductTypesDAOList.size(), 0 );
		
		catalogProductTypesDAOList = new CatalogProductTypesDAOList( env, tenant, new ArrayList<Integer>(Arrays.asList(4)) );
		
		Assert.assertEquals( catalogProductTypesDAOList.size(), 1 );
		
	}
	
	@Parameters({"qa"})
	@Test( priority = 3 )
	public void printCatalogProductTypeCharacteristicsDAOList( @Optional("qa") String tenant ) {
		
		logger.info( Log.CHECKING.createMessage( "printCatalogProductTypeCharacteristicsDAOList" , "Catalog Product Type Characteristics DAO List" ) );	
		
		catalogProductTypesDAOList = new CatalogProductTypesDAOList( env, tenant, null );
		
		logger.info( catalogProductTypesDAOList.toString() );
		
	}
	*/
}
