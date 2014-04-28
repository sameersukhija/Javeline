package com.lumata.e4o.schema.dm;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "dm_rmi_server" )
public class DmRmiServer { 

	public enum Fields { rmi_server_id, ip, port, hostname, active }

	@Column(
			table = "dm_rmi_server",
			field = "rmi_server_id",
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
			getMethod = "getRmiServerId",
			setMethod = "setRmiServerId"
	)
	private Integer rmi_server_id;

	@Column(
			table = "dm_rmi_server",
			field = "ip",
			type = "varchar(16)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 16,
			getMethod = "getIp",
			setMethod = "setIp"
	)
	private String ip;

	@Column(
			table = "dm_rmi_server",
			field = "port",
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
			getMethod = "getPort",
			setMethod = "setPort"
	)
	private Integer port;

	@Column(
			table = "dm_rmi_server",
			field = "hostname",
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
			getMethod = "getHostname",
			setMethod = "setHostname"
	)
	private String hostname;

	@Column(
			table = "dm_rmi_server",
			field = "active",
			type = "enum('0','1')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 2,
			getMethod = "getActive",
			setMethod = "setActive"
	)
	private String active;


	public DmRmiServer() {} 

	public DmRmiServer( ResultSet rs ) throws SQLException {

		this.rmi_server_id = rs.getInt( DmRmiServer.Fields.rmi_server_id.name() );
		this.ip = rs.getString( DmRmiServer.Fields.ip.name() );
		this.port = rs.getInt( DmRmiServer.Fields.port.name() );
		this.hostname = rs.getString( DmRmiServer.Fields.hostname.name() );
		this.active = rs.getString( DmRmiServer.Fields.active.name() );

	}

	public DmRmiServer( JSONObject jo ) throws JSONException {

		this.rmi_server_id = (int)jo.getInt( DmRmiServer.Fields.rmi_server_id.name() );
		this.ip = jo.getString( DmRmiServer.Fields.ip.name() );
		this.port = (int)jo.getInt( DmRmiServer.Fields.port.name() );
		this.hostname = jo.getString( DmRmiServer.Fields.hostname.name() );
		this.active = jo.getString( DmRmiServer.Fields.active.name() );

	}

	public Integer getRmiServerId() {

		return this.rmi_server_id;

	}

	public void setRmiServerId( Integer rmi_server_id ) {

		this.rmi_server_id = rmi_server_id;

	}

	public String getIp() {

		return this.ip;

	}

	public void setIp( String ip ) {

		this.ip = ip;

	}

	public Integer getPort() {

		return this.port;

	}

	public void setPort( Integer port ) {

		this.port = port;

	}

	public String getHostname() {

		return this.hostname;

	}

	public void setHostname( String hostname ) {

		this.hostname = hostname;

	}

	public String getActive() {

		return this.active;

	}

	public void setActive( String active ) {

		this.active = active;

	}

	public Fields[] getEntityFields() {

		return DmRmiServer.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"rmi_server_id\": \"" ).append( this.getRmiServerId() ).append( "\", " )
			.append( "\"ip\": \"" ).append( this.getIp() ).append( "\", " )
			.append( "\"port\": \"" ).append( this.getPort() ).append( "\", " )
			.append( "\"hostname\": \"" ).append( this.getHostname() ).append( "\", " )
			.append( "\"active\": \"" ).append( this.getActive() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }