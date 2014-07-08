package com.lumata.e4o.dao.tenant;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lumata.common.testing.database.Mysql;
import com.lumata.e4o.schema.tenant.Conf;

public class DAOConf extends DAO {
	
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
	
}
