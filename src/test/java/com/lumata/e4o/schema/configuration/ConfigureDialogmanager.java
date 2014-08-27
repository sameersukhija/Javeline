package com.lumata.e4o.schema.configuration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.lumata.e4o.schema.dialogmanager.DmDriver;
import com.lumata.e4o.schema.dialogmanager.DmManager;
import com.lumata.e4o.schema.dialogmanager.DmManagerFeedback;
import com.lumata.e4o.schema.tenant.FilesData;
import com.lumata.e4o.schema.tenant.FilesMeta;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

public class ConfigureDialogmanager {
	
	private static final Logger logger = LoggerFactory.getLogger( ConfigureDialogmanager.class );
	
	NetworkEnvironment env;	
	
	/* 	Initialize Environment */
	@Parameters({"environment"})
	@BeforeSuite
	public void init( @Optional("E4O_VM") String environment ) throws NetworkEnvironmentException {		
		
		logger.debug( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
					
	}
	
	@Parameters({"tenant"})
	@Test( enabled = false )
	public void checkCampaigns( @Optional("tenant") String tenant ) throws SQLException, IOFileException {
	
		Mysql mysql_o2 = new Mysql();
		
		mysql_o2.setHost( "10.120.42.26" );
		mysql_o2.setName( "tenant" );
		mysql_o2.setPort( 3302 );
		mysql_o2.setUser( "system" );
		mysql_o2.setPassword( "system" );
			
		mysql_o2.connect();
		
		FilesMeta fm = new FilesMeta();
		FilesData fd = new FilesData();
				
		String query = select().
						from( fd ).
						join( fm ).
						on( 
							op( FilesData.Fields.id ).eq( FilesMeta.Fields.id ) 
						).	
						where( 
							op( FilesMeta.Fields.name ).eq( "campaigns.xml" ) 
						).
						build();
		
		ResultSet rs = mysql_o2.execQuery( query );
		
		String campaignsXML = "";
		
		while( rs.next() ) {
			
			campaignsXML = rs.getString( FilesData.Fields.content.name() );
			
		}
		
		IOFileUtils.saveResource( campaignsXML, "campaignamanager", "campaigns.xml" );
		
	}
	
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
	
}
