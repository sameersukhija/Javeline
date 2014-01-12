package com.lumata.expression.operators.entities;

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
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 25,
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
			key = "",
			defaultValue = "null",
			extra = "",
			length = 5,
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
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 45,
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

	public void setVariableName( String variable_name ) {

		this.variable_name = variable_name;

	}

	public String getVariableType() {

		return this.variable_type;

	}

	public void setVariableType( String variable_type ) {

		this.variable_type = variable_type;

	}

	public String getScope() {

		return this.scope;

	}

	public void setScope( String scope ) {

		this.scope = scope;

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