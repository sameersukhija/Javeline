package com.lumata.e4o.dao.tenant;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.database.MysqlUtils;
import com.lumata.common.testing.exceptions.DataBaseException;
import com.lumata.e4o.schema.tenant.SetHobbies;
import com.lumata.e4o.system.cdr.CDR;

public class DAOSetHobbies extends DAO {

	private static final Logger logger = LoggerFactory.getLogger( DAOSetHobbies.class );
		
	public DAOSetHobbies( Mysql mysql ) {
		super( mysql );
	}
	
	public static DAOSetHobbies getInstance( Mysql mysql ) {
		return new DAOSetHobbies( mysql );
	}
	
	public Boolean isHobby( String hobbyName ) {
		
		SetHobbies hobbies = new SetHobbies();
		
		hobbies.setHobbiesName( hobbyName );
		
		String query = select().from( hobbies ).where( op( SetHobbies.Fields.hobbies_name ).eq() ).build();
		
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

	public SetHobbies getHobby( String hobbyName ) {
		
		SetHobbies hobbies = new SetHobbies();
		
		hobbies.setHobbiesName( hobbyName );
		
		String query = select().from( hobbies ).where( op( SetHobbies.Fields.hobbies_name ).eq() ).build();
		
		ResultSet rs = this.getMysql().execQuery( query );
	
		SetHobbies hobby = null;
		
		try {
			
			while( rs.next() ) {
				
				hobby = new SetHobbies( rs );
			
				break;
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return hobby;
		
	}
		
	public ArrayList<SetHobbies> getHobbiesList() {
		
		String query = select().from( new SetHobbies() ).build();
		
		ResultSet rs = this.getMysql().execQuery( query );
		
		ArrayList<SetHobbies> hobbyList = new ArrayList<SetHobbies>();
		
		try {
			
			while( rs.next() ) {
				
				SetHobbies hobby = new SetHobbies( rs );

				hobbyList.add( hobby );
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return hobbyList;
		
	}
	
	public void insertDefaultHobbies() {
		
		List<String> hobbies = new ArrayList<String>();
				
		for( CDR.HOBBIES hobby : CDR.HOBBIES.values() ) {
			
			hobbies.add( hobby.name() );
			
		}
		
		insertHobbies( hobbies );
		
	}
	
	public void insertHobbies( List<String> hobbies ) {
		
		SetHobbies hobbyRow = new SetHobbies();
		
		long hobbyId = 1;
		
		try {
			
			hobbyId = (long)(MysqlUtils.getMaxID( "set_hobbies", SetHobbies.Fields.hobbies.name(), this.getMysql() ) + 1 );
		
		} catch (SQLException e ) {
			
			logger.warn( e.getMessage() );
			
		}
				
		for( String hobby : hobbies ) {
			
			if( !isHobby( hobby ) ) {
				
				hobbyRow.setHobbies( hobbyId );
				
				hobbyRow.setHobbiesName( hobby );
				
				String query = insert( hobbyRow ).values().build();
			
				try {
				
					this.getMysql().execUpdate( query );
				
				} catch( DataBaseException e ) {
					
					logger.warn( e.getMessage() );
					
				}	
				
				hobbyId++;
			
			}
					
		}
		
	}

}
