package com.lumata.e4o.gui.administrationmanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorConditionType;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.FormSaveConfigurationHandler;
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
		
		CommoditiesSaveHandler handler = new CommoditiesSaveHandler( 	selenium.getWrappedDriver(), 
																		commoditiesCfg.getCurrentElement());		
		handler.saveAction();

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
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	private class CommoditiesSaveHandler extends FormSaveConfigurationHandler {

		protected CommoditiesSaveHandler(	WebDriver inDriver,
											JsonCurrentElement inCurrentElement) {
			
			super(inDriver, inCurrentElement);
		}

		@Override
		protected Boolean containsErrorElement() {

			Integer numbers = null;
			Boolean resp = Boolean.TRUE;
	
			// error condition
			//*[contains(@class,'errorBackground')]
			
			List<WebElement> elements = getWebDriver().findElements(By.xpath("//*[contains(@class,'errorBackground')]"));
	
			if ( elements == null )
				numbers = 0;
			else
				numbers = elements.size();
			
			if ( numbers != 0 )
				resp = true;
			else
				resp = false;
			
			return resp;
		}

		/**
		 * 
		 */
		private WebElement saveElement = null;
		
		@Override
		protected WebElement getSaveWebElement() {

			if ( saveElement == null )
				saveElement = getWebDriver().findElement(By.xpath("//button[@title='Save']")); 
			
			return saveElement;
		}

		@Override
		protected ElementErrorConditionType defineErrorCondition() {

			ElementErrorConditionType condition = null;
			
			WebElement dialogBox = getWebDriver().findElement(By.xpath("//div[@class='gwt-DialogBox']"));
			
			// error condition
			//div[text()='Bonus name already used']			
			List<WebElement> element = dialogBox.findElements(By.xpath("//div[text()='Bonus name already used']"));
								
			if ( element.size() != 0 )
				condition = ElementErrorConditionType.ELEMENT_AREADY_EXISTS;
			else
				condition = ElementErrorConditionType.GENERAL_ERROR;

			return condition;
		}

		@Override
		protected Boolean cancelAction() {

			Boolean resp = Boolean.FALSE;

			try {

				getWebDriver().findElement(By.id("gwt-debug-Bonus Cancel")).click();
					
				resp = Boolean.TRUE;
			}
			catch ( NoSuchElementException e ) {
			
				e.printStackTrace();
				
				resp = Boolean.FALSE;
			}
			
			return resp;
		}

		@Override
		protected Boolean addTimestampAction() {
			
			Boolean resp = Boolean.FALSE;
			
			try {
				
				WebElement nameElem = getWebDriver().findElement(By.xpath("//input[@id='gwt-debug-Bonus Name']"));
				nameElem.click();
				nameElem.clear();
				
				String name = getCurrentElement().getStringFromPath("name");
				name += TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
				getCurrentElement().setObjectFromPath("name", name);
				
				nameElem.sendKeys(getCurrentElement().getStringFromPath("name"));
				
				saveAction();
				
				resp = Boolean.TRUE;
				
			} catch ( NoSuchElementException | FormException e ) {
				
				e.printStackTrace();
				
				resp = Boolean.FALSE;
			}
			
			return resp;
		}
		
	}
	
	
}
