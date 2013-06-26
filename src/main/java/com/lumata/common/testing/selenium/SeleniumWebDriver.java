package com.lumata.common.testing.selenium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opera.core.systems.OperaDriver;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class SeleniumWebDriver extends WebDriverBackedSelenium {

	private static final  Logger logger = LoggerFactory.getLogger( SeleniumWebDriver.class );
	
	private Map<String, Object> cache = new HashMap<String, Object>();
	private String testName = "";
	
	public static enum BrowserType {
		CHROME,
        FIREFOX,
        IE,
        OPERA,
        SAFARI;			
	}
	
	public SeleniumWebDriver ( String browser, JSONObject browserProfile, String baseUrl ) {
		
		super(getLocalWebDriver( browser, browserProfile ), baseUrl);
		
	}
	
	public SeleniumWebDriver ( BrowserType baseBrowser, String baseUrl, String baseHubAddress ) {
		
		super(getRemoteWebDriver( baseBrowser, baseHubAddress ), baseUrl);
		
	}

	public static WebDriver getLocalWebDriver( String browser, JSONObject browserProfile ) {
		
		 
		
		switch( BrowserType.valueOf( browser ) ) {
		
			case CHROME: { return new ChromeDriver(); }
			case FIREFOX: { 
				
				FirefoxProfile profile = new FirefoxProfile();
				
				try {				
					
					if( browserProfile != null ) {
						
						if( !browserProfile.isNull( "file") ) profile = new FirefoxProfile( new File(browserProfile.getString("file")));
						
						if( !browserProfile.isNull( "options") ) {
							
							JSONObject profileOpts = browserProfile.getJSONObject("options");
							
							Iterator<String> keys = profileOpts.keys();
							while( keys.hasNext() ) {
						        
								try {
									
									String key = keys.next().toString();
   	
						        	profile.setPreference( key, profileOpts.getString(key) );
						        	
						        } catch (JSONException e) {
						            
						        	logger.error( e.getMessage() );
						        	
						        	e.printStackTrace();
						        	
						        }
								
						    }
						    
						}
						
					}
					
				} catch( Exception e ) {
					
					logger.error( e.getMessage() );
					
					e.printStackTrace();
					
				}
				
				return new FirefoxDriver( profile );
			}
			case IE: { return new InternetExplorerDriver(); }
			case OPERA: { return new OperaDriver(); }
			case SAFARI: { return new SafariDriver(); }
			default: return new HtmlUnitDriver(true);
			
		}
		
	}
	
	public static WebDriver getRemoteWebDriver( BrowserType browserType, String baseHubAddress ) {
		
		URL remoteHubAddress = null;
		
		try {
			
			remoteHubAddress = new URL(baseHubAddress);
		
		} catch( MalformedURLException e ) {
			
			logger.error( e.getMessage() );
			
			e.printStackTrace();
			
		}
		
		switch( browserType ) {
		
			case CHROME: { return new RemoteWebDriver(remoteHubAddress, DesiredCapabilities.chrome()); }
			case FIREFOX: { return new RemoteWebDriver(remoteHubAddress, DesiredCapabilities.firefox()); }
			case IE: { return new RemoteWebDriver(remoteHubAddress, DesiredCapabilities.internetExplorer()); }
			case OPERA: { return new RemoteWebDriver(remoteHubAddress, DesiredCapabilities.opera()); }
			case SAFARI: { return new RemoteWebDriver(remoteHubAddress, DesiredCapabilities.safari()); }
			default: return new RemoteWebDriver(remoteHubAddress, DesiredCapabilities.htmlUnit());
			
		}
		
	}	
	
	public Object getCacheValue( Object key ) {
		return cache.get(key);		
	}
	
	public void setCacheValue( String key, Object value ) {
		cache.put(key, value);		
	}
	
	public String getTestName() {
		return this.testName;		
	}
	
	public void setTestName( String testName ) {
		this.testName = testName;		
	}
	
}
