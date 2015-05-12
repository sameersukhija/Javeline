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


@Table( "stats" )
public class Stats { 

	public enum Fields { stats_date, type, name, process_id, status, value, minute, update_time }

	@Column(
			table = "stats",
			field = "stats_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "",
			getMethod = "getStatsDate",
			setMethod = "setStatsDate"
	)
	private Date stats_date;

	@Column(
			table = "stats",
			field = "type",
			type = "varchar(30)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 30,
			comment = "",
			getMethod = "getType",
			setMethod = "setType"
	)
	private String type;

	@Column(
			table = "stats",
			field = "name",
			type = "varchar(30)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 30,
			comment = "",
			getMethod = "getName",
			setMethod = "setName"
	)
	private String name;

	@Column(
			table = "stats",
			field = "process_id",
			type = "varchar(30)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 30,
			comment = "",
			getMethod = "getProcessId",
			setMethod = "setProcessId"
	)
	private String process_id;

	@Column(
			table = "stats",
			field = "status",
			type = "varchar(30)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 30,
			comment = "",
			getMethod = "getStatus",
			setMethod = "setStatus"
	)
	private String status;

	@Column(
			table = "stats",
			field = "value",
			type = "bigint(20)",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 20,
			comment = "",
			getMethod = "getValue",
			setMethod = "setValue"
	)
	private Long value;

	@Column(
			table = "stats",
			field = "minute",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "0",
			extra = "",
			length = 11,
			comment = "",
			getMethod = "getMinute",
			setMethod = "setMinute"
	)
	private Integer minute;

	@Column(
			table = "stats",
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


	public Stats() {} 

	public Stats( ResultSet rs ) throws SQLException {

		this.stats_date = rs.getDate( Stats.Fields.stats_date.name() );
		this.type = rs.getString( Stats.Fields.type.name() );
		this.name = rs.getString( Stats.Fields.name.name() );
		this.process_id = rs.getString( Stats.Fields.process_id.name() );
		this.status = rs.getString( Stats.Fields.status.name() );
		this.value = rs.getLong( Stats.Fields.value.name() );
		this.minute = rs.getInt( Stats.Fields.minute.name() );
		this.update_time = rs.getTimestamp( Stats.Fields.update_time.name() );

	}

	public Stats( JSONObject jo ) throws JSONException, ParseException {

		this.stats_date = Format.getMysqlDateTime( jo.getString( Stats.Fields.stats_date.name() ) );
		this.type = jo.getString( Stats.Fields.type.name() );
		this.name = jo.getString( Stats.Fields.name.name() );
		this.process_id = jo.getString( Stats.Fields.process_id.name() );
		this.status = jo.getString( Stats.Fields.status.name() );
		this.value = (long)jo.getLong( Stats.Fields.value.name() );
		this.minute = (int)jo.getInt( Stats.Fields.minute.name() );
		this.update_time = new Timestamp( Format.getMysqlDateTime( jo.getString( Stats.Fields.update_time.name() ) ).getTime() );

	}

	public Date getStatsDate() {

		return this.stats_date;

	}

	public Stats setStatsDate( Date stats_date ) {

		this.stats_date = stats_date;

		return this;

	}

	public String getType() {

		return this.type;

	}

	public Stats setType( String type ) {

		this.type = type;

		return this;

	}

	public String getName() {

		return this.name;

	}

	public Stats setName( String name ) {

		this.name = name;

		return this;

	}

	public String getProcessId() {

		return this.process_id;

	}

	public Stats setProcessId( String process_id ) {

		this.process_id = process_id;

		return this;

	}

	public String getStatus() {

		return this.status;

	}

	public Stats setStatus( String status ) {

		this.status = status;

		return this;

	}

	public Long getValue() {

		return this.value;

	}

	public Stats setValue( Long value ) {

		this.value = value;

		return this;

	}

	public Integer getMinute() {

		return this.minute;

	}

	public Stats setMinute( Integer minute ) {

		this.minute = minute;

		return this;

	}

	public Timestamp getUpdateTime() {

		return this.update_time;

	}

	public Stats setUpdateTime( Timestamp update_time ) {

		this.update_time = update_time;

		return this;

	}

	public Fields[] getEntityFields() {

		return Stats.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"stats_date\": \"" ).append( this.getStatsDate() ).append( "\", " )
			.append( "\"type\": \"" ).append( this.getType() ).append( "\", " )
			.append( "\"name\": \"" ).append( this.getName() ).append( "\", " )
			.append( "\"process_id\": \"" ).append( this.getProcessId() ).append( "\", " )
			.append( "\"status\": \"" ).append( this.getStatus() ).append( "\", " )
			.append( "\"value\": \"" ).append( this.getValue() ).append( "\", " )
			.append( "\"minute\": \"" ).append( this.getMinute() ).append( "\", " )
			.append( "\"update_time\": \"" ).append( this.getUpdateTime() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }