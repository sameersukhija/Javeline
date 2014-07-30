package com.lumata.unit.webservices.xmlrpc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
import com.lumata.common.testing.utils.Arithmetic;
import com.lumata.e4o.dao.tenant.DAOConf;
import com.lumata.e4o.dao.tenant.DAONetworks;
import com.lumata.e4o.dao.tenant.DAOProfiles;
import com.lumata.e4o.dao.tenant.DAOStatuses;
import com.lumata.e4o.dao.tenant.DAOSubscribers;
import com.lumata.e4o.dao.tenant.DAOSupportedRatePlan;
import com.lumata.e4o.dao.tenant.DAOConf.ConfTag;
import com.lumata.e4o.schema.tenant.Conf;
import com.lumata.e4o.schema.tenant.Networks;
import com.lumata.e4o.schema.tenant.Profiles;
import com.lumata.e4o.schema.tenant.Statuses;
import com.lumata.e4o.schema.tenant.SupportedRatePlan;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.*;
import static com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCParameter.*;
//import static com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidatorMethods.*;
import static com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCSubscriberChannel.*;
//import static com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCSubscriberRelation.*;

public class XMLRPCRequest_Subscribermanager_UpdateSubscriber {
	
	private static final Logger logger = LoggerFactory.getLogger( XMLRPCRequest_Subscribermanager_UpdateSubscriber.class );
	
	public enum ExtendedParameters {
		tongue, gender, salary, imei, imsi, hobbies 
	}
	
	NetworkEnvironment env;
	Server actruleServer;
	User superman;
	Mysql mysql;
	DAOSubscribers daoSubscribers;
	SimpleDateFormat sdf;
	Calendar today;
	
	/** VALID PARAMETERS */
	/** xmlrpc valid parameters */
	String msisdn;
	String subscriptionDate;
	String profile;
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
	public void init( @Optional("E4O_VM") String environment, @Optional("tenant") String tenant, @Optional("actrule") String gui_server, @Optional("superman") String user ) throws NetworkEnvironmentException {		
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );

		actruleServer = env.getServer( gui_server );
		
		superman = actruleServer.getUser( user );
		
		mysql = new Mysql( env.getDataSource( tenant ) );
		
		daoSubscribers = DAOSubscribers.getInstance( mysql );
		
		sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		
		today = Calendar.getInstance();
					
	}
	
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
		
		msisdn = "3399900001";
		subscriptionDate = sdf.format( today.getTime() );
		profile = "prepaid";
		ratePlan = "FUN";
		status = "active";	
		inTag = "QAIN";
		network = "mobile";
		
		XMLRPCRequest.subscribermanager_updateSubscriber().call( 
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
										channels(
											channel( ChannelType.MAIL, "customer@lumatagroup.com", Status.active )	
										),
										relations(),
										params(
											param( "gender", "FEMALE" )	
										),
										services()																
								)
							),
							xmlrpcOptions(
								sleep( 100L )								
							)
		
		);
		
	}

}
