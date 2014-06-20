package com.lumata.e4o.json.gui.loyaltymanager;

import java.util.ArrayList;
import java.util.List;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.JsonConfigurationFile;

public class JSONLoyaltiesCreation extends JsonConfigurationFile {

	public JSONLoyaltiesCreation(String path, String jsonFile) throws JSONSException {
		
		super(path, jsonFile);
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
}
