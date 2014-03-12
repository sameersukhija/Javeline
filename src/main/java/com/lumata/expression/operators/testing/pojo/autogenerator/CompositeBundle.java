package com.lumata.expression.operators.testing.pojo.autogenerator;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "composite_bundle" )
public class CompositeBundle { 

	public enum Fields { bundle, bundle_name }

	@Column(
			table = "composite_bundle",
			field = "bundle",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getBundle",
			setMethod = "setBundle"
	)
	private Long bundle;

	@Column(
			table = "composite_bundle",
			field = "bundle_name",
			type = "varchar(30)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 30,
			getMethod = "getBundleName",
			setMethod = "setBundleName"
	)
	private String bundle_name;


	public CompositeBundle() {} 

	public CompositeBundle( ResultSet rs ) throws SQLException {

		this.bundle = rs.getLong( CompositeBundle.Fields.bundle.name() );
		this.bundle_name = rs.getString( CompositeBundle.Fields.bundle_name.name() );

	}

	public CompositeBundle( JSONObject jo ) throws JSONException {

		this.bundle = (long)jo.getLong( CompositeBundle.Fields.bundle.name() );
		this.bundle_name = jo.getString( CompositeBundle.Fields.bundle_name.name() );

	}

	public Long getBundle() {

		return this.bundle;

	}

	public void setBundle( Long bundle ) {

		this.bundle = bundle;

	}

	public String getBundleName() {

		return this.bundle_name;

	}

	public void setBundleName( String bundle_name ) {

		this.bundle_name = bundle_name;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"bundle\": \"" ).append( this.getBundle() ).append( "\", " )
			.append( "\"bundle_name\": \"" ).append( this.getBundleName() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }