package com.lumata.e4o.dao.tenant;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.database.Mysql;
import com.lumata.e4o.schema.tenant.OfferStock;


public class DAOOfferStock extends DAO {

	private static final Logger logger = LoggerFactory.getLogger( DAOOfferStock.class );
	
	SimpleDateFormat sdf;
	
	public DAOOfferStock( Mysql mysql ) {
		super( mysql );
		sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
	}
	
	public static DAOOfferStock getInstance( Mysql mysql ) {
		return new DAOOfferStock( mysql );
	}
	
	private ArrayList<OfferStock> getOfferStock( String query ) {
		
		ResultSet rs = this.getMysql().execQuery( query );
		
		ArrayList<OfferStock> offerStock = new ArrayList<OfferStock>();
		
		try {
			
			while( rs.next() ) {
				
				OfferStock offStock = new OfferStock( rs );
	
				offerStock.add( offStock );
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return offerStock;
		
	}
	
	public ArrayList<OfferStock> getOfferStockByOffer( Short offer_id ) {
		
		String query = select().
						from( new OfferStock() ).
						where( 
								op( OfferStock.Fields.offer_id ).eq( offer_id ) 
						).
						build();
		
		return getOfferStock( query );		
		
	}
	
	
	
	
//	public Boolean isVoucher( String voucherCode ) {
//		
//		VoucherCodes vouchercode = new VoucherCodes();
//		
//		vouchercode.setCode( voucherCode );
//		
//		String query = select().from( vouchercode ).where( op( VoucherCodes.Fields.code ).eq() ).build();
//		
//		ResultSet rs = this.getMysql().execQuery( query );
//	
//		boolean found = false;
//		
//		try {
//			
//			while( rs.next() ) {
//				
//				found = true;
//				
//			}
//			
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		
//		}
//		
//		return found;
//		
//	}
//
//	private VoucherCodes getVoucher( String query ) {
//		
//		ResultSet rs = this.getMysql().execQuery( query );
//	
//		VoucherCodes voucherCode = null;
//		
//		try {
//			
//			while( rs.next() ) {
//				
//				voucherCode = new VoucherCodes( rs );
//			
//				break;
//				
//			}
//			
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		
//		}
//		
//		return voucherCode;
//		
//	}
//	
//	public VoucherCodes getAvailableVoucher() {
//		
//		String query = select().from( new VoucherCodes() ).orderBy( VoucherCodes.Fields.code ).limit( 1 ).build();
//		
//		return getVoucher( query );
//		
//	}
//		
//	private ArrayList<VoucherCodes> getVoucherList( String query ) {
//		
//		ResultSet rs = this.getMysql().execQuery( query );
//		
//		ArrayList<VoucherCodes> voucherCodeList = new ArrayList<VoucherCodes>();
//		
//		try {
//			
//			while( rs.next() ) {
//				
//				VoucherCodes token = new VoucherCodes( rs );
//
//				voucherCodeList.add( token );
//				
//			}
//			
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		
//		}
//		
//		return voucherCodeList;
//		
//	}
//	
//	public ArrayList<VoucherCodes> getPurchasedVoucher() {
//		
//		String query = select().
//						from( new VoucherCodes() ).
//						join( new StatsPurchase() ).
//						on( 
//							op( VoucherCodes.Fields.purchase_id ).
//							eq( StatsPurchase.Fields.purchase_id ) 
//						).
//						where( op( VoucherCodes.Fields.redeemed_date ).is( NULL ) ).
//						orderBy( VoucherCodes.Fields.code ).
//						build();
//		
//		return getVoucherList( query );
//		
//	}
//	
//	public ArrayList<VoucherCodes> getPurchasedVoucherOnDate( Calendar date ) {
//		
//		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
//		
//		String query = select().
//						from( new VoucherCodes() ).
//						join( new StatsPurchase() ).
//						on( 
//							op( VoucherCodes.Fields.purchase_id ).
//							eq( StatsPurchase.Fields.purchase_id ) 
//						).
//						where(
//							op( StatsPurchase.Fields.agg_date_time ).like( sdf.format( date.getTime() ) ),
//							and( op( VoucherCodes.Fields.redeemed_date ).is( NULL ) )
//						).
//						orderBy( VoucherCodes.Fields.code ).
//						build();
//		
//		return getVoucherList( query );
//		
//	}
//	
//	public ArrayList<VoucherCodes> getRedeemedVoucherOnDate( Calendar date) {
//		
//		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
//		
//		String query = select().
//						from( new VoucherCodes() ).
//						where(
//							op( VoucherCodes.Fields.redeemed_date ).like( sdf.format( date.getTime() ) )
//						).
//						orderBy( VoucherCodes.Fields.code ).
//						build();
//		
//		return getVoucherList( query );
//		
//	}
//	
//	public Integer getValidVouchers( Short offer_id, Integer hoursInterval ) throws SQLException {
//		
////		String query = select( 
////							sum( "case when expiryDate > date_add( now(), INTERVAL " + hoursInterval + " HOUR ) and purchase_id is null then 1 else 0 end" ) 
////						).
////						from( new VoucherCodes() ).
////						build();
//		
//		Integer validVouchers = null;
//		
//		String query = "select sum( case when expiryDate > date_add( now(), INTERVAL " + hoursInterval + " HOUR ) and purchase_id is null then 1 else 0 end) as valid from voucher_codes where offer_id = " + offer_id;
//		
//		System.out.println( query );
//		
//		ResultSet rs = this.getMysql().execQuery( query );
//		
//		while( rs.next() ) {
//			
//			validVouchers = rs.getInt( "valid" );
//			
//		}
//		
//		return validVouchers;
//		
//	}
	

	
}
