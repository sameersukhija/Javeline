package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "offoptim_customer_pack" )
public class OffoptimCustomerPack { 

	public enum Fields { customer_offer_pack_id, token_code, customer_id }

	@Column(
			table = "offoptim_customer_pack",
			field = "customer_offer_pack_id",
			type = "int(10) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = true,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 10,
			getMethod = "getCustomerOfferPackId",
			setMethod = "setCustomerOfferPackId"
	)
	private Integer customer_offer_pack_id;

	@Column(
			table = "offoptim_customer_pack",
			field = "token_code",
			type = "varchar(60)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 60,
			getMethod = "getTokenCode",
			setMethod = "setTokenCode"
	)
	private String token_code;

	@Column(
			table = "offoptim_customer_pack",
			field = "customer_id",
			type = "bigint(20)",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getCustomerId",
			setMethod = "setCustomerId"
	)
	private Long customer_id;


	public OffoptimCustomerPack() {} 

	public OffoptimCustomerPack( ResultSet rs ) throws SQLException {

		this.customer_offer_pack_id = rs.getInt( OffoptimCustomerPack.Fields.customer_offer_pack_id.name() );
		this.token_code = rs.getString( OffoptimCustomerPack.Fields.token_code.name() );
		this.customer_id = rs.getLong( OffoptimCustomerPack.Fields.customer_id.name() );

	}

	public OffoptimCustomerPack( JSONObject jo ) throws JSONException {

		this.customer_offer_pack_id = (int)jo.getInt( OffoptimCustomerPack.Fields.customer_offer_pack_id.name() );
		this.token_code = jo.getString( OffoptimCustomerPack.Fields.token_code.name() );
		this.customer_id = (long)jo.getLong( OffoptimCustomerPack.Fields.customer_id.name() );

	}

	public Integer getCustomerOfferPackId() {

		return this.customer_offer_pack_id;

	}

	public void setCustomerOfferPackId( Integer customer_offer_pack_id ) {

		this.customer_offer_pack_id = customer_offer_pack_id;

	}

	public String getTokenCode() {

		return this.token_code;

	}

	public void setTokenCode( String token_code ) {

		this.token_code = token_code;

	}

	public Long getCustomerId() {

		return this.customer_id;

	}

	public void setCustomerId( Long customer_id ) {

		this.customer_id = customer_id;

	}

	public Fields[] getEntityFields() {

		return OffoptimCustomerPack.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"customer_offer_pack_id\": \"" ).append( this.getCustomerOfferPackId() ).append( "\", " )
			.append( "\"token_code\": \"" ).append( this.getTokenCode() ).append( "\", " )
			.append( "\"customer_id\": \"" ).append( this.getCustomerId() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }