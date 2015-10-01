package com.lumata.e4o.json.gui.loyaltymanager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.JsonConfigurationFile;
import com.lumata.e4o.json.common.JsonConfig;
import com.lumata.e4o.json.gui.campaignmanager.JSONEvent_;

public class JSONLoyaltiesCreation extends JsonConfigurationFile {

		public JSONLoyaltiesCreation(String folder, String file) throws JSONSException {
		
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
    public LoyaltyTypes getType() throws JSONSException { 		
    	
		return LoyaltyTypes.valueOf(getCurrentElement().getStringFromPath( "type")); 		
 	}
    	
    /**
	 * This method returns the "name" of current element.
	 * The current element must be selected with "setCurrentElementById" method.
	 * 
     * @return
     * @throws JSONSException
     */
    public String getName() throws JSONSException { 	
    	
		return getCurrentElement().getStringFromPath("name"); 		
	}	
    
    public String geteventType() throws JSONSException { 	
    	
		return getCurrentElement().getStringFromPath("eventType"); 		
	}	
    
    public String getcriteriaType() throws JSONSException { 	
    	
		return getCurrentElement().getStringFromPath("criteriaType"); 		
	}	
    
    public String getcriteriaValue() throws JSONSException { 	
    	
		return getCurrentElement().getStringFromPath("criteriaValue"); 		
	}	
    
    public String getactionType() throws JSONSException { 	
    	
		return getCurrentElement().getStringFromPath("actionType"); 		
	}	
    
    public String getactionValue() throws JSONSException { 	
    	
		return getCurrentElement().getStringFromPath("option"); 		
	}	
   
    public Boolean getEnabled() throws JSONException {
		return getCurrentElement().getBooleanFromPath( "enabled" );
	}
    /**
	 * This method returns the "description" of current element.
	 * The current element must be selected with "setCurrentElementById" method.
	 * 
     * @return
     */
	public String getDescription() {
		
		return getCurrentElement().getStringFromPath( "description" );
	}
	
	/**
	 * It returns the "classes" array for "Points Program" type
	 * 
	 * @return List<String> of classes or an exception if current element is NOT a "Points Program" type
	 * 
	 * @throws JSONSException 
	 */
	public List<String> getClasses() throws JSONSException {
		
		if ( !getType().equals(LoyaltyTypes.Points) )
			throw new JSONSException("The loyalty \""+this.getName()+"\" cannot have the \"classes\", it is a \""+getType()+" Program\"!");
		
		List<String> resp = null;
		
		try {
			List<Object> raw = getCurrentElement().getJsonListFromPath("classes");
			
			resp = new ArrayList<String>();
			
			for (Object object : raw) 
				resp.add(object.toString());
		}
		catch ( JSONSException e ) {
			
			if ( e.getMessage().contains("Error during looking of \"Array of value\" for \"classes\"!") )
				resp = null;
			else
				throw e;
		}
		
		return resp;
	}
	
	/**
	 * It returns the "badges" array for "Badges Program" type
	 * 
	 * @return List<String> of classes or an exception if current element is NOT a "Badges Program" type
	 * 
	 * @throws JSONSException 
	 */
	public List<String> getBadges() throws JSONSException {
		
		if ( !getType().equals(LoyaltyTypes.Badges) )
			throw new JSONSException("The loyalty \""+this.getName()+"\" cannot have the \"badges\", it is a \""+getType()+" Program\"!");
		
		List<String> resp = null;
		
		try {
			List<Object> raw = getCurrentElement().getJsonListFromPath("badges");
				
			resp = new ArrayList<String>();
			
			for (Object object : raw) 
				resp.add(object.toString());
		}
		catch ( JSONSException e ) {
			
			if ( e.getMessage().contains("Error during looking of \"Array of value\" for \"badges\"!") )
				resp = null;
			else
				throw e;
		}
		
		return resp;
	}	
	
	@Override
	public String getElementsSectionLabel() {

		return "loyalties";
	}

		public Map<String, JSONEvent_> getEvents() throws JSONException, JSONSException { 		
    	
		Map<String, JSONEvent_> events = new LinkedHashMap<String, JSONEvent_>();
		
		List<Object> jsonEvents = getCurrentElement().getJsonListFromPath( "events" );
		
		for( int j = 0; j < ((JSONArray) jsonEvents).length(); j++ ) {
			
			String eventName = "event" + j;
			
			events.put( eventName, new JSONEvent_( ((JSONArray) jsonEvents).getJSONObject( j ) ) );
			
		}
		
		return events;  		
	
	}

		
		
	
		
}
