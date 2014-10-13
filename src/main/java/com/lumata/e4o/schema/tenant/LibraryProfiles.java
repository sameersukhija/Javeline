package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "library_profiles" )
public class LibraryProfiles { 

	public enum Fields { profile_id, profile }

	@Column(
			table = "library_profiles",
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
			table = "library_profiles",
			field = "profile",
			type = "varchar(20)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getProfile",
			setMethod = "setProfile"
	)
	private String profile;


	public LibraryProfiles() {} 

	public LibraryProfiles( ResultSet rs ) throws SQLException {

		this.profile_id = rs.getByte( LibraryProfiles.Fields.profile_id.name() );
		this.profile = rs.getString( LibraryProfiles.Fields.profile.name() );

	}

	public LibraryProfiles( JSONObject jo ) throws JSONException {

		this.profile_id = (byte)jo.getInt( LibraryProfiles.Fields.profile_id.name() );
		this.profile = jo.getString( LibraryProfiles.Fields.profile.name() );

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

	public Fields[] getEntityFields() {

		return LibraryProfiles.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"profile_id\": \"" ).append( this.getProfileId() ).append( "\", " )
			.append( "\"profile\": \"" ).append( this.getProfile() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }