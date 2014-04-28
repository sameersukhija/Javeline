package com.lumata.e4o.tasks;

import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.e4o.system.environment.ExpressionKernelCommands;

public class ExpiredDataTask {
	
	private final boolean exec_ExpiredData_task = true;
	//private final boolean generate_cdr = false;
	
	@Parameters({"environment", "tenant"})
	@Test( enabled = exec_ExpiredData_task )
	public void exec_ExpiredData_task( @Optional("E4O_VM") String environment,  @Optional("tenant") String tenant ) throws NetworkEnvironmentException {

		NetworkEnvironment env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		ExpressionKernelCommands ekc = new ExpressionKernelCommands( env.getSSHService( "actrule" ), "root" );
		
		ExpressionKernelCommands.TaskStatus taskStatus = ekc.execTask( 23500, 1, ExpressionKernelCommands.Task.ExpiredData );
		
		Assert.assertTrue( taskStatus.equals( ExpressionKernelCommands.TaskStatus.OK ) || taskStatus.equals( ExpressionKernelCommands.TaskStatus.ALREADY_DONE ) );
				
	}
	
}
