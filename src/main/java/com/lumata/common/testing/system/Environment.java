package com.lumata.common.testing.system;

import java.util.Properties;

import com.lumata.common.testing.validating.Format;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class Environment {

	private static final  Logger logger = LoggerFactory.getLogger( Environment.class );
	
	private JSONObject envCfg;
	
	public enum EnvLoadingType { FILE, RESOURCE }
	
	/* Create an environment from a JSONObject */
	public Environment( JSONObject environment ) {
		
		this.envCfg = environment;
				
	}
	
	/* Create an environment loading the JSONObject from the default folder ( <home of the project> ) or resource folder ( src/main/resources/lumata-common-testing ) */
	public Environment( String environment, EnvLoadingType loadingType ) throws EnvironmentException {
		
		try {
			
			switch( loadingType ) {
			
				case FILE: { this.envCfg = JSONUtils.loadJSONFile( environment.toLowerCase() + Format.JSON_EXTENSION ); break; }
				case RESOURCE: { this.envCfg = JSONUtils.loadJSONResource( environment.toLowerCase() + Format.JSON_EXTENSION ); break;  }
				default: throw new EnvironmentException( "You cannot load an environment from resources different by FILE or RESOURCE" );
			
			}		
									
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new EnvironmentException( e.getMessage(), e );
			
		} catch( IOFileException e ) {			
			
			logger.error( e.getMessage(), e );
			
			throw new EnvironmentException( e.getMessage(), e );
			
		} 			
			
	}
	
	/* Create an environment loading the JSONObject from the file or resource folder ( src/main/resources/lumata-common-testing/folder ) */
	public Environment( String folder, String environment, EnvLoadingType loadingType ) throws EnvironmentException {
		
		try {
			
			switch( loadingType ) {
			
			case FILE: { this.envCfg = JSONUtils.loadJSONFile( folder, environment.toLowerCase() + Format.JSON_EXTENSION ); break; }
			case RESOURCE: { this.envCfg = JSONUtils.loadJSONResource( folder, environment.toLowerCase() + Format.JSON_EXTENSION ); break;  }
			default: throw new EnvironmentException( "You cannot load an environment from resources different by FILE or RESOURCE" );
		
		}
									
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new EnvironmentException( e.getMessage(), e );
			
		} catch( IOFileException e ) {			
			
			logger.error( e.getMessage(), e );
			
			throw new EnvironmentException( e.getMessage(), e );
			
		} 			
			
	}
		
	// ENVIRONMENT ATTRIBUTES
	public String getName() {
		
		try {
			
			if( !envCfg.isNull("name") ) { return envCfg.getString("name"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	/*
	public void setName( JSONObject listObj ) {
		
		try {
			this.getName().put(listObj);
		} catch( Exception e ) {
			logger.error(e.getMessage());
		}
			
	}
	*/

	public String getHost() {
		
		try {
			
			if( !envCfg.isNull("host") ) { return envCfg.getString("host"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getIPAddress() {
		
		try {
			
			if( !envCfg.isNull("ip_address") ) { return envCfg.getString("ip_address"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getLink() {
		
		try {
			
			if( !envCfg.isNull("link") ) { return envCfg.getString("link"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public JSONObject getUsers() {
		
		try {
			
			if( !envCfg.isNull("users") ) { return envCfg.getJSONObject("users"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

	public JSONObject getUser( String user ) {
		
		try {
			
			if( !getUsers().isNull( user.toLowerCase() ) ) { return getUsers().getJSONObject( user.toLowerCase() ); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getUserName( String user ) {
		
		try {
			
			if( !getUser( user ).isNull("username") ) { return getUser( user ).getString("username"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

	public String getPassword( String user ) {
		
		try {
			
			if( !getUser( user ).isNull("password") ) { return getUser( user ).getString("password"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
		
	public JSONObject getOptions() {
		
		try {
			
			if( !envCfg.isNull( "options" ) ) { return envCfg.getJSONObject( "options" ); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

	public JSONObject getBrowsers() {
		
		try {
			
			if( !envCfg.isNull("browsers") ) { return envCfg.getJSONObject("browsers"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public JSONObject getBrowser( String browser ) {
		
		try {
			
			if( !getBrowsers().isNull( SeleniumWebDriver.BrowserType.valueOf( browser.toUpperCase() ).toString() ) ) { 
					return getBrowsers().getJSONObject( SeleniumWebDriver.BrowserType.valueOf( browser.toUpperCase() ).toString() );
			 }
			
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public JSONObject getBrowserProfile( String browser ) {
		
		try {
			
			if( !getBrowser( browser ).isNull( "profile" ) ) { return getBrowser( browser ).getJSONObject( "profile" ); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public JSONObject getDataSources() {
		
		try {
			
			if( !envCfg.isNull("data_sources") ) { return envCfg.getJSONObject("data_sources"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public JSONObject getDataSource( String dataSource ) {
		
		try {
			
			if( !getDataSources().isNull(dataSource) ) { return getDataSources().getJSONObject(dataSource); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public Properties getDataSourceAsProps( String dataSource ) {
		
		Properties props = new Properties();
		
		try {
			
			if( getDataSources().isNull(dataSource) ) { return null; }
			else {
				JSONObject ds = getDataSource( dataSource );
				if( ds.isNull("host")) { props.put("dbHost", "localhost"); } else { props.put("dbHost", ds.getString("host")); }
				if( ds.isNull("name")) { props.put("dbName", ""); } else { props.put("dbName", ds.getString("name")); }
				if( ds.isNull("port")) { props.put("dbPort", "3306"); } else { props.put("dbPort", ds.getString("port")); }
				if( ds.isNull("user")) { props.put("dbUser", ""); } else { props.put("dbUser", ds.getString("user")); }
				if( ds.isNull("password")) { props.put("dbPasswd", ""); } else { props.put("dbPasswd", ds.getString("password")); }
					
				return props;
			}
			
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	
}
