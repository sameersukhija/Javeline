package com.lumata.expression.operators.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.sql.Timestamp;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "locks" )
public class Locks { 

	public enum Fields { lock_id, locker_id, process_id, update_time }

	@Column(
			table = "locks",
			field = "lock_id",
			type = "varchar(54)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 54,
			getMethod = "getLockId",
			setMethod = "setLockId"
	)
	private String lock_id;

	@Column(
			table = "locks",
			field = "locker_id",
			type = "varchar(256)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 256,
			getMethod = "getLockerId",
			setMethod = "setLockerId"
	)
	private String locker_id;

	@Column(
			table = "locks",
			field = "process_id",
			type = "varchar(30)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 30,
			getMethod = "getProcessId",
			setMethod = "setProcessId"
	)
	private String process_id;

	@Column(
			table = "locks",
			field = "update_time",
			type = "timestamp",
			mysqlType = "timestamp",
			javaType = "Timestamp",
			categoryType = "Date",
			isNull = false,
			key = "",
			defaultValue = "CURRENT_TIMESTAMP",
			extra = "on update CURRENT_TIMESTAMP",
			length = 0,
			getMethod = "getUpdateTime",
			setMethod = "setUpdateTime"
	)
	private Timestamp update_time;


	public Locks() {} 

	public Locks( ResultSet rs ) throws SQLException {

		this.lock_id = rs.getString( Locks.Fields.lock_id.name() );
		this.locker_id = rs.getString( Locks.Fields.locker_id.name() );
		this.process_id = rs.getString( Locks.Fields.process_id.name() );
		this.update_time = rs.getTimestamp( Locks.Fields.update_time.name() );

	}

	public Locks( JSONObject jo ) throws JSONException, ParseException {

		this.lock_id = jo.getString( Locks.Fields.lock_id.name() );
		this.locker_id = jo.getString( Locks.Fields.locker_id.name() );
		this.process_id = jo.getString( Locks.Fields.process_id.name() );
		this.update_time = new Timestamp( Format.getMysqlDateTime( jo.getString( Locks.Fields.update_time.name() ) ).getTime() );

	}

	public String getLockId() {

		return this.lock_id;

	}

	public void setLockId( String lock_id ) {

		this.lock_id = lock_id;

	}

	public String getLockerId() {

		return this.locker_id;

	}

	public void setLockerId( String locker_id ) {

		this.locker_id = locker_id;

	}

	public String getProcessId() {

		return this.process_id;

	}

	public void setProcessId( String process_id ) {

		this.process_id = process_id;

	}

	public Timestamp getUpdateTime() {

		return this.update_time;

	}

	public void setUpdateTime( Timestamp update_time ) {

		this.update_time = update_time;

	}

	public Fields[] getEntityFields() {

		return Locks.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"lock_id\": \"" ).append( this.getLockId() ).append( "\", " )
			.append( "\"locker_id\": \"" ).append( this.getLockerId() ).append( "\", " )
			.append( "\"process_id\": \"" ).append( this.getProcessId() ).append( "\", " )
			.append( "\"update_time\": \"" ).append( this.getUpdateTime() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }