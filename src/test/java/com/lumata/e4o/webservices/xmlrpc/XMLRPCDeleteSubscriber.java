package com.lumata.e4o.webservices.xmlrpc;

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
import com.lumata.e4o.exceptions.XMLRPCParserException;
import com.lumata.e4o.gui.xmlrpc.HTTPXMLRPCForm;
import com.lumata.e4o.gui.xmlrpc.XMLRPCChannel;
import com.lumata.e4o.gui.xmlrpc.XMLRPCRelation;
import com.lumata.e4o.gui.xmlrpc.XMLRPCResultFault;
import com.lumata.e4o.gui.xmlrpc.XMLRPCResultParser;
import com.lumata.e4o.gui.xmlrpc.XMLRPCResultSuccess;
import com.lumata.e4o.gui.xmlrpc.XMLRPCSubscriber;
import com.lumata.e4o.gui.xmlrpc.XMLRPCResultParser.ResultType;
import com.lumata.e4o.schema.tenant.Subscribers;

public class XMLRPCDeleteSubscriber {

	private static final Logger logger = LoggerFactory.getLogger( XMLRPCDeleteSubscriber.class );
	final boolean EXECUTION = true;
	final long XMLRPC_CALL_DELAY = 100;
	
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

	@Parameters( "msisdn" )
	@Test( priority = 9, enabled = EXECUTION )
	public void deleteExistingSubscriber( @Optional( default_msisdn ) String msisdn ) throws XMLRPCParserException  {
		
		msisdn = "33999100000";
		
		Assert.assertTrue( msisdn.length() > 0 );
		
		Assert.assertTrue( this.isSubscriber( msisdn ) );
		
		this.sleep( XMLRPC_CALL_DELAY );
		
		Map<String, Object> subscriberParams = new HashMap<String, Object>();
		subscriberParams.put( XMLRPCSubscriber.Params.msisdn.name(), msisdn );
						
		ArrayList<String> params = new ArrayList<String>();
		params.add( HTTPXMLRPCForm.getAuthenticationParam( env.getUserName( "superman" ), env.getPassword( "superman" )) );
		params.add( HTTPXMLRPCForm.getSubscriber( subscriberParams ) );
		
		ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.subscribermanager_deleteSubscriber.call( env.getLink() + "xmlrpc/" , params );
		
		XMLRPCResultParser responseParser = new XMLRPCResultParser( response.getEntity().toString() );
		
		XMLRPCResultSuccess resultSuccess = XMLRPCDeleteSubscriber.getSuccess( responseParser );
		
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
	
	private static XMLRPCResultSuccess getSuccess( XMLRPCResultParser responseParser ) throws XMLRPCParserException {
		
		Map<ResultType, Object> result = responseParser.parse();
		
		XMLRPCResultSuccess success = (XMLRPCResultSuccess)result.get( ResultType.BOOLEAN );		
		
		return success;
		
	}
	
	private static XMLRPCResultFault getFault( XMLRPCResultParser responseParser ) throws XMLRPCParserException {
		
		Map<ResultType, Object> result = responseParser.parse();
		
		XMLRPCResultFault fault = (XMLRPCResultFault)result.get( ResultType.FAULT );		
		
		return fault;
		
	}
	
	public void sleep( long delay ) {
		
		try { Thread.sleep( delay ); } catch( InterruptedException e ) { logger.error( e.getMessage(), e ); }	
		
	}
		
}
