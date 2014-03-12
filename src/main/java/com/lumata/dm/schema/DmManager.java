package com.lumata.dm.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "dm_manager" )
public class DmManager { 

	public enum Fields { queue_dialog_id, jms_connection_id, message_queue_id, message_queue_pool_size, message_queue_min_consumer, message_queue_max_consumer, message_queue_idle_consumer, message_consumer_bean_name, driver_id, feedback_queue_min_consumer, feedback_queue_max_consumer, feedback_queue_idle_consumer, feedback_consumer_bean_name, hostname, active, send_enabled }

	@Column(
			table = "dm_manager",
			field = "queue_dialog_id",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = true,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 11,
			getMethod = "getQueueDialogId",
			setMethod = "setQueueDialogId"
	)
	private Integer queue_dialog_id;

	@Column(
			table = "dm_manager",
			field = "jms_connection_id",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getJmsConnectionId",
			setMethod = "setJmsConnectionId"
	)
	private Integer jms_connection_id;

	@Column(
			table = "dm_manager",
			field = "message_queue_id",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getMessageQueueId",
			setMethod = "setMessageQueueId"
	)
	private Integer message_queue_id;

	@Column(
			table = "dm_manager",
			field = "message_queue_pool_size",
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
			getMethod = "getMessageQueuePoolSize",
			setMethod = "setMessageQueuePoolSize"
	)
	private Boolean message_queue_pool_size;

	@Column(
			table = "dm_manager",
			field = "message_queue_min_consumer",
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
			getMethod = "getMessageQueueMinConsumer",
			setMethod = "setMessageQueueMinConsumer"
	)
	private Boolean message_queue_min_consumer;

	@Column(
			table = "dm_manager",
			field = "message_queue_max_consumer",
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
			getMethod = "getMessageQueueMaxConsumer",
			setMethod = "setMessageQueueMaxConsumer"
	)
	private Boolean message_queue_max_consumer;

	@Column(
			table = "dm_manager",
			field = "message_queue_idle_consumer",
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
			getMethod = "getMessageQueueIdleConsumer",
			setMethod = "setMessageQueueIdleConsumer"
	)
	private Boolean message_queue_idle_consumer;

	@Column(
			table = "dm_manager",
			field = "message_consumer_bean_name",
			type = "varchar(64)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 64,
			getMethod = "getMessageConsumerBeanName",
			setMethod = "setMessageConsumerBeanName"
	)
	private String message_consumer_bean_name;

	@Column(
			table = "dm_manager",
			field = "driver_id",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getDriverId",
			setMethod = "setDriverId"
	)
	private Integer driver_id;

	@Column(
			table = "dm_manager",
			field = "feedback_queue_min_consumer",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "1",
			extra = "",
			length = 4,
			getMethod = "getFeedbackQueueMinConsumer",
			setMethod = "setFeedbackQueueMinConsumer"
	)
	private Byte feedback_queue_min_consumer;

	@Column(
			table = "dm_manager",
			field = "feedback_queue_max_consumer",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "1",
			extra = "",
			length = 4,
			getMethod = "getFeedbackQueueMaxConsumer",
			setMethod = "setFeedbackQueueMaxConsumer"
	)
	private Byte feedback_queue_max_consumer;

	@Column(
			table = "dm_manager",
			field = "feedback_queue_idle_consumer",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "1",
			extra = "",
			length = 4,
			getMethod = "getFeedbackQueueIdleConsumer",
			setMethod = "setFeedbackQueueIdleConsumer"
	)
	private Byte feedback_queue_idle_consumer;

	@Column(
			table = "dm_manager",
			field = "feedback_consumer_bean_name",
			type = "varchar(64)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 64,
			getMethod = "getFeedbackConsumerBeanName",
			setMethod = "setFeedbackConsumerBeanName"
	)
	private String feedback_consumer_bean_name;

	@Column(
			table = "dm_manager",
			field = "hostname",
			type = "varchar(64)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 64,
			getMethod = "getHostname",
			setMethod = "setHostname"
	)
	private String hostname;

	@Column(
			table = "dm_manager",
			field = "active",
			type = "enum('0','1')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 2,
			getMethod = "getActive",
			setMethod = "setActive"
	)
	private String active;

	@Column(
			table = "dm_manager",
			field = "send_enabled",
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
			getMethod = "getSendEnabled",
			setMethod = "setSendEnabled"
	)
	private Boolean send_enabled;


	public DmManager() {} 

