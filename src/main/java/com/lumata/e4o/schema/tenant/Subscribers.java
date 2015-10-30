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


@Table( "subscribers" )
public class Subscribers { 

	public enum Fields { msisdn, imei, imsi, subscription_date, profile_id, rate_plan_id, status_id, service_id_list, channel_id_list, network_id, tongue, ucg, ucg_start_date, in_tag, hobbies, options, tariff_plan, buyer_profile, ucg_std, gender, salary, update_time }

	@Column(
			table = "subscribers",
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
			table = "subscribers",
			field = "imei",
			type = "bigint(20)",
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
			getMethod = "getImei",
			setMethod = "setImei"
	)
	private Long imei;

	@Column(
			table = "subscribers",
			field = "imsi",
			type = "bigint(20)",
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
			getMethod = "getImsi",
			setMethod = "setImsi"
	)
	private Long imsi;

	@Column(
			table = "subscribers",
			field = "subscription_date",
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
			getMethod = "getSubscriptionDate",
			setMethod = "setSubscriptionDate"
	)
	private Date subscription_date;

	@Column(
			table = "subscribers",
			field = "profile_id",
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
			getMethod = "getProfileId",
			setMethod = "setProfileId"
	)
	private Byte profile_id;

	@Column(
			table = "subscribers",
			field = "rate_plan_id",
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
			getMethod = "getRatePlanId",
			setMethod = "setRatePlanId"
	)
	private Byte rate_plan_id;

	@Column(
			table = "subscribers",
			field = "status_id",
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
			getMethod = "getStatusId",
			setMethod = "setStatusId"
	)
	private Byte status_id;

	@Column(
			table = "subscribers",
			field = "service_id_list",
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
			getMethod = "getServiceIdList",
			setMethod = "setServiceIdList"
	)
	private String service_id_list;

	@Column(
			table = "subscribers",
			field = "channel_id_list",
			type = "set('1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40','41','42','43','44','45','46','47','48','49','50','51','52','53','54','55','56','57','58','59','60','61','62','63','64')",
			mysqlType = "set",
			javaType = "Set",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 64,
			comment = "",
			getMethod = "getChannelIdList",
			setMethod = "setChannelIdList"
	)
	private String channel_id_list;

	@Column(
			table = "subscribers",
			field = "network_id",
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
			getMethod = "getNetworkId",
			setMethod = "setNetworkId"
	)
	private Byte network_id;

	@Column(
			table = "subscribers",
			field = "tongue",
			type = "varchar(30)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 30,
			comment = "",
			getMethod = "getTongue",
			setMethod = "setTongue"
	)
	private String tongue;

	@Column(
			table = "subscribers",
			field = "ucg",
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
			getMethod = "getUcg",
			setMethod = "setUcg"
	)
	private Byte ucg;

	@Column(
			table = "subscribers",
			field = "ucg_start_date",
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
			getMethod = "getUcgStartDate",
			setMethod = "setUcgStartDate"
	)
	private Date ucg_start_date;

	@Column(
			table = "subscribers",
			field = "in_tag",
			type = "enum('QAIN')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 1,
			comment = "",
			getMethod = "getInTag",
			setMethod = "setInTag"
	)
	private String in_tag;

	@Column(
			table = "subscribers",
			field = "hobbies",
			type = "set('1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40','41','42','43','44','45','46','47','48','49','50','51','52','53','54','55','56','57','58','59','60','61','62','63','64')",
			mysqlType = "set",
			javaType = "Set",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 64,
			comment = "",
			getMethod = "getHobbies",
			setMethod = "setHobbies"
	)
	private String hobbies;

	@Column(
			table = "subscribers",
			field = "options",
			type = "varbinary(256)",
			mysqlType = "varbinary",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 256,
			comment = "",
			getMethod = "getOptions",
			setMethod = "setOptions"
	)
	private String options;

	@Column(
			table = "subscribers",
			field = "tariff_plan",
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
			getMethod = "getTariffPlan",
			setMethod = "setTariffPlan"
	)
	private Short tariff_plan;

	@Column(
			table = "subscribers",
			field = "buyer_profile",
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
			getMethod = "getBuyerProfile",
			setMethod = "setBuyerProfile"
	)
	private Short buyer_profile;

	@Column(
			table = "subscribers",
			field = "ucg_std",
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
			getMethod = "getUcgStd",
			setMethod = "setUcgStd"
	)
	private Byte ucg_std;

	@Column(
			table = "subscribers",
			field = "gender",
			type = "enum('COMPANY','FEMALE','MALE','UNDEFINED')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			comment = "",
			getMethod = "getGender",
			setMethod = "setGender"
	)
	private String gender;

	@Column(
			table = "subscribers",
			field = "salary",
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
			getMethod = "getSalary",
			setMethod = "setSalary"
	)
	private Float salary;

	@Column(
			table = "subscribers",
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


	public Subscribers() {} 

	public Subscribers( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( Subscribers.Fields.msisdn.name() );
		this.imei = rs.getLong( Subscribers.Fields.imei.name() );
		this.imsi = rs.getLong( Subscribers.Fields.imsi.name() );
		this.subscription_date = rs.getDate( Subscribers.Fields.subscription_date.name() );
		this.profile_id = rs.getByte( Subscribers.Fields.profile_id.name() );
		this.rate_plan_id = rs.getByte( Subscribers.Fields.rate_plan_id.name() );
		this.status_id = rs.getByte( Subscribers.Fields.status_id.name() );
		this.service_id_list = rs.getString( Subscribers.Fields.service_id_list.name() );
		this.channel_id_list = rs.getString( Subscribers.Fields.channel_id_list.name() );
		this.network_id = rs.getByte( Subscribers.Fields.network_id.name() );
		this.tongue = rs.getString( Subscribers.Fields.tongue.name() );
		this.ucg = rs.getByte( Subscribers.Fields.ucg.name() );
		this.ucg_start_date = rs.getDate( Subscribers.Fields.ucg_start_date.name() );
		this.in_tag = rs.getString( Subscribers.Fields.in_tag.name() );
		this.hobbies = rs.getString( Subscribers.Fields.hobbies.name() );
		this.options = rs.getString( Subscribers.Fields.options.name() );
		this.tariff_plan = rs.getShort( Subscribers.Fields.tariff_plan.name() );
		this.buyer_profile = rs.getShort( Subscribers.Fields.buyer_profile.name() );
		this.ucg_std = rs.getByte( Subscribers.Fields.ucg_std.name() );
		this.gender = rs.getString( Subscribers.Fields.gender.name() );
		this.salary = rs.getFloat( Subscribers.Fields.salary.name() );
		this.update_time = rs.getTimestamp( Subscribers.Fields.update_time.name() );

	}

	public Subscribers( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( Subscribers.Fields.msisdn.name() );
		this.imei = (long)jo.getLong( Subscribers.Fields.imei.name() );
		this.imsi = (long)jo.getLong( Subscribers.Fields.imsi.name() );
		this.subscription_date = Format.getMysqlDateTime( jo.getString( Subscribers.Fields.subscription_date.name() ) );
		this.profile_id = (byte)jo.getInt( Subscribers.Fields.profile_id.name() );
		this.rate_plan_id = (byte)jo.getInt( Subscribers.Fields.rate_plan_id.name() );
		this.status_id = (byte)jo.getInt( Subscribers.Fields.status_id.name() );
		this.service_id_list = jo.getString( Subscribers.Fields.service_id_list.name() );
		this.channel_id_list = jo.getString( Subscribers.Fields.channel_id_list.name() );
		this.network_id = (byte)jo.getInt( Subscribers.Fields.network_id.name() );
		this.tongue = jo.getString( Subscribers.Fields.tongue.name() );
		this.ucg = (byte)jo.getInt( Subscribers.Fields.ucg.name() );
		this.ucg_start_date = Format.getMysqlDateTime( jo.getString( Subscribers.Fields.ucg_start_date.name() ) );
		this.in_tag = jo.getString( Subscribers.Fields.in_tag.name() );
		this.hobbies = jo.getString( Subscribers.Fields.hobbies.name() );
		this.options = jo.getString( Subscribers.Fields.options.name() );
		this.tariff_plan = (short)jo.getInt( Subscribers.Fields.tariff_plan.name() );
		this.buyer_profile = (short)jo.getInt( Subscribers.Fields.buyer_profile.name() );
		this.ucg_std = (byte)jo.getInt( Subscribers.Fields.ucg_std.name() );
		this.gender = jo.getString( Subscribers.Fields.gender.name() );
		this.salary = (float)jo.getDouble( Subscribers.Fields.salary.name() );
		this.update_time = new Timestamp( Format.getMysqlDateTime( jo.getString( Subscribers.Fields.update_time.name() ) ).getTime() );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public Subscribers setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

		return this;

	}

	public Long getImei() {

		return this.imei;

	}

	public Subscribers setImei( Long imei ) {

		this.imei = imei;

		return this;

	}

	public Long getImsi() {

		return this.imsi;

	}

	public Subscribers setImsi( Long imsi ) {

		this.imsi = imsi;

		return this;

	}

	public Date getSubscriptionDate() {

		return this.subscription_date;

	}

	public Subscribers setSubscriptionDate( Date subscription_date ) {

		this.subscription_date = subscription_date;

		return this;

	}

	public Byte getProfileId() {

		return this.profile_id;

	}

	public Subscribers setProfileId( Byte profile_id ) {

		this.profile_id = profile_id;

		return this;

	}

	public Byte getRatePlanId() {

		return this.rate_plan_id;

	}

	public Subscribers setRatePlanId( Byte rate_plan_id ) {

		this.rate_plan_id = rate_plan_id;

		return this;

	}

	public Byte getStatusId() {

		return this.status_id;

	}

	public Subscribers setStatusId( Byte status_id ) {

		this.status_id = status_id;

		return this;

	}

	public String getServiceIdList() {

		return this.service_id_list;

	}

	public Subscribers setServiceIdList( String service_id_list ) {

		this.service_id_list = service_id_list;

		return this;

	}

	public String getChannelIdList() {

		return this.channel_id_list;

	}

	public Subscribers setChannelIdList( String channel_id_list ) {

		this.channel_id_list = channel_id_list;

		return this;

	}

	public Byte getNetworkId() {

		return this.network_id;

	}

	public Subscribers setNetworkId( Byte network_id ) {

		this.network_id = network_id;

		return this;

	}

	public String getTongue() {

		return this.tongue;

	}

	public Subscribers setTongue( String tongue ) {

		this.tongue = tongue;

		return this;

	}

	public Byte getUcg() {

		return this.ucg;

	}

	public Subscribers setUcg( Byte ucg ) {

		this.ucg = ucg;

		return this;

	}

	public Date getUcgStartDate() {

		return this.ucg_start_date;

	}

	public Subscribers setUcgStartDate( Date ucg_start_date ) {

		this.ucg_start_date = ucg_start_date;

		return this;

	}

	public String getInTag() {

		return this.in_tag;

	}

	public Subscribers setInTag( String in_tag ) {

		this.in_tag = in_tag;

		return this;

	}

	public String getHobbies() {

		return this.hobbies;

	}

	public Subscribers setHobbies( String hobbies ) {

		this.hobbies = hobbies;

		return this;

	}

	public String getOptions() {

		return this.options;

	}

	public Subscribers setOptions( String options ) {

		this.options = options;

		return this;

	}

	public Short getTariffPlan() {

		return this.tariff_plan;

	}

	public Subscribers setTariffPlan( Short tariff_plan ) {

		this.tariff_plan = tariff_plan;

		return this;

	}

	public Short getBuyerProfile() {

		return this.buyer_profile;

	}

	public Subscribers setBuyerProfile( Short buyer_profile ) {

		this.buyer_profile = buyer_profile;

		return this;

	}

	public Byte getUcgStd() {

		return this.ucg_std;

	}

	public Subscribers setUcgStd( Byte ucg_std ) {

		this.ucg_std = ucg_std;

		return this;

	}

	public String getGender() {

		return this.gender;

	}

	public Subscribers setGender( String gender ) {

		this.gender = gender;

		return this;

	}

	public Float getSalary() {

		return this.salary;

	}

	public Subscribers setSalary( Float salary ) {

		this.salary = salary;

		return this;

	}

	public Timestamp getUpdateTime() {

		return this.update_time;

	}

	public Subscribers setUpdateTime( Timestamp update_time ) {

		this.update_time = update_time;

		return this;

	}

	public Fields[] getEntityFields() {

		return Subscribers.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
			.append( "\"imei\": \"" ).append( this.getImei() ).append( "\", " )
			.append( "\"imsi\": \"" ).append( this.getImsi() ).append( "\", " )
			.append( "\"subscription_date\": \"" ).append( this.getSubscriptionDate() ).append( "\", " )
			.append( "\"profile_id\": \"" ).append( this.getProfileId() ).append( "\", " )
			.append( "\"rate_plan_id\": \"" ).append( this.getRatePlanId() ).append( "\", " )
			.append( "\"status_id\": \"" ).append( this.getStatusId() ).append( "\", " )
			.append( "\"service_id_list\": \"" ).append( this.getServiceIdList() ).append( "\", " )
			.append( "\"channel_id_list\": \"" ).append( this.getChannelIdList() ).append( "\", " )
			.append( "\"network_id\": \"" ).append( this.getNetworkId() ).append( "\", " )
			.append( "\"tongue\": \"" ).append( this.getTongue() ).append( "\", " )
			.append( "\"ucg\": \"" ).append( this.getUcg() ).append( "\", " )
			.append( "\"ucg_start_date\": \"" ).append( this.getUcgStartDate() ).append( "\", " )
			.append( "\"in_tag\": \"" ).append( this.getInTag() ).append( "\", " )
			.append( "\"hobbies\": \"" ).append( this.getHobbies() ).append( "\", " )
			.append( "\"options\": \"" ).append( this.getOptions() ).append( "\", " )
			.append( "\"tariff_plan\": \"" ).append( this.getTariffPlan() ).append( "\", " )
			.append( "\"buyer_profile\": \"" ).append( this.getBuyerProfile() ).append( "\", " )
			.append( "\"ucg_std\": \"" ).append( this.getUcgStd() ).append( "\", " )
			.append( "\"gender\": \"" ).append( this.getGender() ).append( "\", " )
			.append( "\"salary\": \"" ).append( this.getSalary() ).append( "\", " )
			.append( "\"update_time\": \"" ).append( this.getUpdateTime() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }