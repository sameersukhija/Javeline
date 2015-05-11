package com.lumata.e4o.regressions.gui;

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

import com.lumata.common.testing.database.Mysql;

import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Server;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.catalogmanager.RuleForm;

import com.lumata.e4o.gui.catalogmanager.RulesForm;

import com.lumata.e4o.gui.security.Authorization;

public class TestRuleTypeForm {
	
private static final Logger logger = LoggerFactory.getLogger( TestRuleTypeForm.class );
	
	private int TIMEOUT = 60000;
	private int ATTEMPT_TIMEOUT = 200;
	
	private final boolean testEnabled = true;
	
	private SeleniumWebDriver seleniumWebDriver;
	private NetworkEnvironment env;
	private Mysql mysql;
	
	
	private RulesForm ruleTypeForm;
			
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "gui_server", "tenant", "user"})
	@BeforeClass
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_VM") String environment, @Optional("actrule") String gui_server, @Optional("tenant") String tenant, @Optional("superman") String user ) throws NetworkEnvironmentException, FormException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		/** Create environment configuration */
		env = new NetworkEnvironment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		/** Create mysql connection */
		mysql = new Mysql( env.getDataSource( tenant ) );
		
		/** Create Selenium WebDriver instance */
		Server gui = env.getServer( gui_server );
		seleniumWebDriver = new SeleniumWebDriver( gui.getBrowser( browser ), gui.getLink() );
		
		seleniumWebDriver.setTestName( "init" ); 
		
		/** Login */
		Assert.assertTrue( Authorization.getInstance( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT).login( gui.getUser( user ) ).navigate() );
		
		/** Token Type Form **/
		ruleTypeForm = new RulesForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
		
	}
	
	/* 	Initialize TestCase Name */
	@BeforeMethod
	protected void startSession(Method method) throws Exception {
		seleniumWebDriver.setTestName( method.getName() ); 	
	}
	
	@Test( enabled=testEnabled, priority = 1 )
	
	//To Check form is valid with correct parameters
	public void checkMandatoryField() throws FormException
	{
		final String RULE_TYPE_NAME = Format.addTimestamp( "Rule_" );
		ruleTypeForm.openForm();
		//ruleTypeForm.waitForVisibilityOfElement();
		ruleTypeForm.clickAddBtn();
		ruleTypeForm.setName(RULE_TYPE_NAME);
		ruleTypeForm.setDescription(RULE_TYPE_NAME + " Description" );	
		ruleTypeForm.setTokenType("TType_1427787040383");
		ruleTypeForm.setChannel("Campaign manager");
		
		ruleTypeForm.setAlgorithm(RuleForm.optimizationAlgorithm.RandomAssigment.value());
		ruleTypeForm.clickKeepOfferConsistentNo();
		ruleTypeForm.clickPrevioslyAcceptedOfferNo();
		ruleTypeForm.setMaxNumberOfOffers("1");
		ruleTypeForm.setExpiredOfferBehaviour(RuleForm.expiredOfferBehaviour.Pickupnewoffer.value());
		Assert.assertTrue(ruleTypeForm.formIsValid());
		ruleTypeForm.saveRule();		
	}
	
	@Test( enabled=testEnabled, priority = 2 )
	//To Check form is invalid
	public void checkInvalidForm() throws FormException
	{
		final String RULE_TYPE_NAME = Format.addTimestamp( "Rule_" );
		ruleTypeForm.clickAddBtn();
		ruleTypeForm.saveRule();
		
		Assert.assertTrue(ruleTypeForm.formIsInvalid());
		ruleTypeForm.cancelRule();
		
	}
	
	@Test( enabled=testEnabled, priority = 3 )
	//To Check dupicate value of Rule
	public void checkDulicatedRule() throws FormException
	{
		ruleTypeForm.clickAddBtn();
		ruleTypeForm.setName("Rule1");
		ruleTypeForm.setDescription("Rule1" + " Description" );	
		ruleTypeForm.setTokenType("TType_1427787040383");
		ruleTypeForm.setChannel("Campaign manager");
		
		ruleTypeForm.setAlgorithm(RuleForm.optimizationAlgorithm.RandomAssigment.value());
		ruleTypeForm.clickKeepOfferConsistentNo();
		ruleTypeForm.clickPrevioslyAcceptedOfferNo();
		ruleTypeForm.setMaxNumberOfOffers("1");
		ruleTypeForm.setExpiredOfferBehaviour(RuleForm.expiredOfferBehaviour.Pickupnewoffer.value());
		ruleTypeForm.saveRule();
		Assert.assertTrue(ruleTypeForm.isRuleNameDuplicated());
		ruleTypeForm.cancelRule();
		
	}
	
	@AfterClass
	public void end() throws FormException {
		
		Assert.assertTrue( Authorization.getInstance( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT).quit().navigate() );
		
	}
	

}