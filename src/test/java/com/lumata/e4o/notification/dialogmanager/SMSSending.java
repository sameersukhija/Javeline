package com.lumata.e4o.notification.dialogmanager;

import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;
import com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCParameter.ParameterType;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.EventType.revenue;
import static com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidatorMethods.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.*;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.*;
import static com.lumata.e4o.webservices.xmlrpc.request.types.XMLRPCParameter.*;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;


// TODO
public class SMSSending {

	private static final Logger logger = LoggerFactory.getLogger( SMSSending.class );
	
//	private final long XMLRPC_CALL_DELAY = 100;
//	private final long FEEDBACK_TIMEOUT = 300000;
//	private final long FEEDBACK_POLLING = 5000;
	
	private NetworkEnvironment env;
//	private ActiveMQ activeMQ;
//	private ExpressionKernelCommands ekcCollector;
	private Server actruleServer;
	private User superman;
	private final Long msisdn = 3399900001L;
	
	/* 	Initialize Environment */
	@Parameters({"environment", "activeMQService" })
	@BeforeSuite
	public void init( @Optional("E4O_VM") String environment, @Optional("dm") String activeMQService ) throws NetworkEnvironmentException, JMSException  {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
//		ekcCollector = new ExpressionKernelCommands( env.getService( Service.Type.ssh , "collector" ), "root" );
		
		actruleServer = env.getServer( "actrule" ); 
		
		superman = actruleServer.getUser( "superman" );
		
		//activeMQ = new ActiveMQ( env.getActiveMQService( activeMQService ) );				
	
	}
	
	@Parameters({"dm" })
	@Test( enabled = true )
	public void validateSMSSending( @Optional("dm") String dm ) throws Exception {
	
		generateRecharge();
		
		Boolean dmFeedback = DialogManager.getInstance( env ).getDialogManagerFeedback( dm, 300000L, 5000L );
		
		System.out.println( dmFeedback );
		
		
	}
	
	private void generateRecharge() throws Exception {
				
		XMLRPCRequest.eventmanager_generateCustomEvent().call( 
			actruleServer, 
			xmlrpcBody(
				authentication( superman ),
				custoEvent( msisdn, 
							revenue,
							parameter( ParameterType.recharge, true )					
				)
			),
			xmlrpcValidator(
				successFault()
			),
			xmlrpcOptions(
				sleep( 100L )	
			)		
		);
		
	}
	
	/*
	@Test( enabled = true )
	public void generateSMS() {
				
		ArrayList<String> params = new ArrayList<String>();
		params.add( HTTPXMLRPCForm.getAuthenticationParam( superman.getUsername(), superman.getPassword() ) );
		params.add( HTTPXMLRPCForm.getCustoEventParam( String.valueOf( msisdn ), HTTPXMLRPCForm.EventTypes.revenue, new LinkedHashMap<HTTPXMLRPCForm.EventParameterTypes, String>() { { put( HTTPXMLRPCForm.EventParameterTypes.recharge, "1" ); put( HTTPXMLRPCForm.EventParameterTypes.event_storage_policy, "store" ); } } ) );
						
		ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.eventmanager_generateCustomEvent.call( actruleServer.getLink() + "xmlrpc/" , params );
		
		Assert.assertEquals( response.getStatus(), 200 );
		
	}
	
	@Parameters({"dmdb" })
	@Test( dependsOnMethods = "generateSMS", enabled = true )
	public void checkDialogManagerFeedback( @Optional("dm") String dmdb ) throws JMSException, ParseException, SQLException {
		
		Mysql mysql = new Mysql( env.getDataSource( dmdb ) );
		
		Calendar currentDate = MysqlUtils.getCurrentDate( mysql );
		
		DmFeedback dmFeedbackTable = new DmFeedback();
		
		dmFeedbackTable.setDateCreated( new Timestamp( currentDate.getTimeInMillis() ) );
		
		String query = select().from( dmFeedbackTable ).where( op( DmFeedback.Fields.date_created ).get() ).build();
		
		long spentTime = 0;
		
		DmFeedback dmFeedback = null;
		
		while( spentTime <= FEEDBACK_TIMEOUT || dmFeedback == null  ) {
			
			ResultSet rs = mysql.execQuery( query );
			
			while( rs.next() ) {
				
				dmFeedback = new DmFeedback( rs );
				
			}
			
			spentTime = spentTime + FEEDBACK_POLLING;
			
		}
		
		Assert.assertNotNull( dmFeedback );
		
		mysql.close();
		
	}
		
	@Test( dependsOnMethods = "checkDialogManagerFeedback", enabled = false )
	public void insertMTFeedback() throws JMSException, ParseException, SQLException {
		
		long spentTime = 0;
		
		List<Message> messageList = null;
		
		while( messageList == null || messageList.size() <= 0 || spentTime <= FEEDBACK_TIMEOUT ) {
		
			messageList = activeMQ.readScheduledMessages();
			
			spentTime = spentTime + FEEDBACK_POLLING;
			
		}
		
		if( messageList != null || messageList.size() > 0 ) {
			
			activeMQ.addMTFeedback( env.getDataSource( "jmailer_it" ), messageList );
						
		}
		
	}
		
	/** delete existing subscriber via xmlrpc call */
/*	public void deleteExistingSubscriber( Long msisdn ) throws XMLRPCParserException  {
				
		Assert.assertTrue( msisdn != null );
		
		this.sleep( XMLRPC_CALL_DELAY );
		
		Map<String, Object> subscriberParams = new HashMap<String, Object>();
		subscriberParams.put( XMLRPCSubscriber.Params.msisdn.name(), msisdn );
						
		ArrayList<String> params = new ArrayList<String>();
		params.add( HTTPXMLRPCForm.getAuthenticationParam( superman.getUsername(), superman.getPassword()) );
		params.add( HTTPXMLRPCForm.getSubscriber( subscriberParams ) );
		//System.out.println( params.toString() );
		ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.subscribermanager_deleteSubscriber.call( actruleServer.getLink() + "xmlrpc/" , params );
				
		XMLRPCResultParser responseParser = new XMLRPCResultParser( response.getEntity().toString() );
		
		XMLRPCResultSuccess resultSuccess = this.getSuccess( responseParser );
		
		Assert.assertNotNull( resultSuccess );
		
		Assert.assertEquals( resultSuccess.getBoolean(), "0" );
		
		logger.info( "msisdn deleted: " + msisdn );
		
	}
	
	/** xmlrpc call parser */
/*	private XMLRPCResultParser xmlrpc( HTTPXMLRPCForm.CallTypes callType, Map<String, Object> subscriberParams ) {
		
		ArrayList<String> params = new ArrayList<String>();
		params.add( HTTPXMLRPCForm.getAuthenticationParam( superman.getUsername(), superman.getPassword()) );
		params.add( HTTPXMLRPCForm.getSubscriber( subscriberParams ) );
		//System.out.println( params.toString() );
		ClientResponse<String> response = callType.call( actruleServer.getLink() + "xmlrpc/" , params );
		
		XMLRPCResultParser responseParser = new XMLRPCResultParser( response.getEntity().toString() );
		
		return responseParser;
		
	}
	
	/** sleep */
/*	public void sleep( long delay ) {
		
		try { Thread.sleep( delay ); } catch( InterruptedException e ) { logger.error( e.getMessage(), e ); }	
		
	}
	
	/** check if subscriber exists */
/*	private boolean isSubscriber( Long msisdn, Mysql mysql  ) {
		
		Subscribers subscribersTable = new Subscribers();
		SubsNotif subNotif = new SubsNotif();
		
		String query = select( Subscribers.Fields.msisdn ).
						from( subscribersTable ).
						join( subNotif ).
						on( op( Subscribers.Fields.msisdn ).eq( SubsNotif.Fields.msisdn ) ).	
						where( 
								op( Subscribers.Fields.msisdn ).eq( msisdn ),
								and( op( SubsNotif.Fields.channel_id ).eq( 1 ) )
								
						)
						.build();
		
		ResultSet rs = mysql.execQuery( query );		
		
		boolean found = false;
		
		try {
			
			while ( rs.next() ) {
				
				found = true;
			}
			
		} catch ( SQLException e ) {}
		
		return found;
		
	}
	
	/** get xmlrpc call success */
/*	private XMLRPCResultSuccess getSuccess( XMLRPCResultParser responseParser ) throws XMLRPCParserException {
		
		Map<ResultType, Object> result = responseParser.parse();
		
		XMLRPCResultSuccess success = (XMLRPCResultSuccess)result.get( ResultType.BOOLEAN );		
		
		return success;
		
	}
*/
	
}
