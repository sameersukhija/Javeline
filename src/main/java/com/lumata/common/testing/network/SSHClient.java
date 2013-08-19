package com.lumata.common.testing.network;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.lumata.common.testing.system.Security;

public class SSHClient {

	private static final Logger logger = LoggerFactory.getLogger( SSHClient.class );
	
	protected Session session;
	
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
	
}
