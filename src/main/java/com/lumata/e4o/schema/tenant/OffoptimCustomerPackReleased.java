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


@Table( "offoptim_customer_pack_released" )
public class OffoptimCustomerPackReleased { 

	public enum Fields { released_customer_offer_pack_id, token_code, customer_id, discard_date }

	@Column(
			table = "offoptim_customer_pack_released",
			field = "released_customer_offer_pack_id",
			type = "int(10) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 10,
			comment = "",
			getMethod = "getReleasedCustomerOfferPackId",
			setMethod = "setReleasedCustomerOfferPackId"
	)
	private Integer released_customer_offer_pack_id;

	@Column(
			table = "offoptim_customer_pack_released",
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
			comment = "",
			getMethod = "getTokenCode",
			setMethod = "setTokenCode"
	)
	private String token_code;

	@Column(
			table = "offoptim_customer_pack_released",
			field = "customer_id",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 20,
			comment = "",
			getMethod = "getCustomerId",
			setMethod = "setCustomerId"
	)
	private Long customer_id;

	@Column(
			table = "offoptim_customer_pack_released",
			field = "discard_date",
			type = "datetime",
			mysqlType = "datetime",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "",
			getMethod = "getDiscardDate",
			setMethod = "setDiscardDate"
	)
	private Date discard_date;


	public OffoptimCustomerPackReleased() {} 

	public OffoptimCustomerPackReleased( ResultSet rs ) throws SQLException {

		this.released_customer_offer_pack_id = rs.getInt( OffoptimCustomerPackReleased.Fields.released_customer_offer_pack_id.name() );
		this.token_code = rs.getString( OffoptimCustomerPackReleased.Fields.token_code.name() );
		this.customer_id = rs.getLong( OffoptimCustomerPackReleased.Fields.customer_id.name() );
		this.discard_date = rs.getDate( OffoptimCustomerPackReleased.Fields.discard_date.name() );

	}

	public OffoptimCustomerPackReleased( JSONObject jo ) throws JSONException, ParseException {

		this.released_customer_offer_pack_id = (int)jo.getInt( OffoptimCustomerPackReleased.Fields.released_customer_offer_pack_id.name() );
		this.token_code = jo.getString( OffoptimCustomerPackReleased.Fields.token_code.name() );
		this.customer_id = (long)jo.getLong( OffoptimCustomerPackReleased.Fields.customer_id.name() );
		this.discard_date = Format.getMysqlDateTime( jo.getString( OffoptimCustomerPackReleased.Fields.discard_date.name() ) );

	}

	public Integer getReleasedCustomerOfferPackId() {

		return this.released_customer_offer_pack_id;

	}

	public OffoptimCustomerPackReleased setReleasedCustomerOfferPackId( Integer released_customer_offer_pack_id ) {

		this.released_customer_offer_pack_id = released_customer_offer_pack_id;

		return this;

	}

	public String getTokenCode() {

		return this.token_code;

	}

	public OffoptimCustomerPackReleased setTokenCode( String token_code ) {

		this.token_code = token_code;

		return this;

	}

	public Long getCustomerId() {

		return this.customer_id;

	}

	public OffoptimCustomerPackReleased setCustomerId( Long customer_id ) {

		this.customer_id = customer_id;

		return this;

	}

	public Date getDiscardDate() {

		return this.discard_date;

	}

	public OffoptimCustomerPackReleased setDiscardDate( Date discard_date ) {

		this.discard_date = discard_date;

		return this;

	}

	public Fields[] getEntityFields() {

		return OffoptimCustomerPackReleased.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"released_customer_offer_pack_id\": \"" ).append( this.getReleasedCustomerOfferPackId() ).append( "\", " )
			.append( "\"token_code\": \"" ).append( this.getTokenCode() ).append( "\", " )
			.append( "\"customer_id\": \"" ).append( this.getCustomerId() ).append( "\", " )
			.append( "\"discard_date\": \"" ).append( this.getDiscardDate() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }