package com.lumata.expression.operators.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "library_statuses" )
public class LibraryStatuses { 

	public enum Fields { status_id, profile_id, status }

	@Column(
			table = "library_statuses",
			field = "status_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getStatusId",
			setMethod = "setStatusId"
	)
	private Byte status_id;

	@Column(
			table = "library_statuses",
			field = "profile_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getProfileId",
			setMethod = "setProfileId"
	)
	private Byte profile_id;

	@Column(
			table = "library_statuses",
			field = "status",
			type = "varchar(20)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getStatus",
			setMethod = "setStatus"
	)
	private String status;


	public LibraryStatuses() {} 

	public LibraryStatuses( ResultSet rs ) throws SQLException {

		this.status_id = rs.getByte( LibraryStatuses.Fields.status_id.name() );
		this.profile_id = rs.getByte( LibraryStatuses.Fields.profile_id.name() );
		this.status = rs.getString( LibraryStatuses.Fields.status.name() );

	}

	public LibraryStatuses( JSONObject jo ) throws JSONException {

		this.status_id = (byte)jo.getInt( LibraryStatuses.Fields.status_id.name() );
		this.profile_id = (byte)jo.getInt( LibraryStatuses.Fields.profile_id.name() );
		this.status = jo.getString( LibraryStatuses.Fields.status.name() );

	}

	public Byte getStatusId() {

		return this.status_id;

	}

	public void setStatusId( Byte status_id ) {

		this.status_id = status_id;

	}

	public Byte getProfileId() {

		return this.profile_id;

	}

	public void setProfileId( Byte profile_id ) {

		this.profile_id = profile_id;

	}

	public String getStatus() {

		return this.status;

	}

	public void setStatus( String status ) {

		this.status = status;

	}

	public Fields[] getEntityFields() {

		return LibraryStatuses.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"status_id\": \"" ).append( this.getStatusId() ).append( "\", " )
			.append( "\"profile_id\": \"" ).append( this.getProfileId() ).append( "\", " )
			.append( "\"status\": \"" ).append( this.getStatus() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }