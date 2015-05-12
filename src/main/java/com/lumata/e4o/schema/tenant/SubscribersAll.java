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


@Table( "subscribers_all" )
public class SubscribersAll { 

	public enum Fields { agg_date, period, profile_id, rate_plan_id, status_id, network_id, in_tag, qty_msisdn }

	@Column(
			table = "subscribers_all",
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
			table = "subscribers_all",
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
			table = "subscribers_all",
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
			table = "subscribers_all",
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
			table = "subscribers_all",
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
			table = "subscribers_all",
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
			table = "subscribers_all",
			field = "in_tag",
			type = "varchar(15)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 15,
			comment = "",
			getMethod = "getInTag",
			setMethod = "setInTag"
	)
	private String in_tag;

	@Column(
			table = "subscribers_all",
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


	public SubscribersAll() {} 

	public SubscribersAll( ResultSet rs ) throws SQLException {

		this.agg_date = rs.getDate( SubscribersAll.Fields.agg_date.name() );
		this.period = rs.getString( SubscribersAll.Fields.period.name() );
		this.profile_id = rs.getByte( SubscribersAll.Fields.profile_id.name() );
		this.rate_plan_id = rs.getByte( SubscribersAll.Fields.rate_plan_id.name() );
		this.status_id = rs.getByte( SubscribersAll.Fields.status_id.name() );
		this.network_id = rs.getByte( SubscribersAll.Fields.network_id.name() );
		this.in_tag = rs.getString( SubscribersAll.Fields.in_tag.name() );
		this.qty_msisdn = rs.getInt( SubscribersAll.Fields.qty_msisdn.name() );

	}

	public SubscribersAll( JSONObject jo ) throws JSONException, ParseException {

		this.agg_date = Format.getMysqlDateTime( jo.getString( SubscribersAll.Fields.agg_date.name() ) );
		this.period = jo.getString( SubscribersAll.Fields.period.name() );
		this.profile_id = (byte)jo.getInt( SubscribersAll.Fields.profile_id.name() );
		this.rate_plan_id = (byte)jo.getInt( SubscribersAll.Fields.rate_plan_id.name() );
		this.status_id = (byte)jo.getInt( SubscribersAll.Fields.status_id.name() );
		this.network_id = (byte)jo.getInt( SubscribersAll.Fields.network_id.name() );
		this.in_tag = jo.getString( SubscribersAll.Fields.in_tag.name() );
		this.qty_msisdn = (int)jo.getInt( SubscribersAll.Fields.qty_msisdn.name() );

	}

	public Date getAggDate() {

		return this.agg_date;

	}

	public SubscribersAll setAggDate( Date agg_date ) {

		this.agg_date = agg_date;

		return this;

	}

	public String getPeriod() {

		return this.period;

	}

	public SubscribersAll setPeriod( String period ) {

		this.period = period;

		return this;

	}

	public Byte getProfileId() {

		return this.profile_id;

	}

	public SubscribersAll setProfileId( Byte profile_id ) {

		this.profile_id = profile_id;

		return this;

	}

	public Byte getRatePlanId() {

		return this.rate_plan_id;

	}

	public SubscribersAll setRatePlanId( Byte rate_plan_id ) {

		this.rate_plan_id = rate_plan_id;

		return this;

	}

	public Byte getStatusId() {

		return this.status_id;

	}

	public SubscribersAll setStatusId( Byte status_id ) {

		this.status_id = status_id;

		return this;

	}

	public Byte getNetworkId() {

		return this.network_id;

	}

	public SubscribersAll setNetworkId( Byte network_id ) {

		this.network_id = network_id;

		return this;

	}

	public String getInTag() {

		return this.in_tag;

	}

	public SubscribersAll setInTag( String in_tag ) {

		this.in_tag = in_tag;

		return this;

	}

	public Integer getQtyMsisdn() {

		return this.qty_msisdn;

	}

	public SubscribersAll setQtyMsisdn( Integer qty_msisdn ) {

		this.qty_msisdn = qty_msisdn;

		return this;

	}

	public Fields[] getEntityFields() {

		return SubscribersAll.Fields.values();

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
			.append( "\"in_tag\": \"" ).append( this.getInTag() ).append( "\", " )
			.append( "\"qty_msisdn\": \"" ).append( this.getQtyMsisdn() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }