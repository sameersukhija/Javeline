package com.lumata.expression.interview;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.expression.operators.entities.Agencies;

public class Interview {
	
	@Test( priority = 1, enabled = false )
	public void test1() throws InterruptedException {
		
		// 1 - create Agencies instance
		
		
		// 2 - set id, name, address, phone
		
		
		// 3 - check the id is equals to the previous you have set ( use assertTrue method )
		
						
	}
	
	@Test( priority = 2, enabled = false )
	public void test2() throws InterruptedException {
		
		/*
		 { "prices": {
						"type": "IPhone",
						"channels": [
							"Ch A",
							"Ch B"
						]
					}
		} 
		*/
				
		// 1 - create a JSONObject with the same structure like above
		
				
		// 2 - print the JSONObject
		
		
		// 3 - get and iterate the channels array 
								
		
	}
	
	@Parameters({"browser", "environment", "tenant", "user"})
	@Test( priority = 3, enabled = false )
	public void test3( @Optional("FIREFOX") String browser, @Optional("E4O_QA2") String environment, @Optional("tenant") String tenant, @Optional("superman") String user ) throws InterruptedException, EnvironmentException {
		
		// 1 - create Selenium WebDriver instance SeleniumWebDriver( browser, null, <link> )
		//     * link = www.google.com
		

		// 2 - check for search bar displayed using SeleniumUtils.findForComponentDisplayed(SeleniumWebDriver, SeleniumUtils.SearchBy, <value> ) and type "Lumata" if the webelement is not null
		

		// 3 - check for search button displayed and click it

				
	}
	
}
