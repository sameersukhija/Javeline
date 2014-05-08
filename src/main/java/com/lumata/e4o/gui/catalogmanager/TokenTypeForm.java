package com.lumata.e4o.gui.catalogmanager;

import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.Form;
import com.lumata.e4o.json.gui.catalogmanager.JSONTokenType;

public class TokenTypeForm extends OfferOptimisationForm {

	private static final Logger logger = LoggerFactory.getLogger( TokenTypeForm.class);
	
	private JSONTokenType tokenTypeCfg;
	
	public enum TokenTypeErrorAction { 
		
		TOKEN_TYPE_ALREADY_EXISTS;
				
	};
	
	public enum TokenTypeErrorActionType { 
		
		RETURN_ERROR,
		ABORT,
		ADD_TIMESTAMP_TO_TOKEN_TYPE_NAME;
				
	};
	
	public TokenTypeForm( SeleniumWebDriver selenium, JSONTokenType tokenTypeCfg, long timeout, long interval ) {
		
		super(selenium, timeout, interval);
		
		this.tokenTypeCfg = tokenTypeCfg;
		
	}	
	
	public TokenTypeForm open() throws FormException {
		
		super.open( OfferOptimisationSection.TOKEN_TYPE );
		
		return this;
		
	}

	public TokenTypeForm addTokenTypes() throws FormException, JSONException {
		
		JSONArray tokenTypes = tokenTypeCfg.getList();
		
		for( int tokenTypeIndex = 0; tokenTypeIndex < tokenTypes.length(); tokenTypeIndex++ ) {
			
			tokenTypeCfg.setTokenTypeById( tokenTypeIndex );
			
			if( tokenTypeCfg.getEnabled() ) {
			
				clickLink( "Add" ).
				configureTokenType().
				saveTokenType();
				
			}
					
		}
		
		return this;
		
	}
	
	public TokenTypeForm configureTokenType() throws FormException {
		
		sendKeysByName( "name", tokenTypeCfg.getName() ).
		sendKeysByXPath( "//textarea[@ng-model='tokenType.description']", tokenTypeCfg.getDescription() ).
		sendKeysByXPath( "//input[@ng-model='tokenType.imageUrl']", tokenTypeCfg.getImageUrl() ).
		selectByName( "format", tokenTypeCfg.getFormat() ).
		selectByName( "validityUnit", tokenTypeCfg.getValidityUnit() );
		//typeByName( "validity", tokenTypeCfg.getValidity() )
		//;

		//execJavascript("$('[name=validity]').val(100);");
		//execJavascript("document.getElementsByName('validity')[0].value='100';");
		
		execJavascript("document.getElementsByName('validity')[0].stepUp(1)");
		
		//execJavascript("$('[name=validity]').keypress(100);");
		
		
		//;
		
		//typeByName( "validity", tokenTypeCfg.getValidity() );
		
		//WebElement element = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.NAME, "validity" );

		//JavascriptLibrary javascript = new JavascriptLibrary();

		//javascript.callEmbeddedSelenium( selenium.getWrappedDriver(), "triggerEvent", element, "onchange" );

		
		
		//JavascriptExecutor jsexec = (JavascriptExecutor) selenium.getWrappedDriver();
		
		//jsexec.executeScript("$('[name=validity]').change();");

		//jsexec.executeScript("$('[name=validity]').val(100);");

		
		if( tokenTypeCfg.getUsageUnlimited() == true ) {
			
			clickId( "usageUnlimited-1" ); 
		
		} else {
			
			clickId( "usageUnlimited-0" ).
			clearByName( "usage" ).
			sendKeysByName( "usage", tokenTypeCfg.getUsage() );
			
		}
				
		return this;
		
	}
	
	public Form saveTokenType() throws FormException {
		
		clickName( "btn-add" );
		
		return this;
		
	}
	
	@Override
	public TokenTypeForm clickName( String name ) throws FormException {
		
		super.clickName( name );
		
		return this;
		
	}
	
	@Override
	public TokenTypeForm clickLink( String link ) throws FormException {
		
		super.clickLink( link );
		
		return this;
		
	}
	
	@Override
	public TokenTypeForm sendKeysByName( String name, String text ) throws FormException {
		
		super.sendKeysByName( name, text );
		
		return this;
	
	}
	
	@Override
	public TokenTypeForm sendKeysByXPath( String xpath, String text ) throws FormException {
		
		super.sendKeysByXPath( xpath, text );
		
		return this;
	
	}
	
	@Override
	public TokenTypeForm sendKeysByLink( String link, String text ) throws FormException {
		
		super.sendKeysByLink( link, text );
		
		return this;
	
	}
	
	@Override
	public TokenTypeForm selectByName( String name, String label ) throws FormException {
		
		super.selectByName( name, label );
		
		return this;
		
	}
	
	@Override
	public TokenTypeForm clearByName( String xpath ) throws FormException {
		
		super.clearByName( xpath );
		
		return this;
		
	}
	
	@Override
	public TokenTypeForm typeByName( String name, String text ) throws FormException {
		
		super.typeByName( name, text );
		
		return this;
	
	}
	
	/*
	public static boolean open( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		return OfferOptimisationForm.open(selenium, OfferOptimisationForm.OfferOptimisationSection.TOKEN_TYPE, timeout, interval);
		
	}
	
	public static boolean addTokenType( SeleniumWebDriver selenium, JSONTokenType tokenType, long timeout, long interval ) {
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for link=Add") );
		
		WebElement addTokenTypeBtn = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.LINK, "Add", timeout, interval);
		if( addTokenTypeBtn == null ) { return false; }
			
		addTokenTypeBtn.click();
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for name = name") );
		
		WebElement tokenTypeName = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.NAME, "name", timeout, interval);
		if( tokenTypeName == null ) { return false; }
			
		tokenTypeName.sendKeys(tokenType.getName());
				
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for name = format") );
		
		WebElement tokenTypeFormat = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.NAME, "format", timeout, interval);
		if( tokenTypeFormat == null ) { return false; }
			
		selenium.select( "name=format", "label=" + tokenType.getFormat() );
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for name = validityUnit") );
		
		WebElement tokenTypeValidityUnit = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.NAME, "validityUnit", timeout, interval);
		if( tokenTypeValidityUnit == null ) { return false; }
			
		selenium.select( "name=validityUnit", "label=" + TokenTypeCfg.TokenTypeValidity.valueOf( tokenType.getValidityUnit() ) );

		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for name = validity") );
		
		WebElement tokenTypeValidity = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.NAME, "validity", timeout, interval);
		if( tokenTypeValidity == null ) { return false; }
			
		tokenTypeValidity.sendKeys(tokenType.getValidity());

		if( tokenType.getUsageUnlimited() == true ) {
			
			logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id = usageUnlimited-1") );
			
			WebElement tokenTypeUsageUnlimited1 = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "usageUnlimited-1", timeout, interval);
			if( tokenTypeUsageUnlimited1 == null ) { return false; }
				
			tokenTypeUsageUnlimited1.click();
		
		} else {
			
			logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for id = usageUnlimited-0") );
			
			WebElement tokenTypeUsageUnlimited0 = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "usageUnlimited-0", timeout, interval);
			if( tokenTypeUsageUnlimited0 == null ) { return false; }
				
			tokenTypeUsageUnlimited0.click();
	
			logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for name = usage") );
			
			WebElement tokenTypeUsage = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.NAME, "usage", timeout, interval);
			if( tokenTypeUsage == null ) { return false; }
			
			tokenTypeUsage.clear();
			
			tokenTypeUsage.sendKeys( tokenType.getUsage() );
		
		}
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for name = btn-add") );
		
		WebElement tokenTypeSave = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.NAME, "btn-add", timeout, interval);
		if( tokenTypeSave == null ) { return false; }
		tokenTypeSave.click();		
		
		return TokenTypeForm.manageErrorAction( selenium, tokenType, timeout, interval );
		
	}
	
	public static boolean manageErrorAction( SeleniumWebDriver selenium, JSONTokenType tokenType, long timeout, long interval ) {
		
		logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for error message") );
		
		AngularFrame.close( selenium, timeout, interval );
		
		WebElement btnMessageError = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, "//div[@class='gwt-DialogBox errorDialog']//button", 2000, 100);
		
		if( btnMessageError != null ) { 
			
			btnMessageError.click();
			
			if( !AngularFrame.open( selenium, timeout, interval ) ) { return false; }
			
			JSONObject error_actions = tokenType.getErrorActions();
			
			if( error_actions == null ) {
				
				logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new token type ( Wrong json configuration )" ) );
				
				return false;
								
			} else {
				
				try {
					
					switch( TokenTypeErrorActionType.valueOf( error_actions.getString( TokenTypeErrorAction.TOKEN_TYPE_ALREADY_EXISTS.name() ) ) ) {
						
						case RETURN_ERROR:{
							
							logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Cannot add a new token type ( token type name already exist )" ) );
							
							return false;
							
						}
						case ADD_TIMESTAMP_TO_TOKEN_TYPE_NAME:{
														
							String name_with_timestamp = tokenType.getName() + "_" + String.valueOf( TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) );
														
							tokenType.setName( name_with_timestamp );							
							
							WebElement tokenTypeName = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.NAME, "name", timeout, interval);
							if( tokenTypeName == null ) { return false; }
							
							tokenTypeName.clear();
							tokenTypeName.sendKeys(tokenType.getName());
							
							logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for name=btn-add") );
							
							WebElement tokenTypeSave = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.NAME, "btn-add", timeout, interval);
							if( tokenTypeSave == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Abort token type creation" ) ); return false; }	
							tokenTypeSave.click();
							
							return true;
							
						}
						case ABORT:{
							
							logger.info( Log.CHECKING.createMessage( selenium.getTestName(), "for name=btn-cancel") );
							
							WebElement tokenTypeCancel = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, "html/body/div[1]/div[2]/div/div/div[2]/a[1]", timeout, interval);
							if( tokenTypeCancel == null ) { logger.error(  Log.FAILED.createMessage( selenium.getTestName() , "Abort token type creation" ) ); return false; }	
							tokenTypeCancel.click();
							
							return true;
															
						}
						
					}
				
				} catch( Exception e ) {}
				
			}
							
		}
			
		return AngularFrame.open( selenium, timeout, interval );
		
	}
	*/
}
