package com.g4s.common.testing.network;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailClient {
   
	private static final Logger logger = LoggerFactory.getLogger( MailClient.class );
	
	private enum MailProperty {
		
		protocol("mail.transport.protocol"),
		host("mail.smtp.host"),
		port("mail.smtp.port"),
		starttlsEnabled("mail.smtp.starttls.enable"),
		authorizationRequired("mail.smtp.auth"),
		user("mail.smtp.user");
		
		private String value;
		
		MailProperty( String value ) {
			
			this.value = value;
			
		}
		
		public String value() {
			
			return this.value;
			
		}
		
	}
	
	/**
	 * Mail properties
	 */
	private Properties mailProperties;
		
	/**
	 * Mail session
	 */
	private Session session;
	
	/**
	 * Mail message
	 */
	private MimeMessage message;
		
	/**
	 * Sender
	 */
	private String fromRecipient;
	
	/**
	 * Recivier
	 */
	private String toRecipient;
	
	/**
	 * Reciviers
	 */
	private String[] toRecipients;
	
	/**
	 * host
	 */
	private String host;

	/**
	 * port
	 */
	private Integer port;
	
	/**
	 * STARTTLS configuration
	 */
	private Boolean starttlsEnabled;
	
	/**
	 * authorization configuration
	 */
	private Boolean authorizationRequired;
	
	/**
	 * user
	 */
	private String user;
	
	/**
	 * user
	 */
	private String password;
	
	
	public MailClient() {}
	
	public MailClient( Mail mail ) throws AddressException, MessagingException {
		
		setMailProperties( mail );
		
		if( mail.authorizationRequired() ) {
			
			setUser( mail.user() );
			
			setPassword( mail.password() );
			
			session = Session.getDefaultInstance( mailProperties, new javax.mail.Authenticator() {
			
				protected PasswordAuthentication getPasswordAuthentication() {
					  
					return new PasswordAuthentication( getUser(), getPassword() );
					
				}
			  	    
			});
			
		} else {
			
			session = Session.getDefaultInstance( mailProperties );
			
		}
		
		message = new MimeMessage( session );

		if( null != mail.fromRecipient() && !mail.fromRecipient().isEmpty() ) { this.setFromRecipient( mail.fromRecipient() ); }

		if( null != mail.toRecipient() && !mail.toRecipient().isEmpty() ) { this.setToRecipient( mail.toRecipient() ); }

		if( null != mail.toRecipients() && mail.toRecipients().length > 0 ) { this.setToRecipients( mail.toRecipients() ); }
		
	}
	
	public static MailClient getInstance( Mail mail ) throws AddressException, MessagingException {
		
		return new MailClient( mail );
		
	}
	
	public Properties getMailProperties() {
		
		return this.mailProperties;
		
	}

	public Session getSession() {
		
		return this.session;
		
	}

	public Message getMessage() {
		
		return this.message;
		
	}
	
	public String getFromRecipient() {
		
		return this.fromRecipient;
		
	}
	
	public String getToRecipient() {
		
		return this.toRecipient;
		
	}
	
	public String[] getToRecipients() {
		
		return this.toRecipients;
		
	}
	
	public String getHost() {
		
		return this.host;
		
	}

	public Integer getPort() {
		
		return this.port;
		
	}
	
	public Boolean getStarttlsEnabled() {
		
		return this.starttlsEnabled;
		
	}
	
	public Boolean getAuthorizationRequired() {
		
		return this.authorizationRequired;
		
	}
	
	public String getUser() {
		
		return this.user;
		
	}
	
	public String getPassword() {
		
		return this.password;
		
	}
	
	public MailClient setMailProperties( Mail mail ) {
		
		mailProperties = new Properties();
		
		for( MailProperty mp : MailProperty.values() ) {
			
			Method method;
			
			try {
			  
				method = mail.getClass().getMethod( mp.name() );
				
				mailProperties.put( mp.value(), method.invoke( mail ) );
							
			} catch ( 
						SecurityException | 
						NoSuchMethodException |
						IllegalAccessException | 
						IllegalArgumentException | 
						InvocationTargetException e
			) {
				
				logger.error( e.getMessage(), e );
				
			}
			
		}
		
		return this;
		
	}
	
	public MailClient setFromRecipient( String fromRecipient ) throws AddressException, MessagingException {
		
		this.fromRecipient = fromRecipient;
		
		message.setFrom( new InternetAddress( this.fromRecipient ) );
		
		return this;
		
	}
	
	public MailClient setToRecipient( String toRecipient ) throws AddressException, MessagingException {
		
		this.toRecipient = toRecipient;
		
		message.addRecipient( Message.RecipientType.TO, new InternetAddress( this.toRecipient ) );
		
		return this;
		
	}
	
	public MailClient setToRecipients( String[] toRecipients ) throws MessagingException {
		
		this.toRecipients = toRecipients;

		InternetAddress[] addresses = new InternetAddress[ toRecipients.length ];
		
		for( int r = 0; r < toRecipients.length; r++ ) {
			
			addresses[ r ] = new InternetAddress( toRecipients[ r ] ); 
			
		}
		
		message.setRecipients( Message.RecipientType.TO, addresses );
		
		return this;
		
	}
	
	public MailClient setHost( String host ) {
		
		this.host = host;
		
		return this;
		
	}

	public MailClient setPort( Integer port  ) {
		
		this.port = port;
		
		return this;
		
	}
	
	public MailClient setStarttlsEnabled( Boolean starttlsEnabled ) {
		
		this.starttlsEnabled = starttlsEnabled;
		
		return this;
		
	}
	
	public MailClient setAuthorizationRequired( Boolean authorizationRequired ) {
		
		this.authorizationRequired = authorizationRequired;
		
		return this;
		
	}
	
	public MailClient setUser( String user ) {
		
		this.user = user;
		
		return this;
		
	}
	
	public MailClient setPassword( String password ) {
		
		this.password = password;
		
		return this;
		
	}
		
	public MailClient send( String subject, String text ) throws MessagingException {
			
		logger.info( "Sending mail" );
		
		message.setSubject( subject );
				
		message.setContent( text, "text/html" );
		
		Transport.send( message );
		
		logger.info( "The mail has been sent with success" );
		
		return this;
		
	}
	
}