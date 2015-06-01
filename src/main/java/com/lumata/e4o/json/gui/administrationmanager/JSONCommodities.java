package com.lumata.e4o.json.gui.administrationmanager;

import java.util.ArrayList;
import java.util.List;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.JsonConfigurationFile;

/**
 * 
 */
public class JSONCommodities extends JsonConfigurationFile {
	
	/**
	 * 
	 * @param folder
	 * @param file
	 * @throws JSONSException
	 */
	public JSONCommodities( String folder, String file ) throws JSONSException {
		
		super( folder, file );
	}
	
	/**
	 * This method returns the "type" of current element.
	 * The current element must be selected with "setCurrentElementById" method.
	 * 
	 * @return
	 * 
	 * @throws JSONSException
	 */
    public String getType() throws JSONSException { 		
		return getCurrentElement().getStringFromPath( "type"); 		
 	}
    	
    /**
	 * This method returns the "name" of current element.
	 * The current element must be selected with "setCurrentElementById" method.
	 * 
     * @return
     * @throws JSONSException
     */
    public String getName() throws JSONSException { 		
		return getCurrentElement().getStringFromPath("name"); 		
	}
    public String getDelete() throws JSONSException { 		
		return getCurrentElement().getStringFromPath("delete"); 		
	}

    
    /**
     * 
     * @return
     * @throws JSONSException
     */
	public String getAccount() throws JSONSException { 		
		return getCurrentElement().getStringFromPath("account"); 		
 	}
	
	/**
	 * 
	 * @return
	 * @throws JSONSException
	 */
	public String getAccountType() throws JSONSException { 		
		return getCurrentElement().getStringFromPath("accountType"); 		
 	}
	
	/**
	 * 
	 * @return
	 * @throws JSONSException
	 */
	
	
	public String getNewCurrency() throws JSONSException { 		
		return getCurrentElement().getStringFromPath("currency"); 		
 	}
	
	public String getUnit() throws JSONSException { 		
		return getCurrentElement().getStringFromPath("unit"); 		
 	}

	/**
	 * 
	 * @return
	 * @throws JSONSException
	 */
	public String getDefaultValidityType() throws JSONSException { 		
		return getCurrentElement().getStringFromPath("defaultValidityType"); 		
 	}

	/**
	 * 
	 * @return
	 * @throws JSONSException
	 */
	public String getDefaultPeriodStart() throws JSONSException { 		
		return getCurrentElement().getStringFromPath("defaultPeriodStart");
	}	
	
	/**
	 * 
	 * @return
	 * @throws JSONSException
	 */	
	public String getDefaultPeriodType() throws JSONSException { 		
		return getCurrentElement().getStringFromPath("defaultPeriodType");
	}
	
	/**
	 * 
	 * @return
	 * @throws JSONSException
	 */	
	public String getDefaultQuantityPeriod() throws JSONSException { 		
		return getCurrentElement().getStringFromPath("defaultQuantityPeriod");
	}
	
	/**
	 * 
	 * @return
	 * @throws JSONSException
	 */	
	public String getUnitaryCost() throws JSONSException { 		
		return getCurrentElement().getStringFromPath("unitaryCost");
	}
	
	/**
	 * 
	 * @return
	 * @throws JSONSException
	 */	
	public String getListPrice() throws JSONSException { 		
		return getCurrentElement().getStringFromPath("listPrice");
	}
	
	/**
	 * It returns a list of commodities name into Json file
	 * 
	 * @return List<String>
	 * 
	 * @throws JSONSException
	 */
	public List<String> getListName() throws JSONSException {
		
		List<String> resp = new ArrayList<>();
		
		int numbComm = getList().size();
		
		for (int index = 0; index < numbComm; index++) {
			setCurrentElementById(index);
			resp.add(getName());
		}
		
		return resp;
	}
	
	@Override
	public String getElementsSectionLabel() {

		return "commodities";
	}
}