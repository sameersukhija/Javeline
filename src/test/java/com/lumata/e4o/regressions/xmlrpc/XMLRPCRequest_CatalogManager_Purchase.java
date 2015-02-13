package com.lumata.e4o.regressions.xmlrpc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.dao.tenant.DAOCatalogOffers;
import com.lumata.e4o.dao.tenant.DAOSalesChannels;
import com.lumata.e4o.exceptions.XMLRPCException;
import com.lumata.e4o.regressions.RegressionSuite;
import com.lumata.e4o.schema.tenant.CatalogOffers;
import com.lumata.e4o.schema.tenant.SalesChannels;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.sleep;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.storeRequestAsResource;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.storeResponseAsResource;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.*;
import static com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidatorMethods.fault;
import static org.hamcrest.Matchers.equalTo;

public class XMLRPCRequest_CatalogManager_Purchase {
	
	private static final Logger logger = LoggerFactory.getLogger( XMLRPCRequest_CatalogManager_Purchase.class );
	
	NetworkEnvironment env;
	Server actruleServer;
	User superman;
	Mysql mysql;
			
	/* 	Initialize Environment */
	@Parameters({"environment", "gui_server", "user"})
	@BeforeClass
	public void init( @Optional("E4O_VM") String environment, @Optional("actrule") String gui_server, @Optional("superman") String user ) throws NetworkEnvironmentException {		
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		actruleServer = env.getServer( gui_server );
		
		superman = actruleServer.getUser( user );
		
		mysql = new Mysql( env.getDataSource( "tenant" ) );
		
	}
	
	@Test(enabled=true, priority = 1 )
	public void execFalse() throws Exception {
			
		Assert.assertTrue(false);
				
	}
	
	@Test(enabled=true, priority = 1 )
	public void execTrue() throws Exception {
		
		Thread.sleep( 500 );
		Assert.assertTrue(true);
				
	}
	
	@Test(enabled=false, priority = 1 )
	public void callXMLRPCCRequestNew() throws Exception {
		
		ArrayList<CatalogOffers> simpleOffers = DAOCatalogOffers.getInstance( mysql ).getAllSimpleCatalogOffers();
		ArrayList<CatalogOffers> oneTimeUseOffers = DAOCatalogOffers.getInstance( mysql ).getAllOneTimeUseCatalogOffers();
		ArrayList<CatalogOffers> unlimitedUseOffers = DAOCatalogOffers.getInstance( mysql ).getAllUnlimitedUseCatalogOffers();
		
		SalesChannels ChA = DAOSalesChannels.getInstance( mysql ).getSalesChannelByName( "Ch A" );		
		
		System.out.println( simpleOffers.size() );
		System.out.println( oneTimeUseOffers.size() );
		System.out.println( unlimitedUseOffers.size() );
		
		CatalogOffers OFFUVUD2 = null;
		
		for( CatalogOffers offer : unlimitedUseOffers ) {
			
			if( offer.getOfferName().equals( "OFFUVUD2" ) ) {
				
				OFFUVUD2 = offer;
				
				break;
				
			}
			
		}
		
		System.out.println( OFFUVUD2.getOfferName() );
		
		Long msisdn = 3399900001L;
		
		
		
		//xmlrpcPurchase( msisdn, OFFUVUD2.getOfferName(), ChA.getChannelName() );
		
		
		
//		XMLRPCRequest.catalogmanager_purchase().call( 
//			actruleServer, 
//			xmlrpcBody(
//				authentication( superman ),
//				string("3399900001"),
//				string("OFFOTV5"),
//				string("Ch A"),
//				arrayProductPrices( 
//					//price( 10, "internal points" )	
//				),
//				arraySelectedProducts( 
//					/* product() */	
//				),
//				string(1)
//			),
//			xmlrpcOptions(
//				sleep( 100L ),
//				storeRequestAsResource( "xmlrpc/request/", "request.xml" ),
//				storeResponseAsResource( "xmlrpc/response/", "response.xml" )	
//			)
//		);
		
	}
	
	@Test(enabled=false, priority = 2 )
	public void purchaseUnlimitedUseOffers() throws Exception {
		
		ArrayList<CatalogOffers> unlimitedUseOffers = DAOCatalogOffers.getInstance( mysql ).getAllUnlimitedUseCatalogOffers();
		
		SalesChannels ChA = DAOSalesChannels.getInstance( mysql ).getSalesChannelByName( "Ch A" );		
		
		logger.info( "UnlimitedUseOffers ( " + unlimitedUseOffers.size() + " )" );
		
		
		System.out.println( unlimitedUseOffers.size() );
		
		CatalogOffers OFFUVUD2 = null;
		
		for( CatalogOffers offer : unlimitedUseOffers ) {
			
			if( offer.getOfferName().equals( "OFFUVUD2" ) ) {
				
				OFFUVUD2 = offer;
				
				break;
				
			}
			
		}
		
		System.out.println( OFFUVUD2.getOfferName() );
		
		Long msisdn = 3399900001L;
		
		//xmlrpcPurchase( msisdn, OFFUVUD2.getOfferName(), ChA.getChannelName() );
		
		
		
//		XMLRPCRequest.catalogmanager_purchase().call( 
//			actruleServer, 
//			xmlrpcBody(
//				authentication( superman ),
//				string("3399900001"),
//				string("OFFOTV5"),
//				string("Ch A"),
//				arrayProductPrices( 
//					//price( 10, "internal points" )	
//				),
//				arraySelectedProducts( 
//					/* product() */	
//				),
//				string(1)
//			),
//			xmlrpcOptions(
//				sleep( 100L ),
//				storeRequestAsResource( "xmlrpc/request/", "request.xml" ),
//				storeResponseAsResource( "xmlrpc/response/", "response.xml" )	
//			)
//		);
		
	}
	
	private void xmlrpcPurchase( Long msisdn, String offerName, String channelName ) throws XMLRPCException, Exception {
		
		XMLRPCRequest.catalogmanager_purchase().call( 
			actruleServer, 
			xmlrpcBody(
				authentication( superman ),
				string( msisdn ),
				string( offerName ),
				string( channelName ),
				arrayProductPrices( 
					//price( 10, "internal points" )	
				),
				arraySelectedProducts( 
					/* product() */	
				),
				string(1)
			),
			xmlrpcOptions(
				sleep( 100L )	
			)
		);
		
	}
	
}
