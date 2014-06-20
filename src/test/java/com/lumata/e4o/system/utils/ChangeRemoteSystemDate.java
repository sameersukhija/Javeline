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
import com.lumata.common.testing.system.User;
import com.lumata.e4o.system.environment.ExpressionKernelCommands;

public class ChangeRemoteSystemDate {
	
	NetworkEnvironment env;
	Server actruleServer;
	ExpressionKernelCommands ekcServer;
			
	/* 	Initialize Environment */
	@Parameters({"environment", "gui_server", "user"})
	@BeforeClass
	public void init( @Optional("E4O_VM") String environment, @Optional("actrule") String gui_server, @Optional("superman") String user ) throws NetworkEnvironmentException {		
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );

		actruleServer = env.getServer( gui_server );
		
		final String sshUser = "root";
		final Service sshService = env.getService( Service.Type.ssh , gui_server );
		
		ekcServer = new ExpressionKernelCommands( sshService, sshUser );
				
	}
	
	@Test(enabled=true, priority = 1 )
	public void changeRemoteSystemDateViaSSH() throws Exception {
		
		final int YEAR_ = 2014;
		final int MONTH_ = Calendar.APRIL;
		final int DAY_ = 1;
		final int HOUR_ = 10;
		final int MINUTE_ = 00;
		final int SECOND_ = 00;
				
		Calendar date = Calendar.getInstance();
		date.set( YEAR_, MONTH_, DAY_, HOUR_, MINUTE_, SECOND_ );
				
		ekcServer.setServerDatetime( date );
		
	}
	
}
