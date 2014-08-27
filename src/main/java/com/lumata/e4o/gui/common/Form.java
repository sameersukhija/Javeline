package com.lumata.e4o.gui.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.selenium.SeleniumUtils.SearchBy;
import com.lumata.e4o.exceptions.FormException;

public abstract class Form {
	
	private static final Logger logger = LoggerFactory.getLogger(Form.class);
	
	protected SeleniumWebDriver selenium;
	protected long timeout;
	protected long interval;
	protected boolean status;
	protected WebElement lastWebElement;
	
	public Form(SeleniumWebDriver selenium, long timeout, long interval) {
		
		this.selenium = selenium;
		this.timeout = timeout;
		this.interval = interval;
	
	}
	
	public Calendar getDate( String dateStr ) {
		
		Calendar date = Calendar.getInstance();
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
	    
		try {
			
			date.setTime( sdf.parse( dateStr ) );
		
		} catch ( ParseException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		return date;
		
	}
	
	public long getTimeout() {
		return this.timeout;
	}

	public long getInterval() {
		return this.interval;
	}
	
	public Form setTimeout( long timeout ) {
		
		this.timeout = timeout;
		
		return this;
	
	}

	public Form setInterval( long interval ) {
		
		this.interval = interval;
	
		return this;
		
	}

	public void click(String forName, String xpath) throws FormException {
		
		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for " + forName + ", xpath: " + xpath));
		
		WebElement we = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, xpath, timeout, interval);
		
		if (we == null) {
			throw new FormException("Element not found");
		}
		
		we.click();		
	
	}
	
	public void clickFormat(String forName, String xpath, Object ... params) throws FormException {
		click(forName, String.format(xpath, params));
	}
	
	public void sendKeys(String forName, String xpath, String text) throws FormException {
		
		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for " + forName + ", xpath: " + xpath));
		
		WebElement we = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, xpath, timeout, interval);
		
		if (we == null) {
			throw new FormException("Element not found");
		}
		
