package com.lumata.unit.system.tasks;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Service;
import com.lumata.e4o.system.tasks.TaskManager;


public class TaskExecutor_ExpiredData {
	
	NetworkEnvironment env;
	Service actruleSSHService;
	String sshUser;
			
	/* 	Initialize Environment */
	@Parameters({"environment", "sshServer", "sshUser"})
	@BeforeClass
	public void init( @Optional("E4O_VM") String environment, @Optional("actrule") String sshServer, @Optional("root") String sshUser ) throws NetworkEnvironmentException {		
		
		/** Create environment configuration */
		this.env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );

		this.actruleSSHService = env.getSSHService( sshServer );
				
		this.sshUser = sshUser;
		
	}
	
	@Test(enabled=true, priority = 1 )
	public void execTaskExpiredData() {
		
		TaskManager.exec( actruleSSHService, sshUser ).ExpiredData();
		
	}
	
}
