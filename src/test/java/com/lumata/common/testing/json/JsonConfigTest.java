package com.lumata.common.testing.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.JsonConfig;

/**
 * JsonConfig class unit test
 *
 * @author <a href="mailto:vincenzo.marrazzo@lumatagroup.com">Vincenzo Marrazzo</a>
 * 
 */
public class JsonConfigTest {

	private JsonConfig underTest = null;
	
	/**
	 * Only for test
	 */
	private class SampleFinalJsonConfigObject extends JsonConfig {

		public SampleFinalJsonConfigObject(String path, String jsonFile) throws JSONSException {
			super(path, jsonFile);
		}

		public SampleFinalJsonConfigObject(Map<String, Object> newObject) {
			super(newObject);
		}
	}
	
	@BeforeSuite
	public void setupSuite() throws JSONSException { 
		
		//  create under test object
		underTest = new SampleFinalJsonConfigObject("jsonconfigtest", "jsonconfig_test");
	}
	
	@Test 
	public void testGetIntegerFromPath() {
		
		Integer actual = underTest.getIntegerFromPath("mainListTest.GoodSample.sampleInteger");
		
		Assert.assertNotNull( actual, "Missing \"Integer\" good sample!");
		
		Integer exp = Integer.parseInt("100");
		
		Assert.assertEquals( actual, exp, "Unexpected value!");
		
		try {
			actual = underTest.getIntegerFromPath("mainListTest.BadSample.sampleInteger");
			
			Assert.assertTrue(false, "This step must not reached because value is not an \"Integer\"!");
		}
		catch ( NumberFormatException e ) {
			Assert.assertTrue(true);
		}
		
		actual = underTest.getIntegerFromPath("thisLabelNotExist");
		
		Assert.assertNull(actual, "This \"Integer\" must be null!");
	}
	
	@Test 
	public void testGetStringFromPath() {
		
		String actual = underTest.getStringFromPath("mainListTest.GoodSample.sampleString");
		
		Assert.assertNotNull( actual, "Missing \"String\" good sample!");
		
		String exp = "Forza Napoli!";
		
		Assert.assertEquals( actual, exp, "Unexpected value!");
		
		try {
			actual = underTest.getStringFromPath("mainListTest.BadSample.sampleString");
			
			Assert.assertTrue(false, "This step must not reached because value is not an \"String\"!");
		}
		catch ( ClassCastException e ) {
			Assert.assertTrue(true);
		}		
		
		actual = underTest.getStringFromPath("thisLabelNotExist");
		
		Assert.assertNull(actual, "This \"Integer\" must be null!");
	}
	
	@Test 
	public void testGetBooleanFromPath() {
		
		Boolean actual = underTest.getBooleanFromPath("mainListTest.GoodSample.sampleBoolean");
		
		Assert.assertNotNull( actual, "Missing \"Boolean\" good sample!");
		
		Boolean exp = Boolean.parseBoolean("TRUE");
		
		Assert.assertEquals( actual, exp, "Unexpected value!");
		
		try {
			actual = underTest.getBooleanFromPath("mainListTest.BadSample.sampleBoolean");
			
			Assert.assertTrue(false, "This step must not reached because value is not an \"Boolean\"!");
		}
		catch ( ClassCastException e ) {
			Assert.assertTrue(true);
		}		
		
		actual = underTest.getBooleanFromPath("thisLabelNotExist");
		
		Assert.assertNull(actual, "This \"Integer\" must be null!");
	}
	
	@Test 
	public void testGetJsonMapFromPath() throws JSONSException {
		
		Map<String, Object> actual = underTest.getJsonMapFromPath("mainListTest.GoodSample");
		
		Assert.assertNotNull( actual, "Missing \"Map\" good sample!");
		
		Assert.assertTrue( actual instanceof HashMap, "Missing list type!");
	}
	
	@Test( expectedExceptions = {JSONSException.class} )
	public void testUnexistingMapFromPath() throws JSONSException {
		
		underTest.getJsonMapFromPath("thisLabelNotExist");
		
		Assert.assertTrue( false, "No \"Map\" will be returned here!");
	}

	@Test( expectedExceptions = {JSONSException.class} )
	public void testUnexistingListFromPath() throws JSONSException {
		
		underTest.getJsonListFromPath("thisLabelNotExist");
		
		Assert.assertTrue( false, "No \"List\" will be returned here!");
	}	
	
	@Test
	public void testEmptyList() throws JSONSException {
		
		Object actual = underTest.getJsonListFromPath("emptyComponentsSection.emptyList");
		
		Assert.assertNull( actual, "This \"List\" object must be null!");
	}
	
	@Test
	public void testEmptyMap() throws JSONSException {
		
		Object actual = underTest.getJsonMapFromPath("emptyComponentsSection.emptyMap");
		
		Assert.assertNull( actual, "This \"Map\" object must be null!");
	}
	
	@Test 
	public void testGetJsonListFromPath() throws JSONSException {
		
		List<Object> actual = underTest.getJsonListFromPath("mainListTest.GoodSample.sampleArray");
		
		Assert.assertNotNull( actual, "Missing \"List\" good sample!");
		
		Assert.assertTrue( actual instanceof ArrayList, "Missing map type!");
	}
	
	@Test
	public void testExtMissingIntExist() throws JSONSException {
		
		// this container does NOT exist
		String extMissing = "externalNotExist";
		
		// this label exists but NOT into above external
		String intExist = "mainListTest";
		
		Object actual = underTest.getObjectFromPath( extMissing + JsonConfig.NAVIGATION_SEPARATOR + intExist);

		Assert.assertNull( actual, "This object (with this path) does NOT exist!");
	}
	
	@Test 
	public void testGetStringList() throws JSONSException {
		
		List<String> actual = underTest.getStringList("sampleArrayGetListString");
		
		Assert.assertNotNull( actual, "Missing \"List<String>\" good sample!");
		
		List<String> expected = Arrays.asList("One", "Four", "Three", "Seven");
		
		Assert.assertEquals(actual, expected);
	}	
	
	@Test( expectedExceptions = {JSONSException.class} )
	public void testGetJsonListString() throws JSONSException {
		
		underTest.getStringList("missingArrayLabel");
		
		Assert.assertTrue( false, "No \"List\" will be returned here!");
	}	
	
	@Test( expectedExceptions = {JSONSException.class} )
	public void testModifyUnexistingString() throws JSONSException {
		
		underTest.modifyStringFromPath("thisKeyNotExist", "wow");
		
		Assert.assertTrue( false, "Not possible to reach this line!");
	}
	
	@Test 
	public void testNavigationOfFile() throws JSONSException {
		
		String navigationPath = "mainListTest.GoodSample.sampleString";
		
		Object actual = underTest.getObjectFromPath(navigationPath);
		
		Assert.assertNotNull(actual, "Found object is null!");
		
		Assert.assertTrue(actual instanceof String, "Found object is NOT a String!");
		
		Assert.assertEquals(actual.toString(), "Forza Napoli!", "It contains an unexpected value!");
	}
	
	@Test
	public void testConstructorFromMap() throws JSONSException {
		
		String navigationPath = "mainListTest.GoodSample";
		String navigationOnSubObject = "sampleString";
		
		SampleFinalJsonConfigObject subObject = new SampleFinalJsonConfigObject(underTest.getJsonMapFromPath(navigationPath));
		
		Assert.assertNotNull( subObject, "Sub Object is null!");
		
		Object queryOnSubObject = subObject.getObjectFromPath(navigationOnSubObject);

		Assert.assertNotNull( queryOnSubObject, "Found object is null!");
		
		Assert.assertTrue(queryOnSubObject instanceof String, "Found object is NOT a String!");
		
		Assert.assertEquals(queryOnSubObject.toString(), "Forza Napoli!", "It contains an unexpected value!");
	}
	
	@Test
	public void testApproach2CurrentElement() throws JSONSException {
		
		Map<String, Object> init = new HashMap<>();
		
		init.put("string", "Sample string");
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("uno"); list.add("due"); list.add("tre");

		init.put("list", list);
		
		Map<String, Object> nestedMap = new HashMap<String, Object>();
		nestedMap.put("uno", "1");
		nestedMap.put("due", "2");
		
		init.put("map", nestedMap);
		
		SampleFinalJsonConfigObject localTest = new SampleFinalJsonConfigObject(init);
		
		Assert.assertEquals( localTest.getStringFromPath("string"), "Sample string");
		Assert.assertEquals( localTest.getJsonListFromPath("list"), list);
		Assert.assertEquals( localTest.getJsonMapFromPath("map"), nestedMap);

		Assert.assertEquals( localTest.getStringFromPath("map.uno"), "1");
		Assert.assertEquals( localTest.getStringFromPath("map.due"), "2");
		
		Map<String,Object> actual = localTest.getJsonMapFromPath(".");
		
		// Technical debt the "Integer" can be decoded as "String"
		Assert.assertEquals( actual, init, "Recostructed Map missmatch");
	}
}

