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


@Table( "stats_purchase" )
public class StatsPurchase { 

	public enum Fields { purchase_id, msisdn, agg_date_time, offer_id, quantity, channel_id, price_offer, module_id, feature }

	@Column(
			table = "stats_purchase",
			field = "purchase_id",
			type = "bigint(20)",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = true,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 20,
			getMethod = "getPurchaseId",
			setMethod = "setPurchaseId"
	)
	private Long purchase_id;

	@Column(
			table = "stats_purchase",
			field = "msisdn",
			type = "bigint(20)",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getMsisdn",
			setMethod = "setMsisdn"
	)
	private Long msisdn;

	@Column(
			table = "stats_purchase",
			field = "agg_date_time",
			type = "datetime",
			mysqlType = "datetime",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getAggDateTime",
			setMethod = "setAggDateTime"
	)
	private Date agg_date_time;

	@Column(
			table = "stats_purchase",
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
			table = "stats_purchase",
			field = "quantity",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getQuantity",
			setMethod = "setQuantity"
	)
	private Integer quantity;

	@Column(
			table = "stats_purchase",
			field = "channel_id",
			type = "smallint(6)",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 6,
			getMethod = "getChannelId",
			setMethod = "setChannelId"
	)
	private Short channel_id;

	@Column(
			table = "stats_purchase",
			field = "price_offer",
			type = "varchar(100)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 100,
			getMethod = "getPriceOffer",
			setMethod = "setPriceOffer"
	)
	private String price_offer;

	@Column(
			table = "stats_purchase",
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
			getMethod = "getModuleId",
			setMethod = "setModuleId"
	)
	private Byte module_id;

	@Column(
			table = "stats_purchase",
			field = "feature",
			type = "varchar(55)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 55,
			getMethod = "getFeature",
			setMethod = "setFeature"
	)
	private String feature;


	public StatsPurchase() {} 

	public StatsPurchase( ResultSet rs ) throws SQLException {

		this.purchase_id = rs.getLong( StatsPurchase.Fields.purchase_id.name() );
		this.msisdn = rs.getLong( StatsPurchase.Fields.msisdn.name() );
		this.agg_date_time = rs.getDate( StatsPurchase.Fields.agg_date_time.name() );
		this.offer_id = rs.getShort( StatsPurchase.Fields.offer_id.name() );
		this.quantity = rs.getInt( StatsPurchase.Fields.quantity.name() );
		this.channel_id = rs.getShort( StatsPurchase.Fields.channel_id.name() );
		this.price_offer = rs.getString( StatsPurchase.Fields.price_offer.name() );
		this.module_id = rs.getByte( StatsPurchase.Fields.module_id.name() );
		this.feature = rs.getString( StatsPurchase.Fields.feature.name() );

	}

	public StatsPurchase( JSONObject jo ) throws JSONException, ParseException {

		this.purchase_id = (long)jo.getLong( StatsPurchase.Fields.purchase_id.name() );
		this.msisdn = (long)jo.getLong( StatsPurchase.Fields.msisdn.name() );
		this.agg_date_time = Format.getMysqlDateTime( jo.getString( StatsPurchase.Fields.agg_date_time.name() ) );
		this.offer_id = (short)jo.getInt( StatsPurchase.Fields.offer_id.name() );
		this.quantity = (int)jo.getInt( StatsPurchase.Fields.quantity.name() );
		this.channel_id = (short)jo.getInt( StatsPurchase.Fields.channel_id.name() );
		this.price_offer = jo.getString( StatsPurchase.Fields.price_offer.name() );
		this.module_id = (byte)jo.getInt( StatsPurchase.Fields.module_id.name() );
		this.feature = jo.getString( StatsPurchase.Fields.feature.name() );

	}

	public Long getPurchaseId() {

		return this.purchase_id;

	}

	public void setPurchaseId( Long purchase_id ) {

		this.purchase_id = purchase_id;

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public void setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

	}

	public Date getAggDateTime() {

		return this.agg_date_time;

	}

	public void setAggDateTime( Date agg_date_time ) {

		this.agg_date_time = agg_date_time;

	}

	public Short getOfferId() {

		return this.offer_id;

	}

	public void setOfferId( Short offer_id ) {

		this.offer_id = offer_id;

	}

	public Integer getQuantity() {

		return this.quantity;

	}

	public void setQuantity( Integer quantity ) {

		this.quantity = quantity;

	}

	public Short getChannelId() {

		return this.channel_id;

	}

	public void setChannelId( Short channel_id ) {

		this.channel_id = channel_id;

	}

	public String getPriceOffer() {

		return this.price_offer;

	}

	public void setPriceOffer( String price_offer ) {

		this.price_offer = price_offer;

	}

	public Byte getModuleId() {

		return this.module_id;

	}

	public void setModuleId( Byte module_id ) {

		this.module_id = module_id;

	}

	public String getFeature() {

		return this.feature;

	}

	public void setFeature( String feature ) {

		this.feature = feature;

	}

	public Fields[] getEntityFields() {

		return StatsPurchase.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"purchase_id\": \"" ).append( this.getPurchaseId() ).append( "\", " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"agg_date_time\": \"" ).append( this.getAggDateTime() ).append( "\", " )
			.append( "\"offer_id\": \"" ).append( this.getOfferId() ).append( "\", " )
			.append( "\"quantity\": \"" ).append( this.getQuantity() ).append( "\", " )
			.append( "\"channel_id\": \"" ).append( this.getChannelId() ).append( "\", " )
			.append( "\"price_offer\": \"" ).append( this.getPriceOffer() ).append( "\", " )
			.append( "\"module_id\": \"" ).append( this.getModuleId() ).append( "\", " )
			.append( "\"feature\": \"" ).append( this.getFeature() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }