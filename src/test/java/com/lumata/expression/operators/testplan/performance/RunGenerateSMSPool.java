package com.lumata.expression.operators.testplan.performance;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.expression.operators.dm.DialogManagerConnection;
import com.lumata.expression.operators.performance.GenerateSMSThread;
import com.lumata.expression.operators.performance.GenerateSMSThreadPool;

public class RunGenerateSMSPool {

	private static final Logger logger = LoggerFactory.getLogger( RunGenerateSMSPool.class );
		
	ArrayList<DialogManagerConnection> dmConnections;
	
	ExecutorService pool;
		
	final int N_THREADS = 1;
	final int THREAD_SLEEP = 100;
	final long INTERVAL_ID_SIZE = 1000000;
	final int EXECUTION_TIME = 1000;
	final long ID = 10;
	final String QUEUE = "1.SMS.1";
	final String CONNECTION_FACTORY = "failover:tcp://10.120.44.26:61616";
	//final String CONNECTION_FACTORY = "failover:(tcp://10.120.44.26:61616)?connection.useAsyncSend=true";
	
	RunGenerateSMSPool() {
				
		dmConnections = new ArrayList<DialogManagerConnection>();
				
	}
	
	private void startThreadPool() {
		
		pool = Executors.newFixedThreadPool( N_THREADS );
						
		for( int i = 0; i < N_THREADS; i++ ) {
		    
			DialogManagerConnection dmConnection = new DialogManagerConnection( CONNECTION_FACTORY, QUEUE );
			dmConnections.add( dmConnection );
			
			pool.execute( new GenerateSMSThreadPool(ID, Thread.MAX_PRIORITY, THREAD_SLEEP, ( i * INTERVAL_ID_SIZE ), ( (( i + 1 ) * INTERVAL_ID_SIZE ) - 1 ), dmConnections.get( i ) ) );
									
		}		
		
	}
	
	private void waitExecution() {
		
		try {
			
			Thread.sleep( EXECUTION_TIME );
		
		} catch (Exception e){}
		
	}
		
	private void stopThreadPool() {
		
		pool.shutdownNow();
		
		System.out.println( "STOP" );
						
	}
	
	
	
	/*	
	private void printResult() {
		
		StringBuilder result = new StringBuilder();
        
		int total = 0;
		
		for( int i = 0; i < N_THREADS; i++ ) {
			
			total = total + threads.get( i ).getRequestsCount();
			
			result.append( "Thread ( " )
					.append( i )
					.append( " ) -> requests: " )
					.append( threads.get( i ).getRequestsCount() )
					.append( " - fails: " )
					.append( threads.get( i ).getFailsCount() )
					.append( "\n" );
			
		}
		
		System.out.println( "\nTotal: " + total + "\n" + result.toString() );
		
	}
	*/
	public static void main(String args[]) throws EnvironmentException, IOFileException {
		
		RunGenerateSMSPool generateSMS = new RunGenerateSMSPool();
		
		generateSMS.startThreadPool();
		
		generateSMS.waitExecution();
		
		generateSMS.stopThreadPool();
		
		//generateSMS.waitExecution();
		
		//generateSMS.stopThreads();
		
		//generateSMS.printResult();
		
	}
	
}
