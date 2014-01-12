package com.lumata.expression.operators.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "profiles" )
public class Profiles { 

	public enum Fields { profile_id, profile, default_status_id, status_prov_mode }

	@Column(
			table = "profiles",
			field = "profile_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 3,
			getMethod = "getProfileId",
			setMethod = "setProfileId"
	)
	private Byte profile_id;

	@Column(
			table = "profiles",
			field = "profile",
			type = "varchar(20)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getProfile",
			setMethod = "setProfile"
	)
	private String profile;

	@Column(
			table = "profiles",
			field = "default_status_id",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getDefaultStatusId",
			setMethod = "setDefaultStatusId"
	)
	private Byte default_status_id;

	@Column(
			table = "profiles",
			field = "status_prov_mode",
			type = "enum('CDR','Rules')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 2,
			getMethod = "getStatusProvMode",
			setMethod = "setStatusProvMode"
	)
	private String status_prov_mode;


	public Profiles() {} 

	public Profiles( ResultSet rs ) throws SQLException {

		this.profile_id = rs.getByte( Profiles.Fields.profile_id.name() );
		this.profile = rs.getString( Profiles.Fields.profile.name() );
		this.default_status_id = rs.getByte( Profiles.Fields.default_status_id.name() );
		this.status_prov_mode = rs.getString( Profiles.Fields.status_prov_mode.name() );

	}

	public Profiles( JSONObject jo ) throws JSONException {

		this.profile_id = (byte)jo.getInt( Profiles.Fields.profile_id.name() );
		this.profile = jo.getString( Profiles.Fields.profile.name() );
		this.default_status_id = (byte)jo.getInt( Profiles.Fields.default_status_id.name() );
		this.status_prov_mode = jo.getString( Profiles.Fields.status_prov_mode.name() );

	}

	public Byte getProfileId() {

		return this.profile_id;

	}

	public void setProfileId( Byte profile_id ) {

		this.profile_id = profile_id;

	}

	public String getProfile() {

		return this.profile;

	}

	public void setProfile( String profile ) {

		this.profile = profile;

	}

	public Byte getDefaultStatusId() {

		return this.default_status_id;

	}

	public void setDefaultStatusId( Byte default_status_id ) {

		this.default_status_id = default_status_id;

	}

	public String getStatusProvMode() {

		return this.status_prov_mode;

	}

	public void setStatusProvMode( String status_prov_mode ) {

		this.status_prov_mode = status_prov_mode;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"profile_id\": \"" ).append( this.getProfileId() ).append( "\", " )
			.append( "\"profile\": \"" ).append( this.getProfile() ).append( "\", " )
			.append( "\"default_status_id\": \"" ).append( this.getDefaultStatusId() ).append( "\", " )
			.append( "\"status_prov_mode\": \"" ).append( this.getStatusProvMode() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }