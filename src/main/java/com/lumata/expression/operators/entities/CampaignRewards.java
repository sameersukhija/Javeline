package com.lumata.expression.operators.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.sql.Timestamp;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "campaign_rewards" )
public class CampaignRewards { 

	public enum Fields { msisdn, campaign_id, reward_id, relation_type_id, qty_reward, qty_reward_in, control, update_time }

	@Column(
			table = "campaign_rewards",
			field = "msisdn",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getMsisdn",
			setMethod = "setMsisdn"
	)
	private Long msisdn;

	@Column(
			table = "campaign_rewards",
			field = "campaign_id",
			type = "smallint(3) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getCampaignId",
			setMethod = "setCampaignId"
	)
	private Short campaign_id;

	@Column(
			table = "campaign_rewards",
			field = "reward_id",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 5,
			getMethod = "getRewardId",
			setMethod = "setRewardId"
	)
	private Short reward_id;

	@Column(
			table = "campaign_rewards",
			field = "relation_type_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "0",
			extra = "",
			length = 3,
			getMethod = "getRelationTypeId",
			setMethod = "setRelationTypeId"
	)
	private Byte relation_type_id;

	@Column(
			table = "campaign_rewards",
			field = "qty_reward",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getQtyReward",
			setMethod = "setQtyReward"
	)
	private Integer qty_reward;

	@Column(
			table = "campaign_rewards",
			field = "qty_reward_in",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getQtyRewardIn",
			setMethod = "setQtyRewardIn"
	)
	private Integer qty_reward_in;

	@Column(
			table = "campaign_rewards",
			field = "control",
			type = "tinyint(1)",
			mysqlType = "tinyint",
			javaType = "Boolean",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 1,
			getMethod = "getControl",
			setMethod = "setControl"
	)
	private Boolean control;

	@Column(
			table = "campaign_rewards",
			field = "update_time",
			type = "timestamp",
			mysqlType = "timestamp",
			javaType = "Timestamp",
			categoryType = "Date",
			isNull = false,
			key = "",
			defaultValue = "CURRENT_TIMESTAMP",
			extra = "on update CURRENT_TIMESTAMP",
			length = 0,
			getMethod = "getUpdateTime",
			setMethod = "setUpdateTime"
	)
	private Timestamp update_time;


	public CampaignRewards() {} 

	public CampaignRewards( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( CampaignRewards.Fields.msisdn.name() );
		this.campaign_id = rs.getShort( CampaignRewards.Fields.campaign_id.name() );
		this.reward_id = rs.getShort( CampaignRewards.Fields.reward_id.name() );
		this.relation_type_id = rs.getByte( CampaignRewards.Fields.relation_type_id.name() );
		this.qty_reward = rs.getInt( CampaignRewards.Fields.qty_reward.name() );
		this.qty_reward_in = rs.getInt( CampaignRewards.Fields.qty_reward_in.name() );
		this.control = rs.getBoolean( CampaignRewards.Fields.control.name() );
		this.update_time = rs.getTimestamp( CampaignRewards.Fields.update_time.name() );

	}

	public CampaignRewards( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( CampaignRewards.Fields.msisdn.name() );
		this.campaign_id = (short)jo.getInt( CampaignRewards.Fields.campaign_id.name() );
		this.reward_id = (short)jo.getInt( CampaignRewards.Fields.reward_id.name() );
		this.relation_type_id = (byte)jo.getInt( CampaignRewards.Fields.relation_type_id.name() );
		this.qty_reward = (int)jo.getInt( CampaignRewards.Fields.qty_reward.name() );
		this.qty_reward_in = (int)jo.getInt( CampaignRewards.Fields.qty_reward_in.name() );
		this.control = jo.getBoolean( CampaignRewards.Fields.control.name() );
		this.update_time = new Timestamp( Format.getMysqlDateTime( jo.getString( CampaignRewards.Fields.update_time.name() ) ).getTime() );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public void setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

	}

	public Short getCampaignId() {

		return this.campaign_id;

	}

	public void setCampaignId( Short campaign_id ) {

		this.campaign_id = campaign_id;

	}

	public Short getRewardId() {

		return this.reward_id;

	}

	public void setRewardId( Short reward_id ) {

		this.reward_id = reward_id;

	}

	public Byte getRelationTypeId() {

		return this.relation_type_id;

	}

	public void setRelationTypeId( Byte relation_type_id ) {

		this.relation_type_id = relation_type_id;

	}

	public Integer getQtyReward() {

		return this.qty_reward;

	}

	public void setQtyReward( Integer qty_reward ) {

		this.qty_reward = qty_reward;

	}

	public Integer getQtyRewardIn() {

		return this.qty_reward_in;

	}

	public void setQtyRewardIn( Integer qty_reward_in ) {

		this.qty_reward_in = qty_reward_in;

	}

	public Boolean getControl() {

		return this.control;

	}

	public void setControl( Boolean control ) {

		this.control = control;

	}

	public Timestamp getUpdateTime() {

		return this.update_time;

	}

	public void setUpdateTime( Timestamp update_time ) {

		this.update_time = update_time;

	}

	public Fields[] getEntityFields() {

		return CampaignRewards.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"campaign_id\": \"" ).append( this.getCampaignId() ).append( "\", " )
			.append( "\"reward_id\": \"" ).append( this.getRewardId() ).append( "\", " )
			.append( "\"relation_type_id\": \"" ).append( this.getRelationTypeId() ).append( "\", " )
			.append( "\"qty_reward\": \"" ).append( this.getQtyReward() ).append( "\", " )
			.append( "\"qty_reward_in\": \"" ).append( this.getQtyRewardIn() ).append( "\", " )
			.append( "\"control\": \"" ).append( this.getControl() ).append( "\", " )
			.append( "\"update_time\": \"" ).append( this.getUpdateTime() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }