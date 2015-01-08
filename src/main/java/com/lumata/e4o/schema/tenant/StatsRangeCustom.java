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


@Table( "stats_range_custom" )
public class StatsRangeCustom { 

	public enum Fields { msisdn, agg_date, var_name, profile_id, range_id }

	@Column(
			table = "stats_range_custom",
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
			table = "stats_range_custom",
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
			table = "stats_range_custom",
			field = "var_name",
			type = "tinyint(4)",
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
			getMethod = "getVarName",
			setMethod = "setVarName"
	)
	private Byte var_name;

	@Column(
			table = "stats_range_custom",
			field = "profile_id",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getProfileId",
			setMethod = "setProfileId"
	)
	private Byte profile_id;

	@Column(
			table = "stats_range_custom",
			field = "range_id",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getRangeId",
			setMethod = "setRangeId"
	)
	private Byte range_id;


	public StatsRangeCustom() {} 

	public StatsRangeCustom( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( StatsRangeCustom.Fields.msisdn.name() );
		this.agg_date = rs.getDate( StatsRangeCustom.Fields.agg_date.name() );
		this.var_name = rs.getByte( StatsRangeCustom.Fields.var_name.name() );
		this.profile_id = rs.getByte( StatsRangeCustom.Fields.profile_id.name() );
		this.range_id = rs.getByte( StatsRangeCustom.Fields.range_id.name() );

	}

	public StatsRangeCustom( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( StatsRangeCustom.Fields.msisdn.name() );
		this.agg_date = Format.getMysqlDateTime( jo.getString( StatsRangeCustom.Fields.agg_date.name() ) );
		this.var_name = (byte)jo.getInt( StatsRangeCustom.Fields.var_name.name() );
		this.profile_id = (byte)jo.getInt( StatsRangeCustom.Fields.profile_id.name() );
		this.range_id = (byte)jo.getInt( StatsRangeCustom.Fields.range_id.name() );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public void setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

	}

	public Date getAggDate() {

		return this.agg_date;

	}

	public void setAggDate( Date agg_date ) {

		this.agg_date = agg_date;

	}

	public Byte getVarName() {

		return this.var_name;

	}

	public void setVarName( Byte var_name ) {

		this.var_name = var_name;

	}

	public Byte getProfileId() {

		return this.profile_id;

	}

	public void setProfileId( Byte profile_id ) {

		this.profile_id = profile_id;

	}

	public Byte getRangeId() {

		return this.range_id;

	}

	public void setRangeId( Byte range_id ) {

		this.range_id = range_id;

	}

	public Fields[] getEntityFields() {

		return StatsRangeCustom.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"agg_date\": \"" ).append( this.getAggDate() ).append( "\", " )
			.append( "\"var_name\": \"" ).append( this.getVarName() ).append( "\", " )
			.append( "\"profile_id\": \"" ).append( this.getProfileId() ).append( "\", " )
			.append( "\"range_id\": \"" ).append( this.getRangeId() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }