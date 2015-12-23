package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "offoptim_ruleset_channel" )
public class OffoptimRulesetChannel { 

	public enum Fields { ruleset_id, channel_id, mandatory, max_offer, priority, max_offer_in_period, limit_period, limit_period_value }

	@Column(
			table = "offoptim_ruleset_channel",
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
			table = "offoptim_ruleset_channel",
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
			table = "offoptim_ruleset_channel",
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
			table = "offoptim_ruleset_channel",
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
			table = "offoptim_ruleset_channel",
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

	@Column(
			table = "offoptim_ruleset_channel",
			field = "max_offer_in_period",
			type = "int(10)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 10,
			comment = "it represents max offer per channel in a given period",
			getMethod = "getMaxOfferInPeriod",
			setMethod = "setMaxOfferInPeriod"
	)
	private Integer max_offer_in_period;

	@Column(
			table = "offoptim_ruleset_channel",
			field = "limit_period",
			type = "enum('day','week','month')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			comment = "it represents the period",
			getMethod = "getLimitPeriod",
			setMethod = "setLimitPeriod"
	)
	private String limit_period;

	@Column(
			table = "offoptim_ruleset_channel",
			field = "limit_period_value",
			type = "tinyint(1)",
			mysqlType = "tinyint",
			javaType = "Boolean",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 1,
			comment = "it represents the value of the period",
			getMethod = "getLimitPeriodValue",
			setMethod = "setLimitPeriodValue"
	)
	private Boolean limit_period_value;


	public OffoptimRulesetChannel() {} 

	public OffoptimRulesetChannel( ResultSet rs ) throws SQLException {

		this.ruleset_id = rs.getInt( OffoptimRulesetChannel.Fields.ruleset_id.name() );
		this.channel_id = rs.getByte( OffoptimRulesetChannel.Fields.channel_id.name() );
		this.mandatory = rs.getBoolean( OffoptimRulesetChannel.Fields.mandatory.name() );
		this.max_offer = rs.getInt( OffoptimRulesetChannel.Fields.max_offer.name() );
		this.priority = rs.getByte( OffoptimRulesetChannel.Fields.priority.name() );
		this.max_offer_in_period = rs.getInt( OffoptimRulesetChannel.Fields.max_offer_in_period.name() );
		this.limit_period = rs.getString( OffoptimRulesetChannel.Fields.limit_period.name() );
		this.limit_period_value = rs.getBoolean( OffoptimRulesetChannel.Fields.limit_period_value.name() );

	}

	public OffoptimRulesetChannel( JSONObject jo ) throws JSONException {

		this.ruleset_id = (int)jo.getInt( OffoptimRulesetChannel.Fields.ruleset_id.name() );
		this.channel_id = (byte)jo.getInt( OffoptimRulesetChannel.Fields.channel_id.name() );
		this.mandatory = jo.getBoolean( OffoptimRulesetChannel.Fields.mandatory.name() );
		this.max_offer = (int)jo.getInt( OffoptimRulesetChannel.Fields.max_offer.name() );
		this.priority = (byte)jo.getInt( OffoptimRulesetChannel.Fields.priority.name() );
		this.max_offer_in_period = (int)jo.getInt( OffoptimRulesetChannel.Fields.max_offer_in_period.name() );
		this.limit_period = jo.getString( OffoptimRulesetChannel.Fields.limit_period.name() );
		this.limit_period_value = jo.getBoolean( OffoptimRulesetChannel.Fields.limit_period_value.name() );

	}

	public Integer getRulesetId() {

		return this.ruleset_id;

	}

	public OffoptimRulesetChannel setRulesetId( Integer ruleset_id ) {

		this.ruleset_id = ruleset_id;

		return this;

	}

	public Byte getChannelId() {

		return this.channel_id;

	}

	public OffoptimRulesetChannel setChannelId( Byte channel_id ) {

		this.channel_id = channel_id;

		return this;

	}

	public Boolean getMandatory() {

		return this.mandatory;

	}

	public OffoptimRulesetChannel setMandatory( Boolean mandatory ) {

		this.mandatory = mandatory;

		return this;

	}

	public Integer getMaxOffer() {

		return this.max_offer;

	}

	public OffoptimRulesetChannel setMaxOffer( Integer max_offer ) {

		this.max_offer = max_offer;

		return this;

	}

	public Byte getPriority() {

		return this.priority;

	}

	public OffoptimRulesetChannel setPriority( Byte priority ) {

		this.priority = priority;

		return this;

	}

	public Integer getMaxOfferInPeriod() {

		return this.max_offer_in_period;

	}

	public OffoptimRulesetChannel setMaxOfferInPeriod( Integer max_offer_in_period ) {

		this.max_offer_in_period = max_offer_in_period;

		return this;

	}

	public String getLimitPeriod() {

		return this.limit_period;

	}

	public OffoptimRulesetChannel setLimitPeriod( String limit_period ) {

		this.limit_period = limit_period;

		return this;

	}

	public Boolean getLimitPeriodValue() {

		return this.limit_period_value;

	}

	public OffoptimRulesetChannel setLimitPeriodValue( Boolean limit_period_value ) {

		this.limit_period_value = limit_period_value;

		return this;

	}

	public Fields[] getEntityFields() {

		return OffoptimRulesetChannel.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"ruleset_id\": \"" ).append( this.getRulesetId() ).append( "\", " )
			.append( "\"channel_id\": \"" ).append( this.getChannelId() ).append( "\", " )
			.append( "\"mandatory\": \"" ).append( this.getMandatory() ).append( "\", " )
			.append( "\"max_offer\": \"" ).append( this.getMaxOffer() ).append( "\", " )
			.append( "\"priority\": \"" ).append( this.getPriority() ).append( "\", " )
			.append( "\"max_offer_in_period\": \"" ).append( this.getMaxOfferInPeriod() ).append( "\", " )
			.append( "\"limit_period\": \"" ).append( this.getLimitPeriod() ).append( "\", " )
			.append( "\"limit_period_value\": \"" ).append( this.getLimitPeriodValue() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }