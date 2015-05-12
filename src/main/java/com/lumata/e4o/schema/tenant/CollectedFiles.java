package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.sql.Timestamp;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "collected_files" )
public class CollectedFiles { 

	public enum Fields { name, type, handler, scope, assigner_id, assigner_name, current_pos, update_date, collected_date, assigned_date }

	@Column(
			table = "collected_files",
			field = "name",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 255,
			comment = "",
			getMethod = "getName",
			setMethod = "setName"
	)
	private String name;

	@Column(
			table = "collected_files",
			field = "type",
			type = "enum('FILE','ENTRY')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "FILE",
			extra = "",
			length = 2,
			comment = "",
			getMethod = "getType",
			setMethod = "setType"
	)
	private String type;

	@Column(
			table = "collected_files",
			field = "handler",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 255,
			comment = "",
			getMethod = "getHandler",
			setMethod = "setHandler"
	)
	private String handler;

	@Column(
			table = "collected_files",
			field = "scope",
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
			getMethod = "getScope",
			setMethod = "setScope"
	)
	private String scope;

	@Column(
			table = "collected_files",
			field = "assigner_id",
			type = "varchar(10)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 10,
			comment = "",
			getMethod = "getAssignerId",
			setMethod = "setAssignerId"
	)
	private String assigner_id;

	@Column(
			table = "collected_files",
			field = "assigner_name",
			type = "varchar(50)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 50,
			comment = "",
			getMethod = "getAssignerName",
			setMethod = "setAssignerName"
	)
	private String assigner_name;

	@Column(
			table = "collected_files",
			field = "current_pos",
			type = "bigint(20)",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 20,
			comment = "",
			getMethod = "getCurrentPos",
			setMethod = "setCurrentPos"
	)
	private Long current_pos;

	@Column(
			table = "collected_files",
			field = "update_date",
			type = "timestamp",
			mysqlType = "timestamp",
			javaType = "Timestamp",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "CURRENT_TIMESTAMP",
			extra = "on update CURRENT_TIMESTAMP",
			length = 0,
			comment = "",
			getMethod = "getUpdateDate",
			setMethod = "setUpdateDate"
	)
	private Timestamp update_date;

	@Column(
			table = "collected_files",
			field = "collected_date",
			type = "timestamp",
			mysqlType = "timestamp",
			javaType = "Timestamp",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "",
			getMethod = "getCollectedDate",
			setMethod = "setCollectedDate"
	)
	private Timestamp collected_date;

	@Column(
			table = "collected_files",
			field = "assigned_date",
			type = "timestamp",
			mysqlType = "timestamp",
			javaType = "Timestamp",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "",
			getMethod = "getAssignedDate",
			setMethod = "setAssignedDate"
	)
	private Timestamp assigned_date;


	public CollectedFiles() {} 

	public CollectedFiles( ResultSet rs ) throws SQLException {

		this.name = rs.getString( CollectedFiles.Fields.name.name() );
		this.type = rs.getString( CollectedFiles.Fields.type.name() );
		this.handler = rs.getString( CollectedFiles.Fields.handler.name() );
		this.scope = rs.getString( CollectedFiles.Fields.scope.name() );
		this.assigner_id = rs.getString( CollectedFiles.Fields.assigner_id.name() );
		this.assigner_name = rs.getString( CollectedFiles.Fields.assigner_name.name() );
		this.current_pos = rs.getLong( CollectedFiles.Fields.current_pos.name() );
		this.update_date = rs.getTimestamp( CollectedFiles.Fields.update_date.name() );
		this.collected_date = rs.getTimestamp( CollectedFiles.Fields.collected_date.name() );
		this.assigned_date = rs.getTimestamp( CollectedFiles.Fields.assigned_date.name() );

	}

	public CollectedFiles( JSONObject jo ) throws JSONException, ParseException {

		this.name = jo.getString( CollectedFiles.Fields.name.name() );
		this.type = jo.getString( CollectedFiles.Fields.type.name() );
		this.handler = jo.getString( CollectedFiles.Fields.handler.name() );
		this.scope = jo.getString( CollectedFiles.Fields.scope.name() );
		this.assigner_id = jo.getString( CollectedFiles.Fields.assigner_id.name() );
		this.assigner_name = jo.getString( CollectedFiles.Fields.assigner_name.name() );
		this.current_pos = (long)jo.getLong( CollectedFiles.Fields.current_pos.name() );
		this.update_date = new Timestamp( Format.getMysqlDateTime( jo.getString( CollectedFiles.Fields.update_date.name() ) ).getTime() );
		this.collected_date = new Timestamp( Format.getMysqlDateTime( jo.getString( CollectedFiles.Fields.collected_date.name() ) ).getTime() );
		this.assigned_date = new Timestamp( Format.getMysqlDateTime( jo.getString( CollectedFiles.Fields.assigned_date.name() ) ).getTime() );

	}

	public String getName() {

		return this.name;

	}

	public CollectedFiles setName( String name ) {

		this.name = name;

		return this;

	}

	public String getType() {

		return this.type;

	}

	public CollectedFiles setType( String type ) {

		this.type = type;

		return this;

	}

	public String getHandler() {

		return this.handler;

	}

	public CollectedFiles setHandler( String handler ) {

		this.handler = handler;

		return this;

	}

	public String getScope() {

		return this.scope;

	}

	public CollectedFiles setScope( String scope ) {

		this.scope = scope;

		return this;

	}

	public String getAssignerId() {

		return this.assigner_id;

	}

	public CollectedFiles setAssignerId( String assigner_id ) {

		this.assigner_id = assigner_id;

		return this;

	}

	public String getAssignerName() {

		return this.assigner_name;

	}

	public CollectedFiles setAssignerName( String assigner_name ) {

		this.assigner_name = assigner_name;

		return this;

	}

	public Long getCurrentPos() {

		return this.current_pos;

	}

	public CollectedFiles setCurrentPos( Long current_pos ) {

		this.current_pos = current_pos;

		return this;

	}

	public Timestamp getUpdateDate() {

		return this.update_date;

	}

	public CollectedFiles setUpdateDate( Timestamp update_date ) {

		this.update_date = update_date;

		return this;

	}

	public Timestamp getCollectedDate() {

		return this.collected_date;

	}

	public CollectedFiles setCollectedDate( Timestamp collected_date ) {

		this.collected_date = collected_date;

		return this;

	}

	public Timestamp getAssignedDate() {

		return this.assigned_date;

	}

	public CollectedFiles setAssignedDate( Timestamp assigned_date ) {

		this.assigned_date = assigned_date;

		return this;

	}

	public Fields[] getEntityFields() {

		return CollectedFiles.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"name\": \"" ).append( this.getName() ).append( "\", " )
			.append( "\"type\": \"" ).append( this.getType() ).append( "\", " )
			.append( "\"handler\": \"" ).append( this.getHandler() ).append( "\", " )
			.append( "\"scope\": \"" ).append( this.getScope() ).append( "\", " )
			.append( "\"assigner_id\": \"" ).append( this.getAssignerId() ).append( "\", " )
			.append( "\"assigner_name\": \"" ).append( this.getAssignerName() ).append( "\", " )
			.append( "\"current_pos\": \"" ).append( this.getCurrentPos() ).append( "\", " )
			.append( "\"update_date\": \"" ).append( this.getUpdateDate() ).append( "\", " )
			.append( "\"collected_date\": \"" ).append( this.getCollectedDate() ).append( "\", " )
			.append( "\"assigned_date\": \"" ).append( this.getAssignedDate() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }