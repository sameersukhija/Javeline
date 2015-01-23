package com.lumata.e4o.system.performance;

import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.xmlrpcBody;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCComponent.xmlrpcOptions;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCOption.sleep;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.arrayInt;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.authentication;
import static com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequestMethods.string;

import java.util.ArrayList;

import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.schema.tenant.Token;
import com.lumata.e4o.webservices.xmlrpc.request.XMLRPCRequest;


public class GenerateXMLRPCAcceptThread implements Runnable {

	//private static final Logger logger = LoggerFactory.getLogger( GenerateXMLRPCPurchaseThread.class );
	
	private Thread t;
	private volatile boolean running = true;
	private int threadId;
	private int requests;
	private int fails;
	private long sleep;
	private Server guiServer;
	private User superman;
	private Long msisdn;
	private ArrayList<Token> tokens;
	private Integer offerId;
	
	public GenerateXMLRPCAcceptThread( int threadId, int p, long sleep, Server guiServer, User superman, Long msisdn, ArrayList<Token> tokens, Integer offerId ) {
	
		t = new Thread(this);
		t.setPriority(p);
		this.threadId = threadId;
		this.requests = 0;
		this.fails = 0;
		this.sleep = sleep;
		this.guiServer = guiServer;
		this.superman = superman;
		this.msisdn = msisdn;
		this.tokens = tokens;
		this.offerId = offerId;
			
	}
	
	public int getRequestsCount() {
		
		return this.requests;
		
	}
	
	public int getFailsCount() {
		
		return this.fails;
		
	}
	
	public void run() {
	    
	    while( running ) {
			
			//long start = System.currentTimeMillis();
							
			try {
				
			    if (0 != this.sleep) {
			        Thread.sleep( this.sleep );			        
			    }
			    Thread.yield();

			    if( requests < tokens.size() ) {
				    
			    	XMLRPCRequest.offeroptimizer_accept().call( 	
						guiServer, 
						xmlrpcBody(
							authentication( superman ),
							string( msisdn ),
							string( tokens.get( requests ).getTokenCode() ),
							arrayInt( offerId ),
							string( "web" )
						),
						xmlrpcOptions( 
							sleep( 100L )
						)
					);
			    	
			    }
			    
			    ++requests;
				
			} catch ( Exception e ) {
				this.fails++;
			} 
			
			//System.out.println("***** time spent: " + (System.currentTimeMillis() - start));
		
		}

	}
	
	public synchronized void stopThread() {
		
		running = false;
	
	}
	
	public void startThread() {
	
		t.start();

	}
	
}
