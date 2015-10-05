package com.lumata.e4o.gui.operationmanager;
	
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.Form;

	public class OperationManagementForm<JSONTrafficGeneratorEvent> extends Form {


		/**
		 * 
		 */
//		private static final Logger logger = LoggerFactory.getLogger(OperationManagementForm.class);
		
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
		

}
