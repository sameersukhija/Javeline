package com.lumata.e4o.notification.dialogmanager;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.e4o.notification.dialogmanager.ActiveMQ;

public class SMSInsertMTFeedback {

	private static final Logger logger = LoggerFactory.getLogger( SMSInsertMTFeedback.class );

	private final long FEEDBACK_TIMEOUT = 300000;
	private final long FEEDBACK_POLLING = 5000;
	
	private NetworkEnvironment env;
	private ActiveMQ activeMQ;
	
	/* 	Initialize Environment */
	@Parameters({"environment", "activeMQService" })
	@BeforeSuite
	public void init( @Optional("E4O_VM") String environment, @Optional("dm") String activeMQService ) throws NetworkEnvironmentException, JMSException  {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		activeMQ = new ActiveMQ( env.getActiveMQService( activeMQService ) );				
	
	}

	@Test( enabled = true )
	public void insertMTFeedback() throws JMSException, ParseException, SQLException {
		
		long spentTime = 0;
		
		List<Message> messageList = null;
		
		while( messageList == null || messageList.size() <= 0 || spentTime <= FEEDBACK_TIMEOUT ) {
		
			messageList = activeMQ.readScheduledMessages();
			
			spentTime = spentTime + FEEDBACK_POLLING;
			
		}
		
		if( messageList != null || messageList.size() > 0 ) {
			
			activeMQ.addMTFeedback( env.getDataSource( "jmailer" ), messageList );
						
		}
		
	}
	
}
