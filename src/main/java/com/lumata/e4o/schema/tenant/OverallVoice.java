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


@Table( "overall_voice" )
public class OverallVoice { 

	public enum Fields { agg_date, period, profile_id, rate_plan_id, status_id, network_id, arpu_id, seniority_id, ucg, sum_call_originating, sum_call_terminating, sum_amount_call, sum_duration_call, sum_amount_message, sum_sms, sum_vas, qty_msisdn, qty_msisdn_with_activity }

	@Column(
			table = "overall_voice",
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
			table = "overall_voice",
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
			comment = "",
			getMethod = "getPeriod",
			setMethod = "setPeriod"
	)
	private String period;

	@Column(
			table = "overall_voice",
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
			comment = "",
			getMethod = "getProfileId",
			setMethod = "setProfileId"
	)
	private Byte profile_id;

	@Column(
			table = "overall_voice",
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
			comment = "",
			getMethod = "getRatePlanId",
			setMethod = "setRatePlanId"
	)
	private Byte rate_plan_id;

	@Column(
			table = "overall_voice",
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
			comment = "",
			getMethod = "getStatusId",
			setMethod = "setStatusId"
	)
	private Byte status_id;

	@Column(
			table = "overall_voice",
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
			comment = "",
			getMethod = "getNetworkId",
			setMethod = "setNetworkId"
	)
	private Byte network_id;

	@Column(
			table = "overall_voice",
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
			comment = "",
			getMethod = "getArpuId",
			setMethod = "setArpuId"
	)
	private Byte arpu_id;

	@Column(
			table = "overall_voice",
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
			comment = "",
			getMethod = "getSeniorityId",
			setMethod = "setSeniorityId"
	)
	private Byte seniority_id;

	@Column(
			table = "overall_voice",
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
			comment = "",
			getMethod = "getUcg",
			setMethod = "setUcg"
	)
	private Byte ucg;

	@Column(
			table = "overall_voice",
			field = "sum_call_originating",
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
			getMethod = "getSumCallOriginating",
			setMethod = "setSumCallOriginating"
	)
	private Float sum_call_originating;

	@Column(
			table = "overall_voice",
			field = "sum_call_terminating",
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
			getMethod = "getSumCallTerminating",
			setMethod = "setSumCallTerminating"
	)
	private Float sum_call_terminating;

	@Column(
			table = "overall_voice",
			field = "sum_amount_call",
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
			getMethod = "getSumAmountCall",
			setMethod = "setSumAmountCall"
	)
	private Float sum_amount_call;

	@Column(
			table = "overall_voice",
			field = "sum_duration_call",
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
			getMethod = "getSumDurationCall",
			setMethod = "setSumDurationCall"
	)
	private Float sum_duration_call;

	@Column(
			table = "overall_voice",
			field = "sum_amount_message",
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
			getMethod = "getSumAmountMessage",
			setMethod = "setSumAmountMessage"
	)
	private Float sum_amount_message;

	@Column(
			table = "overall_voice",
			field = "sum_sms",
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
			getMethod = "getSumSms",
			setMethod = "setSumSms"
	)
	private Float sum_sms;

	@Column(
			table = "overall_voice",
			field = "sum_vas",
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
			getMethod = "getSumVas",
			setMethod = "setSumVas"
	)
	private Float sum_vas;

	@Column(
			table = "overall_voice",
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
			comment = "",
			getMethod = "getQtyMsisdn",
			setMethod = "setQtyMsisdn"
	)
	private Integer qty_msisdn;

	@Column(
			table = "overall_voice",
			field = "qty_msisdn_with_activity",
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
			getMethod = "getQtyMsisdnWithActivity",
			setMethod = "setQtyMsisdnWithActivity"
	)
	private Integer qty_msisdn_with_activity;


	public OverallVoice() {} 

	public OverallVoice( ResultSet rs ) throws SQLException {

		this.agg_date = rs.getDate( OverallVoice.Fields.agg_date.name() );
		this.period = rs.getString( OverallVoice.Fields.period.name() );
		this.profile_id = rs.getByte( OverallVoice.Fields.profile_id.name() );
		this.rate_plan_id = rs.getByte( OverallVoice.Fields.rate_plan_id.name() );
		this.status_id = rs.getByte( OverallVoice.Fields.status_id.name() );
		this.network_id = rs.getByte( OverallVoice.Fields.network_id.name() );
		this.arpu_id = rs.getByte( OverallVoice.Fields.arpu_id.name() );
		this.seniority_id = rs.getByte( OverallVoice.Fields.seniority_id.name() );
		this.ucg = rs.getByte( OverallVoice.Fields.ucg.name() );
		this.sum_call_originating = rs.getFloat( OverallVoice.Fields.sum_call_originating.name() );
		this.sum_call_terminating = rs.getFloat( OverallVoice.Fields.sum_call_terminating.name() );
		this.sum_amount_call = rs.getFloat( OverallVoice.Fields.sum_amount_call.name() );
		this.sum_duration_call = rs.getFloat( OverallVoice.Fields.sum_duration_call.name() );
		this.sum_amount_message = rs.getFloat( OverallVoice.Fields.sum_amount_message.name() );
		this.sum_sms = rs.getFloat( OverallVoice.Fields.sum_sms.name() );
		this.sum_vas = rs.getFloat( OverallVoice.Fields.sum_vas.name() );
		this.qty_msisdn = rs.getInt( OverallVoice.Fields.qty_msisdn.name() );
		this.qty_msisdn_with_activity = rs.getInt( OverallVoice.Fields.qty_msisdn_with_activity.name() );

	}

	public OverallVoice( JSONObject jo ) throws JSONException, ParseException {

		this.agg_date = Format.getMysqlDateTime( jo.getString( OverallVoice.Fields.agg_date.name() ) );
		this.period = jo.getString( OverallVoice.Fields.period.name() );
		this.profile_id = (byte)jo.getInt( OverallVoice.Fields.profile_id.name() );
		this.rate_plan_id = (byte)jo.getInt( OverallVoice.Fields.rate_plan_id.name() );
		this.status_id = (byte)jo.getInt( OverallVoice.Fields.status_id.name() );
		this.network_id = (byte)jo.getInt( OverallVoice.Fields.network_id.name() );
		this.arpu_id = (byte)jo.getInt( OverallVoice.Fields.arpu_id.name() );
		this.seniority_id = (byte)jo.getInt( OverallVoice.Fields.seniority_id.name() );
		this.ucg = (byte)jo.getInt( OverallVoice.Fields.ucg.name() );
		this.sum_call_originating = (float)jo.getDouble( OverallVoice.Fields.sum_call_originating.name() );
		this.sum_call_terminating = (float)jo.getDouble( OverallVoice.Fields.sum_call_terminating.name() );
		this.sum_amount_call = (float)jo.getDouble( OverallVoice.Fields.sum_amount_call.name() );
		this.sum_duration_call = (float)jo.getDouble( OverallVoice.Fields.sum_duration_call.name() );
		this.sum_amount_message = (float)jo.getDouble( OverallVoice.Fields.sum_amount_message.name() );
		this.sum_sms = (float)jo.getDouble( OverallVoice.Fields.sum_sms.name() );
		this.sum_vas = (float)jo.getDouble( OverallVoice.Fields.sum_vas.name() );
		this.qty_msisdn = (int)jo.getInt( OverallVoice.Fields.qty_msisdn.name() );
		this.qty_msisdn_with_activity = (int)jo.getInt( OverallVoice.Fields.qty_msisdn_with_activity.name() );

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

	public Float getSumCallOriginating() {

		return this.sum_call_originating;

	}

	public void setSumCallOriginating( Float sum_call_originating ) {

		this.sum_call_originating = sum_call_originating;

	}

	public Float getSumCallTerminating() {

		return this.sum_call_terminating;

	}

	public void setSumCallTerminating( Float sum_call_terminating ) {

		this.sum_call_terminating = sum_call_terminating;

	}

	public Float getSumAmountCall() {

		return this.sum_amount_call;

	}

	public void setSumAmountCall( Float sum_amount_call ) {

		this.sum_amount_call = sum_amount_call;

	}

	public Float getSumDurationCall() {

		return this.sum_duration_call;

	}

	public void setSumDurationCall( Float sum_duration_call ) {

		this.sum_duration_call = sum_duration_call;

	}

	public Float getSumAmountMessage() {

		return this.sum_amount_message;

	}

	public void setSumAmountMessage( Float sum_amount_message ) {

		this.sum_amount_message = sum_amount_message;

	}

	public Float getSumSms() {

		return this.sum_sms;

	}

	public void setSumSms( Float sum_sms ) {

		this.sum_sms = sum_sms;

	}

	public Float getSumVas() {

		return this.sum_vas;

	}

	public void setSumVas( Float sum_vas ) {

		this.sum_vas = sum_vas;

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

	public Fields[] getEntityFields() {

		return OverallVoice.Fields.values();

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
			.append( "\"sum_call_originating\": \"" ).append( this.getSumCallOriginating() ).append( "\", " )
			.append( "\"sum_call_terminating\": \"" ).append( this.getSumCallTerminating() ).append( "\", " )
			.append( "\"sum_amount_call\": \"" ).append( this.getSumAmountCall() ).append( "\", " )
			.append( "\"sum_duration_call\": \"" ).append( this.getSumDurationCall() ).append( "\", " )
			.append( "\"sum_amount_message\": \"" ).append( this.getSumAmountMessage() ).append( "\", " )
			.append( "\"sum_sms\": \"" ).append( this.getSumSms() ).append( "\", " )
			.append( "\"sum_vas\": \"" ).append( this.getSumVas() ).append( "\", " )
			.append( "\"qty_msisdn\": \"" ).append( this.getQtyMsisdn() ).append( "\", " )
			.append( "\"qty_msisdn_with_activity\": \"" ).append( this.getQtyMsisdnWithActivity() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }