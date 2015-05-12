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


@Table( "stats_churn" )
public class StatsChurn { 

	public enum Fields { msisdn, agg_date, question, profile_id, state, proba, rank }

	@Column(
			table = "stats_churn",
			field = "msisdn",
			type = "bigint(19) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 19,
			comment = "",
			getMethod = "getMsisdn",
			setMethod = "setMsisdn"
	)
	private Long msisdn;

	@Column(
			table = "stats_churn",
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
			table = "stats_churn",
			field = "question",
			type = "smallint(6)",
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
			getMethod = "getQuestion",
			setMethod = "setQuestion"
	)
	private Short question;

	@Column(
			table = "stats_churn",
			field = "profile_id",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getProfileId",
			setMethod = "setProfileId"
	)
	private Byte profile_id;

	@Column(
			table = "stats_churn",
			field = "state",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getState",
			setMethod = "setState"
	)
	private Byte state;

	@Column(
			table = "stats_churn",
			field = "proba",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getProba",
			setMethod = "setProba"
	)
	private Byte proba;

	@Column(
			table = "stats_churn",
			field = "rank",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getRank",
			setMethod = "setRank"
	)
	private Byte rank;


	public StatsChurn() {} 

	public StatsChurn( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( StatsChurn.Fields.msisdn.name() );
		this.agg_date = rs.getDate( StatsChurn.Fields.agg_date.name() );
		this.question = rs.getShort( StatsChurn.Fields.question.name() );
		this.profile_id = rs.getByte( StatsChurn.Fields.profile_id.name() );
		this.state = rs.getByte( StatsChurn.Fields.state.name() );
		this.proba = rs.getByte( StatsChurn.Fields.proba.name() );
		this.rank = rs.getByte( StatsChurn.Fields.rank.name() );

	}

	public StatsChurn( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( StatsChurn.Fields.msisdn.name() );
		this.agg_date = Format.getMysqlDateTime( jo.getString( StatsChurn.Fields.agg_date.name() ) );
		this.question = (short)jo.getInt( StatsChurn.Fields.question.name() );
		this.profile_id = (byte)jo.getInt( StatsChurn.Fields.profile_id.name() );
		this.state = (byte)jo.getInt( StatsChurn.Fields.state.name() );
		this.proba = (byte)jo.getInt( StatsChurn.Fields.proba.name() );
		this.rank = (byte)jo.getInt( StatsChurn.Fields.rank.name() );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public StatsChurn setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

		return this;

	}

	public Date getAggDate() {

		return this.agg_date;

	}

	public StatsChurn setAggDate( Date agg_date ) {

		this.agg_date = agg_date;

		return this;

	}

	public Short getQuestion() {

		return this.question;

	}

	public StatsChurn setQuestion( Short question ) {

		this.question = question;

		return this;

	}

	public Byte getProfileId() {

		return this.profile_id;

	}

	public StatsChurn setProfileId( Byte profile_id ) {

		this.profile_id = profile_id;

		return this;

	}

	public Byte getState() {

		return this.state;

	}

	public StatsChurn setState( Byte state ) {

		this.state = state;

		return this;

	}

	public Byte getProba() {

		return this.proba;

	}

	public StatsChurn setProba( Byte proba ) {

		this.proba = proba;

		return this;

	}

	public Byte getRank() {

		return this.rank;

	}

	public StatsChurn setRank( Byte rank ) {

		this.rank = rank;

		return this;

	}

	public Fields[] getEntityFields() {

		return StatsChurn.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"agg_date\": \"" ).append( this.getAggDate() ).append( "\", " )
			.append( "\"question\": \"" ).append( this.getQuestion() ).append( "\", " )
			.append( "\"profile_id\": \"" ).append( this.getProfileId() ).append( "\", " )
			.append( "\"state\": \"" ).append( this.getState() ).append( "\", " )
			.append( "\"proba\": \"" ).append( this.getProba() ).append( "\", " )
			.append( "\"rank\": \"" ).append( this.getRank() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }