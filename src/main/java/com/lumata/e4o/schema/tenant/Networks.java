package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "networks" )
public class Networks { 

	public enum Fields { network_id, network }

	@Column(
			table = "networks",
			field = "network_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getNetworkId",
			setMethod = "setNetworkId"
	)
	private Byte network_id;

	@Column(
			table = "networks",
			field = "network",
			type = "varchar(45)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 45,
			getMethod = "getNetwork",
			setMethod = "setNetwork"
	)
	private String network;


	public Networks() {} 

	public Networks( ResultSet rs ) throws SQLException {

		this.network_id = rs.getByte( Networks.Fields.network_id.name() );
		this.network = rs.getString( Networks.Fields.network.name() );

	}

	public Networks( JSONObject jo ) throws JSONException {

		this.network_id = (byte)jo.getInt( Networks.Fields.network_id.name() );
		this.network = jo.getString( Networks.Fields.network.name() );

	}

	public Byte getNetworkId() {

		return this.network_id;

	}

	public void setNetworkId( Byte network_id ) {

		this.network_id = network_id;

	}

	public String getNetwork() {

		return this.network;

	}

	public void setNetwork( String network ) {

		this.network = network;

	}

	public Fields[] getEntityFields() {

		return Networks.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"network_id\": \"" ).append( this.getNetworkId() ).append( "\", " )
			.append( "\"network\": \"" ).append( this.getNetwork() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }