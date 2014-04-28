package com.lumata.e4o.schema.global;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "channel_destination" )
public class ChannelDestination { 

	public enum Fields { tenant_id, channel_id, destination }

	@Column(
			table = "channel_destination",
			field = "tenant_id",
			type = "int(10) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 10,
			getMethod = "getTenantId",
			setMethod = "setTenantId"
	)
	private Integer tenant_id;

	@Column(
			table = "channel_destination",
			field = "channel_id",
			type = "int(10) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 10,
			getMethod = "getChannelId",
			setMethod = "setChannelId"
	)
	private Integer channel_id;

	@Column(
			table = "channel_destination",
			field = "destination",
			type = "varchar(512)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 512,
			getMethod = "getDestination",
			setMethod = "setDestination"
	)
	private String destination;


	public ChannelDestination() {} 

	public ChannelDestination( ResultSet rs ) throws SQLException {

		this.tenant_id = rs.getInt( ChannelDestination.Fields.tenant_id.name() );
		this.channel_id = rs.getInt( ChannelDestination.Fields.channel_id.name() );
		this.destination = rs.getString( ChannelDestination.Fields.destination.name() );

	}

	public ChannelDestination( JSONObject jo ) throws JSONException {

		this.tenant_id = (int)jo.getInt( ChannelDestination.Fields.tenant_id.name() );
		this.channel_id = (int)jo.getInt( ChannelDestination.Fields.channel_id.name() );
		this.destination = jo.getString( ChannelDestination.Fields.destination.name() );

	}

	public Integer getTenantId() {

		return this.tenant_id;

	}

	public void setTenantId( Integer tenant_id ) {

		this.tenant_id = tenant_id;

	}

	public Integer getChannelId() {

		return this.channel_id;

	}

	public void setChannelId( Integer channel_id ) {

		this.channel_id = channel_id;

	}

	public String getDestination() {

		return this.destination;

	}

	public void setDestination( String destination ) {

		this.destination = destination;

	}

	public Fields[] getEntityFields() {

		return ChannelDestination.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"tenant_id\": \"" ).append( this.getTenantId() ).append( "\", " )
			.append( "\"channel_id\": \"" ).append( this.getChannelId() ).append( "\", " )
			.append( "\"destination\": \"" ).append( this.getDestination() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }