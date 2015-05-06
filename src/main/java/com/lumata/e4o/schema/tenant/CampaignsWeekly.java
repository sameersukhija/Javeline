package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "campaigns_weekly" )
public class CampaignsWeekly { 

	public enum Fields { campaign_id, subs_status, week_id, profile_id, status_id, network_id, arpu_id, seniority_id, qty_msisdn, amount_usage, qty_msisdn_with_amount_usage, amount_recharge, qty_msisdn_with_amount_recharge, amount_invoice, qty_msisdn_with_amount_invoice, amount_payment, qty_msisdn_with_amount_payment, amount_call, qty_msisdn_with_amount_call, amount_message, qty_msisdn_with_amount_message, amount_data, qty_msisdn_with_amount_data, proba }

	@Column(
			table = "campaigns_weekly",
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
			table = "campaigns_weekly",
			field = "subs_status",
			type = "enum('CONTROL','CONTROL_BENEF','PROVISIONED','NOTIFIED','BENEFICIARY','NOTIFIED_BENEFICIARY','NOT_TARGETED_BENEFICIARY','NOT_TARGETED_PROV')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "PROVISIONED",
			extra = "",
			length = 8,
			comment = "",
			getMethod = "getSubsStatus",
			setMethod = "setSubsStatus"
	)
	private String subs_status;

	@Column(
			table = "campaigns_weekly",
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
			table = "campaigns_weekly",
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
			table = "campaigns_weekly",
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
			table = "campaigns_weekly",
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
			table = "campaigns_weekly",
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
			table = "campaigns_weekly",
			field = "seniority_id",
			type = "tinyint(4) unsigned",
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
			getMethod = "getSeniorityId",
			setMethod = "setSeniorityId"
	)
	private Byte seniority_id;

	@Column(
			table = "campaigns_weekly",
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
			table = "campaigns_weekly",
			field = "amount_usage",
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
			getMethod = "getAmountUsage",
			setMethod = "setAmountUsage"
	)
	private Float amount_usage;

	@Column(
			table = "campaigns_weekly",
			field = "qty_msisdn_with_amount_usage",
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
			getMethod = "getQtyMsisdnWithAmountUsage",
			setMethod = "setQtyMsisdnWithAmountUsage"
	)
	private Float qty_msisdn_with_amount_usage;

	@Column(
			table = "campaigns_weekly",
			field = "amount_recharge",
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
			getMethod = "getAmountRecharge",
			setMethod = "setAmountRecharge"
	)
	private Float amount_recharge;

	@Column(
			table = "campaigns_weekly",
			field = "qty_msisdn_with_amount_recharge",
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
			getMethod = "getQtyMsisdnWithAmountRecharge",
			setMethod = "setQtyMsisdnWithAmountRecharge"
	)
	private Float qty_msisdn_with_amount_recharge;

	@Column(
			table = "campaigns_weekly",
			field = "amount_invoice",
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
			getMethod = "getAmountInvoice",
			setMethod = "setAmountInvoice"
	)
	private Float amount_invoice;

	@Column(
			table = "campaigns_weekly",
			field = "qty_msisdn_with_amount_invoice",
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
			getMethod = "getQtyMsisdnWithAmountInvoice",
			setMethod = "setQtyMsisdnWithAmountInvoice"
	)
	private Float qty_msisdn_with_amount_invoice;

	@Column(
			table = "campaigns_weekly",
			field = "amount_payment",
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
			getMethod = "getAmountPayment",
			setMethod = "setAmountPayment"
	)
	private Float amount_payment;

	@Column(
			table = "campaigns_weekly",
			field = "qty_msisdn_with_amount_payment",
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
			getMethod = "getQtyMsisdnWithAmountPayment",
			setMethod = "setQtyMsisdnWithAmountPayment"
	)
	private Float qty_msisdn_with_amount_payment;

	@Column(
			table = "campaigns_weekly",
			field = "amount_call",
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
			getMethod = "getAmountCall",
			setMethod = "setAmountCall"
	)
	private Float amount_call;

