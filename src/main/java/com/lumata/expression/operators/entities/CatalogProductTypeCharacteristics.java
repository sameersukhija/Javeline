package com.lumata.expression.operators.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "catalog_product_type_characteristics" )
public class CatalogProductTypeCharacteristics { 

	public enum Fields { product_type_id, characteristic_name, characteristic_details }

	@Column(
			table = "catalog_product_type_characteristics",
			field = "product_type_id",
			type = "smallint(5)",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 5,
			getMethod = "getProductTypeId",
			setMethod = "setProductTypeId"
	)
	private Short product_type_id;

	@Column(
			table = "catalog_product_type_characteristics",
			field = "characteristic_name",
			type = "varchar(45)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 45,
			getMethod = "getCharacteristicName",
			setMethod = "setCharacteristicName"
	)
	private String characteristic_name;

	@Column(
			table = "catalog_product_type_characteristics",
			field = "characteristic_details",
			type = "blob",
			mysqlType = "blob",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getCharacteristicDetails",
			setMethod = "setCharacteristicDetails"
	)
	private String characteristic_details;


	public CatalogProductTypeCharacteristics() {} 

	public CatalogProductTypeCharacteristics( ResultSet rs ) throws SQLException {

		this.product_type_id = rs.getShort( CatalogProductTypeCharacteristics.Fields.product_type_id.name() );
		this.characteristic_name = rs.getString( CatalogProductTypeCharacteristics.Fields.characteristic_name.name() );
		this.characteristic_details = rs.getString( CatalogProductTypeCharacteristics.Fields.characteristic_details.name() );

	}

	public CatalogProductTypeCharacteristics( JSONObject jo ) throws JSONException {

		this.product_type_id = (short)jo.getInt( CatalogProductTypeCharacteristics.Fields.product_type_id.name() );
		this.characteristic_name = jo.getString( CatalogProductTypeCharacteristics.Fields.characteristic_name.name() );
		this.characteristic_details = jo.getString( CatalogProductTypeCharacteristics.Fields.characteristic_details.name() );

	}

	public Short getProductTypeId() {

		return this.product_type_id;

	}

	public void setProductTypeId( Short product_type_id ) {

		this.product_type_id = product_type_id;

	}

	public String getCharacteristicName() {

		return this.characteristic_name;

	}

	public void setCharacteristicName( String characteristic_name ) {

		this.characteristic_name = characteristic_name;

	}

	public String getCharacteristicDetails() {

		return this.characteristic_details;

	}

	public void setCharacteristicDetails( String characteristic_details ) {

		this.characteristic_details = characteristic_details;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"product_type_id\": \"" ).append( this.getProductTypeId() ).append( "\", " )
			.append( "\"characteristic_name\": \"" ).append( this.getCharacteristicName() ).append( "\", " )
			.append( "\"characteristic_details\": \"" ).append( this.getCharacteristicDetails() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }