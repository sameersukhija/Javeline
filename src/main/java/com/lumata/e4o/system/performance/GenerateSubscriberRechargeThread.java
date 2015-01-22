package com.lumata.e4o.system.performance;

import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.exceptions.GeneratorException;
import com.lumata.e4o.generators.common.Generator;


public class GenerateSubscriberRechargeThread implements Runnable {

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
	
	
	public GenerateSubscriberRechargeThread( int threadId, int p, long sleep, Server guiServer, User superman, Long msisdn ) {
	
		t = new Thread(this);
		t.setPriority(p);
		this.threadId = threadId;
		this.requests = 0;
		this.fails = 0;
		this.sleep = sleep;
		this.guiServer = guiServer;
		this.superman = superman;
		this.msisdn = msisdn;
			
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
								
			    Generator.subscribers()
					.server( guiServer )
					.user( superman )
					.msisdnFixed( msisdn )
					.xmlrpcRecharge( 1L );
				    				
				++requests;
				
			} catch ( InterruptedException | GeneratorException e ) {
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
