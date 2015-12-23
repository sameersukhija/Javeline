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


@Table( "offoptim_offer_history" )
public class OffoptimOfferHistory { 

	public enum Fields { agg_date, offer_id, channel_id, consumed, average_2Be_consumed_per_day }

	@Column(
			table = "offoptim_offer_history",
			field = "agg_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "",
			getMethod = "getAggDate",
			setMethod = "setAggDate"
	)
	private Date agg_date;

	@Column(
			table = "offoptim_offer_history",
			field = "offer_id",
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
			comment = "",
			getMethod = "getOfferId",
			setMethod = "setOfferId"
	)
	private Integer offer_id;

	@Column(
			table = "offoptim_offer_history",
			field = "channel_id",
			type = "smallint(6) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 6,
			comment = "",
			getMethod = "getChannelId",
			setMethod = "setChannelId"
	)
	private Short channel_id;

	@Column(
			table = "offoptim_offer_history",
			field = "consumed",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 11,
			comment = "",
			getMethod = "getConsumed",
			setMethod = "setConsumed"
	)
	private Integer consumed;

	@Column(
			table = "offoptim_offer_history",
			field = "average_2Be_consumed_per_day",
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
			comment = "",
			getMethod = "getAverage2BeConsumedPerDay",
			setMethod = "setAverage2BeConsumedPerDay"
	)
	private Integer average_2Be_consumed_per_day;


	public OffoptimOfferHistory() {} 

	public OffoptimOfferHistory( ResultSet rs ) throws SQLException {

		this.agg_date = rs.getDate( OffoptimOfferHistory.Fields.agg_date.name() );
		this.offer_id = rs.getInt( OffoptimOfferHistory.Fields.offer_id.name() );
		this.channel_id = rs.getShort( OffoptimOfferHistory.Fields.channel_id.name() );
		this.consumed = rs.getInt( OffoptimOfferHistory.Fields.consumed.name() );
		this.average_2Be_consumed_per_day = rs.getInt( OffoptimOfferHistory.Fields.average_2Be_consumed_per_day.name() );

	}

	public OffoptimOfferHistory( JSONObject jo ) throws JSONException, ParseException {

		this.agg_date = Format.getMysqlDateTime( jo.getString( OffoptimOfferHistory.Fields.agg_date.name() ) );
		this.offer_id = (int)jo.getInt( OffoptimOfferHistory.Fields.offer_id.name() );
		this.channel_id = (short)jo.getInt( OffoptimOfferHistory.Fields.channel_id.name() );
		this.consumed = (int)jo.getInt( OffoptimOfferHistory.Fields.consumed.name() );
		this.average_2Be_consumed_per_day = (int)jo.getInt( OffoptimOfferHistory.Fields.average_2Be_consumed_per_day.name() );

	}

	public Date getAggDate() {

		return this.agg_date;

	}

	public OffoptimOfferHistory setAggDate( Date agg_date ) {

		this.agg_date = agg_date;

		return this;

	}

	public Integer getOfferId() {

		return this.offer_id;

	}

	public OffoptimOfferHistory setOfferId( Integer offer_id ) {

		this.offer_id = offer_id;

		return this;

	}

	public Short getChannelId() {

		return this.channel_id;

	}

	public OffoptimOfferHistory setChannelId( Short channel_id ) {

		this.channel_id = channel_id;

		return this;

	}

	public Integer getConsumed() {

		return this.consumed;

	}

	public OffoptimOfferHistory setConsumed( Integer consumed ) {

		this.consumed = consumed;

		return this;

	}

	public Integer getAverage2BeConsumedPerDay() {

		return this.average_2Be_consumed_per_day;

	}

	public OffoptimOfferHistory setAverage2BeConsumedPerDay( Integer average_2Be_consumed_per_day ) {

		this.average_2Be_consumed_per_day = average_2Be_consumed_per_day;

		return this;

	}

	public Fields[] getEntityFields() {

		return OffoptimOfferHistory.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"agg_date\": \"" ).append( this.getAggDate() ).append( "\", " )
			.append( "\"offer_id\": \"" ).append( this.getOfferId() ).append( "\", " )
			.append( "\"channel_id\": \"" ).append( this.getChannelId() ).append( "\", " )
			.append( "\"consumed\": \"" ).append( this.getConsumed() ).append( "\", " )
			.append( "\"average_2Be_consumed_per_day\": \"" ).append( this.getAverage2BeConsumedPerDay() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }