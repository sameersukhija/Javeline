package com.lumata.expression.operators.testplan.mobistar.functional;

import static com.lumata.common.testing.orm.Query.select;
import static com.lumata.common.testing.orm.Filter.op;
import static com.lumata.common.testing.orm.Filter.and;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jboss.resteasy.client.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.entities.Subscribers;
import com.lumata.expression.operators.entities.SupportedRatePlan;
import com.lumata.expression.operators.gui.xmlrpc.HTTPXMLRPCForm;
import com.lumata.expression.operators.gui.xmlrpc.XMLRPCChannel;
import com.lumata.expression.operators.gui.xmlrpc.XMLRPCRelation;
import com.lumata.expression.operators.gui.xmlrpc.XMLRPCResultFault;
import com.lumata.expression.operators.gui.xmlrpc.XMLRPCResultParser;
import com.lumata.expression.operators.gui.xmlrpc.XMLRPCResultParser.ResultType;
import com.lumata.expression.operators.gui.xmlrpc.XMLRPCResultSuccess;
import com.lumata.expression.operators.gui.xmlrpc.XMLRPCSubscriber;

public class XMLRPC_Subscriber {

	private static final Logger logger = LoggerFactory.getLogger( XMLRPC_Subscriber.class );
	
	Environment env;
	Mysql mysql;
	int user_id = 0;
	
	final String default_msisdn = "331234501";
		
	@Parameters({"browser", "environment", "tenant", "user"})
	@BeforeClass
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("qa") String tenant, @Optional("superman") String user ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		mysql = new Mysql( env.getDataSource( tenant ) );
				
	}
	
	// Create subscriber with wrong xmlrpc requests ( some mandatory field is missing ) 
	@Parameters( "msisdn" )
	@Test( priority = 1, enabled = false )
	public void createNewSubscriberWidthMissingMinimalParameters( @Optional( default_msisdn ) String msisdn ) {
		
		XMLRPCResultParser responseParser;
		XMLRPCResultFault resultFault;
		
		if( this.isSubscriber( msisdn ) ) { this.deleteExistingSubscriber( msisdn ); } 
		
		// Case 1 ( only msisdn sent )
		Map<String, Object> subscriberParams = new HashMap<String, Object>();
		subscriberParams.put( XMLRPCSubscriber.Params.msisdn.name(), msisdn );
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultFault = XMLRPC_Subscriber.getFault( responseParser );
		Assert.assertEquals( resultFault.getCode(), "5" );
		Assert.assertEquals( resultFault.getMessage(), "missing mandatory param subscription_date" );
		
		// Case 2 ( only msisdn, subscription_date sent )
		subscriberParams.put( XMLRPCSubscriber.Params.subscription_date.name(), "2014-01-01" );
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultFault = XMLRPC_Subscriber.getFault( responseParser );
		Assert.assertEquals( resultFault.getCode(), "5" );
		Assert.assertEquals( resultFault.getMessage(), "missing mandatory param rate_plan" );
				
		// Case 3 ( only msisdn, subscription_date, rate_plan sent )
		subscriberParams.put( XMLRPCSubscriber.Params.rate_plan.name(), "FUN" );
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultFault = XMLRPC_Subscriber.getFault( responseParser );
		Assert.assertEquals( resultFault.getCode(), "5" );
		Assert.assertEquals( resultFault.getMessage(), "missing mandatory param status" );
		
		// Case 4 ( only msisdn, subscription_date, rate_plan, status sent )
		subscriberParams.put( XMLRPCSubscriber.Params.status.name(), "active" );
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultFault = XMLRPC_Subscriber.getFault( responseParser );
		Assert.assertEquals( resultFault.getCode(), "5" );
		Assert.assertEquals( resultFault.getMessage(), "missing mandatory param in_tag" );

		// Case 5 ( only msisdn, subscription_date, rate_plan, status, in_tag sent )
		subscriberParams.put( XMLRPCSubscriber.Params.in_tag.name(), "QAIN" );
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultFault = XMLRPC_Subscriber.getFault( responseParser );
		Assert.assertEquals( resultFault.getCode(), "5" );
		Assert.assertEquals( resultFault.getMessage(), "invalid mandatory param network" );
	
		// Case 6 ( Success - Minimal parameters sent - msisdn, subscription_date, rate_plan, status, in_tag, network )
		subscriberParams.put( XMLRPCSubscriber.Params.network.name(), "mobile" );
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		XMLRPCResultSuccess resultSuccess = XMLRPC_Subscriber.getSuccess( responseParser );
		Assert.assertNotNull( resultSuccess );
		Assert.assertEquals( resultSuccess.getBoolean(), "0" );
		
	}	
		
	// Create subscriber with wrong xmlrpc requests ( some parameter is wrong ) 
	@Parameters( "msisdn" )
	@Test( priority = 2, enabled = false )
	public void createNewSubscriberWidthWrongMinimalParameters( @Optional( default_msisdn ) String msisdn ) {
		
		XMLRPCResultParser responseParser;
		XMLRPCResultFault resultFault;
		
		if( this.isSubscriber( msisdn ) ) { this.deleteExistingSubscriber( msisdn ); } 
		
		// Minimal parameters
		Map<String, Object> subscriberParams = new HashMap<String, Object>();
		subscriberParams.put( XMLRPCSubscriber.Params.msisdn.name(), msisdn );
		subscriberParams.put( XMLRPCSubscriber.Params.subscription_date.name(), "2014-01-01" );
		subscriberParams.put( XMLRPCSubscriber.Params.rate_plan.name(), "FUN" );
		subscriberParams.put( XMLRPCSubscriber.Params.status.name(), "active" );
		subscriberParams.put( XMLRPCSubscriber.Params.in_tag.name(), "QAIN" );
		subscriberParams.put( XMLRPCSubscriber.Params.network.name(), "mobile" );
				
		// Case 1 ( wrong msisdn )
		subscriberParams.put( XMLRPCSubscriber.Params.msisdn.name(), "wrong_msisdn" );
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultFault = XMLRPC_Subscriber.getFault( responseParser );
		Assert.assertEquals( resultFault.getCode(), "2" );
		Assert.assertEquals( resultFault.getMessage(), "unable to create subscriber" );
		subscriberParams.put( XMLRPCSubscriber.Params.msisdn.name(), default_msisdn );	
		
		// Case 2 ( wrong subscription_date )
		subscriberParams.put( XMLRPCSubscriber.Params.subscription_date.name(), "2014-01-" );
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultFault = XMLRPC_Subscriber.getFault( responseParser );
		Assert.assertEquals( resultFault.getCode(), "6" );
		Assert.assertEquals( resultFault.getMessage(), "invalid subscription_date 2014-01-" );
		subscriberParams.put( XMLRPCSubscriber.Params.subscription_date.name(), "2014-01-01" );
				
		// Case 3 ( wrong rate_plan )
		subscriberParams.put( XMLRPCSubscriber.Params.rate_plan.name(), "WRONG_RATE_PLAN" );
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultFault = XMLRPC_Subscriber.getFault( responseParser );
		Assert.assertEquals( resultFault.getCode(), "6" );
		Assert.assertEquals( resultFault.getMessage(), "invalid rate_plan WRONG_RATE_PLAN" );
		subscriberParams.put( XMLRPCSubscriber.Params.rate_plan.name(), "FUN" );
		
		// Case 4 ( wrong status )
		subscriberParams.put( XMLRPCSubscriber.Params.status.name(), "wrong status" );
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultFault = XMLRPC_Subscriber.getFault( responseParser );
		Assert.assertEquals( resultFault.getCode(), "6" );
		Assert.assertEquals( resultFault.getMessage(), "invalid status wrong status" );		
		subscriberParams.put( XMLRPCSubscriber.Params.status.name(), "active" );
		
		// Case 5 ( wrong in_tag )
		subscriberParams.put( XMLRPCSubscriber.Params.in_tag.name(), "WRONG IN TAG" );
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultFault = XMLRPC_Subscriber.getFault( responseParser );
		Assert.assertEquals( resultFault.getCode(), "6" );
		Assert.assertEquals( resultFault.getMessage(), "invalid in_tag WRONG IN TAG" );
		subscriberParams.put( XMLRPCSubscriber.Params.in_tag.name(), "QAIN" );
		
		// Case 6 ( wrong network )
		subscriberParams.put( XMLRPCSubscriber.Params.network.name(), "wrong network" );
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultFault = XMLRPC_Subscriber.getFault( responseParser );
		Assert.assertEquals( resultFault.getCode(), "6" );
		Assert.assertEquals( resultFault.getMessage(), "invalid network wrong network" );
		subscriberParams.put( XMLRPCSubscriber.Params.network.name(), "mobile" );
			
		// Case 7 ( Success )
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );
		XMLRPCResultSuccess resultSuccess = XMLRPC_Subscriber.getSuccess( responseParser );
		Assert.assertNotNull( resultSuccess );
		Assert.assertEquals( resultSuccess.getBoolean(), "0" );
				
	}			
	
	// Create subscriber with wrong xmlrpc requests ( some no mandatory parameter is wrong ) 
	@Parameters( "msisdn" )
	@Test( priority = 3, enabled = false )
	public void createNewSubscriberWidthWrongAllParameters( @Optional( default_msisdn ) String msisdn ) {
		
		XMLRPCResultParser responseParser;
		XMLRPCResultFault resultFault;
		
		if( this.isSubscriber( msisdn ) ) { this.deleteExistingSubscriber( msisdn ); } 
		
		// Minimal parameters
		Map<String, Object> subscriberParams = new HashMap<String, Object>();
		subscriberParams.put( XMLRPCSubscriber.Params.msisdn.name(), msisdn );
		subscriberParams.put( XMLRPCSubscriber.Params.subscription_date.name(), "2014-01-01" );
		subscriberParams.put( XMLRPCSubscriber.Params.rate_plan.name(), "FUN" );
		subscriberParams.put( XMLRPCSubscriber.Params.status.name(), "active" );
		subscriberParams.put( XMLRPCSubscriber.Params.in_tag.name(), "QAIN" );
		subscriberParams.put( XMLRPCSubscriber.Params.network.name(), "mobile" );
		
		// Case 1 ( wrong profile )
		subscriberParams.put( XMLRPCSubscriber.Params.profile.name(), "wrong profile" );
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultFault = XMLRPC_Subscriber.getFault( responseParser );
		Assert.assertEquals( resultFault.getCode(), "6" );
		Assert.assertEquals( resultFault.getMessage(), "invalid profile wrong profile" );
		
		// Case 2 ( wrong correspondence between profile and rate plan - correct value: prepaid but used account - account has not statuses in statuses table)
		subscriberParams.put( XMLRPCSubscriber.Params.profile.name(), "postpaid" );
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		XMLRPCResultSuccess resultSuccess = XMLRPC_Subscriber.getSuccess( responseParser );
		Assert.assertNotNull( resultSuccess );
		Assert.assertEquals( resultSuccess.getBoolean(), "0" );
		
		Subscribers sbTable = new Subscribers();
		SupportedRatePlan srpTable = new SupportedRatePlan();
		
		String query = select( Subscribers.Fields.msisdn )
						.from( sbTable )
						.join( srpTable )
						.on( op( Subscribers.Fields.rate_plan_id ).eq( SupportedRatePlan.Fields.rate_plan_id ),
								/* the profile_id specified in the supported_rate_plan ( prepaid ) is put in the subscribers table and not postpaid profile sent with the xmlrpc call) */
								and( op( Subscribers.Fields.profile_id ).eq( SupportedRatePlan.Fields.profile_id ) )
						)
						.where( op( Subscribers.Fields.msisdn ).eq( msisdn ) )
						.build();
		
		ResultSet rs = mysql.execQuery( query );
		
		boolean found = false;
		
		try { while( rs.next() ) { found = true; } } catch( SQLException e ) {}
		
		Assert.assertTrue( found );
		
		subscriberParams.put( XMLRPCSubscriber.Params.profile.name(), "prepaid" );
		subscriberParams.put( XMLRPCSubscriber.Params.subprofile.name(), "" );
		
		if( this.isSubscriber( msisdn ) ) { this.deleteExistingSubscriber( msisdn ); } 
				
		// Case 3 ( all parameters ( channels and relations excluded )
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultSuccess = XMLRPC_Subscriber.getSuccess( responseParser );
		Assert.assertNotNull( resultSuccess );
		Assert.assertEquals( resultSuccess.getBoolean(), "0" );
				
	}	
	
	// Create subscriber using channel parameters
	@Parameters( "msisdn" )
	@Test( priority = 4, enabled = false )
	public void createNewSubscriberUsingChannelsParameters( @Optional( default_msisdn ) String msisdn ) {
		
		XMLRPCResultParser responseParser;
		XMLRPCResultFault resultFault;
		
		if( this.isSubscriber( msisdn ) ) { this.deleteExistingSubscriber( msisdn ); } 
		
		Map<String, XMLRPCChannel> channels_list = new HashMap<String, XMLRPCChannel>();
		XMLRPCChannel sms_channel = new XMLRPCChannel( "SMS1", msisdn, "true" );
		channels_list.put( "sms_channel" , sms_channel );
		
		// Parameters
		Map<String, Object> subscriberParams = new HashMap<String, Object>();
		subscriberParams.put( XMLRPCSubscriber.Params.msisdn.name(), msisdn );
		subscriberParams.put( XMLRPCSubscriber.Params.subscription_date.name(), "2014-01-01" );
		subscriberParams.put( XMLRPCSubscriber.Params.rate_plan.name(), "FUN" );
		subscriberParams.put( XMLRPCSubscriber.Params.status.name(), "active" );
		subscriberParams.put( XMLRPCSubscriber.Params.in_tag.name(), "QAIN" );
		subscriberParams.put( XMLRPCSubscriber.Params.network.name(), "mobile" );
		subscriberParams.put( XMLRPCSubscriber.Params.profile.name(), "prepaid" );
		subscriberParams.put( XMLRPCSubscriber.Params.subprofile.name(), "" );
		subscriberParams.put( XMLRPCSubscriber.Params.channels.name(), channels_list );
		
		// Case 1 ( wrong channel name )
		sms_channel.setName( "SMS1" );
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultFault = XMLRPC_Subscriber.getFault( responseParser );
		Assert.assertEquals( resultFault.getCode(), "6" );
		Assert.assertEquals( resultFault.getMessage(), "Invalid channel SMS1" );
		sms_channel.setName( "SMS" );
		
		// Case 2 ( wrong address )
		sms_channel.setAddress( "" );
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultFault = XMLRPC_Subscriber.getFault( responseParser );
		Assert.assertEquals( resultFault.getCode(), "5" );
		Assert.assertEquals( resultFault.getMessage(), "Missing address for channel SMS" );
		sms_channel.setAddress( msisdn );
		
		// Case 3 ( wrong active )
		sms_channel.setActive( "wrong active value" );
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultFault = XMLRPC_Subscriber.getFault( responseParser );
		Assert.assertEquals( resultFault.getCode(), "6" );
		Assert.assertEquals( resultFault.getMessage(), "Invalid active for channel SMS" );
		sms_channel.setActive( "true" );
		
		// Case 4 ( Success )
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		XMLRPCResultSuccess resultSuccess = XMLRPC_Subscriber.getSuccess( responseParser );
		Assert.assertNotNull( resultSuccess );
		Assert.assertEquals( resultSuccess.getBoolean(), "0" );
					
	}	
	
	// Create subscriber using channel parameters
	@Parameters( "msisdn" )
	@Test( priority = 5, enabled = true )
	public void createNewSubscriberUsingRealtionsParameters( @Optional( default_msisdn ) String msisdn ) {
		
		XMLRPCResultParser responseParser;
		XMLRPCResultFault resultFault;
		
		if( this.isSubscriber( msisdn ) ) { this.deleteExistingSubscriber( msisdn ); } 

		Map<String, XMLRPCChannel> channels_list = new HashMap<String, XMLRPCChannel>();
		XMLRPCChannel sms_channel = new XMLRPCChannel( "SMS", msisdn, "true" );
		channels_list.put( "sms_channel" , sms_channel );
		
		Map<String, XMLRPCRelation> relations_list = new HashMap<String, XMLRPCRelation>();
		XMLRPCRelation relation = new XMLRPCRelation( "account", msisdn );
		relations_list.put( "relation" , relation );
		
		// Parameters
		Map<String, Object> subscriberParams = new HashMap<String, Object>();
		subscriberParams.put( XMLRPCSubscriber.Params.msisdn.name(), msisdn );
		subscriberParams.put( XMLRPCSubscriber.Params.subscription_date.name(), "2014-01-01" );
		subscriberParams.put( XMLRPCSubscriber.Params.rate_plan.name(), "FUN" );
		subscriberParams.put( XMLRPCSubscriber.Params.status.name(), "active" );
		subscriberParams.put( XMLRPCSubscriber.Params.in_tag.name(), "QAIN" );
		subscriberParams.put( XMLRPCSubscriber.Params.network.name(), "mobile" );
		subscriberParams.put( XMLRPCSubscriber.Params.profile.name(), "prepaid" );
		subscriberParams.put( XMLRPCSubscriber.Params.subprofile.name(), "" );
		subscriberParams.put( XMLRPCSubscriber.Params.channels.name(), channels_list );
		subscriberParams.put( XMLRPCSubscriber.Params.relations.name(), relations_list );
			
		// Case 1 ( wrong relation type )
		relation.setType( "wrong account" );
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultFault = XMLRPC_Subscriber.getFault( responseParser );
		Assert.assertEquals( resultFault.getCode(), "6" );
		Assert.assertEquals( resultFault.getMessage(), "Invalid relation wrong account" );
		relation.setType( "account" );
		
		// Case 2 ( wrong related msisdn )
		String related_msisdn = msisdn.substring( 0, msisdn.length() - 1 );
		
		while( related_msisdn.equals( msisdn.substring( 0, msisdn.length() - 1 ) ) || related_msisdn.equals( msisdn ) ) {
			
			related_msisdn = related_msisdn + String.valueOf(((int)Math.random() * 9 ));
			
		}
		
		if( this.isSubscriber( related_msisdn ) ) { this.deleteExistingSubscriber( related_msisdn ); } 
		
		relation.setSponsor( related_msisdn );
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultFault = XMLRPC_Subscriber.getFault( responseParser );
		Assert.assertEquals( resultFault.getCode(), "100" );
		Assert.assertEquals( resultFault.getMessage(), "subscriber not found with msisdn 331234500" );
		
		// Case 3 Create second msisdn		
		Map<String, Object> subscriberParams2 = new HashMap<String, Object>();
		subscriberParams2.put( XMLRPCSubscriber.Params.msisdn.name(), related_msisdn );
		subscriberParams2.put( XMLRPCSubscriber.Params.subscription_date.name(), "2014-01-01" );
		subscriberParams2.put( XMLRPCSubscriber.Params.rate_plan.name(), "FUN" );
		subscriberParams2.put( XMLRPCSubscriber.Params.status.name(), "active" );
		subscriberParams2.put( XMLRPCSubscriber.Params.in_tag.name(), "QAIN" );
		subscriberParams2.put( XMLRPCSubscriber.Params.network.name(), "mobile" );
		subscriberParams2.put( XMLRPCSubscriber.Params.profile.name(), "prepaid" );
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams2 );		
		XMLRPCResultSuccess resultSuccess = XMLRPC_Subscriber.getSuccess( responseParser );
		Assert.assertNotNull( resultSuccess );
		Assert.assertEquals( resultSuccess.getBoolean(), "0" );
				
		// Case 4 ( Success )
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultSuccess = XMLRPC_Subscriber.getSuccess( responseParser );
		Assert.assertNotNull( resultSuccess );
		Assert.assertEquals( resultSuccess.getBoolean(), "0" );
					
	}	
	
	
	
	
	
	
	@Test( priority = 7, enabled = false )
	public void getNotSubscriber() {
		
		String msisdn = "wrong";
		
		Map<String, Object> subscriberParams = new HashMap<String, Object>();
		subscriberParams.put( XMLRPCSubscriber.Params.msisdn.name(), msisdn );
		
		ArrayList<String> params = new ArrayList<String>();
		params.add( HTTPXMLRPCForm.getAuthenticationParam( env.getUserName( "superman" ), env.getPassword( "superman" )) );
		params.add( HTTPXMLRPCForm.getSubscriber( subscriberParams ) );
		
		ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.subscribermanager_getSubscriber.call( env.getLink() + "xmlrpc/" , params );
		
		XMLRPCResultParser responseParser = new XMLRPCResultParser( response.getEntity().toString() );
				
		XMLRPCResultFault resultFault = XMLRPC_Subscriber.getFault( responseParser );
		
		Assert.assertNotNull( resultFault );
		
		Assert.assertEquals( resultFault.getCode(), "100" );
		
		Assert.assertEquals( resultFault.getMessage(), "subscriber not found for msisdn wrong" );
			
	}
	
	// Get not existing user
	@Parameters( "msisdn" )
	@Test( priority = 8, enabled = false )
	public void getExistingSubscriber( @Optional( default_msisdn ) String msisdn ) {
				
		Assert.assertTrue( this.isSubscriber( msisdn ) );
		
		Map<String, Object> subscriberParams = new HashMap<String, Object>();
		subscriberParams.put( XMLRPCSubscriber.Params.msisdn.name(), msisdn );
		
		ArrayList<String> params = new ArrayList<String>();
		params.add( HTTPXMLRPCForm.getAuthenticationParam( env.getUserName( "superman" ), env.getPassword( "superman" )) );
		params.add( HTTPXMLRPCForm.getSubscriber( subscriberParams ) );
		
		ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.subscribermanager_getSubscriber.call( env.getLink() + "xmlrpc/" , params );
				
		XMLRPCResultParser responseParser = new XMLRPCResultParser( response.getEntity().toString() );
		
		Map<ResultType, Object> result = responseParser.parse();
		
		XMLRPCSubscriber subscriber = (XMLRPCSubscriber) result.get( ResultType.SUBSCRIBER );
		
		Assert.assertNotNull( subscriber );
		
		Assert.assertEquals( subscriber.getMsisdn(), msisdn );			
		
	}
	
	// Check the subscriber doesn't exist
	@Parameters( "msisdn" )
	@Test( priority = 9, enabled = false )
	public void createNewSubscriber( @Optional( default_msisdn ) String msisdn ) {
								
		if( this.isSubscriber( msisdn ) ) { this.deleteExistingSubscriber( msisdn ); } 
				
		Map<String, Object> subscriberParams = new HashMap<String, Object>();
		subscriberParams.put( XMLRPCSubscriber.Params.msisdn.name(), msisdn );
		subscriberParams.put( XMLRPCSubscriber.Params.subscription_date.name(), "2014-01-06" );
		subscriberParams.put( XMLRPCSubscriber.Params.profile.name(), "prepaid" );
		subscriberParams.put( XMLRPCSubscriber.Params.subprofile.name(), "" );
		subscriberParams.put( XMLRPCSubscriber.Params.rate_plan.name(), "FUN" );
		subscriberParams.put( XMLRPCSubscriber.Params.status.name(), "active" );
		subscriberParams.put( XMLRPCSubscriber.Params.in_tag.name(), "QAIN" );
		subscriberParams.put( XMLRPCSubscriber.Params.network.name(), "mobile" );
				
		ArrayList<String> params = new ArrayList<String>();
		params.add( HTTPXMLRPCForm.getAuthenticationParam( env.getUserName( "superman" ), env.getPassword( "superman" )) );
		params.add( HTTPXMLRPCForm.getSubscriber( subscriberParams ) );
		
		ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber.call( env.getLink() + "xmlrpc/" , params );
				
		XMLRPCResultParser responseParser = new XMLRPCResultParser( response.getEntity().toString() );
		
		XMLRPCResultSuccess resultSuccess = XMLRPC_Subscriber.getSuccess( responseParser );
		
		Assert.assertNotNull( resultSuccess );
		
		Assert.assertEquals( resultSuccess.getBoolean(), "0" );
		
		logger.info( "msisdn created: " + msisdn );		
				
	}	
	
	@Parameters( "msisdn" )
	@Test( priority = 10, enabled = false )
	public void deleteExistingSubscriber( @Optional( default_msisdn ) String msisdn ) {
				
		Assert.assertTrue( msisdn.length() > 0 );
		
		Assert.assertTrue( this.isSubscriber( msisdn ) );
		
		Map<String, Object> subscriberParams = new HashMap<String, Object>();
		subscriberParams.put( XMLRPCSubscriber.Params.msisdn.name(), msisdn );
						
		ArrayList<String> params = new ArrayList<String>();
		params.add( HTTPXMLRPCForm.getAuthenticationParam( env.getUserName( "superman" ), env.getPassword( "superman" )) );
		params.add( HTTPXMLRPCForm.getSubscriber( subscriberParams ) );
		
		ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.subscribermanager_deleteSubscriber.call( env.getLink() + "xmlrpc/" , params );
		
		XMLRPCResultParser responseParser = new XMLRPCResultParser( response.getEntity().toString() );
		
		XMLRPCResultSuccess resultSuccess = XMLRPC_Subscriber.getSuccess( responseParser );
		
		Assert.assertNotNull( resultSuccess );
		
		Assert.assertEquals( resultSuccess.getBoolean(), "0" );
		
		logger.info( "msisdn deleted: " + msisdn );
		
	}
	
	private XMLRPCResultParser xmlrpc( HTTPXMLRPCForm.CallTypes callType, Map<String, Object> subscriberParams ) {
		
		ArrayList<String> params = new ArrayList<String>();
		params.add( HTTPXMLRPCForm.getAuthenticationParam( env.getUserName( "superman" ), env.getPassword( "superman" )) );
		params.add( HTTPXMLRPCForm.getSubscriber( subscriberParams ) );
		
		ClientResponse<String> response = callType.call( env.getLink() + "xmlrpc/" , params );
		
		XMLRPCResultParser responseParser = new XMLRPCResultParser( response.getEntity().toString() );
		
		return responseParser;
		
	}
	
	private boolean isSubscriber( String msisdn ) {
		
		Subscribers subscribersTable = new Subscribers();
		
		String query = select( Subscribers.Fields.msisdn ).from( subscribersTable ).where( op( Subscribers.Fields.msisdn ).eq( msisdn ) ).build();
		
		ResultSet rs = mysql.execQuery( query );		
		
		boolean found = false;
		
		try {
			while ( rs.next() ) {
				found = true;
			}
		} catch ( SQLException e ) {}
		
		return found;
		
	}
	
	private static XMLRPCResultSuccess getSuccess( XMLRPCResultParser responseParser ) {
		
		Map<ResultType, Object> result = responseParser.parse();
		
		XMLRPCResultSuccess success = (XMLRPCResultSuccess)result.get( ResultType.BOOLEAN );		
		
		return success;
		
	}
	
	private static XMLRPCResultFault getFault( XMLRPCResultParser responseParser ) {
		
		Map<ResultType, Object> result = responseParser.parse();
		
		XMLRPCResultFault fault = (XMLRPCResultFault)result.get( ResultType.FAULT );		
		
		return fault;
		
	}
		
}
