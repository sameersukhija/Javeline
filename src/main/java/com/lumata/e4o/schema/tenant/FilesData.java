package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "files_data" )
public class FilesData { 

	public enum Fields { id, content }

	@Column(
			table = "files_data",
			field = "id",
			type = "int(10) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 10,
			getMethod = "getId",
			setMethod = "setId"
	)
	private Integer id;

	@Column(
			table = "files_data",
			field = "content",
			type = "longblob",
			mysqlType = "longblob",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getContent",
			setMethod = "setContent"
	)
	private String content;


	public FilesData() {} 

	public FilesData( ResultSet rs ) throws SQLException {

		this.id = rs.getInt( FilesData.Fields.id.name() );
		this.content = rs.getString( FilesData.Fields.content.name() );

	}

	public FilesData( JSONObject jo ) throws JSONException {

		this.id = (int)jo.getInt( FilesData.Fields.id.name() );
		this.content = jo.getString( FilesData.Fields.content.name() );

	}

	public Integer getId() {

		return this.id;

	}

	public void setId( Integer id ) {

		this.id = id;

	}

	public String getContent() {

		return this.content;

	}

	public void setContent( String content ) {

		this.content = content;

	}

	public Fields[] getEntityFields() {

		return FilesData.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"id\": \"" ).append( this.getId() ).append( "\", " )
			.append( "\"content\": \"" ).append( this.getContent() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }