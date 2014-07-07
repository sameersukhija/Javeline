package com.lumata.e4o.dao.tenant;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lumata.common.testing.database.Mysql;
import com.lumata.e4o.schema.tenant.Profiles;

public class DAOProfiles extends DAO {

	public DAOProfiles( Mysql mysql ) {
		super( mysql );
	}
	
	public static DAOProfiles getInstance( Mysql mysql ) {
		return new DAOProfiles( mysql );
	}
	
	public Boolean isProfiles( String profileName ) {
		
		Profiles profiles = new Profiles();
		
		profiles.setProfile( profileName );
		
		String query = select().from( profiles ).where( op( Profiles.Fields.profile ).eq() ).build();
		
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
	
	public ArrayList<Profiles> getAvailableProfiles() {
				
		ArrayList<Profiles> profiles = new ArrayList<Profiles>();
		
		String query = select().from( new Profiles() ).orderBy( Profiles.Fields.profile ).build();
		
		ResultSet rs = this.getMysql().execQuery( query );
			
		try {
			
			while( rs.next() ) {
				
				Profiles profile = new Profiles( rs );
								
				profiles.add( profile );
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return profiles;
		
	}
	
	public Profiles getProfileById( Integer profile_id ) {
		
		Profiles profile = null;
		
		String query = select().from( new Profiles() ).where( op( Profiles.Fields.profile_id ).eq( profile_id ) ).build();
		
		ResultSet rs = this.getMysql().execQuery( query );
			
		try {
			
			while( rs.next() ) {
				
				profile = new Profiles( rs );
								
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return profile;
		
	}
	
}
