package com.lumata.e4o.gui.loyaltymanager;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;
import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.Environment;
import com.lumata.e4o.exceptions.CommoditiesException;
import com.lumata.e4o.exceptions.OfferException;
import com.lumata.e4o.gui.loyaltymanager.LoyaltyCreationForm;
import com.lumata.e4o.gui.security.Authorization;
import com.lumata.expression.operators.json.loyalty.LoyaltyCreateCfg;
import com.lumata.expression.operators.json.loyalty.LoyaltyManageCfg;

public class ConfigureLoyalty {

	private static final Logger logger = LoggerFactory.getLogger(ConfigureLoyalty.class);
	private static final String CFG_PATH_INPUT_LOYALTIES = "input/loyalties";

	/*private Integer selectLoyaltyProgramsCount() {
		Integer loyaltyProgramsCount = 0;
		
		ResultSet rs = mysql.execQuery("SELECT COUNT(*) FROM loyalty_programs");
		try {
			while (rs.next()) {
				loyaltyProgramsCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "SQL error: " + e.getMessage()));
			return null;
		}
		
		return loyaltyProgramsCount;
	}*/
	
	private int TIMEOUT = 60000;
	private int ATTEMPT_TIMEOUT = 500;
	
	SeleniumWebDriver seleniumWebDriver;
	Environment env;
	Mysql mysql;
	LoyaltyCreationForm form;
	LoyaltyCreateCfg createCfg;
	LoyaltyManageCfg manageCfg;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "tenant", "user", "loyaltyCreateCfg", "loyaltyManageCfg"})
	@BeforeMethod
	public void init(@Optional("FIREFOX") String browser, @Optional("E4O_VM") String environment, @Optional("tenant") String tenant, @Optional("superman") String user, @Optional("loyalty_create") String loyaltyCreateCfg, @Optional("loyalty_manage") String loyaltyManageCfg)
			throws EnvironmentException, OfferException, CommoditiesException, JSONSException, IOFileException {
		
		logger.info(Log.LOADING.createMessage("init", "environment"));
				
		// Create environment configuration
		env = new Environment("input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE);
		
		mysql = new Mysql(env.getDataSource(tenant));
		
		// Create Selenium WebDriver instance
		seleniumWebDriver = new SeleniumWebDriver( browser, env.getBrowser( browser ), env.getLink() );
		seleniumWebDriver.windowMaximize();
		
		// Loyalty configuration
		createCfg = new LoyaltyCreateCfg(CFG_PATH_INPUT_LOYALTIES, loyaltyCreateCfg);
		manageCfg = new LoyaltyManageCfg(CFG_PATH_INPUT_LOYALTIES, loyaltyManageCfg);
		
		// Create form
		form = new LoyaltyCreationForm(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT, createCfg, manageCfg);
		
		// Login
		Assert.assertTrue(Authorization.login(seleniumWebDriver, env.getUserName(user), env.getPassword(user), TIMEOUT, ATTEMPT_TIMEOUT));
	}
	
	@AfterMethod
	public void tearDown() {
		
		seleniumWebDriver.close();
	}
	
	/* 	Initialize TestCase Name */
	@BeforeMethod
	protected void startSession(Method method) throws Exception {
		seleniumWebDriver.setTestName( method.getName() ); 	
	}
	
	@Test(enabled=true)
	public void configureBadges() {
		
		try {
			form.open();
			form.create();
			form.manage();
			form.openSubsection(ImmutableMap.of(
					"clickAccordion", "false"));
			form.delete();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error during loyalty configuration : " + e.getMessage());
		}
	}
	
	@Test(enabled=false)
	public void configureBadgesWithDuplicationError() {

		try {			
			form.openSubsection(ImmutableMap.of(
					"clickAccordion", "false"));
			form.create();
			Assert.assertEquals(form.duplication(), "The name is already used");
			form.closeNewProgramPopup();
			form.delete();
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error during loyalty configuration : " + e.getMessage());
		}
	}
}
