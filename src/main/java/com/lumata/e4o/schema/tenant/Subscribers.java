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

	public enum Fields { msisdn, subscription_date, profile_id, rate_plan_id, status_id, service_id_list, channel_id_list, network_id, tongue, ucg, ucg_start_date, in_tag, geschlecht, vorname, nachname, strasse, PLZ, stadt, uberraschung_registriert, optionen, email_adresse, aktion_teilnahme_zustand, vorrang, ucg_std, ucg_nursery, update_time, ARPU_klasse, hausnummer, registrierungsdatum, voice_oder_data, churn_risk, smartphone_data_usage }

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
			getMethod = "getMsisdn",
			setMethod = "setMsisdn"
	)
	private Long msisdn;

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
			getMethod = "getUcgStartDate",
			setMethod = "setUcgStartDate"
	)
	private Date ucg_start_date;

	@Column(
			table = "subscribers",
			field = "in_tag",
			type = "enum('O2IN')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 1,
			getMethod = "getInTag",
			setMethod = "setInTag"
	)
	private String in_tag;

	@Column(
			table = "subscribers",
			field = "geschlecht",
			type = "enum('Maennlich','Weiblich')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 2,
			getMethod = "getGeschlecht",
			setMethod = "setGeschlecht"
	)
	private String geschlecht;

	@Column(
			table = "subscribers",
			field = "vorname",
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
			getMethod = "getVorname",
			setMethod = "setVorname"
	)
	private String vorname;

	@Column(
			table = "subscribers",
			field = "nachname",
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
			getMethod = "getNachname",
			setMethod = "setNachname"
	)
	private String nachname;

	@Column(
			table = "subscribers",
			field = "strasse",
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
			getMethod = "getStrasse",
			setMethod = "setStrasse"
	)
	private String strasse;

	@Column(
			table = "subscribers",
			field = "PLZ",
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
			getMethod = "getPLZ",
			setMethod = "setPLZ"
	)
	private String PLZ;

	@Column(
			table = "subscribers",
			field = "stadt",
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
			getMethod = "getStadt",
			setMethod = "setStadt"
	)
	private String stadt;

	@Column(
			table = "subscribers",
			field = "uberraschung_registriert",
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
			getMethod = "getUberraschungRegistriert",
			setMethod = "setUberraschungRegistriert"
	)
	private Byte uberraschung_registriert;

	@Column(
			table = "subscribers",
			field = "optionen",
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
			getMethod = "getOptionen",
			setMethod = "setOptionen"
	)
	private String optionen;

	@Column(
			table = "subscribers",
			field = "email_adresse",
			type = "varchar(60)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 60,
			getMethod = "getEmailAdresse",
			setMethod = "setEmailAdresse"
	)
	private String email_adresse;

	@Column(
			table = "subscribers",
			field = "aktion_teilnahme_zustand",
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
			getMethod = "getAktionTeilnahmeZustand",
			setMethod = "setAktionTeilnahmeZustand"
	)
	private String aktion_teilnahme_zustand;

	@Column(
			table = "subscribers",
			field = "vorrang",
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
			getMethod = "getVorrang",
			setMethod = "setVorrang"
	)
	private String vorrang;

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
			getMethod = "getUcgStd",
			setMethod = "setUcgStd"
	)
	private Byte ucg_std;

	@Column(
			table = "subscribers",
			field = "ucg_nursery",
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
			getMethod = "getUcgNursery",
			setMethod = "setUcgNursery"
	)
	private String ucg_nursery;

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
			getMethod = "getUpdateTime",
			setMethod = "setUpdateTime"
	)
	private Timestamp update_time;

	@Column(
			table = "subscribers",
			field = "ARPU_klasse",
			type = "enum('new_customer_A1_A2','new_customer_neg_Arp','new_customer_A3','new_customer_A4_A5','existing_customer_A1_A2','existing_customer_neg_Arpu','existing_customer_A3','existing_customer_A4_A5')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 8,
			getMethod = "getARPUKlasse",
			setMethod = "setARPUKlasse"
	)
	private String ARPU_klasse;

	@Column(
			table = "subscribers",
			field = "hausnummer",
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
			getMethod = "getHausnummer",
			setMethod = "setHausnummer"
	)
	private String hausnummer;

	@Column(
			table = "subscribers",
			field = "registrierungsdatum",
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
			getMethod = "getRegistrierungsdatum",
			setMethod = "setRegistrierungsdatum"
	)
	private Date registrierungsdatum;

	@Column(
			table = "subscribers",
			field = "voice_oder_data",
			type = "enum('mobile_data','mobile_voice')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 2,
			getMethod = "getVoiceOderData",
			setMethod = "setVoiceOderData"
	)
	private String voice_oder_data;

	@Column(
			table = "subscribers",
			field = "churn_risk",
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
			getMethod = "getChurnRisk",
			setMethod = "setChurnRisk"
	)
	private Byte churn_risk;

	@Column(
			table = "subscribers",
			field = "smartphone_data_usage",
			type = "enum('smartphone_Y_data_usage_N','smartphone_Y_data_usage_Y','smartphone_N_data_usage_N','smartphone_N_data_usage_Y')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getSmartphoneDataUsage",
			setMethod = "setSmartphoneDataUsage"
	)
	private String smartphone_data_usage;


	public Subscribers() {} 

	public Subscribers( ResultSet rs ) throws SQLException {

		this.msisdn = rs.getLong( Subscribers.Fields.msisdn.name() );
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
		this.geschlecht = rs.getString( Subscribers.Fields.geschlecht.name() );
		this.vorname = rs.getString( Subscribers.Fields.vorname.name() );
		this.nachname = rs.getString( Subscribers.Fields.nachname.name() );
		this.strasse = rs.getString( Subscribers.Fields.strasse.name() );
		this.PLZ = rs.getString( Subscribers.Fields.PLZ.name() );
		this.stadt = rs.getString( Subscribers.Fields.stadt.name() );
		this.uberraschung_registriert = rs.getByte( Subscribers.Fields.uberraschung_registriert.name() );
		this.optionen = rs.getString( Subscribers.Fields.optionen.name() );
		this.email_adresse = rs.getString( Subscribers.Fields.email_adresse.name() );
		this.aktion_teilnahme_zustand = rs.getString( Subscribers.Fields.aktion_teilnahme_zustand.name() );
		this.vorrang = rs.getString( Subscribers.Fields.vorrang.name() );
		this.ucg_std = rs.getByte( Subscribers.Fields.ucg_std.name() );
		this.ucg_nursery = rs.getString( Subscribers.Fields.ucg_nursery.name() );
		this.update_time = rs.getTimestamp( Subscribers.Fields.update_time.name() );
		this.ARPU_klasse = rs.getString( Subscribers.Fields.ARPU_klasse.name() );
		this.hausnummer = rs.getString( Subscribers.Fields.hausnummer.name() );
		this.registrierungsdatum = rs.getDate( Subscribers.Fields.registrierungsdatum.name() );
		this.voice_oder_data = rs.getString( Subscribers.Fields.voice_oder_data.name() );
		this.churn_risk = rs.getByte( Subscribers.Fields.churn_risk.name() );
		this.smartphone_data_usage = rs.getString( Subscribers.Fields.smartphone_data_usage.name() );

	}

	public Subscribers( JSONObject jo ) throws JSONException, ParseException {

		this.msisdn = (long)jo.getLong( Subscribers.Fields.msisdn.name() );
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
		this.geschlecht = jo.getString( Subscribers.Fields.geschlecht.name() );
		this.vorname = jo.getString( Subscribers.Fields.vorname.name() );
		this.nachname = jo.getString( Subscribers.Fields.nachname.name() );
		this.strasse = jo.getString( Subscribers.Fields.strasse.name() );
		this.PLZ = jo.getString( Subscribers.Fields.PLZ.name() );
		this.stadt = jo.getString( Subscribers.Fields.stadt.name() );
		this.uberraschung_registriert = (byte)jo.getInt( Subscribers.Fields.uberraschung_registriert.name() );
		this.optionen = jo.getString( Subscribers.Fields.optionen.name() );
		this.email_adresse = jo.getString( Subscribers.Fields.email_adresse.name() );
		this.aktion_teilnahme_zustand = jo.getString( Subscribers.Fields.aktion_teilnahme_zustand.name() );
		this.vorrang = jo.getString( Subscribers.Fields.vorrang.name() );
		this.ucg_std = (byte)jo.getInt( Subscribers.Fields.ucg_std.name() );
		this.ucg_nursery = jo.getString( Subscribers.Fields.ucg_nursery.name() );
		this.update_time = new Timestamp( Format.getMysqlDateTime( jo.getString( Subscribers.Fields.update_time.name() ) ).getTime() );
		this.ARPU_klasse = jo.getString( Subscribers.Fields.ARPU_klasse.name() );
		this.hausnummer = jo.getString( Subscribers.Fields.hausnummer.name() );
		this.registrierungsdatum = Format.getMysqlDateTime( jo.getString( Subscribers.Fields.registrierungsdatum.name() ) );
		this.voice_oder_data = jo.getString( Subscribers.Fields.voice_oder_data.name() );
		this.churn_risk = (byte)jo.getInt( Subscribers.Fields.churn_risk.name() );
		this.smartphone_data_usage = jo.getString( Subscribers.Fields.smartphone_data_usage.name() );

	}

	public Long getMsisdn() {

		return this.msisdn;

	}

	public void setMsisdn( Long msisdn ) {

		this.msisdn = msisdn;

	}

	public Date getSubscriptionDate() {

		return this.subscription_date;

	}

	public void setSubscriptionDate( Date subscription_date ) {

		this.subscription_date = subscription_date;

	}

	public Byte getProfileId() {

		return this.profile_id;

	}

	public void setProfileId( Byte profile_id ) {

		this.profile_id = profile_id;

	}

	public Byte getRatePlanId() {

		return this.rate_plan_id;

	}

	public void setRatePlanId( Byte rate_plan_id ) {

		this.rate_plan_id = rate_plan_id;

	}

	public Byte getStatusId() {

		return this.status_id;

	}

	public void setStatusId( Byte status_id ) {

		this.status_id = status_id;

	}

	public String getServiceIdList() {

		return this.service_id_list;

	}

	public void setServiceIdList( String service_id_list ) {

		this.service_id_list = service_id_list;

	}

	public String getChannelIdList() {

		return this.channel_id_list;

	}

	public void setChannelIdList( String channel_id_list ) {

		this.channel_id_list = channel_id_list;

	}

	public Byte getNetworkId() {

		return this.network_id;

	}

	public void setNetworkId( Byte network_id ) {

		this.network_id = network_id;

	}

	public String getTongue() {

		return this.tongue;

	}

	public void setTongue( String tongue ) {

		this.tongue = tongue;

	}

	public Byte getUcg() {

		return this.ucg;

	}

	public void setUcg( Byte ucg ) {

		this.ucg = ucg;

	}

	public Date getUcgStartDate() {

		return this.ucg_start_date;

	}

	public void setUcgStartDate( Date ucg_start_date ) {

		this.ucg_start_date = ucg_start_date;

	}

	public String getInTag() {

		return this.in_tag;

	}

	public void setInTag( String in_tag ) {

		this.in_tag = in_tag;

	}

	public String getGeschlecht() {

		return this.geschlecht;

	}

	public void setGeschlecht( String geschlecht ) {

		this.geschlecht = geschlecht;

	}

	public String getVorname() {

		return this.vorname;

	}

	public void setVorname( String vorname ) {

		this.vorname = vorname;

	}

	public String getNachname() {

		return this.nachname;

	}

	public void setNachname( String nachname ) {

		this.nachname = nachname;

	}

	public String getStrasse() {

		return this.strasse;

	}

	public void setStrasse( String strasse ) {

		this.strasse = strasse;

	}

	public String getPLZ() {

		return this.PLZ;

	}

	public void setPLZ( String PLZ ) {

		this.PLZ = PLZ;

	}

	public String getStadt() {

		return this.stadt;

	}

	public void setStadt( String stadt ) {

		this.stadt = stadt;

	}

	public Byte getUberraschungRegistriert() {

		return this.uberraschung_registriert;

	}

	public void setUberraschungRegistriert( Byte uberraschung_registriert ) {

		this.uberraschung_registriert = uberraschung_registriert;

	}

	public String getOptionen() {

		return this.optionen;

	}

	public void setOptionen( String optionen ) {

		this.optionen = optionen;

	}

	public String getEmailAdresse() {

		return this.email_adresse;

	}

	public void setEmailAdresse( String email_adresse ) {

		this.email_adresse = email_adresse;

	}

	public String getAktionTeilnahmeZustand() {

		return this.aktion_teilnahme_zustand;

	}

	public void setAktionTeilnahmeZustand( String aktion_teilnahme_zustand ) {

		this.aktion_teilnahme_zustand = aktion_teilnahme_zustand;

	}

	public String getVorrang() {

		return this.vorrang;

	}

	public void setVorrang( String vorrang ) {

		this.vorrang = vorrang;

	}

	public Byte getUcgStd() {

		return this.ucg_std;

	}

	public void setUcgStd( Byte ucg_std ) {

		this.ucg_std = ucg_std;

	}

	public String getUcgNursery() {

		return this.ucg_nursery;

	}

	public void setUcgNursery( String ucg_nursery ) {

		this.ucg_nursery = ucg_nursery;

	}

	public Timestamp getUpdateTime() {

		return this.update_time;

	}

	public void setUpdateTime( Timestamp update_time ) {

		this.update_time = update_time;

	}

	public String getARPUKlasse() {

		return this.ARPU_klasse;

	}

	public void setARPUKlasse( String ARPU_klasse ) {

		this.ARPU_klasse = ARPU_klasse;

	}

	public String getHausnummer() {

		return this.hausnummer;

	}

	public void setHausnummer( String hausnummer ) {

		this.hausnummer = hausnummer;

	}

	public Date getRegistrierungsdatum() {

		return this.registrierungsdatum;

	}

	public void setRegistrierungsdatum( Date registrierungsdatum ) {

		this.registrierungsdatum = registrierungsdatum;

	}

	public String getVoiceOderData() {

		return this.voice_oder_data;

	}

	public void setVoiceOderData( String voice_oder_data ) {

		this.voice_oder_data = voice_oder_data;

	}

	public Byte getChurnRisk() {

		return this.churn_risk;

	}

	public void setChurnRisk( Byte churn_risk ) {

		this.churn_risk = churn_risk;

	}

	public String getSmartphoneDataUsage() {

		return this.smartphone_data_usage;

	}

	public void setSmartphoneDataUsage( String smartphone_data_usage ) {

		this.smartphone_data_usage = smartphone_data_usage;

	}

	public Fields[] getEntityFields() {

		return Subscribers.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"msisdn\": \"" ).append( this.getMsisdn() ).append( "\", " )
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
			.append( "\"geschlecht\": \"" ).append( this.getGeschlecht() ).append( "\", " )
			.append( "\"vorname\": \"" ).append( this.getVorname() ).append( "\", " )
			.append( "\"nachname\": \"" ).append( this.getNachname() ).append( "\", " )
			.append( "\"strasse\": \"" ).append( this.getStrasse() ).append( "\", " )
			.append( "\"PLZ\": \"" ).append( this.getPLZ() ).append( "\", " )
			.append( "\"stadt\": \"" ).append( this.getStadt() ).append( "\", " )
			.append( "\"uberraschung_registriert\": \"" ).append( this.getUberraschungRegistriert() ).append( "\", " )
			.append( "\"optionen\": \"" ).append( this.getOptionen() ).append( "\", " )
			.append( "\"email_adresse\": \"" ).append( this.getEmailAdresse() ).append( "\", " )
			.append( "\"aktion_teilnahme_zustand\": \"" ).append( this.getAktionTeilnahmeZustand() ).append( "\", " )
			.append( "\"vorrang\": \"" ).append( this.getVorrang() ).append( "\", " )
			.append( "\"ucg_std\": \"" ).append( this.getUcgStd() ).append( "\", " )
			.append( "\"ucg_nursery\": \"" ).append( this.getUcgNursery() ).append( "\", " )
			.append( "\"update_time\": \"" ).append( this.getUpdateTime() ).append( "\", " )
			.append( "\"ARPU_klasse\": \"" ).append( this.getARPUKlasse() ).append( "\", " )
			.append( "\"hausnummer\": \"" ).append( this.getHausnummer() ).append( "\", " )
			.append( "\"registrierungsdatum\": \"" ).append( this.getRegistrierungsdatum() ).append( "\", " )
			.append( "\"voice_oder_data\": \"" ).append( this.getVoiceOderData() ).append( "\", " )
			.append( "\"churn_risk\": \"" ).append( this.getChurnRisk() ).append( "\", " )
			.append( "\"smartphone_data_usage\": \"" ).append( this.getSmartphoneDataUsage() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }