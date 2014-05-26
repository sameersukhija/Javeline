package com.lumata.e4o.gui.administrationmanager;

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
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.selenium.SeleniumUtils.SearchBy;
import com.lumata.e4o.exceptions.FormException;
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
		
		if ( containsErrorElement() )
			logger.error("Without click \"save\" panel is in error!");
		
		Boolean completed = Boolean.FALSE;
		
		do {
			
			/**
			 * Core save procedure
			 */
			
			clickXPath( "//button[@title='Save']" );
			
			Boolean confirmed = Boolean.FALSE;

			confirmed = handleJavascriptAlertAcceptDismiss(true);

			/**
			 * End - Core save procedure
			 */
			
			// in case no confirmation was executed, check element in error
			if ( !confirmed && containsErrorElement() ) {

				JsonCurrentElement current = complementsCfg.getCurrentElement();
				
				logger.warn("After click \"Save\" panel is in error!");
				
				ElementErrorConditionType condition = null;
				
				searchByXPath("//div[@class='gwt-DialogBox']");
				
				// error condition
				//div[text()='This value already exists']
				List<WebElement> element = SeleniumUtils.findListForComponentDisplayed(	selenium, 
																						SearchBy.XPATH, 
																						lastWebElement, 
																						"//div[text()='This value already exists']"
																					);
				
				if ( element.size() != 0 )
					condition = ElementErrorConditionType.ELEMENT_AREADY_EXISTS;
				else
					condition = ElementErrorConditionType.GENERAL_ERROR;
				
				ElementErrorActionType action = current.getErrorActions().getAction(condition);
				
				// abort insertion
				if ( action.equals(ElementErrorActionType.ABORT_CANCEL) ) {
					clickXPath("//div[contains(@class, 'dialogContent')]//button[@title='Cancel']");
					
					completed = Boolean.TRUE;
				}
				// stop execution and return error
				else if ( action.equals(ElementErrorActionType.RETURN_ERROR) )
					throw new FormException(getClass().getSimpleName() + " cannot configure \""+current.getStringFromPath("name")+"\" complements!");
				// add timestamp to name
				else if ( action.equals(ElementErrorActionType.ADD_TIMESTAMP_TO_FIELD) ) {
					
					// one click su input
					clickXPath( "//td[contains(text(),'Value')]/ancestor::tr[1]//input" );
					
					// delete current text
					lastWebElement.clear();
					
					/**
					 * Technical Debit - This point contains an error
					 */
					
					String actualName = current.getStringFromPath("name");
					current.setObjectFromPath("name", actualName + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
					
					// send old text + timestamp
					typeByXPath( "//td[contains(text(),'Value')]/ancestor::tr[1]//input", complementsCfg.getName() );
					
					completed = Boolean.FALSE;
				}
			}
			else // all ok!
				completed = Boolean.TRUE;			
		}
		while ( !completed );

		return this;
		
	}
}
