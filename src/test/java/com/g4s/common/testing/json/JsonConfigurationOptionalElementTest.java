package com.g4s.common.testing.json;

import junit.framework.Assert;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.g4s.common.testing.exceptions.JSONSException;
import com.g4s.common.testing.json.SampleJsonConfFile4Optional.SampleJsonConfElemOptional;

public class JsonConfigurationOptionalElementTest {
	
	/**
	 * 
	 */
	private SampleJsonConfFile4Optional mainObject = null;
	
	@BeforeSuite
	public void setUp() throws JSONSException {
		
		mainObject = new SampleJsonConfFile4Optional("jsonconfigtest", "sample_optional");
	}	
	
	@Test
	public void readElementWithOptional() throws JSONSException {
		
		Assert.assertEquals( 3, mainObject.getList().size());
		
		mainObject.setCurrentElementById(0);
		
		SampleJsonConfElemOptional elem = mainObject.getOptionalSubElement();
		
		Assert.assertEquals( "value1", elem.getKey1());
		Assert.assertEquals( new Integer(100), elem.getKey2());
		Assert.assertEquals( Boolean.TRUE, elem.getKey3());
		
		mainObject.setCurrentElementById(1);
		
		elem = mainObject.getOptionalSubElement();
		
		Assert.assertEquals( "defaultKey1", elem.getKey1());
		Assert.assertEquals( new Integer(999), elem.getKey2());
		Assert.assertEquals( Boolean.FALSE, elem.getKey3());
		
		mainObject.setCurrentElementById(2);
		
		elem = mainObject.getOptionalSubElement();
		
		Assert.assertEquals( "defaultKey1", elem.getKey1());
		Assert.assertEquals( new Integer(999), elem.getKey2());
		Assert.assertEquals( Boolean.FALSE, elem.getKey3());
	}
}
