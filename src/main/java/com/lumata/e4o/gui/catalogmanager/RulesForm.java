package com.lumata.e4o.gui.catalogmanager;

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

	public enum ElementErrorActionType {

		RETURN_ERROR, ABORT_CANCEL, ADD_TIMESTAMP_TO_FIELD;

	};

	public enum optimizationAlgorithm {

		RandomAssigment("Random Assigment"), BestOffer("Best Offer"), Historybased(
				"History based"), Stockbased("Stock based}"), PreferenceBased(
				"Preference Based"), CustomBased("Custom Based");
		private String value;

		optimizationAlgorithm(String value) {

			this.value = value;
		}

		public String value() {

			return this.value;
		}
	}

	public enum expiredOfferBehaviour {
		Selectvalue("Select value..."), Pickupnewoffer("Pick up new offer"), Bypassoffervaliditydate(
				"Bypass offer validity date");
		private String value;

		expiredOfferBehaviour(String value) {

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
	public RulesForm clickAddBtn() throws FormException {
		
		super.clickName("btn-add");

		//super.clickLink("Add");
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

		super.sendKeysByXPath("//textarea[@ng-model='ruleset.description']",
				description);

		return this;

	}

	// Get RuleDescription
	public String getDescription() throws FormException {

		return super
				.getValueByXPath("//textarea[@ng-model='ruleset.description']");

	}

	// Set TokenType from list of token
	public RulesForm setTokenType(String text) throws FormException {
		super.selectByNameAndVisibleText("tokenType", text);
		return this;
	}

	// Select Channel Type
	public RulesForm setChannel(JSONArray channelRules) throws FormException {
		super.multiselectByXPathAndVisibleText( "//select[@multiple]", ruleCfg.getRuleChannelsAsArray());
		return this;

	}

	// Select OptimizationAlgorithm
	public RulesForm setAlgorithm(String text) throws FormException {
		super.selectByNameAndVisibleText("algorithm", text);
		return this;
	}
	public String getAlgorithm() throws FormException {
		return super.getValueByName("algorithm");
		
	}

	// Set MaxNumber of Offers
	public RulesForm setMaxNumberOfOffers(String text) throws FormException {
		super.sendKeysByName("usage", text);
		return this;
	}

	// Select ExpiredOfferBehaviour
	public RulesForm setExpiredOfferBehaviour(String text) throws FormException {
		super.selectByNameAndVisibleText("redeemExpiredOfferBehavior", text);
		return this;
	}
	public String getExpiredOfferBehaviour() throws FormException {
		return super.getValueByName("redeemExpiredOfferBehavior");
	}

	// Click on save button of saveRule
	public RulesForm saveRule() throws FormException {

		super.clickXPath("//a[@name='btn-add']");

		return this;

	}
	public RulesForm editSaveRule() throws FormException {

		super.clickXPath("//a[@name='btn-edit']");

		return this;

	}

	// Click on Cancel Button
	public RulesForm cancelRule() throws FormException {

		super.clickXPath("//a[@name='btn-cancel']");

		return this;

	}

	// Check YesRadiobutton of KeepOfferConsistent over multiple redraws
	public RulesForm clickKeepOfferConsistentYes() throws FormException {

		super.clickId("keepOffersConsistent-yes");
		return this;
	}

	// Check NoRadioButton of KeepOfferConsistent over multiple redraws
	public RulesForm clickKeepOfferConsistentNo() throws FormException {

		super.clickId("keepOffersConsistent-no");
		return this;
	}

	// Click Yes RadioButton of IncludePreviouslyAcceptedOffer
	public RulesForm clickPrevioslyAcceptedOfferYes() throws FormException {

		super.clickId("previousOffersDrawnIncluded-yes");
		return this;
	}

	// Click No RadioButton of IncludePreviouslyAcceptedOffer
	public RulesForm clickPrevioslyAcceptedOfferNo() throws FormException {

		super.clickId("previousOffersDrawnIncluded-no");
		return this;
	}

	// Set MaxNumberofOffers
	public RulesForm selectMaxOffersFromChannel(String text)
			throws FormException {
		super.sendKeysByXPath("//input[@ng-model='channel.maxOffers']", text);
		return this;
	}

	// Click on CheckMandatoryBox
	public RulesForm checkMandatoryBox() throws FormException {
		super.clickXPath("//input[@ng-model='channel.mandatory']");
		return this;
	}

	// Set Priority if Mandatory
	public RulesForm setPriorityIfMandatory(String text) throws FormException {
		super.sendKeysByXPath("//input[@ng-model='channel.priority']", text);
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
		//waitForPageLoad();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class ,'e4ol-list')]")));
		List<WebElement> ruleList = super.getListByXPath("//div[contains(@class ,'e4ol-list')]","//div[contains(@class , 'e4ol-list__cell ng-binding')]");

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
public RulesForm editRuleByName( String strRuleName ) throws FormException {
		
		List<WebElement> ruleList = getRuleList();
				
		for( int i=0;i<ruleList.size();i++) {
			
			if(ruleList.get(i).getText().trim().equals( strRuleName ) ) {
				
				this.clickXPath("//div[contains(@class ,'e4ol-list')]//div[contains(@class , 'e4ol-list__row')]["+(i+2)+"]//a[@name='btn-edit']");
				
			}
			
		}
		
		return this;
		
	}
public RulesForm copyRuleByName( String strRuleName ) throws FormException {
	
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

					clickLink("Add")
							.configureRule()
							.saveRule()
							.manageErrorAction(
									ruleCfg.getErrorActions().getString(
											"ELEMENT_ALREADY_EXISTS"));

				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return this;

	}

	public RulesForm configureRule() throws FormException, JSONException {

		sendKeysByName("name", ruleCfg.getName())
				.sendKeysByXPath("//textarea[@ng-model='ruleset.description']",
						ruleCfg.getDescription())
				.selectByNameAndVisibleText("tokenType", ruleCfg.getTokenType())
				.multiselectByXPathAndVisibleText("//select[@multiple]",
						ruleCfg.getRuleChannelsAsArray())
				.configureRuleChannels()
				.selectByNameAndVisibleText("algorithm",
						ruleCfg.getOptimizationAlgorithm())
				.sendKeysByName("usage", ruleCfg.getMaximumNumberOfOffers());

		selectByNameAndVisibleText("redeemExpiredOfferBehavior",
				ruleCfg.getExpiredOfferBehaviour());

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

	public RulesForm configureRuleChannels() throws JSONException,
			FormException {

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

			clickXPath("//div[@class='gwt-DialogBox errorDialog']//button")
					.openAngularFrame();

			switch (ElementErrorActionType.valueOf(errorAction)) {

			case RETURN_ERROR: {

				throw new FormException("Error in the form navigation");

			}
			case ABORT_CANCEL: {

				cancelRule();

				break;
			}
			case ADD_TIMESTAMP_TO_FIELD: {

				String name_with_timestamp = ruleCfg.getName()
						+ "_"
						+ String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System
								.currentTimeMillis()));

				ruleCfg.setName(name_with_timestamp);

				clearByName("name").sendKeysByName("name", ruleCfg.getName())
						.saveRule();

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
	public RulesForm clickName(String name) throws FormException {

		super.clickName(name);

		return this;

	}

	@Override
	public RulesForm clickXPath(String xpath) throws FormException {

		super.clickXPath(xpath);

		return this;

	}

	@Override
	public RulesForm clickLink(String link) throws FormException {

		super.clickLink(link);

		return this;

	}

	@Override
	public RulesForm sendKeysByName(String name, String text)
			throws FormException {

		super.sendKeysByName(name, text);

		return this;

	}

	@Override
	public RulesForm sendKeysByXPath(String xpath, String text)
			throws FormException {

		super.sendKeysByXPath(xpath, text);

		return this;

	}

	@Override
	public RulesForm sendKeysByLink(String link, String text)
			throws FormException {

		super.sendKeysByLink(link, text);

		return this;

	}

	@Override
	public RulesForm selectByName(String name, String label)
			throws FormException {

		super.selectByName(name, label);

		return this;

	}

	@Override
	public RulesForm clearByName(String xpath) throws FormException {

		super.clearByName(xpath);

		return this;

	}

	@Override
	public RulesForm typeByName(String name, String text) throws FormException {

		super.typeByName(name, text);

		return this;

	}

	@Override
	public RulesForm multiselectByXPathAndVisibleText(String xpath,
			JSONArray list) throws FormException {

		super.multiselectByXPathAndVisibleText(xpath, list);

		return this;

	}

	@Override
	public RulesForm selectByNameAndVisibleText(String name, String text)
			throws FormException {

		super.selectByNameAndVisibleText(name, text);

		return this;

	}

	@Override
	public RulesForm openAngularFrame() throws FormException {

		super.openAngularFrame();

		return this;

	}

	@Override
	public RulesForm closeAngularFrame() throws FormException {

		super.closeAngularFrame();

		return this;

	}
	
	@Override
	
	public RulesForm goToHome() throws FormException{
			close();
		
		super.goToHome();
		
		return this;
	}

}
