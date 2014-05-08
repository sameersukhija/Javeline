package com.lumata.e4o.notification.dialogmanager;

import static com.lumata.common.testing.orm.Filter.op;
import static com.lumata.common.testing.orm.Filter.and;
import static com.lumata.common.testing.orm.Query.insert;
import static com.lumata.common.testing.orm.Query.select;
import static com.lumata.common.testing.orm.Query.update;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;

import org.jboss.resteasy.client.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.database.MysqlUtils;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.Service;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.exceptions.CDRException;
import com.lumata.e4o.exceptions.XMLRPCParserException;
import com.lumata.e4o.gui.xmlrpc.HTTPXMLRPCForm;
import com.lumata.e4o.gui.xmlrpc.XMLRPCChannel;
import com.lumata.e4o.gui.xmlrpc.XMLRPCResultParser;
import com.lumata.e4o.gui.xmlrpc.XMLRPCResultSuccess;
import com.lumata.e4o.gui.xmlrpc.XMLRPCSubscriber;
import com.lumata.e4o.gui.xmlrpc.XMLRPCResultParser.ResultType;
import com.lumata.e4o.notification.dialogmanager.ActiveMQ;
import com.lumata.e4o.schema.dialogmanager.DmFeedback;
import com.lumata.e4o.schema.tenant.FilesData;
import com.lumata.e4o.schema.tenant.FilesMeta;
import com.lumata.e4o.schema.tenant.SubsNotif;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.system.environment.ExpressionKernelCommands;

public class SMSSending {

	private static final Logger logger = LoggerFactory.getLogger( SMSSending.class );
	
	private final long XMLRPC_CALL_DELAY = 100;
	private final long FEEDBACK_TIMEOUT = 300000;
	private final long FEEDBACK_POLLING = 5000;
	
	private NetworkEnvironment env;
	private ActiveMQ activeMQ;
	private ExpressionKernelCommands ekcCollector;
	private Server actruleServer;
	private User superman;
	private final Long msisdn = 393669393643L;
	
	/* 	Initialize Environment */
	@Parameters({"environment", "activeMQService" })
	@BeforeSuite
	public void init( @Optional("E4O_VM") String environment, @Optional("dm") String activeMQService ) throws NetworkEnvironmentException, JMSException  {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		ekcCollector = new ExpressionKernelCommands( env.getService( Service.Type.ssh , "collector" ), "root" );
		
		actruleServer = env.getServer( "actrule" ); 
		
		superman = actruleServer.getUser( "superman" );
		
		activeMQ = new ActiveMQ( env.getActiveMQService( activeMQService ) );				
	
	}
	
	@Parameters({"tenant"})
	@Test( priority = 1, enabled = true )
	public void configureDMNotifications( @Optional("tenant") String tenant ) throws SQLException, IOFileException {
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
		
		/** check if notif.xml entry is present in the files_meta table */
		FilesMeta fmTable = new FilesMeta();
		
		String query = select().from( fmTable ).where( op( FilesMeta.Fields.name ).eq( "notif.xml" ) ).build();
		
		ResultSet rs = mysql.execQuery( query );
		
		while( rs.next() ) { fmTable = new FilesMeta( rs ); }
		
		if( fmTable.getId() == null ) {
		
			fmTable.setName( "notif.xml" );
			fmTable.setPath( "./data/notif/" );
			fmTable.setUpdatetime( Calendar.getInstance().getTimeInMillis() );
			
			query = insert( fmTable ).values().build();
			
			fmTable.setId( mysql.execUpdate( query ) );
			
		}
		
		/** check if notif.xml entry is present in the files_data table */
		FilesData fdTable = new FilesData();
		
		query = select().from( fdTable ).where( op( FilesData.Fields.id ).eq( fmTable.getId() ) ).build();
		
		rs = mysql.execQuery( query );
		
		while( rs.next() ) { fdTable = new FilesData( rs ); }
		
		String notifXML = IOFileUtils.loadResourceAsString( "input/configuration", "notif.xml" );
		
		fdTable.setContent( notifXML );
		
		/** add notif.xml entry if not exist */
		if( fdTable.getId() == null ) {
				
			fdTable.setId( fmTable.getId() );
						
			query = insert( fdTable ).values().build();
						
			mysql.execUpdate( query );
			
			logger.info( Log.PUTTING.createMessage( "notif.xml entered in files_data table" ) );
					
		} else {
			
			query = update( fdTable ).set( op( FilesData.Fields.content ).eq( fdTable.getContent() ) ).where( op( FilesData.Fields.id ).eq( fdTable.getId() ) ).build();
			
			mysql.execUpdate( query );
			
			logger.info( Log.PUTTING.createMessage( "notif.xml updated in files_data table" ) );
			
		}				
		
		mysql.close();
		
	}
	
	@Test( dependsOnMethods = "configureDMNotifications", enabled = true )
	public void expiredDataTask() throws NetworkEnvironmentException {

		ExpressionKernelCommands.TaskStatus taskStatus = ekcCollector.execTask( 23500, 1, ExpressionKernelCommands.Task.ExpiredData );
		
		Assert.assertTrue( taskStatus.equals( ExpressionKernelCommands.TaskStatus.OK ) || taskStatus.equals( ExpressionKernelCommands.TaskStatus.ALREADY_DONE ) );
				
	}
	
	/** check subscriber */
	@Parameters({"tenant" })
	@Test( dependsOnMethods = "expiredDataTask", enabled = true )
	public void insertSubscribers( @Optional("tenant") String tenant ) throws XMLRPCParserException, CDRException {

		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
		
		XMLRPCResultParser responseParser;
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		Calendar date = Calendar.getInstance();		
			
		/** delete subscriber if exists */
		if( !this.isSubscriber( msisdn, mysql ) ) { 
			
			this.deleteExistingSubscriber( msisdn ); 
		
			this.sleep( XMLRPC_CALL_DELAY );
	
			String query = update( new Subscribers() )
							.set( 	op( Subscribers.Fields.network_id  ).eq( 1 ),
									op( Subscribers.Fields.tongue ).eq( "ENG" )
							)
							.where( op( Subscribers.Fields.msisdn ).eq( msisdn ) ).build();				
						
			/** set subscriber attributes */
			Map<String, Object> subscriberParams = new HashMap<String, Object>();
			subscriberParams.put( XMLRPCSubscriber.Params.msisdn.name(), msisdn );
			subscriberParams.put( XMLRPCSubscriber.Params.subscription_date.name(), sdf.format( date.getTime() ) );
			subscriberParams.put( XMLRPCSubscriber.Params.rate_plan.name(), "FUN" );
			subscriberParams.put( XMLRPCSubscriber.Params.status.name(), "active" );
			subscriberParams.put( XMLRPCSubscriber.Params.in_tag.name(), "QAIN" );
			subscriberParams.put( XMLRPCSubscriber.Params.network.name(), "mobile" );
						
			/** set subscriber channels */
			Map<String, XMLRPCChannel> channels_list = new HashMap<String, XMLRPCChannel>();
			
			XMLRPCChannel smsChannel = new XMLRPCChannel( XMLRPCChannel.Type.SMS, String.valueOf( msisdn ), true );
			channels_list.put( "sms_channel" , smsChannel );
						
			if( channels_list.size() > 0 ) { subscriberParams.put( XMLRPCSubscriber.Params.channels.name(), channels_list ); }		
			
			/** insert subscriber via xmlrpc call*/
			responseParser = this.xmlrpc( HTTPXMLRPCForm.CallTypes.subscribermanager_createSubscriber, subscriberParams );		
			
			XMLRPCResultSuccess resultSuccess = this.getSuccess( responseParser );
			Assert.assertNotNull( resultSuccess );
			Assert.assertEquals( resultSuccess.getBoolean(), "0" );
			
			mysql.execUpdate( query );
				
		logger.info( "msisdn entered: " + msisdn );

		}
		
		mysql.close();
		
	}
	
	@Test(dependsOnMethods = "insertSubscribers", enabled = true )
	public void configureCampaign() throws JMSException, ParseException {
		
		/** TODO */
		
	}

	@Test( dependsOnMethods = "configureCampaign", enabled = true )
	public void aggregateDataTask() throws JMSException, ParseException {
		
		ExpressionKernelCommands.TaskStatus taskStatus = ekcCollector.execTask( 23500, 1, ExpressionKernelCommands.Task.AggregateData );
		
		Assert.assertTrue( taskStatus.equals( ExpressionKernelCommands.TaskStatus.OK ) || taskStatus.equals( ExpressionKernelCommands.TaskStatus.ALREADY_DONE ) );
		
	}
	
	@Test( dependsOnMethods = "aggregateDataTask", enabled = true )
	public void provisionCampaignsTask() throws JMSException, ParseException {
		
		ExpressionKernelCommands.TaskStatus taskStatus = ekcCollector.execTask( 23500, 1, ExpressionKernelCommands.Task.ProvisionCampaigns );
		
		Assert.assertTrue( taskStatus.equals( ExpressionKernelCommands.TaskStatus.OK ) || taskStatus.equals( ExpressionKernelCommands.TaskStatus.ALREADY_DONE ) );
		
	}
	
	@Test( dependsOnMethods = "provisionCampaignsTask", enabled = true )
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
	public void deleteExistingSubscriber( Long msisdn ) throws XMLRPCParserException  {
				
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
	private XMLRPCResultParser xmlrpc( HTTPXMLRPCForm.CallTypes callType, Map<String, Object> subscriberParams ) {
		
		ArrayList<String> params = new ArrayList<String>();
		params.add( HTTPXMLRPCForm.getAuthenticationParam( superman.getUsername(), superman.getPassword()) );
		params.add( HTTPXMLRPCForm.getSubscriber( subscriberParams ) );
		//System.out.println( params.toString() );
		ClientResponse<String> response = callType.call( actruleServer.getLink() + "xmlrpc/" , params );
		
		XMLRPCResultParser responseParser = new XMLRPCResultParser( response.getEntity().toString() );
		
		return responseParser;
		
	}
	
	/** sleep */
	public void sleep( long delay ) {
		
		try { Thread.sleep( delay ); } catch( InterruptedException e ) { logger.error( e.getMessage(), e ); }	
		
	}
	
	/** check if subscriber exists */
	private boolean isSubscriber( Long msisdn, Mysql mysql  ) {
		
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
	private XMLRPCResultSuccess getSuccess( XMLRPCResultParser responseParser ) throws XMLRPCParserException {
		
		Map<ResultType, Object> result = responseParser.parse();
		
		XMLRPCResultSuccess success = (XMLRPCResultSuccess)result.get( ResultType.BOOLEAN );		
		
		return success;
		
	}

	
}
