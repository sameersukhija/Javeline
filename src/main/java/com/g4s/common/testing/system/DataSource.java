package com.g4s.common.testing.system;

import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class DataSource extends Server {

	//private static final  Logger logger = LoggerFactory.getLogger( DataSource.class );
	
	private JSONObject dataSourceCfg;
	private String hostName;
	private String hostAddress;
	private Integer hostPort;
	private Integer maxActive;
	private Integer maxIdle;
	private Integer maxWait;
	private String user;
	private String password;
	
	public DataSource() {}
	
	public DataSource( JSONObject dataSource ) {
		
		this.dataSourceCfg = dataSource;
		if( !dataSource.isNull( "hostName" ) ) { this.hostName = dataSource.getString( "hostName" ); }
		if( !dataSource.isNull( "hostAddress" ) ) { this.hostAddress = dataSource.getString( "hostAddress" ); }
		if( !dataSource.isNull( "hostPort" ) ) { this.hostPort = dataSource.getInt( "hostPort" ); }
		if( !dataSource.isNull( "maxActive" ) ) { this.maxActive = dataSource.getInt( "maxActive" ); }
		if( !dataSource.isNull( "maxIdle" ) ) { this.maxIdle = dataSource.getInt( "maxIdle" ); }
		if( !dataSource.isNull( "maxWait" ) ) { this.maxWait = dataSource.getInt( "maxWait" ); }
		if( !dataSource.isNull( "user" ) ) { this.user = dataSource.getString( "user" ); }
		if( !dataSource.isNull( "password" ) ) { this.password = dataSource.getString( "password" ); }
		
	}

	public JSONObject getJSON() {
		return this.dataSourceCfg;
	}
	
	public String getHostName() {
		return this.hostName;
	}
	
	public String getHostAddress() {
		return this.hostAddress;
	}
	
	public Integer getHostPort() {
		return this.hostPort;
	}
	
	public Integer getMaxActive() {
		return this.maxActive;
	}
	
	public Integer getMaxIdle() {
		return this.maxIdle;
	}
	
	public Integer getMaxWait() {
		return this.maxWait;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setHostName( String hostName ) {
		this.hostName = hostName;
	}
	
	public void setHostAddress( String hostAddress ) {
		this.hostAddress = hostAddress;
	}
	
	public void setHostPort( Integer hostPort ) {
		this.hostPort = hostPort;
	}
	
	public void setMaxActive( Integer maxActive ) {
		this.maxActive = maxActive;
	}
	
	public void setMaxIdle( Integer maxIdle ) {
		this.maxIdle = maxIdle;
	}
	
	public void setMaxWait( Integer maxWait ) {
		this.maxWait = maxWait;
	}
	
	public void setUser( String user ) {
		this.user = user;
	}
	
	public void setPassword( String password ) {
		this.password = password;
	}
	
	@Override
	public String toString() {
				
		return ( this.dataSourceCfg != null ? this.dataSourceCfg.toString() : "{}" );
		
	}
		
}
