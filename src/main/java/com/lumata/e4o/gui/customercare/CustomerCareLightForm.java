package com.lumata.e4o.gui.customercare;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.Security;
import com.lumata.common.testing.system.User;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.Form;

public class CustomerCareLightForm extends Form {

	private final String link = "angular/customerCare/index.html?lang=ENG&login=${login}&pwd=${password}&chrome=1#/${ccTab}/${msisdn}";
	private String url;	
	
	public enum CustomerCareLightFormTab { Tokens, Loyalty }
	
	public enum CustomerCareLightRestTab { Token, Loyalty }
	
	private String currentFormTab;
	
	public CustomerCareLightForm( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super(selenium, timeout, interval);
			
	}

	public CustomerCareLightForm open() {
		
		selenium.open( url );
		
		waitForPageLoading();
		
		return this;
		
	}
	
	public CustomerCareLightForm buildUrl( User user, String msisdn, CustomerCareLightFormTab ccTab ) {
		
		url = link.				
				replace( "${login}", ( null != user.getUsername() ? user.getUsername() : "" ) ).
				replace( "${password}", ( null != user.getPassword() ? Security.decrypt( user.getPassword() ) : "" ) ).
				replace( "${msisdn}", ( null != msisdn ? msisdn : "" ) ).
				replace( "${ccTab}", ( null != ccTab ? ccTab.toString().toLowerCase() : "" ) );	
		
		currentFormTab = ccTab.toString().toLowerCase();
		System.out.println( url );
		return this;
		
	}
	
	public Boolean isAuthenticated() throws FormException {
		
		String authenticationSuccessXPath = "//p[@class='ng-hide' and @ng-show='notAuthenticated' and contains(text(), 'Authentication error, please try again with different credentials' )]";
		
		searchByXPath( authenticationSuccessXPath );
			
		return true;
						
	}
	
	public Boolean isNotAuthenticated() throws FormException {
		
		String authenticationFailureXPath = "//p[@class='' and @ng-show='notAuthenticated' and contains(text(), 'Authentication error, please try again with different credentials' )]";
		
		searchByXPath( authenticationFailureXPath );
			
		return true;
						
	}
	
	public Boolean isResultNotFound() throws FormException {
		
		String resultNotFoundXPath = "//span[@key='actrule-customerCare-" + currentFormTab + "-noResultsFound' and contains(text(), 'No results found')]/ancestor::div[@class='e4ol-section' and @ng-show='" + currentFormTab + " == undefined']";
		
		searchByXPath( resultNotFoundXPath );
			
		return true;
						
	}
	
	public Boolean isResultFound() throws FormException {
		
		String resultFoundXPath = "//div[@class='e4ol-section' and @ng-show='" + currentFormTab + " != undefined']";
		
		searchByXPath( resultFoundXPath );
			
		return true;
						
	}
	
	public CustomerCareLightForm list() throws FormException {
		
		String tokensListXPath = "//div[@ng-show='tokens != undefined']";
		String tokensListContentXPath = "//div[@ng-class=\"{'e4ol-list__row--highlight': token.id == offerToken.id}\"]";
		
		List<WebElement> we = searchListByXPath( tokensListXPath, tokensListContentXPath );
		
		for( int w = 0; w < we.size(); w++ ) {
			
			List<WebElement> weCell = we.get( w ).findElements( By.xpath( "//div[@class='e4ol-list__cell e4ol-list__cell--text ng-binding']" ) );
			
			for( int wc = 0; wc < weCell.size(); wc++ ) {
				
				System.out.print( weCell.get( wc ).getText() );
				System.out.print( " | " );
							
			}
			
			System.out.println( " " );	
			
		}
		
		return this;
		
	}

}
