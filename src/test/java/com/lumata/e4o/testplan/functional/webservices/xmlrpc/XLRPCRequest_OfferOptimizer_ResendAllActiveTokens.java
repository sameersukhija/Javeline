package com.lumata.e4o.testplan.functional.webservices.xmlrpc;

import java.util.ArrayList;
import java.util.Calendar;

import static org.hamcrest.Matchers.*;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
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
import com.lumata.e4o.exceptions.GeneratorException;
import com.lumata.e4o.generators.common.Generator;
import com.lumata.e4o.schema.tenant.CatalogOffers;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.schema.tenant.Token;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.*;
import static com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCParameter.*;
import static com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidatorMethods.*;

public class XLRPCRequest_OfferOptimizer_ResendAllActiveTokens {
	
	private static final Logger logger = LoggerFactory.getLogger( XLRPCRequest_OfferOptimizer_ResendAllActiveTokens.class );
	
	NetworkEnvironment env;
	Server actruleServer;
	User superman;
	Mysql mysql;
	Subscribers subscriberWithNoTokens;
	Subscribers subscriberWithActiveTokens;
	ArrayList<Subscribers> allSubscribers;
	DAOToken daoToken;
	DAOSubscribers daoSubscribers;
	Long TOKEN_TO_GENERATE; // this value must be >= 5
	final Long TOKEN_TO_ALLOCATE = 4L; // this value must be less than TOKEN_TO_GENERATE
	final Long TOKEN_TO_ACCEPT = 1L; // this value must be less than TOKEN_TO_ALLOCATE
	final Long TOKEN_TO_REFUSE = 1L; // this value must be less than TOKEN_TO_ALLOCATE - TOKEN_TO_ACCEPT
	final Long TOKEN_TO_BE_ACTIVE = TOKEN_TO_ALLOCATE - TOKEN_TO_ACCEPT - TOKEN_TO_REFUSE;
	final Long TOKEN_EXPIRED = 6L; // this value must be > 0
	
	ArrayList<Token> activeTokens;
	ArrayList<Token> allocatedTokens;
	ArrayList<Token> acceptedTokens;
	ArrayList<Token> refusedTokens;
	ArrayList<Token> expiredTokens;
		
	final boolean TEST_ENABLED = false;
	
	
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
				
		TOKEN_TO_GENERATE = TOKEN_TO_ALLOCATE + TOKEN_TO_ACCEPT + TOKEN_TO_REFUSE + TOKEN_EXPIRED;
		
	}

	@Test( enabled = true, priority = 1 )
	private void loadSubscribers() throws GeneratorException {
		
		allSubscribers = new ArrayList<Subscribers>();
		
		for( int s = 1; s <= 2; s++ ) {
			
			Long msisdn = daoSubscribers.getNotExitingMsisdn( 3399900001L, 3399910000L, 100 );
			
			Generator.subscribers()
				.environment( env )
				.mysql( mysql )
				.msisdnFixed( msisdn )
				.subscriberHasSMSChannel( true )
				.subscriberHasMAILChannel( false )
				.insertIntoEnvironment( 1L );
			
			allSubscribers.add( daoSubscribers.getSubscriber( msisdn ) );
			
		}
				
		Assert.assertTrue( allSubscribers.size() == 2 );
		
	}
		
	@Test( enabled = true, dependsOnMethods = { "loadSubscribers" } )
	private void loadUserWithActiveTokens() throws Exception {
		
		Assert.assertTrue( TOKEN_TO_GENERATE >= 5 );
		
		subscriberWithActiveTokens = allSubscribers.get( 0 );
			
		Generator.subscribers()
			.server( actruleServer )
			.user( superman )
			.msisdnFixed( subscriberWithActiveTokens.getMsisdn() )
			.xmlrpcRecharge( TOKEN_TO_GENERATE );
		
		Assert.assertTrue( daoToken.getAvailableTokens( subscriberWithActiveTokens.getMsisdn() ).size() == TOKEN_TO_GENERATE );
				
	}
	
	@Test( enabled = true, dependsOnMethods = { "loadUserWithActiveTokens" } )
	private void configureTokens() throws Exception {
		
		activeTokens = daoToken.getAvailableActiveTokens( subscriberWithActiveTokens.getMsisdn() );
		
		logger.info( Log.LOADING.createMessage( "Active Tokens: " + activeTokens.size() ) );
				
		Assert.assertTrue( activeTokens.size() == TOKEN_TO_GENERATE );
	
		
		// allocate tokens
		for( int t = 0; t < TOKEN_TO_ALLOCATE; t++ ) {
			
			logger.info( ( "ALLOCATING Token ( " + activeTokens.get( t ) + " ) for msisdn ( " + subscriberWithActiveTokens.getMsisdn() + " )" ) );
			
			XMLRPCRequest.offeroptimizer_allocate().call( 	
				actruleServer, 
				xmlrpcBody(
					authentication( superman ),
					string( subscriberWithActiveTokens.getMsisdn() ),
					string( activeTokens.get( t ).getTokenCode() )
				)
			);
			
		}
		
		allocatedTokens = daoToken.getAvailableAllocatedActiveTokensAndOffers( subscriberWithActiveTokens.getMsisdn() );
		
		Assert.assertTrue( allocatedTokens.size() == TOKEN_TO_ALLOCATE );

		Assert.assertTrue( allocatedTokens.size() > TOKEN_TO_ACCEPT );

		
		// accept tokens
		for( int t = 0; t < TOKEN_TO_ACCEPT; t++ ) {
					
			ArrayList<CatalogOffers> tokenAssociatedOffers = daoToken.getAssociatedOffers( subscriberWithActiveTokens.getMsisdn(), allocatedTokens.get( t ).getTokenCode() );
			
			Assert.assertTrue( tokenAssociatedOffers.size() > 0 );
			
			XMLRPCRequest.offeroptimizer_accept().call( 	
				actruleServer, 
				xmlrpcBody(
					authentication( superman ),
					string( subscriberWithActiveTokens.getMsisdn() ),
					string( allocatedTokens.get( t ).getTokenCode() ),
					arrayInt( tokenAssociatedOffers.get( 0 ).getOfferId() ),
					string( "web" )
				)
			);
			
		}
				
		acceptedTokens = daoToken.getAvailableAcceptedTokens( subscriberWithActiveTokens.getMsisdn() );
		
		Assert.assertTrue( acceptedTokens.size() == TOKEN_TO_ACCEPT );
		
		
		// refuse tokens
		allocatedTokens = daoToken.getAvailableAllocatedActiveTokensAndOffers( subscriberWithActiveTokens.getMsisdn() );
		
		Assert.assertTrue( allocatedTokens.size() == ( TOKEN_TO_ALLOCATE - TOKEN_TO_ACCEPT ) );
		
		Assert.assertTrue( allocatedTokens.size() >= TOKEN_TO_REFUSE );

		for( int t = 0; t < TOKEN_TO_REFUSE; t++ ) {
			
			XMLRPCRequest.offeroptimizer_refuseAll().call( 	
				actruleServer, 
				xmlrpcBody(
					authentication( superman ),
					string( subscriberWithActiveTokens.getMsisdn() ),
					string( allocatedTokens.get( t ).getTokenCode() ),
					string( "web" )
				)
			);
			
		}
		
		refusedTokens = daoToken.getRefusedTokens( subscriberWithActiveTokens.getMsisdn() );
		
		Assert.assertTrue( refusedTokens.size() == TOKEN_TO_REFUSE );
		
		
		// set expired tokens
		activeTokens = daoToken.getAvailableActiveTokens( subscriberWithActiveTokens.getMsisdn() );
		
		Assert.assertTrue( activeTokens.size() == ( TOKEN_EXPIRED + TOKEN_TO_BE_ACTIVE ) );
		
		for( int t = 0; t < TOKEN_EXPIRED; t++ ) {
			
			Calendar date = Calendar.getInstance();
			
			date.setTime( activeTokens.get( t ).getEventDate() );
			
			date.add( Calendar.SECOND, 1 );
			
			activeTokens.get( t ).setExpirationDate( date.getTime() );
			
			daoToken.setTokenDates( activeTokens.get( t ) );
			
		}
		
		expiredTokens = daoToken.getExpiredTokens( subscriberWithActiveTokens.getMsisdn() );
		
		Assert.assertTrue( expiredTokens.size() == TOKEN_EXPIRED );
		
		activeTokens = daoToken.getAvailableActiveTokens( subscriberWithActiveTokens.getMsisdn() );
		
		Assert.assertTrue( activeTokens.size() == TOKEN_TO_BE_ACTIVE );
		
	}
	
	@Test( enabled = true, dependsOnMethods = { "loadSubscribers" } )
	private void loadUserWithNoTokens() throws Exception {
		
		subscriberWithNoTokens = allSubscribers.get( 1 );
			
		Assert.assertFalse( daoSubscribers.hasTokens( subscriberWithNoTokens.getMsisdn() ) );
						
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
//	
//	@Test( enabled = TEST_ENABLED, priority = 1, description = "1: AUTHENTICATION FAILURE" )
//	public void authenticationFailure() throws Exception {
//	
//		// no user and password set
//		User userNotAuthorized = new User(); 
//				
//		XMLRPCRequest.offeroptimizer_resendAllActiveTokens().call( 
//				actruleServer, 
//				xmlrpcBody(
//					authentication( userNotAuthorized ),
//					string( msisdn ), 
//					integer( 0 ) 
//				),
//				xmlrpcValidator(
//					fault().code( equalTo( 1 ) ),
//					fault().message( equalTo( "authentication failure" ) )
//				),
//				xmlrpcOptions(
//					sleep( 100L )	
//				)
//		
//		);
//		
//		// no password set
//		userNotAuthorized.setUsername( "superman" ); 
//		
//		XMLRPCRequest.offeroptimizer_resendAllActiveTokens().call( 
//				actruleServer, 
//				xmlrpcBody(
//					authentication( userNotAuthorized ),
//					string( msisdn ), 
//					integer( 0 ) 
//				),
//				xmlrpcValidator(
//					fault().code( equalTo( 1 ) ),
//					fault().message( equalTo( "authentication failure" ) )
//				),
//				xmlrpcOptions(
//					sleep( 100L )	
//				)
//		
//		);
//		
//		// wrong password
//		userNotAuthorized.setUsername( "superman" );
//		userNotAuthorized.setSecurityPassword( "wrongPassword" );
//		
//		XMLRPCRequest.offeroptimizer_resendAllActiveTokens().call( 
//				actruleServer, 
//				xmlrpcBody(
//					authentication( userNotAuthorized ),
//					string( msisdn ), 
//					integer( 0 ) 
//				),
//				xmlrpcValidator(
//					fault().code( equalTo( 1 ) ),
//					fault().message( equalTo( "authentication failure" ) )
//				),
//				xmlrpcOptions(
//					sleep( 100L )	
//				)
//		
//		);
//		
//		// wrong user
//		userNotAuthorized.setUsername( "wrongUser" );
//		userNotAuthorized.setSecurityPassword( "super2010Man" );
//		
//		XMLRPCRequest.offeroptimizer_resendAllActiveTokens().call( 
//				actruleServer, 
//				xmlrpcBody(
//					authentication( userNotAuthorized ),
//					string( msisdn ), 
//					integer( 0 ) 
//				),
//				xmlrpcValidator(
//					fault().code( equalTo( 1 ) ),
//					fault().message( equalTo( "authentication failure" ) )
//				),
//				xmlrpcOptions(
//					sleep( 100L )	
//				)
//		
//		);				
//		
//	}
//	
//	@Test( enabled = TEST_ENABLED, priority = 1, description = "4: MALFORMED REQUEST" )
//	public void malformedRequest() throws Exception {
//				
//		XMLRPCRequest.offeroptimizer_resendAllActiveTokens().call( 
//				actruleServer, 
//				xmlrpcBody(
//					// missing authentication, msisdn and max token notification resent
//				),
//				xmlrpcValidator(
//					fault().code( equalTo( 4 ) ),
//					fault().message( equalTo( "Malformed request" ) )
//				),
//				xmlrpcOptions(
//					sleep( 100L )	
//				)
//		
//		);
//
//		XMLRPCRequest.offeroptimizer_resendAllActiveTokens().call( 
//				actruleServer, 
//				xmlrpcBody(
//					authentication( superman )
//					// missing msisdn and max token notification resent
//				),
//				xmlrpcValidator(
//					fault().code( equalTo( 4 ) ),
//					fault().message( equalTo( "Malformed request" ) )
//				),
//				xmlrpcOptions(
//					sleep( 100L )	
//				)
//		
//		);
//
//		XMLRPCRequest.offeroptimizer_resendAllActiveTokens().call( 
//				actruleServer, 
//				xmlrpcBody(
//					authentication( superman ),
//					string( msisdn )
//					// missing max token notification resent
//				),
//				xmlrpcValidator(
//					fault().code( equalTo( 4 ) ),
//					fault().message( equalTo( "Malformed request" ) )
//				),
//				xmlrpcOptions(
//					sleep( 100L )	
//				)
//		
//		);
//
//		XMLRPCRequest.offeroptimizer_resendAllActiveTokens().call( 
//				actruleServer, 
//				xmlrpcBody(
//					authentication( superman ),
//					string( "text" ) // wrong msisdn and missing max token notification resent
//				),
//				xmlrpcValidator(
//					fault().code( equalTo( 4 ) ),
//					fault().message( equalTo( "Malformed request" ) )
//				),
//				xmlrpcOptions(
//					sleep( 100L )	
//				)
//		
//		);
//		
//		XMLRPCRequest.offeroptimizer_resendAllActiveTokens().call( 
//				actruleServer, 
//				xmlrpcBody(
//					authentication( superman ),
//					string( msisdn ),
//					string( "text" ) // wrong max token notification resent
//				),
//				xmlrpcValidator(
//					fault().code( equalTo( 4 ) ),
//					fault().message( equalTo( "Malformed request" ) )
//				),
//				xmlrpcOptions(
//					sleep( 100L )	
//				)
//		
//		);	
//		
//	}
//	
//	@Test( enabled = TEST_ENABLED, priority = 1, description = "100: SUBSCRIBER NOT FOUND" )
//	public void subscriberNotFound() throws Exception {
//	
//		Long wrongMSISDN = 0L;
//		
//		if( !DAOSubscribers.getInstance( mysql ).isSubscriber( wrongMSISDN ) ) {
//				
//			XMLRPCRequest.offeroptimizer_resendAllActiveTokens().call( 
//					actruleServer, 
//					xmlrpcBody(
//						authentication( superman ),
//						string( wrongMSISDN ), // subscriber not found
//						integer( 0 ) 
//					),
//					xmlrpcValidator(
//						fault().code( equalTo( 100 ) ),
//						fault().message( equalTo( "subscriber not found with msisdn " + wrongMSISDN ) )
//					),
//					xmlrpcOptions(
//						sleep( 100L )	
//					)
//			
//			);	
//		
//		} else {
//			
//			throw new Exception( "The msisdn " + wrongMSISDN + " is in the database." );
//			
//		}
//		
//	}
//	
//	@Test( enabled = TEST_ENABLED, priority = 1, description = "630: MESSAGE SENDING ERROR " )
//	public void messageSendingError() throws Exception {
//	
//		Subscribers subscriberWithTokensToResend = DAOSubscribers.getInstance( mysql ).getSubscriberWithActiveToken();
//				
//		if( null != subscriberWithTokensToResend ) {
//						
//			XMLRPCRequest.offeroptimizer_resendAllActiveTokens().call( 
//				actruleServer, 
//				xmlrpcBody(
//					authentication( superman ),
//					string( subscriberWithTokensToResend.getMsisdn() ), // subscriber with consumed or expired token
//					integer( 20 ) 
//				),
//				xmlrpcValidator(
//					fault().code( equalTo( 630 ) ),
//					fault().message( equalTo( "MESSAGE SENDING ERROR" ) )
//				),
//				xmlrpcOptions(
//					sleep( 100L )	
//				)		
//			);	
//				
//		} else {	
//			
//			throw new Exception( "No subscribers with no active tokens");
//		
//		}
//		
//	}
//	
//	@Test( enabled = TEST_ENABLED, priority = 1, description = "631: NO ACTIVE TOKENS AVAILABLE" )
//	public void noAactiveTokensAvailable() throws Exception {
//	
//		ArrayList<Subscribers> subscriberWithoutTokensToResend = DAOSubscribers.getInstance( mysql ).getSubscriberWithNoActiveToken();
//				
//		if( subscriberWithoutTokensToResend.size() <= 0 ) {
//			
//			throw new Exception( "No subscribers with no active tokens");
//			
//		} 
//		
//		for( Subscribers subscriber : subscriberWithoutTokensToResend ) {
//						
//			XMLRPCRequest.offeroptimizer_resendAllActiveTokens().call( 
//				actruleServer, 
//				xmlrpcBody(
//					authentication( superman ),
//					string( subscriber.getMsisdn() ), // subscriber with consumed or expired token
//					integer( 1 ) 
//				),
//				xmlrpcValidator(
//					fault().code( equalTo( 631 ) ),
//					fault().message( equalTo( "NO ACTIVE TOKENS AVAILABLE" ) )
//				),
//				xmlrpcOptions(
//					sleep( 100L )	
//				)
//		
//			);	
//						
//		}
//		
//	}
//	
//	@Test( enabled = TEST_ENABLED, priority = 1, description = "632: MAX NUMBER OF TOKEN RESEND REACHED" )
//	public void maxNumberOfTokenResendReached() throws Exception {
//	
//		Subscribers subscriberWithTokensToResend = DAOSubscribers.getInstance( mysql ).getSubscriberWithActiveToken();
//				
//		if( null != subscriberWithTokensToResend ) {
//						
//			XMLRPCRequest.offeroptimizer_resendAllActiveTokens().call( 
//				actruleServer, 
//				xmlrpcBody(
//					authentication( superman ),
//					string( subscriberWithTokensToResend.getMsisdn() ), // subscriber with consumed or expired token
//					integer( -1 ) 
//				),
//				xmlrpcValidator(
//					fault().code( equalTo( 632 ) ),
//					fault().message( equalTo( "MAX NUMBER OF TOKEN RESEND REACHED" ) )
//				),
//				xmlrpcOptions(
//					sleep( 100L )	
//				)
//		
//			);	
//				
//		} else {	
//			
//			throw new Exception( "No subscribers with no active tokens");
//		
//		}
//		
//	}
//	
//	private void getAvailableTokenToResend() {
//		
//		ArrayList<Token> activeTokens = DAOToken.getInstance( mysql ).getAvailableActiveTokens( 393669393643L );
//		
//		for( Token t : activeTokens ) {
//			
//			System.out.println( t.toString() );
//			
//		}
//		
//	}

}
