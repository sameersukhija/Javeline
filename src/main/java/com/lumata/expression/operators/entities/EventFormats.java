package com.lumata.expression.operators.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.util.Date;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "event_formats" )
public class EventFormats { 

	public enum Fields { id, creation_date, format }

	@Column(
			table = "event_formats",
			field = "id",
			type = "varchar(32)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 32,
			getMethod = "getId",
			setMethod = "setId"
	)
	private String id;

	@Column(
			table = "event_formats",
			field = "creation_date",
			type = "datetime",
			mysqlType = "datetime",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getCreationDate",
			setMethod = "setCreationDate"
	)
	private Date creation_date;

	@Column(
			table = "event_formats",
			field = "format",
			type = "varchar(512)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 512,
			getMethod = "getFormat",
			setMethod = "setFormat"
	)
	private String format;


	public EventFormats() {} 

	public EventFormats( ResultSet rs ) throws SQLException {

		this.id = rs.getString( EventFormats.Fields.id.name() );
		this.creation_date = rs.getDate( EventFormats.Fields.creation_date.name() );
		this.format = rs.getString( EventFormats.Fields.format.name() );

	}

	public EventFormats( JSONObject jo ) throws JSONException, ParseException {

		this.id = jo.getString( EventFormats.Fields.id.name() );
		this.creation_date = Format.getMysqlDateTime( jo.getString( EventFormats.Fields.creation_date.name() ) );
		this.format = jo.getString( EventFormats.Fields.format.name() );

	}

	public String getId() {

		return this.id;

	}

	public void setId( String id ) {

		this.id = id;

	}

	public Date getCreationDate() {

		return this.creation_date;

	}

	public void setCreationDate( Date creation_date ) {

		this.creation_date = creation_date;

	}

	public String getFormat() {

		return this.format;

	}

	public void setFormat( String format ) {

		this.format = format;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"id\": \"" ).append( this.getId() ).append( "\", " )
			.append( "\"creation_date\": \"" ).append( this.getCreationDate() ).append( "\", " )
			.append( "\"format\": \"" ).append( this.getFormat() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }