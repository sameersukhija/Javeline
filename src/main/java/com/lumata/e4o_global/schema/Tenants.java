package com.lumata.e4o_global.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "tenants" )
public class Tenants { 

	public enum Fields { tenantid, name, virtualhost, url, timezone, active }

	@Column(
			table = "tenants",
			field = "tenantid",
			type = "int(10) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = true,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 10,
			getMethod = "getTenantid",
			setMethod = "setTenantid"
	)
	private Integer tenantid;

	@Column(
			table = "tenants",
			field = "name",
			type = "varchar(50)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 50,
			getMethod = "getName",
			setMethod = "setName"
	)
	private String name;

	@Column(
			table = "tenants",
			field = "virtualhost",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 255,
			getMethod = "getVirtualhost",
			setMethod = "setVirtualhost"
	)
	private String virtualhost;

	@Column(
			table = "tenants",
			field = "url",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 255,
			getMethod = "getUrl",
			setMethod = "setUrl"
	)
	private String url;

	@Column(
			table = "tenants",
			field = "timezone",
			type = "varchar(10)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "GMT",
			extra = "",
			length = 10,
			getMethod = "getTimezone",
			setMethod = "setTimezone"
	)
	private String timezone;

	@Column(
			table = "tenants",
			field = "active",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 4,
			getMethod = "getActive",
			setMethod = "setActive"
	)
	private Byte active;


	public Tenants() {} 

	public Tenants( ResultSet rs ) throws SQLException {

		this.tenantid = rs.getInt( Tenants.Fields.tenantid.name() );
		this.name = rs.getString( Tenants.Fields.name.name() );
		this.virtualhost = rs.getString( Tenants.Fields.virtualhost.name() );
		this.url = rs.getString( Tenants.Fields.url.name() );
		this.timezone = rs.getString( Tenants.Fields.timezone.name() );
		this.active = rs.getByte( Tenants.Fields.active.name() );

	}

	public Tenants( JSONObject jo ) throws JSONException {

		this.tenantid = (int)jo.getInt( Tenants.Fields.tenantid.name() );
		this.name = jo.getString( Tenants.Fields.name.name() );
		this.virtualhost = jo.getString( Tenants.Fields.virtualhost.name() );
		this.url = jo.getString( Tenants.Fields.url.name() );
		this.timezone = jo.getString( Tenants.Fields.timezone.name() );
		this.active = (byte)jo.getInt( Tenants.Fields.active.name() );

	}

	public Integer getTenantid() {

		return this.tenantid;

	}

	public void setTenantid( Integer tenantid ) {

		this.tenantid = tenantid;

	}

	public String getName() {

		return this.name;

	}

	public void setName( String name ) {

		this.name = name;

	}

	public String getVirtualhost() {

		return this.virtualhost;

	}

	public void setVirtualhost( String virtualhost ) {

		this.virtualhost = virtualhost;

	}

	public String getUrl() {

		return this.url;

	}

	public void setUrl( String url ) {

		this.url = url;

	}

	public String getTimezone() {

		return this.timezone;

	}

	public void setTimezone( String timezone ) {

		this.timezone = timezone;

	}

	public Byte getActive() {

		return this.active;

	}

	public void setActive( Byte active ) {

		this.active = active;

	}

	public Fields[] getEntityFields() {

		return Tenants.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"tenantid\": \"" ).append( this.getTenantid() ).append( "\", " )
			.append( "\"name\": \"" ).append( this.getName() ).append( "\", " )
			.append( "\"virtualhost\": \"" ).append( this.getVirtualhost() ).append( "\", " )
			.append( "\"url\": \"" ).append( this.getUrl() ).append( "\", " )
			.append( "\"timezone\": \"" ).append( this.getTimezone() ).append( "\", " )
			.append( "\"active\": \"" ).append( this.getActive() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }