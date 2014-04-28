package com.lumata.expression.operators.json.catalogue;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.IOFileUtils.IOLoadingType;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.OfferException;


/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class OfferCfg {

	private static final  Logger logger = LoggerFactory.getLogger( OfferCfg.class );
	
	public enum TokenTypeValidity { seconds, minutes, hours, days }
		
	private JSONObject offCfg;
	
	public OfferCfg( JSONObject tokenType ) {
		
		this.offCfg = tokenType;
				
	}
	
	public OfferCfg( String offerCfg, IOLoadingType loadingType ) throws OfferException {
		
		try {
			
			switch( loadingType ) {
			
				case FILE: { this.offCfg = JSONUtils.loadJSONFile( offerCfg.toLowerCase() + Format.JSON_EXTENSION ); break; }
				case RESOURCE: { this.offCfg = JSONUtils.loadJSONResource( offerCfg.toLowerCase() + Format.JSON_EXTENSION ); break;  }
				default: throw new OfferException( "You cannot load an Offer from resources different by FILE or RESOURCE" );
			
			}		
									
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new OfferException( e.getMessage(), e );
			
		} 			
			
	}
	
	public OfferCfg( String folder, String offerCfg, IOLoadingType loadingType ) throws OfferException {
		
		try {
			
			switch( loadingType ) {
			
				case FILE: { this.offCfg = JSONUtils.loadJSONFile( folder, offerCfg.toLowerCase() + Format.JSON_EXTENSION ); break; }
				case RESOURCE: { this.offCfg = JSONUtils.loadJSONResource( folder, offerCfg.toLowerCase() + Format.JSON_EXTENSION ); break;  }
				default: throw new OfferException( "You cannot load an Offer from resources different by FILE or RESOURCE" );
			
			}
									
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new OfferException( e.getMessage(), e );
			
		} 		
			
	}
	
	
	/*
	public static ArrayList<OfferCfg> createTokenTypeList(  String folder, String tokenType, IOLoadingType loadingType ) throws OfferException {
		
		ArrayList<OfferCfg> list = new ArrayList<OfferCfg>();
		
		try {
			
			JSONObject token_type_list;
			
			switch( loadingType ) {
			
				case FILE: { token_type_list = JSONUtils.loadJSONFile( folder, tokenType.toLowerCase() + Format.JSON_EXTENSION ); break; }
				case RESOURCE: { token_type_list = JSONUtils.loadJSONResource( folder, tokenType.toLowerCase() + Format.JSON_EXTENSION ); break;  }
				default: throw new OfferException( "You cannot load an Offer from resources different by FILE or RESOURCE" );
			
			}
									
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new OfferException( e.getMessage(), e );
			
		} catch( IOFileException e ) {			
			
			logger.error( e.getMessage(), e );
			
			throw new OfferException( e.getMessage(), e );
			
		} 
		
		return list;
		
	}
	*/
	
	
	public JSONObject getDefinition() {
		
		try {
			
			if( !offCfg.isNull("definition") ) { return offCfg.getJSONObject("definition"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getOfferName() {
		
		try {
			
			if( !getDefinition().isNull("offer_name") ) { return getDefinition().getString("offer_name"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getOfferDescription() {
		
		try {
			
			if( !getDefinition().isNull("offer_description") ) { return getDefinition().getString("offer_description"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getImageUrlOfOffer() {
		
		try {
			
			if( !getDefinition().isNull("image_url_of_offer") ) { return getDefinition().getString("image_url_of_offer"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getPreviousOfTheOfferImage() {
		
		try {
			
			if( !getDefinition().isNull("previous_of_the_offer_image") ) { return getDefinition().getString("previous_of_the_offer_image"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getTermsAndConditions() {
		
		try {
			
			if( !getDefinition().isNull("terms_and_conditions") ) { return getDefinition().getString("terms_and_conditions"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public JSONObject getPrices() {
		
		try {
		
			if( !offCfg.isNull("prices") ) { return offCfg.getJSONObject("prices"); }
		
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public JSONArray getPriceChannels() {
		
		try {
		
			if( !getPrices().isNull("channels") ) { return getPrices().getJSONArray("channels"); }
		
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

	public JSONObject getAvailability() {
		
		try {
		
			if( !offCfg.isNull("availability") ) { return offCfg.getJSONObject("availability"); }
		
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getAvailabilityGlobalStockValue() {
		
		try {
		
			if( !getAvailability().isNull("stock_availability") ) { return getAvailability().getString("stock_availability"); }
		
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public JSONArray getAvailabilityChannels() {
		
		try {
		
			if( !getAvailability().isNull("channels_availability") ) { return getAvailability().getJSONArray("channels_availability"); }
		
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getAvailabilityChannelName( JSONObject availability_channel ) {
		
		try {
		
			if( !availability_channel.isNull("channel_name") ) { return availability_channel.getString("channel_name"); }
		
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getAvailabilityChannelValue( JSONObject availability_channel ) {
		
		try {
		
			if( !availability_channel.isNull("channel_value") ) { return availability_channel.getString("channel_value"); }
		
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public JSONObject getErrorActions() {
		
		try {
		
			if( !offCfg.isNull("error_actions") ) { return offCfg.getJSONObject("error_actions"); }
		
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

	public void setOfferName( String name ) {
		
		try {
			
			if( !getDefinition().isNull("offer_name") ) { getDefinition().put( "offer_name", name ); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
	}
	
	/*
	public String getFormat() {
		
		try {
			
			if( !ttCfg.isNull("format") ) { return ttCfg.getString("format"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

	public String getValidityUnit() {
		
		try {
			
			if( !ttCfg.isNull("validityUnit") ) { return ttCfg.getString("validityUnit"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

	public String getValidity() {
		
		try {
			
			if( !ttCfg.isNull("validity") ) { return ttCfg.getString("validity"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public boolean getUsageUnlimited() {
		
		try {
			
			if( !ttCfg.isNull("usageUnlimited") ) { return ttCfg.getBoolean("usageUnlimited"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return true;
		
	}
	
	public String getUsage() {
		
		try {
			
			if( !ttCfg.isNull("usage") ) { return ttCfg.getString("usage"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	*/
}
