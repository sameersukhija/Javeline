package com.lumata.e4o.dao.tenant;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;
import static com.lumata.common.testing.orm.Val.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.schema.tenant.CatalogOffers;
import com.lumata.e4o.schema.tenant.OffoptimCustomerItems;
import com.lumata.e4o.schema.tenant.OffoptimCustomerPack;
import com.lumata.e4o.schema.tenant.OffoptimRuleset;
import com.lumata.e4o.schema.tenant.Token;
import com.lumata.e4o.schema.tenant.TokenType;

public class DAORuleSet extends DAO {

	private static final Logger logger = LoggerFactory.getLogger( DAORuleSet.class );
	
	SimpleDateFormat sdf;
	
	public DAORuleSet( Mysql mysql ) {
		super( mysql );
		sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
	}
	
	public static DAORuleSet getInstance( Mysql mysql ) {
		return new DAORuleSet( mysql );
	}
	


	private OffoptimRuleset getRuleSet( String query ) {
		
		ResultSet rs = this.getMysql().execQuery( query );
	
		OffoptimRuleset ruleSet = null;
		
		try {
			
			while( rs.next() ) {
				
				ruleSet = new OffoptimRuleset( rs );
			
				break;
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return ruleSet;
		
	}
	
	public OffoptimRuleset getRuleSetByName( String ruleSetName ) {
	
		String query = select().
						from( new OffoptimRuleset() ).
						where( 
							op( OffoptimRuleset.Fields.name ).eq( ruleSetName ) 
						).
						build();
		
		return getRuleSet( query );
		
	}
	
	
	
}
