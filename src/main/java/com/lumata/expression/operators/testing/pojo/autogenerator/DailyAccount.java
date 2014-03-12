package com.lumata.expression.operators.testing.pojo.autogenerator;

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


@Table( "daily_account" )
public class DailyAccount { 

	public enum Fields { msisdn, qty_invoice, amount_invoice, qty_payment, amount_payment, qty_rate_plan_id_change, qty_status_id_change, agg_date, update_time }

	@Column(
			table = "daily_account",
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
			table = "daily_account",
			field = "qty_invoice",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 5,
			getMethod = "getQtyInvoice",
			setMethod = "setQtyInvoice"
	)
	private Short qty_invoice;

	@Column(
			table = "daily_account",
			field = "amount_invoice",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getAmountInvoice",
			setMethod = "setAmountInvoice"
	)
	private Integer amount_invoice;

	@Column(
			table = "daily_account",
			field = "qty_payment",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 5,
			getMethod = "getQtyPayment",
			setMethod = "setQtyPayment"
	)
	private Short qty_payment;

	@Column(
			table = "daily_account",
			field = "amount_payment",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getAmountPayment",
			setMethod = "setAmountPayment"
	)
	private Integer amount_payment;

	@Column(
			table = "daily_account",
			field = "qty_rate_plan_id_change",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 5,
			getMethod = "getQtyRatePlanIdChange",
			setMethod = "setQtyRatePlanIdChange"
	)
	private Short qty_rate_plan_id_change;

	@Column(
			table = "daily_account",
			field = "qty_status_id_change",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 5,
			getMethod = "getQtyStatusIdChange",
			setMethod = "setQtyStatusIdChange"
	)
	private Short qty_status_id_change;

	@Column(
			table = "daily_account",
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

	@Column(
			table = "daily_account",
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


	public DailyAccount() {} 

	public DailyAccount( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( DailyAccount.Fields.msisdn.name() );
		this.qty_invoice = rs.getShort( DailyAccount.Fields.qty_invoice.name() );
		this.amount_invoice = rs.getInt( DailyAccount.Fields.amount_invoice.name() );
		this.qty_payment = rs.getShort( DailyAccount.Fields.qty_payment.name() );
		this.amount_payment = rs.getInt( DailyAccount.Fields.amount_payment.name() );
		this.qty_rate_plan_id_change = rs.getShort( DailyAccount.Fields.qty_rate_plan_id_change.name() );
		this.qty_status_id_change = rs.getShort( DailyAccount.Fields.qty_status_id_change.name() );
		this.agg_date = rs.getDate( DailyAccount.Fields.agg_date.name() );
		this.update_time = rs.getTimestamp( DailyAccount.Fields.update_time.name() );

	}

	public DailyAccount( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( DailyAccount.Fields.msisdn.name() );
		this.qty_invoice = (short)jo.getInt( DailyAccount.Fields.qty_invoice.name() );
		this.amount_invoice = (int)jo.getInt( DailyAccount.Fields.amount_invoice.name() );
		this.qty_payment = (short)jo.getInt( DailyAccount.Fields.qty_payment.name() );
		this.amount_payment = (int)jo.getInt( DailyAccount.Fields.amount_payment.name() );
		this.qty_rate_plan_id_change = (short)jo.getInt( DailyAccount.Fields.qty_rate_plan_id_change.name() );
		this.qty_status_id_change = (short)jo.getInt( DailyAccount.Fields.qty_status_id_change.name() );
		this.agg_date = Format.getMysqlDateTime( jo.getString( DailyAccount.Fields.agg_date.name() ) );
		this.update_time = new Timestamp( Format.getMysqlDateTime( jo.getString( DailyAccount.Fields.update_time.name() ) ).getTime() );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public void setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

	}

	public Short getQtyInvoice() {

		return this.qty_invoice;

	}

	public void setQtyInvoice( Short qty_invoice ) {

		this.qty_invoice = qty_invoice;

	}

	public Integer getAmountInvoice() {

		return this.amount_invoice;

	}

	public void setAmountInvoice( Integer amount_invoice ) {

		this.amount_invoice = amount_invoice;

	}

	public Short getQtyPayment() {

		return this.qty_payment;

	}

	public void setQtyPayment( Short qty_payment ) {

		this.qty_payment = qty_payment;

	}

	public Integer getAmountPayment() {

		return this.amount_payment;

	}

	public void setAmountPayment( Integer amount_payment ) {

		this.amount_payment = amount_payment;

	}

	public Short getQtyRatePlanIdChange() {

		return this.qty_rate_plan_id_change;

	}

	public void setQtyRatePlanIdChange( Short qty_rate_plan_id_change ) {

		this.qty_rate_plan_id_change = qty_rate_plan_id_change;

	}

	public Short getQtyStatusIdChange() {

		return this.qty_status_id_change;

	}

	public void setQtyStatusIdChange( Short qty_status_id_change ) {

		this.qty_status_id_change = qty_status_id_change;

	}

	public Date getAggDate() {

		return this.agg_date;

	}

	public void setAggDate( Date agg_date ) {

		this.agg_date = agg_date;

	}

	public Timestamp getUpdateTime() {

		return this.update_time;

	}

	public void setUpdateTime( Timestamp update_time ) {

		this.update_time = update_time;

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
			.append( "\"qty_status_id_change\": \"" ).append( this.getQtyStatusIdChange() ).append( "\", " )
			.append( "\"agg_date\": \"" ).append( this.getAggDate() ).append( "\", " )
			.append( "\"update_time\": \"" ).append( this.getUpdateTime() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }