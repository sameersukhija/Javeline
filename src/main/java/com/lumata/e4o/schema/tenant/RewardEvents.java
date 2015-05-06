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


@Table( "reward_events" )
public class RewardEvents { 

	public enum Fields { event_id, msisdn, event_type, attempts, module_id, feature, notif_id, reward_id, qty_reward, start_date, end_date, duration, period, source_msisdn, relation_type_id, request_id, update_time }

	@Column(
			table = "reward_events",
			field = "event_id",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = true,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 20,
			comment = "",
			getMethod = "getEventId",
			setMethod = "setEventId"
	)
	private Long event_id;

	@Column(
			table = "reward_events",
			field = "msisdn",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "MUL",
			defaultValue = "null",
			extra = "",
			length = 20,
			comment = "",
			getMethod = "getMsisdn",
			setMethod = "setMsisdn"
	)
	private Long msisdn;

	@Column(
			table = "reward_events",
			field = "event_type",
			type = "enum('CREDIT','DEBIT','EXPIRATION','PURCHASE','SET','ACTIVATION','DEACTIVATION')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "CREDIT",
			extra = "",
			length = 7,
			comment = "",
			getMethod = "getEventType",
			setMethod = "setEventType"
	)
	private String event_type;

	@Column(
			table = "reward_events",
			field = "attempts",
			type = "smallint(5) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 5,
			comment = "",
			getMethod = "getAttempts",
			setMethod = "setAttempts"
	)
	private Short attempts;

	@Column(
			table = "reward_events",
			field = "module_id",
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
			comment = "",
			getMethod = "getModuleId",
			setMethod = "setModuleId"
	)
	private Byte module_id;

	@Column(
			table = "reward_events",
			field = "feature",
			type = "varchar(55)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 55,
			comment = "",
			getMethod = "getFeature",
			setMethod = "setFeature"
	)
	private String feature;

	@Column(
			table = "reward_events",
			field = "notif_id",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 20,
			comment = "",
			getMethod = "getNotifId",
			setMethod = "setNotifId"
	)
	private Long notif_id;

	@Column(
			table = "reward_events",
			field = "reward_id",
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
			getMethod = "getRewardId",
			setMethod = "setRewardId"
	)
	private Short reward_id;

	@Column(
			table = "reward_events",
			field = "qty_reward",
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
			getMethod = "getQtyReward",
			setMethod = "setQtyReward"
	)
	private Integer qty_reward;

	@Column(
			table = "reward_events",
			field = "start_date",
			type = "datetime",
			mysqlType = "datetime",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "",
			getMethod = "getStartDate",
			setMethod = "setStartDate"
	)
	private Date start_date;

	@Column(
			table = "reward_events",
			field = "end_date",
			type = "datetime",
			mysqlType = "datetime",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "",
			getMethod = "getEndDate",
			setMethod = "setEndDate"
	)
	private Date end_date;

	@Column(
			table = "reward_events",
			field = "duration",
			type = "smallint(3)",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 3,
			comment = "",
			getMethod = "getDuration",
			setMethod = "setDuration"
	)
	private Short duration;

	@Column(
			table = "reward_events",
			field = "period",
			type = "enum('HOURS','DAYS','WEEKS','MONTHS','QUARTERS','YEARS')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 6,
			comment = "",
			getMethod = "getPeriod",
			setMethod = "setPeriod"
	)
	private String period;

	@Column(
			table = "reward_events",
			field = "source_msisdn",
			type = "bigint(20) unsigned",
			mysqlType = "bigint",
			javaType = "Long",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 20,
			comment = "",
			getMethod = "getSourceMsisdn",
			setMethod = "setSourceMsisdn"
	)
	private Long source_msisdn;

	@Column(
			table = "reward_events",
			field = "relation_type_id",
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
			comment = "",
			getMethod = "getRelationTypeId",
			setMethod = "setRelationTypeId"
	)
	private Byte relation_type_id;

	@Column(
			table = "reward_events",
			field = "request_id",
			type = "varchar(40)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 40,
			comment = "",
			getMethod = "getRequestId",
			setMethod = "setRequestId"
	)
	private String request_id;

	@Column(
			table = "reward_events",
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


	public RewardEvents() {} 

	public RewardEvents( ResultSet rs ) throws SQLException {

		this.event_id = rs.getLong( RewardEvents.Fields.event_id.name() );
		this.msisdn = rs.getLong( RewardEvents.Fields.msisdn.name() );
		this.event_type = rs.getString( RewardEvents.Fields.event_type.name() );
		this.attempts = rs.getShort( RewardEvents.Fields.attempts.name() );
		this.module_id = rs.getByte( RewardEvents.Fields.module_id.name() );
		this.feature = rs.getString( RewardEvents.Fields.feature.name() );
		this.notif_id = rs.getLong( RewardEvents.Fields.notif_id.name() );
		this.reward_id = rs.getShort( RewardEvents.Fields.reward_id.name() );
		this.qty_reward = rs.getInt( RewardEvents.Fields.qty_reward.name() );
		this.start_date = rs.getDate( RewardEvents.Fields.start_date.name() );
		this.end_date = rs.getDate( RewardEvents.Fields.end_date.name() );
		this.duration = rs.getShort( RewardEvents.Fields.duration.name() );
		this.period = rs.getString( RewardEvents.Fields.period.name() );
		this.source_msisdn = rs.getLong( RewardEvents.Fields.source_msisdn.name() );
		this.relation_type_id = rs.getByte( RewardEvents.Fields.relation_type_id.name() );
		this.request_id = rs.getString( RewardEvents.Fields.request_id.name() );
		this.update_time = rs.getTimestamp( RewardEvents.Fields.update_time.name() );

	}

	public RewardEvents( JSONObject jo ) throws JSONException, ParseException {

		this.event_id = (long)jo.getLong( RewardEvents.Fields.event_id.name() );
		this.msisdn = (long)jo.getLong( RewardEvents.Fields.msisdn.name() );
		this.event_type = jo.getString( RewardEvents.Fields.event_type.name() );
		this.attempts = (short)jo.getInt( RewardEvents.Fields.attempts.name() );
		this.module_id = (byte)jo.getInt( RewardEvents.Fields.module_id.name() );
		this.feature = jo.getString( RewardEvents.Fields.feature.name() );
		this.notif_id = (long)jo.getLong( RewardEvents.Fields.notif_id.name() );
		this.reward_id = (short)jo.getInt( RewardEvents.Fields.reward_id.name() );
		this.qty_reward = (int)jo.getInt( RewardEvents.Fields.qty_reward.name() );
		this.start_date = Format.getMysqlDateTime( jo.getString( RewardEvents.Fields.start_date.name() ) );
		this.end_date = Format.getMysqlDateTime( jo.getString( RewardEvents.Fields.end_date.name() ) );
		this.duration = (short)jo.getInt( RewardEvents.Fields.duration.name() );
		this.period = jo.getString( RewardEvents.Fields.period.name() );
		this.source_msisdn = (long)jo.getLong( RewardEvents.Fields.source_msisdn.name() );
		this.relation_type_id = (byte)jo.getInt( RewardEvents.Fields.relation_type_id.name() );
		this.request_id = jo.getString( RewardEvents.Fields.request_id.name() );
		this.update_time = new Timestamp( Format.getMysqlDateTime( jo.getString( RewardEvents.Fields.update_time.name() ) ).getTime() );

	}

	public Long getEventId() {

		return this.event_id;

	}

	public RewardEvents setEventId( Long event_id ) {

		this.event_id = event_id;

		return this;

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public RewardEvents setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

		return this;

	}

	public String getEventType() {

		return this.event_type;

	}

	public RewardEvents setEventType( String event_type ) {

		this.event_type = event_type;

		return this;

	}

	public Short getAttempts() {

		return this.attempts;

	}

	public RewardEvents setAttempts( Short attempts ) {

		this.attempts = attempts;

		return this;

	}

	public Byte getModuleId() {

		return this.module_id;

	}

	public RewardEvents setModuleId( Byte module_id ) {

		this.module_id = module_id;

		return this;

	}

	public String getFeature() {

		return this.feature;

	}

	public RewardEvents setFeature( String feature ) {

		this.feature = feature;

		return this;

	}

	public Long getNotifId() {

		return this.notif_id;

	}

	public RewardEvents setNotifId( Long notif_id ) {

		this.notif_id = notif_id;

		return this;

	}

	public Short getRewardId() {

		return this.reward_id;

	}

	public RewardEvents setRewardId( Short reward_id ) {

		this.reward_id = reward_id;

		return this;

	}

	public Integer getQtyReward() {

		return this.qty_reward;

	}

	public RewardEvents setQtyReward( Integer qty_reward ) {

		this.qty_reward = qty_reward;

		return this;

	}

	public Date getStartDate() {

		return this.start_date;

	}

	public RewardEvents setStartDate( Date start_date ) {

		this.start_date = start_date;

		return this;

	}

	public Date getEndDate() {

		return this.end_date;

	}

	public RewardEvents setEndDate( Date end_date ) {

		this.end_date = end_date;

		return this;

	}

	public Short getDuration() {

		return this.duration;

	}

	public RewardEvents setDuration( Short duration ) {

		this.duration = duration;

		return this;

	}

	public String getPeriod() {

		return this.period;

	}

	public RewardEvents setPeriod( String period ) {

		this.period = period;

		return this;

	}

	public Long getSourceMsisdn() {

		return this.source_msisdn;

	}

	public RewardEvents setSourceMsisdn( Long source_msisdn ) {

		this.source_msisdn = source_msisdn;

		return this;

	}

	public Byte getRelationTypeId() {

		return this.relation_type_id;

	}

	public RewardEvents setRelationTypeId( Byte relation_type_id ) {

		this.relation_type_id = relation_type_id;

		return this;

	}

	public String getRequestId() {

		return this.request_id;

	}

	public RewardEvents setRequestId( String request_id ) {

		this.request_id = request_id;

		return this;

	}

	public Timestamp getUpdateTime() {

		return this.update_time;

	}

	public RewardEvents setUpdateTime( Timestamp update_time ) {

		this.update_time = update_time;

		return this;

	}

	public Fields[] getEntityFields() {

		return RewardEvents.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"event_id\": \"" ).append( this.getEventId() ).append( "\", " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"event_type\": \"" ).append( this.getEventType() ).append( "\", " )
			.append( "\"attempts\": \"" ).append( this.getAttempts() ).append( "\", " )
			.append( "\"module_id\": \"" ).append( this.getModuleId() ).append( "\", " )
			.append( "\"feature\": \"" ).append( this.getFeature() ).append( "\", " )
			.append( "\"notif_id\": \"" ).append( this.getNotifId() ).append( "\", " )
			.append( "\"reward_id\": \"" ).append( this.getRewardId() ).append( "\", " )
			.append( "\"qty_reward\": \"" ).append( this.getQtyReward() ).append( "\", " )
			.append( "\"start_date\": \"" ).append( this.getStartDate() ).append( "\", " )
			.append( "\"end_date\": \"" ).append( this.getEndDate() ).append( "\", " )
			.append( "\"duration\": \"" ).append( this.getDuration() ).append( "\", " )
			.append( "\"period\": \"" ).append( this.getPeriod() ).append( "\", " )
			.append( "\"source_msisdn\": \"" ).append( this.getSourceMsisdn() ).append( "\", " )
			.append( "\"relation_type_id\": \"" ).append( this.getRelationTypeId() ).append( "\", " )
			.append( "\"request_id\": \"" ).append( this.getRequestId() ).append( "\", " )
			.append( "\"update_time\": \"" ).append( this.getUpdateTime() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }