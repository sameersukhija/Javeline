package com.lumata.unit.webservices.xmlrpc;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.e4o.dao.tenant.DAOSubscribers;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.*;
import static com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidatorMethods.*;

public class XMLRPCRequest_Subscribermanager_DeleteSubscriber extends ParentTestCase {
	
	DAOSubscribers daoSubscribers;
			
	@BeforeMethod
	public void init() throws NetworkEnvironmentException {		
		
		daoSubscribers = DAOSubscribers.getInstance( mysqlMaster );
		
	}

	@Test(enabled=TEST_ENABLED, priority = 3 )
	public void deleteSubscriberWidthExistingMsisdn() throws Exception {
	
		Long msisdn = 3399900012L;
	
		XMLRPCRequest.subscribermanager_deleteSubscriber().call( 
				guiServer, 
				xmlrpcBody(
					authentication( user ),
					subscriber( 
						msisdn
					)
				),
				xmlrpcValidator(
					success()
				),
				xmlrpcOptions(
					sleep( 100L )	
				)
		);
			
	}
	
}
