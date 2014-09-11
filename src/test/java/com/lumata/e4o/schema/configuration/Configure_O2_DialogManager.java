package com.lumata.e4o.schema.configuration;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.e4o.schema.dialogmanager.DmCountryConfig;
import com.lumata.e4o.schema.dialogmanager.DmDriver;
import com.lumata.e4o.schema.dialogmanager.DmJmailerConfig;
import com.lumata.e4o.schema.dialogmanager.DmJmsConnection;
import com.lumata.e4o.schema.dialogmanager.DmManager;
import com.lumata.e4o.schema.dialogmanager.DmManagerQueuePool;
import com.lumata.e4o.schema.dialogmanager.DmQueue;
import com.lumata.e4o.schema.dialogmanager.DmReddotServer;
import com.lumata.e4o.schema.dialogmanager.DmRmiServer;
import com.lumata.e4o.schema.dialogmanager.DmTopicPublisher;
import com.lumata.e4o.schema.dialogmanager.DmTopicSubscriber;
import com.lumata.e4o.schema.global.Conf;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

public class Configure_O2_DialogManager {
	
	private static final Logger logger = LoggerFactory.getLogger( Configure_O2_DialogManager.class );
	
	NetworkEnvironment env;	
	
	JSONObject dialogManagerSchema;
	
