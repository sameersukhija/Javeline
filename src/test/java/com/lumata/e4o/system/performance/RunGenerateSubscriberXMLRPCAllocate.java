package com.lumata.e4o.system.performance;

import java.util.ArrayList;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.schema.tenant.Token;
import com.lumata.e4o.dao.tenant.DAOSubscribers;
import com.lumata.e4o.dao.tenant.DAOToken;


public class RunGenerateSubscriberXMLRPCAllocate {

	//private static final Logger logger = LoggerFactory.getLogger( RunGenerateSubscriberXMLRPCPurchase.class );
		
	ArrayList<GenerateXMLRPCAllocateThread> threads;
	
	final int START_MSISDN = 0;
	final int N_THREADS = 10;
	final int THREAD_SLEEP = 0;
	final int EXECUTION_TIME = 120000;
	
	NetworkEnvironment env;	
	Server guiServer;
	User superman;
	Mysql mysql;
	
	
	RunGenerateSubscriberXMLRPCAllocate() throws NetworkEnvironmentException {
				
		threads = new ArrayList<GenerateXMLRPCAllocateThread>();
		
		env = new NetworkEnvironment( "input/environments", "e4o_qa3_ne", IOFileUtils.IOLoadingType.RESOURCE );
		
		guiServer = env.getServer( "actrule" );
		
		superman = guiServer.getUser( "superman" );
		
		mysql = new Mysql( env.getDataSource( "tenant" ) );
				
	}
	
	private void initializeThreads() {
		
		ArrayList<Subscribers> subscribers = DAOSubscribers.getInstance( mysql ).getSubscriberList();
		
		if( null != subscribers &&
			subscribers.size() > 0
		) {
		
			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);	
					
			for( int i = START_MSISDN; i < START_MSISDN + N_THREADS; i++ ) {
			    
				Long msisdn = subscribers.get( i ).getMsisdn();
				
				ArrayList<Token> tokens = DAOToken.getInstance( mysql ).getAvailableActiveTokens( msisdn );
				
				GenerateXMLRPCAllocateThread t = new GenerateXMLRPCAllocateThread( ( i + 1 ), Thread.MAX_PRIORITY, THREAD_SLEEP, guiServer, superman, msisdn, tokens );
				t.startThread();
				    			    
				threads.add( t );
										
			}
		
		}
		
	}
	
	private void waitExecution() {
		
		try {
			
			if( threads.size() > 0 ) { Thread.sleep( EXECUTION_TIME ); }
		
		} catch (Exception e){}
		
	}
	
	private void stopThreads() {
		
		for( int i = 0; i < N_THREADS; i++ ) {
			
			if( null != threads.get( i ) ) { threads.get( i ).stopThread(); }
			
		}	
		
	}
		
	private void printResult() {
		
		StringBuilder result = new StringBuilder();
        
		int total = 0;
		
		for( int i = 0; i < N_THREADS; i++ ) {
			
			if( null != threads.get( i ) ) {
				
				total = total + threads.get( i ).getRequestsCount();
				
				result.append( "Thread ( " )
						.append( i )
						.append( " ) -> requests: " )
						.append( threads.get( i ).getRequestsCount() )
						.append( " - fails: " )
						.append( threads.get( i ).getFailsCount() )
						.append( "\n" );
				
			}
		}
		
		System.out.println( "\nTotal: " + total + "\n" + result.toString() );
		
	}
	
	public static void main(String args[]) throws NetworkEnvironmentException {
		
		RunGenerateSubscriberXMLRPCAllocate generateSubscriberPurchase = new RunGenerateSubscriberXMLRPCAllocate();
		
		generateSubscriberPurchase.initializeThreads();
		
		generateSubscriberPurchase.waitExecution();
		
		generateSubscriberPurchase.stopThreads();
		
		generateSubscriberPurchase.printResult();
		
	}
	
}
