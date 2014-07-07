package com.lumata.e4o.dao.tenant;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lumata.common.testing.database.Mysql;
import com.lumata.e4o.schema.tenant.Statuses;

public class DAOStatuses extends DAO {

	public DAOStatuses( Mysql mysql ) {
		super( mysql );
	}
	
	public static DAOStatuses getInstance( Mysql mysql ) {
		return new DAOStatuses( mysql );
	}
	
	public Boolean isStatus( String statusName ) {
		
		Statuses status = new Statuses();
		
		status.setStatus( statusName );
				
		String query = select().from( status ).where( op( Statuses.Fields.status ).eq() ).build();
		
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
	
	public ArrayList<Statuses> getAvailableStatuses() {
				
		ArrayList<Statuses> statuses = new ArrayList<Statuses>();
		
		String query = select().from( new Statuses() ).orderBy( Statuses.Fields.status ).build();
		
		ResultSet rs = this.getMysql().execQuery( query );
			
		try {
			
			while( rs.next() ) {
				
				Statuses status = new Statuses( rs );
								
				statuses.add( status );
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return statuses;
		
	}
	
	public Statuses getStatusByProfileId( Byte profileId ) {
		
		Statuses status = new Statuses();
		
		status.setProfileId( profileId );
		
		String query = select().from( status ).where( op( Statuses.Fields.profile_id ).eq() ).build();
		
		ResultSet rs = this.getMysql().execQuery( query );
			
		try {
			
			while( rs.next() ) {
				
				status = new Statuses( rs );								
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return status;
		
	}
	
}
