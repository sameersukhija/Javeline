package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.util.Date;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "campaigns_history" )
public class CampaignsHistory { 

	public enum Fields { campaign_id, campaign_name, start_date, type_id, model_name, duration, channels_id_list, campaign_master_id }

	@Column(
			table = "campaigns_history",
			field = "campaign_id",
			type = "smallint(6)",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "0",
			extra = "",
			length = 6,
			comment = "",
			getMethod = "getCampaignId",
			setMethod = "setCampaignId"
	)
	private Short campaign_id;

	@Column(
			table = "campaigns_history",
			field = "campaign_name",
			type = "varchar(55)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 55,
			comment = "",
			getMethod = "getCampaignName",
			setMethod = "setCampaignName"
	)
	private String campaign_name;

	@Column(
			table = "campaigns_history",
			field = "start_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "",
			getMethod = "getStartDate",
			setMethod = "setStartDate"
	)
	private Date start_date;

	@Column(
			table = "campaigns_history",
			field = "type_id",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 5,
			comment = "",
			getMethod = "getTypeId",
			setMethod = "setTypeId"
	)
	private Short type_id;

	@Column(
			table = "campaigns_history",
			field = "model_name",
			type = "varchar(45)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 45,
			comment = "",
			getMethod = "getModelName",
			setMethod = "setModelName"
	)
	private String model_name;

	@Column(
			table = "campaigns_history",
			field = "duration",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 5,
			comment = "",
			getMethod = "getDuration",
			setMethod = "setDuration"
	)
	private Short duration;

	@Column(
			table = "campaigns_history",
			field = "channels_id_list",
			type = "set('1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16')",
			mysqlType = "set",
			javaType = "Set",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 16,
			comment = "",
			getMethod = "getChannelsIdList",
			setMethod = "setChannelsIdList"
	)
	private String channels_id_list;

	@Column(
			table = "campaigns_history",
			field = "campaign_master_id",
			type = "smallint(3)",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			comment = "",
			getMethod = "getCampaignMasterId",
			setMethod = "setCampaignMasterId"
	)
	private Short campaign_master_id;


	public CampaignsHistory() {} 

	public CampaignsHistory( ResultSet rs ) throws SQLException {

		this.campaign_id = rs.getShort( CampaignsHistory.Fields.campaign_id.name() );
		this.campaign_name = rs.getString( CampaignsHistory.Fields.campaign_name.name() );
		this.start_date = rs.getDate( CampaignsHistory.Fields.start_date.name() );
		this.type_id = rs.getShort( CampaignsHistory.Fields.type_id.name() );
		this.model_name = rs.getString( CampaignsHistory.Fields.model_name.name() );
		this.duration = rs.getShort( CampaignsHistory.Fields.duration.name() );
		this.channels_id_list = rs.getString( CampaignsHistory.Fields.channels_id_list.name() );
		this.campaign_master_id = rs.getShort( CampaignsHistory.Fields.campaign_master_id.name() );

	}

	public CampaignsHistory( JSONObject jo ) throws JSONException, ParseException {

		this.campaign_id = (short)jo.getInt( CampaignsHistory.Fields.campaign_id.name() );
		this.campaign_name = jo.getString( CampaignsHistory.Fields.campaign_name.name() );
		this.start_date = Format.getMysqlDateTime( jo.getString( CampaignsHistory.Fields.start_date.name() ) );
		this.type_id = (short)jo.getInt( CampaignsHistory.Fields.type_id.name() );
		this.model_name = jo.getString( CampaignsHistory.Fields.model_name.name() );
		this.duration = (short)jo.getInt( CampaignsHistory.Fields.duration.name() );
		this.channels_id_list = jo.getString( CampaignsHistory.Fields.channels_id_list.name() );
		this.campaign_master_id = (short)jo.getInt( CampaignsHistory.Fields.campaign_master_id.name() );

	}

	public Short getCampaignId() {

		return this.campaign_id;

	}

	public void setCampaignId( Short campaign_id ) {

		this.campaign_id = campaign_id;

	}

	public String getCampaignName() {

		return this.campaign_name;

	}

	public void setCampaignName( String campaign_name ) {

		this.campaign_name = campaign_name;

	}

	public Date getStartDate() {

		return this.start_date;

	}

	public void setStartDate( Date start_date ) {

		this.start_date = start_date;

	}

	public Short getTypeId() {

		return this.type_id;

	}

	public void setTypeId( Short type_id ) {

		this.type_id = type_id;

	}

	public String getModelName() {

		return this.model_name;

	}

	public void setModelName( String model_name ) {

		this.model_name = model_name;

	}

	public Short getDuration() {

		return this.duration;

	}

	public void setDuration( Short duration ) {

		this.duration = duration;

	}

	public String getChannelsIdList() {

		return this.channels_id_list;

	}

	public void setChannelsIdList( String channels_id_list ) {

		this.channels_id_list = channels_id_list;

	}

	public Short getCampaignMasterId() {

		return this.campaign_master_id;

	}

	public void setCampaignMasterId( Short campaign_master_id ) {

		this.campaign_master_id = campaign_master_id;

	}

	public Fields[] getEntityFields() {

		return CampaignsHistory.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"campaign_id\": \"" ).append( this.getCampaignId() ).append( "\", " )
			.append( "\"campaign_name\": \"" ).append( this.getCampaignName() ).append( "\", " )
			.append( "\"start_date\": \"" ).append( this.getStartDate() ).append( "\", " )
			.append( "\"type_id\": \"" ).append( this.getTypeId() ).append( "\", " )
			.append( "\"model_name\": \"" ).append( this.getModelName() ).append( "\", " )
			.append( "\"duration\": \"" ).append( this.getDuration() ).append( "\", " )
			.append( "\"channels_id_list\": \"" ).append( this.getChannelsIdList() ).append( "\", " )
			.append( "\"campaign_master_id\": \"" ).append( this.getCampaignMasterId() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }