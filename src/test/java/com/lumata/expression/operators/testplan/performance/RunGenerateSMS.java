package com.lumata.expression.operators.testplan.performance;

import java.util.ArrayList;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.expression.operators.dm.DialogManagerConnection;
import com.lumata.expression.operators.performance.GenerateSMSThread;

public class RunGenerateSMS {

	private static final Logger logger = LoggerFactory.getLogger( RunGenerateSMS.class );
		
	ArrayList<DialogManagerConnection> dmConnections;
	ArrayList<GenerateSMSThread> threads;
	
	final int N_THREADS = 10;
	final int THREAD_SLEEP = 0;
	final long INTERVAL_ID_SIZE = 1000000;
	final int EXECUTION_TIME = 30000;
	final long ID = 10;
	final String QUEUE = "1.SMS.1";
	final String CONNECTION_FACTORY = "failover:tcp://10.120.44.26:61616";
	//final String CONNECTION_FACTORY = "failover:(tcp://10.120.44.26:61616)?connection.useAsyncSend=true";
	
	RunGenerateSMS() {
				
		dmConnections = new ArrayList<DialogManagerConnection>();
		threads = new ArrayList<GenerateSMSThread>();
				
	}
	
	private void initializeThreads() {
		
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);	
				
		for( int i = 0; i < N_THREADS; i++ ) {
		    
			DialogManagerConnection dmConnection = new DialogManagerConnection( CONNECTION_FACTORY, QUEUE );
			dmConnections.add( dmConnection );
			
			GenerateSMSThread t = new GenerateSMSThread(ID, Thread.MAX_PRIORITY, THREAD_SLEEP, ( i * INTERVAL_ID_SIZE ), ( (( i + 1 ) * INTERVAL_ID_SIZE ) - 1 ), dmConnections.get( i ) );
			t.startThread();
			    			    
			threads.add( t );
									
		}
		
	}
	
	private void waitExecution() {
		
		try {
			
			Thread.sleep( EXECUTION_TIME );
		
		} catch (Exception e){}
		
	}
	
	private void stopThreads() {
		
		for( int i = 0; i < N_THREADS; i++ ) {
			
			try {
				
				dmConnections.get( i ).getSession().close();
				
				dmConnections.get( i ).getConnection().close();			
				
				threads.get( i ).stopThread();
							
			} catch( JMSException e ) {}			
			
		}	
		
	}
		
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
	
	public static void main(String args[]) throws EnvironmentException, IOFileException {
		
		RunGenerateSMS generateSMS = new RunGenerateSMS();
		
		generateSMS.initializeThreads();
		
		generateSMS.waitExecution();
		
		generateSMS.stopThreads();
		
		generateSMS.printResult();
		
	}
	
}
