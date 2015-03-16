package com.lumata.e4o.schema.xmlrpc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class XMLRPCOffer {
	
	public enum XMLRPCOfferStatus {
		allocated, not_accepted, purchased, purchased_failed, refused, delivered
	}
	
	/*
	 *     Offer Allocated: allocated_date
	 *     Offer Not Accepted: allocated_date and  released_date
	 *     Offer Purchased: allocated_date and accepted_date
	 *     Offer Purchase_Failed: allocated_date and accepted_date
	 *     Offer Refused: allocated_date and  refused_date
	 *     Offer Delivered: allocated_date, accepted_date, and delivered_date
	 */
	
	private Short offer_id;
	private String offer_name;
	private String description;
	private String url_image;
	private String agreement;
	private Date start_date;
	private Date end_date;
	private String token_code;
	private Long customer_id;
	private Date allocation_date;
	private String offer_status;
	private Date decision_date;
	private Boolean has_voucher;
	
	public XMLRPCOffer( ResultSet rs ) throws SQLException {
		
		this.offer_id = rs.getShort( "co.offer_id" );
		this.offer_name = ( null != rs.getString( "co.offer_name" ) ? rs.getString( "co.offer_name" ) : "" );
		this.description = ( null != rs.getString( "co.description" ) ? rs.getString( "co.description" ) : "" );
		this.url_image = ( null != rs.getString( "co.url_image" ) ? rs.getString( "co.url_image" ) : "" );
		this.agreement = ( null != rs.getString( "co.agreement" ) ? rs.getString( "co.agreement" ) : "" );
		this.start_date = rs.getDate( "co.start_date" );
		this.end_date = rs.getDate( "co.end_date" );
		this.token_code = ( null != rs.getString( "ocp.token_code" ) ? rs.getString( "ocp.token_code" ) : "" );
		this.customer_id = rs.getLong( "ocp.customer_id" );	
		this.allocation_date = rs.getDate( "oci.allocation_date" );
		this.offer_status = ( null != rs.getString( "oci.offer_status" ) ? rs.getString( "oci.offer_status" ) : "" );
		this.decision_date = rs.getDate( "oci.decision_date" );
		this.has_voucher = rs.getBoolean( "oci.has_voucher" );
		
	}
		
	public Short getOfferId() {

		return this.offer_id;

	}

	public void setOfferId( Short offer_id ) {

		this.offer_id = offer_id;

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

	public String getTokenCode() {

		return this.token_code;

	}

	public void setTokenCode( String token_code ) {

		this.token_code = token_code;

	}

	public Long getCustomerId() {

		return this.customer_id;

	}

	public void setCustomerId( Long customer_id ) {

		this.customer_id = customer_id;

	}
	
	public Date getAllocationDate() {

		return this.allocation_date;

	}

	public void setAllocationDate( Date allocation_date ) {

		this.allocation_date = allocation_date;

	}

	public String getOfferStatus() {

		return this.offer_status;

	}

	public void setOfferStatus( String offer_status ) {

		this.offer_status = XMLRPCOfferStatus.valueOf( offer_status ).name();

	}

	public Date getDecisionDate() {

		return this.decision_date;

	}

	public void setDecisionDate( Date decision_date ) {

		this.decision_date = decision_date;

	}

	public Boolean getHasVoucher() {

		return this.has_voucher;

	}

	public void setHasVoucher( Boolean has_voucher ) {

		this.has_voucher = has_voucher;

	}
	
	
	public static String query( String msisdn, String token ) {
		
		StringBuilder query = new StringBuilder();
		
		query.append( "SELECT " ).
				append( "co.offer_id, " ).
				append( "co.offer_name, " ).
				append( "co.description, " ).
				append( "co.url_image, " ).
				append( "co.agreement, " ).
				append( "co.start_date, " ).
				append( "co.end_date, " ).
				append( "ocp.customer_id, " ).
				append( "ocp.token_code, " ).
				append( "oci.offer_status, " ).
				append( "oci.allocation_date, " ).
				append( "oci.decision_date, " ).
				append( "oci.has_voucher " ).
				append( "FROM offoptim_customer_pack ocp " ).
				append( "JOIN offoptim_customer_items oci " ).
				append( "ON ocp.customer_offer_pack_id = oci.customer_offer_pack_id " ).
				append( "JOIN catalog_offers co " ).
				append( "ON oci.offer_id = co.offer_id " ).
				append( "WHERE ocp.customer_id = '" ).
				append( msisdn ).
				append( "' " ).
				append( "AND ocp.token_code = '" ).
				append( token ).
				append( "';" );
			
		return query.toString();
		
	}

}







// 