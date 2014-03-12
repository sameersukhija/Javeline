package com.lumata.e4o_global.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "sms_next_id" )
public class SmsNextId { 

	public enum Fields { id }

	@Column(
			table = "sms_next_id",
			field = "id",
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
			getMethod = "getId",
			setMethod = "setId"
	)
	private Integer id;


	public SmsNextId() {} 

	public SmsNextId( ResultSet rs ) throws SQLException {

		this.id = rs.getInt( SmsNextId.Fields.id.name() );

	}

	public SmsNextId( JSONObject jo ) throws JSONException {

		this.id = (int)jo.getInt( SmsNextId.Fields.id.name() );

	}

	public Integer getId() {

		return this.id;

	}

	public void setId( Integer id ) {

		this.id = id;

	}

	public Fields[] getEntityFields() {

		return SmsNextId.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"id\": \"" ).append( this.getId() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }