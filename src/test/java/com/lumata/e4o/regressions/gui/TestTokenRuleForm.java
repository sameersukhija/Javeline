package com.lumata.e4o.regressions.gui;

import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.catalogmanager.RulesForm;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm;
import com.lumata.e4o.json.gui.catalogmanager.JSONRules;
import com.lumata.e4o.json.gui.catalogmanager.JSONTokenType;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;
import com.lumata.e4o.testing.common.TCSeleniumWebDriver;

/**
 * 
 * @author Isha Vyas Integration of Rule And Token Form Test
 *
 */
@TCOwners(@TCOwner(name = "Isha Vyas", email = "isha.vyas@lumatagroup.com"))
@TCSeleniumWebDriver
public class TestTokenRuleForm extends ParentTestCase {
	private static final Logger logger = LoggerFactory
			.getLogger(TestTokenRuleForm.class);

	private RulesForm rulesForm;
	private TokenTypeForm tokenTypeForm;
	private JSONTokenType jsonTokenType;
	private JSONRules jsonRules;

	@Test(enabled = TEST_ENABLED, priority = 1)
	@Parameters({ "jsonFilePath_token", "jsonFileName_token",
			"jsonFilePath_rule", "jsonFileName_rule" })
	public void TokenRuleIntegration(@Optional String jsonFilePath_token,
			@Optional String jsonFileName_token,
			@Optional String jsonFilePath_rule,
			@Optional String jsonFileName_rule) throws FormException,
			JSONSException {
		seleniumWebDriver.getWrappedDriver().manage().timeouts()
				.implicitlyWait(30, TimeUnit.SECONDS);
		Reporter.log("Creation of \"Rule form integrated with Token Form\".",
				LOG_TO_STD_OUT);
		String resourcePath = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath_token;
		String resourceFile = jsonFileName_token;
		jsonTokenType = new JSONTokenType(resourcePath, resourceFile);
		Reporter.log("\"Token Types\" is filled with reosurce file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + resourcePath, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + resourceFile, LOG_TO_STD_OUT);

		Reporter.log("Creation of \"Rule Types Form\".", LOG_TO_STD_OUT);
		String resourcePath1 = DEFAULT_RESOURCE_FOLDER_ROOT + jsonFilePath_rule;
		String resourceFile1 = jsonFileName_rule;
		jsonRules = new JSONRules(resourcePath1, resourceFile1);
		Reporter.log("\"Rule Types\" is filled with reosurce file : ",
				LOG_TO_STD_OUT);
		Reporter.log("Resource path -> " + resourcePath1, LOG_TO_STD_OUT);
		Reporter.log("Resource file -> " + resourceFile1, LOG_TO_STD_OUT);

		tokenTypeForm = new TokenTypeForm(seleniumWebDriver, jsonTokenType,
				TIMEOUT, ATTEMPT_TIMEOUT);
		rulesForm = new RulesForm(seleniumWebDriver, jsonRules, TIMEOUT,
				ATTEMPT_TIMEOUT);

		// Create Token
		final String TOKEN_TYPE_NAME = Format.addTimestamp("TType_");
		JSONArray tokenTypes = jsonTokenType.getList();
		for (int tokenTypeIndex = 0; tokenTypeIndex < tokenTypes.length(); tokenTypeIndex++) {

			jsonTokenType.setTokenTypeById(tokenTypeIndex);
			tokenTypeForm.openForm();
			tokenTypeForm.waitVisibleElement(By.linkText("Add"));
			tokenTypeForm.addBtn();
			tokenTypeForm
					.setName(TOKEN_TYPE_NAME)
					.setDescription(TOKEN_TYPE_NAME + "description")
					.setImgUrl(jsonTokenType.getImageUrl())
					.setFormat(TokenTypeForm.TokenFormat.br.value())
					.setValidityType(
							TokenTypeForm.TokenValidityType.Relative.value())
					.setValidityValue(jsonTokenType.getValidityValue())
					.setValidityUnit(
							TokenTypeForm.TokenValidityUnit.days.value())
					.setUnlimitedRedraw(jsonTokenType.getUsageUnlimited()).
					// setNumberOfRedraw(jsonTokenType.getUsage()).
					saveBtn();
			if (tokenTypeForm.isTokenTypeInList(TOKEN_TYPE_NAME)) {
				Assert.assertTrue(true,
						"Token created successfully and exist in list");
				Reporter.log("Token created successfully and exist in list",
						LOG_TO_STD_OUT);
			} else {
				Assert.fail("Token doesn't create successfully");
				Reporter.log("Token doesn't exist in list", LOG_TO_STD_OUT);
			}
			tokenTypeForm.goToHome();

		}

		// Create Rule using Token Type created by Token Form
		final String RULE_TYPE_NAME = Format.addTimestamp("Rule_");
		JSONArray ruleTypes = jsonRules.getList();
		for (int ruleTypeIndex = 0; ruleTypeIndex < ruleTypes.length(); ruleTypeIndex++) {
			jsonRules.setRuleById(ruleTypeIndex);

			rulesForm.openForm();
			rulesForm.clickAddBtn();
			rulesForm.setName(RULE_TYPE_NAME);
			rulesForm.setDescription(RULE_TYPE_NAME + " Description");
			// Use token which created in this class
			rulesForm.setTokenType(TOKEN_TYPE_NAME);
			rulesForm.setChannel(jsonRules.getRuleChannelsAsArray());
			rulesForm.configureRuleChannels();
			rulesForm
					.setAlgorithm(RulesForm.optimizationAlgorithm.RandomAssigment
							.value());
			rulesForm.clickKeepOfferConsistentNo();
			rulesForm.clickPrevioslyAcceptedOfferNo();
			rulesForm
					.setMaxNumberOfOffers(jsonRules.getMaximumNumberOfOffers());
			rulesForm
					.setExpiredOfferBehaviour(RulesForm.expiredOfferBehaviour.Pickupnewoffer
							.value());
			Assert.assertTrue(rulesForm.formIsValid());
			rulesForm.saveRule();
			if (rulesForm.isRuleNameInList(RULE_TYPE_NAME)) {
				Assert.assertTrue(true, "Rule created successfully");
				Reporter.log("Rule created successfully and exist in list",
						LOG_TO_STD_OUT);
			} else {
				Assert.fail("Rule didn't create successfully");
				Reporter.log("Rule failed to create", LOG_TO_STD_OUT);
			}

			rulesForm.goToHome();
		}

	}
}
