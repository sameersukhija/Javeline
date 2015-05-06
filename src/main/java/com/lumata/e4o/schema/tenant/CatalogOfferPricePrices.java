package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "catalog_offer_price_prices" )
public class CatalogOfferPricePrices { 

	public enum Fields { offer_price_id, currency_id, amount }

	@Column(
			table = "catalog_offer_price_prices",
			field = "offer_price_id",
			type = "smallint(5)",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 5,
			comment = "",
			getMethod = "getOfferPriceId",
			setMethod = "setOfferPriceId"
	)
	private Short offer_price_id;

	@Column(
			table = "catalog_offer_price_prices",
			field = "currency_id",
			type = "smallint(3) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			comment = "",
			getMethod = "getCurrencyId",
			setMethod = "setCurrencyId"
	)
	private Short currency_id;

	@Column(
			table = "catalog_offer_price_prices",
			field = "amount",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			comment = "",
			getMethod = "getAmount",
			setMethod = "setAmount"
	)
	private Integer amount;


	public CatalogOfferPricePrices() {} 

	public CatalogOfferPricePrices( ResultSet rs ) throws SQLException {

		this.offer_price_id = rs.getShort( CatalogOfferPricePrices.Fields.offer_price_id.name() );
		this.currency_id = rs.getShort( CatalogOfferPricePrices.Fields.currency_id.name() );
		this.amount = rs.getInt( CatalogOfferPricePrices.Fields.amount.name() );

	}

	public CatalogOfferPricePrices( JSONObject jo ) throws JSONException {

		this.offer_price_id = (short)jo.getInt( CatalogOfferPricePrices.Fields.offer_price_id.name() );
		this.currency_id = (short)jo.getInt( CatalogOfferPricePrices.Fields.currency_id.name() );
		this.amount = (int)jo.getInt( CatalogOfferPricePrices.Fields.amount.name() );

	}

	public Short getOfferPriceId() {

		return this.offer_price_id;

	}

	public CatalogOfferPricePrices setOfferPriceId( Short offer_price_id ) {

		this.offer_price_id = offer_price_id;

		return this;

	}

	public Short getCurrencyId() {

		return this.currency_id;

	}

	public CatalogOfferPricePrices setCurrencyId( Short currency_id ) {

		this.currency_id = currency_id;

		return this;

	}

	public Integer getAmount() {

		return this.amount;

	}

	public CatalogOfferPricePrices setAmount( Integer amount ) {

		this.amount = amount;

		return this;

	}

	public Fields[] getEntityFields() {

		return CatalogOfferPricePrices.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"offer_price_id\": \"" ).append( this.getOfferPriceId() ).append( "\", " )
			.append( "\"currency_id\": \"" ).append( this.getCurrencyId() ).append( "\", " )
			.append( "\"amount\": \"" ).append( this.getAmount() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }