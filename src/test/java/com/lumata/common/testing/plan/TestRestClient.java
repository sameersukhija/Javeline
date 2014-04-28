package com.lumata.common.testing.plan;

import org.jboss.resteasy.client.ClientResponse;
import org.testng.annotations.Test;

import com.lumata.common.testing.network.RestClient;

public class TestRestClient {

	@Test( timeOut=10000 )
	public void postRequest() {
		
		RestClient restClient = new RestClient();
		
		ClientResponse<String> response = restClient.post( "http://10.120.38.25:7070/xmlrpc/", null );
		
		System.out.println( response.getEntity().toString() );
		
	}
	
	
}
