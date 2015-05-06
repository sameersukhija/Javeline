package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "catalog_related_products" )
public class CatalogRelatedProducts { 

	public enum Fields { product_id, related_product_id }

	@Column(
			table = "catalog_related_products",
			field = "product_id",
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
			getMethod = "getProductId",
			setMethod = "setProductId"
	)
	private Short product_id;

	@Column(
			table = "catalog_related_products",
			field = "related_product_id",
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
			getMethod = "getRelatedProductId",
			setMethod = "setRelatedProductId"
	)
	private Short related_product_id;


	public CatalogRelatedProducts() {} 

	public CatalogRelatedProducts( ResultSet rs ) throws SQLException {

		this.product_id = rs.getShort( CatalogRelatedProducts.Fields.product_id.name() );
		this.related_product_id = rs.getShort( CatalogRelatedProducts.Fields.related_product_id.name() );

	}

	public CatalogRelatedProducts( JSONObject jo ) throws JSONException {

		this.product_id = (short)jo.getInt( CatalogRelatedProducts.Fields.product_id.name() );
		this.related_product_id = (short)jo.getInt( CatalogRelatedProducts.Fields.related_product_id.name() );

	}

	public Short getProductId() {

		return this.product_id;

	}

	public CatalogRelatedProducts setProductId( Short product_id ) {

		this.product_id = product_id;

		return this;

	}

	public Short getRelatedProductId() {

		return this.related_product_id;

	}

	public CatalogRelatedProducts setRelatedProductId( Short related_product_id ) {

		this.related_product_id = related_product_id;

		return this;

	}

	public Fields[] getEntityFields() {

		return CatalogRelatedProducts.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"product_id\": \"" ).append( this.getProductId() ).append( "\", " )
			.append( "\"related_product_id\": \"" ).append( this.getRelatedProductId() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }