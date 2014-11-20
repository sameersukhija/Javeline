package com.lumata.e4o.gui.common;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.xmlrpcBody;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.xmlrpcOptions;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.sleep;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.storeRequestAsResource;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.storeResponseAsResource;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.arrayInt;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.authentication;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.custoEvent;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.string;
import static com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCParameter.parameter;
import static com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCParameter.ParameterType.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.e4o.gui.xmlrpc.XMLRPCAllocate;
import com.lumata.e4o.gui.xmlrpc.XMLRPCAllocate.Offer;
import com.lumata.e4o.gui.xmlrpc.XMLRPCTokenList;
import com.lumata.e4o.gui.xmlrpc.XMLRPCTokenList.Token;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.EventType;

/**
 *	This class contains tests for O2 custom reporter testing
 */
public class O2ReporterFiller extends RegressionSuiteXmlrpcCore {
	
	/**
	 * Active tokens list
	 */
	private static List<String> currentActiveTokens = null; 

	/**
	 * Allocated tokens list
	 */
	private static List<String> currentAllocatedTokens = null; 
	
	/**
	 * Consumed tokens list
	 */
	private static List<String> currentConsumedTokens = null;
	
	/**
	 * Default value tracks that user does not provide an external group offer ids
	 */
	private final static String NO_EXT_OFFER_LIST__ = "no offer list";
	
	/**
	 * This map tracks allocation information
	 */
	private static Map<String,StringBuilder> token2Allocation = null;
	
	/**
	 * Surrounded token status
	 */
	enum TokenStatus {
		
		ACTIVE("active"),
		ALLOCATED("offers_allocated"),
		CONSUMED("consumed");
		
		private String value = null;
		private TokenStatus(String in) { value = in; }
		public String toString() { return value; }
	};
	
	/**
	 * 
	 * @param msisdn
	 * @param tokens2BeGenerated
	 * @throws Exception 
	 */
	
	@Test
	@Parameters({ "msisdn", "tokens2BeGenerated"})
	public void generateTokens(@Optional("393492135019") String msisdn, @Optional("10") Integer tokens2BeGenerated) throws Exception {
		
		Reporter.log( "Generate "+tokens2BeGenerated+" tokens for subscriber "+ msisdn , PRINT2STDOUT__);

		final SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		Calendar today = Calendar.getInstance(); 
		
		for( int i = 0; i < tokens2BeGenerated; i++ ) {

			XMLRPCRequest.eventmanager_generateCustomEvent().call( 	
					gui, 
					xmlrpcBody(
						authentication( user ),
						custoEvent( Long.parseLong(msisdn), 
									EventType.revenue,
									parameter( recharge, Boolean.TRUE ),
									parameter( event_time, sdf.format( today.getTime() ) )
						)
					),
					xmlrpcOptions( 
						sleep( XMLRPC_CALL_DELAY ),
						storeRequestAsResource( xmlrpcLogFolder, "request_"+testTime+"_generateCustomEvent_"+i+".xml" ),
						storeResponseAsResource( xmlrpcLogFolder, "response_"+testTime+"_generateCustomEvent_"+i+".xml" )	
					)
			);		
		}	
		
		Thread.sleep(2000L);
	}	

	/**
	 * 
	 * @param msisdn
	 * @throws Exception
	 */

	@Parameters("msisdn")
	@Test
	public void getTokensList(@Optional("393492135019") String msisdn) throws Exception {

		refreshTokenStatus( msisdn, false, true);
		
		Reporter.log( "###############", PRINT2STDOUT__);
		Reporter.log( "##### The subscriber "+ msisdn +" has : ");
		Reporter.log( "##### " + currentActiveTokens.size() + " active tokens.", PRINT2STDOUT__);
		Reporter.log( "##### " + currentAllocatedTokens.size() + " allocated tokens.", PRINT2STDOUT__);
		Reporter.log( "##### " + currentConsumedTokens.size() + " consumed tokens.", PRINT2STDOUT__);
		Reporter.log( "###############", PRINT2STDOUT__);
	}
	
	/**
	 * 
	 * @param msisdn
	 * @throws Exception
	 */

	@Test( dependsOnMethods={"getTokensList"} )
	@Parameters("msisdn")
	public void allocateToken(@Optional("393492135019") String msisdn) throws Exception {
		
		Reporter.log( "###############", PRINT2STDOUT__);
		Reporter.log( "##### The subscriber "+ msisdn +" has " + currentActiveTokens.size() + " active tokens.", PRINT2STDOUT__);
		Reporter.log( "##### Allocate each token.", PRINT2STDOUT__);
		Reporter.log( "##### Tokens ready to be allocated : " + currentActiveTokens, PRINT2STDOUT__);
		Reporter.log( "###############", PRINT2STDOUT__);
		
		for (String code : currentActiveTokens) {
		
			Reporter.log( "Token code : " + code, PRINT2STDOUT__);
	
			XMLRPCAllocate offerPack = new XMLRPCAllocate(XMLRPCRequest.offeroptimizer_allocate().call( 
					gui, 
					xmlrpcBody(
						authentication( user ),
						string( msisdn ),
						string( code )
					),
					xmlrpcOptions(
						sleep( XMLRPC_CALL_DELAY ),
						storeRequestAsResource( xmlrpcLogFolder, "request_"+testTime+"_allocate_"+code+".xml" ),
						storeResponseAsResource( xmlrpcLogFolder, "response_"+testTime+"_allocate_"+code+".xml" )	
					)
			).getResponse().getEntity().toString()).parse();
			
			List<Offer> offers = offerPack.getOfferList();
			
			StringBuilder allocatedOffer = new StringBuilder();
			
			String prefix = "";
			
			for (Offer offer : offers) {
				allocatedOffer.append(prefix);
				prefix = ";";
				allocatedOffer.append(offer.getId());
			}

			Reporter.log( "###############", PRINT2STDOUT__);
			Reporter.log( "##### The subscriber "+ msisdn +" has allocated token " + code, PRINT2STDOUT__);
			Reporter.log( "##### Offer List : " + ( allocatedOffer.length() != 0 ? allocatedOffer : "{empty}"), PRINT2STDOUT__);
			Reporter.log( "###############", PRINT2STDOUT__);
			
			token2Allocation.put( code, allocatedOffer);
		}
	}
	
	/**
	 * 
	 * @param msisdn
	 * @param offerIds
	 * @throws Exception
	 */
	
