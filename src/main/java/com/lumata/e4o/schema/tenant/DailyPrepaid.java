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
import java.sql.Timestamp;


@Table( "daily_prepaid" )
public class DailyPrepaid { 

	public enum Fields { msisdn, amount_usage, balance_main_account, qty_recharge, amount_recharge, validity_date, deactivation_date, qty_rate_plan_id_change, qty_status_id_change, agg_date, update_time }

	@Column(
			table = "daily_prepaid",
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
			table = "daily_prepaid",
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
			table = "daily_prepaid",
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
			table = "daily_prepaid",
			field = "qty_recharge",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 5,
			comment = "",
			getMethod = "getQtyRecharge",
			setMethod = "setQtyRecharge"
	)
	private Short qty_recharge;

	@Column(
			table = "daily_prepaid",
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
			table = "daily_prepaid",
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
			table = "daily_prepaid",
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
			table = "daily_prepaid",
			field = "qty_rate_plan_id_change",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 5,
			comment = "",
			getMethod = "getQtyRatePlanIdChange",
			setMethod = "setQtyRatePlanIdChange"
	)
	private Short qty_rate_plan_id_change;

	@Column(
			table = "daily_prepaid",
			field = "qty_status_id_change",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 5,
			comment = "",
			getMethod = "getQtyStatusIdChange",
			setMethod = "setQtyStatusIdChange"
	)
	private Short qty_status_id_change;

	@Column(
			table = "daily_prepaid",
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

	@Column(
			table = "daily_prepaid",
			field = "update_time",
			type = "timestamp",
			mysqlType = "timestamp",
			javaType = "Timestamp",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "CURRENT_TIMESTAMP",
			extra = "on update CURRENT_TIMESTAMP",
			length = 0,
			comment = "",
			getMethod = "getUpdateTime",
			setMethod = "setUpdateTime"
	)
	private Timestamp update_time;


	public DailyPrepaid() {} 

	public DailyPrepaid( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( DailyPrepaid.Fields.msisdn.name() );
		this.amount_usage = rs.getInt( DailyPrepaid.Fields.amount_usage.name() );
		this.balance_main_account = rs.getInt( DailyPrepaid.Fields.balance_main_account.name() );
		this.qty_recharge = rs.getShort( DailyPrepaid.Fields.qty_recharge.name() );
		this.amount_recharge = rs.getInt( DailyPrepaid.Fields.amount_recharge.name() );
		this.validity_date = rs.getDate( DailyPrepaid.Fields.validity_date.name() );
		this.deactivation_date = rs.getDate( DailyPrepaid.Fields.deactivation_date.name() );
		this.qty_rate_plan_id_change = rs.getShort( DailyPrepaid.Fields.qty_rate_plan_id_change.name() );
		this.qty_status_id_change = rs.getShort( DailyPrepaid.Fields.qty_status_id_change.name() );
		this.agg_date = rs.getDate( DailyPrepaid.Fields.agg_date.name() );
		this.update_time = rs.getTimestamp( DailyPrepaid.Fields.update_time.name() );

	}

	public DailyPrepaid( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( DailyPrepaid.Fields.msisdn.name() );
		this.amount_usage = (int)jo.getInt( DailyPrepaid.Fields.amount_usage.name() );
		this.balance_main_account = (int)jo.getInt( DailyPrepaid.Fields.balance_main_account.name() );
		this.qty_recharge = (short)jo.getInt( DailyPrepaid.Fields.qty_recharge.name() );
		this.amount_recharge = (int)jo.getInt( DailyPrepaid.Fields.amount_recharge.name() );
		this.validity_date = Format.getMysqlDateTime( jo.getString( DailyPrepaid.Fields.validity_date.name() ) );
		this.deactivation_date = Format.getMysqlDateTime( jo.getString( DailyPrepaid.Fields.deactivation_date.name() ) );
		this.qty_rate_plan_id_change = (short)jo.getInt( DailyPrepaid.Fields.qty_rate_plan_id_change.name() );
		this.qty_status_id_change = (short)jo.getInt( DailyPrepaid.Fields.qty_status_id_change.name() );
		this.agg_date = Format.getMysqlDateTime( jo.getString( DailyPrepaid.Fields.agg_date.name() ) );
		this.update_time = new Timestamp( Format.getMysqlDateTime( jo.getString( DailyPrepaid.Fields.update_time.name() ) ).getTime() );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public DailyPrepaid setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

		return this;

	}

	public Integer getAmountUsage() {

		return this.amount_usage;

	}

	public DailyPrepaid setAmountUsage( Integer amount_usage ) {

		this.amount_usage = amount_usage;

		return this;

	}

	public Integer getBalanceMainAccount() {

		return this.balance_main_account;

	}

	public DailyPrepaid setBalanceMainAccount( Integer balance_main_account ) {

		this.balance_main_account = balance_main_account;

		return this;

	}

	public Short getQtyRecharge() {

		return this.qty_recharge;

	}

	public DailyPrepaid setQtyRecharge( Short qty_recharge ) {

		this.qty_recharge = qty_recharge;

		return this;

	}

	public Integer getAmountRecharge() {

		return this.amount_recharge;

	}

	public DailyPrepaid setAmountRecharge( Integer amount_recharge ) {

		this.amount_recharge = amount_recharge;

		return this;

	}

	public Date getValidityDate() {

		return this.validity_date;

	}

	public DailyPrepaid setValidityDate( Date validity_date ) {

		this.validity_date = validity_date;

		return this;

	}

	public Date getDeactivationDate() {

		return this.deactivation_date;

	}

	public DailyPrepaid setDeactivationDate( Date deactivation_date ) {

		this.deactivation_date = deactivation_date;

		return this;

	}

	public Short getQtyRatePlanIdChange() {

		return this.qty_rate_plan_id_change;

	}

	public DailyPrepaid setQtyRatePlanIdChange( Short qty_rate_plan_id_change ) {

		this.qty_rate_plan_id_change = qty_rate_plan_id_change;

		return this;

	}

	public Short getQtyStatusIdChange() {

		return this.qty_status_id_change;

	}

	public DailyPrepaid setQtyStatusIdChange( Short qty_status_id_change ) {

		this.qty_status_id_change = qty_status_id_change;

		return this;

	}

	public Date getAggDate() {

		return this.agg_date;

	}

	public DailyPrepaid setAggDate( Date agg_date ) {

		this.agg_date = agg_date;

		return this;

	}

	public Timestamp getUpdateTime() {

		return this.update_time;

	}

	public DailyPrepaid setUpdateTime( Timestamp update_time ) {

		this.update_time = update_time;

		return this;

	}

	public Fields[] getEntityFields() {

		return DailyPrepaid.Fields.values();

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
			.append( "\"qty_status_id_change\": \"" ).append( this.getQtyStatusIdChange() ).append( "\", " )
			.append( "\"agg_date\": \"" ).append( this.getAggDate() ).append( "\", " )
			.append( "\"update_time\": \"" ).append( this.getUpdateTime() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }