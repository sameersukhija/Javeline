package com.lumata.common.testing.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public final class SeleniumUtils {

	private final static long TIMEOUT = 30000;
	private final static long INTERVAL = 1000;
		
	private static final  Logger logger = LoggerFactory.getLogger( SeleniumUtils.class );
	
	public enum SearchBy { ID, NAME, LINK, PARTIAL_LINK, TAG_NAME, XPATH, CSS, CLASS_NAME  }
	
	private SeleniumUtils() {}
	
	public static void waitFor( long timeout ) {
		
		try { 
			
			Thread.sleep( timeout ); 
		
		} catch(  InterruptedException e ) { 
			
			logger.error(e.getMessage(), e); 
		
		} 
		
	}

	public static void waitForPageLoading( WebDriver driver, long timeout ) {
		
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return Boolean.valueOf(((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"));
            }
        };
        
	    WebDriverWait wait = new WebDriverWait(driver, timeout);
	    wait.until(pageLoadCondition);
		
	}
	
	public static WebElement findForComponentDisplayed( SeleniumWebDriver selenium, SearchBy searchBy, String locator) {
		
		return findForComponentDisplayed( selenium, searchBy, locator, TIMEOUT, INTERVAL );
		
	}
	
	public static WebElement findForComponentDisplayed( SeleniumWebDriver selenium, SearchBy searchBy, String locator, long timeout ) {
				
		return findForComponentDisplayed( selenium, searchBy, locator, timeout, INTERVAL );
		
	}

	public static WebElement findForComponentDisplayed( SeleniumWebDriver selenium, SearchBy searchBy, String locator, long timeout, long interval ) {
		
		WebElement element = null;			
		
		logger.debug( Log.CHECKING.createMessage( "element ( " + locator + " )" ) );
		
		int attempts = Math.round( timeout / ( float ) interval );
		
		for( int i = 1; i <= attempts; i++ ) {
			
			logger.debug( Log.CHECKING.createMessage( "( " + i + " ) element ( " + locator + " )" ) );
			
			try {
				
				Thread.sleep(interval);
				
				switch( searchBy ) {
					
					case ID: { element = selenium.getWrappedDriver().findElement( By.id(locator) ); break;}
					case NAME: { element = selenium.getWrappedDriver().findElement( By.name(locator) ); break;}
					case LINK: { element = selenium.getWrappedDriver().findElement( By.linkText(locator) ); break;}
					case PARTIAL_LINK: { element = selenium.getWrappedDriver().findElement( By.partialLinkText(locator) ); break;}
					case TAG_NAME: { element = selenium.getWrappedDriver().findElement( By.tagName(locator) ); break;}
					case XPATH: { element = selenium.getWrappedDriver().findElement( By.xpath(locator) ); break;  }
					case CSS: { element = selenium.getWrappedDriver().findElement( By.cssSelector(locator) ); break;  }
					case CLASS_NAME: { element = selenium.getWrappedDriver().findElement( By.className(locator) ); break;  }
					
				}	
				
        		break;
            
			} catch ( InterruptedException e ) {}
              
		}
		
		return element;
		
	}
	
	public static List<WebElement> findListForComponentDisplayed( SeleniumWebDriver selenium, SearchBy rootSearchBy, SearchBy searchBy, String rootLocator, String locator) {
		
		return findListForComponentDisplayed( selenium, rootSearchBy, searchBy, rootLocator, locator, TIMEOUT, INTERVAL );
		
	}
	
	public static List<WebElement> findListForComponentDisplayed( SeleniumWebDriver selenium, SearchBy rootSearchBy, SearchBy searchBy, String rootLocator, String locator, long timeout ) {
				
		return findListForComponentDisplayed( selenium, rootSearchBy, searchBy, rootLocator, locator, timeout, INTERVAL );
		
	}

	public static List<WebElement> findListForComponentDisplayed( SeleniumWebDriver selenium, SearchBy rootSearchBy, SearchBy searchBy, String rootLocator, String locator, long timeout, long interval ) {
		
		List<WebElement> elements = null;			
		
		logger.debug( Log.CHECKING.createMessage( " for all elements ( " + locator + " ) contained ( in " + rootLocator + " )" ) );
		
		WebElement rootElement = SeleniumUtils.findForComponentDisplayed(selenium, rootSearchBy, rootLocator, timeout, interval);
		
		if( rootElement != null ) {
			System.out.println( rootElement.toString() );
			switch( searchBy ) {
			
				case ID: { elements = rootElement.findElements( By.id(locator) ); break;}
				case NAME: { elements = rootElement.findElements( By.name(locator) ); break;}
				case LINK: { elements = rootElement.findElements( By.linkText(locator) ); break;}
				case TAG_NAME: { elements = rootElement.findElements( By.tagName(locator) ); break;}
				case XPATH: { elements = rootElement.findElements( By.xpath(locator) ); break;  }
				case CSS: { elements = rootElement.findElements( By.cssSelector(locator) ); break;  }
				case CLASS_NAME: { elements = rootElement.findElements( By.className(locator) ); break;  }
				
			}
		
		}
		
		if( elements.size() <= 0 ) { elements = null; }
		
		return elements;
		
	}
	
	public static List<WebElement> findListForComponentDisplayed( SeleniumWebDriver selenium, SearchBy searchBy, WebElement rootElement, String locator ) {
		
		return findListForComponentDisplayed( selenium, searchBy, rootElement, locator, TIMEOUT, INTERVAL );
		
	}
	
	public static List<WebElement> findListForComponentDisplayed( SeleniumWebDriver selenium, SearchBy searchBy, WebElement rootElement, String locator , long timeout ) {
				
		return findListForComponentDisplayed( selenium, searchBy, rootElement, locator, timeout, INTERVAL );
		
	}

	public static List<WebElement> findListForComponentDisplayed( SeleniumWebDriver selenium, SearchBy searchBy, WebElement rootElement, String locator , long timeout, long interval ) {
		
		List<WebElement> elements = null;			
		
		logger.debug( Log.CHECKING.createMessage( " for all elements ( " + locator + " ) contained ( in " + rootElement.getText() + " )" ) );
		
		if( rootElement != null ) {
				
			switch( searchBy ) {
			
				case ID: { elements = rootElement.findElements( By.id(locator) ); break;}
				case LINK: { elements = rootElement.findElements( By.linkText(locator) ); break;}
				case TAG_NAME: { elements = rootElement.findElements( By.tagName(locator) ); break;}
				case XPATH: { elements = rootElement.findElements( By.xpath(locator) ); break;  }
				case CSS: { elements = rootElement.findElements( By.cssSelector(locator) ); break;  }
				case CLASS_NAME: { elements = rootElement.findElements( By.className(locator) ); break;  }
				
			}
		
		}
		
		return elements;
		
	}
	
}
