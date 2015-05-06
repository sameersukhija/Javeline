package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "services" )
public class Services { 

	public enum Fields { service_id, service }

	@Column(
			table = "services",
			field = "service_id",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = true,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 4,
			comment = "",
			getMethod = "getServiceId",
			setMethod = "setServiceId"
	)
	private Byte service_id;

	@Column(
			table = "services",
			field = "service",
			type = "varchar(45)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 45,
			comment = "",
			getMethod = "getService",
			setMethod = "setService"
	)
	private String service;


	public Services() {} 

	public Services( ResultSet rs ) throws SQLException {

		this.service_id = rs.getByte( Services.Fields.service_id.name() );
		this.service = rs.getString( Services.Fields.service.name() );

	}

	public Services( JSONObject jo ) throws JSONException {

		this.service_id = (byte)jo.getInt( Services.Fields.service_id.name() );
		this.service = jo.getString( Services.Fields.service.name() );

	}

	public Byte getServiceId() {

		return this.service_id;

	}

	public Services setServiceId( Byte service_id ) {

		this.service_id = service_id;

		return this;

	}

	public String getService() {

		return this.service;

	}

	public Services setService( String service ) {

		this.service = service;

		return this;

	}

	public Fields[] getEntityFields() {

		return Services.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"service_id\": \"" ).append( this.getServiceId() ).append( "\", " )
			.append( "\"service\": \"" ).append( this.getService() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }