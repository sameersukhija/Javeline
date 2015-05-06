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


@Table( "stats_bonus" )
public class StatsBonus { 

	public enum Fields { msisdn, agg_date, bonus_id, qty_earned, qty_used, qty_expired }

	@Column(
			table = "stats_bonus",
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
			table = "stats_bonus",
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
			table = "stats_bonus",
			field = "bonus_id",
			type = "smallint(3) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 3,
			comment = "",
			getMethod = "getBonusId",
			setMethod = "setBonusId"
	)
	private Short bonus_id;

	@Column(
			table = "stats_bonus",
			field = "qty_earned",
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
			getMethod = "getQtyEarned",
			setMethod = "setQtyEarned"
	)
	private Integer qty_earned;

	@Column(
			table = "stats_bonus",
			field = "qty_used",
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
			getMethod = "getQtyUsed",
			setMethod = "setQtyUsed"
	)
	private Integer qty_used;

	@Column(
			table = "stats_bonus",
			field = "qty_expired",
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
			getMethod = "getQtyExpired",
			setMethod = "setQtyExpired"
	)
	private Integer qty_expired;


	public StatsBonus() {} 

	public StatsBonus( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( StatsBonus.Fields.msisdn.name() );
		this.agg_date = rs.getDate( StatsBonus.Fields.agg_date.name() );
		this.bonus_id = rs.getShort( StatsBonus.Fields.bonus_id.name() );
		this.qty_earned = rs.getInt( StatsBonus.Fields.qty_earned.name() );
		this.qty_used = rs.getInt( StatsBonus.Fields.qty_used.name() );
		this.qty_expired = rs.getInt( StatsBonus.Fields.qty_expired.name() );

	}

	public StatsBonus( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( StatsBonus.Fields.msisdn.name() );
		this.agg_date = Format.getMysqlDateTime( jo.getString( StatsBonus.Fields.agg_date.name() ) );
		this.bonus_id = (short)jo.getInt( StatsBonus.Fields.bonus_id.name() );
		this.qty_earned = (int)jo.getInt( StatsBonus.Fields.qty_earned.name() );
		this.qty_used = (int)jo.getInt( StatsBonus.Fields.qty_used.name() );
		this.qty_expired = (int)jo.getInt( StatsBonus.Fields.qty_expired.name() );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public StatsBonus setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

		return this;

	}

	public Date getAggDate() {

		return this.agg_date;

	}

	public StatsBonus setAggDate( Date agg_date ) {

		this.agg_date = agg_date;

		return this;

	}

	public Short getBonusId() {

		return this.bonus_id;

	}

	public StatsBonus setBonusId( Short bonus_id ) {

		this.bonus_id = bonus_id;

		return this;

	}

	public Integer getQtyEarned() {

		return this.qty_earned;

	}

	public StatsBonus setQtyEarned( Integer qty_earned ) {

		this.qty_earned = qty_earned;

		return this;

	}

	public Integer getQtyUsed() {

		return this.qty_used;

	}

	public StatsBonus setQtyUsed( Integer qty_used ) {

		this.qty_used = qty_used;

		return this;

	}

	public Integer getQtyExpired() {

		return this.qty_expired;

	}

	public StatsBonus setQtyExpired( Integer qty_expired ) {

		this.qty_expired = qty_expired;

		return this;

	}

	public Fields[] getEntityFields() {

		return StatsBonus.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"agg_date\": \"" ).append( this.getAggDate() ).append( "\", " )
			.append( "\"bonus_id\": \"" ).append( this.getBonusId() ).append( "\", " )
			.append( "\"qty_earned\": \"" ).append( this.getQtyEarned() ).append( "\", " )
			.append( "\"qty_used\": \"" ).append( this.getQtyUsed() ).append( "\", " )
			.append( "\"qty_expired\": \"" ).append( this.getQtyExpired() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }