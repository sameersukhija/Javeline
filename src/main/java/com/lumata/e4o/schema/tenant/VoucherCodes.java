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


@Table( "voucher_codes" )
public class VoucherCodes { 

	public enum Fields { offer_id, code, expiryDate, purchase_id, redeemed_date, location_id, partner_id }

	@Column(
			table = "voucher_codes",
			field = "offer_id",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 5,
			getMethod = "getOfferId",
			setMethod = "setOfferId"
	)
	private Short offer_id;

	@Column(
			table = "voucher_codes",
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
			getMethod = "getCode",
			setMethod = "setCode"
	)
	private String code;

	@Column(
			table = "voucher_codes",
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
			getMethod = "getExpiryDate",
			setMethod = "setExpiryDate"
	)
	private Date expiryDate;

	@Column(
			table = "voucher_codes",
			field = "purchase_id",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getPurchaseId",
			setMethod = "setPurchaseId"
	)
	private Long purchase_id;

	@Column(
			table = "voucher_codes",
			field = "redeemed_date",
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
			getMethod = "getRedeemedDate",
			setMethod = "setRedeemedDate"
	)
	private Date redeemed_date;

	@Column(
			table = "voucher_codes",
			field = "location_id",
			type = "varchar(50)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 50,
			getMethod = "getLocationId",
			setMethod = "setLocationId"
	)
	private String location_id;

	@Column(
			table = "voucher_codes",
			field = "partner_id",
			type = "varchar(50)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 50,
			getMethod = "getPartnerId",
			setMethod = "setPartnerId"
	)
	private String partner_id;


	public VoucherCodes() {} 

	public VoucherCodes( ResultSet rs ) throws SQLException {

		this.offer_id = rs.getShort( VoucherCodes.Fields.offer_id.name() );
		this.code = rs.getString( VoucherCodes.Fields.code.name() );
		this.expiryDate = rs.getDate( VoucherCodes.Fields.expiryDate.name() );
		this.purchase_id = rs.getLong( VoucherCodes.Fields.purchase_id.name() );
		this.redeemed_date = rs.getDate( VoucherCodes.Fields.redeemed_date.name() );
		this.location_id = rs.getString( VoucherCodes.Fields.location_id.name() );
		this.partner_id = rs.getString( VoucherCodes.Fields.partner_id.name() );

	}

	public VoucherCodes( JSONObject jo ) throws JSONException, ParseException {

		this.offer_id = (short)jo.getInt( VoucherCodes.Fields.offer_id.name() );
		this.code = jo.getString( VoucherCodes.Fields.code.name() );
		this.expiryDate = Format.getMysqlDateTime( jo.getString( VoucherCodes.Fields.expiryDate.name() ) );
		this.purchase_id = (long)jo.getLong( VoucherCodes.Fields.purchase_id.name() );
		this.redeemed_date = Format.getMysqlDateTime( jo.getString( VoucherCodes.Fields.redeemed_date.name() ) );
		this.location_id = jo.getString( VoucherCodes.Fields.location_id.name() );
		this.partner_id = jo.getString( VoucherCodes.Fields.partner_id.name() );

	}

	public Short getOfferId() {

		return this.offer_id;

	}

	public void setOfferId( Short offer_id ) {

		this.offer_id = offer_id;

	}

	public String getCode() {

		return this.code;

	}

	public void setCode( String code ) {

		this.code = code;

	}

	public Date getExpiryDate() {

		return this.expiryDate;

	}

	public void setExpiryDate( Date expiryDate ) {

		this.expiryDate = expiryDate;

	}

	public Long getPurchaseId() {

		return this.purchase_id;

	}

	public void setPurchaseId( Long purchase_id ) {

		this.purchase_id = purchase_id;

	}

	public Date getRedeemedDate() {

		return this.redeemed_date;

	}

	public void setRedeemedDate( Date redeemed_date ) {

		this.redeemed_date = redeemed_date;

	}

	public String getLocationId() {

		return this.location_id;

	}

	public void setLocationId( String location_id ) {

		this.location_id = location_id;

	}

	public String getPartnerId() {

		return this.partner_id;

	}

	public void setPartnerId( String partner_id ) {

		this.partner_id = partner_id;

	}

	public Fields[] getEntityFields() {

		return VoucherCodes.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"offer_id\": \"" ).append( this.getOfferId() ).append( "\", " )
			.append( "\"code\": \"" ).append( this.getCode() ).append( "\", " )
			.append( "\"expiryDate\": \"" ).append( this.getExpiryDate() ).append( "\", " )
			.append( "\"purchase_id\": \"" ).append( this.getPurchaseId() ).append( "\", " )
			.append( "\"redeemed_date\": \"" ).append( this.getRedeemedDate() ).append( "\", " )
			.append( "\"location_id\": \"" ).append( this.getLocationId() ).append( "\", " )
			.append( "\"partner_id\": \"" ).append( this.getPartnerId() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }