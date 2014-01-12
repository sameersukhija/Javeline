package com.lumata.expression.operators.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "token_type" )
public class TokenType { 

	public enum Fields { token_type_id, token_type_name, token_label_id, expiration_duration, expiration_duration_unit, qty_max_redeems, single_use_redeem_duration_timeout, token_format, description }

	@Column(
			table = "token_type",
			field = "token_type_id",
			type = "smallint(11) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 11,
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
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 20,
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
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
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
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getExpirationDuration",
			setMethod = "setExpirationDuration"
	)
	private Integer expiration_duration;

	@Column(
			table = "token_type",
			field = "expiration_duration_unit",
			type = "enum('seconds','minutes','hours','days')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getExpirationDurationUnit",
			setMethod = "setExpirationDurationUnit"
	)
	private String expiration_duration_unit;

	@Column(
			table = "token_type",
			field = "qty_max_redeems",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
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
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
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
			key = "",
			defaultValue = "null",
			extra = "",
			length = 60,
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
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getDescription",
			setMethod = "setDescription"
	)
	private String description;


	public TokenType() {} 

	public TokenType( ResultSet rs ) throws SQLException {

		this.token_type_id = rs.getShort( TokenType.Fields.token_type_id.name() );
		this.token_type_name = rs.getString( TokenType.Fields.token_type_name.name() );
		this.token_label_id = rs.getByte( TokenType.Fields.token_label_id.name() );
		this.expiration_duration = rs.getInt( TokenType.Fields.expiration_duration.name() );
		this.expiration_duration_unit = rs.getString( TokenType.Fields.expiration_duration_unit.name() );
		this.qty_max_redeems = rs.getByte( TokenType.Fields.qty_max_redeems.name() );
		this.single_use_redeem_duration_timeout = rs.getInt( TokenType.Fields.single_use_redeem_duration_timeout.name() );
		this.token_format = rs.getString( TokenType.Fields.token_format.name() );
		this.description = rs.getString( TokenType.Fields.description.name() );

	}

	public TokenType( JSONObject jo ) throws JSONException {

		this.token_type_id = (short)jo.getInt( TokenType.Fields.token_type_id.name() );
		this.token_type_name = jo.getString( TokenType.Fields.token_type_name.name() );
		this.token_label_id = (byte)jo.getInt( TokenType.Fields.token_label_id.name() );
		this.expiration_duration = (int)jo.getInt( TokenType.Fields.expiration_duration.name() );
		this.expiration_duration_unit = jo.getString( TokenType.Fields.expiration_duration_unit.name() );
		this.qty_max_redeems = (byte)jo.getInt( TokenType.Fields.qty_max_redeems.name() );
		this.single_use_redeem_duration_timeout = (int)jo.getInt( TokenType.Fields.single_use_redeem_duration_timeout.name() );
		this.token_format = jo.getString( TokenType.Fields.token_format.name() );
		this.description = jo.getString( TokenType.Fields.description.name() );

	}

	public Short getTokenTypeId() {

		return this.token_type_id;

	}

	public void setTokenTypeId( Short token_type_id ) {

		this.token_type_id = token_type_id;

	}

	public String getTokenTypeName() {

		return this.token_type_name;

	}

	public void setTokenTypeName( String token_type_name ) {

		this.token_type_name = token_type_name;

	}

	public Byte getTokenLabelId() {

		return this.token_label_id;

	}

	public void setTokenLabelId( Byte token_label_id ) {

		this.token_label_id = token_label_id;

	}

	public Integer getExpirationDuration() {

		return this.expiration_duration;

	}

	public void setExpirationDuration( Integer expiration_duration ) {

		this.expiration_duration = expiration_duration;

	}

	public String getExpirationDurationUnit() {

		return this.expiration_duration_unit;

	}

	public void setExpirationDurationUnit( String expiration_duration_unit ) {

		this.expiration_duration_unit = expiration_duration_unit;

	}

	public Byte getQtyMaxRedeems() {

		return this.qty_max_redeems;

	}

	public void setQtyMaxRedeems( Byte qty_max_redeems ) {

		this.qty_max_redeems = qty_max_redeems;

	}

	public Integer getSingleUseRedeemDurationTimeout() {

		return this.single_use_redeem_duration_timeout;

	}

	public void setSingleUseRedeemDurationTimeout( Integer single_use_redeem_duration_timeout ) {

		this.single_use_redeem_duration_timeout = single_use_redeem_duration_timeout;

	}

	public String getTokenFormat() {

		return this.token_format;

	}

	public void setTokenFormat( String token_format ) {

		this.token_format = token_format;

	}

	public String getDescription() {

		return this.description;

	}

	public void setDescription( String description ) {

		this.description = description;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"token_type_id\": \"" ).append( this.getTokenTypeId() ).append( "\", " )
			.append( "\"token_type_name\": \"" ).append( this.getTokenTypeName() ).append( "\", " )
			.append( "\"token_label_id\": \"" ).append( this.getTokenLabelId() ).append( "\", " )
			.append( "\"expiration_duration\": \"" ).append( this.getExpirationDuration() ).append( "\", " )
			.append( "\"expiration_duration_unit\": \"" ).append( this.getExpirationDurationUnit() ).append( "\", " )
			.append( "\"qty_max_redeems\": \"" ).append( this.getQtyMaxRedeems() ).append( "\", " )
			.append( "\"single_use_redeem_duration_timeout\": \"" ).append( this.getSingleUseRedeemDurationTimeout() ).append( "\", " )
			.append( "\"token_format\": \"" ).append( this.getTokenFormat() ).append( "\", " )
			.append( "\"description\": \"" ).append( this.getDescription() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }