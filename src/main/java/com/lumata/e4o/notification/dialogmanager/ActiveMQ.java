package com.lumata.e4o.notification.dialogmanager;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.database.MysqlUtils;
import com.lumata.common.testing.system.DataSource;
import com.lumata.common.testing.system.Service;
import com.lumatagroup.dialogmanager.internal.commons.driver.PollingRequest;
import com.lumatagroup.dialogmanager.commons.message.DialogManagerMessage;
import com.lumatagroup.dialogmanager.commons.message.MessageContent;
import com.lumatagroup.dialogmanager.driver.RemoteQueuePollingNotificationInfo;



public class ActiveMQ {

	ActiveMQConnectionFactory connectionFactory;
	Connection connection;
	Session session;
	Destination producerDestination;
	Destination consumerDestination;
	MessageProducer producer;
	MessageConsumer consumer;
	String currentQueue;
	String brokenUrl;
	DialogManagerSMS dms;
		
	public enum SmsNotificationField {
		date, recipient, senderName, textMessage;
	}
	
	public enum DestinationType {
		Queue, TemporaryQueue, Topic;
	}

	public ActiveMQ( Service activeMQ ) throws JMSException {
		
		setActiveMQConnectionFactory( activeMQ );
	
	}
	
	public ActiveMQ( String connection_factory ) throws JMSException {
		
		setActiveMQConnectionFactory( connection_factory );
	
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
	
	public Destination getProducerDestination() {		
		return producerDestination;		
	}

	public Destination getConsumerDestination() {		
		return consumerDestination;		
	}

	public MessageProducer getProducer() {		
		return producer;		
	}	
	
	public String getBrokenUrl() {
		return brokenUrl;
	}
	
	public void setBrokenUrl( String url ) {
		brokenUrl = url;
	}
	
	public void setActiveMQConnectionFactory( String connection_factory ) throws JMSException {
		
		setBrokenUrl( connection_factory );
		
		connectionFactory = new ActiveMQConnectionFactory( connection_factory );
		
		//connectionFactory.setMaxThreadPoolSize(1000);
		//connectionFactory.setRejectedTaskHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		
		this.setConnection();
		
		this.setSession();
	
	}

	public void setActiveMQConnectionFactory( Service activeMQ ) throws JMSException {
		setActiveMQConnectionFactory( "tcp://" + activeMQ.getHostAddress() + ":" + activeMQ.getHostPort() );
	}

	public void setConnection() throws JMSException {
		
		connection = connectionFactory.createConnection(); 
		connection.start();
		
	}
	
	public void setSession() throws JMSException {
		
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
	}
	
	public Destination getDestination( DestinationType destinationType, String queue ) throws JMSException {
		
		Destination destination = null;
		
		switch( destinationType ) {
			
			case Queue: {
				destination = session.createQueue( queue );
				break;
			}
			case TemporaryQueue: {
				destination = session.createTemporaryQueue();
				break;
			}
			case Topic: {
				destination = session.createTopic( queue );
				break;
			}
		
		}
				
		return destination;
		
	}

	public void setProducer( DestinationType destinationType, String queue ) throws JMSException {
		
		producerDestination = getDestination( destinationType, queue );
		
		producer = session.createProducer( producerDestination );
		
	}
	
	public void setConsumer( DestinationType destinationType, String queue ) throws JMSException {
		
		consumerDestination = getDestination( destinationType, queue );
		
		consumer = session.createConsumer( consumerDestination );
		
	}	
	
	public void close() throws JMSException {
		
		this.session.close();
		
		this.connection.close();
			
	}
	
	public Message createMessage( String msisdn, Long messageId, Long tenantId, MessageContent messageContent ) throws JMSException, ParseException {
		
		return createMessage( msisdn, messageId, tenantId, messageContent, 0 );
		
	}
	
	public Message createMessage( String msisdn, Long messageId, Long tenantId, MessageContent messageContent, long scheduled_delay ) throws JMSException, ParseException {
		
		dms = new DialogManagerSMS( msisdn, messageId, tenantId, messageContent );
		
		Message message = session.createObjectMessage( dms.getMessage() );
		
		if( scheduled_delay > 0 ) { message.setLongProperty( ScheduledMessage.AMQ_SCHEDULED_DELAY, scheduled_delay ); }
			
		return message;
		
	}
	
	public Message createRequest() throws JMSException {
	
		Message request = session.createMessage();
	
		request.setStringProperty( ScheduledMessage.AMQ_SCHEDULER_ACTION, ScheduledMessage.AMQ_SCHEDULER_ACTION_BROWSE );
	
		request.setJMSReplyTo( consumerDestination );
		
		return request;
	
	}
	
	public void writeScheduledMessage( Message message, String queue ) throws JMSException {
		
		setProducer( DestinationType.Queue, queue );
		
		producer.send( message );
		
	}
	
	public void writeScheduledMessage( DataSource jmailerDS, Message message, String queue ) throws JMSException {
		
		String mtTable = getMtTableName( message );
		
		Mysql mysql = new Mysql( jmailerDS );
		
		if( !MysqlUtils.isTable( mtTable, mysql ) ) { createMtTable( mysql, mtTable ); }
		
		mysql.close();
		
		writeScheduledMessage( message, queue );
		
	}

	/**
	 * 
	 * Create jmailer daily table using message date
	 * 
	 * @param message
	 * @return mt table name
	 * @throws JMSException
	 */
	public String getMtTableName( Message message ) throws JMSException {
		
		Calendar sendingDate = Calendar.getInstance();
		
		sendingDate.setTimeInMillis( message.getJMSTimestamp() );
		
		return getMtTableName( sendingDate );
	
	}

	/**
	 * 
	 * Create jmailer daily table using current date
	 * 
	 * @return mt table name
	 * @throws JMSException
	 */
	public String getMtTableName() {
		
		return getMtTableName( Calendar.getInstance() );
	
	}

	/**
	 * 
	 * Create jmailer daily table
	 * 
	 * @param date
	 * @return mt table name
	 * @throws JMSException
	 */
	public String getMtTableName( Calendar date ) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				
		String mtDate = sdf.format( date.getTime() );
		
		return "mt" + mtDate;
	
	}
	
