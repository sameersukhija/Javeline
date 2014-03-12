package com.lumata.e4o_tenant.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.sql.Timestamp;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "offer_stock" )
public class OfferStock { 

	public enum Fields { offer_id, channel_id, initial_stock, available, purchased, refused, update_time }

	@Column(
			table = "offer_stock",
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
			table = "offer_stock",
			field = "channel_id",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "0",
			extra = "",
			length = 5,
			getMethod = "getChannelId",
			setMethod = "setChannelId"
	)
	private Short channel_id;

	@Column(
			table = "offer_stock",
			field = "initial_stock",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 20,
			getMethod = "getInitialStock",
			setMethod = "setInitialStock"
	)
	private Long initial_stock;

	@Column(
			table = "offer_stock",
			field = "available",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 20,
			getMethod = "getAvailable",
			setMethod = "setAvailable"
	)
	private Long available;

	@Column(
			table = "offer_stock",
			field = "purchased",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 20,
			getMethod = "getPurchased",
			setMethod = "setPurchased"
	)
	private Long purchased;

	@Column(
			table = "offer_stock",
			field = "refused",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 20,
			getMethod = "getRefused",
			setMethod = "setRefused"
	)
	private Long refused;

	@Column(
			table = "offer_stock",
			field = "update_time",
			type = "timestamp",
			mysqlType = "timestamp",
			javaType = "Timestamp",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "CURRENT_TIMESTAMP",
			extra = "on update CURRENT_TIMESTAMP",
			length = 0,
			getMethod = "getUpdateTime",
			setMethod = "setUpdateTime"
	)
	private Timestamp update_time;


	public OfferStock() {} 

	public OfferStock( ResultSet rs ) throws SQLException {

		this.offer_id = rs.getShort( OfferStock.Fields.offer_id.name() );
		this.channel_id = rs.getShort( OfferStock.Fields.channel_id.name() );
		this.initial_stock = rs.getLong( OfferStock.Fields.initial_stock.name() );
		this.available = rs.getLong( OfferStock.Fields.available.name() );
		this.purchased = rs.getLong( OfferStock.Fields.purchased.name() );
		this.refused = rs.getLong( OfferStock.Fields.refused.name() );
		this.update_time = rs.getTimestamp( OfferStock.Fields.update_time.name() );

	}

	public OfferStock( JSONObject jo ) throws JSONException, ParseException {

		this.offer_id = (short)jo.getInt( OfferStock.Fields.offer_id.name() );
		this.channel_id = (short)jo.getInt( OfferStock.Fields.channel_id.name() );
		this.initial_stock = (long)jo.getLong( OfferStock.Fields.initial_stock.name() );
		this.available = (long)jo.getLong( OfferStock.Fields.available.name() );
		this.purchased = (long)jo.getLong( OfferStock.Fields.purchased.name() );
		this.refused = (long)jo.getLong( OfferStock.Fields.refused.name() );
		this.update_time = new Timestamp( Format.getMysqlDateTime( jo.getString( OfferStock.Fields.update_time.name() ) ).getTime() );

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

	public Long getInitialStock() {

		return this.initial_stock;

	}

	public void setInitialStock( Long initial_stock ) {

		this.initial_stock = initial_stock;

	}

	public Long getAvailable() {

		return this.available;

	}

	public void setAvailable( Long available ) {

		this.available = available;

	}

	public Long getPurchased() {

		return this.purchased;

	}

	public void setPurchased( Long purchased ) {

		this.purchased = purchased;

	}

	public Long getRefused() {

		return this.refused;

	}

	public void setRefused( Long refused ) {

		this.refused = refused;

	}

	public Timestamp getUpdateTime() {

		return this.update_time;

	}

	public void setUpdateTime( Timestamp update_time ) {

		this.update_time = update_time;

	}

	public Fields[] getEntityFields() {

		return OfferStock.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"offer_id\": \"" ).append( this.getOfferId() ).append( "\", " )
			.append( "\"channel_id\": \"" ).append( this.getChannelId() ).append( "\", " )
			.append( "\"initial_stock\": \"" ).append( this.getInitialStock() ).append( "\", " )
			.append( "\"available\": \"" ).append( this.getAvailable() ).append( "\", " )
			.append( "\"purchased\": \"" ).append( this.getPurchased() ).append( "\", " )
			.append( "\"refused\": \"" ).append( this.getRefused() ).append( "\", " )
			.append( "\"update_time\": \"" ).append( this.getUpdateTime() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }