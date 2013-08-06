package com.lumata.expression.operators.offermanagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.system.Environment;

public class OfferOptimReservationList {
	
	private static final Logger logger = LoggerFactory.getLogger(OfferOptimReservationList.class);
	
	private ArrayList<OfferOptimReservation> list;
	
	public OfferOptimReservationList( Environment env, String tenant, ArrayList<Integer> filteredIds ) {
				
		this.list = new ArrayList<OfferOptimReservation>();
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
		
		String query = "SELECT * FROM " + tenant + ".offoptim_reservation;";
		
		ResultSet rs = mysql.execQuery( query );
		
		try {
		
			while( rs.next() ) {
				
				OfferOptimReservation or = new OfferOptimReservation( rs );
				this.list.add( or );
				
			}
		
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		mysql.close();
		
	}
	
	public boolean isEquals( ArrayList<OfferOptimReservation> expectedList ) {
		
		if( this.list.size() != expectedList.size() ) { return false; }
		else {
			
			for( int i = 0; i < this.list.size(); i++ ) {
				
				if( this.list.get( i ) != expectedList.get( i ) ) { return false; }
				
			}
			
		}
		
		return true;
		
	}
	
	@Override
	public String toString() {
		
		StringBuffer str = new StringBuffer();
		
		str.append( "[ ");
		
		for( int i = 0; i < this.list.size(); i++ ) {
			
			str.append( "[ ").append( this.list.get( i ).toString() ).append( " ]");
			
			if( i < this.list.size() - 1 ) { str.append(", "); }
			
		}
		
		str.append( " ]");
		
		return str.toString();
				
	}
	
}
