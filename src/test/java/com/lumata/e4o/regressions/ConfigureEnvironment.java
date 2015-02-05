package com.lumata.e4o.regressions;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.database.MysqlUtils;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Service;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.exceptions.FieldException;
import com.lumata.e4o.system.environment.ExpressionKernelCommands;


public class ConfigureEnvironment {
	
	ExpressionKernelCommands ekc;
	NetworkEnvironment env;
	Service sshService;
	String sshUser = "root";
	User superman;
	Mysql mysql;
	
	/* 	Initialize Environment */
	@Parameters({"environment", "nfsdataServer", "user", "tenant"})
	@BeforeClass
	public void init( @Optional("E4O_VM") String environment, @Optional("collector") String nfsdataServer, @Optional("superman") String user, @Optional("tenant") String tenant ) throws NetworkEnvironmentException {		
		
		environment = "E4O_QA3_NE";
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );

		mysql = new Mysql( env.getDataSource( tenant ) );
		
		ekc = new ExpressionKernelCommands( env.getServer( "actrule" ), env.getSSHService( "actrule" ), "root" );
		
	}
	
	@Parameters({"environment", "tenant"})
	@Test( enabled = true, priority = 1 )
	public void exec_ExpiredData_task( @Optional("E4O_VM") String environment,  @Optional("tenant") String tenant ) throws NetworkEnvironmentException {

		NetworkEnvironment env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		ExpressionKernelCommands ekc = new ExpressionKernelCommands( env.getSSHService( "actrule" ), "root" );
		
		ExpressionKernelCommands.TaskStatus taskStatus = ekc.execTask( 23500, 1, ExpressionKernelCommands.Task.ExpiredData );
		
		Assert.assertTrue( taskStatus.equals( ExpressionKernelCommands.TaskStatus.OK ) || taskStatus.equals( ExpressionKernelCommands.TaskStatus.ALREADY_DONE ) );
				
	}
	
	@Test( enabled = true, priority = 2 )
	public void mysqlRestore() throws IOFileException, FieldException, IOException, InterruptedException {
		
		Assert.assertTrue ( 
			
			MysqlUtils.restore( 
					mysql, 
					"backup_regression.sql" 
			)
			
		);
		
		Assert.assertTrue( ekc.expressionRestart() );
					
	}
	
}
