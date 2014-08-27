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


@Table( "stats_subs_tarif_optionen" )
public class StatsSubsTarifOptionen { 

	public enum Fields { msisdn, tarif_optionen, tarif_optionen_enabled, agg_date }

	@Column(
			table = "stats_subs_tarif_optionen",
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
			getMethod = "getMsisdn",
			setMethod = "setMsisdn"
	)
	private Long msisdn;

	@Column(
			table = "stats_subs_tarif_optionen",
			field = "tarif_optionen",
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
			getMethod = "getTarifOptionen",
			setMethod = "setTarifOptionen"
	)
	private Long tarif_optionen;

	@Column(
			table = "stats_subs_tarif_optionen",
			field = "tarif_optionen_enabled",
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
			getMethod = "getTarifOptionenEnabled",
			setMethod = "setTarifOptionenEnabled"
	)
	private Byte tarif_optionen_enabled;

	@Column(
			table = "stats_subs_tarif_optionen",
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
			getMethod = "getAggDate",
			setMethod = "setAggDate"
	)
	private Date agg_date;


	public StatsSubsTarifOptionen() {} 

	public StatsSubsTarifOptionen( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( StatsSubsTarifOptionen.Fields.msisdn.name() );
		this.tarif_optionen = rs.getLong( StatsSubsTarifOptionen.Fields.tarif_optionen.name() );
		this.tarif_optionen_enabled = rs.getByte( StatsSubsTarifOptionen.Fields.tarif_optionen_enabled.name() );
		this.agg_date = rs.getDate( StatsSubsTarifOptionen.Fields.agg_date.name() );

	}

	public StatsSubsTarifOptionen( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( StatsSubsTarifOptionen.Fields.msisdn.name() );
		this.tarif_optionen = (long)jo.getLong( StatsSubsTarifOptionen.Fields.tarif_optionen.name() );
		this.tarif_optionen_enabled = (byte)jo.getInt( StatsSubsTarifOptionen.Fields.tarif_optionen_enabled.name() );
		this.agg_date = Format.getMysqlDateTime( jo.getString( StatsSubsTarifOptionen.Fields.agg_date.name() ) );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public void setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

	}

	public Long getTarifOptionen() {

		return this.tarif_optionen;

	}

	public void setTarifOptionen( Long tarif_optionen ) {

		this.tarif_optionen = tarif_optionen;

	}

	public Byte getTarifOptionenEnabled() {

		return this.tarif_optionen_enabled;

	}

	public void setTarifOptionenEnabled( Byte tarif_optionen_enabled ) {

		this.tarif_optionen_enabled = tarif_optionen_enabled;

	}

	public Date getAggDate() {

		return this.agg_date;

	}

	public void setAggDate( Date agg_date ) {

		this.agg_date = agg_date;

	}

	public Fields[] getEntityFields() {

		return StatsSubsTarifOptionen.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"tarif_optionen\": \"" ).append( this.getTarifOptionen() ).append( "\", " )
			.append( "\"tarif_optionen_enabled\": \"" ).append( this.getTarifOptionenEnabled() ).append( "\", " )
			.append( "\"agg_date\": \"" ).append( this.getAggDate() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }