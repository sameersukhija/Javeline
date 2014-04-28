package com.lumata.expression.operators.dao.catalog;

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

public class CatalogRelatedProductsDAOList {

	private static final Logger logger = LoggerFactory.getLogger( CatalogRelatedProductsDAOList.class );
	
	private ArrayList<CatalogRelatedProductsDAO> list;
	
	public CatalogRelatedProductsDAOList() {
		
		this.list = new ArrayList<CatalogRelatedProductsDAO>();
		
	}
	
	public CatalogRelatedProductsDAOList( Environment env, String tenant, ArrayList<Integer> filteredIds ) {
				
		//this.load(env, tenant, filteredIds);
		
	}
		
	public CatalogRelatedProductsDAOList( Environment env, String tenant, ArrayList<Integer> filteredIds, String path, String file, IOFileUtils.IOLoadingType loadingType ) {
		
		JSONObject catalogRelatedProductsDAOList = new JSONObject();
				
		try {
		
			//this.load(env, tenant, filteredIds);
			
			switch( loadingType ) {
			
				case FILE: {  
					catalogRelatedProductsDAOList = JSONUtils.loadJSONFile( path, file );
					break;
				}
				case RESOURCE: {
					catalogRelatedProductsDAOList = JSONUtils.loadJSONResource( path, file );
					break;
				}
			
			}
			
			//this.load(env, tenant, filteredIds);
			
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
		} 
		
	}
	
	public int delete( Environment env, String tenant, ArrayList<Integer> filteredIds, CatalogOfferContentDAO catalogOfferContentDAO ) {
		
		int index = 0;
				
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
				
		//String query = "DELETE FROM " + tenant + ".catalog_offer_content " + ( catalogOfferContentDAO != null ? "WHERE " + CatalogOfferContentDAO.Fields.offer_id.toString() + " = '" + catalogOfferContentDAO.getCharacteristicName() + "'": "" ) + ";";
				
		String query = "DELETE FROM " + tenant + ".catalog_related_products;";
				
		index = mysql.execUpdate( query );
				
		mysql.close();
		
		//this.load(env, tenant, filteredIds);
		
		return index;
		
	}
	
	public int deleteAll( Environment env, String tenant, ArrayList<Integer> filteredIds) {
				
		return this.delete( env, tenant, filteredIds, null );
		
	}
	
	public int size() {
		
		return this.list.size();
		
	}
	
	
	/*
	public CatalogRelatedProductsDAO get( int CatalogOfferContentIndex ) {
		
		return this.list.get( CatalogOfferContentIndex );
				
	}
	
	public CatalogRelatedProductsDAO get( int CatalogRelatedProductID ) {
		
		if( CatalogRelatedProductID <= 0 ) { return null; }
		else {
			
			for( int i = 0; i < this.list.size(); i++ ) {
				
				if( this.list.get( i ).getProductID() == CatalogRelatedProductID ) { return this.list.get( i ); }
				
			}
			
		}
		
		return null;
		
	}

	public boolean isCatalogOfferContent( String CatalogOfferContent ) {
		
		if( this.get(CatalogOfferContent) != null ) { return true; }
		
		return false;
		
	}
	
	public int delete( Environment env, String tenant, ArrayList<Integer> filteredIds, CatalogOfferContentDAO catalogOfferContent ) {
		
		int index = 0;
				
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
		
		/*
		String query = "DELETE FROM " + tenant + ".token_label_channel WHERE token_label_id = '" + tokenType.getTokenLabelID() + "';";
				
		index = mysql.execUpdate( query );
		
		query = "DELETE FROM " + tenant + ".token_label WHERE token_label_id = '" + tokenType.getTokenLabelID() + "';";
		
		index = mysql.execUpdate( query );
		
		query = "DELETE FROM " + tenant + ".token_type WHERE token_type_id = '" + tokenType.getTokenTypeID() + "';";
		
		index = mysql.execUpdate( query );		
		
		mysql.close();
		*/
		//this.load(env, tenant, filteredIds);
	/*	
		return index;
		
	}

	public int insert( Environment env, String tenant, ArrayList<Integer> filteredIds, JSONObject tokenType ) {
		
		int index = -1;
		
		/*
		try {
			
			this.insert(env, tenant, filteredIds, tokenType.getString( "" ), expirationDuration, expirationDurationUnit, qtyMaxReddems, singleUseRedeemDurationTimeout, tokenFormat, description, salesChannelsList)
			
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		*/
	/*	return index;
		
	}
	/*
	public int insert( Environment env, String tenant, ArrayList<Integer> filteredIds, TokenType tokenType ) {
		
		int tokenTypeID = -1; 
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
		
		TokenType tt = this.get( tokenType.getTokenTypeName() );
		
		int tokenLabelID = -1;
		 
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
			
		return tokenTypeID;
		
	}
		
	public void load( Environment env, String tenant, ArrayList<Integer> filteredIds ) {
				
		this.list = new ArrayList<TokenType>();
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
				
		String query = 	"SELECT 	 	tt.token_type_id, " +
						"		 	 	tt.token_type_name, " +
						"		 	 	tt.token_label_id, " +
						"		 	 	tt.expiration_duration, " +
						"		 	 	tt.expiration_duration_unit, " +
						"		 	 	tt.qty_max_redeems, " +
						"		 	 	tt.single_use_redeem_duration_timeout, " +
						"		 	 	tt.token_format, " +
						"		 	 	tt.description, " +
						"				sc.channel_id, " +
						"				sc.channel_name, " +
						"				sc.active " +
						"FROM 	" + tenant + ".token_type tt " +
						"JOIN 	" + tenant + ".token_label_channel tlc " +
						"ON 	tt.token_label_id = tlc.token_label_id " +
						"JOIN 	" + tenant + ".sales_channels sc " +
						"ON 	tlc.channel_id = sc.channel_id;";
		
		ResultSet rs = mysql.execQuery( query );
		
		int token_type_id = 0;
		
		try {
		
			TokenType tokenType = null;
			
			while( rs.next() ) {
							
				if( rs.getInt( "token_type_id" ) != token_type_id ) {
					
					tokenType = new TokenType( env, tenant, rs );
								
					token_type_id = tokenType.getTokenTypeID();
					
					this.list.add( tokenType );
					
				} 

				SalesChannels salesChannels = new SalesChannels( rs.getInt( "channel_id" ), rs.getString( "channel_name" ), Boolean.valueOf( rs.getString( "active" ) ) );
				
				tokenType.addSalesChannelsList( salesChannels );
				
			}
		
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		mysql.close();
		
	}
		
	public void refresh( Environment env, String tenant, ArrayList<Integer> filteredIds ) {
		
		this.load(env, tenant, filteredIds);
		
	}
	*/
	
}
