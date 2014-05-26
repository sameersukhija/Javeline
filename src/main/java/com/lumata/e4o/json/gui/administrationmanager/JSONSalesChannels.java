package com.lumata.e4o.json.gui.administrationmanager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.json.common.JsonConfig;


/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class JSONSalesChannels extends JsonConfig {

	private JsonConfig currentSalesChannel;
	
	public JSONSalesChannels( String folder, String file ) throws JSONSException {
		
		super( folder, file );
			
	}

	public JSONArray getList() throws JSONException {		
		return (JSONArray)getJSONArrayFromPath("salesChannels");				
	}
	
	public Boolean getEnabled() throws JSONException {
		return currentSalesChannel.getBooleanFromPath( "enabled" );
	}
	
	public String getName() {
		return currentSalesChannel.getStringFromPath( "name" );
	}
	
	public Boolean getActive() {
		return currentSalesChannel.getBooleanFromPath( "active" );
	}
	
	public JSONObject getErrorActions() throws JSONException {
		return currentSalesChannel.getJSONObjectFromPath( "errorActions" );
	}
	
	public void setName( String name ) {
		setObjectFromPath( "name" , name );
	}
	
	public void setSalesChannelById( Integer currentSalesChannelIndex ) throws JSONException {
		
		this.currentSalesChannel = new JsonConfig( getList().getJSONObject( currentSalesChannelIndex ) );
				
	}
	
}
