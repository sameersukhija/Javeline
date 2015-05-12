package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "groups" )
public class Groups { 

	public enum Fields { id, name, properties, inactivity_timeout }

	@Column(
			table = "groups",
			field = "id",
			type = "smallint(3) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = true,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 3,
			comment = "",
			getMethod = "getId",
			setMethod = "setId"
	)
	private Short id;

	@Column(
			table = "groups",
			field = "name",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 255,
			comment = "",
			getMethod = "getName",
			setMethod = "setName"
	)
	private String name;

	@Column(
			table = "groups",
			field = "properties",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 255,
			comment = "",
			getMethod = "getProperties",
			setMethod = "setProperties"
	)
	private String properties;

	@Column(
			table = "groups",
			field = "inactivity_timeout",
			type = "int(10) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 10,
			comment = "",
			getMethod = "getInactivityTimeout",
			setMethod = "setInactivityTimeout"
	)
	private Integer inactivity_timeout;


	public Groups() {} 

	public Groups( ResultSet rs ) throws SQLException {

		this.id = rs.getShort( Groups.Fields.id.name() );
		this.name = rs.getString( Groups.Fields.name.name() );
		this.properties = rs.getString( Groups.Fields.properties.name() );
		this.inactivity_timeout = rs.getInt( Groups.Fields.inactivity_timeout.name() );

	}

	public Groups( JSONObject jo ) throws JSONException {

		this.id = (short)jo.getInt( Groups.Fields.id.name() );
		this.name = jo.getString( Groups.Fields.name.name() );
		this.properties = jo.getString( Groups.Fields.properties.name() );
		this.inactivity_timeout = (int)jo.getInt( Groups.Fields.inactivity_timeout.name() );

	}

	public Short getId() {

		return this.id;

	}

	public Groups setId( Short id ) {

		this.id = id;

		return this;

	}

	public String getName() {

		return this.name;

	}

	public Groups setName( String name ) {

		this.name = name;

		return this;

	}

	public String getProperties() {

		return this.properties;

	}

	public Groups setProperties( String properties ) {

		this.properties = properties;

		return this;

	}

	public Integer getInactivityTimeout() {

		return this.inactivity_timeout;

	}

	public Groups setInactivityTimeout( Integer inactivity_timeout ) {

		this.inactivity_timeout = inactivity_timeout;

		return this;

	}

	public Fields[] getEntityFields() {

		return Groups.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"id\": \"" ).append( this.getId() ).append( "\", " )
			.append( "\"name\": \"" ).append( this.getName() ).append( "\", " )
			.append( "\"properties\": \"" ).append( this.getProperties() ).append( "\", " )
			.append( "\"inactivity_timeout\": \"" ).append( this.getInactivityTimeout() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }