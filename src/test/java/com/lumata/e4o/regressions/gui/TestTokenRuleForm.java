package com.lumata.e4o.regressions.gui;

import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.catalogmanager.RuleForm;
import com.lumata.e4o.gui.catalogmanager.RulesForm;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm;
import com.lumata.e4o.gui.common.ParentUITestCase;
import com.lumata.e4o.json.gui.catalogmanager.JSONRules;
import com.lumata.e4o.json.gui.catalogmanager.JSONTokenType;

/**
 * 
 * @author Isha Vyas 
 * Integration of Rule And Token Form Test
 *
 */
public class TestTokenRuleForm extends ParentUITestCase {
	private static final Logger logger = LoggerFactory
			.getLogger(TestTokenTypeForm.class);

	private int TIMEOUT = 60000;
	private int ATTEMPT_TIMEOUT = 200;

	private final boolean testEnabled = true;
	private RulesForm rulesForm;
	private TokenTypeForm tokenTypeForm;
	private JSONTokenType jsonTokenType;
	private JSONRules jsonRules;

	@Test(enabled = testEnabled, priority = 1)
	@Parameters({ "jsonFilePath", "jsonFileName", "jsonFilePath1",
			"jsonFileName1" })
	
	
	public void TokenRuleIntegration(@Optional String jsonFilePath,
			@Optional String jsonFileName, @Optional String jsonFilePath1,
			@Optional String jsonFileName1) throws FormException,
			JSONSException {

		Boolean status = false;
		Reporter.log("Creation of \"Token Types Form\".", PRINT2STDOUT__);
		String resourcePath = currentResourceStartPath + jsonFilePath;
		String resourceFile = jsonFileName;
		jsonTokenType = new JSONTokenType(resourcePath, resourceFile);
		Reporter.log("\"Token Types\" is filled with reosurce file : ",
				PRINT2STDOUT__);
		Reporter.log("Resource path -> " + resourcePath, PRINT2STDOUT__);
		Reporter.log("Resource file -> " + resourceFile, PRINT2STDOUT__);

		Reporter.log("Creation of \"Rule Types Form\".", PRINT2STDOUT__);
		String resourcePath1 = currentResourceStartPath + jsonFilePath1;
		String resourceFile1 = jsonFileName1;
		jsonRules = new JSONRules(resourcePath1, resourceFile1);
		Reporter.log("\"Rule Types\" is filled with reosurce file : ",
				PRINT2STDOUT__);
		Reporter.log("Resource path -> " + resourcePath1, PRINT2STDOUT__);
		Reporter.log("Resource file -> " + resourceFile1, PRINT2STDOUT__);
		
		tokenTypeForm = new TokenTypeForm(seleniumWebDriver, jsonTokenType,
				TIMEOUT, ATTEMPT_TIMEOUT);
		rulesForm = new RulesForm(seleniumWebDriver, jsonRules, TIMEOUT,
				ATTEMPT_TIMEOUT);
		
		//Create Token 
		final String TOKEN_TYPE_NAME = Format.addTimestamp("TType_");
		JSONArray tokenTypes = jsonTokenType.getList();
		for (int tokenTypeIndex = 0; tokenTypeIndex < tokenTypes.length(); tokenTypeIndex++) {

			jsonTokenType.setTokenTypeById(tokenTypeIndex);
			tokenTypeForm.openForm();
			seleniumWebDriver.getWrappedDriver().manage().timeouts()
					.implicitlyWait(30, TimeUnit.SECONDS);
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
			tokenTypeForm.goToHome();
		}
		
		//Create Rule using Token Type created by Token Form
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
			rulesForm.setChannel(jsonRules.getChannelName());
			rulesForm.checkMandatoryBox();
			rulesForm
					.setMaxNumberOfOffers(jsonRules.getMaximumNumberOfOffers());
			rulesForm
					.setAlgorithm(RuleForm.optimizationAlgorithm.RandomAssigment
							.value());
			rulesForm.clickKeepOfferConsistentNo();
			rulesForm.clickPrevioslyAcceptedOfferNo();
			rulesForm
					.setMaxNumberOfOffers(jsonRules.getMaximumNumberOfOffers());
			rulesForm
					.setExpiredOfferBehaviour(RuleForm.expiredOfferBehaviour.Pickupnewoffer
							.value());
			Assert.assertTrue(rulesForm.formIsValid());
			rulesForm.saveRule();
			rulesForm.goToHome();
		}

	}
}
