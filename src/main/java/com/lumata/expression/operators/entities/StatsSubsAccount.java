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


@Table( "stats_subs_account" )
public class StatsSubsAccount { 

	public enum Fields { msisdn, qty_invoice, amount_invoice, qty_payment, amount_payment, qty_rate_plan_id_change, last_rate_plan_id_change_date, qty_status_id_change, last_status_id_change_date, last_invoice_date, agg_date }

	@Column(
			table = "stats_subs_account",
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
			table = "stats_subs_account",
			field = "qty_invoice",
			type = "mediumint(8) unsigned",
			mysqlType = "mediumint",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 8,
			getMethod = "getQtyInvoice",
			setMethod = "setQtyInvoice"
	)
	private Integer qty_invoice;

	@Column(
			table = "stats_subs_account",
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
			table = "stats_subs_account",
			field = "qty_payment",
			type = "mediumint(8) unsigned",
			mysqlType = "mediumint",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 8,
			getMethod = "getQtyPayment",
			setMethod = "setQtyPayment"
	)
	private Integer qty_payment;

	@Column(
			table = "stats_subs_account",
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
			table = "stats_subs_account",
			field = "qty_rate_plan_id_change",
			type = "mediumint(8) unsigned",
			mysqlType = "mediumint",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 8,
			getMethod = "getQtyRatePlanIdChange",
			setMethod = "setQtyRatePlanIdChange"
	)
	private Integer qty_rate_plan_id_change;

	@Column(
			table = "stats_subs_account",
			field = "last_rate_plan_id_change_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getLastRatePlanIdChangeDate",
			setMethod = "setLastRatePlanIdChangeDate"
	)
	private Date last_rate_plan_id_change_date;

	@Column(
			table = "stats_subs_account",
			field = "qty_status_id_change",
			type = "mediumint(8) unsigned",
			mysqlType = "mediumint",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 8,
			getMethod = "getQtyStatusIdChange",
			setMethod = "setQtyStatusIdChange"
	)
	private Integer qty_status_id_change;

	@Column(
			table = "stats_subs_account",
			field = "last_status_id_change_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getLastStatusIdChangeDate",
			setMethod = "setLastStatusIdChangeDate"
	)
	private Date last_status_id_change_date;

	@Column(
			table = "stats_subs_account",
			field = "last_invoice_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getLastInvoiceDate",
			setMethod = "setLastInvoiceDate"
	)
	private Date last_invoice_date;

	@Column(
			table = "stats_subs_account",
			field = "agg_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getAggDate",
			setMethod = "setAggDate"
	)
	private Date agg_date;


	public StatsSubsAccount() {} 

	public StatsSubsAccount( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( StatsSubsAccount.Fields.msisdn.name() );
		this.qty_invoice = rs.getInt( StatsSubsAccount.Fields.qty_invoice.name() );
		this.amount_invoice = rs.getFloat( StatsSubsAccount.Fields.amount_invoice.name() );
		this.qty_payment = rs.getInt( StatsSubsAccount.Fields.qty_payment.name() );
		this.amount_payment = rs.getFloat( StatsSubsAccount.Fields.amount_payment.name() );
		this.qty_rate_plan_id_change = rs.getInt( StatsSubsAccount.Fields.qty_rate_plan_id_change.name() );
		this.last_rate_plan_id_change_date = rs.getDate( StatsSubsAccount.Fields.last_rate_plan_id_change_date.name() );
		this.qty_status_id_change = rs.getInt( StatsSubsAccount.Fields.qty_status_id_change.name() );
		this.last_status_id_change_date = rs.getDate( StatsSubsAccount.Fields.last_status_id_change_date.name() );
		this.last_invoice_date = rs.getDate( StatsSubsAccount.Fields.last_invoice_date.name() );
		this.agg_date = rs.getDate( StatsSubsAccount.Fields.agg_date.name() );

	}

