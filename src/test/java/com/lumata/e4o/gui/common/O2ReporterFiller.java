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

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.e4o.gui.xmlrpc.XMLRPCTokenList;
import com.lumata.e4o.gui.xmlrpc.XMLRPCTokenList.Token;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.EventType;

/**
 *	This class contains tests for O2 custom reporter testing
 */
public class O2ReporterFiller extends RegressionSuiteXMLRPC {
	
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
	 * Base folder where XML file are stored
	 */
	private static String baseXmlrpcLogFolder = "xmlrpc";
	
	/**
	 * Current log folder
	 */
	private static String xmlrpcLogFolder = null;
	
	/**
	 * This object describe the origin of time for current test execution
	 */
	private static Date startOfTime4Suite = null;
	
	/**
	 * It describes the execution time for single test for log purpose
	 */
	private String testTime = null;
	
	/**
	 * Default sleep time for XMLPRC message
	 */
	private static Long SLEEP_TIME_ = 100L;
	
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
	
	@BeforeMethod
	private void testSetup() {

		/**
		 * Common suite section
		 */
		
		if ( startOfTime4Suite == null )
			startOfTime4Suite = new Date(Calendar.getInstance().getTimeInMillis() - 60000);
		
		if ( xmlrpcLogFolder == null ) {
			xmlrpcLogFolder = baseXmlrpcLogFolder + File.separator + "execution_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(startOfTime4Suite) + File.separator;
			
			Reporter.log( "###############", PRINT2STDOUT__);
			Reporter.log( "##### XMLRPC response message are stored into folder "+ "execution_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(startOfTime4Suite), PRINT2STDOUT__);
			Reporter.log( "###############", PRINT2STDOUT__);
		}

		/**
		 * Specific test method section
		 */
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		testTime = sdf.format(new Date());
	}
	
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
						sleep( SLEEP_TIME_ ),
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
	
			XMLRPCRequest.offeroptimizer_allocate().call( 
					gui, 
					xmlrpcBody(
						authentication( user ),
						string( msisdn ),
						string( code )
					),
					xmlrpcOptions(
						sleep( SLEEP_TIME_ ),
						storeRequestAsResource( xmlrpcLogFolder, "request_"+testTime+"_allocate_"+code+".xml" ),
						storeResponseAsResource( xmlrpcLogFolder, "response_"+testTime+"_allocate_"+code+".xml" )	
					)
			);		
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
	public void acceptToken(@Optional("393492135019") String msisdn, @Optional("1000;1001") String offerIds) throws Exception {
		
		Reporter.log( "###############", PRINT2STDOUT__);		
		Reporter.log( "##### The subscriber "+ msisdn +" has " + currentAllocatedTokens.size() + " allocted tokens.", PRINT2STDOUT__);
		Reporter.log( "##### Purchase one offer among : " + offerIds.replace(";", " "), PRINT2STDOUT__);
		Reporter.log( "##### Tokens ready to be purchased : " + currentAllocatedTokens, PRINT2STDOUT__);
		Reporter.log( "###############", PRINT2STDOUT__);
		
		String[] offerIdArray = offerIds.split(";");
		
		Random randomGenerator = new Random();
		
		for (String code : currentAllocatedTokens) {
			
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
							sleep( SLEEP_TIME_ ),
							storeRequestAsResource( xmlrpcLogFolder, "request_"+testTime+"_accept_"+code+".xml" ),
							storeResponseAsResource( xmlrpcLogFolder, "response_"+testTime+"_accept_"+code+".xml" )	
					)
			);		
		}
	}	
	
	@Test()
	@Parameters({"msisdn"})
	public void refuseAllToken(@Optional("393492135019") String msisdn) throws Exception {

		refreshTokenStatus( msisdn, true, false);
		
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
							sleep( SLEEP_TIME_ ),
							storeRequestAsResource( xmlrpcLogFolder, "request_"+testTime+"_refuseAll_"+code+".xml" ),
							storeResponseAsResource( xmlrpcLogFolder, "response_"+testTime+"_refuseAll_"+code+".xml" )	
					)
				);					
		}
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
					sleep( SLEEP_TIME_ ),
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
}
