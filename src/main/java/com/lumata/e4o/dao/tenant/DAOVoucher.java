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
import com.lumata.e4o.schema.tenant.CatalogOffers;
import com.lumata.e4o.schema.tenant.OffoptimCustomerItems;
import com.lumata.e4o.schema.tenant.OffoptimCustomerPack;
import com.lumata.e4o.schema.tenant.StatsPurchase;
import com.lumata.e4o.schema.tenant.VoucherCodes;

public class DAOVoucher extends DAO {

	private static final Logger logger = LoggerFactory.getLogger( DAOVoucher.class );
	
	SimpleDateFormat sdf;
	
	public DAOVoucher( Mysql mysql ) {
		super( mysql );
		sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
	}
	
	public static DAOVoucher getInstance( Mysql mysql ) {
		return new DAOVoucher( mysql );
	}
	
	public Boolean isVoucher( String voucherCode ) {
		
		VoucherCodes vouchercode = new VoucherCodes();
		
		vouchercode.setCode( voucherCode );
		
		String query = select().from( vouchercode ).where( op( VoucherCodes.Fields.code ).eq() ).build();
		
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

	private VoucherCodes getVoucher( String query ) {
		
		ResultSet rs = this.getMysql().execQuery( query );
	
		VoucherCodes voucherCode = null;
		
		try {
			
			while( rs.next() ) {
				
				voucherCode = new VoucherCodes( rs );
			
				break;
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return voucherCode;
		
	}
	
	public VoucherCodes getAvailableVoucher() {
		
		String query = select().from( new VoucherCodes() ).orderBy( VoucherCodes.Fields.code ).limit( 1 ).build();
		
		return getVoucher( query );
		
	}
		
	private ArrayList<VoucherCodes> getVoucherList( String query ) {
		
		ResultSet rs = this.getMysql().execQuery( query );
		
		ArrayList<VoucherCodes> voucherCodeList = new ArrayList<VoucherCodes>();
		
		try {
			
			while( rs.next() ) {
				
				VoucherCodes token = new VoucherCodes( rs );

				voucherCodeList.add( token );
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return voucherCodeList;
		
	}
	
	public ArrayList<VoucherCodes> getPurchasedVoucher() {
		
		String query = select().
						from( new VoucherCodes() ).
						join( new StatsPurchase() ).
						on( 
							op( VoucherCodes.Fields.purchase_id ).
							eq( StatsPurchase.Fields.purchase_id ) 
						).
						where( op( VoucherCodes.Fields.redeemed_date ).is( NULL ) ).
						orderBy( VoucherCodes.Fields.code ).
						build();
		
		return getVoucherList( query );
		
	}
	
	public ArrayList<VoucherCodes> getPurchasedVoucherOnDate( Calendar date ) {
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		
		String query = select().
						from( new VoucherCodes() ).
						join( new StatsPurchase() ).
						on( 
							op( VoucherCodes.Fields.purchase_id ).
							eq( StatsPurchase.Fields.purchase_id ) 
						).
						where(
							op( StatsPurchase.Fields.agg_date_time ).like( sdf.format( date.getTime() ) ),
							and( op( VoucherCodes.Fields.redeemed_date ).is( NULL ) )
						).
						orderBy( VoucherCodes.Fields.code ).
						build();
		
		return getVoucherList( query );
		
	}
	
	public ArrayList<VoucherCodes> getRedeemedVoucherOnDate( Calendar date) {
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		
		String query = select().
						from( new VoucherCodes() ).
						where(
							op( VoucherCodes.Fields.redeemed_date ).like( sdf.format( date.getTime() ) )
						).
						orderBy( VoucherCodes.Fields.code ).
						build();
		
		return getVoucherList( query );
		
	}
	
	/*
	 * select msisdn, count(code) from stats_purchase sp join voucher_codes vc on sp.purchase_id = vc.purchase_id and sp.offer_id = 3 and ( vc.expiryDate is null || vc.expiryDate >= NOW() ) and vc.redeemed_date is null group by msisdn;

	 */
	

	
	
	
	
	
	
	
	
	
	
	
//	private ArrayList<CatalogOffers> getOfferList( String query ) {
//		
//		ResultSet rs = this.getMysql().execQuery( query );
//		
//		ArrayList<CatalogOffers> offerList = new ArrayList<CatalogOffers>();
//		
//		try {
//			
//			while( rs.next() ) {
//				
//				CatalogOffers offer = new CatalogOffers( rs );
//
//				offerList.add( offer );
//				
//			}
//			
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		
//		}
//		
//		return offerList;
//		
//	}
//	
//	@SuppressWarnings("all")
//	public ArrayList<VoucherCodes> convert(ArrayList<Object> a) {
//	   return (ArrayList) a;
//	}
//	
//	public ArrayList<VoucherCodes> getAvailableTokens( Long msisdn ) {
//		
//		String query = select().from( new VoucherCodes() ).where( op( VoucherCodes.Fields.msisdn ).eq( msisdn ) ).build();
//		
//		logger.debug( Log.CREATING.createMessage( query ) );
//		
//		return getVoucherList( query );
//		
//	}
//	
//	public ArrayList<VoucherCodes> getAvailableActiveTokens( Long msisdn ) {
//		
//		String query = select().
//						from( new VoucherCodes() ).
//						where( 
//								op( VoucherCodes.Fields.msisdn ).eq( msisdn ), 
//								and(
//									op( VoucherCodes.Fields.last_redeem_date ).is( NULL ),
//									op( VoucherCodes.Fields.consumed_date ).is( NULL ),
//									op( VoucherCodes.Fields.expiration_date ).get( sdf.format( Calendar.getInstance().getTime() ) )
//								)
//						).build();
//		
//		logger.debug( Log.CREATING.createMessage( query ) );
//		
//		return getVoucherList( query );
//		
//	}
//
//	public ArrayList<VoucherCodes> getAvailableAllocatedTokens( Long msisdn ) {
//		
//		String query = select().
//						from( new VoucherCodes() ).
//						where( 
//								op( VoucherCodes.Fields.msisdn ).eq( msisdn ), 
//								and(
//									op( VoucherCodes.Fields.last_redeem_date ).is_not( NULL ),
//									op( VoucherCodes.Fields.consumed_date ).is( NULL ),
//									op( VoucherCodes.Fields.expiration_date ).get( sdf.format( Calendar.getInstance().getTime() ) )
//								)
//						).build();
//		
//		logger.debug( Log.CREATING.createMessage( query ) );
//		
//		return getVoucherList( query );
//		
//	}
//	
//	public ArrayList<VoucherCodes> getAvailableAllocatedActiveTokens( Long msisdn ) {
//		
//		String query = select().
//						from( new VoucherCodes() ).
//						where( 
//								op( VoucherCodes.Fields.msisdn ).eq( msisdn ), 
//								and(
//									op( VoucherCodes.Fields.last_redeem_date ).is_not( NULL ),
//									op( VoucherCodes.Fields.consumed_date ).is( NULL ),
//									op( VoucherCodes.Fields.expiration_date ).get( sdf.format( Calendar.getInstance().getTime() ) )
//								),
//								and(
//									or( op( VoucherCodes.Fields.qty_current_redeems ).let( VoucherCodes.Fields.qty_max_redeems ) ),
//									or( op( VoucherCodes.Fields.qty_max_redeems ).eq( -1 )	)										
//								)
//						).build();
//		
//		logger.info( Log.CREATING.createMessage( query ) );
//		
//		return getVoucherList( query );
//		
//	}
//	
//	public ArrayList<VoucherCodes> getAvailableAllocatedActiveTokensAndOffers( Long msisdn ) {
//		
//		String query = select().
//						from( new VoucherCodes() ).
//						where( 
//								op( VoucherCodes.Fields.msisdn ).eq( msisdn ), 
//								and(
//									op( VoucherCodes.Fields.last_redeem_date ).is_not( NULL ),
//									op( VoucherCodes.Fields.consumed_date ).is( NULL ),
//									op( VoucherCodes.Fields.expiration_date ).get( sdf.format( Calendar.getInstance().getTime() ) ),
//									op( VoucherCodes.Fields.has_offers_associated ).neq( 0 ) 
//								),
//								and(
//									or( op( VoucherCodes.Fields.qty_current_redeems ).let( VoucherCodes.Fields.qty_max_redeems ) ),
//									or( op( VoucherCodes.Fields.qty_max_redeems ).eq( -1 )	)								
//								)
//						).build();
//		
//		logger.info( Log.CREATING.createMessage( query ) );
//		
//		return getVoucherList( query );
//		
//	}
//	
//	public ArrayList<VoucherCodes> getAvailableAcceptedTokens( Long msisdn ) {
//		
//		String query = select().
//						from( new VoucherCodes() ).
//						where( 
//								op( VoucherCodes.Fields.msisdn ).eq( msisdn ), 
//								and(
//									op( VoucherCodes.Fields.last_redeem_date ).is_not( NULL ),
//									op( VoucherCodes.Fields.consumed_date ).is_not( NULL ),
//									op( VoucherCodes.Fields.expiration_date ).get( sdf.format( Calendar.getInstance().getTime() ) ),
//									op( VoucherCodes.Fields.consumed_notes ).eq( "Accept" )
//								)
//						).build();
//		
//		logger.info( Log.CREATING.createMessage( query ) );
//		
//		return getVoucherList( query );
//		
//	}
//	
//	public ArrayList<VoucherCodes> getAvailableAcceptedActiveTokens( Long msisdn ) {
//		
//		String query = select().
//						from( new VoucherCodes() ).
//						where( 
//								op( VoucherCodes.Fields.msisdn ).eq( msisdn ), 
//								and(
//									op( VoucherCodes.Fields.last_redeem_date ).is_not( NULL ),
//									op( VoucherCodes.Fields.consumed_date ).is_not( NULL ),
//									op( VoucherCodes.Fields.expiration_date ).get( sdf.format( Calendar.getInstance().getTime() ) ),
//									op( VoucherCodes.Fields.consumed_notes ).eq( "Accept" )
//								)
//						).build();
//		
//		logger.info( Log.CREATING.createMessage( query ) );
//		
//		return getVoucherList( query );
//		
//	}
//	
//	public ArrayList<VoucherCodes> getRefusedTokens( Long msisdn ) {
//		
//		String query = select().
//						from( new VoucherCodes() ).
//						where( 
//								op( VoucherCodes.Fields.msisdn ).eq( msisdn ), 
//								and(
//									op( VoucherCodes.Fields.last_redeem_date ).is_not( NULL ),
//									op( VoucherCodes.Fields.consumed_date ).is_not( NULL ),
//									op( VoucherCodes.Fields.consumed_notes ).eq( "Refuse" )
//								)
//						).build();
//		
//		logger.info( Log.CREATING.createMessage( query ) );
//		
//		return getVoucherList( query );
//		
//	}
//
//	public ArrayList<VoucherCodes> getExpiredTokens( Long msisdn ) {
//		
//		String query = select().
//						from( new VoucherCodes() ).
//						where( 
//								op( VoucherCodes.Fields.msisdn ).eq( msisdn ), 
//								and(
//										op( VoucherCodes.Fields.expiration_date ).let( sdf.format( Calendar.getInstance().getTime() ) )
//								)
//						).build();
//		
//		logger.info( Log.CREATING.createMessage( query ) );
//		
//		return getVoucherList( query );
//		
//	}
//	
//	public ArrayList<CatalogOffers> getAssociatedOffers( Long msisdn, String token ) {
//		
//		String query = select().
//						from( new CatalogOffers() ).
//						where(
//							op( CatalogOffers.Fields.offer_id ).in(	
//								select( OffoptimCustomerItems.Fields.offer_id ).			
//								from( new VoucherCodes() ).
//								join( new OffoptimCustomerPack() ).
//								on( 
//									op( VoucherCodes.Fields.token_code ).eq( OffoptimCustomerPack.Fields.token_code ) 
//								).
//								join( new OffoptimCustomerItems() ).
//								on( 
//									op( OffoptimCustomerPack.Fields.customer_offer_pack_id ).eq( OffoptimCustomerItems.Fields.customer_offer_pack_id ) 
//								).
//								where(  
//									op( VoucherCodes.Fields.msisdn ).eq( msisdn ),
//									and(
//										op( VoucherCodes.Fields.token_code ).eq( token )	
//									)
//								).
//								statement()
//							)
//						).build();
//		
//		logger.info( Log.LOADING.createMessage( query ) );
//		
//		return getOfferList( query );
//		
//	}
//
//	public void setTokenDates( VoucherCodes token ) {
//		
//		String query = update( token ).
//						set().
//						where(
//							op( VoucherCodes.Fields.token_code ).eq()	
//						).
//						build();
//		
//		this.getMysql().execUpdate( query );
//	
//		logger.info( Log.UPDATING.createMessage( query ) );
//		
//	}
	
}
