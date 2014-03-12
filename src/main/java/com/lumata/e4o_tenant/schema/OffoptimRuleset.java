package com.lumata.e4o_tenant.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "offoptim_ruleset" )
public class OffoptimRuleset { 

	public enum Fields { ruleset_id, name, description, algorithm_id, token_type_id, offers_to_return, allow_duplicate_offers, keep_offers_consistent, allow_redeemed_offers, allocation_time_validity_amount, allocation_time_validity_unit }

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
			getMethod = "getRulesetId",
			setMethod = "setRulesetId"
	)
	private Integer ruleset_id;

	@Column(
			table = "offoptim_ruleset",
			field = "name",
			type = "varchar(20)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 20,
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
			getMethod = "getTokenTypeId",
			setMethod = "setTokenTypeId"
	)
	private Integer token_type_id;

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
			getMethod = "getOffersToReturn",
			setMethod = "setOffersToReturn"
	)
	private Integer offers_to_return;

	@Column(
			table = "offoptim_ruleset",
			field = "allow_duplicate_offers",
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
			getMethod = "getAllowDuplicateOffers",
			setMethod = "setAllowDuplicateOffers"
	)
	private Byte allow_duplicate_offers;

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
			getMethod = "getAllowRedeemedOffers",
			setMethod = "setAllowRedeemedOffers"
	)
	private Byte allow_redeemed_offers;

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
			getMethod = "getAllocationTimeValidityUnit",
			setMethod = "setAllocationTimeValidityUnit"
	)
	private String allocation_time_validity_unit;


	public OffoptimRuleset() {} 

	public OffoptimRuleset( ResultSet rs ) throws SQLException {

		this.ruleset_id = rs.getInt( OffoptimRuleset.Fields.ruleset_id.name() );
		this.name = rs.getString( OffoptimRuleset.Fields.name.name() );
		this.description = rs.getString( OffoptimRuleset.Fields.description.name() );
		this.algorithm_id = rs.getInt( OffoptimRuleset.Fields.algorithm_id.name() );
		this.token_type_id = rs.getInt( OffoptimRuleset.Fields.token_type_id.name() );
		this.offers_to_return = rs.getInt( OffoptimRuleset.Fields.offers_to_return.name() );
		this.allow_duplicate_offers = rs.getByte( OffoptimRuleset.Fields.allow_duplicate_offers.name() );
		this.keep_offers_consistent = rs.getByte( OffoptimRuleset.Fields.keep_offers_consistent.name() );
		this.allow_redeemed_offers = rs.getByte( OffoptimRuleset.Fields.allow_redeemed_offers.name() );
		this.allocation_time_validity_amount = rs.getInt( OffoptimRuleset.Fields.allocation_time_validity_amount.name() );
		this.allocation_time_validity_unit = rs.getString( OffoptimRuleset.Fields.allocation_time_validity_unit.name() );

	}

	public OffoptimRuleset( JSONObject jo ) throws JSONException {

		this.ruleset_id = (int)jo.getInt( OffoptimRuleset.Fields.ruleset_id.name() );
		this.name = jo.getString( OffoptimRuleset.Fields.name.name() );
		this.description = jo.getString( OffoptimRuleset.Fields.description.name() );
		this.algorithm_id = (int)jo.getInt( OffoptimRuleset.Fields.algorithm_id.name() );
		this.token_type_id = (int)jo.getInt( OffoptimRuleset.Fields.token_type_id.name() );
		this.offers_to_return = (int)jo.getInt( OffoptimRuleset.Fields.offers_to_return.name() );
		this.allow_duplicate_offers = (byte)jo.getInt( OffoptimRuleset.Fields.allow_duplicate_offers.name() );
		this.keep_offers_consistent = (byte)jo.getInt( OffoptimRuleset.Fields.keep_offers_consistent.name() );
		this.allow_redeemed_offers = (byte)jo.getInt( OffoptimRuleset.Fields.allow_redeemed_offers.name() );
		this.allocation_time_validity_amount = (int)jo.getInt( OffoptimRuleset.Fields.allocation_time_validity_amount.name() );
		this.allocation_time_validity_unit = jo.getString( OffoptimRuleset.Fields.allocation_time_validity_unit.name() );

	}

	public Integer getRulesetId() {

		return this.ruleset_id;

	}

	public void setRulesetId( Integer ruleset_id ) {

		this.ruleset_id = ruleset_id;

	}

	public String getName() {

		return this.name;

	}

	public void setName( String name ) {

		this.name = name;

	}

	public String getDescription() {

		return this.description;

	}

	public void setDescription( String description ) {

		this.description = description;

	}

	public Integer getAlgorithmId() {

		return this.algorithm_id;

	}

	public void setAlgorithmId( Integer algorithm_id ) {

		this.algorithm_id = algorithm_id;

	}

	public Integer getTokenTypeId() {

		return this.token_type_id;

	}

	public void setTokenTypeId( Integer token_type_id ) {

		this.token_type_id = token_type_id;

	}

	public Integer getOffersToReturn() {

		return this.offers_to_return;

	}

	public void setOffersToReturn( Integer offers_to_return ) {

		this.offers_to_return = offers_to_return;

	}

	public Byte getAllowDuplicateOffers() {

		return this.allow_duplicate_offers;

	}

	public void setAllowDuplicateOffers( Byte allow_duplicate_offers ) {

		this.allow_duplicate_offers = allow_duplicate_offers;

	}

	public Byte getKeepOffersConsistent() {

		return this.keep_offers_consistent;

	}

	public void setKeepOffersConsistent( Byte keep_offers_consistent ) {

		this.keep_offers_consistent = keep_offers_consistent;

	}

	public Byte getAllowRedeemedOffers() {

		return this.allow_redeemed_offers;

	}

	public void setAllowRedeemedOffers( Byte allow_redeemed_offers ) {

		this.allow_redeemed_offers = allow_redeemed_offers;

	}

	public Integer getAllocationTimeValidityAmount() {

		return this.allocation_time_validity_amount;

	}

	public void setAllocationTimeValidityAmount( Integer allocation_time_validity_amount ) {

		this.allocation_time_validity_amount = allocation_time_validity_amount;

	}

	public String getAllocationTimeValidityUnit() {

		return this.allocation_time_validity_unit;

	}

	public void setAllocationTimeValidityUnit( String allocation_time_validity_unit ) {

		this.allocation_time_validity_unit = allocation_time_validity_unit;

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
			.append( "\"offers_to_return\": \"" ).append( this.getOffersToReturn() ).append( "\", " )
			.append( "\"allow_duplicate_offers\": \"" ).append( this.getAllowDuplicateOffers() ).append( "\", " )
			.append( "\"keep_offers_consistent\": \"" ).append( this.getKeepOffersConsistent() ).append( "\", " )
			.append( "\"allow_redeemed_offers\": \"" ).append( this.getAllowRedeemedOffers() ).append( "\", " )
			.append( "\"allocation_time_validity_amount\": \"" ).append( this.getAllocationTimeValidityAmount() ).append( "\", " )
			.append( "\"allocation_time_validity_unit\": \"" ).append( this.getAllocationTimeValidityUnit() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }