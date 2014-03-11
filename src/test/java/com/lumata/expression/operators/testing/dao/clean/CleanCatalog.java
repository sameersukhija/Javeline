package com.lumata.expression.operators.testing.dao.clean;

import static com.lumata.common.testing.orm.Query.delete;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.entities.*;
import com.lumata.expression.operators.exceptions.CommoditiesException;
import com.lumata.expression.operators.exceptions.OfferException;

public class CleanCatalog {

	private static final Logger logger = LoggerFactory.getLogger( CleanCatalog.class );
	
	Environment env;
	String tenant;
	Mysql mysql;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "tenant", "user"})
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("qa") String tenant, @Optional("superman") String user ) throws EnvironmentException, OfferException, CommoditiesException, JSONSException, IOFileException {		
		
		this.tenant = tenant;
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		// Create environment configuration
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
			
	}
	
	@Test
	public void cleanCatalog() {
		
		Mysql mysql = new Mysql( env.getDataSource( this.tenant ) );
		
		CatalogOfferContent coc = new CatalogOfferContent();
		String deleteQuery = delete().from( coc ).build();
		mysql.execUpdate( deleteQuery );
		
		CatalogOfferPrice cop = new CatalogOfferPrice();
		deleteQuery = delete().from( cop ).build();
		mysql.execUpdate( deleteQuery );
		
		CatalogOfferPriceChannels copc = new CatalogOfferPriceChannels();
		deleteQuery = delete().from( copc ).build();
		mysql.execUpdate( deleteQuery );
		
		CatalogOfferPricePrices copp = new CatalogOfferPricePrices();
		deleteQuery = delete().from( copp ).build();
		mysql.execUpdate( deleteQuery );
		
		CatalogOffers co = new CatalogOffers();
		deleteQuery = delete().from( co ).build();
		mysql.execUpdate( deleteQuery );
		
		CatalogRelatedOffers cro = new CatalogRelatedOffers();
		deleteQuery = delete().from( cro ).build();
		mysql.execUpdate( deleteQuery );
		
		OfferStock os = new OfferStock();
		deleteQuery = delete().from( os ).build();
		mysql.execUpdate( deleteQuery );
		
		CatalogProductCharacteristics cpc = new CatalogProductCharacteristics();
		deleteQuery = delete().from( cpc ).build();
		mysql.execUpdate( deleteQuery );
		
		CatalogProductSpecificCharacteristics cpsc = new CatalogProductSpecificCharacteristics();
		deleteQuery = delete().from( cpsc ).build();
		mysql.execUpdate( deleteQuery );
		
		CatalogProductTypeCharacteristics cptc = new CatalogProductTypeCharacteristics();
		deleteQuery = delete().from( cptc ).build();
		mysql.execUpdate( deleteQuery );
		
		CatalogProductTypes cpt = new CatalogProductTypes();
		deleteQuery = delete().from( cpt ).build();
		mysql.execUpdate( deleteQuery );
		
		CatalogProducts cp = new CatalogProducts();
		deleteQuery = delete().from( cp ).build();
		mysql.execUpdate( deleteQuery );
		
		CatalogRelatedProducts crp = new CatalogRelatedProducts();
		deleteQuery = delete().from( crp ).build();
		mysql.execUpdate( deleteQuery );
		
		ProductStock ps = new ProductStock();
		deleteQuery = delete().from( ps ).build();
		mysql.execUpdate( deleteQuery );
		
	}
	
}
