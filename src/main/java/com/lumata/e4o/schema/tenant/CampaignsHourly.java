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


@Table( "campaigns_hourly" )
public class CampaignsHourly { 

	public enum Fields { agg_date_time, campaign_id, subs_status, qty_msisdn, module_id, reward_id, qty_reward, qty_reward_sponsor, qty_subs_reward }

	@Column(
			table = "campaigns_hourly",
			field = "agg_date_time",
			type = "datetime",
			mysqlType = "datetime",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "",
			getMethod = "getAggDateTime",
			setMethod = "setAggDateTime"
	)
	private Date agg_date_time;

	@Column(
			table = "campaigns_hourly",
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
			table = "campaigns_hourly",
			field = "subs_status",
			type = "enum('CONTROL','CONTROL_BENEF','PROVISIONED','NOTIFIED','BENEFICIARY','NOTIFIED_BENEFICIARY','NOT_TARGETED_BENEFICIARY','NOT_TARGETED_PROV')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 8,
			comment = "",
			getMethod = "getSubsStatus",
			setMethod = "setSubsStatus"
	)
	private String subs_status;

	@Column(
			table = "campaigns_hourly",
			field = "qty_msisdn",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			comment = "",
			getMethod = "getQtyMsisdn",
			setMethod = "setQtyMsisdn"
	)
	private Integer qty_msisdn;

	@Column(
			table = "campaigns_hourly",
			field = "module_id",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "0",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getModuleId",
			setMethod = "setModuleId"
	)
	private Byte module_id;

	@Column(
			table = "campaigns_hourly",
			field = "reward_id",
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
			getMethod = "getRewardId",
			setMethod = "setRewardId"
	)
	private Short reward_id;

	@Column(
			table = "campaigns_hourly",
			field = "qty_reward",
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
			getMethod = "getQtyReward",
			setMethod = "setQtyReward"
	)
	private Float qty_reward;

	@Column(
			table = "campaigns_hourly",
			field = "qty_reward_sponsor",
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
			getMethod = "getQtyRewardSponsor",
			setMethod = "setQtyRewardSponsor"
	)
	private Float qty_reward_sponsor;

	@Column(
			table = "campaigns_hourly",
			field = "qty_subs_reward",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			comment = "",
			getMethod = "getQtySubsReward",
			setMethod = "setQtySubsReward"
	)
	private Integer qty_subs_reward;


	public CampaignsHourly() {} 

	public CampaignsHourly( ResultSet rs ) throws SQLException {

		this.agg_date_time = rs.getDate( CampaignsHourly.Fields.agg_date_time.name() );
		this.campaign_id = rs.getShort( CampaignsHourly.Fields.campaign_id.name() );
		this.subs_status = rs.getString( CampaignsHourly.Fields.subs_status.name() );
		this.qty_msisdn = rs.getInt( CampaignsHourly.Fields.qty_msisdn.name() );
		this.module_id = rs.getByte( CampaignsHourly.Fields.module_id.name() );
		this.reward_id = rs.getShort( CampaignsHourly.Fields.reward_id.name() );
		this.qty_reward = rs.getFloat( CampaignsHourly.Fields.qty_reward.name() );
		this.qty_reward_sponsor = rs.getFloat( CampaignsHourly.Fields.qty_reward_sponsor.name() );
		this.qty_subs_reward = rs.getInt( CampaignsHourly.Fields.qty_subs_reward.name() );

	}

	public CampaignsHourly( JSONObject jo ) throws JSONException, ParseException {

		this.agg_date_time = Format.getMysqlDateTime( jo.getString( CampaignsHourly.Fields.agg_date_time.name() ) );
		this.campaign_id = (short)jo.getInt( CampaignsHourly.Fields.campaign_id.name() );
		this.subs_status = jo.getString( CampaignsHourly.Fields.subs_status.name() );
		this.qty_msisdn = (int)jo.getInt( CampaignsHourly.Fields.qty_msisdn.name() );
		this.module_id = (byte)jo.getInt( CampaignsHourly.Fields.module_id.name() );
		this.reward_id = (short)jo.getInt( CampaignsHourly.Fields.reward_id.name() );
		this.qty_reward = (float)jo.getDouble( CampaignsHourly.Fields.qty_reward.name() );
		this.qty_reward_sponsor = (float)jo.getDouble( CampaignsHourly.Fields.qty_reward_sponsor.name() );
		this.qty_subs_reward = (int)jo.getInt( CampaignsHourly.Fields.qty_subs_reward.name() );

	}

	public Date getAggDateTime() {

		return this.agg_date_time;

	}

	public CampaignsHourly setAggDateTime( Date agg_date_time ) {

		this.agg_date_time = agg_date_time;

		return this;

	}

	public Short getCampaignId() {

		return this.campaign_id;

	}

	public CampaignsHourly setCampaignId( Short campaign_id ) {

		this.campaign_id = campaign_id;

		return this;

	}

	public String getSubsStatus() {

		return this.subs_status;

	}

	public CampaignsHourly setSubsStatus( String subs_status ) {

		this.subs_status = subs_status;

		return this;

	}

	public Integer getQtyMsisdn() {

		return this.qty_msisdn;

	}

	public CampaignsHourly setQtyMsisdn( Integer qty_msisdn ) {

		this.qty_msisdn = qty_msisdn;

		return this;

	}

	public Byte getModuleId() {

		return this.module_id;

	}

	public CampaignsHourly setModuleId( Byte module_id ) {

		this.module_id = module_id;

		return this;

	}

	public Short getRewardId() {

		return this.reward_id;

	}

	public CampaignsHourly setRewardId( Short reward_id ) {

		this.reward_id = reward_id;

		return this;

	}

	public Float getQtyReward() {

		return this.qty_reward;

	}

	public CampaignsHourly setQtyReward( Float qty_reward ) {

		this.qty_reward = qty_reward;

		return this;

	}

	public Float getQtyRewardSponsor() {

		return this.qty_reward_sponsor;

	}

	public CampaignsHourly setQtyRewardSponsor( Float qty_reward_sponsor ) {

		this.qty_reward_sponsor = qty_reward_sponsor;

		return this;

	}

	public Integer getQtySubsReward() {

		return this.qty_subs_reward;

	}

	public CampaignsHourly setQtySubsReward( Integer qty_subs_reward ) {

		this.qty_subs_reward = qty_subs_reward;

		return this;

	}

	public Fields[] getEntityFields() {

		return CampaignsHourly.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"agg_date_time\": \"" ).append( this.getAggDateTime() ).append( "\", " )
			.append( "\"campaign_id\": \"" ).append( this.getCampaignId() ).append( "\", " )
			.append( "\"subs_status\": \"" ).append( this.getSubsStatus() ).append( "\", " )
			.append( "\"qty_msisdn\": \"" ).append( this.getQtyMsisdn() ).append( "\", " )
			.append( "\"module_id\": \"" ).append( this.getModuleId() ).append( "\", " )
			.append( "\"reward_id\": \"" ).append( this.getRewardId() ).append( "\", " )
			.append( "\"qty_reward\": \"" ).append( this.getQtyReward() ).append( "\", " )
			.append( "\"qty_reward_sponsor\": \"" ).append( this.getQtyRewardSponsor() ).append( "\", " )
			.append( "\"qty_subs_reward\": \"" ).append( this.getQtySubsReward() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }