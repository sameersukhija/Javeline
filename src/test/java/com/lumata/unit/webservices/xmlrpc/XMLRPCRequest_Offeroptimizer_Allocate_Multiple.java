package com.lumata.unit.webservices.xmlrpc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.lumata.e4o.dao.tenant.DAOToken;
import com.lumata.e4o.schema.tenant.Token;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCMysqlMaster;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.*;

@TCMysqlMaster
public class XMLRPCRequest_Offeroptimizer_Allocate_Multiple extends ParentTestCase {
	
	@Test(enabled=TEST_ENABLED, priority = 1 )
	public void callXMLRPCCRequest() throws Exception {
		
		final Boolean LOG_TO_STD_OUT = Boolean.TRUE;
		
		Calendar date = new GregorianCalendar( 2015, Calendar.JANUARY, 7 );
		
		final Long msisdn = 3399900001L;
		
		final Integer allocations = 1;
		
		ArrayList<Token> tokens = DAOToken.getInstance(mysqlMaster).getAvailableActiveTokensByCampaign(msisdn,"CMSBAB1", "RuleSBAB", date);

//		for( int t = 0; t < tokens.size(); t++ ) {
//			Token token = tokens.get(t);
//			System.out.println( token.getTokenCode() + " - " + token.getFeatureId() + " - " + token.getRulesetId() + " - " + token.getExpirationDate() );
//		}
		
		Integer executedAllocations = 0;
		
		for( int allocation = 0; allocation < (int)Math.min(allocations, tokens.size()); allocation++ ){
			
			Token token = tokens.get(allocation);
			
			Reporter.log("Playing token ( " + token.getTokenCode() + " )", LOG_TO_STD_OUT );
			
			XMLRPCRequest.offeroptimizer_allocate().call( 	
				guiServer, 
				xmlrpcBody(
					authentication( user ),
					string( msisdn ),
					string( token.getTokenCode() )
				),
				xmlrpcOptions(
					storeRequestAsResource( "xmlrpc/request/", "request.xml" ),
					storeResponseAsResource( "xmlrpc/response/", "response.xml" )	
				)
			);
			
			executedAllocations++;
		
		}
		
		System.out.println( executedAllocations );
		
	}
	
}
