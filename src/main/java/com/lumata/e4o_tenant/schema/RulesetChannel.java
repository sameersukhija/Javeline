package com.lumata.e4o_tenant.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "ruleset_channel" )
public class RulesetChannel { 

	public enum Fields { ruleset_id, channel_id }

	@Column(
			table = "ruleset_channel",
			field = "ruleset_id",
			type = "int(10) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 10,
			getMethod = "getRulesetId",
			setMethod = "setRulesetId"
	)
	private Integer ruleset_id;

	@Column(
			table = "ruleset_channel",
			field = "channel_id",
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
			getMethod = "getChannelId",
			setMethod = "setChannelId"
	)
	private Byte channel_id;


	public RulesetChannel() {} 

	public RulesetChannel( ResultSet rs ) throws SQLException {

		this.ruleset_id = rs.getInt( RulesetChannel.Fields.ruleset_id.name() );
		this.channel_id = rs.getByte( RulesetChannel.Fields.channel_id.name() );

	}

	public RulesetChannel( JSONObject jo ) throws JSONException {

		this.ruleset_id = (int)jo.getInt( RulesetChannel.Fields.ruleset_id.name() );
		this.channel_id = (byte)jo.getInt( RulesetChannel.Fields.channel_id.name() );

	}

	public Integer getRulesetId() {

		return this.ruleset_id;

	}

	public void setRulesetId( Integer ruleset_id ) {

		this.ruleset_id = ruleset_id;

	}

	public Byte getChannelId() {

		return this.channel_id;

	}

	public void setChannelId( Byte channel_id ) {

		this.channel_id = channel_id;

	}

	public Fields[] getEntityFields() {

		return RulesetChannel.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"ruleset_id\": \"" ).append( this.getRulesetId() ).append( "\", " )
			.append( "\"channel_id\": \"" ).append( this.getChannelId() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }