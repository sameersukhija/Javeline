package com.lumata.expression.operators.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "campaigns_weekly_subs_status" )
public class CampaignsWeeklySubsStatus { 

	public enum Fields { campaign_id, week_id, profile_id, status_id, network_id, arpu_id, qty_control, qty_control_benef, qty_provisioned, qty_notified, qty_beneficiary, qty_not_targeted, control_amount_usage, provisioned_amount_usage, notified_amount_usage, beneficiary_amount_usage, not_targeted_amount_usage, control_qty_msisdn_with_amount_usage, provisioned_qty_msisdn_with_amount_usage, notified_qty_msisdn_with_amount_usage, beneficiary_qty_msisdn_with_amount_usage, not_targeted_qty_msisdn_with_amount_usage, control_amount_recharge, provisioned_amount_recharge, notified_amount_recharge, beneficiary_amount_recharge, not_targeted_amount_recharge, control_qty_msisdn_with_amount_recharge, provisioned_qty_msisdn_with_amount_recharge, notified_qty_msisdn_with_amount_recharge, beneficiary_qty_msisdn_with_amount_recharge, not_targeted_qty_msisdn_with_amount_recharge, control_amount_invoice, provisioned_amount_invoice, notified_amount_invoice, beneficiary_amount_invoice, not_targeted_amount_invoice, control_qty_msisdn_with_amount_invoice, provisioned_qty_msisdn_with_amount_invoice, notified_qty_msisdn_with_amount_invoice, beneficiary_qty_msisdn_with_amount_invoice, not_targeted_qty_msisdn_with_amount_invoice, control_amount_payment, provisioned_amount_payment, notified_amount_payment, beneficiary_amount_payment, not_targeted_amount_payment, control_qty_msisdn_with_amount_payment, provisioned_qty_msisdn_with_amount_payment, notified_qty_msisdn_with_amount_payment, beneficiary_qty_msisdn_with_amount_payment, not_targeted_qty_msisdn_with_amount_payment, control_amount_call, provisioned_amount_call, notified_amount_call, beneficiary_amount_call, not_targeted_amount_call, control_qty_msisdn_with_amount_call, provisioned_qty_msisdn_with_amount_call, notified_qty_msisdn_with_amount_call, beneficiary_qty_msisdn_with_amount_call, not_targeted_qty_msisdn_with_amount_call, control_amount_message, provisioned_amount_message, notified_amount_message, beneficiary_amount_message, not_targeted_amount_message, control_qty_msisdn_with_amount_message, provisioned_qty_msisdn_with_amount_message, notified_qty_msisdn_with_amount_message, beneficiary_qty_msisdn_with_amount_message, not_targeted_qty_msisdn_with_amount_message, control_amount_data, provisioned_amount_data, notified_amount_data, beneficiary_amount_data, not_targeted_amount_data, control_qty_msisdn_with_amount_data, provisioned_qty_msisdn_with_amount_data, notified_qty_msisdn_with_amount_data, beneficiary_qty_msisdn_with_amount_data, not_targeted_qty_msisdn_with_amount_data, control_proba, provisioned_proba, notified_proba, beneficiary_proba, not_targeted_proba }

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "campaign_id",
			type = "smallint(3) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
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
			field = "control_amount_invoice",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getControlAmountInvoice",
			setMethod = "setControlAmountInvoice"
	)
	private Double control_amount_invoice;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_amount_invoice",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getProvisionedAmountInvoice",
			setMethod = "setProvisionedAmountInvoice"
	)
	private Double provisioned_amount_invoice;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_amount_invoice",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotifiedAmountInvoice",
			setMethod = "setNotifiedAmountInvoice"
	)
	private Double notified_amount_invoice;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_amount_invoice",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getBeneficiaryAmountInvoice",
			setMethod = "setBeneficiaryAmountInvoice"
	)
	private Double beneficiary_amount_invoice;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_amount_invoice",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotTargetedAmountInvoice",
			setMethod = "setNotTargetedAmountInvoice"
	)
	private Double not_targeted_amount_invoice;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_qty_msisdn_with_amount_invoice",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getControlQtyMsisdnWithAmountInvoice",
			setMethod = "setControlQtyMsisdnWithAmountInvoice"
	)
	private Double control_qty_msisdn_with_amount_invoice;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_qty_msisdn_with_amount_invoice",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getProvisionedQtyMsisdnWithAmountInvoice",
			setMethod = "setProvisionedQtyMsisdnWithAmountInvoice"
	)
	private Double provisioned_qty_msisdn_with_amount_invoice;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_qty_msisdn_with_amount_invoice",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotifiedQtyMsisdnWithAmountInvoice",
			setMethod = "setNotifiedQtyMsisdnWithAmountInvoice"
	)
	private Double notified_qty_msisdn_with_amount_invoice;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_qty_msisdn_with_amount_invoice",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getBeneficiaryQtyMsisdnWithAmountInvoice",
			setMethod = "setBeneficiaryQtyMsisdnWithAmountInvoice"
	)
	private Double beneficiary_qty_msisdn_with_amount_invoice;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_qty_msisdn_with_amount_invoice",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotTargetedQtyMsisdnWithAmountInvoice",
			setMethod = "setNotTargetedQtyMsisdnWithAmountInvoice"
	)
	private Double not_targeted_qty_msisdn_with_amount_invoice;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_amount_payment",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getControlAmountPayment",
			setMethod = "setControlAmountPayment"
	)
	private Double control_amount_payment;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_amount_payment",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getProvisionedAmountPayment",
			setMethod = "setProvisionedAmountPayment"
	)
	private Double provisioned_amount_payment;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_amount_payment",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotifiedAmountPayment",
			setMethod = "setNotifiedAmountPayment"
	)
	private Double notified_amount_payment;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_amount_payment",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getBeneficiaryAmountPayment",
			setMethod = "setBeneficiaryAmountPayment"
	)
	private Double beneficiary_amount_payment;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_amount_payment",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotTargetedAmountPayment",
			setMethod = "setNotTargetedAmountPayment"
	)
	private Double not_targeted_amount_payment;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_qty_msisdn_with_amount_payment",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getControlQtyMsisdnWithAmountPayment",
			setMethod = "setControlQtyMsisdnWithAmountPayment"
	)
	private Double control_qty_msisdn_with_amount_payment;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_qty_msisdn_with_amount_payment",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getProvisionedQtyMsisdnWithAmountPayment",
			setMethod = "setProvisionedQtyMsisdnWithAmountPayment"
	)
	private Double provisioned_qty_msisdn_with_amount_payment;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_qty_msisdn_with_amount_payment",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotifiedQtyMsisdnWithAmountPayment",
			setMethod = "setNotifiedQtyMsisdnWithAmountPayment"
	)
	private Double notified_qty_msisdn_with_amount_payment;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_qty_msisdn_with_amount_payment",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getBeneficiaryQtyMsisdnWithAmountPayment",
			setMethod = "setBeneficiaryQtyMsisdnWithAmountPayment"
	)
	private Double beneficiary_qty_msisdn_with_amount_payment;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_qty_msisdn_with_amount_payment",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotTargetedQtyMsisdnWithAmountPayment",
			setMethod = "setNotTargetedQtyMsisdnWithAmountPayment"
	)
	private Double not_targeted_qty_msisdn_with_amount_payment;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_amount_call",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getControlAmountCall",
			setMethod = "setControlAmountCall"
	)
	private Double control_amount_call;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_amount_call",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getProvisionedAmountCall",
			setMethod = "setProvisionedAmountCall"
	)
	private Double provisioned_amount_call;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_amount_call",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotifiedAmountCall",
			setMethod = "setNotifiedAmountCall"
	)
	private Double notified_amount_call;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_amount_call",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getBeneficiaryAmountCall",
			setMethod = "setBeneficiaryAmountCall"
	)
	private Double beneficiary_amount_call;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_amount_call",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotTargetedAmountCall",
			setMethod = "setNotTargetedAmountCall"
	)
	private Double not_targeted_amount_call;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_qty_msisdn_with_amount_call",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getControlQtyMsisdnWithAmountCall",
			setMethod = "setControlQtyMsisdnWithAmountCall"
	)
	private Double control_qty_msisdn_with_amount_call;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_qty_msisdn_with_amount_call",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getProvisionedQtyMsisdnWithAmountCall",
			setMethod = "setProvisionedQtyMsisdnWithAmountCall"
	)
	private Double provisioned_qty_msisdn_with_amount_call;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_qty_msisdn_with_amount_call",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotifiedQtyMsisdnWithAmountCall",
			setMethod = "setNotifiedQtyMsisdnWithAmountCall"
	)
	private Double notified_qty_msisdn_with_amount_call;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_qty_msisdn_with_amount_call",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getBeneficiaryQtyMsisdnWithAmountCall",
			setMethod = "setBeneficiaryQtyMsisdnWithAmountCall"
	)
	private Double beneficiary_qty_msisdn_with_amount_call;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_qty_msisdn_with_amount_call",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotTargetedQtyMsisdnWithAmountCall",
			setMethod = "setNotTargetedQtyMsisdnWithAmountCall"
	)
	private Double not_targeted_qty_msisdn_with_amount_call;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_amount_message",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getControlAmountMessage",
			setMethod = "setControlAmountMessage"
	)
	private Double control_amount_message;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_amount_message",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getProvisionedAmountMessage",
			setMethod = "setProvisionedAmountMessage"
	)
	private Double provisioned_amount_message;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_amount_message",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotifiedAmountMessage",
			setMethod = "setNotifiedAmountMessage"
	)
	private Double notified_amount_message;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_amount_message",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getBeneficiaryAmountMessage",
			setMethod = "setBeneficiaryAmountMessage"
	)
	private Double beneficiary_amount_message;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_amount_message",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotTargetedAmountMessage",
			setMethod = "setNotTargetedAmountMessage"
	)
	private Double not_targeted_amount_message;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_qty_msisdn_with_amount_message",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getControlQtyMsisdnWithAmountMessage",
			setMethod = "setControlQtyMsisdnWithAmountMessage"
	)
	private Double control_qty_msisdn_with_amount_message;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_qty_msisdn_with_amount_message",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getProvisionedQtyMsisdnWithAmountMessage",
			setMethod = "setProvisionedQtyMsisdnWithAmountMessage"
	)
	private Double provisioned_qty_msisdn_with_amount_message;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_qty_msisdn_with_amount_message",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotifiedQtyMsisdnWithAmountMessage",
			setMethod = "setNotifiedQtyMsisdnWithAmountMessage"
	)
	private Double notified_qty_msisdn_with_amount_message;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_qty_msisdn_with_amount_message",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getBeneficiaryQtyMsisdnWithAmountMessage",
			setMethod = "setBeneficiaryQtyMsisdnWithAmountMessage"
	)
	private Double beneficiary_qty_msisdn_with_amount_message;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_qty_msisdn_with_amount_message",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotTargetedQtyMsisdnWithAmountMessage",
			setMethod = "setNotTargetedQtyMsisdnWithAmountMessage"
	)
	private Double not_targeted_qty_msisdn_with_amount_message;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_amount_data",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getControlAmountData",
			setMethod = "setControlAmountData"
	)
	private Double control_amount_data;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_amount_data",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getProvisionedAmountData",
			setMethod = "setProvisionedAmountData"
	)
	private Double provisioned_amount_data;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_amount_data",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotifiedAmountData",
			setMethod = "setNotifiedAmountData"
	)
	private Double notified_amount_data;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_amount_data",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getBeneficiaryAmountData",
			setMethod = "setBeneficiaryAmountData"
	)
	private Double beneficiary_amount_data;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_amount_data",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotTargetedAmountData",
			setMethod = "setNotTargetedAmountData"
	)
	private Double not_targeted_amount_data;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_qty_msisdn_with_amount_data",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getControlQtyMsisdnWithAmountData",
			setMethod = "setControlQtyMsisdnWithAmountData"
	)
	private Double control_qty_msisdn_with_amount_data;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "provisioned_qty_msisdn_with_amount_data",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getProvisionedQtyMsisdnWithAmountData",
			setMethod = "setProvisionedQtyMsisdnWithAmountData"
	)
	private Double provisioned_qty_msisdn_with_amount_data;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "notified_qty_msisdn_with_amount_data",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotifiedQtyMsisdnWithAmountData",
			setMethod = "setNotifiedQtyMsisdnWithAmountData"
	)
	private Double notified_qty_msisdn_with_amount_data;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "beneficiary_qty_msisdn_with_amount_data",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getBeneficiaryQtyMsisdnWithAmountData",
			setMethod = "setBeneficiaryQtyMsisdnWithAmountData"
	)
	private Double beneficiary_qty_msisdn_with_amount_data;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "not_targeted_qty_msisdn_with_amount_data",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getNotTargetedQtyMsisdnWithAmountData",
			setMethod = "setNotTargetedQtyMsisdnWithAmountData"
	)
	private Double not_targeted_qty_msisdn_with_amount_data;

	@Column(
			table = "campaigns_weekly_subs_status",
			field = "control_proba",
			type = "double",
			mysqlType = "double",
			javaType = "Double",
			categoryType = "Number",
			isNull = false,
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
		this.control_amount_invoice = rs.getDouble( CampaignsWeeklySubsStatus.Fields.control_amount_invoice.name() );
		this.provisioned_amount_invoice = rs.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_amount_invoice.name() );
		this.notified_amount_invoice = rs.getDouble( CampaignsWeeklySubsStatus.Fields.notified_amount_invoice.name() );
		this.beneficiary_amount_invoice = rs.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_invoice.name() );
		this.not_targeted_amount_invoice = rs.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_invoice.name() );
		this.control_qty_msisdn_with_amount_invoice = rs.getDouble( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_invoice.name() );
		this.provisioned_qty_msisdn_with_amount_invoice = rs.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_invoice.name() );
		this.notified_qty_msisdn_with_amount_invoice = rs.getDouble( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_invoice.name() );
		this.beneficiary_qty_msisdn_with_amount_invoice = rs.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_invoice.name() );
		this.not_targeted_qty_msisdn_with_amount_invoice = rs.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_invoice.name() );
		this.control_amount_payment = rs.getDouble( CampaignsWeeklySubsStatus.Fields.control_amount_payment.name() );
		this.provisioned_amount_payment = rs.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_amount_payment.name() );
		this.notified_amount_payment = rs.getDouble( CampaignsWeeklySubsStatus.Fields.notified_amount_payment.name() );
		this.beneficiary_amount_payment = rs.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_payment.name() );
		this.not_targeted_amount_payment = rs.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_payment.name() );
		this.control_qty_msisdn_with_amount_payment = rs.getDouble( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_payment.name() );
		this.provisioned_qty_msisdn_with_amount_payment = rs.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_payment.name() );
		this.notified_qty_msisdn_with_amount_payment = rs.getDouble( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_payment.name() );
		this.beneficiary_qty_msisdn_with_amount_payment = rs.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_payment.name() );
		this.not_targeted_qty_msisdn_with_amount_payment = rs.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_payment.name() );
		this.control_amount_call = rs.getDouble( CampaignsWeeklySubsStatus.Fields.control_amount_call.name() );
		this.provisioned_amount_call = rs.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_amount_call.name() );
		this.notified_amount_call = rs.getDouble( CampaignsWeeklySubsStatus.Fields.notified_amount_call.name() );
		this.beneficiary_amount_call = rs.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_call.name() );
		this.not_targeted_amount_call = rs.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_call.name() );
		this.control_qty_msisdn_with_amount_call = rs.getDouble( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_call.name() );
		this.provisioned_qty_msisdn_with_amount_call = rs.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_call.name() );
		this.notified_qty_msisdn_with_amount_call = rs.getDouble( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_call.name() );
		this.beneficiary_qty_msisdn_with_amount_call = rs.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_call.name() );
		this.not_targeted_qty_msisdn_with_amount_call = rs.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_call.name() );
		this.control_amount_message = rs.getDouble( CampaignsWeeklySubsStatus.Fields.control_amount_message.name() );
		this.provisioned_amount_message = rs.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_amount_message.name() );
		this.notified_amount_message = rs.getDouble( CampaignsWeeklySubsStatus.Fields.notified_amount_message.name() );
		this.beneficiary_amount_message = rs.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_message.name() );
		this.not_targeted_amount_message = rs.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_message.name() );
		this.control_qty_msisdn_with_amount_message = rs.getDouble( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_message.name() );
		this.provisioned_qty_msisdn_with_amount_message = rs.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_message.name() );
		this.notified_qty_msisdn_with_amount_message = rs.getDouble( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_message.name() );
		this.beneficiary_qty_msisdn_with_amount_message = rs.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_message.name() );
		this.not_targeted_qty_msisdn_with_amount_message = rs.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_message.name() );
		this.control_amount_data = rs.getDouble( CampaignsWeeklySubsStatus.Fields.control_amount_data.name() );
		this.provisioned_amount_data = rs.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_amount_data.name() );
		this.notified_amount_data = rs.getDouble( CampaignsWeeklySubsStatus.Fields.notified_amount_data.name() );
		this.beneficiary_amount_data = rs.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_data.name() );
		this.not_targeted_amount_data = rs.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_data.name() );
		this.control_qty_msisdn_with_amount_data = rs.getDouble( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_data.name() );
		this.provisioned_qty_msisdn_with_amount_data = rs.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_data.name() );
		this.notified_qty_msisdn_with_amount_data = rs.getDouble( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_data.name() );
		this.beneficiary_qty_msisdn_with_amount_data = rs.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_data.name() );
		this.not_targeted_qty_msisdn_with_amount_data = rs.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_data.name() );
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
		this.control_amount_invoice = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_amount_invoice.name() );
		this.provisioned_amount_invoice = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_amount_invoice.name() );
		this.notified_amount_invoice = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_amount_invoice.name() );
		this.beneficiary_amount_invoice = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_invoice.name() );
		this.not_targeted_amount_invoice = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_invoice.name() );
		this.control_qty_msisdn_with_amount_invoice = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_invoice.name() );
		this.provisioned_qty_msisdn_with_amount_invoice = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_invoice.name() );
		this.notified_qty_msisdn_with_amount_invoice = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_invoice.name() );
		this.beneficiary_qty_msisdn_with_amount_invoice = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_invoice.name() );
		this.not_targeted_qty_msisdn_with_amount_invoice = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_invoice.name() );
		this.control_amount_payment = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_amount_payment.name() );
		this.provisioned_amount_payment = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_amount_payment.name() );
		this.notified_amount_payment = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_amount_payment.name() );
		this.beneficiary_amount_payment = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_payment.name() );
		this.not_targeted_amount_payment = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_payment.name() );
		this.control_qty_msisdn_with_amount_payment = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_payment.name() );
		this.provisioned_qty_msisdn_with_amount_payment = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_payment.name() );
		this.notified_qty_msisdn_with_amount_payment = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_payment.name() );
		this.beneficiary_qty_msisdn_with_amount_payment = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_payment.name() );
		this.not_targeted_qty_msisdn_with_amount_payment = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_payment.name() );
		this.control_amount_call = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_amount_call.name() );
		this.provisioned_amount_call = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_amount_call.name() );
		this.notified_amount_call = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_amount_call.name() );
		this.beneficiary_amount_call = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_call.name() );
		this.not_targeted_amount_call = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_call.name() );
		this.control_qty_msisdn_with_amount_call = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_call.name() );
		this.provisioned_qty_msisdn_with_amount_call = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_call.name() );
		this.notified_qty_msisdn_with_amount_call = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_call.name() );
		this.beneficiary_qty_msisdn_with_amount_call = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_call.name() );
		this.not_targeted_qty_msisdn_with_amount_call = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_call.name() );
		this.control_amount_message = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_amount_message.name() );
		this.provisioned_amount_message = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_amount_message.name() );
		this.notified_amount_message = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_amount_message.name() );
		this.beneficiary_amount_message = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_message.name() );
		this.not_targeted_amount_message = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_message.name() );
		this.control_qty_msisdn_with_amount_message = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_message.name() );
		this.provisioned_qty_msisdn_with_amount_message = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_message.name() );
		this.notified_qty_msisdn_with_amount_message = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_message.name() );
		this.beneficiary_qty_msisdn_with_amount_message = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_message.name() );
		this.not_targeted_qty_msisdn_with_amount_message = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_message.name() );
		this.control_amount_data = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_amount_data.name() );
		this.provisioned_amount_data = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_amount_data.name() );
		this.notified_amount_data = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_amount_data.name() );
		this.beneficiary_amount_data = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_amount_data.name() );
		this.not_targeted_amount_data = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_amount_data.name() );
		this.control_qty_msisdn_with_amount_data = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.control_qty_msisdn_with_amount_data.name() );
		this.provisioned_qty_msisdn_with_amount_data = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.provisioned_qty_msisdn_with_amount_data.name() );
		this.notified_qty_msisdn_with_amount_data = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.notified_qty_msisdn_with_amount_data.name() );
		this.beneficiary_qty_msisdn_with_amount_data = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.beneficiary_qty_msisdn_with_amount_data.name() );
		this.not_targeted_qty_msisdn_with_amount_data = (double)jo.getDouble( CampaignsWeeklySubsStatus.Fields.not_targeted_qty_msisdn_with_amount_data.name() );
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

	public Double getControlAmountInvoice() {

		return this.control_amount_invoice;

	}

	public void setControlAmountInvoice( Double control_amount_invoice ) {

		this.control_amount_invoice = control_amount_invoice;

	}

	public Double getProvisionedAmountInvoice() {

		return this.provisioned_amount_invoice;

	}

	public void setProvisionedAmountInvoice( Double provisioned_amount_invoice ) {

		this.provisioned_amount_invoice = provisioned_amount_invoice;

	}

	public Double getNotifiedAmountInvoice() {

		return this.notified_amount_invoice;

	}

	public void setNotifiedAmountInvoice( Double notified_amount_invoice ) {

		this.notified_amount_invoice = notified_amount_invoice;

	}

	public Double getBeneficiaryAmountInvoice() {

		return this.beneficiary_amount_invoice;

	}

	public void setBeneficiaryAmountInvoice( Double beneficiary_amount_invoice ) {

		this.beneficiary_amount_invoice = beneficiary_amount_invoice;

	}

	public Double getNotTargetedAmountInvoice() {

		return this.not_targeted_amount_invoice;

	}

	public void setNotTargetedAmountInvoice( Double not_targeted_amount_invoice ) {

		this.not_targeted_amount_invoice = not_targeted_amount_invoice;

	}

	public Double getControlQtyMsisdnWithAmountInvoice() {

		return this.control_qty_msisdn_with_amount_invoice;

	}

	public void setControlQtyMsisdnWithAmountInvoice( Double control_qty_msisdn_with_amount_invoice ) {

		this.control_qty_msisdn_with_amount_invoice = control_qty_msisdn_with_amount_invoice;

	}

	public Double getProvisionedQtyMsisdnWithAmountInvoice() {

		return this.provisioned_qty_msisdn_with_amount_invoice;

	}

	public void setProvisionedQtyMsisdnWithAmountInvoice( Double provisioned_qty_msisdn_with_amount_invoice ) {

		this.provisioned_qty_msisdn_with_amount_invoice = provisioned_qty_msisdn_with_amount_invoice;

	}

	public Double getNotifiedQtyMsisdnWithAmountInvoice() {

		return this.notified_qty_msisdn_with_amount_invoice;

	}

	public void setNotifiedQtyMsisdnWithAmountInvoice( Double notified_qty_msisdn_with_amount_invoice ) {

		this.notified_qty_msisdn_with_amount_invoice = notified_qty_msisdn_with_amount_invoice;

	}

	public Double getBeneficiaryQtyMsisdnWithAmountInvoice() {

		return this.beneficiary_qty_msisdn_with_amount_invoice;

	}

	public void setBeneficiaryQtyMsisdnWithAmountInvoice( Double beneficiary_qty_msisdn_with_amount_invoice ) {

		this.beneficiary_qty_msisdn_with_amount_invoice = beneficiary_qty_msisdn_with_amount_invoice;

	}

	public Double getNotTargetedQtyMsisdnWithAmountInvoice() {

		return this.not_targeted_qty_msisdn_with_amount_invoice;

	}

	public void setNotTargetedQtyMsisdnWithAmountInvoice( Double not_targeted_qty_msisdn_with_amount_invoice ) {

		this.not_targeted_qty_msisdn_with_amount_invoice = not_targeted_qty_msisdn_with_amount_invoice;

	}

	public Double getControlAmountPayment() {

		return this.control_amount_payment;

	}

	public void setControlAmountPayment( Double control_amount_payment ) {

		this.control_amount_payment = control_amount_payment;

	}

	public Double getProvisionedAmountPayment() {

		return this.provisioned_amount_payment;

	}

	public void setProvisionedAmountPayment( Double provisioned_amount_payment ) {

		this.provisioned_amount_payment = provisioned_amount_payment;

	}

	public Double getNotifiedAmountPayment() {

		return this.notified_amount_payment;

	}

	public void setNotifiedAmountPayment( Double notified_amount_payment ) {

		this.notified_amount_payment = notified_amount_payment;

	}

	public Double getBeneficiaryAmountPayment() {

		return this.beneficiary_amount_payment;

	}

	public void setBeneficiaryAmountPayment( Double beneficiary_amount_payment ) {

		this.beneficiary_amount_payment = beneficiary_amount_payment;

	}

	public Double getNotTargetedAmountPayment() {

		return this.not_targeted_amount_payment;

	}

	public void setNotTargetedAmountPayment( Double not_targeted_amount_payment ) {

		this.not_targeted_amount_payment = not_targeted_amount_payment;

	}

	public Double getControlQtyMsisdnWithAmountPayment() {

		return this.control_qty_msisdn_with_amount_payment;

	}

	public void setControlQtyMsisdnWithAmountPayment( Double control_qty_msisdn_with_amount_payment ) {

		this.control_qty_msisdn_with_amount_payment = control_qty_msisdn_with_amount_payment;

	}

	public Double getProvisionedQtyMsisdnWithAmountPayment() {

		return this.provisioned_qty_msisdn_with_amount_payment;

	}

	public void setProvisionedQtyMsisdnWithAmountPayment( Double provisioned_qty_msisdn_with_amount_payment ) {

		this.provisioned_qty_msisdn_with_amount_payment = provisioned_qty_msisdn_with_amount_payment;

	}

	public Double getNotifiedQtyMsisdnWithAmountPayment() {

		return this.notified_qty_msisdn_with_amount_payment;

	}

	public void setNotifiedQtyMsisdnWithAmountPayment( Double notified_qty_msisdn_with_amount_payment ) {

		this.notified_qty_msisdn_with_amount_payment = notified_qty_msisdn_with_amount_payment;

	}

	public Double getBeneficiaryQtyMsisdnWithAmountPayment() {

		return this.beneficiary_qty_msisdn_with_amount_payment;

	}

	public void setBeneficiaryQtyMsisdnWithAmountPayment( Double beneficiary_qty_msisdn_with_amount_payment ) {

		this.beneficiary_qty_msisdn_with_amount_payment = beneficiary_qty_msisdn_with_amount_payment;

	}

	public Double getNotTargetedQtyMsisdnWithAmountPayment() {

		return this.not_targeted_qty_msisdn_with_amount_payment;

	}

	public void setNotTargetedQtyMsisdnWithAmountPayment( Double not_targeted_qty_msisdn_with_amount_payment ) {

		this.not_targeted_qty_msisdn_with_amount_payment = not_targeted_qty_msisdn_with_amount_payment;

	}

	public Double getControlAmountCall() {

		return this.control_amount_call;

	}

	public void setControlAmountCall( Double control_amount_call ) {

		this.control_amount_call = control_amount_call;

	}

	public Double getProvisionedAmountCall() {

		return this.provisioned_amount_call;

	}

	public void setProvisionedAmountCall( Double provisioned_amount_call ) {

		this.provisioned_amount_call = provisioned_amount_call;

	}

	public Double getNotifiedAmountCall() {

		return this.notified_amount_call;

	}

	public void setNotifiedAmountCall( Double notified_amount_call ) {

		this.notified_amount_call = notified_amount_call;

	}

	public Double getBeneficiaryAmountCall() {

		return this.beneficiary_amount_call;

	}

	public void setBeneficiaryAmountCall( Double beneficiary_amount_call ) {

		this.beneficiary_amount_call = beneficiary_amount_call;

	}

	public Double getNotTargetedAmountCall() {

		return this.not_targeted_amount_call;

	}

	public void setNotTargetedAmountCall( Double not_targeted_amount_call ) {

		this.not_targeted_amount_call = not_targeted_amount_call;

	}

	public Double getControlQtyMsisdnWithAmountCall() {

		return this.control_qty_msisdn_with_amount_call;

	}

	public void setControlQtyMsisdnWithAmountCall( Double control_qty_msisdn_with_amount_call ) {

		this.control_qty_msisdn_with_amount_call = control_qty_msisdn_with_amount_call;

	}

	public Double getProvisionedQtyMsisdnWithAmountCall() {

		return this.provisioned_qty_msisdn_with_amount_call;

	}

	public void setProvisionedQtyMsisdnWithAmountCall( Double provisioned_qty_msisdn_with_amount_call ) {

		this.provisioned_qty_msisdn_with_amount_call = provisioned_qty_msisdn_with_amount_call;

	}

	public Double getNotifiedQtyMsisdnWithAmountCall() {

		return this.notified_qty_msisdn_with_amount_call;

	}

	public void setNotifiedQtyMsisdnWithAmountCall( Double notified_qty_msisdn_with_amount_call ) {

		this.notified_qty_msisdn_with_amount_call = notified_qty_msisdn_with_amount_call;

	}

	public Double getBeneficiaryQtyMsisdnWithAmountCall() {

		return this.beneficiary_qty_msisdn_with_amount_call;

	}

	public void setBeneficiaryQtyMsisdnWithAmountCall( Double beneficiary_qty_msisdn_with_amount_call ) {

		this.beneficiary_qty_msisdn_with_amount_call = beneficiary_qty_msisdn_with_amount_call;

	}

	public Double getNotTargetedQtyMsisdnWithAmountCall() {

		return this.not_targeted_qty_msisdn_with_amount_call;

	}

	public void setNotTargetedQtyMsisdnWithAmountCall( Double not_targeted_qty_msisdn_with_amount_call ) {

		this.not_targeted_qty_msisdn_with_amount_call = not_targeted_qty_msisdn_with_amount_call;

	}

	public Double getControlAmountMessage() {

		return this.control_amount_message;

	}

	public void setControlAmountMessage( Double control_amount_message ) {

		this.control_amount_message = control_amount_message;

	}

	public Double getProvisionedAmountMessage() {

		return this.provisioned_amount_message;

	}

	public void setProvisionedAmountMessage( Double provisioned_amount_message ) {

		this.provisioned_amount_message = provisioned_amount_message;

	}

	public Double getNotifiedAmountMessage() {

		return this.notified_amount_message;

	}

	public void setNotifiedAmountMessage( Double notified_amount_message ) {

		this.notified_amount_message = notified_amount_message;

	}

	public Double getBeneficiaryAmountMessage() {

		return this.beneficiary_amount_message;

	}

	public void setBeneficiaryAmountMessage( Double beneficiary_amount_message ) {

		this.beneficiary_amount_message = beneficiary_amount_message;

	}

	public Double getNotTargetedAmountMessage() {

		return this.not_targeted_amount_message;

	}

	public void setNotTargetedAmountMessage( Double not_targeted_amount_message ) {

		this.not_targeted_amount_message = not_targeted_amount_message;

	}

	public Double getControlQtyMsisdnWithAmountMessage() {

		return this.control_qty_msisdn_with_amount_message;

	}

	public void setControlQtyMsisdnWithAmountMessage( Double control_qty_msisdn_with_amount_message ) {

		this.control_qty_msisdn_with_amount_message = control_qty_msisdn_with_amount_message;

	}

	public Double getProvisionedQtyMsisdnWithAmountMessage() {

		return this.provisioned_qty_msisdn_with_amount_message;

	}

	public void setProvisionedQtyMsisdnWithAmountMessage( Double provisioned_qty_msisdn_with_amount_message ) {

		this.provisioned_qty_msisdn_with_amount_message = provisioned_qty_msisdn_with_amount_message;

	}

	public Double getNotifiedQtyMsisdnWithAmountMessage() {

		return this.notified_qty_msisdn_with_amount_message;

	}

	public void setNotifiedQtyMsisdnWithAmountMessage( Double notified_qty_msisdn_with_amount_message ) {

		this.notified_qty_msisdn_with_amount_message = notified_qty_msisdn_with_amount_message;

	}

	public Double getBeneficiaryQtyMsisdnWithAmountMessage() {

		return this.beneficiary_qty_msisdn_with_amount_message;

	}

	public void setBeneficiaryQtyMsisdnWithAmountMessage( Double beneficiary_qty_msisdn_with_amount_message ) {

		this.beneficiary_qty_msisdn_with_amount_message = beneficiary_qty_msisdn_with_amount_message;

	}

	public Double getNotTargetedQtyMsisdnWithAmountMessage() {

		return this.not_targeted_qty_msisdn_with_amount_message;

	}

	public void setNotTargetedQtyMsisdnWithAmountMessage( Double not_targeted_qty_msisdn_with_amount_message ) {

		this.not_targeted_qty_msisdn_with_amount_message = not_targeted_qty_msisdn_with_amount_message;

	}

	public Double getControlAmountData() {

		return this.control_amount_data;

	}

	public void setControlAmountData( Double control_amount_data ) {

		this.control_amount_data = control_amount_data;

	}

	public Double getProvisionedAmountData() {

		return this.provisioned_amount_data;

	}

	public void setProvisionedAmountData( Double provisioned_amount_data ) {

		this.provisioned_amount_data = provisioned_amount_data;

	}

	public Double getNotifiedAmountData() {

		return this.notified_amount_data;

	}

	public void setNotifiedAmountData( Double notified_amount_data ) {

		this.notified_amount_data = notified_amount_data;

	}

	public Double getBeneficiaryAmountData() {

		return this.beneficiary_amount_data;

	}

	public void setBeneficiaryAmountData( Double beneficiary_amount_data ) {

		this.beneficiary_amount_data = beneficiary_amount_data;

	}

	public Double getNotTargetedAmountData() {

		return this.not_targeted_amount_data;

	}

	public void setNotTargetedAmountData( Double not_targeted_amount_data ) {

		this.not_targeted_amount_data = not_targeted_amount_data;

	}

	public Double getControlQtyMsisdnWithAmountData() {

		return this.control_qty_msisdn_with_amount_data;

	}

	public void setControlQtyMsisdnWithAmountData( Double control_qty_msisdn_with_amount_data ) {

		this.control_qty_msisdn_with_amount_data = control_qty_msisdn_with_amount_data;

	}

	public Double getProvisionedQtyMsisdnWithAmountData() {

		return this.provisioned_qty_msisdn_with_amount_data;

	}

	public void setProvisionedQtyMsisdnWithAmountData( Double provisioned_qty_msisdn_with_amount_data ) {

		this.provisioned_qty_msisdn_with_amount_data = provisioned_qty_msisdn_with_amount_data;

	}

	public Double getNotifiedQtyMsisdnWithAmountData() {

		return this.notified_qty_msisdn_with_amount_data;

	}

	public void setNotifiedQtyMsisdnWithAmountData( Double notified_qty_msisdn_with_amount_data ) {

		this.notified_qty_msisdn_with_amount_data = notified_qty_msisdn_with_amount_data;

	}

	public Double getBeneficiaryQtyMsisdnWithAmountData() {

		return this.beneficiary_qty_msisdn_with_amount_data;

	}

	public void setBeneficiaryQtyMsisdnWithAmountData( Double beneficiary_qty_msisdn_with_amount_data ) {

		this.beneficiary_qty_msisdn_with_amount_data = beneficiary_qty_msisdn_with_amount_data;

	}

	public Double getNotTargetedQtyMsisdnWithAmountData() {

		return this.not_targeted_qty_msisdn_with_amount_data;

	}

	public void setNotTargetedQtyMsisdnWithAmountData( Double not_targeted_qty_msisdn_with_amount_data ) {

		this.not_targeted_qty_msisdn_with_amount_data = not_targeted_qty_msisdn_with_amount_data;

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