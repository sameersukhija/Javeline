package com.lumata.e4o.scheduling.scheduledtasks;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.e4o.system.environment.ExpressionKernelCommands;

public class AggregadedTask {
	
	//private final boolean generate_cdr_classes = true;
	//private final boolean generate_cdr = false;
	
	@Parameters({"environment", "tenant"})
	@Test( enabled = true )
	public void create_cdr( @Optional("E4O_VM") String environment,  @Optional("tenant") String tenant ) throws IOFileException, ClassNotFoundException, JSONSException, NetworkEnvironmentException, EnvironmentException {

		NetworkEnvironment env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		ExpressionKernelCommands ekc = new ExpressionKernelCommands( env.getSSHService( "actrule" ), "root" );
		
		System.out.println( ekc.execTask( 23500, 1, ExpressionKernelCommands.Task.AggregateData ) );
		
	}
	
}
