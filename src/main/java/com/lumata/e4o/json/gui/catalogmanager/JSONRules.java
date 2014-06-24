package com.lumata.e4o.json.gui.catalogmanager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.json.common.JsonConfig;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class JSONRules extends JsonConfig {

	private static final Logger logger = LoggerFactory.getLogger(JSONRules.class);

	public enum RuleValidity {
		seconds, minutes, hours, days
	}

	private JsonConfig currentRule;
	
	public JSONRules( String folder, String file ) throws JSONSException {
		
		super( folder, file );
			
	}

	public JSONArray getList() throws JSONException {		
		return (JSONArray)getJSONArrayFromPath("rules");				
	}
	
	public Boolean getEnabled() throws JSONException {
		return currentRule.getBooleanFromPath( "enabled" );
	}
	
	public String getName() {
		return currentRule.getStringFromPath( "name" );
	}
	
	public String getDescription() {
		return currentRule.getStringFromPath( "description" );
	}

	public String getTokenType() {
		return currentRule.getStringFromPath( "tokenType" );
	}
	
	public JSONArray getChannels() throws JSONException {		
		return (JSONArray)currentRule.getJSONArrayFromPath("channels");				
	}

	public JSONArray getMandatoryChannels() throws JSONException {		
		return (JSONArray)currentRule.getJSONArrayFromPath("mandatoryChannels");				
	}
	
	public String getOptimizationAlgorithm() {
		return currentRule.getStringFromPath( "optimizationAlgorithm" );		
	}

	public Boolean getKeepOffersConsistentAcrossMultipleRedraws() {
		return currentRule.getBooleanFromPath( "keepOffersConsistentAcrossMultipleRedraws" );		
	}

	public Boolean getIncludePreviouslyAcceptedOffers() {
		return currentRule.getBooleanFromPath( "includePreviouslyAcceptedOffers" );		
	}
	
	public Boolean getAllowDuplicateOffers() {
		return currentRule.getBooleanFromPath( "allowDuplicateOffers" );		
	}

	public Boolean getUnlimitedOffers() {
		return currentRule.getBooleanFromPath( "unlimitedOffers" );		
	}
	
	public String getMaximumNumberOfOffers() {
		return currentRule.getStringFromPath( "maximumNumberOfOffers" );		
	}

	public JSONObject getErrorActions() throws JSONException {
		return currentRule.getJSONObjectFromPath( "errorActions" );
	}
	
	public void setName( String name ) {
		setObjectFromPath( "name" , name );
	}
	
	public void setRuleById( Integer currentRule ) throws JSONException {
		
		this.currentRule = new JsonConfig( getList().getJSONObject( currentRule ) );
				
	}

}
