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


@Table( "catalog_offers" )
public class CatalogOffers { 

	public enum Fields { offer_id, external_id, offer_name, description, voucher_type, voucher_unlimited_code, redemption_notification, start_date, end_date, offer_status, eligibility_criteria, notification, offer_rank, labels_id_list, update_time, url_image, agreement, subscriber_limit }

	@Column(
			table = "catalog_offers",
			field = "offer_id",
			type = "smallint(5)",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = true,
			key = "PRI",
			defaultValue = "null",
			extra = "auto_increment",
			length = 5,
			getMethod = "getOfferId",
			setMethod = "setOfferId"
	)
	private Short offer_id;

	@Column(
			table = "catalog_offers",
			field = "external_id",
			type = "varchar(45)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 45,
			getMethod = "getExternalId",
			setMethod = "setExternalId"
	)
	private String external_id;

	@Column(
			table = "catalog_offers",
			field = "offer_name",
			type = "varchar(45)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "UNI",
			defaultValue = "null",
			extra = "",
			length = 45,
			getMethod = "getOfferName",
			setMethod = "setOfferName"
	)
	private String offer_name;

	@Column(
			table = "catalog_offers",
			field = "description",
			type = "text",
			mysqlType = "text",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getDescription",
			setMethod = "setDescription"
	)
	private String description;

	@Column(
			table = "catalog_offers",
			field = "voucher_type",
			type = "enum('none','oneTimeUse','unlimitedUse')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "none",
			extra = "",
			length = 3,
			getMethod = "getVoucherType",
			setMethod = "setVoucherType"
	)
	private String voucher_type;

	@Column(
			table = "catalog_offers",
			field = "voucher_unlimited_code",
			type = "varchar(15)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 15,
			getMethod = "getVoucherUnlimitedCode",
			setMethod = "setVoucherUnlimitedCode"
	)
	private String voucher_unlimited_code;

	@Column(
			table = "catalog_offers",
			field = "redemption_notification",
			type = "blob",
			mysqlType = "blob",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getRedemptionNotification",
			setMethod = "setRedemptionNotification"
	)
	private String redemption_notification;

	@Column(
			table = "catalog_offers",
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
			getMethod = "getStartDate",
			setMethod = "setStartDate"
	)
	private Date start_date;

	@Column(
			table = "catalog_offers",
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
			getMethod = "getEndDate",
			setMethod = "setEndDate"
	)
	private Date end_date;

	@Column(
			table = "catalog_offers",
			field = "offer_status",
			type = "enum('CREATED','ACTIVATED','CANCELED','TERMINATED')",
			mysqlType = "enum",
			javaType = "Enum",
			categoryType = "Collection",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 4,
			getMethod = "getOfferStatus",
			setMethod = "setOfferStatus"
	)
	private String offer_status;

	@Column(
			table = "catalog_offers",
			field = "eligibility_criteria",
			type = "blob",
			mysqlType = "blob",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getEligibilityCriteria",
			setMethod = "setEligibilityCriteria"
	)
	private String eligibility_criteria;

	@Column(
			table = "catalog_offers",
			field = "notification",
			type = "blob",
			mysqlType = "blob",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getNotification",
			setMethod = "setNotification"
	)
	private String notification;

	@Column(
			table = "catalog_offers",
			field = "offer_rank",
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
			getMethod = "getOfferRank",
			setMethod = "setOfferRank"
	)
	private Byte offer_rank;

	@Column(
			table = "catalog_offers",
			field = "labels_id_list",
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
			getMethod = "getLabelsIdList",
			setMethod = "setLabelsIdList"
	)
	private String labels_id_list;

	@Column(
			table = "catalog_offers",
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
			table = "catalog_offers",
			field = "url_image",
			type = "varchar(255)",
			mysqlType = "varchar",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 255,
			getMethod = "getUrlImage",
			setMethod = "setUrlImage"
	)
	private String url_image;

	@Column(
			table = "catalog_offers",
			field = "agreement",
			type = "text",
			mysqlType = "text",
			javaType = "String",
			categoryType = "String",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "null",
			extra = "",
			length = 0,
			getMethod = "getAgreement",
			setMethod = "setAgreement"
	)
	private String agreement;

	@Column(
			table = "catalog_offers",
			field = "subscriber_limit",
			type = "smallint(5)",
			mysqlType = "smallint",
			javaType = "Short",
			categoryType = "Number",
			isNull = false,
			isAutoincrement = false,
			key = "",
			defaultValue = "-1",
			extra = "",
			length = 5,
			getMethod = "getSubscriberLimit",
			setMethod = "setSubscriberLimit"
	)
	private Short subscriber_limit;


	public CatalogOffers() {} 

	public CatalogOffers( ResultSet rs ) throws SQLException {

		this.offer_id = rs.getShort( CatalogOffers.Fields.offer_id.name() );
		this.external_id = rs.getString( CatalogOffers.Fields.external_id.name() );
		this.offer_name = rs.getString( CatalogOffers.Fields.offer_name.name() );
		this.description = rs.getString( CatalogOffers.Fields.description.name() );
		this.voucher_type = rs.getString( CatalogOffers.Fields.voucher_type.name() );
		this.voucher_unlimited_code = rs.getString( CatalogOffers.Fields.voucher_unlimited_code.name() );
		this.redemption_notification = rs.getString( CatalogOffers.Fields.redemption_notification.name() );
		this.start_date = rs.getDate( CatalogOffers.Fields.start_date.name() );
		this.end_date = rs.getDate( CatalogOffers.Fields.end_date.name() );
		this.offer_status = rs.getString( CatalogOffers.Fields.offer_status.name() );
		this.eligibility_criteria = rs.getString( CatalogOffers.Fields.eligibility_criteria.name() );
		this.notification = rs.getString( CatalogOffers.Fields.notification.name() );
		this.offer_rank = rs.getByte( CatalogOffers.Fields.offer_rank.name() );
		this.labels_id_list = rs.getString( CatalogOffers.Fields.labels_id_list.name() );
		this.update_time = rs.getTimestamp( CatalogOffers.Fields.update_time.name() );
		this.url_image = rs.getString( CatalogOffers.Fields.url_image.name() );
		this.agreement = rs.getString( CatalogOffers.Fields.agreement.name() );
		this.subscriber_limit = rs.getShort( CatalogOffers.Fields.subscriber_limit.name() );

	}

	public CatalogOffers( JSONObject jo ) throws JSONException, ParseException {

		this.offer_id = (short)jo.getInt( CatalogOffers.Fields.offer_id.name() );
		this.external_id = jo.getString( CatalogOffers.Fields.external_id.name() );
		this.offer_name = jo.getString( CatalogOffers.Fields.offer_name.name() );
		this.description = jo.getString( CatalogOffers.Fields.description.name() );
		this.voucher_type = jo.getString( CatalogOffers.Fields.voucher_type.name() );
		this.voucher_unlimited_code = jo.getString( CatalogOffers.Fields.voucher_unlimited_code.name() );
		this.redemption_notification = jo.getString( CatalogOffers.Fields.redemption_notification.name() );
		this.start_date = Format.getMysqlDateTime( jo.getString( CatalogOffers.Fields.start_date.name() ) );
		this.end_date = Format.getMysqlDateTime( jo.getString( CatalogOffers.Fields.end_date.name() ) );
		this.offer_status = jo.getString( CatalogOffers.Fields.offer_status.name() );
		this.eligibility_criteria = jo.getString( CatalogOffers.Fields.eligibility_criteria.name() );
		this.notification = jo.getString( CatalogOffers.Fields.notification.name() );
		this.offer_rank = (byte)jo.getInt( CatalogOffers.Fields.offer_rank.name() );
		this.labels_id_list = jo.getString( CatalogOffers.Fields.labels_id_list.name() );
		this.update_time = new Timestamp( Format.getMysqlDateTime( jo.getString( CatalogOffers.Fields.update_time.name() ) ).getTime() );
		this.url_image = jo.getString( CatalogOffers.Fields.url_image.name() );
		this.agreement = jo.getString( CatalogOffers.Fields.agreement.name() );
		this.subscriber_limit = (short)jo.getInt( CatalogOffers.Fields.subscriber_limit.name() );

	}

	public Short getOfferId() {

		return this.offer_id;

	}

	public void setOfferId( Short offer_id ) {

		this.offer_id = offer_id;

	}

	public String getExternalId() {

		return this.external_id;

	}

	public void setExternalId( String external_id ) {

		this.external_id = external_id;

	}

	public String getOfferName() {

		return this.offer_name;

	}

	public void setOfferName( String offer_name ) {

		this.offer_name = offer_name;

	}

	public String getDescription() {

		return this.description;

	}

	public void setDescription( String description ) {

		this.description = description;

	}

	public String getVoucherType() {

		return this.voucher_type;

	}

	public void setVoucherType( String voucher_type ) {

		this.voucher_type = voucher_type;

	}

	public String getVoucherUnlimitedCode() {

		return this.voucher_unlimited_code;

	}

	public void setVoucherUnlimitedCode( String voucher_unlimited_code ) {

		this.voucher_unlimited_code = voucher_unlimited_code;

	}

	public String getRedemptionNotification() {

		return this.redemption_notification;

	}

	public void setRedemptionNotification( String redemption_notification ) {

		this.redemption_notification = redemption_notification;

	}

	public Date getStartDate() {

		return this.start_date;

	}

	public void setStartDate( Date start_date ) {

		this.start_date = start_date;

	}

	public Date getEndDate() {

		return this.end_date;

	}

	public void setEndDate( Date end_date ) {

		this.end_date = end_date;

	}

	public String getOfferStatus() {

		return this.offer_status;

	}

	public void setOfferStatus( String offer_status ) {

		this.offer_status = offer_status;

	}

	public String getEligibilityCriteria() {

		return this.eligibility_criteria;

	}

	public void setEligibilityCriteria( String eligibility_criteria ) {

		this.eligibility_criteria = eligibility_criteria;

	}

	public String getNotification() {

		return this.notification;

	}

	public void setNotification( String notification ) {

		this.notification = notification;

	}

	public Byte getOfferRank() {

		return this.offer_rank;

	}

	public void setOfferRank( Byte offer_rank ) {

		this.offer_rank = offer_rank;

	}

	public String getLabelsIdList() {

		return this.labels_id_list;

	}

	public void setLabelsIdList( String labels_id_list ) {

		this.labels_id_list = labels_id_list;

	}

	public Timestamp getUpdateTime() {

		return this.update_time;

	}

	public void setUpdateTime( Timestamp update_time ) {

		this.update_time = update_time;

	}

	public String getUrlImage() {

		return this.url_image;

	}

	public void setUrlImage( String url_image ) {

		this.url_image = url_image;

	}

	public String getAgreement() {

		return this.agreement;

	}

	public void setAgreement( String agreement ) {

		this.agreement = agreement;

	}

	public Short getSubscriberLimit() {

		return this.subscriber_limit;

	}

	public void setSubscriberLimit( Short subscriber_limit ) {

		this.subscriber_limit = subscriber_limit;

	}

	public Fields[] getEntityFields() {

		return CatalogOffers.Fields.values();

	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		str.append( "{ " )
			.append( "\"offer_id\": \"" ).append( this.getOfferId() ).append( "\", " )
			.append( "\"external_id\": \"" ).append( this.getExternalId() ).append( "\", " )
			.append( "\"offer_name\": \"" ).append( this.getOfferName() ).append( "\", " )
			.append( "\"description\": \"" ).append( this.getDescription() ).append( "\", " )
			.append( "\"voucher_type\": \"" ).append( this.getVoucherType() ).append( "\", " )
			.append( "\"voucher_unlimited_code\": \"" ).append( this.getVoucherUnlimitedCode() ).append( "\", " )
			.append( "\"redemption_notification\": \"" ).append( this.getRedemptionNotification() ).append( "\", " )
			.append( "\"start_date\": \"" ).append( this.getStartDate() ).append( "\", " )
			.append( "\"end_date\": \"" ).append( this.getEndDate() ).append( "\", " )
			.append( "\"offer_status\": \"" ).append( this.getOfferStatus() ).append( "\", " )
			.append( "\"eligibility_criteria\": \"" ).append( this.getEligibilityCriteria() ).append( "\", " )
			.append( "\"notification\": \"" ).append( this.getNotification() ).append( "\", " )
			.append( "\"offer_rank\": \"" ).append( this.getOfferRank() ).append( "\", " )
			.append( "\"labels_id_list\": \"" ).append( this.getLabelsIdList() ).append( "\", " )
			.append( "\"update_time\": \"" ).append( this.getUpdateTime() ).append( "\", " )
			.append( "\"url_image\": \"" ).append( this.getUrlImage() ).append( "\", " )
			.append( "\"agreement\": \"" ).append( this.getAgreement() ).append( "\", " )
			.append( "\"subscriber_limit\": \"" ).append( this.getSubscriberLimit() ).append( "\"" )
			.append( " }" );

		return str.toString();

	}

 }