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


@Table( "stats_subs_voice_old" )
public class StatsSubsVoiceOld { 

	public enum Fields { qty_call, qty_call_originating, qty_call_terminating, amount_call, duration_call, qty_message, amount_message, qty_sms, qty_vas, last_call_date, last_message_date, msisdn, agg_date }

	@Column(
			table = "stats_subs_voice_old",
			field = "qty_call",
			type = "mediumint(8) unsigned",
			mysqlType = "mediumint",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 8,
			comment = "",
			getMethod = "getQtyCall",
			setMethod = "setQtyCall"
	)
	private Integer qty_call;

	@Column(
			table = "stats_subs_voice_old",
			field = "qty_call_originating",
			type = "mediumint(8) unsigned",
			mysqlType = "mediumint",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 8,
			comment = "",
			getMethod = "getQtyCallOriginating",
			setMethod = "setQtyCallOriginating"
	)
	private Integer qty_call_originating;

	@Column(
			table = "stats_subs_voice_old",
			field = "qty_call_terminating",
			type = "mediumint(8) unsigned",
			mysqlType = "mediumint",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 8,
			comment = "",
			getMethod = "getQtyCallTerminating",
			setMethod = "setQtyCallTerminating"
	)
	private Integer qty_call_terminating;

	@Column(
			table = "stats_subs_voice_old",
			field = "amount_call",
			type = "float",
			mysqlType = "float",
			javaType = "Float",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getAmountCall",
			setMethod = "setAmountCall"
	)
	private Float amount_call;

	@Column(
			table = "stats_subs_voice_old",
			field = "duration_call",
			type = "float unsigned",
			mysqlType = "float",
			javaType = "Float",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getDurationCall",
			setMethod = "setDurationCall"
	)
	private Float duration_call;

	@Column(
			table = "stats_subs_voice_old",
			field = "qty_message",
			type = "mediumint(8) unsigned",
			mysqlType = "mediumint",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 8,
			comment = "",
			getMethod = "getQtyMessage",
			setMethod = "setQtyMessage"
	)
	private Integer qty_message;

	@Column(
			table = "stats_subs_voice_old",
			field = "amount_message",
			type = "float",
			mysqlType = "float",
			javaType = "Float",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getAmountMessage",
			setMethod = "setAmountMessage"
	)
	private Float amount_message;

	@Column(
			table = "stats_subs_voice_old",
			field = "qty_sms",
			type = "mediumint(8) unsigned",
			mysqlType = "mediumint",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 8,
			comment = "",
			getMethod = "getQtySms",
			setMethod = "setQtySms"
	)
	private Integer qty_sms;

	@Column(
			table = "stats_subs_voice_old",
			field = "qty_vas",
			type = "mediumint(8) unsigned",
			mysqlType = "mediumint",
			javaType = "Integer",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 8,
			comment = "",
			getMethod = "getQtyVas",
			setMethod = "setQtyVas"
	)
	private Integer qty_vas;

	@Column(
			table = "stats_subs_voice_old",
			field = "last_call_date",
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
			getMethod = "getLastCallDate",
			setMethod = "setLastCallDate"
	)
	private Date last_call_date;

	@Column(
			table = "stats_subs_voice_old",
			field = "last_message_date",
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
			getMethod = "getLastMessageDate",
			setMethod = "setLastMessageDate"
	)
	private Date last_message_date;

	@Column(
			table = "stats_subs_voice_old",
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
			table = "stats_subs_voice_old",
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


	public StatsSubsVoiceOld() {} 

	public StatsSubsVoiceOld( ResultSet rs ) throws SQLException {

		this.qty_call = rs.getInt( StatsSubsVoiceOld.Fields.qty_call.name() );
		this.qty_call_originating = rs.getInt( StatsSubsVoiceOld.Fields.qty_call_originating.name() );
		this.qty_call_terminating = rs.getInt( StatsSubsVoiceOld.Fields.qty_call_terminating.name() );
		this.amount_call = rs.getFloat( StatsSubsVoiceOld.Fields.amount_call.name() );
		this.duration_call = rs.getFloat( StatsSubsVoiceOld.Fields.duration_call.name() );
		this.qty_message = rs.getInt( StatsSubsVoiceOld.Fields.qty_message.name() );
		this.amount_message = rs.getFloat( StatsSubsVoiceOld.Fields.amount_message.name() );
		this.qty_sms = rs.getInt( StatsSubsVoiceOld.Fields.qty_sms.name() );
		this.qty_vas = rs.getInt( StatsSubsVoiceOld.Fields.qty_vas.name() );
		this.last_call_date = rs.getDate( StatsSubsVoiceOld.Fields.last_call_date.name() );
		this.last_message_date = rs.getDate( StatsSubsVoiceOld.Fields.last_message_date.name() );
		this.msisdn = rs.getLong( StatsSubsVoiceOld.Fields.msisdn.name() );
		this.agg_date = rs.getDate( StatsSubsVoiceOld.Fields.agg_date.name() );

	}

	public StatsSubsVoiceOld( JSONObject jo ) throws JSONException, ParseException {

		this.qty_call = (int)jo.getInt( StatsSubsVoiceOld.Fields.qty_call.name() );
		this.qty_call_originating = (int)jo.getInt( StatsSubsVoiceOld.Fields.qty_call_originating.name() );
		this.qty_call_terminating = (int)jo.getInt( StatsSubsVoiceOld.Fields.qty_call_terminating.name() );
		this.amount_call = (float)jo.getDouble( StatsSubsVoiceOld.Fields.amount_call.name() );
		this.duration_call = (float)jo.getDouble( StatsSubsVoiceOld.Fields.duration_call.name() );
		this.qty_message = (int)jo.getInt( StatsSubsVoiceOld.Fields.qty_message.name() );
		this.amount_message = (float)jo.getDouble( StatsSubsVoiceOld.Fields.amount_message.name() );
		this.qty_sms = (int)jo.getInt( StatsSubsVoiceOld.Fields.qty_sms.name() );
		this.qty_vas = (int)jo.getInt( StatsSubsVoiceOld.Fields.qty_vas.name() );
		this.last_call_date = Format.getMysqlDateTime( jo.getString( StatsSubsVoiceOld.Fields.last_call_date.name() ) );
		this.last_message_date = Format.getMysqlDateTime( jo.getString( StatsSubsVoiceOld.Fields.last_message_date.name() ) );
		this.msisdn = (long)jo.getLong( StatsSubsVoiceOld.Fields.msisdn.name() );
		this.agg_date = Format.getMysqlDateTime( jo.getString( StatsSubsVoiceOld.Fields.agg_date.name() ) );

	}

	public Integer getQtyCall() {

		return this.qty_call;

	}

	public StatsSubsVoiceOld setQtyCall( Integer qty_call ) {

		this.qty_call = qty_call;

		return this;

	}

	public Integer getQtyCallOriginating() {

		return this.qty_call_originating;

	}

	public StatsSubsVoiceOld setQtyCallOriginating( Integer qty_call_originating ) {

		this.qty_call_originating = qty_call_originating;

		return this;

	}

	public Integer getQtyCallTerminating() {

		return this.qty_call_terminating;

	}

	public StatsSubsVoiceOld setQtyCallTerminating( Integer qty_call_terminating ) {

		this.qty_call_terminating = qty_call_terminating;

		return this;

	}

	public Float getAmountCall() {

		return this.amount_call;

	}

	public StatsSubsVoiceOld setAmountCall( Float amount_call ) {

		this.amount_call = amount_call;

		return this;

	}

	public Float getDurationCall() {

		return this.duration_call;

	}

	public StatsSubsVoiceOld setDurationCall( Float duration_call ) {

		this.duration_call = duration_call;

		return this;

	}

	public Integer getQtyMessage() {

		return this.qty_message;

	}

	public StatsSubsVoiceOld setQtyMessage( Integer qty_message ) {

		this.qty_message = qty_message;

		return this;

	}

	public Float getAmountMessage() {

		return this.amount_message;

	}

	public StatsSubsVoiceOld setAmountMessage( Float amount_message ) {

		this.amount_message = amount_message;

		return this;

	}

	public Integer getQtySms() {

		return this.qty_sms;

	}

	public StatsSubsVoiceOld setQtySms( Integer qty_sms ) {

		this.qty_sms = qty_sms;

		return this;

	}

	public Integer getQtyVas() {

		return this.qty_vas;

	}

	public StatsSubsVoiceOld setQtyVas( Integer qty_vas ) {

		this.qty_vas = qty_vas;

		return this;

	}

	public Date getLastCallDate() {

		return this.last_call_date;

	}

	public StatsSubsVoiceOld setLastCallDate( Date last_call_date ) {

		this.last_call_date = last_call_date;

		return this;

	}

	public Date getLastMessageDate() {

		return this.last_message_date;

	}

	public StatsSubsVoiceOld setLastMessageDate( Date last_message_date ) {

		this.last_message_date = last_message_date;

		return this;

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public StatsSubsVoiceOld setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

		return this;

	}

	public Date getAggDate() {

		return this.agg_date;

	}

	public StatsSubsVoiceOld setAggDate( Date agg_date ) {

		this.agg_date = agg_date;

		return this;

	}

	public Fields[] getEntityFields() {

		return StatsSubsVoiceOld.Fields.values();

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
			.append( "\"last_call_date\": \"" ).append( this.getLastCallDate() ).append( "\", " )
			.append( "\"last_message_date\": \"" ).append( this.getLastMessageDate() ).append( "\", " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"agg_date\": \"" ).append( this.getAggDate() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }