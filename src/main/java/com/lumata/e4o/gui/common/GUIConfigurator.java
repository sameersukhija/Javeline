package com.lumata.e4o.gui.common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.system.User;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.administrationmanager.SalesChannelsForm;
import com.lumata.e4o.gui.campaignmanager.CampaignModelForm;
import com.lumata.e4o.gui.catalogmanager.RulesForm;
import com.lumata.e4o.gui.catalogmanager.TokenTypeForm;
import com.lumata.e4o.gui.security.Authorization;
import com.lumata.e4o.json.gui.administrationmanager.JSONSalesChannels;
import com.lumata.e4o.json.gui.campaignmanager.JSONCampaignModel;
import com.lumata.e4o.json.gui.catalogmanager.JSONRules;
import com.lumata.e4o.json.gui.catalogmanager.JSONTokenType;

public class GUIConfigurator {

	private static final Logger logger = LoggerFactory.getLogger( GUIConfigurator.class );
	
	private JSONArray configurator;
	private SeleniumWebDriver seleniumWebDriver;
	private User user;
	private int timeout;
	private int attempt_timeout;

	private enum JSONKey {
		section, folder, file
	}

	private enum GUISection {
		campaigns, campaignModels, loyalty, offers, rules, tokens, salesChannels
	}
	
	public GUIConfigurator( final SeleniumWebDriver seleniumWebDriver, final User user, final int timeout, final int attempt_timeout ) {
		this.seleniumWebDriver = seleniumWebDriver;
		this.user = user;
		this.timeout = timeout;
		this.attempt_timeout = attempt_timeout;
		this.configurator = new JSONArray();
	}
	
	public static GUIConfigurator getInstance( final SeleniumWebDriver seleniumWebDriver, final User user, final int timeout, final int attempt_timeout ) {
		return new GUIConfigurator( seleniumWebDriver, user, timeout, attempt_timeout );
	}
	
	public boolean configureGUI( final String folder, final String file ) throws FormException, JSONException, JSONSException {
		
		loadConfiguration( folder,file );
		
		if( configurator.length() > 0 ) {
		
			if( !login() ) { 
				
				logger.error( Log.FAILED.createMessage( "The GUI cannot be access" ) );
				
				return false;
				
			}
			
			for( int c = 0; c < configurator.length(); c++ ) {
				
				boolean success = true;
				
				JSONObject guiConfig = configurator.getJSONObject( c );
							
				System.out.println( guiConfig.getString( JSONKey.section.name() ) );
				
				if( !guiConfig.has( JSONKey.section.name() ) ) {  }
				
				switch( GUISection.valueOf( guiConfig.getString( JSONKey.section.name() ) ) ) {
				
					case campaigns: {
						System.out.println( guiConfig.getString( JSONKey.file.name() ) );
						break;
					}
					case campaignModels: {
						CampaignModelForm campaignModelForm = new CampaignModelForm( seleniumWebDriver, new JSONCampaignModel( guiConfig.getString( JSONKey.folder.name() ), guiConfig.getString( JSONKey.file.name() ) ), timeout, attempt_timeout );
						success = campaignModelForm.open().addCampaignModels().navigate();
						break;
					}
					case loyalty: {
						
						break;
					}
					case offers: {
						
						break;
					}
					case rules: {
						RulesForm rulesForm = new RulesForm( seleniumWebDriver, new JSONRules( guiConfig.getString( JSONKey.folder.name() ), guiConfig.getString( JSONKey.file.name() ) ), timeout, attempt_timeout );
						success = rulesForm.open().addRules().close().navigate();
						break;
					}
					case tokens: {
						TokenTypeForm tokenTypeForm = new TokenTypeForm( seleniumWebDriver, new JSONTokenType( guiConfig.getString( JSONKey.folder.name() ), guiConfig.getString( JSONKey.file.name() ) ), timeout, attempt_timeout );
						success = tokenTypeForm.open().addTokenTypes().close().navigate();
						break;
					}
					case salesChannels: {
						SalesChannelsForm salesChannelsForm = new SalesChannelsForm( seleniumWebDriver, new JSONSalesChannels( guiConfig.getString( JSONKey.folder.name() ), guiConfig.getString( JSONKey.file.name() ) ), timeout, attempt_timeout );
						success = salesChannelsForm.open().addSalesChannels().navigate();
						break;
					}
					default: {}
				}
				
				if( !success ) { return false; }
				
			} 
			
			return logout();
		
		}
		
		return true;
		
	}
	
	private void loadConfiguration( final String folder, final String file ) {
		
		try {
						
			JSONObject jsonConf = JSONUtils.loadJSONResource( folder, file + Format.JSON_EXTENSION );
			
			this.configurator =  jsonConf.getJSONArray( "configurator" );	
					
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
	}
	
	private boolean login() throws FormException {
		return Authorization.getInstance( seleniumWebDriver, timeout, attempt_timeout).login( user ).navigate();
	}
	
	public boolean logout() throws FormException {		
		return Authorization.getInstance( seleniumWebDriver, timeout, attempt_timeout).quit().navigate();		
	}
	
}
