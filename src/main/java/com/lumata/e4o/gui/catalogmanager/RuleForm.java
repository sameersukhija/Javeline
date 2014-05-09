package com.lumata.e4o.gui.catalogmanager;

import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.selenium.SeleniumUtils.SearchBy;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.Form;
import com.lumata.e4o.json.gui.catalogmanager.JSONRule;

public class RuleForm extends OfferOptimisationForm {

	private static final Logger logger = LoggerFactory.getLogger(RuleForm.class);

	private JSONRule ruleCfg;
	
	public enum RuleErrorAction {

		RULE_ALREADY_EXISTS;

	};

	public enum RuleActionType {

		RETURN_ERROR, ABORT, ADD_TIMESTAMP_TO_RULE_NAME;

	};

	public RuleForm( SeleniumWebDriver selenium, JSONRule ruleCfg, long timeout, long interval ) {
		
		super(selenium, timeout, interval);
		
		this.ruleCfg = ruleCfg;
		
	}	
	
	public RuleForm open() throws FormException {
		
		super.open( OfferOptimisationSection.RULES );
		
		return this;
		
	}
	
	public RuleForm addRules() throws FormException, JSONException {
		
		JSONArray rules = ruleCfg.getList();
		
		for( int ruleIndex = 0; ruleIndex < rules.length(); ruleIndex++ ) {
			
			ruleCfg.setRuleById( ruleIndex );
			
			if( ruleCfg.getEnabled() ) {
			
				clickLink( "Add" ).
				configureRule().
				saveRule();
				
			}
					
		}
		
		return this;
		
	}
	
	public RuleForm configureRule() throws FormException, JSONException {
				
		sendKeysByName( "name", ruleCfg.getName() ).
		sendKeysByXPath( "//textarea[@ng-model='ruleset.description']", ruleCfg.getDescription() ).
		selectByName( "tokenType", ruleCfg.getTokenType() ).
		multiselectByXPathAndVisibleText( "//select[@multiple]", ruleCfg.getChannels() ).
		selectByName( "algorithm", ruleCfg.getOptimizationAlgorithm() );

		if( ruleCfg.getKeepOffersConsistentAcrossMultipleRedraws() ) { clickId( "keepOffersConsistent-1" ); }
		else { clickId( "keepOffersConsistent-0" ); }
		
		if( ruleCfg.getIncludePreviouslyAcceptedOffers() ) { clickId( "previousOffersDrawnIncluded-1" ); }
		else { clickId( "previousOffersDrawnIncluded-0" ); }
		
		if( ruleCfg.getAllowDuplicateOffers() ) { clickId( "duplicatedOfferWithinSingleDrawEnabled-1" ); }
		else { clickId( "duplicatedOfferWithinSingleDrawEnabled-0" ); }

		if( ruleCfg.getUnlimitedOffers() ) { clickId( "numOfOffersToDrawUnlimited-1" ); }
		else { 
			
			clickId( "numOfOffersToDrawUnlimited-0" ). 
			typeByName( "usage", ruleCfg.getMaximumNumberOfOffers() );
		
		}
		
		return this;
		
	}
	
	public Form saveRule() throws FormException {
		
		clickName( "btn-add" );
		
		return this;
		
	}
	
	@Override
	public RuleForm clickName( String name ) throws FormException {
		
		super.clickName( name );
		
		return this;
		
	}
	
	@Override
	public RuleForm clickLink( String link ) throws FormException {
		
		super.clickLink( link );
		
		return this;
		
	}
	
	@Override
	public RuleForm sendKeysByName( String name, String text ) throws FormException {
		
		super.sendKeysByName( name, text );
		
		return this;
	
	}
	
	@Override
	public RuleForm sendKeysByXPath( String xpath, String text ) throws FormException {
		
		super.sendKeysByXPath( xpath, text );
		
		return this;
	
	}
	
	@Override
	public RuleForm sendKeysByLink( String link, String text ) throws FormException {
		
		super.sendKeysByLink( link, text );
		
		return this;
	
	}
	
	@Override
	public RuleForm selectByName( String name, String label ) throws FormException {
		
		super.selectByName( name, label );
		
		return this;
		
	}
	
	@Override
	public RuleForm clearByName( String xpath ) throws FormException {
		
		super.clearByName( xpath );
		
		return this;
		
	}
	
	@Override
	public RuleForm typeByName( String name, String text ) throws FormException {
		
		super.typeByName( name, text );
		
		return this;
	
	}
	
	@Override
	public RuleForm multiselectByXPathAndVisibleText( String xpath, JSONArray list ) throws FormException {
		
		super.multiselectByVisibleText( SearchBy.XPATH, xpath, list );	
		
		return this;
		
	}
	
	
	
	/*
	public static boolean open(SeleniumWebDriver selenium, long timeout, long interval) {

		return OfferOptimisationForm.open(selenium, OfferOptimisationForm.OfferOptimisationSection.RULES, timeout, interval);

	}

	public static boolean addRule(SeleniumWebDriver selenium, RuleCfg rule, long timeout, long interval) {

		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for link=Add"));
		WebElement addRuleBtn = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.LINK, "Add", timeout, interval);
		if (addRuleBtn == null) {
			return false;
		}
		addRuleBtn.click();

		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for name = name"));
		WebElement ruleName = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.NAME, "name", timeout, interval);
		if (ruleName == null) {
			return false;
		}
		ruleName.sendKeys(rule.getName());

		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for name = description"));
		WebElement ruleDesc = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, "//textarea[@name='description']", timeout, interval);
		if (ruleDesc == null) {
			return false;
		}
		ruleDesc.sendKeys(rule.getDescription());	

		WebElement ruleTokenTypeName = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.NAME, "tokenType", timeout, interval);
		if (ruleTokenTypeName == null) {
			return false;
		}
		selenium.select("name=tokenType", "label=" + rule.getTokenTypeName());

		WebElement ruleChannleList = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, "//select[@multiple]", timeout, interval);
		if (ruleChannleList == null) {
			return false;
		}
		try {
			JSONArray channleList = rule.getChannelNameList();
			Select available = new Select(ruleChannleList);
			for (int i = 0; i < channleList.length(); i++) {
				String channel = channleList.getString(i);
				available.selectByVisibleText(channel);
			}
		} catch (JSONException e) {
			logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new rule"), e);
		}

		WebElement ruleAlgorithmName = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.NAME, "algorithm", timeout, interval);
		if (ruleAlgorithmName == null) {
			return false;
		}
		selenium.select("name=algorithm", "label=" + rule.getOfferSelectionAlgorithmId());

		if (rule.getKeepOffersConsistent()) {
			logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id = keepOffersConsistent-1"));
			WebElement keepOffersConsistent1 = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "keepOffersConsistent-1", timeout, interval);
			if (keepOffersConsistent1 == null) {
				return false;
			}
			keepOffersConsistent1.click();
		} else {
			logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id = keepOffersConsistent-0"));
			WebElement keepOffersConsistent0 = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "keepOffersConsistent-0", timeout, interval);
			if (keepOffersConsistent0 == null) {
				return false;
			}
			keepOffersConsistent0.click();

		}

		if (rule.getPreviousOffersDrawnIncluded()) {
			logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id = previousOffersDrawnIncluded-1"));
			WebElement previousOffersDrawnIncluded1 = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "previousOffersDrawnIncluded-1", timeout, interval);
			if (previousOffersDrawnIncluded1 == null) {
				return false;
			}
			previousOffersDrawnIncluded1.click();
		} else {
			logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id = previousOffersDrawnIncluded-0"));
			WebElement previousOffersDrawnIncluded0 = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "previousOffersDrawnIncluded-0", timeout, interval);
			if (previousOffersDrawnIncluded0 == null) {
				return false;
			}
			previousOffersDrawnIncluded0.click();
		}

		if (rule.getDuplicatedOfferWithinSingleDrawEnabled()) {
			logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id = duplicatedOfferWithinSingleDrawEnabled-1"));
			WebElement duplicatedOfferWithinSingleDrawEnabled1 = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "duplicatedOfferWithinSingleDrawEnabled-1",
					timeout, interval);
			if (duplicatedOfferWithinSingleDrawEnabled1 == null) {
				return false;
			}
			duplicatedOfferWithinSingleDrawEnabled1.click();
		} else {
			logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id = duplicatedOfferWithinSingleDrawEnabled-0"));
			WebElement duplicatedOfferWithinSingleDrawEnabled0 = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "duplicatedOfferWithinSingleDrawEnabled-0",
					timeout, interval);
			if (duplicatedOfferWithinSingleDrawEnabled0 == null) {
				return false;
			}
			duplicatedOfferWithinSingleDrawEnabled0.click();
		}

		if (rule.getNumOfOffersToDrawUnlimited()) {
			logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id = numOfOffersToDrawUnlimited-1"));
			WebElement numOfOffersToDrawUnlimited1 = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "numOfOffersToDrawUnlimited-1", timeout, interval);
			if (numOfOffersToDrawUnlimited1 == null) {
				return false;
			}
			numOfOffersToDrawUnlimited1.click();
		} else {
			logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id = numOfOffersToDrawUnlimited-0"));
			WebElement numOfOffersToDrawUnlimited0 = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "numOfOffersToDrawUnlimited-0", timeout, interval);
			if (numOfOffersToDrawUnlimited0 == null) {
				return false;
			}
			numOfOffersToDrawUnlimited0.click();
		}
		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for name = btn-add"));
		WebElement ruleSave = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.NAME, "btn-add", timeout, interval);
		if (ruleSave == null) {
			return false;
		}
		ruleSave.click();
		return manageErrorAction(selenium, rule, timeout, interval);

	}

	public static boolean manageErrorAction(SeleniumWebDriver selenium, RuleCfg rule, long timeout, long interval) {

		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for error message"));

		AngularFrame.close(selenium, timeout, interval);

		WebElement btnMessageError = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, "//div[@class='gwt-DialogBox errorDialog']//button", 2000, 100);

		if (btnMessageError != null) {

			btnMessageError.click();

			if (!AngularFrame.open(selenium, timeout, interval)) {
				return false;
			}

			JSONObject error_actions = rule.getErrorActions();

			if (error_actions == null) {

				logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new rule( Wrong json configuration )"));

				return false;

			} else {

				try {

					switch (RuleActionType.valueOf(error_actions.getString(RuleErrorAction.RULE_ALREADY_EXISTS.name()))) {

					case RETURN_ERROR: {

						logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new rule ( rule name already exist )"));

						return false;

					}
					case ADD_TIMESTAMP_TO_RULE_NAME: {

						//TODO							

						return true;

					}
					case ABORT: {

						logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for name=btn-cancel"));

						//WebElement tokenTypeCancel = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, "//div[@class='e4ol-actions']/a[@class='gwt-Button'][1]", timeout,interval);
						WebElement tokenTypeCancel = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, "html/body/div[1]/div[2]/div/div/div/div/div[2]/a[1]", timeout,interval);						
						if (tokenTypeCancel == null) {
							logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Abort rule creation"));
							return false;
						}
						tokenTypeCancel.click();

						return true;

					}

					}

				} catch (Exception e) {
					logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new rule"), e);
				}

			}

		}

		return AngularFrame.open(selenium, timeout, interval);

	}
*/
}
