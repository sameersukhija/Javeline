package com.lumata.e4o.gui.xmlrpc;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.Security;
import com.lumata.common.testing.validating.Format;
import com.lumata.expression.operators.json.configuration.XMLRPCCfg;
import com.lumata.expression.operators.json.configuration.XMLRPCCfg.ParamType;


public class XMLRPCForm {

	private static final Logger logger = LoggerFactory.getLogger( XMLRPCForm.class );
	
	private static final String HOME = "html/body/div[1]/div/div/";
	private static final String URL = HOME + "div[1]/div[2]/div/input";
	private static final String CALLS_LIST = HOME + "div[2]/div[2]/div/select";
	private static final String CALL_BTN = HOME + "div[2]/div[2]/div/a";
	private static final String PARAMETERS = HOME + "div[4]/div[1]/";
	private static final String PARAMETERS_BTN = HOME + "div[2]/div[4]/div[1]/";
	private static final String ADD_PARAMETER = PARAMETERS_BTN + "a[1]";
	private static final String REMOVE_LAST_PARAMETER = PARAMETERS_BTN + "a[2]";
	private static final String RESULT = HOME + "/div[2]/div[4]/div[2]/div/p";
	
	public enum CALLS {
		
		ACCEPT( "offer_optimizer.accept" ) {
			public String get() { return this.value; }
			public int getNumberParametersRequired() { return 4; }
		},
		ALLOCATE( "offer_optimizer.allocate" ) {
			public String get() { return this.value; }
			public int getNumberParametersRequired() { return 3; }
		},
		REFUSE_ALL( "offer_optimizer.refuseAll" ) {
			public String get() { return this.value; }
			public int getNumberParametersRequired() { return 3; }
		};
		
		CALLS( String value ) {
			
			this.value = value;
			
		}
		
		public String value;
		
		public abstract String get();
		public abstract int getNumberParametersRequired();		
		
	}
	
	public static boolean open( SeleniumWebDriver selenium, String url, long timeout, long interval ) {
		
		logger.info( Log.LOADING.createMessage( "Browser" ) );
		
		selenium.open( url );
		
		if( XMLRPCForm.getURL(selenium, timeout, interval) == null ) { return false; }
	
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
		
		while( XMLRPCForm.getParameterElement(selenium, parametersCount, timeout, interval) != null ) {
			
			logger.info( String.valueOf( parametersCount ) );
			parametersCount++;
			
		}
		
		return ( parametersCount - 1 );
		
	}

	public static String getResult( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		WebElement resultElement = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, RESULT, timeout, interval);
		if( resultElement == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Result not found" ) ); return null; }
		
		String result = resultElement.getText();
				
		return result;
		
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
	
	public static boolean removeAllParameters( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		int numberParametersToDelete = XMLRPCForm.getParametersCount(selenium, 120000, 500);
		
		for( int i = 0; i < numberParametersToDelete; i++ ) {
			
			boolean removed = XMLRPCForm.removeLastParameter(selenium, timeout, interval);
			if( !removed ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Remove All Parameters" ) ); return false; }	
						
		}
		
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
		
		WebElement urlInput = XMLRPCForm.getURL(selenium, timeout, interval);
		if( urlInput == null ) return false;
		
		logger.info( Log.PUTTING.createMessage( "URL ( " + value.toString() + " )" ) );
		urlInput.clear();
		urlInput.sendKeys( value );
		
		return true;
		
	}			
	
	public static boolean setCall( SeleniumWebDriver selenium, CharSequence value, long timeout, long interval ) {
		
		WebElement callsList = XMLRPCForm.getCallsList(selenium, timeout, interval);
		if( callsList == null ) return false;
		
		logger.info( Log.PUTTING.createMessage( "Call ( " + value.toString() + " )" ) );
		callsList.sendKeys( value );
		
		return true;
		
	}
	
	public static boolean sendCall( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		WebElement callButton = XMLRPCForm.getCallBtn(selenium, timeout, interval);
		if( callButton == null ) return false;
		
		logger.info( Log.SELECTING.createMessage( "Call Button )" ) );
		callButton.click();
		
		return true;
		
	}
	
	public static boolean setParameter( SeleniumWebDriver selenium, int index, CharSequence value, long timeout, long interval ) {
		
		WebElement parameterTextArea = XMLRPCForm.getParameterElement(selenium, index, timeout, interval);
		if( parameterTextArea == null ) return false;
		
		logger.info( Log.PUTTING.createMessage( "Set Parameter ( " + value.toString() + " )" ) );
		parameterTextArea.sendKeys( value );
		
		return true;
		
	}
	
	public static boolean setLoginParameter( SeleniumWebDriver selenium, int index, JSONObject data, long timeout, long interval ) {
		
		StringBuffer loginParameter = new StringBuffer();
		
		try {
			
			loginParameter.append( "<authentication>\n" );
			loginParameter.append( "<login>" + data.getString( "user" ) + "</login>\n" );
			loginParameter.append( "<password>" + Security.decrypt( data.getString( "password" ) ) + "</password>\n" );
			loginParameter.append( "</authentication>" );
			
		} catch( JSONException e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return XMLRPCForm.setParameter(selenium, index, loginParameter.toString(), timeout, interval);
		
	}
	
	public static boolean setStringParameter( SeleniumWebDriver selenium, int index, JSONObject data, long timeout, long interval ) {
		
		StringBuffer stringParameter = new StringBuffer();
		
		try {
		
			stringParameter.append( "<string>" );
			stringParameter.append( data.getString( "value" ) );
			stringParameter.append( "</string>" );
		
		} catch( JSONException e ) {

			logger.error( e.getMessage(), e );
			
		}
			
		return XMLRPCForm.setParameter(selenium, index, stringParameter.toString(), timeout, interval);
		
	}
	
	public static boolean setOffersListParameter( SeleniumWebDriver selenium, int index, JSONObject data, long timeout, long interval ) {
		
		StringBuffer offerIDListParameter = new StringBuffer();
		
		try {
			
			JSONArray values = data.getJSONArray( "values" );
			
			offerIDListParameter.append( "<array><data>\n" );
			
			for( int i = 0; i < values.length(); i++ ) {
				
				offerIDListParameter.append( "<value><int>" + values.getString( i ) + "</int></value>\n" );
				
			}
		
			offerIDListParameter.append( "</data></array>\n" );
			
		} catch( JSONException e ) {

			logger.error( e.getMessage(), e );
			
		}
					
		return XMLRPCForm.setParameter(selenium, index, offerIDListParameter.toString(), timeout, interval);
		
	}
	
	public static boolean set( SeleniumWebDriver selenium, XMLRPCCfg configuration, long timeout, long interval ) {
		
		try {
			
			XMLRPCForm.removeAllParameters( selenium, timeout, interval );
			
			XMLRPCForm.setCall( selenium, XMLRPCForm.CALLS.valueOf( configuration.getCall() ).get(), timeout, interval );
		
			JSONArray parameters = configuration.getParameters();
						
			for( int i = 0; i < parameters.length(); i++ ) {
			        	
	            XMLRPCForm.addParameter( selenium, timeout, interval );
	         
	            switch( ParamType.valueOf( configuration.getParamType( i ) ) ) {
	        	
		        	case LOGIN: {
		        				        		
		        		XMLRPCForm.setLoginParameter( selenium, i + 1, configuration.getParamData( i ), timeout, interval);
		        		
		        		break;
		        	}
		        	case STRING: {
		        		
		        		XMLRPCForm.setStringParameter( selenium, i + 1, configuration.getParamData( i ), timeout, interval);
		        		
		        		break;
		        	}
		        	case OFFER_ID_LIST: {
		        		
		        		XMLRPCForm.setOffersListParameter( selenium, i + 1, configuration.getParamData( i ), timeout, interval);
		        		
		        		break;
		        	}
		        	default: { XMLRPCForm.removeLastParameter( selenium, timeout, interval); }
		        	
	        	}
	        	
	        }
			
			
		} catch( Exception e ) {
			
			logger.error( e.getMessage(), e );			
		
		}
				
		return true;
		
	}
	
	public static JSONObject getTemplate( XMLRPCForm.CALLS callType ) {
		
		JSONObject template = null;
		
		String resource_name = "xmlrpc_template_" + callType.name().toLowerCase();
		
		try {
			
			template = JSONUtils.loadJSONResource( "input/xmlrpc", resource_name + Format.JSON_EXTENSION );
								
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
				
		} 
		
		return template;
		
	}

}
