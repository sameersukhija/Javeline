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
import com.lumata.e4o.schema.tenant.SetOptions;

public class DAOSetOptions extends DAO {

	private static final Logger logger = LoggerFactory.getLogger( DAOSetOptions.class );
		
	public DAOSetOptions( Mysql mysql ) {
		super( mysql );
	}
	
	public static DAOSetOptions getInstance( Mysql mysql ) {
		return new DAOSetOptions( mysql );
	}
	
	public Boolean isOption( String optionName ) {
		
		SetOptions options = new SetOptions();
		
		options.setOptionsName( optionName );
		
		String query = select().from( options ).where( op( SetOptions.Fields.options_name ).eq() ).build();
		
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

	public SetOptions getOption( String optionName ) {
		
		SetOptions options = new SetOptions();
		
		options.setOptionsName( optionName );
		
		String query = select().from( options ).where( op( SetOptions.Fields.options_name ).eq() ).build();
		
		ResultSet rs = this.getMysql().execQuery( query );
	
		SetOptions option = null;
		
		try {
			
			while( rs.next() ) {
				
				option = new SetOptions( rs );
			
				break;
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return option;
		
	}
		
	public ArrayList<SetOptions> getOptionsList() {
		
		String query = select().from( new SetOptions() ).build();
		
		ResultSet rs = this.getMysql().execQuery( query );
		
		ArrayList<SetOptions> optionList = new ArrayList<SetOptions>();
		
		try {
			
			while( rs.next() ) {
				
				SetOptions option = new SetOptions( rs );

				optionList.add( option );
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return optionList;
		
	}
	
	public void insertOptions( Enum<?>... options ) {
		
		SetOptions optionRow = new SetOptions();
		
		long optionId = 1;
		
		try {
			
			optionId = (long)(MysqlUtils.getMaxID( "set_options", SetOptions.Fields.options.name(), this.getMysql() ) + 1 );
		
		} catch (SQLException e ) {
			
			logger.warn( e.getMessage() );
			
		}
				
		for( Enum<?> option : options ) {
			
			if( !isOption( option.name() ) ) {
				
				optionRow.setOptions( optionId );
				
				optionRow.setOptionsName( option.name() );
				
				String query = insert( optionRow ).values().build();
			
				try {
				
					this.getMysql().execUpdate( query );
				
				} catch( DataBaseException e ) {
					
					logger.warn( e.getMessage() );
					
				}	
				
				optionId++;
			
			}
					
		}
		
	}

}
