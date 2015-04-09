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
	
	private Integer setTokens( String query ) {
		
		return this.getMysql().execUpdate( query );
		
	}
	
	private ArrayList<CatalogOffers> getOfferList( String query ) {
		
		ResultSet rs = this.getMysql().execQuery( query );
		
		ArrayList<CatalogOffers> offerList = new ArrayList<CatalogOffers>();
		
		try {
			
			while( rs.next() ) {
				
				CatalogOffers offer = new CatalogOffers( rs );

				offerList.add( offer );
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return offerList;
		
	}
	
	public Integer setTokenExpired( Token token, Calendar eventDate, Calendar expirationDate ) {
		
		token.setEventDate( eventDate.getTime() );
		
		token.setExpirationDate( expirationDate.getTime() );
				
		String query = update( token ).
						set( 
							op( Token.Fields.event_date ).eq(), 
							op( Token.Fields.expiration_date ).eq() 
						).
						where( 
							op( Token.Fields.token_code ).eq( token.getTokenCode() ) 
						).
						build();
		
		System.out.println( query );
		
		return 0;
		
	}
	
	@SuppressWarnings("all")
	public ArrayList<Token> convert(ArrayList<Object> a) {
	   return (ArrayList) a;
	}
	
	public ArrayList<Token> getAllTokens() {
		
		String query = select().from( new Token() ).build();
		
		logger.debug( Log.CREATING.createMessage( query ) );
		
		return getTokenList( query );
		
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
	
	public ArrayList<Token> getAvailableActiveTokensByEventDate( Long msisdn, Calendar event_date ) {
		
		String query = select().
						from( new Token() ).
						where( 
								op( Token.Fields.msisdn ).eq( msisdn ), 
								and(
									op( Token.Fields.last_redeem_date ).is( NULL ),
									op( Token.Fields.consumed_date ).is( NULL ),
									op( Token.Fields.expiration_date ).get( sdf.format( Calendar.getInstance().getTime() ) ),
									op( Token.Fields.event_date ).like( sdf.format( event_date.getTime() ) )
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
									or( op( Token.Fields.qty_current_redeems ).let( Token.Fields.qty_max_redeems ) ),
									or( op( Token.Fields.qty_max_redeems ).eq( -1 )	)										
								)
						).build();
		
		logger.info( Log.CREATING.createMessage( query ) );
		
		return getTokenList( query );
		
	}
	
	public ArrayList<Token> getAvailableAllocatedActiveTokensAndOffers( Long msisdn ) {
		
		String query = select().
						from( new Token() ).
						where( 
								op( Token.Fields.msisdn ).eq( msisdn ), 
								and(
									op( Token.Fields.last_redeem_date ).is_not( NULL ),
									op( Token.Fields.consumed_date ).is( NULL ),
									op( Token.Fields.expiration_date ).get( sdf.format( Calendar.getInstance().getTime() ) ),
									op( Token.Fields.has_offers_associated ).neq( 0 ) 
								),
								and(
									or( op( Token.Fields.qty_current_redeems ).let( Token.Fields.qty_max_redeems ) ),
									or( op( Token.Fields.qty_max_redeems ).eq( -1 )	)								
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
									op( Token.Fields.expiration_date ).get( sdf.format( Calendar.getInstance().getTime() ) ),
									op( Token.Fields.consumed_notes ).eq( "Accept" )
								)
						).build();
		
		logger.info( Log.CREATING.createMessage( query ) );
		
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
									op( Token.Fields.expiration_date ).get( sdf.format( Calendar.getInstance().getTime() ) ),
									op( Token.Fields.consumed_notes ).eq( "Accept" )
								)
						).build();
		
		logger.info( Log.CREATING.createMessage( query ) );
		
		return getTokenList( query );
		
	}
	
	public ArrayList<Token> getRefusedTokens( Long msisdn ) {
		
		String query = select().
						from( new Token() ).
						where( 
								op( Token.Fields.msisdn ).eq( msisdn ), 
								and(
									op( Token.Fields.last_redeem_date ).is_not( NULL ),
									op( Token.Fields.consumed_date ).is_not( NULL ),
									op( Token.Fields.consumed_notes ).eq( "Refuse" )
								)
						).build();
		
		logger.info( Log.CREATING.createMessage( query ) );
		
		return getTokenList( query );
		
	}

	public ArrayList<Token> getExpiredTokens() {
		
		String query = select().
						from( new Token() ).
						where( 
							op( Token.Fields.expiration_date ).let( sdf.format( Calendar.getInstance().getTime() ) )								
						).build();
		
		logger.info( Log.CREATING.createMessage( query ) );
		
		return getTokenList( query );
		
	}

	public ArrayList<Token> getExpiredTokens( Long msisdn ) {
		
		String query = select().
						from( new Token() ).
						where( 
								op( Token.Fields.msisdn ).eq( msisdn ), 
								and(
										op( Token.Fields.expiration_date ).let( sdf.format( Calendar.getInstance().getTime() ) )
								)
						).build();
		
		logger.info( Log.CREATING.createMessage( query ) );
		
		return getTokenList( query );
		
	}
	
	public ArrayList<CatalogOffers> getAssociatedOffers( Long msisdn, String token ) {
		
		String query = select().
						from( new CatalogOffers() ).
						where(
							op( CatalogOffers.Fields.offer_id ).in(	
								select( OffoptimCustomerItems.Fields.offer_id ).			
								from( new Token() ).
								join( new OffoptimCustomerPack() ).
								on( 
									op( Token.Fields.token_code ).eq( OffoptimCustomerPack.Fields.token_code ) 
								).
								join( new OffoptimCustomerItems() ).
								on( 
									op( OffoptimCustomerPack.Fields.customer_offer_pack_id ).eq( OffoptimCustomerItems.Fields.customer_offer_pack_id ) 
								).
								where(  
									op( Token.Fields.msisdn ).eq( msisdn ),
									and(
										op( Token.Fields.token_code ).eq( token )	
									)
								).
								statement()
							)
						).build();
		
		logger.info( Log.LOADING.createMessage( query ) );
		
		return getOfferList( query );
		
	}

	public void setTokenDates( Token token ) {
		
		String query = update( token ).
						set().
						where(
							op( Token.Fields.token_code ).eq()	
						).
						build();
		
		this.getMysql().execUpdate( query );
	
		logger.info( Log.UPDATING.createMessage( query ) );
		
	}
	
	public void changeDateExpiredTokenRamdomly() throws ParseException {
		
		ArrayList<Token> tokens = getExpiredTokens();
		
		Collections.shuffle( tokens );
			
		int tokensToActivate = 1 + (int)( Math.random() * ( tokens.size() - 1 ) );
		
		Object[] tokenIds = new Object[ tokensToActivate ];
		
		for( int t = 0; t < tokensToActivate; t++ ) {
			
			tokenIds[ t ] = tokens.get( t ).getTokenCode();
						
		}
 		
		Calendar today = Calendar.getInstance();
		
		today.add( Calendar.DATE, 30 );
		
		String newExpirationDate = Format.getMysqlDateTime( today );
		
		String query = update( new Token() ).
						set( 
							op( Token.Fields.expiration_date ).eq( newExpirationDate ) 
						).
						where( 
							op( Token.Fields.token_code  ).in( tokenIds ) 
						).
						build();
		
		setTokens( query );
		
	}
	
}
