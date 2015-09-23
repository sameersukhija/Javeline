package com.lumata.e4o.notification.dialogmanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lumatagroup.dialogmanager.commons.message.DialogManagerMessage;
import com.lumatagroup.dialogmanager.commons.message.ExternalIdentifier;
import com.lumatagroup.dialogmanager.commons.message.Identifier;
import com.lumatagroup.dialogmanager.commons.message.MessageContent;
import com.lumatagroup.dialogmanager.commons.message.Receiver;
import com.lumatagroup.dialogmanager.commons.message.Sender;
import com.lumatagroup.dialogmanager.commons.message.Time;
import com.lumatagroup.dialogmanager.commons.message.TimeWindow;
import com.lumatagroup.dialogmanager.commons.message.Variable;
import com.lumatagroup.dialogmanager.commons.message.sms.SMSMessageContent;
import com.lumatagroup.dialogmanager.commons.message.sms.SMSReceiver;
import com.lumatagroup.dialogmanager.commons.message.sms.SMSSender;

// TODO
public final class DialogManagerSMS {

    public static final String          SMS_RECEIVER              = "";
	public static final String          SMS_SENDER                = "Lumata";
	public static final int             CAMPAIGN_ID         	= 1;
	public static final String          TENANT_ID                 = "1";
	public static final int             DRIVER_ID                 = 1;
    public static final String          MESSAGE_ID                = "1235";
	public static final String 			EXISTENT_MESSAGE_ID 	  = "0001";
	public static final String          VARIABLE_FIRST_NAME_KEY   = "###first_name###";
	public static final String          VARIABLE_LAST_NAME_KEY    = "###last_name###";
	public static final String          VARIABLE_FIRST_NAME       = "firstName";
	public static final String          VARIABLE_LAST_NAME        = "lastName";
	public static final String          VARIABLE_FIRST_NAME_VALUE = "John";
	public static final String          VARIABLE_LAST_NAME_VALUE  = "Doe";
	public static final String          VARIABLE_CALLBACK_URL     = "callbackURL";
	public static final String          DATE_FORMAT               = "yyyy-MM-DD HH:mm:ss";
	public static final String          NOTIF_LOGS_ID_KEY         = "id";
	public static final String          NOTIF_LOGS_CATEGORY_KEY   = "category";
	public static final String          NOTIF_LOGS_ACTION_KEY     = "action";
	public static final String          NOTIF_LOGS_FEATURE_ID_KEY = "feature_id";
	public static final String          NOTIF_LOGS_IDENTIFIER_KEY = "identifier";
	public static final String          NOTIF_LOGS_TEXT_KEY       = "text";
	public static final String          NOTIF_LOGS_TENANT_ID_KEY  = "tenant_id";
	public static final String          NOTIF_LOGS_ID_RECEIPT_KEY = "id_receipt";
	public static final String          NOTIF_LOGS_ID             = "1";
	public static final String          NOTIF_LOGS_CATEGORY       = "SMS";
    public static final String          NOTIF_LOGS_ACTION         = "10";
	public static final String          NOTIF_LOGS_FEATURE_ID     = String.valueOf(CAMPAIGN_ID);
	public static final String          NOTIF_LOGS_IDENTIFIER     = SMS_RECEIVER;
	public static final String          NOTIF_LOGS_TEXT           = "textMessage";
	public static final String          NOTIF_LOGS_TENANT_ID      = String.valueOf(TENANT_ID);
	public static final String          NOTIF_LOGS_ID_RECEIPT     = "";
	public static final String          MESSAGE_TEXT              = "Text Message";
	
	public static final MessageContent 	MESSAGE_CONTENT 		  = new SMSMessageContent( "Text Message", false ); 
 	
	private DialogManagerMessage dmMessage;
	private Identifier<Long, Long> identifier;
	private String msisdn;
	private Receiver receiver;
	private Sender sender;
	private List<TimeWindow>      timeWindowList      = new ArrayList<TimeWindow>();
	private Map<String, Variable> variables           = new HashMap<String, Variable>();
	private Map<String, String>   notifLogs           = new HashMap<String, String>();
	private Calendar currentDate;
	private Date maxExpirationTimeStamp;

	public DialogManagerSMS( String msisdn, Long messageId, Long tenantId ) throws ParseException {
		
		this( msisdn, messageId, tenantId, MESSAGE_CONTENT );

	}
	
	public DialogManagerSMS( String msisdn, Long messageId, Long tenantId, MessageContent messageContent ) throws ParseException {
			
		init( msisdn, messageId, tenantId );
		
		dmMessage = new DialogManagerMessage (
							identifier, 
							receiver, 
							sender, 
							messageContent,
							maxExpirationTimeStamp, 
							notifLogs, 
							timeWindowList
		);
		

    
		dmMessage.setConsumerCurrentTimestamp( currentDate.getTime() );
		
		fillMetaInfo();
		
	}
	
	private void init( String msisdn, Long messageId, Long tenantId ) throws ParseException {
		
		currentDate = Calendar.getInstance();
				
		Calendar expirationDate = Calendar.getInstance();
		expirationDate.add( Calendar.YEAR, 1 );
		
		SimpleDateFormat sdf = new SimpleDateFormat( DATE_FORMAT );
		
		maxExpirationTimeStamp = sdf.parse( sdf.format( expirationDate.getTime() ) );

		setMsisdn( msisdn );
		
		setIdentifier( messageId, tenantId );
		
		setReceiver();
		
		setSender();	

		fillNotifLog();
		
		fillTimeWindowList();
			
	}
	
