package com.g4s.common.testing.json;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.g4s.common.testing.exceptions.JSONSException;
import com.g4s.common.testing.json.JsonConfig;

/**
 * JsonConfigurationFile class unit test
 *
 * @author <a href="mailto:vincenzo.marrazzo@lumatagroup.com">Vincenzo Marrazzo</a>
 * 
 */
public class JsonConfigurationFileTest {

	/**
	 * 
	 */
	private SampleFinalJsonConfigurationFile mainObject = null;
	
	@BeforeSuite
	public void setUp() throws JSONSException {
		
		mainObject = new SampleFinalJsonConfigurationFile(
				"jsonconfigtest", "sample_commodities");
	}
	
	@Test
	public void testSetCurrentElementByIndex() throws JSONSException {

		JsonConfig currentElementConfig = null;
		
		Assert.assertEquals( mainObject.getList().size(), 3, "List of configuration elements does NOT match!");
		
		mainObject.setCurrentElementById(0);

		currentElementConfig = mainObject.getCurrentElement();

		Assert.assertEquals(currentElementConfig.getStringFromPath("type"), "Internal");
		Assert.assertEquals(currentElementConfig.getStringFromPath("name"), "Points");
		Assert.assertEquals(currentElementConfig.getStringFromPath("account"), "Internal");
		Assert.assertEquals(currentElementConfig.getStringFromPath("accountType"), "INTERNAL_POINT");
		Assert.assertEquals(currentElementConfig.getStringFromPath("unit"), "Without unit");
		Assert.assertEquals(currentElementConfig.getStringFromPath("listPrice"), "");			
		
		mainObject.setCurrentElementById(1);

		currentElementConfig = mainObject.getCurrentElement();

		Assert.assertEquals(currentElementConfig.getStringFromPath("type"), "External");
		Assert.assertEquals(currentElementConfig.getStringFromPath("name"), "Bonus1");
		Assert.assertEquals(currentElementConfig.getStringFromPath("account"), "freeSMS");
		Assert.assertEquals(currentElementConfig.getStringFromPath("accountType"), "IN_SMS");
		Assert.assertEquals(currentElementConfig.getStringFromPath("unit"), "Without unit");
		Assert.assertEquals(currentElementConfig.getStringFromPath("listPrice"), "");

		mainObject.setCurrentElementById(2);

		currentElementConfig = mainObject.getCurrentElement();

		Assert.assertEquals(currentElementConfig.getStringFromPath("type"), "External");
		Assert.assertEquals(currentElementConfig.getStringFromPath("name"), "Bonus2");
		Assert.assertEquals(currentElementConfig.getStringFromPath("account"), "freeSMS");
		Assert.assertEquals(currentElementConfig.getStringFromPath("accountType"), "IN_SMS");
		Assert.assertEquals(currentElementConfig.getStringFromPath("unit"), "Without unit");
		Assert.assertEquals(currentElementConfig.getStringFromPath("listPrice"), "");	
		
		// test "getCurrentElementById" method
		currentElementConfig = mainObject.getCurrentElementById(1);

		Assert.assertEquals(currentElementConfig.getStringFromPath("type"), "External");
		Assert.assertEquals(currentElementConfig.getStringFromPath("name"), "Bonus1");
		Assert.assertEquals(currentElementConfig.getStringFromPath("account"), "freeSMS");
		Assert.assertEquals(currentElementConfig.getStringFromPath("accountType"), "IN_SMS");
		Assert.assertEquals(currentElementConfig.getStringFromPath("unit"), "Without unit");
		Assert.assertEquals(currentElementConfig.getStringFromPath("listPrice"), "");
	}
	
	@Test
	public void testGetElementAndModifyIt() throws JSONSException {
		
		SampleFinalJsonConfigurationFile utObject = new SampleFinalJsonConfigurationFile(
				"jsonconfigtest", "sample_commodities");

		JsonConfig currentElementConfig = null;
		
		utObject.setCurrentElementById(0);

		currentElementConfig = utObject.getCurrentElement();

		Assert.assertEquals(currentElementConfig.getStringFromPath("type"), "Internal");
		Assert.assertEquals(currentElementConfig.getStringFromPath("name"), "Points");
		Assert.assertEquals(currentElementConfig.getStringFromPath("account"), "Internal");
		Assert.assertEquals(currentElementConfig.getStringFromPath("accountType"), "INTERNAL_POINT");
		Assert.assertEquals(currentElementConfig.getStringFromPath("unit"), "Without unit");
		Assert.assertEquals(currentElementConfig.getStringFromPath("listPrice"), "");			
		
		// modify index 0
		currentElementConfig.modifyStringFromPath("name", "Points" + "TIMESTAMP");
		
		utObject.setCurrentElementById(1);

		currentElementConfig = utObject.getCurrentElement();

		Assert.assertEquals(currentElementConfig.getStringFromPath("type"), "External");
		Assert.assertEquals(currentElementConfig.getStringFromPath("name"), "Bonus1");
		Assert.assertEquals(currentElementConfig.getStringFromPath("account"), "freeSMS");
		Assert.assertEquals(currentElementConfig.getStringFromPath("accountType"), "IN_SMS");
		Assert.assertEquals(currentElementConfig.getStringFromPath("unit"), "Without unit");
		Assert.assertEquals(currentElementConfig.getStringFromPath("listPrice"), "");
		
		// Come back on 0
		utObject.setCurrentElementById(0);

		currentElementConfig = utObject.getCurrentElement();

		Assert.assertEquals(currentElementConfig.getStringFromPath("type"), "Internal");
		Assert.assertEquals(currentElementConfig.getStringFromPath("name"), "Points" + "TIMESTAMP");
		Assert.assertEquals(currentElementConfig.getStringFromPath("account"), "Internal");
		Assert.assertEquals(currentElementConfig.getStringFromPath("accountType"), "INTERNAL_POINT");
		Assert.assertEquals(currentElementConfig.getStringFromPath("unit"), "Without unit");
		Assert.assertEquals(currentElementConfig.getStringFromPath("listPrice"), "");
	}
}
