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


@Table( "daily_voice" )
public class DailyVoice { 

	public enum Fields { qty_call, qty_call_originating, qty_call_terminating, amount_call, duration_call, qty_message, amount_message, qty_sms, qty_vas, msisdn, agg_date, update_time }

	@Column(
			table = "daily_voice",
			field = "qty_call",
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
			getMethod = "getQtyCall",
			setMethod = "setQtyCall"
	)
	private Short qty_call;

	@Column(
			table = "daily_voice",
			field = "qty_call_originating",
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
			getMethod = "getQtyCallOriginating",
			setMethod = "setQtyCallOriginating"
	)
	private Short qty_call_originating;

	@Column(
			table = "daily_voice",
			field = "qty_call_terminating",
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
			getMethod = "getQtyCallTerminating",
			setMethod = "setQtyCallTerminating"
	)
	private Short qty_call_terminating;

	@Column(
			table = "daily_voice",
			field = "amount_call",
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
			getMethod = "getAmountCall",
			setMethod = "setAmountCall"
	)
	private Integer amount_call;

	@Column(
			table = "daily_voice",
			field = "duration_call",
			type = "int(10) unsigned",
			mysqlType = "int",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 10,
			comment = "",
			getMethod = "getDurationCall",
			setMethod = "setDurationCall"
	)
	private Integer duration_call;

	@Column(
			table = "daily_voice",
			field = "qty_message",
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
			getMethod = "getQtyMessage",
			setMethod = "setQtyMessage"
	)
	private Short qty_message;

	@Column(
			table = "daily_voice",
			field = "amount_message",
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
			getMethod = "getAmountMessage",
			setMethod = "setAmountMessage"
	)
	private Integer amount_message;

	@Column(
			table = "daily_voice",
			field = "qty_sms",
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
			getMethod = "getQtySms",
			setMethod = "setQtySms"
	)
	private Short qty_sms;

	@Column(
			table = "daily_voice",
			field = "qty_vas",
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
			getMethod = "getQtyVas",
			setMethod = "setQtyVas"
	)
	private Short qty_vas;

	@Column(
			table = "daily_voice",
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
			table = "daily_voice",
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
			table = "daily_voice",
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


	public DailyVoice() {} 

	public DailyVoice( ResultSet rs ) throws SQLException {

		this.qty_call = rs.getShort( DailyVoice.Fields.qty_call.name() );
		this.qty_call_originating = rs.getShort( DailyVoice.Fields.qty_call_originating.name() );
		this.qty_call_terminating = rs.getShort( DailyVoice.Fields.qty_call_terminating.name() );
		this.amount_call = rs.getInt( DailyVoice.Fields.amount_call.name() );
		this.duration_call = rs.getInt( DailyVoice.Fields.duration_call.name() );
		this.qty_message = rs.getShort( DailyVoice.Fields.qty_message.name() );
		this.amount_message = rs.getInt( DailyVoice.Fields.amount_message.name() );
		this.qty_sms = rs.getShort( DailyVoice.Fields.qty_sms.name() );
		this.qty_vas = rs.getShort( DailyVoice.Fields.qty_vas.name() );
		this.msisdn = rs.getLong( DailyVoice.Fields.msisdn.name() );
		this.agg_date = rs.getDate( DailyVoice.Fields.agg_date.name() );
		this.update_time = rs.getTimestamp( DailyVoice.Fields.update_time.name() );

	}

	public DailyVoice( JSONObject jo ) throws JSONException, ParseException {

		this.qty_call = (short)jo.getInt( DailyVoice.Fields.qty_call.name() );
		this.qty_call_originating = (short)jo.getInt( DailyVoice.Fields.qty_call_originating.name() );
		this.qty_call_terminating = (short)jo.getInt( DailyVoice.Fields.qty_call_terminating.name() );
		this.amount_call = (int)jo.getInt( DailyVoice.Fields.amount_call.name() );
		this.duration_call = (int)jo.getInt( DailyVoice.Fields.duration_call.name() );
		this.qty_message = (short)jo.getInt( DailyVoice.Fields.qty_message.name() );
		this.amount_message = (int)jo.getInt( DailyVoice.Fields.amount_message.name() );
		this.qty_sms = (short)jo.getInt( DailyVoice.Fields.qty_sms.name() );
		this.qty_vas = (short)jo.getInt( DailyVoice.Fields.qty_vas.name() );
		this.msisdn = (long)jo.getLong( DailyVoice.Fields.msisdn.name() );
		this.agg_date = Format.getMysqlDateTime( jo.getString( DailyVoice.Fields.agg_date.name() ) );
		this.update_time = new Timestamp( Format.getMysqlDateTime( jo.getString( DailyVoice.Fields.update_time.name() ) ).getTime() );

	}

	public Short getQtyCall() {

		return this.qty_call;

	}

	public void setQtyCall( Short qty_call ) {

		this.qty_call = qty_call;

	}

	public Short getQtyCallOriginating() {

		return this.qty_call_originating;

	}

	public void setQtyCallOriginating( Short qty_call_originating ) {

		this.qty_call_originating = qty_call_originating;

	}

	public Short getQtyCallTerminating() {

		return this.qty_call_terminating;

	}

	public void setQtyCallTerminating( Short qty_call_terminating ) {

		this.qty_call_terminating = qty_call_terminating;

	}

	public Integer getAmountCall() {

		return this.amount_call;

	}

	public void setAmountCall( Integer amount_call ) {

		this.amount_call = amount_call;

	}

	public Integer getDurationCall() {

		return this.duration_call;

	}

	public void setDurationCall( Integer duration_call ) {

		this.duration_call = duration_call;

	}

	public Short getQtyMessage() {

		return this.qty_message;

	}

	public void setQtyMessage( Short qty_message ) {

		this.qty_message = qty_message;

	}

	public Integer getAmountMessage() {

		return this.amount_message;

	}

	public void setAmountMessage( Integer amount_message ) {

		this.amount_message = amount_message;

	}

	public Short getQtySms() {

		return this.qty_sms;

	}

	public void setQtySms( Short qty_sms ) {

		this.qty_sms = qty_sms;

	}

	public Short getQtyVas() {

		return this.qty_vas;

	}

	public void setQtyVas( Short qty_vas ) {

		this.qty_vas = qty_vas;

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public void setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

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

		return DailyVoice.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"qty_call\": \"" ).append( this.getQtyCall() ).append( "\", " )
			.append( "\"qty_call_originating\": \"" ).append( this.getQtyCallOriginating() ).append( "\", " )
			.append( "\"qty_call_terminating\": \"" ).append( this.getQtyCallTerminating() ).append( "\", " )
			.append( "\"amount_call\": \"" ).append( this.getAmountCall() ).append( "\", " )
			.append( "\"duration_call\": \"" ).append( this.getDurationCall() ).append( "\", " )
			.append( "\"qty_message\": \"" ).append( this.getQtyMessage() ).append( "\", " )
			.append( "\"amount_message\": \"" ).append( this.getAmountMessage() ).append( "\", " )
			.append( "\"qty_sms\": \"" ).append( this.getQtySms() ).append( "\", " )
			.append( "\"qty_vas\": \"" ).append( this.getQtyVas() ).append( "\", " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"agg_date\": \"" ).append( this.getAggDate() ).append( "\", " )
			.append( "\"update_time\": \"" ).append( this.getUpdateTime() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }