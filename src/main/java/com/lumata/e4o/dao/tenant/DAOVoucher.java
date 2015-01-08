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
import com.lumata.e4o.schema.tenant.OfferStock;
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
	
	public Integer getValidVouchers( Short offer_id, Integer hoursInterval ) throws SQLException {
		
//		String query = select( 
//							sum( "case when expiryDate > date_add( now(), INTERVAL " + hoursInterval + " HOUR ) and purchase_id is null then 1 else 0 end" ) 
//						).
//						from( new VoucherCodes() ).
//						build();
		
		Integer validVouchers = null;
		
		String query = "select sum( case when expiryDate > date_add( now(), INTERVAL " + hoursInterval + " HOUR ) and purchase_id is null then 1 else 0 end) as valid from voucher_codes where offer_id = " + offer_id + ";";
		
		ResultSet rs = this.getMysql().execQuery( query );
		
		while( rs.next() ) {
			
			validVouchers = rs.getInt( "valid" );
			
		}
		
		return validVouchers;
		
	}
	
	public String getVoucherStatusTable( short offer_id, Integer validVouchers, ArrayList<OfferStock> offerStock ) throws SQLException {
		
		StringBuilder voucherStatusTable = new StringBuilder();
				
		Long remainingStock = 0L;
		
		StringBuilder offerStockTable = new StringBuilder();
		
		for( OfferStock os : offerStock ) {
			
			Long remainingStockSingleChannel = os.getInitialStock() - os.getPurchased() - os.getExpired();
			
			remainingStock = remainingStock + remainingStockSingleChannel;
			
			offerStockTable.
				append( "channel_id (" ).append( os.getChannelId() ).append( ") - " ). 
				append( "remaining (" ).append( remainingStockSingleChannel ).append( ") - " ).
				append( "initial_stock (" ).append( os.getInitialStock() ).append( ") - " ).
				append( "available (" ).append( os.getAvailable() ).append( ") - " ).
				append( "purchased (" ).append( os.getPurchased() ).append( ") - " ).
				append( "refused (" ).append( os.getRefused() ).append( ") - " ).
				append( "expired (" ).append( os.getExpired() ).append( ")\n" );
						
		}
			
		Long stockReduction = remainingStock - validVouchers;
		
		Long unreservedStockReduction = Math.min( offerStock.get( 0 ).getAvailable(), stockReduction );
		
		voucherStatusTable.
			append( "offer_id (" ).append( offer_id ).append( ")\n" ).
			append( "validVouchers (" ).append( validVouchers ).append( ")\n" ).
			append( "remainingStock (" ).append( remainingStock ).append( ")\n" ).
			append( "stockReduction (" ).append( stockReduction ).append( ")\n" ).
			append( "unreservedStockReduction (" ).append( unreservedStockReduction ).append( ")\n" ).
			append( offerStockTable );
		
		return voucherStatusTable.toString();
				
	}
	
	public ArrayList<OfferStock> calculateReduction( Integer validVouchers, ArrayList<OfferStock> offerStock ) {
		
		/* US EFOGC-2876
		 * step 1 ( init )
		 * remainingStock = available - allocated = initial_stock - purchased - expired
		 * validVouchers = ( calculated from the voucher_codes table )
		 * stockReduction = remainingStock - validVouchers
		 * 
		 * step 2 ( reduce from global stock )
		 * unreservedStockReduction = MIN( stockReduction, available( channel 0 ) )
		 * available( channel 0 ) = available( channel 0 ) - unreservedStockReduction 
		 * expired( channel 0 ) = available( channel 0 ) + unreservedStockReduction
		 * stockReduction = stockReduction - unreservedStockReduction
		 * 
		 * step 3 ( reduce from channels )
		 * reservedStockReduction = MIN (stockReduction - available(all channels))
		 * proportional decrease per channel with
		 * available (channel i) = available (channel i) - reservedStockReduction * coef
		 * expired (channel i) = expired (channel i) + reservedStockReduction * coef
		 * stockReduction = stockReduction - reservedStockReduction
		 * 
		 * step 4 - deallocate offers (if stockReduction > 0) 
		 * as you say, search for the oldest offer allocated, and mark them as EXPIRED (need to verify the exact process)
		 * report the quantity deallocated in expired column (so that we can run the task again safely with right calculation of remaining stock)
		 * 
		 *  
		 */
		
		ArrayList<OfferStock> newOfferStock = new ArrayList<OfferStock>();
		
		/* step 1 */
		Long remainingStock = 0L;
		
		for( OfferStock os : offerStock ) {
						
			OfferStock newOs = new OfferStock();
			
			newOs.setChannelId( os.getChannelId() );
			newOs.setInitialStock( os.getInitialStock() );
			newOs.setAvailable( os.getAvailable() );
			newOs.setPurchased( os.getPurchased() );
			newOs.setRefused( os.getRefused() );
			newOs.setExpired( os.getExpired() );
			
			Long remainingStockSingleChannel = os.getInitialStock() - os.getPurchased() - os.getExpired();
			
			remainingStock = remainingStock + remainingStockSingleChannel;
			
			newOfferStock.add( newOs );
						
		}
		
		Long stockReduction = remainingStock - validVouchers;
		
		/* step 2 */
		if( stockReduction > 0 ) {
		
			Long unreservedStockReduction = Math.min( newOfferStock.get( 0 ).getAvailable(), stockReduction );
			
			newOfferStock.get( 0 ).setAvailable( newOfferStock.get( 0 ).getAvailable() - unreservedStockReduction );
			
			newOfferStock.get( 0 ).setExpired( newOfferStock.get( 0 ).getExpired() + unreservedStockReduction );
			
			stockReduction = stockReduction - unreservedStockReduction;
			
			/* step 3 */ 
			if( stockReduction > 0 ) {
				
				//ArrayList<Integer> remainingByChannel = new ArrayList<Integer>(); 
				
				Long availableAllChannels = 0L;
									
				for( int stockIndex = 1; stockIndex < newOfferStock.size(); stockIndex++ ) {
					
					availableAllChannels = availableAllChannels + newOfferStock.get( stockIndex ).getAvailable();
					
				}
				
				Long reservedStockReduction = Math.min( stockReduction, availableAllChannels );
								
				for( int stockIndex = 1; stockIndex < newOfferStock.size(); stockIndex++ ) {
									
					double coeff = ( (double)newOfferStock.get( stockIndex ).getAvailable() / availableAllChannels );
					
					newOfferStock.get( stockIndex ).setAvailable( newOfferStock.get( stockIndex ).getAvailable() - (long)Math.round( reservedStockReduction  * coeff ) );
					
					newOfferStock.get( stockIndex ).setExpired( newOfferStock.get( stockIndex ).getExpired() + (long)Math.round( reservedStockReduction  * coeff ) );
					
				}
				
				stockReduction = stockReduction - reservedStockReduction;
						
				if( stockReduction > 0 ) {
					
					Long offerToDeallocate = stockReduction; 
					
				}
				
			}
		
		}
		
		return newOfferStock;
		
	}
	
	
	
	
	
	
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
