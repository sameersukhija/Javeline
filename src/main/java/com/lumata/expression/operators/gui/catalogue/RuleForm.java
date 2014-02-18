package com.lumata.expression.operators.gui.catalogue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.expression.operators.gui.common.AngularFrame;
import com.lumata.expression.operators.json.catalogue.RuleCfg;

public class RuleForm {

	private static final Logger logger = LoggerFactory.getLogger(RuleForm.class);

	public enum RuleErrorAction {

		RULE_ALREADY_EXISTS;

	};

	public enum RuleActionType {

		RETURN_ERROR, ABORT, ADD_TIMESTAMP_TO_RULE_NAME;

	};

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

						WebElement tokenTypeCancel = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, "html/body/div[1]/div[2]/div/div/div[2]/a[1]", timeout,
								interval);
						if (tokenTypeCancel == null) {
							logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Abort token type creation"));
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

}
