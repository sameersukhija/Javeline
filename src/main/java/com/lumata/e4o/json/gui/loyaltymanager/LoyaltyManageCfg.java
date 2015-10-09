package com.lumata.e4o.json.gui.loyaltymanager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.JsonConfigurationElement;
import com.lumata.common.testing.json.JsonConfigurationFile;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.json.gui.campaignmanager.JSONCriteria;
import com.lumata.e4o.json.gui.campaignmanager.JSONEvent_;
import com.lumata.e4o.json.gui.loyaltymanager.JSONLoyaltiesCreation.LoyaltyTypes;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.json.common.JsonConfig;

public class LoyaltyManageCfg<JSONEvent1_> extends JsonConfig {
	private JsonConfig currentLoyaltyMgr;

public LoyaltyManageCfg(String folder, String file) throws JSONSException {
		
		super(folder, file);
	}


	/**
	 * This enum describes the type of loyalty program
	 */
	public enum LoyaltyTypes {
		
		/**
		 * Points type programs are based on classes
		 */
		Points,
		
		/**
		 * Badge type programs are awarding badges to subscribers
		 */
		Badges
	}
	
	/**
	 * This method returns the "type" of current element.
	 * The current element must be selected with "setCurrentElementById" method.
	 * 
	 * @return a <b>LoyaltyTypes</b> enum
	 * 
	 * @throws JSONSException
	 */
    
	/**
	 * It returns the "classes" array for "Points Program" type
	 * 
	 * @return List<String> of classes or an exception if current element is NOT a "Points Program" type
	 * 
	 * @throws JSONSException 
	 */
	
	/**
	 * It returns the "badges" array for "Badges Program" type
	 * 
	 * @return List<String> of classes or an exception if current element is NOT a "Badges Program" type
	 * @throws FormException 
	 * 
	 * @throws JSONSException 
	 */
	
	//public String getElementsSectionLabel() {

		//return "loyalties";
	//}

	public void setLoyaltyById( Integer currentLoyaltyMgrId ) throws JSONException, FormException {
		
		this.currentLoyaltyMgr = new JsonConfig( getList().getJSONObject( currentLoyaltyMgrId ) );
				
	}
	
	
/*
 * This is the current instance to be configured
 */
private JsonCurrentElement currentInstance2Configure = null;

/**
 * This method returns the "type" of current element.
 * The current element must be selected with "setCurrentElementById" method.
 * 
 * @return a <b>LoyaltyTypes</b> enum
 * 
 * @throws JSONSException
 */
//public LoyaltyTypes getType() throws JSONSException { 		
	
	//return LoyaltyTypes.valueOf(getCurrentElement().getStringFromPath( "type")); 		
	//}

	//public LoyaltyManageCfg(String path, String jsonFile) throws JSONSException, IOFileException {
		//super(path, jsonFile);
	//}
	
	public JSONArray getList() throws FormException, JSONException {
	
	return (JSONArray)getJSONArrayFromPath("loyalties");
			
}

	public Boolean getEnabled() throws  FormException, JSONException {
		return currentLoyaltyMgr.getBooleanFromPath( "enabled" );
	}


	public String getDefinitionName()  throws FormException, JSONException {
		return currentLoyaltyMgr.getStringFromPath("name");
	}

	public String getDefinitionDescription() throws FormException, JSONException {
		return currentLoyaltyMgr.getStringFromPath("description");
	}

	
	public String getDefinitionBadges()  throws FormException, JSONException {
		return currentLoyaltyMgr.getStringFromPath("badges");
	}
	
	public String getSchedulingRedeemDays()  throws FormException, JSONException {
		return currentLoyaltyMgr.getStringFromPath("RedeemDays");
	}
	

	public JSONArray getJSONArrayFromPath( String path ) throws JSONException {
		
		Object obj = getObjectFromPath( path );
		
		return ( !obj.equals( null ) ? new JSONArray( obj.toString() ) : null );
	
	}

	public JSONObject getJSONObjectFromPath( String path ) throws JSONException {
		
		Object obj = getObjectFromPath( path );
		
		return ( !obj.equals( null ) ? new JSONObject( obj.toString() ) : null );
	
	}
	
	public Map<String, JSONEvent_> getAwardedEvents() throws JSONException { 		
    	
		Map<String, JSONEvent_> awarded = new LinkedHashMap<String, JSONEvent_>();
		
		JSONArray jsonEvents = currentLoyaltyMgr.getJSONArrayFromPath( "awarded" );
		
		for( int j = 0; j < jsonEvents.length(); j++ ) {
			
			String eventName = "event" + j;
			
			awarded.put( eventName, new JSONEvent_( jsonEvents.getJSONObject( j ) ) );
			
		}
		
		return awarded;  		
	
	}
		
	
	public Map<String, JSONCriteria> getAwardedcriteria() throws JSONException { 		
    	
		Map<String, JSONCriteria> criteria = new LinkedHashMap<String, JSONCriteria>();
		
		JSONArray jsonEvents = currentLoyaltyMgr.getJSONArrayFromPath( "criteria" );
		
		for( int j = 0; j < jsonEvents.length(); j++ ) {
			
			String eventName = "event" + j;
			
			criteria.put( eventName, new JSONCriteria( jsonEvents.getJSONObject( j ) ) );
			
		}
		
		return criteria;  		
	
	}
		
	
	public Map<String, JSONEvent_> getRedeemedEvents() throws JSONException { 		
    	
		Map<String, JSONEvent_> redeemed = new LinkedHashMap<String, JSONEvent_>();
		
		JSONArray jsonEvents = currentLoyaltyMgr.getJSONArrayFromPath( "redeemed" );
		
		for( int j = 0; j < jsonEvents.length(); j++ ) {
			
			String eventName = "event" + j;
			
			redeemed.put( eventName, new JSONEvent_( jsonEvents.getJSONObject( j ) ) );
			
		}
		
		return redeemed;  		
	
	}
	
	
	public Object getObjectFromPath( String path ) {
		
		if( null != path && null != root ) {
			
			String[] subpathList = path.split("\\.");
			
			JSONObject current = root;
			
			int index = 0;
			
			for( String subpath : subpathList) {
				
				index++;
				
				if( index == subpathList.length ) {
					
					try {
						
						return current.get(subpath);
					
					} catch (JSONException e) {
						
						return null;
					
					}
					
				} else {
					
					try {
						
						current = current.getJSONObject(subpath);
					
					} catch (JSONException e) {
						
						return null;
					
					}
					
				}
			}
			
		}
		
		return null;
	}
	
	public void setObjectFromPath( String path, Object value ) {
		
		try {
			
			if( this.root.has( path ) ) { this.root.put( path , value ); }
		
		} catch( JSONException e ) {}
	
	}
	
	
	public List<String> getStringList(String key) throws JSONException {
		List<String> list = new ArrayList<String>();
		
		JSONArray jsonArray = root.getJSONArray(key);
		
		for (int i=0; i<jsonArray.length(); i++) {
			list.add( jsonArray.getString(i) );
		}
		
		return list;
	}
	
	public JSONEvent_ getEventByIndex( Integer eventIndex ) throws JSONException {
		
		return new JSONEvent_( currentLoyaltyMgr.getJSONArrayFromPath( "awarded" ).getJSONObject( eventIndex ) );
		
	}
	
	public JSONEvent_ getEventByName( String eventName ) throws JSONException, JSONSException {
		
		return getAwardedEvents().get( eventName );
		
	}
	

	public JSONEvent_ getEventByIndex1( Integer eventIndex ) throws JSONException {
		
		return new JSONEvent_( currentLoyaltyMgr.getJSONArrayFromPath( "redeemed" ).getJSONObject( eventIndex ) );
		
	}
	
	public JSONEvent_ getEventByName1( String eventName ) throws JSONException, JSONSException {
		
		return getRedeemedEvents().get( eventName );
		
	}
	
	public JSONObject getErrorActions() throws JSONException {
		return currentLoyaltyMgr.getJSONObjectFromPath( "errorActions" );
	}
	
	public void setcurrentLoyaltyMgrById( Integer currentLoyaltyMgrId )  throws FormException, JSONException {
		
		this.currentLoyaltyMgr = new JsonConfig( getList().getJSONObject( currentLoyaltyMgrId) );
				
	}
	
	public void setName( String name ) {
		setObjectFromPath( "name" , name );
	}


	
	public String getAwardedNotificationMessage() throws JSONException, JSONSException {
		return currentLoyaltyMgr.getStringFromPath("NotifMsg");
	}
	
	
	public String getRedeemedNotificationMessage() throws JSONException {
		return currentLoyaltyMgr.getStringFromPath("NotifMsg1");
	}

}