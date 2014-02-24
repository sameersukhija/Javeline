package com.lumata.expression.operators.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "catalog_related_offers" )
public class CatalogRelatedOffers { 

	public enum Fields { offer_id, related_offer_id }

	@Column(
			table = "catalog_related_offers",
			field = "offer_id",
			type = "smallint(5)",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 5,
			getMethod = "getOfferId",
			setMethod = "setOfferId"
	)
	private Short offer_id;

	@Column(
			table = "catalog_related_offers",
			field = "related_offer_id",
			type = "smallint(5)",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 5,
			getMethod = "getRelatedOfferId",
			setMethod = "setRelatedOfferId"
	)
	private Short related_offer_id;


	public CatalogRelatedOffers() {} 

	public CatalogRelatedOffers( ResultSet rs ) throws SQLException {

		this.offer_id = rs.getShort( CatalogRelatedOffers.Fields.offer_id.name() );
		this.related_offer_id = rs.getShort( CatalogRelatedOffers.Fields.related_offer_id.name() );

	}

	public CatalogRelatedOffers( JSONObject jo ) throws JSONException {

		this.offer_id = (short)jo.getInt( CatalogRelatedOffers.Fields.offer_id.name() );
		this.related_offer_id = (short)jo.getInt( CatalogRelatedOffers.Fields.related_offer_id.name() );

	}

	public Short getOfferId() {

		return this.offer_id;

	}

	public void setOfferId( Short offer_id ) {

		this.offer_id = offer_id;

	}

	public Short getRelatedOfferId() {

		return this.related_offer_id;

	}

	public void setRelatedOfferId( Short related_offer_id ) {

		this.related_offer_id = related_offer_id;

	}

	public Fields[] getEntityFields() {

		return CatalogRelatedOffers.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"offer_id\": \"" ).append( this.getOfferId() ).append( "\", " )
			.append( "\"related_offer_id\": \"" ).append( this.getRelatedOfferId() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }