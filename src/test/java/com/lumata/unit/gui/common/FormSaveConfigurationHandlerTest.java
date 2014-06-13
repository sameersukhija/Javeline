package com.lumata.unit.gui.common;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.ErrorModificableElement;
import com.lumata.common.testing.json.HasErrorActions;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorActionType;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorConditionType;
import com.lumata.common.testing.json.JsonConfigurationFile;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.FormSaveConfigurationHandler;

public class FormSaveConfigurationHandlerTest {

	/**
	 *	This object idealizes the running web application 
	 */
	private Server server = null;
	
	/**
	 *  This object is the Selenium driver prepared during scenario init phase
	 */
	private WebDriver driver = null;
		
	/**
	 * 	This is the object under test that will be encapsulated into tested "Form"
	 */
	private SampleHandler underTestWithoutPopup = null;
	private SampleHandler underTestWithPopup = null;

	/**
	 * This an abstract class carried by "Form" to handle "Save" related event.
	 * 
	 * This class can be the parent for two different handler :<br>
	 * <li> an handler where the save event with a confirmation popup
	 * <li> an handler where the save event without a confirmation popup
	 */
	private abstract class SampleHandler extends FormSaveConfigurationHandler {

		public SampleHandler(WebDriver driver, ErrorModificableElement currentElement) {
			
			super(driver,currentElement);
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

		@Override
		protected Boolean cancelAction() {
			
			// for verification
			addAbortCancelInvoked = Boolean.TRUE;
			
			Boolean resp = Boolean.FALSE;
			
			try {
				getWebDriver().findElement(By.xpath("//input[@id='name-field']")).clear();

				getWebDriver().findElement(By.xpath("//button[@title='Cancel']")).click();
					
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
			 
			// for verification
			addTimestampInvoked = Boolean.TRUE;
						
			Boolean resp = Boolean.FALSE;
			
			try {
				
				String name = getCurrentElement().getStringFromPath("name");
				name += TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
				getCurrentElement().modifyStringFromPath("name", name);
				
				getWebDriver().findElement(By.xpath("//input[@id='name-field']")).clear();
				
				getWebDriver().findElement(By.xpath("//input[@id='name-field']")).sendKeys(getCurrentElement().getStringFromPath("name"));
				
				saveAction();
				
				resp = Boolean.TRUE;
				
			} catch ( NoSuchElementException | FormException | JSONSException e ) {
				
				e.printStackTrace();
				
				resp = Boolean.FALSE;
			}
			
			return resp;
		}

		@Override
		protected ElementErrorConditionType defineErrorCondition() {

			return ElementErrorConditionType.ELEMENT_AREADY_EXISTS;
		}
		
		/**
		 * Only for test
		 * 
		 * @return
		 */
		public Boolean catchConfirmation() {
			
			return acceptedConfirmationPopup();
		}
	}
	
	/**
	 * WITH confirmation popup handler
	 */
	private class WithPopupHandler extends SampleHandler {

		private WebElement saveElement = null;
		
		public WithPopupHandler(WebDriver driver,
				ErrorModificableElement currentElement) {
			super(driver, currentElement);
		}

		@Override
		protected WebElement getSaveWebElement() {

			if ( saveElement == null )
				saveElement = getWebDriver().findElement(By.xpath("//button[@title='Save-popup']")); 
			
			return saveElement;
		}

	}
	
	/**
	 * WITHOUT confirmation popup handler
	 */	
	private class WithoutPopupHandler extends SampleHandler {

		public WithoutPopupHandler(WebDriver driver,
				ErrorModificableElement currentElement) {
			super(driver, currentElement);
		}

		@Override
		protected WebElement getSaveWebElement() {

			return getWebDriver().findElement(By.xpath("//button[@title='Save']"));
		}
		
	}	
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Test section
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	/**
	 * 
	 */
	private ErrorModificableElement defaultCurrentElement = null;
	
	/**
	 * 
	 */
	private JsonConfigurationFile jsonConfigurationFile = null;
	
	/**
	 * Verification scope
	 */
	private Boolean addAbortCancelInvoked = null;
	
	/**
	 * Verification scope
	 */
	private Boolean addTimestampInvoked = null;
	
	@BeforeClass
	public void setUp() throws Exception {
		
		server = new Server(8080);
		ResourceHandler resource_handler = new ResourceHandler();

		resource_handler.setDirectoriesListed(true);

		resource_handler.setResourceBase("./target/test-classes");

		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] { resource_handler, new DefaultHandler() });
		server.setHandler(handlers);
		server.start();
		
		/**
		 * Init driver
		 */
		FirefoxProfile profile = new FirefoxProfile(new File("/home/vincenzo/.mozilla/firefox/ncc8lgcm.default"));
		driver = new FirefoxDriver(profile);
		
		/**
		 * This way is only for test 
		 */
		
		jsonConfigurationFile = new JsonConfigurationFile("test", "only_for_test") {
			
			@Override
			public String getElementsSectionLabel() {

				return "sampleArrayGetListString";
			}
		};

		Map<String, Object> currentElementMap = new HashMap<>();
		currentElementMap.put("name", "This is a value");
		
		Map<String, Object> errorActionsMap = new HashMap<>();
		errorActionsMap.put(	ElementErrorConditionType.ELEMENT_AREADY_EXISTS.toString(), 
								ElementErrorActionType.ADD_TIMESTAMP_TO_FIELD.toString());
		errorActionsMap.put(	ElementErrorConditionType.GENERAL_ERROR.toString(), 
								ElementErrorActionType.RETURN_ERROR.toString());
		
		currentElementMap.put(HasErrorActions.ERROR_ACTIONS_LABEL_, errorActionsMap);
		
		defaultCurrentElement = jsonConfigurationFile.new JsonCurrentElement(currentElementMap);		
		
		/**
		 * 	This way is only for test - END
		 */
	}
	
	@AfterClass
	public void tearDown() {
		
		driver.quit();
	}
	
	@BeforeMethod
	public void separationTest() {
		
		addAbortCancelInvoked = Boolean.FALSE;
		addTimestampInvoked = Boolean.FALSE;
		
		driver.get("http://localhost:8080/test.html");
	}
	
	/**
	 * With popup handler
	 * @throws FormException 
	 */
	
	@Test( priority = 1 )
	public void correctTextInsertionWithoutPopup() throws FormException {
		
		underTestWithoutPopup = new WithoutPopupHandler(driver, defaultCurrentElement);
		
		driver.findElement(By.xpath("//input[@id='name-field']")).sendKeys("Correct String");
		
		underTestWithoutPopup.saveAction();
		
		Assert.assertEquals( 	underTestWithoutPopup.catchConfirmation(), Boolean.FALSE, "The popup is NOT showed!");
		Assert.assertEquals(   	addAbortCancelInvoked, Boolean.FALSE, "No error are showed!");
		Assert.assertEquals(   	addTimestampInvoked, Boolean.FALSE, "No error are showed!");
	}
	
	/**
	 * With popup handler
	 * @throws FormException 
	 */	
	
	@Test( priority = 2 )
	public void correctTextInsertionWithPopup() throws FormException {
		
		underTestWithPopup = new WithPopupHandler(driver, defaultCurrentElement);
		
		driver.findElement(By.xpath("//input[@id='name-field']")).sendKeys("Correct String");
		
		underTestWithPopup.saveAction();

		Assert.assertEquals(	underTestWithPopup.catchConfirmation(), Boolean.TRUE, "The popup is showed!");
		Assert.assertEquals(   	addAbortCancelInvoked, Boolean.FALSE, "No error are showed!");
		Assert.assertEquals(   	addTimestampInvoked, Boolean.FALSE, "No error are showed!");
	}	
	
	@Test( priority = 3 )
	public void wrongTextInsertionWithoutPopupAddTimestamp() throws FormException {

		underTestWithoutPopup = new WithoutPopupHandler(driver, defaultCurrentElement);
		
		driver.findElement(By.xpath("//input[@id='name-field']")).sendKeys("Error String");
		
		underTestWithoutPopup.saveAction();

		Assert.assertEquals(	underTestWithoutPopup.catchConfirmation(), Boolean.FALSE, "The popup is NOT showed!");
		Assert.assertEquals(   	addAbortCancelInvoked, Boolean.FALSE, "The \"Abort\" action is NOT invoked!");
		Assert.assertEquals(   	addTimestampInvoked, Boolean.TRUE, "The \"Timestamp\" action is invoked!");
	}

	
	@Test( priority = 4 )
	public void wrongTextInsertionWithPopupAddTimestamp() throws FormException {
		
		underTestWithPopup = new WithPopupHandler(driver, defaultCurrentElement);		
		
		driver.findElement(By.xpath("//input[@id='name-field']")).sendKeys("Error String");
		
		underTestWithPopup.saveAction();

		// "Confirmation" popup is NOT showed on wrong data
		Assert.assertEquals(	underTestWithPopup.catchConfirmation(), Boolean.FALSE, "The popup is NOT showed!");
		Assert.assertEquals(   	addAbortCancelInvoked, Boolean.FALSE, "The \"Abort\" action is NOT invoked!");
		Assert.assertEquals(   	addTimestampInvoked, Boolean.TRUE, "The \"Timestamp\" action is invoked!");
	}
	
	@Test( priority = 5 )
	public void wrongTextInsertionWithoutPopupAbort() throws FormException {

		Map<String, Object> abortElementMap = new HashMap<>();
		abortElementMap.put("name", "This is a value");
		
		Map<String, Object> errorActionsMap = new HashMap<>();
		errorActionsMap.put(	ElementErrorConditionType.ELEMENT_AREADY_EXISTS.toString(), 
								ElementErrorActionType.ABORT_CANCEL.toString());
		errorActionsMap.put(	ElementErrorConditionType.GENERAL_ERROR.toString(), 
								ElementErrorActionType.RETURN_ERROR.toString());
		
		abortElementMap.put(HasErrorActions.ERROR_ACTIONS_LABEL_, errorActionsMap);
		
		underTestWithoutPopup = new WithoutPopupHandler(	driver,
															jsonConfigurationFile.new JsonCurrentElement(abortElementMap));
		
		driver.findElement(By.xpath("//input[@id='name-field']")).sendKeys("Error String");
		
		underTestWithoutPopup.saveAction();

		Assert.assertEquals(	underTestWithoutPopup.catchConfirmation(), Boolean.FALSE, "The popup is NOT showed!");
		Assert.assertEquals(   	addAbortCancelInvoked, Boolean.TRUE, "The \"Abort\" action is invoked!");
		Assert.assertEquals(   	addTimestampInvoked, Boolean.FALSE, "The \"Timestamp\" action is NOT invoked!");
	}
	
	@Test( priority = 6 )
	public void wrongTextInsertionWithPopupAbort() throws FormException {

		Map<String, Object> abortElementMap = new HashMap<>();
		abortElementMap.put("name", "This is a value");
		
		Map<String, Object> errorActionsMap = new HashMap<>();
		errorActionsMap.put(	ElementErrorConditionType.ELEMENT_AREADY_EXISTS.toString(), 
								ElementErrorActionType.ABORT_CANCEL.toString());
		errorActionsMap.put(	ElementErrorConditionType.GENERAL_ERROR.toString(), 
								ElementErrorActionType.RETURN_ERROR.toString());
		
		abortElementMap.put(HasErrorActions.ERROR_ACTIONS_LABEL_, errorActionsMap);
		
		underTestWithPopup = new WithPopupHandler(	driver,
													jsonConfigurationFile.new JsonCurrentElement(abortElementMap));
		
		driver.findElement(By.xpath("//input[@id='name-field']")).sendKeys("Error String");
		
		underTestWithPopup.saveAction();

		// "Confirmation" popup is NOT showed on wrong data
		Assert.assertEquals(	underTestWithPopup.catchConfirmation(), Boolean.FALSE, "The popup is NOT showed!");
		Assert.assertEquals(   	addAbortCancelInvoked, Boolean.TRUE, "The \"Abort\" action is invoked!");
		Assert.assertEquals(   	addTimestampInvoked, Boolean.FALSE, "The \"Timestamp\" action is NOT invoked!");
	}
	
	@Test( priority = 7 , expectedExceptions = { FormException.class })
	public void wrongTextInsertionWithoutPopupGeneralError() throws FormException {

		Map<String, Object> returnErroElementMap = new HashMap<>();
		returnErroElementMap.put("name", "This is a value");
		
		Map<String, Object> errorActionsMap = new HashMap<>();
		errorActionsMap.put(	ElementErrorConditionType.ELEMENT_AREADY_EXISTS.toString(), 
								ElementErrorActionType.RETURN_ERROR.toString());
		errorActionsMap.put(	ElementErrorConditionType.GENERAL_ERROR.toString(), 
								ElementErrorActionType.RETURN_ERROR.toString());
		
		returnErroElementMap.put(HasErrorActions.ERROR_ACTIONS_LABEL_, errorActionsMap);
		
		underTestWithoutPopup = new WithoutPopupHandler(	driver,
															jsonConfigurationFile.new JsonCurrentElement(returnErroElementMap));
		
		driver.findElement(By.xpath("//input[@id='name-field']")).sendKeys("Error String");
		
		underTestWithoutPopup.saveAction();
	}
	
	@Test( priority = 8 , expectedExceptions = { FormException.class })
	public void wrongTextInsertionWithPopupGeneralError() throws FormException {

		Map<String, Object> returnErroElementMap = new HashMap<>();
		returnErroElementMap.put("name", "This is a value");
		
		Map<String, Object> errorActionsMap = new HashMap<>();
		errorActionsMap.put(	ElementErrorConditionType.ELEMENT_AREADY_EXISTS.toString(), 
								ElementErrorActionType.RETURN_ERROR.toString());
		errorActionsMap.put(	ElementErrorConditionType.GENERAL_ERROR.toString(), 
								ElementErrorActionType.RETURN_ERROR.toString());
		
		returnErroElementMap.put(HasErrorActions.ERROR_ACTIONS_LABEL_, errorActionsMap);
		
		underTestWithPopup = new WithPopupHandler(	driver,
													jsonConfigurationFile.new JsonCurrentElement(returnErroElementMap));
		
		driver.findElement(By.xpath("//input[@id='name-field']")).sendKeys("Error String");
		
		underTestWithPopup.saveAction();
	}		
}
