package com.lumata.common.testing.system;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.lumata.common.testing.validating.Format;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.io.JSONUtils;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class NetworkEnvironment {

	private static final  Logger logger = LoggerFactory.getLogger( NetworkEnvironment.class );
	
	private JSONObject envCfg;
	private Map<String, Server> servers;
	private Map<String, DataSource> dataSources;
	private Map<String, Service> services;
	private JSONObject options;
	
	/* Create an environment from a JSONObject */
	public NetworkEnvironment( JSONObject environment ) {
		
		this.envCfg = environment;
				
	}
	
	/* Create an environment loading the JSONObject from the default folder ( <home of the project> ) or resource folder ( src/main/resources/lumata-common-testing ) */
	public NetworkEnvironment( String environment, IOFileUtils.IOLoadingType loadingType ) throws NetworkEnvironmentException {
		
		try {
			
			switch( loadingType ) {
				
				case FILE: { this.envCfg = JSONUtils.loadJSONFile( environment.toLowerCase() + Format.JSON_EXTENSION ); break; }
				case RESOURCE: { this.envCfg = JSONUtils.loadJSONResource( environment.toLowerCase() + Format.JSON_EXTENSION ); break;  }
				default: throw new NetworkEnvironmentException( "You cannot load an environment from resources different by FILE or RESOURCE" );
			
			}		
				
			this.init();
			
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new NetworkEnvironmentException( e.getMessage(), e );
		}			
			
	}
	
	/* Create an network environment loading the JSONObject from the file or resource folder ( src/main/resources/lumata-common-testing/folder ) */
	public NetworkEnvironment( String folder, String environment, IOFileUtils.IOLoadingType loadingType ) throws NetworkEnvironmentException {
		
		try {
			
			switch( loadingType ) {
			
				case FILE: { this.envCfg = JSONUtils.loadJSONFile( folder, environment.toLowerCase() + Format.JSON_EXTENSION ); break; }
				case RESOURCE: { this.envCfg = JSONUtils.loadJSONResource( folder, environment.toLowerCase() + Format.JSON_EXTENSION ); break;  }
				default: throw new NetworkEnvironmentException( "You cannot load an environment from resources different by FILE or RESOURCE" );
			
			}
			
			this.init();
									
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new NetworkEnvironmentException( e.getMessage(), e );
			
		}		
			
	}
	
	public void init() {
		
		if( !this.envCfg.equals( JSONObject.NULL ) ) {
			
			this.setServers();
			
			this.setDataSources();
			
			this.setServices();
			
			if( !this.envCfg.isNull( "options" ) ) { this.setOptions( this.envCfg.getJSONObject( "options" ) ); }
			
		}
		
	}

	public Map<String, Server> getServers() {
		
		return this.servers;
		
	}

	public Server getServer( String serverName ) {
		
		return ( this.servers != null ? this.servers.get( serverName ) : null );
		
	}
	
	public Map<String, DataSource> getDataSources() {
		
		return this.dataSources;
		
	}

	public DataSource getDataSource( String dataSourceName ) {
		
		return ( this.dataSources != null ? this.dataSources.get( dataSourceName ) : null );
		
	}

	public Service getService( Service.Type type, String serviceName ) {
		
		if(this.services == null ) { return null; }
		
		return ( this.services != null ? this.services.get( type.name() + "#" + serviceName ) : null );
		
	}
	
	public Service getSSHService( String serviceName ) {
		
		return this.getService( Service.Type.ssh, serviceName );
	
	}
	
	public Service getActiveMQService( String serviceName ) {
		
		return this.getService( Service.Type.activemq, serviceName );
	
	}
	
	public Browser getBrowser( String serverName, Browser.Type browserType ) {
		
		return this.getServer( serverName ).getBrowser( browserType );
		
	}
	
	public Browser getBrowser( String serverName, String browserType ) {
		
		return this.getServer( serverName ).getBrowser( Browser.Type.valueOf( browserType ) );
		
	}

	public JSONObject getOptions() {
		return this.options;
	}
	
	public void setServers() {
		
		if( !envCfg.isNull("servers") ) { 

			servers = new HashMap<String, Server>();
			
			@SuppressWarnings("unchecked")
			Iterator<String> servers = envCfg.getJSONObject( "servers" ).keys();
		   	while( servers.hasNext() ) {
		    	
		   		String serverName = servers.next();
		   		
				Server server = new Server( envCfg.getJSONObject( "servers" ).getJSONObject( serverName ) );
				
				this.servers.put( serverName, server );
		    
			}
		
		}
		
	}

	public void setDataSources() {
		
		if( !envCfg.isNull("dataSources") ) { 

			dataSources = new HashMap<String, DataSource>();
			
			@SuppressWarnings("unchecked")
			Iterator<String> dataSources = envCfg.getJSONObject( "dataSources" ).keys();
		   	while( dataSources.hasNext() ) {
		    	
		   		String dataSourceName = dataSources.next();
		   		
		   		DataSource dataSource = new DataSource( envCfg.getJSONObject( "dataSources" ).getJSONObject( dataSourceName ) );
		   		
		   		this.dataSources.put( dataSourceName, dataSource );
		   				    
			}
		
		}
		
	}
	
	public void setServices() {
		
		if( !envCfg.isNull("services") ) { 

			services = new HashMap<String, Service>();
			
			@SuppressWarnings("unchecked")
			Iterator<String> services = envCfg.getJSONObject( "services" ).keys();
		   	while( services.hasNext() ) {
		    	
		   		String serviceType = services.next();
		   		
		   		@SuppressWarnings("unchecked")
				Iterator<String> serviceServers = envCfg.getJSONObject( "services" ).getJSONObject( serviceType ).keys();
		   		while( serviceServers.hasNext() ) {
		   			
		   			String serviceTypeName = serviceServers.next();
		   			
		   			Service service = new Service( envCfg.getJSONObject( "services" ).getJSONObject( serviceType ).getJSONObject( serviceTypeName ), serviceType ) ;
		   		
		   			this.services.put( Service.Type.valueOf( serviceType ).name() + "#" + serviceTypeName, service );
		   			
			   	}		
		    
			}
		
		}
		
	}
	
	public void setOptions( JSONObject options ) {
		
		this.options = options;
				
	}
	
	@Override
	public String toString() {
		
		StringBuilder networkEnvAsString = new StringBuilder();
		
		StringBuilder serversAsString = new StringBuilder();
		
		StringBuilder dataSourcesAsString = new StringBuilder();
		
		StringBuilder servicesAsString = new StringBuilder();
		
		if( this.servers != null ) {
			
			serversAsString.append( "\"servers\": { " );
			
			for( String serverName : this.servers.keySet() ) {
				serversAsString.append( "\"" ).append( serverName ).append( "\": ")
								.append( this.servers.get( serverName ).toString() ).append( ", " );
			}
			
			if( serversAsString.length() > 0 ) { serversAsString.setLength( serversAsString.length() - 2 ); }
			
			serversAsString.append( " }" );
			
		}
		
		if( this.dataSources != null ) {
			
			dataSourcesAsString.append( "\"dataSources\": { " );
			
			for( String dataSourceName : this.dataSources.keySet() ) {
				dataSourcesAsString.append( "\"" ).append( dataSourceName ).append( "\": ")
								.append( this.dataSources.get( dataSourceName ).toString() ).append( ", " );
			}
			
			if( dataSourcesAsString.length() > 0 ) { dataSourcesAsString.setLength( dataSourcesAsString.length() - 2 ); }
			
			dataSourcesAsString.append( " }" );
			
		}
		
		if( this.services != null ) {
			
			servicesAsString.append( "\"services\": { " );
			
			StringBuilder sshServicesAsString = new StringBuilder();
			
			sshServicesAsString.append( "\"ssh\": { " );
			
			for( String serviceName : this.services.keySet() ) {
								
				String[] service = serviceName.split( "#" );
								
				switch( Service.Type.valueOf( service[ 0 ].toLowerCase() ) ) {
				
					case ssh: { 
						sshServicesAsString.append( "\"" ).append( service[ 1 ] ).append( "\": ");
						sshServicesAsString.append( this.getSSHService( service[ 1 ] ) ).append( ", " ); 
						break; 
					}
				
				}
						
			}
			
			if( sshServicesAsString.length() > 0 ) { sshServicesAsString.setLength( sshServicesAsString.length() - 2 ); }
			
			//if( servicesAsString.length() > 0 ) { servicesAsString.setLength( servicesAsString.length() - 2 ); }
			
			sshServicesAsString.append( " }" );
			
			servicesAsString.append( sshServicesAsString )
							.append( " }" );
			
		}
		
		networkEnvAsString.append( "{ " )
							.append( serversAsString ).append( ", " )
							.append( dataSourcesAsString ).append( ", " )
							.append( servicesAsString ).append( ", " )
							.append( "\"options\": " ).append( options.toString() )
							.append( " }" );
		
		return networkEnvAsString.toString();
		
	}
	
}
