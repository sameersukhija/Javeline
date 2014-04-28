package com.lumata.e4o.customer;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.e4o.exceptions.CommoditiesException;
import com.lumata.e4o.gui.security.Authorization;

import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class CampaignModelTab {
	
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
	public void testCampaignModelTab() throws Exception {
		selenium.click("id=gwt-debug-BarCaptionHomeCampaign");
		selenium.click("id=gwt-debug-InputCMCampaignCreation");
		selenium.click("id=gwt-debug-InputCMCampaignModel");
		selenium.click("id=gwt-debug-BtnCampaignModelAdd");
		selenium.type("id=gwt-debug-InputCampaignModelCreationName", "CMSomeEvents");
		selenium.type("id=gwt-debug-InputCampaignModelCreationDescription", "CMSomeEvents");
		selenium.click("id=gwt-debug-CheckCampaignModelCreationUseHierarchy-input");
		selenium.click("id=gwt-debug-CheckCampaignModelCreationUseHierarchy-input");
		selenium.click("id=gwt-debug-BtnCampaignModelCreationEventAdd");
		selenium.click("id=gwt-debug-BtnCampaignModelCreationECAdd");
		selenium.click("id=gwt-debug-ListCampaignModelCreationECType");
		selenium.mouseOver( "id=gwt-debug-ListCampaignModelCreationECType-item0" );
		selenium.mouseOver( "id=gwt-debug-ListCampaignModelCreationECType-item0-item0" );
		selenium.click("id=gwt-debug-ListCampaignModelCreationECType-item0-item0-item0");
		selenium.type("id=gwt-debug-TextCampaignModelCreationECValue", "100");
		selenium.click("id=gwt-debug-BtnCampaignModelCreationECParenthesis");
		selenium.click("xpath=(//td[@id='gwt-debug-ListCampaignModelCreationECType'])[2]");
		selenium.mouseOver( "id=gwt-debug-ListCampaignModelCreationECType-item0" );
		selenium.mouseOver( "id=gwt-debug-ListCampaignModelCreationECType-item0-item0" );
		selenium.click("id='gwt-debug-ListCampaignModelCreationECType-item0-item0-item1'");
		selenium.type("xpath=(//input[@id='gwt-debug-TextCampaignModelCreationECValue'])[2]", "10");
		selenium.click("xpath=(//td[@id='gwt-debug-ListCampaignModelCreationECUnit'])[2]");
		selenium.click("id=gwt-uid-1131");
		selenium.click("id=gwt-debug-BtnCampaignModelCreationECAdd");
		selenium.select("//table[@id='gwt-debug-FormCampaignModelCreationRules']/tbody/tr[2]/td[2]/table/tbody/tr/td/table/tbody/tr/td/div/table/tbody/tr/td/table/tbody/tr/td/div/table/tbody/tr/td/table/tbody/tr[4]/td/select", "label=OR");
		selenium.click("xpath=(//option[@value='OR'])[2]");
		selenium.click("xpath=(//td[@id='gwt-debug-ListCampaignModelCreationECType'])[3]");
		selenium.click("id=gwt-uid-1208");
		selenium.select("xpath=(//select[@id='gwt-debug-ListCampaignModelCreationECOperator'])[3]", "label==");
		selenium.type("xpath=(//input[@id='gwt-debug-TextCampaignModelCreationECValue'])[3]", "12");
		selenium.click("xpath=(//td[@id='gwt-debug-ListCampaignModelCreationECUnit'])[3]");
		selenium.click("id=gwt-uid-1713");
		selenium.click("id=gwt-debug-BtnCampaignModelCreationECAdd");
		selenium.click("xpath=(//td[@id='gwt-debug-ListCampaignModelCreationECType'])[4]");
		selenium.click("id=gwt-uid-1731");
		selenium.select("xpath=(//select[@id='gwt-debug-ListCampaignModelCreationECOperator'])[4]", "label=>");
		selenium.type("xpath=(//input[@id='gwt-debug-TextCampaignModelCreationECValue'])[4]", "23");
		selenium.click("xpath=(//td[@id='gwt-debug-ListCampaignModelCreationECUnit'])[4]");
		selenium.click("id=gwt-debug-ListCampaignModelCreationECUnit-item0");
		selenium.click("xpath=(//button[@id='gwt-debug-BtnCampaignModelCreationECAdd'])[2]");
		selenium.click("xpath=(//td[@id='gwt-debug-ListCampaignModelCreationECType'])[5]");
		selenium.click("id=gwt-uid-1832");
		selenium.type("xpath=(//input[@id='gwt-debug-TextCampaignModelCreationECValue'])[5]", "pippo");
		selenium.click("xpath=(//button[@id='gwt-debug-BtnCampaignModelCreationECAdd'])[2]");
		selenium.click("xpath=(//td[@id='gwt-debug-ListCampaignModelCreationECType'])[6]");
		selenium.click("id=gwt-uid-1929");
		selenium.type("xpath=(//input[@id='gwt-debug-TextCampaignModelCreationECValue'])[6]", "23");
		selenium.click("xpath=(//td[@id='gwt-debug-ListCampaignModelCreationECUnit'])[6]");
		selenium.click("id=gwt-debug-ListCampaignModelCreationECUnit-item0");
		selenium.click("id=gwt-debug-BtnCampaignModelCreationEAAdd");
		selenium.type("id=gwt-debug-TextCampaignModelCreationEAValue", "1");
		selenium.click("id=gwt-debug-ListCampaignModelCreationEAType");
		selenium.click("id=gwt-debug-ListCampaignModelCreationEAType-item0-item0-item0");
		selenium.type("id=gwt-debug-TextCampaignModelCreationEAValue", "100");
		selenium.select("id=gwt-debug-ListCampaignModelCreationEAUnit", "label=/call since campaign start");
		selenium.click("id=gwt-debug-BtnCampaignModelCreationEAAdd");
		selenium.type("xpath=(//input[@id='gwt-debug-TextCampaignModelCreationEAValue'])[2]", "299");
		selenium.click("id=gwt-debug-BtnCampaignModelCreationEAAdd");
		selenium.type("xpath=(//input[@id='gwt-debug-TextCampaignModelCreationEAValue'])[3]", "234");
		selenium.click("xpath=(//td[@id='gwt-debug-ListCampaignModelCreationEAType'])[3]");
		selenium.click("id=gwt-debug-ListCampaignModelCreationEAType-item0-item0-item0");
		selenium.select("xpath=(//select[@id='gwt-debug-ListCampaignModelCreationEAUnit'])[3]", "label=/message since campaign start");
		selenium.click("id=gwt-debug-CheckCampaignModelCreationEBInput-input");
		selenium.click("id=gwt-debug-CheckCampaignModelCreationEBInput-input");
		selenium.click("id=gwt-debug-BtnCampaignModelCreationENAdd");
		selenium.click("id=gwt-debug-BtnCampaignModelCreationENENGEdit");
		selenium.type("xpath=(//input[@type='text'])[91]", "test");
		selenium.click("id=gwt-debug-BtnCampaignModelCreationENESaveTemplate");
		assertEquals(selenium.getAlert(), "Text can not be empty");
		selenium.click("xpath=(//button[@name='btn-cancel'])[3]");
		selenium.click("id=gwt-debug-BtnCampaignModelCreationENCancel");
		selenium.click("id=gwt-debug-BtnCampaignModelCreationSave");
		assertEquals(selenium.getAlert(), "Campaign model successfully added");
	}
	
	
}
