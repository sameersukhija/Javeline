package com.lumata.expression.operators.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.util.Date;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "offoptim_customer_items_released" )
public class OffoptimCustomerItemsReleased { 

	public enum Fields { released_customer_offer_pack_id, offer_id, channel_id, released_date }

	@Column(
			table = "offoptim_customer_items_released",
			field = "released_customer_offer_pack_id",
			type = "int(10) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 10,
			getMethod = "getReleasedCustomerOfferPackId",
			setMethod = "setReleasedCustomerOfferPackId"
	)
	private Integer released_customer_offer_pack_id;

	@Column(
			table = "offoptim_customer_items_released",
			field = "offer_id",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 5,
			getMethod = "getOfferId",
			setMethod = "setOfferId"
	)
	private Short offer_id;

	@Column(
			table = "offoptim_customer_items_released",
			field = "channel_id",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 5,
			getMethod = "getChannelId",
			setMethod = "setChannelId"
	)
	private Short channel_id;

	@Column(
			table = "offoptim_customer_items_released",
			field = "released_date",
			type = "datetime",
			mysqlType = "datetime",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getReleasedDate",
			setMethod = "setReleasedDate"
	)
	private Date released_date;


	public OffoptimCustomerItemsReleased() {} 

	public OffoptimCustomerItemsReleased( ResultSet rs ) throws SQLException {

		this.released_customer_offer_pack_id = rs.getInt( OffoptimCustomerItemsReleased.Fields.released_customer_offer_pack_id.name() );
		this.offer_id = rs.getShort( OffoptimCustomerItemsReleased.Fields.offer_id.name() );
		this.channel_id = rs.getShort( OffoptimCustomerItemsReleased.Fields.channel_id.name() );
		this.released_date = rs.getDate( OffoptimCustomerItemsReleased.Fields.released_date.name() );

	}

	public OffoptimCustomerItemsReleased( JSONObject jo ) throws JSONException, ParseException {

		this.released_customer_offer_pack_id = (int)jo.getInt( OffoptimCustomerItemsReleased.Fields.released_customer_offer_pack_id.name() );
		this.offer_id = (short)jo.getInt( OffoptimCustomerItemsReleased.Fields.offer_id.name() );
		this.channel_id = (short)jo.getInt( OffoptimCustomerItemsReleased.Fields.channel_id.name() );
		this.released_date = Format.getMysqlDateTime( jo.getString( OffoptimCustomerItemsReleased.Fields.released_date.name() ) );

	}

	public Integer getReleasedCustomerOfferPackId() {

		return this.released_customer_offer_pack_id;

	}

	public void setReleasedCustomerOfferPackId( Integer released_customer_offer_pack_id ) {

		this.released_customer_offer_pack_id = released_customer_offer_pack_id;

	}

	public Short getOfferId() {

		return this.offer_id;

	}

	public void setOfferId( Short offer_id ) {

		this.offer_id = offer_id;

	}

	public Short getChannelId() {

		return this.channel_id;

	}

	public void setChannelId( Short channel_id ) {

		this.channel_id = channel_id;

	}

	public Date getReleasedDate() {

		return this.released_date;

	}

	public void setReleasedDate( Date released_date ) {

		this.released_date = released_date;

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"released_customer_offer_pack_id\": \"" ).append( this.getReleasedCustomerOfferPackId() ).append( "\", " )
			.append( "\"offer_id\": \"" ).append( this.getOfferId() ).append( "\", " )
			.append( "\"channel_id\": \"" ).append( this.getChannelId() ).append( "\", " )
			.append( "\"released_date\": \"" ).append( this.getReleasedDate() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }