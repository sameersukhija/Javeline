package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "dynamic_profiles" )
public class DynamicProfiles { 

	public enum Fields { variable_name, variable_type, scope }

	@Column(
			table = "dynamic_profiles",
			field = "variable_name",
			type = "varchar(25)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 25,
			comment = "",
			getMethod = "getVariableName",
			setMethod = "setVariableName"
	)
	private String variable_name;

	@Column(
			table = "dynamic_profiles",
			field = "variable_type",
			type = "enum('INT','ENUM','SET','FLOAT','BOOLEAN')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 5,
			comment = "",
			getMethod = "getVariableType",
			setMethod = "setVariableType"
	)
	private String variable_type;

	@Column(
			table = "dynamic_profiles",
			field = "scope",
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
			comment = "",
			getMethod = "getScope",
			setMethod = "setScope"
	)
	private String scope;


	public DynamicProfiles() {} 

	public DynamicProfiles( ResultSet rs ) throws SQLException {

		this.variable_name = rs.getString( DynamicProfiles.Fields.variable_name.name() );
		this.variable_type = rs.getString( DynamicProfiles.Fields.variable_type.name() );
		this.scope = rs.getString( DynamicProfiles.Fields.scope.name() );

	}

	public DynamicProfiles( JSONObject jo ) throws JSONException {

		this.variable_name = jo.getString( DynamicProfiles.Fields.variable_name.name() );
		this.variable_type = jo.getString( DynamicProfiles.Fields.variable_type.name() );
		this.scope = jo.getString( DynamicProfiles.Fields.scope.name() );

	}

	public String getVariableName() {

		return this.variable_name;

	}

	public DynamicProfiles setVariableName( String variable_name ) {

		this.variable_name = variable_name;

		return this;

	}

	public String getVariableType() {

		return this.variable_type;

	}

	public DynamicProfiles setVariableType( String variable_type ) {

		this.variable_type = variable_type;

		return this;

	}

	public String getScope() {

		return this.scope;

	}

	public DynamicProfiles setScope( String scope ) {

		this.scope = scope;

		return this;

	}

	public Fields[] getEntityFields() {

		return DynamicProfiles.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"variable_name\": \"" ).append( this.getVariableName() ).append( "\", " )
			.append( "\"variable_type\": \"" ).append( this.getVariableType() ).append( "\", " )
			.append( "\"scope\": \"" ).append( this.getScope() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }