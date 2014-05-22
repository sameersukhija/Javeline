package com.lumata.common.testing.json;

import java.util.Map;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorActionType;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorConditionType;
import com.lumata.common.testing.json.JsonConfigurationElement;
import com.lumata.common.testing.json.JsonConfigurationFile;
import com.lumata.common.testing.json.JsonConfigurationElement.JsonErrorActions;

public class PlaygroundJsonTest {

	//
	//	TEST SECTION
	//
	
	@Test
	public void testBasic() throws JSONSException {

		/**
		 * 
		 * Simple json element, it is not present an explicit "element" object
		 * 
		 */
		
		JsonBasic tmp = new JsonBasic("jsonconfigtest", "sample_commodities");

		int numb = tmp.getList().size();

		Reporter.log("Number of elements -> " + numb, true);
		Assert.assertEquals( 3, numb, "Number of elements does not match!");

		// fetch name property from each element
		// into basic implementation it's necessary to set current element
		for (int index = 0; index < numb; index++) {

			tmp.setCurrentElementById(index);

			Reporter.log("Element name of index \""+index+"\" -> " + tmp.getName(), true);
		}
	}

	@Test
	public void testAdvance() throws JSONSException {

		/**
		 * 
		 * Complex json element, the element is developed explicitly
		 * 
		 */
		
		JsonAdvance tmp = new JsonAdvance("jsonconfigtest", "sample_commodities");

		int numb = tmp.getList().size();

		Reporter.log("Number of elements -> " + numb, true);
		Assert.assertEquals( 3, numb, "Number of elements does not match!");

		int refIndex = 0;
		JsonAdvance.JsonComplexElement ref = null;

		// fetch name property from each element
		// into basic implementation it's necessary to set current element
		for (int index = 0; index < numb; index++) {

			tmp.setCurrentElementById(index);

			JsonAdvance.JsonComplexElement tmp2 = tmp.getTypedElement();

			if ( index == refIndex )
				ref = tmp2;

			Reporter.log("Element name of index \""+index+"\" -> " + tmp2.getName(), true);
		}

		Assert.assertNotNull( ref, "Missing element!");
	}

	@Test
	public void testErrorAction() throws JSONSException {

		/**
		 * THIS TEST GENERATE AN EXCEPTION!!!!
		 * 
		 * This test is based on advanced example and for each element
		 * fetches the "errorActions" section and prints the condition/type data.
		 * 
		 * PS This test generates an exception because there is an element without "errorActions" section (it's optionally into json)
		 */
		
		JsonAdvance tmp = new JsonAdvance("jsonconfigtest", "sample_commodities");

		int numb = tmp.getList().size();

		Reporter.log("Number of elements -> " + numb, true);
		Assert.assertEquals( 3, numb, "Number of elements does not match!");

		for (int index = 0; index < numb; index++) {

			tmp.setCurrentElementById(index);

			JsonAdvance.JsonComplexElement tmp2 = tmp.getTypedElement();

			try {
				JsonErrorActions errTmp = tmp2.getErrorActions();

				// print "errorActions" section data
				for (ElementErrorConditionType errCondition : ElementErrorConditionType.values()) 
				{
					ElementErrorActionType errAction = errTmp.getAction(errCondition);

					Reporter.log(errCondition + " -> " + errAction, true);
				}

				if ( index == 1 || index == 2 )
					Assert.assertTrue(false, "This element does not contains \"errorActions\" section and must throw an Exception!");
			}
			catch ( JSONSException e ) {

				if ( index == 1 && e.getMessage().contains("requests an \"errorActions\"") )
					Assert.assertTrue(true);
				else if ( index == 2 && e.getMessage().contains("requests condition") )
					Assert.assertTrue(true);
				else
					Assert.assertTrue(false, "An element with \"errorActions\" throw an Exception!");		
			}
		}
	}
	
	//
	//	Example object section
	//
	
	/**
	 * Basic example :
	 * 
	 * 1. A new extension of JsonConfigurationFile 
	 * 2. WITHOUT JsonConfigurationElement explicit so abstract one is used
	 * 
	 * es. commodities json file contains a list of flat object to be configred
	 */
	private class JsonBasic extends JsonConfigurationFile {

		public JsonBasic(String path, String jsonFile) throws JSONSException {
			super(path, jsonFile);
		}

		@Override
		public String getElementsSectionLabel() {

			return "commodities";
		}

		/**
		 * This methods is located into JsonConfigurationFile and acts on current element
		 * 
		 * @return
		 */
		public String getName() {

			return getCurrentElement().getStringFromPath("name");
		}

	}

	/**
	 * Advanced example :
	 * 
	 * 1. A new extension of JsonConfigurationFile 
	 * 2. A new extension of JsonConfigurationElement
	 * 
	 * es. campaing model json file contains a list of complex object that contain other object/array/etc
	 */
	private class JsonAdvance extends JsonConfigurationFile {

		public JsonAdvance(String path, String jsonFile) throws JSONSException {
			super(path, jsonFile);
		}

		/**
		 * Json element "complex"
		 */
		public class JsonComplexElement extends JsonConfigurationElement {

			public JsonComplexElement(Map<String, Object> newObject) {
				super(newObject);
			}

			/**
			 * This method is only applicable to "element"
			 * 
			 * @return
			 */
			public String getName() {

				return this.getStringFromPath("name");
			}
		}

		/**
		 * This method returns the "element" - Useful to manipulate externally
		 * 
		 * @return
		 *
		 * @throws JSONSException
		 */
		public JsonComplexElement getTypedElement() throws JSONSException {

			return new JsonComplexElement(getCurrentElement().getJsonMapFromPath("."));
		}

		@Override
		public String getElementsSectionLabel() {

			return "commodities";
		}

	}

}
