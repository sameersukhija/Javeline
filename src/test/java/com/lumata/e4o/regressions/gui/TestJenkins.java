package com.lumata.e4o.regressions.gui;

import javax.mail.MessagingException;

import org.json.JSONException;
import org.json.JSONObject;
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
import com.lumata.e4o.testing.common.TCOwner;
import com.lumata.e4o.testing.common.TCOwners;

@TCOwners(
	@TCOwner( name="Arcangelo Di Pasquale", email="arcangelo.dipasquale@lumatagroup.com" )
)
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

	SeleniumWebDriver seleniumWebDriver;
	
	@Parameters("seleniumWebDriverParams")
	@Test( enabled=false, priority = 1 )
	public void checkSeleniumWebDriverFromJenkins( @Optional("") String seleniumWebDriverParams ) throws FormException, JSONException, JSONSException {
		
		JSONObject jo = new JSONObject( seleniumWebDriverParams );
		
		seleniumWebDriver = SeleniumWebDriver.getInstance( jo );
				
		seleniumWebDriver.openBrowser( "http://www.google.com" );
		
		seleniumWebDriver.getWrappedDriver().findElement( By.id( "lst-ib" ) ).sendKeys( "jenkins test" );
		
	}

	@Test( enabled=true, priority = 2 )
	public void checkMailSendingFromJenkins() throws FormException, JSONException, JSONSException {
		
		try {
			
			Mail mail = this.getClass().getAnnotation( Mail.class );
			
			MailClient.getInstance( mail ).send( "subject" , "test mail sending from jenkins" );
			
		} catch( MessagingException e ) {
			
			System.out.println( e.getMessage() );
			
		}

	}
		
	@AfterSuite
	public void end() {
				
		if( null!= seleniumWebDriver ) { seleniumWebDriver.close(); }		
	
	}
		
}
