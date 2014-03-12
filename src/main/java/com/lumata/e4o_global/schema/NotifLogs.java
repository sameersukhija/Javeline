package com.lumata.e4o_global.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.util.Date;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "notif_logs" )
public class NotifLogs { 

	public enum Fields { log_date, id, category, action, feature_id, identifier, text, state, tenant_id }

	@Column(
			table = "notif_logs",
			field = "log_date",
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
			getMethod = "getLogDate",
			setMethod = "setLogDate"
	)
	private Date log_date;

	@Column(
			table = "notif_logs",
			field = "id",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getId",
			setMethod = "setId"
	)
	private Long id;

	@Column(
			table = "notif_logs",
			field = "category",
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
			getMethod = "getCategory",
			setMethod = "setCategory"
	)
	private String category;

	@Column(
			table = "notif_logs",
			field = "action",
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
			getMethod = "getAction",
			setMethod = "setAction"
	)
	private String action;

	@Column(
			table = "notif_logs",
			field = "feature_id",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getFeatureId",
			setMethod = "setFeatureId"
	)
	private Integer feature_id;

	@Column(
			table = "notif_logs",
			field = "identifier",
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
			getMethod = "getIdentifier",
			setMethod = "setIdentifier"
	)
	private String identifier;

	@Column(
			table = "notif_logs",
			field = "text",
			type = "varchar(500)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 500,
			getMethod = "getText",
			setMethod = "setText"
	)
	private String text;

	@Column(
			table = "notif_logs",
			field = "state",
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
			getMethod = "getState",
			setMethod = "setState"
	)
	private String state;

	@Column(
			table = "notif_logs",
			field = "tenant_id",
			type = "smallint(10) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 10,
			getMethod = "getTenantId",
			setMethod = "setTenantId"
	)
	private Short tenant_id;


	public NotifLogs() {} 

	public NotifLogs( ResultSet rs ) throws SQLException {

		this.log_date = rs.getDate( NotifLogs.Fields.log_date.name() );
		this.id = rs.getLong( NotifLogs.Fields.id.name() );
		this.category = rs.getString( NotifLogs.Fields.category.name() );
		this.action = rs.getString( NotifLogs.Fields.action.name() );
		this.feature_id = rs.getInt( NotifLogs.Fields.feature_id.name() );
		this.identifier = rs.getString( NotifLogs.Fields.identifier.name() );
		this.text = rs.getString( NotifLogs.Fields.text.name() );
		this.state = rs.getString( NotifLogs.Fields.state.name() );
		this.tenant_id = rs.getShort( NotifLogs.Fields.tenant_id.name() );

	}

	public NotifLogs( JSONObject jo ) throws JSONException, ParseException {

		this.log_date = Format.getMysqlDateTime( jo.getString( NotifLogs.Fields.log_date.name() ) );
		this.id = (long)jo.getLong( NotifLogs.Fields.id.name() );
		this.category = jo.getString( NotifLogs.Fields.category.name() );
		this.action = jo.getString( NotifLogs.Fields.action.name() );
		this.feature_id = (int)jo.getInt( NotifLogs.Fields.feature_id.name() );
		this.identifier = jo.getString( NotifLogs.Fields.identifier.name() );
		this.text = jo.getString( NotifLogs.Fields.text.name() );
		this.state = jo.getString( NotifLogs.Fields.state.name() );
		this.tenant_id = (short)jo.getInt( NotifLogs.Fields.tenant_id.name() );

	}

	public Date getLogDate() {

		return this.log_date;

	}

	public void setLogDate( Date log_date ) {

		this.log_date = log_date;

	}

	public Long getId() {

		return this.id;

	}

	public void setId( Long id ) {

		this.id = id;

	}

	public String getCategory() {

		return this.category;

	}

	public void setCategory( String category ) {

		this.category = category;

	}

	public String getAction() {

		return this.action;

	}

	public void setAction( String action ) {

		this.action = action;

	}

	public Integer getFeatureId() {

		return this.feature_id;

	}

	public void setFeatureId( Integer feature_id ) {

		this.feature_id = feature_id;

	}

	public String getIdentifier() {

		return this.identifier;

	}

	public void setIdentifier( String identifier ) {

		this.identifier = identifier;

	}

	public String getText() {

		return this.text;

	}

	public void setText( String text ) {

		this.text = text;

	}

	public String getState() {

		return this.state;

	}

	public void setState( String state ) {

		this.state = state;

	}

	public Short getTenantId() {

		return this.tenant_id;

	}

	public void setTenantId( Short tenant_id ) {

		this.tenant_id = tenant_id;

	}

	public Fields[] getEntityFields() {

		return NotifLogs.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"log_date\": \"" ).append( this.getLogDate() ).append( "\", " )
			.append( "\"id\": \"" ).append( this.getId() ).append( "\", " )
			.append( "\"category\": \"" ).append( this.getCategory() ).append( "\", " )
			.append( "\"action\": \"" ).append( this.getAction() ).append( "\", " )
			.append( "\"feature_id\": \"" ).append( this.getFeatureId() ).append( "\", " )
			.append( "\"identifier\": \"" ).append( this.getIdentifier() ).append( "\", " )
			.append( "\"text\": \"" ).append( this.getText() ).append( "\", " )
			.append( "\"state\": \"" ).append( this.getState() ).append( "\", " )
			.append( "\"tenant_id\": \"" ).append( this.getTenantId() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }