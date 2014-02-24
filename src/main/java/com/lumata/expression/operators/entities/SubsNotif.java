package com.lumata.expression.operators.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "subs_notif" )
public class SubsNotif { 

	public enum Fields { msisdn, channel_id, address }

	@Column(
			table = "subs_notif",
			field = "msisdn",
			type = "bigint(20)",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getMsisdn",
			setMethod = "setMsisdn"
	)
	private Long msisdn;

	@Column(
			table = "subs_notif",
			field = "channel_id",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getChannelId",
			setMethod = "setChannelId"
	)
	private Byte channel_id;

	@Column(
			table = "subs_notif",
			field = "address",
			type = "varchar(100)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 100,
			getMethod = "getAddress",
			setMethod = "setAddress"
	)
	private String address;


	public SubsNotif() {} 

	public SubsNotif( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( SubsNotif.Fields.msisdn.name() );
		this.channel_id = rs.getByte( SubsNotif.Fields.channel_id.name() );
		this.address = rs.getString( SubsNotif.Fields.address.name() );

	}

	public SubsNotif( JSONObject jo ) throws JSONException {

		this.msisdn = (long)jo.getLong( SubsNotif.Fields.msisdn.name() );
		this.channel_id = (byte)jo.getInt( SubsNotif.Fields.channel_id.name() );
		this.address = jo.getString( SubsNotif.Fields.address.name() );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public void setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

	}

	public Byte getChannelId() {

		return this.channel_id;

	}

	public void setChannelId( Byte channel_id ) {

		this.channel_id = channel_id;

	}

	public String getAddress() {

		return this.address;

	}

	public void setAddress( String address ) {

		this.address = address;

	}

	public Fields[] getEntityFields() {

		return SubsNotif.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"channel_id\": \"" ).append( this.getChannelId() ).append( "\", " )
			.append( "\"address\": \"" ).append( this.getAddress() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }