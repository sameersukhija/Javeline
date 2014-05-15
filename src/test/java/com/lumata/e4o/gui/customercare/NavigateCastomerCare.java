package com.lumata.e4o.gui.customercare;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.e4o.exceptions.CommoditiesException;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.security.Authorization;

public class NavigateCastomerCare {

	private static final Logger logger = LoggerFactory.getLogger( NavigateCastomerCare.class );
	
	private int TIMEOUT = 60000;
	private int ATTEMPT_TIMEOUT = 50;
	
	SeleniumWebDriver seleniumWebDriver;
	NetworkEnvironment env;
	Mysql mysqlGlobal;
	Mysql mysqlTenant;
			
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "gui_server", "tenant", "user"})
	@BeforeClass
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("actrule") String gui_server, @Optional("qa") String tenant, @Optional("superman") String user ) throws CommoditiesException, JSONSException, IOFileException, NetworkEnvironmentException, FormException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		/** Create mysql connections with global and tenant database */
		mysqlGlobal = new Mysql( env.getDataSource( "global" ) );		
		mysqlTenant = new Mysql( env.getDataSource( tenant ) );
		
		/** Create Selenium WebDriver instance */
		Server gui = env.getServer( gui_server );
		seleniumWebDriver = new SeleniumWebDriver( gui.getBrowser( browser ), gui.getLink() );
		
		/** Login */
		Assert.assertTrue( Authorization.getInstance( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT).login( gui.getUser( user ) ).navigate() );
		
	}
	
	/* 	Initialize TestCase Name */
	@BeforeMethod
	protected void startSession(Method method) throws Exception {
		seleniumWebDriver.setTestName( method.getName() ); 	
	}
	
	@Test(enabled=true, priority = 1 )
	public void navigateCustomerCare() throws CommoditiesException, JSONSException, IOFileException, FormException {
		
		
		CustomerCareForm customerCareForm = new CustomerCareForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( customerCareForm.open().searchMsisdnByIdentifier( "393669393643" ).navigate() );
		
		
		CustomerCareProfileForm customerCareProfileForm = new CustomerCareProfileForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( customerCareProfileForm.open().navigate() );
		
		
		CustomerCareServicesForm customerCareServicesForm = new CustomerCareServicesForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( customerCareServicesForm.open().navigate() );
		
		
		CustomerCareComplementsForm customerCareComplementsForm = new CustomerCareComplementsForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( customerCareComplementsForm.open().navigate() );
		
		
		CustomerCareBonusesForm customerCareBonusesForm = new CustomerCareBonusesForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( customerCareBonusesForm.open().navigate() );
		
		
		CustomerCareCampaignsForm customerCareCampaignsForm = new CustomerCareCampaignsForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( customerCareCampaignsForm.open().navigate() );
		
		
		CustomerCarePurchasesForm customerCarePurchasesForm = new CustomerCarePurchasesForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( customerCarePurchasesForm.open().navigate() );
		
		
		CustomerCareTokensForm customerCareTokensForm = new CustomerCareTokensForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( customerCareTokensForm.open().navigate() );
		
		
		CustomerCareLoyaltyForm customerCareLoyaltyForm = new CustomerCareLoyaltyForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( customerCareLoyaltyForm.open().navigate() );

		
		CustomerCareHistoryForm customerCareHistoryForm = new CustomerCareHistoryForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
		Assert.assertTrue( customerCareHistoryForm.open().navigate() );
		
		
	}
	
	@AfterClass
	public void end() {
		
		mysqlGlobal.close();
		
		mysqlTenant.close();
		
		//seleniumWebDriver.close();
		
	}
		
}
