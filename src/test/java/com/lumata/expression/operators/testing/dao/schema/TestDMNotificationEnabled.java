package com.lumata.expression.operators.testing.dao.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Environment;
import com.lumata.e4o_tenant.schema.FilesData;
import com.lumata.e4o_tenant.schema.FilesMeta;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

public class TestDMNotificationEnabled {
	
	private static final Logger logger = LoggerFactory.getLogger( TestDMNotificationEnabled.class );
	
	Environment env;	
	
	/* 	Initialize Environment */
	@Parameters({"environment"})
	@BeforeSuite
	public void init( @Optional("E4O_VM") String environment ) throws EnvironmentException {		
		
		logger.debug( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
					
	}
	
	@Parameters({"tenant"})
	@Test
	public void configureDMNotifications( @Optional("tenant") String tenant ) throws SQLException, IOFileException {
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
		
		// check if notif.xml entry is present in the files_meta table
		FilesMeta fm = new FilesMeta();
		
		String query = select().from( fm ).where( op( FilesMeta.Fields.name ).eq( "notif.xml" ) ).build();
		
		ResultSet rs = mysql.execQuery( query );
		
		while( rs.next() ) { fm = new FilesMeta( rs ); }
		
		// add notif.xml entry if not exist
		if( fm.getId() == null ) {
		
			fm.setName( "notif.xml" );
			fm.setPath( "./data/notif/" );
			fm.setUpdatetime( Calendar.getInstance().getTimeInMillis() );
			
			query = insert( fm ).values().build();
			
			System.out.println( query );
			
			fm.setId( mysql.execUpdate( query ) );
			
		}
		
		// check if notif.xml entry is present in the files_data table
		FilesData fd = new FilesData();
		
		query = select().from( fd ).where( op( FilesData.Fields.id ).eq( fm.getId() ) ).build();
		
		rs = mysql.execQuery( query );
		
		while( rs.next() ) { fd = new FilesData( rs ); }
		
		String notifXML = IOFileUtils.loadResourceAsString( "input/configuration", "notif.xml" );
		
		fd.setContent( notifXML );
		
		// add notif.xml entry if not exist
		if( fd.getId() == null ) {
				
			fd.setId( fm.getId() );
						
			query = insert( fd ).values().build();
						
			mysql.execUpdate( query );
			System.out.println( query );
			logger.info( Log.PUTTING.createMessage( "notif.xml entered in files_data table" ) );
					
		} else {
			
			query = update( fd ).set( op( FilesData.Fields.content ).eq( fd.getContent() ) ).where( op( FilesData.Fields.id ).eq( fd.getId() ) ).build();
			
			mysql.execUpdate( query );
			System.out.println( query );
			logger.info( Log.PUTTING.createMessage( "notif.xml updated in files_data table" ) );
			
		}				
		
	}
	
}
