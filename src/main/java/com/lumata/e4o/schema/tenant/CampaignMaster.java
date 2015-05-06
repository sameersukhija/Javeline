package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "campaign_master" )
public class CampaignMaster { 

	public enum Fields { campaign_id, master_type }

	@Column(
			table = "campaign_master",
			field = "campaign_id",
			type = "smallint(3)",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			comment = "",
			getMethod = "getCampaignId",
			setMethod = "setCampaignId"
	)
	private Short campaign_id;

	@Column(
			table = "campaign_master",
			field = "master_type",
			type = "set('Recurrent','AB','Promotion')",
			mysqlType = "set",
			javaType = "Set",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			comment = "",
			getMethod = "getMasterType",
			setMethod = "setMasterType"
	)
	private String master_type;


	public CampaignMaster() {} 

	public CampaignMaster( ResultSet rs ) throws SQLException {

		this.campaign_id = rs.getShort( CampaignMaster.Fields.campaign_id.name() );
		this.master_type = rs.getString( CampaignMaster.Fields.master_type.name() );

	}

	public CampaignMaster( JSONObject jo ) throws JSONException {

		this.campaign_id = (short)jo.getInt( CampaignMaster.Fields.campaign_id.name() );
		this.master_type = jo.getString( CampaignMaster.Fields.master_type.name() );

	}

	public Short getCampaignId() {

		return this.campaign_id;

	}

	public CampaignMaster setCampaignId( Short campaign_id ) {

		this.campaign_id = campaign_id;

		return this;

	}

	public String getMasterType() {

		return this.master_type;

	}

	public CampaignMaster setMasterType( String master_type ) {

		this.master_type = master_type;

		return this;

	}

	public Fields[] getEntityFields() {

		return CampaignMaster.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"campaign_id\": \"" ).append( this.getCampaignId() ).append( "\", " )
			.append( "\"master_type\": \"" ).append( this.getMasterType() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }