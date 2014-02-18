package com.lumata.expression.operators.testing.offeroptimizer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
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
import com.lumata.expression.operators.exceptions.OfferException;
import com.lumata.expression.operators.exceptions.RuleException;
import com.lumata.expression.operators.exceptions.TokenTypeException;
import com.lumata.expression.operators.gui.campaigns.CampaignModelForm;
import com.lumata.expression.operators.gui.catalogue.OffersForm;
import com.lumata.expression.operators.gui.catalogue.RuleForm;
import com.lumata.expression.operators.gui.catalogue.TokenTypeForm;
import com.lumata.expression.operators.gui.common.AngularFrame;
import com.lumata.expression.operators.gui.security.Authorization;
import com.lumata.expression.operators.json.campaigns.CampaignModelCfg;
import com.lumata.expression.operators.json.catalogue.OfferCfg;
import com.lumata.expression.operators.json.catalogue.RuleCfg;
import com.lumata.expression.operators.json.catalogue.TokenTypeCfg;
import com.lumata.expression.operators.testing.generators.GenerateSubscribers;

public class AllocateAcceptTest {

	private static final Logger logger = LoggerFactory.getLogger(AllocateAcceptTest.class);

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

	@AfterSuite
	public void close() {
		if (null != mysql) {
			mysql.close();
		}
	}

	private void configureTokenType() throws TokenTypeException {
		ArrayList<TokenTypeCfg> tokenTypeList = TokenTypeCfg.createTokenTypeList("./input/catalogue/token_type", "token_type_list_all", IOLoadingType.RESOURCE);
		Assert.assertTrue(TokenTypeForm.open(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT));
		for (TokenTypeCfg tokenTypeCfg : tokenTypeList) {
			Assert.assertTrue(TokenTypeForm.addTokenType(seleniumWebDriver, tokenTypeCfg, TIMEOUT, ATTEMPT_TIMEOUT));
		}
	}

	private void configureRuleSet() throws RuleException {
		List<RuleCfg> ruleList = RuleCfg.createRuleList("./input/catalogue/rules", "rule_offer_optimizer", IOLoadingType.RESOURCE);
		Assert.assertTrue(RuleForm.open(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT));
		for (RuleCfg ruleCfg : ruleList) {
			Assert.assertTrue(RuleForm.addRule(seleniumWebDriver, ruleCfg, TIMEOUT, ATTEMPT_TIMEOUT));
		}
	}

	private void configureChannels() {
		SalesChannelsList salesChannelsList = new SalesChannelsList(env, tenant, null, "input/sales_channels", "offer_sales_channels.json", IOFileUtils.IOLoadingType.RESOURCE);
	}

	private void configureOffers() throws OfferException {
		for (int i = 0; i < 3; i++) {
			OfferCfg offerCfg1 = new OfferCfg("input/catalogue/offers", "offer_1", IOLoadingType.RESOURCE);
			Assert.assertTrue(OffersForm.open(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT));
			Assert.assertTrue(OffersForm.create(seleniumWebDriver, offerCfg1, TIMEOUT, ATTEMPT_TIMEOUT));

		}
		for (int i = 0; i < 3; i++) {
			OfferCfg offerCfg2 = new OfferCfg("input/catalogue/offers", "offer_2", IOLoadingType.RESOURCE);
			Assert.assertTrue(OffersForm.open(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT));
			Assert.assertTrue(OffersForm.create(seleniumWebDriver, offerCfg2, TIMEOUT, ATTEMPT_TIMEOUT));
		}
	}

	private String createSubscriber(int attemptNumber) throws Exception {
		// Number of subscribers to generate

		// Max MSISDN length
		int MSISDN_MAX_LENGTH = 10;

		// MSISDN PREFIX
		final String SUBSCRIBER_PREFIX = "33999";

		logger.info(Log.PUTTING.createMessage("generateSubscribers", "Insert Subscribers"));

		final int SUBSCRIBERS_PREFIX_DIGITS = (int) (Math.log10(Integer.valueOf(SUBSCRIBER_PREFIX)) + 1);
		final int SUBSCRIBERS_TO_GENERATE_DIGITS = (int) (Math.log10(attemptNumber) + 1);

		final int MSISDN_LENGTH = SUBSCRIBERS_PREFIX_DIGITS + SUBSCRIBERS_TO_GENERATE_DIGITS;

		if (MSISDN_MAX_LENGTH < MSISDN_LENGTH) {
			MSISDN_MAX_LENGTH = MSISDN_LENGTH;
		}

		StringBuilder query = new StringBuilder();

		for (int i = 1; i <= attemptNumber; i++) {

			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
			String subscription_date = DATE_FORMAT.format(new Date());

			String format = "%0" + (MSISDN_MAX_LENGTH - SUBSCRIBERS_PREFIX_DIGITS) + "d";

			String msisdn = SUBSCRIBER_PREFIX + String.format(format, i);

			String existsSql = "select * from subscribers where msisdn='" + msisdn + "';";
			ResultSet rs = mysql.execQuery(existsSql);
			boolean found = false;
			try {
				while (rs.next()) {
					found = true;
					break;
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);

			}
			if (!found) {
				query = GenerateSubscribers.getInsertSubscriberQuery(msisdn, subscription_date);
				mysql.execUpdate(query.toString());
				//System.out.println( query );

				query = GenerateSubscribers.getInsertSubsNotifQuery(msisdn, 1);
				mysql.execUpdate(query.toString());
				return msisdn;
			}
		}
		throw new Exception("Not able to find a clean subscriber");
	}
	
	private void createTokens(String msisdn){
		
	}

	private void setUpConfiguration() throws Exception {
		configureChannels();
		configureTokenType();
		AngularFrame.close(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);
		configureRuleSet();
		AngularFrame.close(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);
		configureOffers();
	}
	
	private void configureCampaignModel(){

		CampaignModelCfg cm_bonus = new CampaignModelCfg( "input/campaigns", "cm_bonus", IOLoadingType.RESOURCE );
				
		Assert.assertTrue( CampaignModelForm.open(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT) );
		Assert.assertTrue( CampaignModelForm.create(seleniumWebDriver, cm_bonus, TIMEOUT, ATTEMPT_TIMEOUT) );	
	}
	
	@Test
	public void testAllocate() throws Exception {
		setUpConfiguration();
//		String msisdn = createSubscriber(10);
	}
}
