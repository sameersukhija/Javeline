package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "catalog_product_characteristics" )
public class CatalogProductCharacteristics { 

	public enum Fields { product_id, product_type_id, characteristic_values }

	@Column(
			table = "catalog_product_characteristics",
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
			table = "catalog_product_characteristics",
			field = "product_type_id",
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
			getMethod = "getProductTypeId",
			setMethod = "setProductTypeId"
	)
	private Short product_type_id;

	@Column(
			table = "catalog_product_characteristics",
			field = "characteristic_values",
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
			getMethod = "getCharacteristicValues",
			setMethod = "setCharacteristicValues"
	)
	private String characteristic_values;


	public CatalogProductCharacteristics() {} 

	public CatalogProductCharacteristics( ResultSet rs ) throws SQLException {

		this.product_id = rs.getShort( CatalogProductCharacteristics.Fields.product_id.name() );
		this.product_type_id = rs.getShort( CatalogProductCharacteristics.Fields.product_type_id.name() );
		this.characteristic_values = rs.getString( CatalogProductCharacteristics.Fields.characteristic_values.name() );

	}

	public CatalogProductCharacteristics( JSONObject jo ) throws JSONException {

		this.product_id = (short)jo.getInt( CatalogProductCharacteristics.Fields.product_id.name() );
		this.product_type_id = (short)jo.getInt( CatalogProductCharacteristics.Fields.product_type_id.name() );
		this.characteristic_values = jo.getString( CatalogProductCharacteristics.Fields.characteristic_values.name() );

	}

	public Short getProductId() {

		return this.product_id;

	}

	public CatalogProductCharacteristics setProductId( Short product_id ) {

		this.product_id = product_id;

		return this;

	}

	public Short getProductTypeId() {

		return this.product_type_id;

	}

	public CatalogProductCharacteristics setProductTypeId( Short product_type_id ) {

		this.product_type_id = product_type_id;

		return this;

	}

	public String getCharacteristicValues() {

		return this.characteristic_values;

	}

	public CatalogProductCharacteristics setCharacteristicValues( String characteristic_values ) {

		this.characteristic_values = characteristic_values;

		return this;

	}

	public Fields[] getEntityFields() {

		return CatalogProductCharacteristics.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"product_id\": \"" ).append( this.getProductId() ).append( "\", " )
			.append( "\"product_type_id\": \"" ).append( this.getProductTypeId() ).append( "\", " )
			.append( "\"characteristic_values\": \"" ).append( this.getCharacteristicValues() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }