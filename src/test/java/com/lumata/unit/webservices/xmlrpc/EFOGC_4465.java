package com.lumata.unit.webservices.xmlrpc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.e4o.dao.tenant.DAOCatalogOffers;
import com.lumata.e4o.dao.tenant.DAOOfferStock;
import com.lumata.e4o.dao.tenant.DAOOffoptimOfferHistory;
import com.lumata.e4o.dao.tenant.DAOSalesChannels;
import com.lumata.e4o.dao.tenant.DAOSubscribers;
import com.lumata.e4o.dao.tenant.DAOConf.ConfTag;
import com.lumata.e4o.exceptions.GeneratorException;
import com.lumata.e4o.generators.common.Generator;
import com.lumata.e4o.schema.tenant.CatalogOffers;
import com.lumata.e4o.schema.tenant.OfferStock;
import com.lumata.e4o.schema.tenant.OffoptimOfferHistory;
import com.lumata.e4o.schema.tenant.SalesChannels;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCMysqlMaster;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.*;

@TCMysqlMaster
public class EFOGC_4465 extends ParentTestCase {

	private String channelNameA = "Ch A";
	private String channelNameB = "Ch B";
	private String offerNameA1 = "OFFSBA1";
	private String offerNameA2 = "OFFSBA2";
	private String offerNameB1 = "OFFSBB1";
	private String offerNameB2 = "OFFSBB2";
	
	private SalesChannels channelA;
	private SalesChannels channelB;
	private CatalogOffers offerA1;
	private CatalogOffers offerA2;
	private CatalogOffers offerB1;
	private CatalogOffers offerB2;
		
	private class StockBasedAlgo {
	
		private Integer todayConsumed;
		private Integer yesterdayConsumed;
		private Integer average_2Be_consumed_per_day;
		private Integer temporaryScore;
		private Integer estimatedAvailable;
		private Integer allAvailable;
		private Integer relativeWeight;
		private Integer score;
		
		public StockBasedAlgo() {}
		
		public double getAverage2BeConsumedPerDay( CatalogOffers offer, SalesChannels channel ) {
			
			OfferStock offerStock = DAOOfferStock.getInstance(mysqlMaster).getOfferStock(offer.getOfferId(), channel.getChannelId());
			
			//return (double)Math.round(offerStock.getAvailable());
			
			return 0;
			
		}
		
		public double getTemporaryScore( CatalogOffers offer, OffoptimOfferHistory offerHistoryToday, OffoptimOfferHistory offerHistoryYesterday ) {
			
			return ( (double)offerHistoryToday.getAverage2BeConsumedPerDay() - (double)offerHistoryToday.getConsumed() / offerHistoryYesterday.getConsumed() );
			
		}
		
		public double getRelativeWeight( CatalogOffers offer, CatalogOffers... involvedOffers ) {
			
			Long available = 0L;
			Long allAvailable = 0L;
			
			for( CatalogOffers involvedOffer: involvedOffers ) {
				ArrayList<OfferStock> offerStockList = DAOOfferStock.getInstance(mysqlMaster).getOfferStockList(offer.getOfferId());
				for( OfferStock offerStock : offerStockList ) {
					if( offerStock.getChannelId() != 0 ) {
						allAvailable += offerStock.getAvailable();
						if( involvedOffer.getOfferId() == offer.getOfferId() ) {
							available = offerStock.getAvailable();
						}
					}
				}				 
			}
					
			return (double)( available / allAvailable );
			
		}
		
		public double getScore( CatalogOffers offer, SalesChannels channel, Calendar aggDate, CatalogOffers... involvedOffers ) {
			
			Calendar aggDateYesterday = (Calendar)aggDate.clone();
			aggDateYesterday.add( Calendar.DAY_OF_MONTH, -1 );
			
			OffoptimOfferHistory offerHistoryToday = DAOOffoptimOfferHistory.getInstance(mysqlMaster).getOffoptimOfferHistory( offer.getOfferId(), channel.getChannelId(), aggDate ); 
			OffoptimOfferHistory offerHistoryYesterday = DAOOffoptimOfferHistory.getInstance(mysqlMaster).getOffoptimOfferHistory( offer.getOfferId(), channel.getChannelId(), aggDateYesterday ); 
			
			System.out.println( offerHistoryToday.getOfferId() );
			System.out.println( offerHistoryYesterday.getOfferId() );
			
			Double temporaryScore = getTemporaryScore( offer, offerHistoryToday, offerHistoryYesterday );
			Double relativeWeight = getRelativeWeight( offer, involvedOffers );
			
			System.out.println( temporaryScore );
			System.out.println( relativeWeight );
			
			return ( temporaryScore * relativeWeight );
			
		}
	
	}
	
	@Parameters({"environment", "tenant"})
	@BeforeClass
	public void initTest() throws NetworkEnvironmentException, GeneratorException {		
		
		this.channelA = DAOSalesChannels.getInstance(mysqlMaster).getSalesChannelByName( channelNameA );
		this.channelB = DAOSalesChannels.getInstance(mysqlMaster).getSalesChannelByName( channelNameB );
				
		this.offerA1 = DAOCatalogOffers.getInstance(mysqlMaster).getCatalogOffersByName( offerNameA1 );
		this.offerA2 = DAOCatalogOffers.getInstance(mysqlMaster).getCatalogOffersByName( offerNameA2 );
		this.offerB1 = DAOCatalogOffers.getInstance(mysqlMaster).getCatalogOffersByName( offerNameB1 );
		this.offerB2 = DAOCatalogOffers.getInstance(mysqlMaster).getCatalogOffersByName( offerNameB2 );
						
	}
	
	@Test
	public void test1() {
		
		Calendar aggDate = new GregorianCalendar(2015, Calendar.JANUARY, 5);
		
		StockBasedAlgo stockBasedAlgo = new StockBasedAlgo();
		
		System.out.println( String.format("operation : %s", stockBasedAlgo.getScore( this.offerA1, this.channelA, aggDate, this.offerA1, this.offerA2, this.offerB1, this.offerB2 ) ) );
		
	}
	
	
	
//	@Test(enabled=TEST_ENABLED, priority = 1 )
//	public void callXMLRPCCRequest() throws Exception {
//		
//		final String msisdn = "3399900001";
//		final String token_code = "N9AT7";
//		
//		XMLRPCRequest.offeroptimizer_allocate().call( 	
//			guiServer, 
//			xmlrpcBody(
//				authentication( user ),
//				string( msisdn ),
//				string( token_code )
//			),
//			xmlrpcOptions(
//				storeRequestAsResource( "xmlrpc/request/", "request.xml" ),
//				storeResponseAsResource( "xmlrpc/response/", "response.xml" )	
//			)
//		);
//		
//	}
	
}