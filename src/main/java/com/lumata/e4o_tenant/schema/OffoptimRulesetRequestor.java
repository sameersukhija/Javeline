package com.lumata.e4o_tenant.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "offoptim_ruleset_requestor" )
public class OffoptimRulesetRequestor { 

	public enum Fields { ruleset_id, requestor_id }

	@Column(
			table = "offoptim_ruleset_requestor",
			field = "ruleset_id",
			type = "int(10) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 10,
			getMethod = "getRulesetId",
			setMethod = "setRulesetId"
	)
	private Integer ruleset_id;

	@Column(
			table = "offoptim_ruleset_requestor",
			field = "requestor_id",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getRequestorId",
			setMethod = "setRequestorId"
	)
	private Integer requestor_id;


	public OffoptimRulesetRequestor() {} 

	public OffoptimRulesetRequestor( ResultSet rs ) throws SQLException {

		this.ruleset_id = rs.getInt( OffoptimRulesetRequestor.Fields.ruleset_id.name() );
		this.requestor_id = rs.getInt( OffoptimRulesetRequestor.Fields.requestor_id.name() );

	}

	public OffoptimRulesetRequestor( JSONObject jo ) throws JSONException {

		this.ruleset_id = (int)jo.getInt( OffoptimRulesetRequestor.Fields.ruleset_id.name() );
		this.requestor_id = (int)jo.getInt( OffoptimRulesetRequestor.Fields.requestor_id.name() );

	}

	public Integer getRulesetId() {

		return this.ruleset_id;

	}

	public void setRulesetId( Integer ruleset_id ) {

		this.ruleset_id = ruleset_id;

	}

	public Integer getRequestorId() {

		return this.requestor_id;

	}

	public void setRequestorId( Integer requestor_id ) {

		this.requestor_id = requestor_id;

	}

	public Fields[] getEntityFields() {

		return OffoptimRulesetRequestor.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"ruleset_id\": \"" ).append( this.getRulesetId() ).append( "\", " )
			.append( "\"requestor_id\": \"" ).append( this.getRequestorId() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }