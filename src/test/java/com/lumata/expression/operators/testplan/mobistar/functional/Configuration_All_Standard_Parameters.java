package com.lumata.expression.operators.testplan.mobistar.functional;

import static com.lumata.common.testing.orm.Query.select;
import static com.lumata.common.testing.orm.Filter.op;

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
import com.lumata.expression.operators.dao.configuration.ConfigurationDAO;
import com.lumata.expression.operators.pojo.configuration.ConfigurationTypes;

public class Configuration_All_Standard_Parameters {

	private static final Logger logger = LoggerFactory.getLogger( Configuration_All_Standard_Parameters.class );
	
	Environment env;
	Mysql mysql;
	int user_id = 0;
	
	@Parameters({"environment", "tenant", "user"})
	@BeforeClass
	public void init( @Optional("E4O_QA") String environment, @Optional("qa") String tenant, @Optional("superman") String user ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		mysql = new Mysql( env.getDataSource( tenant ) );
				
	}
	
	@Parameters({"tenant"})
	@Test()
	public void setCfg( @Optional("qa") String tenant ) {
		
		ConfigurationDAO allStandardParameters = new ConfigurationDAO( ConfigurationTypes.ALL_STANDARD_PARAMETERS_FROM_FILE, null );
		
		allStandardParameters.checkAll( mysql );
		
		
		/*
		asynchronousGlobalINPlugin.insert( mysqlGlobal );
		
		Assert.assertTrue( asynchronousGlobalINPlugin.check( mysqlGlobal ) );
		
		ConfigurationDAO asynchronousTenantINPlugin = new ConfigurationDAO( ConfigurationTypes.STANDARD_RETRY, options );
				
		System.out.println( asynchronousTenantINPlugin.toString() );
		
		asynchronousTenantINPlugin.insert( mysqlTenant );
		
		Assert.assertTrue( asynchronousTenantINPlugin.check( mysqlTenant ) );		
		*/
    
	
	}
	
	
	
	
	
	
	
	/*
	// Create subscriber with wrong xmlrpc requests
	@Parameters( "msisdn" )
	@Test( priority = 1, enabled = true )
	public void createNewSubscriberWidthErrors( @Optional( default_msisdn ) String msisdn ) {
			
		XMLRPCResultParser responseParser;
		XMLRPCResultFault resultFault;
		
		if( this.isSubscriber( msisdn ) ) { this.deleteExistingSubscriber( msisdn ); } 
		
		// Case 1
		Map<String, Object> subscriberParams = new HashMap<String, Object>();
		subscriberParams.put( XMLRPCSubscriber.Params.msisdn.name(), msisdn );
		
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultFault = Configuration_All_Standard_Parameters.getFault( responseParser );
				
		Assert.assertEquals( resultFault.getCode(), "5" );
		Assert.assertEquals( resultFault.getMessage(), "missing mandatory param subscription_date" );
		
		// Case 2
		subscriberParams.put( XMLRPCSubscriber.Params.subscription_date.name(), "2014-01-01" );
		
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultFault = Configuration_All_Standard_Parameters.getFault( responseParser );
		
		Assert.assertEquals( resultFault.getCode(), "5" );
		Assert.assertEquals( resultFault.getMessage(), "missing mandatory param rate_plan" );
				
		// Case 3
		subscriberParams.put( XMLRPCSubscriber.Params.rate_plan.name(), "FUN" );
		
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultFault = Configuration_All_Standard_Parameters.getFault( responseParser );
		
		Assert.assertEquals( resultFault.getCode(), "5" );
		Assert.assertEquals( resultFault.getMessage(), "missing mandatory param status" );
		
		// Case 4
		subscriberParams.put( XMLRPCSubscriber.Params.status.name(), "active" );
		
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultFault = Configuration_All_Standard_Parameters.getFault( responseParser );
		
		Assert.assertEquals( resultFault.getCode(), "5" );
		Assert.assertEquals( resultFault.getMessage(), "missing mandatory param in_tag" );

		// Case 5
		subscriberParams.put( XMLRPCSubscriber.Params.in_tag.name(), "QAIN" );
		
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultFault = Configuration_All_Standard_Parameters.getFault( responseParser );
		
		Assert.assertEquals( resultFault.getCode(), "5" );
		Assert.assertEquals( resultFault.getMessage(), "invalid mandatory param network" );
	
		// Case 6
		subscriberParams.put( XMLRPCSubscriber.Params.network.name(), "mobile" );
		
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultFault = Configuration_All_Standard_Parameters.getFault( responseParser );
		
		// Actual
		Assert.assertEquals( resultFault.getCode(), "6" );
		Assert.assertEquals( resultFault.getMessage(), "invalid status active" );
		
		// Expected
		//Assert.assertEquals( resultFault.getCode(), "5" );
		//Assert.assertEquals( resultFault.getMessage(), "invalid mandatory param profile" );
				
		// Case 7
		subscriberParams.put( XMLRPCSubscriber.Params.profile.name(), "prepaid" );
		subscriberParams.put( XMLRPCSubscriber.Params.subprofile.name(), "" );
		
		subscriberParams.put( XMLRPCSubscriber.Params.subscription_date.name(), "2014-01-45" );
		
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultFault = Configuration_All_Standard_Parameters.getFault( responseParser );
		
		Assert.assertEquals( resultFault.getCode(), "2" );
		Assert.assertEquals( resultFault.getMessage(), "unable to create subscriber" );
		
		// Case 8
		subscriberParams.put( XMLRPCSubscriber.Params.subscription_date.name(), "2014-01-01" );
		
		subscriberParams.put( XMLRPCSubscriber.Params.rate_plan.name(), "NOT EXISTS" );
		
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultFault = Configuration_All_Standard_Parameters.getFault( responseParser );
		
		Assert.assertEquals( resultFault.getCode(), "6" );
		Assert.assertEquals( resultFault.getMessage(), "invalid rate_plan NOT EXISTS" );
		
		// Case 9
		subscriberParams.put( XMLRPCSubscriber.Params.rate_plan.name(), "FUN" );
		
		subscriberParams.put( XMLRPCSubscriber.Params.status.name(), "NOT EXISTS" );
		
		responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
		resultFault = Configuration_All_Standard_Parameters.getFault( responseParser );
		System.out.println( resultFault.getCode() + " - " + resultFault.getMessage() );
		Assert.assertEquals( resultFault.getCode(), "6" );
		Assert.assertEquals( resultFault.getMessage(), "invalid status NOT EXISTS" );		
		
		
		
		
		
		/*
		subscriberParams.put( XMLRPCSubscriber.Params.subscription_date.name(), "2014-01-06" );
		subscriberParams.put( XMLRPCSubscriber.Params.profile.name(), "prepaid" );
		subscriberParams.put( XMLRPCSubscriber.Params.subprofile.name(), "" );
		subscriberParams.put( XMLRPCSubscriber.Params.rate_plan.name(), "FUN" );
		subscriberParams.put( XMLRPCSubscriber.Params.status.name(), "active" );
		subscriberParams.put( XMLRPCSubscriber.Params.in_tag.name(), "QAIN" );
		subscriberParams.put( XMLRPCSubscriber.Params.network.name(), "mobile" );
		*/
		
		
		/*
		ArrayList<String> params = new ArrayList<String>();
		params.add( HTTPXMLRPCForm.getAuthenticationParam( env.getUserName( "superman" ), env.getPassword( "superman" )) );
		params.add( HTTPXMLRPCForm.getSubscriber( subscriberParams ) );
		
		ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber.call( env.getLink() + "xmlrpc/" , params );
		System.out.println( response.getEntity().toString() );		
		XMLRPCResultParser responseParser = new XMLRPCResultParser( response.getEntity().toString() );
		
		XMLRPCResultSuccess resultSuccess = XMLRPC_Subscriber.getSuccess( responseParser );
		
		Assert.assertNotNull( resultSuccess );
		
		Assert.assertEquals( resultSuccess.getBoolean(), "0" );
		
		logger.info( "msisdn created: " + msisdn );		
		*/
	/*	
	}
	
	
	
	
	
	
	
	
	@Test( priority = 1, enabled = false )
	public void getNotSubscriber() {
		
		String msisdn = "wrong";
		
		Map<String, Object> subscriberParams = new HashMap<String, Object>();
		subscriberParams.put( XMLRPCSubscriber.Params.msisdn.name(), msisdn );
		
		ArrayList<String> params = new ArrayList<String>();
		params.add( HTTPXMLRPCForm.getAuthenticationParam( env.getUserName( "superman" ), env.getPassword( "superman" )) );
		params.add( HTTPXMLRPCForm.getSubscriber( subscriberParams ) );
		
		ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.subscribermanager_getSubscriber.call( env.getLink() + "xmlrpc/" , params );
		
		XMLRPCResultParser responseParser = new XMLRPCResultParser( response.getEntity().toString() );
				
		XMLRPCResultFault resultFault = Configuration_All_Standard_Parameters.getFault( responseParser );
		
		Assert.assertNotNull( resultFault );
		
		Assert.assertEquals( resultFault.getCode(), "100" );
		
		Assert.assertEquals( resultFault.getMessage(), "subscriber not found for msisdn wrong" );
			
	}
	
	// Get not existing user
	@Parameters( "msisdn" )
	@Test( priority = 2, enabled = false )
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
	@Test( priority = 3, enabled = false )
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
		
		XMLRPCResultSuccess resultSuccess = Configuration_All_Standard_Parameters.getSuccess( responseParser );
		
		Assert.assertNotNull( resultSuccess );
		
		Assert.assertEquals( resultSuccess.getBoolean(), "0" );
		
		logger.info( "msisdn created: " + msisdn );		
				
	}	
	
	@Parameters( "msisdn" )
	@Test( priority = 4, enabled = false )
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
		
		XMLRPCResultSuccess resultSuccess = Configuration_All_Standard_Parameters.getSuccess( responseParser );
		
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
	*/	
}