	/* 	Initialize Environment */
	@Parameters({"environment"})
	@BeforeSuite
	public void init( @Optional("E4O_VM") String environment ) throws NetworkEnvironmentException, JSONException {		
		
		logger.debug( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		dialogManagerSchema = new JSONObject();
		
		dialogManagerSchema.put( "dm" , new JSONObject() );
		
		System.out.println( dialogManagerSchema );
					
	}
	
	@Parameters({"global"})
	@Test( enabled = false )
	public void checkGlobalConfiguration( @Optional("global") String global ) throws SQLException, IOFileException {
		
		Mysql mysqlGlobal = new Mysql( env.getDataSource( global ) );
		
		Conf confGlobal = new Conf();
		
		String query = select().
				from( confGlobal ).
				where( 
					op( Conf.Fields.name ).eq( "dialog_manager" ),
					or( 
						op( Conf.Fields.name ).eq( "server_host_address" ),
						op( Conf.Fields.name ).eq( "registry_host" ),
						op( Conf.Fields.name ).eq( "dm_host_address" )
					)
				).
				build();

		ResultSet rs = mysqlGlobal.execQuery( query );
		
		while( rs.next() ) {
			
			Conf dialogManagerConfiguration = new Conf( rs );
			
			System.out.println( dialogManagerConfiguration.toString() );
			
		}
		
		mysqlGlobal.close();
		
	}
	
	
	@Parameters({"tenant"})
	@Test( enabled = false )
	public void checkCampaignNotification( @Optional("tenant") String tenant ) throws SQLException, IOFileException {
	
		//TestDMNotificationEnabled.getInstance( env ).configureDMNotifications( tenant, "input/configuration", "notif_o2.xml" );
	
	}

	@Parameters({"dm"})
	@Test( enabled = true )
	public void checkDMCountryConfig( @Optional("dm") String dm ) throws SQLException, IOFileException, JSONException {
	
		Mysql mysqlDM = new Mysql( env.getDataSource( dm ) );
		
		DmCountryConfig dmCountryConfig = new DmCountryConfig();
		
		String query = select().from( dmCountryConfig ).build();
		
		ResultSet rs = mysqlDM.execQuery( query );
		
		JSONArray dm_country_config = new JSONArray();
		
		while( rs.next() ) {
			
			DmCountryConfig dmCountryConfigRow = new DmCountryConfig( rs );
			
			JSONObject jsonRow = new JSONObject( dmCountryConfigRow.toString() );
			
			dm_country_config.put( jsonRow );
								
		}
		
		dialogManagerSchema.getJSONObject( "dm" ).put( "dm_country_config", dm_country_config );
		
		mysqlDM.close();

	}
	
	@Parameters({"dm"})
	@Test( enabled = false )
	public void checkDMDriver( @Optional("dm") String dm ) throws SQLException, IOFileException {
	
		Mysql mysqlDM = new Mysql( env.getDataSource( dm ) );
		
		DmDriver dmDriver = new DmDriver();
		
		String query = select().from( dmDriver ).build();
		
		ResultSet rs = mysqlDM.execQuery( query );
		
		while( rs.next() ) {
			
			DmDriver dmDriverRow = new DmDriver( rs );
			
			System.out.println( dmDriverRow.toString() );
			
		}
		
		mysqlDM.close();

	}
	
	@Parameters({"dm"})
	@Test( enabled = false )
	public void checkDMJmailerConfig( @Optional("dm") String dm ) throws SQLException, IOFileException {
	
		Mysql mysqlDM = new Mysql( env.getDataSource( dm ) );
		
		DmJmailerConfig dmJmailerConfig = new DmJmailerConfig();
		
		String query = select().from( dmJmailerConfig ).build();
		
		ResultSet rs = mysqlDM.execQuery( query );
		
		while( rs.next() ) {
			
			DmJmailerConfig dmJmailerConfigRow = new DmJmailerConfig( rs );
			
			System.out.println( dmJmailerConfigRow.toString() );
			
		}
		
		mysqlDM.close();

	}
	
	@Parameters({"dm"})
	@Test( enabled = false )
	public void checkDMJmsConnection( @Optional("dm") String dm ) throws SQLException, IOFileException {
	
		Mysql mysqlDM = new Mysql( env.getDataSource( dm ) );
		
		DmJmsConnection dmJmsConnection = new DmJmsConnection();
		
		String query = select().from( dmJmsConnection ).build();
		
		ResultSet rs = mysqlDM.execQuery( query );
		
		while( rs.next() ) {
			
			DmJmsConnection dmJmsConnectionRow = new DmJmsConnection( rs );
			
			System.out.println( dmJmsConnectionRow.toString() );
			
		}
		
		mysqlDM.close();

	}
	
	@Parameters({"dm"})
	@Test( enabled = false )
	public void checkDMManager( @Optional("dm") String dm ) throws SQLException, IOFileException {
	
		Mysql mysqlDM = new Mysql( env.getDataSource( dm ) );
		
		DmManager dmManager = new DmManager();
		
		String query = select().from( dmManager ).orderBy( DmManager.Fields.jms_connection_id ).build();
		
		ResultSet rs = mysqlDM.execQuery( query );
		
		while( rs.next() ) {
			
			DmManager dmManagerRow = new DmManager( rs );
			
			System.out.println( dmManagerRow.toString() );
			
		}
		
		mysqlDM.close();

	}
	
	@Parameters({"dm"})
	@Test( enabled = false )
	public void checkDMManagerQueuePool( @Optional("dm") String dm ) throws SQLException, IOFileException {
	
		Mysql mysqlDM = new Mysql( env.getDataSource( dm ) );
		
		DmManagerQueuePool dmManagerQueuePool = new DmManagerQueuePool();
		
		String query = select().from( dmManagerQueuePool ).build();
		
		ResultSet rs = mysqlDM.execQuery( query );
		
		while( rs.next() ) {
			
			DmManagerQueuePool dmManagerQueuePoolRow = new DmManagerQueuePool( rs );
			
			System.out.println( dmManagerQueuePoolRow.toString() );
			
		}
		
		mysqlDM.close();

	}
	
	@Parameters({"dm"})
	@Test( enabled = false )
	public void checkDMQueue( @Optional("dm") String dm ) throws SQLException, IOFileException {
	
		Mysql mysqlDM = new Mysql( env.getDataSource( dm ) );
		
		DmQueue dmQueue = new DmQueue();
		
		String query = select().from( dmQueue ).build();
		
		ResultSet rs = mysqlDM.execQuery( query );
		
		while( rs.next() ) {
			
			DmQueue dmQueueRow = new DmQueue( rs );
			
			System.out.println( dmQueueRow.toString() );
			
		}
		
		mysqlDM.close();

	}
	
	@Parameters({"dm"})
	@Test( enabled = false )
	public void checkDMReddotServer( @Optional("dm") String dm ) throws SQLException, IOFileException {
	
		Mysql mysqlDM = new Mysql( env.getDataSource( dm ) );
		
		DmReddotServer dmReddotServer = new DmReddotServer();
		
		String query = select().from( dmReddotServer ).build();
		
		ResultSet rs = mysqlDM.execQuery( query );
		
		while( rs.next() ) {
			
			DmReddotServer dmReddotServerRow = new DmReddotServer( rs );
			
			System.out.println( dmReddotServerRow.toString() );
			
		}
		
		mysqlDM.close();

	}
	
	@Parameters({"dm"})
	@Test( enabled = false )
	public void checkDMRmiServer( @Optional("dm") String dm ) throws SQLException, IOFileException {
	
		Mysql mysqlDM = new Mysql( env.getDataSource( dm ) );
		
		DmRmiServer dmRmiServer = new DmRmiServer();
		
		String query = select().from( dmRmiServer ).build();
		
		ResultSet rs = mysqlDM.execQuery( query );
		
		while( rs.next() ) {
			
			DmRmiServer dmRmiServerRow = new DmRmiServer( rs );
			
			System.out.println( dmRmiServerRow.toString() );
			
		}
		
		mysqlDM.close();

	}
	
	@Parameters({"dm"})
	@Test( enabled = true )
	public void checkDMTopicPublisher( @Optional("dm") String dm ) throws SQLException, IOFileException {
	
		Mysql mysqlDM = new Mysql( env.getDataSource( dm ) );
		
		DmTopicPublisher dmTopicPublisher = new DmTopicPublisher();
		
		String query = select().from( dmTopicPublisher ).build();
		
		ResultSet rs = mysqlDM.execQuery( query );
		
		while( rs.next() ) {
			
			DmTopicPublisher dmTopicPublisherRow = new DmTopicPublisher( rs );
			
			System.out.println( dmTopicPublisherRow.toString() );
			
		}
		
		mysqlDM.close();
		
	}
	
	@Parameters({"dm"})
	@Test( enabled = true )
	public void checkDMTopicSubscriber( @Optional("dm") String dm ) throws SQLException, IOFileException {
	
		Mysql mysqlDM = new Mysql( env.getDataSource( dm ) );
		
		DmTopicSubscriber dmTopicSubscriber = new DmTopicSubscriber();
		
		String query = select().from( dmTopicSubscriber ).build();
		
		ResultSet rs = mysqlDM.execQuery( query );
		
		while( rs.next() ) {
			
			DmTopicSubscriber dmTopicSubscriberRow = new DmTopicSubscriber( rs );
			
			System.out.println( dmTopicSubscriberRow.toString() );
			
		}
		
		mysqlDM.close();
		
	}
	
	@AfterSuite
	public void end() {
		
		System.out.println( dialogManagerSchema );
		
	}
	
	/*
	@Parameters({"dm"})
	@Test( enabled = false )
	public void configureDMDriver( @Optional("dm") String dm ) throws SQLException, IOFileException {
		
		Mysql mysql = new Mysql( env.getDataSource( dm ) );
		
		Integer maxDriverId = MysqlUtils.getMaxID( "dm_driver", "driver_id", mysql );
		
		DmDriver dm_driver = new DmDriver();
				
		String query = select().from( dm_driver ).build();
		
		ResultSet rs = mysql.execQuery( query );
		
		while( rs.next() ) {
			
			DmDriver dmDriverRow = new DmDriver( rs );
			
			System.out.println(
			        dmDriverRow .getDriverId() + ", " +
					dmDriverRow.getJmsConnectionId() + ", " +
					dmDriverRow.getTenantIdentifier() + ", " +
					dmDriverRow.getFeedbackQueueId() + ", " + 
					dmDriverRow.getFreezeQueueId() + ", " +
					dmDriverRow.getThrottleQueueId() + ", " +
					dmDriverRow.getQueueId() + ", " +
					dmDriverRow.getMinConsumer() + ", " +
					dmDriverRow.getMaxConsumer() + ", " +
					dmDriverRow.getIdleConsumer() + ", " +
					dmDriverRow.getConsumerBeanName() + ", " +
					dmDriverRow.getHostname() + ", " +
					dmDriverRow.getActive()
					
				);
			
			maxDriverId++;
			
			dmDriverRow.setDriverId( maxDriverId );
			dmDriverRow.setJmsConnectionId( 2 );
			dmDriverRow.setHostname( "md1-da012-e4o04.Lumata.int" );
			
			System.out.println(
			        dmDriverRow .getDriverId() + ", " +
					dmDriverRow.getJmsConnectionId() + ", " +
					dmDriverRow.getTenantIdentifier() + ", " +
					dmDriverRow.getFeedbackQueueId() + ", " + 
					dmDriverRow.getFreezeQueueId() + ", " +
					dmDriverRow.getThrottleQueueId() + ", " +
					dmDriverRow.getQueueId() + ", " +
					dmDriverRow.getMinConsumer() + ", " +
					dmDriverRow.getMaxConsumer() + ", " +
					dmDriverRow.getIdleConsumer() + ", " +
					dmDriverRow.getConsumerBeanName() + ", " +
					dmDriverRow.getHostname() + ", " +
					dmDriverRow.getActive()
					
				);
			

			query = insert( dmDriverRow ).values().build();
			
			System.out.println( query );
			
			mysql.execUpdate( query );
			
		}

	}
	
	@Parameters({"dm"})
	@Test( enabled = false )
	public void configureDMManager( @Optional("dm") String dm ) throws SQLException, IOFileException {
		
		Mysql mysql = new Mysql( env.getDataSource( dm ) );
		
		Integer maxQueueDialogId = MysqlUtils.getMaxID( "dm_manager", "queue_dialog_id", mysql );
		
		System.out.println( maxQueueDialogId );
		
		DmManager dm_manager = new DmManager();
				
		String query = select().from( dm_manager ).build();
		
		ResultSet rs = mysql.execQuery( query );
		
		while( rs.next() ) {
			
			DmManager dmManagerRow = new DmManager( rs );
			
			System.out.println( 
					dmManagerRow.getQueueDialogId() + ", " +
					dmManagerRow.getJmsConnectionId() + ", " +
					dmManagerRow.getMessageQueueId() + ", " +
					dmManagerRow.getMessageQueuePoolSize() + ", " + 
					dmManagerRow.getMessageQueueMinConsumer() + ", " +
					dmManagerRow.getMessageQueueMaxConsumer() + ", " +
					dmManagerRow.getMessageQueueIdleConsumer() + ", " +
					dmManagerRow.getMessageConsumerBeanName() + ", " +
					dmManagerRow.getDriverId() + ", " +
					dmManagerRow.getFeedbackQueueMinConsumer() + ", " +
					dmManagerRow.getFeedbackQueueMaxConsumer() + ", " +
					dmManagerRow.getMessageQueueIdleConsumer() + ", " +
					dmManagerRow.getFeedbackConsumerBeanName() + ", " +
					dmManagerRow.getHostname() + ", " +
					dmManagerRow.getActive() + ", " +
					dmManagerRow.getSendEnabled()
					
				);
			
			maxQueueDialogId++;
			
			dmManagerRow.setQueueDialogId( maxQueueDialogId );
			dmManagerRow.setJmsConnectionId( 2 );
			dmManagerRow.setHostname( "md1-da012-e4o04.Lumata.int" );
			
			System.out.println( 
					dmManagerRow.getQueueDialogId() + ", " +
					dmManagerRow.getJmsConnectionId() + ", " +
					dmManagerRow.getMessageQueueId() + ", " +
					dmManagerRow.getMessageQueuePoolSize() + ", " + 
					dmManagerRow.getMessageQueueMinConsumer() + ", " +
					dmManagerRow.getMessageQueueMaxConsumer() + ", " +
					dmManagerRow.getMessageQueueIdleConsumer() + ", " +
					dmManagerRow.getMessageConsumerBeanName() + ", " +
					dmManagerRow.getDriverId() + ", " +
					dmManagerRow.getFeedbackQueueMinConsumer() + ", " +
					dmManagerRow.getFeedbackQueueMaxConsumer() + ", " +
					dmManagerRow.getMessageQueueIdleConsumer() + ", " +
					dmManagerRow.getFeedbackConsumerBeanName() + ", " +
					dmManagerRow.getHostname() + ", " +
					dmManagerRow.getActive() + ", " +
					dmManagerRow.getSendEnabled()
					
				);
			
			query = insert( dmManagerRow ).values().build();
			
			System.out.println( query );
			
			mysql.execUpdate( query );
			
		}

	}
	
	@Parameters({"dm"})
	@Test( enabled = true )
	public void configureDMManagerFeedback( @Optional("dm") String dm ) throws SQLException, IOFileException {
		
		Mysql mysql = new Mysql( env.getDataSource( dm ) );
		
		Integer maxDMManagerFeedbackId = MysqlUtils.getMaxID( "dm_manager_feedback", "manager_feedback_id", mysql );
		
		DmManagerFeedback dm_manager_feedback = new DmManagerFeedback();
				
		String query = select().from( dm_manager_feedback ).build();
		
		ResultSet rs = mysql.execQuery( query );
		
		while( rs.next() ) {
			
			DmManagerFeedback dmManagerFeedbackRow = new DmManagerFeedback( rs );
									
			System.out.println(
					dmManagerFeedbackRow.getManagerFeedbackId() + ", " +
					dmManagerFeedbackRow.getDriverId() + ", " +
					dmManagerFeedbackRow.getMinConsumer() + ", " +
					dmManagerFeedbackRow.getMaxConsumer() + ", " + 
					dmManagerFeedbackRow.getIdleConsumer() + ", " +
					dmManagerFeedbackRow.getConsumerBeanName() + ", " +
					dmManagerFeedbackRow.getActive()
					
				);
			
			maxDMManagerFeedbackId++;
			
			dmManagerFeedbackRow.setManagerFeedbackId( maxDMManagerFeedbackId );
			dmManagerFeedbackRow.setDriverId( maxDMManagerFeedbackId );
			
			System.out.println(
					dmManagerFeedbackRow.getManagerFeedbackId() + ", " +
					dmManagerFeedbackRow.getDriverId() + ", " +
					dmManagerFeedbackRow.getMinConsumer() + ", " +
					dmManagerFeedbackRow.getMaxConsumer() + ", " + 
					dmManagerFeedbackRow.getIdleConsumer() + ", " +
					dmManagerFeedbackRow.getConsumerBeanName() + ", " +
					dmManagerFeedbackRow.getActive()
					
				);

			query = insert( dmManagerFeedbackRow ).values().build();
			
			System.out.println( query );
			
			mysql.execUpdate( query );
			
		}

	}
	*/
}
