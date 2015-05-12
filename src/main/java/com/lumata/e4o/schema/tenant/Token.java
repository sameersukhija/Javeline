package com.lumata.e4o.schema.tenant;

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

	public enum Fields { token_code, msisdn, token_label_id, token_notification_resent, ruleset_id, event_id, event_date, expiration_date, consumed_date, qty_current_redeems, qty_max_redeems, last_redeem_date, single_use_redeem_duration_timeout, qty_incident, qty_use, has_offers_associated, description, image_url, consumed_notes, warning, module_id, feature_id, event_type, token_status }

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
			comment = "it stores the value of the token",
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
			comment = "it represents the MSISDN associated to this token",
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
			comment = "it represents the identifier of the label (e.g., Bronze, Silver, Gold) of the token type",
			getMethod = "getTokenLabelId",
			setMethod = "setTokenLabelId"
	)
	private Byte token_label_id;

	@Column(
			table = "token",
			field = "token_notification_resent",
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
			getMethod = "getTokenNotificationResent",
			setMethod = "setTokenNotificationResent"
	)
	private Byte token_notification_resent;

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
			comment = "it stores a reference to the RuleSet used to configure the token",
			getMethod = "getRulesetId",
			setMethod = "setRulesetId"
	)
	private Integer ruleset_id;

	@Column(
			table = "token",
			field = "event_id",
			type = "varchar(40)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 40,
			comment = "",
			getMethod = "getEventId",
			setMethod = "setEventId"
	)
	private String event_id;

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
			comment = "it stores the date and time of the token creation",
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
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "it stores when the token will expire",
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
			comment = "it stores when the token has been consumed",
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
			comment = "it stores how many time a token has been used until now",
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
			comment = "it stores the max number of play available for the token",
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
			comment = "it stores the last use timeTime",
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
			comment = "it stores the duration timeout in millisecond that will be used to consider multiple requests to use a token a single",
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
			comment = "it represents the number of incidents happened",
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
			comment = "it represents the number of times a token has been used in last singleUseRedeemDurationTimeout time range",
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
			comment = "it represents if this token has some offers associated",
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
			comment = "it represents a human readable description of the token",
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
			comment = "it represents the url image associated to the token type",
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
			comment = "it represents the reason because the token has been consumed",
			getMethod = "getConsumedNotes",
			setMethod = "setConsumedNotes"
	)
	private String consumed_notes;

	@Column(
			table = "token",
			field = "warning",
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
			comment = "",
			getMethod = "getWarning",
			setMethod = "setWarning"
	)
	private Boolean warning;

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
			comment = "it stores a reference to the requestor identifier",
			getMethod = "getModuleId",
			setMethod = "setModuleId"
	)
	private Byte module_id;

	@Column(
			table = "token",
			field = "feature_id",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 255,
			comment = "it represents the argument related to the requestor represented as a string",
			getMethod = "getFeatureId",
			setMethod = "setFeatureId"
	)
	private String feature_id;

	@Column(
			table = "token",
			field = "event_type",
			type = "varchar(60)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 60,
			comment = "",
			getMethod = "getEventType",
			setMethod = "setEventType"
	)
	private String event_type;

	@Column(
			table = "token",
			field = "token_status",
			type = "enum('SAVED','EXPIRED')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "MUL",
			defaultValue = "SAVED",
			extra = "",
			length = 2,
			comment = "",
			getMethod = "getTokenStatus",
			setMethod = "setTokenStatus"
	)
	private String token_status;


	public Token() {} 

	public Token( ResultSet rs ) throws SQLException {

		this.token_code = rs.getString( Token.Fields.token_code.name() );
		this.msisdn = rs.getLong( Token.Fields.msisdn.name() );
		this.token_label_id = rs.getByte( Token.Fields.token_label_id.name() );
		this.token_notification_resent = rs.getByte( Token.Fields.token_notification_resent.name() );
		this.ruleset_id = rs.getInt( Token.Fields.ruleset_id.name() );
		this.event_id = rs.getString( Token.Fields.event_id.name() );
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
		this.warning = rs.getBoolean( Token.Fields.warning.name() );
		this.module_id = rs.getByte( Token.Fields.module_id.name() );
		this.feature_id = rs.getString( Token.Fields.feature_id.name() );
		this.event_type = rs.getString( Token.Fields.event_type.name() );
		this.token_status = rs.getString( Token.Fields.token_status.name() );

	}

	public Token( JSONObject jo ) throws JSONException, ParseException {

		this.token_code = jo.getString( Token.Fields.token_code.name() );
		this.msisdn = (long)jo.getLong( Token.Fields.msisdn.name() );
		this.token_label_id = (byte)jo.getInt( Token.Fields.token_label_id.name() );
		this.token_notification_resent = (byte)jo.getInt( Token.Fields.token_notification_resent.name() );
		this.ruleset_id = (int)jo.getInt( Token.Fields.ruleset_id.name() );
		this.event_id = jo.getString( Token.Fields.event_id.name() );
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
		this.warning = jo.getBoolean( Token.Fields.warning.name() );
		this.module_id = (byte)jo.getInt( Token.Fields.module_id.name() );
		this.feature_id = jo.getString( Token.Fields.feature_id.name() );
		this.event_type = jo.getString( Token.Fields.event_type.name() );
		this.token_status = jo.getString( Token.Fields.token_status.name() );

	}

	public String getTokenCode() {

		return this.token_code;

	}

	public Token setTokenCode( String token_code ) {

		this.token_code = token_code;

		return this;

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public Token setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

		return this;

	}

	public Byte getTokenLabelId() {

		return this.token_label_id;

	}

	public Token setTokenLabelId( Byte token_label_id ) {

		this.token_label_id = token_label_id;

		return this;

	}

	public Byte getTokenNotificationResent() {

		return this.token_notification_resent;

	}

	public Token setTokenNotificationResent( Byte token_notification_resent ) {

		this.token_notification_resent = token_notification_resent;

		return this;

	}

	public Integer getRulesetId() {

		return this.ruleset_id;

	}

	public Token setRulesetId( Integer ruleset_id ) {

		this.ruleset_id = ruleset_id;

		return this;

	}

	public String getEventId() {

		return this.event_id;

	}

	public Token setEventId( String event_id ) {

		this.event_id = event_id;

		return this;

	}

	public Date getEventDate() {

		return this.event_date;

	}

	public Token setEventDate( Date event_date ) {

		this.event_date = event_date;

		return this;

	}

	public Date getExpirationDate() {

		return this.expiration_date;

	}

	public Token setExpirationDate( Date expiration_date ) {

		this.expiration_date = expiration_date;

		return this;

	}

	public Date getConsumedDate() {

		return this.consumed_date;

	}

	public Token setConsumedDate( Date consumed_date ) {

		this.consumed_date = consumed_date;

		return this;

	}

	public Byte getQtyCurrentRedeems() {

		return this.qty_current_redeems;

	}

	public Token setQtyCurrentRedeems( Byte qty_current_redeems ) {

		this.qty_current_redeems = qty_current_redeems;

		return this;

	}

	public Byte getQtyMaxRedeems() {

		return this.qty_max_redeems;

	}

	public Token setQtyMaxRedeems( Byte qty_max_redeems ) {

		this.qty_max_redeems = qty_max_redeems;

		return this;

	}

	public Date getLastRedeemDate() {

		return this.last_redeem_date;

	}

	public Token setLastRedeemDate( Date last_redeem_date ) {

		this.last_redeem_date = last_redeem_date;

		return this;

	}

	public Integer getSingleUseRedeemDurationTimeout() {

		return this.single_use_redeem_duration_timeout;

	}

	public Token setSingleUseRedeemDurationTimeout( Integer single_use_redeem_duration_timeout ) {

		this.single_use_redeem_duration_timeout = single_use_redeem_duration_timeout;

		return this;

	}

	public Byte getQtyIncident() {

		return this.qty_incident;

	}

	public Token setQtyIncident( Byte qty_incident ) {

		this.qty_incident = qty_incident;

		return this;

	}

	public Byte getQtyUse() {

		return this.qty_use;

	}

	public Token setQtyUse( Byte qty_use ) {

		this.qty_use = qty_use;

		return this;

	}

	public Byte getHasOffersAssociated() {

		return this.has_offers_associated;

	}

	public Token setHasOffersAssociated( Byte has_offers_associated ) {

		this.has_offers_associated = has_offers_associated;

		return this;

	}

	public String getDescription() {

		return this.description;

	}

	public Token setDescription( String description ) {

		this.description = description;

		return this;

	}

	public String getImageUrl() {

		return this.image_url;

	}

	public Token setImageUrl( String image_url ) {

		this.image_url = image_url;

		return this;

	}

	public String getConsumedNotes() {

		return this.consumed_notes;

	}

	public Token setConsumedNotes( String consumed_notes ) {

		this.consumed_notes = consumed_notes;

		return this;

	}

	public Boolean getWarning() {

		return this.warning;

	}

	public Token setWarning( Boolean warning ) {

		this.warning = warning;

		return this;

	}

	public Byte getModuleId() {

		return this.module_id;

	}

	public Token setModuleId( Byte module_id ) {

		this.module_id = module_id;

		return this;

	}

	public String getFeatureId() {

		return this.feature_id;

	}

	public Token setFeatureId( String feature_id ) {

		this.feature_id = feature_id;

		return this;

	}

	public String getEventType() {

		return this.event_type;

	}

	public Token setEventType( String event_type ) {

		this.event_type = event_type;

		return this;

	}

	public String getTokenStatus() {

		return this.token_status;

	}

	public Token setTokenStatus( String token_status ) {

		this.token_status = token_status;

		return this;

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
			.append( "\"token_notification_resent\": \"" ).append( this.getTokenNotificationResent() ).append( "\", " )
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
			.append( "\"warning\": \"" ).append( this.getWarning() ).append( "\", " )
			.append( "\"module_id\": \"" ).append( this.getModuleId() ).append( "\", " )
			.append( "\"feature_id\": \"" ).append( this.getFeatureId() ).append( "\", " )
			.append( "\"event_type\": \"" ).append( this.getEventType() ).append( "\", " )
			.append( "\"token_status\": \"" ).append( this.getTokenStatus() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }