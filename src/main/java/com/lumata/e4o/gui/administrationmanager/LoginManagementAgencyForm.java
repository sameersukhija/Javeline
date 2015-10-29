package com.lumata.e4o.gui.administrationmanager;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;

public class LoginManagementAgencyForm extends LoginManagementForm {

	public LoginManagementAgencyForm( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
	}
	
	public LoginManagementAgencyForm open() throws FormException {
		
		super.open().clickId( "gwt-debug-actrule-administration-loginManagement-agency" );
		
		return this;
		
	}	
	
	public LoginManagementAgencyForm clickAddButton() throws FormException
	{
		super.clickXPath( "//table[contains(@class,'page-AgencyPageView')]/tbody/tr/td//table[@class='buttonPanel']/tbody/tr/td//button[@name='btn-add']" );
		return this;
	}


	public LoginManagementAgencyForm setAgencyName(String name) throws FormException {
		sendKeysByXPath ("//td[contains(text(), 'Name')]/parent::tr//input", name);
		
		return this;
	}
	
	public LoginManagementAgencyForm setAgencyAddress(String address) throws FormException {
		sendKeysByXPath ("//td[contains(text(), 'Address')]/parent::tr//input", address);
		
		return this;
	}
	
	public LoginManagementAgencyForm setAgencyPhone(String pno) throws FormException {
		sendKeysByXPath ("//td[contains(text(), 'Phone')]/parent::tr//input", pno);
		
		return this;
	}
	
	public LoginManagementAgencyForm ClickSaveAgency() throws FormException {
		
		super.clickXPath("//button[@name='btn-save']");
				
		return this;
		}
	
	public LoginManagementAgencyForm clickRefreshButton() throws FormException
	{
		this.clickXPath( "//table[contains(@class,'page-AgencyPageView')]//button[@name='btn-refresh']" );
		return this;
	}
	
	public String closeAlertAndGetItsText() {
	    boolean acceptNextAlert = true;
		try {
	      Alert alert = selenium.selectAlert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
	
	public Boolean isAgencyinList(String AgencyName) throws FormException{
		List<WebElement> agencyList = getagencyList(AgencyName);

		for( WebElement AgencyListE1 : agencyList ) {

			if( AgencyListE1.getText().trim().equals( AgencyName ) ) {
		
				return true;

			}	
		}
	
		return false;	
	}

	
	public List<WebElement> getagencyList(String Agency_Name)  throws FormException {

		String rootPath = "//table[contains(@class,'AgencyPageView')]";
		String subPath = "//td[@class='column_name']//div[@class='gwt-Label']";
		
		List<WebElement> agencyList = getListByXPath(rootPath, rootPath + subPath);
		System.out.println(agencyList);
		return agencyList;
	}
	


}
