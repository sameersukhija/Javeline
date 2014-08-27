package com.lumata.e4o.schema.dialogmanager;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "dm_topic_publisher" )
public class DmTopicPublisher { 

	public enum Fields { topic_publisher_id, jms_connection_id, tenant_identifier, topic_name, active }

	@Column(
			table = "dm_topic_publisher",
			field = "topic_publisher_id",
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
			getMethod = "getTopicPublisherId",
			setMethod = "setTopicPublisherId"
	)
	private Integer topic_publisher_id;

	@Column(
			table = "dm_topic_publisher",
			field = "jms_connection_id",
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
			getMethod = "getJmsConnectionId",
			setMethod = "setJmsConnectionId"
	)
	private Integer jms_connection_id;

	@Column(
			table = "dm_topic_publisher",
			field = "tenant_identifier",
			type = "varchar(16)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "",
			extra = "",
			length = 16,
			getMethod = "getTenantIdentifier",
			setMethod = "setTenantIdentifier"
	)
	private String tenant_identifier;

	@Column(
			table = "dm_topic_publisher",
			field = "topic_name",
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
			getMethod = "getTopicName",
			setMethod = "setTopicName"
	)
	private String topic_name;

	@Column(
			table = "dm_topic_publisher",
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


	public DmTopicPublisher() {} 

	public DmTopicPublisher( ResultSet rs ) throws SQLException {

		this.topic_publisher_id = rs.getInt( DmTopicPublisher.Fields.topic_publisher_id.name() );
		this.jms_connection_id = rs.getInt( DmTopicPublisher.Fields.jms_connection_id.name() );
		this.tenant_identifier = rs.getString( DmTopicPublisher.Fields.tenant_identifier.name() );
		this.topic_name = rs.getString( DmTopicPublisher.Fields.topic_name.name() );
		this.active = rs.getString( DmTopicPublisher.Fields.active.name() );

	}

	public DmTopicPublisher( JSONObject jo ) throws JSONException {

		this.topic_publisher_id = (int)jo.getInt( DmTopicPublisher.Fields.topic_publisher_id.name() );
		this.jms_connection_id = (int)jo.getInt( DmTopicPublisher.Fields.jms_connection_id.name() );
		this.tenant_identifier = jo.getString( DmTopicPublisher.Fields.tenant_identifier.name() );
		this.topic_name = jo.getString( DmTopicPublisher.Fields.topic_name.name() );
		this.active = jo.getString( DmTopicPublisher.Fields.active.name() );

	}

	public Integer getTopicPublisherId() {

		return this.topic_publisher_id;

	}

	public void setTopicPublisherId( Integer topic_publisher_id ) {

		this.topic_publisher_id = topic_publisher_id;

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

	public String getTopicName() {

		return this.topic_name;

	}

	public void setTopicName( String topic_name ) {

		this.topic_name = topic_name;

	}

	public String getActive() {

		return this.active;

	}

	public void setActive( String active ) {

		this.active = active;

	}

	public Fields[] getEntityFields() {

		return DmTopicPublisher.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"topic_publisher_id\": \"" ).append( this.getTopicPublisherId() ).append( "\", " )
			.append( "\"jms_connection_id\": \"" ).append( this.getJmsConnectionId() ).append( "\", " )
			.append( "\"tenant_identifier\": \"" ).append( this.getTenantIdentifier() ).append( "\", " )
			.append( "\"topic_name\": \"" ).append( this.getTopicName() ).append( "\", " )
			.append( "\"active\": \"" ).append( this.getActive() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }