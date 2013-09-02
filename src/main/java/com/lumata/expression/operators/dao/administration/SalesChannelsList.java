package com.lumata.expression.operators.dao.administration;

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


public class SalesChannelsList {

	private static final Logger logger = LoggerFactory.getLogger(SalesChannelsList.class);
	
	private ArrayList<SalesChannels> list;
	
	public SalesChannelsList( Environment env, String tenant, ArrayList<Integer> filteredIds ) {
				
		this.load(env, tenant, filteredIds);
		
	}
	
	public SalesChannelsList( Environment env, String tenant, ArrayList<Integer> filteredIds, String path, String file, IOFileUtils.IOLoadingType loadingType ) {
		
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
								
				SalesChannels channel = new SalesChannels( salesChannelsList.getJSONObject( i ) );
								
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
	
	public SalesChannels get( int salesChannelIndex ) {
		
		return this.list.get( salesChannelIndex );
				
	}
	
	public SalesChannels get( String salesChannel ) {
		
		if( salesChannel.length() <= 0 ) { return null; }
		else {
			
			for( int i = 0; i < this.list.size(); i++ ) {
				
				if( ( this.list.get( i ).getChannelName()).equals( salesChannel ) ) { return this.list.get( i ); }
				
			}
			
		}
		
		return null;
		
	}

	public boolean isSalesChannel( String salesChannel ) {
		
		if( this.get(salesChannel) != null ) { return true; }
		
		return false;
		
	}

	public boolean isActive( String salesChannel ) {
		
		if( salesChannel.length() <= 0 ) { return false; }
		else {
			
			for( int i = 0; i < this.list.size(); i++ ) {
				
				if( this.list.get( i ).equals( salesChannel ) ) { return true; }
				
			}
			
		}
		
		return false;
		
	}
	
	public int delete( Environment env, String tenant, ArrayList<Integer> filteredIds, String salesChannel, boolean active ) {
		
		int index = 0;
				
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
		
		String query = "DELETE FROM " + tenant + ".sales_channels WHERE channel_name = '" + salesChannel + "';";
				
		index = mysql.execUpdate( query );
		
		mysql.close();
		
		this.load(env, tenant, filteredIds);
		
		return index;
		
	}

	public int insert( Environment env, String tenant, ArrayList<Integer> filteredIds, SalesChannels salesChannel ) {
						
		return this.insert(env, tenant, filteredIds, salesChannel.getChannelName(), salesChannel.getActive());
		
	}

	public int insert( Environment env, String tenant, ArrayList<Integer> filteredIds, String salesChannel, boolean active ) {
		
		int index = 0;
				
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
		
		String query = "INSERT INTO " + tenant + ".sales_channels ( channel_name, active ) VALUES( '" + salesChannel + "', '" + active + "' );";
				
		index = mysql.execUpdate( query );
		
		mysql.close();
		
		this.load(env, tenant, filteredIds);
		
		return index;
		
	}
	
	public void load( Environment env, String tenant, ArrayList<Integer> filteredIds ) {
				
		this.list = new ArrayList<SalesChannels>();
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
		
		String query = "SELECT * FROM " + tenant + ".sales_channels;";
		
		ResultSet rs = mysql.execQuery( query );
		
		try {
		
			while( rs.next() ) {
				
				SalesChannels salesChannels = new SalesChannels( rs );
				this.list.add( salesChannels );
				
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
