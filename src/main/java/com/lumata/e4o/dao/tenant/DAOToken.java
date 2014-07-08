package com.lumata.e4o.dao.tenant;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.orm.Val;
import com.lumata.e4o.schema.tenant.Token;

public class DAOToken extends DAO {

	public DAOToken( Mysql mysql ) {
		super( mysql );
	}
	
	public static DAOToken getInstance( Mysql mysql ) {
		return new DAOToken( mysql );
	}
	
	public Boolean isToken( String tokenCode ) {
		
		Token token = new Token();
		
		token.setTokenCode( tokenCode );
		
		String query = select().from( token ).where( op( Token.Fields.token_code ).eq() ).build();
		
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
	
	
	
	
	public Token getAvailableToken() {
				
		String query = select().from( new Token() ).orderBy( Token.Fields.token_code ).limit( 1 ).build();
		
		ResultSet rs = this.getMysql().execQuery( query );
	
		Token token = null;
		
		try {
			
			while( rs.next() ) {
				
				token = new Token( rs );
								
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return token;
		
	}
	
}
