package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "sales_channels" )
public class SalesChannels { 

	public enum Fields { channel_id, channel_name, active }

	@Column(
			table = "sales_channels",
			field = "channel_id",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = true,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 5,
			comment = "",
			getMethod = "getChannelId",
			setMethod = "setChannelId"
	)
	private Short channel_id;

	@Column(
			table = "sales_channels",
			field = "channel_name",
			type = "varchar(45)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "UNI",
			defaultValue = "",
			extra = "",
			length = 45,
			comment = "",
			getMethod = "getChannelName",
			setMethod = "setChannelName"
	)
	private String channel_name;

	@Column(
			table = "sales_channels",
			field = "active",
			type = "enum('true','false')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "true",
			extra = "",
			length = 2,
			comment = "",
			getMethod = "getActive",
			setMethod = "setActive"
	)
	private String active;


	public SalesChannels() {} 

	public SalesChannels( ResultSet rs ) throws SQLException {

		this.channel_id = rs.getShort( SalesChannels.Fields.channel_id.name() );
		this.channel_name = rs.getString( SalesChannels.Fields.channel_name.name() );
		this.active = rs.getString( SalesChannels.Fields.active.name() );

	}

	public SalesChannels( JSONObject jo ) throws JSONException {

		this.channel_id = (short)jo.getInt( SalesChannels.Fields.channel_id.name() );
		this.channel_name = jo.getString( SalesChannels.Fields.channel_name.name() );
		this.active = jo.getString( SalesChannels.Fields.active.name() );

	}

	public Short getChannelId() {

		return this.channel_id;

	}

	public void setChannelId( Short channel_id ) {

		this.channel_id = channel_id;

	}

	public String getChannelName() {

		return this.channel_name;

	}

	public void setChannelName( String channel_name ) {

		this.channel_name = channel_name;

	}

	public String getActive() {

		return this.active;

	}

	public void setActive( String active ) {

		this.active = active;

	}

	public Fields[] getEntityFields() {

		return SalesChannels.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"channel_id\": \"" ).append( this.getChannelId() ).append( "\", " )
			.append( "\"channel_name\": \"" ).append( this.getChannelName() ).append( "\", " )
			.append( "\"active\": \"" ).append( this.getActive() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }