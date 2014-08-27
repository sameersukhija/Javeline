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


@Table( "dm_freeze_request" )
public class DmFreezeRequest { 

	public enum Fields { tenant_identifier, channel, module_id, feature_id, freeze_queue_name, last_modify_on }

	@Column(
			table = "dm_freeze_request",
			field = "tenant_identifier",
			type = "varchar(16)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "",
			extra = "",
			length = 16,
			getMethod = "getTenantIdentifier",
			setMethod = "setTenantIdentifier"
	)
	private String tenant_identifier;

	@Column(
			table = "dm_freeze_request",
			field = "channel",
			type = "varchar(32)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "",
			extra = "",
			length = 32,
			getMethod = "getChannel",
			setMethod = "setChannel"
	)
	private String channel;

	@Column(
			table = "dm_freeze_request",
			field = "module_id",
			type = "varchar(32)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "",
			extra = "",
			length = 32,
			getMethod = "getModuleId",
			setMethod = "setModuleId"
	)
	private String module_id;

	@Column(
			table = "dm_freeze_request",
			field = "feature_id",
			type = "varchar(32)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "",
			extra = "",
			length = 32,
			getMethod = "getFeatureId",
			setMethod = "setFeatureId"
	)
	private String feature_id;

	@Column(
			table = "dm_freeze_request",
			field = "freeze_queue_name",
			type = "varchar(32)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "",
			extra = "",
			length = 32,
			getMethod = "getFreezeQueueName",
			setMethod = "setFreezeQueueName"
	)
	private String freeze_queue_name;

	@Column(
			table = "dm_freeze_request",
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


	public DmFreezeRequest() {} 

	public DmFreezeRequest( ResultSet rs ) throws SQLException {

		this.tenant_identifier = rs.getString( DmFreezeRequest.Fields.tenant_identifier.name() );
		this.channel = rs.getString( DmFreezeRequest.Fields.channel.name() );
		this.module_id = rs.getString( DmFreezeRequest.Fields.module_id.name() );
		this.feature_id = rs.getString( DmFreezeRequest.Fields.feature_id.name() );
		this.freeze_queue_name = rs.getString( DmFreezeRequest.Fields.freeze_queue_name.name() );
		this.last_modify_on = rs.getTimestamp( DmFreezeRequest.Fields.last_modify_on.name() );

	}

	public DmFreezeRequest( JSONObject jo ) throws JSONException, ParseException {

		this.tenant_identifier = jo.getString( DmFreezeRequest.Fields.tenant_identifier.name() );
		this.channel = jo.getString( DmFreezeRequest.Fields.channel.name() );
		this.module_id = jo.getString( DmFreezeRequest.Fields.module_id.name() );
		this.feature_id = jo.getString( DmFreezeRequest.Fields.feature_id.name() );
		this.freeze_queue_name = jo.getString( DmFreezeRequest.Fields.freeze_queue_name.name() );
		this.last_modify_on = new Timestamp( Format.getMysqlDateTime( jo.getString( DmFreezeRequest.Fields.last_modify_on.name() ) ).getTime() );

	}

	public String getTenantIdentifier() {

		return this.tenant_identifier;

	}

	public void setTenantIdentifier( String tenant_identifier ) {

		this.tenant_identifier = tenant_identifier;

	}

	public String getChannel() {

		return this.channel;

	}

	public void setChannel( String channel ) {

		this.channel = channel;

	}

	public String getModuleId() {

		return this.module_id;

	}

	public void setModuleId( String module_id ) {

		this.module_id = module_id;

	}

	public String getFeatureId() {

		return this.feature_id;

	}

	public void setFeatureId( String feature_id ) {

		this.feature_id = feature_id;

	}

	public String getFreezeQueueName() {

		return this.freeze_queue_name;

	}

	public void setFreezeQueueName( String freeze_queue_name ) {

		this.freeze_queue_name = freeze_queue_name;

	}

	public Timestamp getLastModifyOn() {

		return this.last_modify_on;

	}

	public void setLastModifyOn( Timestamp last_modify_on ) {

		this.last_modify_on = last_modify_on;

	}

	public Fields[] getEntityFields() {

		return DmFreezeRequest.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"tenant_identifier\": \"" ).append( this.getTenantIdentifier() ).append( "\", " )
			.append( "\"channel\": \"" ).append( this.getChannel() ).append( "\", " )
			.append( "\"module_id\": \"" ).append( this.getModuleId() ).append( "\", " )
			.append( "\"feature_id\": \"" ).append( this.getFeatureId() ).append( "\", " )
			.append( "\"freeze_queue_name\": \"" ).append( this.getFreezeQueueName() ).append( "\", " )
			.append( "\"last_modify_on\": \"" ).append( this.getLastModifyOn() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }