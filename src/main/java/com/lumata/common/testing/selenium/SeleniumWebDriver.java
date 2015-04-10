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
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.system.Browser;
import com.lumata.common.testing.system.OperatingSystem;
import com.opera.core.systems.OperaDriver;
import com.thoughtworks.selenium.SeleniumException;

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
	
	/**
	 * The URL of the remote selenium hub.
	 */
	private URL driverHubUrl = null;
		
	/**
	 * The DesiredCapabilities configuration
	 */
	private DesiredCapabilities capability = null;		
		
	/**
	 * Instance of local <b>WebDriver</b>
	 */
	private WebDriver instance = null;
	
	/**
	 * Current driver type
	 */
	private SeleniumDriverType driverType = null;
	
	/**
	 * The key of the json object configurator.
	 */	
	private enum SeleniumJsonkey {
		
		type("type"),
		local("local"),
		remote("remote"),
		selenium_hub("selenium_hub"),
		capability("capability"),
		platform("platform"),
		browserName("browserName"),
		version("version");
		
		private String key;
		
		SeleniumJsonkey( String key ) {
			
			this.key = key;
			
		}
		
		public String key() {
			
			return this.key;
			
		}
		
	}
	
	/**
	 * The key of the capability json object configurator.
	 */	
	private enum SeleniumDriverType {
		
		local,
		remote
		
	}
	
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
	 * It returns the wrapped <b>WebDriver</b>
	 * 
	 * @return a <b>WebDriver</b> object 
	 */
	public WebDriver getWrappedDriver() {
		
		return instance;
	}
	
	/**
	 * Default SeleniumWebDriver instance
	 * 
	 */
	public SeleniumWebDriver () {}

	/**
	 * New SeleniumWebDriver instance
	 * 
	 * @param browser is the <b>Browser</b> from input configuration
	 * @param baseUrl is the base URL for startup
	 */
	public SeleniumWebDriver ( Browser browser, String baseUrl ) {
		
		instance = getLocalWebDriver( browser );

		openBrowser( baseUrl );
		
	}
	
	/**
	 * New SeleniumWebDriver instance for Grid application
	 * 
	 * @param browser is the <b>Browser</b> from input configuration
	 * @param baseUrl is the base URL for startup
	 * @param baseHubAddress is the URL of Selenium Hub
	 */
	public SeleniumWebDriver ( Browser browser, String baseUrl, String baseHubAddress ) {
		
		this(Browser.Type.valueOf(browser.getType().toString().toUpperCase()), baseUrl, baseHubAddress);
	}
	
	/**
	 * This method reproduces the "WebDriverBackedSelenium" object behavior.
	 * 
	 * It will append relative path to base URL provided during construction. 
	 * 
	 * @param relativePath
	 */
	public SeleniumWebDriver openBrowser( String baseUrl ) {
		
		driverBaseUrl = baseUrl;

		logger.debug("Save the driver base URL : " + driverBaseUrl);
		
		logger.debug("Get base Url");
		
		instance.get(baseUrl);
		
		logger.debug("Windows Maximize");
		
		instance.manage().window().maximize();
		
		return this;
				
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
	 * Select alert windows
	 */
	public Alert selectAlert() {
		
		return instance.switchTo().alert();
		
	}

	public SeleniumWebDriver ( Browser.Type baseBrowser, String baseUrl, String baseHubAddress ) {
		
		instance = getRemoteWebDriver( baseBrowser, baseHubAddress );

		openBrowser( baseUrl );
		
	}	
	
	public static SeleniumWebDriver getInstance( JSONObject configuration ) throws SeleniumException {
		
		SeleniumWebDriver seleniumWebDriver = new SeleniumWebDriver();
		
		if( null == configuration || configuration.length() == 0 ) {  
			
			throw new SeleniumException( "The configuration is null or empty" );
			
		}
		
		try {
		
			if( configuration.has( SeleniumJsonkey.type.key() ) ) {
				
				switch(  SeleniumJsonkey.valueOf( configuration.getString( SeleniumJsonkey.type.key() ).toLowerCase() ) ) {
				
					case local: {
						
						seleniumWebDriver.setDriverType( SeleniumDriverType.local );
						
						JSONObject localConfiguration = configuration.getJSONObject( SeleniumJsonkey.local.key() );
						
						Browser.Type browserType = Browser.Type.valueOf( localConfiguration.getString( SeleniumJsonkey.browserName.key() ).toLowerCase() );
						
						seleniumWebDriver.instance = seleniumWebDriver.getLocalWebDriver( new Browser( localConfiguration.getJSONObject( browserType.name() ), browserType ) );
												
						break;
						
					}
					case remote: {
						
						seleniumWebDriver.setDriverType( SeleniumDriverType.remote );
						
						JSONObject remoteConfiguration = configuration.getJSONObject( SeleniumJsonkey.remote.key() );
						
						if( remoteConfiguration.has( SeleniumJsonkey.selenium_hub.name() ) ) { 
							
							seleniumWebDriver.setHub( remoteConfiguration.getString( SeleniumJsonkey.selenium_hub.key() ) ); 
							
							logger.info( "set selenium hub ( " + seleniumWebDriver.getHub().toExternalForm() + " )" );
							
						}	
						
						if( remoteConfiguration.has( SeleniumJsonkey.capability.name() ) ) { 
														
							seleniumWebDriver.setCapability( seleniumWebDriver.getCapability( remoteConfiguration.getJSONObject( SeleniumJsonkey.capability.key() ) ) ); 
							
							logger.info( "loaded selenium capability ( " + seleniumWebDriver.getCapability() + " )" );
							
						} else {
							
							seleniumWebDriver.setCapability( new DesiredCapabilities() );
							
							logger.info( "loaded default selenium capability ( " + seleniumWebDriver.getCapability() + " )" );
							
						}						
						
						seleniumWebDriver.instance = seleniumWebDriver.getRemoteWebDriver();
						
						break;
						
					}
					default: { break; }
				
				}
				
			} else {
				
				throw new SeleniumException( "It cannot select for selenium driver type in the configuration. Accepted value are: 'local' or 'remote'." );
				
			}
			
		} catch( Exception e ) {
			
			throw new SeleniumException( e.getMessage(), e );
			
		}
		
		return seleniumWebDriver;
		
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
	 * This method returns "Webdriver" object according passed "Browser" object 
	 *
	 * @param browser
	 * 
	 * @return an instance of WebDriver object
	 */
	private WebDriver getLocalWebDriver( Browser browser ) {
	
		WebDriver resp = null;
		
		switch( Browser.Type.valueOf( browser.getType().toString().toLowerCase() ) ) {
		
			case chrome: { 
				
				/** by default the Linux driver will be load **/ 
				File chromeDriverRelativePath = new File( "/src/main/resources/browser/chrome/chromedriver" );
				
				if( OperatingSystem.isWindows() ) {
					
					chromeDriverRelativePath = new File( "/src/main/resources/browser/chrome/chromedriver.exe" );
					
				} 				
				
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + chromeDriverRelativePath.getPath() );
				
				resp = new ChromeDriver(); 
				
				break; 
			
			}
			case firefox: { 

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
			case ie: { resp = new InternetExplorerDriver(); break; }
			case opera: { resp = new OperaDriver(); break; }
			case safari: { resp = new SafariDriver(); break; }
			case phantom: { 
			
				File phantomDriverRelativePath = new File( "/home/adipasquale/Downloads/phantomjs-2.0.0/bin/phantomjs" );
				
				System.setProperty("phantomjs.binary.path", phantomDriverRelativePath.getPath() );				
				
				resp = new PhantomJSDriver(); break; 
				
			}
			case htmlunit: { resp = new HtmlUnitDriver(true); break; }
			default: { resp = new HtmlUnitDriver(true); break; }
		}

		return resp;
	}
	
	private WebDriver getRemoteWebDriver() {
		
		return new RemoteWebDriver( driverHubUrl, capability );
		
	}
	
	@SuppressWarnings("deprecation")
	private WebDriver getRemoteWebDriver( Browser.Type browserType, String baseHubAddress ) {
		
		setHub( baseHubAddress );
		
		switch( browserType ) {
		
			case chrome: { capability = DesiredCapabilities.chrome(); break; }
			case firefox: { capability =  DesiredCapabilities.firefox(); break; }
			case ie: { capability =  DesiredCapabilities.internetExplorer(); break; }
			case opera: { capability =  DesiredCapabilities.opera(); break; }
			case safari: { capability =  DesiredCapabilities.safari(); break; }
			default: { capability =  DesiredCapabilities.htmlUnit();  break; }
			
		}
		
		return new RemoteWebDriver( driverHubUrl, capability );
		
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
	
	public SeleniumDriverType getDriverType() {
		return this.driverType;		
	}
	
	public void setDriverType( SeleniumDriverType driverType ) {
		this.driverType = driverType;		
	}
	
	public void setHub( String url ) throws SeleniumException {
		
		try {
			
			this.driverHubUrl = new URL( url );
		
		} catch (MalformedURLException e) {
		
			throw new SeleniumException( e.getMessage(), e );
			
		}
		
	}
	
	public URL getHub() {
		
		return this.driverHubUrl;
		
	}	
	public void setHub( URL url ) {
		
		this.driverHubUrl = url;
		
	}

	public DesiredCapabilities getCapability() {
		
		return this.capability;
		
	} 
	
	@SuppressWarnings("deprecation")
	public DesiredCapabilities getCapability( JSONObject seleniumCapability ) throws SeleniumException {
		
		DesiredCapabilities capability = new DesiredCapabilities();
		
		if( null != seleniumCapability ) {
		
			try {
				
				switch( Browser.Type.valueOf( seleniumCapability.getString( SeleniumJsonkey.browserName.key() ).toLowerCase() ) ) {
				
					case chrome: { capability = DesiredCapabilities.chrome(); break;}
					case firefox: { capability =  DesiredCapabilities.firefox(); break; }
					case ie: { capability =  DesiredCapabilities.internetExplorer(); break; }
					case opera: { capability =  DesiredCapabilities.opera(); break; }
					case safari: { capability =  DesiredCapabilities.safari(); break; }
					case htmlunit: { capability =  DesiredCapabilities.safari(); break; }
					default: { capability = new DesiredCapabilities(); }
					
				}
				
				for( @SuppressWarnings("unchecked")Iterator<String> iterator = seleniumCapability.keySet().iterator(); iterator.hasNext();) {
				    
					String key = iterator.next();
			
					switch( SeleniumJsonkey.valueOf( key ) ) {
									
						case platform: {
							
							/*
							 * {WINDOWS|XP|VISTA|MAC|LINUX|UNIX|ANDROID}
							 */
							
							capability.setPlatform( Platform.valueOf( seleniumCapability.getString( key ).toUpperCase() ) ); 
							
							break;
							
						}
						case browserName: {
							
							/*
							 * {android|chrome|firefox|htmlunit|internet explorer|iPhone|iPad|opera|safari}
							 */
							
							capability.setBrowserName( seleniumCapability.getString( key ) );
							
						}
						default: {
							
							capability.setCapability( SeleniumJsonkey.valueOf( key ).name(), seleniumCapability.get( key ) );
													
						}
					
					
					}
											    
				}
								
			} catch( Exception e ) {
				
				throw new SeleniumException( e.getMessage(), e );
				
			}
			
		}
		
		return capability;
		
	}
		
	public void setCapability( DesiredCapabilities capability ) {
		
		this.capability = capability;
		
	} 
	
}
