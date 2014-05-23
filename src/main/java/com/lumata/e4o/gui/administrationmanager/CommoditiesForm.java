package com.lumata.e4o.gui.administrationmanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumUtils.SearchBy;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.json.gui.administrationmanager.JSONCommodities;

public class CommoditiesForm extends AdministrationForm {

	/**
	 * 
	 */
	private static final Logger logger = LoggerFactory.getLogger(CommoditiesForm.class);
	
	/**
	 * 
	 */
	private JSONCommodities commoditiesCfg;
	
	/**
	 * 
	 * @param selenium
	 * @param commoditiesCfg
	 * @param timeout
	 * @param interval
	 */
	public CommoditiesForm( SeleniumWebDriver selenium, JSONCommodities commoditiesCfg, long timeout, long interval ) {
		
		super(selenium, timeout, interval);
		
		this.commoditiesCfg = commoditiesCfg;
	}
	
	/**
	 * 
	 */
	public CommoditiesForm open() throws FormException {
		
		super.open().clickId( "gwt-debug-Enter-Commodities" );
		
		return this;	
	}
	
	/**
	 * 
	 * @return
	 * @throws FormException
	 * @throws JSONSException
	 */
	public CommoditiesForm addCommoditiesList() throws FormException, JSONSException {
		
		int numbCommodities = commoditiesCfg.getList().size();
		
		for( int commodityIndex = 0; commodityIndex < numbCommodities; commodityIndex++ ) {
			
			commoditiesCfg.setCurrentElementById(commodityIndex);
			
			clickId( "gwt-debug-Add Bonus" ).
			createCommodity().
			saveCommodity();
					
		}
		
		return this;
	}

	/**
	 * 
	 * @return
	 * @throws FormException
	 * @throws JSONSException
	 */
	public CommoditiesForm createCommodity() throws FormException, JSONSException {
					
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
	
	/**
	 * 
	 * @return
	 * @throws FormException
	 */
	public CommoditiesForm saveCommodity() throws FormException {
		
		if ( containsErrorElement() )
			logger.error("Without click \"save\" panel is in error!");
		
		clickXPath( "//button[@title='Save']" );
		
		Boolean confirmed = Boolean.FALSE;

		// error condition
		//*[contains(@class,'errorBackground')]
		
		// key field
		//*[@id='gwt-debug-Bonus Name']
		
		// key field already present
//		"//div[text()='Bonus name already used']"
		
		confirmed = handleJavascriptAlertAcceptDismiss(true);

		// in case no confirmation was executed, check element in error
		if ( !confirmed && containsErrorElement() )
			logger.error("After click \"save\" panel is in error!");
		
		/**
		 * START REFACTORING - ERROR ACTIONS
		 */
		
		try {
		
			if( isDisplayed() ) { 
				
				clickId( "gwt-debug-Bonus Cancel" );
				
			}
		
		} catch( StaleElementReferenceException e ) {
			
			System.out.println( "button not visible" );
			
		}	
		
		/**
		 * END REFACTORING
		 */
		
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
	 * This method returns the presence of web component in error condition.
	 * The error condition is at application level for the actual form.
	 * (e.g. duplication of value, missing field) 
	 * 
	 * @return true if at least one element is in error
	 * 
	 * @throws FormException
	 */
	private Boolean containsErrorElement() throws FormException {
		
		Boolean resp = Boolean.TRUE;

		searchByXPath("//div[@class='gwt-DialogBox']");
		
		// error condition
		//*[contains(@class,'errorBackground')]
		List<WebElement> element = SeleniumUtils.findListForComponentDisplayed(selenium, SearchBy.XPATH, lastWebElement, "//*[contains(@class,'errorBackground')]");
		
		if ( element.size() != 0 )
			resp = true;
		else
			resp = false;
		
		return resp;
	}
	
	/**
	 * This method returns a list with <b>WebElement</b> in error condition.
	 * The error condition is at application level for the actual form.
	 * (e.g. duplication of value, missing field) 
	 * 
	 * @return a <b>List</b> of element in error condition.
	 * 
	 * @throws FormException
	 */
	private List<WebElement> getErrorElement() throws FormException {
		
		List<WebElement> resp = null;

		searchByXPath("//div[@class='gwt-DialogBox']");
		
		// error condition
		//*[contains(@class,'errorBackground')]
		resp = SeleniumUtils.findListForComponentDisplayed(selenium, SearchBy.XPATH, lastWebElement, "//*[contains(@class,'errorBackground')]");
		
		return resp;
	}
	
	/**
	 * This method delete a list of "Commodities" into running system.
	 * If input var-args is empty or null, this method deletes each commodities
	 * 
	 * @param CommoditiesNames
	 * 
	 * @return true if wanted commodities are corrected removed
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

		logger.debug("Commodities element to be deleted : " + commoditiesLabel);
		
		try {
			
			for (String cnName : commoditiesLabel) {
				
				String singleRule = "//div[text()='"+cnName+"']//ancestor::tr[1]//*[@name='btn-delete']";

				logger.debug("Try to delete \"Commodity\" with name + \""+cnName+"\".");
				
				clickXPath(singleRule);
				
				handleJavascriptAlertAcceptDismiss(true);
			}
			
			resp = Boolean.TRUE;
			
		} catch ( FormException e ) {

			logger.error("Error during delete \"Campaign Moldel \" : " + e.getMessage());
			
			resp = Boolean.FALSE;
		}

		return resp;
	}

	/**
	 * This method handles the popup warning displayed through Javascript.
	 * 
	 * The popup is the ones with Accept/Dismiss possible choice.
	 * 
	 * If input Boolean is TRUE will be pressed "Accept" condition otherwise "Dismiss". 
	 * 
	 * The returns Boolean describe if popup was displayed and pressed.
	 */
	private Boolean handleJavascriptAlertAcceptDismiss(Boolean accept) {
		
		Alert popupAlert = null;
		Boolean pressed = null;
		
		try {
			
			popupAlert = selenium.getWrappedDriver().switchTo().alert();
		    	
			if ( popupAlert != null ) { 
				
				if ( accept )
					popupAlert.accept();
				else 
					popupAlert.dismiss();
				
				pressed = Boolean.TRUE; 
			}
			
		} catch (NoAlertPresentException e) {
			
			// nothing to do
			pressed = Boolean.FALSE;
		}
		
		return pressed;
	}	
	
}
