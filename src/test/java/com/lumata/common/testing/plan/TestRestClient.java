package com.lumata.common.testing.plan;

import org.jboss.resteasy.client.ClientResponse;
import org.testng.annotations.Test;

import com.lumata.common.testing.network.RestClient;

public class TestRestClient {

	@Test
	public void postRequest() {
		
		ClientResponse<String> response = RestClient.post( "http://10.120.38.25:7070/xmlrpc/", null );
		
		System.out.println( response.getEntity().toString() );
		
	}
	
	
}
