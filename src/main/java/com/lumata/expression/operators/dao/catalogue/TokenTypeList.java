package com.lumata.expression.operators.dao.catalogue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.dao.administration.SalesChannels;
import com.lumata.expression.operators.dao.administration.SalesChannelsList;


public class TokenTypeList {

	private static final Logger logger = LoggerFactory.getLogger(TokenTypeList.class);
	
	private ArrayList<TokenType> list;
	
	public TokenTypeList( Environment env, String tenant, ArrayList<Integer> filteredIds ) {
				
		this.load(env, tenant, filteredIds);
		
	}
	
	/*
	public TokenTypeList( Environment env, String tenant, ArrayList<Integer> filteredIds, String path, String file, IOFileUtils.IOLoadingType loadingType ) {
		
		JSONObject salesChannels = new JSONObject();
				
		try {
		
			this.load(env, tenant, filteredIds);
			
			switch( loadingType ) {
			
				case FILE: {  
					salesChannels = JSONUtils.loadJSONFile( path, file );
					break;
				}
				case RESOURCE: {
					salesChannels = JSONUtils.loadJSONResource( path, file );
					break;
				}
			
			}
			
			JSONArray salesChannelsList = salesChannels.getJSONArray( "sales_channels" ); 
		
			for( int i = 0; i < salesChannelsList.length(); i++ ) {
								
				TokenType channel = new TokenType( salesChannelsList.getJSONObject( i ) );
								
				if( !this.isSalesChannel( channel.getChannelName() ) ) { this.insert(env, tenant, filteredIds, channel.getChannelName(), channel.getActive()); }
				
			}
			
			this.load(env, tenant, filteredIds);
			
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
		} catch( IOFileException e ) {
			
			logger.error( e.getMessage(), e );
			
		}	
			
		
	}
*/	
	public TokenType get( int salesChannelIndex ) {
		
		return this.list.get( salesChannelIndex );
				
	}
	
	public TokenType get( String TokenType ) {
		
		if( TokenType.length() <= 0 ) { return null; }
		else {
			
			for( int i = 0; i < this.list.size(); i++ ) {
				System.out.println( this.list.get( i ).getTokenTypeName() );
				if( ( this.list.get( i ).getTokenTypeName()).equals( TokenType ) ) { return this.list.get( i ); }
				
			}
			
		}
		
		return null;
		
	}

	public boolean isTokenType( String TokenType ) {
		
		if( this.get(TokenType) != null ) { return true; }
		
		return false;
		
	}

/*
	public boolean isActive( String salesChannel ) {
		
		if( salesChannel.length() <= 0 ) { return false; }
		else {
			
			for( int i = 0; i < this.list.size(); i++ ) {
				
				if( this.list.get( i ).equals( salesChannel ) ) { return true; }
				
			}
			
		}
		
		return false;
		
	}
	
	public int delete(  Environment env, String tenant, ArrayList<Integer> filteredIds, String salesChannel, boolean active ) {
		
		int index = 0;
				
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
		
		String query = "DELETE FROM " + tenant + ".sales_channels WHERE channel_name = '" + salesChannel + "';";
				
		index = mysql.execUpdate( query );
		
		mysql.close();
		
		this.load(env, tenant, filteredIds);
		
		return index;
		
	}
*/
	public int insert(  Environment env, String tenant, ArrayList<Integer> filteredIds, String tokenTypeName, int expirationDuration, String expirationDurationUnit, int qtyMaxReddems, int singleUseRedeemDurationTimeout, String tokenFormat, String description, ArrayList<String> salesChannelsList ) {
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
		
		SalesChannelsList slc = new SalesChannelsList( env, tenant, filteredIds );
		
		ArrayList<Integer> salesChannelsIndex = new ArrayList<Integer>();
		
		// Get Sales Channels Ids ( Insert Sales Channels if not exists )
		for( int i = 0; i < salesChannelsList.size(); i++ ) {
			
			SalesChannels sc = slc.get( salesChannelsList.get( i ) );
			
			if( sc != null ) {
				
				salesChannelsIndex.add( sc.getChannelID() );
						
			} else {
				
				salesChannelsIndex.add( slc.insert(env, tenant, filteredIds, salesChannelsList.get( i ), true ) ); 
				
			}
			
		}
		
		int tokenLabelIndex;
		
		// Insert Token Label
		String query = "INSERT INTO " + tenant + ".token_label ( label ) VALUES ( '" + tokenTypeName + "' );";
		
		tokenLabelIndex = mysql.execUpdate( query ); 				
		
		System.out.println( "Token Label Index: " + tokenLabelIndex );
		// Insert Token Label Channels
		for( int j = 0; j < salesChannelsIndex.size(); j++ ) {
			
			query = "INSERT INTO " + tenant + ".token_label_channel VALUES ( " + tokenLabelIndex + ", " + salesChannelsIndex.get( j ) + " );";
			
			mysql.execUpdate( query );
							
		}
		
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
				"VALUES ( '" + tokenTypeName + "'," +
				"		  " + tokenLabelIndex + ", " +
				"		  " + expirationDuration + ", " +
				"		  '" + expirationDurationUnit + "', " +
				"		  " + qtyMaxReddems + ", " +
				"		  " + singleUseRedeemDurationTimeout + ", " +
				"		  '" + tokenFormat + "', " +
				"		  '" + description + "' " +
				");";
		System.out.println( query );
		int tokenTypeIndex = mysql.execUpdate( query ); 
		
		return tokenTypeIndex;
		
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
					
					tokenType = new TokenType();
					tokenType.setTokenTypeID( rs.getInt("token_type_id") );
					tokenType.setTokenTypeName( rs.getString("token_type_name") );
					tokenType.setTokenLabelID( rs.getInt("token_label_id") );
					tokenType.setExpirationDuration( rs.getInt("expiration_duration") );
					tokenType.setExpirationDurationUnit( rs.getString("expiration_duration_unit") );
					tokenType.setQtyMaxRedeems( rs.getInt("qty_max_redeems") );
					tokenType.setSingleUseRedeemDurationTimeout( rs.getInt("single_use_redeem_duration_timeout") );
					tokenType.setTokenFormat( rs.getString("token_format") );
					tokenType.setDescription( rs.getString("description") );
					tokenType.setSalesChannelsList( new ArrayList<SalesChannels>() );
					
					token_type_id = tokenType.getTokenTypeID();
					
					this.list.add( tokenType );
					
				} 

				SalesChannels salesChannels = new SalesChannels( rs.getInt( "channel_id" ), rs.getString( "channel_name" ), Boolean.valueOf( rs.getString( "active" ) ) );
				
				if( tokenType != null ) { tokenType.addSalesChannelsList( salesChannels ); }
				
			}
		
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		mysql.close();
		
	}
		
	public void refresh( Environment env, String tenant, ArrayList<Integer> filteredIds ) {
		
		this.load(env, tenant, filteredIds);
		
	}
	
	public int size() {
		
		return this.list.size();
		
	}	
	
}
