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


@Table( "daily_postpaid" )
public class DailyPostpaid { 

	public enum Fields { msisdn, amount_usage, delay_at_payment, qty_rate_plan_id_change, qty_status_id_change, agg_date, update_time }

	@Column(
			table = "daily_postpaid",
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
			table = "daily_postpaid",
			field = "amount_usage",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getAmountUsage",
			setMethod = "setAmountUsage"
	)
	private Integer amount_usage;

	@Column(
			table = "daily_postpaid",
			field = "delay_at_payment",
			type = "int(10) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 10,
			getMethod = "getDelayAtPayment",
			setMethod = "setDelayAtPayment"
	)
	private Integer delay_at_payment;

	@Column(
			table = "daily_postpaid",
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
			table = "daily_postpaid",
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
			table = "daily_postpaid",
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
			table = "daily_postpaid",
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


	public DailyPostpaid() {} 

	public DailyPostpaid( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( DailyPostpaid.Fields.msisdn.name() );
		this.amount_usage = rs.getInt( DailyPostpaid.Fields.amount_usage.name() );
		this.delay_at_payment = rs.getInt( DailyPostpaid.Fields.delay_at_payment.name() );
		this.qty_rate_plan_id_change = rs.getShort( DailyPostpaid.Fields.qty_rate_plan_id_change.name() );
		this.qty_status_id_change = rs.getShort( DailyPostpaid.Fields.qty_status_id_change.name() );
		this.agg_date = rs.getDate( DailyPostpaid.Fields.agg_date.name() );
		this.update_time = rs.getTimestamp( DailyPostpaid.Fields.update_time.name() );

	}

	public DailyPostpaid( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( DailyPostpaid.Fields.msisdn.name() );
		this.amount_usage = (int)jo.getInt( DailyPostpaid.Fields.amount_usage.name() );
		this.delay_at_payment = (int)jo.getInt( DailyPostpaid.Fields.delay_at_payment.name() );
		this.qty_rate_plan_id_change = (short)jo.getInt( DailyPostpaid.Fields.qty_rate_plan_id_change.name() );
		this.qty_status_id_change = (short)jo.getInt( DailyPostpaid.Fields.qty_status_id_change.name() );
		this.agg_date = Format.getMysqlDateTime( jo.getString( DailyPostpaid.Fields.agg_date.name() ) );
		this.update_time = new Timestamp( Format.getMysqlDateTime( jo.getString( DailyPostpaid.Fields.update_time.name() ) ).getTime() );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public void setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

	}

	public Integer getAmountUsage() {

		return this.amount_usage;

	}

	public void setAmountUsage( Integer amount_usage ) {

		this.amount_usage = amount_usage;

	}

	public Integer getDelayAtPayment() {

		return this.delay_at_payment;

	}

	public void setDelayAtPayment( Integer delay_at_payment ) {

		this.delay_at_payment = delay_at_payment;

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

	public Fields[] getEntityFields() {

		return DailyPostpaid.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"amount_usage\": \"" ).append( this.getAmountUsage() ).append( "\", " )
			.append( "\"delay_at_payment\": \"" ).append( this.getDelayAtPayment() ).append( "\", " )
			.append( "\"qty_rate_plan_id_change\": \"" ).append( this.getQtyRatePlanIdChange() ).append( "\", " )
			.append( "\"qty_status_id_change\": \"" ).append( this.getQtyStatusIdChange() ).append( "\", " )
			.append( "\"agg_date\": \"" ).append( this.getAggDate() ).append( "\", " )
			.append( "\"update_time\": \"" ).append( this.getUpdateTime() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }