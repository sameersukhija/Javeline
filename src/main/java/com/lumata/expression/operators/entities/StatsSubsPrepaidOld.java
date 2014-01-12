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


@Table( "stats_subs_prepaid_old" )
public class StatsSubsPrepaidOld { 

	public enum Fields { msisdn, amount_usage, balance_main_account, qty_recharge, amount_recharge, validity_date, deactivation_date, qty_rate_plan_id_change, last_rate_plan_id_change_date, qty_status_id_change, last_status_id_change_date, last_recharge_date, validity_days_at_recharge, agg_date }

	@Column(
			table = "stats_subs_prepaid_old",
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
			table = "stats_subs_prepaid_old",
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
			table = "stats_subs_prepaid_old",
			field = "balance_main_account",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getBalanceMainAccount",
			setMethod = "setBalanceMainAccount"
	)
	private Integer balance_main_account;

	@Column(
			table = "stats_subs_prepaid_old",
			field = "qty_recharge",
			type = "mediumint(8) unsigned",
			mysqlType = "mediumint",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 8,
			getMethod = "getQtyRecharge",
			setMethod = "setQtyRecharge"
	)
	private Integer qty_recharge;

	@Column(
			table = "stats_subs_prepaid_old",
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
			table = "stats_subs_prepaid_old",
			field = "validity_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getValidityDate",
			setMethod = "setValidityDate"
	)
	private Date validity_date;

	@Column(
			table = "stats_subs_prepaid_old",
			field = "deactivation_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getDeactivationDate",
			setMethod = "setDeactivationDate"
	)
	private Date deactivation_date;

	@Column(
			table = "stats_subs_prepaid_old",
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
			table = "stats_subs_prepaid_old",
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
			table = "stats_subs_prepaid_old",
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
			table = "stats_subs_prepaid_old",
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
			table = "stats_subs_prepaid_old",
			field = "last_recharge_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getLastRechargeDate",
			setMethod = "setLastRechargeDate"
	)
	private Date last_recharge_date;

	@Column(
			table = "stats_subs_prepaid_old",
			field = "validity_days_at_recharge",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getValidityDaysAtRecharge",
			setMethod = "setValidityDaysAtRecharge"
	)
	private Integer validity_days_at_recharge;

	@Column(
			table = "stats_subs_prepaid_old",
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


	public StatsSubsPrepaidOld() {} 

	public StatsSubsPrepaidOld( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( StatsSubsPrepaidOld.Fields.msisdn.name() );
		this.amount_usage = rs.getFloat( StatsSubsPrepaidOld.Fields.amount_usage.name() );
		this.balance_main_account = rs.getInt( StatsSubsPrepaidOld.Fields.balance_main_account.name() );
		this.qty_recharge = rs.getInt( StatsSubsPrepaidOld.Fields.qty_recharge.name() );
		this.amount_recharge = rs.getFloat( StatsSubsPrepaidOld.Fields.amount_recharge.name() );
		this.validity_date = rs.getDate( StatsSubsPrepaidOld.Fields.validity_date.name() );
		this.deactivation_date = rs.getDate( StatsSubsPrepaidOld.Fields.deactivation_date.name() );
		this.qty_rate_plan_id_change = rs.getInt( StatsSubsPrepaidOld.Fields.qty_rate_plan_id_change.name() );
		this.last_rate_plan_id_change_date = rs.getDate( StatsSubsPrepaidOld.Fields.last_rate_plan_id_change_date.name() );
		this.qty_status_id_change = rs.getInt( StatsSubsPrepaidOld.Fields.qty_status_id_change.name() );
		this.last_status_id_change_date = rs.getDate( StatsSubsPrepaidOld.Fields.last_status_id_change_date.name() );
		this.last_recharge_date = rs.getDate( StatsSubsPrepaidOld.Fields.last_recharge_date.name() );
		this.validity_days_at_recharge = rs.getInt( StatsSubsPrepaidOld.Fields.validity_days_at_recharge.name() );
		this.agg_date = rs.getDate( StatsSubsPrepaidOld.Fields.agg_date.name() );

	}

	public StatsSubsPrepaidOld( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( StatsSubsPrepaidOld.Fields.msisdn.name() );
		this.amount_usage = (float)jo.getDouble( StatsSubsPrepaidOld.Fields.amount_usage.name() );
		this.balance_main_account = (int)jo.getInt( StatsSubsPrepaidOld.Fields.balance_main_account.name() );
		this.qty_recharge = (int)jo.getInt( StatsSubsPrepaidOld.Fields.qty_recharge.name() );
		this.amount_recharge = (float)jo.getDouble( StatsSubsPrepaidOld.Fields.amount_recharge.name() );
		this.validity_date = Format.getMysqlDateTime( jo.getString( StatsSubsPrepaidOld.Fields.validity_date.name() ) );
		this.deactivation_date = Format.getMysqlDateTime( jo.getString( StatsSubsPrepaidOld.Fields.deactivation_date.name() ) );
		this.qty_rate_plan_id_change = (int)jo.getInt( StatsSubsPrepaidOld.Fields.qty_rate_plan_id_change.name() );
		this.last_rate_plan_id_change_date = Format.getMysqlDateTime( jo.getString( StatsSubsPrepaidOld.Fields.last_rate_plan_id_change_date.name() ) );
		this.qty_status_id_change = (int)jo.getInt( StatsSubsPrepaidOld.Fields.qty_status_id_change.name() );
		this.last_status_id_change_date = Format.getMysqlDateTime( jo.getString( StatsSubsPrepaidOld.Fields.last_status_id_change_date.name() ) );
		this.last_recharge_date = Format.getMysqlDateTime( jo.getString( StatsSubsPrepaidOld.Fields.last_recharge_date.name() ) );
		this.validity_days_at_recharge = (int)jo.getInt( StatsSubsPrepaidOld.Fields.validity_days_at_recharge.name() );
		this.agg_date = Format.getMysqlDateTime( jo.getString( StatsSubsPrepaidOld.Fields.agg_date.name() ) );

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

	public Integer getBalanceMainAccount() {

		return this.balance_main_account;

	}

	public void setBalanceMainAccount( Integer balance_main_account ) {

		this.balance_main_account = balance_main_account;

	}

	public Integer getQtyRecharge() {

		return this.qty_recharge;

	}

	public void setQtyRecharge( Integer qty_recharge ) {

		this.qty_recharge = qty_recharge;

	}

	public Float getAmountRecharge() {

		return this.amount_recharge;

	}

	public void setAmountRecharge( Float amount_recharge ) {

		this.amount_recharge = amount_recharge;

	}

	public Date getValidityDate() {

		return this.validity_date;

	}

	public void setValidityDate( Date validity_date ) {

		this.validity_date = validity_date;

	}

	public Date getDeactivationDate() {

		return this.deactivation_date;

	}

	public void setDeactivationDate( Date deactivation_date ) {

		this.deactivation_date = deactivation_date;

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

	public Date getLastRechargeDate() {

		return this.last_recharge_date;

	}

	public void setLastRechargeDate( Date last_recharge_date ) {

		this.last_recharge_date = last_recharge_date;

	}

	public Integer getValidityDaysAtRecharge() {

		return this.validity_days_at_recharge;

	}

	public void setValidityDaysAtRecharge( Integer validity_days_at_recharge ) {

		this.validity_days_at_recharge = validity_days_at_recharge;

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
			.append( "\"amount_usage\": \"" ).append( this.getAmountUsage() ).append( "\", " )
			.append( "\"balance_main_account\": \"" ).append( this.getBalanceMainAccount() ).append( "\", " )
			.append( "\"qty_recharge\": \"" ).append( this.getQtyRecharge() ).append( "\", " )
			.append( "\"amount_recharge\": \"" ).append( this.getAmountRecharge() ).append( "\", " )
			.append( "\"validity_date\": \"" ).append( this.getValidityDate() ).append( "\", " )
			.append( "\"deactivation_date\": \"" ).append( this.getDeactivationDate() ).append( "\", " )
			.append( "\"qty_rate_plan_id_change\": \"" ).append( this.getQtyRatePlanIdChange() ).append( "\", " )
			.append( "\"last_rate_plan_id_change_date\": \"" ).append( this.getLastRatePlanIdChangeDate() ).append( "\", " )
			.append( "\"qty_status_id_change\": \"" ).append( this.getQtyStatusIdChange() ).append( "\", " )
			.append( "\"last_status_id_change_date\": \"" ).append( this.getLastStatusIdChangeDate() ).append( "\", " )
			.append( "\"last_recharge_date\": \"" ).append( this.getLastRechargeDate() ).append( "\", " )
			.append( "\"validity_days_at_recharge\": \"" ).append( this.getValidityDaysAtRecharge() ).append( "\", " )
			.append( "\"agg_date\": \"" ).append( this.getAggDate() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }