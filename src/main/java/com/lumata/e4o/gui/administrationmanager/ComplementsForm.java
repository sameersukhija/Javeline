package com.lumata.e4o.gui.administrationmanager;

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
import com.lumata.e4o.json.gui.administrationmanager.JSONComplements;

public class ComplementsForm extends AdministrationForm {


	/**
	 * 
	 */
	private static final Logger logger = LoggerFactory.getLogger(ComplementsForm.class);
	
	/**
	 * 
	 */
	private JSONComplements complementsCfg;
		
	/**
	 * 
	 * @param selenium
	 * @param timeout
	 * @param interval
	 */
	public ComplementsForm(SeleniumWebDriver selenium, JSONComplements complementsCfg, long timeout, long interval) {
		
		super(selenium, timeout, interval);
		
		this.complementsCfg = complementsCfg;
	}

	
	/**
	 * 
	 */
	public ComplementsForm open() throws FormException {
		
		super.open().clickId( "gwt-debug-actrule-administration-composites" );
		
		return this;	
	}
		
	
	/**
	 * 
	 * @return
	 * @throws FormException
	 * @throws JSONSException
	 */
	public ComplementsForm addComplementsList() throws FormException, JSONSException {
		
		int numbComplements = complementsCfg.getList().size();
		
		for( int commodityIndex = 0; commodityIndex < numbComplements; commodityIndex++ ) {
			
			complementsCfg.setCurrentElementById(commodityIndex);
			
			/**
			 * Only "enabled" commodities will be configured
			 */
			if ( complementsCfg.getCurrentElement().getEnabled() ) {
				
				clickXPath( "//table[contains(@class,'page-CompositesPageView')]//button[@name='btn-add']" );
				
				createComplement().
				saveComplement();
			}
		}
		
		return this;
	}
	
	/**
	 * 
	 * @return
	 * @throws FormException
	 * @throws JSONSException
	 */
	public ComplementsForm createComplement() throws FormException, JSONSException {

		logger.debug("Complement element to be created : " + complementsCfg.getName());
		
		typeByXPath( "//td[contains(text(),'Value')]/ancestor::tr[1]//input", complementsCfg.getName() );

		return this;
	}
	
	/**
	 * 
	 * @return
	 * @throws FormException
	 * @throws JSONSException 
	 */
	public ComplementsForm saveComplement() throws FormException, JSONSException {
		
		ComplementsSaveHandler handler = new ComplementsSaveHandler( 	selenium.getWrappedDriver(), 
																		complementsCfg.getCurrentElement());
		handler.saveAction();

		return this;	
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	private class ComplementsSaveHandler extends FormSaveConfigurationHandler {

		protected ComplementsSaveHandler(	WebDriver inDriver,
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
			List<WebElement> element = dialogBox.findElements(By.xpath("//div[text()='This value already exists']"));
								
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

				getWebDriver().findElement(By.xpath("//div[contains(@class, 'dialogContent')]//button[@title='Cancel']")).click();
					
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
				
				WebElement nameElem = getWebDriver().findElement(By.xpath("//td[contains(text(),'Value')]/ancestor::tr[1]//input"));
				nameElem.click();
				nameElem.clear();
				
				String name = getCurrentElement().getStringFromPath("name");
				name += TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
				getCurrentElement().modifyStringFromPath("name", name);
				
				nameElem.sendKeys(getCurrentElement().getStringFromPath("name"));
				
				saveAction();
				
				resp = Boolean.TRUE;
				
			} catch ( NoSuchElementException | FormException | JSONSException e ) {
				
				e.printStackTrace();
				
				resp = Boolean.FALSE;
			}
			
			return resp;
		}

	}
}
