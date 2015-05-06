package com.lumata.e4o.schema.tenant;

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
			table = "agencies",
			field = "name",
			type = "varchar(100)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 100,
			comment = "",
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
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 250,
			comment = "",
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
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 20,
			comment = "",
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

	public Agencies setId( Short id ) {

		this.id = id;

		return this;

	}

	public String getName() {

		return this.name;

	}

	public Agencies setName( String name ) {

		this.name = name;

		return this;

	}

	public String getAddress() {

		return this.address;

	}

	public Agencies setAddress( String address ) {

		this.address = address;

		return this;

	}

	public String getPhone() {

		return this.phone;

	}

	public Agencies setPhone( String phone ) {

		this.phone = phone;

		return this;

	}

	public Fields[] getEntityFields() {

		return Agencies.Fields.values();

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