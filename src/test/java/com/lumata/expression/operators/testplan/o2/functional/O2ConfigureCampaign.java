package com.lumata.expression.operators.testplan.o2.functional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

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
import com.lumata.expression.operators.gui.security.Authorization;

public class O2ConfigureCampaign {

	private static final Logger logger = LoggerFactory.getLogger(O2ConfigureCampaign.class);
	// TODO private static final String CFG_PATH_INPUT_LOYALTIES = "input/loyalties";
	
	private int TIMEOUT = 60000;
	private int ATTEMPT_TIMEOUT = 500;
	
	SeleniumWebDriver seleniumWebDriver;
	Environment env;
	Mysql mysql;
	// TODO
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "tenant", "user"})
	@BeforeMethod
	public void init(@Optional("FIREFOX") String browser, @Optional("E4O_VM") String environment, @Optional("tenant") String tenant, @Optional("superman") String user)
			throws EnvironmentException, OfferException, CommoditiesException, JSONSException, IOFileException {
		
		logger.info(Log.LOADING.createMessage("init", "environment"));
				
		// Create environment configuration
		env = new Environment("input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE);
		
		mysql = new Mysql(env.getDataSource(tenant));
		
		// Create Selenium WebDriver instance
		seleniumWebDriver = new SeleniumWebDriver( browser, env.getBrowser( browser ), env.getLink() );
		seleniumWebDriver.windowMaximize();
		
		// TODO configuration
		//createCfg = new LoyaltyCreateCfg(CFG_PATH_INPUT_LOYALTIES, loyaltyCreateCfg);
		//manageCfg = new LoyaltyManageCfg(CFG_PATH_INPUT_LOYALTIES, loyaltyManageCfg);
		
		// TODO Create form
		//form = new LoyaltyCreationForm(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT, createCfg, manageCfg);
		
		// Login
		Assert.assertTrue(Authorization.login(seleniumWebDriver, env.getUserName(user), env.getPassword(user), TIMEOUT, ATTEMPT_TIMEOUT));
	}	
}