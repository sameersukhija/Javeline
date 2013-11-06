package com.lumata.expression.operators.json.catalogue;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.IOFileUtils.IOLoadingType;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.validating.Format;
import com.lumata.expression.operators.exceptions.RuleException;
import com.lumata.expression.operators.exceptions.TokenTypeException;


/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class RuleCfg {

	private static final  Logger logger = LoggerFactory.getLogger( RuleCfg.class );
	
	public enum RuleValidity { seconds, minutes, hours, days }
	
	private JSONObject rlCfg;
	
	public RuleCfg( JSONObject tokenType ) {
		
		this.rlCfg = tokenType;
				
	}
	
	public RuleCfg( String rule, IOLoadingType loadingType ) throws RuleException {
		
		try {
			
			switch( loadingType ) {
			
				case FILE: { this.rlCfg = JSONUtils.loadJSONFile( rule.toLowerCase() + Format.JSON_EXTENSION ); break; }
				case RESOURCE: { this.rlCfg = JSONUtils.loadJSONResource( rule.toLowerCase() + Format.JSON_EXTENSION ); break;  }
				default: throw new RuleException( "You cannot load an Campaign from resources different by FILE or RESOURCE" );
			
			}		
									
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new RuleException( e.getMessage(), e );
			
		} catch( IOFileException e ) {			
			
			logger.error( e.getMessage(), e );
			
			throw new RuleException( e.getMessage(), e );
			
		} 			
			
	}
	
	public RuleCfg( String folder, String rule, IOLoadingType loadingType ) throws RuleException {
		
		try {
			
			switch( loadingType ) {
			
				case FILE: { this.rlCfg = JSONUtils.loadJSONFile( folder, rule.toLowerCase() + Format.JSON_EXTENSION ); break; }
				case RESOURCE: { this.rlCfg = JSONUtils.loadJSONResource( folder, rule.toLowerCase() + Format.JSON_EXTENSION ); break;  }
				default: throw new RuleException( "You cannot load an Campaign from resources different by FILE or RESOURCE" );
			
			}
									
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new RuleException( e.getMessage(), e );
			
		} catch( IOFileException e ) {			
			
			logger.error( e.getMessage(), e );
			
			throw new RuleException( e.getMessage(), e );
			
		} 			
			
	}
	
	public static ArrayList<RuleCfg> createTokenTypeList(  String folder, String rule, IOLoadingType loadingType ) throws RuleException {
		
		ArrayList<RuleCfg> list = new ArrayList<RuleCfg>();
		
		try {
			
			JSONObject token_type_list;
			
			switch( loadingType ) {
			
				case FILE: { token_type_list = JSONUtils.loadJSONFile( folder, rule.toLowerCase() + Format.JSON_EXTENSION ); break; }
				case RESOURCE: { token_type_list = JSONUtils.loadJSONResource( folder, rule.toLowerCase() + Format.JSON_EXTENSION ); break;  }
				default: throw new RuleException( "You cannot load an Campaign from resources different by FILE or RESOURCE" );
			
			}
									
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new RuleException( e.getMessage(), e );
			
		} catch( IOFileException e ) {			
			
			logger.error( e.getMessage(), e );
			
			throw new RuleException( e.getMessage(), e );
			
		} 
		
		return list;
		
	}
	
	public String getName() {
		
		try {
			
			if( !rlCfg.isNull("name") ) { return rlCfg.getString("name"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getDescription() {
		
		try {
			
			if( !rlCfg.isNull("description") ) { return rlCfg.getString("description"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}	
	
	public String getTokenTypeName() {
		
		try {
			
			if( !rlCfg.isNull("tokenTypeName") ) { return rlCfg.getString("tokenTypeName"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

	public JSONArray getChannelNameList() {
		
		try {
			
			if( !rlCfg.isNull("channelNamesList") ) { return rlCfg.getJSONArray("channelNamesList"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

	public String getOfferSelectionAlgorithmId() {
		
		try {
			
			if( !rlCfg.isNull("offerSelectionAlgorithmId") ) { return rlCfg.getString("offerSelectionAlgorithmId"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

	public JSONArray getRequestorIdsList() {
		
		try {
			
			if( !rlCfg.isNull("requestorIdsList") ) { return rlCfg.getJSONArray("requestorIdsList"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

	public Boolean getKeepOffersConsistent() {
		
		try {
			
			if( !rlCfg.isNull("keepOffersConsistent") ) { return rlCfg.getBoolean("keepOffersConsistent"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

	public Boolean getPreviousOffersDrawnIncluded() {
		
		try {
			
			if( !rlCfg.isNull("previousOffersDrawnIncluded") ) { return rlCfg.getBoolean("previousOffersDrawnIncluded"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

	public Boolean getDuplicatedOfferWithinSingleDrawEnabled() {
		
		try {
			
			if( !rlCfg.isNull("duplicatedOfferWithinSingleDrawEnabled") ) { return rlCfg.getBoolean("duplicatedOfferWithinSingleDrawEnabled"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

	public Boolean getNumOfOffersToDrawUnlimited() {
		
		try {
			
			if( !rlCfg.isNull("numOfOffersToDrawUnlimited") ) { return rlCfg.getBoolean("numOfOffersToDrawUnlimited"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

	public Integer getNumOfOffersToDraw() {
		
		try {
			
			if( !rlCfg.isNull("numOfOffersToDraw") ) { return rlCfg.getInt("numOfOffersToDraw"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

	public Integer getAllocationExpiration() {
		
		try {
			
			if( !rlCfg.isNull("allocationExpiration") ) { return rlCfg.getInt("allocationExpiration"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

	public String getAllocationExpirationUnit() {
		
		try {
			
			if( !rlCfg.isNull("allocationExpirationUnit") ) { return rlCfg.getString("allocationExpirationUnit"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
}
