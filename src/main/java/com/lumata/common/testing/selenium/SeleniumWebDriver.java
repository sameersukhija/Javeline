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

import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Browser;
import com.opera.core.systems.OperaDriver;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

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

	public SeleniumWebDriver ( Browser browser, String baseUrl ) {
		
		super( getLocalWebDriver( browser ), baseUrl );
		
	}

	@Deprecated
	public SeleniumWebDriver ( String browser, JSONObject browserProfile, String baseUrl ) {
		
		super(getLocalWebDriver( browser, browserProfile ), baseUrl);
		
	}
	
	public SeleniumWebDriver ( BrowserType baseBrowser, String baseUrl, String baseHubAddress ) {
		
		super(getRemoteWebDriver( baseBrowser, baseHubAddress ), baseUrl);
		
	}

	/**
	 * This method returns "Webdrive" object according passed "Browser" object 
	 *
	 * @param browser
	 * 
	 * @return an istance of WebDriver object
	 */
	public static WebDriver getLocalWebDriver( Browser browser ) {
		
//		switch( (Browser.Type)browser.getType() ) {
//
//			case chrome: { return new ChromeDriver(); }
//			case ie: { return new InternetExplorerDriver(); }
//			case firefox: {
//					
//				/**
//				 * START - REFACTORING FIREFOX PROFILE LOADING
//				 */
//				
//				FirefoxProfile profile = new FirefoxProfile();
//			
//				try {
//				
//					if( browser.getProfile() != null ) {
//					
//						if( browser.getFile() != null ) {
//							
//							StringBuilder path = new StringBuilder();
//							
//							if( browser.getFileLoadingType().equals( IOFileUtils.IOLoadingType.RESOURCE ) ) {
//								
//								path.append( System.getProperty( "user.dir" ) ).append( "/src/main/resources/" );
//								
//							} 
//							
//							path.append( IOFileUtils.buildPath( browser.getFileFolderName(), browser.getFileName()) );
//							
//							profile = new FirefoxProfile( new File( path.toString() ) ); 
//							
//							logger.debug( Log.LOADING.createMessage( "Firefox profile ( " + browser.getProfile() + " )" ) );
//							
//							if( browser.getOptions() != null ) {
//								
//								@SuppressWarnings("unchecked")
//								Iterator<String> keys = browser.getOptions().keys();
//								while( keys.hasNext() ) {
//							        
//									try {
//										
//										String key = keys.next().toString();
//	   	
//							        	profile.setPreference( key, browser.getOptions().getString(key) );
//							        	
//							        	logger.debug( Log.LOADING.createMessage( "Firefox profile option ( " + key + " )" ) );
//							        	
//							        } catch (JSONException e) {
//							            
//							        	logger.error( e.getMessage(), e );
//							        	
//							        }
//									
//							    }
//								
//							}
//						
//						}
//						
//					}
//					
//				} catch( Exception e ) {
//					
//					logger.error( e.getMessage(), e );
//					
//				}
//				
//				return new FirefoxDriver( profile );
//
//				/**
//				 * STOP - REFACTORING FIREFOX PROFILE LOADING
//				 */				
//				
//			}
//			case opera: { return new OperaDriver(); }
//			case safari: { return new SafariDriver(); }
//			
//		}
		
		return getLocalWebDriver(browser.getType().toString(), browser.getProfile());
		
	}
	
	/**
	 * This method returns "Webdrive" object according passed "Browser" object anc browserProfile string
	 * 
	 * @param browser
	 * @param browserProfile
	 * 
	 * @return an istance of WebDriver object
	 */
	public static WebDriver getLocalWebDriver( String browser, JSONObject browserProfile ) {
		
		WebDriver resp = null;
		
		switch( BrowserType.valueOf( browser.toUpperCase() ) ) {
		
			case CHROME: { resp = new ChromeDriver(); break; }
			case FIREFOX: { 
								
				/**
				 * START - REFACTORING FIREFOX PROFILE LOADING
				 */
				
				FirefoxProfile profile = null;
				
				try {				
					
					if( browserProfile != null ) {
						
						JSONObject browserProfileInfo = browserProfile.getJSONObject( "profile" );

						if( !browserProfileInfo.isNull("file") ) { 
							
							JSONObject browserProfileFileInfo = browserProfileInfo.getJSONObject( "file" );
							
							StringBuilder path = new StringBuilder();
									
							if( IOFileUtils.IOLoadingType.valueOf( browserProfileFileInfo.getString("loading_type").toUpperCase() ).equals( IOFileUtils.IOLoadingType.RESOURCE ) ) {
								
								path.append( System.getProperty( "user.dir" ) ).append( "/src/main/resources/" );
								
							}
							
							path.append( IOFileUtils.buildPath( browserProfileFileInfo.getString("folder_name"), browserProfileFileInfo.getString("file_name") ) );
							
							profile = new FirefoxProfile( new File( path.toString() ) ); 
							
							logger.debug( Log.LOADING.createMessage( "Firefox profile ( " + path.toString() + " )" ) );
						
						}
					
						if( !browserProfileInfo.isNull("options") ) {
							
							JSONObject profileOpts = browserProfileInfo.getJSONObject("options");
							
							@SuppressWarnings("unchecked")
							Iterator<String> keys = profileOpts.keys();
							while( keys.hasNext() ) {
						        
								try {
									
									String key = keys.next().toString();
   	
						        	profile.setPreference( key, profileOpts.getString(key) );
						        	
						        	logger.debug( Log.LOADING.createMessage( "Firefox profile option ( " + key + " )" ) );
						        	
						        } catch (JSONException e) {
						            
						        	logger.error( e.getMessage(), e );
						        	
						        }
								
						    }
						    
						}
						
					}
					
				} catch( Exception e ) {
					
					logger.error( e.getMessage(), e );
					
				}

			/**
			 * STOP - REFACTORING FIREFOX PROFILE LOADING
			 */

				if ( profile != null )
					resp = new FirefoxDriver(profile);
				else
					resp = new FirefoxDriver();
				
				break;
			}
			case IE: { resp = new InternetExplorerDriver(); break; }
			case OPERA: { resp = new OperaDriver(); break; }
			case SAFARI: { resp = new SafariDriver(); break; }
			default: { resp = new HtmlUnitDriver(true); break; }
		}

		return resp;
	}
	
	public static WebDriver getRemoteWebDriver( BrowserType browserType, String baseHubAddress ) {
		
		URL remoteHubAddress = null;
		
		try {
			
			remoteHubAddress = new URL(baseHubAddress);
		
		} catch( MalformedURLException e ) {
			
			logger.error( e.getMessage(), e );
			
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
