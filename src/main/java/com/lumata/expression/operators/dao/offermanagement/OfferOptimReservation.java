package com.lumata.expression.operators.dao.offermanagement;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.database.MysqlUtils;
import com.lumata.common.testing.system.Environment;

public class OfferOptimReservation {
	
	private static final Logger logger = LoggerFactory.getLogger(OfferOptimReservation.class);
	
	private int offer_id;
	private int qty_reserved;
	private int qty_allocated;
	private int qty_accepted;
	private int qty_refused;

	public OfferOptimReservation() {}
	
	public OfferOptimReservation( int offer_id, int qty_reserved, int qty_allocated, int qty_accepted, int qty_refused ) {
		
		this.offer_id = offer_id;
		this.qty_reserved = qty_reserved;
		this.qty_allocated = qty_allocated;
		this.qty_accepted = qty_accepted;
		this.qty_refused = qty_refused;
		
	}
	
	public OfferOptimReservation( ResultSet rs ) {
		
		try {
		
			this.offer_id = rs.getInt( "offer_id" );
			this.qty_reserved = rs.getInt( "qty_reserved" );
			this.qty_allocated = rs.getInt( "qty_allocated" );
			this.qty_accepted = rs.getInt( "qty_accepted" );
			this.qty_refused = rs.getInt( "qty_refused" );
		
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
			
	}
	
	public int getOfferID() { return this.offer_id; }
	
	public int getQtyReserved() { return this.qty_reserved; }
	
	public int getQtyAllocated() { return this.qty_allocated; }
	
	public int getQtyAccepted() { return this.qty_accepted; }
	
	public int getQtyRefused() { return this.qty_refused; }
	
	public void setOfferID( int offer_id ) { this.offer_id = offer_id; }
	
	public void setQtyReserved( int qty_reserved ) { this.qty_reserved = qty_reserved; }
	
	public void setQtyAllocated( int qty_allocated ) { this.qty_allocated = qty_allocated; }
	
	public void setQtyAccepted( int qty_accepted ) { this.qty_accepted = qty_accepted; }
	
	public void setQtyRefused( int qty_refused ) { this.qty_refused = qty_refused; }
	
	public boolean insert( Environment env, String tenant ) {
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
				
		try {
			
			if( this.getOfferID() < 0 ) { this.setOfferID( ( MysqlUtils.getMaxID( tenant + ".offoptim_reservation", "offer_id", mysql) + 1 ) ); } 
			
			String query = "INSERT INTO " + tenant + ".offoptim_reservation VALUES ( " + this.getOfferID() + ", " + this.getQtyReserved() + ", " + this.getQtyAllocated() + ", " + this.getQtyAccepted() + ", " + this.getQtyRefused() + " );";
			
			int rs = mysql.execUpdate( query );
			
			if( rs < 0 ) { return false; } 
					
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
			mysql.close();
			
			return false;
			
		}
		
		mysql.close();
		
		return true;
		
	}
	
	public boolean delete( Environment env, String tenant ) throws SQLException {
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
				
		if( this.getOfferID() > 0 ) {  
		
			String query = "DELETE FROM " + tenant + ".offoptim_reservation WHERE offer_id = " + this.getOfferID() + ";";
		
			int rs = mysql.execUpdate( query );
		
			if( rs < 0 ) { return false; }
		
		} else { return false; }	
		
		mysql.close();
		
		return true;
		
	}

	@Override
	public String toString() {
		
		StringBuffer str = new StringBuffer();
		
		str.append( "offer_id:").append( this.offer_id ).append( "," )
			.append( "qty_reserved:" ).append( this.qty_reserved ).append( "," )
			.append( "qty_allocated:" ).append( this.qty_allocated ).append( "," )
			.append( "qty_accepted:" ).append( this.qty_accepted ).append( "," )
			.append( "qty_refused:" ).append( this.qty_refused );
			
		return str.toString();
				
	}
	
}
