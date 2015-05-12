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


@Table( "stats_campaign_archive" )
public class StatsCampaignArchive { 

	public enum Fields { msisdn, campaign_id, start_date, end_date, subs_status, arpu_id, seniority_id, profile_id, status_id, network_id, amount_usage, amount_recharge, amount_invoice, amount_payment, amount_call, amount_message, amount_data }

	@Column(
			table = "stats_campaign_archive",
			field = "msisdn",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 20,
			comment = "",
			getMethod = "getMsisdn",
			setMethod = "setMsisdn"
	)
	private Long msisdn;

	@Column(
			table = "stats_campaign_archive",
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
			table = "stats_campaign_archive",
			field = "start_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "",
			getMethod = "getStartDate",
			setMethod = "setStartDate"
	)
	private Date start_date;

	@Column(
			table = "stats_campaign_archive",
			field = "end_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "0000-00-00",
			extra = "",
			length = 0,
			comment = "",
			getMethod = "getEndDate",
			setMethod = "setEndDate"
	)
	private Date end_date;

	@Column(
			table = "stats_campaign_archive",
			field = "subs_status",
			type = "enum('CONTROL','CONTROL_BENEF','PROVISIONED','NOTIFIED','BENEFICIARY','NOTIFIED_BENEFICIARY','NOT_TARGETED_BENEFICIARY','NOT_TARGETED_PROV')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "PROVISIONED",
			extra = "",
			length = 8,
			comment = "",
			getMethod = "getSubsStatus",
			setMethod = "setSubsStatus"
	)
	private String subs_status;

	@Column(
			table = "stats_campaign_archive",
			field = "arpu_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			comment = "",
			getMethod = "getArpuId",
			setMethod = "setArpuId"
	)
	private Byte arpu_id;

	@Column(
			table = "stats_campaign_archive",
			field = "seniority_id",
			type = "tinyint(4) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getSeniorityId",
			setMethod = "setSeniorityId"
	)
	private Byte seniority_id;

	@Column(
			table = "stats_campaign_archive",
			field = "profile_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			comment = "",
			getMethod = "getProfileId",
			setMethod = "setProfileId"
	)
	private Byte profile_id;

	@Column(
			table = "stats_campaign_archive",
			field = "status_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			comment = "",
			getMethod = "getStatusId",
			setMethod = "setStatusId"
	)
	private Byte status_id;

	@Column(
			table = "stats_campaign_archive",
			field = "network_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			comment = "",
			getMethod = "getNetworkId",
			setMethod = "setNetworkId"
	)
	private Byte network_id;

	@Column(
			table = "stats_campaign_archive",
			field = "amount_usage",
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
			getMethod = "getAmountUsage",
			setMethod = "setAmountUsage"
	)
	private Integer amount_usage;

	@Column(
			table = "stats_campaign_archive",
			field = "amount_recharge",
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
			getMethod = "getAmountRecharge",
			setMethod = "setAmountRecharge"
	)
	private Integer amount_recharge;

	@Column(
			table = "stats_campaign_archive",
			field = "amount_invoice",
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
			getMethod = "getAmountInvoice",
			setMethod = "setAmountInvoice"
	)
	private Integer amount_invoice;

	@Column(
			table = "stats_campaign_archive",
			field = "amount_payment",
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
			getMethod = "getAmountPayment",
			setMethod = "setAmountPayment"
	)
	private Integer amount_payment;

	@Column(
			table = "stats_campaign_archive",
			field = "amount_call",
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
			getMethod = "getAmountCall",
			setMethod = "setAmountCall"
	)
	private Integer amount_call;

	@Column(
			table = "stats_campaign_archive",
			field = "amount_message",
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
			getMethod = "getAmountMessage",
			setMethod = "setAmountMessage"
	)
	private Integer amount_message;

	@Column(
			table = "stats_campaign_archive",
			field = "amount_data",
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
			getMethod = "getAmountData",
			setMethod = "setAmountData"
	)
	private Integer amount_data;


	public StatsCampaignArchive() {} 

	public StatsCampaignArchive( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( StatsCampaignArchive.Fields.msisdn.name() );
		this.campaign_id = rs.getShort( StatsCampaignArchive.Fields.campaign_id.name() );
		this.start_date = rs.getDate( StatsCampaignArchive.Fields.start_date.name() );
		this.end_date = rs.getDate( StatsCampaignArchive.Fields.end_date.name() );
		this.subs_status = rs.getString( StatsCampaignArchive.Fields.subs_status.name() );
		this.arpu_id = rs.getByte( StatsCampaignArchive.Fields.arpu_id.name() );
		this.seniority_id = rs.getByte( StatsCampaignArchive.Fields.seniority_id.name() );
		this.profile_id = rs.getByte( StatsCampaignArchive.Fields.profile_id.name() );
		this.status_id = rs.getByte( StatsCampaignArchive.Fields.status_id.name() );
		this.network_id = rs.getByte( StatsCampaignArchive.Fields.network_id.name() );
		this.amount_usage = rs.getInt( StatsCampaignArchive.Fields.amount_usage.name() );
		this.amount_recharge = rs.getInt( StatsCampaignArchive.Fields.amount_recharge.name() );
		this.amount_invoice = rs.getInt( StatsCampaignArchive.Fields.amount_invoice.name() );
		this.amount_payment = rs.getInt( StatsCampaignArchive.Fields.amount_payment.name() );
		this.amount_call = rs.getInt( StatsCampaignArchive.Fields.amount_call.name() );
		this.amount_message = rs.getInt( StatsCampaignArchive.Fields.amount_message.name() );
		this.amount_data = rs.getInt( StatsCampaignArchive.Fields.amount_data.name() );

	}

	public StatsCampaignArchive( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( StatsCampaignArchive.Fields.msisdn.name() );
		this.campaign_id = (short)jo.getInt( StatsCampaignArchive.Fields.campaign_id.name() );
		this.start_date = Format.getMysqlDateTime( jo.getString( StatsCampaignArchive.Fields.start_date.name() ) );
		this.end_date = Format.getMysqlDateTime( jo.getString( StatsCampaignArchive.Fields.end_date.name() ) );
		this.subs_status = jo.getString( StatsCampaignArchive.Fields.subs_status.name() );
		this.arpu_id = (byte)jo.getInt( StatsCampaignArchive.Fields.arpu_id.name() );
		this.seniority_id = (byte)jo.getInt( StatsCampaignArchive.Fields.seniority_id.name() );
		this.profile_id = (byte)jo.getInt( StatsCampaignArchive.Fields.profile_id.name() );
		this.status_id = (byte)jo.getInt( StatsCampaignArchive.Fields.status_id.name() );
		this.network_id = (byte)jo.getInt( StatsCampaignArchive.Fields.network_id.name() );
		this.amount_usage = (int)jo.getInt( StatsCampaignArchive.Fields.amount_usage.name() );
		this.amount_recharge = (int)jo.getInt( StatsCampaignArchive.Fields.amount_recharge.name() );
		this.amount_invoice = (int)jo.getInt( StatsCampaignArchive.Fields.amount_invoice.name() );
		this.amount_payment = (int)jo.getInt( StatsCampaignArchive.Fields.amount_payment.name() );
		this.amount_call = (int)jo.getInt( StatsCampaignArchive.Fields.amount_call.name() );
		this.amount_message = (int)jo.getInt( StatsCampaignArchive.Fields.amount_message.name() );
		this.amount_data = (int)jo.getInt( StatsCampaignArchive.Fields.amount_data.name() );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public StatsCampaignArchive setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

		return this;

	}

	public Short getCampaignId() {

		return this.campaign_id;

	}

	public StatsCampaignArchive setCampaignId( Short campaign_id ) {

		this.campaign_id = campaign_id;

		return this;

	}

	public Date getStartDate() {

		return this.start_date;

	}

	public StatsCampaignArchive setStartDate( Date start_date ) {

		this.start_date = start_date;

		return this;

	}

	public Date getEndDate() {

		return this.end_date;

	}

	public StatsCampaignArchive setEndDate( Date end_date ) {

		this.end_date = end_date;

		return this;

	}

	public String getSubsStatus() {

		return this.subs_status;

	}

	public StatsCampaignArchive setSubsStatus( String subs_status ) {

		this.subs_status = subs_status;

		return this;

	}

	public Byte getArpuId() {

		return this.arpu_id;

	}

	public StatsCampaignArchive setArpuId( Byte arpu_id ) {

		this.arpu_id = arpu_id;

		return this;

	}

	public Byte getSeniorityId() {

		return this.seniority_id;

	}

	public StatsCampaignArchive setSeniorityId( Byte seniority_id ) {

		this.seniority_id = seniority_id;

		return this;

	}

	public Byte getProfileId() {

		return this.profile_id;

	}

	public StatsCampaignArchive setProfileId( Byte profile_id ) {

		this.profile_id = profile_id;

		return this;

	}

	public Byte getStatusId() {

		return this.status_id;

	}

	public StatsCampaignArchive setStatusId( Byte status_id ) {

		this.status_id = status_id;

		return this;

	}

	public Byte getNetworkId() {

		return this.network_id;

	}

	public StatsCampaignArchive setNetworkId( Byte network_id ) {

		this.network_id = network_id;

		return this;

	}

	public Integer getAmountUsage() {

		return this.amount_usage;

	}

	public StatsCampaignArchive setAmountUsage( Integer amount_usage ) {

		this.amount_usage = amount_usage;

		return this;

	}

	public Integer getAmountRecharge() {

		return this.amount_recharge;

	}

	public StatsCampaignArchive setAmountRecharge( Integer amount_recharge ) {

		this.amount_recharge = amount_recharge;

		return this;

	}

	public Integer getAmountInvoice() {

		return this.amount_invoice;

	}

	public StatsCampaignArchive setAmountInvoice( Integer amount_invoice ) {

		this.amount_invoice = amount_invoice;

		return this;

	}

	public Integer getAmountPayment() {

		return this.amount_payment;

	}

	public StatsCampaignArchive setAmountPayment( Integer amount_payment ) {

		this.amount_payment = amount_payment;

		return this;

	}

	public Integer getAmountCall() {

		return this.amount_call;

	}

	public StatsCampaignArchive setAmountCall( Integer amount_call ) {

		this.amount_call = amount_call;

		return this;

	}

	public Integer getAmountMessage() {

		return this.amount_message;

	}

	public StatsCampaignArchive setAmountMessage( Integer amount_message ) {

		this.amount_message = amount_message;

		return this;

	}

	public Integer getAmountData() {

		return this.amount_data;

	}

	public StatsCampaignArchive setAmountData( Integer amount_data ) {

		this.amount_data = amount_data;

		return this;

	}

	public Fields[] getEntityFields() {

		return StatsCampaignArchive.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"campaign_id\": \"" ).append( this.getCampaignId() ).append( "\", " )
			.append( "\"start_date\": \"" ).append( this.getStartDate() ).append( "\", " )
			.append( "\"end_date\": \"" ).append( this.getEndDate() ).append( "\", " )
			.append( "\"subs_status\": \"" ).append( this.getSubsStatus() ).append( "\", " )
			.append( "\"arpu_id\": \"" ).append( this.getArpuId() ).append( "\", " )
			.append( "\"seniority_id\": \"" ).append( this.getSeniorityId() ).append( "\", " )
			.append( "\"profile_id\": \"" ).append( this.getProfileId() ).append( "\", " )
			.append( "\"status_id\": \"" ).append( this.getStatusId() ).append( "\", " )
			.append( "\"network_id\": \"" ).append( this.getNetworkId() ).append( "\", " )
			.append( "\"amount_usage\": \"" ).append( this.getAmountUsage() ).append( "\", " )
			.append( "\"amount_recharge\": \"" ).append( this.getAmountRecharge() ).append( "\", " )
			.append( "\"amount_invoice\": \"" ).append( this.getAmountInvoice() ).append( "\", " )
			.append( "\"amount_payment\": \"" ).append( this.getAmountPayment() ).append( "\", " )
			.append( "\"amount_call\": \"" ).append( this.getAmountCall() ).append( "\", " )
			.append( "\"amount_message\": \"" ).append( this.getAmountMessage() ).append( "\", " )
			.append( "\"amount_data\": \"" ).append( this.getAmountData() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }