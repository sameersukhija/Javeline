package com.lumata.e4o.gui.loyaltymanager;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.ErrorModificableElement;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorConditionType;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.common.PlaceHolderDate;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.Form;
import com.lumata.e4o.gui.common.FormSaveConfigurationHandler;
import com.lumata.e4o.gui.common.GWTCalendarForm;
import com.lumata.e4o.gui.common.FormSaveConfigurationHandler.SaveResult;
import com.lumata.e4o.json.gui.campaignmanager.JSONAction;
import com.lumata.e4o.json.gui.campaignmanager.JSONCriteria;
import com.lumata.e4o.json.gui.campaignmanager.JSONEvent_;
import com.lumata.e4o.json.gui.loyaltymanager.JSONLoyaltiesCreation;
import com.lumata.e4o.json.gui.loyaltymanager.JSONLoyaltiesCreation.LoyaltyTypes;


public class LoyaltyCreationForm extends LoyaltyManagerForm {

	/**
	 * 
	 */
	private static final Logger logger = LoggerFactory.getLogger(LoyaltyCreationForm.class);
	
	/**
	 * 
	 */
	private JSONLoyaltiesCreation loyaltiesCreationCfg;
	
	/**
	 * 
	 * @param selenium
	 * @param timeout
	 * @param interval
	 */
	
	
	public LoyaltyCreationForm(SeleniumWebDriver selenium, JSONLoyaltiesCreation configuration, long timeout, long interval) {
		
		super(selenium, null, timeout, interval);
		
		this.loyaltiesCreationCfg = configuration;
	}

	/**
	 * 
	 */
	public LoyaltyCreationForm open() throws FormException {
		
		super.open().clickXPath( "//div[text()='Creation']" );
		
		return this;
	}
	
	/**
	 * 
	 * @return
	 * @throws FormException
	 * @throws JSONSException
	 */
	
	
	
	public LoyaltyCreationForm clickaddnewBadge(LoyaltyTypes loyaltyTypes) throws FormException, JSONSException {
	
		
		//LoyaltyTypes type = loyaltiesCreationCfg.getType();
		
		final String openSubSectionXPath = "//table[contains(@class,'page-ConfigurationProgramWidget')]//td[contains(text(),'"+loyaltyTypes+"')]";
		final String addButtonXPath = openSubSectionXPath + "//ancestor::tr[3]//button[@title='Add']";
		super.clickXPath(openSubSectionXPath);					
		super.clickXPath(addButtonXPath);
	
		return this;
	}
	
	public LoyaltyCreationForm clickaddLoyaltyPrograms() throws FormException, JSONSException {
			
			super.clickXPath("//div[contains(text(),'Add new program')]//ancestor::table//input");
	
	return this;
	
	}
	
	public LoyaltyCreationForm addLoyaltyProgramName(String name) throws FormException, JSONSException {
		
			super.sendKeysByXPath("//div[contains(text(),'Add new program')]//ancestor::table/tbody//tr[@class='cycle1']/td[2]//input", name);
		
	return this;
	}
				
	public LoyaltyCreationForm addLoyaltyProgramDesc(String desc) throws FormException, JSONSException {
		
			super.sendKeysByXPath("//div[contains(text(),'Add new program')]//ancestor::table/tbody//tr[@class='cycle2']/td[2]//input", desc);
		
	return this;
	
	}
	
	public LoyaltyCreationForm clickaddLoyaltyBadgeTypes() throws FormException, JSONSException {
		
		super.clickXPath("//div[contains(text(),'Edit program')]//ancestor::tbody//button[@title='Add']");

	return this;
	}
	
	public LoyaltyCreationForm addBadgesTypeName(List<String> badgeTypeList) throws FormException, JSONSException {
		
		for (String badgeType : badgeTypeList) {
		super.clickXPath("//div[contains(text(),'Edit program')]//ancestor::tbody//button[@title='Add']");

		super.sendKeysByXPath("//div[contains(text(),'New Badge Type')]//ancestor::table/tbody//tr[@class='cycle1']/td[2]//input",badgeType);
		super.clickXPath("//div[text()='New Badge Type']//ancestor::tbody//*[@title='Save']");
		}
		return this;
	}
	
	public LoyaltyCreationForm saveLoyaltyProgram() throws FormException {
		
		super.clickName( "btn-save" );
		handleJavascriptAlertAcceptDismiss(Boolean.TRUE);
	    
	return this;
	}

	
	public LoyaltyCreationForm clickRefresh() throws FormException {
		
		super.clickXPath( "//table[@class='gwt-DecoratedTabPanel tab-LoyaltyTab']//table[@class='padding10px page-ConfigurationProgramWidget']//button[@title='Refresh']");
	   
	return this;
	}
	
	
	public Boolean isLoyaltyInList( String loyaltyName ) throws FormException {
		
		List<WebElement> loyaltyList = getLoyaltyList(loyaltyName);

		for( WebElement loyaltyListE1 : loyaltyList ) {

			if( loyaltyListE1.getText().trim().equals( loyaltyName ) ) {
		
				return true;

			}	
		}

		return false;	
	}

	
	public List<WebElement> getLoyaltyList(String strBadgeName)  throws FormException {
		
		String rootPath = "//table/tbody/tr/td/table/tbody/tr[4]";
		String subPath = "//div[text()='" + strBadgeName + "']";

		List<WebElement> loyaltyList = getListByXPath(rootPath, rootPath + subPath);
		System.out.println(loyaltyList);
		return loyaltyList;
	}

	public LoyaltyCreationForm clickclosebutton() throws FormException, JSONSException {
		
		super.clickXPath("//button[@name='btn-close']");
		waitForPageLoad();

	return this;
	}
	
	/**
	 * This method fills the classes/badges into program displayed UI
	 * 
	 * @param type is the "Loyalty Program type"
	 * @param element2fill is the list of Classes/Badges to fill
	 * @return
	 * @throws FormException 
	 */
	private LoyaltyCreationForm configureLoyaltyProgram( LoyaltyTypes type, List<String> element2fill) throws FormException {

		//div[text()='Badges']//ancestor::table[1]//button[@title='Add']
		//div[text()='Classes']//ancestor::table[1]//button[@title='Add']
		
		final String rule2Add = "//div[text()='"+( type.equals(LoyaltyTypes.Points) ? "Classes" : "Badges" )+"']//ancestor::table[1]//button[@title='Add']";
		final String textField  = "//div[text()='"+( type.equals(LoyaltyTypes.Points) ? "New Class" : "New Badge Type" )+"']//ancestor::table//input"; 
//		final String saveButton = "//div[text()='"+( type.equals(LoyaltyTypes.Points) ? "New Class" : "New Badge Type" )+"']//ancestor::table//button[@title='Save']";

		for (String element : element2fill) {
			
			clickXPath(rule2Add);
			
			sendKeysByXPath(textField, element);
			
			try {
				Thread.sleep(1_000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
//			clickXPath(saveButton);
			
			// error handling on class/badge
//			LoyaltyAddClassBadgeHandler handler = new LoyaltyAddClassBadgeHandler( 	selenium.getWrappedDriver(), loyaltiesCreationCfg.getCurrentElement());

			// error handling on class/badge
			LoyaltyAddClassBadgeHandler handler = new LoyaltyAddClassBadgeHandler( 	selenium.getWrappedDriver(), loyaltiesCreationCfg.getCurrentElement());
			
			// this is custom method
			handler.setType(type);
			
			handler.saveAction();
		}
		
		clickXPath("//div[contains(text(),'Edit program')]//ancestor::table//button[@title='Close']");
		
		return this;
	}		

	/**
	 * 
	 * Handler for program name
	 *
	 */
	private class LoyaltyNameSaveHandler extends FormSaveConfigurationHandler {

		protected LoyaltyNameSaveHandler(	WebDriver inDriver,
											ErrorModificableElement inCurrentElement) {
			
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
			List<WebElement> element = dialogBox.findElements(By.xpath("//div[text()='The name is already used']"));
								
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
				
				WebElement nameElem = getWebDriver().findElement(By.xpath("//*[contains(@class,'errorBackground')]"));
				nameElem.click();
				nameElem.clear();
				
				String name = getCurrentElement().getStringFromPath("name");
				
				SecureRandom random = new SecureRandom();
				
				name += new BigInteger( 16, random).toString(16);
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
	
	/**
	 * 
	 * Handler for adding classes and badges
	 *
	 */
	private class LoyaltyAddClassBadgeHandler extends FormSaveConfigurationHandler {

		protected LoyaltyAddClassBadgeHandler(	WebDriver inDriver,
												ErrorModificableElement inCurrentElement) {

			super(inDriver, inCurrentElement);
		}

		/**
		 * 
		 */
		private String xpathRule = null; 
		
		/**
		 * This method customize the "Save" event according program type.
		 * 
		 * This method resets the "Save" WebElement and generates the new XPath rule.
		 * 
		 * @param type is the loyalty program type
		 */
		public void setType(LoyaltyTypes type) {
			
			logger.debug("Reset of " + getClass().getSimpleName() + " and its \"Save\" element.");
			
			saveElement = null;
			
			xpathRule = "//div[text()='"+( type.equals(LoyaltyTypes.Points) ? "New Class" : "New Badge Type" )+"']//ancestor::table//button[@title='Save']";
		}

		@Override
		protected Boolean containsErrorElement() {

			Integer numbers = null;
			Boolean resp = Boolean.TRUE;

			// error condition
			// *[contains(@class,'errorBackground')]

			List<WebElement> elements = getWebDriver().findElements(
					By.xpath("//*[contains(@class,'errorBackground')]"));

			if (elements == null)
				numbers = 0;
			else
				numbers = elements.size();

			if (numbers != 0)
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

			if (saveElement == null)
				saveElement = getWebDriver().findElement(By.xpath(xpathRule));

			return saveElement;
		}

		@Override
		protected ElementErrorConditionType defineErrorCondition() {

			ElementErrorConditionType condition = null;

			WebElement dialogBox = getWebDriver().findElement(
					By.xpath("//div[@class='gwt-DialogBox']"));

			// error condition
			// div[text()='Bonus name already used']
			List<WebElement> element = dialogBox.findElements(By
					.xpath("//div[text()='The name is already used']"));

			if (element.size() != 0)
				condition = ElementErrorConditionType.ELEMENT_AREADY_EXISTS;
			else
				condition = ElementErrorConditionType.GENERAL_ERROR;

			return condition;
		}

		@Override
		protected Boolean cancelAction() {

			Boolean resp = Boolean.FALSE;

			try {

				getWebDriver()
						.findElement(
								By.xpath("//div[contains(@class, 'dialogContent')]//button[@title='Cancel']"))
						.click();

				resp = Boolean.TRUE;
			} catch (NoSuchElementException e) {

				e.printStackTrace();

				resp = Boolean.FALSE;
			}

			return resp;
		}

		@Override
		protected Boolean addTimestampAction() {

			Boolean resp = Boolean.FALSE;

			try {

				WebElement nameElem = getWebDriver().findElement(
						By.xpath("//*[contains(@class,'errorBackground')]"));
				nameElem.click();
				nameElem.clear();

				String name = getCurrentElement().getStringFromPath("name");

				SecureRandom random = new SecureRandom();

				name += new BigInteger(16, random).toString(16);
				getCurrentElement().modifyStringFromPath("name", name);

				nameElem.sendKeys(getCurrentElement().getStringFromPath("name"));

				saveAction();

				resp = Boolean.TRUE;

			} catch (NoSuchElementException | FormException | JSONSException e) {

				e.printStackTrace();

				resp = Boolean.FALSE;
			}

			return resp;
		}
	}
	

	

	
	
	
	
	
	protected Form open1() throws FormException {
		WebDriverWait wait=new WebDriverWait(selenium.getWrappedDriver(), 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("gwt-debug-BarCaptionHomeCampaign")));
		return clickId( "gwt-debug-BarCaptionHomeCampaign" );
		
	}

	

	
	
//	private LoyaltyCreateCfg createCfg;
//	private LoyaltyManageCfg manageCfg;
//	
//	public LoyaltyCreationForm(SeleniumWebDriver selenium, long timeout, long interval,
//			LoyaltyCreateCfg createCfg, LoyaltyManageCfg manageCfg) {
//		
//		super(selenium, timeout, interval);
//		
//		this.createCfg = createCfg;
//		this.manageCfg = manageCfg;
//	}
//	
//	public void open() throws Exception {
//		
////		super.open(selenium, timeout, interval);
//		openSubsection(ImmutableMap.of("clickAccordion", "true"));
//	}
//	
//	public void openSubsection(Map<String, String> map) throws Exception {
//		
//		click( "subSectionTab", "//div[contains(text(),'Loyalty')]");
//		
//		// TODO missed: click("subSectionTab", "html/body/table[2]/tbody/tr/td/table/tbody/tr[2]/td/div/div[2]/table/tbody/tr[1]/td/table//*[text()='Management']");
//		
//		if (isTrueKeyOrMissing(map, "clickAccordion")) 
//			clickFormat( "accordion", "//div//*[text()='%s']", createCfg.getAccordionName());
//	}
//
//	public void create() throws Exception {
//		
//		addNewProgram();
//		
//		addBadgeTypeFromList(createCfg.getTypeNameList());
//		
//		click("badgeTypeClose", "//div[contains(text(),'Edit program')]//ancestor::tbody//*[@title='Close']");
//	}
//
//	public String duplication() throws Exception {
//		
//		addNewProgram();
//		
//		return getText("errorMessage", "html/body/div[6]/div/div");
//	}
//
//	public void manage() throws Exception {
//		
//		click( "subSectionTab", "//div[contains(text(),'Loyalty')]");
//		
//		click("subSectionTab", "//table[contains(@class,'tab-LoyaltyTab')]//div[text()='Management']");
//		
//		clickFormat( "editProgram", "//*[text()='%s']//ancestor::tbody[2]//*[@title='Edit']", createCfg.getProgramName());
//		
//		click( "addBadge", "//div[text()='Badges']//ancestor::tbody[2]//*[@title='Add']");
//		
//		sendKeys("badgeDef, k2, v2initionName",
//			"//td[contains(text(),'Name')]/ancestor::tr[1]//input",
//			manageCfg.getDefinitionName());
//		
//		sendKeys("badgeDefinitionDesc",
//			"//td[contains(text(),'Description')]/ancestor::tr[1]//input",
//			manageCfg.getDefinitionDescription());
//		
//		click("next", "//div[text()='Badge creation']//ancestor::tbody//*[@title='Next']");
//	
//		click("next2", "//div[text()='Badge creation']//ancestor::tbody//*[@title='Next']");
//		
//		click("next3", "//div[text()='Badge creation']//ancestor::tbody//*[@title='Next']");
//		
//		click("addAwarded", "//div[text()='Badge creation']//ancestor::tbody//*[@title='Add']");
//		
//		click("eventType", "//td[@role='menuitem']");
//		
//		clickFormat("selectRevenue", "//td[@role='menuitem' and text()='%s']",
//			manageCfg.getAwardedEventType());
//		
//		click("addAction", "//*[@id='gwt-debug-BtnCampaignModelCreationEAAdd']");
//		
//		click("saveBadge", "//div[text()='Badge creation']//ancestor::tbody//*[@title='Save']");
//		
//		click("closeBadge", "//div[text()='Badges']//ancestor::tbody//*[@title='Close']");
//	}
//
//	public void closeNewProgramPopup() throws Exception {
//		
//		click("closeProgramPopup",
//			"html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td[1]/button");
//	}
//	
//	private void addNewProgram() throws Exception {
//		
//		clickFormat("addNewProgramPopup",
//				"//div//*[text()='%s']/ancestor::td[2]//button",
//				createCfg.getAccordionName());
//
//		sendKeys(	"programNameInput",
//					"//td[contains(text(),'Program Name')]/ancestor::tr[1]//input",				
//					createCfg.getProgramName());
//		
//		sendKeys(	"programDescInput",
//					"//td[contains(text(),'Program Description')]/ancestor::tr[1]//input",
//					createCfg.getProgramDesc());
//		
//		click(	"programSave",
//				"//*[@title='Save']");
//	}
//	
//	private void addBadgeTypeFromList(List<String> badgeTypeList) throws Exception {
//		
//		for (String badgeType : badgeTypeList) {
//			
//			click(	"addBadgeType",
//					"//div[contains(text(),'Edit program')]//ancestor::tbody//button[@title='Add']");
//			
//			sendKeys(	"badgeNameInput",
//					"//div[text()='New Badge Type']//ancestor::tbody//input",
//					badgeType);
//			
//			click(	"badgeTypeSave", 
//					"//div[text()='New Badge Type']//ancestor::tbody//*[@title='Save']");
//		}
//	}
//	
//	public void delete() throws Exception {
//		clickFormat("delete",
//			"//div[contains(text(),'%s')]//ancestor::tbody[2]//*[@title='Delete']",
//			createCfg.getProgramName());
//		
//		// Wait GWT deletes the record from the GUI
//		Thread.sleep(4);
//		Boolean isInvisible = (new WebDriverWait(selenium.getWrappedDriver(), 10))
//			.until(ExpectedConditions.invisibilityOfElementLocated(By.id(
//				"html/body/table[2]//*[text()='"+createCfg.getProgramName()+"']/../..//*[@title='Delete']")));
//		
//		System.out.println("isInvisible: " + isInvisible);
//	}
}
