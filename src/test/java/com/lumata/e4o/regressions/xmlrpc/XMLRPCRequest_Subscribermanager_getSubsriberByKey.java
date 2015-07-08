package com.lumata.e4o.regressions.xmlrpc;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.xmlrpcOptions;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.xmlrpcValidator;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.sleep;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.authentication;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.params;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.services;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.subscriber;
import static com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCParameter.*;
import static com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidatorMethods.fault;
import static org.hamcrest.Matchers.equalTo;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCMysqlMaster;

import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods;
import com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCParameter;

@TCMysqlMaster
public class XMLRPCRequest_Subscribermanager_getSubsriberByKey extends ParentTestCase {
	
private static final Logger logger = LoggerFactory.getLogger(XMLRPCRequest_Subscribermanager_getSubsriberByKey.class );
	
	final boolean TEST_ENABLED = true;
	

	
	@Test(enabled=TEST_ENABLED, priority = 1 )
	public void testGetSubscriberByIMSI() throws Exception{
		
		XMLRPCComponent obj = XMLRPCComponent.xmlrpcBody(authentication(user), XMLRPCRequestMethods.getsubscriberByKey(param("imsi", "090886")));
		XMLRPCRequest.subscribermanager_getSubscriberByKey().call(guiServer,obj,xmlrpcOptions(
				sleep( 100L )	
			));
	
						
	}
	
	@Test(enabled=TEST_ENABLED , priority = 2)
	public void testSubscriberNotFound() throws Exception{
	
		XMLRPCComponent obj = XMLRPCComponent.xmlrpcBody(authentication(user), XMLRPCRequestMethods.getsubscriberByKey(param("imsi", "09088")));
		XMLRPCRequest.subscribermanager_getSubscriberByKey().call(guiServer,obj,xmlrpcValidator(
				fault().code( equalTo( 100 ) ),
				fault().message( equalTo( "Subscriber not found for imsi = 09088" ) )
			),
			xmlrpcOptions(
				sleep( 100L )	
			));
	}
	
	@Test(enabled=TEST_ENABLED , priority = 3)
		public void testMissingParameter() throws Exception{
		
		XMLRPCComponent obj = XMLRPCComponent.xmlrpcBody(authentication(user), XMLRPCRequestMethods.getsubscriberByKey(param(null,"090886")));
		XMLRPCRequest.subscribermanager_getSubscriberByKey().call(guiServer,obj,xmlrpcValidator(
				fault().code( equalTo( 5 ) ),
				fault().message( equalTo( "Missing mandatory param parameters" ) )
			),
			xmlrpcOptions(
				sleep( 100L )	
			));
			
		}
	
	}
	
	

	

	
	
	


