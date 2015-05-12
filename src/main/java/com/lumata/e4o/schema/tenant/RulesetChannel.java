package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "ruleset_channel" )
public class RulesetChannel { 

	public enum Fields { ruleset_id, channel_id, mandatory, max_offer, priority }

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
			comment = "it represents the identifier of the ruleset",
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
			comment = "it represents the identifier of the channel",
			getMethod = "getChannelId",
			setMethod = "setChannelId"
	)
	private Byte channel_id;

	@Column(
			table = "ruleset_channel",
			field = "mandatory",
			type = "tinyint(1) unsigned",
			mysqlType = "tinyint",
			javaType = "Boolean",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 1,
			comment = "it represents if channel is mandatory or not",
			getMethod = "getMandatory",
			setMethod = "setMandatory"
	)
	private Boolean mandatory;

	@Column(
			table = "ruleset_channel",
			field = "max_offer",
			type = "int(3)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "-1",
			extra = "",
			length = 3,
			comment = "it represents the maximum number of offer fir this channel",
			getMethod = "getMaxOffer",
			setMethod = "setMaxOffer"
	)
	private Integer max_offer;

	@Column(
			table = "ruleset_channel",
			field = "priority",
			type = "tinyint(3)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "1",
			extra = "",
			length = 3,
			comment = "represents priority to order channels EFOGC-2752",
			getMethod = "getPriority",
			setMethod = "setPriority"
	)
	private Byte priority;


	public RulesetChannel() {} 

	public RulesetChannel( ResultSet rs ) throws SQLException {

		this.ruleset_id = rs.getInt( RulesetChannel.Fields.ruleset_id.name() );
		this.channel_id = rs.getByte( RulesetChannel.Fields.channel_id.name() );
		this.mandatory = rs.getBoolean( RulesetChannel.Fields.mandatory.name() );
		this.max_offer = rs.getInt( RulesetChannel.Fields.max_offer.name() );
		this.priority = rs.getByte( RulesetChannel.Fields.priority.name() );

	}

	public RulesetChannel( JSONObject jo ) throws JSONException {

		this.ruleset_id = (int)jo.getInt( RulesetChannel.Fields.ruleset_id.name() );
		this.channel_id = (byte)jo.getInt( RulesetChannel.Fields.channel_id.name() );
		this.mandatory = jo.getBoolean( RulesetChannel.Fields.mandatory.name() );
		this.max_offer = (int)jo.getInt( RulesetChannel.Fields.max_offer.name() );
		this.priority = (byte)jo.getInt( RulesetChannel.Fields.priority.name() );

	}

	public Integer getRulesetId() {

		return this.ruleset_id;

	}

	public RulesetChannel setRulesetId( Integer ruleset_id ) {

		this.ruleset_id = ruleset_id;

		return this;

	}

	public Byte getChannelId() {

		return this.channel_id;

	}

	public RulesetChannel setChannelId( Byte channel_id ) {

		this.channel_id = channel_id;

		return this;

	}

	public Boolean getMandatory() {

		return this.mandatory;

	}

	public RulesetChannel setMandatory( Boolean mandatory ) {

		this.mandatory = mandatory;

		return this;

	}

	public Integer getMaxOffer() {

		return this.max_offer;

	}

	public RulesetChannel setMaxOffer( Integer max_offer ) {

		this.max_offer = max_offer;

		return this;

	}

	public Byte getPriority() {

		return this.priority;

	}

	public RulesetChannel setPriority( Byte priority ) {

		this.priority = priority;

		return this;

	}

	public Fields[] getEntityFields() {

		return RulesetChannel.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"ruleset_id\": \"" ).append( this.getRulesetId() ).append( "\", " )
			.append( "\"channel_id\": \"" ).append( this.getChannelId() ).append( "\", " )
			.append( "\"mandatory\": \"" ).append( this.getMandatory() ).append( "\", " )
			.append( "\"max_offer\": \"" ).append( this.getMaxOffer() ).append( "\", " )
			.append( "\"priority\": \"" ).append( this.getPriority() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }