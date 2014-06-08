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
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.e4o.exceptions.CommoditiesException;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.exceptions.OfferException;
import com.lumata.e4o.gui.loyaltymanager.LoyaltyCreationForm;
import com.lumata.e4o.gui.security.Authorization;
import com.lumata.e4o.json.gui.loyaltymanager.LoyaltyCreateCfg;
import com.lumata.e4o.json.gui.loyaltymanager.LoyaltyManageCfg;

public class OLDConfigureLoyalty {

	private static final Logger logger = LoggerFactory.getLogger(OLDConfigureLoyalty.class);
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
	NetworkEnvironment env;
	Mysql mysql;
	LoyaltyCreationForm form;
	LoyaltyCreateCfg createCfg;
	LoyaltyManageCfg manageCfg;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "tenant", "gui_server", "user", "loyaltyCreateCfg", "loyaltyManageCfg"})
	@BeforeMethod
	public void init(@Optional("FIREFOX") String browser, @Optional("E4O_VM") String environment, @Optional("tenant") String tenant, @Optional("actrule") String gui_server, @Optional("superman") String user, @Optional("loyalty_create") String loyaltyCreateCfg, @Optional("loyalty_manage") String loyaltyManageCfg)
			throws OfferException, CommoditiesException, JSONSException, IOFileException, NetworkEnvironmentException, FormException {
		
		logger.info(Log.LOADING.createMessage("init", "environment"));
				
		// Create environment configuration
		env = new NetworkEnvironment("input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE);
		
		mysql = new Mysql(env.getDataSource(tenant));
		
		// Create Selenium WebDriver instance
		Server gui = env.getServer( gui_server );
		seleniumWebDriver = new SeleniumWebDriver( gui.getBrowser( browser ), gui.getLink() );
		
		// Loyalty configuration
		createCfg = new LoyaltyCreateCfg(CFG_PATH_INPUT_LOYALTIES, loyaltyCreateCfg);
		manageCfg = new LoyaltyManageCfg(CFG_PATH_INPUT_LOYALTIES, loyaltyManageCfg);
		
		// Create form
//		form = new LoyaltyCreationForm(seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT, createCfg, manageCfg);
		
		// Login
		Assert.assertTrue( Authorization.getInstance( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT).login( gui.getUser( user ) ).navigate() );
		
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
//			form.open();
//			form.create();
//			form.manage();
//			form.openSubsection(ImmutableMap.of(
//					"clickAccordion", "false"));
//			form.delete();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error during loyalty configuration : " + e.getMessage());
		}
	}
	
	@Test(enabled=false)
	public void configureBadgesWithDuplicationError() {

		try {			
//			form.openSubsection(ImmutableMap.of(
//					"clickAccordion", "false"));
//			form.create();
//			Assert.assertEquals(form.duplication(), "The name is already used");
//			form.closeNewProgramPopup();
//			form.delete();
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error during loyalty configuration : " + e.getMessage());
		}
	}
}
