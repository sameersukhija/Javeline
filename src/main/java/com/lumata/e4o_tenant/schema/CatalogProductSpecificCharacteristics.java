package com.lumata.e4o_tenant.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "catalog_product_specific_characteristics" )
public class CatalogProductSpecificCharacteristics { 

	public enum Fields { product_id, characteristic_name, characteristic_details }

	@Column(
			table = "catalog_product_specific_characteristics",
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
			getMethod = "getProductId",
			setMethod = "setProductId"
	)
	private Short product_id;

	@Column(
			table = "catalog_product_specific_characteristics",
			field = "characteristic_name",
			type = "varchar(45)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 45,
			getMethod = "getCharacteristicName",
			setMethod = "setCharacteristicName"
	)
	private String characteristic_name;

	@Column(
			table = "catalog_product_specific_characteristics",
			field = "characteristic_details",
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
			getMethod = "getCharacteristicDetails",
			setMethod = "setCharacteristicDetails"
	)
	private String characteristic_details;


	public CatalogProductSpecificCharacteristics() {} 

	public CatalogProductSpecificCharacteristics( ResultSet rs ) throws SQLException {

		this.product_id = rs.getShort( CatalogProductSpecificCharacteristics.Fields.product_id.name() );
		this.characteristic_name = rs.getString( CatalogProductSpecificCharacteristics.Fields.characteristic_name.name() );
		this.characteristic_details = rs.getString( CatalogProductSpecificCharacteristics.Fields.characteristic_details.name() );

	}

	public CatalogProductSpecificCharacteristics( JSONObject jo ) throws JSONException {

		this.product_id = (short)jo.getInt( CatalogProductSpecificCharacteristics.Fields.product_id.name() );
		this.characteristic_name = jo.getString( CatalogProductSpecificCharacteristics.Fields.characteristic_name.name() );
		this.characteristic_details = jo.getString( CatalogProductSpecificCharacteristics.Fields.characteristic_details.name() );

	}

	public Short getProductId() {

		return this.product_id;

	}

	public void setProductId( Short product_id ) {

		this.product_id = product_id;

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

	public Fields[] getEntityFields() {

		return CatalogProductSpecificCharacteristics.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"product_id\": \"" ).append( this.getProductId() ).append( "\", " )
			.append( "\"characteristic_name\": \"" ).append( this.getCharacteristicName() ).append( "\", " )
			.append( "\"characteristic_details\": \"" ).append( this.getCharacteristicDetails() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }