package com.lumata.e4o_tenant.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.sql.Timestamp;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;
import java.util.Date;


@Table( "event_files" )
public class EventFiles { 

	public enum Fields { event_name, file_name, file_time, check_time }

	@Column(
			table = "event_files",
			field = "event_name",
			type = "varchar(25)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 25,
			getMethod = "getEventName",
			setMethod = "setEventName"
	)
	private String event_name;

	@Column(
			table = "event_files",
			field = "file_name",
			type = "varchar(250)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 250,
			getMethod = "getFileName",
			setMethod = "setFileName"
	)
	private String file_name;

	@Column(
			table = "event_files",
			field = "file_time",
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
			getMethod = "getFileTime",
			setMethod = "setFileTime"
	)
	private Timestamp file_time;

	@Column(
			table = "event_files",
			field = "check_time",
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
			getMethod = "getCheckTime",
			setMethod = "setCheckTime"
	)
	private Date check_time;


	public EventFiles() {} 

	public EventFiles( ResultSet rs ) throws SQLException {

		this.event_name = rs.getString( EventFiles.Fields.event_name.name() );
		this.file_name = rs.getString( EventFiles.Fields.file_name.name() );
		this.file_time = rs.getTimestamp( EventFiles.Fields.file_time.name() );
		this.check_time = rs.getDate( EventFiles.Fields.check_time.name() );

	}

	public EventFiles( JSONObject jo ) throws JSONException, ParseException {

		this.event_name = jo.getString( EventFiles.Fields.event_name.name() );
		this.file_name = jo.getString( EventFiles.Fields.file_name.name() );
		this.file_time = new Timestamp( Format.getMysqlDateTime( jo.getString( EventFiles.Fields.file_time.name() ) ).getTime() );
		this.check_time = Format.getMysqlDateTime( jo.getString( EventFiles.Fields.check_time.name() ) );

	}

	public String getEventName() {

		return this.event_name;

	}

	public void setEventName( String event_name ) {

		this.event_name = event_name;

	}

	public String getFileName() {

		return this.file_name;

	}

	public void setFileName( String file_name ) {

		this.file_name = file_name;

	}

	public Timestamp getFileTime() {

		return this.file_time;

	}

	public void setFileTime( Timestamp file_time ) {

		this.file_time = file_time;

	}

	public Date getCheckTime() {

		return this.check_time;

	}

	public void setCheckTime( Date check_time ) {

		this.check_time = check_time;

	}

	public Fields[] getEntityFields() {

		return EventFiles.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"event_name\": \"" ).append( this.getEventName() ).append( "\", " )
			.append( "\"file_name\": \"" ).append( this.getFileName() ).append( "\", " )
			.append( "\"file_time\": \"" ).append( this.getFileTime() ).append( "\", " )
			.append( "\"check_time\": \"" ).append( this.getCheckTime() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }