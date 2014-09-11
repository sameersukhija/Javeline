package com.lumata.e4o.schema.configuration;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.e4o.notification.dialogmanager.DialogManager;


public class ConfigureNotifXML {
	
	private static final Logger logger = LoggerFactory.getLogger( ConfigureNotifXML.class );
	
	NetworkEnvironment env;	
	
	/* 	Initialize Environment */
	@Parameters({"environment"})
	@BeforeSuite
	public void init( @Optional("E4O_VM") String environment ) throws NetworkEnvironmentException {		
		
		logger.debug( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
					
	}
	
	@Parameters({"tenant", "notifXMLFolder", "notifXMLFile"})
	@Test
	public void configureDMNotifications( @Optional("tenant") String tenant, @Optional("input/configuration") String notifXMLFolder, @Optional("notif.xml") String notifXMLFile ) throws SQLException, IOFileException {
		
		DialogManager.getInstance( env ).configureNotifXML( tenant, notifXMLFolder, notifXMLFile, true );
	
	}
	
}
