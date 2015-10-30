package com.lumata.e4o.gui.catalogmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.json.gui.catalogmanager.JSONRuleChannel;
import com.lumata.e4o.json.gui.catalogmanager.JSONRules;

//created by Isha Vyas
public class RulesForm extends OfferOptimisationForm {

	private JSONRules ruleCfg;
	
	private final String CHANNEL_ROW_XPATH = "//div[contains(@class,'e4ol-list__cell--text') and contains(text(),'{channelName}')]/parent::div";

	public enum ElementErrorActionType {

		RETURN_ERROR, ABORT_CANCEL, ADD_TIMESTAMP_TO_FIELD;

	};

	public enum OptimizationAlgorithm {

		RandomAssigment("Random Assigment"), 
		BestOffer("Best Offer"), 
		Historybased("History based"), 
		Stockbased("Stock based"), 
		PreferenceBased("Preference Based"), 
		CustomBased("Custom Based");
		
		private String value;

		OptimizationAlgorithm(String value) {

			this.value = value;
		}

		public String value() {

			return this.value;
			
		}
		
	}

	public enum ExpiredOfferBehaviour {
		
		Selectvalue("Select value..."), 
		Pickupnewoffer("Pick up new offer"), 
		Bypassoffervaliditydate("Bypass offer validity date");
		
		private String value;

		ExpiredOfferBehaviour(String value) {

			this.value = value;
		}

		public String value() {

			return this.value;
		}

	}

	public enum MaxOffersPerTimePeriodUnit {
		
		Selectvalue("Select value..."), 
		Day("Day"), 
		Week("Week"),
		Month("Month");
		
		private String value;

		MaxOffersPerTimePeriodUnit(String value) {

			this.value = value;
		}

		public String value() {

			return this.value;
		}

	}

	public RulesForm(SeleniumWebDriver selenium, JSONRules ruleCfg,
			long timeout, long interval) {

		super(selenium, timeout, interval);

		this.ruleCfg = ruleCfg;

	}

	//Latest one
	public RulesForm(SeleniumWebDriver selenium, long timeout, long interval) {
		super(selenium, timeout, interval);

	}

	public RulesForm openForm() throws FormException {

		super.open(OfferOptimisationSection.RULES);

		return this;

	}

	// Click Add button to add new Rule
	public RulesForm addBtn() throws FormException {
		
		super.clickName("btn-add");

		return this;

	}

	// Set RuleName field
	public RulesForm setName(String name) throws FormException {

		super.sendKeysByName("name", name);

		return this;

	}

	// Get RuleName
	public String getName() throws FormException {

		return super.getValueByName("name");

	}

	// Set RuleDescription
	public RulesForm setDescription(String description) throws FormException {

		super.sendKeysByXPath("//textarea[@ng-model='ruleset.description']", description );

		return this;

	}

	// Get RuleDescription
	public String getDescription() throws FormException {

		return super.getValueByXPath("//textarea[@ng-model='ruleset.description']");

	}

	// Set TokenType from list of token
	public RulesForm setTokenType(String text) throws FormException {
		super.selectByNameAndVisibleText("tokenType", text);
		return this;
	}

	// Set Channels Type
	public RulesForm setChannels(JSONArray channelRules) throws FormException {
		super.multiselectByXPathAndVisibleText( "//select[@multiple]", ruleCfg.getRuleChannelsAsArray());
		return this;

	}

	// Set Channel Type
	public RulesForm addChannel( String channelName ) throws FormException {
		super.multiselectByXPathAndVisibleText( "//select[@multiple]", channelName );
		return this;

	}
	
	// Get Selected Channels
	private List<WebElement> getSelectedChannels( String xpath ) throws FormException {
		
		List<WebElement> salesChannels = super.searchListByXPath( "//ul[@class='select2-choices']", xpath );

		return salesChannels;
		
	}

	// Get Selected Channels
	public List<WebElement> getSelectedChannels() throws FormException {
		
		return getSelectedChannels( "//li[@class='select2-search-choice']" );
		
	}
	
	// Remove All Selected Channels
	public RulesForm removeAllSelectedChannels() throws FormException {
		
		List<WebElement> salesChannels = this.getSelectedChannels( "//a[@class='select2-search-choice-close']" );
		
		while( salesChannels.size() > 0 ) {
			
			WebElement salesChannel = salesChannels.get(0);
			
			salesChannel.click();
			
			try {			
				
				salesChannels = this.getSelectedChannels( "//a[@class='select2-search-choice-close']" );
			
			} catch( FormException fe ) { break; }
			
		}

		return this;
		
	}
	
	// Select OptimizationAlgorithm
	public RulesForm setAlgorithm( OptimizationAlgorithm algorithm ) throws FormException {
		return setAlgorithm(algorithm.value());
	}
	
	// Select OptimizationAlgorithm
	public RulesForm setAlgorithm(String algorithm) throws FormException {
		super.selectByNameAndVisibleText("algorithm", algorithm);
		return this;
	}
	
	// Get OptimizationAlgorithm
	public String getAlgorithm() throws FormException {
		return super.getValueByName("algorithm");
		
	}

	// Set MaxNumber of Offers
	public RulesForm setMaxNumberOfOffers( Integer maxNumberOfOffers ) throws FormException {
		super.sendKeysByName("usage", String.valueOf( maxNumberOfOffers ));
		return this;
	}
	
	// Set MaxNumber of Offers
	public RulesForm setMaxNumberOfOffers( String maxNumberOfOffers ) throws FormException {
		super.sendKeysByName("usage", maxNumberOfOffers);
		return this;
	}

	// Select ExpiredOfferBehaviour
	public RulesForm setExpiredOfferBehaviour( ExpiredOfferBehaviour expiredOfferBehaviour ) throws FormException {
		return setExpiredOfferBehaviour( expiredOfferBehaviour.value() );
	}
	
	// Select ExpiredOfferBehaviour
	public RulesForm setExpiredOfferBehaviour( String expiredOfferBehaviour ) throws FormException {
		super.selectByNameAndVisibleText("redeemExpiredOfferBehavior", expiredOfferBehaviour);
		return this;
	}
	
	public String getExpiredOfferBehaviour() throws FormException {
		return super.getValueByName("redeemExpiredOfferBehavior");
	}

	// Click on save button of saveRule
	public RulesForm saveBtn() throws FormException {

		super.clickXPath("//a[@name='btn-add']");

		return this;

	}

	// Click on save button of editRule
	public RulesForm saveEditBtn() throws FormException {

		super.clickXPath("//a[@name='btn-edit']");

		return this;

	}

	public RulesForm editRule() throws FormException {

		super.clickXPath("//a[@name='btn-edit']");

		return this;

	}

	// Click on Cancel Button
	public RulesForm cancelBtn() throws FormException {

		super.clickXPath("//a[@name='btn-cancel']");

		return this;

	}

	// Check YesRadiobutton of KeepOfferConsistent over multiple redraws
	public RulesForm setKeepOfferConsistentYes() throws FormException {

		super.clickId("keepOffersConsistent-yes");
		return this;
	}

	// Check NoRadioButton of KeepOfferConsistent over multiple redraws
	public RulesForm setKeepOfferConsistentNo() throws FormException {

		super.clickId("keepOffersConsistent-no");
		return this;
	}

	// Click Yes RadioButton of IncludePreviouslyAcceptedOffer
	public RulesForm setPrevioslyAcceptedOfferYes() throws FormException {

		super.clickId("previousOffersDrawnIncluded-yes");
		return this;
	}

	// Click No RadioButton of IncludePreviouslyAcceptedOffer
	public RulesForm setPrevioslyAcceptedOfferNo() throws FormException {

		super.clickId("previousOffersDrawnIncluded-no");
		return this;
	}

	// Reset MaxOffersPerAllocation for a specific channel
	public RulesForm resetMaxOffersPerAllocation( String channelName ) throws FormException {
		
		String MAX_OFFERS_PER_ALLOCATION_XPATH = CHANNEL_ROW_XPATH.replace( "{channelName}", channelName ) + "//input[@ng-model='channel.maxOffers']";
		
		super.sendKeysByXPath( MAX_OFFERS_PER_ALLOCATION_XPATH, "" );
		
		return this;
		
	}

	// Set MaxOffersPerAllocation for a specific channel
	public RulesForm setMaxOffersPerAllocation( String channelName, Integer maxOffersPerAllocation ) throws FormException {
		
		return setMaxOffersPerAllocation( channelName, String.valueOf( maxOffersPerAllocation ) );
	
	}

	// Set MaxOffersPerAllocation for a specific channel
	public RulesForm setMaxOffersPerAllocation( String channelName, String maxOffersPerAllocation ) throws FormException {
		
		String MAX_OFFERS_PER_ALLOCATION_XPATH = CHANNEL_ROW_XPATH.replace( "{channelName}", channelName ) + "//input[@ng-model='channel.maxOffers']";
		
		super.sendKeysByXPath( MAX_OFFERS_PER_ALLOCATION_XPATH, maxOffersPerAllocation );

		return this;
	
	}

	// Reset MaxOffersPerTimePeriodValue for a specific channel
	public RulesForm resetMaxOffersInTimePeriodValue( String channelName ) throws FormException {
		
		String MAX_OFFERS_PER_TIME_PERIOD_XPATH = CHANNEL_ROW_XPATH.replace( "{channelName}", channelName ) + "//input[@ng-model='channel.maxOfferInPeriod']";
		
		super.sendKeysByXPath( MAX_OFFERS_PER_TIME_PERIOD_XPATH, "" );
		
		return this;
		
	}

	// Set MaxOffersPerTimePeriodValue for a specific channel
	public RulesForm setMaxOffersInTimePeriodValue( String channelName, Integer maxOffersInTimePeriodValue ) throws FormException {
		
		return setMaxOffersInTimePeriodValue( channelName, String.valueOf( maxOffersInTimePeriodValue ) );
	
	}

	// Set MaxOffersPerTimePeriodValue for a specific channel
	public RulesForm setMaxOffersInTimePeriodValue( String channelName, String maxOffersInTimePeriodValue ) throws FormException {
		
		String MAX_OFFERS_PER_TIME_PERIOD_XPATH = CHANNEL_ROW_XPATH.replace( "{channelName}", channelName ) + "//input[@ng-model='channel.maxOfferInPeriod']";
		
		super.sendKeysByXPath( MAX_OFFERS_PER_TIME_PERIOD_XPATH, maxOffersInTimePeriodValue );

		return this;
	
	}

	// Reset MaxOffersPerTimePeriodUnit for a specific channel
	public RulesForm resetMaxOffersInTimePeriodUnit( String channelName ) throws FormException {
		
		return setMaxOffersInTimePeriodUnit( channelName, MaxOffersPerTimePeriodUnit.Selectvalue );
		
	}

	// Set MaxOffersPerTimePeriodUnit for a specific channel
	public RulesForm setMaxOffersInTimePeriodUnit( String channelName, MaxOffersPerTimePeriodUnit maxOffersInTimePeriodUnit ) throws FormException {
		
		return setMaxOffersInTimePeriodUnit( channelName, maxOffersInTimePeriodUnit.value() );
	
	}

	// Set MaxOffersPerTimePeriodUnit for a specific channel
	public RulesForm setMaxOffersInTimePeriodUnit( String channelName, String maxOffersInTimePeriodUnit ) throws FormException {
		
		String MAX_OFFERS_PER_TIME_PERIOD_XPATH = CHANNEL_ROW_XPATH.replace( "{channelName}", channelName ) + "//select[@name='period']";
		
		super.selectByXPathAndVisibleText( MAX_OFFERS_PER_TIME_PERIOD_XPATH, maxOffersInTimePeriodUnit );
		
		return this;
	
	}
	
	// Check Mandatory box
	public RulesForm checkMandatoryChannel( String channelName ) throws FormException {
		
		String MANDATORY_CHANNEL_XPATH = CHANNEL_ROW_XPATH.replace( "{channelName}", channelName ) + "//input[@ng-model='channel.mandatory']";
		
		if(!super.isSelectedByXPath(MANDATORY_CHANNEL_XPATH) ) {
			
			super.clickXPath(MANDATORY_CHANNEL_XPATH);
		
		}
				
		return this;
		
	}

	// Uncheck Mandatory box
	public RulesForm uncheckMandatoryChannel( String channelName ) throws FormException {
		
		String MANDATORY_CHANNEL_XPATH = CHANNEL_ROW_XPATH.replace( "{channelName}", channelName ) + "//input[@ng-model='channel.mandatory']";
		
		if(super.isSelectedByXPath(MANDATORY_CHANNEL_XPATH) ) {
			
			super.clickXPath(MANDATORY_CHANNEL_XPATH);
		
		}
				
		return this;
		
	}
	
	// Set Priority if Mandatory
	public RulesForm setPriorityChannel( String channelName, Integer priority ) throws FormException {
		
		return setPriorityChannel( channelName, String.valueOf( priority ) );
	
	}
	
	// Set Priority if Mandatory
	public RulesForm setPriorityChannel( String channelName, String priority ) throws FormException {
		
		String PRIORITY_CHANNEL_XPATH = CHANNEL_ROW_XPATH.replace( "{channelName}", channelName ) + "//input[@ng-model='channel.priority']";
		
		super.sendKeysByXPath( PRIORITY_CHANNEL_XPATH, priority );
		
		return this;
	
	}
	
	private Boolean isFieldInvalid(WebElement el) throws FormException {
		WebElement e1 = super.search(SeleniumUtils.SearchBy.XPATH,
				"//input[contains(@class,'ng-invalid')]");
		return e1.isDisplayed();
	}

	// To check form is valid
	private Boolean isFieldValid(WebElement el) throws FormException {
		WebElement e1 = super.search(SeleniumUtils.SearchBy.XPATH,
				"//input[contains(@class,'ng-valid')]");
		return e1.isDisplayed();
	}

	// To check form is invalid
	public Boolean formIsInvalid() throws FormException {

		WebElement name = super.search(SeleniumUtils.SearchBy.NAME, "name");
		WebElement description = super.search(SeleniumUtils.SearchBy.NAME,
				"description");
		WebElement tokenType = super.search(SeleniumUtils.SearchBy.NAME,
				"tokenType");
		WebElement channel = super.search(SeleniumUtils.SearchBy.XPATH,
				"//select[@multiple]");
		WebElement algorithm = super.search(SeleniumUtils.SearchBy.NAME,
				"algorithm");
		WebElement expiredOfferBehaviour = super.search(
				SeleniumUtils.SearchBy.NAME, "redeemExpiredOfferBehavior");

		return (isFieldInvalid(name) && isFieldInvalid(description)
				&& isFieldInvalid(tokenType) && isFieldInvalid(channel)
				&& isFieldInvalid(algorithm) && isFieldInvalid(expiredOfferBehaviour));

	}

	// To check form is valid
	public Boolean formIsValid() throws FormException {

		WebElement name = super.search(SeleniumUtils.SearchBy.NAME, "name");
		WebElement description = super.search(SeleniumUtils.SearchBy.NAME,
				"description");
		WebElement tokenType = super.search(SeleniumUtils.SearchBy.NAME,
				"tokenType");
		WebElement channel = super.search(SeleniumUtils.SearchBy.XPATH,
				"//select[@multiple]");
		WebElement algorithm = super.search(SeleniumUtils.SearchBy.NAME,
				"algorithm");
		WebElement expiredOfferBehaviour = super.search(
				SeleniumUtils.SearchBy.NAME, "redeemExpiredOfferBehavior");

		return (isFieldValid(name) && isFieldValid(description)
				&& isFieldValid(tokenType) && isFieldValid(channel)
				&& isFieldValid(algorithm) && isFieldValid(expiredOfferBehaviour));

	}

	// To check RuleName is duplicate or not
	public Boolean isRuleNameDuplicated() throws FormException {

		closeAngularFrame();
		Boolean isTokenDuplicated = false;

		final String DIALOG_XPATH = "//div[@class='gwt-DialogBox errorDialog']";
		final String DIALOG_ERROR_MESSAGE_XPATH = DIALOG_XPATH
				+ "//div[contains(text(), 'This value already exists')]";
		final String DIALOG_BTN_XPATH = DIALOG_XPATH + "//button[@title='OK']";

		WebElement errorMessage = super.search(SeleniumUtils.SearchBy.XPATH,
				DIALOG_ERROR_MESSAGE_XPATH, 1000L, 200L);

		if (null != errorMessage) {

			isTokenDuplicated = true;

			super.clickXPath(DIALOG_BTN_XPATH);

		}

		openAngularFrame();

		return isTokenDuplicated;

	}
	
	public List<WebElement> getRuleList() throws FormException {
		
		super.waitVisibleElementByXPath("//div[contains(@class ,'e4ol-list')]");
		
		List<WebElement> ruleList = new ArrayList<WebElement>();
		
		try {
		
			ruleList = super.getListByXPath("//div[contains(@class ,'e4ol-list')]","//div[contains(@class , 'e4ol-list__cell ng-binding')]");
			
		} catch( FormException fe ) {
			
			/** in case of empty list **/;
		
		}
		
		return ruleList;
		
	}
	
	public Boolean isRuleNameInList( String strRuleName ) throws FormException {
		
		List<WebElement> ruleList = getRuleList();
				
		for( WebElement ruleNameEl : ruleList ) {
			
			if(ruleNameEl.getText().trim().equals( strRuleName ) ) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	public RulesForm editByName( String strRuleName ) throws FormException {
		
		List<WebElement> ruleList = getRuleList();
				
		for( int i=0;i<ruleList.size();i++) {
			
			if(ruleList.get(i).getText().trim().equals( strRuleName ) ) {
				
				this.clickXPath("//div[contains(@class ,'e4ol-list')]//div[contains(@class , 'e4ol-list__row')]["+(i+2)+"]//a[@name='btn-edit']");
				
			}
			
		}
		
		return this;
		
	}

	public RulesForm copyByName( String strRuleName ) throws FormException {
		
		List<WebElement> ruleList = getRuleList();
				
		for( int i=0;i<ruleList.size();i++) {
			
			if(ruleList.get(i).getText().trim().equals( strRuleName ) ) {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class ,'e4ol-list')]//div[contains(@class , 'e4ol-list__row')]["+(i+2)+"]//a[@name='btn-copy']")));
				this.clickXPath("//div[contains(@class ,'e4ol-list')]//div[contains(@class , 'e4ol-list__row')]["+(i+2)+"]//a[@name='btn-copy']");
				break;
				
			}
			
		}
		
		return this;
		
	}

	// old methods which will become obsolete later on. I kept it as its
	// impacting other test cases right now. To remove compilation issue I am
	// keeping it here for the time being till we develop those testcases which
	// are impacting it.
	public RulesForm addRules() throws FormException, JSONException {

		JSONArray rules = ruleCfg.getList();
		try {
			for (int ruleIndex = 0; ruleIndex < rules.length(); ruleIndex++) {

				ruleCfg.setRuleById(ruleIndex);

				if (ruleCfg.getEnabled()) {

					clickLink("Add");
					configureRule().
					saveBtn().
					manageErrorAction( ruleCfg.getErrorActions().getString( "ELEMENT_ALREADY_EXISTS"));

				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return this;

	}

	public RulesForm configureRule() throws FormException, JSONException {

		sendKeysByName("name", ruleCfg.getName()).
		sendKeysByXPath("//textarea[@ng-model='ruleset.description']", ruleCfg.getDescription() ).
		selectByNameAndVisibleText("tokenType", ruleCfg.getTokenType() ).
		multiselectByXPathAndVisibleText("//select[@multiple]", ruleCfg.getRuleChannelsAsArray());
		configureRuleChannels().
		selectByNameAndVisibleText("algorithm", ruleCfg.getOptimizationAlgorithm()).
		sendKeysByName("usage", ruleCfg.getMaximumNumberOfOffers());

		selectByNameAndVisibleText("redeemExpiredOfferBehavior", ruleCfg.getExpiredOfferBehaviour());

		if (ruleCfg.getKeepOffersConsistentAcrossMultipleRedraws()) {
			clickId("keepOffersConsistent-yes");
		} else {
			clickId("keepOffersConsistent-no");
		}

		if (ruleCfg.getIncludePreviouslyAcceptedOffers()) {
			clickId("previousOffersDrawnIncluded-yes");
		} else {
			clickId("previousOffersDrawnIncluded-no");
		}

		return this;

	}

	public RulesForm configureRuleChannels() throws JSONException, FormException {

		Map<String, JSONRuleChannel> ruleChannels = ruleCfg.getRuleChannels();

		for (String ruleChannelName : ruleChannels.keySet()) {

			JSONRuleChannel ruleChannel = ruleCfg
					.getRuleChannelByName(ruleChannelName);

			if (ruleChannel.getMandatory()) {
				clickXPath("//div[@ng-repeat='channel in ruleset.ruleSetChannel']//div[contains(text(),'"
						+ ruleChannel.getName()
						+ "')]//ancestor::div[1]//input[@type='checkbox']");
			}

			if (!ruleChannel.getUnlimited()) {
				sendKeysByXPath(
						"//div[@ng-repeat='channel in ruleset.ruleSetChannel']//div[contains(text(),'"
								+ ruleChannel.getName()
								+ "')]//ancestor::div[1]//input[@type='number']",
						ruleChannel.getMaxOffer());
			}

		}

		return this;

	}

	public RulesForm manageErrorAction(String errorAction) throws FormException {

		closeAngularFrame();

		try {

			searchByXPath("//div[@class='gwt-DialogBox errorDialog']", 2000, 50);

		} catch (FormException fe) {

			// no error to manage

		}

		if (status) {

			clickXPath("//div[@class='gwt-DialogBox errorDialog']//button");
					
			super.openAngularFrame();

			switch (ElementErrorActionType.valueOf(errorAction)) {

				case RETURN_ERROR: {
	
					throw new FormException("Error in the form navigation");
	
				}
				case ABORT_CANCEL: {
	
					cancelBtn();
	
					break;
				}
				case ADD_TIMESTAMP_TO_FIELD: {
	
					String name_with_timestamp = ruleCfg.getName()
							+ "_"
							+ String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System
									.currentTimeMillis()));
	
					ruleCfg.setName(name_with_timestamp);
	
					clearByName("name").sendKeysByName("name", ruleCfg.getName());
					
					saveBtn();
	
					break;
				}

			}

		} else {
			openAngularFrame();
		}

		status = true;

		return this;

	}

	@Override
	public RulesForm goToHome() throws FormException{
	
		close();
		
		super.goToHome();
		
		return this;
	}

}
