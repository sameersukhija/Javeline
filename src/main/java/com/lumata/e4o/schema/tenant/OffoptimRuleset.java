package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "offoptim_ruleset" )
public class OffoptimRuleset { 

	public enum Fields { ruleset_id, name, description, algorithm_id, token_type_id, token_resend_notification, offers_to_return, keep_offers_consistent, allow_redeemed_offers, allow_allocated_offers, allocation_time_validity_amount, allocation_time_validity_unit, redeem_expired_offer_behavior }

	@Column(
			table = "offoptim_ruleset",
			field = "ruleset_id",
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
			comment = "",
			getMethod = "getRulesetId",
			setMethod = "setRulesetId"
	)
	private Integer ruleset_id;

	@Column(
			table = "offoptim_ruleset",
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
			comment = "",
			getMethod = "getName",
			setMethod = "setName"
	)
	private String name;

	@Column(
			table = "offoptim_ruleset",
			field = "description",
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
			comment = "",
			getMethod = "getDescription",
			setMethod = "setDescription"
	)
	private String description;

	@Column(
			table = "offoptim_ruleset",
			field = "algorithm_id",
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
			comment = "",
			getMethod = "getAlgorithmId",
			setMethod = "setAlgorithmId"
	)
	private Integer algorithm_id;

	@Column(
			table = "offoptim_ruleset",
			field = "token_type_id",
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
			comment = "",
			getMethod = "getTokenTypeId",
			setMethod = "setTokenTypeId"
	)
	private Integer token_type_id;

	@Column(
			table = "offoptim_ruleset",
			field = "token_resend_notification",
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
			comment = "",
			getMethod = "getTokenResendNotification",
			setMethod = "setTokenResendNotification"
	)
	private String token_resend_notification;

	@Column(
			table = "offoptim_ruleset",
			field = "offers_to_return",
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
			comment = "",
			getMethod = "getOffersToReturn",
			setMethod = "setOffersToReturn"
	)
	private Integer offers_to_return;

	@Column(
			table = "offoptim_ruleset",
			field = "keep_offers_consistent",
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
			comment = "",
			getMethod = "getKeepOffersConsistent",
			setMethod = "setKeepOffersConsistent"
	)
	private Byte keep_offers_consistent;

	@Column(
			table = "offoptim_ruleset",
			field = "allow_redeemed_offers",
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
			comment = "",
			getMethod = "getAllowRedeemedOffers",
			setMethod = "setAllowRedeemedOffers"
	)
	private Byte allow_redeemed_offers;

	@Column(
			table = "offoptim_ruleset",
			field = "allow_allocated_offers",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "1",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getAllowAllocatedOffers",
			setMethod = "setAllowAllocatedOffers"
	)
	private Byte allow_allocated_offers;

	@Column(
			table = "offoptim_ruleset",
			field = "allocation_time_validity_amount",
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
			comment = "",
			getMethod = "getAllocationTimeValidityAmount",
			setMethod = "setAllocationTimeValidityAmount"
	)
	private Integer allocation_time_validity_amount;

	@Column(
			table = "offoptim_ruleset",
			field = "allocation_time_validity_unit",
			type = "enum('seconds','minutes','hours','days')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getAllocationTimeValidityUnit",
			setMethod = "setAllocationTimeValidityUnit"
	)
	private String allocation_time_validity_unit;

	@Column(
			table = "offoptim_ruleset",
			field = "redeem_expired_offer_behavior",
			type = "enum('pick_up_new_offer','bypass_offer_validity_date')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 2,
			comment = "",
			getMethod = "getRedeemExpiredOfferBehavior",
			setMethod = "setRedeemExpiredOfferBehavior"
	)
	private String redeem_expired_offer_behavior;


	public OffoptimRuleset() {} 

	public OffoptimRuleset( ResultSet rs ) throws SQLException {

		this.ruleset_id = rs.getInt( OffoptimRuleset.Fields.ruleset_id.name() );
		this.name = rs.getString( OffoptimRuleset.Fields.name.name() );
		this.description = rs.getString( OffoptimRuleset.Fields.description.name() );
		this.algorithm_id = rs.getInt( OffoptimRuleset.Fields.algorithm_id.name() );
		this.token_type_id = rs.getInt( OffoptimRuleset.Fields.token_type_id.name() );
		this.token_resend_notification = rs.getString( OffoptimRuleset.Fields.token_resend_notification.name() );
		this.offers_to_return = rs.getInt( OffoptimRuleset.Fields.offers_to_return.name() );
		this.keep_offers_consistent = rs.getByte( OffoptimRuleset.Fields.keep_offers_consistent.name() );
		this.allow_redeemed_offers = rs.getByte( OffoptimRuleset.Fields.allow_redeemed_offers.name() );
		this.allow_allocated_offers = rs.getByte( OffoptimRuleset.Fields.allow_allocated_offers.name() );
		this.allocation_time_validity_amount = rs.getInt( OffoptimRuleset.Fields.allocation_time_validity_amount.name() );
		this.allocation_time_validity_unit = rs.getString( OffoptimRuleset.Fields.allocation_time_validity_unit.name() );
		this.redeem_expired_offer_behavior = rs.getString( OffoptimRuleset.Fields.redeem_expired_offer_behavior.name() );

	}

	public OffoptimRuleset( JSONObject jo ) throws JSONException {

		this.ruleset_id = (int)jo.getInt( OffoptimRuleset.Fields.ruleset_id.name() );
		this.name = jo.getString( OffoptimRuleset.Fields.name.name() );
		this.description = jo.getString( OffoptimRuleset.Fields.description.name() );
		this.algorithm_id = (int)jo.getInt( OffoptimRuleset.Fields.algorithm_id.name() );
		this.token_type_id = (int)jo.getInt( OffoptimRuleset.Fields.token_type_id.name() );
		this.token_resend_notification = jo.getString( OffoptimRuleset.Fields.token_resend_notification.name() );
		this.offers_to_return = (int)jo.getInt( OffoptimRuleset.Fields.offers_to_return.name() );
		this.keep_offers_consistent = (byte)jo.getInt( OffoptimRuleset.Fields.keep_offers_consistent.name() );
		this.allow_redeemed_offers = (byte)jo.getInt( OffoptimRuleset.Fields.allow_redeemed_offers.name() );
		this.allow_allocated_offers = (byte)jo.getInt( OffoptimRuleset.Fields.allow_allocated_offers.name() );
		this.allocation_time_validity_amount = (int)jo.getInt( OffoptimRuleset.Fields.allocation_time_validity_amount.name() );
		this.allocation_time_validity_unit = jo.getString( OffoptimRuleset.Fields.allocation_time_validity_unit.name() );
		this.redeem_expired_offer_behavior = jo.getString( OffoptimRuleset.Fields.redeem_expired_offer_behavior.name() );

	}

	public Integer getRulesetId() {

		return this.ruleset_id;

	}

	public OffoptimRuleset setRulesetId( Integer ruleset_id ) {

		this.ruleset_id = ruleset_id;

		return this;

	}

	public String getName() {

		return this.name;

	}

	public OffoptimRuleset setName( String name ) {

		this.name = name;

		return this;

	}

	public String getDescription() {

		return this.description;

	}

	public OffoptimRuleset setDescription( String description ) {

		this.description = description;

		return this;

	}

	public Integer getAlgorithmId() {

		return this.algorithm_id;

	}

	public OffoptimRuleset setAlgorithmId( Integer algorithm_id ) {

		this.algorithm_id = algorithm_id;

		return this;

	}

	public Integer getTokenTypeId() {

		return this.token_type_id;

	}

	public OffoptimRuleset setTokenTypeId( Integer token_type_id ) {

		this.token_type_id = token_type_id;

		return this;

	}

	public String getTokenResendNotification() {

		return this.token_resend_notification;

	}

	public OffoptimRuleset setTokenResendNotification( String token_resend_notification ) {

		this.token_resend_notification = token_resend_notification;

		return this;

	}

	public Integer getOffersToReturn() {

		return this.offers_to_return;

	}

	public OffoptimRuleset setOffersToReturn( Integer offers_to_return ) {

		this.offers_to_return = offers_to_return;

		return this;

	}

	public Byte getKeepOffersConsistent() {

		return this.keep_offers_consistent;

	}

	public OffoptimRuleset setKeepOffersConsistent( Byte keep_offers_consistent ) {

		this.keep_offers_consistent = keep_offers_consistent;

		return this;

	}

	public Byte getAllowRedeemedOffers() {

		return this.allow_redeemed_offers;

	}

	public OffoptimRuleset setAllowRedeemedOffers( Byte allow_redeemed_offers ) {

		this.allow_redeemed_offers = allow_redeemed_offers;

		return this;

	}

	public Byte getAllowAllocatedOffers() {

		return this.allow_allocated_offers;

	}

	public OffoptimRuleset setAllowAllocatedOffers( Byte allow_allocated_offers ) {

		this.allow_allocated_offers = allow_allocated_offers;

		return this;

	}

	public Integer getAllocationTimeValidityAmount() {

		return this.allocation_time_validity_amount;

	}

	public OffoptimRuleset setAllocationTimeValidityAmount( Integer allocation_time_validity_amount ) {

		this.allocation_time_validity_amount = allocation_time_validity_amount;

		return this;

	}

	public String getAllocationTimeValidityUnit() {

		return this.allocation_time_validity_unit;

	}

	public OffoptimRuleset setAllocationTimeValidityUnit( String allocation_time_validity_unit ) {

		this.allocation_time_validity_unit = allocation_time_validity_unit;

		return this;

	}

	public String getRedeemExpiredOfferBehavior() {

		return this.redeem_expired_offer_behavior;

	}

	public OffoptimRuleset setRedeemExpiredOfferBehavior( String redeem_expired_offer_behavior ) {

		this.redeem_expired_offer_behavior = redeem_expired_offer_behavior;

		return this;

	}

	public Fields[] getEntityFields() {

		return OffoptimRuleset.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"ruleset_id\": \"" ).append( this.getRulesetId() ).append( "\", " )
			.append( "\"name\": \"" ).append( this.getName() ).append( "\", " )
			.append( "\"description\": \"" ).append( this.getDescription() ).append( "\", " )
			.append( "\"algorithm_id\": \"" ).append( this.getAlgorithmId() ).append( "\", " )
			.append( "\"token_type_id\": \"" ).append( this.getTokenTypeId() ).append( "\", " )
			.append( "\"token_resend_notification\": \"" ).append( this.getTokenResendNotification() ).append( "\", " )
			.append( "\"offers_to_return\": \"" ).append( this.getOffersToReturn() ).append( "\", " )
			.append( "\"keep_offers_consistent\": \"" ).append( this.getKeepOffersConsistent() ).append( "\", " )
			.append( "\"allow_redeemed_offers\": \"" ).append( this.getAllowRedeemedOffers() ).append( "\", " )
			.append( "\"allow_allocated_offers\": \"" ).append( this.getAllowAllocatedOffers() ).append( "\", " )
			.append( "\"allocation_time_validity_amount\": \"" ).append( this.getAllocationTimeValidityAmount() ).append( "\", " )
			.append( "\"allocation_time_validity_unit\": \"" ).append( this.getAllocationTimeValidityUnit() ).append( "\", " )
			.append( "\"redeem_expired_offer_behavior\": \"" ).append( this.getRedeemExpiredOfferBehavior() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }