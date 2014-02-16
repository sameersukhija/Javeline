package com.lumata.expression.solutions;

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
		Agencies agencies = new Agencies();
		
		// 2 - set id, name, address, phone
		agencies.setId((short)1);
		agencies.setName("e");
		agencies.setAddress("s");
		agencies.setPhone("111");
		
		// 3 - check the id is equals to the previous you have set ( use assertTrue method )
		Assert.assertTrue( agencies.getId().equals( (short)1 ) );
						
	}
	
	@Test( priority = 2, enabled = true )
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
		JSONObject json = new JSONObject();
		JSONObject prices = new JSONObject();
		JSONArray channels = new JSONArray();
		
		try {
			
			channels.put( "Ch A" );
			channels.put( "Ch B" );
			
			prices.put( "type" , "IPhone" );
			prices.put( "channels" , channels );
			
			json.put( "prices" , prices );
			
		} catch (JSONException e) {			
			e.printStackTrace();			
		}
				
		// 2 - print the JSONObject
		System.out.println( json.toString() );
			
		// 3 - get and iterate the channels array 
		try {
			
			JSONArray ch = json.getJSONObject( "prices" ).getJSONArray( "channels" );
		
			System.out.println( "Channels" );
			
			for( int i = 0; i < ch.length(); i++ ) {
				
				System.out.println( ch.get( i ) );
				
			}
		
		} catch (JSONException e) {
			e.printStackTrace();
		}						
		
	}
	
	@Parameters({"browser", "environment", "tenant", "user"})
	@Test( priority = 3, enabled = false )
	public void test3( @Optional("FIREFOX") String browser, @Optional("E4O_QA2") String environment, @Optional("tenant") String tenant, @Optional("superman") String user ) throws InterruptedException, EnvironmentException {
		
		// 1 - create Selenium WebDriver instance SeleniumWebDriver( browser, null, <link> )
		//     * link = www.google.com
		SeleniumWebDriver seleniumWebDriver = new SeleniumWebDriver( browser, null, "http://www.google.com" );
		seleniumWebDriver.open( "/" );
		
		// 2 - check for search bar displayed using SeleniumUtils.findForComponentDisplayed(SeleniumWebDriver, SeleniumUtils.SearchBy, <value> ) and type "Lumata" if the webelement is not null
		WebElement searchField = SeleniumUtils.findForComponentDisplayed(seleniumWebDriver, SeleniumUtils.SearchBy.ID, "gbqfq" );
		if( searchField != null ) { seleniumWebDriver.type( "id=gbqfq" , "Lumata" ); }
		
		// 3 - check for search button displayed and click it
		WebElement searchBtn = SeleniumUtils.findForComponentDisplayed(seleniumWebDriver, SeleniumUtils.SearchBy.ID, "gbqfb" );
		if( searchBtn != null ) { searchBtn.click(); }
				
	}
	
}
