package com.lumata.e4o.testplan.functional.webservices.xmlrpc;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.hamcrest.Matchers.*;

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
import com.lumata.e4o.dao.DAOSubscribers;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.*;
import static com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCParameter.*;
import static com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidatorMethods.*;

public class XMLRPCRequest_Subscribermanager_CreateSubscriber {
	
	NetworkEnvironment env;
	Server actruleServer;
	User superman;
	Mysql mysql;
	DAOSubscribers daoSubscribers;
	SimpleDateFormat sdf;
	Calendar today;
			
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
		
		final Long msisdn = null;
		final String subscriptionDate = null;
		final String profile = null;
		final String ratePlan = null;
		final String status = null;
		final String inTag = null;
		final String network = null;
		
	}
	
	
	
	@Test(enabled=true, priority = 1 )
	public void createSubscriberWithWrongMsisdn() throws Exception {
		
		final Long msisdn = null;
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
								fault().message( equalTo( "missing mandatory param msisdn" ) )
							),
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}
	
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
	
}
