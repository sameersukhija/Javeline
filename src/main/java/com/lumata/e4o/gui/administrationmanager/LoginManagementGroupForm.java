package com.lumata.e4o.gui.administrationmanager;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.openqa.selenium.WebElement;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.json.gui.administrationmanager.JSONLMGroup;
import com.lumata.e4o.json.gui.catalogmanager.JSONTokenType;

public class LoginManagementGroupForm extends LoginManagementForm {

	private List<LoginManagementGroup> groupList;
	private JSONLMGroup lmGroupCfg;
	
	String groupTableXPath = "//table[@class='gwt-DecoratedTabPanel tab-LoginManagementPageView']//table[@class='tableList']//div[text()='List of groups']/parent::td/parent::tr/parent::tbody";
	String groupTableElementXPath = groupTableXPath + "//tr[contains(@class, 'contentRow')]";
	
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
		
		String groupTableElementEditXPath = groupTableElementXPath + "//div[text()='" + groupName + "']/parent::td/parent::tr//button[@title='Edit' and @name='btn-edit']";
		String editGroupTableXPath = "//div[@class='gwt-DialogBox']//div[text()='Edit group']/ancestor::tbody//table[contains(@class, 'tableList Form')]";
				
		clickXPath( groupTableElementEditXPath );
		//sendKeysByXPath( editGroupTableXPath + "//td[contains(text(), 'Name')]/parent::tr//input", "customercare" ).
		if( isSelectedByXPath( editGroupTableXPath + "//td[contains(text(), 'Is removable')]/parent::tr//input" ) != lmGroupCfg.getIsRemovable() ) { clickXPath( editGroupTableXPath + "//td[contains(text(), 'Is removable')]/parent::tr//input" ); }
		if( isSelectedByXPath( editGroupTableXPath + "//td[contains(text(), 'Has agencies')]/parent::tr//input" ) != lmGroupCfg.getHasAgencies() ) { clickXPath( editGroupTableXPath + "//td[contains(text(), 'Has agencies')]/parent::tr//input" ); }
		if( isSelectedByXPath( editGroupTableXPath + "//td[contains(text(), 'Can users be added')]/parent::tr//input" ) != lmGroupCfg.getCanUsersBeAdded() ) { clickXPath( editGroupTableXPath + "//td[contains(text(), 'Can users be added')]/parent::tr//input" ); }
		if( isSelectedByXPath( editGroupTableXPath + "//td[contains(text(), 'Can be selected in group list')]/parent::tr//input" ) != lmGroupCfg.getCanBeSelectedInGroupList() ) { clickXPath( editGroupTableXPath + "//td[contains(text(), 'Can be selected in group list')]/parent::tr//input" ); }
		
		return this;
		
	}
	
	@Override
	public List<WebElement> searchListByXPath( String rootTag, String tag ) throws FormException {
		
		return super.searchListByXPath( rootTag, tag );
		
	}
	
}
