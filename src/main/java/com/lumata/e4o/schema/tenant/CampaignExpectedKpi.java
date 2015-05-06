package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "campaign_expected_kpi" )
public class CampaignExpectedKpi { 

	public enum Fields { campaign_id, kpi_name, kpi_value }

	@Column(
			table = "campaign_expected_kpi",
			field = "campaign_id",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 5,
			comment = "",
			getMethod = "getCampaignId",
			setMethod = "setCampaignId"
	)
	private Short campaign_id;

	@Column(
			table = "campaign_expected_kpi",
			field = "kpi_name",
			type = "varchar(45)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 45,
			comment = "",
			getMethod = "getKpiName",
			setMethod = "setKpiName"
	)
	private String kpi_name;

	@Column(
			table = "campaign_expected_kpi",
			field = "kpi_value",
			type = "float",
			mysqlType = "float",
			javaType = "Float",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getKpiValue",
			setMethod = "setKpiValue"
	)
	private Float kpi_value;


	public CampaignExpectedKpi() {} 

	public CampaignExpectedKpi( ResultSet rs ) throws SQLException {

		this.campaign_id = rs.getShort( CampaignExpectedKpi.Fields.campaign_id.name() );
		this.kpi_name = rs.getString( CampaignExpectedKpi.Fields.kpi_name.name() );
		this.kpi_value = rs.getFloat( CampaignExpectedKpi.Fields.kpi_value.name() );

	}

	public CampaignExpectedKpi( JSONObject jo ) throws JSONException {

		this.campaign_id = (short)jo.getInt( CampaignExpectedKpi.Fields.campaign_id.name() );
		this.kpi_name = jo.getString( CampaignExpectedKpi.Fields.kpi_name.name() );
		this.kpi_value = (float)jo.getDouble( CampaignExpectedKpi.Fields.kpi_value.name() );

	}

	public Short getCampaignId() {

		return this.campaign_id;

	}

	public CampaignExpectedKpi setCampaignId( Short campaign_id ) {

		this.campaign_id = campaign_id;

		return this;

	}

	public String getKpiName() {

		return this.kpi_name;

	}

	public CampaignExpectedKpi setKpiName( String kpi_name ) {

		this.kpi_name = kpi_name;

		return this;

	}

	public Float getKpiValue() {

		return this.kpi_value;

	}

	public CampaignExpectedKpi setKpiValue( Float kpi_value ) {

		this.kpi_value = kpi_value;

		return this;

	}

	public Fields[] getEntityFields() {

		return CampaignExpectedKpi.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"campaign_id\": \"" ).append( this.getCampaignId() ).append( "\", " )
			.append( "\"kpi_name\": \"" ).append( this.getKpiName() ).append( "\", " )
			.append( "\"kpi_value\": \"" ).append( this.getKpiValue() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }