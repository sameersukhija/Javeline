package com.lumata.expression.operators.dao.catalog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.system.Environment;

public class CatalogProductSpecificCharacteristicsDAOList {

	private static final Logger logger = LoggerFactory.getLogger( CatalogProductSpecificCharacteristicsDAOList.class );
	
	private ArrayList<CatalogProductTypeCharacteristicsDAO> list;
	
	public CatalogProductSpecificCharacteristicsDAOList() {
		
		this.list = new ArrayList<CatalogProductTypeCharacteristicsDAO>();
		
	}
	
	public CatalogProductSpecificCharacteristicsDAOList( Environment env, String tenant, ArrayList<Integer> filteredIds ) {
				
		this.load(env, tenant, filteredIds);
		
	}
	
	public CatalogProductSpecificCharacteristicsDAOList( Environment env, String tenant, ArrayList<Integer> filteredIds, String path, String file, IOFileUtils.IOLoadingType loadingType ) {
		
		JSONObject catalogProductTypeCharacteristics = new JSONObject();
				
		try {
		
			this.load(env, tenant, filteredIds);
			
			switch( loadingType ) {
			
				case FILE: {  
					catalogProductTypeCharacteristics = JSONUtils.loadJSONFile( path, file );
					break;
				}
				case RESOURCE: {
					catalogProductTypeCharacteristics = JSONUtils.loadJSONResource( path, file );
					break;
				}
			
			}
			
			this.load(env, tenant, filteredIds);
			
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
		} catch( IOFileException e ) {
			
			logger.error( e.getMessage(), e );
			
		}	
			
		
	}

	public CatalogProductTypeCharacteristicsDAO get( int catalogProductTypeCharacteristicsIndex ) {
		
		return this.list.get( catalogProductTypeCharacteristicsIndex );
				
	}
	
	public CatalogProductTypeCharacteristicsDAO get( String characteristic_name ) {
		
		if( characteristic_name.length() <= 0 ) { return null; }
		else {
			
			for( int i = 0; i < this.list.size(); i++ ) {
				
				if( ( this.list.get( i ).getCharacteristicName() ).equals( characteristic_name ) ) { return this.list.get( i ); }
				
			}
			
		}
		
		return null;
		
	}

	public boolean isCatalogProductTypes( String characteristic_name ) {
		
		if( this.get( characteristic_name ) != null ) { return true; }
		
		return false;
		
	}
	
	public int delete( Environment env, String tenant, ArrayList<Integer> filteredIds, CatalogOfferContentDAO catalogOfferContentDAO ) {
		
		int index = 0;
				
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
				
		//String query = "DELETE FROM " + tenant + ".catalog_offer_content " + ( catalogOfferContentDAO != null ? "WHERE " + CatalogOfferContentDAO.Fields.offer_id.toString() + " = '" + catalogOfferContentDAO.getCharacteristicName() + "'": "" ) + ";";
				
		String query = "DELETE FROM " + tenant + ".catalog_product_specific_characteristics;";
				
		index = mysql.execUpdate( query );
				
		mysql.close();
		
		//this.load(env, tenant, filteredIds);
		
		return index;
		
	}
	
	public int deleteAll( Environment env, String tenant, ArrayList<Integer> filteredIds) {
				
		return this.delete( env, tenant, filteredIds, null );
		
	}

	public int insert( Environment env, String tenant, ArrayList<Integer> filteredIds, JSONObject catalogProductTypeCharacteristics ) {
		
		int index = -1;
		
		/*
		try {
			
			this.insert(env, tenant, filteredIds, tokenType.getString( "" ), expirationDuration, expirationDurationUnit, qtyMaxReddems, singleUseRedeemDurationTimeout, tokenFormat, description, salesChannelsList)
			
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		*/
		return index;
		
	}
	
	public int insert( Environment env, String tenant, ArrayList<Integer> filteredIds, CatalogProductTypeCharacteristicsDAO catalogProductTypeCharacteristics ) {
		
		int productID = -1; 
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
		
		CatalogProductTypeCharacteristicsDAO cptc = this.get( catalogProductTypeCharacteristics.getCharacteristicName() );
		
		/*
		if( tt == null ) {
			
			// Insert Token Label
			String query = "INSERT INTO " + tenant + ".token_label ( label ) VALUES ( '" + tokenType.getTokenTypeName() + "' );";
			
			tokenLabelID = mysql.execUpdate( query );
			
			// Insert Token Type
			query = "INSERT INTO " + tenant + ".token_type " +
					"( " +
					" token_type_name, " +
					" token_label_id, " +
					" expiration_duration, " +
					" expiration_duration_unit, " +
					" qty_max_redeems, " +
					" single_use_redeem_duration_timeout, " +
					" token_format, " +
					" description " +
					") " +
					"VALUES ( '" + tokenType.getTokenTypeName() + "'," +
					"		  " + tokenType.getTokenLabelID() + ", " +
					"		  " + tokenType.getExpirationDuration() + ", " +
					"		  '" + tokenType.getExpirationDurationUnit() + "', " +
					"		  " + tokenType.getQtyMaxRedeems() + ", " +
					"		  " + tokenType.getSingleUseRedeemDurationTimeout() + ", " +
					"		  '" + tokenType.getTokenFormat() + "', " +
					"		  '" + tokenType.getDescription() + "' " +
					");";
			
			tokenTypeID = mysql.execUpdate( query ); 
			
		} else {
			
			tokenTypeID = tt.getTokenTypeID();
			
			tokenLabelID = tt.getTokenLabelID();
			
		}
		
		// Get Sales Channels Ids ( Insert Sales Channels if not exists )
		SalesChannelsList slc = new SalesChannelsList( env, tenant, filteredIds );
		
		ArrayList<Integer> salesChannelsIDs = new ArrayList<Integer>();
		
		for( int i = 0; i < tokenType.getSalesChannelsList().size(); i++ ) {
			
			if( tt == null || !tt.hasSalesChannels( tokenType.getSalesChannelsList().get( i ).getChannelName() ) ) { 
				
				salesChannelsIDs.add( slc.insert(env, tenant, filteredIds, ( SalesChannels )tokenType.getSalesChannelsList().get( i ) ) ); 
			
			}
			
		}
		
		// Insert Token Label Channels
		for( int j = 0; j < salesChannelsIDs.size(); j++ ) {
			
			String query = "INSERT INTO " + tenant + ".token_label_channel VALUES ( " + tokenLabelID + ", " + salesChannelsIDs.get( j ) + " );";
			
			mysql.execUpdate( query );
							
		}
		*/	
		return productID;
		
	}
		
	
	public void load( Environment env, String tenant, ArrayList<Integer> filteredIds ) {
		
		
		this.list = new ArrayList<CatalogProductTypeCharacteristicsDAO>();
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
				
		String query = 	"SELECT 	 	cpsc.product_type_id, " +
						"		 	 	cpsc.type_name, " +
						"		 	 	cpsc.description " +
						"		 	 	" + tenant + ".catalog_product_specific_characteristics cpsc;";
		
		ResultSet rs = mysql.execQuery( query );
		
		
		
		/*
		int catalog_product_type_id = 0;
		
		try {
		
			CatalogProductTypes catalogProductTypes = null;
			
			while( rs.next() ) {
						
				if( rs.getInt( "product_type_id" ) != catalog_product_type_id ) {
				
					catalogProductTypes = new CatalogProductTypes( env, tenant, rs );
					
					catalog_product_type_id = catalogProductTypes.getProductTypeID();
					
					this.list.add( catalogProductTypes );
					
				}
				
			}
			
			//CatalogProductTypeCharacteristics catalogProductTypeCharacteristics = new CatalogProductTypeCharacteristics( rs.getInt( "product_type_id" ), rs.getString( "characteristic_name" ), rs.getString( "characteristic_details" ) );
			
			//tokenType.addSalesChannelsList( salesChannels );
		
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		mysql.close();
		*/
	}
		
	public void refresh( Environment env, String tenant, ArrayList<Integer> filteredIds ) {
		
		this.load(env, tenant, filteredIds);
		
	}
	
	public int size() {
		
		return this.list.size();
		
	}	
	
}
