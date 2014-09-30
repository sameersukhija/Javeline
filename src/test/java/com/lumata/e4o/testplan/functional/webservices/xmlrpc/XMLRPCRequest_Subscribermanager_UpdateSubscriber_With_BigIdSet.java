package com.lumata.e4o.testplan.functional.webservices.xmlrpc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import junit.framework.Assert;
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
import com.lumata.common.testing.utils.Arithmetic;
import com.lumata.e4o.dao.tenant.DAOConf;
import com.lumata.e4o.dao.tenant.DAONetworks;
import com.lumata.e4o.dao.tenant.DAOProfiles;
import com.lumata.e4o.dao.tenant.DAOSetOptions;
import com.lumata.e4o.dao.tenant.DAOStatuses;
import com.lumata.e4o.dao.tenant.DAOSubscribers;
import com.lumata.e4o.dao.tenant.DAOSupportedRatePlan;
import com.lumata.e4o.dao.tenant.DAOConf.ConfTag;
import com.lumata.e4o.schema.tenant.Conf;
import com.lumata.e4o.schema.tenant.Networks;
import com.lumata.e4o.schema.tenant.Profiles;
import com.lumata.e4o.schema.tenant.SetOptions;
import com.lumata.e4o.schema.tenant.Statuses;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.schema.tenant.SupportedRatePlan;
import com.lumata.e4o.system.fields.FieldSet;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.*;
import static com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCParameter.*;
import static com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidatorMethods.*;


public class XMLRPCRequest_Subscribermanager_UpdateSubscriber_With_BigIdSet {
	
	private static final Logger logger = LoggerFactory.getLogger( XMLRPCRequest_Subscribermanager_UpdateSubscriber_With_BigIdSet.class );
	
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
		
		supportedRatePlan = getFirstValidRatePlan();
		profileObj = getProfileByValidRatePlan( supportedRatePlan.getProfileId() );
		statusObj = getStatusByProfileId( supportedRatePlan.getProfileId() );
		confObjInTag = getFirstValidConfTagByName( ConfTag.in_tags_list ); 
		confObjTongue = getFirstValidConfTagByName( ConfTag.language ); 
		networks = getFirstAvailableNetwork();
		
		String[] languages = confObjTongue.getCurrent().split(";");
		
		/** initialize xmlrpc parameters with correct value */
		msisdn = "3399900001";
		subscriptionDate = sdf.format( today.getTime() );
		ratePlan = supportedRatePlan.getRatePlan();
		profile = profileObj.getProfile();
		subprofile = "";
		status = statusObj.getStatus();
		inTag = confObjInTag.getCurrent();
		network = networks.getNetwork();
		tongue = ( null != languages ? languages[ 0 ] : "" );
		String gender;
		String salary;
		String imei;
		String imsi;
		String hobbies;
		
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
		
	@Test( enabled=true, priority = 1 )
	public void updateSubscriberWithExtendedParametersBigIdSet() throws Exception {
		
		Boolean useMoreThan1024Options = false;
		
		Set<String> setOptions = new LinkedHashSet<String>();
		
		if( useMoreThan1024Options ) {
			
			ArrayList<SetOptions> options = DAOSetOptions.getInstance( mysql ).getOptionsList();
			
			for( SetOptions option : options ) {
			
				setOptions.add( option.getOptionsName() );
					
			}
			
		} else {
			
			for( int o = 1; o <= 1024; o++ ) {
			
				setOptions.add( "option_" + o );
							
			}
			
		}
		
		FieldSet fs = new FieldSet( setOptions );
		
		fs.setSetStrategyRandom( 1000 );
		
		//for( int subscriber = 1; subscriber <= 1; subscriber++ ) {
		
			//String msisdn = String.valueOf( DAOSubscribers.getInstance( mysql ).getNotExitingMsisdn( 3310000000L, 3999999999L, 100 ) );
				
			String msisdn = "3931975305";
		
			XMLRPCRequest.subscribermanager_updateSubscriber().call( 
				actruleServer, 
				xmlrpcBody(
					authentication( superman ),
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
								param( "options", fs.getSet() )	
							),
							services()																
					)
				),
				xmlrpcOptions(
					sleep( 100L ),
					storeRequestAsResource( "xmlrpc/request/", "request.xml" ),
					storeResponseAsResource( "xmlrpc/response/", "response.xml" )	
				),
				xmlrpcValidator(
					success()
				)
				
			);
			
		//}
		
	}	

}
