package com.lumata.expression.operators.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "gui_users" )
public class GuiUsers { 

	public enum Fields { id, name, password, email, agency_id }

	@Column(
			table = "gui_users",
			field = "id",
			type = "smallint(3) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 3,
			getMethod = "getId",
			setMethod = "setId"
	)
	private Short id;

	@Column(
			table = "gui_users",
			field = "name",
			type = "varchar(100)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 100,
			getMethod = "getName",
			setMethod = "setName"
	)
	private String name;

	@Column(
			table = "gui_users",
			field = "password",
			type = "varchar(100)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 100,
			getMethod = "getPassword",
			setMethod = "setPassword"
	)
	private String password;

	@Column(
			table = "gui_users",
			field = "email",
			type = "varchar(100)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 100,
			getMethod = "getEmail",
			setMethod = "setEmail"
	)
	private String email;

	@Column(
			table = "gui_users",
			field = "agency_id",
			type = "smallint(3) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getAgencyId",
			setMethod = "setAgencyId"
	)
	private Short agency_id;


	public GuiUsers() {} 

	public GuiUsers( ResultSet rs ) throws SQLException {

		this.id = rs.getShort( GuiUsers.Fields.id.name() );
		this.name = rs.getString( GuiUsers.Fields.name.name() );
		this.password = rs.getString( GuiUsers.Fields.password.name() );
		this.email = rs.getString( GuiUsers.Fields.email.name() );
		this.agency_id = rs.getShort( GuiUsers.Fields.agency_id.name() );

	}

	public GuiUsers( JSONObject jo ) throws JSONException {

		this.id = (short)jo.getInt( GuiUsers.Fields.id.name() );
		this.name = jo.getString( GuiUsers.Fields.name.name() );
		this.password = jo.getString( GuiUsers.Fields.password.name() );
		this.email = jo.getString( GuiUsers.Fields.email.name() );
		this.agency_id = (short)jo.getInt( GuiUsers.Fields.agency_id.name() );

	}

	public Short getId() {

		return this.id;

	}

	public void setId( Short id ) {

		this.id = id;

	}

	public String getName() {

		return this.name;

	}

	public void setName( String name ) {

		this.name = name;

	}

	public String getPassword() {

		return this.password;

	}

	public void setPassword( String password ) {

		this.password = password;

	}

	public String getEmail() {

		return this.email;

	}

	public void setEmail( String email ) {

		this.email = email;

	}

	public Short getAgencyId() {

		return this.agency_id;

	}

	public void setAgencyId( Short agency_id ) {

		this.agency_id = agency_id;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"id\": \"" ).append( this.getId() ).append( "\", " )
			.append( "\"name\": \"" ).append( this.getName() ).append( "\", " )
			.append( "\"password\": \"" ).append( this.getPassword() ).append( "\", " )
			.append( "\"email\": \"" ).append( this.getEmail() ).append( "\", " )
			.append( "\"agency_id\": \"" ).append( this.getAgencyId() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }