package com.lumata.expression.operators.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "campaign_infos" )
public class CampaignInfos { 

	public enum Fields { campaign_id, key_infos, value_infos }

	@Column(
			table = "campaign_infos",
			field = "campaign_id",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 5,
			getMethod = "getCampaignId",
			setMethod = "setCampaignId"
	)
	private Short campaign_id;

	@Column(
			table = "campaign_infos",
			field = "key_infos",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 255,
			getMethod = "getKeyInfos",
			setMethod = "setKeyInfos"
	)
	private String key_infos;

	@Column(
			table = "campaign_infos",
			field = "value_infos",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 255,
			getMethod = "getValueInfos",
			setMethod = "setValueInfos"
	)
	private String value_infos;


	public CampaignInfos() {} 

	public CampaignInfos( ResultSet rs ) throws SQLException {

		this.campaign_id = rs.getShort( CampaignInfos.Fields.campaign_id.name() );
		this.key_infos = rs.getString( CampaignInfos.Fields.key_infos.name() );
		this.value_infos = rs.getString( CampaignInfos.Fields.value_infos.name() );

	}

	public CampaignInfos( JSONObject jo ) throws JSONException {

		this.campaign_id = (short)jo.getInt( CampaignInfos.Fields.campaign_id.name() );
		this.key_infos = jo.getString( CampaignInfos.Fields.key_infos.name() );
		this.value_infos = jo.getString( CampaignInfos.Fields.value_infos.name() );

	}

	public Short getCampaignId() {

		return this.campaign_id;

	}

	public void setCampaignId( Short campaign_id ) {

		this.campaign_id = campaign_id;

	}

	public String getKeyInfos() {

		return this.key_infos;

	}

	public void setKeyInfos( String key_infos ) {

		this.key_infos = key_infos;

	}

	public String getValueInfos() {

		return this.value_infos;

	}

	public void setValueInfos( String value_infos ) {

		this.value_infos = value_infos;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"campaign_id\": \"" ).append( this.getCampaignId() ).append( "\", " )
			.append( "\"key_infos\": \"" ).append( this.getKeyInfos() ).append( "\", " )
			.append( "\"value_infos\": \"" ).append( this.getValueInfos() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }