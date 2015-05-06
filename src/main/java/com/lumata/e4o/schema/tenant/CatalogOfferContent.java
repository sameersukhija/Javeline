package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "catalog_offer_content" )
public class CatalogOfferContent { 

	public enum Fields { offer_id, content_type, content_id, quantity }

	@Column(
			table = "catalog_offer_content",
			field = "offer_id",
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
			getMethod = "getOfferId",
			setMethod = "setOfferId"
	)
	private Short offer_id;

	@Column(
			table = "catalog_offer_content",
			field = "content_type",
			type = "enum('OFFER','PRODUCT','PRODUCT_TYPE')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			comment = "",
			getMethod = "getContentType",
			setMethod = "setContentType"
	)
	private String content_type;

	@Column(
			table = "catalog_offer_content",
			field = "content_id",
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
			getMethod = "getContentId",
			setMethod = "setContentId"
	)
	private Short content_id;

	@Column(
			table = "catalog_offer_content",
			field = "quantity",
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
			getMethod = "getQuantity",
			setMethod = "setQuantity"
	)
	private Integer quantity;


	public CatalogOfferContent() {} 

	public CatalogOfferContent( ResultSet rs ) throws SQLException {

		this.offer_id = rs.getShort( CatalogOfferContent.Fields.offer_id.name() );
		this.content_type = rs.getString( CatalogOfferContent.Fields.content_type.name() );
		this.content_id = rs.getShort( CatalogOfferContent.Fields.content_id.name() );
		this.quantity = rs.getInt( CatalogOfferContent.Fields.quantity.name() );

	}

	public CatalogOfferContent( JSONObject jo ) throws JSONException {

		this.offer_id = (short)jo.getInt( CatalogOfferContent.Fields.offer_id.name() );
		this.content_type = jo.getString( CatalogOfferContent.Fields.content_type.name() );
		this.content_id = (short)jo.getInt( CatalogOfferContent.Fields.content_id.name() );
		this.quantity = (int)jo.getInt( CatalogOfferContent.Fields.quantity.name() );

	}

	public Short getOfferId() {

		return this.offer_id;

	}

	public CatalogOfferContent setOfferId( Short offer_id ) {

		this.offer_id = offer_id;

		return this;

	}

	public String getContentType() {

		return this.content_type;

	}

	public CatalogOfferContent setContentType( String content_type ) {

		this.content_type = content_type;

		return this;

	}

	public Short getContentId() {

		return this.content_id;

	}

	public CatalogOfferContent setContentId( Short content_id ) {

		this.content_id = content_id;

		return this;

	}

	public Integer getQuantity() {

		return this.quantity;

	}

	public CatalogOfferContent setQuantity( Integer quantity ) {

		this.quantity = quantity;

		return this;

	}

	public Fields[] getEntityFields() {

		return CatalogOfferContent.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"offer_id\": \"" ).append( this.getOfferId() ).append( "\", " )
			.append( "\"content_type\": \"" ).append( this.getContentType() ).append( "\", " )
			.append( "\"content_id\": \"" ).append( this.getContentId() ).append( "\", " )
			.append( "\"quantity\": \"" ).append( this.getQuantity() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }