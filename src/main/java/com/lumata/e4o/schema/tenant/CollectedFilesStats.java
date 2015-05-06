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


@Table( "collected_files_stats" )
public class CollectedFilesStats { 

	public enum Fields { handler, scope, name, type, processed_date }

	@Column(
			table = "collected_files_stats",
			field = "handler",
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
			getMethod = "getHandler",
			setMethod = "setHandler"
	)
	private String handler;

	@Column(
			table = "collected_files_stats",
			field = "scope",
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
			getMethod = "getScope",
			setMethod = "setScope"
	)
	private String scope;

	@Column(
			table = "collected_files_stats",
			field = "name",
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
			getMethod = "getName",
			setMethod = "setName"
	)
	private String name;

	@Column(
			table = "collected_files_stats",
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
			table = "collected_files_stats",
			field = "processed_date",
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
			getMethod = "getProcessedDate",
			setMethod = "setProcessedDate"
	)
	private Timestamp processed_date;


	public CollectedFilesStats() {} 

	public CollectedFilesStats( ResultSet rs ) throws SQLException {

		this.handler = rs.getString( CollectedFilesStats.Fields.handler.name() );
		this.scope = rs.getString( CollectedFilesStats.Fields.scope.name() );
		this.name = rs.getString( CollectedFilesStats.Fields.name.name() );
		this.type = rs.getString( CollectedFilesStats.Fields.type.name() );
		this.processed_date = rs.getTimestamp( CollectedFilesStats.Fields.processed_date.name() );

	}

	public CollectedFilesStats( JSONObject jo ) throws JSONException, ParseException {

		this.handler = jo.getString( CollectedFilesStats.Fields.handler.name() );
		this.scope = jo.getString( CollectedFilesStats.Fields.scope.name() );
		this.name = jo.getString( CollectedFilesStats.Fields.name.name() );
		this.type = jo.getString( CollectedFilesStats.Fields.type.name() );
		this.processed_date = new Timestamp( Format.getMysqlDateTime( jo.getString( CollectedFilesStats.Fields.processed_date.name() ) ).getTime() );

	}

	public String getHandler() {

		return this.handler;

	}

	public CollectedFilesStats setHandler( String handler ) {

		this.handler = handler;

		return this;

	}

	public String getScope() {

		return this.scope;

	}

	public CollectedFilesStats setScope( String scope ) {

		this.scope = scope;

		return this;

	}

	public String getName() {

		return this.name;

	}

	public CollectedFilesStats setName( String name ) {

		this.name = name;

		return this;

	}

	public String getType() {

		return this.type;

	}

	public CollectedFilesStats setType( String type ) {

		this.type = type;

		return this;

	}

	public Timestamp getProcessedDate() {

		return this.processed_date;

	}

	public CollectedFilesStats setProcessedDate( Timestamp processed_date ) {

		this.processed_date = processed_date;

		return this;

	}

	public Fields[] getEntityFields() {

		return CollectedFilesStats.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"handler\": \"" ).append( this.getHandler() ).append( "\", " )
			.append( "\"scope\": \"" ).append( this.getScope() ).append( "\", " )
			.append( "\"name\": \"" ).append( this.getName() ).append( "\", " )
			.append( "\"type\": \"" ).append( this.getType() ).append( "\", " )
			.append( "\"processed_date\": \"" ).append( this.getProcessedDate() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }