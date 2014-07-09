package com.lumata.unit.webservices.xmlrpc;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.jboss.resteasy.client.ClientResponse;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.exceptions.CampaignModelException;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.exceptions.GeneratorException;
import com.lumata.e4o.generators.common.Generator;
import com.lumata.e4o.gui.administrationmanager.SalesChannelsForm;
import com.lumata.e4o.gui.campaignmanager.CampaignModelForm;
import com.lumata.e4o.gui.catalogmanager.ConfigureRules;
import com.lumata.e4o.gui.catalogmanager.RulesForm;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm;
import com.lumata.e4o.gui.security.Authorization;
import com.lumata.e4o.gui.xmlrpc.XMLRPCRequestOld;
import com.lumata.e4o.json.gui.administrationmanager.JSONSalesChannels;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaignModel;
import com.lumata.e4o.json.gui.catalogmanager.JSONRules;
import com.lumata.e4o.json.gui.catalogmanager.JSONTokenType;
import com.lumata.e4o.schema.tenant.OffoptimCustomerItems;
import com.lumata.e4o.schema.tenant.OffoptimCustomerPack;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.schema.tenant.Token;
import com.lumata.e4o.utils.generators.subscribers.GenerateSubscribers;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.sleep;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.*;


public class XMLRPCRequest_Optimizer_Rule_Criteria {

	private static final Logger logger = LoggerFactory.getLogger( ConfigureRules.class );
	
	private int TIMEOUT = 60000;
	private int ATTEMPT_TIMEOUT = 200;
	
	private SeleniumWebDriver seleniumWebDriver;
	private Boolean SELENIUM_IS_ACTIVE = false;
	
	private final boolean CONFIGURE_RULES = false;
	private final boolean CONFIGURE_TOKEN_TYPES = false;
	private final boolean CONFIGURE_CAMPAIGN_MODEL = false;
	
	private final boolean GENERATE_SUBSCRIBERS = false;
		
	private final boolean GENERATE_TOKENS = false;
	private final boolean PLAY_TOKENS = true;
		
	private NetworkEnvironment env;
	private Server guiServer;
	private User superman;
	
	private Mysql mysql;	
	
