package com.lumata.e4o_tenant.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "bonuses" )
public class Bonuses { 

	public enum Fields { bonus_id, bonus_name, unit, operations, in_credit, handleOptOut, inFeedbackRequired, commodity_type, account_type, max_value, default_validity_type, default_period_type, default_qty_period, default_fixed_validity_from, unitary_cost, recommended_price, expiryNotifDays, expiryNotif }

	@Column(
			table = "bonuses",
			field = "bonus_id",
			type = "smallint(3) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = true,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 3,
			getMethod = "getBonusId",
			setMethod = "setBonusId"
	)
	private Short bonus_id;

	@Column(
			table = "bonuses",
			field = "bonus_name",
			type = "varchar(30)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 30,
			getMethod = "getBonusName",
			setMethod = "setBonusName"
	)
	private String bonus_name;

	@Column(
			table = "bonuses",
			field = "unit",
			type = "varchar(15)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "none",
			extra = "",
			length = 15,
			getMethod = "getUnit",
			setMethod = "setUnit"
	)
	private String unit;

	@Column(
			table = "bonuses",
			field = "operations",
			type = "set('CREDIT','DEBIT','SET','ACTIVATE','DEACTIVATE')",
			mysqlType = "set",
			javaType = "Set",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 5,
			getMethod = "getOperations",
			setMethod = "setOperations"
	)
	private String operations;

	@Column(
			table = "bonuses",
			field = "in_credit",
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
			getMethod = "getInCredit",
			setMethod = "setInCredit"
	)
	private Boolean in_credit;

	@Column(
			table = "bonuses",
			field = "handleOptOut",
			type = "varchar(5)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "false",
			extra = "",
			length = 5,
			getMethod = "getHandleOptOut",
			setMethod = "setHandleOptOut"
	)
	private String handleOptOut;

	@Column(
			table = "bonuses",
			field = "inFeedbackRequired",
			type = "varchar(5)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "false",
			extra = "",
			length = 5,
			getMethod = "getInFeedbackRequired",
			setMethod = "setInFeedbackRequired"
	)
	private String inFeedbackRequired;

	@Column(
			table = "bonuses",
			field = "commodity_type",
			type = "varchar(20)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getCommodityType",
			setMethod = "setCommodityType"
	)
	private String commodity_type;

	@Column(
			table = "bonuses",
			field = "account_type",
			type = "varchar(25)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 25,
			getMethod = "getAccountType",
			setMethod = "setAccountType"
	)
	private String account_type;

	@Column(
			table = "bonuses",
			field = "max_value",
			type = "mediumint(9)",
			mysqlType = "mediumint",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 9,
			getMethod = "getMaxValue",
			setMethod = "setMaxValue"
	)
	private Integer max_value;

	@Column(
			table = "bonuses",
			field = "default_validity_type",
			type = "enum('Fixed','Variable_Days','Variable_Weeks','Variable_Months','Variable_Quarters')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 5,
			getMethod = "getDefaultValidityType",
			setMethod = "setDefaultValidityType"
	)
	private String default_validity_type;

	@Column(
			table = "bonuses",
			field = "default_period_type",
			type = "enum('Days','Weeks','Months','Quarters','Years')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 5,
			getMethod = "getDefaultPeriodType",
			setMethod = "setDefaultPeriodType"
	)
	private String default_period_type;

	@Column(
			table = "bonuses",
			field = "default_qty_period",
			type = "smallint(6)",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "1",
			extra = "",
			length = 6,
			getMethod = "getDefaultQtyPeriod",
			setMethod = "setDefaultQtyPeriod"
	)
	private Short default_qty_period;

	@Column(
			table = "bonuses",
			field = "default_fixed_validity_from",
			type = "enum('BEGINNING_OF_CURRENT_PERIOD','BEGINNING_OF_NEXT_PERIOD','LAST_SUBSCRIPTION_ANNIVERSARY_DATE')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getDefaultFixedValidityFrom",
			setMethod = "setDefaultFixedValidityFrom"
	)
	private String default_fixed_validity_from;

	@Column(
			table = "bonuses",
			field = "unitary_cost",
			type = "float",
			mysqlType = "float",
			javaType = "Float",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getUnitaryCost",
			setMethod = "setUnitaryCost"
	)
	private Float unitary_cost;

	@Column(
			table = "bonuses",
			field = "recommended_price",
			type = "float",
			mysqlType = "float",
			javaType = "Float",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getRecommendedPrice",
			setMethod = "setRecommendedPrice"
	)
	private Float recommended_price;

	@Column(
			table = "bonuses",
			field = "expiryNotifDays",
			type = "smallint(3) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getExpiryNotifDays",
			setMethod = "setExpiryNotifDays"
	)
	private Short expiryNotifDays;

	@Column(
			table = "bonuses",
			field = "expiryNotif",
			type = "blob",
			mysqlType = "blob",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getExpiryNotif",
			setMethod = "setExpiryNotif"
	)
	private String expiryNotif;


	public Bonuses() {} 

	public Bonuses( ResultSet rs ) throws SQLException {

		this.bonus_id = rs.getShort( Bonuses.Fields.bonus_id.name() );
		this.bonus_name = rs.getString( Bonuses.Fields.bonus_name.name() );
		this.unit = rs.getString( Bonuses.Fields.unit.name() );
		this.operations = rs.getString( Bonuses.Fields.operations.name() );
		this.in_credit = rs.getBoolean( Bonuses.Fields.in_credit.name() );
		this.handleOptOut = rs.getString( Bonuses.Fields.handleOptOut.name() );
		this.inFeedbackRequired = rs.getString( Bonuses.Fields.inFeedbackRequired.name() );
		this.commodity_type = rs.getString( Bonuses.Fields.commodity_type.name() );
		this.account_type = rs.getString( Bonuses.Fields.account_type.name() );
		this.max_value = rs.getInt( Bonuses.Fields.max_value.name() );
		this.default_validity_type = rs.getString( Bonuses.Fields.default_validity_type.name() );
		this.default_period_type = rs.getString( Bonuses.Fields.default_period_type.name() );
		this.default_qty_period = rs.getShort( Bonuses.Fields.default_qty_period.name() );
		this.default_fixed_validity_from = rs.getString( Bonuses.Fields.default_fixed_validity_from.name() );
		this.unitary_cost = rs.getFloat( Bonuses.Fields.unitary_cost.name() );
		this.recommended_price = rs.getFloat( Bonuses.Fields.recommended_price.name() );
		this.expiryNotifDays = rs.getShort( Bonuses.Fields.expiryNotifDays.name() );
		this.expiryNotif = rs.getString( Bonuses.Fields.expiryNotif.name() );

	}

	public Bonuses( JSONObject jo ) throws JSONException {

		this.bonus_id = (short)jo.getInt( Bonuses.Fields.bonus_id.name() );
		this.bonus_name = jo.getString( Bonuses.Fields.bonus_name.name() );
		this.unit = jo.getString( Bonuses.Fields.unit.name() );
		this.operations = jo.getString( Bonuses.Fields.operations.name() );
		this.in_credit = jo.getBoolean( Bonuses.Fields.in_credit.name() );
		this.handleOptOut = jo.getString( Bonuses.Fields.handleOptOut.name() );
		this.inFeedbackRequired = jo.getString( Bonuses.Fields.inFeedbackRequired.name() );
		this.commodity_type = jo.getString( Bonuses.Fields.commodity_type.name() );
		this.account_type = jo.getString( Bonuses.Fields.account_type.name() );
		this.max_value = (int)jo.getInt( Bonuses.Fields.max_value.name() );
		this.default_validity_type = jo.getString( Bonuses.Fields.default_validity_type.name() );
		this.default_period_type = jo.getString( Bonuses.Fields.default_period_type.name() );
		this.default_qty_period = (short)jo.getInt( Bonuses.Fields.default_qty_period.name() );
		this.default_fixed_validity_from = jo.getString( Bonuses.Fields.default_fixed_validity_from.name() );
		this.unitary_cost = (float)jo.getDouble( Bonuses.Fields.unitary_cost.name() );
		this.recommended_price = (float)jo.getDouble( Bonuses.Fields.recommended_price.name() );
		this.expiryNotifDays = (short)jo.getInt( Bonuses.Fields.expiryNotifDays.name() );
		this.expiryNotif = jo.getString( Bonuses.Fields.expiryNotif.name() );

	}

	public Short getBonusId() {

		return this.bonus_id;

	}

	public void setBonusId( Short bonus_id ) {

		this.bonus_id = bonus_id;

	}

	public String getBonusName() {

		return this.bonus_name;

	}

	public void setBonusName( String bonus_name ) {

		this.bonus_name = bonus_name;

	}

	public String getUnit() {

		return this.unit;

	}

	public void setUnit( String unit ) {

		this.unit = unit;

	}

	public String getOperations() {

		return this.operations;

	}

	public void setOperations( String operations ) {

		this.operations = operations;

	}

	public Boolean getInCredit() {

		return this.in_credit;

	}

	public void setInCredit( Boolean in_credit ) {

		this.in_credit = in_credit;

	}

	public String getHandleOptOut() {

		return this.handleOptOut;

	}

	public void setHandleOptOut( String handleOptOut ) {

		this.handleOptOut = handleOptOut;

	}

	public String getInFeedbackRequired() {

		return this.inFeedbackRequired;

	}

	public void setInFeedbackRequired( String inFeedbackRequired ) {

		this.inFeedbackRequired = inFeedbackRequired;

	}

	public String getCommodityType() {

		return this.commodity_type;

	}

	public void setCommodityType( String commodity_type ) {

		this.commodity_type = commodity_type;

	}

	public String getAccountType() {

		return this.account_type;

	}

	public void setAccountType( String account_type ) {

		this.account_type = account_type;

	}

	public Integer getMaxValue() {

		return this.max_value;

	}

	public void setMaxValue( Integer max_value ) {

		this.max_value = max_value;

	}

	public String getDefaultValidityType() {

		return this.default_validity_type;

	}

	public void setDefaultValidityType( String default_validity_type ) {

		this.default_validity_type = default_validity_type;

	}

	public String getDefaultPeriodType() {

		return this.default_period_type;

	}

	public void setDefaultPeriodType( String default_period_type ) {

		this.default_period_type = default_period_type;

	}

	public Short getDefaultQtyPeriod() {

		return this.default_qty_period;

	}

	public void setDefaultQtyPeriod( Short default_qty_period ) {

		this.default_qty_period = default_qty_period;

	}

	public String getDefaultFixedValidityFrom() {

		return this.default_fixed_validity_from;

	}

	public void setDefaultFixedValidityFrom( String default_fixed_validity_from ) {

		this.default_fixed_validity_from = default_fixed_validity_from;

	}

	public Float getUnitaryCost() {

		return this.unitary_cost;

	}

	public void setUnitaryCost( Float unitary_cost ) {

		this.unitary_cost = unitary_cost;

	}

	public Float getRecommendedPrice() {

		return this.recommended_price;

	}

	public void setRecommendedPrice( Float recommended_price ) {

		this.recommended_price = recommended_price;

	}

	public Short getExpiryNotifDays() {

		return this.expiryNotifDays;

	}

	public void setExpiryNotifDays( Short expiryNotifDays ) {

		this.expiryNotifDays = expiryNotifDays;

	}

	public String getExpiryNotif() {

		return this.expiryNotif;

	}

	public void setExpiryNotif( String expiryNotif ) {

		this.expiryNotif = expiryNotif;

	}

	public Fields[] getEntityFields() {

		return Bonuses.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"bonus_id\": \"" ).append( this.getBonusId() ).append( "\", " )
			.append( "\"bonus_name\": \"" ).append( this.getBonusName() ).append( "\", " )
			.append( "\"unit\": \"" ).append( this.getUnit() ).append( "\", " )
			.append( "\"operations\": \"" ).append( this.getOperations() ).append( "\", " )
			.append( "\"in_credit\": \"" ).append( this.getInCredit() ).append( "\", " )
			.append( "\"handleOptOut\": \"" ).append( this.getHandleOptOut() ).append( "\", " )
			.append( "\"inFeedbackRequired\": \"" ).append( this.getInFeedbackRequired() ).append( "\", " )
			.append( "\"commodity_type\": \"" ).append( this.getCommodityType() ).append( "\", " )
			.append( "\"account_type\": \"" ).append( this.getAccountType() ).append( "\", " )
			.append( "\"max_value\": \"" ).append( this.getMaxValue() ).append( "\", " )
			.append( "\"default_validity_type\": \"" ).append( this.getDefaultValidityType() ).append( "\", " )
			.append( "\"default_period_type\": \"" ).append( this.getDefaultPeriodType() ).append( "\", " )
			.append( "\"default_qty_period\": \"" ).append( this.getDefaultQtyPeriod() ).append( "\", " )
			.append( "\"default_fixed_validity_from\": \"" ).append( this.getDefaultFixedValidityFrom() ).append( "\", " )
			.append( "\"unitary_cost\": \"" ).append( this.getUnitaryCost() ).append( "\", " )
			.append( "\"recommended_price\": \"" ).append( this.getRecommendedPrice() ).append( "\", " )
			.append( "\"expiryNotifDays\": \"" ).append( this.getExpiryNotifDays() ).append( "\", " )
			.append( "\"expiryNotif\": \"" ).append( this.getExpiryNotif() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }