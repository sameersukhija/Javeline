package com.lumata.e4o_global.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "channels" )
public class Channels { 

	public enum Fields { channel_id, channel, priority }

	@Column(
			table = "channels",
			field = "channel_id",
			type = "int(10) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = true,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 10,
			getMethod = "getChannelId",
			setMethod = "setChannelId"
	)
	private Integer channel_id;

	@Column(
			table = "channels",
			field = "channel",
			type = "varchar(50)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 50,
			getMethod = "getChannel",
			setMethod = "setChannel"
	)
	private String channel;

	@Column(
			table = "channels",
			field = "priority",
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
			getMethod = "getPriority",
			setMethod = "setPriority"
	)
	private Integer priority;


	public Channels() {} 

	public Channels( ResultSet rs ) throws SQLException {

		this.channel_id = rs.getInt( Channels.Fields.channel_id.name() );
		this.channel = rs.getString( Channels.Fields.channel.name() );
		this.priority = rs.getInt( Channels.Fields.priority.name() );

	}

	public Channels( JSONObject jo ) throws JSONException {

		this.channel_id = (int)jo.getInt( Channels.Fields.channel_id.name() );
		this.channel = jo.getString( Channels.Fields.channel.name() );
		this.priority = (int)jo.getInt( Channels.Fields.priority.name() );

	}

	public Integer getChannelId() {

		return this.channel_id;

	}

	public void setChannelId( Integer channel_id ) {

		this.channel_id = channel_id;

	}

	public String getChannel() {

		return this.channel;

	}

	public void setChannel( String channel ) {

		this.channel = channel;

	}

	public Integer getPriority() {

		return this.priority;

	}

	public void setPriority( Integer priority ) {

		this.priority = priority;

	}

	public Fields[] getEntityFields() {

		return Channels.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"channel_id\": \"" ).append( this.getChannelId() ).append( "\", " )
			.append( "\"channel\": \"" ).append( this.getChannel() ).append( "\", " )
			.append( "\"priority\": \"" ).append( this.getPriority() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }