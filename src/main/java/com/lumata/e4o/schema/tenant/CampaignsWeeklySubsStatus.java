package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "campaigns_weekly_subs_status" )
public class CampaignsWeeklySubsStatus { 

	public enum Fields { campaign_id, week_id, profile_id, status_id, network_id, arpu_id, seniority_id, qty_control, qty_control_benef, qty_provisioned, qty_notified, qty_beneficiary, qty_not_targeted, control_amount_usage, provisioned_amount_usage, notified_amount_usage, beneficiary_amount_usage, not_targeted_amount_usage, control_qty_msisdn_with_amount_usage, provisioned_qty_msisdn_with_amount_usage, notified_qty_msisdn_with_amount_usage, beneficiary_qty_msisdn_with_amount_usage, not_targeted_qty_msisdn_with_amount_usage, control_amount_recharge, provisioned_amount_recharge, notified_amount_recharge, beneficiary_amount_recharge, not_targeted_amount_recharge, control_qty_msisdn_with_amount_recharge, provisioned_qty_msisdn_with_amount_recharge, notified_qty_msisdn_with_amount_recharge, beneficiary_qty_msisdn_with_amount_recharge, not_targeted_qty_msisdn_with_amount_recharge, control_amount_invoice, provisioned_amount_invoice, notified_amount_invoice, beneficiary_amount_invoice, not_targeted_amount_invoice, control_qty_msisdn_with_amount_invoice, provisioned_qty_msisdn_with_amount_invoice, notified_qty_msisdn_with_amount_invoice, beneficiary_qty_msisdn_with_amount_invoice, not_targeted_qty_msisdn_with_amount_invoice, control_amount_payment, provisioned_amount_payment, notified_amount_payment, beneficiary_amount_payment, not_targeted_amount_payment, control_qty_msisdn_with_amount_payment, provisioned_qty_msisdn_with_amount_payment, notified_qty_msisdn_with_amount_payment, beneficiary_qty_msisdn_with_amount_payment, not_targeted_qty_msisdn_with_amount_payment, control_amount_call, provisioned_amount_call, notified_amount_call, beneficiary_amount_call, not_targeted_amount_call, control_qty_msisdn_with_amount_call, provisioned_qty_msisdn_with_amount_call, notified_qty_msisdn_with_amount_call, beneficiary_qty_msisdn_with_amount_call, not_targeted_qty_msisdn_with_amount_call, control_amount_message, provisioned_amount_message, notified_amount_message, beneficiary_amount_message, not_targeted_amount_message, control_qty_msisdn_with_amount_message, provisioned_qty_msisdn_with_amount_message, notified_qty_msisdn_with_amount_message, beneficiary_qty_msisdn_with_amount_message, not_targeted_qty_msisdn_with_amount_message, control_amount_data, provisioned_amount_data, notified_amount_data, beneficiary_amount_data, not_targeted_amount_data, control_qty_msisdn_with_amount_data, provisioned_qty_msisdn_with_amount_data, notified_qty_msisdn_with_amount_data, beneficiary_qty_msisdn_with_amount_data, not_targeted_qty_msisdn_with_amount_data, control_proba, provisioned_proba, notified_proba, beneficiary_proba, not_targeted_proba }

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "campaign_id",
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
			comment = "",
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
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
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
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
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
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
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
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
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
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getArpuId",
			setMethod = "setArpuId"
	)
	private Byte arpu_id;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "seniority_id",
			type = "tinyint(4) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getSeniorityId",
			setMethod = "setSeniorityId"
	)
	private Byte seniority_id;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "qty_control",
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
			getMethod = "getQtyControl",
			setMethod = "setQtyControl"
	)
	private Integer qty_control;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "qty_control_benef",
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
			getMethod = "getQtyControlBenef",
			setMethod = "setQtyControlBenef"
	)
	private Integer qty_control_benef;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "qty_provisioned",
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
			getMethod = "getQtyProvisioned",
			setMethod = "setQtyProvisioned"
	)
	private Integer qty_provisioned;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "qty_notified",
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
			getMethod = "getQtyNotified",
			setMethod = "setQtyNotified"
	)
	private Integer qty_notified;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "qty_beneficiary",
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
			getMethod = "getQtyBeneficiary",
			setMethod = "setQtyBeneficiary"
	)
	private Integer qty_beneficiary;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "qty_not_targeted",
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
			getMethod = "getQtyNotTargeted",
			setMethod = "setQtyNotTargeted"
	)
	private Integer qty_not_targeted;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_amount_usage",
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
			getMethod = "getControlAmountUsage",
			setMethod = "setControlAmountUsage"
	)
	private Float control_amount_usage;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_amount_usage",
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
			getMethod = "getProvisionedAmountUsage",
			setMethod = "setProvisionedAmountUsage"
	)
	private Float provisioned_amount_usage;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_amount_usage",
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
			getMethod = "getNotifiedAmountUsage",
			setMethod = "setNotifiedAmountUsage"
	)
	private Float notified_amount_usage;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_amount_usage",
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
			getMethod = "getBeneficiaryAmountUsage",
			setMethod = "setBeneficiaryAmountUsage"
	)
	private Float beneficiary_amount_usage;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_amount_usage",
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
			getMethod = "getNotTargetedAmountUsage",
			setMethod = "setNotTargetedAmountUsage"
	)
	private Float not_targeted_amount_usage;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_qty_msisdn_with_amount_usage",
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
			getMethod = "getControlQtyMsisdnWithAmountUsage",
			setMethod = "setControlQtyMsisdnWithAmountUsage"
	)
	private Float control_qty_msisdn_with_amount_usage;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_qty_msisdn_with_amount_usage",
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
			getMethod = "getProvisionedQtyMsisdnWithAmountUsage",
			setMethod = "setProvisionedQtyMsisdnWithAmountUsage"
	)
	private Float provisioned_qty_msisdn_with_amount_usage;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_qty_msisdn_with_amount_usage",
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
			getMethod = "getNotifiedQtyMsisdnWithAmountUsage",
			setMethod = "setNotifiedQtyMsisdnWithAmountUsage"
	)
	private Float notified_qty_msisdn_with_amount_usage;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_qty_msisdn_with_amount_usage",
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
			getMethod = "getBeneficiaryQtyMsisdnWithAmountUsage",
			setMethod = "setBeneficiaryQtyMsisdnWithAmountUsage"
	)
	private Float beneficiary_qty_msisdn_with_amount_usage;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_qty_msisdn_with_amount_usage",
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
			getMethod = "getNotTargetedQtyMsisdnWithAmountUsage",
			setMethod = "setNotTargetedQtyMsisdnWithAmountUsage"
	)
	private Float not_targeted_qty_msisdn_with_amount_usage;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_amount_recharge",
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
			getMethod = "getControlAmountRecharge",
			setMethod = "setControlAmountRecharge"
	)
	private Float control_amount_recharge;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_amount_recharge",
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
			getMethod = "getProvisionedAmountRecharge",
			setMethod = "setProvisionedAmountRecharge"
	)
	private Float provisioned_amount_recharge;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_amount_recharge",
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
			getMethod = "getNotifiedAmountRecharge",
			setMethod = "setNotifiedAmountRecharge"
	)
	private Float notified_amount_recharge;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_amount_recharge",
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
			getMethod = "getBeneficiaryAmountRecharge",
			setMethod = "setBeneficiaryAmountRecharge"
	)
	private Float beneficiary_amount_recharge;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_amount_recharge",
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
			getMethod = "getNotTargetedAmountRecharge",
			setMethod = "setNotTargetedAmountRecharge"
	)
	private Float not_targeted_amount_recharge;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_qty_msisdn_with_amount_recharge",
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
			getMethod = "getControlQtyMsisdnWithAmountRecharge",
			setMethod = "setControlQtyMsisdnWithAmountRecharge"
	)
	private Float control_qty_msisdn_with_amount_recharge;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_qty_msisdn_with_amount_recharge",
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
			getMethod = "getProvisionedQtyMsisdnWithAmountRecharge",
			setMethod = "setProvisionedQtyMsisdnWithAmountRecharge"
	)
	private Float provisioned_qty_msisdn_with_amount_recharge;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_qty_msisdn_with_amount_recharge",
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
			getMethod = "getNotifiedQtyMsisdnWithAmountRecharge",
			setMethod = "setNotifiedQtyMsisdnWithAmountRecharge"
	)
	private Float notified_qty_msisdn_with_amount_recharge;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_qty_msisdn_with_amount_recharge",
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
			getMethod = "getBeneficiaryQtyMsisdnWithAmountRecharge",
			setMethod = "setBeneficiaryQtyMsisdnWithAmountRecharge"
	)
	private Float beneficiary_qty_msisdn_with_amount_recharge;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_qty_msisdn_with_amount_recharge",
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
			getMethod = "getNotTargetedQtyMsisdnWithAmountRecharge",
			setMethod = "setNotTargetedQtyMsisdnWithAmountRecharge"
	)
	private Float not_targeted_qty_msisdn_with_amount_recharge;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_amount_invoice",
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
			getMethod = "getControlAmountInvoice",
			setMethod = "setControlAmountInvoice"
	)
	private Float control_amount_invoice;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_amount_invoice",
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
			getMethod = "getProvisionedAmountInvoice",
			setMethod = "setProvisionedAmountInvoice"
	)
	private Float provisioned_amount_invoice;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_amount_invoice",
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
			getMethod = "getNotifiedAmountInvoice",
			setMethod = "setNotifiedAmountInvoice"
	)
	private Float notified_amount_invoice;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_amount_invoice",
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
			getMethod = "getBeneficiaryAmountInvoice",
			setMethod = "setBeneficiaryAmountInvoice"
	)
	private Float beneficiary_amount_invoice;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_amount_invoice",
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
			getMethod = "getNotTargetedAmountInvoice",
			setMethod = "setNotTargetedAmountInvoice"
	)
	private Float not_targeted_amount_invoice;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_qty_msisdn_with_amount_invoice",
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
			getMethod = "getControlQtyMsisdnWithAmountInvoice",
			setMethod = "setControlQtyMsisdnWithAmountInvoice"
	)
	private Float control_qty_msisdn_with_amount_invoice;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_qty_msisdn_with_amount_invoice",
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
			getMethod = "getProvisionedQtyMsisdnWithAmountInvoice",
			setMethod = "setProvisionedQtyMsisdnWithAmountInvoice"
	)
	private Float provisioned_qty_msisdn_with_amount_invoice;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_qty_msisdn_with_amount_invoice",
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
			getMethod = "getNotifiedQtyMsisdnWithAmountInvoice",
			setMethod = "setNotifiedQtyMsisdnWithAmountInvoice"
	)
	private Float notified_qty_msisdn_with_amount_invoice;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_qty_msisdn_with_amount_invoice",
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
			getMethod = "getBeneficiaryQtyMsisdnWithAmountInvoice",
			setMethod = "setBeneficiaryQtyMsisdnWithAmountInvoice"
	)
	private Float beneficiary_qty_msisdn_with_amount_invoice;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_qty_msisdn_with_amount_invoice",
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
			getMethod = "getNotTargetedQtyMsisdnWithAmountInvoice",
			setMethod = "setNotTargetedQtyMsisdnWithAmountInvoice"
	)
	private Float not_targeted_qty_msisdn_with_amount_invoice;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_amount_payment",
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
			getMethod = "getControlAmountPayment",
			setMethod = "setControlAmountPayment"
	)
	private Float control_amount_payment;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_amount_payment",
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
			getMethod = "getProvisionedAmountPayment",
			setMethod = "setProvisionedAmountPayment"
	)
	private Float provisioned_amount_payment;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_amount_payment",
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
			getMethod = "getNotifiedAmountPayment",
			setMethod = "setNotifiedAmountPayment"
	)
	private Float notified_amount_payment;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_amount_payment",
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
			getMethod = "getBeneficiaryAmountPayment",
			setMethod = "setBeneficiaryAmountPayment"
	)
	private Float beneficiary_amount_payment;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_amount_payment",
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
			getMethod = "getNotTargetedAmountPayment",
			setMethod = "setNotTargetedAmountPayment"
	)
	private Float not_targeted_amount_payment;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_qty_msisdn_with_amount_payment",
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
			getMethod = "getControlQtyMsisdnWithAmountPayment",
			setMethod = "setControlQtyMsisdnWithAmountPayment"
	)
	private Float control_qty_msisdn_with_amount_payment;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_qty_msisdn_with_amount_payment",
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
			getMethod = "getProvisionedQtyMsisdnWithAmountPayment",
			setMethod = "setProvisionedQtyMsisdnWithAmountPayment"
	)
	private Float provisioned_qty_msisdn_with_amount_payment;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_qty_msisdn_with_amount_payment",
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
			getMethod = "getNotifiedQtyMsisdnWithAmountPayment",
			setMethod = "setNotifiedQtyMsisdnWithAmountPayment"
	)
	private Float notified_qty_msisdn_with_amount_payment;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_qty_msisdn_with_amount_payment",
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
			getMethod = "getBeneficiaryQtyMsisdnWithAmountPayment",
			setMethod = "setBeneficiaryQtyMsisdnWithAmountPayment"
	)
	private Float beneficiary_qty_msisdn_with_amount_payment;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_qty_msisdn_with_amount_payment",
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
			getMethod = "getNotTargetedQtyMsisdnWithAmountPayment",
			setMethod = "setNotTargetedQtyMsisdnWithAmountPayment"
	)
	private Float not_targeted_qty_msisdn_with_amount_payment;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_amount_call",
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
			getMethod = "getControlAmountCall",
			setMethod = "setControlAmountCall"
	)
	private Float control_amount_call;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_amount_call",
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
			getMethod = "getProvisionedAmountCall",
			setMethod = "setProvisionedAmountCall"
	)
	private Float provisioned_amount_call;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_amount_call",
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
			getMethod = "getNotifiedAmountCall",
			setMethod = "setNotifiedAmountCall"
	)
	private Float notified_amount_call;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_amount_call",
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
			getMethod = "getBeneficiaryAmountCall",
			setMethod = "setBeneficiaryAmountCall"
	)
	private Float beneficiary_amount_call;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_amount_call",
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
			getMethod = "getNotTargetedAmountCall",
			setMethod = "setNotTargetedAmountCall"
	)
	private Float not_targeted_amount_call;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_qty_msisdn_with_amount_call",
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
			getMethod = "getControlQtyMsisdnWithAmountCall",
			setMethod = "setControlQtyMsisdnWithAmountCall"
	)
	private Float control_qty_msisdn_with_amount_call;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_qty_msisdn_with_amount_call",
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
			getMethod = "getProvisionedQtyMsisdnWithAmountCall",
			setMethod = "setProvisionedQtyMsisdnWithAmountCall"
	)
	private Float provisioned_qty_msisdn_with_amount_call;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_qty_msisdn_with_amount_call",
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
			getMethod = "getNotifiedQtyMsisdnWithAmountCall",
			setMethod = "setNotifiedQtyMsisdnWithAmountCall"
	)
	private Float notified_qty_msisdn_with_amount_call;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_qty_msisdn_with_amount_call",
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
			getMethod = "getBeneficiaryQtyMsisdnWithAmountCall",
			setMethod = "setBeneficiaryQtyMsisdnWithAmountCall"
	)
	private Float beneficiary_qty_msisdn_with_amount_call;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_qty_msisdn_with_amount_call",
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
			getMethod = "getNotTargetedQtyMsisdnWithAmountCall",
			setMethod = "setNotTargetedQtyMsisdnWithAmountCall"
	)
	private Float not_targeted_qty_msisdn_with_amount_call;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_amount_message",
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
			getMethod = "getControlAmountMessage",
			setMethod = "setControlAmountMessage"
	)
	private Float control_amount_message;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_amount_message",
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
			getMethod = "getProvisionedAmountMessage",
			setMethod = "setProvisionedAmountMessage"
	)
	private Float provisioned_amount_message;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_amount_message",
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
			getMethod = "getNotifiedAmountMessage",
			setMethod = "setNotifiedAmountMessage"
	)
	private Float notified_amount_message;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_amount_message",
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
			getMethod = "getBeneficiaryAmountMessage",
			setMethod = "setBeneficiaryAmountMessage"
	)
	private Float beneficiary_amount_message;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_amount_message",
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
			getMethod = "getNotTargetedAmountMessage",
			setMethod = "setNotTargetedAmountMessage"
	)
	private Float not_targeted_amount_message;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_qty_msisdn_with_amount_message",
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
			getMethod = "getControlQtyMsisdnWithAmountMessage",
			setMethod = "setControlQtyMsisdnWithAmountMessage"
	)
	private Float control_qty_msisdn_with_amount_message;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_qty_msisdn_with_amount_message",
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
			getMethod = "getProvisionedQtyMsisdnWithAmountMessage",
			setMethod = "setProvisionedQtyMsisdnWithAmountMessage"
	)
	private Float provisioned_qty_msisdn_with_amount_message;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_qty_msisdn_with_amount_message",
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
			getMethod = "getNotifiedQtyMsisdnWithAmountMessage",
			setMethod = "setNotifiedQtyMsisdnWithAmountMessage"
	)
	private Float notified_qty_msisdn_with_amount_message;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_qty_msisdn_with_amount_message",
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
			getMethod = "getBeneficiaryQtyMsisdnWithAmountMessage",
			setMethod = "setBeneficiaryQtyMsisdnWithAmountMessage"
	)
	private Float beneficiary_qty_msisdn_with_amount_message;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_qty_msisdn_with_amount_message",
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
			getMethod = "getNotTargetedQtyMsisdnWithAmountMessage",
			setMethod = "setNotTargetedQtyMsisdnWithAmountMessage"
	)
	private Float not_targeted_qty_msisdn_with_amount_message;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_amount_data",
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
			getMethod = "getControlAmountData",
			setMethod = "setControlAmountData"
	)
	private Float control_amount_data;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_amount_data",
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
			getMethod = "getProvisionedAmountData",
			setMethod = "setProvisionedAmountData"
	)
	private Float provisioned_amount_data;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_amount_data",
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
			getMethod = "getNotifiedAmountData",
			setMethod = "setNotifiedAmountData"
	)
	private Float notified_amount_data;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_amount_data",
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
			getMethod = "getBeneficiaryAmountData",
			setMethod = "setBeneficiaryAmountData"
	)
	private Float beneficiary_amount_data;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_amount_data",
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
			getMethod = "getNotTargetedAmountData",
			setMethod = "setNotTargetedAmountData"
	)
	private Float not_targeted_amount_data;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_qty_msisdn_with_amount_data",
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
			getMethod = "getControlQtyMsisdnWithAmountData",
			setMethod = "setControlQtyMsisdnWithAmountData"
	)
	private Float control_qty_msisdn_with_amount_data;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_qty_msisdn_with_amount_data",
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
			getMethod = "getProvisionedQtyMsisdnWithAmountData",
			setMethod = "setProvisionedQtyMsisdnWithAmountData"
	)
	private Float provisioned_qty_msisdn_with_amount_data;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_qty_msisdn_with_amount_data",
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
			getMethod = "getNotifiedQtyMsisdnWithAmountData",
			setMethod = "setNotifiedQtyMsisdnWithAmountData"
	)
	private Float notified_qty_msisdn_with_amount_data;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_qty_msisdn_with_amount_data",
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
			getMethod = "getBeneficiaryQtyMsisdnWithAmountData",
			setMethod = "setBeneficiaryQtyMsisdnWithAmountData"
	)
	private Float beneficiary_qty_msisdn_with_amount_data;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_qty_msisdn_with_amount_data",
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
			getMethod = "getNotTargetedQtyMsisdnWithAmountData",
			setMethod = "setNotTargetedQtyMsisdnWithAmountData"
	)
	private Float not_targeted_qty_msisdn_with_amount_data;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_proba",
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
			getMethod = "getControlProba",
			setMethod = "setControlProba"
	)
	private Float control_proba;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_proba",
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
			getMethod = "getProvisionedProba",
			setMethod = "setProvisionedProba"
	)
	private Float provisioned_proba;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_proba",
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
			getMethod = "getNotifiedProba",
			setMethod = "setNotifiedProba"
	)
	private Float notified_proba;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_proba",
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
			getMethod = "getBeneficiaryProba",
			setMethod = "setBeneficiaryProba"
	)
	private Float beneficiary_proba;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_proba",
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
			getMethod = "getNotTargetedProba",
			setMethod = "setNotTargetedProba"
	)
	private Float not_targeted_proba;


	public CampaignsWeeklySubsStatus() {} 

	public CampaignsWeeklySubsStatus( ResultSet rs ) throws SQLException {

		this.campaign_id = rs.getShort( CampaignsWeeklySubsStatus.Fields.campaign_id.name() );
		this.week_id = rs.getByte( CampaignsWeeklySubsStatus.Fields.week_id.name() );
		this.profile_id = rs.getByte( CampaignsWeeklySubsStatus.Fields.profile_id.name() );
		this.status_id = rs.getByte( CampaignsWeeklySubsStatus.Fields.status_id.name() );
		this.network_id = rs.getByte( CampaignsWeeklySubsStatus.Fields.network_id.name() );
		this.arpu_id = rs.getByte( CampaignsWeeklySubsStatus.Fields.arpu_id.name() );
		this.seniority_id = rs.getByte( CampaignsWeeklySubsStatus.Fields.seniority_id.name() );
		this.qty_control = rs.getInt( CampaignsWeeklySubsStatus.Fields.qty_control.name() );
		this.qty_control_benef = rs.getInt( CampaignsWeeklySubsStatus.Fields.qty_control_benef.name() );
		this.qty_provisioned = rs.getInt( CampaignsWeeklySubsStatus.Fields.qty_provisioned.name() );
		this.qty_notified = rs.getInt( CampaignsWeeklySubsStatus.Fields.qty_notified.name() );
		this.qty_beneficiary = rs.getInt( CampaignsWeeklySubsStatus.Fields.qty_beneficiary.name() );
		this.qty_not_targeted = rs.getInt( CampaignsWeeklySubsStatus.Fields.qty_not_targeted.name() );
		this.control_amount_usage = rs.getFloat( CampaignsWeeklySubsStatus.Fields.control_amount_usage.name() );
		this.provisioned_amount_usage = rs.getFloat( CampaignsWeeklySubsStatus.Fields.provisioned_amount_usage.name() );
		this.notified_amount_usage = rs.getFloat( CampaignsWeeklySubsStatus.Fields.notified_amount_usage.name() );
		this.beneficiary_amount_usage = rs.getFloat( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_usage.name() );
		this.not_targeted_amount_usage = rs.getFloat( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_usage.name() );
		this.control_qty_msisdn_with_amount_usage = rs.getFloat( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_usage.name() );
		this.provisioned_qty_msisdn_with_amount_usage = rs.getFloat( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_usage.name() );
		this.notified_qty_msisdn_with_amount_usage = rs.getFloat( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_usage.name() );
		this.beneficiary_qty_msisdn_with_amount_usage = rs.getFloat( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_usage.name() );
		this.not_targeted_qty_msisdn_with_amount_usage = rs.getFloat( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_usage.name() );
		this.control_amount_recharge = rs.getFloat( CampaignsWeeklySubsStatus.Fields.control_amount_recharge.name() );
		this.provisioned_amount_recharge = rs.getFloat( CampaignsWeeklySubsStatus.Fields.provisioned_amount_recharge.name() );
		this.notified_amount_recharge = rs.getFloat( CampaignsWeeklySubsStatus.Fields.notified_amount_recharge.name() );
		this.beneficiary_amount_recharge = rs.getFloat( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_recharge.name() );
		this.not_targeted_amount_recharge = rs.getFloat( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_recharge.name() );
		this.control_qty_msisdn_with_amount_recharge = rs.getFloat( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_recharge.name() );
		this.provisioned_qty_msisdn_with_amount_recharge = rs.getFloat( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_recharge.name() );
		this.notified_qty_msisdn_with_amount_recharge = rs.getFloat( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_recharge.name() );
		this.beneficiary_qty_msisdn_with_amount_recharge = rs.getFloat( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_recharge.name() );
		this.not_targeted_qty_msisdn_with_amount_recharge = rs.getFloat( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_recharge.name() );
		this.control_amount_invoice = rs.getFloat( CampaignsWeeklySubsStatus.Fields.control_amount_invoice.name() );
		this.provisioned_amount_invoice = rs.getFloat( CampaignsWeeklySubsStatus.Fields.provisioned_amount_invoice.name() );
		this.notified_amount_invoice = rs.getFloat( CampaignsWeeklySubsStatus.Fields.notified_amount_invoice.name() );
		this.beneficiary_amount_invoice = rs.getFloat( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_invoice.name() );
		this.not_targeted_amount_invoice = rs.getFloat( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_invoice.name() );
		this.control_qty_msisdn_with_amount_invoice = rs.getFloat( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_invoice.name() );
		this.provisioned_qty_msisdn_with_amount_invoice = rs.getFloat( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_invoice.name() );
		this.notified_qty_msisdn_with_amount_invoice = rs.getFloat( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_invoice.name() );
		this.beneficiary_qty_msisdn_with_amount_invoice = rs.getFloat( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_invoice.name() );
		this.not_targeted_qty_msisdn_with_amount_invoice = rs.getFloat( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_invoice.name() );
		this.control_amount_payment = rs.getFloat( CampaignsWeeklySubsStatus.Fields.control_amount_payment.name() );
		this.provisioned_amount_payment = rs.getFloat( CampaignsWeeklySubsStatus.Fields.provisioned_amount_payment.name() );
		this.notified_amount_payment = rs.getFloat( CampaignsWeeklySubsStatus.Fields.notified_amount_payment.name() );
		this.beneficiary_amount_payment = rs.getFloat( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_payment.name() );
		this.not_targeted_amount_payment = rs.getFloat( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_payment.name() );
		this.control_qty_msisdn_with_amount_payment = rs.getFloat( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_payment.name() );
		this.provisioned_qty_msisdn_with_amount_payment = rs.getFloat( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_payment.name() );
		this.notified_qty_msisdn_with_amount_payment = rs.getFloat( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_payment.name() );
		this.beneficiary_qty_msisdn_with_amount_payment = rs.getFloat( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_payment.name() );
		this.not_targeted_qty_msisdn_with_amount_payment = rs.getFloat( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_payment.name() );
		this.control_amount_call = rs.getFloat( CampaignsWeeklySubsStatus.Fields.control_amount_call.name() );
		this.provisioned_amount_call = rs.getFloat( CampaignsWeeklySubsStatus.Fields.provisioned_amount_call.name() );
		this.notified_amount_call = rs.getFloat( CampaignsWeeklySubsStatus.Fields.notified_amount_call.name() );
		this.beneficiary_amount_call = rs.getFloat( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_call.name() );
		this.not_targeted_amount_call = rs.getFloat( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_call.name() );
		this.control_qty_msisdn_with_amount_call = rs.getFloat( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_call.name() );
		this.provisioned_qty_msisdn_with_amount_call = rs.getFloat( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_call.name() );
		this.notified_qty_msisdn_with_amount_call = rs.getFloat( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_call.name() );
		this.beneficiary_qty_msisdn_with_amount_call = rs.getFloat( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_call.name() );
		this.not_targeted_qty_msisdn_with_amount_call = rs.getFloat( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_call.name() );
		this.control_amount_message = rs.getFloat( CampaignsWeeklySubsStatus.Fields.control_amount_message.name() );
		this.provisioned_amount_message = rs.getFloat( CampaignsWeeklySubsStatus.Fields.provisioned_amount_message.name() );
		this.notified_amount_message = rs.getFloat( CampaignsWeeklySubsStatus.Fields.notified_amount_message.name() );
		this.beneficiary_amount_message = rs.getFloat( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_message.name() );
		this.not_targeted_amount_message = rs.getFloat( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_message.name() );
		this.control_qty_msisdn_with_amount_message = rs.getFloat( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_message.name() );
		this.provisioned_qty_msisdn_with_amount_message = rs.getFloat( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_message.name() );
		this.notified_qty_msisdn_with_amount_message = rs.getFloat( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_message.name() );
		this.beneficiary_qty_msisdn_with_amount_message = rs.getFloat( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_message.name() );
		this.not_targeted_qty_msisdn_with_amount_message = rs.getFloat( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_message.name() );
		this.control_amount_data = rs.getFloat( CampaignsWeeklySubsStatus.Fields.control_amount_data.name() );
		this.provisioned_amount_data = rs.getFloat( CampaignsWeeklySubsStatus.Fields.provisioned_amount_data.name() );
		this.notified_amount_data = rs.getFloat( CampaignsWeeklySubsStatus.Fields.notified_amount_data.name() );
		this.beneficiary_amount_data = rs.getFloat( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_data.name() );
		this.not_targeted_amount_data = rs.getFloat( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_data.name() );
		this.control_qty_msisdn_with_amount_data = rs.getFloat( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_data.name() );
		this.provisioned_qty_msisdn_with_amount_data = rs.getFloat( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_data.name() );
		this.notified_qty_msisdn_with_amount_data = rs.getFloat( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_data.name() );
		this.beneficiary_qty_msisdn_with_amount_data = rs.getFloat( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_data.name() );
		this.not_targeted_qty_msisdn_with_amount_data = rs.getFloat( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_data.name() );
		this.control_proba = rs.getFloat( CampaignsWeeklySubsStatus.Fields.control_proba.name() );
		this.provisioned_proba = rs.getFloat( CampaignsWeeklySubsStatus.Fields.provisioned_proba.name() );
		this.notified_proba = rs.getFloat( CampaignsWeeklySubsStatus.Fields.notified_proba.name() );
		this.beneficiary_proba = rs.getFloat( CampaignsWeeklySubsStatus.Fields.beneficiary_proba.name() );
		this.not_targeted_proba = rs.getFloat( CampaignsWeeklySubsStatus.Fields.not_targeted_proba.name() );

	}

	public CampaignsWeeklySubsStatus( JSONObject jo ) throws JSONException {

		this.campaign_id = (short)jo.getInt( CampaignsWeeklySubsStatus.Fields.campaign_id.name() );
		this.week_id = (byte)jo.getInt( CampaignsWeeklySubsStatus.Fields.week_id.name() );
		this.profile_id = (byte)jo.getInt( CampaignsWeeklySubsStatus.Fields.profile_id.name() );
		this.status_id = (byte)jo.getInt( CampaignsWeeklySubsStatus.Fields.status_id.name() );
		this.network_id = (byte)jo.getInt( CampaignsWeeklySubsStatus.Fields.network_id.name() );
		this.arpu_id = (byte)jo.getInt( CampaignsWeeklySubsStatus.Fields.arpu_id.name() );
		this.seniority_id = (byte)jo.getInt( CampaignsWeeklySubsStatus.Fields.seniority_id.name() );
		this.qty_control = (int)jo.getInt( CampaignsWeeklySubsStatus.Fields.qty_control.name() );
		this.qty_control_benef = (int)jo.getInt( CampaignsWeeklySubsStatus.Fields.qty_control_benef.name() );
		this.qty_provisioned = (int)jo.getInt( CampaignsWeeklySubsStatus.Fields.qty_provisioned.name() );
		this.qty_notified = (int)jo.getInt( CampaignsWeeklySubsStatus.Fields.qty_notified.name() );
		this.qty_beneficiary = (int)jo.getInt( CampaignsWeeklySubsStatus.Fields.qty_beneficiary.name() );
		this.qty_not_targeted = (int)jo.getInt( CampaignsWeeklySubsStatus.Fields.qty_not_targeted.name() );
		this.control_amount_usage = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_amount_usage.name() );
		this.provisioned_amount_usage = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_amount_usage.name() );
		this.notified_amount_usage = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_amount_usage.name() );
		this.beneficiary_amount_usage = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_usage.name() );
		this.not_targeted_amount_usage = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_usage.name() );
		this.control_qty_msisdn_with_amount_usage = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_usage.name() );
		this.provisioned_qty_msisdn_with_amount_usage = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_usage.name() );
		this.notified_qty_msisdn_with_amount_usage = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_usage.name() );
		this.beneficiary_qty_msisdn_with_amount_usage = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_usage.name() );
		this.not_targeted_qty_msisdn_with_amount_usage = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_usage.name() );
		this.control_amount_recharge = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_amount_recharge.name() );
		this.provisioned_amount_recharge = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_amount_recharge.name() );
		this.notified_amount_recharge = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_amount_recharge.name() );
		this.beneficiary_amount_recharge = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_recharge.name() );
		this.not_targeted_amount_recharge = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_recharge.name() );
		this.control_qty_msisdn_with_amount_recharge = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_recharge.name() );
		this.provisioned_qty_msisdn_with_amount_recharge = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_recharge.name() );
		this.notified_qty_msisdn_with_amount_recharge = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_recharge.name() );
		this.beneficiary_qty_msisdn_with_amount_recharge = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_recharge.name() );
		this.not_targeted_qty_msisdn_with_amount_recharge = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_recharge.name() );
		this.control_amount_invoice = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_amount_invoice.name() );
		this.provisioned_amount_invoice = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_amount_invoice.name() );
		this.notified_amount_invoice = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_amount_invoice.name() );
		this.beneficiary_amount_invoice = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_invoice.name() );
		this.not_targeted_amount_invoice = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_invoice.name() );
		this.control_qty_msisdn_with_amount_invoice = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_invoice.name() );
		this.provisioned_qty_msisdn_with_amount_invoice = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_invoice.name() );
		this.notified_qty_msisdn_with_amount_invoice = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_invoice.name() );
		this.beneficiary_qty_msisdn_with_amount_invoice = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_invoice.name() );
		this.not_targeted_qty_msisdn_with_amount_invoice = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_invoice.name() );
		this.control_amount_payment = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_amount_payment.name() );
		this.provisioned_amount_payment = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_amount_payment.name() );
		this.notified_amount_payment = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_amount_payment.name() );
		this.beneficiary_amount_payment = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_payment.name() );
		this.not_targeted_amount_payment = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_payment.name() );
		this.control_qty_msisdn_with_amount_payment = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_payment.name() );
		this.provisioned_qty_msisdn_with_amount_payment = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_payment.name() );
		this.notified_qty_msisdn_with_amount_payment = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_payment.name() );
		this.beneficiary_qty_msisdn_with_amount_payment = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_payment.name() );
		this.not_targeted_qty_msisdn_with_amount_payment = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_payment.name() );
		this.control_amount_call = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_amount_call.name() );
		this.provisioned_amount_call = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_amount_call.name() );
		this.notified_amount_call = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_amount_call.name() );
		this.beneficiary_amount_call = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_call.name() );
		this.not_targeted_amount_call = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_call.name() );
		this.control_qty_msisdn_with_amount_call = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_call.name() );
		this.provisioned_qty_msisdn_with_amount_call = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_call.name() );
		this.notified_qty_msisdn_with_amount_call = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_call.name() );
		this.beneficiary_qty_msisdn_with_amount_call = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_call.name() );
		this.not_targeted_qty_msisdn_with_amount_call = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_call.name() );
		this.control_amount_message = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_amount_message.name() );
		this.provisioned_amount_message = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_amount_message.name() );
		this.notified_amount_message = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_amount_message.name() );
		this.beneficiary_amount_message = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_message.name() );
		this.not_targeted_amount_message = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_message.name() );
		this.control_qty_msisdn_with_amount_message = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_message.name() );
		this.provisioned_qty_msisdn_with_amount_message = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_message.name() );
		this.notified_qty_msisdn_with_amount_message = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_message.name() );
		this.beneficiary_qty_msisdn_with_amount_message = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_message.name() );
		this.not_targeted_qty_msisdn_with_amount_message = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_message.name() );
		this.control_amount_data = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_amount_data.name() );
		this.provisioned_amount_data = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_amount_data.name() );
		this.notified_amount_data = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_amount_data.name() );
		this.beneficiary_amount_data = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_data.name() );
		this.not_targeted_amount_data = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_data.name() );
		this.control_qty_msisdn_with_amount_data = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_data.name() );
		this.provisioned_qty_msisdn_with_amount_data = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_data.name() );
		this.notified_qty_msisdn_with_amount_data = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_data.name() );
		this.beneficiary_qty_msisdn_with_amount_data = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_data.name() );
		this.not_targeted_qty_msisdn_with_amount_data = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_data.name() );
		this.control_proba = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_proba.name() );
		this.provisioned_proba = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_proba.name() );
		this.notified_proba = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_proba.name() );
		this.beneficiary_proba = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_proba.name() );
		this.not_targeted_proba = (float)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_proba.name() );

	}

	public Short getCampaignId() {

		return this.campaign_id;

	}

	public CampaignsWeeklySubsStatus setCampaignId( Short campaign_id ) {

		this.campaign_id = campaign_id;

		return this;

	}

	public Byte getWeekId() {

		return this.week_id;

	}

	public CampaignsWeeklySubsStatus setWeekId( Byte week_id ) {

		this.week_id = week_id;

		return this;

	}

	public Byte getProfileId() {

		return this.profile_id;

	}

	public CampaignsWeeklySubsStatus setProfileId( Byte profile_id ) {

		this.profile_id = profile_id;

		return this;

	}

	public Byte getStatusId() {

		return this.status_id;

	}

	public CampaignsWeeklySubsStatus setStatusId( Byte status_id ) {

		this.status_id = status_id;

		return this;

	}

	public Byte getNetworkId() {

		return this.network_id;

	}

	public CampaignsWeeklySubsStatus setNetworkId( Byte network_id ) {

		this.network_id = network_id;

		return this;

	}

	public Byte getArpuId() {

		return this.arpu_id;

	}

	public CampaignsWeeklySubsStatus setArpuId( Byte arpu_id ) {

		this.arpu_id = arpu_id;

		return this;

	}

	public Byte getSeniorityId() {

		return this.seniority_id;

	}

	public CampaignsWeeklySubsStatus setSeniorityId( Byte seniority_id ) {

		this.seniority_id = seniority_id;

		return this;

	}

	public Integer getQtyControl() {

		return this.qty_control;

	}

	public CampaignsWeeklySubsStatus setQtyControl( Integer qty_control ) {

		this.qty_control = qty_control;

		return this;

	}

	public Integer getQtyControlBenef() {

		return this.qty_control_benef;

	}

	public CampaignsWeeklySubsStatus setQtyControlBenef( Integer qty_control_benef ) {

		this.qty_control_benef = qty_control_benef;

		return this;

	}

	public Integer getQtyProvisioned() {

		return this.qty_provisioned;

	}

	public CampaignsWeeklySubsStatus setQtyProvisioned( Integer qty_provisioned ) {

		this.qty_provisioned = qty_provisioned;

		return this;

	}

	public Integer getQtyNotified() {

		return this.qty_notified;

	}

	public CampaignsWeeklySubsStatus setQtyNotified( Integer qty_notified ) {

		this.qty_notified = qty_notified;

		return this;

	}

	public Integer getQtyBeneficiary() {

		return this.qty_beneficiary;

	}

	public CampaignsWeeklySubsStatus setQtyBeneficiary( Integer qty_beneficiary ) {

		this.qty_beneficiary = qty_beneficiary;

		return this;

	}

	public Integer getQtyNotTargeted() {

		return this.qty_not_targeted;

	}

	public CampaignsWeeklySubsStatus setQtyNotTargeted( Integer qty_not_targeted ) {

		this.qty_not_targeted = qty_not_targeted;

		return this;

	}

	public Float getControlAmountUsage() {

		return this.control_amount_usage;

	}

	public CampaignsWeeklySubsStatus setControlAmountUsage( Float control_amount_usage ) {

		this.control_amount_usage = control_amount_usage;

		return this;

	}

	public Float getProvisionedAmountUsage() {

		return this.provisioned_amount_usage;

	}

	public CampaignsWeeklySubsStatus setProvisionedAmountUsage( Float provisioned_amount_usage ) {

		this.provisioned_amount_usage = provisioned_amount_usage;

		return this;

	}

	public Float getNotifiedAmountUsage() {

		return this.notified_amount_usage;

	}

	public CampaignsWeeklySubsStatus setNotifiedAmountUsage( Float notified_amount_usage ) {

		this.notified_amount_usage = notified_amount_usage;

		return this;

	}

	public Float getBeneficiaryAmountUsage() {

		return this.beneficiary_amount_usage;

	}

	public CampaignsWeeklySubsStatus setBeneficiaryAmountUsage( Float beneficiary_amount_usage ) {

		this.beneficiary_amount_usage = beneficiary_amount_usage;

		return this;

	}

	public Float getNotTargetedAmountUsage() {

		return this.not_targeted_amount_usage;

	}

	public CampaignsWeeklySubsStatus setNotTargetedAmountUsage( Float not_targeted_amount_usage ) {

		this.not_targeted_amount_usage = not_targeted_amount_usage;

		return this;

	}

	public Float getControlQtyMsisdnWithAmountUsage() {

		return this.control_qty_msisdn_with_amount_usage;

	}

	public CampaignsWeeklySubsStatus setControlQtyMsisdnWithAmountUsage( Float control_qty_msisdn_with_amount_usage ) {

		this.control_qty_msisdn_with_amount_usage = control_qty_msisdn_with_amount_usage;

		return this;

	}

	public Float getProvisionedQtyMsisdnWithAmountUsage() {

		return this.provisioned_qty_msisdn_with_amount_usage;

	}

	public CampaignsWeeklySubsStatus setProvisionedQtyMsisdnWithAmountUsage( Float provisioned_qty_msisdn_with_amount_usage ) {

		this.provisioned_qty_msisdn_with_amount_usage = provisioned_qty_msisdn_with_amount_usage;

		return this;

	}

	public Float getNotifiedQtyMsisdnWithAmountUsage() {

		return this.notified_qty_msisdn_with_amount_usage;

	}

	public CampaignsWeeklySubsStatus setNotifiedQtyMsisdnWithAmountUsage( Float notified_qty_msisdn_with_amount_usage ) {

		this.notified_qty_msisdn_with_amount_usage = notified_qty_msisdn_with_amount_usage;

		return this;

	}

	public Float getBeneficiaryQtyMsisdnWithAmountUsage() {

		return this.beneficiary_qty_msisdn_with_amount_usage;

	}

	public CampaignsWeeklySubsStatus setBeneficiaryQtyMsisdnWithAmountUsage( Float beneficiary_qty_msisdn_with_amount_usage ) {

		this.beneficiary_qty_msisdn_with_amount_usage = beneficiary_qty_msisdn_with_amount_usage;

		return this;

	}

	public Float getNotTargetedQtyMsisdnWithAmountUsage() {

		return this.not_targeted_qty_msisdn_with_amount_usage;

	}

	public CampaignsWeeklySubsStatus setNotTargetedQtyMsisdnWithAmountUsage( Float not_targeted_qty_msisdn_with_amount_usage ) {

		this.not_targeted_qty_msisdn_with_amount_usage = not_targeted_qty_msisdn_with_amount_usage;

		return this;

	}

	public Float getControlAmountRecharge() {

		return this.control_amount_recharge;

	}

	public CampaignsWeeklySubsStatus setControlAmountRecharge( Float control_amount_recharge ) {

		this.control_amount_recharge = control_amount_recharge;

		return this;

	}

	public Float getProvisionedAmountRecharge() {

		return this.provisioned_amount_recharge;

	}

	public CampaignsWeeklySubsStatus setProvisionedAmountRecharge( Float provisioned_amount_recharge ) {

		this.provisioned_amount_recharge = provisioned_amount_recharge;

		return this;

	}

	public Float getNotifiedAmountRecharge() {

		return this.notified_amount_recharge;

	}

	public CampaignsWeeklySubsStatus setNotifiedAmountRecharge( Float notified_amount_recharge ) {

		this.notified_amount_recharge = notified_amount_recharge;

		return this;

	}

	public Float getBeneficiaryAmountRecharge() {

		return this.beneficiary_amount_recharge;

	}

	public CampaignsWeeklySubsStatus setBeneficiaryAmountRecharge( Float beneficiary_amount_recharge ) {

		this.beneficiary_amount_recharge = beneficiary_amount_recharge;

		return this;

	}

	public Float getNotTargetedAmountRecharge() {

		return this.not_targeted_amount_recharge;

	}

	public CampaignsWeeklySubsStatus setNotTargetedAmountRecharge( Float not_targeted_amount_recharge ) {

		this.not_targeted_amount_recharge = not_targeted_amount_recharge;

		return this;

	}

	public Float getControlQtyMsisdnWithAmountRecharge() {

		return this.control_qty_msisdn_with_amount_recharge;

	}

	public CampaignsWeeklySubsStatus setControlQtyMsisdnWithAmountRecharge( Float control_qty_msisdn_with_amount_recharge ) {

		this.control_qty_msisdn_with_amount_recharge = control_qty_msisdn_with_amount_recharge;

		return this;

	}

	public Float getProvisionedQtyMsisdnWithAmountRecharge() {

		return this.provisioned_qty_msisdn_with_amount_recharge;

	}

	public CampaignsWeeklySubsStatus setProvisionedQtyMsisdnWithAmountRecharge( Float provisioned_qty_msisdn_with_amount_recharge ) {

		this.provisioned_qty_msisdn_with_amount_recharge = provisioned_qty_msisdn_with_amount_recharge;

		return this;

	}

	public Float getNotifiedQtyMsisdnWithAmountRecharge() {

		return this.notified_qty_msisdn_with_amount_recharge;

	}

	public CampaignsWeeklySubsStatus setNotifiedQtyMsisdnWithAmountRecharge( Float notified_qty_msisdn_with_amount_recharge ) {

		this.notified_qty_msisdn_with_amount_recharge = notified_qty_msisdn_with_amount_recharge;

		return this;

	}

	public Float getBeneficiaryQtyMsisdnWithAmountRecharge() {

		return this.beneficiary_qty_msisdn_with_amount_recharge;

	}

	public CampaignsWeeklySubsStatus setBeneficiaryQtyMsisdnWithAmountRecharge( Float beneficiary_qty_msisdn_with_amount_recharge ) {

		this.beneficiary_qty_msisdn_with_amount_recharge = beneficiary_qty_msisdn_with_amount_recharge;

		return this;

	}

	public Float getNotTargetedQtyMsisdnWithAmountRecharge() {

		return this.not_targeted_qty_msisdn_with_amount_recharge;

	}

	public CampaignsWeeklySubsStatus setNotTargetedQtyMsisdnWithAmountRecharge( Float not_targeted_qty_msisdn_with_amount_recharge ) {

		this.not_targeted_qty_msisdn_with_amount_recharge = not_targeted_qty_msisdn_with_amount_recharge;

		return this;

	}

	public Float getControlAmountInvoice() {

		return this.control_amount_invoice;

	}

	public CampaignsWeeklySubsStatus setControlAmountInvoice( Float control_amount_invoice ) {

		this.control_amount_invoice = control_amount_invoice;

		return this;

	}

	public Float getProvisionedAmountInvoice() {

		return this.provisioned_amount_invoice;

	}

	public CampaignsWeeklySubsStatus setProvisionedAmountInvoice( Float provisioned_amount_invoice ) {

		this.provisioned_amount_invoice = provisioned_amount_invoice;

		return this;

	}

	public Float getNotifiedAmountInvoice() {

		return this.notified_amount_invoice;

	}

	public CampaignsWeeklySubsStatus setNotifiedAmountInvoice( Float notified_amount_invoice ) {

		this.notified_amount_invoice = notified_amount_invoice;

		return this;

	}

	public Float getBeneficiaryAmountInvoice() {

		return this.beneficiary_amount_invoice;

	}

	public CampaignsWeeklySubsStatus setBeneficiaryAmountInvoice( Float beneficiary_amount_invoice ) {

		this.beneficiary_amount_invoice = beneficiary_amount_invoice;

		return this;

	}

	public Float getNotTargetedAmountInvoice() {

		return this.not_targeted_amount_invoice;

	}

	public CampaignsWeeklySubsStatus setNotTargetedAmountInvoice( Float not_targeted_amount_invoice ) {

		this.not_targeted_amount_invoice = not_targeted_amount_invoice;

		return this;

	}

	public Float getControlQtyMsisdnWithAmountInvoice() {

		return this.control_qty_msisdn_with_amount_invoice;

	}

	public CampaignsWeeklySubsStatus setControlQtyMsisdnWithAmountInvoice( Float control_qty_msisdn_with_amount_invoice ) {

		this.control_qty_msisdn_with_amount_invoice = control_qty_msisdn_with_amount_invoice;

		return this;

	}

	public Float getProvisionedQtyMsisdnWithAmountInvoice() {

		return this.provisioned_qty_msisdn_with_amount_invoice;

	}

	public CampaignsWeeklySubsStatus setProvisionedQtyMsisdnWithAmountInvoice( Float provisioned_qty_msisdn_with_amount_invoice ) {

		this.provisioned_qty_msisdn_with_amount_invoice = provisioned_qty_msisdn_with_amount_invoice;

		return this;

	}

	public Float getNotifiedQtyMsisdnWithAmountInvoice() {

		return this.notified_qty_msisdn_with_amount_invoice;

	}

	public CampaignsWeeklySubsStatus setNotifiedQtyMsisdnWithAmountInvoice( Float notified_qty_msisdn_with_amount_invoice ) {

		this.notified_qty_msisdn_with_amount_invoice = notified_qty_msisdn_with_amount_invoice;

		return this;

	}

	public Float getBeneficiaryQtyMsisdnWithAmountInvoice() {

		return this.beneficiary_qty_msisdn_with_amount_invoice;

	}

	public CampaignsWeeklySubsStatus setBeneficiaryQtyMsisdnWithAmountInvoice( Float beneficiary_qty_msisdn_with_amount_invoice ) {

		this.beneficiary_qty_msisdn_with_amount_invoice = beneficiary_qty_msisdn_with_amount_invoice;

		return this;

	}

	public Float getNotTargetedQtyMsisdnWithAmountInvoice() {

		return this.not_targeted_qty_msisdn_with_amount_invoice;

	}

	public CampaignsWeeklySubsStatus setNotTargetedQtyMsisdnWithAmountInvoice( Float not_targeted_qty_msisdn_with_amount_invoice ) {

		this.not_targeted_qty_msisdn_with_amount_invoice = not_targeted_qty_msisdn_with_amount_invoice;

		return this;

	}

	public Float getControlAmountPayment() {

		return this.control_amount_payment;

	}

	public CampaignsWeeklySubsStatus setControlAmountPayment( Float control_amount_payment ) {

		this.control_amount_payment = control_amount_payment;

		return this;

	}

	public Float getProvisionedAmountPayment() {

		return this.provisioned_amount_payment;

	}

	public CampaignsWeeklySubsStatus setProvisionedAmountPayment( Float provisioned_amount_payment ) {

		this.provisioned_amount_payment = provisioned_amount_payment;

		return this;

	}

	public Float getNotifiedAmountPayment() {

		return this.notified_amount_payment;

	}

	public CampaignsWeeklySubsStatus setNotifiedAmountPayment( Float notified_amount_payment ) {

		this.notified_amount_payment = notified_amount_payment;

		return this;

	}

	public Float getBeneficiaryAmountPayment() {

		return this.beneficiary_amount_payment;

	}

	public CampaignsWeeklySubsStatus setBeneficiaryAmountPayment( Float beneficiary_amount_payment ) {

		this.beneficiary_amount_payment = beneficiary_amount_payment;

		return this;

	}

	public Float getNotTargetedAmountPayment() {

		return this.not_targeted_amount_payment;

	}

	public CampaignsWeeklySubsStatus setNotTargetedAmountPayment( Float not_targeted_amount_payment ) {

		this.not_targeted_amount_payment = not_targeted_amount_payment;

		return this;

	}

	public Float getControlQtyMsisdnWithAmountPayment() {

		return this.control_qty_msisdn_with_amount_payment;

	}

	public CampaignsWeeklySubsStatus setControlQtyMsisdnWithAmountPayment( Float control_qty_msisdn_with_amount_payment ) {

		this.control_qty_msisdn_with_amount_payment = control_qty_msisdn_with_amount_payment;

		return this;

	}

	public Float getProvisionedQtyMsisdnWithAmountPayment() {

		return this.provisioned_qty_msisdn_with_amount_payment;

	}

	public CampaignsWeeklySubsStatus setProvisionedQtyMsisdnWithAmountPayment( Float provisioned_qty_msisdn_with_amount_payment ) {

		this.provisioned_qty_msisdn_with_amount_payment = provisioned_qty_msisdn_with_amount_payment;

		return this;

	}

	public Float getNotifiedQtyMsisdnWithAmountPayment() {

		return this.notified_qty_msisdn_with_amount_payment;

	}

	public CampaignsWeeklySubsStatus setNotifiedQtyMsisdnWithAmountPayment( Float notified_qty_msisdn_with_amount_payment ) {

		this.notified_qty_msisdn_with_amount_payment = notified_qty_msisdn_with_amount_payment;

		return this;

	}

	public Float getBeneficiaryQtyMsisdnWithAmountPayment() {

		return this.beneficiary_qty_msisdn_with_amount_payment;

	}

	public CampaignsWeeklySubsStatus setBeneficiaryQtyMsisdnWithAmountPayment( Float beneficiary_qty_msisdn_with_amount_payment ) {

		this.beneficiary_qty_msisdn_with_amount_payment = beneficiary_qty_msisdn_with_amount_payment;

		return this;

	}

	public Float getNotTargetedQtyMsisdnWithAmountPayment() {

		return this.not_targeted_qty_msisdn_with_amount_payment;

	}

	public CampaignsWeeklySubsStatus setNotTargetedQtyMsisdnWithAmountPayment( Float not_targeted_qty_msisdn_with_amount_payment ) {

		this.not_targeted_qty_msisdn_with_amount_payment = not_targeted_qty_msisdn_with_amount_payment;

		return this;

	}

	public Float getControlAmountCall() {

		return this.control_amount_call;

	}

	public CampaignsWeeklySubsStatus setControlAmountCall( Float control_amount_call ) {

		this.control_amount_call = control_amount_call;

		return this;

	}

	public Float getProvisionedAmountCall() {

		return this.provisioned_amount_call;

	}

	public CampaignsWeeklySubsStatus setProvisionedAmountCall( Float provisioned_amount_call ) {

		this.provisioned_amount_call = provisioned_amount_call;

		return this;

	}

	public Float getNotifiedAmountCall() {

		return this.notified_amount_call;

	}

	public CampaignsWeeklySubsStatus setNotifiedAmountCall( Float notified_amount_call ) {

		this.notified_amount_call = notified_amount_call;

		return this;

	}

	public Float getBeneficiaryAmountCall() {

		return this.beneficiary_amount_call;

	}

	public CampaignsWeeklySubsStatus setBeneficiaryAmountCall( Float beneficiary_amount_call ) {

		this.beneficiary_amount_call = beneficiary_amount_call;

		return this;

	}

	public Float getNotTargetedAmountCall() {

		return this.not_targeted_amount_call;

	}

	public CampaignsWeeklySubsStatus setNotTargetedAmountCall( Float not_targeted_amount_call ) {

		this.not_targeted_amount_call = not_targeted_amount_call;

		return this;

	}

	public Float getControlQtyMsisdnWithAmountCall() {

		return this.control_qty_msisdn_with_amount_call;

	}

	public CampaignsWeeklySubsStatus setControlQtyMsisdnWithAmountCall( Float control_qty_msisdn_with_amount_call ) {

		this.control_qty_msisdn_with_amount_call = control_qty_msisdn_with_amount_call;

		return this;

	}

	public Float getProvisionedQtyMsisdnWithAmountCall() {

		return this.provisioned_qty_msisdn_with_amount_call;

	}

	public CampaignsWeeklySubsStatus setProvisionedQtyMsisdnWithAmountCall( Float provisioned_qty_msisdn_with_amount_call ) {

		this.provisioned_qty_msisdn_with_amount_call = provisioned_qty_msisdn_with_amount_call;

		return this;

	}

	public Float getNotifiedQtyMsisdnWithAmountCall() {

		return this.notified_qty_msisdn_with_amount_call;

	}

	public CampaignsWeeklySubsStatus setNotifiedQtyMsisdnWithAmountCall( Float notified_qty_msisdn_with_amount_call ) {

		this.notified_qty_msisdn_with_amount_call = notified_qty_msisdn_with_amount_call;

		return this;

	}

	public Float getBeneficiaryQtyMsisdnWithAmountCall() {

		return this.beneficiary_qty_msisdn_with_amount_call;

	}

	public CampaignsWeeklySubsStatus setBeneficiaryQtyMsisdnWithAmountCall( Float beneficiary_qty_msisdn_with_amount_call ) {

		this.beneficiary_qty_msisdn_with_amount_call = beneficiary_qty_msisdn_with_amount_call;

		return this;

	}

	public Float getNotTargetedQtyMsisdnWithAmountCall() {

		return this.not_targeted_qty_msisdn_with_amount_call;

	}

	public CampaignsWeeklySubsStatus setNotTargetedQtyMsisdnWithAmountCall( Float not_targeted_qty_msisdn_with_amount_call ) {

		this.not_targeted_qty_msisdn_with_amount_call = not_targeted_qty_msisdn_with_amount_call;

		return this;

	}

	public Float getControlAmountMessage() {

		return this.control_amount_message;

	}

	public CampaignsWeeklySubsStatus setControlAmountMessage( Float control_amount_message ) {

		this.control_amount_message = control_amount_message;

		return this;

	}

	public Float getProvisionedAmountMessage() {

		return this.provisioned_amount_message;

	}

	public CampaignsWeeklySubsStatus setProvisionedAmountMessage( Float provisioned_amount_message ) {

		this.provisioned_amount_message = provisioned_amount_message;

		return this;

	}

	public Float getNotifiedAmountMessage() {

		return this.notified_amount_message;

	}

	public CampaignsWeeklySubsStatus setNotifiedAmountMessage( Float notified_amount_message ) {

		this.notified_amount_message = notified_amount_message;

		return this;

	}

	public Float getBeneficiaryAmountMessage() {

		return this.beneficiary_amount_message;

	}

	public CampaignsWeeklySubsStatus setBeneficiaryAmountMessage( Float beneficiary_amount_message ) {

		this.beneficiary_amount_message = beneficiary_amount_message;

		return this;

	}

	public Float getNotTargetedAmountMessage() {

		return this.not_targeted_amount_message;

	}

	public CampaignsWeeklySubsStatus setNotTargetedAmountMessage( Float not_targeted_amount_message ) {

		this.not_targeted_amount_message = not_targeted_amount_message;

		return this;

	}

	public Float getControlQtyMsisdnWithAmountMessage() {

		return this.control_qty_msisdn_with_amount_message;

	}

	public CampaignsWeeklySubsStatus setControlQtyMsisdnWithAmountMessage( Float control_qty_msisdn_with_amount_message ) {

		this.control_qty_msisdn_with_amount_message = control_qty_msisdn_with_amount_message;

		return this;

	}

	public Float getProvisionedQtyMsisdnWithAmountMessage() {

		return this.provisioned_qty_msisdn_with_amount_message;

	}

	public CampaignsWeeklySubsStatus setProvisionedQtyMsisdnWithAmountMessage( Float provisioned_qty_msisdn_with_amount_message ) {

		this.provisioned_qty_msisdn_with_amount_message = provisioned_qty_msisdn_with_amount_message;

		return this;

	}

	public Float getNotifiedQtyMsisdnWithAmountMessage() {

		return this.notified_qty_msisdn_with_amount_message;

	}

	public CampaignsWeeklySubsStatus setNotifiedQtyMsisdnWithAmountMessage( Float notified_qty_msisdn_with_amount_message ) {

		this.notified_qty_msisdn_with_amount_message = notified_qty_msisdn_with_amount_message;

		return this;

	}

	public Float getBeneficiaryQtyMsisdnWithAmountMessage() {

		return this.beneficiary_qty_msisdn_with_amount_message;

	}

	public CampaignsWeeklySubsStatus setBeneficiaryQtyMsisdnWithAmountMessage( Float beneficiary_qty_msisdn_with_amount_message ) {

		this.beneficiary_qty_msisdn_with_amount_message = beneficiary_qty_msisdn_with_amount_message;

		return this;

	}

	public Float getNotTargetedQtyMsisdnWithAmountMessage() {

		return this.not_targeted_qty_msisdn_with_amount_message;

	}

	public CampaignsWeeklySubsStatus setNotTargetedQtyMsisdnWithAmountMessage( Float not_targeted_qty_msisdn_with_amount_message ) {

		this.not_targeted_qty_msisdn_with_amount_message = not_targeted_qty_msisdn_with_amount_message;

		return this;

	}

	public Float getControlAmountData() {

		return this.control_amount_data;

	}

	public CampaignsWeeklySubsStatus setControlAmountData( Float control_amount_data ) {

		this.control_amount_data = control_amount_data;

		return this;

	}

	public Float getProvisionedAmountData() {

		return this.provisioned_amount_data;

	}

	public CampaignsWeeklySubsStatus setProvisionedAmountData( Float provisioned_amount_data ) {

		this.provisioned_amount_data = provisioned_amount_data;

		return this;

	}

	public Float getNotifiedAmountData() {

		return this.notified_amount_data;

	}

	public CampaignsWeeklySubsStatus setNotifiedAmountData( Float notified_amount_data ) {

		this.notified_amount_data = notified_amount_data;

		return this;

	}

	public Float getBeneficiaryAmountData() {

		return this.beneficiary_amount_data;

	}

	public CampaignsWeeklySubsStatus setBeneficiaryAmountData( Float beneficiary_amount_data ) {

		this.beneficiary_amount_data = beneficiary_amount_data;

		return this;

	}

	public Float getNotTargetedAmountData() {

		return this.not_targeted_amount_data;

	}

	public CampaignsWeeklySubsStatus setNotTargetedAmountData( Float not_targeted_amount_data ) {

		this.not_targeted_amount_data = not_targeted_amount_data;

		return this;

	}

	public Float getControlQtyMsisdnWithAmountData() {

		return this.control_qty_msisdn_with_amount_data;

	}

	public CampaignsWeeklySubsStatus setControlQtyMsisdnWithAmountData( Float control_qty_msisdn_with_amount_data ) {

		this.control_qty_msisdn_with_amount_data = control_qty_msisdn_with_amount_data;

		return this;

	}

	public Float getProvisionedQtyMsisdnWithAmountData() {

		return this.provisioned_qty_msisdn_with_amount_data;

	}

	public CampaignsWeeklySubsStatus setProvisionedQtyMsisdnWithAmountData( Float provisioned_qty_msisdn_with_amount_data ) {

		this.provisioned_qty_msisdn_with_amount_data = provisioned_qty_msisdn_with_amount_data;

		return this;

	}

	public Float getNotifiedQtyMsisdnWithAmountData() {

		return this.notified_qty_msisdn_with_amount_data;

	}

	public CampaignsWeeklySubsStatus setNotifiedQtyMsisdnWithAmountData( Float notified_qty_msisdn_with_amount_data ) {

		this.notified_qty_msisdn_with_amount_data = notified_qty_msisdn_with_amount_data;

		return this;

	}

	public Float getBeneficiaryQtyMsisdnWithAmountData() {

		return this.beneficiary_qty_msisdn_with_amount_data;

	}

	public CampaignsWeeklySubsStatus setBeneficiaryQtyMsisdnWithAmountData( Float beneficiary_qty_msisdn_with_amount_data ) {

		this.beneficiary_qty_msisdn_with_amount_data = beneficiary_qty_msisdn_with_amount_data;

		return this;

	}

	public Float getNotTargetedQtyMsisdnWithAmountData() {

		return this.not_targeted_qty_msisdn_with_amount_data;

	}

	public CampaignsWeeklySubsStatus setNotTargetedQtyMsisdnWithAmountData( Float not_targeted_qty_msisdn_with_amount_data ) {

		this.not_targeted_qty_msisdn_with_amount_data = not_targeted_qty_msisdn_with_amount_data;

		return this;

	}

	public Float getControlProba() {

		return this.control_proba;

	}

	public CampaignsWeeklySubsStatus setControlProba( Float control_proba ) {

		this.control_proba = control_proba;

		return this;

	}

	public Float getProvisionedProba() {

		return this.provisioned_proba;

	}

	public CampaignsWeeklySubsStatus setProvisionedProba( Float provisioned_proba ) {

		this.provisioned_proba = provisioned_proba;

		return this;

	}

	public Float getNotifiedProba() {

		return this.notified_proba;

	}

	public CampaignsWeeklySubsStatus setNotifiedProba( Float notified_proba ) {

		this.notified_proba = notified_proba;

		return this;

	}

	public Float getBeneficiaryProba() {

		return this.beneficiary_proba;

	}

	public CampaignsWeeklySubsStatus setBeneficiaryProba( Float beneficiary_proba ) {

		this.beneficiary_proba = beneficiary_proba;

		return this;

	}

	public Float getNotTargetedProba() {

		return this.not_targeted_proba;

	}

	public CampaignsWeeklySubsStatus setNotTargetedProba( Float not_targeted_proba ) {

		this.not_targeted_proba = not_targeted_proba;

		return this;

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
			.append( "\"seniority_id\": \"" ).append( this.getSeniorityId() ).append( "\", " )
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
			.append( "\"control_amount_invoice\": \"" ).append( this.getControlAmountInvoice() ).append( "\", " )
			.append( "\"provisioned_amount_invoice\": \"" ).append( this.getProvisionedAmountInvoice() ).append( "\", " )
			.append( "\"notified_amount_invoice\": \"" ).append( this.getNotifiedAmountInvoice() ).append( "\", " )
			.append( "\"beneficiary_amount_invoice\": \"" ).append( this.getBeneficiaryAmountInvoice() ).append( "\", " )
			.append( "\"not_targeted_amount_invoice\": \"" ).append( this.getNotTargetedAmountInvoice() ).append( "\", " )
			.append( "\"control_qty_msisdn_with_amount_invoice\": \"" ).append( this.getControlQtyMsisdnWithAmountInvoice() ).append( "\", " )
			.append( "\"provisioned_qty_msisdn_with_amount_invoice\": \"" ).append( this.getProvisionedQtyMsisdnWithAmountInvoice() ).append( "\", " )
			.append( "\"notified_qty_msisdn_with_amount_invoice\": \"" ).append( this.getNotifiedQtyMsisdnWithAmountInvoice() ).append( "\", " )
			.append( "\"beneficiary_qty_msisdn_with_amount_invoice\": \"" ).append( this.getBeneficiaryQtyMsisdnWithAmountInvoice() ).append( "\", " )
			.append( "\"not_targeted_qty_msisdn_with_amount_invoice\": \"" ).append( this.getNotTargetedQtyMsisdnWithAmountInvoice() ).append( "\", " )
			.append( "\"control_amount_payment\": \"" ).append( this.getControlAmountPayment() ).append( "\", " )
			.append( "\"provisioned_amount_payment\": \"" ).append( this.getProvisionedAmountPayment() ).append( "\", " )
			.append( "\"notified_amount_payment\": \"" ).append( this.getNotifiedAmountPayment() ).append( "\", " )
			.append( "\"beneficiary_amount_payment\": \"" ).append( this.getBeneficiaryAmountPayment() ).append( "\", " )
			.append( "\"not_targeted_amount_payment\": \"" ).append( this.getNotTargetedAmountPayment() ).append( "\", " )
			.append( "\"control_qty_msisdn_with_amount_payment\": \"" ).append( this.getControlQtyMsisdnWithAmountPayment() ).append( "\", " )
			.append( "\"provisioned_qty_msisdn_with_amount_payment\": \"" ).append( this.getProvisionedQtyMsisdnWithAmountPayment() ).append( "\", " )
			.append( "\"notified_qty_msisdn_with_amount_payment\": \"" ).append( this.getNotifiedQtyMsisdnWithAmountPayment() ).append( "\", " )
			.append( "\"beneficiary_qty_msisdn_with_amount_payment\": \"" ).append( this.getBeneficiaryQtyMsisdnWithAmountPayment() ).append( "\", " )
			.append( "\"not_targeted_qty_msisdn_with_amount_payment\": \"" ).append( this.getNotTargetedQtyMsisdnWithAmountPayment() ).append( "\", " )
			.append( "\"control_amount_call\": \"" ).append( this.getControlAmountCall() ).append( "\", " )
			.append( "\"provisioned_amount_call\": \"" ).append( this.getProvisionedAmountCall() ).append( "\", " )
			.append( "\"notified_amount_call\": \"" ).append( this.getNotifiedAmountCall() ).append( "\", " )
			.append( "\"beneficiary_amount_call\": \"" ).append( this.getBeneficiaryAmountCall() ).append( "\", " )
			.append( "\"not_targeted_amount_call\": \"" ).append( this.getNotTargetedAmountCall() ).append( "\", " )
			.append( "\"control_qty_msisdn_with_amount_call\": \"" ).append( this.getControlQtyMsisdnWithAmountCall() ).append( "\", " )
			.append( "\"provisioned_qty_msisdn_with_amount_call\": \"" ).append( this.getProvisionedQtyMsisdnWithAmountCall() ).append( "\", " )
			.append( "\"notified_qty_msisdn_with_amount_call\": \"" ).append( this.getNotifiedQtyMsisdnWithAmountCall() ).append( "\", " )
			.append( "\"beneficiary_qty_msisdn_with_amount_call\": \"" ).append( this.getBeneficiaryQtyMsisdnWithAmountCall() ).append( "\", " )
			.append( "\"not_targeted_qty_msisdn_with_amount_call\": \"" ).append( this.getNotTargetedQtyMsisdnWithAmountCall() ).append( "\", " )
			.append( "\"control_amount_message\": \"" ).append( this.getControlAmountMessage() ).append( "\", " )
			.append( "\"provisioned_amount_message\": \"" ).append( this.getProvisionedAmountMessage() ).append( "\", " )
			.append( "\"notified_amount_message\": \"" ).append( this.getNotifiedAmountMessage() ).append( "\", " )
			.append( "\"beneficiary_amount_message\": \"" ).append( this.getBeneficiaryAmountMessage() ).append( "\", " )
			.append( "\"not_targeted_amount_message\": \"" ).append( this.getNotTargetedAmountMessage() ).append( "\", " )
			.append( "\"control_qty_msisdn_with_amount_message\": \"" ).append( this.getControlQtyMsisdnWithAmountMessage() ).append( "\", " )
			.append( "\"provisioned_qty_msisdn_with_amount_message\": \"" ).append( this.getProvisionedQtyMsisdnWithAmountMessage() ).append( "\", " )
			.append( "\"notified_qty_msisdn_with_amount_message\": \"" ).append( this.getNotifiedQtyMsisdnWithAmountMessage() ).append( "\", " )
			.append( "\"beneficiary_qty_msisdn_with_amount_message\": \"" ).append( this.getBeneficiaryQtyMsisdnWithAmountMessage() ).append( "\", " )
			.append( "\"not_targeted_qty_msisdn_with_amount_message\": \"" ).append( this.getNotTargetedQtyMsisdnWithAmountMessage() ).append( "\", " )
			.append( "\"control_amount_data\": \"" ).append( this.getControlAmountData() ).append( "\", " )
			.append( "\"provisioned_amount_data\": \"" ).append( this.getProvisionedAmountData() ).append( "\", " )
			.append( "\"notified_amount_data\": \"" ).append( this.getNotifiedAmountData() ).append( "\", " )
			.append( "\"beneficiary_amount_data\": \"" ).append( this.getBeneficiaryAmountData() ).append( "\", " )
			.append( "\"not_targeted_amount_data\": \"" ).append( this.getNotTargetedAmountData() ).append( "\", " )
			.append( "\"control_qty_msisdn_with_amount_data\": \"" ).append( this.getControlQtyMsisdnWithAmountData() ).append( "\", " )
			.append( "\"provisioned_qty_msisdn_with_amount_data\": \"" ).append( this.getProvisionedQtyMsisdnWithAmountData() ).append( "\", " )
			.append( "\"notified_qty_msisdn_with_amount_data\": \"" ).append( this.getNotifiedQtyMsisdnWithAmountData() ).append( "\", " )
			.append( "\"beneficiary_qty_msisdn_with_amount_data\": \"" ).append( this.getBeneficiaryQtyMsisdnWithAmountData() ).append( "\", " )
			.append( "\"not_targeted_qty_msisdn_with_amount_data\": \"" ).append( this.getNotTargetedQtyMsisdnWithAmountData() ).append( "\", " )
			.append( "\"control_proba\": \"" ).append( this.getControlProba() ).append( "\", " )
			.append( "\"provisioned_proba\": \"" ).append( this.getProvisionedProba() ).append( "\", " )
			.append( "\"notified_proba\": \"" ).append( this.getNotifiedProba() ).append( "\", " )
			.append( "\"beneficiary_proba\": \"" ).append( this.getBeneficiaryProba() ).append( "\", " )
			.append( "\"not_targeted_proba\": \"" ).append( this.getNotTargetedProba() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }