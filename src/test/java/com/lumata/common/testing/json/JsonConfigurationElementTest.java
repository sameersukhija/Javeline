package com.lumata.common.testing.json;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorActionType;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorConditionType;
import com.lumata.common.testing.json.JsonConfigurationElement.JsonErrorActions;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;

public class JsonConfigurationElementTest {

	/**
	 * 
	 */
	private SampleFinalJsonConfigurationFile mainObject = null;
	
	@BeforeSuite
	public void setUp() throws JSONSException {
		
		mainObject = new SampleFinalJsonConfigurationFile("jsonconfigtest", "sample_commodities");
	}	
	
	@Test
	public void testEnabledAndDeleteProperties() throws JSONSException {
		
		JsonCurrentElement currentElementConfig = null;
		
		mainObject.setCurrentElementById(0);

		currentElementConfig = mainObject.getCurrentElement();
		
		// explicit case
		Assert.assertEquals(	currentElementConfig.getEnabled(), Boolean.FALSE, "\"enabled\" status must be \"false\"" );
		Assert.assertEquals(	currentElementConfig.getDelete(), Boolean.TRUE, "\"delete\" status must be \"true\"" );
		
		mainObject.setCurrentElementById(1);

		currentElementConfig = mainObject.getCurrentElement();
		
		// implicit case
		Assert.assertEquals(	currentElementConfig.getEnabled(), Boolean.TRUE, "\"enabled\" status must be \"true\"" );
		Assert.assertEquals(	currentElementConfig.getDelete(), Boolean.FALSE, "\"delete\" status must be \"false\"" );
		
		mainObject.setCurrentElementById(2);

		currentElementConfig = mainObject.getCurrentElement();
		
		// implicit/explicit case
		Assert.assertEquals(	currentElementConfig.getEnabled(), Boolean.TRUE, "\"enabled\" status must be \"true\"" );
		Assert.assertEquals(	currentElementConfig.getDelete(), Boolean.TRUE, "\"delete\" status must be \"true\"" );
	}
	
	@Test
	public void testGetErrorActions() throws JSONSException {

		JsonCurrentElement currentElementConfig = null;
		
		mainObject.setCurrentElementById(0);

		currentElementConfig = mainObject.getCurrentElement();
		
		JsonErrorActions errorActions = currentElementConfig.getErrorActions();
		
		Assert.assertNotNull(errorActions, "ErrorActions element is missing!");
		
		Assert.assertEquals(	errorActions.getStringFromPath("ELEMENT_AREADY_EXISTS"), "ABORT_CANCEL"	);
		Assert.assertEquals(	errorActions.getStringFromPath("GENERAL_ERROR"), "RETURN_ERROR" );
		
		Assert.assertEquals(	errorActions.getAction(ElementErrorConditionType.ELEMENT_AREADY_EXISTS), ElementErrorActionType.ABORT_CANCEL );
		Assert.assertEquals(	errorActions.getAction(ElementErrorConditionType.GENERAL_ERROR), ElementErrorActionType.RETURN_ERROR );
		
		// miss entire "errorActions" section
		mainObject.setCurrentElementById(1);
		
		try {
			mainObject.getCurrentElement().getErrorActions();
			
			Assert.assertTrue(false);
			
		} catch (JSONSException e) {
			Assert.assertTrue(true);
		}

		// miss a condition into "errorActions" section
		mainObject.setCurrentElementById(1);
		
		try {
			mainObject.getCurrentElement().getErrorActions().getAction(ElementErrorConditionType.ELEMENT_AREADY_EXISTS);
			
			Assert.assertTrue(false);
			
		} catch (JSONSException e) {
			Assert.assertTrue(true);
		}
	}
}
