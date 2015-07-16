package com.lumata.e4o.system.environment;

import org.apache.commons.collections.map.MultiKeyMap;

public class ExpressionKernelCommandsList {

	private MultiKeyMap ekcl;
	
	public ExpressionKernelCommandsList() {
		
		ekcl = new MultiKeyMap();
		
	}
	
	public static ExpressionKernelCommandsList getInstance() {
		
		return new ExpressionKernelCommandsList();
		
	}

	public ExpressionKernelCommands get( String sshServer, String sshUser ) {
		
		return (ExpressionKernelCommands)ekcl.get( sshServer, sshUser );
		
	}

	public ExpressionKernelCommandsList put( String sshServer, String sshUser, ExpressionKernelCommands ekc ) {
		
		ekcl.put( sshServer, sshUser, ekc );
		
		return this;
		
	}
		
}
