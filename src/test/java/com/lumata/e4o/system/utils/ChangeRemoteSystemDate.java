package com.lumata.e4o.system.utils;

import java.util.Calendar;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.Service;
import com.lumata.e4o.system.environment.ExpressionKernelCommands;

public class ChangeRemoteSystemDate {
	
	NetworkEnvironment env;
	Server actruleServer;
	ExpressionKernelCommands ekcServer;
			
	/* 	Initialize Environment */
	@Parameters({"environment", "gui_server", "user"})
	@BeforeClass
	public void init( @Optional("E4O_VM_NE") String environment, @Optional("actrule") String gui_server, @Optional("superman") String user ) throws NetworkEnvironmentException {		
		environment = "E4O_QA3_NE";
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );

		actruleServer = env.getServer( gui_server );
		
		final String sshUser = "root";
		final Service sshService = env.getService( Service.Type.ssh , gui_server );
		
		ekcServer = new ExpressionKernelCommands( sshService, sshUser );
				
	}
	
	@Test(enabled=true, priority = 1 )
	public void changeRemoteSystemDateViaSSH() throws Exception {
		
		Boolean now = false;
		
		Calendar date = Calendar.getInstance();
		
		int YEAR;
		int MONTH;
		int DAY;
		int HOUR;
		int MINUTE;
		int SECOND;
		
		if( now ) {
			
			YEAR = date.get( Calendar.YEAR );
			MONTH = date.get( Calendar.MONTH );
			DAY = date.get( Calendar.DAY_OF_MONTH );
			HOUR = date.get( Calendar.HOUR_OF_DAY );
			MINUTE = date.get( Calendar.MINUTE );
			SECOND = date.get( Calendar.SECOND );
								
		} else {
		
			YEAR = 2015;
			MONTH = Calendar.OCTOBER;
			DAY = 27;
			HOUR = 10;
			MINUTE = 00;
			SECOND = 00;
			
		}
			
		date.set( YEAR, MONTH, DAY, HOUR, MINUTE, SECOND );
		
		//date.add( Calendar.DAY_OF_MONTH, 40 );
				
		ekcServer.setServerDatetime( date, true );
		
	}
	
}
