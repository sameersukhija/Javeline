package com.lumata.e4o.regressions.gui;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;

@TCOwners(
	@TCOwner( name="Arcangelo Di Pasquale", email="arcangelo.dipasquale@lumatagroup.com" )
)
public class TestJenkins {

	SeleniumWebDriver seleniumWebDriver;
	
	@Parameters("seleniumWebDriverParams")
	@Test( enabled=true, priority = 1 )
	public void checkMandatoryFields1( @Optional("") String seleniumWebDriverParams ) throws FormException, JSONException, JSONSException {
		
		JSONObject jo = new JSONObject( seleniumWebDriverParams );
		
		seleniumWebDriver = SeleniumWebDriver.getInstance( jo );
				
		seleniumWebDriver.openBrowser( "http://www.google.com" );
		
		seleniumWebDriver.getWrappedDriver().findElement( By.id( "lst-ib" ) ).sendKeys( "jenkins test" );
		
	}
	
	@AfterSuite
	public void end() {
		seleniumWebDriver.close();		
	}
		
}
