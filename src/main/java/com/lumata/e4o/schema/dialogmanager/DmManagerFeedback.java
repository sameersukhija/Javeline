package com.lumata.e4o.schema.dialogmanager;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "dm_manager_feedback" )
public class DmManagerFeedback { 

	public enum Fields { manager_feedback_id, driver_id, min_consumer, max_consumer, idle_consumer, consumer_bean_name, active }

	@Column(
			table = "dm_manager_feedback",
			field = "manager_feedback_id",
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
			getMethod = "getManagerFeedbackId",
			setMethod = "setManagerFeedbackId"
	)
	private Integer manager_feedback_id;

	@Column(
			table = "dm_manager_feedback",
			field = "driver_id",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getDriverId",
			setMethod = "setDriverId"
	)
	private Integer driver_id;

	@Column(
			table = "dm_manager_feedback",
			field = "min_consumer",
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
			getMethod = "getMinConsumer",
			setMethod = "setMinConsumer"
	)
	private Byte min_consumer;

	@Column(
			table = "dm_manager_feedback",
			field = "max_consumer",
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
			getMethod = "getMaxConsumer",
			setMethod = "setMaxConsumer"
	)
	private Byte max_consumer;

	@Column(
			table = "dm_manager_feedback",
			field = "idle_consumer",
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
			getMethod = "getIdleConsumer",
			setMethod = "setIdleConsumer"
	)
	private Byte idle_consumer;

	@Column(
			table = "dm_manager_feedback",
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
			table = "dm_manager_feedback",
			field = "active",
			type = "tinyint(1)",
			mysqlType = "tinyint",
			javaType = "Boolean",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 1,
			getMethod = "getActive",
			setMethod = "setActive"
	)
	private Boolean active;


	public DmManagerFeedback() {} 

	public DmManagerFeedback( ResultSet rs ) throws SQLException {

		this.manager_feedback_id = rs.getInt( DmManagerFeedback.Fields.manager_feedback_id.name() );
		this.driver_id = rs.getInt( DmManagerFeedback.Fields.driver_id.name() );
		this.min_consumer = rs.getByte( DmManagerFeedback.Fields.min_consumer.name() );
		this.max_consumer = rs.getByte( DmManagerFeedback.Fields.max_consumer.name() );
		this.idle_consumer = rs.getByte( DmManagerFeedback.Fields.idle_consumer.name() );
		this.consumer_bean_name = rs.getString( DmManagerFeedback.Fields.consumer_bean_name.name() );
		this.active = rs.getBoolean( DmManagerFeedback.Fields.active.name() );

	}

	public DmManagerFeedback( JSONObject jo ) throws JSONException {

		this.manager_feedback_id = (int)jo.getInt( DmManagerFeedback.Fields.manager_feedback_id.name() );
		this.driver_id = (int)jo.getInt( DmManagerFeedback.Fields.driver_id.name() );
		this.min_consumer = (byte)jo.getInt( DmManagerFeedback.Fields.min_consumer.name() );
		this.max_consumer = (byte)jo.getInt( DmManagerFeedback.Fields.max_consumer.name() );
		this.idle_consumer = (byte)jo.getInt( DmManagerFeedback.Fields.idle_consumer.name() );
		this.consumer_bean_name = jo.getString( DmManagerFeedback.Fields.consumer_bean_name.name() );
		this.active = jo.getBoolean( DmManagerFeedback.Fields.active.name() );

	}

	public Integer getManagerFeedbackId() {

		return this.manager_feedback_id;

	}

	public void setManagerFeedbackId( Integer manager_feedback_id ) {

		this.manager_feedback_id = manager_feedback_id;

	}

	public Integer getDriverId() {

		return this.driver_id;

	}

	public void setDriverId( Integer driver_id ) {

		this.driver_id = driver_id;

	}

	public Byte getMinConsumer() {

		return this.min_consumer;

	}

	public void setMinConsumer( Byte min_consumer ) {

		this.min_consumer = min_consumer;

	}

	public Byte getMaxConsumer() {

		return this.max_consumer;

	}

	public void setMaxConsumer( Byte max_consumer ) {

		this.max_consumer = max_consumer;

	}

	public Byte getIdleConsumer() {

		return this.idle_consumer;

	}

	public void setIdleConsumer( Byte idle_consumer ) {

		this.idle_consumer = idle_consumer;

	}

	public String getConsumerBeanName() {

		return this.consumer_bean_name;

	}

	public void setConsumerBeanName( String consumer_bean_name ) {

		this.consumer_bean_name = consumer_bean_name;

	}

	public Boolean getActive() {

		return this.active;

	}

	public void setActive( Boolean active ) {

		this.active = active;

	}

	public Fields[] getEntityFields() {

		return DmManagerFeedback.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"manager_feedback_id\": \"" ).append( this.getManagerFeedbackId() ).append( "\", " )
			.append( "\"driver_id\": \"" ).append( this.getDriverId() ).append( "\", " )
			.append( "\"min_consumer\": \"" ).append( this.getMinConsumer() ).append( "\", " )
			.append( "\"max_consumer\": \"" ).append( this.getMaxConsumer() ).append( "\", " )
			.append( "\"idle_consumer\": \"" ).append( this.getIdleConsumer() ).append( "\", " )
			.append( "\"consumer_bean_name\": \"" ).append( this.getConsumerBeanName() ).append( "\", " )
			.append( "\"active\": \"" ).append( this.getActive() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }