package com.lumata.e4o.schema.dm;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "dm_topic_subscriber" )
public class DmTopicSubscriber { 

	public enum Fields { topic_subscriber_id, topic_publisher_id, consumer_bean_name, hostname, active }

	@Column(
			table = "dm_topic_subscriber",
			field = "topic_subscriber_id",
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
			getMethod = "getTopicSubscriberId",
			setMethod = "setTopicSubscriberId"
	)
	private Integer topic_subscriber_id;

	@Column(
			table = "dm_topic_subscriber",
			field = "topic_publisher_id",
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
			getMethod = "getTopicPublisherId",
			setMethod = "setTopicPublisherId"
	)
	private Integer topic_publisher_id;

	@Column(
			table = "dm_topic_subscriber",
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
			table = "dm_topic_subscriber",
			field = "hostname",
			type = "varchar(64)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 64,
			getMethod = "getHostname",
			setMethod = "setHostname"
	)
	private String hostname;

	@Column(
			table = "dm_topic_subscriber",
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


	public DmTopicSubscriber() {} 

	public DmTopicSubscriber( ResultSet rs ) throws SQLException {

		this.topic_subscriber_id = rs.getInt( DmTopicSubscriber.Fields.topic_subscriber_id.name() );
		this.topic_publisher_id = rs.getInt( DmTopicSubscriber.Fields.topic_publisher_id.name() );
		this.consumer_bean_name = rs.getString( DmTopicSubscriber.Fields.consumer_bean_name.name() );
		this.hostname = rs.getString( DmTopicSubscriber.Fields.hostname.name() );
		this.active = rs.getString( DmTopicSubscriber.Fields.active.name() );

	}

	public DmTopicSubscriber( JSONObject jo ) throws JSONException {

		this.topic_subscriber_id = (int)jo.getInt( DmTopicSubscriber.Fields.topic_subscriber_id.name() );
		this.topic_publisher_id = (int)jo.getInt( DmTopicSubscriber.Fields.topic_publisher_id.name() );
		this.consumer_bean_name = jo.getString( DmTopicSubscriber.Fields.consumer_bean_name.name() );
		this.hostname = jo.getString( DmTopicSubscriber.Fields.hostname.name() );
		this.active = jo.getString( DmTopicSubscriber.Fields.active.name() );

	}

	public Integer getTopicSubscriberId() {

		return this.topic_subscriber_id;

	}

	public void setTopicSubscriberId( Integer topic_subscriber_id ) {

		this.topic_subscriber_id = topic_subscriber_id;

	}

	public Integer getTopicPublisherId() {

		return this.topic_publisher_id;

	}

	public void setTopicPublisherId( Integer topic_publisher_id ) {

		this.topic_publisher_id = topic_publisher_id;

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

		return DmTopicSubscriber.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"topic_subscriber_id\": \"" ).append( this.getTopicSubscriberId() ).append( "\", " )
			.append( "\"topic_publisher_id\": \"" ).append( this.getTopicPublisherId() ).append( "\", " )
			.append( "\"consumer_bean_name\": \"" ).append( this.getConsumerBeanName() ).append( "\", " )
			.append( "\"hostname\": \"" ).append( this.getHostname() ).append( "\", " )
			.append( "\"active\": \"" ).append( this.getActive() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }