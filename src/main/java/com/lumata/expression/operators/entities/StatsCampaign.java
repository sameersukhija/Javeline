package com.lumata.expression.operators.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.util.Date;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;
import java.sql.Timestamp;


@Table( "stats_campaign" )
public class StatsCampaign { 

	public enum Fields { msisdn, campaign_id, start_date, end_date, subs_status, substate_id, cs_done, amount_usage, amount_recharge, amount_invoice, amount_payment, amount_call, amount_message, amount_data, params, update_time }

	@Column(
			table = "stats_campaign",
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
			table = "stats_campaign",
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
			table = "stats_campaign",
			field = "start_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getStartDate",
			setMethod = "setStartDate"
	)
	private Date start_date;

	@Column(
			table = "stats_campaign",
			field = "end_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getEndDate",
			setMethod = "setEndDate"
	)
	private Date end_date;

	@Column(
			table = "stats_campaign",
			field = "subs_status",
			type = "enum('CONTROL','CONTROL_BENEF','PROVISIONED','NOTIFIED','BENEFICIARY','NOTIFIED_BENEFICIARY','NOT_TARGETED_BENEFICIARY','NOT_TARGETED_PROV')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			key = "",
			defaultValue = "PROVISIONED",
			extra = "",
			length = 8,
			getMethod = "getSubsStatus",
			setMethod = "setSubsStatus"
	)
	private String subs_status;

	@Column(
			table = "stats_campaign",
			field = "substate_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 3,
			getMethod = "getSubstateId",
			setMethod = "setSubstateId"
	)
	private Byte substate_id;

	@Column(
			table = "stats_campaign",
			field = "cs_done",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getCsDone",
			setMethod = "setCsDone"
	)
	private Byte cs_done;

	@Column(
			table = "stats_campaign",
			field = "amount_usage",
			type = "float",
			mysqlType = "float",
			javaType = "Float",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getAmountUsage",
			setMethod = "setAmountUsage"
	)
	private Float amount_usage;

	@Column(
			table = "stats_campaign",
			field = "amount_recharge",
			type = "float",
			mysqlType = "float",
			javaType = "Float",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getAmountRecharge",
			setMethod = "setAmountRecharge"
	)
	private Float amount_recharge;

	@Column(
			table = "stats_campaign",
			field = "amount_invoice",
			type = "float",
			mysqlType = "float",
			javaType = "Float",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getAmountInvoice",
			setMethod = "setAmountInvoice"
	)
	private Float amount_invoice;

	@Column(
			table = "stats_campaign",
			field = "amount_payment",
			type = "float",
			mysqlType = "float",
			javaType = "Float",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getAmountPayment",
			setMethod = "setAmountPayment"
	)
	private Float amount_payment;

	@Column(
			table = "stats_campaign",
			field = "amount_call",
			type = "float",
			mysqlType = "float",
			javaType = "Float",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getAmountCall",
			setMethod = "setAmountCall"
	)
	private Float amount_call;

	@Column(
			table = "stats_campaign",
			field = "amount_message",
			type = "float",
			mysqlType = "float",
			javaType = "Float",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getAmountMessage",
			setMethod = "setAmountMessage"
	)
	private Float amount_message;

	@Column(
			table = "stats_campaign",
			field = "amount_data",
			type = "float",
			mysqlType = "float",
			javaType = "Float",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getAmountData",
			setMethod = "setAmountData"
	)
	private Float amount_data;

	@Column(
			table = "stats_campaign",
			field = "params",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "",
			extra = "",
			length = 255,
			getMethod = "getParams",
			setMethod = "setParams"
	)
	private String params;

	@Column(
			table = "stats_campaign",
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


	public StatsCampaign() {} 

	public StatsCampaign( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( StatsCampaign.Fields.msisdn.name() );
		this.campaign_id = rs.getShort( StatsCampaign.Fields.campaign_id.name() );
		this.start_date = rs.getDate( StatsCampaign.Fields.start_date.name() );
		this.end_date = rs.getDate( StatsCampaign.Fields.end_date.name() );
		this.subs_status = rs.getString( StatsCampaign.Fields.subs_status.name() );
		this.substate_id = rs.getByte( StatsCampaign.Fields.substate_id.name() );
		this.cs_done = rs.getByte( StatsCampaign.Fields.cs_done.name() );
		this.amount_usage = rs.getFloat( StatsCampaign.Fields.amount_usage.name() );
		this.amount_recharge = rs.getFloat( StatsCampaign.Fields.amount_recharge.name() );
		this.amount_invoice = rs.getFloat( StatsCampaign.Fields.amount_invoice.name() );
		this.amount_payment = rs.getFloat( StatsCampaign.Fields.amount_payment.name() );
		this.amount_call = rs.getFloat( StatsCampaign.Fields.amount_call.name() );
		this.amount_message = rs.getFloat( StatsCampaign.Fields.amount_message.name() );
		this.amount_data = rs.getFloat( StatsCampaign.Fields.amount_data.name() );
		this.params = rs.getString( StatsCampaign.Fields.params.name() );
		this.update_time = rs.getTimestamp( StatsCampaign.Fields.update_time.name() );

	}

	public StatsCampaign( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( StatsCampaign.Fields.msisdn.name() );
		this.campaign_id = (short)jo.getInt( StatsCampaign.Fields.campaign_id.name() );
		this.start_date = Format.getMysqlDateTime( jo.getString( StatsCampaign.Fields.start_date.name() ) );
		this.end_date = Format.getMysqlDateTime( jo.getString( StatsCampaign.Fields.end_date.name() ) );
		this.subs_status = jo.getString( StatsCampaign.Fields.subs_status.name() );
		this.substate_id = (byte)jo.getInt( StatsCampaign.Fields.substate_id.name() );
		this.cs_done = (byte)jo.getInt( StatsCampaign.Fields.cs_done.name() );
		this.amount_usage = (float)jo.getDouble( StatsCampaign.Fields.amount_usage.name() );
		this.amount_recharge = (float)jo.getDouble( StatsCampaign.Fields.amount_recharge.name() );
		this.amount_invoice = (float)jo.getDouble( StatsCampaign.Fields.amount_invoice.name() );
		this.amount_payment = (float)jo.getDouble( StatsCampaign.Fields.amount_payment.name() );
		this.amount_call = (float)jo.getDouble( StatsCampaign.Fields.amount_call.name() );
		this.amount_message = (float)jo.getDouble( StatsCampaign.Fields.amount_message.name() );
		this.amount_data = (float)jo.getDouble( StatsCampaign.Fields.amount_data.name() );
		this.params = jo.getString( StatsCampaign.Fields.params.name() );
		this.update_time = new Timestamp( Format.getMysqlDateTime( jo.getString( StatsCampaign.Fields.update_time.name() ) ).getTime() );

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

	public Date getStartDate() {

		return this.start_date;

	}

	public void setStartDate( Date start_date ) {

		this.start_date = start_date;

	}

	public Date getEndDate() {

		return this.end_date;

	}

	public void setEndDate( Date end_date ) {

		this.end_date = end_date;

	}

	public String getSubsStatus() {

		return this.subs_status;

	}

	public void setSubsStatus( String subs_status ) {

		this.subs_status = subs_status;

	}

	public Byte getSubstateId() {

		return this.substate_id;

	}

	public void setSubstateId( Byte substate_id ) {

		this.substate_id = substate_id;

	}

	public Byte getCsDone() {

		return this.cs_done;

	}

	public void setCsDone( Byte cs_done ) {

		this.cs_done = cs_done;

	}

	public Float getAmountUsage() {

		return this.amount_usage;

	}

	public void setAmountUsage( Float amount_usage ) {

		this.amount_usage = amount_usage;

	}

	public Float getAmountRecharge() {

		return this.amount_recharge;

	}

	public void setAmountRecharge( Float amount_recharge ) {

		this.amount_recharge = amount_recharge;

	}

	public Float getAmountInvoice() {

		return this.amount_invoice;

	}

	public void setAmountInvoice( Float amount_invoice ) {

		this.amount_invoice = amount_invoice;

	}

	public Float getAmountPayment() {

		return this.amount_payment;

	}

	public void setAmountPayment( Float amount_payment ) {

		this.amount_payment = amount_payment;

	}

	public Float getAmountCall() {

		return this.amount_call;

	}

	public void setAmountCall( Float amount_call ) {

		this.amount_call = amount_call;

	}

	public Float getAmountMessage() {

		return this.amount_message;

	}

	public void setAmountMessage( Float amount_message ) {

		this.amount_message = amount_message;

	}

	public Float getAmountData() {

		return this.amount_data;

	}

	public void setAmountData( Float amount_data ) {

		this.amount_data = amount_data;

	}

	public String getParams() {

		return this.params;

	}

	public void setParams( String params ) {

		this.params = params;

	}

	public Timestamp getUpdateTime() {

		return this.update_time;

	}

	public void setUpdateTime( Timestamp update_time ) {

		this.update_time = update_time;

	}

	public Fields[] getEntityFields() {

		return StatsCampaign.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"campaign_id\": \"" ).append( this.getCampaignId() ).append( "\", " )
			.append( "\"start_date\": \"" ).append( this.getStartDate() ).append( "\", " )
			.append( "\"end_date\": \"" ).append( this.getEndDate() ).append( "\", " )
			.append( "\"subs_status\": \"" ).append( this.getSubsStatus() ).append( "\", " )
			.append( "\"substate_id\": \"" ).append( this.getSubstateId() ).append( "\", " )
			.append( "\"cs_done\": \"" ).append( this.getCsDone() ).append( "\", " )
			.append( "\"amount_usage\": \"" ).append( this.getAmountUsage() ).append( "\", " )
			.append( "\"amount_recharge\": \"" ).append( this.getAmountRecharge() ).append( "\", " )
			.append( "\"amount_invoice\": \"" ).append( this.getAmountInvoice() ).append( "\", " )
			.append( "\"amount_payment\": \"" ).append( this.getAmountPayment() ).append( "\", " )
			.append( "\"amount_call\": \"" ).append( this.getAmountCall() ).append( "\", " )
			.append( "\"amount_message\": \"" ).append( this.getAmountMessage() ).append( "\", " )
			.append( "\"amount_data\": \"" ).append( this.getAmountData() ).append( "\", " )
			.append( "\"params\": \"" ).append( this.getParams() ).append( "\", " )
			.append( "\"update_time\": \"" ).append( this.getUpdateTime() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }