package com.lumata.e4o.json.gui.administrationmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.JsonConfigurationElement;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.json.common.JsonConfig;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers.JSONPricesElement;
import com.lumata.e4o.json.gui.loyaltymanager.JSONLoyaltiesCreation.LoyaltyTypes;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class JSONLMGroup extends JsonConfig {

	
	private JsonConfig currentGroup;
	private Object object;
	private List<String> resp;
	
	
	public JSONLMGroup( String folder, String file ) throws JSONSException {
		
		super( folder, file );
			
	}

	public JSONArray getList() throws JSONException {
		
		return (JSONArray)getJSONArrayFromPath( "groups" );
				
	}
	public Boolean getEdit() throws JSONException {
		return currentGroup.getBooleanFromPath( "edit" );
	}
	
	public Boolean getEnabled() throws JSONException {
		return currentGroup.getBooleanFromPath( "enabled" );
	}
	
	public String getName() throws JSONException {
		if( null == this.currentGroup ) { System.out.println( "NULL" ); }
		return currentGroup.getStringFromPath( "name" );
	}

	public Boolean getIsRemovable() throws JSONException {
		return currentGroup.getBooleanFromPath( "isRemovable" );
	}
	
	public Boolean getHasAgencies() throws JSONException {
		return currentGroup.getBooleanFromPath( "hasAgencies" );
	}
	
	public Boolean getCanUsersBeAdded() throws JSONException {
		return currentGroup.getBooleanFromPath( "canUsersBeAdded" );
	}
	
	public Boolean getCanBeSelectedInGroupList() throws JSONException {
		return currentGroup.getBooleanFromPath( "canBeSelectedInGroupList" );
	}

	public List<String> getTabs() throws JSONSException {
		
		List<String> resp = null;
	   	
		List<String> raw = currentGroup.getStringList("tabs");
			
		resp = new ArrayList<String>();
		
		for (Object object : raw) 
				resp.add(object.toString());
		
		return resp;
	}
	
	
	public JSONLMGroup getGroupById( Integer currentGroupId ) throws JSONException, JSONSException {
		
		setGroupById( currentGroupId );
		
		return this;
						
	}

	public void setGroupById( Integer currentGroupId ) throws JSONException, JSONSException {
		
		this.currentGroup = new JsonConfig( getList().getJSONObject( currentGroupId ) );
		if( null != this.currentGroup ) { System.out.println( "GOOODDDD" ); }				
	}
	
}
