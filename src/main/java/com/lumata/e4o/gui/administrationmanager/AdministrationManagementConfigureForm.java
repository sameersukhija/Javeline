package com.lumata.e4o.gui.administrationmanager;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;

public class AdministrationManagementConfigureForm extends AdministrationForm {

	public AdministrationManagementConfigureForm( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
	}
	
	
		public AdministrationManagementConfigureForm open() throws FormException {
		
		super.clickId( "gwt-debug-BarCaptionHomeAdministration" );
		
		return this;
		
		}	
		
		
		public AdministrationManagementConfigureForm clickLdapSectionbutton(String strName) throws FormException
		{
			super.clickXPath( "//table/tbody/tr/td[text()='" + strName + "']" );
			
			return this;
		}
		
		public AdministrationManagementConfigureForm clickeditLdapconfigbutton(String strName) throws FormException
		{
			super.clickXPath( "//div[text()='" + strName + "']/../../td[4]/table/tbody/tr/td[2]/button[@name='btn-edit']" );
			return this;
		}

		
		
		public AdministrationManagementConfigureForm clickEditpropertyButton() throws FormException
		{
			super.clickXPath( "//table[@class='margin10px']/tbody/tr/td/table[@class='tableList']/tbody/tr[@class='contentRow cycle2']/td/table/tbody/tr/td/button" );
			return this;
		}

		
		public AdministrationManagementConfigureForm setLdapValue(String value) throws FormException {
			sendKeysByXPath ("//textarea", value);
			
			return this;
		}
		
		public AdministrationManagementConfigureForm setUseremail(String email) throws FormException {
			sendKeysByXPath ("//td[contains(text(), 'Email')]/parent::tr//input", email);
			
			return this;
		}
	
		public AdministrationManagementConfigureForm setUserpassword(String password) throws FormException {
			sendKeysByXPath ("//td[contains(text(), 'Password')]/parent::tr[@class='cycle2']//input", password );
			
			return this;
		}
		
		
		public AdministrationManagementConfigureForm setUserconfirmpassword(String cpassword) throws FormException {
			sendKeysByXPath ("//td[contains(text(), 'Confirm Password')]/parent::tr//input", cpassword );
			
			return this;
		}
		
		
		public AdministrationManagementConfigureForm setAgencyName(String aname) throws FormException {
			super.selectByXPathAndVisibleText ("//table[@class='tableList Form marginBottom10px']/tbody/tr[@class='cycle2']/td[2]/select", aname);
			return this;
		}
		
		
		public AdministrationManagementConfigureForm ClickTabsButton() throws FormException {
			
			clickXPath ("//table[@class='tableList']/tbody/tr[@class='cycle2']/td/table/tbody/tr/td/button[@class='gwt-Button gwt-Button[disabled]']");
			
			return this;
			
		}
		public String getClickTabsButton() throws FormException {
			
			return this.getValueByXPath("//table[@class='tableList']/tbody/tr[@class='cycle2']/td/table/tbody/tr/td/button[@class='gwt-Button gwt-Button[disabled]']");
			
		}
		
		public AdministrationManagementConfigureForm setAddLoginManagementGroup(String jsonArray) throws FormException {
			
			super.selectByXPathAndVisibleText("//table[@class='tableList']/tbody/tr[@class='cycle2']//td/div/table/tbody/tr/td/select[@class='gwt-ListBox']",jsonArray);
					
			return this;
			}
		
		public String getAddLoginManagementGroup() throws FormException {
			
			return super.getValueByXPath( "//table[@class='tableList']/tbody/tr[@class='cycle2']//td/div/table/tbody/tr/td/select[@class='gwt-ListBox']");
		
		}

		public AdministrationManagementConfigureForm setAddUserPermission(String jsonArray) throws FormException {
			
			super.selectByXPathAndVisibleText("//table[@class='tableList']/tbody/tr[@class='cycle2']//td/div/table/tbody/tr/td[2]/select[@class='gwt-ListBox']",jsonArray);
					
			return this;
			}
		
		public String getAddUserPermission() throws FormException {
			
			return super.getValueByXPath( "//table[@class='tableList']/tbody/tr[@class='cycle2']//td/div/table/tbody/tr/td[2]/select[@class='gwt-ListBox']");
		
		}

		
		public AdministrationManagementConfigureForm ClickSaveLdapProperty() throws FormException {
			
			super.clickXPath("//button[@name='btn-save']");
					
			return this;
			}
		
		public AdministrationManagementConfigureForm clickRefreshButton() throws FormException
		{
			this.clickXPath( "//table[contains(@class,'page-UserPageView')]//button[@name='btn-refresh']" );
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
		
		public Boolean isLdapPropertytrueinList(String LP) throws FormException{
			List<WebElement> LPList = getList();

			for( WebElement getListE1 : LPList) {

				if( getListE1.getText().trim().equals( LP ) ) {
			
					return true;

				}	
			}
		
			return false;	
		}

		
		public List<WebElement> getList()  throws FormException {

			String rootPath = "//div[text()='LDAP_use']/../../td";
			String subPath = "//div[@class='gwt-Label']";
			
			List<WebElement> getList = getListByXPath(rootPath, rootPath + subPath);
			System.out.println(getList);
			return getList;
		}
		
		/**
		* Click on logout and accept if alert popup is displayed
		*
		* @param selenium
		* @throws FormException
		*/
		public AdministrationManagementConfigureForm logout() throws FormException {
			
			clickId( "gwt-debug-Logout E4O" );
				
			try {
				
				Alert confirmLogout = selenium.selectAlert();
				
				if ( confirmLogout != null ) { confirmLogout.accept(); }
			
			} catch (NoAlertPresentException e) {
				
				status = true;
			
			}
			
			return this;
		
		}

		/**
		* Logout and close browser
		*
		* @param selenium
		* @throws FormException
		*/
		public  AdministrationManagementConfigureForm quit() throws FormException {
			
			logout();
		
			selenium.close();
		
			return this;
		
		}


}	
	
	

