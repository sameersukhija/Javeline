package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "set_options" )
public class SetOptions { 

	public enum Fields { options, options_name }

	@Column(
			table = "set_options",
			field = "options",
			type = "bigint(20)",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getOptions",
			setMethod = "setOptions"
	)
	private Long options;

	@Column(
			table = "set_options",
			field = "options_name",
			type = "varchar(100)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 100,
			getMethod = "getOptionsName",
			setMethod = "setOptionsName"
	)
	private String options_name;


	public SetOptions() {} 

	public SetOptions( ResultSet rs ) throws SQLException {

		this.options = rs.getLong( SetOptions.Fields.options.name() );
		this.options_name = rs.getString( SetOptions.Fields.options_name.name() );

	}

	public SetOptions( JSONObject jo ) throws JSONException {

		this.options = (long)jo.getLong( SetOptions.Fields.options.name() );
		this.options_name = jo.getString( SetOptions.Fields.options_name.name() );

	}

	public Long getOptions() {

		return this.options;

	}

	public void setOptions( Long options ) {

		this.options = options;

	}

	public String getOptionsName() {

		return this.options_name;

	}

	public void setOptionsName( String options_name ) {

		this.options_name = options_name;

	}

	public Fields[] getEntityFields() {

		return SetOptions.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"options\": \"" ).append( this.getOptions() ).append( "\", " )
			.append( "\"options_name\": \"" ).append( this.getOptionsName() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }