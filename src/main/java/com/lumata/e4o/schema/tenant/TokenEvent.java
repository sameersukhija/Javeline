package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.util.Date;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;
import java.sql.Timestamp;


@Table( "token_event" )
public class TokenEvent { 

	public enum Fields { token_event_id, token_code, msisdn, event_date, type, success, warning, allocate_is_auto, user_accept_channel, extra, update_time }

	@Column(
			table = "token_event",
			field = "token_event_id",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = true,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 20,
			comment = "it represents the primary key associated with the event",
			getMethod = "getTokenEventId",
			setMethod = "setTokenEventId"
	)
	private Long token_event_id;

	@Column(
			table = "token_event",
			field = "token_code",
			type = "varchar(60)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 60,
			comment = "it represents the value of the token",
			getMethod = "getTokenCode",
			setMethod = "setTokenCode"
	)
	private String token_code;

	@Column(
			table = "token_event",
			field = "msisdn",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 20,
			comment = "it represents the MSISDN associated to this token",
			getMethod = "getMsisdn",
			setMethod = "setMsisdn"
	)
	private Long msisdn;

	@Column(
			table = "token_event",
			field = "event_date",
			type = "datetime",
			mysqlType = "datetime",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "it stores the date and time of the event related to the token",
			getMethod = "getEventDate",
			setMethod = "setEventDate"
	)
	private Date event_date;

	@Column(
			table = "token_event",
			field = "type",
			type = "enum('CREATE','ALLOCATE','ACCEPT','REFUSE-ALL','RESEND')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 5,
			comment = "it stores the type of the event",
			getMethod = "getType",
			setMethod = "setType"
	)
	private String type;

	@Column(
			table = "token_event",
			field = "success",
			type = "tinyint(1) unsigned",
			mysqlType = "tinyint",
			javaType = "Boolean",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 1,
			comment = "it represents the fact that the event has been executed correctly",
			getMethod = "getSuccess",
			setMethod = "setSuccess"
	)
	private Boolean success;

	@Column(
			table = "token_event",
			field = "warning",
			type = "tinyint(1) unsigned",
			mysqlType = "tinyint",
			javaType = "Boolean",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 1,
			comment = "it represents the fact that the event was not allowed in the current token status",
			getMethod = "getWarning",
			setMethod = "setWarning"
	)
	private Boolean warning;

	@Column(
			table = "token_event",
			field = "allocate_is_auto",
			type = "tinyint(1) unsigned",
			mysqlType = "tinyint",
			javaType = "Boolean",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 1,
			comment = "it represents the fact that in case the event is an 'ALLOCATE' it has been executed automatically by the system",
			getMethod = "getAllocateIsAuto",
			setMethod = "setAllocateIsAuto"
	)
	private Boolean allocate_is_auto;

	@Column(
			table = "token_event",
			field = "user_accept_channel",
			type = "varchar(250)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 250,
			comment = "it stores the channel the user used to make an 'ACCEPT'. It will be NULL in case the type is not 'ACCEPT'",
			getMethod = "getUserAcceptChannel",
			setMethod = "setUserAcceptChannel"
	)
	private String user_accept_channel;

	@Column(
			table = "token_event",
			field = "extra",
			type = "text",
			mysqlType = "text",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "it stores extra information in JSON format if needed",
			getMethod = "getExtra",
			setMethod = "setExtra"
	)
	private String extra;

	@Column(
			table = "token_event",
			field = "update_time",
			type = "timestamp",
			mysqlType = "timestamp",
			javaType = "Timestamp",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "CURRENT_TIMESTAMP",
			extra = "on update CURRENT_TIMESTAMP",
			length = 0,
			comment = "",
			getMethod = "getUpdateTime",
			setMethod = "setUpdateTime"
	)
	private Timestamp update_time;


	public TokenEvent() {} 

