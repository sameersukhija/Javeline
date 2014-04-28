package com.lumata.e4o.schema.global;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "e4o_module" )
public class E4oModule { 

	public enum Fields { module_id, module_name }

	@Column(
			table = "e4o_module",
			field = "module_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getModuleId",
			setMethod = "setModuleId"
	)
	private Byte module_id;

	@Column(
			table = "e4o_module",
			field = "module_name",
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
			getMethod = "getModuleName",
			setMethod = "setModuleName"
	)
	private String module_name;


	public E4oModule() {} 

	public E4oModule( ResultSet rs ) throws SQLException {

		this.module_id = rs.getByte( E4oModule.Fields.module_id.name() );
		this.module_name = rs.getString( E4oModule.Fields.module_name.name() );

	}

	public E4oModule( JSONObject jo ) throws JSONException {

		this.module_id = (byte)jo.getInt( E4oModule.Fields.module_id.name() );
		this.module_name = jo.getString( E4oModule.Fields.module_name.name() );

	}

	public Byte getModuleId() {

		return this.module_id;

	}

	public void setModuleId( Byte module_id ) {

		this.module_id = module_id;

	}

	public String getModuleName() {

		return this.module_name;

	}

	public void setModuleName( String module_name ) {

		this.module_name = module_name;

	}

	public Fields[] getEntityFields() {

		return E4oModule.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"module_id\": \"" ).append( this.getModuleId() ).append( "\", " )
			.append( "\"module_name\": \"" ).append( this.getModuleName() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }