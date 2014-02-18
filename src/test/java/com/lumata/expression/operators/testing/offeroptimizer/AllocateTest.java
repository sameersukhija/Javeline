package com.lumata.expression.operators.testing.offeroptimizer;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.io.IOFileUtils.IOLoadingType;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.dao.administration.SalesChannelsList;
import com.lumata.expression.operators.exceptions.RuleException;
import com.lumata.expression.operators.exceptions.TokenTypeException;
import com.lumata.expression.operators.gui.catalogue.OffersForm;
import com.lumata.expression.operators.gui.catalogue.RuleForm;
import com.lumata.expression.operators.gui.catalogue.TokenTypeForm;
import com.lumata.expression.operators.gui.common.AngularFrame;
import com.lumata.expression.operators.gui.security.Authorization;
import com.lumata.expression.operators.json.catalogue.RuleCfg;
import com.lumata.expression.operators.json.catalogue.TokenTypeCfg;

public class AllocateTest {

	private static final Logger logger = LoggerFactory.getLogger(AllocateTest.class);

	Environment env;
	Mysql mysql;
	SeleniumWebDriver seleniumWebDriver;
	private int TIMEOUT = 600000;
	private int ATTEMPT_TIMEOUT = 100;

	private String tenant;

	/* 	Initialize Environment */
	@Parameters({ "browser", "environment", "tenant", "user" })
	@BeforeSuite
	public void init(@Optional("FIREFOX") String browser, @Optional("E4O_VM") String environment, @Optional("tenant") String tenant, @Optional("superman") String user)
			throws EnvironmentException {

		logger.info(Log.LOADING.createMessage("init", "environment"));

		env = new Environment("input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE);
		this.tenant = tenant;
		mysql = new Mysql(env.getDataSource(tenant));
		seleniumWebDriver = new SeleniumWebDriver(browser, env.getBrowser(browser), env.getLink());
		seleniumWebDriver.windowMaximize();

		Assert.assertTrue(Authorization.login(seleniumWebDriver, env.getUserName(user), env.getPassword(user), 60000, 500));

		System.out.println("TENANT: " + env.getDataSource(tenant).toString());
	}

	private void setUpConfiguration() throws TokenTypeException, RuleException {
//		configureChannels();
//		configureTokenType();
//		AngularFrame.close(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);
		configureRuleSet();
	}

	private void configureTokenType() throws TokenTypeException {
		ArrayList<TokenTypeCfg> tokenTypeList = TokenTypeCfg.createTokenTypeList("./input/catalogue/token_type", "token_type_list_all", IOLoadingType.RESOURCE);
		Assert.assertTrue( TokenTypeForm.open(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT) );
		for (TokenTypeCfg tokenTypeCfg : tokenTypeList) {
			Assert.assertTrue( TokenTypeForm.addTokenType(seleniumWebDriver, tokenTypeCfg, TIMEOUT, ATTEMPT_TIMEOUT) );
		}
	}
	
	private void configureRuleSet() throws RuleException{
		List<RuleCfg> ruleList = RuleCfg.createRuleList("./input/catalogue/rules", "rule_offer_optimizer", IOLoadingType.RESOURCE);
		Assert.assertTrue( RuleForm.open(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT) );
		for (RuleCfg ruleCfg : ruleList) {
			Assert.assertTrue( RuleForm.addRule(seleniumWebDriver, ruleCfg, TIMEOUT, ATTEMPT_TIMEOUT) );
		}
	}

	private void configureChannels(){
		SalesChannelsList salesChannelsList = new SalesChannelsList( env, tenant, null, "input/sales_channels", "offer_sales_channels.json", IOFileUtils.IOLoadingType.RESOURCE );
	}
	
	@Test
	public void testAllocate() throws TokenTypeException,RuleException {
		try {
			setUpConfiguration();
		} catch (TokenTypeException e) {
			logger.error("Error loading token type",e);
		}
	}
}
