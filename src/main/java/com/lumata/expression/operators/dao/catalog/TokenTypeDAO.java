package com.lumata.expression.operators.dao.catalog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.dao.administration.SalesChannels;
import com.lumata.expression.operators.dao.administration.SalesChannelsList;


public class TokenTypeDAO {

	private static final Logger logger = LoggerFactory.getLogger(TokenTypeDAO.class);
	
	private int token_type_id;
	private String token_type_name;
	private int token_label_id;
	private int expiration_duration;
	private String expiration_duration_unit;
	private int qty_max_redeems;
	private int single_use_redeem_duration_timeout;
	private String token_format;
	private String description;
	private ArrayList<SalesChannels> salesChannelsList;
	
	public TokenTypeDAO() {
		
		this.set( null, null, -1, "", -1, 0, "SECONDS", 0, 0, "", "", new ArrayList<SalesChannels>() );
		
	}
	
	public TokenTypeDAO( Environment env, String tenant, int token_type_id, String token_type_name, int token_label_id, int expiration_duration, String expiration_duration_unit, int qty_max_redeems, int single_use_redeem_duration_timeout, String token_format, String description, ArrayList<SalesChannels> salesChannelsList ) {
		
		this.set( env, tenant, token_type_id, token_type_name, token_label_id, expiration_duration, expiration_duration_unit, qty_max_redeems, single_use_redeem_duration_timeout, token_format, description, salesChannelsList);
					
	}
	
	public TokenTypeDAO( Environment env, String tenant, ResultSet rs ) {
		
		try {
					
			this.set( env, tenant, rs.getInt("token_type_id"), rs.getString("token_type_name"), rs.getInt("token_label_id"), rs.getInt("expiration_duration"), rs.getString("expiration_duration_unit"), rs.getInt("qty_max_redeems"), rs.getInt("single_use_redeem_duration_timeout"), rs.getString("token_format"), rs.getString("description"), new ArrayList<SalesChannels>() );
					
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}	
		
	}

	public TokenTypeDAO( Environment env, String tenant, JSONObject tokenType ) {
		
		try {
		
			JSONArray salesChannelsList = tokenType.getJSONArray( "salesChannels" );
			
			ArrayList<SalesChannels> salesChannelsArrayList = new ArrayList<SalesChannels>();
			
			for( int i = 0; i < salesChannelsList.length(); i++ ) {
				
				SalesChannels salesChannels = new SalesChannels( salesChannelsList.getJSONObject( i ) );
				
				salesChannelsArrayList.add( salesChannels );
								
			}
			
			this.set(env, tenant,( tokenType.isNull( "token_type_id" ) ? -1 : tokenType.getInt( "token_type_id" ) ), tokenType.getString( "token_type_name" ), tokenType.getInt( "token_label_id" ), tokenType.getInt( "expiration_duration" ), tokenType.getString( "expiration_duration_unit" ), tokenType.getInt( "qty_max_redeems" ), tokenType.getInt( "single_use_redeem_duration_timeout" ), tokenType.getString( "token_format" ), tokenType.getString( "description" ), salesChannelsArrayList );
					
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}	
		
	}
	
	public int getTokenTypeID() {
		
		return this.token_type_id;
		
	}
	
	public String getTokenTypeName() {
		
		return this.token_type_name;
		
	}

	public int getTokenLabelID() {
	
		return this.token_label_id;
		
	}

	public int getExpirationDuration() {
		
		return this.expiration_duration;
		
	}

	public String getExpirationDurationUnit() {
		
		return this.expiration_duration_unit;
		
	}

	public int getQtyMaxRedeems() {
		
		return this.qty_max_redeems;
		
	}

	public int getSingleUseRedeemDurationTimeout() {
		
		return this.single_use_redeem_duration_timeout;
		
	}

	public String getTokenFormat() {
		
		return this.token_format;
		
	}

	public String getDescription() {
		
		return this.description;
		
	}
	
	public ArrayList<SalesChannels> getSalesChannelsList() {
	
		return this.salesChannelsList;
		
	}

	public void set( Environment env, String tenant, int token_type_id, String token_type_name, int token_label_id, int expiration_duration, String expiration_duration_unit, int qty_max_redeems, int single_use_redeem_duration_timeout, String token_format, String description, ArrayList<SalesChannels> salesChannelsList ) {
		
		this.setTokenTypeID( token_type_id );
		this.setTokenTypeName( token_type_name );
		this.setTokenLabelID( token_label_id );
		this.setExpirationDuration( expiration_duration );
		this.setExpirationDurationUnit( expiration_duration_unit );
		this.setQtyMaxRedeems( qty_max_redeems );
		this.setSingleUseRedeemDurationTimeout( single_use_redeem_duration_timeout );
		this.setTokenFormat( token_format );
		this.setDescription( description );	
		this.setSalesChannelsList( env, tenant, salesChannelsList );		
		
	}
	
	public void setTokenTypeID( int token_type_id ) {
		
		this.token_type_id = token_type_id;
		
	}
	
	public void setTokenTypeName( String token_type_name ) {
		
		this.token_type_name = token_type_name;
		
	}

	public void setTokenLabelID( int token_label_id ) {
	
		this.token_label_id = token_label_id;
		
	}

	public void setExpirationDuration( int expiration_duration ) {
		
		this.expiration_duration = expiration_duration;
		
	}

	public void setExpirationDurationUnit( String expiration_duration_unit ) {
		
		this.expiration_duration_unit = expiration_duration_unit;
		
	}

	public void setQtyMaxRedeems( int qty_max_redeems ) {
		
		this.qty_max_redeems = qty_max_redeems;
		
	}

	public void setSingleUseRedeemDurationTimeout( int single_use_redeem_duration_timeout ) {
		
		this.single_use_redeem_duration_timeout = single_use_redeem_duration_timeout;
		
	}

	public void setTokenFormat( String token_format ) {
		
		this.token_format = token_format;
		
	}

	public void setDescription( String description ) {
		
		this.description = description;
		
	}

	public void setSalesChannelsList( ArrayList<SalesChannels> salesChannelsList ) {
		
		this.salesChannelsList = salesChannelsList;
		
	}

	public void setSalesChannelsList( Environment env, String tenant, ArrayList<SalesChannels> salesChannelsList ) {
		
		this.salesChannelsList = new ArrayList<SalesChannels>();
		
		SalesChannelsList slc = new SalesChannelsList( env, tenant, null );
		
		for( int i = 0; i < salesChannelsList.size(); i++ ) {
			
			SalesChannels sc = slc.get( salesChannelsList.get( i ).getChannelName() );
						
			if( sc == null ) { 
				
				slc.insert(env, tenant, null, salesChannelsList.get( i ).getChannelName(), salesChannelsList.get( i ).getActive() ); 
				
				this.salesChannelsList.add( slc.get( salesChannelsList.get( i ).getChannelName() ) );
				
			} else {
				
				this.salesChannelsList.add( sc );
				
			}
			
		}		
		
	}
	
	public void addSalesChannelsList( SalesChannels salesChannels ) {
		
		if( this != null && !this.hasSalesChannels( salesChannels.getChannelName() ) ) { this.salesChannelsList.add( salesChannels ); }
		
	}
	
	public boolean hasSalesChannels( String salesChannels ) {
		
		for( int i = 0; i < this.getSalesChannelsList().size(); i++ ) {
			
			SalesChannels sc = this.getSalesChannelsList().get( i );
			
			if( ( sc.getChannelName().trim() ).equals( salesChannels.trim() ) ) { return true; }
			
		}		
		
		return false;
		
	}
	
}