	@Column(
			table = "campaigns_weekly",
			field = "qty_msisdn_with_amount_call",
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
			getMethod = "getQtyMsisdnWithAmountCall",
			setMethod = "setQtyMsisdnWithAmountCall"
	)
	private Float qty_msisdn_with_amount_call;

	@Column(
			table = "campaigns_weekly",
			field = "amount_message",
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
			getMethod = "getAmountMessage",
			setMethod = "setAmountMessage"
	)
	private Float amount_message;

	@Column(
			table = "campaigns_weekly",
			field = "qty_msisdn_with_amount_message",
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
			getMethod = "getQtyMsisdnWithAmountMessage",
			setMethod = "setQtyMsisdnWithAmountMessage"
	)
	private Float qty_msisdn_with_amount_message;

	@Column(
			table = "campaigns_weekly",
			field = "amount_data",
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
			getMethod = "getAmountData",
			setMethod = "setAmountData"
	)
	private Float amount_data;

	@Column(
			table = "campaigns_weekly",
			field = "qty_msisdn_with_amount_data",
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
			getMethod = "getQtyMsisdnWithAmountData",
			setMethod = "setQtyMsisdnWithAmountData"
	)
	private Float qty_msisdn_with_amount_data;

	@Column(
			table = "campaigns_weekly",
			field = "proba",
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
			getMethod = "getProba",
			setMethod = "setProba"
	)
	private Float proba;


	public CampaignsWeekly() {} 

	public CampaignsWeekly( ResultSet rs ) throws SQLException {

		this.campaign_id = rs.getShort( CampaignsWeekly.Fields.campaign_id.name() );
		this.subs_status = rs.getString( CampaignsWeekly.Fields.subs_status.name() );
		this.week_id = rs.getByte( CampaignsWeekly.Fields.week_id.name() );
		this.profile_id = rs.getByte( CampaignsWeekly.Fields.profile_id.name() );
		this.status_id = rs.getByte( CampaignsWeekly.Fields.status_id.name() );
		this.network_id = rs.getByte( CampaignsWeekly.Fields.network_id.name() );
		this.arpu_id = rs.getByte( CampaignsWeekly.Fields.arpu_id.name() );
		this.seniority_id = rs.getByte( CampaignsWeekly.Fields.seniority_id.name() );
		this.qty_msisdn = rs.getInt( CampaignsWeekly.Fields.qty_msisdn.name() );
		this.amount_usage = rs.getFloat( CampaignsWeekly.Fields.amount_usage.name() );
		this.qty_msisdn_with_amount_usage = rs.getFloat( CampaignsWeekly.Fields.qty_msisdn_with_amount_usage.name() );
		this.amount_recharge = rs.getFloat( CampaignsWeekly.Fields.amount_recharge.name() );
		this.qty_msisdn_with_amount_recharge = rs.getFloat( CampaignsWeekly.Fields.qty_msisdn_with_amount_recharge.name() );
		this.amount_invoice = rs.getFloat( CampaignsWeekly.Fields.amount_invoice.name() );
		this.qty_msisdn_with_amount_invoice = rs.getFloat( CampaignsWeekly.Fields.qty_msisdn_with_amount_invoice.name() );
		this.amount_payment = rs.getFloat( CampaignsWeekly.Fields.amount_payment.name() );
		this.qty_msisdn_with_amount_payment = rs.getFloat( CampaignsWeekly.Fields.qty_msisdn_with_amount_payment.name() );
		this.amount_call = rs.getFloat( CampaignsWeekly.Fields.amount_call.name() );
		this.qty_msisdn_with_amount_call = rs.getFloat( CampaignsWeekly.Fields.qty_msisdn_with_amount_call.name() );
		this.amount_message = rs.getFloat( CampaignsWeekly.Fields.amount_message.name() );
		this.qty_msisdn_with_amount_message = rs.getFloat( CampaignsWeekly.Fields.qty_msisdn_with_amount_message.name() );
		this.amount_data = rs.getFloat( CampaignsWeekly.Fields.amount_data.name() );
		this.qty_msisdn_with_amount_data = rs.getFloat( CampaignsWeekly.Fields.qty_msisdn_with_amount_data.name() );
		this.proba = rs.getFloat( CampaignsWeekly.Fields.proba.name() );

	}

	public CampaignsWeekly( JSONObject jo ) throws JSONException {

		this.campaign_id = (short)jo.getInt( CampaignsWeekly.Fields.campaign_id.name() );
		this.subs_status = jo.getString( CampaignsWeekly.Fields.subs_status.name() );
		this.week_id = (byte)jo.getInt( CampaignsWeekly.Fields.week_id.name() );
		this.profile_id = (byte)jo.getInt( CampaignsWeekly.Fields.profile_id.name() );
		this.status_id = (byte)jo.getInt( CampaignsWeekly.Fields.status_id.name() );
		this.network_id = (byte)jo.getInt( CampaignsWeekly.Fields.network_id.name() );
		this.arpu_id = (byte)jo.getInt( CampaignsWeekly.Fields.arpu_id.name() );
		this.seniority_id = (byte)jo.getInt( CampaignsWeekly.Fields.seniority_id.name() );
		this.qty_msisdn = (int)jo.getInt( CampaignsWeekly.Fields.qty_msisdn.name() );
		this.amount_usage = (float)jo.getDouble( CampaignsWeekly.Fields.amount_usage.name() );
		this.qty_msisdn_with_amount_usage = (float)jo.getDouble( CampaignsWeekly.Fields.qty_msisdn_with_amount_usage.name() );
		this.amount_recharge = (float)jo.getDouble( CampaignsWeekly.Fields.amount_recharge.name() );
		this.qty_msisdn_with_amount_recharge = (float)jo.getDouble( CampaignsWeekly.Fields.qty_msisdn_with_amount_recharge.name() );
		this.amount_invoice = (float)jo.getDouble( CampaignsWeekly.Fields.amount_invoice.name() );
		this.qty_msisdn_with_amount_invoice = (float)jo.getDouble( CampaignsWeekly.Fields.qty_msisdn_with_amount_invoice.name() );
		this.amount_payment = (float)jo.getDouble( CampaignsWeekly.Fields.amount_payment.name() );
		this.qty_msisdn_with_amount_payment = (float)jo.getDouble( CampaignsWeekly.Fields.qty_msisdn_with_amount_payment.name() );
		this.amount_call = (float)jo.getDouble( CampaignsWeekly.Fields.amount_call.name() );
		this.qty_msisdn_with_amount_call = (float)jo.getDouble( CampaignsWeekly.Fields.qty_msisdn_with_amount_call.name() );
		this.amount_message = (float)jo.getDouble( CampaignsWeekly.Fields.amount_message.name() );
		this.qty_msisdn_with_amount_message = (float)jo.getDouble( CampaignsWeekly.Fields.qty_msisdn_with_amount_message.name() );
		this.amount_data = (float)jo.getDouble( CampaignsWeekly.Fields.amount_data.name() );
		this.qty_msisdn_with_amount_data = (float)jo.getDouble( CampaignsWeekly.Fields.qty_msisdn_with_amount_data.name() );
		this.proba = (float)jo.getDouble( CampaignsWeekly.Fields.proba.name() );

	}

	public Short getCampaignId() {

		return this.campaign_id;

	}

	public CampaignsWeekly setCampaignId( Short campaign_id ) {

		this.campaign_id = campaign_id;

		return this;

	}

	public String getSubsStatus() {

		return this.subs_status;

	}

	public CampaignsWeekly setSubsStatus( String subs_status ) {

		this.subs_status = subs_status;

		return this;

	}

	public Byte getWeekId() {

		return this.week_id;

	}

	public CampaignsWeekly setWeekId( Byte week_id ) {

		this.week_id = week_id;

		return this;

	}

	public Byte getProfileId() {

		return this.profile_id;

	}

	public CampaignsWeekly setProfileId( Byte profile_id ) {

		this.profile_id = profile_id;

		return this;

	}

	public Byte getStatusId() {

		return this.status_id;

	}

	public CampaignsWeekly setStatusId( Byte status_id ) {

		this.status_id = status_id;

		return this;

	}

	public Byte getNetworkId() {

		return this.network_id;

	}

	public CampaignsWeekly setNetworkId( Byte network_id ) {

		this.network_id = network_id;

		return this;

	}

	public Byte getArpuId() {

		return this.arpu_id;

	}

	public CampaignsWeekly setArpuId( Byte arpu_id ) {

		this.arpu_id = arpu_id;

		return this;

	}

	public Byte getSeniorityId() {

		return this.seniority_id;

	}

	public CampaignsWeekly setSeniorityId( Byte seniority_id ) {

		this.seniority_id = seniority_id;

		return this;

	}

	public Integer getQtyMsisdn() {

		return this.qty_msisdn;

	}

	public CampaignsWeekly setQtyMsisdn( Integer qty_msisdn ) {

		this.qty_msisdn = qty_msisdn;

		return this;

	}

	public Float getAmountUsage() {

		return this.amount_usage;

	}

	public CampaignsWeekly setAmountUsage( Float amount_usage ) {

		this.amount_usage = amount_usage;

		return this;

	}

	public Float getQtyMsisdnWithAmountUsage() {

		return this.qty_msisdn_with_amount_usage;

	}

	public CampaignsWeekly setQtyMsisdnWithAmountUsage( Float qty_msisdn_with_amount_usage ) {

		this.qty_msisdn_with_amount_usage = qty_msisdn_with_amount_usage;

		return this;

	}

	public Float getAmountRecharge() {

		return this.amount_recharge;

	}

	public CampaignsWeekly setAmountRecharge( Float amount_recharge ) {

		this.amount_recharge = amount_recharge;

		return this;

	}

	public Float getQtyMsisdnWithAmountRecharge() {

		return this.qty_msisdn_with_amount_recharge;

	}

	public CampaignsWeekly setQtyMsisdnWithAmountRecharge( Float qty_msisdn_with_amount_recharge ) {

		this.qty_msisdn_with_amount_recharge = qty_msisdn_with_amount_recharge;

		return this;

	}

	public Float getAmountInvoice() {

		return this.amount_invoice;

	}

	public CampaignsWeekly setAmountInvoice( Float amount_invoice ) {

		this.amount_invoice = amount_invoice;

		return this;

	}

	public Float getQtyMsisdnWithAmountInvoice() {

		return this.qty_msisdn_with_amount_invoice;

	}

	public CampaignsWeekly setQtyMsisdnWithAmountInvoice( Float qty_msisdn_with_amount_invoice ) {

		this.qty_msisdn_with_amount_invoice = qty_msisdn_with_amount_invoice;

		return this;

	}

	public Float getAmountPayment() {

		return this.amount_payment;

	}

	public CampaignsWeekly setAmountPayment( Float amount_payment ) {

		this.amount_payment = amount_payment;

		return this;

	}

	public Float getQtyMsisdnWithAmountPayment() {

		return this.qty_msisdn_with_amount_payment;

	}

	public CampaignsWeekly setQtyMsisdnWithAmountPayment( Float qty_msisdn_with_amount_payment ) {

		this.qty_msisdn_with_amount_payment = qty_msisdn_with_amount_payment;

		return this;

	}

	public Float getAmountCall() {

		return this.amount_call;

	}

	public CampaignsWeekly setAmountCall( Float amount_call ) {

		this.amount_call = amount_call;

		return this;

	}

	public Float getQtyMsisdnWithAmountCall() {

		return this.qty_msisdn_with_amount_call;

	}

	public CampaignsWeekly setQtyMsisdnWithAmountCall( Float qty_msisdn_with_amount_call ) {

		this.qty_msisdn_with_amount_call = qty_msisdn_with_amount_call;

		return this;

	}

