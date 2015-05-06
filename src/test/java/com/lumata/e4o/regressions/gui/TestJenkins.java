package com.lumata.e4o.regressions.gui;

import javax.mail.MessagingException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.network.Mail;
import com.lumata.common.testing.network.MailClient;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;

// configuration in case to use pod51015.outlook.com mail server
//@Mail(
//	protocol = "smtp",
//	fromRecipient = "qa-e4o.all@lumatagroup.com",
//	toRecipients = { "arcangelo.dipasquale@lumatagroup.com" },
//	host = "pod51015.outlook.com",
//	port = 587,
//	starttlsEnabled = true,
//	authorizationRequired = true,
//	user = "arcangelo.dipasquale@lumatagroup.com",
//	password = ""
//)
@Mail(
	protocol = "smtp",
	fromRecipient = "qa.e4o.all@lumatagroup.com",
	toRecipients = { "arcangelo.dipasquale@lumatagroup.com", "mauro.ripamonti@lumatagroup.com" },
	host = "internal.mailservices.lumata.int",
	port = 25,
	starttlsEnabled = false,
	authorizationRequired = false,
	user = "",
	password = ""
)
public class TestJenkins {

	private final String SELENIUM_WEB_DRIVER_PARAMS = "{ 'type':'local', 'local': { 'browserName':'firefox', 'firefox': {} }, 'remote': { 'selenium_hub':'http://ci.lumata.int/wd/hub', 'capability': { 'platform': 'LINUX', 'browserName': 'chrome' } } }";
	
	SeleniumWebDriver seleniumWebDriver;
	
	@Parameters("seleniumWebDriverParams")
	@Test( enabled=false, priority = 1 )
	public void checkSeleniumWebDriverFromJenkins( @Optional( SELENIUM_WEB_DRIVER_PARAMS ) String seleniumWebDriverParams ) throws FormException, JSONException, JSONSException {
		
		JSONObject jo = new JSONObject( seleniumWebDriverParams );
		
		seleniumWebDriver = SeleniumWebDriver.getInstance( jo );
				
		seleniumWebDriver.openBrowser( "http://www.google.com" );
		
		seleniumWebDriver.getWrappedDriver().findElement( By.id( "lst-ib" ) ).sendKeys( "jenkins test" );
		
	}

	@Test( enabled=false, priority = 2 )
	public void checkMailSendingFromJenkins() throws FormException, JSONException, JSONSException {
		
		try {
			
			Mail mail = this.getClass().getAnnotation( Mail.class );
			
			MailClient.getInstance( mail ).send( "subject: test mail sending from jenkins" , "test mail sending from jenkins" );
			
		} catch( MessagingException e ) {}

	}
	
	@Test( enabled=true, priority = 3 )
	public void generateResultReport() {
		
		Assert.assertTrue( false );
		
	}
		
	@AfterSuite
	public void end() {
				
		if( null!= seleniumWebDriver ) { seleniumWebDriver.close(); }		
	
	}
		
}
