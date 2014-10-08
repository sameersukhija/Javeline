package com.lumata.common.testing.network;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Service;

public class SSHExecClient extends SSHClient {

	private static final Logger logger = LoggerFactory.getLogger( SSHExecClient.class );

	public SSHExecClient( Service service, String user ) {
		
		super( service, user );
				
		init();
	
	}
	
	public SSHExecClient( String host, int port, String user, String encryptedPassword ) {
	
		super( host, port, user, encryptedPassword );
				
		init();
	
	}
	
	private void init() {
		
		try {
			
			this.setChannel( this.session.openChannel(SSHClient.Types.exec.name()));
			
		} catch( JSchException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
	}

	public ArrayList<String> execCommand( String command ) {
		
		ArrayList<String> result = new ArrayList<String>();
				
		try {
		
			((ChannelExec)this.channel).setCommand(command);
		
		 	this.channel.setInputStream(null);
		 
		 	((ChannelExec)channel).setErrStream(System.err);
		 
		 	InputStream in = channel.getInputStream();
		
			channel.connect();
			
			/** get command result */
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	        String line;
	        
	        while(( line = reader.readLine()) != null) {
	        	result.add( line );	            
	        }
	       
	        /** get exit status */
	        int exitStatus = ((ChannelExec)channel).getExitStatus();
	        ((ChannelExec)channel).disconnect();
	        session.disconnect();
	        
	        if( null != in ) { in.close(); }
	        
	        if( exitStatus < 0 ) {
	        	logger.warn( Log.CHECKING.createMessage( "command executed with exit status not set" ) );
	        } else if(exitStatus > 0){
	        	logger.warn( Log.CHECKING.createMessage( "command executed with error ( error_code: " + exitStatus + " )" ) );	        	
	        } else {
	        	logger.info( Log.CHECKING.createMessage( "command executed correctly" ) );	            
	        }
			
		} catch ( IOException | JSchException e) {
		
			logger.error( e.getMessage(), e );
			
		}
		
		return result;
		
	}
	
}
