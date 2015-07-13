package com.lumata.e4o.system.performance;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.e4o.notification.dialogmanager.DialogManagerConnection;
import com.lumata.e4o.notification.dialogmanager.DialogManagerMessageUtils;
import com.lumatagroup.dialogmanager.commons.message.DialogManagerMessage;

public class GenerateSMSThreadPool implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger( GenerateSMSThreadPool.class );
	
	//Thread t;
	private volatile boolean running = true;
	private long id;
	private long thread_number;
	private long sleep;
	private long interval_id_size;
	private long sleep_to_print_result;
	private long max_requests;
	private int requests;
	private int fails;
	DialogManagerConnection dmConnection;
	
	public GenerateSMSThreadPool(long id, int thread_number, int priority, long sleep, long max_requests, long interval_id_size, long sleep_to_print_result, DialogManagerConnection dmConnection ) {
	
		//t = new Thread( this );
		//t.setPriority( priority );
		this.id = id;
		this.thread_number = thread_number;
		this.sleep = sleep;
		this.interval_id_size = interval_id_size;
		this.sleep_to_print_result = sleep_to_print_result;
		this.max_requests = max_requests;
		this.requests = 0;
		this.fails = 0;		
		this.dmConnection = dmConnection;
			
	}
	
	public int getRequestsCount() {
		
		return this.requests;
		
	}
	
	public int getFailsCount() {
		
		return this.fails;
		
	}
	
	public void run() {
	    
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
		long last_update_time = System.currentTimeMillis();
		
		System.out.println( formatter.format( new Date( last_update_time ) ) + " - Thread ( " + thread_number + " ) started" );
		
	    long smsID = id * ( thread_number * interval_id_size );
	
	    try {
			
	    	while( running ) {
				
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
					
					long curr_time = System.currentTimeMillis();
					
					if( sleep_to_print_result > 0 && ( curr_time - last_update_time > sleep_to_print_result ) ) {
						
						last_update_time = curr_time;
						
						StringBuilder curr_result = new StringBuilder();
						
						curr_result.append( formatter.format( new Date( curr_time ) ) )
									.append( " - Thread ( " )
									.append( thread_number )
									.append( " ) -> requests: " )
									.append( requests )
									.append( " - fails: " )
									.append( fails );
						
						System.out.println( curr_result );
						
					}
					
					if( max_requests > 0  && requests >= max_requests ) { running = false; }
					
				} catch ( JMSException | ParseException e) {
					this.fails++;
					logger.error( e.getMessage(), e );
				} 
								
			}
		
	    } catch ( InterruptedException e ) {
	    	
	    	running = false;
	    	
	    	dmConnection.close();
	    	
	    	try { Thread.sleep( 1000 ); } catch (InterruptedException e1) {}
	    		    	
	    	long curr_time = System.currentTimeMillis();
	    	
	    	System.out.println( formatter.format( new Date( curr_time ) ) + " - Thread ( " + thread_number + " ) stopped" );

	    }
	    
	}
	
}
