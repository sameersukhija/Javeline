package com.lumata.e4o.regressions.xmlrpc;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.xmlrpcBody;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.xmlrpcOptions;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.xmlrpcValidator;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.sleep;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.authentication;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.params;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.services;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.subscriber;
import static com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCParameter.*;
import static com.lumata.e4o.webservices.response.types.XMLRPCResponseSubscriber.*;
import static com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidatorMethods.fault;
import static com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidatorMethods.success;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

import java.text.ParseException;
import java.util.Calendar;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.dao.tenant.DAOSubscribers;
import com.lumata.e4o.regressions.xmlrpc.XMLRPCRequest_Subscribermanager_CreateSubscriber.ExtendedParameters;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCMysqlMaster;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;


@TCMysqlMaster
public class XMLRPCRequest_Subscribermanager_GetSubsriberByKey extends ParentTestCase {
	
	private Subscribers subscriber = null;
	private final Long MSISDN = 3399999999L;
	private final Long IMSI = 1234567890123456789L;
	private final String PROFILE_NAME = "prepaid";
	private final String RATE_PLAN_NAME = "FUN";
	private final String STATUS_NAME = "active";
	private final String IN_TAG = "QAIN";
	private final String NETWORK_NAME = "mobile";
	
	/** NULL VALUES **/
	private final Long IMSI_NULL = null;
	
	/** NO VALID VALUES **/
	private final Long IMSI_NO_EXISTING = 1111111111111111111L;
	
	@BeforeClass(groups={"a"})
	public void initEnv() throws ParseException, Exception {
		
		subscriber = DAOSubscribers.getInstance( mysqlMaster ).getSubscriber( MSISDN );
	
		if( null == subscriber ) {
			
			XMLRPCRequest.subscribermanager_createSubscriber().call( 
				guiServer, 
				xmlrpcBody(
					authentication( user ),
					subscriber( 
						String.valueOf( MSISDN ),
						Format.getMysqlDate( Calendar.getInstance() ),
						PROFILE_NAME,
						null,
						RATE_PLAN_NAME,
						STATUS_NAME,
						IN_TAG,
						NETWORK_NAME,
						params(
							param( ExtendedParameters.imsi, IMSI )	
						),
						services()																
					)
				),
				xmlrpcValidator(
					success()
				),
				xmlrpcOptions(
					sleep( 100L )
				)
			);
			
			subscriber = DAOSubscribers.getInstance( mysqlMaster ).getSubscriber( MSISDN );
			
		} else {
			
			XMLRPCRequest.subscribermanager_updateSubscriber().call( 
				guiServer, 
				xmlrpcBody(
					authentication( user ),
					subscriber( 
						String.valueOf( MSISDN ),
						Format.getMysqlDate( subscriber.getSubscriptionDate() ),
						PROFILE_NAME,
						null,
						String.valueOf(RATE_PLAN_NAME),
						STATUS_NAME,
						IN_TAG,
						NETWORK_NAME,
						params(
							param( ExtendedParameters.imsi, IMSI )	
						),
						services()																
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
		
	/** Note: The test will fail when the fix EFOGC-4333 will be resolved, because the rate_plan must return back the name instead of the id */
	@Test(enabled=TEST_ENABLED, priority = 1 )
	public void testGetSubscriberByIMSI() throws Exception {
		
		XMLRPCRequest.subscribermanager_getSubscriberByKey().call(
			guiServer, 
			xmlrpcBody(
				authentication(user),				
				subscriber( 
					params(
						param( ExtendedParameters.imsi, IMSI )	
					)																
				)
			),
			xmlrpcValidator(
				subscriberResponse().msisdn( equalTo( MSISDN ) ),
				subscriberResponse().msisdn( greaterThan( 3399900000L ) ),
				subscriberResponse().subscriptionDate( equalTo( Format.getMysqlDate( subscriber.getSubscriptionDate() ) ) ),
				subscriberResponse().profile( equalTo( PROFILE_NAME ) ),
				/** condition on RATE_PLAN_ID to change in RATE_PLAN_NAME when the EFOGC-4333 will be resolved **/
				subscriberResponse().rate_plan( equalTo( RATE_PLAN_NAME ) ),
				subscriberResponse().status( equalTo( STATUS_NAME ) ),
				subscriberResponse().inTag( equalTo( IN_TAG ) ),
				subscriberResponse().imsi( equalTo( IMSI ) )
			),
			xmlrpcOptions(
				sleep( 100L )	
			)
		);
						
	}
	
	@Test( enabled=TEST_ENABLED , priority = 2 )
	public void testMissingParameter() throws Exception{

		XMLRPCRequest.subscribermanager_getSubscriberByKey().call(
			guiServer, 
			xmlrpcBody(
				authentication( user ),				
				subscriber( 
					params(
						param( ExtendedParameters.imsi, IMSI_NULL )	
					)																
				)
			),
			xmlrpcValidator(
					fault().code( equalTo( 5 ) ),
					fault().message( equalTo( "Missing mandatory param parameters" ) )
			),
			xmlrpcOptions(
				sleep( 100L )	
			)
		);
		
	}
	
	@Test( enabled=TEST_ENABLED , priority = 3 )
	public void testSubscriberNotFound() throws Exception {

		XMLRPCRequest.subscribermanager_getSubscriberByKey().call(
			guiServer, 
			xmlrpcBody(
				authentication( user ),				
				subscriber( 
					params(
						param( ExtendedParameters.imsi, IMSI_NO_EXISTING )	
					)																
				)
			),
			xmlrpcValidator(
					fault().code( equalTo( 100 ) ),
					fault().message( equalTo( "Subscriber not found for imsi = " + IMSI_NO_EXISTING ) )
			),
			xmlrpcOptions(
				sleep( 100L )	
			)
		);		
		
	}
	
	// Method to test Authentication Failure
	@Test(enabled = TEST_ENABLED, priority = 4 )
	public void testAuthenticatinFailure() throws Exception {
		XMLRPCRequest.subscribermanager_getSubscriberByKey().call(
			guiServer,
			xmlrpcBody(
				authentication("superman", "bGppYm1NSUhJMm43"),
				subscriber(
					params(
						param(
						ExtendedParameters.imsi, IMSI)
					)
				)
			),
			xmlrpcValidator(
				fault().code(equalTo(1)), 
				fault().message(equalTo("authentication failure"))
			),
			xmlrpcOptions(
				sleep(100L)
			)
		);
	}
	
}
