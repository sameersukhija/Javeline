package com.lumata.e4o_tenant.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.util.Date;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "token" )
public class Token { 

	public enum Fields { token_code, msisdn, token_label_id, ruleset_id, event_id, event_date, expiration_date, consumed_date, qty_current_redeems, qty_max_redeems, last_redeem_date, single_use_redeem_duration_timeout, qty_incident, qty_use, has_offers_associated, description, image_url, consumed_notes, module_id, feature_id }

	@Column(
			table = "token",
			field = "token_code",
			type = "varchar(60)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 60,
			getMethod = "getTokenCode",
			setMethod = "setTokenCode"
	)
	private String token_code;

	@Column(
			table = "token",
			field = "msisdn",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getMsisdn",
			setMethod = "setMsisdn"
	)
	private Long msisdn;

	@Column(
			table = "token",
			field = "token_label_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getTokenLabelId",
			setMethod = "setTokenLabelId"
	)
	private Byte token_label_id;

	@Column(
			table = "token",
			field = "ruleset_id",
			type = "int(10) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 10,
			getMethod = "getRulesetId",
			setMethod = "setRulesetId"
	)
	private Integer ruleset_id;

	@Column(
			table = "token",
			field = "event_id",
			type = "smallint(11) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getEventId",
			setMethod = "setEventId"
	)
	private Short event_id;

	@Column(
			table = "token",
			field = "event_date",
			type = "datetime",
			mysqlType = "datetime",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getEventDate",
			setMethod = "setEventDate"
	)
	private Date event_date;

	@Column(
			table = "token",
			field = "expiration_date",
			type = "datetime",
			mysqlType = "datetime",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getExpirationDate",
			setMethod = "setExpirationDate"
	)
	private Date expiration_date;

	@Column(
			table = "token",
			field = "consumed_date",
			type = "datetime",
			mysqlType = "datetime",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getConsumedDate",
			setMethod = "setConsumedDate"
	)
	private Date consumed_date;

	@Column(
			table = "token",
			field = "qty_current_redeems",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 3,
			getMethod = "getQtyCurrentRedeems",
			setMethod = "setQtyCurrentRedeems"
	)
	private Byte qty_current_redeems;

	@Column(
			table = "token",
			field = "qty_max_redeems",
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
			getMethod = "getQtyMaxRedeems",
			setMethod = "setQtyMaxRedeems"
	)
	private Byte qty_max_redeems;

	@Column(
			table = "token",
			field = "last_redeem_date",
			type = "datetime",
			mysqlType = "datetime",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getLastRedeemDate",
			setMethod = "setLastRedeemDate"
	)
	private Date last_redeem_date;

	@Column(
			table = "token",
			field = "single_use_redeem_duration_timeout",
			type = "int(11) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 11,
			getMethod = "getSingleUseRedeemDurationTimeout",
			setMethod = "setSingleUseRedeemDurationTimeout"
	)
	private Integer single_use_redeem_duration_timeout;

	@Column(
			table = "token",
			field = "qty_incident",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 3,
			getMethod = "getQtyIncident",
			setMethod = "setQtyIncident"
	)
	private Byte qty_incident;

	@Column(
			table = "token",
			field = "qty_use",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 3,
			getMethod = "getQtyUse",
			setMethod = "setQtyUse"
	)
	private Byte qty_use;

	@Column(
			table = "token",
			field = "has_offers_associated",
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
			getMethod = "getHasOffersAssociated",
			setMethod = "setHasOffersAssociated"
	)
	private Byte has_offers_associated;

	@Column(
			table = "token",
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
			table = "token",
			field = "image_url",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 255,
			getMethod = "getImageUrl",
			setMethod = "setImageUrl"
	)
	private String image_url;

	@Column(
			table = "token",
			field = "consumed_notes",
			type = "varchar(256)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 256,
			getMethod = "getConsumedNotes",
			setMethod = "setConsumedNotes"
	)
	private String consumed_notes;

	@Column(
			table = "token",
			field = "module_id",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			getMethod = "getModuleId",
			setMethod = "setModuleId"
	)
	private Byte module_id;

	@Column(
			table = "token",
			field = "feature_id",
			type = "varchar(256)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 256,
			getMethod = "getFeatureId",
			setMethod = "setFeatureId"
	)
	private String feature_id;


	public Token() {} 

	public Token( ResultSet rs ) throws SQLException {

		this.token_code = rs.getString( Token.Fields.token_code.name() );
		this.msisdn = rs.getLong( Token.Fields.msisdn.name() );
		this.token_label_id = rs.getByte( Token.Fields.token_label_id.name() );
		this.ruleset_id = rs.getInt( Token.Fields.ruleset_id.name() );
		this.event_id = rs.getShort( Token.Fields.event_id.name() );
		this.event_date = rs.getDate( Token.Fields.event_date.name() );
		this.expiration_date = rs.getDate( Token.Fields.expiration_date.name() );
		this.consumed_date = rs.getDate( Token.Fields.consumed_date.name() );
		this.qty_current_redeems = rs.getByte( Token.Fields.qty_current_redeems.name() );
		this.qty_max_redeems = rs.getByte( Token.Fields.qty_max_redeems.name() );
		this.last_redeem_date = rs.getDate( Token.Fields.last_redeem_date.name() );
		this.single_use_redeem_duration_timeout = rs.getInt( Token.Fields.single_use_redeem_duration_timeout.name() );
		this.qty_incident = rs.getByte( Token.Fields.qty_incident.name() );
		this.qty_use = rs.getByte( Token.Fields.qty_use.name() );
		this.has_offers_associated = rs.getByte( Token.Fields.has_offers_associated.name() );
		this.description = rs.getString( Token.Fields.description.name() );
		this.image_url = rs.getString( Token.Fields.image_url.name() );
		this.consumed_notes = rs.getString( Token.Fields.consumed_notes.name() );
		this.module_id = rs.getByte( Token.Fields.module_id.name() );
		this.feature_id = rs.getString( Token.Fields.feature_id.name() );

	}

	public Token( JSONObject jo ) throws JSONException, ParseException {

		this.token_code = jo.getString( Token.Fields.token_code.name() );
		this.msisdn = (long)jo.getLong( Token.Fields.msisdn.name() );
		this.token_label_id = (byte)jo.getInt( Token.Fields.token_label_id.name() );
		this.ruleset_id = (int)jo.getInt( Token.Fields.ruleset_id.name() );
		this.event_id = (short)jo.getInt( Token.Fields.event_id.name() );
		this.event_date = Format.getMysqlDateTime( jo.getString( Token.Fields.event_date.name() ) );
		this.expiration_date = Format.getMysqlDateTime( jo.getString( Token.Fields.expiration_date.name() ) );
		this.consumed_date = Format.getMysqlDateTime( jo.getString( Token.Fields.consumed_date.name() ) );
		this.qty_current_redeems = (byte)jo.getInt( Token.Fields.qty_current_redeems.name() );
		this.qty_max_redeems = (byte)jo.getInt( Token.Fields.qty_max_redeems.name() );
		this.last_redeem_date = Format.getMysqlDateTime( jo.getString( Token.Fields.last_redeem_date.name() ) );
		this.single_use_redeem_duration_timeout = (int)jo.getInt( Token.Fields.single_use_redeem_duration_timeout.name() );
		this.qty_incident = (byte)jo.getInt( Token.Fields.qty_incident.name() );
		this.qty_use = (byte)jo.getInt( Token.Fields.qty_use.name() );
		this.has_offers_associated = (byte)jo.getInt( Token.Fields.has_offers_associated.name() );
		this.description = jo.getString( Token.Fields.description.name() );
		this.image_url = jo.getString( Token.Fields.image_url.name() );
		this.consumed_notes = jo.getString( Token.Fields.consumed_notes.name() );
		this.module_id = (byte)jo.getInt( Token.Fields.module_id.name() );
		this.feature_id = jo.getString( Token.Fields.feature_id.name() );

	}

	public String getTokenCode() {

		return this.token_code;

	}

	public void setTokenCode( String token_code ) {

		this.token_code = token_code;

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public void setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

	}

	public Byte getTokenLabelId() {

		return this.token_label_id;

	}

	public void setTokenLabelId( Byte token_label_id ) {

		this.token_label_id = token_label_id;

	}

	public Integer getRulesetId() {

		return this.ruleset_id;

	}

	public void setRulesetId( Integer ruleset_id ) {

		this.ruleset_id = ruleset_id;

	}

	public Short getEventId() {

		return this.event_id;

	}

	public void setEventId( Short event_id ) {

		this.event_id = event_id;

	}

	public Date getEventDate() {

		return this.event_date;

	}

	public void setEventDate( Date event_date ) {

		this.event_date = event_date;

	}

	public Date getExpirationDate() {

		return this.expiration_date;

	}

	public void setExpirationDate( Date expiration_date ) {

		this.expiration_date = expiration_date;

	}

	public Date getConsumedDate() {

		return this.consumed_date;

	}

	public void setConsumedDate( Date consumed_date ) {

		this.consumed_date = consumed_date;

	}

	public Byte getQtyCurrentRedeems() {

		return this.qty_current_redeems;

	}

	public void setQtyCurrentRedeems( Byte qty_current_redeems ) {

		this.qty_current_redeems = qty_current_redeems;

	}

	public Byte getQtyMaxRedeems() {

		return this.qty_max_redeems;

	}

	public void setQtyMaxRedeems( Byte qty_max_redeems ) {

		this.qty_max_redeems = qty_max_redeems;

	}

	public Date getLastRedeemDate() {

		return this.last_redeem_date;

	}

	public void setLastRedeemDate( Date last_redeem_date ) {

		this.last_redeem_date = last_redeem_date;

	}

	public Integer getSingleUseRedeemDurationTimeout() {

		return this.single_use_redeem_duration_timeout;

	}

	public void setSingleUseRedeemDurationTimeout( Integer single_use_redeem_duration_timeout ) {

		this.single_use_redeem_duration_timeout = single_use_redeem_duration_timeout;

	}

	public Byte getQtyIncident() {

		return this.qty_incident;

	}

	public void setQtyIncident( Byte qty_incident ) {

		this.qty_incident = qty_incident;

	}

	public Byte getQtyUse() {

		return this.qty_use;

	}

	public void setQtyUse( Byte qty_use ) {

		this.qty_use = qty_use;

	}

	public Byte getHasOffersAssociated() {

		return this.has_offers_associated;

	}

	public void setHasOffersAssociated( Byte has_offers_associated ) {

		this.has_offers_associated = has_offers_associated;

	}

	public String getDescription() {

		return this.description;

	}

	public void setDescription( String description ) {

		this.description = description;

	}

	public String getImageUrl() {

		return this.image_url;

	}

	public void setImageUrl( String image_url ) {

		this.image_url = image_url;

	}

	public String getConsumedNotes() {

		return this.consumed_notes;

	}

	public void setConsumedNotes( String consumed_notes ) {

		this.consumed_notes = consumed_notes;

	}

	public Byte getModuleId() {

		return this.module_id;

	}

	public void setModuleId( Byte module_id ) {

		this.module_id = module_id;

	}

	public String getFeatureId() {

		return this.feature_id;

	}

	public void setFeatureId( String feature_id ) {

		this.feature_id = feature_id;

	}

	public Fields[] getEntityFields() {

		return Token.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"token_code\": \"" ).append( this.getTokenCode() ).append( "\", " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"token_label_id\": \"" ).append( this.getTokenLabelId() ).append( "\", " )
			.append( "\"ruleset_id\": \"" ).append( this.getRulesetId() ).append( "\", " )
			.append( "\"event_id\": \"" ).append( this.getEventId() ).append( "\", " )
			.append( "\"event_date\": \"" ).append( this.getEventDate() ).append( "\", " )
			.append( "\"expiration_date\": \"" ).append( this.getExpirationDate() ).append( "\", " )
			.append( "\"consumed_date\": \"" ).append( this.getConsumedDate() ).append( "\", " )
			.append( "\"qty_current_redeems\": \"" ).append( this.getQtyCurrentRedeems() ).append( "\", " )
			.append( "\"qty_max_redeems\": \"" ).append( this.getQtyMaxRedeems() ).append( "\", " )
			.append( "\"last_redeem_date\": \"" ).append( this.getLastRedeemDate() ).append( "\", " )
			.append( "\"single_use_redeem_duration_timeout\": \"" ).append( this.getSingleUseRedeemDurationTimeout() ).append( "\", " )
			.append( "\"qty_incident\": \"" ).append( this.getQtyIncident() ).append( "\", " )
			.append( "\"qty_use\": \"" ).append( this.getQtyUse() ).append( "\", " )
			.append( "\"has_offers_associated\": \"" ).append( this.getHasOffersAssociated() ).append( "\", " )
			.append( "\"description\": \"" ).append( this.getDescription() ).append( "\", " )
			.append( "\"image_url\": \"" ).append( this.getImageUrl() ).append( "\", " )
			.append( "\"consumed_notes\": \"" ).append( this.getConsumedNotes() ).append( "\", " )
			.append( "\"module_id\": \"" ).append( this.getModuleId() ).append( "\", " )
			.append( "\"feature_id\": \"" ).append( this.getFeatureId() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }