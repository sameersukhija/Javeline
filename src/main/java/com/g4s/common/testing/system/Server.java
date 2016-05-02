package com.g4s.common.testing.system;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * @param <User>
 * 
 */
public class Server {

	private static final  Logger logger = LoggerFactory.getLogger( Server.class );
	
	protected JSONObject serverCfg;
	
	private Map<String, User> users = new HashMap<String, User>();
	private Map<Browser.Type, Browser> browsers = new HashMap<Browser.Type, Browser>();
	private String hostName;
	private String hostAddress;
	private Integer hostPort;
	private String link;
	
	public Server() {}
	
	public Server( JSONObject server ) {
	
		try {
			
			this.serverCfg = server;
			
			if( !serverCfg.isNull("hostName") ) { this.hostName = serverCfg.getString("hostName"); }
			
			if( !serverCfg.isNull("hostAddress") ) { this.hostAddress = serverCfg.getString("hostAddress"); }
			
			if( !serverCfg.isNull("hostPort") ) { this.hostPort = serverCfg.getInt("hostPort"); }
			
			if( !serverCfg.isNull("link") ) { this.link = serverCfg.getString("link"); }
			
			if( !serverCfg.isNull("users") ) { 

				@SuppressWarnings("unchecked")
				Iterator<String> users = serverCfg.getJSONObject( "users" ).keys();
			   	while( users.hasNext() ) {
			    	
			   		String username = users.next();
			   		
					User user = new User( serverCfg.getJSONObject( "users" ).getJSONObject( username ) );
					
					this.users.put( username, user );
			    
				}
			   	
			}
					
			if( !serverCfg.isNull("browsers") ) { 

				@SuppressWarnings("unchecked")
				Iterator<String> browsers = serverCfg.getJSONObject( "browsers" ).keys();
			   	while( browsers.hasNext() ) {
			    	
			   		String browserType = browsers.next();
			   		
			   		Browser browser = new Browser( serverCfg.getJSONObject( "browsers" ).getJSONObject( browserType ), browserType );
					
					this.browsers.put( Browser.Type.valueOf( browserType.toLowerCase() ), browser );
			    
				}
			
			}
						
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
	}
	
	public Map<String, User> getUsers() {
		
		return this.users;
		
	}

	public <LdapUser> Map<String, User> getLdapUsers() {
		
		return this.Ldapusers;
		
	}
	
	public User getUser( String user ) {
		
		return ( this.users != null && this.users.containsKey( user ) ? this.users.get( user ) : null );
		
	}

	public User getLdapUser( String Ldapuser ) {
		
		return ( this.Ldapusers != null && this.Ldapusers.containsKey( Ldapuser ) ? this.Ldapusers.get( Ldapuser ) : null );
		
	}

	public Map<Browser.Type, Browser> getBrowsers() {
		
		return this.browsers;
		
	}

	public Browser getBrowser( String browser ) {
		
		return this.getBrowser( Browser.Type.valueOf( browser.toLowerCase() ) );
		
	}

	public Browser getBrowser( Browser.Type browserType ) {
		
		return ( this.browsers != null && this.browsers.containsKey( browserType ) ? this.browsers.get( browserType ) : null );
		
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
	
	public String getLink() {
		
		return this.link;
		
	}
	
	public void setUsers( Map<String, User> users ) {
		
		this.users = users;
		
	}

	public void setLdapUsers( Map<String, User> Ldapusers ) {
		
		this.Ldapusers = Ldapusers;
		
	}

	public void setBrowsers( Map<Browser.Type, Browser> browsers ) {
		
		this.browsers = browsers;
		
	}
	
	public void setHostName( String host_name ) {
		
		this.hostName = host_name;
		
	}
	
	public void setHostAddress( String host_address ) {
		
		this.hostAddress = host_address;
		
	}
	
	public void setHostPort( Integer hostPort ) {
		
		this.hostPort = hostPort;
		
	}
	
	public void setLink( String link ) {
		
		this.link = link;
		
	}
	
	@Override
	public  String toString() {
		
		StringBuilder serverAsString = new StringBuilder();
		
		StringBuilder usersAsString = new StringBuilder();
		
		StringBuilder LdapusersAsString = new StringBuilder();
		
		for( String user : this.users.keySet() ) {
			usersAsString.append("\"").append( user ).append( "\": " ).append( ((User)this.users.get( user )).toString() ).append( ", " );
		}
		
		for( String Ldapuser : this.Ldapusers.keySet() ) {
			LdapusersAsString.append("\"").append( Ldapuser ).append( "\": " ).append( ((User)this.Ldapusers.get( Ldapuser )).toString() ).append( ", " );
		}
		
		if( usersAsString.length() > 0 ) { usersAsString.setLength( usersAsString.length() - 2 ); }

		StringBuilder browsersAsString = new StringBuilder();
		
		for( Browser.Type browser : this.browsers.keySet() ) {
			browsersAsString.append("\"").append( browser ).append( "\": " ).append( ((Browser)this.browsers.get( browser )).toString() ).append( ", " );
		}
		
		if( browsersAsString.length() > 0 ) { browsersAsString.setLength( browsersAsString.length() - 2 ); }

		serverAsString.append( "{ " )
						.append( "\"hostName\": " + ( this.getHostName() != null ? "\"" : "" ) ).append( this.getHostName() ).append( ( this.getHostName() != null ? "\"" : "" ) + ", " )
						.append( "\"hostAddress\": " + ( this.getHostAddress() != null ? "\"" : "" ) ).append( this.getHostAddress() ).append( ( this.getHostAddress() != null ? "\"" : "" ) + ", " )
						.append( "\"hostPort\": " ).append( this.getHostPort() ).append( ", " )
						.append( "\"link\": " + ( this.getLink() != null ? "\"" : "" ) ).append( this.getLink() ).append( ( this.getLink() != null ? "\"" : "" ) + ", " )
						.append( "\"users\": { " ).append( usersAsString ).append( " }, " )
						.append( "\"browsers\": { " ).append( browsersAsString ).append( " }" )
						.append( " }" );
		
		return serverAsString.toString();
		
	}
	
}
