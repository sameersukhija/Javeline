package com.lumata.e4o.schema.global;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "channel_return_code" )
public class ChannelReturnCode { 

	public enum Fields { return_code, description, channel_id, message_state }

	@Column(
			table = "channel_return_code",
			field = "return_code",
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
			getMethod = "getReturnCode",
			setMethod = "setReturnCode"
	)
	private Short return_code;

	@Column(
			table = "channel_return_code",
			field = "description",
			type = "varchar(200)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 200,
			getMethod = "getDescription",
			setMethod = "setDescription"
	)
	private String description;

	@Column(
			table = "channel_return_code",
			field = "channel_id",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getChannelId",
			setMethod = "setChannelId"
	)
	private Byte channel_id;

	@Column(
			table = "channel_return_code",
			field = "message_state",
			type = "enum('NOT_SENT','SENT','DELIVERED','EXPIRED','ERROR','NO_CHANNEL_LANGUAGE')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 6,
			getMethod = "getMessageState",
			setMethod = "setMessageState"
	)
	private String message_state;


	public ChannelReturnCode() {} 

	public ChannelReturnCode( ResultSet rs ) throws SQLException {

		this.return_code = rs.getShort( ChannelReturnCode.Fields.return_code.name() );
		this.description = rs.getString( ChannelReturnCode.Fields.description.name() );
		this.channel_id = rs.getByte( ChannelReturnCode.Fields.channel_id.name() );
		this.message_state = rs.getString( ChannelReturnCode.Fields.message_state.name() );

	}

	public ChannelReturnCode( JSONObject jo ) throws JSONException {

		this.return_code = (short)jo.getInt( ChannelReturnCode.Fields.return_code.name() );
		this.description = jo.getString( ChannelReturnCode.Fields.description.name() );
		this.channel_id = (byte)jo.getInt( ChannelReturnCode.Fields.channel_id.name() );
		this.message_state = jo.getString( ChannelReturnCode.Fields.message_state.name() );

	}

	public Short getReturnCode() {

		return this.return_code;

	}

	public void setReturnCode( Short return_code ) {

		this.return_code = return_code;

	}

	public String getDescription() {

		return this.description;

	}

	public void setDescription( String description ) {

		this.description = description;

	}

	public Byte getChannelId() {

		return this.channel_id;

	}

	public void setChannelId( Byte channel_id ) {

		this.channel_id = channel_id;

	}

	public String getMessageState() {

		return this.message_state;

	}

	public void setMessageState( String message_state ) {

		this.message_state = message_state;

	}

	public Fields[] getEntityFields() {

		return ChannelReturnCode.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"return_code\": \"" ).append( this.getReturnCode() ).append( "\", " )
			.append( "\"description\": \"" ).append( this.getDescription() ).append( "\", " )
			.append( "\"channel_id\": \"" ).append( this.getChannelId() ).append( "\", " )
			.append( "\"message_state\": \"" ).append( this.getMessageState() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }