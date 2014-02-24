package com.lumata.expression.operators.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.sql.Timestamp;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "product_stock" )
public class ProductStock { 

	public enum Fields { product_id, channel_id, initial_stock, available, purchased, refused, update_time }

	@Column(
			table = "product_stock",
			field = "product_id",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 5,
			getMethod = "getProductId",
			setMethod = "setProductId"
	)
	private Short product_id;

	@Column(
			table = "product_stock",
			field = "channel_id",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "0",
			extra = "",
			length = 5,
			getMethod = "getChannelId",
			setMethod = "setChannelId"
	)
	private Short channel_id;

	@Column(
			table = "product_stock",
			field = "initial_stock",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 20,
			getMethod = "getInitialStock",
			setMethod = "setInitialStock"
	)
	private Long initial_stock;

	@Column(
			table = "product_stock",
			field = "available",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 20,
			getMethod = "getAvailable",
			setMethod = "setAvailable"
	)
	private Long available;

	@Column(
			table = "product_stock",
			field = "purchased",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 20,
			getMethod = "getPurchased",
			setMethod = "setPurchased"
	)
	private Long purchased;

	@Column(
			table = "product_stock",
			field = "refused",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 20,
			getMethod = "getRefused",
			setMethod = "setRefused"
	)
	private Long refused;

	@Column(
			table = "product_stock",
			field = "update_time",
			type = "timestamp",
			mysqlType = "timestamp",
			javaType = "Timestamp",
			categoryType = "Date",
			isNull = false,
			key = "",
			defaultValue = "CURRENT_TIMESTAMP",
			extra = "on update CURRENT_TIMESTAMP",
			length = 0,
			getMethod = "getUpdateTime",
			setMethod = "setUpdateTime"
	)
	private Timestamp update_time;


	public ProductStock() {} 

	public ProductStock( ResultSet rs ) throws SQLException {

		this.product_id = rs.getShort( ProductStock.Fields.product_id.name() );
		this.channel_id = rs.getShort( ProductStock.Fields.channel_id.name() );
		this.initial_stock = rs.getLong( ProductStock.Fields.initial_stock.name() );
		this.available = rs.getLong( ProductStock.Fields.available.name() );
		this.purchased = rs.getLong( ProductStock.Fields.purchased.name() );
		this.refused = rs.getLong( ProductStock.Fields.refused.name() );
		this.update_time = rs.getTimestamp( ProductStock.Fields.update_time.name() );

	}

	public ProductStock( JSONObject jo ) throws JSONException, ParseException {

		this.product_id = (short)jo.getInt( ProductStock.Fields.product_id.name() );
		this.channel_id = (short)jo.getInt( ProductStock.Fields.channel_id.name() );
		this.initial_stock = (long)jo.getLong( ProductStock.Fields.initial_stock.name() );
		this.available = (long)jo.getLong( ProductStock.Fields.available.name() );
		this.purchased = (long)jo.getLong( ProductStock.Fields.purchased.name() );
		this.refused = (long)jo.getLong( ProductStock.Fields.refused.name() );
		this.update_time = new Timestamp( Format.getMysqlDateTime( jo.getString( ProductStock.Fields.update_time.name() ) ).getTime() );

	}

	public Short getProductId() {

		return this.product_id;

	}

	public void setProductId( Short product_id ) {

		this.product_id = product_id;

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

		return ProductStock.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"product_id\": \"" ).append( this.getProductId() ).append( "\", " )
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