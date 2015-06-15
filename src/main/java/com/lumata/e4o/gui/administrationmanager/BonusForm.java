package com.lumata.e4o.gui.administrationmanager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorConditionType;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.FormSaveConfigurationHandler;
import com.lumata.e4o.json.gui.administrationmanager.JSONCommodities;

public class BonusForm extends AdministrationForm {

	private JSONCommodities commoditiesCfg;

	/**
	 * 
	 * @param selenium
	 * @param commoditiesCfg
	 * @param timeout
	 * @param interval
	 */
	public BonusForm(SeleniumWebDriver selenium,
			JSONCommodities commoditiesCfg, long timeout, long interval) {

		super(selenium, timeout, interval);

		this.commoditiesCfg = commoditiesCfg;
	}

	public BonusForm open() throws FormException {

		super.open().clickId("gwt-debug-Enter-Commodities");

		return this;
	}

	public BonusForm clickAddBonus() throws FormException {
		super.clickId("gwt-debug-Add Bonus");
		return this;
	}

	public BonusForm selectType(String type) throws FormException {
		super.selectById("gwt-debug-Bonus Type", type);
		return this;
	}

	public BonusForm enterName(String name) throws FormException {
		super.typeById("gwt-debug-Bonus Name", name);
		return this;
	}

	public BonusForm selectAccountType(String strAccountType)
			throws FormException {
		super.selectById("gwt-debug-Bonus Account Type", strAccountType);
		return this;
	}

	public BonusForm selectUnit(String strUnit) throws FormException {
		super.selectById("gwt-debug-Bonus Unit", strUnit);
		return this;
	}

	public BonusForm selectDefaultValidityType(String strDefaultValidityType)
			throws FormException {
		super.selectById("gwt-debug-Bonus Default Validity Type",
				strDefaultValidityType);
		return this;
	}
	
	public BonusForm selectDefaultPeriodStart(String strDefaultPeriodStart)
			throws FormException {
		super.selectById("gwt-debug-Bonus Default Period Start",
				strDefaultPeriodStart);
		return this;
	}


	public BonusForm selectPeriodType(String strDefaultPeriodType)
			throws FormException {
		super.selectById("gwt-debug-Bonus Default Period Type",
				strDefaultPeriodType);
		return this;
	}

	public BonusForm enterDefaultQuantityPeriod(String strDefaultQuantityPeriod)
			throws FormException {
		super.typeById("gwt-debug-Bonus Default Quantity Period",
				strDefaultQuantityPeriod);
		return this;
	}

	public BonusForm enterUnitaryCost(String strUnitaryCost)
			throws FormException {
		super.typeById("gwt-debug-Bonus Unitary Cost", strUnitaryCost);
		return this;
	}

	public BonusForm enterListPrice(String strListPrice) throws FormException {
		super.typeById("gwt-debug-Bonus List Price", strListPrice);
		return this;
	}
	
	public BonusForm clickButtonLimit() throws FormException
	{
		super.clickName("btn-limit");
		return this;
	}
	
	public BonusForm enterBalanclimitValue(String strValue) throws FormException
	{
		super.typeByXPath("//input[contains(@class,'gwt-TextBox')]", strValue);
		return this;
	}
	
	public BonusForm clickEdit() throws FormException
	{
		super.clickName("btn-edit");
		return this;
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
	

	/**
	 * It saves just created commodity and handles post-saving results
	 * 
	 * @return
	 * 
	 * @throws FormException
	 * @throws JSONSException 
	 */
	public BonusForm saveCommodity() throws FormException, JSONSException {
		
		CommoditiesSaveHandler handler = new CommoditiesSaveHandler( 	selenium.getWrappedDriver(), 
																		commoditiesCfg.getCurrentElement());		
		handler.saveAction();

		return this;	
	}
	

}
