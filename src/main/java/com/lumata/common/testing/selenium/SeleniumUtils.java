package com.lumata.common.testing.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.network.RestClient;

/**
 * @author <a href="mailto:arcangelo.dipasquale@gmail.com">Arcangelo Di Pasquale</a>
 * 
 */
public class SeleniumUtils {

	private final static long TIMEOUT = 30000;
	private final static long INTERVAL = 1000;
		
	private static final  Logger logger = LoggerFactory.getLogger( SeleniumUtils.class );
	
	public enum SearchBy { ID, LINK, TAG_NAME, XPATH, CSS  }
	
	public static void waitFor( long timeout ) {
		
		try { 
			
			Thread.sleep( timeout ); 
		
		} catch(  InterruptedException e ) { 
			
			logger.error(e.getMessage()); 
		
		} 
		
	}

	public static void waitForPageLoading( WebDriver driver, long timeout ) {
		
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
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
		
		long attempts = Math.round( timeout / interval );
		
		for( int i = 1; i <= attempts; i++ ) {
			
			logger.debug( Log.CHECKING.createMessage( "( " + i + " ) element ( " + locator + " )" ) );
			
			try {
				
				Thread.sleep(interval);
				
				switch( searchBy ) {
				
					case ID: { element = selenium.getWrappedDriver().findElement( By.id(locator) ); break;}
					case LINK: { element = selenium.getWrappedDriver().findElement( By.linkText(locator) ); break;}
					case TAG_NAME: { element = selenium.getWrappedDriver().findElement( By.tagName(locator) ); break;}
					case XPATH: { element = selenium.getWrappedDriver().findElement( By.xpath(locator) ); break;  }
					case CSS: { element = selenium.getWrappedDriver().findElement( By.cssSelector(locator) ); break;  }
					
				}				
        		break;
            
			} catch ( Exception e ) {}
              
		}
		
		return element;
		
	}
	
}
