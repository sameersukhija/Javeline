package com.lumata.expression.operators.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "subs_voice" )
public class SubsVoice { 

	public enum Fields { msisdn }

	@Column(
			table = "subs_voice",
			field = "msisdn",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getMsisdn",
			setMethod = "setMsisdn"
	)
	private Long msisdn;


	public SubsVoice() {} 

	public SubsVoice( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( SubsVoice.Fields.msisdn.name() );

	}

	public SubsVoice( JSONObject jo ) throws JSONException {

		this.msisdn = (long)jo.getLong( SubsVoice.Fields.msisdn.name() );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public void setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

	}

	public Fields[] getEntityFields() {

		return SubsVoice.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }