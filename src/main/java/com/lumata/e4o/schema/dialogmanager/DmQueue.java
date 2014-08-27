package com.lumata.e4o.schema.dialogmanager;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "dm_queue" )
public class DmQueue { 

	public enum Fields { queue_id, tenant_identifier, name, description }

	@Column(
			table = "dm_queue",
			field = "queue_id",
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
			getMethod = "getQueueId",
			setMethod = "setQueueId"
	)
	private Integer queue_id;

	@Column(
			table = "dm_queue",
			field = "tenant_identifier",
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
			getMethod = "getTenantIdentifier",
			setMethod = "setTenantIdentifier"
	)
	private String tenant_identifier;

	@Column(
			table = "dm_queue",
			field = "name",
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
			getMethod = "getName",
			setMethod = "setName"
	)
	private String name;

	@Column(
			table = "dm_queue",
			field = "description",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "",
			extra = "",
			length = 255,
			getMethod = "getDescription",
			setMethod = "setDescription"
	)
	private String description;


	public DmQueue() {} 

	public DmQueue( ResultSet rs ) throws SQLException {

		this.queue_id = rs.getInt( DmQueue.Fields.queue_id.name() );
		this.tenant_identifier = rs.getString( DmQueue.Fields.tenant_identifier.name() );
		this.name = rs.getString( DmQueue.Fields.name.name() );
		this.description = rs.getString( DmQueue.Fields.description.name() );

	}

	public DmQueue( JSONObject jo ) throws JSONException {

		this.queue_id = (int)jo.getInt( DmQueue.Fields.queue_id.name() );
		this.tenant_identifier = jo.getString( DmQueue.Fields.tenant_identifier.name() );
		this.name = jo.getString( DmQueue.Fields.name.name() );
		this.description = jo.getString( DmQueue.Fields.description.name() );

	}

	public Integer getQueueId() {

		return this.queue_id;

	}

	public void setQueueId( Integer queue_id ) {

		this.queue_id = queue_id;

	}

	public String getTenantIdentifier() {

		return this.tenant_identifier;

	}

	public void setTenantIdentifier( String tenant_identifier ) {

		this.tenant_identifier = tenant_identifier;

	}

	public String getName() {

		return this.name;

	}

	public void setName( String name ) {

		this.name = name;

	}

	public String getDescription() {

		return this.description;

	}

	public void setDescription( String description ) {

		this.description = description;

	}

	public Fields[] getEntityFields() {

		return DmQueue.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"queue_id\": \"" ).append( this.getQueueId() ).append( "\", " )
			.append( "\"tenant_identifier\": \"" ).append( this.getTenantIdentifier() ).append( "\", " )
			.append( "\"name\": \"" ).append( this.getName() ).append( "\", " )
			.append( "\"description\": \"" ).append( this.getDescription() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }