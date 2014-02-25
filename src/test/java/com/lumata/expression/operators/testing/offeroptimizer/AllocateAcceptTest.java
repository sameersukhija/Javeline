package com.lumata.expression.operators.testing.offeroptimizer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jboss.resteasy.client.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.internal.Lists;
import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.io.IOFileUtils.IOLoadingType;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.dao.administration.SalesChannelsList;
import com.lumata.expression.operators.exceptions.CampaignModelException;
import com.lumata.expression.operators.exceptions.OfferException;
import com.lumata.expression.operators.exceptions.RuleException;
import com.lumata.expression.operators.exceptions.TokenTypeException;
import com.lumata.expression.operators.gui.campaigns.CampaignCreationForm;
import com.lumata.expression.operators.gui.campaigns.CampaignModelForm;
import com.lumata.expression.operators.gui.catalogue.OffersForm;
import com.lumata.expression.operators.gui.catalogue.RuleForm;
import com.lumata.expression.operators.gui.catalogue.TokenTypeForm;
import com.lumata.expression.operators.gui.security.Authorization;
import com.lumata.expression.operators.gui.xmlrpc.HTTPXMLRPCForm;
import com.lumata.expression.operators.gui.xmlrpc.XMLRPCResultParser;
import com.lumata.expression.operators.gui.xmlrpc.XMLRPCResultSuccess;
import com.lumata.expression.operators.gui.xmlrpc.XMLRPCResultParser.ResultType;
import com.lumata.expression.operators.json.campaigns.CampaignCfg;
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

	@BeforeClass
	public void onStartup() {

	}

	@AfterClass
	public void onClose() {
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
				logger.info("Create subscriber " + msisdn);
				return msisdn;
			}
		}
		throw new Exception("Not able to find a clean subscriber");
	}

	private void configureCampaignModel() throws CampaignModelException {
		logger.info("configuring campaing model");
		CampaignModelCfg cm_bonus = new CampaignModelCfg("input/campaigns", "cm_offer_optimizer_test", IOLoadingType.RESOURCE);
		Assert.assertTrue(CampaignModelForm.open(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT));
		Assert.assertTrue(CampaignModelForm.create(seleniumWebDriver, cm_bonus, TIMEOUT, ATTEMPT_TIMEOUT));
		logger.info("end configuring campaing model");
	}

	private void configureCampaign() throws Exception {
		logger.info("configuring campaing");
		CampaignCfg campaignCfg = new CampaignCfg("input/campaigns", "campaign_offer_optimizer_test", IOLoadingType.RESOURCE);
		Assert.assertTrue(CampaignCreationForm.open(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT));
		Assert.assertTrue(CampaignCreationForm.create(seleniumWebDriver, campaignCfg, TIMEOUT, ATTEMPT_TIMEOUT));
		logger.info("end configuring campaing ");
	}

	private void setUpConfiguration() throws Exception {
		//		configureChannels();
		//		configureTokenType();
		//		AngularFrame.close(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);
		//		configureRuleSet();
		//		AngularFrame.close(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);
		//		configureOffers();
		//		configureCampaignModel();
		//		configureCampaign();
	}

	private void createTokens(String msisdn, int tokenNUmber) {
		logger.info("Creating " + tokenNUmber + " tokens for subscriber " + msisdn);
		ArrayList<String> params = new ArrayList<String>();
		params.add(HTTPXMLRPCForm.getAuthenticationParam(env.getUserName("superman"), env.getPassword("superman")));
		params.add(HTTPXMLRPCForm.getCustoEventParam(msisdn, HTTPXMLRPCForm.EventTypes.revenue, new LinkedHashMap<HTTPXMLRPCForm.EventParameterTypes, String>() {
			{
				put(HTTPXMLRPCForm.EventParameterTypes.recharge, "1");
			}
		}));
		for (int i = 0; i < tokenNUmber; i++) {
			ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.eventmanager_generateCustomEvent.call(env.getLink() + "xmlrpc/", params);
			String responseText = response.getEntity().toString();
			if (!responseText.contains("Success")) {
				logger.error("Error creating event|param request= " + params + " \n response=" + responseText);
				Assert.fail();
			}

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	private List<String> retrieveToken(String msisdn) {
		List<String> tokenList = Lists.newArrayList();
		logger.info("retrieving tokens for subscriber " + msisdn);

		ArrayList<String> params = new ArrayList<String>();
		params.add(HTTPXMLRPCForm.getAuthenticationParam(env.getUserName("superman"), env.getPassword("superman")));
		params.add(HTTPXMLRPCForm.getStringParam(msisdn));
		params.add(HTTPXMLRPCForm.getStringParam(""));
		params.add(HTTPXMLRPCForm.getStringParam(""));

		ClientResponse<String> response = HTTPXMLRPCForm.CallTypes.offeroptimizer_getTokensList.call(env.getLink() + "xmlrpc/", params);
		String responseText = response.getEntity().toString();
		System.out.println(responseText);
		if (!responseText.contains("tokens")) {
			logger.error("Error retrieving tokens |param request= " + params + " \n response=" + responseText);
			Assert.fail();
		}
		return tokenList;
	}

	@Test
	public void testAllocate() throws Exception {
		int tokenNumber = 5;
		setUpConfiguration();
		//		String msisdn = createSubscriber(10);
		//		createTokens(msisdn, tokenNumber);
		Thread.sleep(2000);
		String msisdn = "3399900001";
		List<String> tokenList = retrieveToken(msisdn);
		Assert.assertEquals(tokenList.size(), tokenNumber);
	}
}
