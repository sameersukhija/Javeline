package com.lumata.e4o.json.gui.catalogmanager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.json.common.JsonConfig;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class JSONRules extends JsonConfig {

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
	
	public JSONArray getRuleChannelsAsArray() throws JSONException {	
		
		ArrayList<String> ruleChannels = new ArrayList<String>();
		
		JSONArray ruleChannelsCfg = currentRule.getJSONArrayFromPath( "channels" );
		
		for( int c = 0; c < ruleChannelsCfg.length(); c++ ) {
			
			ruleChannels.add( new JSONRuleChannel( ruleChannelsCfg.getJSONObject( c ) ).getName() );
			
		}
		
		return new JSONArray( ruleChannels.toString() );
	
	}
	
	public Map<String, JSONRuleChannel> getRuleChannels() throws JSONException {	
		
		Map<String, JSONRuleChannel> ruleChannels = new LinkedHashMap<String, JSONRuleChannel>();
		
		JSONArray ruleChannelsCfg = currentRule.getJSONArrayFromPath( "channels" );
		
		for( int c = 0; c < ruleChannelsCfg.length(); c++ ) {
			
			String ruleChannelName = "ruleChannel" + c;
			
			ruleChannels.put( ruleChannelName, new JSONRuleChannel( ruleChannelsCfg.getJSONObject( c ) ) );
			
		}
		
		return ruleChannels;  		
						
	}

	public JSONRuleChannel getRuleChannelByIndex( Integer ruleChannelIndex ) throws JSONException {
		
		return new JSONRuleChannel( getJSONArrayFromPath( "channels" ).getJSONObject( ruleChannelIndex ) );
		
	}
	
	public JSONRuleChannel getRuleChannelByName( String ruleChannelName ) throws JSONException {
		
		return getRuleChannels().get( ruleChannelName );
		
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
	
	public String getMaximumNumberOfOffers() {
		return String.valueOf( currentRule.getIntegerFromPath( "maximumNumberOfOffers" ) );		
	}
	
	public String getExpiredOfferBehaviour() {
		return currentRule.getStringFromPath( "expiredOfferBehaviour" );		
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
