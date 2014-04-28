package com.lumata.e4o.schema.global;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "tabs" )
public class Tabs { 

	public enum Fields { id, name, level }

	@Column(
			table = "tabs",
			field = "id",
			type = "tinyint(1) unsigned",
			mysqlType = "tinyint",
			javaType = "Boolean",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = true,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 1,
			getMethod = "getId",
			setMethod = "setId"
	)
	private Boolean id;

	@Column(
			table = "tabs",
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
			getMethod = "getName",
			setMethod = "setName"
	)
	private String name;

	@Column(
			table = "tabs",
			field = "level",
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
			getMethod = "getLevel",
			setMethod = "setLevel"
	)
	private Boolean level;


	public Tabs() {} 

	public Tabs( ResultSet rs ) throws SQLException {

		this.id = rs.getBoolean( Tabs.Fields.id.name() );
		this.name = rs.getString( Tabs.Fields.name.name() );
		this.level = rs.getBoolean( Tabs.Fields.level.name() );

	}

	public Tabs( JSONObject jo ) throws JSONException {

		this.id = jo.getBoolean( Tabs.Fields.id.name() );
		this.name = jo.getString( Tabs.Fields.name.name() );
		this.level = jo.getBoolean( Tabs.Fields.level.name() );

	}

	public Boolean getId() {

		return this.id;

	}

	public void setId( Boolean id ) {

		this.id = id;

	}

	public String getName() {

		return this.name;

	}

	public void setName( String name ) {

		this.name = name;

	}

	public Boolean getLevel() {

		return this.level;

	}

	public void setLevel( Boolean level ) {

		this.level = level;

	}

	public Fields[] getEntityFields() {

		return Tabs.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"id\": \"" ).append( this.getId() ).append( "\", " )
			.append( "\"name\": \"" ).append( this.getName() ).append( "\", " )
			.append( "\"level\": \"" ).append( this.getLevel() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }