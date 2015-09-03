package com.lumata.e4o.regressions.gui;

import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.dao.tenant.DAORuleSet;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.catalogmanager.RulesForm;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm;
import com.lumata.e4o.json.gui.catalogmanager.JSONRules;
import com.lumata.e4o.json.gui.catalogmanager.JSONTokenType;
import com.lumata.e4o.schema.tenant.OffoptimRuleset;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCMysqlMaster;
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
@TCMysqlMaster
public class TestTokenRuleForm extends ParentTestCase {


	private RulesForm rulesForm;
	private TokenTypeForm tokenTypeForm;
	private JSONTokenType jsonTokenType;
	private JSONRules jsonRules;
	final String TOKEN_TYPE_NAME = Format.addTimestamp("TType_");
	private String RULE_NAME;

	@Test(enabled = TEST_ENABLED, priority = 1)
	@Parameters({ "jsonFilePath_token", "jsonFileName_token",
			"jsonFilePath_rule", "jsonFileName_rule"})
	public void testUc24_01CreateNewRule(@Optional String jsonFilePath_token,
			@Optional String jsonFileName_token,
			@Optional String jsonFilePath_rule,
			@Optional String jsonFileName_rule
			) throws FormException,
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

		RULE_NAME = "Rule_1";

		JSONArray ruleTypes = jsonRules.getList();
		for (int ruleTypeIndex = 0; ruleTypeIndex < ruleTypes.length(); ruleTypeIndex++) {
			jsonRules.setRuleById(ruleTypeIndex);

			rulesForm.openForm();
			rulesForm.addBtn();
			rulesForm.setName(RULE_NAME);
			rulesForm.setDescription(RULE_NAME + " Description");
			// Use token which created in this class
			rulesForm.setTokenType(TOKEN_TYPE_NAME);
			rulesForm.setChannels(jsonRules.getRuleChannelsAsArray());
			rulesForm.configureRuleChannels();
			rulesForm
					.setAlgorithm(jsonRules.getOptimizationAlgorithm());
			if(jsonRules.getKeepOffersConsistentAcrossMultipleRedraws()==true)
			{
				rulesForm.setKeepOfferConsistentYes();
			}
			else{
				rulesForm.setKeepOfferConsistentNo();
			}
			if(jsonRules.getIncludePreviouslyAcceptedOffers()==true)
			{
				rulesForm.setPrevioslyAcceptedOfferYes();
			}
			else{
				rulesForm.setPrevioslyAcceptedOfferNo();
			}
			rulesForm
					.setMaxNumberOfOffers(jsonRules.getMaximumNumberOfOffers());
			rulesForm
					.setExpiredOfferBehaviour(jsonRules.getExpiredOfferBehaviour());
			Assert.assertTrue(rulesForm.formIsValid());
			rulesForm.saveBtn();
			if (rulesForm.isRuleNameInList(RULE_NAME)) {
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
	
	@Test(enabled = TEST_ENABLED, priority = 2)
	public void testUc24_02EditRule() throws FormException{
		seleniumWebDriver.getWrappedDriver().manage().timeouts()
		.implicitlyWait(30, TimeUnit.SECONDS);
		Reporter.log("Edit an existing offer",
		LOG_TO_STD_OUT);
		rulesForm = new RulesForm(seleniumWebDriver, TIMEOUT,
				ATTEMPT_TIMEOUT);
		rulesForm.openForm();
		rulesForm.editRuleByName(RULE_NAME);
		rulesForm.setDescription("edited description");
		rulesForm.setAlgorithm("Best Offer");
		rulesForm.setKeepOfferConsistentNo();
		rulesForm.setPrevioslyAcceptedOfferNo();
		rulesForm.setExpiredOfferBehaviour("Bypass offer validity date");
		rulesForm.editRule();
		
		rulesForm.editRuleByName(RULE_NAME);
		OffoptimRuleset ruleSet = DAORuleSet.getInstance(mysqlMaster)
				.getRuleSetByName(RULE_NAME);
		Assert.assertEquals(rulesForm.getName(),
				ruleSet.getName());
		Assert.assertEquals(rulesForm.getDescription(),
				ruleSet.getDescription());
		Assert.assertTrue(2 == ruleSet.getAlgorithmId());
		Assert.assertTrue(0 == ruleSet.getKeepOffersConsistent());
		Assert.assertTrue(0 == ruleSet.getAllowRedeemedOffers());
		Assert.assertTrue(ruleSet.getRedeemExpiredOfferBehavior().equals("bypass_offer_validity_date"));
		
		rulesForm.cancelBtn();
		rulesForm.goToHome();
	}
	@Test(enabled = TEST_ENABLED, priority = 3)
	public void testUc24_03CopyRule() throws FormException{
		seleniumWebDriver.getWrappedDriver().manage().timeouts()
		.implicitlyWait(30, TimeUnit.SECONDS);
		Reporter.log("Copy an existing offer",
		LOG_TO_STD_OUT);
		rulesForm = new RulesForm(seleniumWebDriver, TIMEOUT,
				ATTEMPT_TIMEOUT);
		rulesForm.openForm();
		rulesForm.copyRuleByName(RULE_NAME);
		String copied_rule_name=Format.addTimestamp("Rule_");
		rulesForm.typeByName("name", copied_rule_name);
		rulesForm.setDescription("copied rule");
		rulesForm.saveBtn();
		
		rulesForm.editRuleByName(copied_rule_name);
		OffoptimRuleset ruleSet = DAORuleSet.getInstance(mysqlMaster)
				.getRuleSetByName(copied_rule_name);
		Assert.assertEquals(rulesForm.getName(),ruleSet.getName());
		Assert.assertEquals(rulesForm.getDescription(),
				ruleSet.getDescription());
		Assert.assertTrue(2 == ruleSet.getAlgorithmId());
		Assert.assertTrue(0 == ruleSet.getKeepOffersConsistent());
		Assert.assertTrue(0 == ruleSet.getAllowRedeemedOffers());
		Assert.assertTrue(ruleSet.getRedeemExpiredOfferBehavior().equals("bypass_offer_validity_date"));
		
		rulesForm.cancelBtn();
		rulesForm.goToHome();
	}
}
