package com.lumata.e4o.json.gui.catalogmanager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.json.common.JsonConfig;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class JSONOffers extends JsonConfig {

	private JsonConfig currentOffer;
		
	public JSONOffers( String folder, String file ) throws JSONSException {
		
		super( folder, file );
			
	}

	public JSONOffers( JSONObject offers ) throws JSONSException {
		
		super( offers );
			
	}
	
	public JSONArray getList() throws JSONException {		
		return (JSONArray)getJSONArrayFromPath("offers");				
	}
	
	public Boolean getEnabled() throws JSONException {
		return currentOffer.getBooleanFromPath( "enabled" );
	}	
	
	/** definition */
	public String getName() {
		return currentOffer.getStringFromPath( "definition.name" );
	}
	
	public String getDescription() {
		return currentOffer.getStringFromPath( "definition.description" );
	}

	public String getVoucher() {
		return currentOffer.getStringFromPath( "definition.voucher" );
	}
	
	public String getImageURLOfOffer() {
		return currentOffer.getStringFromPath( "definition.imageURLOfOffer" );
	}
	
	public String getTermsAndConditions() {
		return currentOffer.getStringFromPath( "definition.termsAndConditions" );
	}

	public void setOfferById( Integer currentOfferId ) throws JSONException {
		
		this.currentOffer = new JsonConfig( getList().getJSONObject( currentOfferId ) );
				
	}
	
	
	
	/*
	public JSONArray getList() throws JSONException {		
		return (JSONArray)getJSONArrayFromPath("offers");				
	}
	
	public Boolean getEnabled() throws JSONException {
		return currentOffer.getBooleanFromPath( "enabled" );
	}
		
	/** definition */
/*	public String getName() {
		return currentOffer.getStringFromPath( "definition.name" );
	}
	
	public String getDescription() {
		return currentOffer.getStringFromPath( "definition.description" );
	}

	public String getVoucher() {
		return currentOffer.getStringFromPath( "definition.voucher" );
	}
	
	public String getImageURLOfOffer() {
		return currentOffer.getStringFromPath( "definition.imageURLOfOffer" );
	}
	
	public String getTermsAndConditions() {
		return currentOffer.getStringFromPath( "definition.termsAndConditions" );
	}

	
	
	
	
	public JSONOffers getOfferById( Integer currentOfferId ) throws JSONException, JSONSException {
		
		setOfferById( currentOfferId );
		
		return this.currentOffer;
				
	}

	public void setOfferById( Integer currentOfferId ) throws JSONException, JSONSException {
		
		this.currentOffer = new JSONOffers( getList().getJSONObject( currentOfferId ) );
				
	}
	
	
	
	
	//
	
	
	public JSONArray getChannels() throws JSONException {		
		return (JSONArray)currentOffer.getJSONArrayFromPath("channels");				
	}
	
	public String getOptimizationAlgorithm() {
		return currentOffer.getStringFromPath( "optimizationAlgorithm" );		
	}

	public Boolean getKeepOffersConsistentAcrossMultipleRedraws() {
		return currentOffer.getBooleanFromPath( "keepOffersConsistentAcrossMultipleRedraws" );		
	}

	public Boolean getIncludePreviouslyAcceptedOffers() {
		return currentOffer.getBooleanFromPath( "includePreviouslyAcceptedOffers" );		
	}
	
	public Boolean getAllowDuplicateOffers() {
		return currentOffer.getBooleanFromPath( "allowDuplicateOffers" );		
	}

	public Boolean getUnlimitedOffers() {
		return currentOffer.getBooleanFromPath( "unlimitedOffers" );		
	}
	
	public String getMaximumNumberOfOffers() {
		return currentOffer.getStringFromPath( "maximumNumberOfOffers" );		
	}

	public JSONObject getErrorActions() throws JSONException {
		return currentOffer.getJSONObjectFromPath( "errorActions" );
	}
	
	public void setName( String name ) {
		setObjectFromPath( "name" , name );
	}
	

*/
}
