package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "set_hobbies" )
public class Setoptions { 

	public enum Fields { hobbies, hobbies_name }

	@Column(
			table = "set_hobbies",
			field = "hobbies",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getHobbies",
			setMethod = "setHobbies"
	)
	private Long hobbies;

	@Column(
			table = "set_hobbies",
			field = "hobbies_name",
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
			getMethod = "getHobbiesName",
			setMethod = "setHobbiesName"
	)
	private String hobbies_name;


	public Setoptions() {} 

	public Setoptions( ResultSet rs ) throws SQLException {

		this.hobbies = rs.getLong( Setoptions.Fields.hobbies.name() );
		this.hobbies_name = rs.getString( Setoptions.Fields.hobbies_name.name() );

	}

	public Setoptions( JSONObject jo ) throws JSONException {

		this.hobbies = (long)jo.getLong( Setoptions.Fields.hobbies.name() );
		this.hobbies_name = jo.getString( Setoptions.Fields.hobbies_name.name() );

	}

	public Long getHobbies() {

		return this.hobbies;

	}

	public void setHobbies( Long hobbies ) {

		this.hobbies = hobbies;

	}

	public String getHobbiesName() {

		return this.hobbies_name;

	}

	public void setHobbiesName( String hobbies_name ) {

		this.hobbies_name = hobbies_name;

	}

	public Fields[] getEntityFields() {

		return Setoptions.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"hobbies\": \"" ).append( this.getHobbies() ).append( "\", " )
			.append( "\"hobbies_name\": \"" ).append( this.getHobbiesName() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }