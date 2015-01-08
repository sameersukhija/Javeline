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


@Table( "bonuses_all" )
public class BonusesAll { 

	public enum Fields { agg_date, period, profile_id, rate_plan_id, status_id, network_id, arpu_id, seniority_id, bonus_id, qty_msisdn, sum_qty_earned, sum_qty_used, sum_qty_expired }

	@Column(
			table = "bonuses_all",
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
			table = "bonuses_all",
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
			table = "bonuses_all",
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
			table = "bonuses_all",
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
			table = "bonuses_all",
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
			table = "bonuses_all",
			field = "network_id",
			type = "tinyint(4) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getNetworkId",
			setMethod = "setNetworkId"
	)
	private Byte network_id;

	@Column(
			table = "bonuses_all",
			field = "arpu_id",
			type = "tinyint(4) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getArpuId",
			setMethod = "setArpuId"
	)
	private Byte arpu_id;

	@Column(
			table = "bonuses_all",
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
			table = "bonuses_all",
			field = "bonus_id",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "0",
			extra = "",
			length = 5,
			comment = "",
			getMethod = "getBonusId",
			setMethod = "setBonusId"
	)
	private Short bonus_id;

	@Column(
			table = "bonuses_all",
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
			table = "bonuses_all",
			field = "sum_qty_earned",
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
			getMethod = "getSumQtyEarned",
			setMethod = "setSumQtyEarned"
	)
	private Float sum_qty_earned;

	@Column(
			table = "bonuses_all",
			field = "sum_qty_used",
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
			getMethod = "getSumQtyUsed",
			setMethod = "setSumQtyUsed"
	)
	private Float sum_qty_used;

	@Column(
			table = "bonuses_all",
			field = "sum_qty_expired",
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
			getMethod = "getSumQtyExpired",
			setMethod = "setSumQtyExpired"
	)
	private Float sum_qty_expired;


	public BonusesAll() {} 

	public BonusesAll( ResultSet rs ) throws SQLException {

		this.agg_date = rs.getDate( BonusesAll.Fields.agg_date.name() );
		this.period = rs.getString( BonusesAll.Fields.period.name() );
		this.profile_id = rs.getByte( BonusesAll.Fields.profile_id.name() );
		this.rate_plan_id = rs.getByte( BonusesAll.Fields.rate_plan_id.name() );
		this.status_id = rs.getByte( BonusesAll.Fields.status_id.name() );
		this.network_id = rs.getByte( BonusesAll.Fields.network_id.name() );
		this.arpu_id = rs.getByte( BonusesAll.Fields.arpu_id.name() );
		this.seniority_id = rs.getByte( BonusesAll.Fields.seniority_id.name() );
		this.bonus_id = rs.getShort( BonusesAll.Fields.bonus_id.name() );
		this.qty_msisdn = rs.getInt( BonusesAll.Fields.qty_msisdn.name() );
		this.sum_qty_earned = rs.getFloat( BonusesAll.Fields.sum_qty_earned.name() );
		this.sum_qty_used = rs.getFloat( BonusesAll.Fields.sum_qty_used.name() );
		this.sum_qty_expired = rs.getFloat( BonusesAll.Fields.sum_qty_expired.name() );

	}

	public BonusesAll( JSONObject jo ) throws JSONException, ParseException {

		this.agg_date = Format.getMysqlDateTime( jo.getString( BonusesAll.Fields.agg_date.name() ) );
		this.period = jo.getString( BonusesAll.Fields.period.name() );
		this.profile_id = (byte)jo.getInt( BonusesAll.Fields.profile_id.name() );
		this.rate_plan_id = (byte)jo.getInt( BonusesAll.Fields.rate_plan_id.name() );
		this.status_id = (byte)jo.getInt( BonusesAll.Fields.status_id.name() );
		this.network_id = (byte)jo.getInt( BonusesAll.Fields.network_id.name() );
		this.arpu_id = (byte)jo.getInt( BonusesAll.Fields.arpu_id.name() );
		this.seniority_id = (byte)jo.getInt( BonusesAll.Fields.seniority_id.name() );
		this.bonus_id = (short)jo.getInt( BonusesAll.Fields.bonus_id.name() );
		this.qty_msisdn = (int)jo.getInt( BonusesAll.Fields.qty_msisdn.name() );
		this.sum_qty_earned = (float)jo.getDouble( BonusesAll.Fields.sum_qty_earned.name() );
		this.sum_qty_used = (float)jo.getDouble( BonusesAll.Fields.sum_qty_used.name() );
		this.sum_qty_expired = (float)jo.getDouble( BonusesAll.Fields.sum_qty_expired.name() );

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

	public Short getBonusId() {

		return this.bonus_id;

	}

	public void setBonusId( Short bonus_id ) {

		this.bonus_id = bonus_id;

	}

	public Integer getQtyMsisdn() {

		return this.qty_msisdn;

	}

	public void setQtyMsisdn( Integer qty_msisdn ) {

		this.qty_msisdn = qty_msisdn;

	}

	public Float getSumQtyEarned() {

		return this.sum_qty_earned;

	}

	public void setSumQtyEarned( Float sum_qty_earned ) {

		this.sum_qty_earned = sum_qty_earned;

	}

	public Float getSumQtyUsed() {

		return this.sum_qty_used;

	}

	public void setSumQtyUsed( Float sum_qty_used ) {

		this.sum_qty_used = sum_qty_used;

	}

	public Float getSumQtyExpired() {

		return this.sum_qty_expired;

	}

	public void setSumQtyExpired( Float sum_qty_expired ) {

		this.sum_qty_expired = sum_qty_expired;

	}

	public Fields[] getEntityFields() {

		return BonusesAll.Fields.values();

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
			.append( "\"bonus_id\": \"" ).append( this.getBonusId() ).append( "\", " )
			.append( "\"qty_msisdn\": \"" ).append( this.getQtyMsisdn() ).append( "\", " )
			.append( "\"sum_qty_earned\": \"" ).append( this.getSumQtyEarned() ).append( "\", " )
			.append( "\"sum_qty_used\": \"" ).append( this.getSumQtyUsed() ).append( "\", " )
			.append( "\"sum_qty_expired\": \"" ).append( this.getSumQtyExpired() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }