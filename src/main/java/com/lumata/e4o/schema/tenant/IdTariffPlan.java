package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "id_tariff_plan" )
public class IdTariffPlan { 

	public enum Fields { tariff_plan, tariff_plan_name }

	@Column(
			table = "id_tariff_plan",
			field = "tariff_plan",
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
			comment = "",
			getMethod = "getTariffPlan",
			setMethod = "setTariffPlan"
	)
	private Long tariff_plan;

	@Column(
			table = "id_tariff_plan",
			field = "tariff_plan_name",
			type = "varchar(100)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 100,
			comment = "",
			getMethod = "getTariffPlanName",
			setMethod = "setTariffPlanName"
	)
	private String tariff_plan_name;


	public IdTariffPlan() {} 

	public IdTariffPlan( ResultSet rs ) throws SQLException {

		this.tariff_plan = rs.getLong( IdTariffPlan.Fields.tariff_plan.name() );
		this.tariff_plan_name = rs.getString( IdTariffPlan.Fields.tariff_plan_name.name() );

	}

	public IdTariffPlan( JSONObject jo ) throws JSONException {

		this.tariff_plan = (long)jo.getLong( IdTariffPlan.Fields.tariff_plan.name() );
		this.tariff_plan_name = jo.getString( IdTariffPlan.Fields.tariff_plan_name.name() );

	}

	public Long getTariffPlan() {

		return this.tariff_plan;

	}

	public IdTariffPlan setTariffPlan( Long tariff_plan ) {

		this.tariff_plan = tariff_plan;

		return this;

	}

	public String getTariffPlanName() {

		return this.tariff_plan_name;

	}

	public IdTariffPlan setTariffPlanName( String tariff_plan_name ) {

		this.tariff_plan_name = tariff_plan_name;

		return this;

	}

	public Fields[] getEntityFields() {

		return IdTariffPlan.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"tariff_plan\": \"" ).append( this.getTariffPlan() ).append( "\", " )
			.append( "\"tariff_plan_name\": \"" ).append( this.getTariffPlanName() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }