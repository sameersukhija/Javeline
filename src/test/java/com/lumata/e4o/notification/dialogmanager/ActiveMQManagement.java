package com.lumata.e4o.notification.dialogmanager;

import java.sql.SQLException;
import java.text.ParseException;

import javax.jms.JMSException;

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

// TODO
public class ActiveMQManagement {

	private static final Logger logger = LoggerFactory.getLogger( ActiveMQManagement.class );
	
	NetworkEnvironment env;
	ActiveMQ activeMQ;
	
	/* 	Initialize Environment */
	@Parameters({"environment", "activeMQService" })
	@BeforeSuite
	public void init( @Optional("E4O_VM") String environment, @Optional("dm") String activeMQService ) throws NetworkEnvironmentException, JMSException  {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		activeMQ = new ActiveMQ( env.getActiveMQService( activeMQService ) );				
	
	}
	
	@Test( priority = 1, enabled = false )
	public void writeScheduledMessage() throws JMSException, ParseException {
		
//		Message message = activeMQ.createMessage( "393669393643", 2L, 1L, "new test for sms message" );
//		
//		activeMQ.writeScheduledMessage( env.getDataSource( "jmailer_it" ), message, "1.SMS.1" );
				
	}
		
	@Test( priority = 2, enabled = true )
	public void insertMTFeedback() throws JMSException, ParseException, SQLException {
		
//		List<Message> messageList = activeMQ.readScheduledMessages();
//	
//		activeMQ.addMTFeedback( env.getDataSource( "jmailer_it" ), messageList );
				
	}
	
}
