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
import com.lumata.e4o.json.gui.campaignmanager.JSONEvent_;
import com.lumata.e4o.json.gui.loyaltymanager.JSONLoyaltiesCreation.LoyaltyTypes;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.json.common.JsonConfig;

public class LoyaltyManageCfg extends JsonConfig {
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
	 * 
	 * @throws JSONSException 
	 */
	
	//public String getElementsSectionLabel() {

		//return "loyalties";
	//}

	public void setLoyaltyById( Integer currentLoyaltyMgrId ) throws JSONException {
		
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
	
	public JSONArray getList() throws JSONException {
	
	return (JSONArray)getJSONArrayFromPath("loyalties");
			
}

	public Boolean getEnabled() throws JSONException {
		return currentLoyaltyMgr.getBooleanFromPath( "enabled" );
	}


	public String getDefinitionName() throws JSONException {
		return currentLoyaltyMgr.getStringFromPath("name");
	}

	public String getDefinitionDescription() throws JSONException {
		return currentLoyaltyMgr.getStringFromPath("description");
	}

	
	public String getDefinitionBadges() throws JSONException {
		return currentLoyaltyMgr.getStringFromPath("badges");
	}
	
	public String getSchedulingRedeemDays() throws JSONException {
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
		
		return new JSONEvent_( currentLoyaltyMgr.getJSONArrayFromPath( "events" ).getJSONObject( eventIndex ) );
		
	}
	
	public JSONEvent_ getEventByName( String eventName ) throws JSONException, JSONSException {
		
		return getAwardedEvents().get( eventName );
		
	}
	

	public JSONObject getErrorActions() throws JSONException {
		return currentLoyaltyMgr.getJSONObjectFromPath( "errorActions" );
	}
	
	public void setcurrentLoyaltyMgrById( Integer currentLoyaltyMgrId ) throws JSONException {
		
		this.currentLoyaltyMgr = new JsonConfig( getList().getJSONObject( currentLoyaltyMgrId) );
				
	}
	
	public void setName( String name ) {
		setObjectFromPath( "name" , name );
	}


	public String getAwardedEventType() throws JSONException {
		return currentLoyaltyMgr.getStringFromPath("eventType");
	}


	public String getAwardedCriteriaType() throws JSONException, JSONSException {
		return currentLoyaltyMgr.getStringFromPath("criteriaType");
	}

	public String getAwardedCriteriaValue() throws JSONException, JSONSException {
		return currentLoyaltyMgr.getStringFromPath("criteriaValue");
	}

	public String  getAwardedActionType() throws JSONException, JSONSException {
		return currentLoyaltyMgr.getStringFromPath("actionType");
	}

	public String getAwardedOption() throws JSONException, JSONSException {
		return currentLoyaltyMgr.getStringFromPath("option");
	}
	
	public String getAwardedNotificationMessage() throws JSONException, JSONSException {
		return currentLoyaltyMgr.getStringFromPath("NotifMsg");
	}
	
	public String getRedeemedEventType() throws JSONException {
		return currentLoyaltyMgr.getStringFromPath("eventType1");
	}

	public String getRedeemedCriteriaType() throws JSONException {
		return currentLoyaltyMgr.getStringFromPath("criteriaType1");
	}

	public String getRedeemedCriteriaValue() throws JSONException {
		return currentLoyaltyMgr.getStringFromPath("criteriaValue1");
	}

	public String getRedeemedActionType() throws JSONException {
		return currentLoyaltyMgr.getStringFromPath("actionType1");
	}

	public String getRedeemedOption() throws JSONException {
		return currentLoyaltyMgr.getStringFromPath("option1");
	}
	
	public String getRedeemedNotificationMessage() throws JSONException {
		return currentLoyaltyMgr.getStringFromPath("NotifMsg1");
	}

}