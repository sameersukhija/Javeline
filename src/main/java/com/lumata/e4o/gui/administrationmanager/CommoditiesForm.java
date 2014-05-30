package com.lumata.e4o.gui.administrationmanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorActionType;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorConditionType;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
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
	 * It configures "Commodities Form" according passed Json configuration
	 * 
	 * @return
	 * 
	 * @throws FormException
	 * @throws JSONSException
	 */
	public CommoditiesForm addCommoditiesList() throws FormException, JSONSException {
		
		int numbCommodities = commoditiesCfg.getList().size();
		
		for( int commodityIndex = 0; commodityIndex < numbCommodities; commodityIndex++ ) {
			
			commoditiesCfg.setCurrentElementById(commodityIndex);
			
			/**
			 * Only "enabled" commodities will be configured
			 */
			if ( commoditiesCfg.getCurrentElement().getEnabled() ) {
				clickId( "gwt-debug-Add Bonus" );
				
				createCommodity().
				saveCommodity();
			}
					
		}
		
		return this;
	}

	/**
	 * It creates a single commodity
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
	 * It saves just created commodity and handles post-saving results
	 * 
	 * @return
	 * 
	 * @throws FormException
	 * @throws JSONSException 
	 */
	public CommoditiesForm saveCommodity() throws FormException, JSONSException {
		
		if ( containsErrorElement() )
			logger.error("Without click \"save\" panel is in error!");
		
		Boolean completed = Boolean.FALSE;
		
		do {
			
			/**
			 * Core save procedure
			 */
			
			clickXPath( "//button[@title='Save']" );
			
			Boolean confirmed = Boolean.FALSE;

			// error condition
			//*[contains(@class,'errorBackground')]
			
			// key field
			//*[@id='gwt-debug-Bonus Name']
			
			// key field already present
//			"//div[text()='Bonus name already used']"
			
			confirmed = handleJavascriptAlertAcceptDismiss(true);

			/**
			 * End - Core save procedure
			 */
			
			// in case no confirmation was executed, check element in error
			if ( !confirmed && containsErrorElement() ) {

				JsonCurrentElement current = commoditiesCfg.getCurrentElement();
				
				logger.warn("After click \"Save\" panel is in error!");
				
				ElementErrorConditionType condition = null;
				
				searchByXPath("//div[@class='gwt-DialogBox']");
				
				// error condition
				//div[text()='Bonus name already used']
				List<WebElement> element = SeleniumUtils.findListForComponentDisplayed(	selenium, 
																						SearchBy.XPATH, 
																						lastWebElement, 
																						"//div[text()='Bonus name already used']"
																					);
				
				if ( element.size() != 0 )
					condition = ElementErrorConditionType.ELEMENT_AREADY_EXISTS;
				else
					condition = ElementErrorConditionType.GENERAL_ERROR;
				
				ElementErrorActionType action = current.getErrorActions().getAction(condition);
				
				// abort insertion
				if ( action.equals(ElementErrorActionType.ABORT_CANCEL) ) {
					clickId( "gwt-debug-Bonus Cancel" );
					
					completed = Boolean.TRUE;
				}
				// stop execution and return error
				else if ( action.equals(ElementErrorActionType.RETURN_ERROR) )
					throw new FormException(getClass().getSimpleName() + " cannot configure \""+current.getStringFromPath("name")+"\" commodities!");
				// add timestamp to name
				else if ( action.equals(ElementErrorActionType.ADD_TIMESTAMP_TO_FIELD) ) {
					
					// one click su input
					clickXPath( "//input[@id='gwt-debug-Bonus Name']");
					
					// delete current text
					lastWebElement.clear();
					
					String actualName = current.getStringFromPath("name");
					current.setObjectFromPath("name", actualName + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
					
					// send old text + timestamp
					typeById( "gwt-debug-Bonus Name", commoditiesCfg.getName() );
					
					completed = Boolean.FALSE;
				}
			}
			else // all ok!
				completed = Boolean.TRUE;			
		}
		while ( !completed );

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

			logger.error("Error during delete \"Commodities \" : " + e.getMessage());
			
			resp = Boolean.FALSE;
		}

		return resp;
	}
	
	
}
