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


@Table( "overall_postpaid" )
public class OverallPostpaid { 

	public enum Fields { agg_date, period, profile_id, rate_plan_id, status_id, network_id, arpu_id, seniority_id, ucg, sum_amount_usage, qty_msisdn, qty_msisdn_with_activity }

	@Column(
			table = "overall_postpaid",
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
			table = "overall_postpaid",
			field = "period",
			type = "enum('day','week','month')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getPeriod",
			setMethod = "setPeriod"
	)
	private String period;

	@Column(
			table = "overall_postpaid",
			field = "profile_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getProfileId",
			setMethod = "setProfileId"
	)
	private Byte profile_id;

	@Column(
			table = "overall_postpaid",
			field = "rate_plan_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getRatePlanId",
			setMethod = "setRatePlanId"
	)
	private Byte rate_plan_id;

	@Column(
			table = "overall_postpaid",
			field = "status_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getStatusId",
			setMethod = "setStatusId"
	)
	private Byte status_id;

	@Column(
			table = "overall_postpaid",
			field = "network_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getNetworkId",
			setMethod = "setNetworkId"
	)
	private Byte network_id;

	@Column(
			table = "overall_postpaid",
			field = "arpu_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getArpuId",
			setMethod = "setArpuId"
	)
	private Byte arpu_id;

	@Column(
			table = "overall_postpaid",
			field = "seniority_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getSeniorityId",
			setMethod = "setSeniorityId"
	)
	private Byte seniority_id;

	@Column(
			table = "overall_postpaid",
			field = "ucg",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getUcg",
			setMethod = "setUcg"
	)
	private Byte ucg;

	@Column(
			table = "overall_postpaid",
			field = "sum_amount_usage",
			type = "float",
			mysqlType = "float",
			javaType = "Float",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getSumAmountUsage",
			setMethod = "setSumAmountUsage"
	)
	private Float sum_amount_usage;

	@Column(
			table = "overall_postpaid",
			field = "qty_msisdn",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getQtyMsisdn",
			setMethod = "setQtyMsisdn"
	)
	private Integer qty_msisdn;

	@Column(
			table = "overall_postpaid",
			field = "qty_msisdn_with_activity",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getQtyMsisdnWithActivity",
			setMethod = "setQtyMsisdnWithActivity"
	)
	private Integer qty_msisdn_with_activity;


	public OverallPostpaid() {} 

	public OverallPostpaid( ResultSet rs ) throws SQLException {

		this.agg_date = rs.getDate( OverallPostpaid.Fields.agg_date.name() );
		this.period = rs.getString( OverallPostpaid.Fields.period.name() );
		this.profile_id = rs.getByte( OverallPostpaid.Fields.profile_id.name() );
		this.rate_plan_id = rs.getByte( OverallPostpaid.Fields.rate_plan_id.name() );
		this.status_id = rs.getByte( OverallPostpaid.Fields.status_id.name() );
		this.network_id = rs.getByte( OverallPostpaid.Fields.network_id.name() );
		this.arpu_id = rs.getByte( OverallPostpaid.Fields.arpu_id.name() );
		this.seniority_id = rs.getByte( OverallPostpaid.Fields.seniority_id.name() );
		this.ucg = rs.getByte( OverallPostpaid.Fields.ucg.name() );
		this.sum_amount_usage = rs.getFloat( OverallPostpaid.Fields.sum_amount_usage.name() );
		this.qty_msisdn = rs.getInt( OverallPostpaid.Fields.qty_msisdn.name() );
		this.qty_msisdn_with_activity = rs.getInt( OverallPostpaid.Fields.qty_msisdn_with_activity.name() );

	}

	public OverallPostpaid( JSONObject jo ) throws JSONException, ParseException {

		this.agg_date = Format.getMysqlDateTime( jo.getString( OverallPostpaid.Fields.agg_date.name() ) );
		this.period = jo.getString( OverallPostpaid.Fields.period.name() );
		this.profile_id = (byte)jo.getInt( OverallPostpaid.Fields.profile_id.name() );
		this.rate_plan_id = (byte)jo.getInt( OverallPostpaid.Fields.rate_plan_id.name() );
		this.status_id = (byte)jo.getInt( OverallPostpaid.Fields.status_id.name() );
		this.network_id = (byte)jo.getInt( OverallPostpaid.Fields.network_id.name() );
		this.arpu_id = (byte)jo.getInt( OverallPostpaid.Fields.arpu_id.name() );
		this.seniority_id = (byte)jo.getInt( OverallPostpaid.Fields.seniority_id.name() );
		this.ucg = (byte)jo.getInt( OverallPostpaid.Fields.ucg.name() );
		this.sum_amount_usage = (float)jo.getDouble( OverallPostpaid.Fields.sum_amount_usage.name() );
		this.qty_msisdn = (int)jo.getInt( OverallPostpaid.Fields.qty_msisdn.name() );
		this.qty_msisdn_with_activity = (int)jo.getInt( OverallPostpaid.Fields.qty_msisdn_with_activity.name() );

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

	public Float getSumAmountUsage() {

		return this.sum_amount_usage;

	}

	public void setSumAmountUsage( Float sum_amount_usage ) {

		this.sum_amount_usage = sum_amount_usage;

	}

	public Integer getQtyMsisdn() {

		return this.qty_msisdn;

	}

	public void setQtyMsisdn( Integer qty_msisdn ) {

		this.qty_msisdn = qty_msisdn;

	}

	public Integer getQtyMsisdnWithActivity() {

		return this.qty_msisdn_with_activity;

	}

	public void setQtyMsisdnWithActivity( Integer qty_msisdn_with_activity ) {

		this.qty_msisdn_with_activity = qty_msisdn_with_activity;

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
			.append( "\"sum_amount_usage\": \"" ).append( this.getSumAmountUsage() ).append( "\", " )
			.append( "\"qty_msisdn\": \"" ).append( this.getQtyMsisdn() ).append( "\", " )
			.append( "\"qty_msisdn_with_activity\": \"" ).append( this.getQtyMsisdnWithActivity() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }