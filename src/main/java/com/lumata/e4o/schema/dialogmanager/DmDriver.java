package com.lumata.e4o.schema.dialogmanager;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "dm_driver" )
public class DmDriver { 

	public enum Fields { driver_id, jms_connection_id, tenant_identifier, feedback_queue_id, freeze_queue_id, throttle_queue_id, queue_id, min_consumer, max_consumer, idle_consumer, consumer_bean_name, hostname, active }

	@Column(
			table = "dm_driver",
			field = "driver_id",
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
			getMethod = "getDriverId",
			setMethod = "setDriverId"
	)
	private Integer driver_id;

	@Column(
			table = "dm_driver",
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
			table = "dm_driver",
			field = "tenant_identifier",
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
			getMethod = "getTenantIdentifier",
			setMethod = "setTenantIdentifier"
	)
	private String tenant_identifier;

	@Column(
			table = "dm_driver",
			field = "feedback_queue_id",
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
			getMethod = "getFeedbackQueueId",
			setMethod = "setFeedbackQueueId"
	)
	private Integer feedback_queue_id;

	@Column(
			table = "dm_driver",
			field = "freeze_queue_id",
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
			getMethod = "getFreezeQueueId",
			setMethod = "setFreezeQueueId"
	)
	private Integer freeze_queue_id;

	@Column(
			table = "dm_driver",
			field = "throttle_queue_id",
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
			getMethod = "getThrottleQueueId",
			setMethod = "setThrottleQueueId"
	)
	private Integer throttle_queue_id;

	@Column(
			table = "dm_driver",
			field = "queue_id",
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
			getMethod = "getQueueId",
			setMethod = "setQueueId"
	)
	private Integer queue_id;

	@Column(
			table = "dm_driver",
			field = "min_consumer",
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
			getMethod = "getMinConsumer",
			setMethod = "setMinConsumer"
	)
	private Boolean min_consumer;

	@Column(
			table = "dm_driver",
			field = "max_consumer",
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
			getMethod = "getMaxConsumer",
			setMethod = "setMaxConsumer"
	)
	private Boolean max_consumer;

	@Column(
			table = "dm_driver",
			field = "idle_consumer",
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
			getMethod = "getIdleConsumer",
			setMethod = "setIdleConsumer"
	)
	private Boolean idle_consumer;

	@Column(
			table = "dm_driver",
			field = "consumer_bean_name",
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
			getMethod = "getConsumerBeanName",
			setMethod = "setConsumerBeanName"
	)
	private String consumer_bean_name;

	@Column(
			table = "dm_driver",
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
			table = "dm_driver",
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


	public DmDriver() {} 

	public DmDriver( ResultSet rs ) throws SQLException {

		this.driver_id = rs.getInt( DmDriver.Fields.driver_id.name() );
		this.jms_connection_id = rs.getInt( DmDriver.Fields.jms_connection_id.name() );
		this.tenant_identifier = rs.getString( DmDriver.Fields.tenant_identifier.name() );
		this.feedback_queue_id = rs.getInt( DmDriver.Fields.feedback_queue_id.name() );
		this.freeze_queue_id = rs.getInt( DmDriver.Fields.freeze_queue_id.name() );
		this.throttle_queue_id = rs.getInt( DmDriver.Fields.throttle_queue_id.name() );
		this.queue_id = rs.getInt( DmDriver.Fields.queue_id.name() );
		this.min_consumer = rs.getBoolean( DmDriver.Fields.min_consumer.name() );
		this.max_consumer = rs.getBoolean( DmDriver.Fields.max_consumer.name() );
		this.idle_consumer = rs.getBoolean( DmDriver.Fields.idle_consumer.name() );
		this.consumer_bean_name = rs.getString( DmDriver.Fields.consumer_bean_name.name() );
		this.hostname = rs.getString( DmDriver.Fields.hostname.name() );
		this.active = rs.getString( DmDriver.Fields.active.name() );

	}

	public DmDriver( JSONObject jo ) throws JSONException {

		this.driver_id = (int)jo.getInt( DmDriver.Fields.driver_id.name() );
		this.jms_connection_id = (int)jo.getInt( DmDriver.Fields.jms_connection_id.name() );
		this.tenant_identifier = jo.getString( DmDriver.Fields.tenant_identifier.name() );
		this.feedback_queue_id = (int)jo.getInt( DmDriver.Fields.feedback_queue_id.name() );
		this.freeze_queue_id = (int)jo.getInt( DmDriver.Fields.freeze_queue_id.name() );
		this.throttle_queue_id = (int)jo.getInt( DmDriver.Fields.throttle_queue_id.name() );
		this.queue_id = (int)jo.getInt( DmDriver.Fields.queue_id.name() );
		this.min_consumer = jo.getBoolean( DmDriver.Fields.min_consumer.name() );
		this.max_consumer = jo.getBoolean( DmDriver.Fields.max_consumer.name() );
		this.idle_consumer = jo.getBoolean( DmDriver.Fields.idle_consumer.name() );
		this.consumer_bean_name = jo.getString( DmDriver.Fields.consumer_bean_name.name() );
		this.hostname = jo.getString( DmDriver.Fields.hostname.name() );
		this.active = jo.getString( DmDriver.Fields.active.name() );

	}

	public Integer getDriverId() {

		return this.driver_id;

	}

	public void setDriverId( Integer driver_id ) {

		this.driver_id = driver_id;

	}

	public Integer getJmsConnectionId() {

		return this.jms_connection_id;

	}

	public void setJmsConnectionId( Integer jms_connection_id ) {

		this.jms_connection_id = jms_connection_id;

	}

	public String getTenantIdentifier() {

		return this.tenant_identifier;

	}

	public void setTenantIdentifier( String tenant_identifier ) {

		this.tenant_identifier = tenant_identifier;

	}

	public Integer getFeedbackQueueId() {

		return this.feedback_queue_id;

	}

	public void setFeedbackQueueId( Integer feedback_queue_id ) {

		this.feedback_queue_id = feedback_queue_id;

	}

	public Integer getFreezeQueueId() {

		return this.freeze_queue_id;

	}

	public void setFreezeQueueId( Integer freeze_queue_id ) {

		this.freeze_queue_id = freeze_queue_id;

	}

	public Integer getThrottleQueueId() {

		return this.throttle_queue_id;

	}

	public void setThrottleQueueId( Integer throttle_queue_id ) {

		this.throttle_queue_id = throttle_queue_id;

	}

	public Integer getQueueId() {

		return this.queue_id;

	}

	public void setQueueId( Integer queue_id ) {

		this.queue_id = queue_id;

	}

	public Boolean getMinConsumer() {

		return this.min_consumer;

	}

	public void setMinConsumer( Boolean min_consumer ) {

		this.min_consumer = min_consumer;

	}

	public Boolean getMaxConsumer() {

		return this.max_consumer;

	}

	public void setMaxConsumer( Boolean max_consumer ) {

		this.max_consumer = max_consumer;

	}

	public Boolean getIdleConsumer() {

		return this.idle_consumer;

	}

	public void setIdleConsumer( Boolean idle_consumer ) {

		this.idle_consumer = idle_consumer;

	}

	public String getConsumerBeanName() {

		return this.consumer_bean_name;

	}

	public void setConsumerBeanName( String consumer_bean_name ) {

		this.consumer_bean_name = consumer_bean_name;

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

	public Fields[] getEntityFields() {

		return DmDriver.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"driver_id\": \"" ).append( this.getDriverId() ).append( "\", " )
			.append( "\"jms_connection_id\": \"" ).append( this.getJmsConnectionId() ).append( "\", " )
			.append( "\"tenant_identifier\": \"" ).append( this.getTenantIdentifier() ).append( "\", " )
			.append( "\"feedback_queue_id\": \"" ).append( this.getFeedbackQueueId() ).append( "\", " )
			.append( "\"freeze_queue_id\": \"" ).append( this.getFreezeQueueId() ).append( "\", " )
			.append( "\"throttle_queue_id\": \"" ).append( this.getThrottleQueueId() ).append( "\", " )
			.append( "\"queue_id\": \"" ).append( this.getQueueId() ).append( "\", " )
			.append( "\"min_consumer\": \"" ).append( this.getMinConsumer() ).append( "\", " )
			.append( "\"max_consumer\": \"" ).append( this.getMaxConsumer() ).append( "\", " )
			.append( "\"idle_consumer\": \"" ).append( this.getIdleConsumer() ).append( "\", " )
			.append( "\"consumer_bean_name\": \"" ).append( this.getConsumerBeanName() ).append( "\", " )
			.append( "\"hostname\": \"" ).append( this.getHostname() ).append( "\", " )
			.append( "\"active\": \"" ).append( this.getActive() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }