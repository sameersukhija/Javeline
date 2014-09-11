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


@Table( "overall_account" )
public class OverallAccount { 

	public enum Fields { agg_date, period, profile_id, rate_plan_id, status_id, network_id, arpu_id, seniority_id, ucg, sum_invoice, sum_amount_invoice, sum_payment, sum_amount_payment, qty_msisdn }

	@Column(
			table = "overall_account",
			field = "agg_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getAggDate",
			setMethod = "setAggDate"
	)
	private Date agg_date;

	@Column(
			table = "overall_account",
			field = "period",
			type = "enum('day','week','month')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getPeriod",
			setMethod = "setPeriod"
	)
	private String period;

	@Column(
			table = "overall_account",
			field = "profile_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getProfileId",
			setMethod = "setProfileId"
	)
	private Byte profile_id;

	@Column(
			table = "overall_account",
			field = "rate_plan_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getRatePlanId",
			setMethod = "setRatePlanId"
	)
	private Byte rate_plan_id;

	@Column(
			table = "overall_account",
			field = "status_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getStatusId",
			setMethod = "setStatusId"
	)
	private Byte status_id;

	@Column(
			table = "overall_account",
			field = "network_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getNetworkId",
			setMethod = "setNetworkId"
	)
	private Byte network_id;

	@Column(
			table = "overall_account",
			field = "arpu_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getArpuId",
			setMethod = "setArpuId"
	)
	private Byte arpu_id;

	@Column(
			table = "overall_account",
			field = "seniority_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getSeniorityId",
			setMethod = "setSeniorityId"
	)
	private Byte seniority_id;

	@Column(
			table = "overall_account",
			field = "ucg",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getUcg",
			setMethod = "setUcg"
	)
	private Byte ucg;

	@Column(
			table = "overall_account",
			field = "sum_invoice",
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
			getMethod = "getSumInvoice",
			setMethod = "setSumInvoice"
	)
	private Float sum_invoice;

	@Column(
			table = "overall_account",
			field = "sum_amount_invoice",
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
			getMethod = "getSumAmountInvoice",
			setMethod = "setSumAmountInvoice"
	)
	private Float sum_amount_invoice;

	@Column(
			table = "overall_account",
			field = "sum_payment",
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
			getMethod = "getSumPayment",
			setMethod = "setSumPayment"
	)
	private Float sum_payment;

	@Column(
			table = "overall_account",
			field = "sum_amount_payment",
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
			getMethod = "getSumAmountPayment",
			setMethod = "setSumAmountPayment"
	)
	private Float sum_amount_payment;

	@Column(
			table = "overall_account",
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
			getMethod = "getQtyMsisdn",
			setMethod = "setQtyMsisdn"
	)
	private Integer qty_msisdn;


	public OverallAccount() {} 

	public OverallAccount( ResultSet rs ) throws SQLException {

		this.agg_date = rs.getDate( OverallAccount.Fields.agg_date.name() );
		this.period = rs.getString( OverallAccount.Fields.period.name() );
		this.profile_id = rs.getByte( OverallAccount.Fields.profile_id.name() );
		this.rate_plan_id = rs.getByte( OverallAccount.Fields.rate_plan_id.name() );
		this.status_id = rs.getByte( OverallAccount.Fields.status_id.name() );
		this.network_id = rs.getByte( OverallAccount.Fields.network_id.name() );
		this.arpu_id = rs.getByte( OverallAccount.Fields.arpu_id.name() );
		this.seniority_id = rs.getByte( OverallAccount.Fields.seniority_id.name() );
		this.ucg = rs.getByte( OverallAccount.Fields.ucg.name() );
		this.sum_invoice = rs.getFloat( OverallAccount.Fields.sum_invoice.name() );
		this.sum_amount_invoice = rs.getFloat( OverallAccount.Fields.sum_amount_invoice.name() );
		this.sum_payment = rs.getFloat( OverallAccount.Fields.sum_payment.name() );
		this.sum_amount_payment = rs.getFloat( OverallAccount.Fields.sum_amount_payment.name() );
		this.qty_msisdn = rs.getInt( OverallAccount.Fields.qty_msisdn.name() );

	}

	public OverallAccount( JSONObject jo ) throws JSONException, ParseException {

		this.agg_date = Format.getMysqlDateTime( jo.getString( OverallAccount.Fields.agg_date.name() ) );
		this.period = jo.getString( OverallAccount.Fields.period.name() );
		this.profile_id = (byte)jo.getInt( OverallAccount.Fields.profile_id.name() );
		this.rate_plan_id = (byte)jo.getInt( OverallAccount.Fields.rate_plan_id.name() );
		this.status_id = (byte)jo.getInt( OverallAccount.Fields.status_id.name() );
		this.network_id = (byte)jo.getInt( OverallAccount.Fields.network_id.name() );
		this.arpu_id = (byte)jo.getInt( OverallAccount.Fields.arpu_id.name() );
		this.seniority_id = (byte)jo.getInt( OverallAccount.Fields.seniority_id.name() );
		this.ucg = (byte)jo.getInt( OverallAccount.Fields.ucg.name() );
		this.sum_invoice = (float)jo.getDouble( OverallAccount.Fields.sum_invoice.name() );
		this.sum_amount_invoice = (float)jo.getDouble( OverallAccount.Fields.sum_amount_invoice.name() );
		this.sum_payment = (float)jo.getDouble( OverallAccount.Fields.sum_payment.name() );
		this.sum_amount_payment = (float)jo.getDouble( OverallAccount.Fields.sum_amount_payment.name() );
		this.qty_msisdn = (int)jo.getInt( OverallAccount.Fields.qty_msisdn.name() );

	}

	public Date getAggDate() {

		return this.agg_date;

	}

	public void setAggDate( Date agg_date ) {

		this.agg_date = agg_date;

	}

	public String getPeriod() {

		return this.period;

	}

	public void setPeriod( String period ) {

		this.period = period;

	}

	public Byte getProfileId() {

		return this.profile_id;

	}

	public void setProfileId( Byte profile_id ) {

		this.profile_id = profile_id;

	}

	public Byte getRatePlanId() {

		return this.rate_plan_id;

	}

	public void setRatePlanId( Byte rate_plan_id ) {

		this.rate_plan_id = rate_plan_id;

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

	public Byte getSeniorityId() {

		return this.seniority_id;

	}

	public void setSeniorityId( Byte seniority_id ) {

		this.seniority_id = seniority_id;

	}

	public Byte getUcg() {

		return this.ucg;

	}

	public void setUcg( Byte ucg ) {

		this.ucg = ucg;

	}

	public Float getSumInvoice() {

		return this.sum_invoice;

	}

	public void setSumInvoice( Float sum_invoice ) {

		this.sum_invoice = sum_invoice;

	}

	public Float getSumAmountInvoice() {

		return this.sum_amount_invoice;

	}

	public void setSumAmountInvoice( Float sum_amount_invoice ) {

		this.sum_amount_invoice = sum_amount_invoice;

	}

	public Float getSumPayment() {

		return this.sum_payment;

	}

	public void setSumPayment( Float sum_payment ) {

		this.sum_payment = sum_payment;

	}

	public Float getSumAmountPayment() {

		return this.sum_amount_payment;

	}

	public void setSumAmountPayment( Float sum_amount_payment ) {

		this.sum_amount_payment = sum_amount_payment;

	}

	public Integer getQtyMsisdn() {

		return this.qty_msisdn;

	}

	public void setQtyMsisdn( Integer qty_msisdn ) {

		this.qty_msisdn = qty_msisdn;

	}

	public Fields[] getEntityFields() {

		return OverallAccount.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"agg_date\": \"" ).append( this.getAggDate() ).append( "\", " )
			.append( "\"period\": \"" ).append( this.getPeriod() ).append( "\", " )
			.append( "\"profile_id\": \"" ).append( this.getProfileId() ).append( "\", " )
			.append( "\"rate_plan_id\": \"" ).append( this.getRatePlanId() ).append( "\", " )
			.append( "\"status_id\": \"" ).append( this.getStatusId() ).append( "\", " )
			.append( "\"network_id\": \"" ).append( this.getNetworkId() ).append( "\", " )
			.append( "\"arpu_id\": \"" ).append( this.getArpuId() ).append( "\", " )
			.append( "\"seniority_id\": \"" ).append( this.getSeniorityId() ).append( "\", " )
			.append( "\"ucg\": \"" ).append( this.getUcg() ).append( "\", " )
			.append( "\"sum_invoice\": \"" ).append( this.getSumInvoice() ).append( "\", " )
			.append( "\"sum_amount_invoice\": \"" ).append( this.getSumAmountInvoice() ).append( "\", " )
			.append( "\"sum_payment\": \"" ).append( this.getSumPayment() ).append( "\", " )
			.append( "\"sum_amount_payment\": \"" ).append( this.getSumAmountPayment() ).append( "\", " )
			.append( "\"qty_msisdn\": \"" ).append( this.getQtyMsisdn() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }