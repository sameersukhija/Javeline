package com.lumata.e4o.gui.campaignmanager;

import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaignType;

public class CampaignTypeForm extends CampaignManagerForm {

	private JSONCampaignType campaignTypeCfg;
	
    public enum ElementErrorActionType {

        RETURN_ERROR,
        ABORT,
        ADD_TIMESTAMP_TO_FIELD;

    }; 
    
	public CampaignTypeForm( SeleniumWebDriver selenium, JSONCampaignType campaignTypeCfg, long timeout, long interval ) {
		
		super(selenium, timeout, interval);
		
		this.campaignTypeCfg = campaignTypeCfg;
		
	}	
	
	public CampaignTypeForm openForm() throws FormException {
		
		super.openForm().clickId( "gwt-debug-InputCMCampaignType" );
		
		return this;
		
	}
	
	public CampaignTypeForm addCampaignTypes() throws FormException, JSONException {
		
		JSONArray campaignTypes = campaignTypeCfg.getList();
		
		for( int campaignTypeIndex = 0; campaignTypeIndex < campaignTypes.length(); campaignTypeIndex++ ) {
			
			campaignTypeCfg.setCampaignTypeById( campaignTypeIndex );
			
			if( campaignTypeCfg.getEnabled() ) {
				
				clickXPath( "//button[@name='btn-add' and @title='Add Campaign Type']" ).
				configureCampaignType().
				saveCampaignType().
				manageAddCampaignTypeErrorAction( campaignTypeCfg.getErrorActions().getString( "ELEMENT_ALREADY_EXISTS" ) );
				
				if( null != campaignTypeCfg.getMetaType() ) {
					
					clickXPath( "//button[@name='btn-metaType']" ).
					configureMetaType().
					saveMetaType();
				
				}
				
			}
					
		}
		
		return this;
		
	}
	
	public CampaignTypeForm configureCampaignType() throws FormException, JSONException {
		
		sendKeysById( "gwt-debug-Campaign Type Name", campaignTypeCfg.getTypeName() ).
		sendKeysById( "gwt-debug-Max Occurrence Campaign Type", campaignTypeCfg.getMaxOccurence() ).
		sendKeysById( "gwt-debug-Max Simultaneous Campaign Type", campaignTypeCfg.getMaxSimultaneous() ).
		clickId( "gwt-debug-Prediction Impact Campaign Type" ).
		clickId( "gwt-debug-Full Statistics Campaign Type" );		
		
		if( isEnabledById( "gwt-debug-Waiting Period Campaign Type" ) ) {
		
			sendKeysById( "gwt-debug-Waiting Period Campaign Type", campaignTypeCfg.getWaitingPeriodInDays() );
		
		}
		
		return this;
		
	}
	
	public CampaignTypeForm manageAddCampaignTypeErrorAction( String errorAction ) throws FormException {
		
		try {
		
			searchByXPath( "//div[@class='gwt-PopupPanel']//div[@class='gwt-Label' and contains(text(), 'Name already used')]", 2000, 50 );
		
		} catch( FormException fe ) {
			
			// no error to manage
			
		}
			
		if( status ) {
		
			switch( ElementErrorActionType.valueOf( errorAction ) ) {
			
				case RETURN_ERROR: {  
					
					throw new FormException( "Error in the form navigation" );
									
				}
				case ABORT: {  
										
					cancelCampaignType();				
					
					break; 				
				}
				case ADD_TIMESTAMP_TO_FIELD: {  
					
					String name_with_timestamp = campaignTypeCfg.getTypeName() + "_" + String.valueOf( TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) );
					
					campaignTypeCfg.setTypeName( name_with_timestamp );					
					
					clearByName( "name" ).
					sendKeysByName( "name", campaignTypeCfg.getTypeName() ).
					saveCampaignType();					
					
					break; 				
				}
			
			}
		
		}
		
		status = true;
		
		return this;
		
	}

	public CampaignTypeForm saveCampaignType() throws FormException {
		
		super.clickId( "gwt-debug-Campaign Type Save" );
		
		return this;
		
	}
	
	public CampaignTypeForm cancelCampaignType() throws FormException {
		
		super.clickId( "gwt-debug-Campaign Type Cancel" );
		
		return this;
		
	}
	
	public CampaignTypeForm configureMetaType() throws FormException, JSONException {
		
		String dialogEditGeneralLimitsXPath = "//div[@class='dialogMiddleCenterInner dialogContent']//table[@class='tableList Form']";
		String dialogEditGeneralLimitsMaxOccurenceXPath = dialogEditGeneralLimitsXPath + "//td[@class='headers' and text() = 'Max Occurence']/parent::tr//input";
		String dialogEditGeneralLimitsMaxSimultaneousXPath = dialogEditGeneralLimitsXPath + "//td[@class='headers' and text() = 'Max Simultaneous']/parent::tr//input";
		String dialogEditGeneralLimitsWaitingPeriodInDaysXPath = dialogEditGeneralLimitsXPath + "//td[@class='headers' and text() = 'Waiting Period (in days)']/parent::tr//input";
		
		clearByXPath( dialogEditGeneralLimitsMaxOccurenceXPath ).
		sendKeysByXPath( dialogEditGeneralLimitsMaxOccurenceXPath, campaignTypeCfg.getMetaTypeMaxOccurence() ).
		clearByXPath( dialogEditGeneralLimitsMaxSimultaneousXPath ).
		sendKeysByXPath( dialogEditGeneralLimitsMaxSimultaneousXPath, campaignTypeCfg.getMetaTypeMaxSimultaneous() );
		
		if( isEnabledByXPath( dialogEditGeneralLimitsWaitingPeriodInDaysXPath ) ) {
			
			sendKeysById( dialogEditGeneralLimitsWaitingPeriodInDaysXPath, campaignTypeCfg.getMetaTypeWaitingPeriodInDays() );
		
		}
		
		return this;
		
	}
	
	public CampaignTypeForm saveMetaType() throws FormException {
		
		super.clickXPath( "//div[@class='dialogMiddleCenterInner dialogContent']//button[@name='btn-save']" );
		
		return this;
		
	}
	
	public CampaignTypeForm cancelMetaType() throws FormException {
		
		super.clickXPath( "//div[@class='dialogMiddleCenterInner dialogContent']//button[@name='btn-cancel']" );
		
		return this;
		
	}
	
	
	@Override
	public CampaignTypeForm execJavascript( String command ) {
		
		super.execJavascript( command );
		
		return this;
		
	}
	
	@Override
	public CampaignTypeForm clickName( String name ) throws FormException {
		
		super.clickName( name );
		
		return this;
		
	}
	
	@Override
	public CampaignTypeForm clickXPath( String xpath ) throws FormException {
		
		super.clickXPath( xpath );
		
		return this;
		
	}
	
	@Override
	public CampaignTypeForm clickLink( String link ) throws FormException {
		
		super.clickLink( link );
		
		return this;
		
	}
	
	@Override
	public CampaignTypeForm sendKeysByName( String name, String text ) throws FormException {
		
		super.sendKeysByName( name, text );
		
		return this;
	
	}
	
	@Override
	public CampaignTypeForm sendKeysByXPath( String xpath, String text ) throws FormException {
		
		super.sendKeysByXPath( xpath, text );
		
		return this;
	
	}
	
	@Override
	public CampaignTypeForm sendKeysByLink( String link, String text ) throws FormException {
		
		super.sendKeysByLink( link, text );
		
		return this;
	
	}
	
	@Override
	public CampaignTypeForm selectByName( String name, String label ) throws FormException {
		
		super.selectByName( name, label );
		
		return this;
		
	}
	
	@Override
	public CampaignTypeForm clearByName( String name ) throws FormException {
		
		super.clearByName( name );
		
		return this;
		
	}
	
	@Override
	public CampaignTypeForm clearByXPath( String xpath ) throws FormException {
		
		super.clearByXPath( xpath );
		
		return this;
		
	}
	
	@Override
	public CampaignTypeForm typeByName( String name, String text ) throws FormException {
		
		super.typeByName( name, text );
		
		return this;
	
	}
	
	@Override
	public CampaignTypeForm multiselectByXPathAndVisibleText( String xpath, JSONArray list ) throws FormException {
		
		super.multiselectByXPathAndVisibleText( xpath, list );	
		
		return this;
		
	}
	
	@Override
	public CampaignTypeForm selectByNameAndVisibleText( String name, String text ) throws FormException {
		
		super.selectByNameAndVisibleText( name, text );	
		
		return this;
		
	}
	
	@Override
	public boolean isEnabledById( String id ) throws FormException {
					
		return super.isEnabledById( id );
		
	}
	
	@Override
	public boolean isEnabledByXPath( String xpath ) throws FormException {
					
		return super.isEnabledByXPath( xpath );
		
	}
	
}
