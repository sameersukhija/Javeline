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
import com.lumata.e4o.gui.catalogmanager.OffersForm;
import com.lumata.e4o.gui.common.Form;
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
	
	public CommoditiesForm clickPaymentMeanTab() throws FormException, JSONSException {
		clickXPath( "//div[4]/table/tbody/tr/td/table/tbody/tr/td[3]/table/tbody/tr[2]/td[2]/div/div/div" );
		waitForPageLoad();
		return this;
		
	}
	
	
	
	public CommoditiesForm setCommodityPaymentMean(String currency) throws FormException, JSONSException {
		
		typeByXPath( "//div[text()='Add Payment Mean']//ancestor::table//tr[2]//table[@class='tableList Form']//tbody/tr[4]//td/input", currency);
		
		return this;
	}
	
	
	
	public String getCommodityPaymentMean() throws FormException {
		
		return super.getValueByXPath("//div[text()='Add Payment Mean']//ancestor::table//tr[2]//table[@class='tableList Form']//tbody/tr[4]//td/input");
		
	}
	public CommoditiesForm clickAddPaymentMeanButton() throws FormException{
		clickXPath("//div[text()='Payment Mean']//ancestor::table[@class='gwt-DecoratedTabPanel tab-CommoditiesPageView']//table[contains(@class,'page-BillablePageView')]//tbody/tr//button[@title='Add']");
		return this;
	}
	public CommoditiesForm setCommodityPaymentType(String getpaymentType) throws FormException, JSONSException {
		
		if ( !"Internal".equals(getpaymentType) )
		selectByXPathAndVisibleText("//div[text()='Add Payment Mean']//ancestor::table//tr[2]//table[@class='tableList Form']//tbody/tr[1]//td/select", getpaymentType);		
		
		return this;
	}
	
	public String getCommodityPaymentType() throws FormException {
		
		return super.getValueByXPath("//div[text()='Add Payment Mean']//ancestor::table//tr[2]//table[@class='tableList Form']//tbody/tr[1]//td/select");
		
	}
	public CommoditiesForm setCommodityPaymentAccount(String getCommodityAccountName) throws FormException, JSONSException {
		selectByXPathAndVisibleText("//div[text()='Add Payment Mean']//ancestor::table//tr[2]//table[@class='tableList Form']//tbody/tr[2]//td/select", getCommodityAccountName);
		return this;
	}

	public String getCommodityPaymentAccount() throws FormException {
		
		return super.getValueByXPath("//div[text()='Add Payment Mean']//ancestor::table//tr[2]//table[@class='tableList Form']//tbody/tr[2]//td/select");
		
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
	
	public CommoditiesForm saveCommodityPaymentMean() throws FormException, JSONSException {
		
		clickXPath("//div[text()='Add Payment Mean']//ancestor::table//tr[2]//table[contains(@class,'buttonPanel')]//tbody/tr[1]//td[2]//button[@title='Save']");
		handleJavascriptAlertAcceptDismiss(true);
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
		
					
			List<WebElement> cmLabels = searchListByXPath(rootPath, rootPath + subPath);
			
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
	
	
	public Boolean deleteCommoditiesPaymentMean(String... CommoditiesNames) throws FormException {

		List<String> commoditiesLabel = null;
		Boolean resp = Boolean.FALSE;
		
		if ( CommoditiesNames != null && CommoditiesNames.length != 0 )
			commoditiesLabel = Arrays.asList(CommoditiesNames);
		
		else { // fetch every commodities present on UI
			
			commoditiesLabel = new ArrayList<String>();
			
			String rootPath = "//div[4]/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr/td/table";
			String subPath = "/tbody/tr/td[2]/button";
		
			List<WebElement> cmLabels = searchListByXPath(rootPath, rootPath + subPath);
			clickXPath( "//div[4]/table/tbody/tr/td/table/tbody/tr/td[3]/table/tbody/tr[2]/td[2]/div/div/div" );
			
			for (WebElement webElement : cmLabels)
				commoditiesLabel.add(webElement.getText());
		}

		logger.debug("Commodities element to be deleted : " + commoditiesLabel);
		
		try {
			
			for (String cnName : commoditiesLabel) {
				
				String singleRule = "//tr[3]/td/div/div/table/tbody/tr[5]/td[3]/table/tbody/tr/td[2]/button";

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
				getCurrentElement().modifyStringFromPath("name", name);
				
				nameElem.sendKeys(getCurrentElement().getStringFromPath("name"));
				
				saveAction();
				
				resp = Boolean.TRUE;
				
			} catch ( NoSuchElementException | JSONSException | FormException e ) {
				
				e.printStackTrace();
				
				resp = Boolean.FALSE;
			}
			
			return resp;
		}
		
	}

	public Boolean editCommodities(String... CommoditiesNames) throws FormException {
		List<String> commoditiesLabel = null;
		Boolean resp = Boolean.FALSE;
		
		if ( CommoditiesNames != null && CommoditiesNames.length != 0 )
			commoditiesLabel = Arrays.asList(CommoditiesNames);
		else { // fetch every commodities present on UI
			
			commoditiesLabel = new ArrayList<String>();
			
			String rootPath = "//table[contains(@class, \"page-BonusPageView\")]";
			String subPath = "//tr[contains(@class,\"contentRow cycle\")]//td[@class=\"column_description\"][1]";
			
			
			List<WebElement> cmLabels = searchListByXPath(rootPath, rootPath + subPath);
			
			for (WebElement webElement : cmLabels)
				commoditiesLabel.add(webElement.getText());
		}

		logger.debug("Commodities element to be edited : " + commoditiesLabel);
		
		try {
			
			for (String cnName : commoditiesLabel) {
				
				String singleRule = "//div[text()='"+cnName+"']//ancestor::tr[1]//*[@name='btn-edit']";

				logger.debug("Try to edit \"Commodity\" with name + \""+cnName+"\".");
				
				clickXPath(singleRule);
				
				handleJavascriptAlertAcceptDismiss(true);
			}
			
			resp = Boolean.TRUE;
			
		} catch ( FormException e ) {

			logger.error("Error during edit \"Commodities \" : " + e.getMessage());
			
			resp = Boolean.FALSE;
		}

		return resp;
	}
		



//	public Boolean editCommoditiesPaymentMean(String... CommoditiesNames) throws FormException, JSONSException {
//		List<String> commoditiesLabel = null;
//		Boolean resp = Boolean.FALSE;
//	
//		if ( CommoditiesNames != null && CommoditiesNames.length != 0 )
//		commoditiesLabel = Arrays.asList(CommoditiesNames);
//		else { // fetch every commodities present on UI
//		
//		commoditiesLabel = new ArrayList<String>();
//		
//		String rootPath = "//div[4]/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr/td/table";
//		String subPath = "/tbody/tr/td[2]/button";
//		clickXPath( "//div[4]/table/tbody/tr/td/table/tbody/tr/td[3]/table/tbody/tr[2]/td[2]/div/div/div" );
//		
//		List<WebElement> cmLabels = searchListByXPath(rootPath, rootPath + subPath);
//		
//		for (WebElement webElement : cmLabels)
//			commoditiesLabel.add(webElement.getText());
//	}
//
//		logger.debug("Commodities element to be edited : " + commoditiesLabel);
//	
//		try {
//		
//		for (String cnName : commoditiesLabel) {
//			
//			//String singleRule = "//div[text()='"+cnName+"']//ancestor::tr[1]//*[@name='btn-edit']";
//			clickXPath("//tr[3]/td/div/div/table/tbody/tr[5]/td[3]/table/tbody/tr/td/button");
//			String singleRule = "//tr[4]/td[2]/input";
//			
//			logger.debug("Try to edit \"Commodity\" with name + \""+cnName+"\".");
//			
//			typeByXPath(singleRule, commoditiesCfg.getCurrencyType());
//			
//			handleJavascriptAlertAcceptDismiss(true);
//		}
//		
//		resp = Boolean.TRUE;
//		
//		} catch ( FormException e ) {
//
//		logger.error("Error during edit \"Commodities \" : " + e.getMessage());
//		
//		resp = Boolean.FALSE;
//		}
//
//		return resp;
//}
	
	public CommoditiesForm clickEditCommoditiesPaymentMeanbutton(String currency) throws FormException, JSONSException {
		
		String editXpath="//div[text()='"+currency+"']//ancestor::tr[1]//td[3]//button[@title='Edit']";
		clickXPath(editXpath);
			
		return this;
	}	
	
	
	public CommoditiesForm setEditCommodityPaymentMean(String getEditCurrencyType) throws FormException, JSONSException {
		
		typeByXPath("//div[text()='Edit payment mean']//ancestor::table//tr[2]//table[@class='tableList Form']//tbody/tr[4]//td/input", getEditCurrencyType);
		return this;
	}
	
	
	
	public String getEditCommodityPaymentMean() throws FormException {
		
		return super.getValueByXPath("//div[text()='Edit payment mean']//ancestor::table//tr[2]//table[@class='tableList Form']//tbody/tr[4]//td/input");
		
	}

	public CommoditiesForm saveEditCommodityPaymentMean() throws FormException, JSONSException {
		clickXPath("//div[text()='Edit payment mean']//ancestor::table//tr[2]//table[contains(@class,'buttonPanel')]//tbody/tr[1]//td[2]//button[@title='Save']");
		handleJavascriptAlertAcceptDismiss(true);
		
		return this;
	}
	
}
