package com.lumata.expression.operators.dao.catalogue;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.dao.administration.SalesChannels;
import com.lumata.expression.operators.dao.administration.SalesChannelsList;


public class TokenType {

	private static final Logger logger = LoggerFactory.getLogger(TokenType.class);
	
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
	
	public TokenType() {
		
		this.token_type_id = -1;
		this.token_type_name = "";
		this.token_label_id = -1;
		this.expiration_duration = 0;
		this.expiration_duration_unit = "";
		this.qty_max_redeems = 0;
		this.single_use_redeem_duration_timeout = 0;
		this.token_format = "";
		this.description = "";	
		this.salesChannelsList = new ArrayList<SalesChannels>();
		
	}
	
	public TokenType( Environment env, String tenant, int token_type_id, String token_type_name, int token_label_id, int expiration_duration, String expiration_duration_unit, int qty_max_redeems, int single_use_redeem_duration_timeout, String token_format, String description, ArrayList<SalesChannels> salesChannelsList ) {
		
		this.token_type_id = token_type_id;
		this.token_type_name = token_type_name;
		this.token_label_id = token_label_id;
		this.expiration_duration = expiration_duration;
		this.expiration_duration_unit = expiration_duration_unit;
		this.qty_max_redeems = qty_max_redeems;
		this.single_use_redeem_duration_timeout = single_use_redeem_duration_timeout;
		this.token_format = token_format;
		this.description = description;	
		this.salesChannelsList = salesChannelsList;	
		
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
	
	public TokenType( Environment env, String tenant, JSONObject tokenType ) {
		
		try {
		
			this.token_type_id = ( tokenType.isNull( "token_type_id" ) ? -1 : tokenType.getInt( "token_type_id" ) );
			this.token_type_name = tokenType.getString( "token_type_name" );
			this.token_label_id = tokenType.getInt( "token_label_id" );
			this.expiration_duration = tokenType.getInt( "expiration_duration" );
			this.expiration_duration_unit = tokenType.getString( "expiration_duration_unit" );
			this.qty_max_redeems = tokenType.getInt( "qty_max_redeems" );
			this.single_use_redeem_duration_timeout = tokenType.getInt( "single_use_redeem_duration_timeout" );
			this.token_format = tokenType.getString( "token_format" );
			this.description = tokenType.getString( "description" );
				
			SalesChannelsList slc = new SalesChannelsList( env, tenant, null );
			
			JSONArray salesChannelsList = tokenType.getJSONArray( "salesChannels" );
			
			for( int i = 0; i < salesChannelsList.length(); i++ ) {
				
				JSONObject salesChannels = salesChannelsList.getJSONObject( i );
				
				SalesChannels sc = slc.get( salesChannels.getString( "channel_name" ) );
				
				if( sc == null ) { 
					
					slc.insert(env, tenant, null, salesChannels.getString( "channel_name" ), Boolean.valueOf( salesChannels.getString( "active" ) ) ); 
					
					this.salesChannelsList.add( slc.get( salesChannels.getString( "channel_name" ) ) );
					
				} else {
					
					this.salesChannelsList.add( sc );
					
				}
				
			}
			
		
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
	
	public void addSalesChannelsList( SalesChannels salesChannels ) {
		
		this.salesChannelsList.add( salesChannels );
		
	}
	
}
