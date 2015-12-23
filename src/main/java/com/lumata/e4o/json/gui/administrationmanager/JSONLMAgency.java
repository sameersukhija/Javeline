package com.lumata.e4o.json.gui.administrationmanager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.json.common.JsonConfig;

/**
 * @author <a href="mailto:sameer.sukhija@lumatagroup.com">Sameer Sukhija</a>
 * 
 */
public class JSONLMAgency extends JsonConfig {

	
	private JsonConfig currentAgency;
	
	public JSONLMAgency( String folder, String file ) throws JSONSException {
		
		super( folder, file );
			
	}

	public JSONArray getList() throws JSONException {
		
		return getJSONArrayFromPath( "Agencies" );
				
	}
	
	public Boolean getEnabled() throws JSONException {
		return currentAgency.getBooleanFromPath( "enabled" );
	}
	
	
	public String getName() throws JSONException {
		if( null == this.currentAgency ) { System.out.println( "NULL" ); }
		return currentAgency.getStringFromPath( "name" );
	}

	public String getaddress() throws JSONException {
		return currentAgency.getStringFromPath( "address" );
	}
	
	public String getphone() throws JSONException {
		return currentAgency.getStringFromPath( "phone" );
	}
	
		
		
	public JSONLMAgency getAgencyById( Integer currentAgencyId ) throws JSONException, JSONSException {
		
		setAgencyById( currentAgencyId );
		
		return this;
						
	}

	public void setAgencyById( Integer currentAgencyId ) throws JSONException, JSONSException {
		
		this.currentAgency = new JsonConfig( getList().getJSONObject( currentAgencyId  ) );
		if( null != this.currentAgency ) { System.out.println( "GOOODDDD" ); }				
	}
	
}
