package com.lumata.expression.operators.json.administration;

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
import com.lumata.expression.operators.exceptions.CampaignModelException;
import com.lumata.expression.operators.exceptions.CommoditiesException;


/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class CommoditiesCfg {

	private static final  Logger logger = LoggerFactory.getLogger( CommoditiesCfg.class );
	
	private JSONObject cmCfg;
	
	public enum CMLoadingType { FILE, RESOURCE }
	
	/* Create an CampaignModel from a JSONObject */
	public CommoditiesCfg( JSONObject Commodities ) {
		
		this.cmCfg = Commodities;
				
	}
	
	public CommoditiesCfg( String commodities, IOLoadingType loadingType ) throws CommoditiesException {
		
		try {
			
			switch( loadingType ) {
			
				case FILE: { this.cmCfg = JSONUtils.loadJSONFile( commodities.toLowerCase() + Format.JSON_EXTENSION ); break; }
				case RESOURCE: { this.cmCfg = JSONUtils.loadJSONResource( commodities.toLowerCase() + Format.JSON_EXTENSION ); break;  }
				default: throw new CommoditiesException( "You cannot load an CampaignModel from resources different by FILE or RESOURCE" );
			
			}		
									
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new CommoditiesException( e.getMessage(), e );
			
		} catch( IOFileException e ) {			
			
			logger.error( e.getMessage(), e );
			
			throw new CommoditiesException( e.getMessage(), e );
			
		} 			
			
	}
		
	public CommoditiesCfg( String folder, String commodities, IOLoadingType loadingType ) throws CommoditiesException {
		
		try {
			
			switch( loadingType ) {
			
			case FILE: { this.cmCfg = JSONUtils.loadJSONFile( folder, commodities.toLowerCase() + Format.JSON_EXTENSION ); break; }
			case RESOURCE: { this.cmCfg = JSONUtils.loadJSONResource( folder, commodities.toLowerCase() + Format.JSON_EXTENSION ); break;  }
			default: throw new CommoditiesException( "You cannot load an CampaignModel from resources different by FILE or RESOURCE" );
		
		}
									
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new CommoditiesException( e.getMessage(), e );
			
		} catch( IOFileException e ) {			
			
			logger.error( e.getMessage(), e );
			
			throw new CommoditiesException( e.getMessage(), e );
			
		} 			
			
	}
		
	
	// CampaignModel ATTRIBUTES
    public String getType() {
 		
 		try {
 			
 			if( !cmCfg.isNull("type") ) { return cmCfg.getString("type"); }
 		
 		} catch( Exception e ) {

 			logger.error( e.getMessage(), e );
 			
 		}
 		
 		return null;
 		
 	}
    	 
    public String getName() {
		
		try {
			
			if( !cmCfg.isNull("name") ) { return cmCfg.getString("name"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

	public String getAccount() {
		
		try {
			
			if( !cmCfg.isNull("account") ) { return cmCfg.getString("account"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getAccountType() {
		
		try {
			
			if( !cmCfg.isNull("account_type") ) { return cmCfg.getString("account_type"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getUnit() {
		
		try {
			
			if( !cmCfg.isNull("unit") ) { return cmCfg.getString("unit"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

	public String getDefaultValidityType() {
		
		try {
			
			if( !cmCfg.isNull("default_validity_type") ) { return cmCfg.getString("default_validity_type"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getDefaultPeriodType() {
		
		try {
			
			if( !cmCfg.isNull("default_period_type") ) { return cmCfg.getString("default_period_type"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getDefaultQuantityPeriod() {
		
		try {
			
			if( !cmCfg.isNull("default_quantity_period") ) { return cmCfg.getString("default_quantity_period"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getUnitaryCost() {
		
		try {
			
			if( !cmCfg.isNull("unitary_cost") ) { return cmCfg.getString("unitary_cost"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getListPrice() {
		
		try {
			
			if( !cmCfg.isNull("list_price") ) { return cmCfg.getString("list_price"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public void setName( String value ) {
		
		try {
			
			cmCfg.put( "name", value );
		
		} catch( JSONException e ) {

			logger.error( e.getMessage(), e );
			
		}
				
	}

	
	
}
