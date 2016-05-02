package com.g4s.common.testing.plan;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.g4s.common.testing.exceptions.EnvironmentException;
import com.g4s.common.testing.exceptions.NetworkEnvironmentException;
import com.g4s.common.testing.io.IOFileUtils;
import com.g4s.common.testing.system.Browser;
import com.g4s.common.testing.system.DataSource;
import com.g4s.common.testing.system.NetworkEnvironment;
import com.g4s.common.testing.system.Server;
import com.g4s.common.testing.system.Service;
import com.g4s.common.testing.system.User;

public class TestNetworkEnvironment {
	
	private NetworkEnvironment env = null;	
	
	@Parameters({"environment"})
	@BeforeClass
	public void init( @Optional("E4O_VM_NE") String environment ) throws NetworkEnvironmentException 
	{		
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		Assert.assertNotNull( env , "NetworkEnvironment is null during init phase!");
	}
	
	@Test
	public void printEnv() throws EnvironmentException {		

		if( env != null ) {
			
			Reporter.log( env.toString() );
			
		}
		
	}
	
	@Test
	public void getServer() throws EnvironmentException {		
		
		if( env != null ) {
			
			Server actrule = env.getServer( "actrule" );
			
			Reporter.log( ( actrule != null ? actrule.toString() : null ) );
			
		}
		
	}
	
	@Test
	public void getUser() throws EnvironmentException {		
		
		if( env != null ) {
			
			User user = env.getServer( "actrule" ).getUser( "superman" );
						
			Reporter.log( ( user != null ? user.toString() : null ) );
			
		}
		
	}
	
	@Test
	public void getService() throws EnvironmentException {		
		
		if( env != null ) {
			
			Service service = env.getSSHService( "actrule" );
					
			Reporter.log( ( service != null ? service.toString() : null ) );
				
		}
		
	}
	
	@Test
	public void getBrowser() throws EnvironmentException {		
		
		if( env != null ) {
			
			Browser browser = env.getBrowser( "actrule", Browser.Type.firefox );
					
			Reporter.log( ( browser != null ? browser.toString() : null ) );		
		}	
	}
	
	@Test
	public void getDataSource() throws EnvironmentException, SQLException {		
		
		if( env != null ) {
			
			DataSource ds = env.getDataSource( "tenant" );
					
			Reporter.log( ( ds != null ? ds.toString() : null ) );
			
			// out of scope because we are testing network element init
//			Mysql mysql = new Mysql( ds );
//			
//			ResultSet rs = mysql.execQuery( "select msisdn from subscribers;" );
//			
//			while( rs.next() ) { Reporter.log( rs.getLong("msisdn") ); }
		}
	}

}
