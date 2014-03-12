package com.lumata.expression.operators.testing.pojo.autogenerator;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.util.Date;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "stats_range" )
public class StatsRange { 

	public enum Fields { msisdn, agg_date, profile_id, arpu_id, seniority_id }

	@Column(
			table = "stats_range",
			field = "msisdn",
			type = "bigint(20)",
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
			table = "stats_range",
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
			table = "stats_range",
			field = "profile_id",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getProfileId",
			setMethod = "setProfileId"
	)
	private Byte profile_id;

	@Column(
			table = "stats_range",
			field = "arpu_id",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getArpuId",
			setMethod = "setArpuId"
	)
	private Byte arpu_id;

	@Column(
			table = "stats_range",
			field = "seniority_id",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getSeniorityId",
			setMethod = "setSeniorityId"
	)
	private Byte seniority_id;


	public StatsRange() {} 

	public StatsRange( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( StatsRange.Fields.msisdn.name() );
		this.agg_date = rs.getDate( StatsRange.Fields.agg_date.name() );
		this.profile_id = rs.getByte( StatsRange.Fields.profile_id.name() );
		this.arpu_id = rs.getByte( StatsRange.Fields.arpu_id.name() );
		this.seniority_id = rs.getByte( StatsRange.Fields.seniority_id.name() );

	}

	public StatsRange( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( StatsRange.Fields.msisdn.name() );
		this.agg_date = Format.getMysqlDateTime( jo.getString( StatsRange.Fields.agg_date.name() ) );
		this.profile_id = (byte)jo.getInt( StatsRange.Fields.profile_id.name() );
		this.arpu_id = (byte)jo.getInt( StatsRange.Fields.arpu_id.name() );
		this.seniority_id = (byte)jo.getInt( StatsRange.Fields.seniority_id.name() );

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

	public Byte getProfileId() {

		return this.profile_id;

	}

	public void setProfileId( Byte profile_id ) {

		this.profile_id = profile_id;

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

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"agg_date\": \"" ).append( this.getAggDate() ).append( "\", " )
			.append( "\"profile_id\": \"" ).append( this.getProfileId() ).append( "\", " )
			.append( "\"arpu_id\": \"" ).append( this.getArpuId() ).append( "\", " )
			.append( "\"seniority_id\": \"" ).append( this.getSeniorityId() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }