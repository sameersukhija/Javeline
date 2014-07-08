package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "catalog_labels" )
public class CatalogLabels { 

	public enum Fields { label_id, label_name }

	@Column(
			table = "catalog_labels",
			field = "label_id",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getLabelId",
			setMethod = "setLabelId"
	)
	private Integer label_id;

	@Column(
			table = "catalog_labels",
			field = "label_name",
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
			getMethod = "getLabelName",
			setMethod = "setLabelName"
	)
	private String label_name;


	public CatalogLabels() {} 

	public CatalogLabels( ResultSet rs ) throws SQLException {

		this.label_id = rs.getInt( CatalogLabels.Fields.label_id.name() );
		this.label_name = rs.getString( CatalogLabels.Fields.label_name.name() );

	}

	public CatalogLabels( JSONObject jo ) throws JSONException {

		this.label_id = (int)jo.getInt( CatalogLabels.Fields.label_id.name() );
		this.label_name = jo.getString( CatalogLabels.Fields.label_name.name() );

	}

	public Integer getLabelId() {

		return this.label_id;

	}

	public void setLabelId( Integer label_id ) {

		this.label_id = label_id;

	}

	public String getLabelName() {

		return this.label_name;

	}

	public void setLabelName( String label_name ) {

		this.label_name = label_name;

	}

	public Fields[] getEntityFields() {

		return CatalogLabels.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"label_id\": \"" ).append( this.getLabelId() ).append( "\", " )
			.append( "\"label_name\": \"" ).append( this.getLabelName() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }