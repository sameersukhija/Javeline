package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "loyalty_badges_types" )
public class LoyaltyBadgesTypes { 

	public enum Fields { badge_type_id, program_id, badge_type_name }

	@Column(
			table = "loyalty_badges_types",
			field = "badge_type_id",
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
			getMethod = "getBadgeTypeId",
			setMethod = "setBadgeTypeId"
	)
	private Byte badge_type_id;

	@Column(
			table = "loyalty_badges_types",
			field = "program_id",
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
			getMethod = "getProgramId",
			setMethod = "setProgramId"
	)
	private Byte program_id;

	@Column(
			table = "loyalty_badges_types",
			field = "badge_type_name",
			type = "varchar(20)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 20,
			comment = "",
			getMethod = "getBadgeTypeName",
			setMethod = "setBadgeTypeName"
	)
	private String badge_type_name;


	public LoyaltyBadgesTypes() {} 

	public LoyaltyBadgesTypes( ResultSet rs ) throws SQLException {

		this.badge_type_id = rs.getByte( LoyaltyBadgesTypes.Fields.badge_type_id.name() );
		this.program_id = rs.getByte( LoyaltyBadgesTypes.Fields.program_id.name() );
		this.badge_type_name = rs.getString( LoyaltyBadgesTypes.Fields.badge_type_name.name() );

	}

	public LoyaltyBadgesTypes( JSONObject jo ) throws JSONException {

		this.badge_type_id = (byte)jo.getInt( LoyaltyBadgesTypes.Fields.badge_type_id.name() );
		this.program_id = (byte)jo.getInt( LoyaltyBadgesTypes.Fields.program_id.name() );
		this.badge_type_name = jo.getString( LoyaltyBadgesTypes.Fields.badge_type_name.name() );

	}

	public Byte getBadgeTypeId() {

		return this.badge_type_id;

	}

	public LoyaltyBadgesTypes setBadgeTypeId( Byte badge_type_id ) {

		this.badge_type_id = badge_type_id;

		return this;

	}

	public Byte getProgramId() {

		return this.program_id;

	}

	public LoyaltyBadgesTypes setProgramId( Byte program_id ) {

		this.program_id = program_id;

		return this;

	}

	public String getBadgeTypeName() {

		return this.badge_type_name;

	}

	public LoyaltyBadgesTypes setBadgeTypeName( String badge_type_name ) {

		this.badge_type_name = badge_type_name;

		return this;

	}

	public Fields[] getEntityFields() {

		return LoyaltyBadgesTypes.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"badge_type_id\": \"" ).append( this.getBadgeTypeId() ).append( "\", " )
			.append( "\"program_id\": \"" ).append( this.getProgramId() ).append( "\", " )
			.append( "\"badge_type_name\": \"" ).append( this.getBadgeTypeName() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }