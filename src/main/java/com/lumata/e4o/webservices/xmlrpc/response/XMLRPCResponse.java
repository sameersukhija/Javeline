package com.lumata.e4o.webservices.xmlrpc.response;

import org.jboss.resteasy.client.ClientResponse;

public class XMLRPCResponse {
	
	private ClientResponse<String> response;
	
	public XMLRPCResponse() {}
	
	public XMLRPCResponse( ClientResponse<String> response ) {
		this.response = response;
	}
	
	public ClientResponse<String> getResponse() {
		return this.response;
	}

	public void setResponse( ClientResponse<String> response ) {
		this.response = response;
	}

}
