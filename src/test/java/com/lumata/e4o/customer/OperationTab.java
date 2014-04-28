package com.lumata.e4o.customer;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.e4o.exceptions.CommoditiesException;
import com.lumata.e4o.gui.security.Authorization;
import com.thoughtworks.selenium.*;

import org.testng.Assert;
import org.testng.annotations.*;

public class OperationTab {
	
	private int TIMEOUT = 60000;
	private int ATTEMPT_TIMEOUT = 500;
	
	SeleniumWebDriver selenium;
	NetworkEnvironment env;
			
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "gui_server", "tenant", "user"})
	@BeforeClass
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("actrule") String gui_server, @Optional("qa") String tenant, @Optional("superman") String user ) throws CommoditiesException, JSONSException, IOFileException, NetworkEnvironmentException {		
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		/** Create Selenium WebDriver instance */
		Server gui = env.getServer( gui_server );
		selenium = new SeleniumWebDriver( gui.getBrowser( browser ), gui.getLink() );
		selenium.windowMaximize();
		
		/** Login */
		Assert.assertTrue(Authorization.login(selenium, gui.getUser( user ), TIMEOUT, ATTEMPT_TIMEOUT));
		
	}
	
	@Test 
	public void testOperationTab() throws Exception {
		selenium.click("id=gwt-debug-BarCaptionHomeOperations");
		selenium.click("id=gwt-debug-InputOPActions");
		selenium.select("//div[@id='gwt-debug-FormOperations-bottom']/div[4]/div/div/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td[2]/select", "label=ExpiredData");
		selenium.click("name=btn-process");
		Assert.assertTrue(selenium.getConfirmation().matches("^Do you really want to trigger the execution of task ExpiredData [\\s\\S]\nWARNING: not all tasks can/should be executed several times a day / week !\nWARNING: this may affect on-going CDR processing or other tasks !$"));
	}
	
}
