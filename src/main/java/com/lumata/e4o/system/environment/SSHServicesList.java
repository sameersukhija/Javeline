package com.lumata.e4o.system.environment;

import org.apache.commons.collections.map.MultiKeyMap;

import com.lumata.common.testing.system.Service;

public class SSHServicesList {

	private MultiKeyMap sshl;
	
	public SSHServicesList() {
		
		sshl = new MultiKeyMap();
		
	}
	
	public static SSHServicesList getInstance() {
		
		return new SSHServicesList();
		
	}

	public SSHService get( String sshServer, String sshUser ) {
		
		return (SSHService)sshl.get( sshServer, sshUser );
		
	}

	public SSHServicesList put( String sshServer, String sshUser, SSHService sshService ) {
		
		sshl.put( sshServer, sshUser, sshService );
		
		return this;
		
	}
		
}
