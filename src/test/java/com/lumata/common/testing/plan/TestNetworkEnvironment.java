package com.lumata.common.testing.plan;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.Browser;
import com.lumata.common.testing.system.DataSource;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.Service;
import com.lumata.common.testing.system.User;

public class TestNetworkEnvironment {
	
	private static final  Logger logger = LoggerFactory.getLogger( TestNetworkEnvironment.class );
		
	NetworkEnvironment env;	
	
	@Parameters({"environment"})
	@BeforeClass( enabled = true )
	public void loadNetworkEnvironmentFromResource_1( @Optional("E4O_VM_NE") String environment ) throws NetworkEnvironmentException {		
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		Assert.assertNotNull( env );		
	}
	
	@Test( enabled = true )
	public void printEnv() throws EnvironmentException {		
		
		if( env != null ) {
			
			System.out.println( env.toString() );
			
		}
		
	}
	
	@Test( enabled = false )
	public void getServer() throws EnvironmentException {		
		
		if( env != null ) {
			
			Server actrule = env.getServer( "actrule" );
			
			System.out.println( ( actrule != null ? actrule.toString() : null ) );
			
		}
		
	}
	
	@Test( enabled = false )
	public void getUser() throws EnvironmentException {		
		
		if( env != null ) {
			
			User user = env.getServer( "actrule" ).getUser( "superman" );
						
			System.out.println( ( user != null ? user.toString() : null ) );
			
		}
		
	}
	
	@Test( enabled = false )
	public void getService() throws EnvironmentException {		
		
		if( env != null ) {
			
			Service service = env.getSSHService( "actrule" );
					
			System.out.println( ( service != null ? service.toString() : null ) );
				
		}
		
	}
	
	@Test( enabled = false )
	public void getBrowser() throws EnvironmentException {		
		
		if( env != null ) {
			
			Browser browser = env.getBrowser( "actrule", Browser.Type.firefox );
					
			System.out.println( ( browser != null ? browser.toString() : null ) );
				
		}
		
	}
	
	@Test( enabled = false )
	public void getDataSource() throws EnvironmentException, SQLException {		
		
		if( env != null ) {
			
			DataSource ds = env.getDataSource( "tenant" );
					
			System.out.println( ( ds != null ? ds.toString() : null ) );
			
			Mysql mysql = new Mysql( ds );
			
			ResultSet rs = mysql.execQuery( "select msisdn from subscribers;" );
			
			while( rs.next() ) { System.out.println( rs.getLong("msisdn") ); }
				
		}
		
	}

}
