package com.lumata.expression.operators.testing.pojo.autogenerator;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "offoptim_algorithm" )
public class OffoptimAlgorithm { 

	public enum Fields { algorithm_id, name, description, class_path }

	@Column(
			table = "offoptim_algorithm",
			field = "algorithm_id",
			type = "int(10) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 10,
			getMethod = "getAlgorithmId",
			setMethod = "setAlgorithmId"
	)
	private Integer algorithm_id;

	@Column(
			table = "offoptim_algorithm",
			field = "name",
			type = "varchar(20)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getName",
			setMethod = "setName"
	)
	private String name;

	@Column(
			table = "offoptim_algorithm",
			field = "description",
			type = "text",
			mysqlType = "text",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getDescription",
			setMethod = "setDescription"
	)
	private String description;

	@Column(
			table = "offoptim_algorithm",
			field = "class_path",
			type = "text",
			mysqlType = "text",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getClassPath",
			setMethod = "setClassPath"
	)
	private String class_path;


	public OffoptimAlgorithm() {} 

	public OffoptimAlgorithm( ResultSet rs ) throws SQLException {

		this.algorithm_id = rs.getInt( OffoptimAlgorithm.Fields.algorithm_id.name() );
		this.name = rs.getString( OffoptimAlgorithm.Fields.name.name() );
		this.description = rs.getString( OffoptimAlgorithm.Fields.description.name() );
		this.class_path = rs.getString( OffoptimAlgorithm.Fields.class_path.name() );

	}

	public OffoptimAlgorithm( JSONObject jo ) throws JSONException {

		this.algorithm_id = (int)jo.getInt( OffoptimAlgorithm.Fields.algorithm_id.name() );
		this.name = jo.getString( OffoptimAlgorithm.Fields.name.name() );
		this.description = jo.getString( OffoptimAlgorithm.Fields.description.name() );
		this.class_path = jo.getString( OffoptimAlgorithm.Fields.class_path.name() );

	}

	public Integer getAlgorithmId() {

		return this.algorithm_id;

	}

	public void setAlgorithmId( Integer algorithm_id ) {

		this.algorithm_id = algorithm_id;

	}

	public String getName() {

		return this.name;

	}

	public void setName( String name ) {

		this.name = name;

	}

	public String getDescription() {

		return this.description;

	}

	public void setDescription( String description ) {

		this.description = description;

	}

	public String getClassPath() {

		return this.class_path;

	}

	public void setClassPath( String class_path ) {

		this.class_path = class_path;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"algorithm_id\": \"" ).append( this.getAlgorithmId() ).append( "\", " )
			.append( "\"name\": \"" ).append( this.getName() ).append( "\", " )
			.append( "\"description\": \"" ).append( this.getDescription() ).append( "\", " )
			.append( "\"class_path\": \"" ).append( this.getClassPath() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }