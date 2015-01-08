package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "library_bonuses" )
public class LibraryBonuses { 

	public enum Fields { account_type_id, account_type }

	@Column(
			table = "library_bonuses",
			field = "account_type_id",
			type = "smallint(3) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			comment = "",
			getMethod = "getAccountTypeId",
			setMethod = "setAccountTypeId"
	)
	private Short account_type_id;

	@Column(
			table = "library_bonuses",
			field = "account_type",
			type = "varchar(50)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 50,
			comment = "",
			getMethod = "getAccountType",
			setMethod = "setAccountType"
	)
	private String account_type;


	public LibraryBonuses() {} 

	public LibraryBonuses( ResultSet rs ) throws SQLException {

		this.account_type_id = rs.getShort( LibraryBonuses.Fields.account_type_id.name() );
		this.account_type = rs.getString( LibraryBonuses.Fields.account_type.name() );

	}

	public LibraryBonuses( JSONObject jo ) throws JSONException {

		this.account_type_id = (short)jo.getInt( LibraryBonuses.Fields.account_type_id.name() );
		this.account_type = jo.getString( LibraryBonuses.Fields.account_type.name() );

	}

	public Short getAccountTypeId() {

		return this.account_type_id;

	}

	public void setAccountTypeId( Short account_type_id ) {

		this.account_type_id = account_type_id;

	}

	public String getAccountType() {

		return this.account_type;

	}

	public void setAccountType( String account_type ) {

		this.account_type = account_type;

	}

	public Fields[] getEntityFields() {

		return LibraryBonuses.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"account_type_id\": \"" ).append( this.getAccountTypeId() ).append( "\", " )
			.append( "\"account_type\": \"" ).append( this.getAccountType() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }