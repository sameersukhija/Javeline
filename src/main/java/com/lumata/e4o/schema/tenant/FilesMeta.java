package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "files_meta" )
public class FilesMeta { 

	public enum Fields { id, name, path, updatetime }

	@Column(
			table = "files_meta",
			field = "id",
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
			getMethod = "getId",
			setMethod = "setId"
	)
	private Integer id;

	@Column(
			table = "files_meta",
			field = "name",
			type = "varchar(50)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 50,
			comment = "",
			getMethod = "getName",
			setMethod = "setName"
	)
	private String name;

	@Column(
			table = "files_meta",
			field = "path",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 255,
			comment = "",
			getMethod = "getPath",
			setMethod = "setPath"
	)
	private String path;

	@Column(
			table = "files_meta",
			field = "updatetime",
			type = "bigint(20)",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 20,
			comment = "",
			getMethod = "getUpdatetime",
			setMethod = "setUpdatetime"
	)
	private Long updatetime;


	public FilesMeta() {} 

	public FilesMeta( ResultSet rs ) throws SQLException {

		this.id = rs.getInt( FilesMeta.Fields.id.name() );
		this.name = rs.getString( FilesMeta.Fields.name.name() );
		this.path = rs.getString( FilesMeta.Fields.path.name() );
		this.updatetime = rs.getLong( FilesMeta.Fields.updatetime.name() );

	}

	public FilesMeta( JSONObject jo ) throws JSONException {

		this.id = (int)jo.getInt( FilesMeta.Fields.id.name() );
		this.name = jo.getString( FilesMeta.Fields.name.name() );
		this.path = jo.getString( FilesMeta.Fields.path.name() );
		this.updatetime = (long)jo.getLong( FilesMeta.Fields.updatetime.name() );

	}

	public Integer getId() {

		return this.id;

	}

	public FilesMeta setId( Integer id ) {

		this.id = id;

		return this;

	}

	public String getName() {

		return this.name;

	}

	public FilesMeta setName( String name ) {

		this.name = name;

		return this;

	}

	public String getPath() {

		return this.path;

	}

	public FilesMeta setPath( String path ) {

		this.path = path;

		return this;

	}

	public Long getUpdatetime() {

		return this.updatetime;

	}

	public FilesMeta setUpdatetime( Long updatetime ) {

		this.updatetime = updatetime;

		return this;

	}

	public Fields[] getEntityFields() {

		return FilesMeta.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"id\": \"" ).append( this.getId() ).append( "\", " )
			.append( "\"name\": \"" ).append( this.getName() ).append( "\", " )
			.append( "\"path\": \"" ).append( this.getPath() ).append( "\", " )
			.append( "\"updatetime\": \"" ).append( this.getUpdatetime() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }