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


@Table( "daily_subs" )
public class DailySubs { 

	public enum Fields { msisdn, profile_id, rate_plan_id, status_id, network_id, ucg, agg_date, update_time }

	@Column(
			table = "daily_subs",
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
			table = "daily_subs",
			field = "profile_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			comment = "",
			getMethod = "getProfileId",
			setMethod = "setProfileId"
	)
	private Byte profile_id;

	@Column(
			table = "daily_subs",
			field = "rate_plan_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			comment = "",
			getMethod = "getRatePlanId",
			setMethod = "setRatePlanId"
	)
	private Byte rate_plan_id;

	@Column(
			table = "daily_subs",
			field = "status_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			comment = "",
			getMethod = "getStatusId",
			setMethod = "setStatusId"
	)
	private Byte status_id;

	@Column(
			table = "daily_subs",
			field = "network_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			comment = "",
			getMethod = "getNetworkId",
			setMethod = "setNetworkId"
	)
	private Byte network_id;

	@Column(
			table = "daily_subs",
			field = "ucg",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			comment = "",
			getMethod = "getUcg",
			setMethod = "setUcg"
	)
	private Byte ucg;

	@Column(
			table = "daily_subs",
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
			table = "daily_subs",
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


	public DailySubs() {} 

	public DailySubs( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( DailySubs.Fields.msisdn.name() );
		this.profile_id = rs.getByte( DailySubs.Fields.profile_id.name() );
		this.rate_plan_id = rs.getByte( DailySubs.Fields.rate_plan_id.name() );
		this.status_id = rs.getByte( DailySubs.Fields.status_id.name() );
		this.network_id = rs.getByte( DailySubs.Fields.network_id.name() );
		this.ucg = rs.getByte( DailySubs.Fields.ucg.name() );
		this.agg_date = rs.getDate( DailySubs.Fields.agg_date.name() );
		this.update_time = rs.getTimestamp( DailySubs.Fields.update_time.name() );

	}

	public DailySubs( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( DailySubs.Fields.msisdn.name() );
		this.profile_id = (byte)jo.getInt( DailySubs.Fields.profile_id.name() );
		this.rate_plan_id = (byte)jo.getInt( DailySubs.Fields.rate_plan_id.name() );
		this.status_id = (byte)jo.getInt( DailySubs.Fields.status_id.name() );
		this.network_id = (byte)jo.getInt( DailySubs.Fields.network_id.name() );
		this.ucg = (byte)jo.getInt( DailySubs.Fields.ucg.name() );
		this.agg_date = Format.getMysqlDateTime( jo.getString( DailySubs.Fields.agg_date.name() ) );
		this.update_time = new Timestamp( Format.getMysqlDateTime( jo.getString( DailySubs.Fields.update_time.name() ) ).getTime() );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public DailySubs setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

		return this;

	}

	public Byte getProfileId() {

		return this.profile_id;

	}

	public DailySubs setProfileId( Byte profile_id ) {

		this.profile_id = profile_id;

		return this;

	}

	public Byte getRatePlanId() {

		return this.rate_plan_id;

	}

	public DailySubs setRatePlanId( Byte rate_plan_id ) {

		this.rate_plan_id = rate_plan_id;

		return this;

	}

	public Byte getStatusId() {

		return this.status_id;

	}

	public DailySubs setStatusId( Byte status_id ) {

		this.status_id = status_id;

		return this;

	}

	public Byte getNetworkId() {

		return this.network_id;

	}

	public DailySubs setNetworkId( Byte network_id ) {

		this.network_id = network_id;

		return this;

	}

	public Byte getUcg() {

		return this.ucg;

	}

	public DailySubs setUcg( Byte ucg ) {

		this.ucg = ucg;

		return this;

	}

	public Date getAggDate() {

		return this.agg_date;

	}

	public DailySubs setAggDate( Date agg_date ) {

		this.agg_date = agg_date;

		return this;

	}

	public Timestamp getUpdateTime() {

		return this.update_time;

	}

	public DailySubs setUpdateTime( Timestamp update_time ) {

		this.update_time = update_time;

		return this;

	}

	public Fields[] getEntityFields() {

		return DailySubs.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"profile_id\": \"" ).append( this.getProfileId() ).append( "\", " )
			.append( "\"rate_plan_id\": \"" ).append( this.getRatePlanId() ).append( "\", " )
			.append( "\"status_id\": \"" ).append( this.getStatusId() ).append( "\", " )
			.append( "\"network_id\": \"" ).append( this.getNetworkId() ).append( "\", " )
			.append( "\"ucg\": \"" ).append( this.getUcg() ).append( "\", " )
			.append( "\"agg_date\": \"" ).append( this.getAggDate() ).append( "\", " )
			.append( "\"update_time\": \"" ).append( this.getUpdateTime() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }