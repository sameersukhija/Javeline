package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "relation_types" )
public class RelationTypes { 

	public enum Fields { type_id, relation_type }

	@Column(
			table = "relation_types",
			field = "type_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = true,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 3,
			getMethod = "getTypeId",
			setMethod = "setTypeId"
	)
	private Byte type_id;

	@Column(
			table = "relation_types",
			field = "relation_type",
			type = "varchar(45)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 45,
			getMethod = "getRelationType",
			setMethod = "setRelationType"
	)
	private String relation_type;


	public RelationTypes() {} 

	public RelationTypes( ResultSet rs ) throws SQLException {

		this.type_id = rs.getByte( RelationTypes.Fields.type_id.name() );
		this.relation_type = rs.getString( RelationTypes.Fields.relation_type.name() );

	}

	public RelationTypes( JSONObject jo ) throws JSONException {

		this.type_id = (byte)jo.getInt( RelationTypes.Fields.type_id.name() );
		this.relation_type = jo.getString( RelationTypes.Fields.relation_type.name() );

	}

	public Byte getTypeId() {

		return this.type_id;

	}

	public void setTypeId( Byte type_id ) {

		this.type_id = type_id;

	}

	public String getRelationType() {

		return this.relation_type;

	}

	public void setRelationType( String relation_type ) {

		this.relation_type = relation_type;

	}

	public Fields[] getEntityFields() {

		return RelationTypes.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"type_id\": \"" ).append( this.getTypeId() ).append( "\", " )
			.append( "\"relation_type\": \"" ).append( this.getRelationType() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }