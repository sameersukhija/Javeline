package com.lumata.expression.operators.dm;

//import java.util.concurrent.ThreadPoolExecutor;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;

import com.lumatagroup.expression.commons.campaign.notification.domain.SmsNotification;

public class DialogManagerConnection {

	public static final String BROKER_URL = "tcp://10.120.44.26:61616";
	
	ActiveMQConnectionFactory connectionFactory;
	Connection connection;
	Session session;
	Destination destination;
	MessageProducer producer;
	
	public DialogManagerConnection( String connection_factory, String queue ) {
		
		this.setActiveMQConnectionFactory( connection_factory );
		
		this.setConnection();
		
		this.setSession();
		
		this.setDestination( queue );
		
		this.setProducer();
	
	}
	
	public ActiveMQConnectionFactory getActiveMQConnectionFactory() {
		return connectionFactory;
	}
	
	public Connection getConnection() {
		
		return connection;
		
	}
	
	public Session getSession() {
		
		return session;
		
	}
	
	public Destination getDestination() {
		
		return destination;
		
	}
	
	public MessageProducer getProducer() {
		
		return producer;
		
	}	
	
	public void setActiveMQConnectionFactory( String connection_factory ) {
		connectionFactory = new ActiveMQConnectionFactory( connection_factory );
		//connectionFactory.setMaxThreadPoolSize(1000);
		//connectionFactory.setRejectedTaskHandler(new ThreadPoolExecutor.CallerRunsPolicy());
	}
	
	public void setConnection() {
		
		try { 
		
			connection = connectionFactory.createConnection(); 
			connection.start();
		
		} catch( JMSException e ) {}
	
	}
	
	public void setSession() {
		
		try { 
		
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		} catch( JMSException e ) {}
	
	}
	
	public void setDestination( String queue ) {
		
		try { 
		
			destination = session.createQueue( queue );
		
		} catch( JMSException e ) {}
	
	}
	
	public void setProducer() {
		
		try { 
		
			producer = session.createProducer( destination );
		
		} catch( JMSException e ) {}
	
	}	
	
	public void close() {
		
		try {
			
			this.session.close();
			
			this.connection.close();
			
		} catch( JMSException e ) {}
			
	}
	
	public static void writeScheduled() throws Exception {
		
		// http://activemq.apache.org/hello-world.html
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
				
		Connection connection = connectionFactory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("TEST.FOO");

		connection.start();

		MessageProducer producer = session.createProducer(destination);
		TextMessage message = session.createTextMessage("test msg");
		long time = 60*60*24 * 1000; // 24h
		message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, time);
		producer.send(message);
		
		System.out.println("-- END --");
	}
	
	public static void readFromScheduled() throws Exception {
		
		// http://activemq.apache.org/hello-world.html
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
		
		// ---------------------------------------------------------------------------
		// http://timbish.blogspot.it/2010/10/enhanced-jms-scheduler-in-activemq.html
		// ---------------------------------------------------------------------------
		
		Connection connection = connectionFactory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination requestBrowse = session.createTopic(ScheduledMessage.AMQ_SCHEDULER_MANAGEMENT_DESTINATION);
		Destination browseDest = session.createTemporaryQueue();
		
		MessageConsumer browser = session.createConsumer(browseDest);

		connection.start();
		
		MessageProducer producer = session.createProducer(requestBrowse);
		Message request = session.createMessage();
		request.setStringProperty(ScheduledMessage.AMQ_SCHEDULER_ACTION, ScheduledMessage.AMQ_SCHEDULER_ACTION_BROWSE);
		request.setJMSReplyTo(browseDest);
		producer.send(request);
	
		Message scheduled = null;
		
		while (null != (scheduled = browser.receive(100))) {
			System.out.println(scheduled);
			
			// http://git.lumata.int/projects/MRM/repos/expression-dialog/browse/dialog-manager/src/main/java/com/lumatagroup/expression/dialog/service/MessageQueueControllerImpl.java?at=refs%2Fheads%2Fdev
			/*Message remove = session.createMessage();
			remove.setStringProperty(ScheduledMessage.AMQ_SCHEDULER_ACTION, ScheduledMessage.AMQ_SCHEDULER_ACTION_REMOVE);
			remove.setStringProperty(ScheduledMessage.AMQ_SCHEDULED_ID, scheduled.getStringProperty(ScheduledMessage.AMQ_SCHEDULED_ID));
			producer.send(remove);*/
			
			//TextMessage message = (TextMessage) scheduled;
			//System.out.println("Text: " + message.getText());
			
			//SmsNotification message = (SmsNotification) scheduled;
			//System.out.println("UniqueNotificationId: " + message.getUniqueNotificationId());
			
			ObjectMessage objMessage = (ObjectMessage) scheduled;
			SmsNotification dmMessageRead = (SmsNotification) objMessage.getObject();
			System.out.println("UniqueNotificationId: " + dmMessageRead.getUniqueNotificationId());
		}
		
		System.out.println("-- END --");
	}
}