	public Float getAmountMessage() {

		return this.amount_message;

	}

	public CampaignsWeekly setAmountMessage( Float amount_message ) {

		this.amount_message = amount_message;

		return this;

	}

	public Float getQtyMsisdnWithAmountMessage() {

		return this.qty_msisdn_with_amount_message;

	}

	public CampaignsWeekly setQtyMsisdnWithAmountMessage( Float qty_msisdn_with_amount_message ) {

		this.qty_msisdn_with_amount_message = qty_msisdn_with_amount_message;

		return this;

	}

	public Float getAmountData() {

		return this.amount_data;

	}

	public CampaignsWeekly setAmountData( Float amount_data ) {

		this.amount_data = amount_data;

		return this;

	}

	public Float getQtyMsisdnWithAmountData() {

		return this.qty_msisdn_with_amount_data;

	}

	public CampaignsWeekly setQtyMsisdnWithAmountData( Float qty_msisdn_with_amount_data ) {

		this.qty_msisdn_with_amount_data = qty_msisdn_with_amount_data;

		return this;

	}

	public Float getProba() {

		return this.proba;

	}

	public CampaignsWeekly setProba( Float proba ) {

		this.proba = proba;

		return this;

	}

	public Fields[] getEntityFields() {

		return CampaignsWeekly.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"campaign_id\": \"" ).append( this.getCampaignId() ).append( "\", " )
			.append( "\"subs_status\": \"" ).append( this.getSubsStatus() ).append( "\", " )
			.append( "\"week_id\": \"" ).append( this.getWeekId() ).append( "\", " )
			.append( "\"profile_id\": \"" ).append( this.getProfileId() ).append( "\", " )
			.append( "\"status_id\": \"" ).append( this.getStatusId() ).append( "\", " )
			.append( "\"network_id\": \"" ).append( this.getNetworkId() ).append( "\", " )
			.append( "\"arpu_id\": \"" ).append( this.getArpuId() ).append( "\", " )
			.append( "\"seniority_id\": \"" ).append( this.getSeniorityId() ).append( "\", " )
			.append( "\"qty_msisdn\": \"" ).append( this.getQtyMsisdn() ).append( "\", " )
			.append( "\"amount_usage\": \"" ).append( this.getAmountUsage() ).append( "\", " )
			.append( "\"qty_msisdn_with_amount_usage\": \"" ).append( this.getQtyMsisdnWithAmountUsage() ).append( "\", " )
			.append( "\"amount_recharge\": \"" ).append( this.getAmountRecharge() ).append( "\", " )
			.append( "\"qty_msisdn_with_amount_recharge\": \"" ).append( this.getQtyMsisdnWithAmountRecharge() ).append( "\", " )
			.append( "\"amount_invoice\": \"" ).append( this.getAmountInvoice() ).append( "\", " )
			.append( "\"qty_msisdn_with_amount_invoice\": \"" ).append( this.getQtyMsisdnWithAmountInvoice() ).append( "\", " )
			.append( "\"amount_payment\": \"" ).append( this.getAmountPayment() ).append( "\", " )
			.append( "\"qty_msisdn_with_amount_payment\": \"" ).append( this.getQtyMsisdnWithAmountPayment() ).append( "\", " )
			.append( "\"amount_call\": \"" ).append( this.getAmountCall() ).append( "\", " )
			.append( "\"qty_msisdn_with_amount_call\": \"" ).append( this.getQtyMsisdnWithAmountCall() ).append( "\", " )
			.append( "\"amount_message\": \"" ).append( this.getAmountMessage() ).append( "\", " )
			.append( "\"qty_msisdn_with_amount_message\": \"" ).append( this.getQtyMsisdnWithAmountMessage() ).append( "\", " )
			.append( "\"amount_data\": \"" ).append( this.getAmountData() ).append( "\", " )
			.append( "\"qty_msisdn_with_amount_data\": \"" ).append( this.getQtyMsisdnWithAmountData() ).append( "\", " )
			.append( "\"proba\": \"" ).append( this.getProba() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }