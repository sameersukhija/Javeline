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
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );

		actruleServer = env.getServer( gui_server );
		
		final String sshUser = "root";
		final Service sshService = env.getService( Service.Type.ssh , gui_server );
		
		ekcServer = new ExpressionKernelCommands( sshService, sshUser );
				
	}
	
	@Test(enabled=true, priority = 1 )
	public void changeRemoteSystemDateViaSSH() throws Exception {
		
		Calendar date = Calendar.getInstance();
		
		date.set( Calendar.HOUR_OF_DAY, 23 );
		date.set( Calendar.MINUTE, 00 );
		date.set( Calendar.SECOND, 00 );
		
		final int YEAR_ = 2014;
		final int MONTH_ = Calendar.MAY;
		final int DAY_ = 27;
		final int HOUR_ = date.get( Calendar.HOUR_OF_DAY );
		final int MINUTE_ = date.get( Calendar.MINUTE );
		final int SECOND_ = date.get( Calendar.SECOND );
				
		date.set( YEAR_, MONTH_, DAY_, HOUR_, MINUTE_, SECOND_ );
				
		ekcServer.setServerDatetime( date, true );
		
	}
	
}
