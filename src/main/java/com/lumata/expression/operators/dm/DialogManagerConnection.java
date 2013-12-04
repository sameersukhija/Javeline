package com.lumata.expression.operators.dm;

import java.util.concurrent.ThreadPoolExecutor;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class DialogManagerConnection {

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
		connectionFactory.setMaxThreadPoolSize(1000);
		connectionFactory.setRejectedTaskHandler(new ThreadPoolExecutor.CallerRunsPolicy());
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
	
}
