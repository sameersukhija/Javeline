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


@Table( "offoptim_customer_items" )
public class OffoptimCustomerItems { 

	public enum Fields { customer_offer_pack_id, offer_id, channel_id, allocation_date, offer_status, decision_date, has_voucher }

	@Column(
			table = "offoptim_customer_items",
			field = "customer_offer_pack_id",
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
			getMethod = "getCustomerOfferPackId",
			setMethod = "setCustomerOfferPackId"
	)
	private Integer customer_offer_pack_id;

	@Column(
			table = "offoptim_customer_items",
			field = "offer_id",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 5,
			getMethod = "getOfferId",
			setMethod = "setOfferId"
	)
	private Short offer_id;

	@Column(
			table = "offoptim_customer_items",
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
			getMethod = "getChannelId",
			setMethod = "setChannelId"
	)
	private Byte channel_id;

	@Column(
			table = "offoptim_customer_items",
			field = "allocation_date",
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
			getMethod = "getAllocationDate",
			setMethod = "setAllocationDate"
	)
	private Date allocation_date;

	@Column(
			table = "offoptim_customer_items",
			field = "offer_status",
			type = "enum('allocated','accepted','not_accepted','purchased','not_purchased','refused')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "allocated",
			extra = "",
			length = 6,
			getMethod = "getOfferStatus",
			setMethod = "setOfferStatus"
	)
	private String offer_status;

	@Column(
			table = "offoptim_customer_items",
			field = "decision_date",
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
			getMethod = "getDecisionDate",
			setMethod = "setDecisionDate"
	)
	private Date decision_date;

	@Column(
			table = "offoptim_customer_items",
			field = "has_voucher",
			type = "tinyint(1)",
			mysqlType = "tinyint",
			javaType = "Boolean",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 1,
			getMethod = "getHasVoucher",
			setMethod = "setHasVoucher"
	)
	private Boolean has_voucher;


	public OffoptimCustomerItems() {} 

	public OffoptimCustomerItems( ResultSet rs ) throws SQLException {

		this.customer_offer_pack_id = rs.getInt( OffoptimCustomerItems.Fields.customer_offer_pack_id.name() );
		this.offer_id = rs.getShort( OffoptimCustomerItems.Fields.offer_id.name() );
		this.channel_id = rs.getByte( OffoptimCustomerItems.Fields.channel_id.name() );
		this.allocation_date = rs.getDate( OffoptimCustomerItems.Fields.allocation_date.name() );
		this.offer_status = rs.getString( OffoptimCustomerItems.Fields.offer_status.name() );
		this.decision_date = rs.getDate( OffoptimCustomerItems.Fields.decision_date.name() );
		this.has_voucher = rs.getBoolean( OffoptimCustomerItems.Fields.has_voucher.name() );

	}

	public OffoptimCustomerItems( JSONObject jo ) throws JSONException, ParseException {

		this.customer_offer_pack_id = (int)jo.getInt( OffoptimCustomerItems.Fields.customer_offer_pack_id.name() );
		this.offer_id = (short)jo.getInt( OffoptimCustomerItems.Fields.offer_id.name() );
		this.channel_id = (byte)jo.getInt( OffoptimCustomerItems.Fields.channel_id.name() );
		this.allocation_date = Format.getMysqlDateTime( jo.getString( OffoptimCustomerItems.Fields.allocation_date.name() ) );
		this.offer_status = jo.getString( OffoptimCustomerItems.Fields.offer_status.name() );
		this.decision_date = Format.getMysqlDateTime( jo.getString( OffoptimCustomerItems.Fields.decision_date.name() ) );
		this.has_voucher = jo.getBoolean( OffoptimCustomerItems.Fields.has_voucher.name() );

	}

	public Integer getCustomerOfferPackId() {

		return this.customer_offer_pack_id;

	}

	public void setCustomerOfferPackId( Integer customer_offer_pack_id ) {

		this.customer_offer_pack_id = customer_offer_pack_id;

	}

	public Short getOfferId() {

		return this.offer_id;

	}

	public void setOfferId( Short offer_id ) {

		this.offer_id = offer_id;

	}

	public Byte getChannelId() {

		return this.channel_id;

	}

	public void setChannelId( Byte channel_id ) {

		this.channel_id = channel_id;

	}

	public Date getAllocationDate() {

		return this.allocation_date;

	}

	public void setAllocationDate( Date allocation_date ) {

		this.allocation_date = allocation_date;

	}

	public String getOfferStatus() {

		return this.offer_status;

	}

	public void setOfferStatus( String offer_status ) {

		this.offer_status = offer_status;

	}

	public Date getDecisionDate() {

		return this.decision_date;

	}

	public void setDecisionDate( Date decision_date ) {

		this.decision_date = decision_date;

	}

	public Boolean getHasVoucher() {

		return this.has_voucher;

	}

	public void setHasVoucher( Boolean has_voucher ) {

		this.has_voucher = has_voucher;

	}

	public Fields[] getEntityFields() {

		return OffoptimCustomerItems.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"customer_offer_pack_id\": \"" ).append( this.getCustomerOfferPackId() ).append( "\", " )
			.append( "\"offer_id\": \"" ).append( this.getOfferId() ).append( "\", " )
			.append( "\"channel_id\": \"" ).append( this.getChannelId() ).append( "\", " )
			.append( "\"allocation_date\": \"" ).append( this.getAllocationDate() ).append( "\", " )
			.append( "\"offer_status\": \"" ).append( this.getOfferStatus() ).append( "\", " )
			.append( "\"decision_date\": \"" ).append( this.getDecisionDate() ).append( "\", " )
			.append( "\"has_voucher\": \"" ).append( this.getHasVoucher() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }