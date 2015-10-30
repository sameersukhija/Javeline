package com.lumata.e4o.dao.tenant;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;






import java.util.Calendar;

import com.lumata.common.testing.database.Mysql;
import com.lumata.e4o.schema.tenant.CatalogOffers;
import com.lumata.e4o.schema.tenant.OffoptimCustomerItems;
import com.lumata.e4o.schema.tenant.OffoptimCustomerPack;
import com.lumata.e4o.schema.tenant.OffoptimOfferHistory;
import com.lumata.e4o.schema.tenant.SalesChannels;
import com.lumata.e4o.schema.tenant.Token;


public class DAOOffoptimOfferHistory extends DAO {

	SimpleDateFormat sdf;
	
	private enum OfferType {
		
		none,
		oneTimeUse,
		unlimitedUse;
		
	}
	
	public DAOOffoptimOfferHistory( Mysql mysql ) {
		super( mysql );
		sdf = new SimpleDateFormat( "yyyy-MM-dd" );
	}
	
	public static DAOOffoptimOfferHistory getInstance( Mysql mysql ) {
		return new DAOOffoptimOfferHistory( mysql );
	}
	
	private ArrayList<OffoptimOfferHistory> getOffoptimOfferHistoryList( String query ) {
		
		ArrayList<OffoptimOfferHistory> offoptimOfferHistoryList = new ArrayList<OffoptimOfferHistory>();
		
		ResultSet rs = this.getMysql().execQuery( query );
		
		try {
			
			while( rs.next() ) {
				
				OffoptimOfferHistory offoptimOfferHistory = new OffoptimOfferHistory( rs );
		
				offoptimOfferHistoryList.add( offoptimOfferHistory );				
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return offoptimOfferHistoryList;
		
	}
	
	private OffoptimOfferHistory getOffoptimOfferHistory( String query ) {
		
		ResultSet rs = this.getMysql().execQuery( query );
		
		OffoptimOfferHistory offoptimOfferHistory = new OffoptimOfferHistory();
		
		try {
			
			while( rs.next() ) {
				
				offoptimOfferHistory = new OffoptimOfferHistory( rs );
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return offoptimOfferHistory;
		
	}

	public ArrayList<OffoptimOfferHistory> getOffoptimOfferHistory( Short offerId ) {
	
		String query = select().
						from( new OffoptimOfferHistory() ).
						where( op( OffoptimOfferHistory.Fields.offer_id ).eq( offerId ) ).
						build(); 
		
		return getOffoptimOfferHistoryList( query );	
		
	}	
	
	public ArrayList<OffoptimOfferHistory> getOffoptimOfferHistory( Short offerId, Short channelId ) {
		
		String query = select().
						from( new OffoptimOfferHistory() ).
						where( 
							op( OffoptimOfferHistory.Fields.offer_id ).eq( offerId ),
							and( op( OffoptimOfferHistory.Fields.channel_id ).eq( channelId ) )
						).
						build(); 
		
		return getOffoptimOfferHistoryList( query );	
		
	}

	public ArrayList<OffoptimOfferHistory> getOffoptimOfferHistory( Short offerId, Calendar aggDate ) {
		
		String query = select().
						from( new OffoptimOfferHistory() ).
						where( 
							op( OffoptimOfferHistory.Fields.offer_id ).eq( offerId ),
							and( 
								op( OffoptimOfferHistory.Fields.agg_date ).eq( this.sdf.format( aggDate.getTime() ) ) 
							)					
						).
						build(); 
		
		return getOffoptimOfferHistoryList( query );	
		
	}
	
	public OffoptimOfferHistory getOffoptimOfferHistory( Short offerId, Short channelId, Calendar aggDate ) {
		
		String query = select().
						from( new OffoptimOfferHistory() ).
						where( 
							op( OffoptimOfferHistory.Fields.offer_id ).eq( offerId ),
							and( 
								op( OffoptimOfferHistory.Fields.channel_id ).eq( channelId ),
								op( OffoptimOfferHistory.Fields.agg_date ).eq( this.sdf.format( aggDate.getTime() ) ) 
							)					
						).
						build(); 
		
		return getOffoptimOfferHistory( query );	
		
	}
	
	
//
//	SalesChannels sc = new SalesChannels();
//	sc.s	
	
//	public CatalogOffers getCatalogOffersByName( String offerName ) {
//		
//		String query = select().
//						from( new CatalogOffers() ).
//						where( op( CatalogOffers.Fields.offer_name ).eq( offerName ) ).
//						build(); 
//		
//		return getOffoptimOfferHistory( query );	
//		
//	}
//	
//	public ArrayList<CatalogOffers> getAllCatalogOffers() {
//		
//		String query = select().
//						from( new CatalogOffers() ).
//						build(); 
//		
//		return getOffoptimOfferHistoryList( query );	
//		
//	}
//	
//	public ArrayList<CatalogOffers> getAllCatalogOffersByType( OfferType offerType ) {
//		
//		String query = select().
//						from( new CatalogOffers() ).
//						where( op( CatalogOffers.Fields.voucher_type ).eq( offerType.name() ) ).
//						build(); 
//		
//		return getOffoptimOfferHistoryList( query );	
//		
//	}
//	
//	public CatalogOffers getCatalogOffersByTypeAndName( OfferType offerType, String offerName ) {
//		
//		String query = select().
//						from( new CatalogOffers() ).
//						where( 
//								op( CatalogOffers.Fields.voucher_type ).eq( offerType.name() ),
//								and( op( CatalogOffers.Fields.offer_name ).eq( offerName ) ) 
//						).
//						build(); 
//		
//		return getOffoptimOfferHistory( query );	
//		
//	}
//
//	public ArrayList<CatalogOffers> getAllSimpleCatalogOffers() {
//		
//		return getAllCatalogOffersByType( OfferType.none );	
//		
//	}
//	
//	public ArrayList<CatalogOffers> getAllOneTimeUseCatalogOffers() {
//		
//		return getAllCatalogOffersByType( OfferType.oneTimeUse );	
//		
//	}
//
//	public ArrayList<CatalogOffers> getAllUnlimitedUseCatalogOffers() {
//		
//		return getAllCatalogOffersByType( OfferType.unlimitedUse );	
//		
//	}
//	
//	public CatalogOffers getSimpleCatalogOffersByName( String offerName ) {
//		
//		return getCatalogOffersByTypeAndName( OfferType.none, offerName );	
//		
//	}
//	
//	public CatalogOffers getOneTimeCatalogOffersByName( String offerName ) {
//		
//		return getCatalogOffersByTypeAndName( OfferType.oneTimeUse, offerName );	
//		
//	}
//
//	public CatalogOffers getUnlimitedCatalogOffersByName( String offerName ) {
//		
//		return getCatalogOffersByTypeAndName( OfferType.unlimitedUse, offerName );	
//		
//	}
//	
//	public ArrayList<CatalogOffers> getAllCatalogOffersByToken( Token token ) {
//		
//		String query = select().
//						from( new CatalogOffers() ).
//						join( new OffoptimCustomerItems() ).
//						on(
//							op( OffoptimCustomerItems.Fields.offer_id  ).
//							eq( CatalogOffers.Fields.offer_id ) 
//						).						
//						join( new OffoptimCustomerPack() ).
//						on( 
//							op( OffoptimCustomerPack.Fields.customer_offer_pack_id ).
//							eq( OffoptimCustomerItems.Fields.customer_offer_pack_id ) 
//						).
//						where( op( OffoptimCustomerPack.Fields.token_code ).eq( token.getTokenCode() ) ).
//						build(); 
//		
//		return getOffoptimOfferHistoryList( query );	
//		
//	}
	
}
