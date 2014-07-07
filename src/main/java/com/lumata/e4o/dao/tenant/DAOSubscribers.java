package com.lumata.e4o.dao.tenant;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.lumata.common.testing.database.Mysql;
import com.lumata.e4o.schema.tenant.Subscribers;

public class DAOSubscribers extends DAO {

	public DAOSubscribers( Mysql mysql ) {
		super( mysql );
	}
	
	public static DAOSubscribers getInstance( Mysql mysql ) {
		return new DAOSubscribers( mysql );
	}
	
	public Boolean isSubscriber( Long msisdn ) {
		
		Subscribers subscriber = new Subscribers();
		
		subscriber.setMsisdn( msisdn );
		
		String query = select().from( subscriber ).where( op( Subscribers.Fields.msisdn ).eq() ).build();
		
		ResultSet rs = this.getMysql().execQuery( query );
	
		boolean found = false;
		
		try {
			
			while( rs.next() ) {
				
				found = true;
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return found;
		
	}
	
	public Boolean isSubscriber( Subscribers subscriber ) {
		
		String query = select().
						from( subscriber ).
						where( 
								op( Subscribers.Fields.msisdn ).eq(), 
								and( 
									op( Subscribers.Fields.subscription_date ).eq(), 
									op( Subscribers.Fields.profile_id ).eq(),
									op( Subscribers.Fields.rate_plan_id ).eq(),
									op( Subscribers.Fields.status_id ).eq(),
									op( Subscribers.Fields.in_tag ).eq(),
									op( Subscribers.Fields.network_id ).eq()
								)
						).
						build();
		
		ResultSet rs = this.getMysql().execQuery( query );
	
		boolean found = false;
		
		try {
			
			while( rs.next() ) {
				
				found = true;
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return found;
		
	}
	
	public Subscribers getAvailableSubscriber() {
				
		String query = select().from( new Subscribers() ).orderBy( Subscribers.Fields.msisdn ).limit( 1 ).build();
		
		ResultSet rs = this.getMysql().execQuery( query );
	
		Subscribers subscriber = null;
		
		try {
			
			while( rs.next() ) {
				
				subscriber = new Subscribers( rs );
								
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return subscriber;
		
	}
	
}
