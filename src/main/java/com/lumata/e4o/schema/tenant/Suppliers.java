package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "suppliers" )
public class Suppliers { 

	public enum Fields { supplier_id, name, email, phone, website }

	@Column(
			table = "suppliers",
			field = "supplier_id",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = true,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 5,
			getMethod = "getSupplierId",
			setMethod = "setSupplierId"
	)
	private Short supplier_id;

	@Column(
			table = "suppliers",
			field = "name",
			type = "varchar(45)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 45,
			getMethod = "getName",
			setMethod = "setName"
	)
	private String name;

	@Column(
			table = "suppliers",
			field = "email",
			type = "varchar(70)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 70,
			getMethod = "getEmail",
			setMethod = "setEmail"
	)
	private String email;

	@Column(
			table = "suppliers",
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
			getMethod = "getPhone",
			setMethod = "setPhone"
	)
	private String phone;

	@Column(
			table = "suppliers",
			field = "website",
			type = "varchar(70)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 70,
			getMethod = "getWebsite",
			setMethod = "setWebsite"
	)
	private String website;


	public Suppliers() {} 

	public Suppliers( ResultSet rs ) throws SQLException {

		this.supplier_id = rs.getShort( Suppliers.Fields.supplier_id.name() );
		this.name = rs.getString( Suppliers.Fields.name.name() );
		this.email = rs.getString( Suppliers.Fields.email.name() );
		this.phone = rs.getString( Suppliers.Fields.phone.name() );
		this.website = rs.getString( Suppliers.Fields.website.name() );

	}

	public Suppliers( JSONObject jo ) throws JSONException {

		this.supplier_id = (short)jo.getInt( Suppliers.Fields.supplier_id.name() );
		this.name = jo.getString( Suppliers.Fields.name.name() );
		this.email = jo.getString( Suppliers.Fields.email.name() );
		this.phone = jo.getString( Suppliers.Fields.phone.name() );
		this.website = jo.getString( Suppliers.Fields.website.name() );

	}

	public Short getSupplierId() {

		return this.supplier_id;

	}

	public void setSupplierId( Short supplier_id ) {

		this.supplier_id = supplier_id;

	}

	public String getName() {

		return this.name;

	}

	public void setName( String name ) {

		this.name = name;

	}

	public String getEmail() {

		return this.email;

	}

	public void setEmail( String email ) {

		this.email = email;

	}

	public String getPhone() {

		return this.phone;

	}

	public void setPhone( String phone ) {

		this.phone = phone;

	}

	public String getWebsite() {

		return this.website;

	}

	public void setWebsite( String website ) {

		this.website = website;

	}

	public Fields[] getEntityFields() {

		return Suppliers.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"supplier_id\": \"" ).append( this.getSupplierId() ).append( "\", " )
			.append( "\"name\": \"" ).append( this.getName() ).append( "\", " )
			.append( "\"email\": \"" ).append( this.getEmail() ).append( "\", " )
			.append( "\"phone\": \"" ).append( this.getPhone() ).append( "\", " )
			.append( "\"website\": \"" ).append( this.getWebsite() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }