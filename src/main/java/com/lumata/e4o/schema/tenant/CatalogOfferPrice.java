package com.lumata.e4o.schema.tenant;

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
			isAutoincrement = true,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 5,
			comment = "",
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
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 5,
			comment = "",
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
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "",
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

	public CatalogOfferPrice setOfferPriceId( Short offer_price_id ) {

		this.offer_price_id = offer_price_id;

		return this;

	}

	public Short getOfferId() {

		return this.offer_id;

	}

	public CatalogOfferPrice setOfferId( Short offer_id ) {

		this.offer_id = offer_id;

		return this;

	}

	public String getCriteria() {

		return this.criteria;

	}

	public CatalogOfferPrice setCriteria( String criteria ) {

		this.criteria = criteria;

		return this;

	}

	public Fields[] getEntityFields() {

		return CatalogOfferPrice.Fields.values();

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