package com.lumata.e4o.dao.tenant;

import static com.lumata.common.testing.orm.Filter.op;
import static com.lumata.common.testing.orm.Query.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lumata.common.testing.database.Mysql;
import com.lumata.e4o.schema.tenant.Networks;

public class DAONetworks extends DAO {
	
	public DAONetworks( Mysql mysql ) {
		super( mysql );
	}
	
	public static DAONetworks getInstance( Mysql mysql ) {
		return new DAONetworks( mysql );
	}

	public ArrayList<Networks> getAvailableNetworks() {
				
		ArrayList<Networks> networks = new ArrayList<Networks>();
		
		String query = select().from( new Networks() ).build();
		
		ResultSet rs = this.getMysql().execQuery( query );
			
		try {
			
			while( rs.next() ) {
				
				Networks network = new Networks( rs );
								
				networks.add( network );
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return networks;
		
	}
	
	public Networks getNetwork( String query ) {
		
		Networks network = null;
		
		ResultSet rs = this.getMysql().execQuery( query );
		
		try {
			
			while( rs.next() ) {
				
				network = new Networks( rs );
								
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return network;
		
	}
	
	public Networks getNetworksId( Integer network_id ) {
		
		String query = select().from( new Networks() ).where( op( Networks.Fields.network_id ).eq( network_id ) ).build();
		
		return getNetwork( query );
		
	}
	
	public Networks getNetworksName( String network_name ) {
		
		String query = select().from( new Networks() ).where( op( Networks.Fields.network ).eq( network_name ) ).build();
		
		return getNetwork( query );
		
	}
	
}
