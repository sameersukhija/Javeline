package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "loyalty_programs" )
public class LoyaltyPrograms { 

	public enum Fields { program_id, program_name, program_type, version_prod, version_test }

	@Column(
			table = "loyalty_programs",
			field = "program_id",
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
			getMethod = "getProgramId",
			setMethod = "setProgramId"
	)
	private Byte program_id;

	@Column(
			table = "loyalty_programs",
			field = "program_name",
			type = "varchar(20)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getProgramName",
			setMethod = "setProgramName"
	)
	private String program_name;

	@Column(
			table = "loyalty_programs",
			field = "program_type",
			type = "enum('CLASSES','BADGES')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 2,
			getMethod = "getProgramType",
			setMethod = "setProgramType"
	)
	private String program_type;

	@Column(
			table = "loyalty_programs",
			field = "version_prod",
			type = "varchar(20)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getVersionProd",
			setMethod = "setVersionProd"
	)
	private String version_prod;

	@Column(
			table = "loyalty_programs",
			field = "version_test",
			type = "varchar(20)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getVersionTest",
			setMethod = "setVersionTest"
	)
	private String version_test;


	public LoyaltyPrograms() {} 

	public LoyaltyPrograms( ResultSet rs ) throws SQLException {

		this.program_id = rs.getByte( LoyaltyPrograms.Fields.program_id.name() );
		this.program_name = rs.getString( LoyaltyPrograms.Fields.program_name.name() );
		this.program_type = rs.getString( LoyaltyPrograms.Fields.program_type.name() );
		this.version_prod = rs.getString( LoyaltyPrograms.Fields.version_prod.name() );
		this.version_test = rs.getString( LoyaltyPrograms.Fields.version_test.name() );

	}

	public LoyaltyPrograms( JSONObject jo ) throws JSONException {

		this.program_id = (byte)jo.getInt( LoyaltyPrograms.Fields.program_id.name() );
		this.program_name = jo.getString( LoyaltyPrograms.Fields.program_name.name() );
		this.program_type = jo.getString( LoyaltyPrograms.Fields.program_type.name() );
		this.version_prod = jo.getString( LoyaltyPrograms.Fields.version_prod.name() );
		this.version_test = jo.getString( LoyaltyPrograms.Fields.version_test.name() );

	}

	public Byte getProgramId() {

		return this.program_id;

	}

	public void setProgramId( Byte program_id ) {

		this.program_id = program_id;

	}

	public String getProgramName() {

		return this.program_name;

	}

	public void setProgramName( String program_name ) {

		this.program_name = program_name;

	}

	public String getProgramType() {

		return this.program_type;

	}

	public void setProgramType( String program_type ) {

		this.program_type = program_type;

	}

	public String getVersionProd() {

		return this.version_prod;

	}

	public void setVersionProd( String version_prod ) {

		this.version_prod = version_prod;

	}

	public String getVersionTest() {

		return this.version_test;

	}

	public void setVersionTest( String version_test ) {

		this.version_test = version_test;

	}

	public Fields[] getEntityFields() {

		return LoyaltyPrograms.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"program_id\": \"" ).append( this.getProgramId() ).append( "\", " )
			.append( "\"program_name\": \"" ).append( this.getProgramName() ).append( "\", " )
			.append( "\"program_type\": \"" ).append( this.getProgramType() ).append( "\", " )
			.append( "\"version_prod\": \"" ).append( this.getVersionProd() ).append( "\", " )
			.append( "\"version_test\": \"" ).append( this.getVersionTest() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }