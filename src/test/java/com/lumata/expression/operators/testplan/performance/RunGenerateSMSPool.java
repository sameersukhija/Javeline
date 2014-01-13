package com.lumata.expression.operators.testplan.performance;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.expression.operators.dm.DialogManagerConnection;
import com.lumata.expression.operators.performance.GenerateSMSThreadPool;

public class RunGenerateSMSPool {

	private static final Logger logger = LoggerFactory.getLogger( RunGenerateSMSPool.class );
		
	ArrayList<GenerateSMSThreadPool> smsThreadPool;
	
	ExecutorService pool;
		
	final int N_THREADS = 1;
	final int THREAD_SLEEP = 1000;
	final int MAX_REQUESTS = 1;
	final long INTERVAL_PRINT_RESULT = 1000;
	final long INTERVAL_ID_SIZE = 1000000;
	final int EXECUTION_TIME = 1100;
	final long ID = 10;
	final String QUEUE = "1.SMS.1";
	final String CONNECTION_FACTORY = "failover:tcp://10.120.44.26:61616";
	//final String CONNECTION_FACTORY = "failover:(tcp://10.120.44.26:61616)?connection.useAsyncSend=true";
	
	RunGenerateSMSPool() {
				
		smsThreadPool = new ArrayList<GenerateSMSThreadPool>();
				
	}
	
	private void startThreadPool() {
		
		pool = Executors.newFixedThreadPool( N_THREADS );
						
		for( int i = 0; i < N_THREADS; i++ ) {
						
			final int THREAD_NUMBER = i;
			
			DialogManagerConnection dmConnection = new DialogManagerConnection( CONNECTION_FACTORY, QUEUE );
						
			GenerateSMSThreadPool smsThread = new GenerateSMSThreadPool( ID, THREAD_NUMBER, Thread.MAX_PRIORITY, THREAD_SLEEP, MAX_REQUESTS, INTERVAL_ID_SIZE, INTERVAL_PRINT_RESULT, dmConnection );
			smsThreadPool.add( smsThread );
			
			pool.submit( smsThread );
									
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
			
	private void printResult() {
		
		StringBuilder result = new StringBuilder();
        
		int total = 0;
		
		for( int i = 0; i < N_THREADS; i++ ) {
			
			total = total + smsThreadPool.get( i ).getRequestsCount();
			
			result.append( "Thread ( " )
					.append( i )
					.append( " ) -> requests: " )
					.append( smsThreadPool.get( i ).getRequestsCount() )
					.append( " - fails: " )
					.append( smsThreadPool.get( i ).getFailsCount() )
					.append( "\n" );
			
		}
		
		System.out.println( "\nTotal: " + total + "\n" + result.toString() );
		
	}
		
	public static void main(String args[]) throws EnvironmentException, IOFileException {
		
		RunGenerateSMSPool generateSMS = new RunGenerateSMSPool();
		
		generateSMS.startThreadPool();
		
		generateSMS.waitExecution();
		
		generateSMS.stopThreadPool();
		
		generateSMS.printResult();
				
	}
	
}
