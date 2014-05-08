package com.lumata.e4o.schema.dialogmanager;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.sql.Timestamp;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "dm_feedback" )
public class DmFeedback { 

	public enum Fields { message_id, message_driver_id, tenant_id, driver_id, module_id, feature_id, channel, status, sender, recipient, message_text, message_text_converted, max_expiration_timestamp, retry_attempts, max_retry_attempts, one_attempt_per_day, delay_between_retries, date_created, date_feedback, date_modified, time_windows, variables, notif_logs, criteria, meta_info }

	@Column(
			table = "dm_feedback",
			field = "message_id",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 255,
			getMethod = "getMessageId",
			setMethod = "setMessageId"
	)
	private String message_id;

	@Column(
			table = "dm_feedback",
			field = "message_driver_id",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 255,
			getMethod = "getMessageDriverId",
			setMethod = "setMessageDriverId"
	)
	private String message_driver_id;

	@Column(
			table = "dm_feedback",
			field = "tenant_id",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "",
			extra = "",
			length = 255,
			getMethod = "getTenantId",
			setMethod = "setTenantId"
	)
	private String tenant_id;

	@Column(
			table = "dm_feedback",
			field = "driver_id",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getDriverId",
			setMethod = "setDriverId"
	)
	private Integer driver_id;

	@Column(
			table = "dm_feedback",
			field = "module_id",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 255,
			getMethod = "getModuleId",
			setMethod = "setModuleId"
	)
	private String module_id;

	@Column(
			table = "dm_feedback",
			field = "feature_id",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 255,
			getMethod = "getFeatureId",
			setMethod = "setFeatureId"
	)
	private String feature_id;

	@Column(
			table = "dm_feedback",
			field = "channel",
			type = "varchar(50)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 50,
			getMethod = "getChannel",
			setMethod = "setChannel"
	)
	private String channel;

	@Column(
			table = "dm_feedback",
			field = "status",
			type = "varchar(50)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 50,
			getMethod = "getStatus",
			setMethod = "setStatus"
	)
	private String status;

	@Column(
			table = "dm_feedback",
			field = "sender",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 255,
			getMethod = "getSender",
			setMethod = "setSender"
	)
	private String sender;

	@Column(
			table = "dm_feedback",
			field = "recipient",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 255,
			getMethod = "getRecipient",
			setMethod = "setRecipient"
	)
	private String recipient;

	@Column(
			table = "dm_feedback",
			field = "message_text",
			type = "text",
			mysqlType = "text",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getMessageText",
			setMethod = "setMessageText"
	)
	private String message_text;

	@Column(
			table = "dm_feedback",
			field = "message_text_converted",
			type = "text",
			mysqlType = "text",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getMessageTextConverted",
			setMethod = "setMessageTextConverted"
	)
	private String message_text_converted;

	@Column(
			table = "dm_feedback",
			field = "max_expiration_timestamp",
			type = "timestamp",
			mysqlType = "timestamp",
			javaType = "Timestamp",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0000-00-00 00:00:00",
			extra = "",
			length = 0,
			getMethod = "getMaxExpirationTimestamp",
			setMethod = "setMaxExpirationTimestamp"
	)
	private Timestamp max_expiration_timestamp;

	@Column(
			table = "dm_feedback",
			field = "retry_attempts",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getRetryAttempts",
			setMethod = "setRetryAttempts"
	)
	private Integer retry_attempts;

	@Column(
			table = "dm_feedback",
			field = "max_retry_attempts",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getMaxRetryAttempts",
			setMethod = "setMaxRetryAttempts"
	)
	private Integer max_retry_attempts;

	@Column(
			table = "dm_feedback",
			field = "one_attempt_per_day",
			type = "tinyint(1)",
			mysqlType = "tinyint",
			javaType = "Boolean",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "1",
			extra = "",
			length = 1,
			getMethod = "getOneAttemptPerDay",
			setMethod = "setOneAttemptPerDay"
	)
	private Boolean one_attempt_per_day;

	@Column(
			table = "dm_feedback",
			field = "delay_between_retries",
			type = "bigint(20)",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getDelayBetweenRetries",
			setMethod = "setDelayBetweenRetries"
	)
	private Long delay_between_retries;

	@Column(
			table = "dm_feedback",
			field = "date_created",
			type = "timestamp",
			mysqlType = "timestamp",
			javaType = "Timestamp",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0000-00-00 00:00:00",
			extra = "",
			length = 0,
			getMethod = "getDateCreated",
			setMethod = "setDateCreated"
	)
	private Timestamp date_created;

	@Column(
			table = "dm_feedback",
			field = "date_feedback",
			type = "timestamp",
			mysqlType = "timestamp",
			javaType = "Timestamp",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0000-00-00 00:00:00",
			extra = "",
			length = 0,
			getMethod = "getDateFeedback",
			setMethod = "setDateFeedback"
	)
	private Timestamp date_feedback;

	@Column(
			table = "dm_feedback",
			field = "date_modified",
			type = "timestamp",
			mysqlType = "timestamp",
			javaType = "Timestamp",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0000-00-00 00:00:00",
			extra = "",
			length = 0,
			getMethod = "getDateModified",
			setMethod = "setDateModified"
	)
	private Timestamp date_modified;

	@Column(
			table = "dm_feedback",
			field = "time_windows",
			type = "text",
			mysqlType = "text",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getTimeWindows",
			setMethod = "setTimeWindows"
	)
	private String time_windows;

	@Column(
			table = "dm_feedback",
			field = "variables",
			type = "text",
			mysqlType = "text",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getVariables",
			setMethod = "setVariables"
	)
	private String variables;

	@Column(
			table = "dm_feedback",
			field = "notif_logs",
			type = "text",
			mysqlType = "text",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getNotifLogs",
			setMethod = "setNotifLogs"
	)
	private String notif_logs;

	@Column(
			table = "dm_feedback",
			field = "criteria",
			type = "text",
			mysqlType = "text",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getCriteria",
			setMethod = "setCriteria"
	)
	private String criteria;

	@Column(
			table = "dm_feedback",
			field = "meta_info",
			type = "text",
			mysqlType = "text",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getMetaInfo",
			setMethod = "setMetaInfo"
	)
	private String meta_info;


	public DmFeedback() {} 

	public DmFeedback( ResultSet rs ) throws SQLException {

		this.message_id = rs.getString( DmFeedback.Fields.message_id.name() );
		this.message_driver_id = rs.getString( DmFeedback.Fields.message_driver_id.name() );
		this.tenant_id = rs.getString( DmFeedback.Fields.tenant_id.name() );
		this.driver_id = rs.getInt( DmFeedback.Fields.driver_id.name() );
		this.module_id = rs.getString( DmFeedback.Fields.module_id.name() );
		this.feature_id = rs.getString( DmFeedback.Fields.feature_id.name() );
		this.channel = rs.getString( DmFeedback.Fields.channel.name() );
		this.status = rs.getString( DmFeedback.Fields.status.name() );
		this.sender = rs.getString( DmFeedback.Fields.sender.name() );
		this.recipient = rs.getString( DmFeedback.Fields.recipient.name() );
		this.message_text = rs.getString( DmFeedback.Fields.message_text.name() );
		this.message_text_converted = rs.getString( DmFeedback.Fields.message_text_converted.name() );
		this.max_expiration_timestamp = rs.getTimestamp( DmFeedback.Fields.max_expiration_timestamp.name() );
		this.retry_attempts = rs.getInt( DmFeedback.Fields.retry_attempts.name() );
		this.max_retry_attempts = rs.getInt( DmFeedback.Fields.max_retry_attempts.name() );
		this.one_attempt_per_day = rs.getBoolean( DmFeedback.Fields.one_attempt_per_day.name() );
		this.delay_between_retries = rs.getLong( DmFeedback.Fields.delay_between_retries.name() );
		this.date_created = rs.getTimestamp( DmFeedback.Fields.date_created.name() );
		this.date_feedback = rs.getTimestamp( DmFeedback.Fields.date_feedback.name() );
		this.date_modified = rs.getTimestamp( DmFeedback.Fields.date_modified.name() );
		this.time_windows = rs.getString( DmFeedback.Fields.time_windows.name() );
		this.variables = rs.getString( DmFeedback.Fields.variables.name() );
		this.notif_logs = rs.getString( DmFeedback.Fields.notif_logs.name() );
		this.criteria = rs.getString( DmFeedback.Fields.criteria.name() );
		this.meta_info = rs.getString( DmFeedback.Fields.meta_info.name() );

	}

	public DmFeedback( JSONObject jo ) throws JSONException, ParseException {

		this.message_id = jo.getString( DmFeedback.Fields.message_id.name() );
		this.message_driver_id = jo.getString( DmFeedback.Fields.message_driver_id.name() );
		this.tenant_id = jo.getString( DmFeedback.Fields.tenant_id.name() );
		this.driver_id = (int)jo.getInt( DmFeedback.Fields.driver_id.name() );
		this.module_id = jo.getString( DmFeedback.Fields.module_id.name() );
		this.feature_id = jo.getString( DmFeedback.Fields.feature_id.name() );
		this.channel = jo.getString( DmFeedback.Fields.channel.name() );
		this.status = jo.getString( DmFeedback.Fields.status.name() );
		this.sender = jo.getString( DmFeedback.Fields.sender.name() );
		this.recipient = jo.getString( DmFeedback.Fields.recipient.name() );
		this.message_text = jo.getString( DmFeedback.Fields.message_text.name() );
		this.message_text_converted = jo.getString( DmFeedback.Fields.message_text_converted.name() );
		this.max_expiration_timestamp = new Timestamp( Format.getMysqlDateTime( jo.getString( DmFeedback.Fields.max_expiration_timestamp.name() ) ).getTime() );
		this.retry_attempts = (int)jo.getInt( DmFeedback.Fields.retry_attempts.name() );
		this.max_retry_attempts = (int)jo.getInt( DmFeedback.Fields.max_retry_attempts.name() );
		this.one_attempt_per_day = jo.getBoolean( DmFeedback.Fields.one_attempt_per_day.name() );
		this.delay_between_retries = (long)jo.getLong( DmFeedback.Fields.delay_between_retries.name() );
		this.date_created = new Timestamp( Format.getMysqlDateTime( jo.getString( DmFeedback.Fields.date_created.name() ) ).getTime() );
		this.date_feedback = new Timestamp( Format.getMysqlDateTime( jo.getString( DmFeedback.Fields.date_feedback.name() ) ).getTime() );
		this.date_modified = new Timestamp( Format.getMysqlDateTime( jo.getString( DmFeedback.Fields.date_modified.name() ) ).getTime() );
		this.time_windows = jo.getString( DmFeedback.Fields.time_windows.name() );
		this.variables = jo.getString( DmFeedback.Fields.variables.name() );
		this.notif_logs = jo.getString( DmFeedback.Fields.notif_logs.name() );
		this.criteria = jo.getString( DmFeedback.Fields.criteria.name() );
		this.meta_info = jo.getString( DmFeedback.Fields.meta_info.name() );

	}

	public String getMessageId() {

		return this.message_id;

	}

	public void setMessageId( String message_id ) {

		this.message_id = message_id;

	}

	public String getMessageDriverId() {

		return this.message_driver_id;

	}

	public void setMessageDriverId( String message_driver_id ) {

		this.message_driver_id = message_driver_id;

	}

	public String getTenantId() {

		return this.tenant_id;

	}

	public void setTenantId( String tenant_id ) {

		this.tenant_id = tenant_id;

	}

	public Integer getDriverId() {

		return this.driver_id;

	}

	public void setDriverId( Integer driver_id ) {

		this.driver_id = driver_id;

	}

	public String getModuleId() {

		return this.module_id;

	}

	public void setModuleId( String module_id ) {

		this.module_id = module_id;

	}

	public String getFeatureId() {

		return this.feature_id;

	}

	public void setFeatureId( String feature_id ) {

		this.feature_id = feature_id;

	}

	public String getChannel() {

		return this.channel;

	}

	public void setChannel( String channel ) {

		this.channel = channel;

	}

	public String getStatus() {

		return this.status;

	}

	public void setStatus( String status ) {

		this.status = status;

	}

	public String getSender() {

		return this.sender;

	}

	public void setSender( String sender ) {

		this.sender = sender;

	}

	public String getRecipient() {

		return this.recipient;

	}

	public void setRecipient( String recipient ) {

		this.recipient = recipient;

	}

	public String getMessageText() {

		return this.message_text;

	}

	public void setMessageText( String message_text ) {

		this.message_text = message_text;

	}

	public String getMessageTextConverted() {

		return this.message_text_converted;

	}

	public void setMessageTextConverted( String message_text_converted ) {

		this.message_text_converted = message_text_converted;

	}

	public Timestamp getMaxExpirationTimestamp() {

		return this.max_expiration_timestamp;

	}

	public void setMaxExpirationTimestamp( Timestamp max_expiration_timestamp ) {

		this.max_expiration_timestamp = max_expiration_timestamp;

	}

	public Integer getRetryAttempts() {

		return this.retry_attempts;

	}

	public void setRetryAttempts( Integer retry_attempts ) {

		this.retry_attempts = retry_attempts;

	}

	public Integer getMaxRetryAttempts() {

		return this.max_retry_attempts;

	}

	public void setMaxRetryAttempts( Integer max_retry_attempts ) {

		this.max_retry_attempts = max_retry_attempts;

	}

	public Boolean getOneAttemptPerDay() {

		return this.one_attempt_per_day;

	}

	public void setOneAttemptPerDay( Boolean one_attempt_per_day ) {

		this.one_attempt_per_day = one_attempt_per_day;

	}

	public Long getDelayBetweenRetries() {

		return this.delay_between_retries;

	}

	public void setDelayBetweenRetries( Long delay_between_retries ) {

		this.delay_between_retries = delay_between_retries;

	}

	public Timestamp getDateCreated() {

		return this.date_created;

	}

	public void setDateCreated( Timestamp date_created ) {

		this.date_created = date_created;

	}

	public Timestamp getDateFeedback() {

		return this.date_feedback;

	}

	public void setDateFeedback( Timestamp date_feedback ) {

		this.date_feedback = date_feedback;

	}

	public Timestamp getDateModified() {

		return this.date_modified;

	}

	public void setDateModified( Timestamp date_modified ) {

		this.date_modified = date_modified;

	}

	public String getTimeWindows() {

		return this.time_windows;

	}

	public void setTimeWindows( String time_windows ) {

		this.time_windows = time_windows;

	}

	public String getVariables() {

		return this.variables;

	}

	public void setVariables( String variables ) {

		this.variables = variables;

	}

	public String getNotifLogs() {

		return this.notif_logs;

	}

	public void setNotifLogs( String notif_logs ) {

		this.notif_logs = notif_logs;

	}

	public String getCriteria() {

		return this.criteria;

	}

	public void setCriteria( String criteria ) {

		this.criteria = criteria;

	}

	public String getMetaInfo() {

		return this.meta_info;

	}

	public void setMetaInfo( String meta_info ) {

		this.meta_info = meta_info;

	}

	public Fields[] getEntityFields() {

		return DmFeedback.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"message_id\": \"" ).append( this.getMessageId() ).append( "\", " )
			.append( "\"message_driver_id\": \"" ).append( this.getMessageDriverId() ).append( "\", " )
			.append( "\"tenant_id\": \"" ).append( this.getTenantId() ).append( "\", " )
			.append( "\"driver_id\": \"" ).append( this.getDriverId() ).append( "\", " )
			.append( "\"module_id\": \"" ).append( this.getModuleId() ).append( "\", " )
			.append( "\"feature_id\": \"" ).append( this.getFeatureId() ).append( "\", " )
			.append( "\"channel\": \"" ).append( this.getChannel() ).append( "\", " )
			.append( "\"status\": \"" ).append( this.getStatus() ).append( "\", " )
			.append( "\"sender\": \"" ).append( this.getSender() ).append( "\", " )
			.append( "\"recipient\": \"" ).append( this.getRecipient() ).append( "\", " )
			.append( "\"message_text\": \"" ).append( this.getMessageText() ).append( "\", " )
			.append( "\"message_text_converted\": \"" ).append( this.getMessageTextConverted() ).append( "\", " )
			.append( "\"max_expiration_timestamp\": \"" ).append( this.getMaxExpirationTimestamp() ).append( "\", " )
			.append( "\"retry_attempts\": \"" ).append( this.getRetryAttempts() ).append( "\", " )
			.append( "\"max_retry_attempts\": \"" ).append( this.getMaxRetryAttempts() ).append( "\", " )
			.append( "\"one_attempt_per_day\": \"" ).append( this.getOneAttemptPerDay() ).append( "\", " )
			.append( "\"delay_between_retries\": \"" ).append( this.getDelayBetweenRetries() ).append( "\", " )
			.append( "\"date_created\": \"" ).append( this.getDateCreated() ).append( "\", " )
			.append( "\"date_feedback\": \"" ).append( this.getDateFeedback() ).append( "\", " )
			.append( "\"date_modified\": \"" ).append( this.getDateModified() ).append( "\", " )
			.append( "\"time_windows\": \"" ).append( this.getTimeWindows() ).append( "\", " )
			.append( "\"variables\": \"" ).append( this.getVariables() ).append( "\", " )
			.append( "\"notif_logs\": \"" ).append( this.getNotifLogs() ).append( "\", " )
			.append( "\"criteria\": \"" ).append( this.getCriteria() ).append( "\", " )
			.append( "\"meta_info\": \"" ).append( this.getMetaInfo() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }