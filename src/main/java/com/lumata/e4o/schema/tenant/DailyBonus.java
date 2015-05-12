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
import java.sql.Timestamp;


@Table( "daily_bonus" )
public class DailyBonus { 

	public enum Fields { msisdn, event_date, bonus_id, qty_bonus, start_date, end_date, validity_type, event_type, update_time }

	@Column(
			table = "daily_bonus",
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
			table = "daily_bonus",
			field = "event_date",
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
			getMethod = "getEventDate",
			setMethod = "setEventDate"
	)
	private Date event_date;

	@Column(
			table = "daily_bonus",
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
			table = "daily_bonus",
			field = "qty_bonus",
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
			getMethod = "getQtyBonus",
			setMethod = "setQtyBonus"
	)
	private Integer qty_bonus;

	@Column(
			table = "daily_bonus",
			field = "start_date",
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
			getMethod = "getStartDate",
			setMethod = "setStartDate"
	)
	private Date start_date;

	@Column(
			table = "daily_bonus",
			field = "end_date",
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
			getMethod = "getEndDate",
			setMethod = "setEndDate"
	)
	private Date end_date;

	@Column(
			table = "daily_bonus",
			field = "validity_type",
			type = "enum('Fixed','Variable_Days','Variable_Weeks','Variable_Months','Variable_Quarters')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "null",
			extra = "",
			length = 5,
			comment = "",
			getMethod = "getValidityType",
			setMethod = "setValidityType"
	)
	private String validity_type;

	@Column(
			table = "daily_bonus",
			field = "event_type",
			type = "enum('Credit','Debit','Expiration','Set','Activation','Deactivation')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "PRI",
			defaultValue = "Credit",
			extra = "",
			length = 6,
			comment = "",
			getMethod = "getEventType",
			setMethod = "setEventType"
	)
	private String event_type;

	@Column(
			table = "daily_bonus",
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


	public DailyBonus() {} 

	public DailyBonus( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( DailyBonus.Fields.msisdn.name() );
		this.event_date = rs.getDate( DailyBonus.Fields.event_date.name() );
		this.bonus_id = rs.getShort( DailyBonus.Fields.bonus_id.name() );
		this.qty_bonus = rs.getInt( DailyBonus.Fields.qty_bonus.name() );
		this.start_date = rs.getDate( DailyBonus.Fields.start_date.name() );
		this.end_date = rs.getDate( DailyBonus.Fields.end_date.name() );
		this.validity_type = rs.getString( DailyBonus.Fields.validity_type.name() );
		this.event_type = rs.getString( DailyBonus.Fields.event_type.name() );
		this.update_time = rs.getTimestamp( DailyBonus.Fields.update_time.name() );

	}

	public DailyBonus( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( DailyBonus.Fields.msisdn.name() );
		this.event_date = Format.getMysqlDateTime( jo.getString( DailyBonus.Fields.event_date.name() ) );
		this.bonus_id = (short)jo.getInt( DailyBonus.Fields.bonus_id.name() );
		this.qty_bonus = (int)jo.getInt( DailyBonus.Fields.qty_bonus.name() );
		this.start_date = Format.getMysqlDateTime( jo.getString( DailyBonus.Fields.start_date.name() ) );
		this.end_date = Format.getMysqlDateTime( jo.getString( DailyBonus.Fields.end_date.name() ) );
		this.validity_type = jo.getString( DailyBonus.Fields.validity_type.name() );
		this.event_type = jo.getString( DailyBonus.Fields.event_type.name() );
		this.update_time = new Timestamp( Format.getMysqlDateTime( jo.getString( DailyBonus.Fields.update_time.name() ) ).getTime() );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public DailyBonus setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

		return this;

	}

	public Date getEventDate() {

		return this.event_date;

	}

	public DailyBonus setEventDate( Date event_date ) {

		this.event_date = event_date;

		return this;

	}

	public Short getBonusId() {

		return this.bonus_id;

	}

	public DailyBonus setBonusId( Short bonus_id ) {

		this.bonus_id = bonus_id;

		return this;

	}

	public Integer getQtyBonus() {

		return this.qty_bonus;

	}

	public DailyBonus setQtyBonus( Integer qty_bonus ) {

		this.qty_bonus = qty_bonus;

		return this;

	}

	public Date getStartDate() {

		return this.start_date;

	}

	public DailyBonus setStartDate( Date start_date ) {

		this.start_date = start_date;

		return this;

	}

	public Date getEndDate() {

		return this.end_date;

	}

	public DailyBonus setEndDate( Date end_date ) {

		this.end_date = end_date;

		return this;

	}

	public String getValidityType() {

		return this.validity_type;

	}

	public DailyBonus setValidityType( String validity_type ) {

		this.validity_type = validity_type;

		return this;

	}

	public String getEventType() {

		return this.event_type;

	}

	public DailyBonus setEventType( String event_type ) {

		this.event_type = event_type;

		return this;

	}

	public Timestamp getUpdateTime() {

		return this.update_time;

	}

	public DailyBonus setUpdateTime( Timestamp update_time ) {

		this.update_time = update_time;

		return this;

	}

	public Fields[] getEntityFields() {

		return DailyBonus.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"event_date\": \"" ).append( this.getEventDate() ).append( "\", " )
			.append( "\"bonus_id\": \"" ).append( this.getBonusId() ).append( "\", " )
			.append( "\"qty_bonus\": \"" ).append( this.getQtyBonus() ).append( "\", " )
			.append( "\"start_date\": \"" ).append( this.getStartDate() ).append( "\", " )
			.append( "\"end_date\": \"" ).append( this.getEndDate() ).append( "\", " )
			.append( "\"validity_type\": \"" ).append( this.getValidityType() ).append( "\", " )
			.append( "\"event_type\": \"" ).append( this.getEventType() ).append( "\", " )
			.append( "\"update_time\": \"" ).append( this.getUpdateTime() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }