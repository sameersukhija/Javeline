package com.lumata.expression.operators.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "catalog_offer_price" )
public class CatalogOfferPrice { 

	public enum Fields { offer_price_id, offer_id, criteria }

	@Column(
			table = "catalog_offer_price",
			field = "offer_price_id",
			type = "smallint(5)",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 5,
			getMethod = "getOfferPriceId",
			setMethod = "setOfferPriceId"
	)
	private Short offer_price_id;

	@Column(
			table = "catalog_offer_price",
			field = "offer_id",
			type = "smallint(5)",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 5,
			getMethod = "getOfferId",
			setMethod = "setOfferId"
	)
	private Short offer_id;

	@Column(
			table = "catalog_offer_price",
			field = "criteria",
			type = "blob",
			mysqlType = "blob",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getCriteria",
			setMethod = "setCriteria"
	)
	private String criteria;


	public CatalogOfferPrice() {} 

	public CatalogOfferPrice( ResultSet rs ) throws SQLException {

		this.offer_price_id = rs.getShort( CatalogOfferPrice.Fields.offer_price_id.name() );
		this.offer_id = rs.getShort( CatalogOfferPrice.Fields.offer_id.name() );
		this.criteria = rs.getString( CatalogOfferPrice.Fields.criteria.name() );

	}

	public CatalogOfferPrice( JSONObject jo ) throws JSONException {

		this.offer_price_id = (short)jo.getInt( CatalogOfferPrice.Fields.offer_price_id.name() );
		this.offer_id = (short)jo.getInt( CatalogOfferPrice.Fields.offer_id.name() );
		this.criteria = jo.getString( CatalogOfferPrice.Fields.criteria.name() );

	}

	public Short getOfferPriceId() {

		return this.offer_price_id;

	}

	public void setOfferPriceId( Short offer_price_id ) {

		this.offer_price_id = offer_price_id;

	}

	public Short getOfferId() {

		return this.offer_id;

	}

	public void setOfferId( Short offer_id ) {

		this.offer_id = offer_id;

	}

	public String getCriteria() {

		return this.criteria;

	}

	public void setCriteria( String criteria ) {

		this.criteria = criteria;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"offer_price_id\": \"" ).append( this.getOfferPriceId() ).append( "\", " )
			.append( "\"offer_id\": \"" ).append( this.getOfferId() ).append( "\", " )
			.append( "\"criteria\": \"" ).append( this.getCriteria() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }