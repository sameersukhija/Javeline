package com.lumata.e4o_global.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.sql.Timestamp;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "sms" )
public class Sms { 

	public enum Fields { id, action, attempts, idreceipt, msisdn, text, campaign_id, source, expiration_timestamp, max_expiration_timestamp, insert_timestamp, tenant_id }

	@Column(
			table = "sms",
			field = "id",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getId",
			setMethod = "setId"
	)
	private Long id;

	@Column(
			table = "sms",
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
			table = "sms",
			field = "attempts",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 5,
			getMethod = "getAttempts",
			setMethod = "setAttempts"
	)
	private Short attempts;

	@Column(
			table = "sms",
			field = "idreceipt",
			type = "varchar(65)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 65,
			getMethod = "getIdreceipt",
			setMethod = "setIdreceipt"
	)
	private String idreceipt;

	@Column(
			table = "sms",
			field = "msisdn",
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
			getMethod = "getMsisdn",
			setMethod = "setMsisdn"
	)
	private Long msisdn;

	@Column(
			table = "sms",
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
			table = "sms",
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
			table = "sms",
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
			table = "sms",
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
			table = "sms",
			field = "max_expiration_timestamp",
			type = "timestamp",
			mysqlType = "timestamp",
			javaType = "Timestamp",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0000-00-00 00:00:00",
			extra = "",
			length = 0,
			getMethod = "getMaxExpirationTimestamp",
			setMethod = "setMaxExpirationTimestamp"
	)
	private Timestamp max_expiration_timestamp;

	@Column(
			table = "sms",
			field = "insert_timestamp",
			type = "timestamp",
			mysqlType = "timestamp",
			javaType = "Timestamp",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "MUL",
			defaultValue = "CURRENT_TIMESTAMP",
			extra = "",
			length = 0,
			getMethod = "getInsertTimestamp",
			setMethod = "setInsertTimestamp"
	)
	private Timestamp insert_timestamp;

	@Column(
			table = "sms",
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


	public Sms() {} 

	public Sms( ResultSet rs ) throws SQLException {

		this.id = rs.getLong( Sms.Fields.id.name() );
		this.action = rs.getByte( Sms.Fields.action.name() );
		this.attempts = rs.getShort( Sms.Fields.attempts.name() );
		this.idreceipt = rs.getString( Sms.Fields.idreceipt.name() );
		this.msisdn = rs.getLong( Sms.Fields.msisdn.name() );
		this.text = rs.getString( Sms.Fields.text.name() );
		this.campaign_id = rs.getInt( Sms.Fields.campaign_id.name() );
		this.source = rs.getString( Sms.Fields.source.name() );
		this.expiration_timestamp = rs.getTimestamp( Sms.Fields.expiration_timestamp.name() );
		this.max_expiration_timestamp = rs.getTimestamp( Sms.Fields.max_expiration_timestamp.name() );
		this.insert_timestamp = rs.getTimestamp( Sms.Fields.insert_timestamp.name() );
		this.tenant_id = rs.getShort( Sms.Fields.tenant_id.name() );

	}

	public Sms( JSONObject jo ) throws JSONException, ParseException {

		this.id = (long)jo.getLong( Sms.Fields.id.name() );
		this.action = (byte)jo.getInt( Sms.Fields.action.name() );
		this.attempts = (short)jo.getInt( Sms.Fields.attempts.name() );
		this.idreceipt = jo.getString( Sms.Fields.idreceipt.name() );
		this.msisdn = (long)jo.getLong( Sms.Fields.msisdn.name() );
		this.text = jo.getString( Sms.Fields.text.name() );
		this.campaign_id = (int)jo.getInt( Sms.Fields.campaign_id.name() );
		this.source = jo.getString( Sms.Fields.source.name() );
		this.expiration_timestamp = new Timestamp( Format.getMysqlDateTime( jo.getString( Sms.Fields.expiration_timestamp.name() ) ).getTime() );
		this.max_expiration_timestamp = new Timestamp( Format.getMysqlDateTime( jo.getString( Sms.Fields.max_expiration_timestamp.name() ) ).getTime() );
		this.insert_timestamp = new Timestamp( Format.getMysqlDateTime( jo.getString( Sms.Fields.insert_timestamp.name() ) ).getTime() );
		this.tenant_id = (short)jo.getInt( Sms.Fields.tenant_id.name() );

	}

	public Long getId() {

		return this.id;

	}

	public void setId( Long id ) {

		this.id = id;

	}

	public Byte getAction() {

		return this.action;

	}

	public void setAction( Byte action ) {

		this.action = action;

	}

	public Short getAttempts() {

		return this.attempts;

	}

	public void setAttempts( Short attempts ) {

		this.attempts = attempts;

	}

	public String getIdreceipt() {

		return this.idreceipt;

	}

	public void setIdreceipt( String idreceipt ) {

		this.idreceipt = idreceipt;

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public void setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

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

	public Timestamp getExpirationTimestamp() {

		return this.expiration_timestamp;

	}

	public void setExpirationTimestamp( Timestamp expiration_timestamp ) {

		this.expiration_timestamp = expiration_timestamp;

	}

	public Timestamp getMaxExpirationTimestamp() {

		return this.max_expiration_timestamp;

	}

	public void setMaxExpirationTimestamp( Timestamp max_expiration_timestamp ) {

		this.max_expiration_timestamp = max_expiration_timestamp;

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

		return Sms.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"id\": \"" ).append( this.getId() ).append( "\", " )
			.append( "\"action\": \"" ).append( this.getAction() ).append( "\", " )
			.append( "\"attempts\": \"" ).append( this.getAttempts() ).append( "\", " )
			.append( "\"idreceipt\": \"" ).append( this.getIdreceipt() ).append( "\", " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"text\": \"" ).append( this.getText() ).append( "\", " )
			.append( "\"campaign_id\": \"" ).append( this.getCampaignId() ).append( "\", " )
			.append( "\"source\": \"" ).append( this.getSource() ).append( "\", " )
			.append( "\"expiration_timestamp\": \"" ).append( this.getExpirationTimestamp() ).append( "\", " )
			.append( "\"max_expiration_timestamp\": \"" ).append( this.getMaxExpirationTimestamp() ).append( "\", " )
			.append( "\"insert_timestamp\": \"" ).append( this.getInsertTimestamp() ).append( "\", " )
			.append( "\"tenant_id\": \"" ).append( this.getTenantId() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }