package com.lumata.e4o.system.performance;

import java.text.ParseException;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.e4o.notification.dialogmanager.DialogManagerConnection;
import com.lumata.e4o.notification.dialogmanager.DialogManagerMessageUtils;
import com.lumatagroup.expression.e4o.commons.jms.message.DialogManagerMessage;

public class GenerateSMSThread implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger( GenerateSMSThread.class );
	
	private Thread t;
	private volatile boolean running = true;
	private int requests;
	private int fails;
	private long sleep;
	private long id;
	private long interval_left;
	private long interval_right;
	DialogManagerConnection dmConnection;
	
	public GenerateSMSThread(long id, int p, long sleep, long interval_left, long interval_right, DialogManagerConnection dmConnection ) {
	
		t = new Thread(this);
		t.setPriority(p);
		this.id = id;
		this.interval_left = interval_left;
		this.interval_right = interval_right;
		this.requests = 0;
		this.fails = 0;
		this.sleep = sleep;
		this.dmConnection = dmConnection;
			
	}
	
	public int getRequestsCount() {
		
		return this.requests;
		
	}
	
	public int getFailsCount() {
		
		return this.fails;
		
	}
	
	public void run() {
	    
	    long smsID = id * interval_left;
	
		while( running ) {
			
			//long start = System.currentTimeMillis();
							
			try {
				
			    if (0 != this.sleep) {
			        Thread.sleep( this.sleep );
			    }
			    Thread.yield();
						
				DialogManagerMessage dmMessage = DialogManagerMessageUtils.newValidDialogManagerMessage(smsID, 1L);
				Message message = dmConnection.getSession().createObjectMessage(dmMessage);
				
				dmConnection.getProducer().send( dmConnection.getDestination(), message, DeliveryMode.PERSISTENT, ObjectMessage.DEFAULT_PRIORITY, ObjectMessage.DEFAULT_TIME_TO_LIVE );
					
				++smsID;
				++requests;
				
			} catch ( InterruptedException | JMSException | ParseException e) {
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
