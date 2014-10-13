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


@Table( "conf" )
public class Conf { 

	public enum Fields { name, position, section, process_id, auth_group, current, previous, dyn_static, time, type, description }

	@Column(
			table = "conf",
			field = "name",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 255,
			getMethod = "getName",
			setMethod = "setName"
	)
	private String name;

	@Column(
			table = "conf",
			field = "position",
			type = "tinyint(1) unsigned",
			mysqlType = "tinyint",
			javaType = "Boolean",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "0",
			extra = "",
			length = 1,
			getMethod = "getPosition",
			setMethod = "setPosition"
	)
	private Boolean position;

	@Column(
			table = "conf",
			field = "section",
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
			getMethod = "getSection",
			setMethod = "setSection"
	)
	private String section;

	@Column(
			table = "conf",
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
			getMethod = "getProcessId",
			setMethod = "setProcessId"
	)
	private String process_id;

	@Column(
			table = "conf",
			field = "auth_group",
			type = "enum('Customer','Admin','Internal')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "Internal",
			extra = "",
			length = 3,
			getMethod = "getAuthGroup",
			setMethod = "setAuthGroup"
	)
	private String auth_group;

	@Column(
			table = "conf",
			field = "current",
			type = "varchar(512)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 512,
			getMethod = "getCurrent",
			setMethod = "setCurrent"
	)
	private String current;

	@Column(
			table = "conf",
			field = "previous",
			type = "varchar(512)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 512,
			getMethod = "getPrevious",
			setMethod = "setPrevious"
	)
	private String previous;

	@Column(
			table = "conf",
			field = "dyn_static",
			type = "enum('RW','RW_INITIAL','RO')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "RO",
			extra = "",
			length = 3,
			getMethod = "getDynStatic",
			setMethod = "setDynStatic"
	)
	private String dyn_static;

	@Column(
			table = "conf",
			field = "time",
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
			getMethod = "getTime",
			setMethod = "setTime"
	)
	private Date time;

	@Column(
			table = "conf",
			field = "type",
			type = "enum('Value','SQL','FILE','System')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "Value",
			extra = "",
			length = 4,
			getMethod = "getType",
			setMethod = "setType"
	)
	private String type;

	@Column(
			table = "conf",
			field = "description",
			type = "varchar(512)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 512,
			getMethod = "getDescription",
			setMethod = "setDescription"
	)
	private String description;


	public Conf() {} 

	public Conf( ResultSet rs ) throws SQLException {

		this.name = rs.getString( Conf.Fields.name.name() );
		this.position = rs.getBoolean( Conf.Fields.position.name() );
		this.section = rs.getString( Conf.Fields.section.name() );
		this.process_id = rs.getString( Conf.Fields.process_id.name() );
		this.auth_group = rs.getString( Conf.Fields.auth_group.name() );
		this.current = rs.getString( Conf.Fields.current.name() );
		this.previous = rs.getString( Conf.Fields.previous.name() );
		this.dyn_static = rs.getString( Conf.Fields.dyn_static.name() );
		this.time = rs.getDate( Conf.Fields.time.name() );
		this.type = rs.getString( Conf.Fields.type.name() );
		this.description = rs.getString( Conf.Fields.description.name() );

	}

	public Conf( JSONObject jo ) throws JSONException, ParseException {

		this.name = jo.getString( Conf.Fields.name.name() );
		this.position = jo.getBoolean( Conf.Fields.position.name() );
		this.section = jo.getString( Conf.Fields.section.name() );
		this.process_id = jo.getString( Conf.Fields.process_id.name() );
		this.auth_group = jo.getString( Conf.Fields.auth_group.name() );
		this.current = jo.getString( Conf.Fields.current.name() );
		this.previous = jo.getString( Conf.Fields.previous.name() );
		this.dyn_static = jo.getString( Conf.Fields.dyn_static.name() );
		this.time = Format.getMysqlDateTime( jo.getString( Conf.Fields.time.name() ) );
		this.type = jo.getString( Conf.Fields.type.name() );
		this.description = jo.getString( Conf.Fields.description.name() );

	}

	public String getName() {

		return this.name;

	}

	public void setName( String name ) {

		this.name = name;

	}

	public Boolean getPosition() {

		return this.position;

	}

	public void setPosition( Boolean position ) {

		this.position = position;

	}

	public String getSection() {

		return this.section;

	}

	public void setSection( String section ) {

		this.section = section;

	}

	public String getProcessId() {

		return this.process_id;

	}

	public void setProcessId( String process_id ) {

		this.process_id = process_id;

	}

	public String getAuthGroup() {

		return this.auth_group;

	}

	public void setAuthGroup( String auth_group ) {

		this.auth_group = auth_group;

	}

	public String getCurrent() {

		return this.current;

	}

	public void setCurrent( String current ) {

		this.current = current;

	}

	public String getPrevious() {

		return this.previous;

	}

	public void setPrevious( String previous ) {

		this.previous = previous;

	}

	public String getDynStatic() {

		return this.dyn_static;

	}

	public void setDynStatic( String dyn_static ) {

		this.dyn_static = dyn_static;

	}

	public Date getTime() {

		return this.time;

	}

	public void setTime( Date time ) {

		this.time = time;

	}

	public String getType() {

		return this.type;

	}

	public void setType( String type ) {

		this.type = type;

	}

	public String getDescription() {

		return this.description;

	}

	public void setDescription( String description ) {

		this.description = description;

	}

	public Fields[] getEntityFields() {

		return Conf.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"name\": \"" ).append( this.getName() ).append( "\", " )
			.append( "\"position\": \"" ).append( this.getPosition() ).append( "\", " )
			.append( "\"section\": \"" ).append( this.getSection() ).append( "\", " )
			.append( "\"process_id\": \"" ).append( this.getProcessId() ).append( "\", " )
			.append( "\"auth_group\": \"" ).append( this.getAuthGroup() ).append( "\", " )
			.append( "\"current\": \"" ).append( this.getCurrent() ).append( "\", " )
			.append( "\"previous\": \"" ).append( this.getPrevious() ).append( "\", " )
			.append( "\"dyn_static\": \"" ).append( this.getDynStatic() ).append( "\", " )
			.append( "\"time\": \"" ).append( this.getTime() ).append( "\", " )
			.append( "\"type\": \"" ).append( this.getType() ).append( "\", " )
			.append( "\"description\": \"" ).append( this.getDescription() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }