package com.lumata.common.testing.system;

import org.json.JSONObject;

public class RemoteService {

	private String host;
	private int port;
	private String user;
	private String password;
	
	public RemoteService() {}

	public RemoteService( String host, int port, String user, String password ) {
		
		this.setHost( host );
		this.setPort( port );
		this.setUser( user );
		this.setPassword( password );
		
	}
	
	public RemoteService( JSONObject serviceInfo ) {
		
		this.setHost( serviceInfo.getString( "host" ) );
		this.setPort( serviceInfo.getInt( "port" ) );
		this.setUser( serviceInfo.getString( "user" ) );
		this.setPassword( serviceInfo.getString( "password" ) );
		
	}
	
	public String getHost() {
		
		return this.host;
		
	}
	
	public int getPort() {
		
		return this.port;
		
	}

	public String getUser() {
	
		return this.user;
		
	}

	public String getPassword() {
	
		return this.password;
		
	}
	
	public void setHost( String host ) {
		
		this.host = host;
		
	}
	
	public void setPort( int port ) {
		
		this.port = port;
		
	}

	public void setUser( String user ) {
	
		this.user = user;
		
	}

	public void setPassword( String password ) {
	
		this.password = password;
		
	}

}