	private void fillNotifLog() {
		notifLogs.clear();
		notifLogs.put(NOTIF_LOGS_ID_KEY, NOTIF_LOGS_ID);
		notifLogs.put(NOTIF_LOGS_CATEGORY_KEY, NOTIF_LOGS_CATEGORY);
		notifLogs.put(NOTIF_LOGS_ACTION_KEY, NOTIF_LOGS_ACTION);
		notifLogs.put(NOTIF_LOGS_FEATURE_ID_KEY, NOTIF_LOGS_FEATURE_ID);
		notifLogs.put(NOTIF_LOGS_IDENTIFIER_KEY, NOTIF_LOGS_IDENTIFIER);
		notifLogs.put(NOTIF_LOGS_TEXT_KEY, NOTIF_LOGS_TEXT);
		notifLogs.put(NOTIF_LOGS_TENANT_ID_KEY, NOTIF_LOGS_TENANT_ID);
		notifLogs.put(NOTIF_LOGS_ID_RECEIPT_KEY, NOTIF_LOGS_ID_RECEIPT);
	}

	private void fillTimeWindowList() throws ParseException {
		
		timeWindowList.clear();
		
		SimpleDateFormat sdf = new SimpleDateFormat( DATE_FORMAT );
		
		Calendar beginDate = Calendar.getInstance();
		beginDate.set( currentDate.get( Calendar.YEAR ) - 1, currentDate.get( Calendar.MONTH ), currentDate.get( Calendar.DATE), 8, 0, 0 );
		
		Calendar endDate = Calendar.getInstance();
		endDate.set( currentDate.get( Calendar.YEAR ) - 1, currentDate.get( Calendar.MONTH ), currentDate.get( Calendar.DATE), 20, 0, 0 );

		TimeWindow tw1 = new TimeWindow( 
								new Time( sdf.parse( sdf.format( beginDate.getTime() ) ) ), 
								new Time( sdf.parse( sdf.format( endDate.getTime() ) ) ), 
								null
		);
		
		beginDate.set( Calendar.YEAR, currentDate.get( Calendar.YEAR ) + 1 );
		endDate.set( Calendar.YEAR, currentDate.get( Calendar.YEAR ) + 1 );

		TimeWindow tw2 = new TimeWindow( 
				new Time( sdf.parse( sdf.format( beginDate.getTime() ) ) ), 
				new Time( sdf.parse( sdf.format( endDate.getTime() ) ) ), 
				null
		);
		
		timeWindowList.add( tw1 );
		
		timeWindowList.add( tw2 );
	
	}

	@SuppressWarnings("unused")
	private void fillVariables() throws ParseException {
		variables.clear();
		Date maxExpirationTimeStamp = new SimpleDateFormat(DATE_FORMAT).parse("2013-12-31 23:59:59");
		Variable v1 =
				new Variable(VARIABLE_FIRST_NAME_KEY, VARIABLE_FIRST_NAME_VALUE, null, null, maxExpirationTimeStamp,
						VARIABLE_CALLBACK_URL);
		Variable v2 =
				new Variable(VARIABLE_LAST_NAME_KEY, VARIABLE_LAST_NAME_VALUE, null, null, maxExpirationTimeStamp,
						VARIABLE_CALLBACK_URL);
		variables.put(VARIABLE_FIRST_NAME, v1);
		variables.put(VARIABLE_LAST_NAME, v2);
	}

	private void fillMetaInfo() {
		
		//DMM META INFO
		dmMessage.addMetainfo(DialogManagerMessage.META_INFO_CHANNEL_TYPE, NOTIF_LOGS_CATEGORY);
		dmMessage.addMetainfo(DialogManagerMessage.META_INFO_MODULE_ID, NOTIF_LOGS_ACTION);
		dmMessage.addMetainfo(DialogManagerMessage.META_INFO_FEATURE_ID, NOTIF_LOGS_FEATURE_ID);
		
		//DMM CRITERIA INFO
		dmMessage.addMetainfo(DialogManagerMessage.CRITERIA_INFO_TENANT_ID, identifier.getConvertedTenantId());
		dmMessage.addMetainfo(DialogManagerMessage.CRITERIA_INFO_MODULE_ID, NOTIF_LOGS_ACTION);
		dmMessage.addMetainfo(DialogManagerMessage.CRITERIA_INFO_FEATURE_ID, NOTIF_LOGS_FEATURE_ID);
		
	}
	
	public Identifier<Long, Long> getIdentifier(Long messageId, Long tenantId) {
		return identifier;
	}

	public DialogManagerMessage getMessage() {
		return dmMessage;
	}
	
	public void setMsisdn( String msisdn ) {
		this.msisdn = msisdn;		
	}

	public void setIdentifier( Long messageId, Long tenantId) {
		identifier = new ExternalIdentifier( messageId, null, new Integer(1), tenantId );		
	}
	
	public void setReceiver() {
		receiver = new SMSReceiver( msisdn );		
	}
	
	public void setSender() {
		sender = new SMSSender( SMS_SENDER );		
	}

}
