package com.lumata.expression.operators.dao.administration;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SalesChannels {

	private static final Logger logger = LoggerFactory.getLogger(SalesChannels.class);
	
	public static int DEFAULT_CHANNEL_ID = -1;
	private int channel_id;
	private String channel_name;
	private boolean active;
	
	public SalesChannels() {}
	
	public SalesChannels( int channel_id, String channel_name, boolean active ) {
		
		this.channel_id = ( channel_id <= 0 ? DEFAULT_CHANNEL_ID : channel_id );
		this.channel_name = channel_name;
		this.active = active;		
			
	}
	
	public SalesChannels( JSONObject salesChannel ) {
		
		try {
		
			this.channel_id =  ( salesChannel.isNull( "channel_id" ) ? DEFAULT_CHANNEL_ID : salesChannel.getInt( "channel_id" ) );
			this.channel_name = salesChannel.getString( "channel_name" );
			this.active = salesChannel.getBoolean( "active" );
		
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}	
		
	}

	public SalesChannels( ResultSet rs ) {
		
		try {
		
			this.channel_id = rs.getInt( "channel_id" );
			this.channel_name = rs.getString( "channel_name" );
			this.active = rs.getBoolean( "active" );
		
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}	
		
	}

	public int getChannelID() {
		
		return this.channel_id;
		
	}
	
	public String getChannelName() {
		
		return this.channel_name;
		
	}

	public boolean getActive() {
	
		return this.active;
		
	}

	public void setChannelID( int channel_id ) {
		
		this.channel_id = channel_id;
		
	}
	
	public void setChannelName( String channel_name ) {
		
		this.channel_name = channel_name;
		
	}

	public void setActive( boolean active ) {
	
		this.active = active;
		
	}
	
}