	@Test( dependsOnMethods={"getTokensList"} )
	@Parameters({"msisdn","offerIds"})
	public void acceptToken(@Optional("393492135019") String msisdn, @Optional(NO_EXT_OFFER_LIST__) String offerIds) throws Exception {
		
		Reporter.log( "###############", PRINT2STDOUT__);		
		Reporter.log( "##### The subscriber "+ msisdn +" has " + currentAllocatedTokens.size() + " allocted tokens.", PRINT2STDOUT__);
		if ( offerIds.equals(NO_EXT_OFFER_LIST__) )
			Reporter.log( "##### Each token will be purchased according allocation (if avaiable)", PRINT2STDOUT__);
		else
			Reporter.log( "##### Purchase one offer among : " + offerIds.replace(";", " "), PRINT2STDOUT__);
		Reporter.log( "##### Tokens ready to be purchased : " + currentAllocatedTokens, PRINT2STDOUT__);
		Reporter.log( "###############", PRINT2STDOUT__);
		
		String[] extOfferIdArray = offerIds.equals(NO_EXT_OFFER_LIST__) ? null : offerIds.split(";");
		
		Random randomGenerator = new Random();
		
		for (String code : currentAllocatedTokens) {
		
			String[] offerIdArray = null;
			
			// force external
			if ( extOfferIdArray != null )
				offerIdArray = extOfferIdArray;
			else // no ext
				if ( token2Allocation.containsKey(code) ) // allocation
					offerIdArray = token2Allocation.get(code).toString().split(";");
				// no allocation -> nothing to do and skip token
			
			if ( offerIdArray == null ) {
				Reporter.log( "##### Token code -> " + code + " is missing information for purchase.", PRINT2STDOUT__);
				continue;
			}
			
			String offerId = offerIdArray[randomGenerator.nextInt(offerIdArray.length)];
		    
			Reporter.log( "##### Token code -> " + code + "\tOffer Id -> " + offerId, PRINT2STDOUT__);
			
			XMLRPCRequest.offeroptimizer_accept().call( 
					gui, 
					xmlrpcBody(
						authentication( user ),
						string( msisdn ),
						string( code ),
						arrayInt( offerId),
						string( "acceptToken" )
					),
					xmlrpcOptions(
							sleep( XMLRPC_CALL_DELAY ),
							storeRequestAsResource( xmlrpcLogFolder, "request_"+testTime+"_accept_"+code+".xml" ),
							storeResponseAsResource( xmlrpcLogFolder, "response_"+testTime+"_accept_"+code+".xml" )	
					)
			);
			
			// cleanup
			if ( token2Allocation.containsKey(code) )
				token2Allocation.remove(code);
		}
	}	
	
	@Test()
	@Parameters({"msisdn", "wideTime"})
	public void refuseTokens(@Optional("393492135019") String msisdn, @Optional("true") Boolean wideTime) throws Exception {

		if ( wideTime ) {
			
			Reporter.log( "###############", PRINT2STDOUT__);		
			Reporter.log( "##### Application refuses all allocated tokens.", PRINT2STDOUT__);
			Reporter.log( "###############", PRINT2STDOUT__);	
		}
		
		refreshTokenStatus( msisdn, wideTime, false);
		
		Reporter.log( "###############", PRINT2STDOUT__);		
		Reporter.log( "##### The subscriber "+ msisdn +" has " + currentAllocatedTokens.size() + " allocted tokens.", PRINT2STDOUT__);
		Reporter.log( "##### Tokens ready to be purchased : " + currentAllocatedTokens, PRINT2STDOUT__);
		Reporter.log( "##### Refuse all tokens", PRINT2STDOUT__);
		Reporter.log( "###############", PRINT2STDOUT__);
		
		for (String code : currentAllocatedTokens) {
			
			Reporter.log( "##### Token code -> " + code, PRINT2STDOUT__);
			
			XMLRPCRequest.offeroptimizer_refuseAll().call( 	
					gui, 
					xmlrpcBody(
						authentication( user ),
						string( msisdn ),
						string( code ),
						string( "my_sofa" )
					),
					xmlrpcOptions(
							sleep( XMLRPC_CALL_DELAY ),
							storeRequestAsResource( xmlrpcLogFolder, "request_"+testTime+"_refuseAll_"+code+".xml" ),
							storeResponseAsResource( xmlrpcLogFolder, "response_"+testTime+"_refuseAll_"+code+".xml" )	
					)
				);					
		}
	}
	
	@Parameters({"msisdn", "status"})
	@Test(priority = 10)
	public void getSubscribersCampaignDetails(@Optional("393492135019") String msisdn, @Optional("ACTIVATED") String status) throws Exception {
	
		Reporter.log( "Get Subscribers Campaign Details for msisdn -> " + msisdn, PRINT2STDOUT__);
		
		XMLRPCRequest.campaignmanager_getSubscribersCampaignDetails().call( 	
				gui, 
				xmlrpcBody(
					authentication( user ),
					string( msisdn ),
					string( status )
				),
				xmlrpcOptions(
						sleep( XMLRPC_CALL_DELAY ),
						storeRequestAsResource( xmlrpcLogFolder, "request_"+testTime+"_getSubscribersCampaignDetails.xml" ),
						storeResponseAsResource( xmlrpcLogFolder, "response_"+testTime+"_getSubscribersCampaignDetails.xml" )	
				));
	}
	
	/**
	 * 
	 * @param msisdn
	 * @param print
	 * 
	 * @throws Exception 
	 */
	
	private void refreshTokenStatus(String msisdn, Boolean wideTime, Boolean print) throws Exception {
		
		currentActiveTokens = new ArrayList<String>();
		currentAllocatedTokens = new ArrayList<String>();
		currentConsumedTokens = new ArrayList<String>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

		String past = sdf.format(startOfTime4Suite) + "+0000";
		
		Reporter.log( "###############", print);
		Reporter.log( "##### Request current token list for subscriber "+ msisdn , print);
		 
		if ( wideTime ) {
			
			Reporter.log( "##### All existing tokens" , print);
			
			past = "";
		}
		else {
			Reporter.log( "##### Time interval : " , print);
			Reporter.log( "##### Starting -> " + past , print);
			Reporter.log( "##### Ending -> Right now" , print);
		}
		
		Reporter.log( "###############", print);

		try {
			
			Thread.sleep( 5_000 );
		
		} catch(  InterruptedException e ) {
			Assert.fail("General error on Java VM!");			  
		}

		XMLRPCTokenList tokenList = new XMLRPCTokenList(		
				XMLRPCRequest.offeroptimizer_getTokensList().call( 	
				gui, 
				xmlrpcBody(
					authentication( user ),
					string( msisdn ),
					string(past),
					string("")
				),
				xmlrpcOptions(
					sleep( XMLRPC_CALL_DELAY ),
					storeRequestAsResource( xmlrpcLogFolder, "request_"+testTime+"_getTokensList.xml" ),
					storeResponseAsResource( xmlrpcLogFolder, "response_"+testTime+"_getTokensList.xml" )	
				)
			).getResponse().getEntity().toString()).parse();

		List<Token> tokens = tokenList.getTokenList();
		
		StringBuffer printable = new StringBuffer();
		
		for (Token token : tokens) {
			
			String code = token.getCode();
			String status = token.getStatus();
			
			printable.append("(").append(code).append(",").append(status).append(")\t");
			
			if ( status.equals(TokenStatus.ACTIVE.toString()) )
				currentActiveTokens.add(code);
			else if ( status.equals(TokenStatus.ALLOCATED.toString()) )
				currentAllocatedTokens.add(code);
			else if ( status.equals(TokenStatus.CONSUMED.toString()) )
				currentConsumedTokens.add(code);
		}
	}
	
	@Test( dependsOnMethods={"getTokensList"} )
	@Parameters({"msisdn","offerIds"})
	public void allocatePurchaseToken(@Optional("393492135019") String msisdn, @Optional(NO_EXT_OFFER_LIST__) String offerIds) throws Exception {
		
		Reporter.log( "###############", PRINT2STDOUT__);
		Reporter.log( "##### The subscriber "+ msisdn +" has " + currentActiveTokens.size() + " active tokens.", PRINT2STDOUT__);
		Reporter.log( "##### Allocate/Purchase each token.", PRINT2STDOUT__);
		Reporter.log( "##### Tokens ready to be allocated : " + currentActiveTokens, PRINT2STDOUT__);
		Reporter.log( "###############", PRINT2STDOUT__);
		
		/**
		 * Start main loop
		 */
		for (String code : currentActiveTokens) {
		
			/**
			 * Start allocation
			 */
			Reporter.log( "Token code : " + code, PRINT2STDOUT__);
	
			XMLRPCAllocate offerPack = new XMLRPCAllocate(XMLRPCRequest.offeroptimizer_allocate().call( 
					gui, 
					xmlrpcBody(
						authentication( user ),
						string( msisdn ),
						string( code )
					),
					xmlrpcOptions(
						sleep( XMLRPC_CALL_DELAY ),
						storeRequestAsResource( xmlrpcLogFolder, "request_"+testTime+"_allocate_"+code+".xml" ),
						storeResponseAsResource( xmlrpcLogFolder, "response_"+testTime+"_allocate_"+code+".xml" )	
					)
			).getResponse().getEntity().toString()).parse();
			
			/**
			 * Technical debt
			 * How to ensure that status?
			 */
			
			List<Offer> offers = offerPack.getOfferList();
			
			StringBuilder allocatedOffer = new StringBuilder();
			
			String prefix = "";
			
			for (Offer offer : offers) {
				allocatedOffer.append(prefix);
				prefix = ";";
				allocatedOffer.append(offer.getId());
			}

			Reporter.log( "###############", PRINT2STDOUT__);
			Reporter.log( "##### The subscriber "+ msisdn +" has allocated token " + code, PRINT2STDOUT__);
			Reporter.log( "##### Offer List : " + ( allocatedOffer.length() != 0 ? allocatedOffer : "{empty}"), PRINT2STDOUT__);
			Reporter.log( "###############", PRINT2STDOUT__);
			
//			token2Allocation.put( code, allocatedOffer);

			/**
			 * End allocation
			 */
			
			/**
			 * Start purchase
			 */
			
			Reporter.log( "###############", PRINT2STDOUT__);		
			Reporter.log( "##### The subscriber "+ msisdn +" has " + code + " allocted token.", PRINT2STDOUT__);
			if ( offerIds.equals(NO_EXT_OFFER_LIST__) )
				Reporter.log( "##### Token will be purchased according allocation (if available)", PRINT2STDOUT__);
			else
				Reporter.log( "##### Purchase one offer among : " + offerIds.replace(";", " "), PRINT2STDOUT__);
//			Reporter.log( "##### Tokens ready to be purchased : " + currentAllocatedTokens, PRINT2STDOUT__);
			Reporter.log( "###############", PRINT2STDOUT__);
			
			String[] extOfferIdArray = offerIds.equals(NO_EXT_OFFER_LIST__) ? null : offerIds.split(";");
			
			Random randomGenerator = new Random();
			
			String[] offerIdArray = null;
			
			// force external
			if ( extOfferIdArray != null )
				offerIdArray = extOfferIdArray;
			else // no ext
				if ( allocatedOffer != null && allocatedOffer.length() != 0 ) // allocation
					offerIdArray = allocatedOffer.toString().split(";");
				// no allocation -> nothing to do and skip token
			
			if ( offerIdArray == null ) {
				Reporter.log( "##### Token code -> " + code + " is missing information for purchase.", PRINT2STDOUT__);
				continue;
			}			
			
			String offerId = offerIdArray[randomGenerator.nextInt(offerIdArray.length)];
		    
			Reporter.log( "##### Token code -> " + code + "\tOffer Id -> " + offerId, PRINT2STDOUT__);
			
			XMLRPCRequest.offeroptimizer_accept().call( 
					gui, 
					xmlrpcBody(
						authentication( user ),
						string( msisdn ),
						string( code ),
						arrayInt( offerId),
						string( "acceptToken" )
					),
					xmlrpcOptions(
							sleep( XMLRPC_CALL_DELAY ),
							storeRequestAsResource( xmlrpcLogFolder, "request_"+testTime+"_accept_"+code+".xml" ),
							storeResponseAsResource( xmlrpcLogFolder, "response_"+testTime+"_accept_"+code+".xml" )	
					)
			);
			
			/**
			 * Technical debt
			 * How to ensure that status?
			 */			
			
			// cleanup
//			if ( token2Allocation.containsKey(code) )
//				token2Allocation.remove(code);		
			
			/**
			 * End purchase
			 */
		}
		/**
		 * End main loop
		 */
	}
	
	/**
	 * Static section
	 */
	static {
		token2Allocation = new HashMap<String, StringBuilder>();
	}
}
