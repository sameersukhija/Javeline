package com.lumata.e4o_tenant.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.util.Date;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "stats_subs_postpaid_old" )
public class StatsSubsPostpaidOld { 

	public enum Fields { msisdn, amount_usage, delay_at_payment, qty_rate_plan_id_change, last_rate_plan_id_change_date, qty_status_id_change, last_status_id_change_date, agg_date }

	@Column(
			table = "stats_subs_postpaid_old",
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
			getMethod = "getMsisdn",
			setMethod = "setMsisdn"
	)
	private Long msisdn;

	@Column(
			table = "stats_subs_postpaid_old",
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
			getMethod = "getAmountUsage",
			setMethod = "setAmountUsage"
	)
	private Float amount_usage;

	@Column(
			table = "stats_subs_postpaid_old",
			field = "delay_at_payment",
			type = "float unsigned",
			mysqlType = "float",
			javaType = "Float",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getDelayAtPayment",
			setMethod = "setDelayAtPayment"
	)
	private Float delay_at_payment;

	@Column(
			table = "stats_subs_postpaid_old",
			field = "qty_rate_plan_id_change",
			type = "mediumint(8) unsigned",
			mysqlType = "mediumint",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 8,
			getMethod = "getQtyRatePlanIdChange",
			setMethod = "setQtyRatePlanIdChange"
	)
	private Integer qty_rate_plan_id_change;

	@Column(
			table = "stats_subs_postpaid_old",
			field = "last_rate_plan_id_change_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getLastRatePlanIdChangeDate",
			setMethod = "setLastRatePlanIdChangeDate"
	)
	private Date last_rate_plan_id_change_date;

	@Column(
			table = "stats_subs_postpaid_old",
			field = "qty_status_id_change",
			type = "mediumint(8) unsigned",
			mysqlType = "mediumint",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 8,
			getMethod = "getQtyStatusIdChange",
			setMethod = "setQtyStatusIdChange"
	)
	private Integer qty_status_id_change;

	@Column(
			table = "stats_subs_postpaid_old",
			field = "last_status_id_change_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getLastStatusIdChangeDate",
			setMethod = "setLastStatusIdChangeDate"
	)
	private Date last_status_id_change_date;

	@Column(
			table = "stats_subs_postpaid_old",
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


	public StatsSubsPostpaidOld() {} 

	public StatsSubsPostpaidOld( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( StatsSubsPostpaidOld.Fields.msisdn.name() );
		this.amount_usage = rs.getFloat( StatsSubsPostpaidOld.Fields.amount_usage.name() );
		this.delay_at_payment = rs.getFloat( StatsSubsPostpaidOld.Fields.delay_at_payment.name() );
		this.qty_rate_plan_id_change = rs.getInt( StatsSubsPostpaidOld.Fields.qty_rate_plan_id_change.name() );
		this.last_rate_plan_id_change_date = rs.getDate( StatsSubsPostpaidOld.Fields.last_rate_plan_id_change_date.name() );
		this.qty_status_id_change = rs.getInt( StatsSubsPostpaidOld.Fields.qty_status_id_change.name() );
		this.last_status_id_change_date = rs.getDate( StatsSubsPostpaidOld.Fields.last_status_id_change_date.name() );
		this.agg_date = rs.getDate( StatsSubsPostpaidOld.Fields.agg_date.name() );

	}

	public StatsSubsPostpaidOld( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( StatsSubsPostpaidOld.Fields.msisdn.name() );
		this.amount_usage = (float)jo.getDouble( StatsSubsPostpaidOld.Fields.amount_usage.name() );
		this.delay_at_payment = (float)jo.getDouble( StatsSubsPostpaidOld.Fields.delay_at_payment.name() );
		this.qty_rate_plan_id_change = (int)jo.getInt( StatsSubsPostpaidOld.Fields.qty_rate_plan_id_change.name() );
		this.last_rate_plan_id_change_date = Format.getMysqlDateTime( jo.getString( StatsSubsPostpaidOld.Fields.last_rate_plan_id_change_date.name() ) );
		this.qty_status_id_change = (int)jo.getInt( StatsSubsPostpaidOld.Fields.qty_status_id_change.name() );
		this.last_status_id_change_date = Format.getMysqlDateTime( jo.getString( StatsSubsPostpaidOld.Fields.last_status_id_change_date.name() ) );
		this.agg_date = Format.getMysqlDateTime( jo.getString( StatsSubsPostpaidOld.Fields.agg_date.name() ) );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public void setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

	}

	public Float getAmountUsage() {

		return this.amount_usage;

	}

	public void setAmountUsage( Float amount_usage ) {

		this.amount_usage = amount_usage;

	}

	public Float getDelayAtPayment() {

		return this.delay_at_payment;

	}

	public void setDelayAtPayment( Float delay_at_payment ) {

		this.delay_at_payment = delay_at_payment;

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

	public Date getAggDate() {

		return this.agg_date;

	}

	public void setAggDate( Date agg_date ) {

		this.agg_date = agg_date;

	}

	public Fields[] getEntityFields() {

		return StatsSubsPostpaidOld.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"amount_usage\": \"" ).append( this.getAmountUsage() ).append( "\", " )
			.append( "\"delay_at_payment\": \"" ).append( this.getDelayAtPayment() ).append( "\", " )
			.append( "\"qty_rate_plan_id_change\": \"" ).append( this.getQtyRatePlanIdChange() ).append( "\", " )
			.append( "\"last_rate_plan_id_change_date\": \"" ).append( this.getLastRatePlanIdChangeDate() ).append( "\", " )
			.append( "\"qty_status_id_change\": \"" ).append( this.getQtyStatusIdChange() ).append( "\", " )
			.append( "\"last_status_id_change_date\": \"" ).append( this.getLastStatusIdChangeDate() ).append( "\", " )
			.append( "\"agg_date\": \"" ).append( this.getAggDate() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }