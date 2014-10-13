package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;


@Table( "catalog_offer_price_channels" )
public class CatalogOfferPriceChannels { 

	public enum Fields { offer_price_id, channel_id }

	@Column(
			table = "catalog_offer_price_channels",
			field = "offer_price_id",
			type = "smallint(5)",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 5,
			getMethod = "getOfferPriceId",
			setMethod = "setOfferPriceId"
	)
	private Short offer_price_id;

	@Column(
			table = "catalog_offer_price_channels",
			field = "channel_id",
			type = "smallint(5)",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 5,
			getMethod = "getChannelId",
			setMethod = "setChannelId"
	)
	private Short channel_id;


	public CatalogOfferPriceChannels() {} 

	public CatalogOfferPriceChannels( ResultSet rs ) throws SQLException {

		this.offer_price_id = rs.getShort( CatalogOfferPriceChannels.Fields.offer_price_id.name() );
		this.channel_id = rs.getShort( CatalogOfferPriceChannels.Fields.channel_id.name() );

	}

	public CatalogOfferPriceChannels( JSONObject jo ) throws JSONException {

		this.offer_price_id = (short)jo.getInt( CatalogOfferPriceChannels.Fields.offer_price_id.name() );
		this.channel_id = (short)jo.getInt( CatalogOfferPriceChannels.Fields.channel_id.name() );

	}

	public Short getOfferPriceId() {

		return this.offer_price_id;

	}

	public void setOfferPriceId( Short offer_price_id ) {

		this.offer_price_id = offer_price_id;

	}

	public Short getChannelId() {

		return this.channel_id;

	}

	public void setChannelId( Short channel_id ) {

		this.channel_id = channel_id;

	}

	public Fields[] getEntityFields() {

		return CatalogOfferPriceChannels.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"offer_price_id\": \"" ).append( this.getOfferPriceId() ).append( "\", " )
			.append( "\"channel_id\": \"" ).append( this.getChannelId() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }