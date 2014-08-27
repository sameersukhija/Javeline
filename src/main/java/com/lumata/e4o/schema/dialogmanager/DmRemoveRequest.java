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


@Table( "dm_remove_request" )
public class DmRemoveRequest { 

	public enum Fields { tenant_identifier, customer_id, channel, module_id, feature_id, last_modify_on }

	@Column(
			table = "dm_remove_request",
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
			table = "dm_remove_request",
			field = "customer_id",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "",
			extra = "",
			length = 255,
			getMethod = "getCustomerId",
			setMethod = "setCustomerId"
	)
	private String customer_id;

	@Column(
			table = "dm_remove_request",
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
			table = "dm_remove_request",
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
			table = "dm_remove_request",
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
			table = "dm_remove_request",
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


	public DmRemoveRequest() {} 

	public DmRemoveRequest( ResultSet rs ) throws SQLException {

		this.tenant_identifier = rs.getString( DmRemoveRequest.Fields.tenant_identifier.name() );
		this.customer_id = rs.getString( DmRemoveRequest.Fields.customer_id.name() );
		this.channel = rs.getString( DmRemoveRequest.Fields.channel.name() );
		this.module_id = rs.getString( DmRemoveRequest.Fields.module_id.name() );
		this.feature_id = rs.getString( DmRemoveRequest.Fields.feature_id.name() );
		this.last_modify_on = rs.getTimestamp( DmRemoveRequest.Fields.last_modify_on.name() );

	}

	public DmRemoveRequest( JSONObject jo ) throws JSONException, ParseException {

		this.tenant_identifier = jo.getString( DmRemoveRequest.Fields.tenant_identifier.name() );
		this.customer_id = jo.getString( DmRemoveRequest.Fields.customer_id.name() );
		this.channel = jo.getString( DmRemoveRequest.Fields.channel.name() );
		this.module_id = jo.getString( DmRemoveRequest.Fields.module_id.name() );
		this.feature_id = jo.getString( DmRemoveRequest.Fields.feature_id.name() );
		this.last_modify_on = new Timestamp( Format.getMysqlDateTime( jo.getString( DmRemoveRequest.Fields.last_modify_on.name() ) ).getTime() );

	}

	public String getTenantIdentifier() {

		return this.tenant_identifier;

	}

	public void setTenantIdentifier( String tenant_identifier ) {

		this.tenant_identifier = tenant_identifier;

	}

	public String getCustomerId() {

		return this.customer_id;

	}

	public void setCustomerId( String customer_id ) {

		this.customer_id = customer_id;

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

	public Timestamp getLastModifyOn() {

		return this.last_modify_on;

	}

	public void setLastModifyOn( Timestamp last_modify_on ) {

		this.last_modify_on = last_modify_on;

	}

	public Fields[] getEntityFields() {

		return DmRemoveRequest.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"tenant_identifier\": \"" ).append( this.getTenantIdentifier() ).append( "\", " )
			.append( "\"customer_id\": \"" ).append( this.getCustomerId() ).append( "\", " )
			.append( "\"channel\": \"" ).append( this.getChannel() ).append( "\", " )
			.append( "\"module_id\": \"" ).append( this.getModuleId() ).append( "\", " )
			.append( "\"feature_id\": \"" ).append( this.getFeatureId() ).append( "\", " )
			.append( "\"last_modify_on\": \"" ).append( this.getLastModifyOn() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }