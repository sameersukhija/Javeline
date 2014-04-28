package com.lumata.interview;

import junit.framework.Assert;
import static junit.framework.Assert.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.generators.container.Agencies;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
//import com.lumata.expression.operators.entities.Agencies;

public class Interview {
	
	@Test( priority = 1, enabled = false )
	public void test1() throws InterruptedException {
		
		Short forced = 4;
		
		// 1 - create Agencies instance
		Agencies dut = new Agencies();
		
		// 2 - set id, name, address, phone
		dut.setId(forced);
		dut.setName("Vincenzo");
		dut.setAddress("Building B");
		dut.setPhone("0112345567");
		
		// 3 - check the id is equals to the previous you have set ( use assertTrue method )
		Short obtained = dut.getId();
		assertTrue("Abbiamo perso il numero, atteso "+forced+ " ottenuto "+obtained, forced.equals(obtained) );
						
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
	@Test( priority = 3, enabled = true )
	public void test3( @Optional("FIREFOX") String browser, @Optional("E4O_QA2") String environment, @Optional("tenant") String tenant, @Optional("superman") String user ) throws InterruptedException, EnvironmentException {
		
		// 1 - create Selenium WebDriver instance SeleniumWebDriver( browser, null, <link> )
		//     * link = www.google.com
		SeleniumWebDriver driver = new SeleniumWebDriver(browser, null, "http://www.google.com");
		driver.open("/");

		// 2 - check for search bar displayed using SeleniumUtils.findForComponentDisplayed(SeleniumWebDriver, SeleniumUtils.SearchBy, <value> ) and type "Lumata" if the webelement is not null
		

		// 3 - check for search button displayed and click it

				
	}
	
}
