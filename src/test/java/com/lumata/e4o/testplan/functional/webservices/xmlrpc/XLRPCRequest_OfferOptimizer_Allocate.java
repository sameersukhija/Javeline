package com.lumata.e4o.testplan.functional.webservices.xmlrpc;

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.annotations.mysql.Column;
import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.dao.tenant.DAOSubscribers;
import com.lumata.e4o.dao.tenant.DAOToken;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.schema.tenant.Token;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.*;
import static com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidatorMethods.*;

public class XLRPCRequest_OfferOptimizer_Allocate {
	
	private static final Logger logger = LoggerFactory.getLogger( XLRPCRequest_OfferOptimizer_Allocate.class );
	
	public enum ExtendedParameters {
		tongue, gender, salary, imei, imsi, hobbies 
	}
	
	NetworkEnvironment env;
	Server actruleServer;
	User superman;
	Mysql mysql;
	DAOToken daoToken;
	DAOSubscribers daoSubscribers;
	
	/** VALID PARAMETERS */
	/** xmlrpc valid parameters */
	String msisdn;
	String token;
	
	/** NULL PARAMETERS */
	/** xmlrpc null parameters */
	final String msisdnNull = null;
	final String tokenNull = null;

	/** WRONG PARAMETERS */	
	/** xmlrpc wrong parameters */
	String msisdnWrong;
	String tokenWrong;
	
	/** OVER LENGTH PARAMETERS */	
	/** xmlrpc over length parameters */
	String msisdnOverLength;
	String tokenOverLength;
	
	
	/* 	Initialize Environment */
	@Parameters({"environment", "tenant", "gui_server", "user"})
	@BeforeClass
	public void init( @Optional("E4O_VM") String environment, @Optional("tenant") String tenant, @Optional("actrule") String gui_server, @Optional("superman") String user ) throws NetworkEnvironmentException {		
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );

		actruleServer = env.getServer( gui_server );
		
		superman = actruleServer.getUser( user );
		
		mysql = new Mysql( env.getDataSource( tenant ) );
		
		daoSubscribers = DAOSubscribers.getInstance( mysql );
		
		daoToken = DAOToken.getInstance( mysql );
		
		/** initialize xmlrpc parameters with correct value */
		msisdn = "3399900001";
		
		/** initialize xmlrpc parameters with wrong value */
		msisdnWrong = "msisdnWrong";
		tokenWrong = "tokenWrong";
		
		/** xmlrpc over length parameters */
		msisdnOverLength = RandomStringUtils.randomNumeric( ( getColumnLenght( Token.class, Token.Fields.msisdn ) + 1 ) );
		tokenOverLength = RandomStringUtils.randomAlphanumeric( ( getColumnLenght( Token.class, Token.Fields.token_code ) + 1 ) );
		
		
		/*

		supportedRatePlan = getFirstValidRatePlan();
		profileObj = getProfileByValidRatePlan( supportedRatePlan.getProfileId() );
		statusObj = getStatusByProfileId( supportedRatePlan.getProfileId() );
		confObjInTag = getFirstValidConfTagByName( ConfTag.in_tags_list ); 
		confObjTongue = getFirstValidConfTagByName( ConfTag.language ); 
		networks = getFirstAvailableNetwork();
		
		*/
			
	}
	
	private Integer getColumnLenght( Class<?> obj, Enum<?> column ) {
		
		try {
			
			Column col = obj.getDeclaredField( column.name() ).getAnnotation( Column.class );
			
			return col.length();
		
		} catch (NoSuchFieldException | SecurityException e) {
			
			logger.error( Log.FAILED.createMessage( e.getMessage() ), e );
			
		}
		
		return null;
		
	}
	
	@Test(enabled=true, priority = 1 )
	public void allocateWithNullMsisdn() throws Exception {
		
		XMLRPCRequest.offeroptimizer_allocate().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								string( msisdnNull ),
								string( tokenNull )
							),
							xmlrpcValidator(
								fault().code( equalTo( 100 ) ),
								fault().message( equalTo( "Subscriber not found: " ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	/*
	 * To enable after fixing https://tracker.lumata.com/browse/EFOGC-2373
	 */
	@Test(enabled=false, priority = 2 )
	public void allocateWithNullToken() throws Exception {
		
		XMLRPCRequest.offeroptimizer_allocate().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								string( msisdn ),
								string( tokenNull )
							),
							xmlrpcValidator(
									fault().code( equalTo( 401 ) ),
									fault().message( equalTo( "Malformed request" ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}

	@Test(enabled=true, priority = 3 )
	public void allocateWithWrongMsisdn() throws Exception {
		
		XMLRPCRequest.offeroptimizer_allocate().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								string( msisdnWrong ),
								string( token )
							),
							xmlrpcValidator(
								fault().code( equalTo( 100 ) ),
								fault().message( equalTo( "Subscriber not found: " + msisdnWrong ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	/*
	 * To enable after fixing https://tracker.lumata.com/browse/EFOGC-2373
	 */
	@Test(enabled=false, priority = 4 )
	public void allocateWithWrongToken() throws Exception {
		
		XMLRPCRequest.offeroptimizer_allocate().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								string( msisdn ),
								string( tokenWrong )
							),
							xmlrpcValidator(
								fault().code( equalTo( 401 ) ),
								fault().message( equalTo( "Malformed request" ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	@Test(enabled=true, priority = 5 )
	public void allocateWithOverLengthMsisdn() throws Exception {
		
		XMLRPCRequest.offeroptimizer_allocate().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								string( msisdnOverLength ),
								string( token )
							),
							xmlrpcValidator(
								fault().code( equalTo( 100 ) ),
								fault().message( equalTo( "Subscriber not found: " + msisdnOverLength ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	/*
	 * To enable after fixing https://tracker.lumata.com/browse/EFOGC-2373
	 */
	@Test(enabled=false, priority = 6 )
	public void allocateWithOverLengthToken() throws Exception {
		
		XMLRPCRequest.offeroptimizer_allocate().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								string( msisdn ),
								string( tokenOverLength )
							),
							xmlrpcValidator(
								fault().code( equalTo( 401 ) ),
								fault().message( equalTo( "Malformed request" ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	@Test(enabled=true, priority = 7 )
	public void allocateActiveToken() throws Exception {
		
		Subscribers subscriber = DAOSubscribers.getInstance( mysql ).getSubscriberWithActiveToken();
		ArrayList<Token> token = DAOToken.getInstance( mysql ).getAvailableActiveTokens( subscriber.getMsisdn() );
		
		if( null != subscriber && null != token && token.size() > 0 ) {
		
			XMLRPCRequest.offeroptimizer_allocate().call( 
								actruleServer, 
								xmlrpcBody(
									authentication( superman ),
									string( msisdn ),
									string( token.get( 0 ).getTokenCode() )
								),
								xmlrpcValidator(
									fault().code( equalTo( 100 ) ),
									fault().message( equalTo( "Subscriber not found: " + msisdnOverLength ) )
								),
								xmlrpcOptions(
									sleep( 100L )	
								)
			
			);
						
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*	
	private Long getNotExitingMsisdn( Long startValue, Long endValue, Integer attempts ) {
		
		Long msisdn = null;
		
		for( int a = 0; a < attempts; a++ ) {
			
			msisdn = Arithmetic.random( startValue, endValue );
			
			if( !DAOSubscribers.getInstance( mysql ).isSubscriber( msisdn ) ) {
				
				return msisdn;
				
			}
			
		}
		
		return msisdn;
		
	}
	
	private SupportedRatePlan getFirstValidRatePlan() {
		
		ArrayList<SupportedRatePlan> supportedRatePlan = DAOSupportedRatePlan.getInstance( mysql ).getAvailableRatePlanList();
		
		if( supportedRatePlan.size() > 0 ) {
			
			return supportedRatePlan.get( 0 );
			
		}
		
		return new SupportedRatePlan();
		
	}
	
	private Profiles getProfileByValidRatePlan( Byte profileId ) {
				
		return DAOProfiles.getInstance( mysql ).getProfileById( Integer.valueOf( profileId.toString() ) );
		
	}
	
	private Statuses getStatusByProfileId( Byte profileId ) {
		
		return DAOStatuses.getInstance( mysql ).getStatusByProfileId( profileId );
				
	}

	private Conf getFirstValidConfTagByName( ConfTag confTag ) {
		
		Conf confObj = new Conf();
		
		ArrayList<Conf> confValueListByName = DAOConf.getInstance( mysql ).getCurrentValueByName( confTag );
		
		if( null != confValueListByName && confValueListByName.size() > 0 ) {
			
			confObj = confValueListByName.get( 0 );
			
		}
		
		return confObj;
		
	}
	
	private Networks getFirstAvailableNetwork() {
		
		Networks network = new Networks();
		
		ArrayList<Networks> networks = DAONetworks.getInstance( mysql ).getAvailableNetworks();
		
		if( null != networks && networks.size() > 0 ) {
			
			network = networks.get( 0 );
			
		}
		
		return network;
		
	}
	
	private Integer getColumnLenght( Class<?> obj, Enum<?> column ) {
		
		try {
			
			Column col = obj.getDeclaredField( column.name() ).getAnnotation( Column.class );
			
			return col.length();
		
		} catch (NoSuchFieldException | SecurityException e) {
			
			logger.error( Log.FAILED.createMessage( e.getMessage() ), e );
			
		}
		
		return null;
		
	}
	
	@Test(enabled=true, priority = 1 )
	public void createSubscriberWithNullMsisdn() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdnNull,
										subscriptionDateNull,
										profileNull,
										ratePlanNull,
										statusNull,
										inTagNull,
										networkNull,
										params(),
										services()																
								)
							),
							xmlrpcValidator(
								fault().code( equalTo( 5 ) ),
								fault().message( equalTo( "missing mandatory param msisdn" ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	@Test(enabled=true, priority = 2 )
	public void createSubscriberWithNullSubscriptionDate() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdn,
										subscriptionDateNull,
										profileNull,
										ratePlanNull,
										statusNull,
										inTagNull,
										networkNull,
										params(),
										services()																
								)
							),
							xmlrpcValidator(
								fault().code( equalTo( 5 ) ),
								fault().message( equalTo( "missing mandatory param subscription_date" ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	@Test(enabled=true, priority = 3 )
	public void createSubscriberWithNullRatePlan() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profileNull,
										ratePlanNull,
										statusNull,
										inTagNull,
										networkNull,
										params(),
										services()																
								)
							),
							xmlrpcValidator(
								fault().code( equalTo( 5 ) ),
								fault().message( equalTo( "missing mandatory param rate_plan" ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	@Test(enabled=true, priority = 4 )
	public void createSubscriberWithNullStatus() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profileNull,
										ratePlan,
										statusNull,
										inTagNull,
										networkNull,
										params(),
										services()																
								)
							),
							xmlrpcValidator(
								fault().code( equalTo( 5 ) ),
								fault().message( equalTo( "missing mandatory param status" ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	@Test(enabled=true, priority = 5 )
	public void createSubscriberWithNullInTag() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profileNull,
										ratePlan,
										status,
										inTagNull,
										networkNull,
										params(),
										services()																
								)
							),
							xmlrpcValidator(
								fault().code( equalTo( 5 ) ),
								fault().message( equalTo( "missing mandatory param in_tag" ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	@Test(enabled=true, priority = 6 )
	public void createSubscriberWithNullNetwork() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profileNull,
										ratePlan,
										status,
										inTag,
										networkNull,
										params(),
										services()																
								)
							),
							xmlrpcValidator(
								fault().code( equalTo( 5 ) ),
								fault().message( equalTo( "missing mandatory param network" ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	/** the profile is not mandatory */
/*	@Test(enabled=true, priority = 7 )
	public void createSubscriberWithNullProfile() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profileNull,
										ratePlan,
										status,
										inTag,
										network,
										params(),
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
		
		Subscribers subscriber = new Subscribers();
		
		subscriber.setMsisdn( Long.valueOf( msisdn ) );
		subscriber.setSubscriptionDate( sdf.parse( subscriptionDate ) );
		subscriber.setProfileId( profileObj.getProfileId() );
		subscriber.setRatePlanId( supportedRatePlan.getRatePlanId() );
		subscriber.setStatusId( statusObj.getStatusId() );
		subscriber.setInTag( confObjInTag.getCurrent() );
		subscriber.setNetworkId( networks.getNetworkId() );
		
		Assert.assertTrue( DAOSubscribers.getInstance( mysql ).isSubscriber( subscriber ) ); 
		
		msisdn = String.valueOf( getNotExitingMsisdn( 3910000000L, 3999999999L, 100 ) );
		
	}
	
	@Test(enabled=true, priority = 8 )
	public void createSubscriberWithoutOptionalParameters() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										ratePlan,
										status,
										inTag,
										network,
										params(),
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
		
		Subscribers subscriber = new Subscribers();
		
		subscriber.setMsisdn( Long.valueOf( msisdn ) );
		subscriber.setSubscriptionDate( sdf.parse( subscriptionDate ) );
		subscriber.setProfileId( profileObj.getProfileId() );
		subscriber.setRatePlanId( supportedRatePlan.getRatePlanId() );
		subscriber.setStatusId( statusObj.getStatusId() );
		subscriber.setInTag( confObjInTag.getCurrent() );
		subscriber.setNetworkId( networks.getNetworkId() );
		
		Assert.assertTrue( DAOSubscribers.getInstance( mysql ).isSubscriber( subscriber ) ); 
		
		msisdn = String.valueOf( getNotExitingMsisdn( 3910000000L, 3999999999L, 100 ) );
		
	}
		
	@Test(enabled=true, priority = 9 )
	public void createSubscriberWithWrongMsisdn() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdnWrong,
										subscriptionDate,
										profile,
										ratePlan,
										status,
										inTag,
										network,
										params(),
										services()																
								)
							),
							xmlrpcValidator(
								fault().code( equalTo( 2 ) ),
								fault().message( equalTo( "unable to create subscriber" ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	@Test(enabled=true, priority = 10 )
	public void createSubscriberWithWrongSubscriptionDate() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdn,
										subscriptionDateWrong,
										profile,
										ratePlan,
										status,
										inTag,
										network,
										params(),
										services()																
								)
							),
							xmlrpcValidator(
								fault().code( equalTo( 6 ) ),
								fault().message( equalTo( "invalid subscription_date " + subscriptionDateWrong ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	@Test(enabled=true, priority = 11 )
	public void createSubscriberWithWrongProfile() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profileWrong,
										ratePlan,
										status,
										inTag,
										network,
										params(),
										services()																
								)
							),
							xmlrpcValidator(
								fault().code( equalTo( 6 ) ),
								fault().message( equalTo( "invalid profile " + profileWrong ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	@Test(enabled=true, priority = 12 )
	public void createSubscriberWithWrongRatePlan() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										ratePlanWrong,
										status,
										inTag,
										network,
										params(),
										services()																
								)
							),
							xmlrpcValidator(
								fault().code( equalTo( 6 ) ),
								fault().message( equalTo( "invalid rate_plan " + ratePlanWrong ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	@Test(enabled=true, priority = 13 )
	public void createSubscriberWithWrongStatus() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										ratePlan,
										statusWrong,
										inTag,
										network,
										params(),
										services()																
								)
							),
							xmlrpcValidator(
								fault().code( equalTo( 6 ) ),
								fault().message( equalTo( "invalid status " + statusWrong ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	@Test(enabled=true, priority = 14 )
	public void createSubscriberWithWrongInTag() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										ratePlan,
										status,
										inTagWrong,
										network,
										params(),
										services()																
								)
							),
							xmlrpcValidator(
								fault().code( equalTo( 6 ) ),
								fault().message( equalTo( "invalid in_tag " + inTagWrong ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	@Test(enabled=true, priority = 15 )
	public void createSubscriberWithWrongNetwork() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										ratePlan,
										status,
										inTag,
										networkWrong,
										params(),
										services()																
								)
							),
							xmlrpcValidator(
								fault().code( equalTo( 6 ) ),
								fault().message( equalTo( "invalid network " + networkWrong ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	@Test(enabled=true, priority = 16 )
	public void createSubscriberWithOverLengthMsisdn() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdnOverLength,
										subscriptionDate,
										profile,
										ratePlan,
										status,
										inTag,
										network,
										params(),
										services()																
								)
							),
							xmlrpcValidator(
								fault().code( equalTo( 2 ) ),
								fault().message( equalTo( "unable to create subscriber" ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	@Test(enabled=true, priority = 17 )
	public void createSubscriberWithOverLengthSubscriptionDate() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdn,
										subscriptionDateOverLength,
										profile,
										ratePlan,
										status,
										inTag,
										network,
										params(),
										services()																
								)
							),
							xmlrpcValidator(
								fault().code( equalTo( 6 ) ),
								fault().message( equalTo( "invalid subscription_date " + subscriptionDateOverLength ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	@Test(enabled=true, priority = 18 )
	public void createSubscriberWithOverLengthProfile() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profileOverLength,
										ratePlan,
										status,
										inTag,
										network,
										params(),
										services()																
								)
							),
							xmlrpcValidator(
								fault().code( equalTo( 6 ) ),
								fault().message( equalTo( "invalid profile " + profileOverLength ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	@Test(enabled=true, priority = 19 )
	public void createSubscriberWithOverLengthRatePlan() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										ratePlanOverLength,
										status,
										inTag,
										network,
										params(),
										services()																
								)
							),
							xmlrpcValidator(
								fault().code( equalTo( 6 ) ),
								fault().message( equalTo( "invalid rate_plan " + ratePlanOverLength ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	@Test(enabled=true, priority = 20 )
	public void createSubscriberWithOverLengthStatus() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										ratePlan,
										statusOverLength,
										inTag,
										network,
										params(),
										services()																
								)
							),
							xmlrpcValidator(
								fault().code( equalTo( 6 ) ),
								fault().message( equalTo( "invalid status " + statusOverLength ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	@Test(enabled=true, priority = 21 )
	public void createSubscriberWithOverLengthInTag() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										ratePlan,
										status,
										inTagOverLength,
										network,
										params(),
										services()																
								)
							),
							xmlrpcValidator(
								fault().code( equalTo( 6 ) ),
								fault().message( equalTo( "invalid in_tag " + inTagOverLength ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	@Test(enabled=true, priority = 22 )
	public void createSubscriberWithOverLengthNetwork() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										ratePlan,
										status,
										inTag,
										networkOverLength,
										params(),
										services()																
								)
							),
							xmlrpcValidator(
								fault().code( equalTo( 6 ) ),
								fault().message( equalTo( "invalid network " + networkOverLength ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	@Test(enabled=true, priority = 23 )
	public void createSubscriberWithNotValidProfile() throws Exception {
		
		Profiles notValidProfile = DAOProfiles.getInstance( mysql ).getNotValidProfileByRatePlan( supportedRatePlan );
		
		String notValidProfileStr = ( null != notValidProfile ? String.valueOf( notValidProfile.getProfileId() ) : "" ); 
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdn,
										subscriptionDate,
										notValidProfileStr,
										ratePlan,
										status,
										inTag,
										network,
										params(),
										services()																
								)
							),
							xmlrpcValidator(
								fault().code( equalTo( 6 ) ),
								fault().message( equalTo( "invalid profile " + notValidProfileStr ) )
							),
							xmlrpcOptions(
								sleep( 100L )
							)
		
		);
		
	}
		
	@Test(enabled=true, priority = 24 )
	public void createSubscriberWithExtendedParametersNotExisting() throws Exception {
				
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										ratePlan,
										status,
										inTag,
										network,
										params(
											param( "NotExisting", "" )	
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
		
		Subscribers subscriber = new Subscribers();
		
		subscriber.setMsisdn( Long.valueOf( msisdn ) );
		subscriber.setSubscriptionDate( sdf.parse( subscriptionDate ) );
		subscriber.setProfileId( profileObj.getProfileId() );
		subscriber.setRatePlanId( supportedRatePlan.getRatePlanId() );
		subscriber.setStatusId( statusObj.getStatusId() );
		subscriber.setInTag( confObjInTag.getCurrent() );
		subscriber.setNetworkId( networks.getNetworkId() );
		
		Assert.assertTrue( DAOSubscribers.getInstance( mysql ).isSubscriber( subscriber ) ); 
		
		msisdn = String.valueOf( getNotExitingMsisdn( 3910000000L, 3999999999L, 100 ) );
		
	}
	
	@Test(enabled=true, priority = 25 )
	public void createSubscriberWithExtendedParametersWrongTongue() throws Exception {
				
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										ratePlan,
										status,
										inTag,
										network,
										params(
											param( ExtendedParameters.tongue, "" )	
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
		
		/*
		Subscribers subscriber = new Subscribers();
		
		subscriber.setMsisdn( Long.valueOf( msisdn ) );
		subscriber.setSubscriptionDate( sdf.parse( subscriptionDate ) );
		subscriber.setProfileId( profileObj.getProfileId() );
		subscriber.setRatePlanId( supportedRatePlan.getRatePlanId() );
		subscriber.setStatusId( statusObj.getStatusId() );
		subscriber.setInTag( confObj.getCurrent() );
		subscriber.setNetworkId( networks.getNetworkId() );
		
		Assert.assertTrue( DAOSubscribers.getInstance( mysql ).isSubscriber( subscriber ) ); 
		
		msisdn = String.valueOf( getNotExitingMsisdn( 3910000000L, 3999999999L, 100 ) );
		
		*/
		
	//}
	
	
	
	
	/*
	 * 
			<params>
					  <param>
					    <paramName>tongue</paramName>
					    <paramValue>SPA</paramValue>
					  </param>
					  <param>
					    <paramName>gender</paramName>
					    <paramValue>FEMALE</paramValue>
					  </param>					  
            <param>
					    <paramName>salary</paramName>
					    <paramValue>100000</paramValue>
					  </param>					  
					  <param>
					    <paramName>imei</paramName>
					    <paramValue>111177775526</paramValue>
					  </param>
					  <param>
					    <paramName>imsi</paramName>
					    <paramValue>10003333423</paramValue>
					  </param>
					  <param>
					    <paramName>hobbies</paramName>
					    <paramValue>Eat,Walk</paramValue>
					  </param>					  
					</params>
	 */
	
	
	
	
	//-----------------------------------------
	
	
	
	
	/*
	@Test(enabled=true, priority = 2 )
	public void createSubscriberWithWrongSubscriptionDate() throws Exception {
		
		final Long msisdn = 3399900001L;
		final String subscriptionDate = null;
		final String profile = null;
		final String ratePlan = null;
		final String status = null;
		final String inTag = null;
		final String network = null;
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										ratePlan,
										status,
										inTag,
										network,
										params(),
										services()																
								)
							),
							xmlrpcValidator(
								fault().code( equalTo( 5 ) ),
								fault().message( equalTo( "missing mandatory param subscription_date" ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	@Test(enabled=true, priority = 3 )
	public void createSubscriberWithWrongRatePlan() throws Exception {
		
		final Long msisdn = 3399900001L;
		final String subscriptionDate = sdf.format( today.getTime() );
		final String profile = null;
		final String ratePlan = null;
		final String status = null;
		final String inTag = null;
		final String network = null;
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										ratePlan,
										status,
										inTag,
										network,
										params(),
										services()																
								)
							),
							xmlrpcValidator(
								fault().code( equalTo( 5 ) ),
								fault().message( equalTo( "missing mandatory param rate_plan" ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	@Test(enabled=true, priority = 3 )
	public void createSubscriberWithWrongProfile() throws Exception {
		
		final Long msisdn = 3399900001L;
		final String subscriptionDate = sdf.format( today.getTime() );
		final String profile = null;
		final String ratePlan = "FUN";
		final String status = null;
		final String inTag = null;
		final String network = null;
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										ratePlan,
										status,
										inTag,
										network,
										params(),
										services()																
								)
							),
							xmlrpcValidator(
								fault().code( equalTo( 5 ) ),
								fault().message( equalTo( "missing mandatory param rate_plan" ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test(enabled=false, priority = 1 )
	public void createSubscriberWithWrongMsisdn1() throws Exception {
		
		final Long msisdn = 339990000L;
		final String subscriptionDate = sdf.format( Calendar.getInstance().getTime() );
		final String profile = "prepaid";
		final String ratePlan = "FUN";
		final String status = "active";
		final String inTag = "QAIN";
		final String network = "mobile";
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
				actruleServer, 
				xmlrpcBody(
					authentication( superman ),
					subscriber( 
							msisdn,
							subscriptionDate,
							profile,
							ratePlan,
							status,
							inTag,
							network,
							params(
								param( "imei", 123456 ),
								param( "imsi", 123456 ),
								param( "gender", "male" ),
								param( "tongue", "FRA" ),
								param( "gender", "male" ),
								param( "birthdate", "1981-10-20" ),
								param( "zipcode", 123456 ),
								param( "province", "Brussels" ),
								param( "region", "Brussels" )														     
							),
							services( "voice", "data" )																
					)
				),
				xmlrpcValidator(
					fault().code( equalTo( 102 ) ),
					fault().message( equalTo( "subscriber already exist for msisdn " + msisdn ) )
				),
				xmlrpcOptions(
					sleep( 100L )	
				)
		);
		
	}
	*/
}