	public DmManager( ResultSet rs ) throws SQLException {

		this.queue_dialog_id = rs.getInt( DmManager.Fields.queue_dialog_id.name() );
		this.jms_connection_id = rs.getInt( DmManager.Fields.jms_connection_id.name() );
		this.message_queue_id = rs.getInt( DmManager.Fields.message_queue_id.name() );
		this.message_queue_pool_size = rs.getBoolean( DmManager.Fields.message_queue_pool_size.name() );
		this.message_queue_min_consumer = rs.getBoolean( DmManager.Fields.message_queue_min_consumer.name() );
		this.message_queue_max_consumer = rs.getBoolean( DmManager.Fields.message_queue_max_consumer.name() );
		this.message_queue_idle_consumer = rs.getBoolean( DmManager.Fields.message_queue_idle_consumer.name() );
		this.message_consumer_bean_name = rs.getString( DmManager.Fields.message_consumer_bean_name.name() );
		this.driver_id = rs.getInt( DmManager.Fields.driver_id.name() );
		this.feedback_queue_min_consumer = rs.getByte( DmManager.Fields.feedback_queue_min_consumer.name() );
		this.feedback_queue_max_consumer = rs.getByte( DmManager.Fields.feedback_queue_max_consumer.name() );
		this.feedback_queue_idle_consumer = rs.getByte( DmManager.Fields.feedback_queue_idle_consumer.name() );
		this.feedback_consumer_bean_name = rs.getString( DmManager.Fields.feedback_consumer_bean_name.name() );
		this.hostname = rs.getString( DmManager.Fields.hostname.name() );
		this.active = rs.getString( DmManager.Fields.active.name() );
		this.send_enabled = rs.getBoolean( DmManager.Fields.send_enabled.name() );

	}

	public DmManager( JSONObject jo ) throws JSONException {

		this.queue_dialog_id = (int)jo.getInt( DmManager.Fields.queue_dialog_id.name() );
		this.jms_connection_id = (int)jo.getInt( DmManager.Fields.jms_connection_id.name() );
		this.message_queue_id = (int)jo.getInt( DmManager.Fields.message_queue_id.name() );
		this.message_queue_pool_size = jo.getBoolean( DmManager.Fields.message_queue_pool_size.name() );
		this.message_queue_min_consumer = jo.getBoolean( DmManager.Fields.message_queue_min_consumer.name() );
		this.message_queue_max_consumer = jo.getBoolean( DmManager.Fields.message_queue_max_consumer.name() );
		this.message_queue_idle_consumer = jo.getBoolean( DmManager.Fields.message_queue_idle_consumer.name() );
		this.message_consumer_bean_name = jo.getString( DmManager.Fields.message_consumer_bean_name.name() );
		this.driver_id = (int)jo.getInt( DmManager.Fields.driver_id.name() );
		this.feedback_queue_min_consumer = (byte)jo.getInt( DmManager.Fields.feedback_queue_min_consumer.name() );
		this.feedback_queue_max_consumer = (byte)jo.getInt( DmManager.Fields.feedback_queue_max_consumer.name() );
		this.feedback_queue_idle_consumer = (byte)jo.getInt( DmManager.Fields.feedback_queue_idle_consumer.name() );
		this.feedback_consumer_bean_name = jo.getString( DmManager.Fields.feedback_consumer_bean_name.name() );
		this.hostname = jo.getString( DmManager.Fields.hostname.name() );
		this.active = jo.getString( DmManager.Fields.active.name() );
		this.send_enabled = jo.getBoolean( DmManager.Fields.send_enabled.name() );

	}

	public Integer getQueueDialogId() {

		return this.queue_dialog_id;

	}

	public void setQueueDialogId( Integer queue_dialog_id ) {

		this.queue_dialog_id = queue_dialog_id;

	}

	public Integer getJmsConnectionId() {

		return this.jms_connection_id;

	}

	public void setJmsConnectionId( Integer jms_connection_id ) {

		this.jms_connection_id = jms_connection_id;

	}

	public Integer getMessageQueueId() {

		return this.message_queue_id;

	}

	public void setMessageQueueId( Integer message_queue_id ) {

		this.message_queue_id = message_queue_id;

	}

	public Boolean getMessageQueuePoolSize() {

		return this.message_queue_pool_size;

	}

	public void setMessageQueuePoolSize( Boolean message_queue_pool_size ) {

		this.message_queue_pool_size = message_queue_pool_size;

	}

	public Boolean getMessageQueueMinConsumer() {

		return this.message_queue_min_consumer;

	}

	public void setMessageQueueMinConsumer( Boolean message_queue_min_consumer ) {

		this.message_queue_min_consumer = message_queue_min_consumer;

	}

