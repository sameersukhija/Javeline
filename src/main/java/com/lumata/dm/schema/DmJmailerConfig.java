package com.lumata.dm.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "dm_jmailer_config" )
public class DmJmailerConfig { 

	public enum Fields { tenantId, country, channel, sms_route, sms_sender, sms_promoter, sms_format, sms_service, deliver_route, options }

	@Column(
			table = "dm_jmailer_config",
			field = "tenantId",
			type = "varchar(50)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 50,
			getMethod = "getTenantId",
			setMethod = "setTenantId"
	)
	private String tenantId;

	@Column(
			table = "dm_jmailer_config",
			field = "country",
			type = "varchar(8)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 8,
			getMethod = "getCountry",
			setMethod = "setCountry"
	)
	private String country;

	@Column(
			table = "dm_jmailer_config",
			field = "channel",
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
			getMethod = "getChannel",
			setMethod = "setChannel"
	)
	private String channel;

	@Column(
			table = "dm_jmailer_config",
			field = "sms_route",
			type = "varchar(100)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 100,
			getMethod = "getSmsRoute",
			setMethod = "setSmsRoute"
	)
	private String sms_route;

	@Column(
			table = "dm_jmailer_config",
			field = "sms_sender",
			type = "varchar(100)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 100,
			getMethod = "getSmsSender",
			setMethod = "setSmsSender"
	)
	private String sms_sender;

	@Column(
			table = "dm_jmailer_config",
			field = "sms_promoter",
			type = "varchar(100)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 100,
			getMethod = "getSmsPromoter",
			setMethod = "setSmsPromoter"
	)
	private String sms_promoter;

	@Column(
			table = "dm_jmailer_config",
			field = "sms_format",
			type = "varchar(100)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 100,
			getMethod = "getSmsFormat",
			setMethod = "setSmsFormat"
	)
	private String sms_format;

	@Column(
			table = "dm_jmailer_config",
			field = "sms_service",
			type = "varchar(100)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 100,
			getMethod = "getSmsService",
			setMethod = "setSmsService"
	)
	private String sms_service;

	@Column(
			table = "dm_jmailer_config",
			field = "deliver_route",
			type = "varchar(100)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 100,
			getMethod = "getDeliverRoute",
			setMethod = "setDeliverRoute"
	)
	private String deliver_route;

	@Column(
			table = "dm_jmailer_config",
			field = "options",
			type = "text",
			mysqlType = "text",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getOptions",
			setMethod = "setOptions"
	)
	private String options;


	public DmJmailerConfig() {} 

	public DmJmailerConfig( ResultSet rs ) throws SQLException {

		this.tenantId = rs.getString( DmJmailerConfig.Fields.tenantId.name() );
		this.country = rs.getString( DmJmailerConfig.Fields.country.name() );
		this.channel = rs.getString( DmJmailerConfig.Fields.channel.name() );
		this.sms_route = rs.getString( DmJmailerConfig.Fields.sms_route.name() );
		this.sms_sender = rs.getString( DmJmailerConfig.Fields.sms_sender.name() );
		this.sms_promoter = rs.getString( DmJmailerConfig.Fields.sms_promoter.name() );
		this.sms_format = rs.getString( DmJmailerConfig.Fields.sms_format.name() );
		this.sms_service = rs.getString( DmJmailerConfig.Fields.sms_service.name() );
		this.deliver_route = rs.getString( DmJmailerConfig.Fields.deliver_route.name() );
		this.options = rs.getString( DmJmailerConfig.Fields.options.name() );

	}

	public DmJmailerConfig( JSONObject jo ) throws JSONException {

		this.tenantId = jo.getString( DmJmailerConfig.Fields.tenantId.name() );
		this.country = jo.getString( DmJmailerConfig.Fields.country.name() );
		this.channel = jo.getString( DmJmailerConfig.Fields.channel.name() );
		this.sms_route = jo.getString( DmJmailerConfig.Fields.sms_route.name() );
		this.sms_sender = jo.getString( DmJmailerConfig.Fields.sms_sender.name() );
		this.sms_promoter = jo.getString( DmJmailerConfig.Fields.sms_promoter.name() );
		this.sms_format = jo.getString( DmJmailerConfig.Fields.sms_format.name() );
		this.sms_service = jo.getString( DmJmailerConfig.Fields.sms_service.name() );
		this.deliver_route = jo.getString( DmJmailerConfig.Fields.deliver_route.name() );
		this.options = jo.getString( DmJmailerConfig.Fields.options.name() );

	}

	public String getTenantId() {

		return this.tenantId;

	}

	public void setTenantId( String tenantId ) {

		this.tenantId = tenantId;

	}

	public String getCountry() {

		return this.country;

	}

	public void setCountry( String country ) {

		this.country = country;

	}

	public String getChannel() {

		return this.channel;

	}

	public void setChannel( String channel ) {

		this.channel = channel;

	}

	public String getSmsRoute() {

		return this.sms_route;

	}

	public void setSmsRoute( String sms_route ) {

		this.sms_route = sms_route;

	}

	public String getSmsSender() {

		return this.sms_sender;

	}

	public void setSmsSender( String sms_sender ) {

		this.sms_sender = sms_sender;

	}

	public String getSmsPromoter() {

		return this.sms_promoter;

	}

	public void setSmsPromoter( String sms_promoter ) {

		this.sms_promoter = sms_promoter;

	}

	public String getSmsFormat() {

		return this.sms_format;

	}

	public void setSmsFormat( String sms_format ) {

		this.sms_format = sms_format;

	}

	public String getSmsService() {

		return this.sms_service;

	}

	public void setSmsService( String sms_service ) {

		this.sms_service = sms_service;

	}

	public String getDeliverRoute() {

		return this.deliver_route;

	}

	public void setDeliverRoute( String deliver_route ) {

		this.deliver_route = deliver_route;

	}

	public String getOptions() {

		return this.options;

	}

	public void setOptions( String options ) {

		this.options = options;

	}

	public Fields[] getEntityFields() {

		return DmJmailerConfig.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"tenantId\": \"" ).append( this.getTenantId() ).append( "\", " )
			.append( "\"country\": \"" ).append( this.getCountry() ).append( "\", " )
			.append( "\"channel\": \"" ).append( this.getChannel() ).append( "\", " )
			.append( "\"sms_route\": \"" ).append( this.getSmsRoute() ).append( "\", " )
			.append( "\"sms_sender\": \"" ).append( this.getSmsSender() ).append( "\", " )
			.append( "\"sms_promoter\": \"" ).append( this.getSmsPromoter() ).append( "\", " )
			.append( "\"sms_format\": \"" ).append( this.getSmsFormat() ).append( "\", " )
			.append( "\"sms_service\": \"" ).append( this.getSmsService() ).append( "\", " )
			.append( "\"deliver_route\": \"" ).append( this.getDeliverRoute() ).append( "\", " )
			.append( "\"options\": \"" ).append( this.getOptions() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }