package com.lumata.e4o.gui.operationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.OperationManagementForm.OperationManagementForm;
import com.lumata.e4o.gui.common.Form;

public class Operations {

	private static final Logger logger = LoggerFactory.getLogger(Operations.class);
	
	public Operations(SeleniumWebDriver selenium, long timeout, long interval) {
		// TODO Auto-generated constructor stub
	}

	protected static boolean select( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id=gwt-debug-BarCaptionHomeOperations") );
		
		WebElement operationsButton = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-BarCaptionHomeOperations", timeout, interval);
		if( operationsButton == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot open the Operations DashBoard" ) ); return false; }	
		
		logger.info( Log.SELECTING.createMessage( selenium.getTestName(), "to open the Operations DashBoard") );
		operationsButton.click();
		
		return true;
		
	}

	}
	

