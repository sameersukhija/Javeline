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


@Table( "stats_subs_postpaid" )
public class StatsSubsPostpaid { 

	public enum Fields { msisdn, amount_usage, delay_at_payment, qty_rate_plan_id_change, last_rate_plan_id_change_date, qty_status_id_change, last_status_id_change_date, agg_date }

	@Column(
			table = "stats_subs_postpaid",
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
			table = "stats_subs_postpaid",
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
			table = "stats_subs_postpaid",
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
			comment = "",
			getMethod = "getDelayAtPayment",
			setMethod = "setDelayAtPayment"
	)
	private Float delay_at_payment;

	@Column(
			table = "stats_subs_postpaid",
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
			comment = "",
			getMethod = "getQtyRatePlanIdChange",
			setMethod = "setQtyRatePlanIdChange"
	)
	private Integer qty_rate_plan_id_change;

	@Column(
			table = "stats_subs_postpaid",
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
			comment = "",
			getMethod = "getLastRatePlanIdChangeDate",
			setMethod = "setLastRatePlanIdChangeDate"
	)
	private Date last_rate_plan_id_change_date;

	@Column(
			table = "stats_subs_postpaid",
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
			comment = "",
			getMethod = "getQtyStatusIdChange",
			setMethod = "setQtyStatusIdChange"
	)
	private Integer qty_status_id_change;

	@Column(
			table = "stats_subs_postpaid",
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
			comment = "",
			getMethod = "getLastStatusIdChangeDate",
			setMethod = "setLastStatusIdChangeDate"
	)
	private Date last_status_id_change_date;

	@Column(
			table = "stats_subs_postpaid",
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
			comment = "",
			getMethod = "getAggDate",
			setMethod = "setAggDate"
	)
	private Date agg_date;


	public StatsSubsPostpaid() {} 

	public StatsSubsPostpaid( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( StatsSubsPostpaid.Fields.msisdn.name() );
		this.amount_usage = rs.getFloat( StatsSubsPostpaid.Fields.amount_usage.name() );
		this.delay_at_payment = rs.getFloat( StatsSubsPostpaid.Fields.delay_at_payment.name() );
		this.qty_rate_plan_id_change = rs.getInt( StatsSubsPostpaid.Fields.qty_rate_plan_id_change.name() );
		this.last_rate_plan_id_change_date = rs.getDate( StatsSubsPostpaid.Fields.last_rate_plan_id_change_date.name() );
		this.qty_status_id_change = rs.getInt( StatsSubsPostpaid.Fields.qty_status_id_change.name() );
		this.last_status_id_change_date = rs.getDate( StatsSubsPostpaid.Fields.last_status_id_change_date.name() );
		this.agg_date = rs.getDate( StatsSubsPostpaid.Fields.agg_date.name() );

	}

	public StatsSubsPostpaid( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( StatsSubsPostpaid.Fields.msisdn.name() );
		this.amount_usage = (float)jo.getDouble( StatsSubsPostpaid.Fields.amount_usage.name() );
		this.delay_at_payment = (float)jo.getDouble( StatsSubsPostpaid.Fields.delay_at_payment.name() );
		this.qty_rate_plan_id_change = (int)jo.getInt( StatsSubsPostpaid.Fields.qty_rate_plan_id_change.name() );
		this.last_rate_plan_id_change_date = Format.getMysqlDateTime( jo.getString( StatsSubsPostpaid.Fields.last_rate_plan_id_change_date.name() ) );
		this.qty_status_id_change = (int)jo.getInt( StatsSubsPostpaid.Fields.qty_status_id_change.name() );
		this.last_status_id_change_date = Format.getMysqlDateTime( jo.getString( StatsSubsPostpaid.Fields.last_status_id_change_date.name() ) );
		this.agg_date = Format.getMysqlDateTime( jo.getString( StatsSubsPostpaid.Fields.agg_date.name() ) );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public StatsSubsPostpaid setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

		return this;

	}

	public Float getAmountUsage() {

		return this.amount_usage;

	}

	public StatsSubsPostpaid setAmountUsage( Float amount_usage ) {

		this.amount_usage = amount_usage;

		return this;

	}

	public Float getDelayAtPayment() {

		return this.delay_at_payment;

	}

	public StatsSubsPostpaid setDelayAtPayment( Float delay_at_payment ) {

		this.delay_at_payment = delay_at_payment;

		return this;

	}

	public Integer getQtyRatePlanIdChange() {

		return this.qty_rate_plan_id_change;

	}

	public StatsSubsPostpaid setQtyRatePlanIdChange( Integer qty_rate_plan_id_change ) {

		this.qty_rate_plan_id_change = qty_rate_plan_id_change;

		return this;

	}

	public Date getLastRatePlanIdChangeDate() {

		return this.last_rate_plan_id_change_date;

	}

	public StatsSubsPostpaid setLastRatePlanIdChangeDate( Date last_rate_plan_id_change_date ) {

		this.last_rate_plan_id_change_date = last_rate_plan_id_change_date;

		return this;

	}

	public Integer getQtyStatusIdChange() {

		return this.qty_status_id_change;

	}

	public StatsSubsPostpaid setQtyStatusIdChange( Integer qty_status_id_change ) {

		this.qty_status_id_change = qty_status_id_change;

		return this;

	}

	public Date getLastStatusIdChangeDate() {

		return this.last_status_id_change_date;

	}

	public StatsSubsPostpaid setLastStatusIdChangeDate( Date last_status_id_change_date ) {

		this.last_status_id_change_date = last_status_id_change_date;

		return this;

	}

	public Date getAggDate() {

		return this.agg_date;

	}

	public StatsSubsPostpaid setAggDate( Date agg_date ) {

		this.agg_date = agg_date;

		return this;

	}

	public Fields[] getEntityFields() {

		return StatsSubsPostpaid.Fields.values();

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