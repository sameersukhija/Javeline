package com.lumata.e4o.notification.dialogmanager;

import static com.lumata.common.testing.orm.Filter.op;
import static com.lumata.common.testing.orm.Query.select;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Optional;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.database.MysqlUtils;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.e4o.schema.dialogmanager.DmFeedback;
import com.lumata.e4o.schema.tenant.FilesData;
import com.lumata.e4o.schema.tenant.FilesMeta;

import static com.lumata.common.testing.orm.Query.*;

public class DialogManager {

	private static final Logger logger = LoggerFactory.getLogger( DialogManager.class );
	
	NetworkEnvironment env;
	
	public DialogManager( NetworkEnvironment env ) {
	
		this.env = env;
		
	}
	
	public static DialogManager getInstance( NetworkEnvironment env ) {
		
		return new DialogManager( env );
	
	}

	public Boolean hasNotifXML( String tenant ) {
		
		Boolean hasNotifXML = ( getNotifXML( tenant ).getId() != null );
		
		logger.info( Log.CHECKING.createMessage( "the system " + ( hasNotifXML ? "has " : "has not ") + "the notif.xml file configured" ) );
		
		return hasNotifXML;
		
	}

	public FilesData getNotifXML( String tenant ) {
		
		logger.info( Log.GETTING.createMessage( "notif.xml file" ) );
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
		
		FilesMeta fm = new FilesMeta();
		
		FilesData fd = new FilesData();
		
		String query = select( FilesData.Fields.id, FilesData.Fields.content ).
						from( fm ).
						join( fd ).
						on( op( FilesMeta.Fields.id ).eq( FilesData.Fields.id ) ).
						where( op( FilesMeta.Fields.name ).eq( "notif.xml" ) ).build();
	
		ResultSet rs = mysql.execQuery( query );
		
		try {
			
			while( rs.next() ) { 
	
				fd = new FilesData( rs ); 
				
			}
		
		} catch (SQLException e) {
			
			return null;
		
		}
		
		mysql.close();
		
		return fd;
		
	}
		
	public FilesData insertNotifXML( String tenant, String notifXMLContent ) {
		
		logger.info( Log.INSERTING.createMessage( "notif.xml file" ) );
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
		
		FilesMeta fm = new FilesMeta();
		
		String query = insert( fm ).values().build();
		
		fm.setName( "notif.xml" );
		
		fm.setPath( "./data/notif/" );
		
		fm.setUpdatetime( Calendar.getInstance().getTimeInMillis() );
		
		fm.setId( mysql.execUpdate( query ) );
		
		FilesData fd = new FilesData();

		fd.setId( fm.getId() );
		
		fd.setContent( notifXMLContent );
		
		query = insert( fd ).values().build();

		mysql.execUpdate( query );
		
		mysql.close();
		
		return fd;
		
	}
	
	public FilesData updateNotifXML( String tenant, FilesData fd ) {
		
		logger.info( Log.UPDATING.createMessage( "notif.xml file" ) );
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
		
		String query = update( fd ).set( op( FilesData.Fields.content ).eq( fd.getContent() ) ).where( op( FilesData.Fields.id ).eq( fd.getId() ) ).build();
	
		mysql.execUpdate( query );
		
		mysql.close();
		
		return fd;
		
	}
	
	public void configureNotifXML( String tenant, String notifXMLFolder, String notifXMLFile, Boolean forceUpdate ) throws IOFileException {
		
		String notifXMLContent = IOFileUtils.loadResourceAsString( notifXMLFolder, notifXMLFile );
				
		FilesData fd;
		
		if( !hasNotifXML( tenant ) ) {
			
			fd = insertNotifXML( tenant, notifXMLContent );
			
		} else {
			
			if( forceUpdate ) {
				
				fd = getNotifXML( tenant );
				
				logger.info( Log.CHECKING.createMessage( "current notif.xml file: " + fd.getContent() ) );
								
				fd.setContent( notifXMLContent );
				
				updateNotifXML( tenant, fd );
				
				logger.info( Log.STORED.createMessage( "new notif.xml file: " + notifXMLContent ) );
				
			}
			
		}
		
	}
	
	public Boolean getDialogManagerFeedback( @Optional("dm") String dm, Long feedbackTimeout, Long feedbackPolling ) throws JMSException, ParseException, SQLException {
		
		Mysql mysql = new Mysql( env.getDataSource( dm ) );
		
		Calendar currentDate = MysqlUtils.getCurrentDate( mysql );
		
		currentDate.add( Calendar.MINUTE , -1 );
		
		DmFeedback dmFeedbackTable = new DmFeedback();
		
		dmFeedbackTable.setDateCreated( new Timestamp( currentDate.getTimeInMillis() ) );
		
		String query = select().from( dmFeedbackTable ).where( op( DmFeedback.Fields.date_created ).get() ).build();
		System.out.println( query );
		long spentTime = 0;
		
		DmFeedback dmFeedback = null;
		
		while( spentTime <= feedbackTimeout || dmFeedback == null  ) {
			
			ResultSet rs = mysql.execQuery( query );
			
			while( rs.next() ) {
				
				dmFeedback = new DmFeedback( rs );
				
			}
			
			spentTime = spentTime + feedbackPolling;
			
		}
				
		mysql.close();
		
		return ( null != dmFeedback );
		
	}
	
}
