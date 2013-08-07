package com.lumata.expression.operators.dao.offermanagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OfferOptimCustomerItems {
	
	private static final Logger logger = LoggerFactory.getLogger(OfferOptimCustomerItems.class);
	
	private int customer_offer_pack_id;
	private int offer_id;
	private Date allocation_date;
	private String offer_status;
	private Date decision_date;
	
	public enum OfferStatuses { allocated, accepted, not_accepted, refused };

	public OfferOptimCustomerItems() {}
	
	public OfferOptimCustomerItems( int customer_offer_pack_id, int offer_id, Date allocation_date, String offer_status, Date decision_date ) {
		
		this.customer_offer_pack_id = customer_offer_pack_id;
		this.offer_id = offer_id;
		this.allocation_date = allocation_date;
		this.offer_status = offer_status;
		this.decision_date = decision_date;
	
	}

	public OfferOptimCustomerItems( int customer_offer_pack_id, int offer_id, String allocation_date, String offer_status, String decision_date ) {
		
		try {
		
			this.customer_offer_pack_id = customer_offer_pack_id;
			this.offer_id = offer_id;
			this.allocation_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse( allocation_date );
			this.offer_status = offer_status;
			this.decision_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse( decision_date );
		
		} catch ( ParseException e  ) {
			
			logger.error( e.getMessage(), e );
			
		}	
	}
	
	public OfferOptimCustomerItems( ResultSet rs ) {
		
		try {
		
			this.customer_offer_pack_id = rs.getInt( "customer_offer_pack_id" );
			this.offer_id = rs.getInt( "offer_id" );
			this.allocation_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse( rs.getString( "allocation_date" ) );
			this.offer_status = rs.getString( "offer_status" );
			this.decision_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse( rs.getString( "decision_date" ) );
		
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		} catch ( ParseException e  ) {
			
			logger.error( e.getMessage(), e );
			
		}	
		
	}
	
	public int getCustomerOfferPackID() { return this.customer_offer_pack_id; }
	
	public int getOfferID() { return this.offer_id; }
	
	public Date getAllocationDateAsDate() { return this.allocation_date; }
	
	public String getAllocationDateAsString() { return this.allocation_date.toString(); }
	
	public String getOfferStatus() { return this.offer_status; }
	
	public Date getDecisionDateAsDate() { return this.decision_date; }
	
	public String getDecisionDateAsString() { return this.decision_date.toString(); }
	
	public void setCustomerOfferPackID( int customer_offer_pack_id ) { this.customer_offer_pack_id = customer_offer_pack_id; }
	
	public void setOfferID( int offer_id ) { this.offer_id = offer_id; }
	
	public void setAllocationDate( Date allocation_date ) { this.allocation_date = allocation_date; }
	
	public void setAllocationDate( String allocation_date ) { 
		
		try {
		
			this.allocation_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse( allocation_date ); 
	
		} catch ( ParseException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
	}
	
	public void setOfferStatus( String offer_status ) { this.offer_status = offer_status; }
	
	public void setDecisionDate( Date decision_date ) { this.decision_date = decision_date; }
	
	public void setDecisionDate( String decision_date ) { 

		try {
			
			this.decision_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse( decision_date ); 
	
		} catch ( ParseException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
	
	}
	
	@Override
	public String toString() {
		
		StringBuffer str = new StringBuffer();
		
		str.append( "customer_offer_pack_id:").append( this.customer_offer_pack_id ).append( "," )
			.append( "offer_id:" ).append( this.offer_id ).append( "," )
			.append( "allocation_date:" ).append( this.allocation_date.toString() ).append( "," )
			.append( "offer_status:" ).append( this.offer_status ).append( "," )
			.append( "decision_date:" ).append( this.decision_date.toString() );
			
		return str.toString();
				
	}
	
}
