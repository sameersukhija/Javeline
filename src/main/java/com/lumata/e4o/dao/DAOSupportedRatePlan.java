package com.lumata.e4o.dao;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lumata.common.testing.database.Mysql;
import com.lumata.e4o.schema.tenant.SupportedRatePlan;

public class DAOSupportedRatePlan extends DAO {

	public DAOSupportedRatePlan( Mysql mysql ) {
		super( mysql );
	}
	
	public static DAOSupportedRatePlan getInstance( Mysql mysql ) {
		return new DAOSupportedRatePlan( mysql );
	}
	
	public Boolean isSupportedRatePlan( String ratePlanName ) {
		
		SupportedRatePlan supportedRatePlan = new SupportedRatePlan();
		
		supportedRatePlan.setRatePlan( ratePlanName );
		
		String query = select().from( supportedRatePlan ).where( op( SupportedRatePlan.Fields.rate_plan ).eq() ).build();
		
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
	
	public ArrayList<SupportedRatePlan> getAvailableRatePlanList() {
				
		ArrayList<SupportedRatePlan> supportedRatePlans = new ArrayList<SupportedRatePlan>();
		
		String query = select().from( new SupportedRatePlan() ).orderBy( SupportedRatePlan.Fields.rate_plan ).build();
		
		ResultSet rs = this.getMysql().execQuery( query );
			
		try {
			
			while( rs.next() ) {
				
				SupportedRatePlan supportedRatePlan = new SupportedRatePlan( rs );
								
				supportedRatePlans.add( supportedRatePlan );
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return supportedRatePlans;
		
	}
	
}