	/* 	Initialize Environment */
	@SuppressWarnings("unused")
	@Parameters({"browser", "environment", "gui_server", "tenant", "user"})
	@BeforeClass
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_VM") String environment, @Optional("actrule") String gui_server, @Optional("tenant") String tenant, @Optional("superman") String user ) throws NetworkEnvironmentException, FormException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		SELENIUM_IS_ACTIVE = CONFIGURE_RULES || CONFIGURE_TOKEN_TYPES || CONFIGURE_CAMPAIGN_MODEL;
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );

		mysql = new Mysql( env.getDataSource( tenant ) );
				
		/** Create Selenium WebDriver instance */
		guiServer = env.getServer( gui_server );
		
		superman = guiServer.getUser( user );
		if(null == superman ) { System.out.println( "KO" ); }
		if( SELENIUM_IS_ACTIVE ) {
			
			seleniumWebDriver = new SeleniumWebDriver( guiServer.getBrowser( browser ), guiServer.getLink() );
			
			/** Login */
			Assert.assertTrue( Authorization.getInstance( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT).login( superman ).navigate() );
		
		}	
		
	}

	@Parameters({"salesChannelsList"})
	@Test( enabled=CONFIGURE_RULES, priority = 1 )
	public void configureSalesChannels( @Optional("salesChannelsList") String salesChannelsList ) throws FormException, JSONException, JSONSException {
		
		SalesChannelsForm salesChannelsForm = new SalesChannelsForm( seleniumWebDriver, new JSONSalesChannels( "input/administrationmanager/salesChannels", salesChannelsList ), TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( salesChannelsForm.open().addSalesChannels().navigate() );
		
	}
	
	@Parameters({"tokenTypeList"})
	@Test( enabled=CONFIGURE_TOKEN_TYPES, priority = 2 )
	public void configureTokeType( @Optional("tokenTypeOptimizationRuleList") String tokenTypeList ) throws FormException, JSONException, JSONSException {
		
		TokenTypeForm tokenTypeForm = new TokenTypeForm( seleniumWebDriver, new JSONTokenType( "input/catalogmanager/tokenTypes", tokenTypeList ), TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( tokenTypeForm.open().addTokenTypes().close().navigate() );
		
	}
		
	@Parameters({"ruleList"})
	@Test( enabled=CONFIGURE_RULES, priority = 3 )
	public void configureRules( @Optional("ruleOptimizationRuleList") String ruleList ) throws FormException, JSONException, JSONSException {
		
		RulesForm rulesForm = new RulesForm( seleniumWebDriver, new JSONRules( "input/catalogmanager/rules", ruleList ), TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( rulesForm.open().addRules().close().navigate() );
		
	}
		
	@Parameters({"campaignModelList"})
	@Test( enabled = CONFIGURE_CAMPAIGN_MODEL, priority = 4 )
	public void configureCampaignModel( @Optional("campaignModelOptimizationRuleList") String campaignModelList ) throws CampaignModelException, JSONSException, IOFileException, FormException, JSONException {

		CampaignModelForm campaignModelForm = new CampaignModelForm( seleniumWebDriver, new JSONCampaignModel( "input/campaignmanager/campaignModels", campaignModelList ), TIMEOUT, ATTEMPT_TIMEOUT );
						
		Assert.assertTrue( campaignModelForm.open()
							.addCampaignModels()
							.navigate() 
		);		
				
    }
	
	@Test( enabled = GENERATE_SUBSCRIBERS, priority = 5 )
	public void loadSubscribers() throws GeneratorException {
		
		final Long STARTED_MSISDN = 3399900001L;
		final Integer INCREMENT = 1;
		final Boolean HAS_SMS_CHANNEL = true;
		final Boolean HAS_MAIL_CHANNEL = true;
		final Long SUBSCRIBERS_TO_GENERATE = 1000L;
		
		
		Generator.subscribers()
					.environment( env )
					.mysql( mysql )
					.msisdnIncremental( STARTED_MSISDN, INCREMENT )
					.subscriberHasSMSChannel( HAS_SMS_CHANNEL )
					.subscriberHasMAILChannel( HAS_MAIL_CHANNEL )
					.insertIntoEnvironment( SUBSCRIBERS_TO_GENERATE );
		
	}
	
	@Test(enabled=GENERATE_TOKENS, priority = 6 )
	public void generateRecharges() throws Exception {
	
		XMLRPCRequest_GenerateRecharges.run().generateRecharges();
		
	}
	
	@Test(enabled=PLAY_TOKENS, priority = 7 )
	public void allocateOffers() throws Exception {
			
		final Double percentageTokensToAllocate = 0.0;
		final Double percentageTokensToAccept = 0.0;
		final Double percentageTokensToRefuse = 0.1;
		
		/** allocate tokens */
		ArrayList<Token> allActiveTokens = getActiveTokens();
		
		Long tokensToAllocate = Math.round( allActiveTokens.size() * percentageTokensToAllocate );
				
		Collections.shuffle( allActiveTokens );
				
		for( int tta = 0; tta < tokensToAllocate; tta++ ) {
					
			ClientResponse<String> response = XMLRPCRequestOld.offeroptimizer_allocate.call( 	
					guiServer, 
					xmlrpcBody(
						authentication( superman.getUsername(), superman.getPassword() ),
						string( allActiveTokens.get( tta ).getMsisdn() ),
						string( allActiveTokens.get( tta ).getTokenCode() )
					),
					xmlrpcOptions( 
						sleep( 100L ) 
					)
			);
			
			System.out.println( response.getEntity().toString() );
			
		}
		
		
		/** accept tokens */
		ArrayList<Token> allAllocatedTokens = getAllocatedTokens();
		
		Long tokensToAccept = Math.round( allAllocatedTokens.size() * percentageTokensToAccept );
		
		for( int tta = 0; tta < tokensToAccept; tta++ ) {
		
			int randomTokenToAcceptIndex = (int)( Math.random() * allAllocatedTokens.size() );
			
			Long msisdn = allAllocatedTokens.get( randomTokenToAcceptIndex ).getMsisdn();
			
			String tokenCode = allAllocatedTokens.get( randomTokenToAcceptIndex ).getTokenCode();
			
			ArrayList<Short> offersCanBeAllocated = getAssociatedOffers( tokenCode );
			
			if( offersCanBeAllocated.size() > 0 ) {
			
				int randomOfferToAllocateIndex = (int)( Math.random() * offersCanBeAllocated.size() );
								
				ClientResponse<String> response = XMLRPCRequestOld.offeroptimizer_accept.call( 	
						guiServer, 
						xmlrpcBody(
							authentication( superman.getUsername(), superman.getPassword() ),
							string( msisdn ),
							string( tokenCode ),
							arrayInt( offersCanBeAllocated.get( randomOfferToAllocateIndex ) )
						),
						xmlrpcOptions( 
							sleep( 100L ) 
						)
				);
				
				System.out.println( response.getEntity().toString() );
			
			}
			
		}
		
		/** refuse tokens */
		allAllocatedTokens = getAllocatedTokens();
		
		Long tokensToRefuse = Math.round( allAllocatedTokens.size() * percentageTokensToRefuse );
		
		for( int tta = 0; tta < tokensToRefuse; tta++ ) {
		
			int randomTokenToRefuseIndex = (int)( Math.random() * allAllocatedTokens.size() );
			
			Long msisdn = allAllocatedTokens.get( randomTokenToRefuseIndex ).getMsisdn();
			
			String tokenCode = allAllocatedTokens.get( randomTokenToRefuseIndex ).getTokenCode();
			
			ClientResponse<String> response = XMLRPCRequestOld.offeroptimizer_refuseAll.call( 	
					guiServer, 
					xmlrpcBody(
						authentication( superman.getUsername(), superman.getPassword() ),
						string( msisdn ),
						string( tokenCode )
					),
					xmlrpcOptions( 
						sleep( 100L ) 
					)
			);
			
			System.out.println( response.getEntity().toString() );
						
		}
		
	}

	/**
	*	get all subscribers
	*/
	public ArrayList<Subscribers> getSubscribers() throws Exception {
		
		ArrayList<Subscribers> subscribers = new ArrayList<Subscribers>();
		
		Subscribers subscribersTable = new Subscribers();
		
		String query = select().
				from( subscribersTable ).
				build();

		ResultSet rs = mysql.execQuery( query );
		
		while( rs.next() ) {
			
			Subscribers subscriber = new Subscribers( rs );
			
			subscribers.add( subscriber );
			
		}
		
		return subscribers;
		
	}

	/**
	 * get all tokens
	*/
	public ArrayList<Token> getTokens() throws Exception {
	
		ArrayList<Token> tokens = new ArrayList<Token>();
		
		Token tokenTable = new Token();
		
		String query = select().
						from( tokenTable ).
						build();
		
		ResultSet rs = mysql.execQuery( query );
		
		while( rs.next() ) {
			
			Token token = new Token( rs );
				
			tokens.add( token );
			
		}
		
		return tokens;
		
	}
		
	/**
	 * get active tokens
	*/
	public ArrayList<Token> getActiveTokens() throws Exception {
		
		ArrayList<Token> tokens = new ArrayList<Token>();
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		
		Date currentDateTime = new Date();
		
		Token tokenTable = new Token();
		
		String query = select().
						from( tokenTable ).
						where(  op( Token.Fields.has_offers_associated ).eq( 0 ),
								and( 	
										op( Token.Fields.qty_current_redeems ).eq( 0 ),
										op( Token.Fields.expiration_date ).get( sdf.format( currentDateTime ) )
								)
						).
						build();
		
		ResultSet rs = mysql.execQuery( query );
		System.out.println( query );
		while( rs.next() ) {
			
			Token token = new Token( rs );
			
			tokens.add( token );
			
		}
		
		return tokens;
		
	}
		
	/**
	 * get allocated tokens
	*/
	public ArrayList<Token> getAllocatedTokens() throws Exception {
		
		ArrayList<Token> tokens = new ArrayList<Token>();
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss" );
		
		Date currentDateTime = new Date();
		
		Token tokenTable = new Token();
		
		String query = select().
						from( tokenTable ).
						where( 
								op( Token.Fields.has_offers_associated ).get( 1 ),
								and( 	
										op( Token.Fields.expiration_date ).get( sdf.format( currentDateTime ) )
								)
						).
						build();
		
		ResultSet rs = mysql.execQuery( query );
		
		while( rs.next() ) {
			
			Token token = new Token( rs );
			
			tokens.add( token );
			
		}
		
		return tokens;
		
	}
	
	/**
	 * get allocated tokens
	*/
	public ArrayList<Short> getAssociatedOffers( String tokenCode ) throws Exception {
		
		ArrayList<Short> offers = new ArrayList<Short>();
		
		OffoptimCustomerPack offoptimCustomerPackTable = new OffoptimCustomerPack();
		
		OffoptimCustomerItems offoptimCustomerItemsTable = new OffoptimCustomerItems();
		
		String query = select().
						from( offoptimCustomerItemsTable ).
						join( offoptimCustomerPackTable ).
						on( 
								op( OffoptimCustomerItems.Fields.customer_offer_pack_id ).eq( OffoptimCustomerPack.Fields.customer_offer_pack_id ) 
						).
						where( 
								op( OffoptimCustomerPack.Fields.token_code ).eq( tokenCode ),
								and(
										op( OffoptimCustomerItems.Fields.offer_status ).eq( "allocated" )
								)
						).
						build();
		
		ResultSet rs = mysql.execQuery( query );
		
		while( rs.next() ) {
			
			offers.add( rs.getShort( OffoptimCustomerItems.Fields.offer_id.name() ) );
			
		}
		
		return offers;
		
	}

		
	@AfterClass
	public void end() throws FormException {
		
		if( SELENIUM_IS_ACTIVE ) {
			
			Assert.assertTrue( Authorization.getInstance( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT).quit().navigate() );
		
		}
		
		mysql.close();
		
	}
	
	
}
