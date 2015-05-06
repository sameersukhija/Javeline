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


@Table( "subs_badges" )
public class SubsBadges { 

	public enum Fields { msisdn, program_id, badge_id, award_date, redeem_end_date, redeem_date, subs_status }

	@Column(
			table = "subs_badges",
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
			table = "subs_badges",
			field = "program_id",
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
			getMethod = "getProgramId",
			setMethod = "setProgramId"
	)
	private Byte program_id;

	@Column(
			table = "subs_badges",
			field = "badge_id",
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
			getMethod = "getBadgeId",
			setMethod = "setBadgeId"
	)
	private Byte badge_id;

	@Column(
			table = "subs_badges",
			field = "award_date",
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
			getMethod = "getAwardDate",
			setMethod = "setAwardDate"
	)
	private Date award_date;

	@Column(
			table = "subs_badges",
			field = "redeem_end_date",
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
			getMethod = "getRedeemEndDate",
			setMethod = "setRedeemEndDate"
	)
	private Date redeem_end_date;

	@Column(
			table = "subs_badges",
			field = "redeem_date",
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
			getMethod = "getRedeemDate",
			setMethod = "setRedeemDate"
	)
	private Date redeem_date;

	@Column(
			table = "subs_badges",
			field = "subs_status",
			type = "enum('AWARDED','REDEEMED','EXPIRED')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			comment = "",
			getMethod = "getSubsStatus",
			setMethod = "setSubsStatus"
	)
	private String subs_status;


	public SubsBadges() {} 

	public SubsBadges( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( SubsBadges.Fields.msisdn.name() );
		this.program_id = rs.getByte( SubsBadges.Fields.program_id.name() );
		this.badge_id = rs.getByte( SubsBadges.Fields.badge_id.name() );
		this.award_date = rs.getDate( SubsBadges.Fields.award_date.name() );
		this.redeem_end_date = rs.getDate( SubsBadges.Fields.redeem_end_date.name() );
		this.redeem_date = rs.getDate( SubsBadges.Fields.redeem_date.name() );
		this.subs_status = rs.getString( SubsBadges.Fields.subs_status.name() );

	}

	public SubsBadges( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( SubsBadges.Fields.msisdn.name() );
		this.program_id = (byte)jo.getInt( SubsBadges.Fields.program_id.name() );
		this.badge_id = (byte)jo.getInt( SubsBadges.Fields.badge_id.name() );
		this.award_date = Format.getMysqlDateTime( jo.getString( SubsBadges.Fields.award_date.name() ) );
		this.redeem_end_date = Format.getMysqlDateTime( jo.getString( SubsBadges.Fields.redeem_end_date.name() ) );
		this.redeem_date = Format.getMysqlDateTime( jo.getString( SubsBadges.Fields.redeem_date.name() ) );
		this.subs_status = jo.getString( SubsBadges.Fields.subs_status.name() );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public SubsBadges setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

		return this;

	}

	public Byte getProgramId() {

		return this.program_id;

	}

	public SubsBadges setProgramId( Byte program_id ) {

		this.program_id = program_id;

		return this;

	}

	public Byte getBadgeId() {

		return this.badge_id;

	}

	public SubsBadges setBadgeId( Byte badge_id ) {

		this.badge_id = badge_id;

		return this;

	}

	public Date getAwardDate() {

		return this.award_date;

	}

	public SubsBadges setAwardDate( Date award_date ) {

		this.award_date = award_date;

		return this;

	}

	public Date getRedeemEndDate() {

		return this.redeem_end_date;

	}

	public SubsBadges setRedeemEndDate( Date redeem_end_date ) {

		this.redeem_end_date = redeem_end_date;

		return this;

	}

	public Date getRedeemDate() {

		return this.redeem_date;

	}

	public SubsBadges setRedeemDate( Date redeem_date ) {

		this.redeem_date = redeem_date;

		return this;

	}

	public String getSubsStatus() {

		return this.subs_status;

	}

	public SubsBadges setSubsStatus( String subs_status ) {

		this.subs_status = subs_status;

		return this;

	}

	public Fields[] getEntityFields() {

		return SubsBadges.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"program_id\": \"" ).append( this.getProgramId() ).append( "\", " )
			.append( "\"badge_id\": \"" ).append( this.getBadgeId() ).append( "\", " )
			.append( "\"award_date\": \"" ).append( this.getAwardDate() ).append( "\", " )
			.append( "\"redeem_end_date\": \"" ).append( this.getRedeemEndDate() ).append( "\", " )
			.append( "\"redeem_date\": \"" ).append( this.getRedeemDate() ).append( "\", " )
			.append( "\"subs_status\": \"" ).append( this.getSubsStatus() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }