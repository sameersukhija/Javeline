package com.lumata.e4o_tenant.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "bonus_campaign_limits" )
public class BonusCampaignLimits { 

	public enum Fields { bonus_id, campaign_id, max_bonus_per_subs, max_bonus, max_qty_subs_beneficiary, max_qty_occurence_beneficiary }

	@Column(
			table = "bonus_campaign_limits",
			field = "bonus_id",
			type = "smallint(3) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getBonusId",
			setMethod = "setBonusId"
	)
	private Short bonus_id;

	@Column(
			table = "bonus_campaign_limits",
			field = "campaign_id",
			type = "mediumint(8) unsigned",
			mysqlType = "mediumint",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 8,
			getMethod = "getCampaignId",
			setMethod = "setCampaignId"
	)
	private Integer campaign_id;

	@Column(
			table = "bonus_campaign_limits",
			field = "max_bonus_per_subs",
			type = "int(11) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getMaxBonusPerSubs",
			setMethod = "setMaxBonusPerSubs"
	)
	private Integer max_bonus_per_subs;

	@Column(
			table = "bonus_campaign_limits",
			field = "max_bonus",
			type = "int(11) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getMaxBonus",
			setMethod = "setMaxBonus"
	)
	private Integer max_bonus;

	@Column(
			table = "bonus_campaign_limits",
			field = "max_qty_subs_beneficiary",
			type = "mediumint(8) unsigned",
			mysqlType = "mediumint",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 8,
			getMethod = "getMaxQtySubsBeneficiary",
			setMethod = "setMaxQtySubsBeneficiary"
	)
	private Integer max_qty_subs_beneficiary;

	@Column(
			table = "bonus_campaign_limits",
			field = "max_qty_occurence_beneficiary",
			type = "mediumint(8) unsigned",
			mysqlType = "mediumint",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 8,
			getMethod = "getMaxQtyOccurenceBeneficiary",
			setMethod = "setMaxQtyOccurenceBeneficiary"
	)
	private Integer max_qty_occurence_beneficiary;


	public BonusCampaignLimits() {} 

	public BonusCampaignLimits( ResultSet rs ) throws SQLException {

		this.bonus_id = rs.getShort( BonusCampaignLimits.Fields.bonus_id.name() );
		this.campaign_id = rs.getInt( BonusCampaignLimits.Fields.campaign_id.name() );
		this.max_bonus_per_subs = rs.getInt( BonusCampaignLimits.Fields.max_bonus_per_subs.name() );
		this.max_bonus = rs.getInt( BonusCampaignLimits.Fields.max_bonus.name() );
		this.max_qty_subs_beneficiary = rs.getInt( BonusCampaignLimits.Fields.max_qty_subs_beneficiary.name() );
		this.max_qty_occurence_beneficiary = rs.getInt( BonusCampaignLimits.Fields.max_qty_occurence_beneficiary.name() );

	}

	public BonusCampaignLimits( JSONObject jo ) throws JSONException {

		this.bonus_id = (short)jo.getInt( BonusCampaignLimits.Fields.bonus_id.name() );
		this.campaign_id = (int)jo.getInt( BonusCampaignLimits.Fields.campaign_id.name() );
		this.max_bonus_per_subs = (int)jo.getInt( BonusCampaignLimits.Fields.max_bonus_per_subs.name() );
		this.max_bonus = (int)jo.getInt( BonusCampaignLimits.Fields.max_bonus.name() );
		this.max_qty_subs_beneficiary = (int)jo.getInt( BonusCampaignLimits.Fields.max_qty_subs_beneficiary.name() );
		this.max_qty_occurence_beneficiary = (int)jo.getInt( BonusCampaignLimits.Fields.max_qty_occurence_beneficiary.name() );

	}

	public Short getBonusId() {

		return this.bonus_id;

	}

	public void setBonusId( Short bonus_id ) {

		this.bonus_id = bonus_id;

	}

	public Integer getCampaignId() {

		return this.campaign_id;

	}

	public void setCampaignId( Integer campaign_id ) {

		this.campaign_id = campaign_id;

	}

	public Integer getMaxBonusPerSubs() {

		return this.max_bonus_per_subs;

	}

	public void setMaxBonusPerSubs( Integer max_bonus_per_subs ) {

		this.max_bonus_per_subs = max_bonus_per_subs;

	}

	public Integer getMaxBonus() {

		return this.max_bonus;

	}

	public void setMaxBonus( Integer max_bonus ) {

		this.max_bonus = max_bonus;

	}

	public Integer getMaxQtySubsBeneficiary() {

		return this.max_qty_subs_beneficiary;

	}

	public void setMaxQtySubsBeneficiary( Integer max_qty_subs_beneficiary ) {

		this.max_qty_subs_beneficiary = max_qty_subs_beneficiary;

	}

	public Integer getMaxQtyOccurenceBeneficiary() {

		return this.max_qty_occurence_beneficiary;

	}

	public void setMaxQtyOccurenceBeneficiary( Integer max_qty_occurence_beneficiary ) {

		this.max_qty_occurence_beneficiary = max_qty_occurence_beneficiary;

	}

	public Fields[] getEntityFields() {

		return BonusCampaignLimits.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"bonus_id\": \"" ).append( this.getBonusId() ).append( "\", " )
			.append( "\"campaign_id\": \"" ).append( this.getCampaignId() ).append( "\", " )
			.append( "\"max_bonus_per_subs\": \"" ).append( this.getMaxBonusPerSubs() ).append( "\", " )
			.append( "\"max_bonus\": \"" ).append( this.getMaxBonus() ).append( "\", " )
			.append( "\"max_qty_subs_beneficiary\": \"" ).append( this.getMaxQtySubsBeneficiary() ).append( "\", " )
			.append( "\"max_qty_occurence_beneficiary\": \"" ).append( this.getMaxQtyOccurenceBeneficiary() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }