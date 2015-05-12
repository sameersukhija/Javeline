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


@Table( "stats_subs_prepaid" )
public class StatsSubsPrepaid { 

	public enum Fields { msisdn, amount_usage, balance_main_account, qty_recharge, amount_recharge, validity_date, deactivation_date, qty_rate_plan_id_change, last_rate_plan_id_change_date, qty_status_id_change, last_status_id_change_date, last_recharge_date, validity_days_at_recharge, agg_date }

	@Column(
			table = "stats_subs_prepaid",
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
			table = "stats_subs_prepaid",
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
			table = "stats_subs_prepaid",
			field = "balance_main_account",
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
			getMethod = "getBalanceMainAccount",
			setMethod = "setBalanceMainAccount"
	)
	private Integer balance_main_account;

	@Column(
			table = "stats_subs_prepaid",
			field = "qty_recharge",
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
			getMethod = "getQtyRecharge",
			setMethod = "setQtyRecharge"
	)
	private Integer qty_recharge;

	@Column(
			table = "stats_subs_prepaid",
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
			table = "stats_subs_prepaid",
			field = "validity_date",
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
			getMethod = "getValidityDate",
			setMethod = "setValidityDate"
	)
	private Date validity_date;

	@Column(
			table = "stats_subs_prepaid",
			field = "deactivation_date",
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
			getMethod = "getDeactivationDate",
			setMethod = "setDeactivationDate"
	)
	private Date deactivation_date;

	@Column(
			table = "stats_subs_prepaid",
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
			table = "stats_subs_prepaid",
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
			table = "stats_subs_prepaid",
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
			table = "stats_subs_prepaid",
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
			table = "stats_subs_prepaid",
			field = "last_recharge_date",
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
			getMethod = "getLastRechargeDate",
			setMethod = "setLastRechargeDate"
	)
	private Date last_recharge_date;

	@Column(
			table = "stats_subs_prepaid",
			field = "validity_days_at_recharge",
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
			getMethod = "getValidityDaysAtRecharge",
			setMethod = "setValidityDaysAtRecharge"
	)
	private Integer validity_days_at_recharge;

	@Column(
			table = "stats_subs_prepaid",
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


	public StatsSubsPrepaid() {} 

	public StatsSubsPrepaid( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( StatsSubsPrepaid.Fields.msisdn.name() );
		this.amount_usage = rs.getFloat( StatsSubsPrepaid.Fields.amount_usage.name() );
		this.balance_main_account = rs.getInt( StatsSubsPrepaid.Fields.balance_main_account.name() );
		this.qty_recharge = rs.getInt( StatsSubsPrepaid.Fields.qty_recharge.name() );
		this.amount_recharge = rs.getFloat( StatsSubsPrepaid.Fields.amount_recharge.name() );
		this.validity_date = rs.getDate( StatsSubsPrepaid.Fields.validity_date.name() );
		this.deactivation_date = rs.getDate( StatsSubsPrepaid.Fields.deactivation_date.name() );
		this.qty_rate_plan_id_change = rs.getInt( StatsSubsPrepaid.Fields.qty_rate_plan_id_change.name() );
		this.last_rate_plan_id_change_date = rs.getDate( StatsSubsPrepaid.Fields.last_rate_plan_id_change_date.name() );
		this.qty_status_id_change = rs.getInt( StatsSubsPrepaid.Fields.qty_status_id_change.name() );
		this.last_status_id_change_date = rs.getDate( StatsSubsPrepaid.Fields.last_status_id_change_date.name() );
		this.last_recharge_date = rs.getDate( StatsSubsPrepaid.Fields.last_recharge_date.name() );
		this.validity_days_at_recharge = rs.getInt( StatsSubsPrepaid.Fields.validity_days_at_recharge.name() );
		this.agg_date = rs.getDate( StatsSubsPrepaid.Fields.agg_date.name() );

	}

	public StatsSubsPrepaid( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( StatsSubsPrepaid.Fields.msisdn.name() );
		this.amount_usage = (float)jo.getDouble( StatsSubsPrepaid.Fields.amount_usage.name() );
		this.balance_main_account = (int)jo.getInt( StatsSubsPrepaid.Fields.balance_main_account.name() );
		this.qty_recharge = (int)jo.getInt( StatsSubsPrepaid.Fields.qty_recharge.name() );
		this.amount_recharge = (float)jo.getDouble( StatsSubsPrepaid.Fields.amount_recharge.name() );
		this.validity_date = Format.getMysqlDateTime( jo.getString( StatsSubsPrepaid.Fields.validity_date.name() ) );
		this.deactivation_date = Format.getMysqlDateTime( jo.getString( StatsSubsPrepaid.Fields.deactivation_date.name() ) );
		this.qty_rate_plan_id_change = (int)jo.getInt( StatsSubsPrepaid.Fields.qty_rate_plan_id_change.name() );
		this.last_rate_plan_id_change_date = Format.getMysqlDateTime( jo.getString( StatsSubsPrepaid.Fields.last_rate_plan_id_change_date.name() ) );
		this.qty_status_id_change = (int)jo.getInt( StatsSubsPrepaid.Fields.qty_status_id_change.name() );
		this.last_status_id_change_date = Format.getMysqlDateTime( jo.getString( StatsSubsPrepaid.Fields.last_status_id_change_date.name() ) );
		this.last_recharge_date = Format.getMysqlDateTime( jo.getString( StatsSubsPrepaid.Fields.last_recharge_date.name() ) );
		this.validity_days_at_recharge = (int)jo.getInt( StatsSubsPrepaid.Fields.validity_days_at_recharge.name() );
		this.agg_date = Format.getMysqlDateTime( jo.getString( StatsSubsPrepaid.Fields.agg_date.name() ) );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public StatsSubsPrepaid setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

		return this;

	}

	public Float getAmountUsage() {

		return this.amount_usage;

	}

	public StatsSubsPrepaid setAmountUsage( Float amount_usage ) {

		this.amount_usage = amount_usage;

		return this;

	}

	public Integer getBalanceMainAccount() {

		return this.balance_main_account;

	}

	public StatsSubsPrepaid setBalanceMainAccount( Integer balance_main_account ) {

		this.balance_main_account = balance_main_account;

		return this;

	}

	public Integer getQtyRecharge() {

		return this.qty_recharge;

	}

	public StatsSubsPrepaid setQtyRecharge( Integer qty_recharge ) {

		this.qty_recharge = qty_recharge;

		return this;

	}

	public Float getAmountRecharge() {

		return this.amount_recharge;

	}

	public StatsSubsPrepaid setAmountRecharge( Float amount_recharge ) {

		this.amount_recharge = amount_recharge;

		return this;

	}

	public Date getValidityDate() {

		return this.validity_date;

	}

	public StatsSubsPrepaid setValidityDate( Date validity_date ) {

		this.validity_date = validity_date;

		return this;

	}

	public Date getDeactivationDate() {

		return this.deactivation_date;

	}

	public StatsSubsPrepaid setDeactivationDate( Date deactivation_date ) {

		this.deactivation_date = deactivation_date;

		return this;

	}

	public Integer getQtyRatePlanIdChange() {

		return this.qty_rate_plan_id_change;

	}

	public StatsSubsPrepaid setQtyRatePlanIdChange( Integer qty_rate_plan_id_change ) {

		this.qty_rate_plan_id_change = qty_rate_plan_id_change;

		return this;

	}

	public Date getLastRatePlanIdChangeDate() {

		return this.last_rate_plan_id_change_date;

	}

	public StatsSubsPrepaid setLastRatePlanIdChangeDate( Date last_rate_plan_id_change_date ) {

		this.last_rate_plan_id_change_date = last_rate_plan_id_change_date;

		return this;

	}

	public Integer getQtyStatusIdChange() {

		return this.qty_status_id_change;

	}

	public StatsSubsPrepaid setQtyStatusIdChange( Integer qty_status_id_change ) {

		this.qty_status_id_change = qty_status_id_change;

		return this;

	}

	public Date getLastStatusIdChangeDate() {

		return this.last_status_id_change_date;

	}

	public StatsSubsPrepaid setLastStatusIdChangeDate( Date last_status_id_change_date ) {

		this.last_status_id_change_date = last_status_id_change_date;

		return this;

	}

	public Date getLastRechargeDate() {

		return this.last_recharge_date;

	}

	public StatsSubsPrepaid setLastRechargeDate( Date last_recharge_date ) {

		this.last_recharge_date = last_recharge_date;

		return this;

	}

	public Integer getValidityDaysAtRecharge() {

		return this.validity_days_at_recharge;

	}

	public StatsSubsPrepaid setValidityDaysAtRecharge( Integer validity_days_at_recharge ) {

		this.validity_days_at_recharge = validity_days_at_recharge;

		return this;

	}

	public Date getAggDate() {

		return this.agg_date;

	}

	public StatsSubsPrepaid setAggDate( Date agg_date ) {

		this.agg_date = agg_date;

		return this;

	}

	public Fields[] getEntityFields() {

		return StatsSubsPrepaid.Fields.values();

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