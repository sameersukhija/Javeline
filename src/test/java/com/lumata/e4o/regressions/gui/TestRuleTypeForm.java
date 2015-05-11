//package com.lumata.e4o.regressions.gui;
//
//import java.lang.reflect.Method;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import com.lumata.common.testing.exceptions.NetworkEnvironmentException;
//import com.lumata.common.testing.validating.Format;
//import com.lumata.e4o.exceptions.FormException;
//import com.lumata.e4o.gui.catalogmanager.RulesForm;
//import com.lumata.e4o.testing.common.ParentTestCase;
//import com.lumata.e4o.testing.common.TCOwner;
//import com.lumata.e4o.testing.common.TCOwners;
//import com.lumata.e4o.testing.common.TCSeleniumWebDriver;
//@TCOwners(
//		@TCOwner( name="Isha Vyas", email="isha.vyas@lumatagroup.com" )
//	)
//	@TCSeleniumWebDriver
//public class TestRuleTypeForm extends ParentTestCase{
//	
//	private RulesForm ruleTypeForm;
//			
//	@BeforeMethod
//	public void initRuleTypeForm( Method method ) throws NetworkEnvironmentException, FormException {		
//		
//		/** Token Type Form **/
//		ruleTypeForm = new RulesForm( seleniumWebDriver, TIMEOUT, ATTEMPT_TIMEOUT );
//		
//		seleniumWebDriver.setTestName( method.getName() );
//		
//	}
//	
//	@Test( enabled=TEST_ENABLED, priority = 1 )
//	
//	//To Check form is valid with correct parameters
//	public void checkMandatoryField() throws FormException
//	{
//		final String RULE_TYPE_NAME = Format.addTimestamp( "Rule_" );
//		ruleTypeForm.openForm();
//		//ruleTypeForm.waitForVisibilityOfElement();
//		ruleTypeForm.clickAddBtn();
//		ruleTypeForm.setName(RULE_TYPE_NAME);
//		ruleTypeForm.setDescription(RULE_TYPE_NAME + " Description" );	
//		ruleTypeForm.setTokenType("TType_1427787040383");
//		ruleTypeForm.setChannel("Campaign manager");
//		
//		ruleTypeForm.setAlgorithm(RulesForm.optimizationAlgorithm.RandomAssigment.value());
//		ruleTypeForm.clickKeepOfferConsistentNo();
//		ruleTypeForm.clickPrevioslyAcceptedOfferNo();
//		ruleTypeForm.setMaxNumberOfOffers("1");
//		ruleTypeForm.setExpiredOfferBehaviour(RulesForm.expiredOfferBehaviour.Pickupnewoffer.value());
//		Assert.assertTrue(ruleTypeForm.formIsValid());
//		ruleTypeForm.saveRule();		
//	}
//	
//	@Test( enabled=TEST_ENABLED, priority = 2 )
//	//To Check form is invalid
//	public void checkInvalidForm() throws FormException
//	{
//		ruleTypeForm.clickAddBtn();
//		ruleTypeForm.saveRule();
//		
//		Assert.assertTrue(ruleTypeForm.formIsInvalid());
//		ruleTypeForm.cancelRule();
//		
//	}
//	
//	@Test( enabled=TEST_ENABLED, priority = 3 )
//	//To Check dupicate value of Rule
//	public void checkDulicatedRule() throws FormException
//	{
//		ruleTypeForm.clickAddBtn();
//		ruleTypeForm.setName("Rule1");
//		ruleTypeForm.setDescription("Rule1" + " Description" );	
//		ruleTypeForm.setTokenType("TType_1427787040383");
//		ruleTypeForm.setChannel("Campaign manager");
//		
//		ruleTypeForm.setAlgorithm(RulesForm.optimizationAlgorithm.RandomAssigment.value());
//		ruleTypeForm.clickKeepOfferConsistentNo();
//		ruleTypeForm.clickPrevioslyAcceptedOfferNo();
//		ruleTypeForm.setMaxNumberOfOffers("1");
//		ruleTypeForm.setExpiredOfferBehaviour(RulesForm.expiredOfferBehaviour.Pickupnewoffer.value());
//		ruleTypeForm.saveRule();
//		Assert.assertTrue(ruleTypeForm.isRuleNameDuplicated());
//		ruleTypeForm.cancelRule();
//		
//	}
//
//}