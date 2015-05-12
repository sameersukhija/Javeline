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


@Table( "catalog_products" )
public class CatalogProducts { 

	public enum Fields { product_id, external_id, product_name, description, start_date, end_date, recommended_price, supplier_id, unitary_cost, url_image, agreement }

	@Column(
			table = "catalog_products",
			field = "product_id",
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
			getMethod = "getProductId",
			setMethod = "setProductId"
	)
	private Short product_id;

	@Column(
			table = "catalog_products",
			field = "external_id",
			type = "varchar(10)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 10,
			comment = "",
			getMethod = "getExternalId",
			setMethod = "setExternalId"
	)
	private String external_id;

	@Column(
			table = "catalog_products",
			field = "product_name",
			type = "varchar(45)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 45,
			comment = "",
			getMethod = "getProductName",
			setMethod = "setProductName"
	)
	private String product_name;

	@Column(
			table = "catalog_products",
			field = "description",
			type = "varchar(512)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 512,
			comment = "",
			getMethod = "getDescription",
			setMethod = "setDescription"
	)
	private String description;

	@Column(
			table = "catalog_products",
			field = "start_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "",
			getMethod = "getStartDate",
			setMethod = "setStartDate"
	)
	private Date start_date;

	@Column(
			table = "catalog_products",
			field = "end_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "",
			getMethod = "getEndDate",
			setMethod = "setEndDate"
	)
	private Date end_date;

	@Column(
			table = "catalog_products",
			field = "recommended_price",
			type = "float",
			mysqlType = "float",
			javaType = "Float",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getRecommendedPrice",
			setMethod = "setRecommendedPrice"
	)
	private Float recommended_price;

	@Column(
			table = "catalog_products",
			field = "supplier_id",
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
			getMethod = "getSupplierId",
			setMethod = "setSupplierId"
	)
	private Short supplier_id;

	@Column(
			table = "catalog_products",
			field = "unitary_cost",
			type = "float",
			mysqlType = "float",
			javaType = "Float",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getUnitaryCost",
			setMethod = "setUnitaryCost"
	)
	private Float unitary_cost;

	@Column(
			table = "catalog_products",
			field = "url_image",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 255,
			comment = "",
			getMethod = "getUrlImage",
			setMethod = "setUrlImage"
	)
	private String url_image;

	@Column(
			table = "catalog_products",
			field = "agreement",
			type = "text",
			mysqlType = "text",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "",
			getMethod = "getAgreement",
			setMethod = "setAgreement"
	)
	private String agreement;


	public CatalogProducts() {} 

	public CatalogProducts( ResultSet rs ) throws SQLException {

		this.product_id = rs.getShort( CatalogProducts.Fields.product_id.name() );
		this.external_id = rs.getString( CatalogProducts.Fields.external_id.name() );
		this.product_name = rs.getString( CatalogProducts.Fields.product_name.name() );
		this.description = rs.getString( CatalogProducts.Fields.description.name() );
		this.start_date = rs.getDate( CatalogProducts.Fields.start_date.name() );
		this.end_date = rs.getDate( CatalogProducts.Fields.end_date.name() );
		this.recommended_price = rs.getFloat( CatalogProducts.Fields.recommended_price.name() );
		this.supplier_id = rs.getShort( CatalogProducts.Fields.supplier_id.name() );
		this.unitary_cost = rs.getFloat( CatalogProducts.Fields.unitary_cost.name() );
		this.url_image = rs.getString( CatalogProducts.Fields.url_image.name() );
		this.agreement = rs.getString( CatalogProducts.Fields.agreement.name() );

	}

	public CatalogProducts( JSONObject jo ) throws JSONException, ParseException {

		this.product_id = (short)jo.getInt( CatalogProducts.Fields.product_id.name() );
		this.external_id = jo.getString( CatalogProducts.Fields.external_id.name() );
		this.product_name = jo.getString( CatalogProducts.Fields.product_name.name() );
		this.description = jo.getString( CatalogProducts.Fields.description.name() );
		this.start_date = Format.getMysqlDateTime( jo.getString( CatalogProducts.Fields.start_date.name() ) );
		this.end_date = Format.getMysqlDateTime( jo.getString( CatalogProducts.Fields.end_date.name() ) );
		this.recommended_price = (float)jo.getDouble( CatalogProducts.Fields.recommended_price.name() );
		this.supplier_id = (short)jo.getInt( CatalogProducts.Fields.supplier_id.name() );
		this.unitary_cost = (float)jo.getDouble( CatalogProducts.Fields.unitary_cost.name() );
		this.url_image = jo.getString( CatalogProducts.Fields.url_image.name() );
		this.agreement = jo.getString( CatalogProducts.Fields.agreement.name() );

	}

	public Short getProductId() {

		return this.product_id;

	}

	public CatalogProducts setProductId( Short product_id ) {

		this.product_id = product_id;

		return this;

	}

	public String getExternalId() {

		return this.external_id;

	}

	public CatalogProducts setExternalId( String external_id ) {

		this.external_id = external_id;

		return this;

	}

	public String getProductName() {

		return this.product_name;

	}

	public CatalogProducts setProductName( String product_name ) {

		this.product_name = product_name;

		return this;

	}

	public String getDescription() {

		return this.description;

	}

	public CatalogProducts setDescription( String description ) {

		this.description = description;

		return this;

	}

	public Date getStartDate() {

		return this.start_date;

	}

	public CatalogProducts setStartDate( Date start_date ) {

		this.start_date = start_date;

		return this;

	}

	public Date getEndDate() {

		return this.end_date;

	}

	public CatalogProducts setEndDate( Date end_date ) {

		this.end_date = end_date;

		return this;

	}

	public Float getRecommendedPrice() {

		return this.recommended_price;

	}

	public CatalogProducts setRecommendedPrice( Float recommended_price ) {

		this.recommended_price = recommended_price;

		return this;

	}

	public Short getSupplierId() {

		return this.supplier_id;

	}

	public CatalogProducts setSupplierId( Short supplier_id ) {

		this.supplier_id = supplier_id;

		return this;

	}

	public Float getUnitaryCost() {

		return this.unitary_cost;

	}

	public CatalogProducts setUnitaryCost( Float unitary_cost ) {

		this.unitary_cost = unitary_cost;

		return this;

	}

	public String getUrlImage() {

		return this.url_image;

	}

	public CatalogProducts setUrlImage( String url_image ) {

		this.url_image = url_image;

		return this;

	}

	public String getAgreement() {

		return this.agreement;

	}

	public CatalogProducts setAgreement( String agreement ) {

		this.agreement = agreement;

		return this;

	}

	public Fields[] getEntityFields() {

		return CatalogProducts.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"product_id\": \"" ).append( this.getProductId() ).append( "\", " )
			.append( "\"external_id\": \"" ).append( this.getExternalId() ).append( "\", " )
			.append( "\"product_name\": \"" ).append( this.getProductName() ).append( "\", " )
			.append( "\"description\": \"" ).append( this.getDescription() ).append( "\", " )
			.append( "\"start_date\": \"" ).append( this.getStartDate() ).append( "\", " )
			.append( "\"end_date\": \"" ).append( this.getEndDate() ).append( "\", " )
			.append( "\"recommended_price\": \"" ).append( this.getRecommendedPrice() ).append( "\", " )
			.append( "\"supplier_id\": \"" ).append( this.getSupplierId() ).append( "\", " )
			.append( "\"unitary_cost\": \"" ).append( this.getUnitaryCost() ).append( "\", " )
			.append( "\"url_image\": \"" ).append( this.getUrlImage() ).append( "\", " )
			.append( "\"agreement\": \"" ).append( this.getAgreement() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }