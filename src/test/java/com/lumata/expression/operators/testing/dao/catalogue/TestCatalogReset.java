package com.lumata.expression.operators.testing.dao.catalogue;

import java.sql.SQLException;

import org.testng.Assert;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.database.MysqlUtils;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.dao.catalog.CatalogOfferContentDAOList;
import com.lumata.expression.operators.dao.catalog.CatalogOfferPriceDAOList;
import com.lumata.expression.operators.dao.catalog.CatalogOffersDAOList;
import com.lumata.expression.operators.dao.catalog.CatalogPricesDAOList;
import com.lumata.expression.operators.dao.catalog.CatalogPricesListDAOList;
import com.lumata.expression.operators.dao.catalog.CatalogProductCharacteristicsDAOList;
import com.lumata.expression.operators.dao.catalog.CatalogProductSpecificCharacteristicsDAOList;
import com.lumata.expression.operators.dao.catalog.CatalogProductTypeCharacteristicsDAOList;
import com.lumata.expression.operators.dao.catalog.CatalogProductTypesDAOList;
import com.lumata.expression.operators.dao.catalog.CatalogProductsDAOList;
import com.lumata.expression.operators.dao.catalog.CatalogRelatedOffersDAOList;
import com.lumata.expression.operators.dao.catalog.CatalogRelatedProductsDAOList;
import com.lumata.expression.operators.dao.catalog.OfferStockDAOList;
import com.lumata.expression.operators.dao.catalog.ProductStockDAOList;

public class TestCatalogReset {

	Environment env;
	Mysql mysql;
	
	@Parameters({"environment", "tenant"})
	@BeforeMethod
	public void init( @Optional("E4O_VM") String environment, @Optional("tenant") String tenant ) throws EnvironmentException {
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		mysql = new Mysql( env.getDataSource( tenant ) );
		
	}
	
	@Parameters({"tenant"})
	@Test( priority = 1, enabled = true )
	public void resetCatalogOfferContent( @Optional("tenant") String tenant ) throws SQLException {
		
		CatalogOfferContentDAOList catalogOfferContentDAOList = new CatalogOfferContentDAOList();
		
		catalogOfferContentDAOList.deleteAll( env, tenant, null );
		
		Assert.assertEquals( catalogOfferContentDAOList.size() , 0 );
		
		Assert.assertEquals( MysqlUtils.getTableSize( "catalog_offer_content" , mysql ) , 0 );
		
	}
	
	@Parameters({"tenant"})
	@Test( priority = 2, enabled = true )
	public void resetCatalogOfferPrice( @Optional("tenant") String tenant ) throws SQLException {
		
		CatalogOfferPriceDAOList catalogOfferPriceDAOList = new CatalogOfferPriceDAOList();
		
		catalogOfferPriceDAOList.deleteAll( env, tenant, null );
		
		Assert.assertEquals( catalogOfferPriceDAOList.size(), 0 );
		
		Assert.assertEquals( MysqlUtils.getTableSize( "catalog_offer_price" , mysql ) , 0 );
		
	}
	
	@Parameters({"tenant"})
	@Test( priority = 3, enabled = true )
	public void resetCatalogOffers( @Optional("tenant") String tenant ) throws SQLException {
		
		CatalogOffersDAOList catalogOffersDAOList = new CatalogOffersDAOList();
		
		catalogOffersDAOList.deleteAll( env, tenant, null );
		
		Assert.assertEquals( catalogOffersDAOList.size(), 0 );
		
		Assert.assertEquals( MysqlUtils.getTableSize( "catalog_offers" , mysql ) , 0 );
		
	}
	
	@Parameters({"tenant"})
	@Test( priority = 4, enabled = true )
	public void resetCatalogPrices( @Optional("tenant") String tenant ) throws SQLException {
		
		CatalogPricesDAOList catalogPricesDAOList = new CatalogPricesDAOList();
		
		catalogPricesDAOList.deleteAll( env, tenant, null );
		
		Assert.assertEquals( catalogPricesDAOList.size(), 0 );
		
		Assert.assertEquals( MysqlUtils.getTableSize( "catalog_offer_price" , mysql ) , 0 );
		
	}
	
	@Parameters({"tenant"})
	@Test( priority = 5, enabled = true )
	public void resetCatalogPricesList( @Optional("tenant") String tenant ) throws SQLException {
		
		CatalogPricesListDAOList catalogPricesListDAOList = new CatalogPricesListDAOList();
		
		catalogPricesListDAOList.deleteAll( env, tenant, null );
		
		Assert.assertEquals( catalogPricesListDAOList.size(), 0 );
		
		Assert.assertEquals( MysqlUtils.getTableSize( "catalog_offer_price_prices" , mysql ) , 0 );
		
	}
	
	@Parameters({"tenant"})
	@Test( priority = 6, enabled = true )
	public void resetCatalogProductCharacteristics( @Optional("tenant") String tenant ) throws SQLException {
		
		CatalogProductCharacteristicsDAOList catalogProductCharacteristicsDAOList = new CatalogProductCharacteristicsDAOList();
		
		catalogProductCharacteristicsDAOList.deleteAll( env, tenant, null );
		
		Assert.assertEquals( catalogProductCharacteristicsDAOList.size(), 0 );
		
		Assert.assertEquals( MysqlUtils.getTableSize( "catalog_product_characteristics" , mysql ) , 0 );
		
	}
	
	@Parameters({"tenant"})
	@Test( priority = 7, enabled = true )
	public void resetCatalogProductSpecificCharacteristics( @Optional("tenant") String tenant ) throws SQLException {
		
		CatalogProductSpecificCharacteristicsDAOList catalogProductSpecificCharacteristicsDAOList = new CatalogProductSpecificCharacteristicsDAOList();
		
		catalogProductSpecificCharacteristicsDAOList.deleteAll( env, tenant, null );
		
		Assert.assertEquals( catalogProductSpecificCharacteristicsDAOList.size(), 0 );
		
		Assert.assertEquals( MysqlUtils.getTableSize( "catalog_product_specific_characteristics" , mysql ) , 0 );
		
	}
	
	@Parameters({"tenant"})
	@Test( priority = 8, enabled = true )
	public void resetCatalogProductTypeCharacteristics( @Optional("tenant") String tenant ) throws SQLException {
		
		CatalogProductTypeCharacteristicsDAOList catalogProductTypeCharacteristicsDAOList = new CatalogProductTypeCharacteristicsDAOList();
		
		catalogProductTypeCharacteristicsDAOList.deleteAll( env, tenant, null );
		
		Assert.assertEquals( catalogProductTypeCharacteristicsDAOList.size(), 0 );
		
		Assert.assertEquals( MysqlUtils.getTableSize( "catalog_product_type_characteristics" , mysql ) , 0 );
		
	}
	
	@Parameters({"tenant"})
	@Test( priority = 9, enabled = true )
	public void resetCatalogProductTypes( @Optional("tenant") String tenant ) throws SQLException {
		
		CatalogProductTypesDAOList catalogProductTypesDAOList = new CatalogProductTypesDAOList();
		
		catalogProductTypesDAOList.deleteAll( env, tenant, null );
		
		Assert.assertEquals( catalogProductTypesDAOList.size(), 0 );
		
		Assert.assertEquals( MysqlUtils.getTableSize( "catalog_product_types" , mysql ) , 0 );
		
	}
	
	@Parameters({"tenant"})
	@Test( priority = 10, enabled = true )
	public void resetCatalogProducts( @Optional("tenant") String tenant ) throws SQLException {
		
		CatalogProductsDAOList catalogProductsDAOList = new CatalogProductsDAOList();
		
		catalogProductsDAOList.deleteAll( env, tenant, null );
		
		Assert.assertEquals( catalogProductsDAOList.size(), 0 );
		
		Assert.assertEquals( MysqlUtils.getTableSize( "catalog_products" , mysql ) , 0 );
		
	}
	
	@Parameters({"tenant"})
	@Test( priority = 11, enabled = true )
	public void resetCatalogRelatedOffers( @Optional("tenant") String tenant ) throws SQLException {
		
		CatalogRelatedOffersDAOList catalogRelatedOffersDAOList = new CatalogRelatedOffersDAOList();
		
		catalogRelatedOffersDAOList.deleteAll( env, tenant, null );
		
		Assert.assertEquals( catalogRelatedOffersDAOList.size(), 0 );
		
		Assert.assertEquals( MysqlUtils.getTableSize( "catalog_related_offers" , mysql ) , 0 );
		
	}
	
	@Parameters({"tenant"})
	@Test( priority = 12, enabled = true )
	public void resetCatalogRelatedProducts( @Optional("tenant") String tenant ) throws SQLException {
		
		CatalogRelatedProductsDAOList catalogRelatedProductsDAOList = new CatalogRelatedProductsDAOList();
		
		catalogRelatedProductsDAOList.deleteAll( env, tenant, null );
		
		Assert.assertEquals( catalogRelatedProductsDAOList.size(), 0 );
		
		Assert.assertEquals( MysqlUtils.getTableSize( "catalog_related_products" , mysql ) , 0 );
		
	}
	
	@Parameters({"tenant"})
	@Test( priority = 13, enabled = true )
	public void resetProductStock( @Optional("tenant") String tenant ) throws SQLException {
		
		ProductStockDAOList productStockDAOList = new ProductStockDAOList();
		
		productStockDAOList.deleteAll( env, tenant, null );
		
		Assert.assertEquals( productStockDAOList.size(), 0 );
		
		Assert.assertEquals( MysqlUtils.getTableSize( "product_stock" , mysql ) , 0 );
		
	}
	
	@Parameters({"tenant"})
	@Test( priority = 14, enabled = true )
	public void resetOfferStock( @Optional("tenant") String tenant ) throws SQLException {
		
		OfferStockDAOList offerStockDAOList = new OfferStockDAOList();
		
		offerStockDAOList.deleteAll( env, tenant, null );
		
		Assert.assertEquals( offerStockDAOList.size(), 0 );
		
		Assert.assertEquals( MysqlUtils.getTableSize( "offer_stock" , mysql ) , 0 );
		
	}
		
}
