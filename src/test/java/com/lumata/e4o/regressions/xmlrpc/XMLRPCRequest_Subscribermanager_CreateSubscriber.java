package com.lumata.e4o.regressions.xmlrpc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static org.hamcrest.Matchers.*;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.annotations.mysql.Column;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.utils.Arithmetic;
import com.lumata.e4o.dao.tenant.DAOConf;
import com.lumata.e4o.dao.tenant.DAONetworks;
import com.lumata.e4o.dao.tenant.DAOProfiles;
import com.lumata.e4o.dao.tenant.DAOSetHobbies;
import com.lumata.e4o.dao.tenant.DAOSetOptions;
import com.lumata.e4o.dao.tenant.DAOStatuses;
import com.lumata.e4o.dao.tenant.DAOSubscribers;
import com.lumata.e4o.dao.tenant.DAOSupportedRatePlan;
import com.lumata.e4o.dao.tenant.DAOConf.ConfTag;
import com.lumata.e4o.exceptions.GeneratorException;
import com.lumata.e4o.generators.common.Generator;
import com.lumata.e4o.schema.tenant.Conf;
import com.lumata.e4o.schema.tenant.Networks;
import com.lumata.e4o.schema.tenant.Profiles;
import com.lumata.e4o.schema.tenant.SetHobbies;
import com.lumata.e4o.schema.tenant.SetOptions;
import com.lumata.e4o.schema.tenant.Statuses;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.schema.tenant.SupportedRatePlan;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCMysqlMaster;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.*;
import static com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCParameter.*;
import static com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidatorMethods.*;

@TCMysqlMaster
public class XMLRPCRequest_Subscribermanager_CreateSubscriber extends ParentTestCase {
	
	private static final Logger logger = LoggerFactory.getLogger( XMLRPCRequest_Subscribermanager_CreateSubscriber.class );
	
	final boolean TEST_ENABLED = true;
	
	public enum ExtendedParameters {
		tongue, gender, salary, imei, imsi, hobbies, options 
	}
	
//	NetworkEnvironment env;
//	Server guiServer;
//	User user;
//	Mysql mysqlMaster;
	DAOSubscribers daoSubscribers;
	SimpleDateFormat sdf;
	Calendar today;
	
	/** VALID PARAMETERS */
	/** xmlrpc valid parameters */
	String msisdn;
	String subscriptionDate;
	String profile;
	String subprofile;
	String ratePlan;
	String status;
	String inTag;
	String network;

	/** xmlrpc valid extended parameters */
	String tongue;
	String gender;
	String salary;
	String imei;
	String imsi;
	String hobbies;
	
	
	/** NULL PARAMETERS */
	/** xmlrpc null parameters */
	final String msisdnNull = null;
	final String subscriptionDateNull = null;
	final String profileNull = null;
	final String ratePlanNull = null;
	final String statusNull = null;
	final String inTagNull = null;
	final String networkNull = null;

	/** xmlrpc null extended parameters */
	final String tongueNull = null;
	final String genderNull = null;
	final String salaryNull = null;
	final String imeiNull = null;
	final String imsiNull = null;
	final String hobbiesNull = null;

	
	/** WRONG PARAMETERS */	
	/** xmlrpc wrong parameters */
	String msisdnWrong;
	String subscriptionDateWrong;
	String profileWrong;
	String ratePlanWrong;
	String statusWrong;
	String inTagWrong;
	String networkWrong;

	/** xmlrpc wrong extended parameters */
	String tongueWrong;
	String genderWrong;
	String salaryWrong;
	String imeiWrong;
	String imsiWrong;
	String hobbiesWrong;

	
	/** OVER LENGTH PARAMETERS */	
	/** xmlrpc over length parameters */
	String msisdnOverLength;
	String subscriptionDateOverLength;
	String profileOverLength;
	String ratePlanOverLength;
	String statusOverLength;
	String inTagOverLength;
	String networkOverLength;
	
	/** xmlrpc over length extended parameters */
	String tongueOverLength;
	String genderOverLength;
	String salaryOverLength;
	String imeiOverLength;
	String imsiOverLength;
	String hobbiesOverLength;

	
	SupportedRatePlan supportedRatePlan;
	Profiles profileObj;
	Statuses statusObj;
	Conf confObjInTag; 
	Conf confObjTongue; 
	Networks networks;
	
