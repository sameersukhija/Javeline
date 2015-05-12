package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.util.Date;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "logical_lock" )
public class LogicalLock { 

	public enum Fields { lock_id, locker_id, creation_time }

	@Column(
			table = "logical_lock",
			field = "lock_id",
			type = "varchar(100)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 100,
			comment = "",
			getMethod = "getLockId",
			setMethod = "setLockId"
	)
	private String lock_id;

	@Column(
			table = "logical_lock",
			field = "locker_id",
			type = "varchar(100)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 100,
			comment = "",
			getMethod = "getLockerId",
			setMethod = "setLockerId"
	)
	private String locker_id;

	@Column(
			table = "logical_lock",
			field = "creation_time",
			type = "datetime",
			mysqlType = "datetime",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "",
			getMethod = "getCreationTime",
			setMethod = "setCreationTime"
	)
	private Date creation_time;


	public LogicalLock() {} 

	public LogicalLock( ResultSet rs ) throws SQLException {

		this.lock_id = rs.getString( LogicalLock.Fields.lock_id.name() );
		this.locker_id = rs.getString( LogicalLock.Fields.locker_id.name() );
		this.creation_time = rs.getDate( LogicalLock.Fields.creation_time.name() );

	}

	public LogicalLock( JSONObject jo ) throws JSONException, ParseException {

		this.lock_id = jo.getString( LogicalLock.Fields.lock_id.name() );
		this.locker_id = jo.getString( LogicalLock.Fields.locker_id.name() );
		this.creation_time = Format.getMysqlDateTime( jo.getString( LogicalLock.Fields.creation_time.name() ) );

	}

	public String getLockId() {

		return this.lock_id;

	}

	public LogicalLock setLockId( String lock_id ) {

		this.lock_id = lock_id;

		return this;

	}

	public String getLockerId() {

		return this.locker_id;

	}

	public LogicalLock setLockerId( String locker_id ) {

		this.locker_id = locker_id;

		return this;

	}

	public Date getCreationTime() {

		return this.creation_time;

	}

	public LogicalLock setCreationTime( Date creation_time ) {

		this.creation_time = creation_time;

		return this;

	}

	public Fields[] getEntityFields() {

		return LogicalLock.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"lock_id\": \"" ).append( this.getLockId() ).append( "\", " )
			.append( "\"locker_id\": \"" ).append( this.getLockerId() ).append( "\", " )
			.append( "\"creation_time\": \"" ).append( this.getCreationTime() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }