package com.lumata.e4o.system.environment;

import com.lumata.common.testing.system.Service;

public class SSHService {
	
	private Service service;
	
	private String user;
	
	public SSHService( Service service, String user ) {
		
		this.service = service;
		
		this.user = user;
		
	}
	
	public Service getService() {
		return this.service;
	}
	
	public String getUser() {
		return this.user;
	}
	
}
