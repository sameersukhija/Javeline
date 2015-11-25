package com.lumata.e4o.gui.administrationmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.catalogmanager.OffersForm;
import com.lumata.e4o.json.gui.administrationmanager.JSONLMGroup;
import com.lumata.e4o.json.gui.catalogmanager.JSONTokenType;

public class LoginManagementGroupForm extends LoginManagementForm {

	private List<LoginManagementGroup> groupList;
	private JSONLMGroup lmGroupCfg;
	
	String groupTableXPath = "//table[@class='gwt-DecoratedTabPanel tab-LoginManagementPageView']//table[@class='tableList']//div[text()='List of groups']/parent::td/parent::tr/parent::tbody";
	String groupTableElementXPath = groupTableXPath + "//tr[contains(@class, 'contentRow')]";
	private JSONLMGroup setupLMG;
	private String ctabs;
	private int i=1;	
	
	public class LoginManagementGroup {
		
		private String groupName;
		private String groupTabs;
			
		public LoginManagementGroup() {}		
		
		public LoginManagementGroup( String groupName, String groupTabs ) {
			
			this.groupName = groupName;
			
			this.groupTabs = groupTabs;
			
		}
		
		public String getGroupName() {
			return groupName;
		}
		
		public String getGroupTabs() {
			return groupTabs;
		}

		public void setGroupName( String groupName ) {
			this.groupName = groupName;
		}
		
		public void setGroupTabs( String groupTabs ) {
			this.groupTabs = groupTabs;
		}
		
	}
	
	public LoginManagementGroupForm( SeleniumWebDriver selenium, JSONLMGroup lmGroupCfg, long timeout, long interval ) {
		
		super( selenium, timeout, interval );
		
		this.lmGroupCfg = lmGroupCfg;
		
	}
	
	public LoginManagementGroupForm open() throws FormException {
		
		super.open().clickId( "gwt-debug-actrule-administration-loginManagement-group" );
		
		return this;
		
	}	
	
	public LoginManagementGroupForm loadGroupList() throws FormException {
		
		groupList = new ArrayList<LoginManagementGroup>();
				
		List<WebElement> groupWebElementList = searchListByXPath( groupTableXPath, groupTableElementXPath );
		
		for( int i = 0; i < groupWebElementList.size(); i++ ) {
			
			LoginManagementGroup loginManagementGroup = new LoginManagementGroup(); 
			
			String[] groupInfo = groupWebElementList.get( i ).getText().split( "\\n" );
			
			if( groupInfo.length >= 1 ) { loginManagementGroup.setGroupName( groupInfo[ 0 ] ); }
			else { loginManagementGroup.setGroupName( "" ); }
			
			if( groupInfo.length >= 2 ) { loginManagementGroup.setGroupTabs( groupInfo[ 1 ] ); }
			else { loginManagementGroup.setGroupTabs( "" ); }
			
			groupList.add( loginManagementGroup );
									
		}
		
		return this;
		
	}
	
	public List<LoginManagementGroup> getGroupList() {
		return groupList;
	}
	
	public Boolean isGroup( String groupName ) throws FormException {
		
		loadGroupList();
		
		if( null != groupList ) {
			
			for( int g = 0; g < groupList.size(); g++ ) {
				
				if( null != groupList.get( g ).getGroupName() && groupList.get( g ).getGroupName().equals( groupName )) {
					
					return true;
					
				}
				
			}
			
		}
		
		return false;
		
	}
	
	public LoginManagementGroupForm editGroup( String groupName ) throws FormException, JSONException {
		
		String groupTableElementEditXPath = "//div[text()='" + groupName + "']/parent::td/parent::tr//button[@title='Edit' and @name='btn-edit']";
				
		clickXPath( groupTableElementEditXPath );
		clickXPath(  "//td[contains(text(), 'Has agencies')]/parent::tr//input" ); 
		
		return this;
		
	}
	
	public LoginManagementGroupForm deleteGroup( String groupName ) throws FormException, JSONException {
		
		String groupTableElementEditXPath = "//div[text()='" + groupName + "']/parent::td/parent::tr//button[@title='Delete' and @name='btn-delete']";
				
		clickXPath( groupTableElementEditXPath );
		
		return this;
	}
	
	public LoginManagementGroupForm clickAddButton() throws FormException
	{
		this.clickXPath( "//table[contains(@class,'page-GroupPageView')]//button[@name='btn-add']" );
		return this;
	}
	
	public LoginManagementGroupForm clickRefreshButton() throws FormException
	{
		this.clickXPath( "//table[contains(@class,'page-GroupPageView')]//button[@name='btn-refresh']" );
		return this;
	}
	
	public LoginManagementGroupForm  setGroupName(String name) throws FormException {
		
		sendKeysByXPath ("//td[contains(text(), 'Name')]/parent::tr//input", name);
		
		return this;
		
	}
	public String getGroupName() throws FormException {
		
		return this.getValueByXPath("//td[contains(text(), 'Name')]/parent::tr//input");
		
	}
	
	public LoginManagementGroupForm setGroupIsRemovable(Boolean boolean1) throws FormException {
		if (false != boolean1)
		{
		
		clickXPath ("//td[contains(text(), 'Is removable')]/parent::tr//input");
		}
		return this;
		
	}
	public String getGroupIsRemovable() throws FormException {
		
		return this.getValueByXPath("//td[contains(text(), 'Is removable')]/parent::tr//input");
		
	}
	
	public LoginManagementGroupForm setGroupHasAgencies(Boolean boolean1) throws FormException {
		
		if (false != boolean1)
		{	
		clickXPath ("//td[contains(text(), 'Has agencies')]/parent::tr//input");
		}
		return this;
		
	}
	public String getGroupHasAgencies() throws FormException {
		
		return this.getValueByXPath("//td[contains(text(), 'Has agencies')]/parent::tr//input");
		
	}
	
	public LoginManagementGroupForm setGroupUserAddition(Boolean boolean1) throws FormException {
		
		if (false != boolean1)
		{	
		clickXPath ("//td[contains(text(), 'Can users be added')]/parent::tr//input");
		}
		return this;
		
	}
	public String getGroupUserAddition() throws FormException {
		
		return this.getValueByXPath("//td[contains(text(), 'Can users be added')]/parent::tr//input");
		
	}
	
	public LoginManagementGroupForm setGroupUserselectedinList(Boolean boolean1) throws FormException {
		
		if (false != boolean1)
		{	
		clickXPath ("//td[contains(text(), 'Can be selected in group list')]/parent::tr//input");
		}
		return this;
		
	}
	public String getGroupUserselectedinList() throws FormException {
		
		return this.getValueByXPath("//td[contains(text(), 'Can be selected in group list')]/parent::tr//input");
		
	}
	
	public LoginManagementGroupForm ClickTabsButton() throws FormException {
		
		clickXPath ("//table[@class='tableList']/tbody/tr[@class='cycle2']/td/table/tbody/tr/td/button[@class='gwt-Button gwt-Button[disabled]']");
		
		return this;
		
	}
	public String getClickTabsButton() throws FormException {
		
		return this.getValueByXPath("//table[@class='tableList']/tbody/tr[@class='cycle2']/td/table/tbody/tr/td/button[@class='gwt-Button gwt-Button[disabled]']");
		
	}
	
	public LoginManagementGroupForm setAddCampaignsTab(String jsonArray) throws FormException, JSONSException {
		
		super.selectByXPathAndVisibleText("//table[@class='tableList']/tbody/tr[@class='cycle2']//tr[1]/td/div/table/tbody/tr/td/select[@class='gwt-ListBox']", jsonArray);	
		AddCampaignCreationTab( jsonArray);
	       return this;
		}
	
	
	public String getAddCampaignsTab() throws FormException {
		
		return super.getValueByXPath( "//table[@class='tableList']/tbody/tr[@class='cycle2']//tr[1]/td/div/table/tbody/tr/td/select[@class='gwt-ListBox']");
	
	}

	public LoginManagementGroupForm AddCampaignCreationTab(String jsonArray) throws FormException {
		
		super.selectByXPathAndVisibleText("//table[@class='tableList']/tbody/tr[@class='cycle2']//tr[2]/td/div/table/tbody/tr/td/select[@class='gwt-ListBox']", jsonArray);
		
		return this;
		}
	
	public String getAddCampaignCreationTab() throws FormException {
		
		return super.getValueByXPath( "//table[@class='tableList']/tbody/tr[@class='cycle2']//tr[2]/td/div/table/tbody/tr/td/select[@class='gwt-ListBox']");
	
	}

	public LoginManagementGroupForm AddCampaignModelTab(String jsonArray) throws FormException {
		
		super.selectByXPathAndVisibleText("//table[@class='tableList']/tbody/tr[@class='cycle2']//tr[3]/td/div/table/tbody/tr/td/select[@class='gwt-ListBox']",  jsonArray);
				
		return this;
		}
	
	public String getAddCampaignModelTab() throws FormException {
		
		return super.getValueByXPath( "//table[@class='tableList']/tbody/tr[@class='cycle2']//tr[3]/td/div/table/tbody/tr/td/select[@class='gwt-ListBox']");
	
	}

	public LoginManagementGroupForm AddCatalogueTab(String jsonArray) throws FormException {
		
		super.selectByXPathAndVisibleText("//table[@class='tableList']/tbody/tr[@class='cycle2']//tr[4]/td/div/table/tbody/tr/td/select[@class='gwt-ListBox']",  jsonArray);
				
		return this;
		}
	
	public String getAddCatalogueTab() throws FormException {
		
		return super.getValueByXPath( "//table[@class='tableList']/tbody/tr[@class='cycle2']//tr[4]/td/div/table/tbody/tr/td/select[@class='gwt-ListBox']");
	
	}

	
	public LoginManagementGroupForm ClickSaveGroup() throws FormException {
		
		super.clickXPath("//button[@name='btn-save']");
				
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
	
		
	
	@Override
	public List<WebElement> searchListByXPath( String rootTag, String tag ) throws FormException {
		
		return super.searchListByXPath( rootTag, tag );
		
	}

	public Boolean isGroupinList(String GROUPNAME) throws FormException{
		List<WebElement> groupList = getgroupList();

		for( WebElement GroupListE1 : groupList ) {

			if( GroupListE1.getText().trim().equals( GROUPNAME ) ) {
		
				return true;

			}	
		}
	
		return false;	
	}

	
	public List<WebElement> getgroupList()  throws FormException {

		String rootPath = "//table[contains(@class,'GroupPageView')]";
		String subPath = "//td[@class='column_name']//div[@class='gwt-Label']";
				//"//div[text()='"+Group_Name+"']";
		
		List<WebElement> groupList = getListByXPath(rootPath, rootPath + subPath);
		System.out.println(groupList);
		return groupList;
	}
	
}