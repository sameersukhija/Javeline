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


@Table( "campaigns" )
public class Campaigns { 

	public enum Fields { campaign_id, campaign_name, targeting_type, state, start_date, end_date, prov_start_date, prov_end_date, min_days_prov_exec, channels_id_list, notif_days, notif_time, model_name, type_id, hourly_stats_done, archive_stats_done, short_code, campaign_master_id, variation, occurence, manual_prov_available }

	@Column(
			table = "campaigns",
			field = "campaign_id",
			type = "smallint(3) unsigned",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = true,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 3,
			comment = "",
			getMethod = "getCampaignId",
			setMethod = "setCampaignId"
	)
	private Short campaign_id;

	@Column(
			table = "campaigns",
			field = "campaign_name",
			type = "varchar(55)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 55,
			comment = "",
			getMethod = "getCampaignName",
			setMethod = "setCampaignName"
	)
	private String campaign_name;

	@Column(
			table = "campaigns",
			field = "targeting_type",
			type = "enum('ATL','BTL')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "BTL",
			extra = "",
			length = 2,
			comment = "",
			getMethod = "getTargetingType",
			setMethod = "setTargetingType"
	)
	private String targeting_type;

	@Column(
			table = "campaigns",
			field = "state",
			type = "enum('SAVED','ACTIVATED','SUSPENDED','EOL','PAUSED')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "SAVED",
			extra = "",
			length = 5,
			comment = "",
			getMethod = "getState",
			setMethod = "setState"
	)
	private String state;

	@Column(
			table = "campaigns",
			field = "start_date",
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
			getMethod = "getStartDate",
			setMethod = "setStartDate"
	)
	private Date start_date;

	@Column(
			table = "campaigns",
			field = "end_date",
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
			getMethod = "getEndDate",
			setMethod = "setEndDate"
	)
	private Date end_date;

	@Column(
			table = "campaigns",
			field = "prov_start_date",
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
			getMethod = "getProvStartDate",
			setMethod = "setProvStartDate"
	)
	private Date prov_start_date;

	@Column(
			table = "campaigns",
			field = "prov_end_date",
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
			getMethod = "getProvEndDate",
			setMethod = "setProvEndDate"
	)
	private Date prov_end_date;

	@Column(
			table = "campaigns",
			field = "min_days_prov_exec",
			type = "tinyint(4)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "0",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getMinDaysProvExec",
			setMethod = "setMinDaysProvExec"
	)
	private Byte min_days_prov_exec;

	@Column(
			table = "campaigns",
			field = "channels_id_list",
			type = "set('1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16')",
			mysqlType = "set",
			javaType = "Set",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 16,
			comment = "",
			getMethod = "getChannelsIdList",
			setMethod = "setChannelsIdList"
	)
	private String channels_id_list;

	@Column(
			table = "campaigns",
			field = "notif_days",
			type = "tinyint(2)",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 2,
			comment = "Number of days to notify subs in advance",
			getMethod = "getNotifDays",
			setMethod = "setNotifDays"
	)
	private Byte notif_days;

	@Column(
			table = "campaigns",
			field = "notif_time",
			type = "time",
			mysqlType = "time",
			javaType = "Date",
			categoryType = "Date",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			comment = "",
			getMethod = "getNotifTime",
			setMethod = "setNotifTime"
	)
	private Date notif_time;

	@Column(
			table = "campaigns",
			field = "model_name",
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
			getMethod = "getModelName",
			setMethod = "setModelName"
	)
	private String model_name;

	@Column(
			table = "campaigns",
			field = "type_id",
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
			getMethod = "getTypeId",
			setMethod = "setTypeId"
	)
	private Short type_id;

	@Column(
			table = "campaigns",
			field = "hourly_stats_done",
			type = "tinyint(1)",
			mysqlType = "tinyint",
			javaType = "Boolean",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 1,
			comment = "",
			getMethod = "getHourlyStatsDone",
			setMethod = "setHourlyStatsDone"
	)
	private Boolean hourly_stats_done;

	@Column(
			table = "campaigns",
			field = "archive_stats_done",
			type = "tinyint(1)",
			mysqlType = "tinyint",
			javaType = "Boolean",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 1,
			comment = "",
			getMethod = "getArchiveStatsDone",
			setMethod = "setArchiveStatsDone"
	)
	private Boolean archive_stats_done;

	@Column(
			table = "campaigns",
			field = "short_code",
			type = "varchar(45)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "",
			extra = "",
			length = 45,
			comment = "",
			getMethod = "getShortCode",
			setMethod = "setShortCode"
	)
	private String short_code;

	@Column(
			table = "campaigns",
			field = "campaign_master_id",
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
			getMethod = "getCampaignMasterId",
			setMethod = "setCampaignMasterId"
	)
	private Short campaign_master_id;

	@Column(
			table = "campaigns",
			field = "variation",
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
			getMethod = "getVariation",
			setMethod = "setVariation"
	)
	private Short variation;

	@Column(
			table = "campaigns",
			field = "occurence",
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
			getMethod = "getOccurence",
			setMethod = "setOccurence"
	)
	private Short occurence;

	@Column(
			table = "campaigns",
			field = "manual_prov_available",
			type = "tinyint(3) unsigned",
			mysqlType = "tinyint",
			javaType = "Byte",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "1",
			extra = "",
			length = 3,
			comment = "",
			getMethod = "getManualProvAvailable",
			setMethod = "setManualProvAvailable"
	)
	private Byte manual_prov_available;


	public Campaigns() {} 

	public Campaigns( ResultSet rs ) throws SQLException {

		this.campaign_id = rs.getShort( Campaigns.Fields.campaign_id.name() );
		this.campaign_name = rs.getString( Campaigns.Fields.campaign_name.name() );
		this.targeting_type = rs.getString( Campaigns.Fields.targeting_type.name() );
		this.state = rs.getString( Campaigns.Fields.state.name() );
		this.start_date = rs.getDate( Campaigns.Fields.start_date.name() );
		this.end_date = rs.getDate( Campaigns.Fields.end_date.name() );
		this.prov_start_date = rs.getDate( Campaigns.Fields.prov_start_date.name() );
		this.prov_end_date = rs.getDate( Campaigns.Fields.prov_end_date.name() );
		this.min_days_prov_exec = rs.getByte( Campaigns.Fields.min_days_prov_exec.name() );
		this.channels_id_list = rs.getString( Campaigns.Fields.channels_id_list.name() );
		this.notif_days = rs.getByte( Campaigns.Fields.notif_days.name() );
		this.notif_time = rs.getDate( Campaigns.Fields.notif_time.name() );
		this.model_name = rs.getString( Campaigns.Fields.model_name.name() );
		this.type_id = rs.getShort( Campaigns.Fields.type_id.name() );
		this.hourly_stats_done = rs.getBoolean( Campaigns.Fields.hourly_stats_done.name() );
		this.archive_stats_done = rs.getBoolean( Campaigns.Fields.archive_stats_done.name() );
		this.short_code = rs.getString( Campaigns.Fields.short_code.name() );
		this.campaign_master_id = rs.getShort( Campaigns.Fields.campaign_master_id.name() );
		this.variation = rs.getShort( Campaigns.Fields.variation.name() );
		this.occurence = rs.getShort( Campaigns.Fields.occurence.name() );
		this.manual_prov_available = rs.getByte( Campaigns.Fields.manual_prov_available.name() );

	}

	public Campaigns( JSONObject jo ) throws JSONException, ParseException {

		this.campaign_id = (short)jo.getInt( Campaigns.Fields.campaign_id.name() );
		this.campaign_name = jo.getString( Campaigns.Fields.campaign_name.name() );
		this.targeting_type = jo.getString( Campaigns.Fields.targeting_type.name() );
		this.state = jo.getString( Campaigns.Fields.state.name() );
		this.start_date = Format.getMysqlDateTime( jo.getString( Campaigns.Fields.start_date.name() ) );
		this.end_date = Format.getMysqlDateTime( jo.getString( Campaigns.Fields.end_date.name() ) );
		this.prov_start_date = Format.getMysqlDateTime( jo.getString( Campaigns.Fields.prov_start_date.name() ) );
		this.prov_end_date = Format.getMysqlDateTime( jo.getString( Campaigns.Fields.prov_end_date.name() ) );
		this.min_days_prov_exec = (byte)jo.getInt( Campaigns.Fields.min_days_prov_exec.name() );
		this.channels_id_list = jo.getString( Campaigns.Fields.channels_id_list.name() );
		this.notif_days = (byte)jo.getInt( Campaigns.Fields.notif_days.name() );
		this.notif_time = Format.getMysqlDateTime( jo.getString( Campaigns.Fields.notif_time.name() ) );
		this.model_name = jo.getString( Campaigns.Fields.model_name.name() );
		this.type_id = (short)jo.getInt( Campaigns.Fields.type_id.name() );
		this.hourly_stats_done = jo.getBoolean( Campaigns.Fields.hourly_stats_done.name() );
		this.archive_stats_done = jo.getBoolean( Campaigns.Fields.archive_stats_done.name() );
		this.short_code = jo.getString( Campaigns.Fields.short_code.name() );
		this.campaign_master_id = (short)jo.getInt( Campaigns.Fields.campaign_master_id.name() );
		this.variation = (short)jo.getInt( Campaigns.Fields.variation.name() );
		this.occurence = (short)jo.getInt( Campaigns.Fields.occurence.name() );
		this.manual_prov_available = (byte)jo.getInt( Campaigns.Fields.manual_prov_available.name() );

	}

	public Short getCampaignId() {

		return this.campaign_id;

	}

	public Campaigns setCampaignId( Short campaign_id ) {

		this.campaign_id = campaign_id;

		return this;

	}

	public String getCampaignName() {

		return this.campaign_name;

	}

	public Campaigns setCampaignName( String campaign_name ) {

		this.campaign_name = campaign_name;

		return this;

	}

	public String getTargetingType() {

		return this.targeting_type;

	}

	public Campaigns setTargetingType( String targeting_type ) {

		this.targeting_type = targeting_type;

		return this;

	}

	public String getState() {

		return this.state;

	}

	public Campaigns setState( String state ) {

		this.state = state;

		return this;

	}

	public Date getStartDate() {

		return this.start_date;

	}

	public Campaigns setStartDate( Date start_date ) {

		this.start_date = start_date;

		return this;

	}

	public Date getEndDate() {

		return this.end_date;

	}

	public Campaigns setEndDate( Date end_date ) {

		this.end_date = end_date;

		return this;

	}

	public Date getProvStartDate() {

		return this.prov_start_date;

	}

	public Campaigns setProvStartDate( Date prov_start_date ) {

		this.prov_start_date = prov_start_date;

		return this;

	}

	public Date getProvEndDate() {

		return this.prov_end_date;

	}

	public Campaigns setProvEndDate( Date prov_end_date ) {

		this.prov_end_date = prov_end_date;

		return this;

	}

	public Byte getMinDaysProvExec() {

		return this.min_days_prov_exec;

	}

	public Campaigns setMinDaysProvExec( Byte min_days_prov_exec ) {

		this.min_days_prov_exec = min_days_prov_exec;

		return this;

	}

	public String getChannelsIdList() {

		return this.channels_id_list;

	}

	public Campaigns setChannelsIdList( String channels_id_list ) {

		this.channels_id_list = channels_id_list;

		return this;

	}

	public Byte getNotifDays() {

		return this.notif_days;

	}

	public Campaigns setNotifDays( Byte notif_days ) {

		this.notif_days = notif_days;

		return this;

	}

	public Date getNotifTime() {

		return this.notif_time;

	}

	public Campaigns setNotifTime( Date notif_time ) {

		this.notif_time = notif_time;

		return this;

	}

	public String getModelName() {

		return this.model_name;

	}

	public Campaigns setModelName( String model_name ) {

		this.model_name = model_name;

		return this;

	}

	public Short getTypeId() {

		return this.type_id;

	}

	public Campaigns setTypeId( Short type_id ) {

		this.type_id = type_id;

		return this;

	}

	public Boolean getHourlyStatsDone() {

		return this.hourly_stats_done;

	}

	public Campaigns setHourlyStatsDone( Boolean hourly_stats_done ) {

		this.hourly_stats_done = hourly_stats_done;

		return this;

	}

	public Boolean getArchiveStatsDone() {

		return this.archive_stats_done;

	}

	public Campaigns setArchiveStatsDone( Boolean archive_stats_done ) {

		this.archive_stats_done = archive_stats_done;

		return this;

	}

	public String getShortCode() {

		return this.short_code;

	}

	public Campaigns setShortCode( String short_code ) {

		this.short_code = short_code;

		return this;

	}

	public Short getCampaignMasterId() {

		return this.campaign_master_id;

	}

	public Campaigns setCampaignMasterId( Short campaign_master_id ) {

		this.campaign_master_id = campaign_master_id;

		return this;

	}

	public Short getVariation() {

		return this.variation;

	}

	public Campaigns setVariation( Short variation ) {

		this.variation = variation;

		return this;

	}

	public Short getOccurence() {

		return this.occurence;

	}

	public Campaigns setOccurence( Short occurence ) {

		this.occurence = occurence;

		return this;

	}

	public Byte getManualProvAvailable() {

		return this.manual_prov_available;

	}

	public Campaigns setManualProvAvailable( Byte manual_prov_available ) {

		this.manual_prov_available = manual_prov_available;

		return this;

	}

	public Fields[] getEntityFields() {

		return Campaigns.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"campaign_id\": \"" ).append( this.getCampaignId() ).append( "\", " )
			.append( "\"campaign_name\": \"" ).append( this.getCampaignName() ).append( "\", " )
			.append( "\"targeting_type\": \"" ).append( this.getTargetingType() ).append( "\", " )
			.append( "\"state\": \"" ).append( this.getState() ).append( "\", " )
			.append( "\"start_date\": \"" ).append( this.getStartDate() ).append( "\", " )
			.append( "\"end_date\": \"" ).append( this.getEndDate() ).append( "\", " )
			.append( "\"prov_start_date\": \"" ).append( this.getProvStartDate() ).append( "\", " )
			.append( "\"prov_end_date\": \"" ).append( this.getProvEndDate() ).append( "\", " )
			.append( "\"min_days_prov_exec\": \"" ).append( this.getMinDaysProvExec() ).append( "\", " )
			.append( "\"channels_id_list\": \"" ).append( this.getChannelsIdList() ).append( "\", " )
			.append( "\"notif_days\": \"" ).append( this.getNotifDays() ).append( "\", " )
			.append( "\"notif_time\": \"" ).append( this.getNotifTime() ).append( "\", " )
			.append( "\"model_name\": \"" ).append( this.getModelName() ).append( "\", " )
			.append( "\"type_id\": \"" ).append( this.getTypeId() ).append( "\", " )
			.append( "\"hourly_stats_done\": \"" ).append( this.getHourlyStatsDone() ).append( "\", " )
			.append( "\"archive_stats_done\": \"" ).append( this.getArchiveStatsDone() ).append( "\", " )
			.append( "\"short_code\": \"" ).append( this.getShortCode() ).append( "\", " )
			.append( "\"campaign_master_id\": \"" ).append( this.getCampaignMasterId() ).append( "\", " )
			.append( "\"variation\": \"" ).append( this.getVariation() ).append( "\", " )
			.append( "\"occurence\": \"" ).append( this.getOccurence() ).append( "\", " )
			.append( "\"manual_prov_available\": \"" ).append( this.getManualProvAvailable() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }