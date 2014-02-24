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


@Table( "stats_subs_bundle" )
public class StatsSubsBundle { 

	public enum Fields { msisdn, bundle, bundle_balance, qty_bundle_purchased, agg_date }

	@Column(
			table = "stats_subs_bundle",
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
			table = "stats_subs_bundle",
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
			table = "stats_subs_bundle",
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
			table = "stats_subs_bundle",
			field = "qty_bundle_purchased",
			type = "mediumint(8) unsigned",
			mysqlType = "mediumint",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 8,
			getMethod = "getQtyBundlePurchased",
			setMethod = "setQtyBundlePurchased"
	)
	private Integer qty_bundle_purchased;

	@Column(
			table = "stats_subs_bundle",
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


	public StatsSubsBundle() {} 

	public StatsSubsBundle( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( StatsSubsBundle.Fields.msisdn.name() );
		this.bundle = rs.getLong( StatsSubsBundle.Fields.bundle.name() );
		this.bundle_balance = rs.getInt( StatsSubsBundle.Fields.bundle_balance.name() );
		this.qty_bundle_purchased = rs.getInt( StatsSubsBundle.Fields.qty_bundle_purchased.name() );
		this.agg_date = rs.getDate( StatsSubsBundle.Fields.agg_date.name() );

	}

	public StatsSubsBundle( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( StatsSubsBundle.Fields.msisdn.name() );
		this.bundle = (long)jo.getLong( StatsSubsBundle.Fields.bundle.name() );
		this.bundle_balance = (int)jo.getInt( StatsSubsBundle.Fields.bundle_balance.name() );
		this.qty_bundle_purchased = (int)jo.getInt( StatsSubsBundle.Fields.qty_bundle_purchased.name() );
		this.agg_date = Format.getMysqlDateTime( jo.getString( StatsSubsBundle.Fields.agg_date.name() ) );

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

	public Integer getQtyBundlePurchased() {

		return this.qty_bundle_purchased;

	}

	public void setQtyBundlePurchased( Integer qty_bundle_purchased ) {

		this.qty_bundle_purchased = qty_bundle_purchased;

	}

	public Date getAggDate() {

		return this.agg_date;

	}

	public void setAggDate( Date agg_date ) {

		this.agg_date = agg_date;

	}

	public Fields[] getEntityFields() {

		return StatsSubsBundle.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"bundle\": \"" ).append( this.getBundle() ).append( "\", " )
			.append( "\"bundle_balance\": \"" ).append( this.getBundleBalance() ).append( "\", " )
			.append( "\"qty_bundle_purchased\": \"" ).append( this.getQtyBundlePurchased() ).append( "\", " )
			.append( "\"agg_date\": \"" ).append( this.getAggDate() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }