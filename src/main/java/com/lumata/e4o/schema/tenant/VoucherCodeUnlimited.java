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


@Table( "voucher_code_unlimited" )
public class VoucherCodeUnlimited { 

	public enum Fields { offer_id, code, expiryDate, partner_id, format }

	@Column(
			table = "voucher_code_unlimited",
			field = "offer_id",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 5,
			comment = "",
			getMethod = "getOfferId",
			setMethod = "setOfferId"
	)
	private Short offer_id;

	@Column(
			table = "voucher_code_unlimited",
			field = "code",
			type = "varchar(512)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 512,
			comment = "",
			getMethod = "getCode",
			setMethod = "setCode"
	)
	private String code;

	@Column(
			table = "voucher_code_unlimited",
			field = "expiryDate",
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
			comment = "",
			getMethod = "getExpiryDate",
			setMethod = "setExpiryDate"
	)
	private Date expiryDate;

	@Column(
			table = "voucher_code_unlimited",
			field = "partner_id",
			type = "varchar(50)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "",
			extra = "",
			length = 50,
			comment = "",
			getMethod = "getPartnerId",
			setMethod = "setPartnerId"
	)
	private String partner_id;

	@Column(
			table = "voucher_code_unlimited",
			field = "format",
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
			comment = "",
			getMethod = "getFormat",
			setMethod = "setFormat"
	)
	private String format;


	public VoucherCodeUnlimited() {} 

	public VoucherCodeUnlimited( ResultSet rs ) throws SQLException {

		this.offer_id = rs.getShort( VoucherCodeUnlimited.Fields.offer_id.name() );
		this.code = rs.getString( VoucherCodeUnlimited.Fields.code.name() );
		this.expiryDate = rs.getDate( VoucherCodeUnlimited.Fields.expiryDate.name() );
		this.partner_id = rs.getString( VoucherCodeUnlimited.Fields.partner_id.name() );
		this.format = rs.getString( VoucherCodeUnlimited.Fields.format.name() );

	}

	public VoucherCodeUnlimited( JSONObject jo ) throws JSONException, ParseException {

		this.offer_id = (short)jo.getInt( VoucherCodeUnlimited.Fields.offer_id.name() );
		this.code = jo.getString( VoucherCodeUnlimited.Fields.code.name() );
		this.expiryDate = Format.getMysqlDateTime( jo.getString( VoucherCodeUnlimited.Fields.expiryDate.name() ) );
		this.partner_id = jo.getString( VoucherCodeUnlimited.Fields.partner_id.name() );
		this.format = jo.getString( VoucherCodeUnlimited.Fields.format.name() );

	}

	public Short getOfferId() {

		return this.offer_id;

	}

	public VoucherCodeUnlimited setOfferId( Short offer_id ) {

		this.offer_id = offer_id;

		return this;

	}

	public String getCode() {

		return this.code;

	}

	public VoucherCodeUnlimited setCode( String code ) {

		this.code = code;

		return this;

	}

	public Date getExpiryDate() {

		return this.expiryDate;

	}

	public VoucherCodeUnlimited setExpiryDate( Date expiryDate ) {

		this.expiryDate = expiryDate;

		return this;

	}

	public String getPartnerId() {

		return this.partner_id;

	}

	public VoucherCodeUnlimited setPartnerId( String partner_id ) {

		this.partner_id = partner_id;

		return this;

	}

	public String getFormat() {

		return this.format;

	}

	public VoucherCodeUnlimited setFormat( String format ) {

		this.format = format;

		return this;

	}

	public Fields[] getEntityFields() {

		return VoucherCodeUnlimited.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"offer_id\": \"" ).append( this.getOfferId() ).append( "\", " )
			.append( "\"code\": \"" ).append( this.getCode() ).append( "\", " )
			.append( "\"expiryDate\": \"" ).append( this.getExpiryDate() ).append( "\", " )
			.append( "\"partner_id\": \"" ).append( this.getPartnerId() ).append( "\", " )
			.append( "\"format\": \"" ).append( this.getFormat() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }