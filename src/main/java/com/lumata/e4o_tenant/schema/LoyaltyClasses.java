package com.lumata.e4o_tenant.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "loyalty_classes" )
public class LoyaltyClasses { 

	public enum Fields { class_id, project_id, project_name, class_name, class_rank }

	@Column(
			table = "loyalty_classes",
			field = "class_id",
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
			getMethod = "getClassId",
			setMethod = "setClassId"
	)
	private Byte class_id;

	@Column(
			table = "loyalty_classes",
			field = "project_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getProjectId",
			setMethod = "setProjectId"
	)
	private Byte project_id;

	@Column(
			table = "loyalty_classes",
			field = "project_name",
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
			getMethod = "getProjectName",
			setMethod = "setProjectName"
	)
	private String project_name;

	@Column(
			table = "loyalty_classes",
			field = "class_name",
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
			getMethod = "getClassName",
			setMethod = "setClassName"
	)
	private String class_name;

	@Column(
			table = "loyalty_classes",
			field = "class_rank",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getClassRank",
			setMethod = "setClassRank"
	)
	private Byte class_rank;


	public LoyaltyClasses() {} 

	public LoyaltyClasses( ResultSet rs ) throws SQLException {

		this.class_id = rs.getByte( LoyaltyClasses.Fields.class_id.name() );
		this.project_id = rs.getByte( LoyaltyClasses.Fields.project_id.name() );
		this.project_name = rs.getString( LoyaltyClasses.Fields.project_name.name() );
		this.class_name = rs.getString( LoyaltyClasses.Fields.class_name.name() );
		this.class_rank = rs.getByte( LoyaltyClasses.Fields.class_rank.name() );

	}

	public LoyaltyClasses( JSONObject jo ) throws JSONException {

		this.class_id = (byte)jo.getInt( LoyaltyClasses.Fields.class_id.name() );
		this.project_id = (byte)jo.getInt( LoyaltyClasses.Fields.project_id.name() );
		this.project_name = jo.getString( LoyaltyClasses.Fields.project_name.name() );
		this.class_name = jo.getString( LoyaltyClasses.Fields.class_name.name() );
		this.class_rank = (byte)jo.getInt( LoyaltyClasses.Fields.class_rank.name() );

	}

	public Byte getClassId() {

		return this.class_id;

	}

	public void setClassId( Byte class_id ) {

		this.class_id = class_id;

	}

	public Byte getProjectId() {

		return this.project_id;

	}

	public void setProjectId( Byte project_id ) {

		this.project_id = project_id;

	}

	public String getProjectName() {

		return this.project_name;

	}

	public void setProjectName( String project_name ) {

		this.project_name = project_name;

	}

	public String getClassName() {

		return this.class_name;

	}

	public void setClassName( String class_name ) {

		this.class_name = class_name;

	}

	public Byte getClassRank() {

		return this.class_rank;

	}

	public void setClassRank( Byte class_rank ) {

		this.class_rank = class_rank;

	}

	public Fields[] getEntityFields() {

		return LoyaltyClasses.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"class_id\": \"" ).append( this.getClassId() ).append( "\", " )
			.append( "\"project_id\": \"" ).append( this.getProjectId() ).append( "\", " )
			.append( "\"project_name\": \"" ).append( this.getProjectName() ).append( "\", " )
			.append( "\"class_name\": \"" ).append( this.getClassName() ).append( "\", " )
			.append( "\"class_rank\": \"" ).append( this.getClassRank() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }