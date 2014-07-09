package com.lumata.e4o.dao.tenant;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.orm.Val;
import com.lumata.e4o.schema.tenant.Subscribers;
import com.lumata.e4o.schema.tenant.Token;

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
									op( Subscribers.Fields.service_id_list ).is( Val.NULL ),
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
	
	private Subscribers getSubscriber( String query ) {
		
		ResultSet rs = this.getMysql().execQuery( query );
		
		Subscribers subscriber = null;
		
		try {
			
			while( rs.next() ) {
				
				subscriber = new Subscribers( rs );
				
				break;
								
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return subscriber;
		
	}
	
	public Subscribers getAvailableSubscriber() {
				
		String query = select().from( new Subscribers() ).orderBy( Subscribers.Fields.msisdn ).limit( 1 ).build();
		
		return getSubscriber( query );
		
	}
	
	public Subscribers getSubscriberWithNoToken() {
		
		String query = select().
						from( new Subscribers() ).
						where( 
								op( Subscribers.Fields.msisdn ).not_in(  
									select( Token.Fields.msisdn ).
										from( new Token() ).
										statement()
								) 
								
						).
						build();
						
		return getSubscriber( query );
		
	}
	
	public Subscribers getSubscriberWithActiveToken() {
		
		String query = select().
				from( new Subscribers() ).
				where( 
						op( Subscribers.Fields.msisdn ).in(  
							select( Token.Fields.msisdn ).
								from( new Token() ).
								statement()
						) 
						
				).
				build();
		
		return getSubscriber( query );
		
	}
	
	private ArrayList<Subscribers> getSubscriberList( String query ) {
		
		ResultSet rs = this.getMysql().execQuery( query );
	
		ArrayList<Subscribers> subscriberList = new ArrayList<Subscribers>();
		
		try {
			
			while( rs.next() ) {
				
				Subscribers subscriber = new Subscribers( rs );
							
				subscriberList.add( subscriber );
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return subscriberList;
		
	}
	
	public ArrayList<Subscribers> getSubscriberList() {
		
		String query = select().from( new Subscribers() ).orderBy( Subscribers.Fields.msisdn ).build();
	
		return getSubscriberList( query );
		
	}
	
}
