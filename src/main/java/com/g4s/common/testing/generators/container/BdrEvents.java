package com.g4s.common.testing.generators.container;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.g4s.common.testing.annotations.mysql.Column;
import com.g4s.common.testing.annotations.mysql.Table;
import com.g4s.common.testing.validating.Format;

import java.util.Date;
import java.text.ParseException;


@Table( "bdr_events" )
public class BdrEvents { 

	public enum Fields { format, msisdn, event_date, transaction_id, event_data }

	@Column(
			table = "bdr_events",
			field = "format",
			type = "varchar(32)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 32,
			getMethod = "getFormat",
			setMethod = "setFormat"
	)
	private String format;

	@Column(
			table = "bdr_events",
			field = "msisdn",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getMsisdn",
			setMethod = "setMsisdn"
	)
	private Long msisdn;

	@Column(
			table = "bdr_events",
			field = "event_date",
			type = "datetime",
			mysqlType = "datetime",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getEventDate",
			setMethod = "setEventDate"
	)
	private Date event_date;

	@Column(
			table = "bdr_events",
			field = "transaction_id",
			type = "varchar(40)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 40,
			getMethod = "getTransactionId",
			setMethod = "setTransactionId"
	)
	private String transaction_id;

	@Column(
			table = "bdr_events",
			field = "event_data",
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
			getMethod = "getEventData",
			setMethod = "setEventData"
	)
	private String event_data;


	public BdrEvents() {} 

	public BdrEvents( ResultSet rs ) throws SQLException {

		this.format = rs.getString( BdrEvents.Fields.format.name() );
		this.msisdn = rs.getLong( BdrEvents.Fields.msisdn.name() );
		this.event_date = rs.getDate( BdrEvents.Fields.event_date.name() );
		this.transaction_id = rs.getString( BdrEvents.Fields.transaction_id.name() );
		this.event_data = rs.getString( BdrEvents.Fields.event_data.name() );

	}

	public BdrEvents( JSONObject jo ) throws JSONException, ParseException {

		this.format = jo.getString( BdrEvents.Fields.format.name() );
		this.msisdn = (long)jo.getLong( BdrEvents.Fields.msisdn.name() );
		this.event_date = Format.getMysqlDateTime( jo.getString( BdrEvents.Fields.event_date.name() ) );
		this.transaction_id = jo.getString( BdrEvents.Fields.transaction_id.name() );
		this.event_data = jo.getString( BdrEvents.Fields.event_data.name() );

	}

	public String getFormat() {

		return this.format;

	}

	public void setFormat( String format ) {

		this.format = format;

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

	public String getTransactionId() {

		return this.transaction_id;

	}

	public void setTransactionId( String transaction_id ) {

		this.transaction_id = transaction_id;

	}

	public String getEventData() {

		return this.event_data;

	}

	public void setEventData( String event_data ) {

		this.event_data = event_data;

	}

	public Fields[] getEntityFields() {

		return BdrEvents.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"format\": \"" ).append( this.getFormat() ).append( "\", " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"event_date\": \"" ).append( this.getEventDate() ).append( "\", " )
			.append( "\"transaction_id\": \"" ).append( this.getTransactionId() ).append( "\", " )
			.append( "\"event_data\": \"" ).append( this.getEventData() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }