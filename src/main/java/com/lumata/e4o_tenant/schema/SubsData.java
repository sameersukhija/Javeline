package com.lumata.e4o_tenant.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "subs_data" )
public class SubsData { 

	public enum Fields { msisdn }

	@Column(
			table = "subs_data",
			field = "msisdn",
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
			getMethod = "getMsisdn",
			setMethod = "setMsisdn"
	)
	private Long msisdn;


	public SubsData() {} 

	public SubsData( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( SubsData.Fields.msisdn.name() );

	}

	public SubsData( JSONObject jo ) throws JSONException {

		this.msisdn = (long)jo.getLong( SubsData.Fields.msisdn.name() );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public void setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

	}

	public Fields[] getEntityFields() {

		return SubsData.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }