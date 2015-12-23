package com.lumata.e4o.dao.tenant;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.database.Mysql;
import com.lumata.e4o.schema.tenant.OfferStock;


public class DAOOfferStock extends DAO {

	SimpleDateFormat sdf;
	
	public DAOOfferStock( Mysql mysql ) {
		super( mysql );
		sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
	}
	
	public static DAOOfferStock getInstance( Mysql mysql ) {
		return new DAOOfferStock( mysql );
	}
	
	private ArrayList<OfferStock> getOfferStockList( String query ) {
		
		ResultSet rs = this.getMysql().execQuery( query );
		
		ArrayList<OfferStock> offerStock = new ArrayList<OfferStock>();
		
		try {
			
			while( rs.next() ) {
				
				OfferStock offStock = new OfferStock( rs );
	
				offerStock.add( offStock );
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return offerStock;
		
	}
	
	public ArrayList<OfferStock> getOfferStockList( Short offerId ) {
		
		String query = select().
						from( new OfferStock() ).
						where( 
							op( OfferStock.Fields.offer_id ).eq( offerId ) 
						).
						build();
		
		return getOfferStockList( query );		
		
	}
	
	private OfferStock getOfferStock( String query ) {
	
		ResultSet rs = this.getMysql().execQuery( query );
	
		OfferStock offerStock = null;
		
		try {
			
			while( rs.next() ) {
				
				offerStock = new OfferStock( rs );
			
				break;
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return offerStock;
		
	}
	
	public OfferStock getOfferStock( Short offerId, Short channelId ) {
		
		String query = select().
						from( new OfferStock() ).
						where( 
							op( OfferStock.Fields.offer_id ).eq( offerId ),
							and(
								op( OfferStock.Fields.channel_id ).eq( channelId )
							)
						).
						build();
		
		return getOfferStock( query );		
		
	}
	
}
