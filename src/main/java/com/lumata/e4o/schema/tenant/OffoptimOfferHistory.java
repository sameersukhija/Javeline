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

	public enum Fields { offer_id, today_consumed, yesterday_consumed, last_modified_date }

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
			field = "today_consumed",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "1",
			extra = "",
			length = 11,
			comment = "",
			getMethod = "getTodayConsumed",
			setMethod = "setTodayConsumed"
	)
	private Integer today_consumed;

	@Column(
			table = "offoptim_offer_history",
			field = "yesterday_consumed",
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
			getMethod = "getYesterdayConsumed",
			setMethod = "setYesterdayConsumed"
	)
	private Integer yesterday_consumed;

	@Column(
			table = "offoptim_offer_history",
			field = "last_modified_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "",
			getMethod = "getLastModifiedDate",
			setMethod = "setLastModifiedDate"
	)
	private Date last_modified_date;


	public OffoptimOfferHistory() {} 

	public OffoptimOfferHistory( ResultSet rs ) throws SQLException {

		this.offer_id = rs.getInt( OffoptimOfferHistory.Fields.offer_id.name() );
		this.today_consumed = rs.getInt( OffoptimOfferHistory.Fields.today_consumed.name() );
		this.yesterday_consumed = rs.getInt( OffoptimOfferHistory.Fields.yesterday_consumed.name() );
		this.last_modified_date = rs.getDate( OffoptimOfferHistory.Fields.last_modified_date.name() );

	}

	public OffoptimOfferHistory( JSONObject jo ) throws JSONException, ParseException {

		this.offer_id = (int)jo.getInt( OffoptimOfferHistory.Fields.offer_id.name() );
		this.today_consumed = (int)jo.getInt( OffoptimOfferHistory.Fields.today_consumed.name() );
		this.yesterday_consumed = (int)jo.getInt( OffoptimOfferHistory.Fields.yesterday_consumed.name() );
		this.last_modified_date = Format.getMysqlDateTime( jo.getString( OffoptimOfferHistory.Fields.last_modified_date.name() ) );

	}

	public Integer getOfferId() {

		return this.offer_id;

	}

	public OffoptimOfferHistory setOfferId( Integer offer_id ) {

		this.offer_id = offer_id;

		return this;

	}

	public Integer getTodayConsumed() {

		return this.today_consumed;

	}

	public OffoptimOfferHistory setTodayConsumed( Integer today_consumed ) {

		this.today_consumed = today_consumed;

		return this;

	}

	public Integer getYesterdayConsumed() {

		return this.yesterday_consumed;

	}

	public OffoptimOfferHistory setYesterdayConsumed( Integer yesterday_consumed ) {

		this.yesterday_consumed = yesterday_consumed;

		return this;

	}

	public Date getLastModifiedDate() {

		return this.last_modified_date;

	}

	public OffoptimOfferHistory setLastModifiedDate( Date last_modified_date ) {

		this.last_modified_date = last_modified_date;

		return this;

	}

	public Fields[] getEntityFields() {

		return OffoptimOfferHistory.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"offer_id\": \"" ).append( this.getOfferId() ).append( "\", " )
			.append( "\"today_consumed\": \"" ).append( this.getTodayConsumed() ).append( "\", " )
			.append( "\"yesterday_consumed\": \"" ).append( this.getYesterdayConsumed() ).append( "\", " )
			.append( "\"last_modified_date\": \"" ).append( this.getLastModifiedDate() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }