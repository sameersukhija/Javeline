package com.lumata.expression.operators.testplan.functional;

import java.lang.reflect.Method;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.Environment;
import com.lumata.expression.operators.exceptions.CommoditiesException;
import com.lumata.expression.operators.exceptions.OfferException;
import com.lumata.expression.operators.exceptions.TokenTypeException;
import com.lumata.expression.operators.gui.loyalty.LoyaltyCreationForm;
import com.lumata.expression.operators.gui.security.Authorization;
import com.lumata.expression.operators.json.loyalty.LoyaltyCreateCfg;

public class ConfigureLoyalty {

	private static final Logger logger = LoggerFactory.getLogger(ConfigureLoyalty.class);
	
	private int TIMEOUT = 60000;
	private int ATTEMPT_TIMEOUT = 500;
	
	SeleniumWebDriver seleniumWebDriver;
	Environment env;
	Mysql mysql;
	LoyaltyCreationForm form;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "tenant", "user"})
	@BeforeSuite
	public void init(@Optional("FIREFOX") String browser, @Optional("E4O_VM") String environment, @Optional("tenant") String tenant, @Optional("superman") String user)
			throws EnvironmentException, OfferException, CommoditiesException, JSONSException, IOFileException {
		
		logger.info(Log.LOADING.createMessage("init", "environment"));
		
		// Create environment configuration
		env = new Environment("input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE);
		
		mysql = new Mysql(env.getDataSource(tenant));
		
		// Create Selenium WebDriver instance
		seleniumWebDriver = new SeleniumWebDriver( browser, env.getBrowser( browser ), env.getLink() );
		seleniumWebDriver.windowMaximize();
		
		// Create form
		form = new LoyaltyCreationForm(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT);
		
		// Login
		Assert.assertTrue(Authorization.login(seleniumWebDriver, env.getUserName(user), env.getPassword(user), TIMEOUT, ATTEMPT_TIMEOUT));
	}
	
	/* 	Initialize TestCase Name */
	@BeforeMethod
	protected void startSession(Method method) throws Exception {
		seleniumWebDriver.setTestName( method.getName() ); 	
	}
	
	@Parameters({"tenant", "loyaltyCreateCfg"})
	@Test(enabled=true, priority = 1)
	public void configureBadges(@Optional("tenant") String tenant, @Optional("loyalty_create") String loyaltyCreateCfg) throws TokenTypeException, JSONSException, IOFileException, JSONException {
		
		LoyaltyCreateCfg createCfg = new LoyaltyCreateCfg("input/loyalties", loyaltyCreateCfg);
		
		// open section from menu and popup
		Assert.assertTrue(form.open(createCfg));
		
		// create program
		Assert.assertTrue(form.create(createCfg));
		
		// manage program
		Assert.assertTrue(form.manage(createCfg));
	}
}