	public StatsSubsAccount( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( StatsSubsAccount.Fields.msisdn.name() );
		this.qty_invoice = (int)jo.getInt( StatsSubsAccount.Fields.qty_invoice.name() );
		this.amount_invoice = (float)jo.getDouble( StatsSubsAccount.Fields.amount_invoice.name() );
		this.qty_payment = (int)jo.getInt( StatsSubsAccount.Fields.qty_payment.name() );
		this.amount_payment = (float)jo.getDouble( StatsSubsAccount.Fields.amount_payment.name() );
		this.qty_rate_plan_id_change = (int)jo.getInt( StatsSubsAccount.Fields.qty_rate_plan_id_change.name() );
		this.last_rate_plan_id_change_date = Format.getMysqlDateTime( jo.getString( StatsSubsAccount.Fields.last_rate_plan_id_change_date.name() ) );
		this.qty_status_id_change = (int)jo.getInt( StatsSubsAccount.Fields.qty_status_id_change.name() );
		this.last_status_id_change_date = Format.getMysqlDateTime( jo.getString( StatsSubsAccount.Fields.last_status_id_change_date.name() ) );
		this.last_invoice_date = Format.getMysqlDateTime( jo.getString( StatsSubsAccount.Fields.last_invoice_date.name() ) );
		this.agg_date = Format.getMysqlDateTime( jo.getString( StatsSubsAccount.Fields.agg_date.name() ) );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public void setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

	}

	public Integer getQtyInvoice() {

		return this.qty_invoice;

	}

	public void setQtyInvoice( Integer qty_invoice ) {

		this.qty_invoice = qty_invoice;

	}

	public Float getAmountInvoice() {

		return this.amount_invoice;

	}

	public void setAmountInvoice( Float amount_invoice ) {

		this.amount_invoice = amount_invoice;

	}

	public Integer getQtyPayment() {

		return this.qty_payment;

	}

	public void setQtyPayment( Integer qty_payment ) {

		this.qty_payment = qty_payment;

	}

	public Float getAmountPayment() {

		return this.amount_payment;

	}

	public void setAmountPayment( Float amount_payment ) {

		this.amount_payment = amount_payment;

	}

	public Integer getQtyRatePlanIdChange() {

		return this.qty_rate_plan_id_change;

	}

	public void setQtyRatePlanIdChange( Integer qty_rate_plan_id_change ) {

		this.qty_rate_plan_id_change = qty_rate_plan_id_change;

	}

	public Date getLastRatePlanIdChangeDate() {

		return this.last_rate_plan_id_change_date;

	}

	public void setLastRatePlanIdChangeDate( Date last_rate_plan_id_change_date ) {

		this.last_rate_plan_id_change_date = last_rate_plan_id_change_date;

	}

	public Integer getQtyStatusIdChange() {

		return this.qty_status_id_change;

	}

	public void setQtyStatusIdChange( Integer qty_status_id_change ) {

		this.qty_status_id_change = qty_status_id_change;

	}

	public Date getLastStatusIdChangeDate() {

		return this.last_status_id_change_date;

	}

	public void setLastStatusIdChangeDate( Date last_status_id_change_date ) {

		this.last_status_id_change_date = last_status_id_change_date;

	}

	public Date getLastInvoiceDate() {

		return this.last_invoice_date;

	}

	public void setLastInvoiceDate( Date last_invoice_date ) {

		this.last_invoice_date = last_invoice_date;

	}

	public Date getAggDate() {

		return this.agg_date;

	}

	public void setAggDate( Date agg_date ) {

		this.agg_date = agg_date;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"qty_invoice\": \"" ).append( this.getQtyInvoice() ).append( "\", " )
			.append( "\"amount_invoice\": \"" ).append( this.getAmountInvoice() ).append( "\", " )
			.append( "\"qty_payment\": \"" ).append( this.getQtyPayment() ).append( "\", " )
			.append( "\"amount_payment\": \"" ).append( this.getAmountPayment() ).append( "\", " )
			.append( "\"qty_rate_plan_id_change\": \"" ).append( this.getQtyRatePlanIdChange() ).append( "\", " )
			.append( "\"last_rate_plan_id_change_date\": \"" ).append( this.getLastRatePlanIdChangeDate() ).append( "\", " )
			.append( "\"qty_status_id_change\": \"" ).append( this.getQtyStatusIdChange() ).append( "\", " )
			.append( "\"last_status_id_change_date\": \"" ).append( this.getLastStatusIdChangeDate() ).append( "\", " )
			.append( "\"last_invoice_date\": \"" ).append( this.getLastInvoiceDate() ).append( "\", " )
			.append( "\"agg_date\": \"" ).append( this.getAggDate() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }