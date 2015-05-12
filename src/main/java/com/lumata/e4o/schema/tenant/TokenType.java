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


@Table( "token_type" )
public class TokenType { 

	public enum Fields { token_type_id, token_type_name, token_label_id, expiration_duration, expiration_duration_unit, expiration_date, qty_max_redeems, single_use_redeem_duration_timeout, token_format, description, image_url }

	@Column(
			table = "token_type",
			field = "token_type_id",
			type = "smallint(11) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = true,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 11,
			comment = "it stores the key of the token type",
			getMethod = "getTokenTypeId",
			setMethod = "setTokenTypeId"
	)
	private Short token_type_id;

	@Column(
			table = "token_type",
			field = "token_type_name",
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
			comment = "it stores a the unique name of the token type",
			getMethod = "getTokenTypeName",
			setMethod = "setTokenTypeName"
	)
	private String token_type_name;

	@Column(
			table = "token_type",
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
			table = "token_type",
			field = "expiration_duration",
			type = "int(11) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			comment = "it stores the duration timeout before the token expires",
			getMethod = "getExpirationDuration",
			setMethod = "setExpirationDuration"
	)
	private Integer expiration_duration;

	@Column(
			table = "token_type",
			field = "expiration_duration_unit",
			type = "enum('seconds','minutes','hours','days','fixed')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 5,
			comment = "it stores the expiration_duration unit",
			getMethod = "getExpirationDurationUnit",
			setMethod = "setExpirationDurationUnit"
	)
	private String expiration_duration_unit;

	@Column(
			table = "token_type",
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
			comment = "it stores expiration_date",
			getMethod = "getExpirationDate",
			setMethod = "setExpirationDate"
	)
	private Date expiration_date;

	@Column(
			table = "token_type",
			field = "qty_max_redeems",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "it stores the max number of play available for the token",
			getMethod = "getQtyMaxRedeems",
			setMethod = "setQtyMaxRedeems"
	)
	private Byte qty_max_redeems;

	@Column(
			table = "token_type",
			field = "single_use_redeem_duration_timeout",
			type = "int(11) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			comment = "it stores the duration timeout in millisecond that will be used to consider multiple requests to use a token a single",
			getMethod = "getSingleUseRedeemDurationTimeout",
			setMethod = "setSingleUseRedeemDurationTimeout"
	)
	private Integer single_use_redeem_duration_timeout;

	@Column(
			table = "token_type",
			field = "token_format",
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
			comment = "it stores the token format used to generate new tokens",
			getMethod = "getTokenFormat",
			setMethod = "setTokenFormat"
	)
	private String token_format;

	@Column(
			table = "token_type",
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
			comment = "it stores a human readable description of the token type",
			getMethod = "getDescription",
			setMethod = "setDescription"
	)
	private String description;

	@Column(
			table = "token_type",
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


	public TokenType() {} 

	public TokenType( ResultSet rs ) throws SQLException {

		this.token_type_id = rs.getShort( TokenType.Fields.token_type_id.name() );
		this.token_type_name = rs.getString( TokenType.Fields.token_type_name.name() );
		this.token_label_id = rs.getByte( TokenType.Fields.token_label_id.name() );
		this.expiration_duration = rs.getInt( TokenType.Fields.expiration_duration.name() );
		this.expiration_duration_unit = rs.getString( TokenType.Fields.expiration_duration_unit.name() );
		this.expiration_date = rs.getDate( TokenType.Fields.expiration_date.name() );
		this.qty_max_redeems = rs.getByte( TokenType.Fields.qty_max_redeems.name() );
		this.single_use_redeem_duration_timeout = rs.getInt( TokenType.Fields.single_use_redeem_duration_timeout.name() );
		this.token_format = rs.getString( TokenType.Fields.token_format.name() );
		this.description = rs.getString( TokenType.Fields.description.name() );
		this.image_url = rs.getString( TokenType.Fields.image_url.name() );

	}

	public TokenType( JSONObject jo ) throws JSONException, ParseException {

		this.token_type_id = (short)jo.getInt( TokenType.Fields.token_type_id.name() );
		this.token_type_name = jo.getString( TokenType.Fields.token_type_name.name() );
		this.token_label_id = (byte)jo.getInt( TokenType.Fields.token_label_id.name() );
		this.expiration_duration = (int)jo.getInt( TokenType.Fields.expiration_duration.name() );
		this.expiration_duration_unit = jo.getString( TokenType.Fields.expiration_duration_unit.name() );
		this.expiration_date = Format.getMysqlDateTime( jo.getString( TokenType.Fields.expiration_date.name() ) );
		this.qty_max_redeems = (byte)jo.getInt( TokenType.Fields.qty_max_redeems.name() );
		this.single_use_redeem_duration_timeout = (int)jo.getInt( TokenType.Fields.single_use_redeem_duration_timeout.name() );
		this.token_format = jo.getString( TokenType.Fields.token_format.name() );
		this.description = jo.getString( TokenType.Fields.description.name() );
		this.image_url = jo.getString( TokenType.Fields.image_url.name() );

	}

	public Short getTokenTypeId() {

		return this.token_type_id;

	}

	public TokenType setTokenTypeId( Short token_type_id ) {

		this.token_type_id = token_type_id;

		return this;

	}

	public String getTokenTypeName() {

		return this.token_type_name;

	}

	public TokenType setTokenTypeName( String token_type_name ) {

		this.token_type_name = token_type_name;

		return this;

	}

	public Byte getTokenLabelId() {

		return this.token_label_id;

	}

	public TokenType setTokenLabelId( Byte token_label_id ) {

		this.token_label_id = token_label_id;

		return this;

	}

	public Integer getExpirationDuration() {

		return this.expiration_duration;

	}

	public TokenType setExpirationDuration( Integer expiration_duration ) {

		this.expiration_duration = expiration_duration;

		return this;

	}

	public String getExpirationDurationUnit() {

		return this.expiration_duration_unit;

	}

	public TokenType setExpirationDurationUnit( String expiration_duration_unit ) {

		this.expiration_duration_unit = expiration_duration_unit;

		return this;

	}

	public Date getExpirationDate() {

		return this.expiration_date;

	}

	public TokenType setExpirationDate( Date expiration_date ) {

		this.expiration_date = expiration_date;

		return this;

	}

	public Byte getQtyMaxRedeems() {

		return this.qty_max_redeems;

	}

	public TokenType setQtyMaxRedeems( Byte qty_max_redeems ) {

		this.qty_max_redeems = qty_max_redeems;

		return this;

	}

	public Integer getSingleUseRedeemDurationTimeout() {

		return this.single_use_redeem_duration_timeout;

	}

	public TokenType setSingleUseRedeemDurationTimeout( Integer single_use_redeem_duration_timeout ) {

		this.single_use_redeem_duration_timeout = single_use_redeem_duration_timeout;

		return this;

	}

	public String getTokenFormat() {

		return this.token_format;

	}

	public TokenType setTokenFormat( String token_format ) {

		this.token_format = token_format;

		return this;

	}

	public String getDescription() {

		return this.description;

	}

	public TokenType setDescription( String description ) {

		this.description = description;

		return this;

	}

	public String getImageUrl() {

		return this.image_url;

	}

	public TokenType setImageUrl( String image_url ) {

		this.image_url = image_url;

		return this;

	}

	public Fields[] getEntityFields() {

		return TokenType.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"token_type_id\": \"" ).append( this.getTokenTypeId() ).append( "\", " )
			.append( "\"token_type_name\": \"" ).append( this.getTokenTypeName() ).append( "\", " )
			.append( "\"token_label_id\": \"" ).append( this.getTokenLabelId() ).append( "\", " )
			.append( "\"expiration_duration\": \"" ).append( this.getExpirationDuration() ).append( "\", " )
			.append( "\"expiration_duration_unit\": \"" ).append( this.getExpirationDurationUnit() ).append( "\", " )
			.append( "\"expiration_date\": \"" ).append( this.getExpirationDate() ).append( "\", " )
			.append( "\"qty_max_redeems\": \"" ).append( this.getQtyMaxRedeems() ).append( "\", " )
			.append( "\"single_use_redeem_duration_timeout\": \"" ).append( this.getSingleUseRedeemDurationTimeout() ).append( "\", " )
			.append( "\"token_format\": \"" ).append( this.getTokenFormat() ).append( "\", " )
			.append( "\"description\": \"" ).append( this.getDescription() ).append( "\", " )
			.append( "\"image_url\": \"" ).append( this.getImageUrl() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }