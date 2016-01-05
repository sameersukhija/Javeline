package com.lumata.e4o.gui.administrationmanager;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.security.Authorization;

public class LoginManagementUserForm extends LoginManagementForm {

	public LoginManagementUserForm( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
	}
	
	
	public LoginManagementUserForm open() throws FormException {
		
		super.open().clickId( "gwt-debug-actrule-administration-loginManagement-user" );
		waitForPageLoad();
		return this;
		
	}	
		public LoginManagementUserForm clickAddButton() throws FormException
		{
			super.clickXPath( "//table[contains(@class,'page-UserPageView')]/tbody/tr/td//table[@class='buttonPanel']/tbody/tr/td//button[@name='btn-add']" );
			return this;
		}

		public LoginManagementUserForm clickCopyButton(String strName) throws FormException
		{
			super.clickXPath( "//div[text()='" + strName + "']/../../td[5]/table/tbody/tr/td[1]/button" );
			return this;
		}
		
		
		public LoginManagementUserForm clickEditButton(String strName) throws FormException
		{
			super.clickXPath( "//div[text()='" + strName + "']/../../td[5]/table/tbody/tr/td[2]/button" );
			return this;
		}

		public LoginManagementUserForm clickDeleteButton(String strName) throws FormException
		{
			super.clickXPath( "//div[text()='" + strName + "']/../../td[5]/table/tbody/tr/td[3]/button" );
			return this;
		}
		public LoginManagementUserForm setUserName(String name) throws FormException {
			sendKeysByXPath ("//td[contains(text(), 'Name')]/parent::tr//input", name);
			
			return this;
		}
		
		public LoginManagementUserForm setUseremail(String email) throws FormException {
			sendKeysByXPath ("//td[contains(text(), 'Email')]/parent::tr//input", email);
			
			return this;
		}
	
		public LoginManagementUserForm setUserpassword(String password) throws FormException {
			sendKeysByXPath ("//td[contains(text(), 'Password')]/parent::tr[@class='cycle2']//input", password );
			
			return this;
		}
		
		
		public LoginManagementUserForm setUserconfirmpassword(String cpassword) throws FormException {
			sendKeysByXPath ("//td[contains(text(), 'Confirm Password')]/parent::tr//input", cpassword );
			
			return this;
		}
		
		
		public LoginManagementUserForm setAgencyName(String aname) throws FormException {
			super.selectByXPathAndVisibleText ("//table[@class='tableList Form marginBottom10px']/tbody/tr[@class='cycle2']/td[2]/select", aname);
			return this;
		}
		
		
		public LoginManagementUserForm clickTabsButton() throws FormException {
			
			clickXPath ("//table[@class='tableList']/tbody/tr[@class='cycle2']/td/table/tbody/tr/td/button[@class='gwt-Button gwt-Button[disabled]']");
			
			return this;
			
		}
		public String getClickTabsButton() throws FormException {
			
			return this.getValueByXPath("//table[@class='tableList']/tbody/tr[@class='cycle2']/td/table/tbody/tr/td/button[@class='gwt-Button gwt-Button[disabled]']");
			
		}
		
		public LoginManagementUserForm setAddLoginManagementGroup(String jsonArray) throws FormException {
			
			super.selectByXPathAndVisibleText("//table[@class='tableList']/tbody/tr[@class='cycle2']//td/div/table/tbody/tr/td/select[@class='gwt-ListBox']",jsonArray);
					
			return this;
			}
		
		public String getAddLoginManagementGroup() throws FormException {
			
			return super.getValueByXPath( "//table[@class='tableList']/tbody/tr[@class='cycle2']//td/div/table/tbody/tr/td/select[@class='gwt-ListBox']");
		
		}

		public LoginManagementUserForm setAddUserPermission(String jsonArray) throws FormException {
			
			super.selectByXPathAndVisibleText("//table[@class='tableList']/tbody/tr[@class='cycle2']//td/div/table/tbody/tr/td[2]/select[@class='gwt-ListBox']",jsonArray);
					
			return this;
			}
		
		public String getAddUserPermission() throws FormException {
			
			return super.getValueByXPath( "//table[@class='tableList']/tbody/tr[@class='cycle2']//td/div/table/tbody/tr/td[2]/select[@class='gwt-ListBox']");
		
		}

		
		public LoginManagementUserForm clickSaveUser() throws FormException {
			
			super.clickXPath("//button[@name='btn-save']");
					
			return this;
			}
		
		public LoginManagementUserForm clickCancel() throws FormException {
			
			super.clickXPath("//button[@name='btn-cancel']");
					
			return this;
			}
		
		public LoginManagementUserForm clickRefreshButton() throws FormException
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
		
		
		
		
		public List<WebElement> getUserPermissionList()  throws FormException {

			String rootPath = "//table[@class='tableList']/tbody/tr[@class='cycle2']";
			String subPath = "//td/div/table/tbody/tr/td[2]/select[@class='gwt-ListBox']";
			//clickXPath(rootPath,rootPath+subPath);
			
			List<WebElement> PermissionList = getListByXPath(rootPath, rootPath + subPath);
			System.out.println(PermissionList);
			return PermissionList;
		}
		
		public Boolean isGroupPermissionModified(String groupPermission) throws FormException{

			String value=null;
			String GP=getValueByXPath("//table[@class='tableList']/tbody/tr[@class='cycle2']//td/div/table/tbody/tr/td[2]/select");
	        if(groupPermission.equals("Manager"))
	        	value="10";
	        else if (groupPermission.equals("Editor"))
	        	value="20";
	        else if (groupPermission.equals("Reader"))
	        	value="30";
	        if (GP.equals(value))
	        {
	            System.out.println("Group Permission modified Successfully");
		   
	    		return true;

	        }        
			
			return false;	
		}
	
		
		public Boolean isUserinList(String USERNAME) throws FormException{
			List<WebElement> userList = getuserList();

			for( WebElement UserListE1 : userList ) {

				if( UserListE1.getText().trim().equals( USERNAME ) ) {
			
					return true;

				}	
			}
		
			return false;	
		}
		
		public List<WebElement> getuserList()  throws FormException {

			String rootPath = "//table[contains(@class,'UserPageView')]";
			String subPath = "//td[@class='column_name']//div[@class='gwt-Label']";
			
			List<WebElement> userList = getListByXPath(rootPath, rootPath + subPath);
			System.out.println(userList);
			return userList;
		}

		
		public Boolean isUserloggedin(String USERNAME) throws FormException{

			String USER=getText(USERNAME, "//table/tbody/tr/td[3]//div[text()='" + USERNAME +  "']");
	        
	        if (USERNAME.equals(USER))
	        {
	            System.out.println("Rajesh logged into E4O Successfully");
		   
	    		return true;

	        }        
			
			return false;	
		}
		/**
		* Click on logout and accept if alert popup is displayed
		*
		* @param selenium
		* @throws FormException
		*/
		
		public  LoginManagementUserForm logout() throws FormException {
			
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
		public  LoginManagementUserForm quit() throws FormException {
			
			logout();
		
			selenium.close();
		
			return this;
		
		}

		
				


}	
	
	

