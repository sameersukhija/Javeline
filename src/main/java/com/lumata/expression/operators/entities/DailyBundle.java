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
import java.sql.Timestamp;


@Table( "daily_bundle" )
public class DailyBundle { 

	public enum Fields { msisdn, bundle, bundle_balance, qty_bundle_purchased, agg_date, update_time }

	@Column(
			table = "daily_bundle",
			field = "msisdn",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getMsisdn",
			setMethod = "setMsisdn"
	)
	private Long msisdn;

	@Column(
			table = "daily_bundle",
			field = "bundle",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 20,
			getMethod = "getBundle",
			setMethod = "setBundle"
	)
	private Long bundle;

	@Column(
			table = "daily_bundle",
			field = "bundle_balance",
			type = "int(11)",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 11,
			getMethod = "getBundleBalance",
			setMethod = "setBundleBalance"
	)
	private Integer bundle_balance;

	@Column(
			table = "daily_bundle",
			field = "qty_bundle_purchased",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 5,
			getMethod = "getQtyBundlePurchased",
			setMethod = "setQtyBundlePurchased"
	)
	private Short qty_bundle_purchased;

	@Column(
			table = "daily_bundle",
			field = "agg_date",
			type = "date",
			mysqlType = "date",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getAggDate",
			setMethod = "setAggDate"
	)
	private Date agg_date;

	@Column(
			table = "daily_bundle",
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


	public DailyBundle() {} 

	public DailyBundle( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( DailyBundle.Fields.msisdn.name() );
		this.bundle = rs.getLong( DailyBundle.Fields.bundle.name() );
		this.bundle_balance = rs.getInt( DailyBundle.Fields.bundle_balance.name() );
		this.qty_bundle_purchased = rs.getShort( DailyBundle.Fields.qty_bundle_purchased.name() );
		this.agg_date = rs.getDate( DailyBundle.Fields.agg_date.name() );
		this.update_time = rs.getTimestamp( DailyBundle.Fields.update_time.name() );

	}

	public DailyBundle( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( DailyBundle.Fields.msisdn.name() );
		this.bundle = (long)jo.getLong( DailyBundle.Fields.bundle.name() );
		this.bundle_balance = (int)jo.getInt( DailyBundle.Fields.bundle_balance.name() );
		this.qty_bundle_purchased = (short)jo.getInt( DailyBundle.Fields.qty_bundle_purchased.name() );
		this.agg_date = Format.getMysqlDateTime( jo.getString( DailyBundle.Fields.agg_date.name() ) );
		this.update_time = new Timestamp( Format.getMysqlDateTime( jo.getString( DailyBundle.Fields.update_time.name() ) ).getTime() );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public void setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

	}

	public Long getBundle() {

		return this.bundle;

	}

	public void setBundle( Long bundle ) {

		this.bundle = bundle;

	}

	public Integer getBundleBalance() {

		return this.bundle_balance;

	}

	public void setBundleBalance( Integer bundle_balance ) {

		this.bundle_balance = bundle_balance;

	}

	public Short getQtyBundlePurchased() {

		return this.qty_bundle_purchased;

	}

	public void setQtyBundlePurchased( Short qty_bundle_purchased ) {

		this.qty_bundle_purchased = qty_bundle_purchased;

	}

	public Date getAggDate() {

		return this.agg_date;

	}

	public void setAggDate( Date agg_date ) {

		this.agg_date = agg_date;

	}

	public Timestamp getUpdateTime() {

		return this.update_time;

	}

	public void setUpdateTime( Timestamp update_time ) {

		this.update_time = update_time;

	}

	public Fields[] getEntityFields() {

		return DailyBundle.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"bundle\": \"" ).append( this.getBundle() ).append( "\", " )
			.append( "\"bundle_balance\": \"" ).append( this.getBundleBalance() ).append( "\", " )
			.append( "\"qty_bundle_purchased\": \"" ).append( this.getQtyBundlePurchased() ).append( "\", " )
			.append( "\"agg_date\": \"" ).append( this.getAggDate() ).append( "\", " )
			.append( "\"update_time\": \"" ).append( this.getUpdateTime() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }