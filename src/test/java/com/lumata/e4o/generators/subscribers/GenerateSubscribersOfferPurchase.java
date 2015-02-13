package com.lumata.e4o.generators.subscribers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.dao.tenant.DAOCatalogOffers;
import com.lumata.e4o.dao.tenant.DAOOfferStock;
import com.lumata.e4o.dao.tenant.DAOSalesChannels;
import com.lumata.e4o.exceptions.GeneratorException;
import com.lumata.e4o.generators.common.Generator;
import com.lumata.e4o.schema.tenant.CatalogOffers;
import com.lumata.e4o.schema.tenant.OfferStock;
import com.lumata.e4o.schema.tenant.SalesChannels;


public class GenerateSubscribersOfferPurchase {

	private static final Logger logger = LoggerFactory.getLogger( GenerateSubscribersOfferPurchase.class );
	
	final boolean GENERATE_FIXED_SUBSCRIBER = true;
	final boolean GENERATE_FIXED_SUBSCRIBER_WITH_OPTION = false;
	final boolean GENERATE_FIXED_SUBSCRIBER_RANDOM_RECHARGE = false;
	final boolean GENERATE_FIXED_SUBSCRIBER_RANDOM_RECHARGE_AND_SUBSCRIBERS = false;
	final boolean GENERATE_INCREMENTAL_SUBSCRIBERS = false;
	final boolean GENERATE_RANDOM_SUBSCRIBERS = false;
	
	NetworkEnvironment env;	
	Server guiServer;
	User superman;
	Mysql mysql;
	
	/* 	Initialize Environment */
	@Parameters({"environment"})
	@BeforeSuite
	public void init( @Optional("E4O_VM") String environment ) throws NetworkEnvironmentException {		
		
		logger.debug( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
			
		guiServer = env.getServer( "actrule" );
		
		superman = guiServer.getUser( "superman" );
		
		mysql = new Mysql( env.getDataSource( "tenant" ) );
		
	}

	@Test( enabled = GENERATE_FIXED_SUBSCRIBER )
	public void generateFixedSubscriber() throws GeneratorException {
		
		short offerId = 2;
		
		ArrayList<OfferStock> offerStock = DAOOfferStock.getInstance( mysql ).getOfferStockByOffer( offerId );
		
		if( offerStock.size() >= 2 ) {
			
			SalesChannels salesChannel = DAOSalesChannels.getInstance( mysql ).getSalesChannelById( offerStock.get( 1 ).getChannelId() );
			
			CatalogOffers offer = DAOCatalogOffers.getInstance( mysql ).getCatalogOffersById( offerStock.get( 0 ).getOfferId() );
			
			final Long FIXED_MSISDN = 3399900001L;
			
			for( int available = 1; available <= offerStock.get( 1 ).getAvailable(); available++ ) {
			
				Generator.subscribers()
							.server( guiServer )
							.user( superman )
							.msisdnFixed( FIXED_MSISDN )
							.xmlrpcPurchaseOffer( offer.getOfferName(), salesChannel.getChannelName() );
			
			}
			
		}
		
	}
	
//	@Test( enabled = GENERATE_FIXED_SUBSCRIBER_WITH_OPTION )
//	public void generateFixedSubscriberWithOptionalParameter() throws GeneratorException {
//		
//		final Long FIXED_MSISDN = 3399900001L;
//		final Long RECHARGE_TO_GENERATE = 1L;
//		final SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
//		Calendar today = Calendar.getInstance(); 
//				
//		Generator.subscribers()
//					.server( guiServer )
//					.user( superman )
//					.msisdnFixed( FIXED_MSISDN )
//					.xmlrpcRecharge( RECHARGE_TO_GENERATE, parameter( recharge, true ), parameter( event_date, sdf.format( today.getTime() ) ) );
//					
//	}
//	
//	@Test( enabled = false )
//	public void generateFixedMultiSubscribers() throws GeneratorException {
//		
//		final Long[] FIXED_MSISDN = { 393669393643L, 393356848728L, 393280654379L  };
//		final Long RECHARGE_TO_GENERATE = 1L;
//		final SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
//		Calendar today = Calendar.getInstance(); 
//		
//		for( Long msisdn : FIXED_MSISDN ) {
//			
//			Generator.subscribers()
//						.server( guiServer )
//						.user( superman )
//						.msisdnFixed( msisdn )
//						.xmlrpcRecharge( RECHARGE_TO_GENERATE, parameter( recharge, true ), parameter( event_date, sdf.format( today.getTime() ) ) );
//						
//		}
//		
//	}
//	
//	@Test( enabled = GENERATE_INCREMENTAL_SUBSCRIBERS )
//	public void generateIncrementalSubscribers() throws GeneratorException {
//		
//		final Long STARTED_MSISDN = 3399900001L;
//		final Integer INCREMENT = 1;
//		final Boolean HAS_SMS_CHANNEL = true;
//		final Boolean HAS_MAIL_CHANNEL = true;
//		final Integer REPEAT = 20;
//		final Long RECHARGE_TO_GENERATE = 5L;
//		
//		Generator.subscribers()
//					.server( guiServer )
//					.user( superman )
//					.msisdnIncremental( STARTED_MSISDN, INCREMENT )
//					.subscriberHasSMSChannel( HAS_SMS_CHANNEL )
//					.subscriberHasMAILChannel( HAS_MAIL_CHANNEL )
//					.repeat( REPEAT )
//					.xmlrpcRecharge( RECHARGE_TO_GENERATE );
//		
//	}
//	
//	@Test( enabled = GENERATE_FIXED_SUBSCRIBER_RANDOM_RECHARGE )
//	public void generateFixedSubscriberRandomRecharge() throws GeneratorException {
//		
//		final Long FIXED_MSISDN = 3399900001L;
//		final Integer MIN_EVENTS = 1;
//		final Integer MAX_EVENTS = 1;
//				
//		Generator.subscribers()
//					.server( guiServer )
//					.user( superman )
//					.msisdnFixed( FIXED_MSISDN )
//					.minRandomEvents( MIN_EVENTS )
//					.maxRandomEvents( MAX_EVENTS )
//					.xmlrpcRecharge();
//					
//	}
//	
//	@Test( enabled = GENERATE_FIXED_SUBSCRIBER_RANDOM_RECHARGE_AND_SUBSCRIBERS )
//	public void generateFixedSubscriberRandomRechargeAndSubscribers() throws GeneratorException {
//		
//		final Long STARTED_MSISDN = 3399900002L;
//		final Integer INCREMENT = 1;
//		final Integer REPEAT = 49;
//		final Integer MIN_EVENTS = 20;
//		final Integer MAX_EVENTS = 50;
//				
//		Generator.subscribers()
//					.server( guiServer )
//					.user( superman )
//					.msisdnIncremental( STARTED_MSISDN, INCREMENT )
//					.minRandomEvents( MIN_EVENTS )
//					.maxRandomEvents( MAX_EVENTS )
//					.repeat( REPEAT )
//					.xmlrpcRecharge();
//					
//	}
	
	@AfterSuite
	public void end() {}
	
}
