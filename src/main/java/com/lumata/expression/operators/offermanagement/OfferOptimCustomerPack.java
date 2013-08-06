package com.lumata.expression.operators.offermanagement;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OfferOptimCustomerPack {
	
	private static final Logger logger = LoggerFactory.getLogger(OfferOptimCustomerPack.class);
	
	private int customer_offer_pack_id;
	private String token_code;
	private int customer_id;
		
	public OfferOptimCustomerPack() {}
	
	public OfferOptimCustomerPack( int customer_offer_pack_id, String token_code, int customer_id ) {
		
		this.customer_offer_pack_id = customer_offer_pack_id;
		this.token_code = token_code;
		this.customer_id = customer_id;
				
	}
	
	public OfferOptimCustomerPack( ResultSet rs ) {
		
		try {
		
			this.customer_offer_pack_id = rs.getInt( "customer_offer_pack_id" );
			this.token_code = rs.getString( "token_code" );
			this.customer_id = rs.getInt( "customer_id" );
		
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
			
	}
	
	public int getCustomerOfferPackID() { return this.customer_offer_pack_id; }
	
	public String getTokenCode() { return this.token_code; }
	
	public int getCustomerID() { return this.customer_id; }
	
	public void setCustomerOfferPackID( int customer_offer_pack_id ) { this.customer_offer_pack_id = customer_offer_pack_id; }
	
	public void setTokenCode( String token_code ) { this.token_code = token_code; }
	
	public void setCustomerID( int customer_id ) { this.customer_id = customer_id; }
	

	@Override
	public String toString() {
		
		StringBuffer str = new StringBuffer();
		
		str.append( "customer_offer_pack_id:").append( this.customer_offer_pack_id ).append( "," )
			.append( "token_code:" ).append( this.token_code ).append( "," )
			.append( "customer_id:" ).append( this.customer_id );
			
		return str.toString();
				
	}
	
}
