package com.lumata.e4o.gui.administration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.json.administration.JSONCommodities;

public class CommoditiesForm extends AdministrationForm {

	private static final Logger logger = LoggerFactory.getLogger(CommoditiesForm.class);
	
	private JSONCommodities commoditiesCfg;
	
	public CommoditiesForm( SeleniumWebDriver selenium, JSONCommodities commoditiesCfg, long timeout, long interval ) {
		
		super(selenium, timeout, interval);
		
		this.commoditiesCfg = commoditiesCfg;
		
	}
	
	public CommoditiesForm open() throws FormException {
		
		super.open().clickId( "gwt-debug-Enter-Commodities" );
		
		return this;
		
	}
	
	public CommoditiesForm addCommoditiesList() throws FormException {
		
		ArrayList<String> commodities = commoditiesCfg.list();
		
		for( int commodityIndex = 0; commodityIndex < commodities.size(); commodityIndex++ ) {
			
			commoditiesCfg.setCurrentCommodity( commodities.get( commodityIndex ) );
			
			clickId( "gwt-debug-Add Bonus" ).
			createCommodity().
			saveCommodity();
					
		}
		
		return this;
		
	}

	public CommoditiesForm createCommodity() throws FormException {
					
		selectById( "gwt-debug-Bonus Type", commoditiesCfg.getType() );

		if ( !"Internal".equals(commoditiesCfg.getType()) )
			selectById( "gwt-debug-Bonus Account", commoditiesCfg.getAccount() );
		
		typeById( "gwt-debug-Bonus Name", commoditiesCfg.getName() ).
		selectById( "gwt-debug-Bonus Account Type", commoditiesCfg.getAccountType() ).
		selectById( "gwt-debug-Bonus Unit", commoditiesCfg.getUnit() ).
		selectById( "gwt-debug-Bonus Default Validity Type", commoditiesCfg.getDefaultValidityType() ).
		selectById( "gwt-debug-Bonus Default Period Type", commoditiesCfg.getDefaultPeriodType() ).
		typeById( "gwt-debug-Bonus Default Quantity Period", commoditiesCfg.getDefaultQuantityPeriod() ).
		typeById( "gwt-debug-Bonus Unitary Cost", commoditiesCfg.getUnitaryCost() );
		typeById( "gwt-debug-Bonus List Price", commoditiesCfg.getListPrice() );
		
		return this;
	}
	
	public CommoditiesForm saveCommodity() throws FormException {
		
		clickId( "gwt-debug-Bonus Save" );
		
		try {
		
			if( isDisplayed() ) { 
				
				clickId( "gwt-debug-Bonus Cancel" );
				
			}
		
		} catch( StaleElementReferenceException e ) {
			
			System.out.println( "button not visible" );
			
		}	
		
		return this;
		
	}
	
	@Override
	public CommoditiesForm clickId( String id ) throws FormException {
		
		super.clickId( id );
		
		return this;
		
	}
	
	public JSONCommodities getCommodityFormCfg() {
		return this.commoditiesCfg;
	}
	
	public void setCommodityFormCfg( JSONCommodities commoditiesCfg ) {
		this.commoditiesCfg = commoditiesCfg;
	}
	
	/**
	 * This method delete a list of "Commodities" into running system.
	 * If input var-args is empty or null, this method deletes each commodities
	 * 
	 * @param CommoditiesNames
	 * 
	 * @return true if wanted campaign model are corrected removed
	 * 
	 * @throws FormException 
	 */
	public Boolean deleteCommodities(String... CommoditiesNames) throws FormException {

		List<String> commoditiesLabel = null;
		Boolean resp = Boolean.FALSE;
		
		if ( CommoditiesNames != null && CommoditiesNames.length != 0 )
			commoditiesLabel = Arrays.asList(CommoditiesNames);
		else { // fetch every commodities present on UI
			
			commoditiesLabel = new ArrayList<String>();
			
			String rootPath = "//table[contains(@class, \"page-BonusPageView\")]";
			String subPath = "//tr[contains(@class,\"contentRow cycle\")]//td[@class=\"column_description\"][1]";
		
			List<WebElement> cmLabels = getListByXPath(rootPath, rootPath + subPath);
			
			for (WebElement webElement : cmLabels)
				commoditiesLabel.add(webElement.getText());
		}

		logger.debug("Campaign labels to be deleted : " + commoditiesLabel);
		
		try {
			
			for (String cnName : commoditiesLabel) {
				
				String singleRule = "//div[text()='"+cnName+"']//ancestor::tr[1]//*[@name='btn-delete']";

				logger.debug("Try to delete \"Campaign Moldel \" + \""+cnName+"\".");
				
				clickXPath(singleRule);
			}
			
			resp = Boolean.TRUE;
			
		} catch ( FormException e ) {

			logger.error("Error during delete \"Campaign Moldel \" : " + e.getMessage());
			
			resp = Boolean.FALSE;
		}

		return resp;
	}	
	
}
