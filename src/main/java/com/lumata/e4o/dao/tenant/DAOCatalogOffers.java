package com.lumata.e4o.dao.tenant;

import static com.lumata.common.testing.orm.Query.*;
import static com.lumata.common.testing.orm.Filter.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;




import com.lumata.common.testing.database.Mysql;
import com.lumata.e4o.schema.tenant.CatalogOffers;
import com.lumata.e4o.schema.tenant.OffoptimCustomerItems;
import com.lumata.e4o.schema.tenant.OffoptimCustomerPack;
import com.lumata.e4o.schema.tenant.Token;


public class DAOCatalogOffers extends DAO {

	//private static final Logger logger = LoggerFactory.getLogger( DAOCatalogOffers.class );
	
	SimpleDateFormat sdf;
	
	private enum OfferType {
		
		none,
		oneTimeUse,
		unlimitedUse;
		
	}
	
	public DAOCatalogOffers( Mysql mysql ) {
		super( mysql );
		sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
	}
	
	public static DAOCatalogOffers getInstance( Mysql mysql ) {
		return new DAOCatalogOffers( mysql );
	}
	
	private ArrayList<CatalogOffers> getCatalogOffersList( String query ) {
		
		ArrayList<CatalogOffers> catalogOfferList = new ArrayList<CatalogOffers>();
		
		ResultSet rs = this.getMysql().execQuery( query );
		
		try {
			
			while( rs.next() ) {
				
				CatalogOffers catalogOffer = new CatalogOffers( rs );
		
				catalogOfferList.add( catalogOffer );				
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return catalogOfferList;
		
	}
	
	private CatalogOffers getCatalogOffers( String query ) {
		
		ResultSet rs = this.getMysql().execQuery( query );
		
		CatalogOffers catalogOffers = new CatalogOffers();
		
		try {
			
			while( rs.next() ) {
				
				catalogOffers = new CatalogOffers( rs );
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return catalogOffers;
		
	}
	
	public CatalogOffers getCatalogOffersById( Short offerId ) {
		
		String query = select().
						from( new CatalogOffers() ).
						where( op( CatalogOffers.Fields.offer_id ).eq( offerId ) ).
						build(); 
		
		return getCatalogOffers( query );	
		
	}
	
	public ArrayList<CatalogOffers> getAllCatalogOffers() {
		
		String query = select().
						from( new CatalogOffers() ).
						build(); 
		
		return getCatalogOffersList( query );	
		
	}
	
	public ArrayList<CatalogOffers> getAllCatalogOffersByType( OfferType offerType ) {
		
		String query = select().
						from( new CatalogOffers() ).
						where( op( CatalogOffers.Fields.voucher_type ).eq( offerType.name() ) ).
						build(); 
		
		return getCatalogOffersList( query );	
		
	}
	
	public CatalogOffers getCatalogOffersByTypeAndName( OfferType offerType, String offerName ) {
		
		String query = select().
						from( new CatalogOffers() ).
						where( 
								op( CatalogOffers.Fields.voucher_type ).eq( offerType.name() ),
								and( op( CatalogOffers.Fields.offer_name ).eq( offerName ) ) 
						).
						build(); 
		
		return getCatalogOffers( query );	
		
	}

	public ArrayList<CatalogOffers> getAllSimpleCatalogOffers() {
		
		return getAllCatalogOffersByType( OfferType.none );	
		
	}
	
	public ArrayList<CatalogOffers> getAllOneTimeUseCatalogOffers() {
		
		return getAllCatalogOffersByType( OfferType.oneTimeUse );	
		
	}

	public ArrayList<CatalogOffers> getAllUnlimitedUseCatalogOffers() {
		
		return getAllCatalogOffersByType( OfferType.unlimitedUse );	
		
	}
	
	public CatalogOffers getSimpleCatalogOffersByName( String offerName ) {
		
		return getCatalogOffersByTypeAndName( OfferType.none, offerName );	
		
	}
	
	public CatalogOffers getOneTimeCatalogOffersByName( String offerName ) {
		
		return getCatalogOffersByTypeAndName( OfferType.oneTimeUse, offerName );	
		
	}

	public CatalogOffers getUnlimitedCatalogOffersByName( String offerName ) {
		
		return getCatalogOffersByTypeAndName( OfferType.unlimitedUse, offerName );	
		
	}
	
	public ArrayList<CatalogOffers> getAllCatalogOffersByToken( Token token ) {
		
		String query = select().
						from( new CatalogOffers() ).
						join( new OffoptimCustomerItems() ).
						on(
							op( OffoptimCustomerItems.Fields.offer_id  ).
							eq( CatalogOffers.Fields.offer_id ) 
						).						
						join( new OffoptimCustomerPack() ).
						on( 
							op( OffoptimCustomerPack.Fields.customer_offer_pack_id ).
							eq( OffoptimCustomerItems.Fields.customer_offer_pack_id ) 
						).
						where( op( OffoptimCustomerPack.Fields.token_code ).eq( token.getTokenCode() ) ).
						build(); 
		
		return getCatalogOffersList( query );	
		
	}
	
}
