package com.lumata.common.testing.selenium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Browser;
import com.lumata.common.testing.system.OperatingSystem;
import com.opera.core.systems.OperaDriver;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class SeleniumWebDriver {

	private static final  Logger logger = LoggerFactory.getLogger( SeleniumWebDriver.class );
	
	private Map<String, Object> cache = new HashMap<String, Object>();
	private String testName = "";
	
	/**
	 * The base URL will affect only "open" method execution.
	 */
	private String driverBaseUrl = "";
	
	public static enum BrowserType {
		CHROME,
        FIREFOX,
        IE,
        OPERA,
        SAFARI,
        PHANTOM,
        HTMLUNIT;			
	}
	
	/**
	 * Instance of local <b>WebDriver</b>
	 */
	private WebDriver instance = null;
	
	/**
	 * It returns the wrapped <b>WebDriver</b>
	 * 
	 * @return a <b>WebDriver</b> object 
	 */
	public WebDriver getWrappedDriver() {
		
		return instance;
	}

	/**
	 * New SeleniumWebDriver instance
	 * 
	 * @param browser is the <b>Browser</b> from input configuration
	 * @param baseUrl is the base URL for startup
	 */
	public SeleniumWebDriver ( Browser browser, String baseUrl ) {
		
		instance = getLocalWebDriver( browser );

		driverBaseUrl = baseUrl;

		logger.debug("Save the driver base URL : " + driverBaseUrl);
		
		adjustWindows(baseUrl);
	}
	
	/**
	 * New SeleniumWebDriver instance for Grid application
	 * 
	 * @param browser is the <b>Browser</b> from input configuration
	 * @param baseUrl is the base URL for startup
	 * @param baseHubAddress is the URL of Selenium Hub
	 */
	public SeleniumWebDriver ( Browser browser, String baseUrl, String baseHubAddress ) {
		
		this(BrowserType.valueOf(browser.getType().toString().toUpperCase()), baseUrl, baseHubAddress);
	}
	
	/**
	 * This method reproduces the "WebDriverBackedSelenium" object behavior.
	 * 
	 * It will append relative path to base URL provided during construction. 
	 * 
	 * @param relativePath
	 */
	public void open(String relativePath) {
		
		getWrappedDriver().get(driverBaseUrl + relativePath);
	}
	
	/**
	 * Close the <b>WebDriver</b> object
	 */
	public void close() {
		
		instance.quit();
	}
	
	/**
	 * Close the <b>WebDriver</b> object
	 */
	public Alert selectAlert() {
		
		return instance.switchTo().alert();
		
	}
	
	/**
	 * 
	 * @param browser
	 * @param browserProfile
	 * @param baseUrl
	 * 
	 * @deprecated use SeleniumWebDriver ( Browser browser, String baseUrl )
	 */
	public SeleniumWebDriver ( String browser, JSONObject browserProfile, String baseUrl ) {
		
		instance = getLocalWebDriver( browser, browserProfile );
		
		instance.get(baseUrl);	
	}

	public SeleniumWebDriver ( BrowserType baseBrowser, String baseUrl, String baseHubAddress ) {
		
		instance = getRemoteWebDriver( baseBrowser, baseHubAddress );

		driverBaseUrl = baseUrl;

		logger.debug("Save the driver base URL : " + driverBaseUrl);
		
		adjustWindows(baseUrl);
	}	
	
	/**
	 * reload page using set url
	 * 
	 */
	public void refresh() {
		
		instance.get( driverBaseUrl );
		
	}
	
	/**
	 * reload page
	 * 
	 * @param url is the startup URL to get
	 */
	public void refresh( String url ) {
		
		instance.get( url );
		
	}
	
	/**
	 * Fist steps to adjust windows
	 * 
	 * @param baseUrl is the startup URL to get
	 */
	private void adjustWindows(String baseUrl) {
		
		logger.debug("Windows Maximize");
		
		instance.manage().window().maximize();
		
		logger.debug("Get base Url");
		
		instance.get(baseUrl);
	}

	/**
	 * This method returns "Webdriver" object according passed "Browser" object 
	 *
	 * @param browser
	 * 
	 * @return an instance of WebDriver object
	 */
	private static WebDriver getLocalWebDriver( Browser browser ) {
	
		WebDriver resp = null;
		
		switch( BrowserType.valueOf( browser.getType().toString().toUpperCase() ) ) {
		
			case CHROME: { 
				
				/** by default the Linux driver will be load **/ 
				File chromeDriverRelativePath = new File( "/src/main/resources/browser/chrome/chromedriver" );
				
				if( OperatingSystem.isWindows() ) {
					
					chromeDriverRelativePath = new File( "/src/main/resources/browser/chrome/chromedriver.exe" );
					
				} 				
				
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + chromeDriverRelativePath.getPath() );
				
				resp = new ChromeDriver(); 
				
				break; 
			
			}
			case FIREFOX: { 

				FirefoxProfile profile = null;
				FirefoxBinary binary = null;
				
				JSONObject browserProfile = browser.getProfile();
				JSONObject optionsProfile = browser.getOptions();
				File firefoxBinary = browser.getBinary();
		
				try {				
		
					// there is a profile section
					if( null != browserProfile ) {
						
						JSONObject browserProfileFileInfo = browser.getFile();
						
						// there is a profile folder description
						if( null != browserProfileFileInfo ) { 

							StringBuilder profilePath = new StringBuilder();
							
							File profileFolder = new File( browser.getFileFolderName() );
							
							File profileName = new File( browser.getFileName() );
							
							switch( browser.getFileLoadingType() ) {
							
								case RESOURCE: {
									
									logger.info("Browser configuration selects Firefox profile as \"Resource\".");
									
									File resourceFolderPath = new File( "/src/main/resources/" );
									
									profilePath.append( System.getProperty( "user.dir" ) ).append( resourceFolderPath.getPath() ).append( File.separator );
									
									break;
									
								}
								case FILE: {
									
									logger.info("Browser configuration selects Firefox profile as \"File\".");
									
									profilePath.append( profileFolder.getPath() ).append( File.separator ).append( profileName.getPath() );
									
									break;
									
								}
							
							}
								
							logger.debug( Log.LOADING.createMessage( "Firefox profile on local file system ( " + profilePath.toString() + " )" ) );	
							
							profile = new FirefoxProfile( new File( profilePath.toString() ) ); 				
						}
						else { // else will be used the default profile
							
							logger.info("No information are provided to Firefox profile folder -> default profile is used.");
							
							profile = new FirefoxProfile();
						}
						
						/**
						 * Options section handling
						 */
						if( optionsProfile != null ) {
							
							@SuppressWarnings("unchecked")
							Iterator<String> keys = optionsProfile.keys();
							while( keys.hasNext() ) {
						        
								try {
									
									String key = keys.next().toString();
   	
						        	profile.setPreference( key, optionsProfile.getString(key) );
						        	
						        	logger.debug( Log.LOADING.createMessage( "Firefox profile option ( " + key + " )" ) );
						        	
						        } catch (JSONException e) {
						            
						        	logger.error( e.getMessage(), e );
						        	
						        }
								
						    }
						    
						}
						
						/**
						 * Binary file provided
						 */
						if ( firefoxBinary != null ) {
							
							binary = new FirefoxBinary(firefoxBinary);
							
							logger.debug("Firefox binary file provided -> " + firefoxBinary.toString());
						}
						
					} // end profile description
			
				} catch( Exception e ) {					
					
					logger.error( e.getMessage(), e );
				
				}

				if ( profile != null ) {
					
					resp = ( binary!= null ? new FirefoxDriver( binary, profile) : new FirefoxDriver(profile) );
				
				} else {
					
					resp = new FirefoxDriver();
				
				}
				
				break;
		
			}
			case IE: { resp = new InternetExplorerDriver(); break; }
			case OPERA: { resp = new OperaDriver(); break; }
			case SAFARI: { resp = new SafariDriver(); break; }
			case PHANTOM: { 
			
				// 
				File phantomDriverRelativePath = new File( "/home/adipasquale/Downloads/phantomjs-2.0.0/bin/phantomjs" );
				
				System.setProperty("phantomjs.binary.path", phantomDriverRelativePath.getPath() );				
				
				resp = new PhantomJSDriver(); break; 
				
			}
			case HTMLUNIT: { resp = new HtmlUnitDriver(true); break; }
			default: { resp = new HtmlUnitDriver(true); break; }
		}

		return resp;
	}
	
	/**
	 * This method returns "Webdriver" object according passed "Browser" object and browserProfile string
	 * 
	 * @param browser
	 * @param browserProfile
	 * 
	 * @return an instance of WebDriver object
	 * 
	 * @deprecated use getLocalWebDriver( Browser browser )
	 */
	private static WebDriver getLocalWebDriver( String browser, JSONObject browserProfile ) {
		
		WebDriver resp = null;
		
		switch( BrowserType.valueOf( browser.toUpperCase() ) ) {
		
			case CHROME: { 
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/browser/chrome/chromedriver");
				resp = new ChromeDriver(); 
				break; 
			}
			case FIREFOX: { 
					
				FirefoxProfile profile = null;
				
				try {				
					
					if( browserProfile != null ) {
						
						JSONObject browserProfileInfo = browserProfile.getJSONObject("profile");

						if( !browserProfileInfo.isNull("file") ) { 
							
							JSONObject browserProfileFileInfo = browserProfileInfo.getJSONObject("file");
							
							StringBuilder path = new StringBuilder();
									
							if( IOFileUtils.IOLoadingType.valueOf( browserProfileFileInfo.getString("loadingType").toUpperCase() ).equals( IOFileUtils.IOLoadingType.RESOURCE ) ) {
								
								path.append( System.getProperty( "user.dir" ) ).append( "/src/main/resources/" );
								
							}
							
							path.append( IOFileUtils.buildPath( browserProfileFileInfo.getString("loadingType"), browserProfileFileInfo.getString("fileName") ) );
							
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
	
	private static WebDriver getRemoteWebDriver( BrowserType browserType, String baseHubAddress ) {
		
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
