package com.lumata.e4o.notification.dialogmanager;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.lumata.common.testing.log.Log;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCActiveMQ;
import com.lumata.e4o.testing.common.TCMysqlJMailerMaster;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;


@TCOwners(@TCOwner(name = "Arcangelo Di Pasquale", email = "arcangelo.dipasquale@lumatagroup.com"))
@TCMysqlJMailerMaster
@TCActiveMQ( servers={"dm1"} )
public class SMSInsertMTFeedback extends ParentTestCase {

	private final long FEEDBACK_TIMEOUT = 600000;
	private final long FEEDBACK_POLLING = 1000;

	@Test( enabled = true )
	public void insertMTFeedback() throws JMSException, ParseException, SQLException {
				
		Reporter.log( Log.GETTING.createMessage( this.getClass().getSimpleName(), "sms feedback messages" ), LOG_TO_STD_OUT );
				
		long spentTime = 0;
		
		List<Message> messageList = null;
		
		while( ( messageList == null || messageList.size() <= 0 ) && spentTime <= FEEDBACK_TIMEOUT ) {
		
			messageList = activemq.get("dm1").readScheduledMessages();
			
			spentTime = spentTime + FEEDBACK_POLLING;
			
			try { Thread.sleep( FEEDBACK_POLLING ); } catch( InterruptedException e ) {}
			
			//Reporter.log( Log.GETTING.createMessage( this.getClass().getSimpleName(), "feedback sms messages ( elapsed time: " + spentTime + " ms ) " ), LOG_TO_STD_OUT );
			
		}
		
		Reporter.log( Log.GETTING.createMessage( this.getClass().getSimpleName(), "insert sms feedback in the jmailer daily table" ), LOG_TO_STD_OUT );
		
		Boolean generateFeedbackError = true;
		
		if( messageList != null && messageList.size() > 0 ) {
			
			activemq.get("dm1").addMTFeedback( mysqlJMailerMaster, messageList, generateFeedbackError );
						
		}
		
	}
	
}
