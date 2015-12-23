package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "id_buyer_profile" )
public class IdBuyerProfile { 

	public enum Fields { buyer_profile, buyer_profile_name }

	@Column(
			table = "id_buyer_profile",
			field = "buyer_profile",
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
			getMethod = "getBuyerProfile",
			setMethod = "setBuyerProfile"
	)
	private Long buyer_profile;

	@Column(
			table = "id_buyer_profile",
			field = "buyer_profile_name",
			type = "varchar(100)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 100,
			comment = "",
			getMethod = "getBuyerProfileName",
			setMethod = "setBuyerProfileName"
	)
	private String buyer_profile_name;


	public IdBuyerProfile() {} 

	public IdBuyerProfile( ResultSet rs ) throws SQLException {

		this.buyer_profile = rs.getLong( IdBuyerProfile.Fields.buyer_profile.name() );
		this.buyer_profile_name = rs.getString( IdBuyerProfile.Fields.buyer_profile_name.name() );

	}

	public IdBuyerProfile( JSONObject jo ) throws JSONException {

		this.buyer_profile = (long)jo.getLong( IdBuyerProfile.Fields.buyer_profile.name() );
		this.buyer_profile_name = jo.getString( IdBuyerProfile.Fields.buyer_profile_name.name() );

	}

	public Long getBuyerProfile() {

		return this.buyer_profile;

	}

	public IdBuyerProfile setBuyerProfile( Long buyer_profile ) {

		this.buyer_profile = buyer_profile;

		return this;

	}

	public String getBuyerProfileName() {

		return this.buyer_profile_name;

	}

	public IdBuyerProfile setBuyerProfileName( String buyer_profile_name ) {

		this.buyer_profile_name = buyer_profile_name;

		return this;

	}

	public Fields[] getEntityFields() {

		return IdBuyerProfile.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"buyer_profile\": \"" ).append( this.getBuyerProfile() ).append( "\", " )
			.append( "\"buyer_profile_name\": \"" ).append( this.getBuyerProfileName() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }