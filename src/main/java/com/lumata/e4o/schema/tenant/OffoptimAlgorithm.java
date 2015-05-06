package com.lumata.e4o.schema.tenant;

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
			isAutoincrement = true,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 10,
			comment = "",
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
			isAutoincrement = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 20,
			comment = "",
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
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "",
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
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "",
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

	public OffoptimAlgorithm setAlgorithmId( Integer algorithm_id ) {

		this.algorithm_id = algorithm_id;

		return this;

	}

	public String getName() {

		return this.name;

	}

	public OffoptimAlgorithm setName( String name ) {

		this.name = name;

		return this;

	}

	public String getDescription() {

		return this.description;

	}

	public OffoptimAlgorithm setDescription( String description ) {

		this.description = description;

		return this;

	}

	public String getClassPath() {

		return this.class_path;

	}

	public OffoptimAlgorithm setClassPath( String class_path ) {

		this.class_path = class_path;

		return this;

	}

	public Fields[] getEntityFields() {

		return OffoptimAlgorithm.Fields.values();

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