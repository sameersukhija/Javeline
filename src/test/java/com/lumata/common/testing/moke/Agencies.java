package com.lumata.common.testing.moke;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "agencies" )
public class Agencies { 

	public enum Fields { id, name, address, phone }

	@Column(
			table = "agencies",
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
			table = "agencies",
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
			table = "agencies",
			field = "address",
			type = "varchar(250)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 250,
			getMethod = "getAddress",
			setMethod = "setAddress"
	)
	private String address;

	@Column(
			table = "agencies",
			field = "phone",
			type = "varchar(20)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getPhone",
			setMethod = "setPhone"
	)
	private String phone;


	public Agencies() {} 

	public Agencies( ResultSet rs ) throws SQLException {

		this.id = rs.getShort( Agencies.Fields.id.name() );
		this.name = rs.getString( Agencies.Fields.name.name() );
		this.address = rs.getString( Agencies.Fields.address.name() );
		this.phone = rs.getString( Agencies.Fields.phone.name() );

	}

	public Agencies( JSONObject jo ) throws JSONException {

		this.id = (short)jo.getInt( Agencies.Fields.id.name() );
		this.name = jo.getString( Agencies.Fields.name.name() );
		this.address = jo.getString( Agencies.Fields.address.name() );
		this.phone = jo.getString( Agencies.Fields.phone.name() );

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

	public String getAddress() {

		return this.address;

	}

	public void setAddress( String address ) {

		this.address = address;

	}

	public String getPhone() {

		return this.phone;

	}

	public void setPhone( String phone ) {

		this.phone = phone;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"id\": \"" ).append( this.getId() ).append( "\", " )
			.append( "\"name\": \"" ).append( this.getName() ).append( "\", " )
			.append( "\"address\": \"" ).append( this.getAddress() ).append( "\", " )
			.append( "\"phone\": \"" ).append( this.getPhone() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }