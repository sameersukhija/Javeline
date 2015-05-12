package com.lumata.e4o.schema.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.annotations.mysql.Column;
import java.sql.Timestamp;
import java.text.ParseException;
import com.lumata.common.testing.validating.Format;


@Table( "stats_offer" )
public class StatsOffer { 

	public enum Fields { msisdn, offer_id, qty_allocated, qty_purchased, qty_remaining, update_time }

	@Column(
			table = "stats_offer",
			field = "msisdn",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 20,
			comment = "",
			getMethod = "getMsisdn",
			setMethod = "setMsisdn"
	)
	private Long msisdn;

	@Column(
			table = "stats_offer",
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
			comment = "",
			getMethod = "getOfferId",
			setMethod = "setOfferId"
	)
	private Short offer_id;

	@Column(
			table = "stats_offer",
			field = "qty_allocated",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 5,
			comment = "",
			getMethod = "getQtyAllocated",
			setMethod = "setQtyAllocated"
	)
	private Short qty_allocated;

	@Column(
			table = "stats_offer",
			field = "qty_purchased",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 5,
			comment = "",
			getMethod = "getQtyPurchased",
			setMethod = "setQtyPurchased"
	)
	private Short qty_purchased;

	@Column(
			table = "stats_offer",
			field = "qty_remaining",
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
			comment = "",
			getMethod = "getQtyRemaining",
			setMethod = "setQtyRemaining"
	)
	private Short qty_remaining;

	@Column(
			table = "stats_offer",
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
			comment = "",
			getMethod = "getUpdateTime",
			setMethod = "setUpdateTime"
	)
	private Timestamp update_time;


	public StatsOffer() {} 

	public StatsOffer( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( StatsOffer.Fields.msisdn.name() );
		this.offer_id = rs.getShort( StatsOffer.Fields.offer_id.name() );
		this.qty_allocated = rs.getShort( StatsOffer.Fields.qty_allocated.name() );
		this.qty_purchased = rs.getShort( StatsOffer.Fields.qty_purchased.name() );
		this.qty_remaining = rs.getShort( StatsOffer.Fields.qty_remaining.name() );
		this.update_time = rs.getTimestamp( StatsOffer.Fields.update_time.name() );

	}

	public StatsOffer( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( StatsOffer.Fields.msisdn.name() );
		this.offer_id = (short)jo.getInt( StatsOffer.Fields.offer_id.name() );
		this.qty_allocated = (short)jo.getInt( StatsOffer.Fields.qty_allocated.name() );
		this.qty_purchased = (short)jo.getInt( StatsOffer.Fields.qty_purchased.name() );
		this.qty_remaining = (short)jo.getInt( StatsOffer.Fields.qty_remaining.name() );
		this.update_time = new Timestamp( Format.getMysqlDateTime( jo.getString( StatsOffer.Fields.update_time.name() ) ).getTime() );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public StatsOffer setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

		return this;

	}

	public Short getOfferId() {

		return this.offer_id;

	}

	public StatsOffer setOfferId( Short offer_id ) {

		this.offer_id = offer_id;

		return this;

	}

	public Short getQtyAllocated() {

		return this.qty_allocated;

	}

	public StatsOffer setQtyAllocated( Short qty_allocated ) {

		this.qty_allocated = qty_allocated;

		return this;

	}

	public Short getQtyPurchased() {

		return this.qty_purchased;

	}

	public StatsOffer setQtyPurchased( Short qty_purchased ) {

		this.qty_purchased = qty_purchased;

		return this;

	}

	public Short getQtyRemaining() {

		return this.qty_remaining;

	}

	public StatsOffer setQtyRemaining( Short qty_remaining ) {

		this.qty_remaining = qty_remaining;

		return this;

	}

	public Timestamp getUpdateTime() {

		return this.update_time;

	}

	public StatsOffer setUpdateTime( Timestamp update_time ) {

		this.update_time = update_time;

		return this;

	}

	public Fields[] getEntityFields() {

		return StatsOffer.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"offer_id\": \"" ).append( this.getOfferId() ).append( "\", " )
			.append( "\"qty_allocated\": \"" ).append( this.getQtyAllocated() ).append( "\", " )
			.append( "\"qty_purchased\": \"" ).append( this.getQtyPurchased() ).append( "\", " )
			.append( "\"qty_remaining\": \"" ).append( this.getQtyRemaining() ).append( "\", " )
			.append( "\"update_time\": \"" ).append( this.getUpdateTime() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }