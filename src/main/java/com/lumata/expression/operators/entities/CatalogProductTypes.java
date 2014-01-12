package com.lumata.expression.operators.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "catalog_product_types" )
public class CatalogProductTypes { 

	public enum Fields { product_type_id, type_name, description }

	@Column(
			table = "catalog_product_types",
			field = "product_type_id",
			type = "smallint(5)",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 5,
			getMethod = "getProductTypeId",
			setMethod = "setProductTypeId"
	)
	private Short product_type_id;

	@Column(
			table = "catalog_product_types",
			field = "type_name",
			type = "varchar(45)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 45,
			getMethod = "getTypeName",
			setMethod = "setTypeName"
	)
	private String type_name;

	@Column(
			table = "catalog_product_types",
			field = "description",
			type = "varchar(512)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 512,
			getMethod = "getDescription",
			setMethod = "setDescription"
	)
	private String description;


	public CatalogProductTypes() {} 

	public CatalogProductTypes( ResultSet rs ) throws SQLException {

		this.product_type_id = rs.getShort( CatalogProductTypes.Fields.product_type_id.name() );
		this.type_name = rs.getString( CatalogProductTypes.Fields.type_name.name() );
		this.description = rs.getString( CatalogProductTypes.Fields.description.name() );

	}

	public CatalogProductTypes( JSONObject jo ) throws JSONException {

		this.product_type_id = (short)jo.getInt( CatalogProductTypes.Fields.product_type_id.name() );
		this.type_name = jo.getString( CatalogProductTypes.Fields.type_name.name() );
		this.description = jo.getString( CatalogProductTypes.Fields.description.name() );

	}

	public Short getProductTypeId() {

		return this.product_type_id;

	}

	public void setProductTypeId( Short product_type_id ) {

		this.product_type_id = product_type_id;

	}

	public String getTypeName() {

		return this.type_name;

	}

	public void setTypeName( String type_name ) {

		this.type_name = type_name;

	}

	public String getDescription() {

		return this.description;

	}

	public void setDescription( String description ) {

		this.description = description;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"product_type_id\": \"" ).append( this.getProductTypeId() ).append( "\", " )
			.append( "\"type_name\": \"" ).append( this.getTypeName() ).append( "\", " )
			.append( "\"description\": \"" ).append( this.getDescription() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }