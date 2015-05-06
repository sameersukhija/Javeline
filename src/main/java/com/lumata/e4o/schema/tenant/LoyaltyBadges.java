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


@Table( "loyalty_badges" )
public class LoyaltyBadges { 

	public enum Fields { badge_id, version_name, program_id, badge_name, badge_description, badge_type_id, badge_start_date, badge_end_date, badge_not_awarded_url, badge_awarded_url, badge_redeemed_url, badge_validity_duration_days, badge_status }

	@Column(
			table = "loyalty_badges",
			field = "badge_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = true,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 3,
			comment = "",
			getMethod = "getBadgeId",
			setMethod = "setBadgeId"
	)
	private Byte badge_id;

	@Column(
			table = "loyalty_badges",
			field = "version_name",
			type = "varchar(20)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 20,
			comment = "",
			getMethod = "getVersionName",
			setMethod = "setVersionName"
	)
	private String version_name;

	@Column(
			table = "loyalty_badges",
			field = "program_id",
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
			getMethod = "getProgramId",
			setMethod = "setProgramId"
	)
	private Byte program_id;

	@Column(
			table = "loyalty_badges",
			field = "badge_name",
			type = "varchar(64)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 64,
			comment = "",
			getMethod = "getBadgeName",
			setMethod = "setBadgeName"
	)
	private String badge_name;

	@Column(
			table = "loyalty_badges",
			field = "badge_description",
			type = "varchar(512)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 512,
			comment = "",
			getMethod = "getBadgeDescription",
			setMethod = "setBadgeDescription"
	)
	private String badge_description;

	@Column(
			table = "loyalty_badges",
			field = "badge_type_id",
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
			getMethod = "getBadgeTypeId",
			setMethod = "setBadgeTypeId"
	)
	private Byte badge_type_id;

	@Column(
			table = "loyalty_badges",
			field = "badge_start_date",
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
			getMethod = "getBadgeStartDate",
			setMethod = "setBadgeStartDate"
	)
	private Date badge_start_date;

	@Column(
			table = "loyalty_badges",
			field = "badge_end_date",
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
			getMethod = "getBadgeEndDate",
			setMethod = "setBadgeEndDate"
	)
	private Date badge_end_date;

	@Column(
			table = "loyalty_badges",
			field = "badge_not_awarded_url",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 255,
			comment = "",
			getMethod = "getBadgeNotAwardedUrl",
			setMethod = "setBadgeNotAwardedUrl"
	)
	private String badge_not_awarded_url;

	@Column(
			table = "loyalty_badges",
			field = "badge_awarded_url",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 255,
			comment = "",
			getMethod = "getBadgeAwardedUrl",
			setMethod = "setBadgeAwardedUrl"
	)
	private String badge_awarded_url;

	@Column(
			table = "loyalty_badges",
			field = "badge_redeemed_url",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 255,
			comment = "",
			getMethod = "getBadgeRedeemedUrl",
			setMethod = "setBadgeRedeemedUrl"
	)
	private String badge_redeemed_url;

	@Column(
			table = "loyalty_badges",
			field = "badge_validity_duration_days",
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
			getMethod = "getBadgeValidityDurationDays",
			setMethod = "setBadgeValidityDurationDays"
	)
	private Byte badge_validity_duration_days;

	@Column(
			table = "loyalty_badges",
			field = "badge_status",
			type = "enum('SAVED','ACTIVATED','SUSPENDED','EOL','PAUSED')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "SAVED",
			extra = "",
			length = 5,
			comment = "",
			getMethod = "getBadgeStatus",
			setMethod = "setBadgeStatus"
	)
	private String badge_status;


	public LoyaltyBadges() {} 

	public LoyaltyBadges( ResultSet rs ) throws SQLException {

		this.badge_id = rs.getByte( LoyaltyBadges.Fields.badge_id.name() );
		this.version_name = rs.getString( LoyaltyBadges.Fields.version_name.name() );
		this.program_id = rs.getByte( LoyaltyBadges.Fields.program_id.name() );
		this.badge_name = rs.getString( LoyaltyBadges.Fields.badge_name.name() );
		this.badge_description = rs.getString( LoyaltyBadges.Fields.badge_description.name() );
		this.badge_type_id = rs.getByte( LoyaltyBadges.Fields.badge_type_id.name() );
		this.badge_start_date = rs.getDate( LoyaltyBadges.Fields.badge_start_date.name() );
		this.badge_end_date = rs.getDate( LoyaltyBadges.Fields.badge_end_date.name() );
		this.badge_not_awarded_url = rs.getString( LoyaltyBadges.Fields.badge_not_awarded_url.name() );
		this.badge_awarded_url = rs.getString( LoyaltyBadges.Fields.badge_awarded_url.name() );
		this.badge_redeemed_url = rs.getString( LoyaltyBadges.Fields.badge_redeemed_url.name() );
		this.badge_validity_duration_days = rs.getByte( LoyaltyBadges.Fields.badge_validity_duration_days.name() );
		this.badge_status = rs.getString( LoyaltyBadges.Fields.badge_status.name() );

	}

	public LoyaltyBadges( JSONObject jo ) throws JSONException, ParseException {

		this.badge_id = (byte)jo.getInt( LoyaltyBadges.Fields.badge_id.name() );
		this.version_name = jo.getString( LoyaltyBadges.Fields.version_name.name() );
		this.program_id = (byte)jo.getInt( LoyaltyBadges.Fields.program_id.name() );
		this.badge_name = jo.getString( LoyaltyBadges.Fields.badge_name.name() );
		this.badge_description = jo.getString( LoyaltyBadges.Fields.badge_description.name() );
		this.badge_type_id = (byte)jo.getInt( LoyaltyBadges.Fields.badge_type_id.name() );
		this.badge_start_date = Format.getMysqlDateTime( jo.getString( LoyaltyBadges.Fields.badge_start_date.name() ) );
		this.badge_end_date = Format.getMysqlDateTime( jo.getString( LoyaltyBadges.Fields.badge_end_date.name() ) );
		this.badge_not_awarded_url = jo.getString( LoyaltyBadges.Fields.badge_not_awarded_url.name() );
		this.badge_awarded_url = jo.getString( LoyaltyBadges.Fields.badge_awarded_url.name() );
		this.badge_redeemed_url = jo.getString( LoyaltyBadges.Fields.badge_redeemed_url.name() );
		this.badge_validity_duration_days = (byte)jo.getInt( LoyaltyBadges.Fields.badge_validity_duration_days.name() );
		this.badge_status = jo.getString( LoyaltyBadges.Fields.badge_status.name() );

	}

	public Byte getBadgeId() {

		return this.badge_id;

	}

	public LoyaltyBadges setBadgeId( Byte badge_id ) {

		this.badge_id = badge_id;

		return this;

	}

	public String getVersionName() {

		return this.version_name;

	}

	public LoyaltyBadges setVersionName( String version_name ) {

		this.version_name = version_name;

		return this;

	}

	public Byte getProgramId() {

		return this.program_id;

	}

	public LoyaltyBadges setProgramId( Byte program_id ) {

		this.program_id = program_id;

		return this;

	}

	public String getBadgeName() {

		return this.badge_name;

	}

	public LoyaltyBadges setBadgeName( String badge_name ) {

		this.badge_name = badge_name;

		return this;

	}

	public String getBadgeDescription() {

		return this.badge_description;

	}

	public LoyaltyBadges setBadgeDescription( String badge_description ) {

		this.badge_description = badge_description;

		return this;

	}

	public Byte getBadgeTypeId() {

		return this.badge_type_id;

	}

	public LoyaltyBadges setBadgeTypeId( Byte badge_type_id ) {

		this.badge_type_id = badge_type_id;

		return this;

	}

	public Date getBadgeStartDate() {

		return this.badge_start_date;

	}

	public LoyaltyBadges setBadgeStartDate( Date badge_start_date ) {

		this.badge_start_date = badge_start_date;

		return this;

	}

	public Date getBadgeEndDate() {

		return this.badge_end_date;

	}

	public LoyaltyBadges setBadgeEndDate( Date badge_end_date ) {

		this.badge_end_date = badge_end_date;

		return this;

	}

	public String getBadgeNotAwardedUrl() {

		return this.badge_not_awarded_url;

	}

	public LoyaltyBadges setBadgeNotAwardedUrl( String badge_not_awarded_url ) {

		this.badge_not_awarded_url = badge_not_awarded_url;

		return this;

	}

	public String getBadgeAwardedUrl() {

		return this.badge_awarded_url;

	}

	public LoyaltyBadges setBadgeAwardedUrl( String badge_awarded_url ) {

		this.badge_awarded_url = badge_awarded_url;

		return this;

	}

	public String getBadgeRedeemedUrl() {

		return this.badge_redeemed_url;

	}

	public LoyaltyBadges setBadgeRedeemedUrl( String badge_redeemed_url ) {

		this.badge_redeemed_url = badge_redeemed_url;

		return this;

	}

	public Byte getBadgeValidityDurationDays() {

		return this.badge_validity_duration_days;

	}

	public LoyaltyBadges setBadgeValidityDurationDays( Byte badge_validity_duration_days ) {

		this.badge_validity_duration_days = badge_validity_duration_days;

		return this;

	}

	public String getBadgeStatus() {

		return this.badge_status;

	}

	public LoyaltyBadges setBadgeStatus( String badge_status ) {

		this.badge_status = badge_status;

		return this;

	}

	public Fields[] getEntityFields() {

		return LoyaltyBadges.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"badge_id\": \"" ).append( this.getBadgeId() ).append( "\", " )
			.append( "\"version_name\": \"" ).append( this.getVersionName() ).append( "\", " )
			.append( "\"program_id\": \"" ).append( this.getProgramId() ).append( "\", " )
			.append( "\"badge_name\": \"" ).append( this.getBadgeName() ).append( "\", " )
			.append( "\"badge_description\": \"" ).append( this.getBadgeDescription() ).append( "\", " )
			.append( "\"badge_type_id\": \"" ).append( this.getBadgeTypeId() ).append( "\", " )
			.append( "\"badge_start_date\": \"" ).append( this.getBadgeStartDate() ).append( "\", " )
			.append( "\"badge_end_date\": \"" ).append( this.getBadgeEndDate() ).append( "\", " )
			.append( "\"badge_not_awarded_url\": \"" ).append( this.getBadgeNotAwardedUrl() ).append( "\", " )
			.append( "\"badge_awarded_url\": \"" ).append( this.getBadgeAwardedUrl() ).append( "\", " )
			.append( "\"badge_redeemed_url\": \"" ).append( this.getBadgeRedeemedUrl() ).append( "\", " )
			.append( "\"badge_validity_duration_days\": \"" ).append( this.getBadgeValidityDurationDays() ).append( "\", " )
			.append( "\"badge_status\": \"" ).append( this.getBadgeStatus() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }