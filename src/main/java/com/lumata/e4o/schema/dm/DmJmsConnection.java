package com.lumata.e4o.schema.dm;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "dm_jms_connection" )
public class DmJmsConnection { 

	public enum Fields { jms_connectionId, brokerURL, description, active }

	@Column(
			table = "dm_jms_connection",
			field = "jms_connectionId",
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
			getMethod = "getJmsConnectionId",
			setMethod = "setJmsConnectionId"
	)
	private Integer jms_connectionId;

	@Column(
			table = "dm_jms_connection",
			field = "brokerURL",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 255,
			getMethod = "getBrokerURL",
			setMethod = "setBrokerURL"
	)
	private String brokerURL;

	@Column(
			table = "dm_jms_connection",
			field = "description",
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
			getMethod = "getDescription",
			setMethod = "setDescription"
	)
	private String description;

	@Column(
			table = "dm_jms_connection",
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


	public DmJmsConnection() {} 

	public DmJmsConnection( ResultSet rs ) throws SQLException {

		this.jms_connectionId = rs.getInt( DmJmsConnection.Fields.jms_connectionId.name() );
		this.brokerURL = rs.getString( DmJmsConnection.Fields.brokerURL.name() );
		this.description = rs.getString( DmJmsConnection.Fields.description.name() );
		this.active = rs.getBoolean( DmJmsConnection.Fields.active.name() );

	}

	public DmJmsConnection( JSONObject jo ) throws JSONException {

		this.jms_connectionId = (int)jo.getInt( DmJmsConnection.Fields.jms_connectionId.name() );
		this.brokerURL = jo.getString( DmJmsConnection.Fields.brokerURL.name() );
		this.description = jo.getString( DmJmsConnection.Fields.description.name() );
		this.active = jo.getBoolean( DmJmsConnection.Fields.active.name() );

	}

	public Integer getJmsConnectionId() {

		return this.jms_connectionId;

	}

	public void setJmsConnectionId( Integer jms_connectionId ) {

		this.jms_connectionId = jms_connectionId;

	}

	public String getBrokerURL() {

		return this.brokerURL;

	}

	public void setBrokerURL( String brokerURL ) {

		this.brokerURL = brokerURL;

	}

	public String getDescription() {

		return this.description;

	}

	public void setDescription( String description ) {

		this.description = description;

	}

	public Boolean getActive() {

		return this.active;

	}

	public void setActive( Boolean active ) {

		this.active = active;

	}

	public Fields[] getEntityFields() {

		return DmJmsConnection.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"jms_connectionId\": \"" ).append( this.getJmsConnectionId() ).append( "\", " )
			.append( "\"brokerURL\": \"" ).append( this.getBrokerURL() ).append( "\", " )
			.append( "\"description\": \"" ).append( this.getDescription() ).append( "\", " )
			.append( "\"active\": \"" ).append( this.getActive() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }