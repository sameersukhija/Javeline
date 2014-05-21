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
	public void testGetErrorActions() throws JSONSException {

		JsonCurrentElement currentElementConfig = null;
		
		mainObject.setCurrentElementById(0);

		currentElementConfig = mainObject.getCurrentElement();
		
		JsonErrorActions errorActions = currentElementConfig.getErrorActions();
		
		Assert.assertNotNull(errorActions, "ErrorActions element is missing!");
		
//		Assert.assertEquals(	errorActions.getStringFromPath(ElementErrorConditionType.ELEMENT_AREADY_EXISTS.toString()), 
//								ElementErrorActionType.ABORT_CANCEL.toString()
//							);
//		Assert.assertEquals(	errorActions.getStringFromPath(ElementErrorConditionType.GENERAL_ERROR.toString()), 
//								ElementErrorActionType.RETURN_ERROR.toString()
//							);
		
		Assert.assertEquals(	errorActions.getStringFromPath("ELEMENT_AREADY_EXISTS"), "ABORT_CANCEL"	);
		Assert.assertEquals(	errorActions.getStringFromPath("GENERAL_ERROR"), "RETURN_ERROR" );
		
		Assert.assertEquals(	errorActions.getAction(ElementErrorConditionType.ELEMENT_AREADY_EXISTS), ElementErrorActionType.ABORT_CANCEL );
		Assert.assertEquals(	errorActions.getAction(ElementErrorConditionType.GENERAL_ERROR), ElementErrorActionType.RETURN_ERROR );
	}
}