	public void createMtTable( Mysql mysql, String mtTableName ) {
		
		mysql.execUpdate("CREATE TABLE IF NOT EXISTS `" + mtTableName + "` (`code` int(11) NOT NULL,`date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,`acked` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',`delivered` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',`user` varchar(16) NOT NULL,`service` varchar(64) NOT NULL,`ip` varchar(15) NOT NULL,`phone` varchar(50) NOT NULL,`sender` varchar(20) NOT NULL,`message` varchar(255) NOT NULL,`promoter` varchar(32) NOT NULL,`id` varchar(80) NOT NULL,`id_obj` varchar(25) NOT NULL,`status` int(11) NOT NULL,`ack` varchar(50) NOT NULL,`error` varchar(255) NOT NULL,`type` varchar(4) NOT NULL,`tag` varchar(15) NOT NULL,`operator` varchar(16) NOT NULL,`rights` varchar(128) DEFAULT NULL,`notes` varchar(128) NOT NULL,`autotimestamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',PRIMARY KEY (`code`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
				
	}
	
	public List<Message> readScheduledMessages() throws JMSException {
		
		List<Message> messageList = new ArrayList<Message>();
		
		setConsumer( DestinationType.TemporaryQueue, null );
		
		setProducer( DestinationType.Topic, ScheduledMessage.AMQ_SCHEDULER_MANAGEMENT_DESTINATION ); 
		
		Message request = createRequest();
			
		producer.send( request );
	
		Message scheduledMessage = null;
		
		while( null != ( scheduledMessage = consumer.receive( 100 ) ) ) {
			
			messageList.add( scheduledMessage );			
		
		}
		
		return messageList;
		
	}
	
	public void addMTFeedback( Mysql mysql, List<Message> messageList ) throws JMSException, SQLException {
				
		for( int m = 0; m < messageList.size(); m++ ) {
			
			Message message = messageList.get( m );
					
			ObjectMessage objMessage = (ObjectMessage) message;
			
			if( objMessage.getObject() instanceof PollingRequest ) {
			
				PollingRequest pr = (PollingRequest)objMessage.getObject();
			
				RemoteQueuePollingNotificationInfo sms = (RemoteQueuePollingNotificationInfo)pr.getRequest();
			
				String mtTable = getMtTableName( message );
				
				if( !MysqlUtils.isTable( mtTable, mysql ) ) { createMtTable( mysql, mtTable ); }
				
				Integer codeId = ( MysqlUtils.getMaxID( mtTable, "code", mysql ) + 1 );
				
				StringBuilder query = new StringBuilder();
				
				query.append( "INSERT INTO " )
						.append( mtTable )
						.append( " ( code, date, acked, delivered, user, service, ip, phone, sender, message, promoter, id, id_obj, status, ack, error, type, tag, operator, rights, notes, autotimestamp ) " )
						.append( "SELECT " )
						.append( codeId )
						.append( ", NOW(), NOW(), DATE_ADD( NOW(), INTERVAL 2 HOUR ), 'user', 'e4O', '10.120.8.31', '+" )
						.append( sms.getSmsNotification().getRecipient() )
						.append( "', '" )
						.append( sms.getSmsNotification().getSenderName() )
						.append( "', '" )
						.append( sms.getSmsNotification().getTextMessage() )
						.append( "', 'E4O', '', '', 0, UUID(), '', 'SMS', 'MAP2_LMBLOX', '1', '', 'TransID=608c3d87&notificationId=" )
						.append( sms.getUniqueNotificationId() ) 
						.append( "', NOW() FROM DUAL WHERE NOT EXISTS ( SELECT notes FROM " )
						.append( mtTable )
						.append( " WHERE notes = 'TransID=608c3d87&notificationId=" )
						.append( sms.getSmsNotification().getUniqueNotificationId() )
						.append( "' ) LIMIT 1;" 
				);
				
				System.out.println( query );
				
				mysql.execUpdate( query.toString() );
				
			}
			
		}
		
		mysql.close();
		
	}
	
	public void deleteScheduledMessages( List<Message> messageList ) throws JMSException {
		
		for( int m = 0; m < messageList.size(); m++ ) {
			
			try {
				
				Message message = messageList.get( m );
				
				deleteScheduledMessage( message );
			
			} catch( Exception e ) {
				
				System.out.println( e.getMessage() );
			
			}
		}
		
	}
	
	public void deleteScheduledMessage( Message message ) throws JMSException {
							
		message.setStringProperty(ScheduledMessage.AMQ_SCHEDULER_ACTION, ScheduledMessage.AMQ_SCHEDULER_ACTION_REMOVE);
		
		message.setStringProperty(ScheduledMessage.AMQ_SCHEDULED_ID, message.getStringProperty(ScheduledMessage.AMQ_SCHEDULED_ID));
		
		setProducer( DestinationType.Topic, ScheduledMessage.AMQ_SCHEDULER_MANAGEMENT_DESTINATION );
		
		producer.send( message );
		
	}
 	
	public DialogManagerMessage getDialogManagerMessage( Message message ) throws JMSException {
		
		ObjectMessage objMessage = (ObjectMessage)message;
		
		if( objMessage.getObject() instanceof DialogManagerMessage ) {
			
			return (DialogManagerMessage) objMessage.getObject();
		
		} else { return null; }
		
	}

	
/*	

		// http://git.lumata.int/projects/MRM/repos/expression-dialog/browse/dialog-manager/src/main/java/com/lumatagroup/expression/dialog/service/MessageQueueControllerImpl.java?at=refs%2Fheads%2Fdev
		/*Message remove = session.createMessage();
		remove.setStringProperty(ScheduledMessage.AMQ_SCHEDULER_ACTION, ScheduledMessage.AMQ_SCHEDULER_ACTION_REMOVE);
		remove.setStringProperty(ScheduledMessage.AMQ_SCHEDULED_ID, scheduled.getStringProperty(ScheduledMessage.AMQ_SCHEDULED_ID));
		producer.send(remove);
		
		//TextMessage message = (TextMessage) scheduled;
		//System.out.println("Text: " + message.getText());
		
		//SmsNotification message = (SmsNotification) scheduled;
		//System.out.println("UniqueNotificationId: " + message.getUniqueNotificationId());
	
*/	
	
/*	
	public static List<SmsNotification> readFromScheduled() throws Exception {
		List<SmsNotification> smsList = new ArrayList<SmsNotification>();
		
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
/*			
			ObjectMessage objMessage = (ObjectMessage) scheduled;
			SmsNotification dmMessageRead = (SmsNotification) objMessage.getObject();
			smsList.add(dmMessageRead);
		}
		
		return smsList;
	}
*/	

	
	// TODO
//	public static void insertIntoMt( Mysql mysql, PollingRequest pr ) throws Exception {
//		
//		RemoteQueuePollingNotificationInfo sms = (RemoteQueuePollingNotificationInfo)pr.getRequest();
//		
//		String mtDate = new SimpleDateFormat("yyyyMMdd").format(sms.getSmsNotification().getSendingDate());
//		
//		// ----------------------------------------------
//		// Check if table exist or create
//		// ----------------------------------------------
//		
//		ResultSet rs = mysql.execQuery( "SELECT count(*) FROM mt" + mtDate );
//		
//		if (rs == null) {
//			
//			mysql.execUpdate("CREATE TABLE `mt" + mtDate + "` (`code` int(11) NOT NULL,`date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,`acked` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',`delivered` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',`user` varchar(16) NOT NULL,`service` varchar(64) NOT NULL,`ip` varchar(15) NOT NULL,`phone` varchar(50) NOT NULL,`sender` varchar(20) NOT NULL,`message` varchar(255) NOT NULL,`promoter` varchar(32) NOT NULL,`id` varchar(80) NOT NULL,`id_obj` varchar(25) NOT NULL,`status` int(11) NOT NULL,`ack` varchar(50) NOT NULL,`error` varchar(255) NOT NULL,`type` varchar(4) NOT NULL,`tag` varchar(15) NOT NULL,`operator` varchar(16) NOT NULL,`rights` varchar(128) DEFAULT NULL,`notes` varchar(128) NOT NULL,`autotimestamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',PRIMARY KEY (`code`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
//		
//		}
//		
//		// ----------------------------------------------
//		// Manual increment 'code'
//		// ----------------------------------------------
//		
//		rs = mysql.execQuery("SELECT max(code) FROM mt" + mtDate);
//		Integer code = 0;
//		
//		while (rs.next()) {
//			code = rs.getInt(1);
//			
//			if (code == null) {
//				code = 1;
//			} else {
//				code = rs.getInt(1) + 1;
//			}
//		}
//		
//		// ----------------------------------------------
//		// Insert MT record
//		// ----------------------------------------------
//			
//		String sql = "INSERT INTO `mt" + mtDate + "`(`code`,`date`,`acked`,`delivered`,`user`,`service`,`ip`,`phone`,`sender`,`message`,`promoter`,`id`,`id_obj`,`status`,`ack`,`error`,`type`,`tag`,`operator`,`rights`,`notes`,`autotimestamp`)"
//				+ "VALUES ("+code+",NOW(),NOW(),DATE_ADD(NOW(), INTERVAL 2 HOUR),'user','e4O','10.120.8.31','+39"+sms.getRecipient()+"','"+sms.getSenderName()+"','"+sms.getTextMessage()+"','E4O','','',0,UUID(),'','SMS','MAP2_LMBLOX','1','','TransID=608c3d87&notificationId="+sms.getUniqueNotificationId()+"',now())";
//		
//		mysql.execUpdate(sql);
//	}
	
//
//	/*
//	public static void main(String[] args) throws Exception {
//		
//		Environment env = new Environment("input/environments", "e4o_qa", IOFileUtils.IOLoadingType.RESOURCE);
//		Mysql mysql = new Mysql(env.getDataSource("jmailer_it"));
//		
//		/* Test
//		SmsNotification sms = new SmsNotification(null);
//		sms.setSendingDate(new Date());
//		sms.setRecipient("444555666");
//		sms.setSenderName("name");
//		sms.setTextMessage("message");
//		*/
//	/*	
//		for (SmsNotification sms : readFromScheduled()) {
//			insertIntoMt(mysql, sms);
//		}
//	}
//	*/
}
