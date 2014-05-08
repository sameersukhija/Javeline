package com.lumata.e4o.schema.dialogmanager;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "dm_reddot_server" )
public class DmReddotServer { 

	public enum Fields { reddot_server_id, destination_reddot_ip, destination_reddot_port, source_hostname, active }

	@Column(
			table = "dm_reddot_server",
			field = "reddot_server_id",
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
			getMethod = "getReddotServerId",
			setMethod = "setReddotServerId"
	)
	private Integer reddot_server_id;

	@Column(
			table = "dm_reddot_server",
			field = "destination_reddot_ip",
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
			getMethod = "getDestinationReddotIp",
			setMethod = "setDestinationReddotIp"
	)
	private String destination_reddot_ip;

	@Column(
			table = "dm_reddot_server",
			field = "destination_reddot_port",
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
			getMethod = "getDestinationReddotPort",
			setMethod = "setDestinationReddotPort"
	)
	private Integer destination_reddot_port;

	@Column(
			table = "dm_reddot_server",
			field = "source_hostname",
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
			getMethod = "getSourceHostname",
			setMethod = "setSourceHostname"
	)
	private String source_hostname;

	@Column(
			table = "dm_reddot_server",
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


	public DmReddotServer() {} 

	public DmReddotServer( ResultSet rs ) throws SQLException {

		this.reddot_server_id = rs.getInt( DmReddotServer.Fields.reddot_server_id.name() );
		this.destination_reddot_ip = rs.getString( DmReddotServer.Fields.destination_reddot_ip.name() );
		this.destination_reddot_port = rs.getInt( DmReddotServer.Fields.destination_reddot_port.name() );
		this.source_hostname = rs.getString( DmReddotServer.Fields.source_hostname.name() );
		this.active = rs.getString( DmReddotServer.Fields.active.name() );

	}

	public DmReddotServer( JSONObject jo ) throws JSONException {

		this.reddot_server_id = (int)jo.getInt( DmReddotServer.Fields.reddot_server_id.name() );
		this.destination_reddot_ip = jo.getString( DmReddotServer.Fields.destination_reddot_ip.name() );
		this.destination_reddot_port = (int)jo.getInt( DmReddotServer.Fields.destination_reddot_port.name() );
		this.source_hostname = jo.getString( DmReddotServer.Fields.source_hostname.name() );
		this.active = jo.getString( DmReddotServer.Fields.active.name() );

	}

	public Integer getReddotServerId() {

		return this.reddot_server_id;

	}

	public void setReddotServerId( Integer reddot_server_id ) {

		this.reddot_server_id = reddot_server_id;

	}

	public String getDestinationReddotIp() {

		return this.destination_reddot_ip;

	}

	public void setDestinationReddotIp( String destination_reddot_ip ) {

		this.destination_reddot_ip = destination_reddot_ip;

	}

	public Integer getDestinationReddotPort() {

		return this.destination_reddot_port;

	}

	public void setDestinationReddotPort( Integer destination_reddot_port ) {

		this.destination_reddot_port = destination_reddot_port;

	}

	public String getSourceHostname() {

		return this.source_hostname;

	}

	public void setSourceHostname( String source_hostname ) {

		this.source_hostname = source_hostname;

	}

	public String getActive() {

		return this.active;

	}

	public void setActive( String active ) {

		this.active = active;

	}

	public Fields[] getEntityFields() {

		return DmReddotServer.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"reddot_server_id\": \"" ).append( this.getReddotServerId() ).append( "\", " )
			.append( "\"destination_reddot_ip\": \"" ).append( this.getDestinationReddotIp() ).append( "\", " )
			.append( "\"destination_reddot_port\": \"" ).append( this.getDestinationReddotPort() ).append( "\", " )
			.append( "\"source_hostname\": \"" ).append( this.getSourceHostname() ).append( "\", " )
			.append( "\"active\": \"" ).append( this.getActive() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }