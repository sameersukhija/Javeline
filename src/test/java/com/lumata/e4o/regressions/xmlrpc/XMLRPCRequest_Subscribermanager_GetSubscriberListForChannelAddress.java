package com.lumata.e4o.regressions.xmlrpc;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.xmlrpcBody;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.xmlrpcOptions;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.xmlrpcValidator;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.sleep;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.authentication;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.channels;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.subscriberChannel;
import static com.lumata.e4o.webservices.response.types.XMLRPCResponseSubscriber.*;
import static com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCSubscriberChannel.*;
import static com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidatorMethods.fault;
import static com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidatorMethods.success;
import static org.hamcrest.Matchers.equalTo;

import java.text.ParseException;
import java.util.Calendar;

import org.testng.annotations.Test;

import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.dao.tenant.DAOSubscribers;
import com.lumata.e4o.regressions.xmlrpc.XMLRPCRequest_Subscribermanager_CreateSubscriber.ExtendedParameters;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCMysqlMaster;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;
import com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCSubscriberChannel.ChannelType;


@TCMysqlMaster
public class XMLRPCRequest_Subscribermanager_GetSubscriberListForChannelAddress extends ParentTestCase {
	
	private Subscribers subscriber = null;
	private final Long MSISDN = 3399900001L;
	private final Long IMSI = 990888L;
	private final String PROFILE_NAME = "prepaid";
	private final String RATE_PLAN_NAME = "FUN";
	private final String STATUS_NAME = "active";
	private final String IN_TAG = "QAIN";
	private final String NETWORK_NAME = "mobile";
	
	/** NULL VALUES **/
	private final Long IMSI_NULL = null;
	
	/** NO VALID VALUES **/
	private final Long IMSI_NO_EXISTING = 111111111111L;
	
	
//	@Test(enabled=TEST_ENABLED, priority = 1)
//	public void initEnv() throws ParseException, Exception {
//		
//		subscriber = DAOSubscribers.getInstance( mysqlMaster ).getSubscriber( MSISDN );
//	
//		if( null == subscriber ) {
//			
//			XMLRPCRequest.subscribermanager_createSubscriber().call( 
//				guiServer, 
//				xmlrpcBody(
//					authentication( user ),
//					subscriber( 
//						String.valueOf( MSISDN ),
//						Format.getMysqlDate( Calendar.getInstance() ),
//						PROFILE_NAME,
//						null,
//						RATE_PLAN_NAME,
//						STATUS_NAME,
//						IN_TAG,
//						NETWORK_NAME,
//						params(
//							param( ExtendedParameters.imsi, IMSI )	
//						),
//						services()																
//					)
//				),
//				xmlrpcValidator(
//					success()
//				),
//				xmlrpcOptions(
//					sleep( 100L )
//				)
//			);
//			
//		} else {
//			
//			XMLRPCRequest.subscribermanager_updateSubscriber().call( 
//				guiServer, 
//				xmlrpcBody(
//					authentication( user ),
//					subscriber( 
//						String.valueOf( MSISDN ),
//						Format.getMysqlDate( subscriber.getSubscriptionDate() ),
//						PROFILE_NAME,
//						null,
//						RATE_PLAN_NAME,
//						STATUS_NAME,
//						IN_TAG,
//						NETWORK_NAME,
//						params(
//							param( ExtendedParameters.imsi, IMSI )	
//						),
//						services()																
//					)
//				),
//				xmlrpcValidator(
//					success()
//				),
//				xmlrpcOptions(
//					sleep( 100L )
//				)
//			);
//			
//		}
//	
//	}
	
	@Test(enabled=TEST_ENABLED, priority = 2 )
	public void testGetSubscriberByChannelAddress() throws Exception {
		
		XMLRPCRequest.subscribermanager_getSubscriberListForChannelAddress().call(
			guiServer, 
			xmlrpcBody(
				authentication(user),
				subscriberChannel(
					null,
					ChannelType.SMS,
					String.valueOf( MSISDN ),
					true
				)
			),
//			xmlrpcValidator(
//				subscriberResponse().msisdn( equalTo( MSISDN ) ),
//				subscriberResponse().subscriptionDate( equalTo( Format.getMysqlDate( subscriber.getSubscriptionDate() ) ) ),
//				subscriberResponse().profile( equalTo( PROFILE_NAME ) ),
//				subscriberResponse().rate_plan( equalTo( RATE_PLAN_NAME ) ),
//				subscriberResponse().status( equalTo( STATUS_NAME ) ),
//				subscriberResponse().inTag( equalTo( IN_TAG ) ),
//				subscriberResponse().imsi( equalTo( IMSI ) )
//			),
			xmlrpcOptions(
				sleep( 100L )	
			)
		);
						
	}
		
//	/** The test fails because the rate_plan is an integer instead of a string */
//	@Test(enabled=TEST_ENABLED, priority = 2 )
//	public void testGetSubscriberByIMSI() throws Exception {
//		
//		XMLRPCRequest.subscribermanager_getSubscriberByKey().call(
//			guiServer, 
//			xmlrpcBody(
//				authentication(user),				
//				subscriber( 
//					params(
//						param( ExtendedParameters.imsi, IMSI )	
//					)																
//				)
//			),
//			xmlrpcValidator(
//				subscriberResponse().msisdn( equalTo( MSISDN ) ),
//				subscriberResponse().subscriptionDate( equalTo( Format.getMysqlDate( subscriber.getSubscriptionDate() ) ) ),
//				subscriberResponse().profile( equalTo( PROFILE_NAME ) ),
//				subscriberResponse().rate_plan( equalTo( RATE_PLAN_NAME ) ),
//				subscriberResponse().status( equalTo( STATUS_NAME ) ),
//				subscriberResponse().inTag( equalTo( IN_TAG ) ),
//				subscriberResponse().imsi( equalTo( IMSI ) )
//			),
//			xmlrpcOptions(
//				sleep( 100L )	
//			)
//		);
//						
//	}
	
//	@Test( enabled=TEST_ENABLED , priority = 3 )
//	public void testMissingParameter() throws Exception{
//
//		XMLRPCRequest.subscribermanager_getSubscriberByKey().call(
//				guiServer, 
//				xmlrpcBody(
//					authentication( user ),				
//					subscriber( 
//						params(
//							param( ExtendedParameters.imsi, IMSI_NULL )	
//						)																
//					)
//				),
//				xmlrpcValidator(
//						fault().code( equalTo( 5 ) ),
//						fault().message( equalTo( "Missing mandatory param parameters" ) )
//				),
//				xmlrpcOptions(
//					sleep( 100L )	
//				)
//			);
//		
//	}
//	
//	@Test( enabled=TEST_ENABLED , priority = 4 )
//	public void testSubscriberNotFound() throws Exception {
//
//		XMLRPCRequest.subscribermanager_getSubscriberByKey().call(
//			guiServer, 
//			xmlrpcBody(
//				authentication( user ),				
//				subscriber( 
//					params(
//						param( ExtendedParameters.imsi, IMSI_NO_EXISTING )	
//					)																
//				)
//			),
//			xmlrpcValidator(
//					fault().code( equalTo( 100 ) ),
//					fault().message( equalTo( "Subscriber not found for imsi = " + IMSI_NO_EXISTING ) )
//			),
//			xmlrpcOptions(
//				sleep( 100L )	
//			)
//		);		
//		
//	}
	
}
	

	
	

	

	
	
	


