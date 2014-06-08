package com.lumata.e4o.gui.loyaltymanager;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
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
		
		super(selenium, timeout, interval);
		
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
	public LoyaltyCreationForm addProductTypes() throws FormException, JSONSException {
	
		int numbProdType = loyaltiesCreationCfg.getList().size();
		
		for (int index = 0; index < numbProdType; index++) {
			
			JsonCurrentElement current = loyaltiesCreationCfg.getCurrentElementById(index);
			
			if ( current.getEnabled() ) {
				
				List<String> element2fill = null;
				LoyaltyTypes type = loyaltiesCreationCfg.getType();
				WebElement addButtonElement = null;
				
				final String openSubSectionXPath = "//table[contains(@class,'page-ConfigurationProgramWidget')]//td[contains(text(),'"+type+"')]";
				final String addButtonXPath = openSubSectionXPath + "//ancestor::tr[3]//button[@title='Add']";
				
				Boolean buttonPressed = Boolean.FALSE;
				int count = 0;
				
				do {
					
					count++;
					
					if ( count == 10 ) {
						
						String message = getClass().getSimpleName() + " error : add button for sub-section \""+type+"\" NOT found!";
						
						logger.error(message);
						
						throw new FormException(message);
					}
					
					// if button 'Add' not visible
					// open sub-section
					try {
						addButtonElement = selenium.getWrappedDriver().findElement(By.xpath(addButtonXPath));
						
						Boolean subSectionOpen = addButtonElement.isDisplayed();
						
						// switch sub-section
						if ( !subSectionOpen ) {
							logger.debug("Sub-section \""+type+"\" NOT open, open it.");
							
							clickXPath(openSubSectionXPath);
							
							try {
								Thread.sleep(1_000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						else 
							logger.debug("Sub-section \""+type+"\" already open.");
							
						clickXPath(addButtonXPath);
						
						buttonPressed = Boolean.TRUE;
						
					} catch ( NotFoundException e ) {
						// try again Sam!
					}	
				}
				while ( !buttonPressed );
				
				if ( type.equals(LoyaltyTypes.Points) ) 
					element2fill = loyaltiesCreationCfg.getClasses();
				else  // badges
					element2fill = loyaltiesCreationCfg.getBadges();

				// this is the rule of displayed popup
				//div[contains(text(),'Add new program')]//ancestor::table//input
				
				List<WebElement> nameAndDesc = selenium.getWrappedDriver().findElements(By.xpath("//div[contains(text(),'Add new program')]//ancestor::table//input"));
				
				String name = loyaltiesCreationCfg.getName();
				String desc = loyaltiesCreationCfg.getDescription();
				
				// name
				nameAndDesc.get(0).sendKeys(name);
				
				//div[contains(text(),'Add new program')]//ancestor::table//tr[contains(text(),'Name')]
				
				// description
				if ( desc != null && desc.length() != 0 )
					nameAndDesc.get(1).sendKeys(desc);
				
				// press OK
				clickXPath("//div[contains(text(),'Add new program')]//ancestor::table//button[@title='Save']");
				
				// handle error on program name
				
				configureLoyaltyProgram( type, element2fill).
				saveLoyaltyProgram();
			}
		}
		
		return this;
	}

	/**
	 * 
	 */
	private void saveLoyaltyProgram() {
		
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
		final String saveButton = "//div[text()='"+( type.equals(LoyaltyTypes.Points) ? "New Class" : "New Badge Type" )+"']//ancestor::table//button[@title='Save']";
		
		for (String element : element2fill) {
			
			clickXPath(rule2Add);
			
			sendKeysByXPath(textField, element);
			
			try {
				Thread.sleep(1_000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			clickXPath(saveButton);
			
			// error handling on class/badge
			
		}
		
		clickXPath("//div[contains(text(),'Edit program')]//ancestor::table//button[@title='Close']");
		
		return this;
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
