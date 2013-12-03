package com.lumata.expression.operators.dm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lumatagroup.expression.e4o.commons.jms.message.DialogManagerMessage;
import com.lumatagroup.expression.e4o.commons.jms.message.ExternalIdentifier;
import com.lumatagroup.expression.e4o.commons.jms.message.Identifier;
import com.lumatagroup.expression.e4o.commons.jms.message.Time;
import com.lumatagroup.expression.e4o.commons.jms.message.TimeWindow;
import com.lumatagroup.expression.e4o.commons.jms.message.Variable;
import com.lumatagroup.expression.e4o.commons.jms.message.sms.SMSReceiver;
import com.lumatagroup.expression.e4o.commons.jms.message.sms.SMSSender;

public final class DialogManagerMessageUtils {

    public static final int             CAMPAIGN_ID         = 1;
    public static final String          TENANT_ID                 = "1";
    public static final int             DRIVER_ID                 = 1;
    public static final String MESSAGE_ID = "1234";
    public static final String EXISTENT_MESSAGE_ID = "0001";
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
    public static final String          NOTIF_LOGS_ACTION         = "CampaignProvisioning";
    public static final String          NOTIF_LOGS_FEATURE_ID     = String.valueOf(CAMPAIGN_ID);
    public static final String          NOTIF_LOGS_IDENTIFIER     = "393356902723";
    public static final String          NOTIF_LOGS_TEXT           = "textMessage";
    public static final String          NOTIF_LOGS_TENANT_ID      = String.valueOf(TENANT_ID);
    public static final String          NOTIF_LOGS_ID_RECEIPT     = "";
    public static final String          SMS_RECEIVER              = "393356902723";
    public static final String          SMS_SENDER                = "Lumata";
    public static final String          MESSAGE_TEXT              = "Text Message";
    public static List<TimeWindow>      timeWindowList      = new ArrayList<TimeWindow>();
    public static Map<String, Variable> variables           = new HashMap<String, Variable>();
    public static Map<String, String>   notifLogs           = new HashMap<String, String>();

    public static DialogManagerMessage newValidDialogManagerMessage(Long messageId, Long tenantId)
                                                                                                  throws ParseException {
        fillNotifLog();
        fillTimeWindowList();
        Identifier<Long, Long> identifier = getIdentifier(messageId, tenantId);
        SMSReceiver receiver = new SMSReceiver(SMS_RECEIVER);
        SMSSender sender = new SMSSender(SMS_SENDER);
        Date maxExpirationTimeStamp = new SimpleDateFormat(DATE_FORMAT).parse("2015-11-07 23:00:00");
        DialogManagerMessage dmMessage =
                                         new DialogManagerMessage(identifier, receiver, sender, MESSAGE_TEXT,
                                                                  maxExpirationTimeStamp, notifLogs, timeWindowList);

        return dmMessage;
    }

    private static void fillNotifLog() {
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

    private static void fillTimeWindowList() throws ParseException {
        timeWindowList.clear();
        Date beginDate = new SimpleDateFormat(DATE_FORMAT).parse("2013-11-07 08:00:00");
        Date endDate = new SimpleDateFormat(DATE_FORMAT).parse("2013-11-07 20:00:00");
        TimeWindow tw1 = new TimeWindow(new Time(beginDate), new Time(endDate), null);
        beginDate = new SimpleDateFormat(DATE_FORMAT).parse("2015-11-07 22:00:00");
        endDate = new SimpleDateFormat(DATE_FORMAT).parse("2015-11-07 23:00:00");
        TimeWindow tw2 = new TimeWindow(new Time(beginDate), new Time(endDate), null);
        timeWindowList.add(tw1);
        timeWindowList.add(tw2);
    }

    private static void fillVariables() throws ParseException {
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

    private static Identifier<Long, Long> getIdentifier(Long messageId, Long tenantId) {
        Identifier<Long, Long> identifier = new ExternalIdentifier(messageId, null, new Integer(1), tenantId);
        return identifier;
    }

}