	public Boolean getMessageQueueMaxConsumer() {

		return this.message_queue_max_consumer;

	}

	public void setMessageQueueMaxConsumer( Boolean message_queue_max_consumer ) {

		this.message_queue_max_consumer = message_queue_max_consumer;

	}

	public Boolean getMessageQueueIdleConsumer() {

		return this.message_queue_idle_consumer;

	}

	public void setMessageQueueIdleConsumer( Boolean message_queue_idle_consumer ) {

		this.message_queue_idle_consumer = message_queue_idle_consumer;

	}

	public String getMessageConsumerBeanName() {

		return this.message_consumer_bean_name;

	}

	public void setMessageConsumerBeanName( String message_consumer_bean_name ) {

		this.message_consumer_bean_name = message_consumer_bean_name;

	}

	public Integer getDriverId() {

		return this.driver_id;

	}

	public void setDriverId( Integer driver_id ) {

		this.driver_id = driver_id;

	}

	public Byte getFeedbackQueueMinConsumer() {

		return this.feedback_queue_min_consumer;

	}

	public void setFeedbackQueueMinConsumer( Byte feedback_queue_min_consumer ) {

		this.feedback_queue_min_consumer = feedback_queue_min_consumer;

	}

	public Byte getFeedbackQueueMaxConsumer() {

		return this.feedback_queue_max_consumer;

	}

	public void setFeedbackQueueMaxConsumer( Byte feedback_queue_max_consumer ) {

		this.feedback_queue_max_consumer = feedback_queue_max_consumer;

	}

	public Byte getFeedbackQueueIdleConsumer() {

		return this.feedback_queue_idle_consumer;

	}

	public void setFeedbackQueueIdleConsumer( Byte feedback_queue_idle_consumer ) {

		this.feedback_queue_idle_consumer = feedback_queue_idle_consumer;

	}

	public String getFeedbackConsumerBeanName() {

		return this.feedback_consumer_bean_name;

	}

	public void setFeedbackConsumerBeanName( String feedback_consumer_bean_name ) {

		this.feedback_consumer_bean_name = feedback_consumer_bean_name;

	}

	public String getHostname() {

		return this.hostname;

	}

	public void setHostname( String hostname ) {

		this.hostname = hostname;

	}

	public String getActive() {

		return this.active;

	}

	public void setActive( String active ) {

		this.active = active;

	}

	public Boolean getSendEnabled() {

		return this.send_enabled;

	}

	public void setSendEnabled( Boolean send_enabled ) {

		this.send_enabled = send_enabled;

	}

	public Fields[] getEntityFields() {

		return DmManager.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"queue_dialog_id\": \"" ).append( this.getQueueDialogId() ).append( "\", " )
			.append( "\"jms_connection_id\": \"" ).append( this.getJmsConnectionId() ).append( "\", " )
			.append( "\"message_queue_id\": \"" ).append( this.getMessageQueueId() ).append( "\", " )
			.append( "\"message_queue_pool_size\": \"" ).append( this.getMessageQueuePoolSize() ).append( "\", " )
			.append( "\"message_queue_min_consumer\": \"" ).append( this.getMessageQueueMinConsumer() ).append( "\", " )
			.append( "\"message_queue_max_consumer\": \"" ).append( this.getMessageQueueMaxConsumer() ).append( "\", " )
			.append( "\"message_queue_idle_consumer\": \"" ).append( this.getMessageQueueIdleConsumer() ).append( "\", " )
			.append( "\"message_consumer_bean_name\": \"" ).append( this.getMessageConsumerBeanName() ).append( "\", " )
			.append( "\"driver_id\": \"" ).append( this.getDriverId() ).append( "\", " )
			.append( "\"feedback_queue_min_consumer\": \"" ).append( this.getFeedbackQueueMinConsumer() ).append( "\", " )
			.append( "\"feedback_queue_max_consumer\": \"" ).append( this.getFeedbackQueueMaxConsumer() ).append( "\", " )
			.append( "\"feedback_queue_idle_consumer\": \"" ).append( this.getFeedbackQueueIdleConsumer() ).append( "\", " )
			.append( "\"feedback_consumer_bean_name\": \"" ).append( this.getFeedbackConsumerBeanName() ).append( "\", " )
			.append( "\"hostname\": \"" ).append( this.getHostname() ).append( "\", " )
			.append( "\"active\": \"" ).append( this.getActive() ).append( "\", " )
			.append( "\"send_enabled\": \"" ).append( this.getSendEnabled() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }