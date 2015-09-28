package com.lumata.e4o.gui.loyaltymanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.common.PlaceHolderDate;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.campaignmanager.CampaignModelForm;
//import com.lumata.e4o.gui.loyaltymanager.LoyaltyManagementForm.WizardTab;
import com.lumata.e4o.gui.common.GWTCalendarForm;
import com.lumata.e4o.json.gui.campaignmanager.JSONAction;
import com.lumata.e4o.json.gui.campaignmanager.JSONCriteria;
import com.lumata.e4o.json.gui.campaignmanager.JSONEvent_;
import com.lumata.e4o.json.gui.loyaltymanager.LoyaltyManageCfg;

public class LoyaltyManagmentForm extends LoyaltyManagerForm {
	
	public LoyaltyManagmentForm(SeleniumWebDriver selenium, LoyaltyManageCfg setupLoyaltiesManagement, long timeout,
			long interval) {
		super(selenium, setupLoyaltiesManagement, timeout, interval);
		// TODO Auto-generated constructor stub
	}

	private enum WizardTab {
		Definition, Scheduling, Awarded, Redeemed,  Activation
	}

	public static boolean open(SeleniumWebDriver selenium, long timeout, long interval) {
		
		/* return MenuBar.select(selenium, new SectionImpl<MenuBar.HomeSections, String, String>(MenuBar.HomeSections.LOYALTY,
				MenuBar.HomeSections.LOYALTY.section_id_prefix,  // --> "gwt-debug-actrule-loyalty"  
				MenuBar.HomeSections.LOYALTY.section_type), timeout, interval); */
		
		WebElement sectionTab = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-actrule-loyalty", timeout, interval);
		sectionTab.click();
		
		return true;
	}
	
	public LoyaltyManagmentForm Management() throws FormException {
		
		super.open().clickXPath( "//div[text()='Management']" );
		
		return this;
	}
	
	public LoyaltyManagmentForm clickEditBadgeCreation() throws FormException, JSONSException {
		
		super.clickXPath("//div[contains(text(),'Program: BadgesProgram')]//ancestor::tbody/tr/td[3]//button[@title='Edit']");

	return this;
	}
	

	public LoyaltyManagmentForm clickaddBadgeLoyaltyPrograms() throws FormException, JSONSException {
		
		clickXPath("//div[contains(text(),'Badges')]/ancestor::tbody/tr[@class='cycle2 terminatedRow-cycle2 headers']/td//button[@title='Add']");

	return this;
	}
	
	public LoyaltyManagmentForm addBadgeLoyaltyProgramName(String name) throws FormException, JSONSException {
		
		super.sendKeysByXPath("//div[contains(text(),'Badge creation')]//ancestor::table/tbody//tr[@class='cycle1']/td[2]//input", name);
	
	return this;
	}
	

	public LoyaltyManagmentForm addBadgeLoyaltyProgramDesc(String desc) throws FormException, JSONSException {
		
		super.sendKeysByXPath("//div[contains(text(),'Badge creation')]//ancestor::table/tbody//tr[@class='cycle2']/td[2]//input", desc);
	
	return this;
	}

	public LoyaltyManagmentForm selectBadgeTypeName(String badgeType) throws FormException, JSONSException {
		
		super.selectByXPathAndVisibleText("//table[@class='marginTop10px marginBottom10px']/tbody/tr/td/table[@class='tableList Form']/tbody//tr[3]/td[2]//select", badgeType);

	return this;
	}
	
	public LoyaltyManagmentForm openSchedulingTab() throws FormException {
		
		super.clickXPath( getWizardTabXPath( WizardTab.Scheduling ) );
		
	return this;
		
	}
	
	private String getWizardTabXPath( WizardTab wizardTab ) {
		
		String wizardDefinitionTabXPath = "//div[contains(@class, 'dialogMiddleCenterInner dialogContent')]//a[text()='" + wizardTab.name() + "']";
		
		return wizardDefinitionTabXPath;
		
	}
	
	public LoyaltyManagmentForm setLoyaltySchedulingExecutionStart( String executionStartDate ) throws FormException {
		
		String loyaltySchedulingExecutionStartXPath = "//table[@class='marginTop10px marginBottom10px']/tbody/tr/td/table[@class='tableList Form']/tbody/tr[@class='cycle1'][1]//td[2]/input";
		
		configureGWTCalendarByXPath( loyaltySchedulingExecutionStartXPath, resolveDateField( executionStartDate ) );
				
		return this;
		
	}

	public LoyaltyManagmentForm  configureGWTCalendarByXPath( String xpath, Calendar date ) throws FormException, JSONException {
	
		GWTCalendarForm.
		create( selenium, timeout, interval ).
		openByXPath( xpath ).
		setDate( date );
	
		return this;
	
}
	
	
	
	public LoyaltyManagmentForm setLoyaltySchedulingExecutionStart( Calendar executionStartDate ) throws FormException {
		
		try {
			
			setLoyaltySchedulingExecutionStart( Format.getMysqlDateTime( executionStartDate ) );
		
		} catch (ParseException e) {
			
			throw new FormException( e.getMessage(), e );
			
		}
		
		return this;
		
	}
	
	
	public String getLoyaltySchedulingExecutionStart() throws FormException {

		return super.getValueByXPath("//table[@class='marginTop10px marginBottom10px']/tbody/tr/td/table[@class='tableList Form']/tbody/tr[@class='cycle1'][1]//td[2]/input");

	}
	

	private Calendar resolveDateField(String StringDateField) throws FormException {
		
		Calendar date = Calendar.getInstance();
		if( PlaceHolderDate.getInstance( StringDateField ).isPlaceHolderDate() )
			date = PlaceHolderDate.getInstance( StringDateField ).parse();						
		else 		
			try {
				date.setTime( new SimpleDateFormat("yyyy-MM-dd").parse( StringDateField ) );
			} catch (ParseException e) {
				throw new FormException("Error during SingleExecutionStart parsing " + StringDateField);
			}
				
		return date;
	}

	
	public LoyaltyManagmentForm setLoyaltySchedulingExecutionEnd( String executionEndDate ) throws FormException {
		
		String loyaltySchedulingExecutionEndXPath = "//table[@class='marginTop10px marginBottom10px']/tbody/tr/td/table[@class='tableList Form']/tbody/tr[@class='cycle2']//td[2]/input";
		
		configureGWTCalendarByXPath( loyaltySchedulingExecutionEndXPath, resolveDateField( executionEndDate ) );
				
		return this;
		
	}

	public String getLoyaltySchedulingExecutionEnd() throws FormException {

		return super.getValueByXPath("//table[@class='marginTop10px marginBottom10px']/tbody/tr/td/table[@class='tableList Form']/tbody/tr[@class='cycle2']//td[2]/input");

	}

	public  LoyaltyManagmentForm  setLoyaltySchedulingExecutionEnd( Calendar executionEndDate ) throws FormException {
		
		try {
			
			setLoyaltySchedulingExecutionEnd( Format.getMysqlDateTime( executionEndDate ) );
		
		} catch (ParseException e) {
			
			throw new FormException( e.getMessage(), e );
			
		}
		
		return this;
	}	
	
	
	
	public LoyaltyManagmentForm setLoyaltyRedeemDays( String redeemDays ) throws FormException {
		
		String loyaltyRedeemdaysXPath = "//table[@class='marginTop10px marginBottom10px']/tbody/tr/td/table[@class='tableList Form']/tbody/tr[@class='cycle1'][2]//td[2]/input";
				
		super.sendKeysByXPath(loyaltyRedeemdaysXPath, redeemDays);
		
		return this;
		
	}
	
	public String getLoyaltyRedeemDays() throws FormException {

		return super.getValueByXPath("//table[@class='marginTop10px marginBottom10px']/tbody/tr/td/table[@class='tableList Form']/tbody/tr[@class='cycle1'][2]//td[2]/input");

	}
	
	public LoyaltyManagmentForm openAwardedTab() throws FormException {
		
		super.clickXPath( getWizardTabXPath( WizardTab.Awarded ) );
		
	return this;
		
	}
	
	public LoyaltyManagmentForm openRedeemedTab() throws FormException {
		
		super.clickXPath( getWizardTabXPath( WizardTab.Redeemed ) );
		
	return this;
		
	}
	
	
	public LoyaltyManagmentForm openActivationTab() throws FormException {
		
		super.clickXPath( getWizardTabXPath( WizardTab.Activation ) );
		
	return this;
		
	}
	
	public LoyaltyManagmentForm addEvents(Map<String, JSONEvent_> events) throws JSONException, FormException {
		
		
		int eventRow = 1;
		
		for( String eventName : events.keySet() ) {
			
			eventRow++;
			
			
			//addAwardedEventButton();
			
			configureEvent( events.get( eventName ), eventRow );
						
		}
		
		return this;
		
	}
	
	public LoyaltyManagmentForm configureEvent( JSONEvent_ event, Integer eventRow ) throws JSONException, FormException {
		
		String loyaltyAwardeventXPath = "//table[@class='tableList rulesTable marginTop10px']/tbody//tr[2]/td/div[@class='act-ListBoxHolder act-ListBoxHolder-horizontal']/table/tbody/tr/td[@class='act-ListBox']";
		clickXPath( loyaltyAwardeventXPath ).
		
		//String eventXPath ="//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[" + eventRow + "]//*[@id='gwt-debug-ListCampaignModelCreationETType']";
		//clickXPath( eventXPath ).
		selectDropDownListItem( event.getEventType() );
		//addCriterae(event,eventRow);
		//addActions( event, eventRow );
		
		return this;
		
	}
	
	public LoyaltyManagmentForm addCriterae( JSONEvent_ event, Integer eventRow ) throws JSONException, FormException {

		Map<String, JSONCriteria> criteria = event.getCriteria();
		
		int criteriaRow = 0;
		
		for( String criteriaName : criteria.keySet() ) {
					
			addCriteria( eventRow );
			
			criteriaRow++;
						
			configureCriteria( criteria.get( criteriaName ), eventRow, criteriaRow );
												
		}
		
		return this;
		
	}
	
	public LoyaltyManagmentForm configureCriteria( JSONCriteria criteria, Integer eventRow, Integer criteriaRow ) throws JSONException, FormException {
		
		//String loyaltyAwardcriteriaXPath = "//div[@class='act-ListBoxHolder act-ListBoxHolder-horizontal']/table/tbody/tr/td[@class='act-ListBox act-ListBox-selected']";
		//clickXPath( loyaltyAwardcriteriaXPath ).
		//selectDropDownListItem( criteria );
		//String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow cycle2' ) and position() = " + eventRow + " ]//td[@class='column_criteria']"; 
		//String criteriaXPathRow = eventXPathRow + "//div[@class='criterionContainer']//table/tbody/tr["+ criteriaRow + "]";
		//String criteriaXPathRowAValue = criteriaXPathRow + "//*[@id='gwt-debug-TextCampaignModelCreationECValue']";
		String loyaltyAwardcriteriaXPath = "//div[@class='act-ListBoxHolder act-ListBoxHolder-horizontal']/table/tbody/tr/td[@class='act-ListBox act-ListBox-selected']";
		String loyaltyAwardcriteriavalueXPath = "//input[@id='gwt-debug-TextCampaignModelCreationECValue']";
		//sendKeysByXPath( loyaltyAwardcriteriavalueXPath,Value );
		
	
		/** configure action */
		
		clickXPath( loyaltyAwardcriteriaXPath ).
		selectDropDownListItem( criteria.getType() );
		
		if( null != criteria.getValue() ) { sendKeysByXPath( loyaltyAwardcriteriavalueXPath, criteria.getValue() ); }
				
		return this;
		
	}
		
	public LoyaltyManagmentForm addCriteria( Integer eventRow ) throws FormException {
		
		//super.clickXPath( "//td[@class='column_criteria']/table/tbody/tr/td/table/tbody/tr/td/div[@class='criterionContainer']/table/tbody/tr/td[3]/button[@class='gwt-Button']" );
		
		String eventXPathRow = "//td[@class='column_criteria']/table/tbody/tr/td/table/tbody/tr/td/div[@class='criterionContainer']"; 
		String criteriaXPathRowAAdd = eventXPathRow + "/table/tbody/tr/td[3]/button[@class='gwt-Button']";
		
		clickXPath( criteriaXPathRowAAdd );
		
		return this;
		
	}
	
	public LoyaltyManagmentForm addActions( JSONEvent_ event, Integer eventRow ) throws JSONException, FormException {

		Map<String, JSONAction> actions = event.getActions();
		
		int actionRow = 0;
		
		for( String actionName : actions.keySet() ) {
					
			addAction( eventRow );
			
			actionRow++;
						
			configureAction( actions.get( actionName ), eventRow, actionRow );
												
		}
		
		return this;
		
	}

	public LoyaltyManagmentForm configureAction( JSONAction action, Integer eventRow, Integer actionRow ) throws JSONException, FormException {
		
		String ruleActionPattern = "^Rulesets[.].*";		
		
		String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow' ) and position() = " + eventRow + " ]//td[@class='column_commodity']"; 
		String actionXPathRow = eventXPathRow + "//table[@class='commodityContainer']/tbody/tr[" + actionRow + "]";
		String actionXPathRowAType = actionXPathRow + "//*[@id='gwt-debug-ListCampaignModelCreationEAType']";
		String actionXPathRowAUnit = actionXPathRow + "//*[@id='gwt-debug-ListCampaignModelCreationEAUnit']";			
		String actionXPathRowAAutoAllocation = actionXPathRow + "//*[contains(text(), '::AUTO_ALLOCATE::') ]/parent::select";
		
		
		/** configure action */
		clickXPath( actionXPathRowAType ).
		selectDropDownListItem( action.getName() );
		
		if( null != action.getOption() ) { 
			
			if( action.getName().matches( ruleActionPattern ) ) {
				
				selectByXPathAndVisibleText( actionXPathRowAAutoAllocation.replace( "::AUTO_ALLOCATE::" , action.getOption() ), action.getOption() );
				
			} else { selectByXPathAndVisibleText( actionXPathRowAUnit, action.getOption() ); }
		
		}
				
		return this;
		
	}
	
	public LoyaltyManagmentForm addAction( Integer eventRow ) throws FormException {
		String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow' ) and position() = " + eventRow + " ]//td[@class='column_commodity']"; 
		String actionXPathRowAAdd = eventXPathRow + "//*[@id='gwt-debug-BtnCampaignModelCreationEAAdd']";
		
		clickXPath( actionXPathRowAAdd );
		
		return this;
		
		
	}
	public LoyaltyManagmentForm seteventType(String awardedEventType ) throws FormException {
		
		String loyaltyAwardeventXPath = "//table[@class='tableList rulesTable marginTop10px']/tbody//tr[2]/td/div[@class='act-ListBoxHolder act-ListBoxHolder-horizontal']/table/tbody/tr/td[@class='act-ListBox']";
		clickXPath( loyaltyAwardeventXPath ).
		selectDropDownListItem(awardedEventType );		
		
		return this;
		
	}
	
	public String geteventType(String Etype) throws FormException {

		return super.getValueByXPath("//table[@class='tableList rulesTable marginTop10px']/tbody//tr[2]/td/div[@class='act-ListBoxHolder act-ListBoxHolder-horizontal']/table/tbody/tr/td[@class='act-ListBox']");

	}
	
	public LoyaltyManagmentForm setcriteriaType( String criteria ) throws FormException {
		
		String loyaltyAwardcriteriaXPath = "//div[@class='act-ListBoxHolder act-ListBoxHolder-horizontal']/table/tbody/tr/td[@class='act-ListBox act-ListBox-selected']";
		clickXPath( loyaltyAwardcriteriaXPath ).
		selectDropDownListItem( criteria );		
		return this;
		
	}
	
	public String getcriteriaType() throws FormException {

		return super.getValueByXPath("//div[@class='act-ListBoxHolder act-ListBoxHolder-horizontal']/table/tbody/tr/td[@class='act-ListBox act-ListBox-selected']");

	}
	
	public LoyaltyManagmentForm setcriteriaValue( String Value ) throws FormException {
		
		String loyaltyAwardcriteriavalueXPath = "//input[@id='gwt-debug-TextCampaignModelCreationECValue']";
		sendKeysByXPath( loyaltyAwardcriteriavalueXPath,Value );
		
		return this;
		
	}
	
	public String getcriteriaValue() throws FormException {

		return super.getValueByXPath("//input[@id='gwt-debug-TextCampaignModelCreationECValue']");

	}
	
	
	public LoyaltyManagmentForm setActionType( String action ) throws FormException {
		
		String loyaltyAwardactionXPath = "//td[@id='gwt-debug-ListCampaignModelCreationEAType']";
		clickXPath( loyaltyAwardactionXPath ).
		selectDropDownListItem( action );		
		return this;
		
	}
	
	public String getactionType() throws FormException {

		return super.getValueByXPath("//td[@id='gwt-debug-ListCampaignModelCreationEAType']");

	}
	
	public LoyaltyManagmentForm setactionValue( String option ) throws FormException {
		
		String loyaltyAwardactionXPath = "//table[@class='commodityContainer']/tbody/tr/td[6]/select[@class='gwt-ListBox']";
		selectByXPathAndVisibleText( loyaltyAwardactionXPath,option );
		
		return this;
		
	}
	
	public String getactionValue() throws FormException {

		return super.getValueByXPath("//table[@class='commodityContainer']/tbody/tr/td[6]/select[@class='gwt-ListBox']");

	}
	
	public LoyaltyManagmentForm clickAwardedcheckbox() throws FormException {
		
		super.clickXPath("//tr[@class='contentRow cycle2']/td[5]//span[@class='gwt-CheckBox']/input");
		
		return this;
		
	}
	
	public LoyaltyManagmentForm addAwardedEventButton() throws FormException {
		
		super.clickXPath( "//table[@class='tableList rulesTable marginTop10px']/tbody/tr[3]/td[@class='column_eventType']/button[@name='btn-add']" );
			
		return this;
		
	}
	
	public LoyaltyManagmentForm addRedeemedEventButton() throws JSONException, FormException {
		
		super.clickXPath( "//table[@class='tableList rulesTable marginTop10px']/tbody/tr[3]/td[@class='column_eventType']/button[@name='btn-add']" );
			
		return this;
		
	}
	
	public LoyaltyManagmentForm addAwardedCriteriaButton() throws JSONException, FormException {
		
		super.clickXPath( "//td[@class='column_criteria']/table/tbody/tr/td/table/tbody/tr/td/div[@class='criterionContainer']/table/tbody/tr/td[3]/button[@class='gwt-Button']" );
			
		return this;
		
	}

	
	public LoyaltyManagmentForm addAwardedActionButton() throws JSONException, FormException {
		
		super.clickXPath( "//button[@id='gwt-debug-BtnCampaignModelCreationEAAdd']" );
			
		return this;
		
	}

	
	public LoyaltyManagmentForm addAwardedCriteria( Integer eventRow ) throws FormException {
		
		
		String eventXPathRow = "//*[@id='gwt-debug-FormCampaignModelCreationRules']//tr[contains(@class, 'contentRow cycle2' ) and position() = " + eventRow + " ]//td[@class='column_criteria']"; 
		String criteriaXPathRowAAdd = eventXPathRow + "//*[@id='gwt-debug-BtnCampaignModelCreationECAdd']";
		
		clickXPath( criteriaXPathRowAAdd );
		
		return this;
		
	}
	
	public LoyaltyManagmentForm clickAwardNotificationbutton() throws FormException {
		
		super.clickXPath("//button[@name='btn-notification']");
		
		return this;
		
	}
	
	public LoyaltyManagmentForm clickAwardNotificationEditbutton() throws FormException {
		
		super.clickXPath("//button[@id='gwt-debug-BtnCampaignModelCreationENENGEdit']");
		
		return this;
		
	}
	
	public LoyaltyManagmentForm AwardNotificationMessage(String NotifMessage) throws FormException {
		
		super.sendKeysByXPath("//textarea[@id='gwt-debug-TextCampaignModelCreationENEValue']", NotifMessage);
		
		super.clickXPath("//button[@id='gwt-debug-BtnCampaignModelCreationENESave']");
		
		super.clickXPath("//button[@id='gwt-debug-BtnCampaignModelCreationENOk']");
		
		return this;
		
	}
	
	public String getAwardNotificationMessage() throws FormException {

		return super.getValueByXPath("//textarea[@id='gwt-debug-TextCampaignModelCreationENEValue']");

	}
	
	public LoyaltyManagmentForm ActivateLoyaltyProgram() throws FormException {
		
		super.clickXPath( "//table[@class='buttonPanel marginTop10px']/tbody/tr/td[5]/button[@name='btn-activate']" );
		
	return this;
	}
	
	public Boolean isLoyaltyProgramInList(String loyaltyName ) throws FormException{
		
		List<WebElement> loyaltyList1 = getLoyaltyListProgram(loyaltyName);

		for( WebElement loyaltyListE1 : loyaltyList1 ) {

			if( loyaltyListE1.getText().trim().equals( loyaltyName ) ) {
		
				return true;

			}	
		}

		return false;	
	}

	
	public List<WebElement> getLoyaltyListProgram(String strBadgePName)  throws FormException {
		
		String rootPath = "//div[@class='dialogMiddleCenterInner dialogContent']/table/tbody/tr/td/table/tbody/tr/td/div/div/table/tbody/tr/td/table/tbody";
		String subPath = "//tr[@class='contentRow cycle2 activatedRow-cycle2']/td/div[text()='" + strBadgePName + "']";

		List<WebElement> loyaltyList1 = getListByXPath(rootPath, rootPath + subPath);
		System.out.println(loyaltyList1);
		return loyaltyList1;
	}
	
	public LoyaltyManagmentForm clickclosebutton() throws FormException, JSONSException {
		
		super.clickXPath("//button[@name='btn-close']");
		waitForPageLoad();

	return this;
	}
	
	public LoyaltyManagmentForm clickActivatebutton(String strBadgePName) throws FormException, JSONSException {
		
		waitForPageLoad();
		String rootPath=("//table[@class='gwt-DisclosurePanel DPGraph gwt-DisclosurePanel-open']/tbody/tr[2]/td/div/table[@class='content']/tbody/tr/td");
		String subPath=("/table[@class='tableList']/tbody/tr/td/div[text()='" + strBadgePName + "']//ancestor::tbody/tr[@class='contentRow cycle2 savedRow-cycle2']/td[3]/table/tbody/tr/td[5]/button[@title='Activate']");
		super.clickXPath(rootPath, rootPath + subPath);
		

		//div[@class='marginTop10px']/div/table/tbody/tr[3]/td/table[@class='gwt-DisclosurePanel DPGraph gwt-DisclosurePanel-open']/tbody/tr[2]/td/div/table[@class='content']/tbody/tr/td/table[@class='tableList']/tbody/tr[1]/td/div[text()='Program: LoyaltyRewards']//ancestor::tbody/tr[@class='contentRow cycle2 savedRow-cycle2']/td[3]/table/tbody/tr/td[5]/button[@title='Activate']

		
	return this;
	}
	
	public Boolean handleJavascriptAlertAcceptDismiss(Boolean accept) {
		
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
			pressed = Boolean.TRUE;
		}
		
		return pressed;
	}

	
		
}

