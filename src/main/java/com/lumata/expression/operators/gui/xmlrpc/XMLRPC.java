package com.lumata.expression.operators.gui.xmlrpc;

import org.json.JSONObject;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;


public class XMLRPC {

	private static final Logger logger = LoggerFactory.getLogger( XMLRPC.class );
	
	private static final String HOME = "html/body/div[1]/div/div/";
	private static final String URL = HOME + "div[1]/div[2]/div/input";
	private static final String CALLS_LIST = HOME + "div[2]/div[2]/div/select";
	private static final String CALL_BTN = HOME + "div[2]/div[2]/div/a";
	private static final String PARAMETERS = HOME + "div[4]/div[1]/";
	private static final String PARAMETERS_BTN = HOME + "div[2]/div[4]/div[1]/";
	private static final String ADD_PARAMETER = PARAMETERS_BTN + "a[1]";
	private static final String REMOVE_LAST_PARAMETER = PARAMETERS_BTN + "a[2]";
	private static final String RESULT = HOME + "div[4]/div[2]";
	
	public static boolean open( SeleniumWebDriver selenium, String url, long timeout, long interval ) {
		
		logger.info( Log.LOADING.createMessage( "Browser" ) );
		
		selenium.open( url );
		
		if( XMLRPC.getURL(selenium, timeout, interval) == null ) { return false; }
	
		return true;
		
	}
	
	public static WebElement getURL( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		WebElement urlInput = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, URL, timeout, interval);
		if( urlInput == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "URL bar not found" ) ); return null; }	
		
		return urlInput;
		
	}
	
	public static WebElement getCallsList( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		WebElement callsList = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, CALLS_LIST, timeout, interval);
		if( callsList == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Calls List not found" ) ); return null; }	
		
		return callsList;
		
	}
	
	public static WebElement getCallBtn( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		WebElement callButton = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, CALL_BTN, timeout, interval);
		if( callButton == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Call Button not found" ) ); return null; }	
		
		return callButton;
		
	}
	
	public static WebElement getParameterElement( SeleniumWebDriver selenium, int index, long timeout, long interval ) {
		
		StringBuffer parameterTextArea = new StringBuffer();
		
		parameterTextArea.append( PARAMETERS_BTN ).append( "div[" ).append( index ).append( "]/textarea" );
		
		WebElement parameterElement = null;
		
		try{	
			
			parameterElement = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, parameterTextArea.toString(), timeout, interval);
			
		} catch( NoSuchElementException e ) {}
		
		return parameterElement;
		
	}

	public static int getParametersCount( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		int parametersCount = 1;
		
		while( XMLRPC.getParameterElement(selenium, parametersCount, timeout, interval) != null ) {
			
			logger.info( String.valueOf( parametersCount ) );
			parametersCount++;
			
		}
		
		return ( parametersCount - 1 );
		
	}

	public static WebElement getResult( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		WebElement resultElement = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, RESULT, timeout, interval);
		if( resultElement == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Result not found" ) ); return null; }	
		
		return resultElement;
		
	}

	public static boolean addParameter( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		WebElement addParameterButton = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, ADD_PARAMETER, timeout, interval);
		if( addParameterButton == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Add Parameter Button not found" ) ); return false; }	
		
		addParameterButton.click();
		
		return true;
		
	}
	
	public static boolean removeLastParameter( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		WebElement delLastParameterButton = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, REMOVE_LAST_PARAMETER, timeout, interval);
		if( delLastParameterButton == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Remove Last Parameter Button not found" ) ); return false; }	
		
		delLastParameterButton.click();
		
		return true;
		
	}

	public static boolean setCfg( JSONObject dataSource, String tenant ) {
		
		Mysql mysql = new Mysql( dataSource );
		
		String query = "insert into " + tenant + ".conf values ('allow_list', 0, 'xmlrpc', 'NULL', 'Internal', 'true', '', 'RW', now(), 'Value', 'Do we support the listing of XMLRPC signatures');";
		logger.info( query );
		mysql.execQuery(query);
		
		mysql.close();
		
		return true;
		
	}

	public static boolean setURL( SeleniumWebDriver selenium, CharSequence value, long timeout, long interval ) {
		
		WebElement urlInput = XMLRPC.getURL(selenium, timeout, interval);
		if( urlInput == null ) return false;
		
		logger.info( Log.PUTTING.createMessage( "URL ( " + value.toString() + " )" ) );
		urlInput.clear();
		urlInput.sendKeys( value );
		
		return true;
		
	}			
	
	public static boolean setCall( SeleniumWebDriver selenium, CharSequence value, long timeout, long interval ) {
		
		WebElement callsList = XMLRPC.getCallsList(selenium, timeout, interval);
		if( callsList == null ) return false;
		
		logger.info( Log.PUTTING.createMessage( "Call ( " + value.toString() + " )" ) );
		callsList.sendKeys( value );
		
		return true;
		
	}
	
	public static boolean sendCall( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		WebElement callButton = XMLRPC.getCallBtn(selenium, timeout, interval);
		if( callButton == null ) return false;
		
		logger.info( Log.SELECTING.createMessage( "Call Button )" ) );
		callButton.click();
		
		return true;
		
	}
	
	public static boolean setParameter( SeleniumWebDriver selenium, int index, CharSequence value, long timeout, long interval ) {
		
		WebElement parameterTextArea = XMLRPC.getParameterElement(selenium, index, timeout, interval);
		if( parameterTextArea == null ) return false;
		
		logger.info( Log.PUTTING.createMessage( "Set Parameter ( " + value.toString() + " )" ) );
		parameterTextArea.sendKeys( value );
		
		return true;
		
	}
	
	public static boolean setLoginParameter( SeleniumWebDriver selenium, int index, String user, String password, long timeout, long interval ) {
		
		StringBuffer loginParameter = new StringBuffer();
		
		loginParameter.append( "<authentication>\n" );
		loginParameter.append( "<login>superman</login>\n" );
		loginParameter.append( "<password>super2010Man</password>\n" );
		loginParameter.append( "</authentication>" );
				
		return XMLRPC.setParameter(selenium, index, loginParameter.toString(), timeout, interval);
		
	}

}
