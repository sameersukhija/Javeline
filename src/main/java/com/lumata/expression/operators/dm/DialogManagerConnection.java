package com.lumata.expression.operators.dm;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.Environment;
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
			
			ObjectMessage objMessage = (ObjectMessage) scheduled;
			SmsNotification dmMessageRead = (SmsNotification) objMessage.getObject();
			smsList.add(dmMessageRead);
		}
		
		return smsList;
	}
	
	public static void insertIntoMt(Mysql mysql, SmsNotification sms) throws Exception {
		
		String mtDate = new SimpleDateFormat("yyyyMMdd").format(sms.getSendingDate());
		
		// ----------------------------------------------
		// Check if table exist or create
		// ----------------------------------------------
		
		ResultSet rs = mysql.execQuery("SELECT count(*) FROM mt" + mtDate);
		
		if (rs == null) {
			mysql.execUpdate("CREATE TABLE `mt" + mtDate + "` (`code` int(11) NOT NULL,`date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,`acked` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',`delivered` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',`user` varchar(16) NOT NULL,`service` varchar(64) NOT NULL,`ip` varchar(15) NOT NULL,`phone` varchar(50) NOT NULL,`sender` varchar(20) NOT NULL,`message` varchar(255) NOT NULL,`promoter` varchar(32) NOT NULL,`id` varchar(80) NOT NULL,`id_obj` varchar(25) NOT NULL,`status` int(11) NOT NULL,`ack` varchar(50) NOT NULL,`error` varchar(255) NOT NULL,`type` varchar(4) NOT NULL,`tag` varchar(15) NOT NULL,`operator` varchar(16) NOT NULL,`rights` varchar(128) DEFAULT NULL,`notes` varchar(128) NOT NULL,`autotimestamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',PRIMARY KEY (`code`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
		}
		
		// ----------------------------------------------
		// Manual increment 'code'
		// ----------------------------------------------
		
		rs = mysql.execQuery("SELECT max(code) FROM mt" + mtDate);
		Integer code = 0;
		
		while (rs.next()) {
			code = rs.getInt(1);
			
			if (code == null) {
				code = 1;
			} else {
				code = rs.getInt(1) + 1;
			}
		}
		
		// ----------------------------------------------
		// Insert MT record
		// ----------------------------------------------
		
		String sql = "INSERT INTO `mt" + mtDate + "`(`code`,`date`,`acked`,`delivered`,`user`,`service`,`ip`,`phone`,`sender`,`message`,`promoter`,`id`,`id_obj`,`status`,`ack`,`error`,`type`,`tag`,`operator`,`rights`,`notes`,`autotimestamp`)"
				+ "VALUES ("+code+",NOW(),NOW(),DATE_ADD(NOW(), INTERVAL 2 HOUR),'user','e4O','10.120.8.31','+39"+sms.getRecipient()+"','"+sms.getSenderName()+"','"+sms.getTextMessage()+"','E4O','','',0,UUID(),'','SMS','MAP2_LMBLOX','1','','TransID=608c3d87&notificationId="+sms.getUniqueNotificationId()+"',now())";
		
		mysql.execUpdate(sql);
	}
	
	public static void main(String[] args) throws Exception {
		
		Environment env = new Environment("input/environments", "e4o_qa", IOFileUtils.IOLoadingType.RESOURCE);
		Mysql mysql = new Mysql(env.getDataSource("jmailer_it"));
		
		/* Test
		SmsNotification sms = new SmsNotification(null);
		sms.setSendingDate(new Date());
		sms.setRecipient("444555666");
		sms.setSenderName("name");
		sms.setTextMessage("message");
		*/
		
		for (SmsNotification sms : readFromScheduled()) {
			insertIntoMt(mysql, sms);
		}
	}
}
