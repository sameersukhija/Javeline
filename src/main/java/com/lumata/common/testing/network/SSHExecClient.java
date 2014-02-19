package com.lumata.common.testing.network;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;

public class SSHExecClient extends SSHClient {

	private static final Logger logger = LoggerFactory.getLogger( SSHExecClient.class );
		
	public SSHExecClient( String host, int port, String user, String encryptedPassword ) {
	
		super( host, port, user, encryptedPassword );
				
		try {
			
			this.setChannel( this.session.openChannel(SSHClient.Types.exec.name()));
			
		} catch( JSchException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
	
	}

	public InputStream execCommand( String command ) {
		
		InputStream in = null;
		
		try {
		
			((ChannelExec)this.channel).setCommand(command);
		
		 	this.channel.setInputStream(null);
		 
		 	((ChannelExec)channel).setErrStream(System.err);
		 
			in = channel.getInputStream();
		
			channel.connect();
		
		} catch ( IOException | JSchException e) {
		
			logger.error( e.getMessage(), e );
			
		}
		
		return in;
		
	}
	
}
