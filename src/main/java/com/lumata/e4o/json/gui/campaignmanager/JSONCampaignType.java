package com.lumata.e4o.json.gui.campaignmanager;

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
public class JSONCampaignType extends JsonConfig {

	private static final Logger logger = LoggerFactory.getLogger(JSONCampaignType.class);

	private JsonConfig currentCampaignType;
	
	public JSONCampaignType( String folder, String file ) throws JSONSException {
		
		super( folder, file );
			
	}

	public JSONArray getList() throws JSONException {		
		return (JSONArray)getJSONArrayFromPath("campaignTypes");				
	}
	
	public Boolean getEnabled() throws JSONException {
		return currentCampaignType.getBooleanFromPath( "enabled" );
	}
	
	public String getTypeName() {
		return currentCampaignType.getStringFromPath( "typeName" );
	}
	
	public String getMaxOccurence() {
		return currentCampaignType.getStringFromPath( "maxOccurence" );
	}

	public String getMaxSimultaneous() {
		return currentCampaignType.getStringFromPath( "maxSimultaneous" );
	}
	
	public String getWaitingPeriodInDays() {
		return currentCampaignType.getStringFromPath( "waitingPeriodInDays" );		
	}

	public String getPredictionImpact() {
		return currentCampaignType.getStringFromPath( "predictionImpact" );		
	}

	public String getFullStatistics() {
		return currentCampaignType.getStringFromPath( "fullStatistics" );		
	}

	public JSONObject getMetaType() throws JSONException {
		return currentCampaignType.getJSONObjectFromPath( "metaType" );		
	}

	public String getMetaTypeMaxOccurence() throws JSONException {
		return currentCampaignType.getStringFromPath( "metaType.maxOccurence" );		
	}

	public String getMetaTypeMaxSimultaneous() throws JSONException {
		return currentCampaignType.getStringFromPath( "metaType.maxSimultaneous" );		
	}

	public String getMetaTypeWaitingPeriodInDays() throws JSONException {
		return currentCampaignType.getStringFromPath( "metaType.waitingPeriodInDays" );		
	}

	
	public JSONObject getErrorActions() throws JSONException {
		return currentCampaignType.getJSONObjectFromPath( "errorActions" );
	}
	
	public void setTypeName( String typeName ) {
		setObjectFromPath( "typeName" , typeName );
	}
	
	public void setCampaignTypeById( Integer currentCampaignType ) throws JSONException {
		
		this.currentCampaignType = new JsonConfig( getList().getJSONObject( currentCampaignType ) );
				
	}

}
