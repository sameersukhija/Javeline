package com.lumata.e4o.dao.tenant;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.database.MysqlUtils;
import com.lumata.common.testing.exceptions.DataBaseException;
import com.lumata.e4o.schema.tenant.Setoptions;

public class DAOSetHobbies extends DAO {

	private static final Logger logger = LoggerFactory.getLogger( DAOSetHobbies.class );
		
	public DAOSetHobbies( Mysql mysql ) {
		super( mysql );
	}
	
	public static DAOSetHobbies getInstance( Mysql mysql ) {
		return new DAOSetHobbies( mysql );
	}
	
	public Boolean isHobby( String hobbyName ) {
		
		Setoptions hobbies = new Setoptions();
		
		hobbies.setHobbiesName( hobbyName );
		
		String query = select().from( hobbies ).where( op( Setoptions.Fields.hobbies_name ).eq() ).build();
		
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

	public Setoptions getHobby( String hobbyName ) {
		
		Setoptions hobbies = new Setoptions();
		
		hobbies.setHobbiesName( hobbyName );
		
		String query = select().from( hobbies ).where( op( Setoptions.Fields.hobbies_name ).eq() ).build();
		
		ResultSet rs = this.getMysql().execQuery( query );
	
		Setoptions hobby = null;
		
		try {
			
			while( rs.next() ) {
				
				hobby = new Setoptions( rs );
			
				break;
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return hobby;
		
	}
		
	public ArrayList<Setoptions> getHobbiesList() {
		
		String query = select().from( new Setoptions() ).build();
		
		ResultSet rs = this.getMysql().execQuery( query );
		
		ArrayList<Setoptions> hobbyList = new ArrayList<Setoptions>();
		
		try {
			
			while( rs.next() ) {
				
				Setoptions hobby = new Setoptions( rs );

				hobbyList.add( hobby );
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return hobbyList;
		
	}
	
	public void insertHobbies( Enum<?>... hobbies ) {
		
		Setoptions hobbyRow = new Setoptions();
		
		long hobbyId = 1;
		
		try {
			
			hobbyId = (long)(MysqlUtils.getMaxID( "set_hobbies", Setoptions.Fields.hobbies.name(), this.getMysql() ) + 1 );
		
		} catch (SQLException e ) {
			
			logger.warn( e.getMessage() );
			
		}
				
		for( Enum<?> hobby : hobbies ) {
			
			if( !isHobby( hobby.name() ) ) {
				
				hobbyRow.setHobbies( hobbyId );
				
				hobbyRow.setHobbiesName( hobby.name() );
				
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
