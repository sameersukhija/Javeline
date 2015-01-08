package com.lumata.e4o.testplan.functional.o2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
import com.lumata.e4o.dao.tenant.DAOStatuses;
import com.lumata.e4o.dao.tenant.DAOSubscribers;
import com.lumata.e4o.dao.tenant.DAOSupportedRatePlan;
import com.lumata.e4o.dao.tenant.DAOConf.ConfTag;
import com.lumata.e4o.dao.tenant.DAOToken;
import com.lumata.e4o.dao.tenant.DAOTokenLabel;
import com.lumata.e4o.schema.tenant.Conf;
import com.lumata.e4o.schema.tenant.Networks;
import com.lumata.e4o.schema.tenant.Profiles;
import com.lumata.e4o.schema.tenant.Statuses;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.schema.tenant.SupportedRatePlan;
import com.lumata.e4o.schema.tenant.Token;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.*;
import static com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCParameter.*;
import static com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidatorMethods.*;

public class O2ConfigureIMMTokens {
	
	private static final Logger logger = LoggerFactory.getLogger( O2ConfigureIMMTokens.class );
	
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
	
	
	
	final String tokenLabel = "O2";
	
	
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
		
		
	}
	
	/*
	 * insert into token values ( "3336Y6", "491798456944", "8", "0", "8", NULL, "2014-03-30 07:57:15", "2014-04-27 07:58:03", NULL, "1", "1", NULL, "0", "0", "0", "0", "Migration from iMM", NULL, NULL, "0", "1", NULL, "revenue" );
	 * insert into ruleset_channel values ( "8", "5", "0", "1"  );
	 * insert into sales_channels values (  "5", "Churn_Tier1", true );
	 * insert into token_event values ( "6594578", "3336Y6", "491798456944", "2014-03-30 07:57:15", "CREATE", "1", "0", "0", NULL, NULL, "2014-09-21 15:56:25" );
	 * insert into token_type values ( "8", "O2", "8", "28", "days", NULL, "-1", "0", "[ACDEFGHJKMNPQRTWXY34679]{5}", "generic O2 token code", "" );
	 * insert into token_label values ( "8", "O2" );
	 * 
	 */
	
	@Test
	private void initSchema() {
		
		System.out.println( DAOTokenLabel.getInstance( mysql ).isTokenLabel( "O2" ) );
		
		
		
		
		//String query = insert_into()
		
		
		
	}
	
	
	
	
	
	@Test(enabled=false, priority = 1 )
	public void getTokenList() throws Exception {
		
		System.out.println( actruleServer.getHostAddress() );
		
		String msisdn = "491798456944";
		
		XMLRPCRequest.offeroptimizer_getTokensList().call( 
							actruleServer, 
							xmlrpcBody(
								authentication( superman ),
								string( msisdn ),
								string(""),
								string("")
							),
							/*xmlrpcValidator(
								fault().code( equalTo( 100 ) ),
								fault().message( equalTo( "Subscriber not found: " ) )
							),*/
							xmlrpcOptions(
								sleep( 100L )	
							)
		
		);
		
	}

}
