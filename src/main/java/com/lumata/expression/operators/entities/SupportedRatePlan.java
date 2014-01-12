package com.lumata.expression.operators.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "supported_rate_plan" )
public class SupportedRatePlan { 

	public enum Fields { rate_plan_id, profile_id, rate_plan }

	@Column(
			table = "supported_rate_plan",
			field = "rate_plan_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 3,
			getMethod = "getRatePlanId",
			setMethod = "setRatePlanId"
	)
	private Byte rate_plan_id;

	@Column(
			table = "supported_rate_plan",
			field = "profile_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getProfileId",
			setMethod = "setProfileId"
	)
	private Byte profile_id;

	@Column(
			table = "supported_rate_plan",
			field = "rate_plan",
			type = "varchar(50)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 50,
			getMethod = "getRatePlan",
			setMethod = "setRatePlan"
	)
	private String rate_plan;


	public SupportedRatePlan() {} 

	public SupportedRatePlan( ResultSet rs ) throws SQLException {

		this.rate_plan_id = rs.getByte( SupportedRatePlan.Fields.rate_plan_id.name() );
		this.profile_id = rs.getByte( SupportedRatePlan.Fields.profile_id.name() );
		this.rate_plan = rs.getString( SupportedRatePlan.Fields.rate_plan.name() );

	}

	public SupportedRatePlan( JSONObject jo ) throws JSONException {

		this.rate_plan_id = (byte)jo.getInt( SupportedRatePlan.Fields.rate_plan_id.name() );
		this.profile_id = (byte)jo.getInt( SupportedRatePlan.Fields.profile_id.name() );
		this.rate_plan = jo.getString( SupportedRatePlan.Fields.rate_plan.name() );

	}

	public Byte getRatePlanId() {

		return this.rate_plan_id;

	}

	public void setRatePlanId( Byte rate_plan_id ) {

		this.rate_plan_id = rate_plan_id;

	}

	public Byte getProfileId() {

		return this.profile_id;

	}

	public void setProfileId( Byte profile_id ) {

		this.profile_id = profile_id;

	}

	public String getRatePlan() {

		return this.rate_plan;

	}

	public void setRatePlan( String rate_plan ) {

		this.rate_plan = rate_plan;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"rate_plan_id\": \"" ).append( this.getRatePlanId() ).append( "\", " )
			.append( "\"profile_id\": \"" ).append( this.getProfileId() ).append( "\", " )
			.append( "\"rate_plan\": \"" ).append( this.getRatePlan() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }