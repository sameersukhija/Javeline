package com.lumata.expression.operators.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "campaign_params" )
public class CampaignParams { 

	public enum Fields { campaign_id, params }

	@Column(
			table = "campaign_params",
			field = "campaign_id",
			type = "smallint(6)",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 6,
			getMethod = "getCampaignId",
			setMethod = "setCampaignId"
	)
	private Short campaign_id;

	@Column(
			table = "campaign_params",
			field = "params",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 255,
			getMethod = "getParams",
			setMethod = "setParams"
	)
	private String params;


	public CampaignParams() {} 

	public CampaignParams( ResultSet rs ) throws SQLException {

		this.campaign_id = rs.getShort( CampaignParams.Fields.campaign_id.name() );
		this.params = rs.getString( CampaignParams.Fields.params.name() );

	}

	public CampaignParams( JSONObject jo ) throws JSONException {

		this.campaign_id = (short)jo.getInt( CampaignParams.Fields.campaign_id.name() );
		this.params = jo.getString( CampaignParams.Fields.params.name() );

	}

	public Short getCampaignId() {

		return this.campaign_id;

	}

	public void setCampaignId( Short campaign_id ) {

		this.campaign_id = campaign_id;

	}

	public String getParams() {

		return this.params;

	}

	public void setParams( String params ) {

		this.params = params;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"campaign_id\": \"" ).append( this.getCampaignId() ).append( "\", " )
			.append( "\"params\": \"" ).append( this.getParams() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }