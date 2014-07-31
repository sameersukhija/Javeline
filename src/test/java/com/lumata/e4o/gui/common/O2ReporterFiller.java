package com.lumata.e4o.gui.common;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.xmlrpcBody;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.xmlrpcOptions;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.sleep;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.arrayInt;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.authentication;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.string;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.jboss.resteasy.client.ClientResponse;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.e4o.exceptions.GeneratorException;
import com.lumata.e4o.generators.common.Generator;
import com.lumata.e4o.gui.xmlrpc.HTTPXMLRPCForm;
import com.lumata.e4o.gui.xmlrpc.XMLRPCTokenList;
import com.lumata.e4o.gui.xmlrpc.XMLRPCTokenList.Token;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;

/**
 *	This class contains tests for O2 custom reporter testing
 */
public class O2ReporterFiller extends RegressionSuiteXMLRPC {
	
	@Test
	@Parameters({"msisdn","tokens2BeGenerated"})
	public void generateTokens(@Optional("393492135019") String msisdn, @Optional("10") Integer tokens2BeGenerated) throws NumberFormatException, GeneratorException {
		
		Reporter.log( "Generate "+tokens2BeGenerated+" tokens for subscriber "+ msisdn , PRINT2STDOUT__);

		String local_msisdn = msisdn;
		
		for( int i = 0; i < tokens2BeGenerated; i++ ) {

			Generator.subscribers()
				.server( gui )
				.user( user )
				.msisdnFixed( Long.parseLong(local_msisdn) )
				.xmlrpcRecharge( 1L );			
			
			try {
			
				Thread.sleep( 1_000 );
			
			} catch(  InterruptedException e ) {
				Assert.fail("General error on Java VM!");			  
			}
		}				
	}	
	
	/**
	 * 
	 */
	private static List<String> currentActiveTokens = null; 
	
	/**
	 * @throws Exception 
	 * 
	 */
	@Test
	@Parameters("msisdn")
	public void getActiveTokensList(@Optional("393492135019") String msisdn) throws Exception {
		
		currentActiveTokens = getTokenByStatus(msisdn, "active", false);

		Reporter.log( "###############", PRINT2STDOUT__);
		Reporter.log( "##### The subscriber "+ msisdn +" has " + currentActiveTokens.size() + " active tokens.", PRINT2STDOUT__);
		Reporter.log( "###############", PRINT2STDOUT__);
	}	
	
	/**
	 * 
	 */
	private static List<String> currentAllocatedTokens = null; 
	
	/**
	 * @throws Exception 
	 * 
	 */
	@Test(groups="preparation4Accept")
	@Parameters("msisdn")
	public void getAllocatedTokensList(@Optional("393492135019") String msisdn) throws Exception {
		
		currentAllocatedTokens = getTokenByStatus(msisdn, "offers_allocated", false);

		Reporter.log( "###############", PRINT2STDOUT__);
		Reporter.log( "##### The subscriber "+ msisdn +" has " + currentAllocatedTokens.size() + " allocated tokens.", PRINT2STDOUT__);
		Reporter.log( "###############", PRINT2STDOUT__);
	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	@Test
	@Parameters("msisdn")
	public void getConsumedTokensList(@Optional("393492135019") String msisdn) throws Exception {
		
		List<String> consumed = getTokenByStatus(msisdn, "consumed", false);

		Reporter.log( "###############", PRINT2STDOUT__);
		Reporter.log( "##### The subscriber "+ msisdn +" has " + consumed.size() + " consumed tokens.", PRINT2STDOUT__);
		Reporter.log( "###############", PRINT2STDOUT__);
	}	

	
	/**
	 * 
	 * @param msisdn
	 * @param string
	 * @return
	 * @throws Exception
	 */
	private List<String> getTokenByStatus(String msisdn, String lookedStatus, Boolean print) throws Exception {
		
		List<String> resp = new ArrayList<>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		
		Calendar cal = Calendar.getInstance();  
		String now = sdf.format(cal.getTime()) + "+0000";
		
		cal.add(Calendar.DAY_OF_YEAR, -2);
		String past = sdf.format(cal.getTime()) + "+0000";
		
		String local_msisdn = msisdn;
		
		Reporter.log( "###############", print);
		Reporter.log( "##### Request current token list for subscriber "+ msisdn , print);
		Reporter.log( "##### Time interval : " , print);
		Reporter.log( "##### Starting -> " + past , print);
		Reporter.log( "##### Ending -> " + now , print);
		Reporter.log( "###############", print);

		try {
			
			Thread.sleep( 5_000 );
		
		} catch(  InterruptedException e ) {
			Assert.fail("General error on Java VM!");			  
		}
		
		ArrayList<String> params = new ArrayList<String>();
		
		params.add( HTTPXMLRPCForm.getAuthenticationParam( user.getUsername(), user.getPassword()) );
		params.add( HTTPXMLRPCForm.getStringParam(local_msisdn) );
		params.add( HTTPXMLRPCForm.getStringParam(past) );
		params.add( HTTPXMLRPCForm.getStringParam(now) );
																																										
		ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.offeroptimizer_getTokensList.call( gui.getLink() + "xmlrpc/" , params );
		XMLRPCTokenList tokenList = new XMLRPCTokenList(response.getEntity().toString()).parse();

		List<Token> tokens = tokenList.getTokenList();
		
		StringBuffer printable = new StringBuffer();
		
		for (Token token : tokens) {
			
			String code = token.getCode();
			String status = token.getStatus();
			
			printable.append("(").append(code).append(",").append(status).append(")\t");
			
			if ( status.equals(lookedStatus) )
				resp.add(code);
		}
				
		Reporter.log( "###############", print);
		Reporter.log( "##### The subscriber "+ msisdn +" has the token list (code,status) : ", print);
		Reporter.log( "##### " + printable, print);
		Reporter.log( "###############", print);

		return resp;
	}

	@Test( groups = {"preparation4Accept"}, dependsOnMethods = {"getActiveTokensList"} )
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
						sleep( 100L )	
					)
			);		
		}
	}
	
	@Test(dependsOnGroups={"preparation4Accept"})
	@Parameters({"msisdn","offerIds"})
	public void acceptToken(@Optional("393492135019") String msisdn, @Optional("1000;1001") String offerIds) throws Exception {
		
		Reporter.log( "###############", PRINT2STDOUT__);		
		Reporter.log( "##### The subscriber "+ msisdn +" has " + currentAllocatedTokens.size() + " active tokens.", PRINT2STDOUT__);
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
						sleep( 100L )	
					)
			);		
		}
	}
}
