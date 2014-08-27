package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "campaigns_weekly_subs_status" )
public class CampaignsWeeklySubsStatus { 

	public enum Fields { campaign_id, week_id, profile_id, status_id, network_id, arpu_id, qty_control, qty_control_benef, qty_provisioned, qty_notified, qty_beneficiary, qty_not_targeted, control_amount_usage, provisioned_amount_usage, notified_amount_usage, beneficiary_amount_usage, not_targeted_amount_usage, control_qty_msisdn_with_amount_usage, provisioned_qty_msisdn_with_amount_usage, notified_qty_msisdn_with_amount_usage, beneficiary_qty_msisdn_with_amount_usage, not_targeted_qty_msisdn_with_amount_usage, control_amount_recharge, provisioned_amount_recharge, notified_amount_recharge, beneficiary_amount_recharge, not_targeted_amount_recharge, control_qty_msisdn_with_amount_recharge, provisioned_qty_msisdn_with_amount_recharge, notified_qty_msisdn_with_amount_recharge, beneficiary_qty_msisdn_with_amount_recharge, not_targeted_qty_msisdn_with_amount_recharge, control_proba, provisioned_proba, notified_proba, beneficiary_proba, not_targeted_proba }

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "campaign_id",
			type = "smallint(3) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getCampaignId",
			setMethod = "setCampaignId"
	)
	private Short campaign_id;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "week_id",
			type = "tinyint(4) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getWeekId",
			setMethod = "setWeekId"
	)
	private Byte week_id;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "profile_id",
			type = "tinyint(4) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getProfileId",
			setMethod = "setProfileId"
	)
	private Byte profile_id;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "status_id",
			type = "tinyint(4) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getStatusId",
			setMethod = "setStatusId"
	)
	private Byte status_id;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "network_id",
			type = "tinyint(4) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNetworkId",
			setMethod = "setNetworkId"
	)
	private Byte network_id;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "arpu_id",
			type = "tinyint(4) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getArpuId",
			setMethod = "setArpuId"
	)
	private Byte arpu_id;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "qty_control",
			type = "decimal(32,0)",
			mysqlType = "decimal",
			javaType = "Float",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 32,
			getMethod = "getQtyControl",
			setMethod = "setQtyControl"
	)
	private Float qty_control;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "qty_control_benef",
			type = "decimal(32,0)",
			mysqlType = "decimal",
			javaType = "Float",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 32,
			getMethod = "getQtyControlBenef",
			setMethod = "setQtyControlBenef"
	)
	private Float qty_control_benef;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "qty_provisioned",
			type = "decimal(32,0)",
			mysqlType = "decimal",
			javaType = "Float",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 32,
			getMethod = "getQtyProvisioned",
			setMethod = "setQtyProvisioned"
	)
	private Float qty_provisioned;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "qty_notified",
			type = "decimal(32,0)",
			mysqlType = "decimal",
			javaType = "Float",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 32,
			getMethod = "getQtyNotified",
			setMethod = "setQtyNotified"
	)
	private Float qty_notified;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "qty_beneficiary",
			type = "decimal(32,0)",
			mysqlType = "decimal",
			javaType = "Float",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 32,
			getMethod = "getQtyBeneficiary",
			setMethod = "setQtyBeneficiary"
	)
	private Float qty_beneficiary;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "qty_not_targeted",
			type = "decimal(32,0)",
			mysqlType = "decimal",
			javaType = "Float",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 32,
			getMethod = "getQtyNotTargeted",
			setMethod = "setQtyNotTargeted"
	)
	private Float qty_not_targeted;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_amount_usage",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getControlAmountUsage",
			setMethod = "setControlAmountUsage"
	)
	private Double control_amount_usage;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_amount_usage",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getProvisionedAmountUsage",
			setMethod = "setProvisionedAmountUsage"
	)
	private Double provisioned_amount_usage;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_amount_usage",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotifiedAmountUsage",
			setMethod = "setNotifiedAmountUsage"
	)
	private Double notified_amount_usage;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_amount_usage",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getBeneficiaryAmountUsage",
			setMethod = "setBeneficiaryAmountUsage"
	)
	private Double beneficiary_amount_usage;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_amount_usage",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotTargetedAmountUsage",
			setMethod = "setNotTargetedAmountUsage"
	)
	private Double not_targeted_amount_usage;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_qty_msisdn_with_amount_usage",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getControlQtyMsisdnWithAmountUsage",
			setMethod = "setControlQtyMsisdnWithAmountUsage"
	)
	private Double control_qty_msisdn_with_amount_usage;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_qty_msisdn_with_amount_usage",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getProvisionedQtyMsisdnWithAmountUsage",
			setMethod = "setProvisionedQtyMsisdnWithAmountUsage"
	)
	private Double provisioned_qty_msisdn_with_amount_usage;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_qty_msisdn_with_amount_usage",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotifiedQtyMsisdnWithAmountUsage",
			setMethod = "setNotifiedQtyMsisdnWithAmountUsage"
	)
	private Double notified_qty_msisdn_with_amount_usage;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_qty_msisdn_with_amount_usage",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getBeneficiaryQtyMsisdnWithAmountUsage",
			setMethod = "setBeneficiaryQtyMsisdnWithAmountUsage"
	)
	private Double beneficiary_qty_msisdn_with_amount_usage;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_qty_msisdn_with_amount_usage",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotTargetedQtyMsisdnWithAmountUsage",
			setMethod = "setNotTargetedQtyMsisdnWithAmountUsage"
	)
	private Double not_targeted_qty_msisdn_with_amount_usage;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_amount_recharge",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getControlAmountRecharge",
			setMethod = "setControlAmountRecharge"
	)
	private Double control_amount_recharge;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_amount_recharge",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getProvisionedAmountRecharge",
			setMethod = "setProvisionedAmountRecharge"
	)
	private Double provisioned_amount_recharge;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_amount_recharge",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotifiedAmountRecharge",
			setMethod = "setNotifiedAmountRecharge"
	)
	private Double notified_amount_recharge;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_amount_recharge",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getBeneficiaryAmountRecharge",
			setMethod = "setBeneficiaryAmountRecharge"
	)
	private Double beneficiary_amount_recharge;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_amount_recharge",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotTargetedAmountRecharge",
			setMethod = "setNotTargetedAmountRecharge"
	)
	private Double not_targeted_amount_recharge;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_qty_msisdn_with_amount_recharge",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getControlQtyMsisdnWithAmountRecharge",
			setMethod = "setControlQtyMsisdnWithAmountRecharge"
	)
	private Double control_qty_msisdn_with_amount_recharge;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_qty_msisdn_with_amount_recharge",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getProvisionedQtyMsisdnWithAmountRecharge",
			setMethod = "setProvisionedQtyMsisdnWithAmountRecharge"
	)
	private Double provisioned_qty_msisdn_with_amount_recharge;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_qty_msisdn_with_amount_recharge",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotifiedQtyMsisdnWithAmountRecharge",
			setMethod = "setNotifiedQtyMsisdnWithAmountRecharge"
	)
	private Double notified_qty_msisdn_with_amount_recharge;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_qty_msisdn_with_amount_recharge",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getBeneficiaryQtyMsisdnWithAmountRecharge",
			setMethod = "setBeneficiaryQtyMsisdnWithAmountRecharge"
	)
	private Double beneficiary_qty_msisdn_with_amount_recharge;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_qty_msisdn_with_amount_recharge",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotTargetedQtyMsisdnWithAmountRecharge",
			setMethod = "setNotTargetedQtyMsisdnWithAmountRecharge"
	)
	private Double not_targeted_qty_msisdn_with_amount_recharge;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_proba",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getControlProba",
			setMethod = "setControlProba"
	)
	private Double control_proba;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_proba",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getProvisionedProba",
			setMethod = "setProvisionedProba"
	)
	private Double provisioned_proba;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_proba",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotifiedProba",
			setMethod = "setNotifiedProba"
	)
	private Double notified_proba;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_proba",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getBeneficiaryProba",
			setMethod = "setBeneficiaryProba"
	)
	private Double beneficiary_proba;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_proba",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotTargetedProba",
			setMethod = "setNotTargetedProba"
	)
	private Double not_targeted_proba;


	public CampaignsWeeklySubsStatus() {} 

	public CampaignsWeeklySubsStatus( ResultSet rs ) throws SQLException {

		this.campaign_id = rs.getShort( CampaignsWeeklySubsStatus.Fields.campaign_id.name() );
		this.week_id = rs.getByte( CampaignsWeeklySubsStatus.Fields.week_id.name() );
		this.profile_id = rs.getByte( CampaignsWeeklySubsStatus.Fields.profile_id.name() );
		this.status_id = rs.getByte( CampaignsWeeklySubsStatus.Fields.status_id.name() );
		this.network_id = rs.getByte( CampaignsWeeklySubsStatus.Fields.network_id.name() );
		this.arpu_id = rs.getByte( CampaignsWeeklySubsStatus.Fields.arpu_id.name() );
		this.qty_control = rs.getFloat( CampaignsWeeklySubsStatus.Fields.qty_control.name() );
		this.qty_control_benef = rs.getFloat( CampaignsWeeklySubsStatus.Fields.qty_control_benef.name() );
		this.qty_provisioned = rs.getFloat( CampaignsWeeklySubsStatus.Fields.qty_provisioned.name() );
		this.qty_notified = rs.getFloat( CampaignsWeeklySubsStatus.Fields.qty_notified.name() );
		this.qty_beneficiary = rs.getFloat( CampaignsWeeklySubsStatus.Fields.qty_beneficiary.name() );
		this.qty_not_targeted = rs.getFloat( CampaignsWeeklySubsStatus.Fields.qty_not_targeted.name() );
		this.control_amount_usage = rs.getDouble( CampaignsWeeklySubsStatus.Fields.control_amount_usage.name() );
		this.provisioned_amount_usage = rs.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_amount_usage.name() );
		this.notified_amount_usage = rs.getDouble( CampaignsWeeklySubsStatus.Fields.notified_amount_usage.name() );
		this.beneficiary_amount_usage = rs.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_usage.name() );
		this.not_targeted_amount_usage = rs.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_usage.name() );
		this.control_qty_msisdn_with_amount_usage = rs.getDouble( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_usage.name() );
		this.provisioned_qty_msisdn_with_amount_usage = rs.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_usage.name() );
		this.notified_qty_msisdn_with_amount_usage = rs.getDouble( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_usage.name() );
		this.beneficiary_qty_msisdn_with_amount_usage = rs.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_usage.name() );
		this.not_targeted_qty_msisdn_with_amount_usage = rs.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_usage.name() );
		this.control_amount_recharge = rs.getDouble( CampaignsWeeklySubsStatus.Fields.control_amount_recharge.name() );
		this.provisioned_amount_recharge = rs.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_amount_recharge.name() );
		this.notified_amount_recharge = rs.getDouble( CampaignsWeeklySubsStatus.Fields.notified_amount_recharge.name() );
		this.beneficiary_amount_recharge = rs.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_recharge.name() );
		this.not_targeted_amount_recharge = rs.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_recharge.name() );
		this.control_qty_msisdn_with_amount_recharge = rs.getDouble( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_recharge.name() );
		this.provisioned_qty_msisdn_with_amount_recharge = rs.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_recharge.name() );
		this.notified_qty_msisdn_with_amount_recharge = rs.getDouble( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_recharge.name() );
		this.beneficiary_qty_msisdn_with_amount_recharge = rs.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_recharge.name() );
		this.not_targeted_qty_msisdn_with_amount_recharge = rs.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_recharge.name() );
		this.control_proba = rs.getDouble( CampaignsWeeklySubsStatus.Fields.control_proba.name() );
		this.provisioned_proba = rs.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_proba.name() );
		this.notified_proba = rs.getDouble( CampaignsWeeklySubsStatus.Fields.notified_proba.name() );
		this.beneficiary_proba = rs.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_proba.name() );
		this.not_targeted_proba = rs.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_proba.name() );

	}

	public CampaignsWeeklySubsStatus( JSONObject jo ) throws JSONException {

		this.campaign_id = (short)jo.getInt( CampaignsWeeklySubsStatus.Fields.campaign_id.name() );
		this.week_id = (byte)jo.getInt( CampaignsWeeklySubsStatus.Fields.week_id.name() );
		this.profile_id = (byte)jo.getInt( CampaignsWeeklySubsStatus.Fields.profile_id.name() );
		this.status_id = (byte)jo.getInt( CampaignsWeeklySubsStatus.Fields.status_id.name() );
		this.network_id = (byte)jo.getInt( CampaignsWeeklySubsStatus.Fields.network_id.name() );
		this.arpu_id = (byte)jo.getInt( CampaignsWeeklySubsStatus.Fields.arpu_id.name() );
		this.qty_control = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.qty_control.name() );
		this.qty_control_benef = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.qty_control_benef.name() );
		this.qty_provisioned = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.qty_provisioned.name() );
		this.qty_notified = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.qty_notified.name() );
		this.qty_beneficiary = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.qty_beneficiary.name() );
		this.qty_not_targeted = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.qty_not_targeted.name() );
		this.control_amount_usage = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_amount_usage.name() );
		this.provisioned_amount_usage = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_amount_usage.name() );
		this.notified_amount_usage = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_amount_usage.name() );
		this.beneficiary_amount_usage = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_usage.name() );
		this.not_targeted_amount_usage = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_usage.name() );
		this.control_qty_msisdn_with_amount_usage = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_usage.name() );
		this.provisioned_qty_msisdn_with_amount_usage = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_usage.name() );
		this.notified_qty_msisdn_with_amount_usage = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_usage.name() );
		this.beneficiary_qty_msisdn_with_amount_usage = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_usage.name() );
		this.not_targeted_qty_msisdn_with_amount_usage = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_usage.name() );
		this.control_amount_recharge = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_amount_recharge.name() );
		this.provisioned_amount_recharge = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_amount_recharge.name() );
		this.notified_amount_recharge = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_amount_recharge.name() );
		this.beneficiary_amount_recharge = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_recharge.name() );
		this.not_targeted_amount_recharge = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_recharge.name() );
		this.control_qty_msisdn_with_amount_recharge = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_recharge.name() );
		this.provisioned_qty_msisdn_with_amount_recharge = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_recharge.name() );
		this.notified_qty_msisdn_with_amount_recharge = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_recharge.name() );
		this.beneficiary_qty_msisdn_with_amount_recharge = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_recharge.name() );
		this.not_targeted_qty_msisdn_with_amount_recharge = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_recharge.name() );
		this.control_proba = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_proba.name() );
		this.provisioned_proba = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_proba.name() );
		this.notified_proba = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_proba.name() );
		this.beneficiary_proba = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_proba.name() );
		this.not_targeted_proba = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_proba.name() );

	}

	public Short getCampaignId() {

		return this.campaign_id;

	}

	public void setCampaignId( Short campaign_id ) {

		this.campaign_id = campaign_id;

	}

	public Byte getWeekId() {

		return this.week_id;

	}

	public void setWeekId( Byte week_id ) {

		this.week_id = week_id;

	}

	public Byte getProfileId() {

		return this.profile_id;

	}

	public void setProfileId( Byte profile_id ) {

		this.profile_id = profile_id;

	}

	public Byte getStatusId() {

		return this.status_id;

	}

	public void setStatusId( Byte status_id ) {

		this.status_id = status_id;

	}

	public Byte getNetworkId() {

		return this.network_id;

	}

	public void setNetworkId( Byte network_id ) {

		this.network_id = network_id;

	}

	public Byte getArpuId() {

		return this.arpu_id;

	}

	public void setArpuId( Byte arpu_id ) {

		this.arpu_id = arpu_id;

	}

	public Float getQtyControl() {

		return this.qty_control;

	}

	public void setQtyControl( Float qty_control ) {

		this.qty_control = qty_control;

	}

	public Float getQtyControlBenef() {

		return this.qty_control_benef;

	}

	public void setQtyControlBenef( Float qty_control_benef ) {

		this.qty_control_benef = qty_control_benef;

	}

	public Float getQtyProvisioned() {

		return this.qty_provisioned;

	}

	public void setQtyProvisioned( Float qty_provisioned ) {

		this.qty_provisioned = qty_provisioned;

	}

	public Float getQtyNotified() {

		return this.qty_notified;

	}

	public void setQtyNotified( Float qty_notified ) {

		this.qty_notified = qty_notified;

	}

	public Float getQtyBeneficiary() {

		return this.qty_beneficiary;

	}

	public void setQtyBeneficiary( Float qty_beneficiary ) {

		this.qty_beneficiary = qty_beneficiary;

	}

	public Float getQtyNotTargeted() {

		return this.qty_not_targeted;

	}

	public void setQtyNotTargeted( Float qty_not_targeted ) {

		this.qty_not_targeted = qty_not_targeted;

	}

	public Double getControlAmountUsage() {

		return this.control_amount_usage;

	}

	public void setControlAmountUsage( Double control_amount_usage ) {

		this.control_amount_usage = control_amount_usage;

	}

	public Double getProvisionedAmountUsage() {

		return this.provisioned_amount_usage;

	}

	public void setProvisionedAmountUsage( Double provisioned_amount_usage ) {

		this.provisioned_amount_usage = provisioned_amount_usage;

	}

	public Double getNotifiedAmountUsage() {

		return this.notified_amount_usage;

	}

	public void setNotifiedAmountUsage( Double notified_amount_usage ) {

		this.notified_amount_usage = notified_amount_usage;

	}

	public Double getBeneficiaryAmountUsage() {

		return this.beneficiary_amount_usage;

	}

	public void setBeneficiaryAmountUsage( Double beneficiary_amount_usage ) {

		this.beneficiary_amount_usage = beneficiary_amount_usage;

	}

	public Double getNotTargetedAmountUsage() {

		return this.not_targeted_amount_usage;

	}

	public void setNotTargetedAmountUsage( Double not_targeted_amount_usage ) {

		this.not_targeted_amount_usage = not_targeted_amount_usage;

	}

	public Double getControlQtyMsisdnWithAmountUsage() {

		return this.control_qty_msisdn_with_amount_usage;

	}

	public void setControlQtyMsisdnWithAmountUsage( Double control_qty_msisdn_with_amount_usage ) {

		this.control_qty_msisdn_with_amount_usage = control_qty_msisdn_with_amount_usage;

	}

	public Double getProvisionedQtyMsisdnWithAmountUsage() {

		return this.provisioned_qty_msisdn_with_amount_usage;

	}

	public void setProvisionedQtyMsisdnWithAmountUsage( Double provisioned_qty_msisdn_with_amount_usage ) {

		this.provisioned_qty_msisdn_with_amount_usage = provisioned_qty_msisdn_with_amount_usage;

	}

	public Double getNotifiedQtyMsisdnWithAmountUsage() {

		return this.notified_qty_msisdn_with_amount_usage;

	}

	public void setNotifiedQtyMsisdnWithAmountUsage( Double notified_qty_msisdn_with_amount_usage ) {

		this.notified_qty_msisdn_with_amount_usage = notified_qty_msisdn_with_amount_usage;

	}

	public Double getBeneficiaryQtyMsisdnWithAmountUsage() {

		return this.beneficiary_qty_msisdn_with_amount_usage;

	}

	public void setBeneficiaryQtyMsisdnWithAmountUsage( Double beneficiary_qty_msisdn_with_amount_usage ) {

		this.beneficiary_qty_msisdn_with_amount_usage = beneficiary_qty_msisdn_with_amount_usage;

	}

	public Double getNotTargetedQtyMsisdnWithAmountUsage() {

		return this.not_targeted_qty_msisdn_with_amount_usage;

	}

	public void setNotTargetedQtyMsisdnWithAmountUsage( Double not_targeted_qty_msisdn_with_amount_usage ) {

		this.not_targeted_qty_msisdn_with_amount_usage = not_targeted_qty_msisdn_with_amount_usage;

	}

	public Double getControlAmountRecharge() {

		return this.control_amount_recharge;

	}

	public void setControlAmountRecharge( Double control_amount_recharge ) {

		this.control_amount_recharge = control_amount_recharge;

	}

	public Double getProvisionedAmountRecharge() {

		return this.provisioned_amount_recharge;

	}

	public void setProvisionedAmountRecharge( Double provisioned_amount_recharge ) {

		this.provisioned_amount_recharge = provisioned_amount_recharge;

	}

	public Double getNotifiedAmountRecharge() {

		return this.notified_amount_recharge;

	}

	public void setNotifiedAmountRecharge( Double notified_amount_recharge ) {

		this.notified_amount_recharge = notified_amount_recharge;

	}

	public Double getBeneficiaryAmountRecharge() {

		return this.beneficiary_amount_recharge;

	}

	public void setBeneficiaryAmountRecharge( Double beneficiary_amount_recharge ) {

		this.beneficiary_amount_recharge = beneficiary_amount_recharge;

	}

	public Double getNotTargetedAmountRecharge() {

		return this.not_targeted_amount_recharge;

	}

	public void setNotTargetedAmountRecharge( Double not_targeted_amount_recharge ) {

		this.not_targeted_amount_recharge = not_targeted_amount_recharge;

	}

	public Double getControlQtyMsisdnWithAmountRecharge() {

		return this.control_qty_msisdn_with_amount_recharge;

	}

	public void setControlQtyMsisdnWithAmountRecharge( Double control_qty_msisdn_with_amount_recharge ) {

		this.control_qty_msisdn_with_amount_recharge = control_qty_msisdn_with_amount_recharge;

	}

	public Double getProvisionedQtyMsisdnWithAmountRecharge() {

		return this.provisioned_qty_msisdn_with_amount_recharge;

	}

	public void setProvisionedQtyMsisdnWithAmountRecharge( Double provisioned_qty_msisdn_with_amount_recharge ) {

		this.provisioned_qty_msisdn_with_amount_recharge = provisioned_qty_msisdn_with_amount_recharge;

	}

	public Double getNotifiedQtyMsisdnWithAmountRecharge() {

		return this.notified_qty_msisdn_with_amount_recharge;

	}

	public void setNotifiedQtyMsisdnWithAmountRecharge( Double notified_qty_msisdn_with_amount_recharge ) {

		this.notified_qty_msisdn_with_amount_recharge = notified_qty_msisdn_with_amount_recharge;

	}

	public Double getBeneficiaryQtyMsisdnWithAmountRecharge() {

		return this.beneficiary_qty_msisdn_with_amount_recharge;

	}

	public void setBeneficiaryQtyMsisdnWithAmountRecharge( Double beneficiary_qty_msisdn_with_amount_recharge ) {

		this.beneficiary_qty_msisdn_with_amount_recharge = beneficiary_qty_msisdn_with_amount_recharge;

	}

	public Double getNotTargetedQtyMsisdnWithAmountRecharge() {

		return this.not_targeted_qty_msisdn_with_amount_recharge;

	}

	public void setNotTargetedQtyMsisdnWithAmountRecharge( Double not_targeted_qty_msisdn_with_amount_recharge ) {

		this.not_targeted_qty_msisdn_with_amount_recharge = not_targeted_qty_msisdn_with_amount_recharge;

	}

	public Double getControlProba() {

		return this.control_proba;

	}

	public void setControlProba( Double control_proba ) {

		this.control_proba = control_proba;

	}

	public Double getProvisionedProba() {

		return this.provisioned_proba;

	}

	public void setProvisionedProba( Double provisioned_proba ) {

		this.provisioned_proba = provisioned_proba;

	}

	public Double getNotifiedProba() {

		return this.notified_proba;

	}

	public void setNotifiedProba( Double notified_proba ) {

		this.notified_proba = notified_proba;

	}

	public Double getBeneficiaryProba() {

		return this.beneficiary_proba;

	}

	public void setBeneficiaryProba( Double beneficiary_proba ) {

		this.beneficiary_proba = beneficiary_proba;

	}

	public Double getNotTargetedProba() {

		return this.not_targeted_proba;

	}

	public void setNotTargetedProba( Double not_targeted_proba ) {

		this.not_targeted_proba = not_targeted_proba;

	}

	public Fields[] getEntityFields() {

		return CampaignsWeeklySubsStatus.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"campaign_id\": \"" ).append( this.getCampaignId() ).append( "\", " )
			.append( "\"week_id\": \"" ).append( this.getWeekId() ).append( "\", " )
			.append( "\"profile_id\": \"" ).append( this.getProfileId() ).append( "\", " )
			.append( "\"status_id\": \"" ).append( this.getStatusId() ).append( "\", " )
			.append( "\"network_id\": \"" ).append( this.getNetworkId() ).append( "\", " )
			.append( "\"arpu_id\": \"" ).append( this.getArpuId() ).append( "\", " )
			.append( "\"qty_control\": \"" ).append( this.getQtyControl() ).append( "\", " )
			.append( "\"qty_control_benef\": \"" ).append( this.getQtyControlBenef() ).append( "\", " )
			.append( "\"qty_provisioned\": \"" ).append( this.getQtyProvisioned() ).append( "\", " )
			.append( "\"qty_notified\": \"" ).append( this.getQtyNotified() ).append( "\", " )
			.append( "\"qty_beneficiary\": \"" ).append( this.getQtyBeneficiary() ).append( "\", " )
			.append( "\"qty_not_targeted\": \"" ).append( this.getQtyNotTargeted() ).append( "\", " )
			.append( "\"control_amount_usage\": \"" ).append( this.getControlAmountUsage() ).append( "\", " )
			.append( "\"provisioned_amount_usage\": \"" ).append( this.getProvisionedAmountUsage() ).append( "\", " )
			.append( "\"notified_amount_usage\": \"" ).append( this.getNotifiedAmountUsage() ).append( "\", " )
			.append( "\"beneficiary_amount_usage\": \"" ).append( this.getBeneficiaryAmountUsage() ).append( "\", " )
			.append( "\"not_targeted_amount_usage\": \"" ).append( this.getNotTargetedAmountUsage() ).append( "\", " )
			.append( "\"control_qty_msisdn_with_amount_usage\": \"" ).append( this.getControlQtyMsisdnWithAmountUsage() ).append( "\", " )
			.append( "\"provisioned_qty_msisdn_with_amount_usage\": \"" ).append( this.getProvisionedQtyMsisdnWithAmountUsage() ).append( "\", " )
			.append( "\"notified_qty_msisdn_with_amount_usage\": \"" ).append( this.getNotifiedQtyMsisdnWithAmountUsage() ).append( "\", " )
			.append( "\"beneficiary_qty_msisdn_with_amount_usage\": \"" ).append( this.getBeneficiaryQtyMsisdnWithAmountUsage() ).append( "\", " )
			.append( "\"not_targeted_qty_msisdn_with_amount_usage\": \"" ).append( this.getNotTargetedQtyMsisdnWithAmountUsage() ).append( "\", " )
			.append( "\"control_amount_recharge\": \"" ).append( this.getControlAmountRecharge() ).append( "\", " )
			.append( "\"provisioned_amount_recharge\": \"" ).append( this.getProvisionedAmountRecharge() ).append( "\", " )
			.append( "\"notified_amount_recharge\": \"" ).append( this.getNotifiedAmountRecharge() ).append( "\", " )
			.append( "\"beneficiary_amount_recharge\": \"" ).append( this.getBeneficiaryAmountRecharge() ).append( "\", " )
			.append( "\"not_targeted_amount_recharge\": \"" ).append( this.getNotTargetedAmountRecharge() ).append( "\", " )
			.append( "\"control_qty_msisdn_with_amount_recharge\": \"" ).append( this.getControlQtyMsisdnWithAmountRecharge() ).append( "\", " )
			.append( "\"provisioned_qty_msisdn_with_amount_recharge\": \"" ).append( this.getProvisionedQtyMsisdnWithAmountRecharge() ).append( "\", " )
			.append( "\"notified_qty_msisdn_with_amount_recharge\": \"" ).append( this.getNotifiedQtyMsisdnWithAmountRecharge() ).append( "\", " )
			.append( "\"beneficiary_qty_msisdn_with_amount_recharge\": \"" ).append( this.getBeneficiaryQtyMsisdnWithAmountRecharge() ).append( "\", " )
			.append( "\"not_targeted_qty_msisdn_with_amount_recharge\": \"" ).append( this.getNotTargetedQtyMsisdnWithAmountRecharge() ).append( "\", " )
			.append( "\"control_proba\": \"" ).append( this.getControlProba() ).append( "\", " )
			.append( "\"provisioned_proba\": \"" ).append( this.getProvisionedProba() ).append( "\", " )
			.append( "\"notified_proba\": \"" ).append( this.getNotifiedProba() ).append( "\", " )
			.append( "\"beneficiary_proba\": \"" ).append( this.getBeneficiaryProba() ).append( "\", " )
			.append( "\"not_targeted_proba\": \"" ).append( this.getNotTargetedProba() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }