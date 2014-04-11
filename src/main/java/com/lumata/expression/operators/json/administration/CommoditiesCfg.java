package com.lumata.expression.operators.json.administration;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.expression.operators.exceptions.CommoditiesException;
import com.lumata.expression.operators.json.common.JsonConfig;


/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class CommoditiesCfg extends JsonConfig {

	private static final  Logger logger = LoggerFactory.getLogger( CommoditiesCfg.class );
	
	private String currentCommodity;
	
	public CommoditiesCfg( String folder, String file ) throws CommoditiesException, JSONSException, IOFileException {
		
		super( folder, file );
			
	}

	public JSONObject getCommoditiesList() throws JSONException {
		return (JSONObject)getObjectFromPath("commodities");
	}
	
	public ArrayList<String> list() {
		
		ArrayList<String> commoditiesList = new ArrayList<String>();
				
		Iterator<?> keys = ((JSONObject)getObjectFromPath("commodities")).keys();
        while( keys.hasNext() ){
            String key = (String)keys.next();
            commoditiesList.add( key );
        }
		
		return commoditiesList;
		
	}	 
		
    public String getType() { 		
    	return getStringFromPath( "commodities/" + currentCommodity + "/type"); 		
 	}
    	 
    public String getName() { 		
    	return getStringFromPath("commodities/" + currentCommodity + "/name"); 		
	}

	public String getAccount() { 		
    	return getStringFromPath("commodities/" + currentCommodity + "/account"); 		
 	}
	
	public String getAccountType() { 		
    	return getStringFromPath("commodities/" + currentCommodity + "/account_type"); 		
 	}
	
	public String getUnit() { 		
    	return getStringFromPath("commodities/" + currentCommodity + "/unit"); 		
 	}

	public String getDefaultValidityType() { 		
    	return getStringFromPath("commodities/" + currentCommodity + "/default_validity_type"); 		
 	}
	
	public String getDefaultPeriodType() { 		
    	return getStringFromPath("commodities/" + currentCommodity + "/default_period_type");
	}
	
	public String getDefaultQuantityPeriod() { 		
		return getStringFromPath("commodities/" + currentCommodity + "/default_quantity_period");
	}
	
	public String getUnitaryCost() { 		
		return getStringFromPath("commodities/" + currentCommodity + "/unitary_cost");
	}
	
	public String getListPrice() { 		
		return getStringFromPath("commodities/" + currentCommodity + "/list_price");
	}
	
	public void setCurrentCommodity( String currentCommodity ) {
		
		this.currentCommodity = currentCommodity;
				
	}

}
