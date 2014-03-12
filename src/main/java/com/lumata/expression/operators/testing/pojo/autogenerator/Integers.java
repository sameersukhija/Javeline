package com.lumata.expression.operators.testing.pojo.autogenerator;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "integers" )
public class Integers { 

	public enum Fields { number }

	@Column(
			table = "integers",
			field = "number",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNumber",
			setMethod = "setNumber"
	)
	private Byte number;


	public Integers() {} 

	public Integers( ResultSet rs ) throws SQLException {

		this.number = rs.getByte( Integers.Fields.number.name() );

	}

	public Integers( JSONObject jo ) throws JSONException {

		this.number = (byte)jo.getInt( Integers.Fields.number.name() );

	}

	public Byte getNumber() {

		return this.number;

	}

	public void setNumber( Byte number ) {

		this.number = number;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"number\": \"" ).append( this.getNumber() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }