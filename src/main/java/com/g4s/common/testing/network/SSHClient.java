package com.g4s.common.testing.network;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.g4s.common.testing.system.Security;
import com.g4s.common.testing.system.Service;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSHClient {

	private static final Logger logger = LoggerFactory.getLogger( SSHClient.class );
		
	protected Session session;
	protected Channel channel;

	public enum Types { sftp, exec, shell }
	
	public SSHClient( Service service, String user ) {
		
		this( service.getHostAddress(), service.getHostPort(), service.getUser( user ).getUsername(), service.getUser( user ).getPassword() );
			
	}
	
	public SSHClient( String host, int port, String user, String encryptedPassword ) {
		
		JSch jsch= new JSch();
		
		try {
		
			this.session = jsch.getSession( user, host, port );
    	
			this.session.setPassword( Security.decrypt( encryptedPassword ) );
			
			this.setDefaultCfg();
			
			this.session.connect();
		
		} catch( JSchException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
	}
	
	public void setDefaultCfg() {
		
		Properties config = new Properties(); 
    	
		config.put("StrictHostKeyChecking", "no");
    	
    	this.session.setConfig( config );
		
	}
	
	public Channel getChannel() {
		
		return this.channel;
		
	}
	
	public void setChannel( Channel channel ) {
		
		this.channel = channel;
		
	}

	public boolean isConnected() {
	
		return this.session.isConnected();
		
	}
	
}
