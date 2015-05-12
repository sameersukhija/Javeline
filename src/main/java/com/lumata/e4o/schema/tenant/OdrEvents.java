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


@Table( "odr_events" )
public class OdrEvents { 

	public enum Fields { format, msisdn, event_date, transaction_id, event_data, id, insert_time }

	@Column(
			table = "odr_events",
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
			comment = "",
			getMethod = "getFormat",
			setMethod = "setFormat"
	)
	private String format;

	@Column(
			table = "odr_events",
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
			comment = "",
			getMethod = "getMsisdn",
			setMethod = "setMsisdn"
	)
	private Long msisdn;

	@Column(
			table = "odr_events",
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
			comment = "",
			getMethod = "getEventDate",
			setMethod = "setEventDate"
	)
	private Date event_date;

	@Column(
			table = "odr_events",
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
			comment = "",
			getMethod = "getTransactionId",
			setMethod = "setTransactionId"
	)
	private String transaction_id;

	@Column(
			table = "odr_events",
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
			comment = "",
			getMethod = "getEventData",
			setMethod = "setEventData"
	)
	private String event_data;

	@Column(
			table = "odr_events",
			field = "id",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = true,
			key = "MUL",
			defaultValue = "null",
			extra = "auto_increment",
			length = 11,
			comment = "",
			getMethod = "getId",
			setMethod = "setId"
	)
	private Integer id;

	@Column(
			table = "odr_events",
			field = "insert_time",
			type = "timestamp",
			mysqlType = "timestamp",
			javaType = "Timestamp",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "CURRENT_TIMESTAMP",
			extra = "",
			length = 0,
			comment = "",
			getMethod = "getInsertTime",
			setMethod = "setInsertTime"
	)
	private Timestamp insert_time;


	public OdrEvents() {} 

	public OdrEvents( ResultSet rs ) throws SQLException {

		this.format = rs.getString( OdrEvents.Fields.format.name() );
		this.msisdn = rs.getLong( OdrEvents.Fields.msisdn.name() );
		this.event_date = rs.getDate( OdrEvents.Fields.event_date.name() );
		this.transaction_id = rs.getString( OdrEvents.Fields.transaction_id.name() );
		this.event_data = rs.getString( OdrEvents.Fields.event_data.name() );
		this.id = rs.getInt( OdrEvents.Fields.id.name() );
		this.insert_time = rs.getTimestamp( OdrEvents.Fields.insert_time.name() );

	}

	public OdrEvents( JSONObject jo ) throws JSONException, ParseException {

		this.format = jo.getString( OdrEvents.Fields.format.name() );
		this.msisdn = (long)jo.getLong( OdrEvents.Fields.msisdn.name() );
		this.event_date = Format.getMysqlDateTime( jo.getString( OdrEvents.Fields.event_date.name() ) );
		this.transaction_id = jo.getString( OdrEvents.Fields.transaction_id.name() );
		this.event_data = jo.getString( OdrEvents.Fields.event_data.name() );
		this.id = (int)jo.getInt( OdrEvents.Fields.id.name() );
		this.insert_time = new Timestamp( Format.getMysqlDateTime( jo.getString( OdrEvents.Fields.insert_time.name() ) ).getTime() );

	}

	public String getFormat() {

		return this.format;

	}

	public OdrEvents setFormat( String format ) {

		this.format = format;

		return this;

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public OdrEvents setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

		return this;

	}

	public Date getEventDate() {

		return this.event_date;

	}

	public OdrEvents setEventDate( Date event_date ) {

		this.event_date = event_date;

		return this;

	}

	public String getTransactionId() {

		return this.transaction_id;

	}

	public OdrEvents setTransactionId( String transaction_id ) {

		this.transaction_id = transaction_id;

		return this;

	}

	public String getEventData() {

		return this.event_data;

	}

	public OdrEvents setEventData( String event_data ) {

		this.event_data = event_data;

		return this;

	}

	public Integer getId() {

		return this.id;

	}

	public OdrEvents setId( Integer id ) {

		this.id = id;

		return this;

	}

	public Timestamp getInsertTime() {

		return this.insert_time;

	}

	public OdrEvents setInsertTime( Timestamp insert_time ) {

		this.insert_time = insert_time;

		return this;

	}

	public Fields[] getEntityFields() {

		return OdrEvents.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"format\": \"" ).append( this.getFormat() ).append( "\", " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"event_date\": \"" ).append( this.getEventDate() ).append( "\", " )
			.append( "\"transaction_id\": \"" ).append( this.getTransactionId() ).append( "\", " )
			.append( "\"event_data\": \"" ).append( this.getEventData() ).append( "\", " )
			.append( "\"id\": \"" ).append( this.getId() ).append( "\", " )
			.append( "\"insert_time\": \"" ).append( this.getInsertTime() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }