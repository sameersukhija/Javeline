package com.lumata.e4o.schema.global;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.sql.Timestamp;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "notif_order" )
public class NotifOrder { 

	public enum Fields { id, category, action, identifier, process_id, text, campaign_id, source, lock_id, expiration_timestamp, insert_timestamp, tenant_id }

	@Column(
			table = "notif_order",
			field = "id",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = true,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 20,
			getMethod = "getId",
			setMethod = "setId"
	)
	private Long id;

	@Column(
			table = "notif_order",
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
			table = "notif_order",
			field = "action",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getAction",
			setMethod = "setAction"
	)
	private Byte action;

	@Column(
			table = "notif_order",
			field = "identifier",
			type = "varchar(20)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getIdentifier",
			setMethod = "setIdentifier"
	)
	private String identifier;

	@Column(
			table = "notif_order",
			field = "process_id",
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
			getMethod = "getProcessId",
			setMethod = "setProcessId"
	)
	private String process_id;

	@Column(
			table = "notif_order",
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
			table = "notif_order",
			field = "campaign_id",
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
			getMethod = "getCampaignId",
			setMethod = "setCampaignId"
	)
	private Integer campaign_id;

	@Column(
			table = "notif_order",
			field = "source",
			type = "varchar(21)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 21,
			getMethod = "getSource",
			setMethod = "setSource"
	)
	private String source;

	@Column(
			table = "notif_order",
			field = "lock_id",
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
			getMethod = "getLockId",
			setMethod = "setLockId"
	)
	private Long lock_id;

	@Column(
			table = "notif_order",
			field = "expiration_timestamp",
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
			getMethod = "getExpirationTimestamp",
			setMethod = "setExpirationTimestamp"
	)
	private Timestamp expiration_timestamp;

	@Column(
			table = "notif_order",
			field = "insert_timestamp",
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
			getMethod = "getInsertTimestamp",
			setMethod = "setInsertTimestamp"
	)
	private Timestamp insert_timestamp;

	@Column(
			table = "notif_order",
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


	public NotifOrder() {} 

	public NotifOrder( ResultSet rs ) throws SQLException {

		this.id = rs.getLong( NotifOrder.Fields.id.name() );
		this.category = rs.getString( NotifOrder.Fields.category.name() );
		this.action = rs.getByte( NotifOrder.Fields.action.name() );
		this.identifier = rs.getString( NotifOrder.Fields.identifier.name() );
		this.process_id = rs.getString( NotifOrder.Fields.process_id.name() );
		this.text = rs.getString( NotifOrder.Fields.text.name() );
		this.campaign_id = rs.getInt( NotifOrder.Fields.campaign_id.name() );
		this.source = rs.getString( NotifOrder.Fields.source.name() );
		this.lock_id = rs.getLong( NotifOrder.Fields.lock_id.name() );
		this.expiration_timestamp = rs.getTimestamp( NotifOrder.Fields.expiration_timestamp.name() );
		this.insert_timestamp = rs.getTimestamp( NotifOrder.Fields.insert_timestamp.name() );
		this.tenant_id = rs.getShort( NotifOrder.Fields.tenant_id.name() );

	}

	public NotifOrder( JSONObject jo ) throws JSONException, ParseException {

		this.id = (long)jo.getLong( NotifOrder.Fields.id.name() );
		this.category = jo.getString( NotifOrder.Fields.category.name() );
		this.action = (byte)jo.getInt( NotifOrder.Fields.action.name() );
		this.identifier = jo.getString( NotifOrder.Fields.identifier.name() );
		this.process_id = jo.getString( NotifOrder.Fields.process_id.name() );
		this.text = jo.getString( NotifOrder.Fields.text.name() );
		this.campaign_id = (int)jo.getInt( NotifOrder.Fields.campaign_id.name() );
		this.source = jo.getString( NotifOrder.Fields.source.name() );
		this.lock_id = (long)jo.getLong( NotifOrder.Fields.lock_id.name() );
		this.expiration_timestamp = new Timestamp( Format.getMysqlDateTime( jo.getString( NotifOrder.Fields.expiration_timestamp.name() ) ).getTime() );
		this.insert_timestamp = new Timestamp( Format.getMysqlDateTime( jo.getString( NotifOrder.Fields.insert_timestamp.name() ) ).getTime() );
		this.tenant_id = (short)jo.getInt( NotifOrder.Fields.tenant_id.name() );

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

	public Byte getAction() {

		return this.action;

	}

	public void setAction( Byte action ) {

		this.action = action;

	}

	public String getIdentifier() {

		return this.identifier;

	}

	public void setIdentifier( String identifier ) {

		this.identifier = identifier;

	}

	public String getProcessId() {

		return this.process_id;

	}

	public void setProcessId( String process_id ) {

		this.process_id = process_id;

	}

	public String getText() {

		return this.text;

	}

	public void setText( String text ) {

		this.text = text;

	}

	public Integer getCampaignId() {

		return this.campaign_id;

	}

	public void setCampaignId( Integer campaign_id ) {

		this.campaign_id = campaign_id;

	}

	public String getSource() {

		return this.source;

	}

	public void setSource( String source ) {

		this.source = source;

	}

	public Long getLockId() {

		return this.lock_id;

	}

	public void setLockId( Long lock_id ) {

		this.lock_id = lock_id;

	}

	public Timestamp getExpirationTimestamp() {

		return this.expiration_timestamp;

	}

	public void setExpirationTimestamp( Timestamp expiration_timestamp ) {

		this.expiration_timestamp = expiration_timestamp;

	}

	public Timestamp getInsertTimestamp() {

		return this.insert_timestamp;

	}

	public void setInsertTimestamp( Timestamp insert_timestamp ) {

		this.insert_timestamp = insert_timestamp;

	}

	public Short getTenantId() {

		return this.tenant_id;

	}

	public void setTenantId( Short tenant_id ) {

		this.tenant_id = tenant_id;

	}

	public Fields[] getEntityFields() {

		return NotifOrder.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"id\": \"" ).append( this.getId() ).append( "\", " )
			.append( "\"category\": \"" ).append( this.getCategory() ).append( "\", " )
			.append( "\"action\": \"" ).append( this.getAction() ).append( "\", " )
			.append( "\"identifier\": \"" ).append( this.getIdentifier() ).append( "\", " )
			.append( "\"process_id\": \"" ).append( this.getProcessId() ).append( "\", " )
			.append( "\"text\": \"" ).append( this.getText() ).append( "\", " )
			.append( "\"campaign_id\": \"" ).append( this.getCampaignId() ).append( "\", " )
			.append( "\"source\": \"" ).append( this.getSource() ).append( "\", " )
			.append( "\"lock_id\": \"" ).append( this.getLockId() ).append( "\", " )
			.append( "\"expiration_timestamp\": \"" ).append( this.getExpirationTimestamp() ).append( "\", " )
			.append( "\"insert_timestamp\": \"" ).append( this.getInsertTimestamp() ).append( "\", " )
			.append( "\"tenant_id\": \"" ).append( this.getTenantId() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }