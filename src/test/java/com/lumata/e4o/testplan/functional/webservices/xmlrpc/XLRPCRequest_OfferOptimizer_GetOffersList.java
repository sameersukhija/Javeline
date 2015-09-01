package com.lumata.e4o.testplan.functional.webservices.xmlrpc;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.dao.tenant.DAOSubscribers;
import com.lumata.e4o.dao.tenant.DAOToken;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.*;

public class XLRPCRequest_OfferOptimizer_GetOffersList {
	
//	private static final Logger logger = LoggerFactory.getLogger( XLRPCRequest_OfferOptimizer_GetOffersList.class );
	
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
//		msisdnOverLength = RandomStringUtils.randomNumeric( ( getColumnLenght( Token.class, Token.Fields.msisdn ) + 1 ) );
//		tokenOverLength = RandomStringUtils.randomAlphanumeric( ( getColumnLenght( Token.class, Token.Fields.token_code ) + 1 ) );
		
		
		/*

		supportedRatePlan = getFirstValidRatePlan();
		profileObj = getProfileByValidRatePlan( supportedRatePlan.getProfileId() );
		statusObj = getStatusByProfileId( supportedRatePlan.getProfileId() );
		confObjInTag = getFirstValidConfTagByName( ConfTag.in_tags_list ); 
		confObjTongue = getFirstValidConfTagByName( ConfTag.language ); 
		networks = getFirstAvailableNetwork();
		
		*/
			
	}
	
//	private Integer getColumnLenght( Class<?> obj, Enum<?> column ) {
//		
//		try {
//			
//			Column col = obj.getDeclaredField( column.name() ).getAnnotation( Column.class );
//			
//			return col.length();
//		
//		} catch (NoSuchFieldException | SecurityException e) {
//			
//			logger.error( Log.FAILED.createMessage( e.getMessage() ), e );
//			
//		}
//		
//		return null;
//		
//	}
	
	@Test(enabled=true, priority = 1 )
	public void getOffersList() throws Exception {
		
		String msisdn = "3399900001";
		String token = "gl-cc48b";
  		
		XMLRPCRequest.offeroptimizer_getOffersList().call( 
			actruleServer, 
			xmlrpcBody(
				authentication( superman ),
				string( msisdn ),
				string( token )
			),
			/*xmlrpcValidator(
				fault().code( equalTo( 100 ) ),
				fault().message( equalTo( "Subscriber not found: " ) )
			),*/
			xmlrpcOptions(
				sleep( 100L ),
				storeRequestAsResource( "xmlrpc/request/", "request.xml" ),
				storeResponseAsResource( "xmlrpc/response/", "response.xml" )	
			)
		
		);
		
	}

}
