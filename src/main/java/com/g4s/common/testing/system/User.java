package com.g4s.common.testing.system;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class User {

	private static final  Logger logger = LoggerFactory.getLogger( User.class );
	
	private JSONObject userCfg;	
	private String username;
	private String password;
	private String privateKey;
	
	public User() {}
	
	public User( JSONObject user ) {
	
		try {
			
			this.userCfg = user;
			
			if( !userCfg.isNull("username") ) { this.username = userCfg.getString("username"); }
			
			if( !userCfg.isNull("password") ) { this.password = userCfg.getString("password"); }
			
			if( !userCfg.isNull("privateKey") ) { this.privateKey = userCfg.getString("privateKey"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
	}
	
	public String getUsername() {
		
		return this.username;
		
	}
	
	public String getPassword() {
		
		return this.password;
		
	}
	
	public String getPrivateKey() {
		
		return this.privateKey;
		
	}

	public void setUsername( String username ) {
		
		this.username = username;
		
	}
	
	public void setPassword( String password ) {
		
		this.password = password;
		
	}
	
	public void setSecurityPassword( String password ) {
		
		this.password = Security.encrypt( password );
		
	}
	
	public void setSecurityMD5Password( String password ) {
		
		this.password = Security.encrypt( Security.getMD5( password ) );
		
	}
	
	public void setPrivateKey( String privateKey ) {
		
		this.privateKey = privateKey;
		
	}

	public JSONObject toJson() {
		
		return this.userCfg;
		
	}
	
	@Override
	public String toString() {

		StringBuilder userAsString = new StringBuilder();

		userAsString.append( "{ " )
					.append( "\"username\": " + ( this.getUsername() != null ? "\"" : "" ) ).append( this.getUsername() ).append( ( this.getUsername() != null ? "\"" : "" ) + ", " )
					.append( "\"password\": " + ( this.getPassword() != null ? "\"" : "" ) ).append( this.getPassword() ).append( ( this.getPassword() != null ? "\"" : "" ) + ", " )
					.append( "\"privateKey\": " + ( this.getPrivateKey() != null ? "\"" : "" ) ).append( this.getPrivateKey() ).append( ( this.getPrivateKey() != null ? "\"" : "" ) )
					.append( " }" );
	
		return userAsString.toString();
		
	}
	
}
