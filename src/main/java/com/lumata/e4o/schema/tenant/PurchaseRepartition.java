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


@Table( "purchase_repartition" )
public class PurchaseRepartition { 

	public enum Fields { agg_date, period, offer_id, channel_id, profile_id, rate_plan_id, status_id, network_id, arpu_id, seniority_id, qty_msisdn, sum_qty_purchased }

	@Column(
			table = "purchase_repartition",
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

	@Column(
			table = "purchase_repartition",
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
			getMethod = "getPeriod",
			setMethod = "setPeriod"
	)
	private String period;

	@Column(
			table = "purchase_repartition",
			field = "offer_id",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 5,
			getMethod = "getOfferId",
			setMethod = "setOfferId"
	)
	private Short offer_id;

	@Column(
			table = "purchase_repartition",
			field = "channel_id",
			type = "smallint(6)",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 6,
			getMethod = "getChannelId",
			setMethod = "setChannelId"
	)
	private Short channel_id;

	@Column(
			table = "purchase_repartition",
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
			getMethod = "getProfileId",
			setMethod = "setProfileId"
	)
	private Byte profile_id;

	@Column(
			table = "purchase_repartition",
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
			getMethod = "getRatePlanId",
			setMethod = "setRatePlanId"
	)
	private Byte rate_plan_id;

	@Column(
			table = "purchase_repartition",
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
			getMethod = "getStatusId",
			setMethod = "setStatusId"
	)
	private Byte status_id;

	@Column(
			table = "purchase_repartition",
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
			getMethod = "getNetworkId",
			setMethod = "setNetworkId"
	)
	private Byte network_id;

	@Column(
			table = "purchase_repartition",
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
			getMethod = "getArpuId",
			setMethod = "setArpuId"
	)
	private Byte arpu_id;

	@Column(
			table = "purchase_repartition",
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
			getMethod = "getSeniorityId",
			setMethod = "setSeniorityId"
	)
	private Byte seniority_id;

	@Column(
			table = "purchase_repartition",
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
			getMethod = "getQtyMsisdn",
			setMethod = "setQtyMsisdn"
	)
	private Integer qty_msisdn;

	@Column(
			table = "purchase_repartition",
			field = "sum_qty_purchased",
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
			getMethod = "getSumQtyPurchased",
			setMethod = "setSumQtyPurchased"
	)
	private Integer sum_qty_purchased;


	public PurchaseRepartition() {} 

	public PurchaseRepartition( ResultSet rs ) throws SQLException {

		this.agg_date = rs.getDate( PurchaseRepartition.Fields.agg_date.name() );
		this.period = rs.getString( PurchaseRepartition.Fields.period.name() );
		this.offer_id = rs.getShort( PurchaseRepartition.Fields.offer_id.name() );
		this.channel_id = rs.getShort( PurchaseRepartition.Fields.channel_id.name() );
		this.profile_id = rs.getByte( PurchaseRepartition.Fields.profile_id.name() );
		this.rate_plan_id = rs.getByte( PurchaseRepartition.Fields.rate_plan_id.name() );
		this.status_id = rs.getByte( PurchaseRepartition.Fields.status_id.name() );
		this.network_id = rs.getByte( PurchaseRepartition.Fields.network_id.name() );
		this.arpu_id = rs.getByte( PurchaseRepartition.Fields.arpu_id.name() );
		this.seniority_id = rs.getByte( PurchaseRepartition.Fields.seniority_id.name() );
		this.qty_msisdn = rs.getInt( PurchaseRepartition.Fields.qty_msisdn.name() );
		this.sum_qty_purchased = rs.getInt( PurchaseRepartition.Fields.sum_qty_purchased.name() );

	}

	public PurchaseRepartition( JSONObject jo ) throws JSONException, ParseException {

		this.agg_date = Format.getMysqlDateTime( jo.getString( PurchaseRepartition.Fields.agg_date.name() ) );
		this.period = jo.getString( PurchaseRepartition.Fields.period.name() );
		this.offer_id = (short)jo.getInt( PurchaseRepartition.Fields.offer_id.name() );
		this.channel_id = (short)jo.getInt( PurchaseRepartition.Fields.channel_id.name() );
		this.profile_id = (byte)jo.getInt( PurchaseRepartition.Fields.profile_id.name() );
		this.rate_plan_id = (byte)jo.getInt( PurchaseRepartition.Fields.rate_plan_id.name() );
		this.status_id = (byte)jo.getInt( PurchaseRepartition.Fields.status_id.name() );
		this.network_id = (byte)jo.getInt( PurchaseRepartition.Fields.network_id.name() );
		this.arpu_id = (byte)jo.getInt( PurchaseRepartition.Fields.arpu_id.name() );
		this.seniority_id = (byte)jo.getInt( PurchaseRepartition.Fields.seniority_id.name() );
		this.qty_msisdn = (int)jo.getInt( PurchaseRepartition.Fields.qty_msisdn.name() );
		this.sum_qty_purchased = (int)jo.getInt( PurchaseRepartition.Fields.sum_qty_purchased.name() );

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

	public Short getOfferId() {

		return this.offer_id;

	}

	public void setOfferId( Short offer_id ) {

		this.offer_id = offer_id;

	}

	public Short getChannelId() {

		return this.channel_id;

	}

	public void setChannelId( Short channel_id ) {

		this.channel_id = channel_id;

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

	public Integer getQtyMsisdn() {

		return this.qty_msisdn;

	}

	public void setQtyMsisdn( Integer qty_msisdn ) {

		this.qty_msisdn = qty_msisdn;

	}

	public Integer getSumQtyPurchased() {

		return this.sum_qty_purchased;

	}

	public void setSumQtyPurchased( Integer sum_qty_purchased ) {

		this.sum_qty_purchased = sum_qty_purchased;

	}

	public Fields[] getEntityFields() {

		return PurchaseRepartition.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"agg_date\": \"" ).append( this.getAggDate() ).append( "\", " )
			.append( "\"period\": \"" ).append( this.getPeriod() ).append( "\", " )
			.append( "\"offer_id\": \"" ).append( this.getOfferId() ).append( "\", " )
			.append( "\"channel_id\": \"" ).append( this.getChannelId() ).append( "\", " )
			.append( "\"profile_id\": \"" ).append( this.getProfileId() ).append( "\", " )
			.append( "\"rate_plan_id\": \"" ).append( this.getRatePlanId() ).append( "\", " )
			.append( "\"status_id\": \"" ).append( this.getStatusId() ).append( "\", " )
			.append( "\"network_id\": \"" ).append( this.getNetworkId() ).append( "\", " )
			.append( "\"arpu_id\": \"" ).append( this.getArpuId() ).append( "\", " )
			.append( "\"seniority_id\": \"" ).append( this.getSeniorityId() ).append( "\", " )
			.append( "\"qty_msisdn\": \"" ).append( this.getQtyMsisdn() ).append( "\", " )
			.append( "\"sum_qty_purchased\": \"" ).append( this.getSumQtyPurchased() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }