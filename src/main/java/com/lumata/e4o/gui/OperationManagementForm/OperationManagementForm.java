package com.lumata.e4o.gui.OperationManagementForm;
	
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.ErrorModificableElement;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorConditionType;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.common.PlaceHolderDate;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.Form;
import com.lumata.e4o.gui.common.FormSaveConfigurationHandler;
import com.lumata.e4o.gui.common.GWTCalendarForm;
import com.lumata.e4o.gui.common.FormSaveConfigurationHandler.SaveResult;
import com.lumata.e4o.gui.operationmanager.Operations;
import com.lumata.e4o.json.gui.campaignmanager.JSONAction;
import com.lumata.e4o.json.gui.campaignmanager.JSONCriteria;
import com.lumata.e4o.json.gui.campaignmanager.JSONEvent_;
import com.lumata.e4o.json.gui.loyaltymanager.JSONLoyaltiesCreation;
import com.lumata.e4o.json.gui.loyaltymanager.JSONLoyaltiesCreation.LoyaltyTypes;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.Form;

	public class OperationManagementForm<JSONTrafficGeneratorEvent> extends Form {


		/**
		 * 
		 */
		private static final Logger logger = LoggerFactory.getLogger(OperationManagementForm.class);
		
		/**
		 * 
		 */
		private JSONTrafficGeneratorEvent TrafficGeneratorEvent;
		private enum WizardTab {
			Operations
		}
		/**
		 * 
		 * @param selenium
		 * @param timeout
		 * @param interval
		 */
		
		
		public OperationManagementForm(SeleniumWebDriver selenium, JSONTrafficGeneratorEvent configuration, long timeout, long interval) {
			
			super(selenium, timeout, interval);
			
			this.TrafficGeneratorEvent = configuration;
		}

		/**
		 * 
		 */
			
		public  OperationManagementForm open() throws FormException {
			
			super.clickXPath("//div[@id='gwt-debug-BarCaptionHomeOperations']" );
			
			return this;
			
		}

		public  OperationManagementForm clickTrafficGenerator() throws FormException {
			
			super.clickXPath("//div[@id='gwt-debug-InputOPTrafficGenerator']" );
			
			return this;
			
		}

		
		public  OperationManagementForm<JSONTrafficGeneratorEvent> SetSubscriberId(String Subsid) throws FormException {
			
			super.sendKeysByXPath("//input[@id='gwt-debug-InputGUITrafficGeneratorEventSubID']", Subsid );
			
			return this;
			
		}

		public  OperationManagementForm SetSource(String Source) throws FormException {
			
			String SourceXPath = "//select[@id='gwt-debug-ListGUITrafficGeneratorEventSource']";
			selectByXPathAndVisibleText( SourceXPath,Source );
			return this;
			
		}
		
		public  OperationManagementForm SetEvent(String Event) throws FormException {
			
			String EventXPath = "//select[@id='gwt-debug-ListGUITrafficGeneratorEventClass']";
			selectByXPathAndVisibleText( EventXPath,Event );
			return this;
				
		}

		public  OperationManagementForm clickAddEventParameterButton() throws FormException {
			
			super.clickXPath("//button[@id='gwt-debug-BtnGUITrafficGeneratorEventParametersAdd']" );
			
			return this;
			
		}


		public  OperationManagementForm SetEventParameterName(String EventPn) throws FormException {
			
			String EventParameterXPath = "//input[@id='gwt-debug-InputGUITrafficGeneratorEventParameterName']";
			sendKeysByXPath( EventParameterXPath,EventPn );
			
			return this;
			
		}

		public  OperationManagementForm SetEventParameterValue(String EventPv) throws FormException {
			
			String EventParameterValueXPath = "//input[@id='gwt-debug-InputGUITrafficGeneratorEventParameterValue']";
			sendKeysByXPath( EventParameterValueXPath,EventPv );
			
			return this;
			
		}

		public  OperationManagementForm clickApplyButton() throws FormException {
			
			super.clickXPath("//button[@id='gwt-debug-FormGUITrafficGeneratorEventParameterApply']" );
			
			return this;
			
		}

		public  OperationManagementForm clickEventExecuteButton() throws FormException {
			
			super.clickXPath("//button[@id='gwt-debug-BtnGUITrafficGeneratorEventExecute']" );
			
			return this;
			
		}
		
		public Boolean handleJavascriptAlertAcceptDismiss(Boolean accept) {
			
			Alert popupAlert = null;
			Boolean pressed = null;
			
			try {
				
				popupAlert = selenium.selectAlert();
			    	
				if ( popupAlert != null ) { 
					
					if ( accept )
						popupAlert.accept();
					else 
						popupAlert.dismiss();
					
					pressed = Boolean.TRUE; 
				}
				
			} catch (NoAlertPresentException e) {
				
				// nothing to do
				pressed = Boolean.TRUE;
			}
			
			return pressed;
		}
		
		

		//input[@id='gwt-debug-InputGUITrafficGeneratorEventSubID']
		//div[@id='gwt-debug-InputOPTrafficGenerator']
		//select[@id='gwt-debug-ListGUITrafficGeneratorEventClass']
		//button[@id='gwt-debug-BtnGUITrafficGeneratorEventParametersAdd']
		//input[@id='gwt-debug-InputGUITrafficGeneratorEventParameterName']
		//input[@id='gwt-debug-InputGUITrafficGeneratorEventParameterValue']
		//button[@id='gwt-debug-FormGUITrafficGeneratorEventParameterApply']
		//button[@id='gwt-debug-BtnGUITrafficGeneratorEventExecute']
}