		we.sendKeys(text);
	
	}

	public void selectByVisibleText(String forName, String xpath, String text) throws FormException {
		
		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for " + forName + ", xpath: " + xpath));
		
		WebElement selectUnitRecharge = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, xpath, timeout, interval);
		
		if (selectUnitRecharge == null) {
			throw new FormException("Element not found");
		}
		
		Select select = new Select(selectUnitRecharge);
		
		select.selectByVisibleText(text);
	
	}
	
	public String getText(String forName, String xpath) throws FormException {
		
		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for " + forName + ", xpath: " + xpath));
		
		WebElement we = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, xpath, timeout, interval);
		
		if (we == null) {
			throw new FormException("Element not found");
		}
		
		return we.getText();
	
	}
	
	public boolean isTrueKey(Map<String, String> map, String key) {
		return map.containsKey(key) && map.get(key).equalsIgnoreCase("true");
	}
	
	public boolean isTrueKeyOrMissing(Map<String, String> map, String key) {
		return map.containsKey(key) == false || map.get(key).equalsIgnoreCase("true");
	}
	
	public Form waitForPageLoading() {
		
		SeleniumUtils.waitForPageLoading( selenium.getWrappedDriver(), timeout );
		
		return this;		
	
	}
		
	private WebElement search( SeleniumUtils.SearchBy by, String tag, Long timeout, Long interval ) throws FormException {
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for " + by.name().toLowerCase() + " = " + tag ) );
		
		WebElement we = SeleniumUtils.findForComponentDisplayed( selenium, by, tag, ( null != timeout ? timeout : this.timeout ), ( null != interval ? interval : this.interval ) );
		
		if( we == null ) {
			
			status = false;
			
			throw new FormException( Log.FAILED.createMessage( selenium.getTestName() , "Element not found ( " + tag + " )" ) ); 
		
		}	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "for " + by.name().toLowerCase() + " = " + tag ) );
		
		status = true;
		
		return we;
		
	}

	public WebElement search( SeleniumUtils.SearchBy by, String tag ) throws FormException {
		
		return search( by, tag, null, null );
		
	}

	public Form searchById( String id, long timeout, long interval ) throws FormException {
		
		search( SeleniumUtils.SearchBy.ID, id, timeout, interval );
		
		return this;
		
	}
	
	public Form searchByXPath( String xpath ) throws FormException {
		
		search( SeleniumUtils.SearchBy.XPATH, xpath );
		
		return this;
		
	}
	
	public Form searchByXPath( String xpath, long timeout, long interval ) throws FormException {
				
		search( SeleniumUtils.SearchBy.XPATH, xpath, timeout, interval );
		
		return this;
		
	}
	
	private List<WebElement> searchList( SeleniumUtils.SearchBy by, String tag ) throws FormException {
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for " + by.name().toLowerCase() + " = " + tag ) );
		
		List<WebElement> we = SeleniumUtils.findListForComponentDisplayed( selenium, by, tag, timeout, interval );
		
		if( we == null ) {
			
			status = false;
			
			throw new FormException( Log.FAILED.createMessage( selenium.getTestName() , "Element not found ( " + tag + " )" ) ); 
		
		}	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "for " + by.name().toLowerCase() + " = " + tag ) );
		
		status = true;
		
		return we;
		
	}
	
	public List<WebElement> searchListById( String tag ) throws FormException {
		
		return searchList( SeleniumUtils.SearchBy.ID, tag );
		
	}
	
	private List<WebElement> searchList( SeleniumUtils.SearchBy rootBy, SeleniumUtils.SearchBy by, String rootTag, String tag ) throws FormException {
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for " + by.name().toLowerCase() + " = " + tag ) );
		
		List<WebElement> we = SeleniumUtils.findListForComponentDisplayed( selenium, rootBy, by, rootTag, tag, timeout, interval );
		
		if( we == null ) {
			
			status = false;
			
			throw new FormException( Log.FAILED.createMessage( selenium.getTestName() , "Element not found ( " + rootTag + " )" ) ); 
		
		}	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "for " + rootBy.name().toLowerCase() + " = " + rootTag ) );
		
		status = true;
		
		return we;
		
	}
	
	public List<WebElement> searchListById( String rootTag, String tag ) throws FormException {
		
		return searchList( SeleniumUtils.SearchBy.ID, SeleniumUtils.SearchBy.ID, rootTag, tag );
		
	}
	
	public List<WebElement> searchListByXPath( String rootTag, String tag ) throws FormException {
		
		return searchList( SeleniumUtils.SearchBy.XPATH, SeleniumUtils.SearchBy.XPATH, rootTag, tag );
		
	}
	
	public List<WebElement> getListByXPath( String rootXPath, String xpath ) throws FormException {
		
		return searchList( SeleniumUtils.SearchBy.XPATH, SeleniumUtils.SearchBy.XPATH, rootXPath, xpath );
		
	}

	private String getText( SeleniumUtils.SearchBy by, String xpath ) throws FormException {
		
		lastWebElement =  search( by, xpath );
		
		return lastWebElement.getText();
	}
	
	private String getText( SeleniumUtils.SearchBy by, String xpath, Long timeout, Long interval ) throws FormException {
		
		lastWebElement =  search( by, xpath, timeout, interval );
		
		return lastWebElement.getText();
	}

	public String getTextByXPath( String xpath ) throws FormException {
		
		return getText( SeleniumUtils.SearchBy.XPATH, xpath );
		
	}
	
	public String getTextByXPath( String xpath, Long timeout, Long interval ) throws FormException {
		
		return getText( SeleniumUtils.SearchBy.XPATH, xpath, timeout, interval );
		
	}
	
	public boolean isDisplayed() throws FormException {
		
		if( lastWebElement == null ) { return false; }
		
		return lastWebElement.isDisplayed();
		
	}

	public boolean isSelected( SeleniumUtils.SearchBy by, String tag ) throws FormException {
		
		lastWebElement =  search( by, tag );
		
		return lastWebElement.isSelected();
		
	}
	
	public boolean isSelectedByXPath( String tag ) throws FormException {
		
		lastWebElement =  search( SeleniumUtils.SearchBy.XPATH, tag );
		
		return lastWebElement.isSelected();
		
	}
	
	public boolean isEnabled( SeleniumUtils.SearchBy by, String tag ) throws FormException {
		
		lastWebElement =  search( by, tag );
		
		return lastWebElement.isEnabled();
		
	}

	public boolean isEnabledById( String id ) throws FormException {
		
		return isEnabled( SeleniumUtils.SearchBy.ID, id );
		
	}
	
	public boolean isEnabledByXPath( String xpath ) throws FormException {
		
		return isEnabled( SeleniumUtils.SearchBy.XPATH, xpath );
		
	}
	
	private Form click( SeleniumUtils.SearchBy by, String tag ) throws FormException {
		
		lastWebElement =  search( by, tag );
		lastWebElement.click();
				
		return this;
		
	}
	
	public Form clickId( String id ) throws FormException {
		
		return click( SeleniumUtils.SearchBy.ID, id );
		
	}
	
	public Form clickName( String name ) throws FormException {
		
		return click( SeleniumUtils.SearchBy.NAME, name );
		
	}
	
	public Form clickXPath( String xpath ) throws FormException {
		
		return click( SeleniumUtils.SearchBy.XPATH, xpath );
		
	}
	
	public Form clickLink( String link ) throws FormException {
		
		return click( SeleniumUtils.SearchBy.LINK, link );
		
	}	
	
	protected Form select( SeleniumUtils.SearchBy by, String tag, String label ) throws FormException {
		
		lastWebElement = search( by, tag );
				
		//selenium.select( by.name().toLowerCase() + "=" + tag, "label=" + label );
		Select select = new Select(lastWebElement);
		select.selectByVisibleText(label);

		return this;
		
	}
	
	public Form selectById( String id, String label ) throws FormException {
		
		return select( SeleniumUtils.SearchBy.ID, id, label );
		
	}
	
	public Form selectByName( String name, String label ) throws FormException {
		
		return select( SeleniumUtils.SearchBy.NAME, name, label );
		
	}
	
	protected Form selectByVisibleText( SeleniumUtils.SearchBy by, String tag, String text) throws FormException {
		
		lastWebElement = search( by, tag );
		
		if( lastWebElement == null ) {
			throw new FormException("Element not found");
		}
		
		Select select = new Select( lastWebElement );
		
		select.selectByVisibleText( text );
		
		return this;
	
	}
	
	public Form selectByIdAndVisibleText( String id, String text ) throws FormException {
		
		return selectByVisibleText( SearchBy.ID, id, text );	
		
	}
	
	public Form selectByXPathAndVisibleText( String xpath, String text ) throws FormException {
		
		return selectByVisibleText( SearchBy.XPATH, xpath, text );	
		
	}
	
	public Form selectByNameAndVisibleText( String name, String text ) throws FormException {
		
		return selectByVisibleText( SearchBy.NAME, name, text );	
		
	}
	
	private Form multiselectByVisibleText( SeleniumUtils.SearchBy by, String tag, JSONArray list ) throws FormException {
		
		lastWebElement = search( by, tag );
		
		try {
				
			Select available = new Select( lastWebElement );
			
			for (int i = 0; i < list.length(); i++) {
				
				String item = list.getString( i );
				
				available.selectByVisibleText( item );
			
			}
			
		} catch( JSONException e) {
			
			throw new FormException( e.getMessage(), e );
		
		}
		
		return this;
	
	}
	
	public Form multiselectByXPathAndVisibleText( String xpath, JSONArray list ) throws FormException {
		
		return multiselectByVisibleText( SearchBy.XPATH, xpath, list );	
		
	}
	
	private Form type( SeleniumUtils.SearchBy by, String tag, String text ) throws FormException {
		
		lastWebElement = search( by, tag );
		
		lastWebElement.clear();
		
		lastWebElement.sendKeys(text);
			
		return this;
		
	}
	
	public Form typeById( String id, String text ) throws FormException {
		
		return type( SeleniumUtils.SearchBy.ID, id, text );
		
	}

	public Form typeByName( String name, String text ) throws FormException {
		
		return type( SeleniumUtils.SearchBy.NAME, name, text );
		
	}
	
	public Form typeByXPath( String xpath, String text ) throws FormException {
		
		return type( SeleniumUtils.SearchBy.XPATH, xpath, text );
		
	}
	
	private Form sendKeys( SeleniumUtils.SearchBy by, String tag, String text ) throws FormException {
		
		lastWebElement = search( by, tag );
		
		lastWebElement.clear();
		
		lastWebElement.sendKeys( text );
		
		return this;
	
	}
	
	public Form sendKeysById( String id, String text ) throws FormException {
		
		return sendKeys( SeleniumUtils.SearchBy.ID, id, text ); 
	
	}
	
	public Form sendKeysByName( String name, String text ) throws FormException {
		
		return sendKeys( SeleniumUtils.SearchBy.NAME, name, text ); 
	
	}
	
	public Form sendKeysByXPath( String xpath, String text ) throws FormException {
		
		return sendKeys( SeleniumUtils.SearchBy.XPATH, xpath, text ); 
	
	}
	
	public Form sendKeysByLink( String link, String text ) throws FormException {
		
		return sendKeys( SeleniumUtils.SearchBy.LINK, link, text ); 
	
	}
	
	public Form selectDropDownListItem( String itemPath ) throws FormException {
		
		if( itemPath != null ) { 
			
			String[] itemSubPaths = itemPath.split( "\\." ); 
			
			if( itemSubPaths.length > 0 ) { 
				
				String xpath = ".//div[@class='gwt-MenuBarPopup act-ListBoxHolderPopup']//td[text()='" + itemSubPaths[ 0 ] + "']";
				
				mouseOverByXPath( xpath );
				
				if( itemSubPaths.length > 1 ) {
					
					for( int x = 1; x < itemSubPaths.length; x++ ) {
						
						xpath = ".//div[@class='gwt-MenuBarPopup']//td[text()='" + itemSubPaths[ x ] + "']";
						
						mouseOverByXPath( xpath );
											
					}
					
				} 

				lastWebElement.click();
				
			}
					
		} else {
			
			throw new FormException( "Element not valid" ); 
			
		}
								
		return this;
	
	}
	
	public Form mouseOver( SeleniumUtils.SearchBy by, String text ) throws FormException {
		
		lastWebElement = search( by, text );
		
		Actions action = new Actions(selenium.getWrappedDriver());
		action.moveToElement(lastWebElement).build().perform();
		
		return this;
		
	}
	
	public Form mouseOverByXPath( String text ) throws FormException {
		
		return mouseOver( SeleniumUtils.SearchBy.XPATH, text );
			
	}
	
	public Form clear( SeleniumUtils.SearchBy by, String text ) throws FormException {
		
		lastWebElement = search( by, text );
		
		lastWebElement.clear();
		
		return this;
		
	}

	public Form clearById( String id ) throws FormException {
		
		return clear( SeleniumUtils.SearchBy.ID, id );
			
	}
	
	public Form clearByName( String name ) throws FormException {
		
		return clear( SeleniumUtils.SearchBy.NAME, name );
			
	}
	
	public Form clearByXPath( String xpath ) throws FormException {
		
		return clear( SeleniumUtils.SearchBy.XPATH, xpath );
			
	}

	public Form switchToDefaultContent() throws FormException {
		
		selenium.getWrappedDriver().switchTo().defaultContent();
		
		return this;
		
	}
	
	public Form switchToFrame( SeleniumUtils.SearchBy by, String frame ) throws FormException {
		
		lastWebElement = search( by, frame );
		
		selenium.getWrappedDriver().switchTo().frame( lastWebElement );
		
		return this;
		
	}
	
	public Form switchToFrameByName( String frame ) throws FormException {
		
		return switchToFrame( SeleniumUtils.SearchBy.NAME, frame );
		
	}
	
	public Form switchToFrameByClassName( String frame ) throws FormException {
		
		return switchToFrame( SeleniumUtils.SearchBy.CLASS_NAME, frame );
		
	}

	public Boolean isChecked( SeleniumUtils.SearchBy by, String tag ) throws FormException {
		
		lastWebElement = search( by, tag );
				
		return lastWebElement.isSelected();
		
	}
	
	public Boolean isCheckedByXPath( String xpath ) throws FormException {
		
		return isChecked( SeleniumUtils.SearchBy.XPATH, xpath );
		
	}
	
	public Form execJavascript( String command ) {
		
		JavascriptExecutor jsexec = (JavascriptExecutor) selenium.getWrappedDriver();
		
		jsexec.executeScript( command );
		
		return this;
		
	}
	
	public Form confirmDialog() {
		
		Alert dialog = selenium.selectAlert();
		
		if( null != dialog ) { dialog.accept(); }
		
		return this;
		
	}
	
	public Form abortDialog() {
		
		Alert dialog = selenium.selectAlert();
		
		if( null != dialog ) { dialog.dismiss(); }
		
		return this;
		
	}
	
	public boolean navigate() {
		
		return status;
		
	}
	
	/**
	 * This method returns the presence of web component in error condition.
	 * The error condition is at application level for the actual form.
	 * (e.g. duplication of value, missing field) 
	 * 
	 * @return true if at least one visible element is in error.
	 * 
	 * @throws FormException
	 */
	protected Boolean containsErrorElement() throws FormException {
		
		Integer numbers = null;
		Boolean resp = Boolean.TRUE;

		// error condition
		//*[contains(@class,'errorBackground')]
		
		List<WebElement> elements = getErrorElement();

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
	 * This method returns a list with <b>WebElement</b> in error condition.
	 * The error condition is at application level for the actual form.
	 * (e.g. duplication of value, missing field) 
	 * 
	 * @param errorXPathRule is the XPath rule used to looking error condition
	 * 
	 * @return a <b>List</b> of element in error condition, empty if no visible elements are in error.
	 * 
	 * @throws FormException
	 */
	protected List<WebElement> getErrorElement(String errorXPathRule) throws FormException {
		
		List<WebElement> resp = null;

		// error condition
		//*[contains(@class,'errorBackground')]
		//div[text()='An error occured']
		
		try {

			logger.debug("Looking for more visible form.");
			Integer maxZed = 0;
			
			List<WebElement> visibleDiv = selenium.getWrappedDriver().findElements(By.xpath("//div[not(contains(@style,'none'))]/parent::div[contains(@style,'z-index')]"));
			
			for( WebElement ele:visibleDiv ) {
				
				String zed = ele.getCssValue("z-index");	
				
				Integer currZed = null;
				
				try {
					currZed = Integer.parseInt(zed);
				} catch ( NumberFormatException e ) {
					currZed = 0;
				}
				
				if ( currZed > maxZed ) {
					maxZed = currZed;
					lastWebElement = ele;
				}
			}
			
			logger.debug("Max \"z-index\" is -> " + maxZed);
			
			searchByXPath("//*[contains(@style,'"+maxZed+"')]");
			
			resp = SeleniumUtils.findListForComponentDisplayed(	selenium, 
																SearchBy.XPATH, 
																lastWebElement, 
																errorXPathRule);
			
		} catch ( StaleElementReferenceException | NoSuchElementException e ) {
			
			resp = new ArrayList<WebElement>();
		}		

		return resp;
	}
	
	/**
	 * This method returns a list with <b>WebElement</b> in error condition.
	 * The error condition is at application level for the actual form.
	 * (e.g. duplication of value, missing field) 
	 * 
	 * This method is for generic error on the data input value.
	 * 
	 * @return a <b>List</b> of element in error condition, empty if no visible elements are in error.
	 * 
	 * @throws FormException
	 */
	protected List<WebElement> getErrorElement() throws FormException {
		
		return getErrorElement("//*[contains(@class,'errorBackground')]");
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
	protected Boolean handleJavascriptAlertAcceptDismiss(Boolean accept) {
		
		Alert popupAlert = null;
		Boolean pressed = null;
		
		try {
			
			popupAlert = selenium.selectAlert();
		    	
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
