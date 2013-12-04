package com.lumata.expression.operators.testplan.performance;

import java.util.ArrayList;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.expression.operators.performance.GenerateSMSThread;

public class RunGenerateSMS {

	private static final Logger logger = LoggerFactory.getLogger( RunGenerateSMS.class );
	
	Connection connection;
	Session session;
	Destination destination;
	MessageProducer producer;
	ArrayList<GenerateSMSThread> threads;
	
	final int N_THREADS = 4;
	final int THREAD_SLEEP = 0;
	final long INTERVAL_ID_SIZE = 1000000;
	final int EXECUTION_TIME = 100000;
	
	RunGenerateSMS() {
		
		try {
			
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("failover:tcp://10.120.44.26:61616");
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("1.SMS.10");
			producer = session.createProducer(destination);
		
		} catch( JMSException e ) {}		
		
	}
	
	private void initializeThreads() {
		
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		
		threads = new ArrayList<GenerateSMSThread>();
				
		for( int i = 0; i < N_THREADS; i++ ) {
			
			threads.add( new GenerateSMSThread( Thread.MAX_PRIORITY, THREAD_SLEEP, ( i * INTERVAL_ID_SIZE ), ( (( i + 1 ) * INTERVAL_ID_SIZE ) - 1 ), producer, session, destination) );
			
		}
		
	}
	
	private void startThreads() {
		
		for( int i = 0; i < N_THREADS; i++ ) {
			
			threads.get( i ).startThread();
						
		}

	}
	
	private void waitExecution() {
		
		try {
			
			Thread.sleep( EXECUTION_TIME );
		
		} catch (Exception e){}
		
	}
	
	private void stopThreads() {
		
		for( int i = 0; i < N_THREADS; i++ ) {
			threads.get( i ).stopThread();
			
		}
		
		try {
			
			session.close();
			connection.close();
		
		} catch( JMSException e ) {}

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
		
		generateSMS.startThreads();
		
		generateSMS.waitExecution();
		
		generateSMS.stopThreads();
		
		generateSMS.printResult();
		
	}
	
}
