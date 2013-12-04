package com.lumata.expression.operators.performance;

import java.text.ParseException;

import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.expression.operators.dm.DialogManagerMessageUtils;
import com.lumatagroup.expression.e4o.commons.jms.message.DialogManagerMessage;

public class GenerateSMSThread implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger( GenerateSMSThread.class );
	
	private Thread t;
	private volatile boolean running = true;
	private int requests;
	private int fails;
	private long sleep;
	private long interval_left;
	private long interval_right;
	MessageProducer producer;
	Session session;
	Destination destination;
	
	public GenerateSMSThread( int p, long sleep, long interval_left, long interval_right, MessageProducer producer, Session session, Destination destination ) {
	
		t = new Thread(this);
		t.setPriority(p);
		this.interval_left = interval_left;
		this.interval_right = interval_right;
		this.requests = 0;
		this.fails = 0;
		this.sleep = sleep;
		this.producer = producer;
		this.session = session;
		this.destination = destination;
			
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
				
				//long smsID = (long)(Math.random() * (interval_right - interval_left)) + interval_left;
				
				long smsID = interval_left + this.requests++; 
						
				DialogManagerMessage dmMessage = DialogManagerMessageUtils.newValidDialogManagerMessage(smsID, 1L);
				Message message = session.createObjectMessage(dmMessage);
				
				producer.send( destination, message, DeliveryMode.PERSISTENT, ObjectMessage.DEFAULT_PRIORITY, ObjectMessage.DEFAULT_TIME_TO_LIVE );
								
			} catch (InterruptedException e) {
				this.fails++;
			} catch (JMSException e) {
				this.fails++;
			} catch (ParseException e) {
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
