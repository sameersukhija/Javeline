package com.lumata.e4o.dao.tenant;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lumata.common.testing.database.Mysql;
import com.lumata.e4o.schema.tenant.Conf;
import com.lumata.e4o.system.configuration.ConfProperties;

public class DAOConf extends DAO {
	
	private ConfProperties properties;
	
	public enum ConfTag {
		in_tags_list, language
	}

	public DAOConf( Mysql mysql ) {
		super( mysql );
	}
	
	public static DAOConf getInstance( Mysql mysql ) {
		return new DAOConf( mysql );
	}

	public ArrayList<Conf> getCurrentValueByName( ConfTag confTag ) {
				
		ArrayList<Conf> confValueList = new ArrayList<Conf>();
		
		String query = select().from( new Conf() ).where( op( Conf.Fields.name ).eq( confTag.name() ) ).orderBy( Conf.Fields.position ).build();
		
		ResultSet rs = this.getMysql().execQuery( query );
			
		try {
			
			while( rs.next() ) {
				
				Conf confRow = new Conf( rs );
								
				confValueList.add( confRow );
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return confValueList;
		
	}
	
	public DAOConf standard_retry() {
		
		this.properties = ConfProperties.
							getInstance().
							waiting_time( 0, null ).
							sql_attempt_clause( 0, "(>0) {1  MINUTE},(>2) {2 MINUTE}" );
						
		return this;
		
	}
	
	public DAOConf qa_unknown_msisdn( List<Long> msisdnList ) {
		
		this.properties = ConfProperties.getInstance();
		
		for( int m = 0; m < msisdnList.size(); m++ ) {
			
			this.properties.qa_unknown_msisdn( m, String.valueOf( msisdnList.get( m ) ) );
			
		}
								
		return this;
		
	}
	
	public void apply() {
		
		for( Conf conf : this.properties.list() ) {
			
			String query = insert( conf ).values().on_duplicate_key_update( op( Conf.Fields.position ).eq(), op( Conf.Fields.current ).eq() ).build();
			
			System.out.println( query );
			
			if( null != this.getMysql() ) { this.getMysql().execUpdate( query ); }
			
		}
		
	}
		
}
