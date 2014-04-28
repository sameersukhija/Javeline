package com.lumata.e4o.schema.global;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "process_ids" )
public class ProcessIds { 

	public enum Fields { process_name, process_id }

	@Column(
			table = "process_ids",
			field = "process_name",
			type = "varchar(128)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 128,
			getMethod = "getProcessName",
			setMethod = "setProcessName"
	)
	private String process_name;

	@Column(
			table = "process_ids",
			field = "process_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = true,
			key = "MUL",
			defaultValue = "null",
			extra = "auto_increment",
			length = 3,
			getMethod = "getProcessId",
			setMethod = "setProcessId"
	)
	private Byte process_id;


	public ProcessIds() {} 

	public ProcessIds( ResultSet rs ) throws SQLException {

		this.process_name = rs.getString( ProcessIds.Fields.process_name.name() );
		this.process_id = rs.getByte( ProcessIds.Fields.process_id.name() );

	}

	public ProcessIds( JSONObject jo ) throws JSONException {

		this.process_name = jo.getString( ProcessIds.Fields.process_name.name() );
		this.process_id = (byte)jo.getInt( ProcessIds.Fields.process_id.name() );

	}

	public String getProcessName() {

		return this.process_name;

	}

	public void setProcessName( String process_name ) {

		this.process_name = process_name;

	}

	public Byte getProcessId() {

		return this.process_id;

	}

	public void setProcessId( Byte process_id ) {

		this.process_id = process_id;

	}

	public Fields[] getEntityFields() {

		return ProcessIds.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"process_name\": \"" ).append( this.getProcessName() ).append( "\", " )
			.append( "\"process_id\": \"" ).append( this.getProcessId() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }