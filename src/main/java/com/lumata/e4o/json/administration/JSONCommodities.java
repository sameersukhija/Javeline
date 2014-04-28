package com.lumata.e4o.json.administration;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.exceptions.CommoditiesException;
import com.lumata.expression.operators.json.common.JsonConfig;


/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class JSONCommodities extends JsonConfig {

	private String currentCommodity;
	
	public JSONCommodities( String folder, String file ) throws CommoditiesException, JSONSException, IOFileException {
		
		super( folder, file );
			
	}

	public JSONObject getCommoditiesList() throws JSONException {
		return (JSONObject)getObjectFromPath("commodities");
	}
	
	public ArrayList<String> list() {
		
		ArrayList<String> commoditiesList = new ArrayList<String>();
				
		Iterator<?> keys = ((JSONObject)getObjectFromPath("commodities")).keys();
        while( keys.hasNext() ) {
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
    	return getStringFromPath("commodities/" + currentCommodity + "/accountType"); 		
 	}
	
	public String getUnit() { 		
    	return getStringFromPath("commodities/" + currentCommodity + "/unit"); 		
 	}

	public String getDefaultValidityType() { 		
    	return getStringFromPath("commodities/" + currentCommodity + "/defaultValidityType"); 		
 	}
	
	public String getDefaultPeriodType() { 		
    	return getStringFromPath("commodities/" + currentCommodity + "/defaultPeriodType");
	}
	
	public String getDefaultQuantityPeriod() { 		
		return getStringFromPath("commodities/" + currentCommodity + "/defaultQuantityPeriod");
	}
	
	public String getUnitaryCost() { 		
		return getStringFromPath("commodities/" + currentCommodity + "/unitaryCost");
	}
	
	public String getListPrice() { 		
		return getStringFromPath("commodities/" + currentCommodity + "/listPrice");
	}
	
	public void setCurrentCommodity( String currentCommodity ) {
		
		this.currentCommodity = currentCommodity;
				
	}

}
