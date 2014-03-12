package com.lumata.expression.operators.testing.pojo.autogenerator;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.util.Date;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "conf_update_log" )
public class ConfUpdateLog { 

	public enum Fields { rrd_key, time, user, command }

	@Column(
			table = "conf_update_log",
			field = "rrd_key",
			type = "int(10) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 10,
			getMethod = "getRrdKey",
			setMethod = "setRrdKey"
	)
	private Integer rrd_key;

	@Column(
			table = "conf_update_log",
			field = "time",
			type = "datetime",
			mysqlType = "datetime",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getTime",
			setMethod = "setTime"
	)
	private Date time;

	@Column(
			table = "conf_update_log",
			field = "user",
			type = "varchar(50)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 50,
			getMethod = "getUser",
			setMethod = "setUser"
	)
	private String user;

	@Column(
			table = "conf_update_log",
			field = "command",
			type = "varchar(1024)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 1024,
			getMethod = "getCommand",
			setMethod = "setCommand"
	)
	private String command;


	public ConfUpdateLog() {} 

	public ConfUpdateLog( ResultSet rs ) throws SQLException {

		this.rrd_key = rs.getInt( ConfUpdateLog.Fields.rrd_key.name() );
		this.time = rs.getDate( ConfUpdateLog.Fields.time.name() );
		this.user = rs.getString( ConfUpdateLog.Fields.user.name() );
		this.command = rs.getString( ConfUpdateLog.Fields.command.name() );

	}

	public ConfUpdateLog( JSONObject jo ) throws JSONException, ParseException {

		this.rrd_key = (int)jo.getInt( ConfUpdateLog.Fields.rrd_key.name() );
		this.time = Format.getMysqlDateTime( jo.getString( ConfUpdateLog.Fields.time.name() ) );
		this.user = jo.getString( ConfUpdateLog.Fields.user.name() );
		this.command = jo.getString( ConfUpdateLog.Fields.command.name() );

	}

	public Integer getRrdKey() {

		return this.rrd_key;

	}

	public void setRrdKey( Integer rrd_key ) {

		this.rrd_key = rrd_key;

	}

	public Date getTime() {

		return this.time;

	}

	public void setTime( Date time ) {

		this.time = time;

	}

	public String getUser() {

		return this.user;

	}

	public void setUser( String user ) {

		this.user = user;

	}

	public String getCommand() {

		return this.command;

	}

	public void setCommand( String command ) {

		this.command = command;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"rrd_key\": \"" ).append( this.getRrdKey() ).append( "\", " )
			.append( "\"time\": \"" ).append( this.getTime() ).append( "\", " )
			.append( "\"user\": \"" ).append( this.getUser() ).append( "\", " )
			.append( "\"command\": \"" ).append( this.getCommand() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }