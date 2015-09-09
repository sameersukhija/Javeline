package com.lumata.e4o.regressions.xmlrpc;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.e4o.dao.tenant.DAOSubscribers;
import com.lumata.e4o.generators.common.Generator;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCMysqlMaster;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.*;
import static com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidatorMethods.*;

@TCMysqlMaster
public class XMLRPCRequest_Subscribermanager_DeleteSubscriber extends ParentTestCase {
	
	DAOSubscribers daoSubscribers;
			
	@BeforeClass
	public void init() throws NetworkEnvironmentException {		
		
		daoSubscribers = DAOSubscribers.getInstance( mysqlMaster );
		
	}

	@Test(enabled=TEST_ENABLED, priority = 1 )
	public void deleteSubscriberWidthMsisdnNull() throws Exception {
	
		Long msisdn = null;
		
		XMLRPCRequest.subscribermanager_deleteSubscriber().call( 
				guiServer, 
				xmlrpcBody(
					authentication( user ),
					subscriber( 
							msisdn
					)
				),
				xmlrpcValidator(
					fault().code( equalTo( 100 ) ),
					fault().message( equalTo( "subscriber not found with msisdn " + msisdn ) )
				),
				xmlrpcOptions(
					sleep( 100L )	
				)
		);
		
	}
	
	@Test(enabled=TEST_ENABLED, priority = 2 )
	public void deleteSubscriberWidthNotExistingMsisdn() throws Exception {
	
		Long msisdn = 0L;
		
		if( !daoSubscribers.isSubscriber( msisdn ) ) {
		
			XMLRPCRequest.subscribermanager_deleteSubscriber().call( 
					guiServer, 
					xmlrpcBody(
						authentication( user ),
						subscriber( 
								msisdn
						)
					),
					xmlrpcValidator(
						fault().code( equalTo( 100 ) ),
						fault().message( equalTo( "subscriber not found with msisdn " + msisdn ) )
					),
					xmlrpcOptions(
						sleep( 100L )	
					)
			);
		
		}
		
	}

	@Test(enabled=TEST_ENABLED, priority = 3 )
	public void deleteSubscriberWidthExistingMsisdn() throws Exception {
	
		final Long FIXED_MSISDN = 3399999999L;
		final Boolean HAS_SMS_CHANNEL = true;
		final Boolean HAS_MAIL_CHANNEL = true;
		final Long SUBSCRIBERS_TO_GENERATE = 1L;
		
		Generator.subscribers()
			.environment( env )
			.mysql( mysqlMaster )
			.msisdnFixed( FIXED_MSISDN )
			.subscriberHasSMSChannel( HAS_SMS_CHANNEL )
			.subscriberHasMAILChannel( HAS_MAIL_CHANNEL )
			.insertIntoEnvironment( SUBSCRIBERS_TO_GENERATE );
		
		Subscribers subscriber = daoSubscribers.getSubscriber( FIXED_MSISDN );
		
		if( null != subscriber ) {
		
			XMLRPCRequest.subscribermanager_deleteSubscriber().call( 
					guiServer, 
					xmlrpcBody(
						authentication( user ),
						subscriber( 
							subscriber.getMsisdn()
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
	
}
