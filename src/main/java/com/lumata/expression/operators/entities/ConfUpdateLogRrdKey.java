package com.lumata.expression.operators.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "conf_update_log_rrd_key" )
public class ConfUpdateLogRrdKey { 

	public enum Fields { rrd_key }

	@Column(
			table = "conf_update_log_rrd_key",
			field = "rrd_key",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 20,
			getMethod = "getRrdKey",
			setMethod = "setRrdKey"
	)
	private Long rrd_key;


	public ConfUpdateLogRrdKey() {} 

	public ConfUpdateLogRrdKey( ResultSet rs ) throws SQLException {

		this.rrd_key = rs.getLong( ConfUpdateLogRrdKey.Fields.rrd_key.name() );

	}

	public ConfUpdateLogRrdKey( JSONObject jo ) throws JSONException {

		this.rrd_key = (long)jo.getLong( ConfUpdateLogRrdKey.Fields.rrd_key.name() );

	}

	public Long getRrdKey() {

		return this.rrd_key;

	}

	public void setRrdKey( Long rrd_key ) {

		this.rrd_key = rrd_key;

	}

	public Fields[] getEntityFields() {

		return ConfUpdateLogRrdKey.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"rrd_key\": \"" ).append( this.getRrdKey() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }