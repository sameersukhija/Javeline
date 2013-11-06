package com.lumata.expression.operators.performance;

import java.util.ArrayList;
import java.util.HashMap;

import org.jboss.resteasy.client.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.gui.xmlrpc.HTTPXMLRPCForm;

public class GenerateTokenThread implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger( GenerateTokenThread.class );
	
	private Thread t;
	private boolean running = true;
	private String link;
	private ArrayList<String> params;
	private int requests;
	
	public GenerateTokenThread(int p, Environment env) {
	
		t = new Thread(this);
		t.setPriority(p);
		this.requests = 0;
		this.setParameters( env.getUserName( "superman" ), env.getPassword( "superman" ), "331234561" );
		this.setLink( env.getLink() + "/xmlrpc" );
	
	}
	
	public int getRequestCount() {
		
		return this.requests;
		
	}
	
	public void run() {
	
		while( running ) {
			
			ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.eventmanager_generateCustomEvent.call( link , params );
			
			this.requests++;
			
			try {
				
				Thread.sleep(100);
			
			} catch (Exception e){}
			//logger.info( "TOKEN RESPONSE: " + response.getEntity().toString() );
			
		}

	}
	
	public void stopThread() {
		
		running = false;
	
	}
	
	public void startThread() {
	
		t.start();

	}
	
	public void setParameters( String user, String password, String msisdn ) {
		
		this.params = new ArrayList<String>();
		this.params.add( HTTPXMLRPCForm.getAuthenticationParam( user, password ) );
		this.params.add( HTTPXMLRPCForm.getCustoEventParam( msisdn, HTTPXMLRPCForm.EventTypes.revenue, new HashMap<HTTPXMLRPCForm.EventParameterTypes, String>() { { put( HTTPXMLRPCForm.EventParameterTypes.recharge, "1" ); } } ) );
				
	}
	
	public void setLink( String link ) {
		
		this.link = link;
		
	}
	
}
