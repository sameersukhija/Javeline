package com.lumata.e4o.dao.tenant;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;
import static com.lumata.common.testing.orm.Val.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.log.Log;
import com.lumata.e4o.schema.tenant.Token;

public class DAOToken extends DAO {

	private static final Logger logger = LoggerFactory.getLogger( DAOToken.class );
	
	SimpleDateFormat sdf;
	
	public DAOToken( Mysql mysql ) {
		super( mysql );
		sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
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

	private Token getToken( String query ) {
		
		ResultSet rs = this.getMysql().execQuery( query );
	
		Token token = null;
		
		try {
			
			while( rs.next() ) {
				
				token = new Token( rs );
			
				break;
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return token;
		
	}
	public Token getAvailableToken() {
		
		String query = select().from( new Token() ).orderBy( Token.Fields.token_code ).limit( 1 ).build();
		
		return getToken( query );
		
	}
		
	private ArrayList<Token> getTokenList( String query ) {
		
		ResultSet rs = this.getMysql().execQuery( query );
		
		ArrayList<Token> tokenList = new ArrayList<Token>();
		
		try {
			
			while( rs.next() ) {
				
				Token token = new Token( rs );

				tokenList.add( token );
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return tokenList;
		
	}
	
	@SuppressWarnings("all")
	public ArrayList<Token> convert(ArrayList<Object> a) {
	   return (ArrayList) a;
	}
	
	public ArrayList<Token> getAvailableTokens( Long msisdn ) {
		
		String query = select().from( new Token() ).where( op( Token.Fields.msisdn ).eq( msisdn ) ).build();
		
		logger.debug( Log.CREATING.createMessage( query ) );
		
		return getTokenList( query );
		
	}
	
	public ArrayList<Token> getAvailableActiveTokens( Long msisdn ) {
		
		String query = select().
						from( new Token() ).
						where( 
								op( Token.Fields.msisdn ).eq( msisdn ), 
								and(
									op( Token.Fields.last_redeem_date ).is( NULL ),
									op( Token.Fields.consumed_date ).is( NULL ),
									op( Token.Fields.expiration_date ).get( sdf.format( Calendar.getInstance().getTime() ) )
								)
						).build();
		
		logger.debug( Log.CREATING.createMessage( query ) );
		
		return getTokenList( query );
		
	}

	public ArrayList<Token> getAvailableAllocatedTokens( Long msisdn ) {
		
		String query = select().
						from( new Token() ).
						where( 
								op( Token.Fields.msisdn ).eq( msisdn ), 
								and(
									op( Token.Fields.last_redeem_date ).is_not( NULL ),
									op( Token.Fields.consumed_date ).is( NULL ),
									op( Token.Fields.expiration_date ).get( sdf.format( Calendar.getInstance().getTime() ) )
								)
						).build();
		
		logger.debug( Log.CREATING.createMessage( query ) );
		
		return getTokenList( query );
		
	}
	
	public ArrayList<Token> getAvailableAllocatedActiveTokens( Long msisdn ) {
		
		String query = select().
						from( new Token() ).
						where( 
								op( Token.Fields.msisdn ).eq( msisdn ), 
								and(
									op( Token.Fields.last_redeem_date ).is_not( NULL ),
									op( Token.Fields.consumed_date ).is( NULL ),
									op( Token.Fields.expiration_date ).get( sdf.format( Calendar.getInstance().getTime() ) )
								),
								and(
									op( Token.Fields.qty_current_redeems ).let( Token.Fields.qty_max_redeems )										
								)
						).build();
		
		logger.info( Log.CREATING.createMessage( query ) );
		
		return getTokenList( query );
		
	}
	
	public ArrayList<Token> getAvailableAcceptedTokens( Long msisdn ) {
		
		String query = select().
						from( new Token() ).
						where( 
								op( Token.Fields.msisdn ).eq( msisdn ), 
								and(
									op( Token.Fields.last_redeem_date ).is_not( NULL ),
									op( Token.Fields.consumed_date ).is_not( NULL ),
									op( Token.Fields.expiration_date ).get( sdf.format( Calendar.getInstance().getTime() ) )
								)
						).build();
		
		logger.debug( Log.CREATING.createMessage( query ) );
		
		return getTokenList( query );
		
	}
	
	public ArrayList<Token> getAvailableAcceptedActiveTokens( Long msisdn ) {
		
		String query = select().
						from( new Token() ).
						where( 
								op( Token.Fields.msisdn ).eq( msisdn ), 
								and(
									op( Token.Fields.last_redeem_date ).is_not( NULL ),
									op( Token.Fields.consumed_date ).is_not( NULL ),
									op( Token.Fields.expiration_date ).get( sdf.format( Calendar.getInstance().getTime() ) )
								)
						).build();
		
		logger.debug( Log.CREATING.createMessage( query ) );
		
		return getTokenList( query );
		
	}
	
}