	/* 	Initialize Environment */
	@Parameters({"environment", "tenant", "gui_server", "user"})
	@BeforeClass
	public void initTest( ) throws NetworkEnvironmentException, GeneratorException {		
		
		daoSubscribers = DAOSubscribers.getInstance( mysqlMaster );
		
		sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		
		today = Calendar.getInstance();

		supportedRatePlan = getFirstValidRatePlan();
		profileObj = getProfileByValidRatePlan( supportedRatePlan.getProfileId() );
		statusObj = getStatusByProfileId( supportedRatePlan.getProfileId() );
		confObjInTag = getFirstValidConfTagByName( ConfTag.in_tags_list ); 
		confObjTongue = getFirstValidConfTagByName( ConfTag.language ); 
		networks = getFirstAvailableNetwork();
		
		String[] languages = confObjTongue.getCurrent().split(";");
		
		/** initialize xmlrpc parameters with correct value */
		msisdn = String.valueOf( getNotExitingMsisdn( 3910000000L, 3999999999L, 100 ) );
		subscriptionDate = sdf.format( today.getTime() );
		ratePlan = supportedRatePlan.getRatePlan();
		profile = profileObj.getProfile();
		subprofile = "";
		status = statusObj.getStatus();
		inTag = confObjInTag.getCurrent();
		network = networks.getNetwork();
		tongue = ( null != languages ? languages[ 0 ] : "" );
		
//		String gender;
//		String salary;
//		String imei;
//		String imsi;
//		String hobbies;
		
		/** initialize xmlrpc parameters with wrong value */
		msisdnWrong = "msisdnWrong";
		subscriptionDateWrong = "2014-";
		profileWrong = "profileWrong";
		ratePlanWrong = "ratePlanWrong";
		statusWrong = "statusWrong";
		inTagWrong = "inTagWrong";
		networkWrong = "networkWrong";	
		
		/** xmlrpc over length parameters */
		msisdnOverLength = RandomStringUtils.randomNumeric( ( getColumnLenght( Subscribers.class, Subscribers.Fields.msisdn ) + 1 ) );
		subscriptionDateOverLength = RandomStringUtils.randomAlphanumeric( ( getColumnLenght( Subscribers.class, Subscribers.Fields.subscription_date ) + 1 ) );
		profileOverLength = RandomStringUtils.randomNumeric( ( getColumnLenght( Subscribers.class, Subscribers.Fields.profile_id ) + 1 ) );
		ratePlanOverLength = RandomStringUtils.randomNumeric( ( getColumnLenght( Subscribers.class, Subscribers.Fields.rate_plan_id ) + 1 ) );
		statusOverLength = RandomStringUtils.randomNumeric( ( getColumnLenght( Subscribers.class, Subscribers.Fields.status_id ) + 1 ) );
		inTagOverLength = RandomStringUtils.randomAlphanumeric( ( getColumnLenght( Subscribers.class, Subscribers.Fields.in_tag ) + 1 ) );
		networkOverLength = RandomStringUtils.randomNumeric( ( getColumnLenght( Subscribers.class, Subscribers.Fields.network_id ) + 1 ) );
		
		/** insert hobbies **/
		//Generator.subscribers().environment( env ).mysql( mysqlMaster ).insertDefaultHobbies();
		Generator.subscribers().environment( env ).mysql( mysqlMaster ).insertHobbies( "hobby_", 64L );
		
		/** insert options **/
		Generator.subscribers().environment( env ).mysql( mysqlMaster ).insertOptions( "option_", 256L );
						
	}
	
	private Long getNotExitingMsisdn( Long startValue, Long endValue, Integer attempts ) {
		
		Long msisdn = null;
		
		for( int a = 0; a < attempts; a++ ) {
			
			msisdn = Arithmetic.random( startValue, endValue );
			
			if( !DAOSubscribers.getInstance( mysqlMaster ).isSubscriber( msisdn ) ) {
				
				return msisdn;
				
			}
			
		}
		
		return msisdn;
		
	}
	
	private SupportedRatePlan getFirstValidRatePlan() {
		
		ArrayList<SupportedRatePlan> supportedRatePlan = DAOSupportedRatePlan.getInstance( mysqlMaster ).getAvailableRatePlanList();
		
		if( supportedRatePlan.size() > 0 ) {
			
			return supportedRatePlan.get( 0 );
			
		}
		
		return new SupportedRatePlan();
		
	}
	
	private Profiles getProfileByValidRatePlan( Byte profileId ) {
				
		return DAOProfiles.getInstance( mysqlMaster ).getProfileById( Integer.valueOf( profileId.toString() ) );
		
	}
	
	private Statuses getStatusByProfileId( Byte profileId ) {
		
		return DAOStatuses.getInstance( mysqlMaster ).getStatusByProfileId( profileId );
				
	}

	private Conf getFirstValidConfTagByName( ConfTag confTag ) {
		
		Conf confObj = new Conf();
		
		ArrayList<Conf> confValueListByName = DAOConf.getInstance( mysqlMaster ).getCurrentValueByName( confTag );
		
		if( null != confValueListByName && confValueListByName.size() > 0 ) {
			
			confObj = confValueListByName.get( 0 );
			
		}
		
		return confObj;
		
	}
	
	private Networks getFirstAvailableNetwork() {
		
		Networks network = new Networks();
		
		ArrayList<Networks> networks = DAONetworks.getInstance( mysqlMaster ).getAvailableNetworks();
		
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
	
	@Test(enabled=TEST_ENABLED, priority = 1 )
	public void createSubscriberWithNullMsisdn() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdnNull,
										subscriptionDateNull,
										profileNull,
										subprofile,
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
	
	@Test(enabled=TEST_ENABLED, priority = 2 )
	public void createSubscriberWithNullSubscriptionDate() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDateNull,
										profileNull,
										subprofile,
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
	
	@Test(enabled=TEST_ENABLED, priority = 3 )
	public void createSubscriberWithNullRatePlan() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profileNull,
										subprofile,
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
	
	@Test(enabled=TEST_ENABLED, priority = 4 )
	public void createSubscriberWithNullStatus() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profileNull,
										subprofile,
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
	
	@Test(enabled=TEST_ENABLED, priority = 5 )
	public void createSubscriberWithNullInTag() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profileNull,
										subprofile,
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
	
	@Test(enabled=TEST_ENABLED, priority = 6 )
	public void createSubscriberWithNullNetwork() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profileNull,
										subprofile,
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
	@Test(enabled=TEST_ENABLED, priority = 7 )
	public void createSubscriberWithNullProfile() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profileNull,
										subprofile,
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
		
		Assert.assertTrue( DAOSubscribers.getInstance( mysqlMaster ).isSubscriber( subscriber ) ); 
		
		msisdn = String.valueOf( getNotExitingMsisdn( 3910000000L, 3999999999L, 100 ) );
		
	}
	
	@Test(enabled=TEST_ENABLED, priority = 8 )
	public void createSubscriberWithoutOptionalParameters() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
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
		
		Assert.assertTrue( DAOSubscribers.getInstance( mysqlMaster ).isSubscriber( subscriber ) ); 
		
		msisdn = String.valueOf( getNotExitingMsisdn( 3910000000L, 3999999999L, 100 ) );
		
	}
		
	@Test(enabled=TEST_ENABLED, priority = 9 )
	public void createSubscriberWithWrongMsisdn() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdnWrong,
										subscriptionDate,
										profile,
										subprofile,
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
	
	@Test(enabled=TEST_ENABLED, priority = 10 )
	public void createSubscriberWithWrongSubscriptionDate() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDateWrong,
										profile,
										subprofile,
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
	
	@Test(enabled=TEST_ENABLED, priority = 11 )
	public void createSubscriberWithWrongProfile() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profileWrong,
										subprofile,
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
	
	@Test(enabled=TEST_ENABLED, priority = 12 )
	public void createSubscriberWithWrongRatePlan() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
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
	
	@Test(enabled=TEST_ENABLED, priority = 13 )
	public void createSubscriberWithWrongStatus() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
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
	
	@Test(enabled=TEST_ENABLED, priority = 14 )
	public void createSubscriberWithWrongInTag() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
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
	
	@Test(enabled=TEST_ENABLED, priority = 15 )
	public void createSubscriberWithWrongNetwork() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
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
	
	@Test(enabled=TEST_ENABLED, priority = 16 )
	public void createSubscriberWithOverLengthMsisdn() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdnOverLength,
										subscriptionDate,
										profile,
										subprofile,
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
	
	@Test(enabled=TEST_ENABLED, priority = 17 )
	public void createSubscriberWithOverLengthSubscriptionDate() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDateOverLength,
										profile,
										subprofile,
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
	
	@Test(enabled=TEST_ENABLED, priority = 18 )
	public void createSubscriberWithOverLengthProfile() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profileOverLength,
										subprofile,
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
	
	@Test(enabled=TEST_ENABLED, priority = 19 )
	public void createSubscriberWithOverLengthRatePlan() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
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
	
	@Test(enabled=TEST_ENABLED, priority = 20 )
	public void createSubscriberWithOverLengthStatus() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
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
	
	@Test(enabled=TEST_ENABLED, priority = 21 )
	public void createSubscriberWithOverLengthInTag() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
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
	
	@Test(enabled=TEST_ENABLED, priority = 22 )
	public void createSubscriberWithOverLengthNetwork() throws Exception {
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
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
	
	@Test(enabled=TEST_ENABLED, priority = 23 )
	public void createSubscriberWithNotValidProfile() throws Exception {
		
		Profiles notValidProfile = DAOProfiles.getInstance( mysqlMaster ).getNotValidProfileByRatePlan( supportedRatePlan );
		
		String notValidProfileStr = ( null != notValidProfile ? String.valueOf( notValidProfile.getProfileId() ) : "" ); 
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										notValidProfileStr,
										subprofile,
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
		
	@Test(enabled=TEST_ENABLED, priority = 24 )
	public void createSubscriberWithExtendedParametersNotExisting() throws Exception {
				
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
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
		
		Assert.assertTrue( DAOSubscribers.getInstance( mysqlMaster ).isSubscriber( subscriber ) ); 
		
		msisdn = String.valueOf( getNotExitingMsisdn( 3910000000L, 3999999999L, 100 ) );
		
	}
	
	@Test(enabled=TEST_ENABLED, priority = 25 )
	public void createSubscriberWithExtendedParametersEmptyTongue() throws Exception {
				
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
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
		
		
		Subscribers subscriber = new Subscribers();
		
		subscriber.setMsisdn( Long.valueOf( msisdn ) );
		subscriber.setSubscriptionDate( sdf.parse( subscriptionDate ) );
		subscriber.setProfileId( profileObj.getProfileId() );
		subscriber.setRatePlanId( supportedRatePlan.getRatePlanId() );
		subscriber.setStatusId( statusObj.getStatusId() );
		subscriber.setInTag( confObjInTag.getCurrent() );
		subscriber.setNetworkId( networks.getNetworkId() );
		
		Assert.assertTrue( DAOSubscribers.getInstance( mysqlMaster ).isSubscriber( subscriber ) ); 
		
		msisdn = String.valueOf( getNotExitingMsisdn( 3910000000L, 3999999999L, 100 ) );	
		
	}
	
	@Test(enabled=TEST_ENABLED, priority = 26 )
	public void createSubscriberWithExtendedParametersWrongTongue() throws Exception {
				
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
										ratePlan,
										status,
										inTag,
										network,
										params(
											param( ExtendedParameters.tongue, "USA" )	
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
		
		Assert.assertTrue( DAOSubscribers.getInstance( mysqlMaster ).isSubscriber( subscriber ) ); 
		
		msisdn = String.valueOf( getNotExitingMsisdn( 3910000000L, 3999999999L, 100 ) );	
		
	}
	
	@Test(enabled=TEST_ENABLED, priority = 27 )
	public void createSubscriberWithExtendedParametersCorrectTongue() throws Exception {
				
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
										ratePlan,
										status,
										inTag,
										network,
										params(
											param( ExtendedParameters.tongue, "ENG" )	
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
		
		Assert.assertTrue( DAOSubscribers.getInstance( mysqlMaster ).isSubscriber( subscriber ) ); 
		
		msisdn = String.valueOf( getNotExitingMsisdn( 3910000000L, 3999999999L, 100 ) );	
		
	}
	
	@Test(enabled=TEST_ENABLED, priority = 28 )
	public void createSubscriberWithExtendedParametersEmptyGender() throws Exception {
				
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
										ratePlan,
										status,
										inTag,
										network,
										params(
											param( ExtendedParameters.gender, "" )	
										),
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
	
	@Test(enabled=TEST_ENABLED, priority = 29 )
	public void createSubscriberWithExtendedParametersWrongGender() throws Exception {
				
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
										ratePlan,
										status,
										inTag,
										network,
										params(
											param( ExtendedParameters.gender, "WRONG" )	
										),
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
	
	@Test(enabled=TEST_ENABLED, priority = 30 )
	public void createSubscriberWithExtendedParametersCorrectGender() throws Exception {
				
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
										ratePlan,
										status,
										inTag,
										network,
										params(
											param( ExtendedParameters.gender, "MALE" )	
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
		
		Assert.assertTrue( DAOSubscribers.getInstance( mysqlMaster ).isSubscriber( subscriber ) ); 
		
		msisdn = String.valueOf( getNotExitingMsisdn( 3910000000L, 3999999999L, 100 ) );
	
	}
	
	@Test(enabled=TEST_ENABLED, priority = 31 )
	public void createSubscriberWithExtendedParametersEmptySalary() throws Exception {
				
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
										ratePlan,
										status,
										inTag,
										network,
										params(
											param( ExtendedParameters.salary, "" )	
										),
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
	
	@Test(enabled=TEST_ENABLED, priority = 32 )
	public void createSubscriberWithExtendedParametersWrongSalary() throws Exception {
				
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
										ratePlan,
										status,
										inTag,
										network,
										params(
											param( ExtendedParameters.salary, "WRONG" )	
										),
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
	
	@Test(enabled=TEST_ENABLED, priority = 33 )
	public void createSubscriberWithExtendedParametersCorrectSalary() throws Exception {
				
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
										ratePlan,
										status,
										inTag,
										network,
										params(
											param( ExtendedParameters.salary, "1000.00" )	
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
		
		Assert.assertTrue( DAOSubscribers.getInstance( mysqlMaster ).isSubscriber( subscriber ) ); 
		
		msisdn = String.valueOf( getNotExitingMsisdn( 3910000000L, 3999999999L, 100 ) );
	
	}
	
	@Test(enabled=TEST_ENABLED, priority = 34 )
	public void createSubscriberWithExtendedParametersEmptyImei() throws Exception {
				
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
										ratePlan,
										status,
										inTag,
										network,
										params(
											param( ExtendedParameters.imei, "" )	
										),
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
	
	@Test(enabled=TEST_ENABLED, priority = 35 )
	public void createSubscriberWithExtendedParametersWrongImei() throws Exception {
				
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
										ratePlan,
										status,
										inTag,
										network,
										params(
											param( ExtendedParameters.imei, "WRONG" )	
										),
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
	
	@Test(enabled=TEST_ENABLED, priority = 36 )
	public void createSubscriberWithExtendedParametersCorrectImei() throws Exception {
				
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
										ratePlan,
										status,
										inTag,
										network,
										params(
											param( ExtendedParameters.imei, "1234567890" )	
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
		
		Assert.assertTrue( DAOSubscribers.getInstance( mysqlMaster ).isSubscriber( subscriber ) ); 
		
		msisdn = String.valueOf( getNotExitingMsisdn( 3910000000L, 3999999999L, 100 ) );
	
	}
	
	@Test(enabled=TEST_ENABLED, priority = 37 )
	public void createSubscriberWithExtendedParametersEmptyImsi() throws Exception {
				
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
										ratePlan,
										status,
										inTag,
										network,
										params(
											param( ExtendedParameters.imsi, "" )	
										),
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
	
	@Test(enabled=TEST_ENABLED, priority = 38 )
	public void createSubscriberWithExtendedParametersWrongImsi() throws Exception {
				
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
										ratePlan,
										status,
										inTag,
										network,
										params(
											param( ExtendedParameters.imsi, "WRONG" )	
										),
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
	
	@Test(enabled=TEST_ENABLED, priority = 39 )
	public void createSubscriberWithExtendedParametersCorrectImsi() throws Exception {
				
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
										ratePlan,
										status,
										inTag,
										network,
										params(
											param( ExtendedParameters.imsi, "1234567890" )	
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
		
		Assert.assertTrue( DAOSubscribers.getInstance( mysqlMaster ).isSubscriber( subscriber ) ); 
		
		msisdn = String.valueOf( getNotExitingMsisdn( 3910000000L, 3999999999L, 100 ) );
	
	}
	
	@Test(enabled=TEST_ENABLED, priority = 40 )
	public void createSubscriberWithExtendedParametersEmptyHobbies() throws Exception {
				
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
										ratePlan,
										status,
										inTag,
										network,
										params(
											param( ExtendedParameters.hobbies, "" )	
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
		
		msisdn = String.valueOf( getNotExitingMsisdn( 3910000000L, 3999999999L, 100 ) );
	
	}
	
	@Test(enabled=TEST_ENABLED, priority = 41 )
	public void createSubscriberWithExtendedParametersWrongHobbies() throws Exception {
				
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
										ratePlan,
										status,
										inTag,
										network,
										params(
											param( ExtendedParameters.hobbies, "WRONG" )	
										),
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
	
	@Test(enabled=TEST_ENABLED, priority = 42 )
	public void createSubscriberWithExtendedParametersCorrectHobbies() throws Exception {
			
		StringBuilder hobbies = new StringBuilder();
		
//		for( CDR.HOBBIES hobby : CDR.HOBBIES.values() ) {
//			
//			hobbies.append( hobby.name() + ", " );
//			
//		}
		
		ArrayList<SetHobbies> hobbiesList = DAOSetHobbies.getInstance( mysqlMaster ).getHobbiesList();
		
		for( SetHobbies hobby : hobbiesList ) {
			
			hobbies.append( hobby.getHobbiesName() + ", " );
			
		}
		
		hobbies.setLength( hobbies.length() - 2 );
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
										ratePlan,
										status,
										inTag,
										network,
										params(
											param( ExtendedParameters.hobbies, hobbies.toString() )	
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
		
		Assert.assertTrue( DAOSubscribers.getInstance( mysqlMaster ).isSubscriber( subscriber ) ); 
		
		msisdn = String.valueOf( getNotExitingMsisdn( 3910000000L, 3999999999L, 100 ) );
	
	}
	
	@Test(enabled=TEST_ENABLED, priority = 43 )
	public void createSubscriberWithExtendedParametersEmptyOptions() throws Exception {
				
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
										ratePlan,
										status,
										inTag,
										network,
										params(
											param( ExtendedParameters.options, "" )	
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
		
		msisdn = String.valueOf( getNotExitingMsisdn( 3910000000L, 3999999999L, 100 ) );
	
	}
	
	@Test(enabled=TEST_ENABLED, priority = 44 )
	public void createSubscriberWithExtendedParametersWrongOptions() throws Exception {
				
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
										ratePlan,
										status,
										inTag,
										network,
										params(
											param( ExtendedParameters.options, "WRONG" )	
										),
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
	
	// TO CHECK
//	@Test(enabled=true, priority = 45 )
//	public void createSubscriberWithExtendedParametersBigOptions() throws Exception {
//			
//		StringBuilder otpions = new StringBuilder();
//		
//		ArrayList<SetOptions> optionsList = DAOSetOptions.getInstance( mysql ).getOptionsList();
//				
//		for( SetOptions option : optionsList ) {
//			
//			otpions.append( option.getOptionsName() + ", " );
//			
//		}
//		
//		otpions.append( "extra_option1, extra_option2, extra_option3 " );
//		
//		XMLRPCRequest.subscribermanager_createSubscriber().call( 
//							actruleServer, 
//							xmlrpcBody(
//								authentication( superman ),
//								subscriber( 
//										msisdn,
//										subscriptionDate,
//										profile,
//										subprofile,
//										ratePlan,
//										status,
//										inTag,
//										network,
//										params(
//											param( ExtendedParameters.options, otpions )	
//										),
//										services()																
//								)
//							),
//							xmlrpcValidator(
//								fault().code( equalTo( 2 ) ),
//								fault().message( equalTo( "unable to create subscriber" ) )
//							),
//							xmlrpcOptions(
//								sleep( 100L )
//							)
//		
//		);
//	
//	}
	
	@Test(enabled=TEST_ENABLED, priority = 46 )
	public void createSubscriberWithExtendedParametersCorrectOptions() throws Exception {
		
		StringBuilder otpions = new StringBuilder();
		
		ArrayList<SetOptions> optionsList = DAOSetOptions.getInstance( mysqlMaster ).getOptionsList();
				
		for( SetOptions option : optionsList ) {
			
			otpions.append( option.getOptionsName() + ", " );
			
		}
		
		otpions.setLength( otpions.length() - 2 );
		
		XMLRPCRequest.subscribermanager_createSubscriber().call( 
							guiServer, 
							xmlrpcBody(
								authentication( user ),
								subscriber( 
										msisdn,
										subscriptionDate,
										profile,
										subprofile,
										ratePlan,
										status,
										inTag,
										network,
										params(
											param( ExtendedParameters.options, otpions )	
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
		
		Assert.assertTrue( DAOSubscribers.getInstance( mysqlMaster ).isSubscriber( subscriber ) ); 
		
		msisdn = String.valueOf( getNotExitingMsisdn( 3910000000L, 3999999999L, 100 ) );
	
	}

}
