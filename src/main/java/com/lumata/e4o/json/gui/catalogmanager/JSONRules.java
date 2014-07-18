package com.lumata.e4o.json.gui.catalogmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.HasErrorActions.JsonErrorActions;
import com.lumata.common.testing.json.JsonConfigurationElement;
import com.lumata.common.testing.json.JsonConfigurationFile;

public class JSONRules extends JsonConfigurationFile {

	/**
	 * 
	 * @param folder
	 * @param file
	 * @throws JSONSException
	 */
	public JSONRules( String folder, String file ) throws JSONSException {
		
		super( folder, file );		
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {

		return getCurrentElement().getStringFromPath( "name" );
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDescription() {
		
		return getCurrentElement().getStringFromPath( "description" );
	}

	/**
	 * 
	 * @return
	 */
	public String getTokenType() {
		
		return getCurrentElement().getStringFromPath( "tokenType" );
	}
	
	/**
	 * 
	 * @return
	 * @throws JSONSException
	 */
	@SuppressWarnings("unchecked")
	public List<JSONChannel> getChannels() throws JSONSException {
		
		List<JSONChannel> resp = new ArrayList<>();
		
		List<Object> raw = getCurrentElement().getJsonListFromPath("channels");
		
		for (Object object : raw)
			resp.add(new JSONChannel((Map<String, Object>) object));
		
		return resp;				
	}
	
	/**
	 * 
	 * @return
	 */
	public String getOptimizationAlgorithm() {
		
		return getCurrentElement().getStringFromPath( "optimizationAlgorithm" );		
	}

	/**
	 * 
	 * @return
	 */
	public Boolean getKeepOffersConsistentAcrossMultipleRedraws() {
		
		return getCurrentElement().getBooleanFromPath( "keepOffersConsistentAcrossMultipleRedraws" );		
	}

	/**
	 * 
	 * @return
	 */
	public Boolean getIncludePreviouslyAcceptedOffers() {
		
		return getCurrentElement().getBooleanFromPath( "includePreviouslyAcceptedOffers" );		
	}
	
	/**
	 * 
	 * @return
	 */
	public Integer getMaximumNumberOfOffers() {
		
		return getCurrentElement().getIntegerFromPath( "maximumNumberOfOffers" );		
	}
	
	/**
	 * 
	 * @param name
	 * @throws JSONSException
	 */
	public void setName( String name ) throws JSONSException {
		
		getCurrentElement().modifyStringFromPath( "name" , name );
	}
	
	/**
	 * 
	 * @return
	 * @throws JSONSException 
	 */
	public JsonErrorActions getErrorActions() throws JSONSException {

		return getCurrentElement().getErrorActions();
	}	
	
	/**
	 * 
	 */
	public class JSONChannel extends JsonConfigurationElement {

		public JSONChannel(Map<String, Object> newObject) {
			
			super(newObject);
		}
		
		/**
		 * Channel name
		 * 
		 * @return
		 */
		public String getName() {
			
			return getStringFromPath("name");
		}
		
		/**
		 * Mandatory
		 * 
		 * @return
		 */
		public Boolean isMandatory() {
			
			return getBooleanFromPath("mandatory");
		}
		
		/**
		 * Unlimited offer
		 * 
		 * @return
		 */
		public Boolean isUnlimited() {
			
			return getBooleanFromPath( "unlimited" );		
		}
		
		/**
		 * Get max offers from this channel
		 */
		public Integer getMaxOffers() {
			
			return getIntegerFromPath("maxOffer");
		}
	}

	@Override
	public String getElementsSectionLabel() {

		return "rules";
	}
}
