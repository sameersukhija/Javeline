package com.lumata.common.testing.plan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.ResourcePropertiesException;
import com.lumata.common.testing.system.ResourceProperties;
import com.lumata.common.testing.validating.Format;
import com.lumata.common.testing.validating.Format.Operators;
import com.lumata.common.testing.validating.Validator;

public class TestValidator {
	
	private static final  Logger logger = LoggerFactory.getLogger( TestValidator.class );
	  
	
	
	/*
	@BeforeClass
	public void init() throws ResourcePropertiesException {		
		
		try {
			JSONObject validate = new JSONObject("{ \"validationType\": \"MAP\", \"validationParam\": \"\", \"validationFormat\": \"TEST_MAP\", \"validationFilter\": \"\", \"validationOp\": \"\", \"validationAllowBlank\": false, \"validationExpected\": true }");
			validator.put(validate);
			validate = new JSONObject("{ \"validationType\": \"DATE\", \"validationParam\": \"\", \"validationFormat\": \"yyyy-mm-dd\", \"validationFilter\": \"\", \"validationOp\": \"\", \"validationAllowBlank\": false, \"validationExpected\": true }");
			validator.put(validate);
			validate = new JSONObject("{ \"validationType\": \"MSISDN\", \"validationParam\": \"\", \"validationFormat\": \"[0-9]+\", \"validationFilter\": \"[ ()+-]\", \"validationOp\": \"\", \"validationAllowBlank\": false, \"validationExpected\": true }");
			validator.put(validate);
			validate = new JSONObject("{ \"validationType\": \"LENGHT\", \"validationParam\": \"4\", \"validationFormat\": \"\", \"validationFilter\": \"\", \"validationOp\": \"LESS_OR_EQUAL_THAN\", \"validationAllowBlank\": false, \"validationExpected\": true }");
			validator.put(validate);
			validate = new JSONObject("{ \"validationType\": \"NONE\", \"validationParam\": \"\", \"validationFormat\": \"\", \"validationFilter\": \"\", \"validationOp\": \"\", \"validationAllowBlank\": false, \"validationExpected\": true }");
			validator.put(validate);
			validate = new JSONObject("{ \"validationType\": \"ENUM\", \"validationParam\": \"\", \"validationFormat\": \"com.lumata.common.testing.selenium.SeleniumWebDriver$BrowserType\", \"validationFilter\": \"\", \"validationOp\": \"\", \"validationAllowBlank\": false, \"validationExpected\": true }");
			validator.put(validate);
			validate = new JSONObject("{ \"validationType\": \"EMAIL\", \"validationParam\": \"\", \"validationFormat\": \"^[\\w\\.-]+@([\\w\\-]+\\.)+[a-zA-Z]{2,4}$\", \"validationFilter\": \"\", \"validationOp\": \"\", \"validationAllowBlank\": false, \"validationExpected\": true }");
			validator.put(validate);			
		} catch( JSONException je ) {
			logger.error(je.getMessage());
		}
		
	}
	*/
	
	@Test()
	public void getValidation_1() {		
		try {
			JSONArray validator = new JSONArray();
			JSONObject validate = new JSONObject("{ \"validationType\": \"MAP\", \"validationParam\": \"\", \"validationFormat\": \"TEST_MAP\", \"validationFilter\": \"\", \"validationOp\": \"\", \"validationAllowBlank\": false, \"validationExpected\": true }");
			validator.put(validate);
			Validator val = new Validator( validator );
			TestMap tm = new TestMap();
			Assert.assertTrue( val.validation("key_1", tm) );
		} catch( JSONException je ) {
			logger.error(je.getMessage());
		}
	}
	
	@Test()
	public void getValidation_2() {		
		try {
			JSONArray validator = new JSONArray();
			JSONObject validate = new JSONObject("{ \"validationType\": \"MAP\", \"validationParam\": \"\", \"validationFormat\": \"TEST_MAP\", \"validationFilter\": \"\", \"validationOp\": \"\", \"validationAllowBlank\": false, \"validationExpected\": true }");
			validator.put(validate);
			Validator val = new Validator( validator );
			TestMap tm = new TestMap();
			Assert.assertFalse( val.validation("key_wrong", tm) );
		} catch( JSONException je ) {
			logger.error(je.getMessage());
		}
	}
	
	/** Negative test using validationExpected: false */
	@Test()
	public void getValidation_3() {		
		try {
			JSONArray validator = new JSONArray();
			JSONObject validate = new JSONObject("{ \"validationType\": \"MAP\", \"validationParam\": \"\", \"validationFormat\": \"TEST_MAP\", \"validationFilter\": \"\", \"validationOp\": \"\", \"validationAllowBlank\": false, \"validationExpected\": false }");
			validator.put(validate);
			Validator val = new Validator( validator );
			TestMap tm = new TestMap();
			Assert.assertTrue( val.validation("key_wrong", tm) );
		} catch( JSONException je ) {
			logger.error(je.getMessage());
		}
	}
	
	@Test()
	public void getValidation_4() {		
		try {
			JSONArray validator = new JSONArray();
			JSONObject validate = new JSONObject("{ \"validationType\": \"DATE\", \"validationParam\": \"\", \"validationFormat\": \"yyyy-mm-dd\", \"validationFilter\": \"\", \"validationOp\": \"\", \"validationAllowBlank\": false, \"validationExpected\": true }");
			validator.put(validate);
			Validator val = new Validator( validator );
			Assert.assertTrue( val.validation("2013-05-02", new Object()) );
		} catch( JSONException je ) {
			logger.error(je.getMessage());
		}
	}
	
	@Test()
	public void getValidation_5() {		
		try {
			JSONArray validator = new JSONArray();
			JSONObject validate = new JSONObject("{ \"validationType\": \"DATE\", \"validationParam\": \"\", \"validationFormat\": \"yyyy-mm-dd\", \"validationFilter\": \"\", \"validationOp\": \"\", \"validationAllowBlank\": false, \"validationExpected\": true }");
			validator.put(validate);
			Validator val = new Validator( validator );
			Assert.assertFalse( val.validation("2013/05/02", new Object()) );
		} catch( JSONException je ) {
			logger.error(je.getMessage());
		}
	}
	
	/** Negative test using validationExpected: false */
	@Test()
	public void getValidation_6() {		
		try {
			JSONArray validator = new JSONArray();
			JSONObject validate = new JSONObject("{ \"validationType\": \"DATE\", \"validationParam\": \"\", \"validationFormat\": \"yyyy-mm-dd\", \"validationFilter\": \"\", \"validationOp\": \"\", \"validationAllowBlank\": false, \"validationExpected\": false }");
			validator.put(validate);
			Validator val = new Validator( validator );
			Assert.assertTrue( val.validation("2013/05/02", new Object()) );
		} catch( JSONException je ) {
			logger.error(je.getMessage());
		}
	}
	
	@Test()
	public void getValidation_7() {		
		try {
			JSONArray validator = new JSONArray();
			JSONObject validate = new JSONObject("{ \"validationType\": \"MSISDN\", \"validationParam\": \"\", \"validationFormat\": \"[0-9]+\", \"validationFilter\": \"[ ()+-]\", \"validationOp\": \"\", \"validationAllowBlank\": false, \"validationExpected\": true }");
			validator.put(validate);
			validate = new JSONObject("{ \"validationType\": \"LENGHT\", \"validationParam\": \"4\", \"validationFormat\": \"\", \"validationFilter\": \"\", \"validationOp\": \"LESS_OR_EQUAL_THAN\", \"validationAllowBlank\": false, \"validationExpected\": true }");
			validator.put(validate);
			Validator val = new Validator( validator );
			Assert.assertTrue( val.validation("1234", new Object()) );
		} catch( JSONException je ) {
			logger.error(je.getMessage());
		}
	}
	
	@Test()
	public void getValidation_8() {		
		try {
			JSONArray validator = new JSONArray();
			JSONObject validate = new JSONObject("{ \"validationType\": \"MSISDN\", \"validationParam\": \"\", \"validationFormat\": \"[0-9]+\", \"validationFilter\": \"[ ()+-]\", \"validationOp\": \"\", \"validationAllowBlank\": false, \"validationExpected\": true }");
			validator.put(validate);
			validate = new JSONObject("{ \"validationType\": \"LENGHT\", \"validationParam\": \"9\", \"validationFormat\": \"\", \"validationFilter\": \"\", \"validationOp\": \"LESS_OR_EQUAL_THAN\", \"validationAllowBlank\": false, \"validationExpected\": true }");
			validator.put(validate);
			Validator val = new Validator( validator );
			Assert.assertTrue( val.validation("+-(1) 234", new Object()) );
		} catch( JSONException je ) {
			logger.error(je.getMessage());
		}
	}
	
	@Test()
	public void getValidation_9() {		
		try {
			JSONArray validator = new JSONArray();
			JSONObject validate = new JSONObject("{ \"validationType\": \"MSISDN\", \"validationParam\": \"\", \"validationFormat\": \"[0-9]+\", \"validationFilter\": \"[ ()+-]\", \"validationOp\": \"\", \"validationAllowBlank\": false, \"validationExpected\": true }");
			validator.put(validate);
			validate = new JSONObject("{ \"validationType\": \"LENGHT\", \"validationParam\": \"4\", \"validationFormat\": \"\", \"validationFilter\": \"\", \"validationOp\": \"LESS_OR_EQUAL_THAN\", \"validationAllowBlank\": false, \"validationExpected\": true }");
			validator.put(validate);
			Validator val = new Validator( validator );
			Assert.assertFalse( val.validation("£$%12=34", new Object()) );
		} catch( JSONException je ) {
			logger.error(je.getMessage());
		}
	}
	
	@Test()
	public void getValidation_10() {		
		try {
			JSONArray validator = new JSONArray();
			JSONObject validate = new JSONObject("{ \"validationType\": \"MSISDN\", \"validationParam\": \"\", \"validationFormat\": \"[0-9]+\", \"validationFilter\": \"[ ()+-]\", \"validationOp\": \"\", \"validationAllowBlank\": false, \"validationExpected\": true }");
			validator.put(validate);
			validate = new JSONObject("{ \"validationType\": \"LENGHT\", \"validationParam\": \"4\", \"validationFormat\": \"\", \"validationFilter\": \"\", \"validationOp\": \"LESS_OR_EQUAL_THAN\", \"validationAllowBlank\": false, \"validationExpected\": true }");
			validator.put(validate);
			Validator val = new Validator( validator );
			Assert.assertFalse( val.validation("12345", new Object()) );
		} catch( JSONException je ) {
			logger.error(je.getMessage());
		}
	}
	
	/** Negative test using validationExpected: false */
	@Test()
	public void getValidation_11() {		
		try {
			JSONArray validator = new JSONArray();
			JSONObject validate = new JSONObject("{ \"validationType\": \"MSISDN\", \"validationParam\": \"\", \"validationFormat\": \"[0-9]+\", \"validationFilter\": \"[ ()+-]\", \"validationOp\": \"\", \"validationAllowBlank\": false, \"validationExpected\": false }");
			validator.put(validate);
			validate = new JSONObject("{ \"validationType\": \"LENGHT\", \"validationParam\": \"4\", \"validationFormat\": \"\", \"validationFilter\": \"\", \"validationOp\": \"LESS_OR_EQUAL_THAN\", \"validationAllowBlank\": false, \"validationExpected\": false }");
			validator.put(validate);
			Validator val = new Validator( validator );
			Assert.assertTrue( val.validation("£$%12=34", new Object()) );
		} catch( JSONException je ) {
			logger.error(je.getMessage());
		}
	}
	
	/** Any validation */
	@Test()
	public void getValidation_12() {		
		try {
			JSONArray validator = new JSONArray();
			JSONObject validate = new JSONObject("{ \"validationType\": \"NONE\", \"validationParam\": \"\", \"validationFormat\": \"\", \"validationFilter\": \"\", \"validationOp\": \"\", \"validationAllowBlank\": false, \"validationExpected\": true }");
			validator.put(validate);			
			Validator val = new Validator( validator );
			Assert.assertTrue( val.validation(null, null) );
		} catch( JSONException je ) {
			logger.error(je.getMessage());
		}
	}
	
	@Test()
	public void getValidation_13() {		
		try {
			JSONArray validator = new JSONArray();
			JSONObject validate = new JSONObject("{ \"validationType\": \"ENUM\", \"validationParam\": \"\", \"validationFormat\": \"com.lumata.common.testing.system.Browser$Type\", \"validationFilter\": \"\", \"validationOp\": \"\", \"validationAllowBlank\": false, \"validationExpected\": true }");
			validator.put(validate);			
			Validator val = new Validator( validator );
			Assert.assertTrue( val.validation("firefox", new Object()) );
		} catch( JSONException je ) {
			logger.error(je.getMessage());
		}
	}
	
	@Test()
	public void getValidation_14() {		
		try {
			JSONArray validator = new JSONArray();
			JSONObject validate = new JSONObject("{ \"validationType\": \"ENUM\", \"validationParam\": \"\", \"validationFormat\": \"com.lumata.common.testing.selenium.SeleniumWebDriver$BrowserType\", \"validationFilter\": \"\", \"validationOp\": \"\", \"validationAllowBlank\": false, \"validationExpected\": true }");
			validator.put(validate);			
			Validator val = new Validator( validator );
			Assert.assertFalse( val.validation("PIPPO", new Object()) );
		} catch( JSONException je ) {
			logger.error(je.getMessage());
		}
	}
	
	/** Negative test using validationExpected: false */
	@Test()
	public void getValidation_15() {		
		try {
			JSONArray validator = new JSONArray();
			JSONObject validate = new JSONObject("{ \"validationType\": \"ENUM\", \"validationParam\": \"\", \"validationFormat\": \"com.lumata.common.testing.selenium.SeleniumWebDriver$BrowserType\", \"validationFilter\": \"\", \"validationOp\": \"\", \"validationAllowBlank\": false, \"validationExpected\": false }");
			validator.put(validate);			
			Validator val = new Validator( validator );
			Assert.assertTrue( val.validation("PIPPO", new Object()) );
		} catch( JSONException je ) {
			logger.error(je.getMessage());
		}
	}
	
	@Test()
	public void getValidation_16() {		
		try {
			JSONArray validator = new JSONArray();
			JSONObject validate = new JSONObject("{ \"validationType\": \"EMAIL\", \"validationParam\": \"\", \"validationFormat\": \"^[\\\\w\\\\.-]+@([\\\\w\\\\-]+\\\\.)+[a-zA-Z]{2,4}$\", \"validationFilter\": \"\", \"validationOp\": \"\", \"validationAllowBlank\": false, \"validationExpected\": true }");
			validator.put(validate);
			Validator val = new Validator( validator );
			Assert.assertTrue( val.validation("arcangelo.dipasquale@lumatagroup.com", new Object()) );
		} catch( JSONException je ) {
			logger.error(je.getMessage());
		}
	}
	
	@Test()
	public void getValidation_17() {		
		try {
			JSONArray validator = new JSONArray();
			JSONObject validate = new JSONObject("{ \"validationType\": \"EMAIL\", \"validationParam\": \"\", \"validationFormat\": \"^[\\\\w\\\\.-]+@([\\\\w\\\\-]+\\\\.)+[a-zA-Z]{2,4}$\", \"validationFilter\": \"\", \"validationOp\": \"\", \"validationAllowBlank\": false, \"validationExpected\": true }");
			validator.put(validate);			
			Validator val = new Validator( validator );
			Assert.assertFalse( val.validation("arcangelo.dipasquale@lumatagroup", new Object()) );
		} catch( JSONException je ) {
			logger.error(je.getMessage());
		}
	}
	
	/** Negative test using validationExpected: false */
	@Test()
	public void getValidation_18() {		
		try {
			JSONArray validator = new JSONArray();
			JSONObject validate = new JSONObject("{ \"validationType\": \"EMAIL\", \"validationParam\": \"\", \"validationFormat\": \"^[\\\\w\\\\.-]+@([\\\\w\\\\-]+\\\\.)+[a-zA-Z]{2,4}$\", \"validationFilter\": \"\", \"validationOp\": \"\", \"validationAllowBlank\": false, \"validationExpected\": false }");
			validator.put(validate);			
			Validator val = new Validator( validator );
			Assert.assertTrue( val.validation("@lumatagroup.com", new Object()) );
		} catch( JSONException je ) {
			logger.error(je.getMessage());
		}
	}
	
}