	public TokenEvent( ResultSet rs ) throws SQLException {

		this.token_event_id = rs.getLong( TokenEvent.Fields.token_event_id.name() );
		this.token_code = rs.getString( TokenEvent.Fields.token_code.name() );
		this.msisdn = rs.getLong( TokenEvent.Fields.msisdn.name() );
		this.event_date = rs.getDate( TokenEvent.Fields.event_date.name() );
		this.type = rs.getString( TokenEvent.Fields.type.name() );
		this.success = rs.getBoolean( TokenEvent.Fields.success.name() );
		this.warning = rs.getBoolean( TokenEvent.Fields.warning.name() );
		this.allocate_is_auto = rs.getBoolean( TokenEvent.Fields.allocate_is_auto.name() );
		this.user_accept_channel = rs.getString( TokenEvent.Fields.user_accept_channel.name() );
		this.extra = rs.getString( TokenEvent.Fields.extra.name() );
		this.update_time = rs.getTimestamp( TokenEvent.Fields.update_time.name() );

	}

	public TokenEvent( JSONObject jo ) throws JSONException, ParseException {

		this.token_event_id = (long)jo.getLong( TokenEvent.Fields.token_event_id.name() );
		this.token_code = jo.getString( TokenEvent.Fields.token_code.name() );
		this.msisdn = (long)jo.getLong( TokenEvent.Fields.msisdn.name() );
		this.event_date = Format.getMysqlDateTime( jo.getString( TokenEvent.Fields.event_date.name() ) );
		this.type = jo.getString( TokenEvent.Fields.type.name() );
		this.success = jo.getBoolean( TokenEvent.Fields.success.name() );
		this.warning = jo.getBoolean( TokenEvent.Fields.warning.name() );
		this.allocate_is_auto = jo.getBoolean( TokenEvent.Fields.allocate_is_auto.name() );
		this.user_accept_channel = jo.getString( TokenEvent.Fields.user_accept_channel.name() );
		this.extra = jo.getString( TokenEvent.Fields.extra.name() );
		this.update_time = new Timestamp( Format.getMysqlDateTime( jo.getString( TokenEvent.Fields.update_time.name() ) ).getTime() );

	}

	public Long getTokenEventId() {

		return this.token_event_id;

	}

	public void setTokenEventId( Long token_event_id ) {

		this.token_event_id = token_event_id;

	}

	public String getTokenCode() {

		return this.token_code;

	}

	public void setTokenCode( String token_code ) {

		this.token_code = token_code;

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public void setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

	}

	public Date getEventDate() {

		return this.event_date;

	}

	public void setEventDate( Date event_date ) {

		this.event_date = event_date;

	}

	public String getType() {

		return this.type;

	}

	public void setType( String type ) {

		this.type = type;

	}

	public Boolean getSuccess() {

		return this.success;

	}

	public void setSuccess( Boolean success ) {

		this.success = success;

	}

	public Boolean getWarning() {

		return this.warning;

	}

	public void setWarning( Boolean warning ) {

		this.warning = warning;

	}

	public Boolean getAllocateIsAuto() {

		return this.allocate_is_auto;

	}

	public void setAllocateIsAuto( Boolean allocate_is_auto ) {

		this.allocate_is_auto = allocate_is_auto;

	}

	public String getUserAcceptChannel() {

		return this.user_accept_channel;

	}

	public void setUserAcceptChannel( String user_accept_channel ) {

		this.user_accept_channel = user_accept_channel;

	}

	public String getExtra() {

		return this.extra;

	}

	public void setExtra( String extra ) {

		this.extra = extra;

	}

	public Timestamp getUpdateTime() {

		return this.update_time;

	}

	public void setUpdateTime( Timestamp update_time ) {

		this.update_time = update_time;

	}

	public Fields[] getEntityFields() {

		return TokenEvent.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"token_event_id\": \"" ).append( this.getTokenEventId() ).append( "\", " )
			.append( "\"token_code\": \"" ).append( this.getTokenCode() ).append( "\", " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"event_date\": \"" ).append( this.getEventDate() ).append( "\", " )
			.append( "\"type\": \"" ).append( this.getType() ).append( "\", " )
			.append( "\"success\": \"" ).append( this.getSuccess() ).append( "\", " )
			.append( "\"warning\": \"" ).append( this.getWarning() ).append( "\", " )
			.append( "\"allocate_is_auto\": \"" ).append( this.getAllocateIsAuto() ).append( "\", " )
			.append( "\"user_accept_channel\": \"" ).append( this.getUserAcceptChannel() ).append( "\", " )
			.append( "\"extra\": \"" ).append( this.getExtra() ).append( "\", " )
			.append( "\"update_time\": \"" ).append( this.getUpdateTime() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }