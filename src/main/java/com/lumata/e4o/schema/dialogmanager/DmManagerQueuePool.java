package com.lumata.e4o.schema.dialogmanager;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.sql.Timestamp;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "dm_manager_queue_pool" )
public class DmManagerQueuePool { 

	public enum Fields { queue_dialog_pool_id, queue_dialog_id, queue_name, owner_id, last_modify_on }

	@Column(
			table = "dm_manager_queue_pool",
			field = "queue_dialog_pool_id",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = true,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 11,
			getMethod = "getQueueDialogPoolId",
			setMethod = "setQueueDialogPoolId"
	)
	private Integer queue_dialog_pool_id;

	@Column(
			table = "dm_manager_queue_pool",
			field = "queue_dialog_id",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getQueueDialogId",
			setMethod = "setQueueDialogId"
	)
	private Integer queue_dialog_id;

	@Column(
			table = "dm_manager_queue_pool",
			field = "queue_name",
			type = "varchar(64)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 64,
			getMethod = "getQueueName",
			setMethod = "setQueueName"
	)
	private String queue_name;

	@Column(
			table = "dm_manager_queue_pool",
			field = "owner_id",
			type = "varchar(64)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 64,
			getMethod = "getOwnerId",
			setMethod = "setOwnerId"
	)
	private String owner_id;

	@Column(
			table = "dm_manager_queue_pool",
			field = "last_modify_on",
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
			getMethod = "getLastModifyOn",
			setMethod = "setLastModifyOn"
	)
	private Timestamp last_modify_on;


	public DmManagerQueuePool() {} 

	public DmManagerQueuePool( ResultSet rs ) throws SQLException {

		this.queue_dialog_pool_id = rs.getInt( DmManagerQueuePool.Fields.queue_dialog_pool_id.name() );
		this.queue_dialog_id = rs.getInt( DmManagerQueuePool.Fields.queue_dialog_id.name() );
		this.queue_name = rs.getString( DmManagerQueuePool.Fields.queue_name.name() );
		this.owner_id = rs.getString( DmManagerQueuePool.Fields.owner_id.name() );
		this.last_modify_on = rs.getTimestamp( DmManagerQueuePool.Fields.last_modify_on.name() );

	}

	public DmManagerQueuePool( JSONObject jo ) throws JSONException, ParseException {

		this.queue_dialog_pool_id = (int)jo.getInt( DmManagerQueuePool.Fields.queue_dialog_pool_id.name() );
		this.queue_dialog_id = (int)jo.getInt( DmManagerQueuePool.Fields.queue_dialog_id.name() );
		this.queue_name = jo.getString( DmManagerQueuePool.Fields.queue_name.name() );
		this.owner_id = jo.getString( DmManagerQueuePool.Fields.owner_id.name() );
		this.last_modify_on = new Timestamp( Format.getMysqlDateTime( jo.getString( DmManagerQueuePool.Fields.last_modify_on.name() ) ).getTime() );

	}

	public Integer getQueueDialogPoolId() {

		return this.queue_dialog_pool_id;

	}

	public void setQueueDialogPoolId( Integer queue_dialog_pool_id ) {

		this.queue_dialog_pool_id = queue_dialog_pool_id;

	}

	public Integer getQueueDialogId() {

		return this.queue_dialog_id;

	}

	public void setQueueDialogId( Integer queue_dialog_id ) {

		this.queue_dialog_id = queue_dialog_id;

	}

	public String getQueueName() {

		return this.queue_name;

	}

	public void setQueueName( String queue_name ) {

		this.queue_name = queue_name;

	}

	public String getOwnerId() {

		return this.owner_id;

	}

	public void setOwnerId( String owner_id ) {

		this.owner_id = owner_id;

	}

	public Timestamp getLastModifyOn() {

		return this.last_modify_on;

	}

	public void setLastModifyOn( Timestamp last_modify_on ) {

		this.last_modify_on = last_modify_on;

	}

	public Fields[] getEntityFields() {

		return DmManagerQueuePool.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"queue_dialog_pool_id\": \"" ).append( this.getQueueDialogPoolId() ).append( "\", " )
			.append( "\"queue_dialog_id\": \"" ).append( this.getQueueDialogId() ).append( "\", " )
			.append( "\"queue_name\": \"" ).append( this.getQueueName() ).append( "\", " )
			.append( "\"owner_id\": \"" ).append( this.getOwnerId() ).append( "\", " )
			.append( "\"last_modify_on\": \"" ).append( this.getLastModifyOn() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }