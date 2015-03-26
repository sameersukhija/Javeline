package com.lumata.e4o.gui.catalogmanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.openqa.selenium.WebElement;

import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumUtils.SearchBy;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.common.PlaceHolderDate;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.AngularCalendarForm;
import com.lumata.e4o.json.gui.catalogmanager.JSONTokenType;

public class TokenTypeForm extends OfferOptimisationForm {

	private JSONTokenType tokenTypeCfg;
	
	public enum TokenFormat {
		
		br("br-[0123456789abcdef]{5}"),
		sl("sl-[0123456789abcdef]{5}"),
		gl("bl-[0123456789abcdef]{5}"),
		imm5("[ACDEFGHJKMNPQRTWXY34679]{5}"),
		imm6("[ACDEFGHJKMNPQRTWXY34679]{6}"),
		imm7("[ACDEFGHJKMNPQRTWXY34679]{7}");
		
		private String value;
		
		TokenFormat( String value ) {
			
			this.value = value;
			
		}
		
		public String value() {
			
			return this.value;
			
		}
		
	}
	
	public enum TokenValidityType {
		
		Relative("Relative"),
		Absolute("Absolute");
		
		private String value;
		
		TokenValidityType( String value ) {
			
			this.value = value;
			
		}
		
		public String value() {
			
			return this.value;
			
		}
		
	}
	
	public enum TokenValidityUnit {
		
		seconds("seconds"),
		minutes("minutes"),
		hour("hour"),
		days("days");
		
		private String value;
		
		TokenValidityUnit( String value ) {
			
			this.value = value;
			
		}
		
		public String value() {
			
			return this.value;
			
		}
		
	}
	
    public enum ElementErrorActionType {

        RETURN_ERROR,
        ABORT,
        ADD_TIMESTAMP_TO_FIELD;

    }; 
	
	public TokenTypeForm( SeleniumWebDriver selenium, long timeout, long interval ) {
		
		super(selenium, timeout, interval);
			
	}
	
	public TokenTypeForm( SeleniumWebDriver selenium, JSONTokenType tokenTypeCfg, long timeout, long interval ) {
		
		super(selenium, timeout, interval);
		
		this.tokenTypeCfg = tokenTypeCfg;
		
	}	
	
	public TokenTypeForm openForm() throws FormException {
		
		super.open( OfferOptimisationSection.TOKEN_TYPE );
		
		return this;
		
	}

	public TokenTypeForm addTokenTypes() throws FormException, JSONException {
		
		JSONArray tokenTypes = tokenTypeCfg.getList();
		
		for( int tokenTypeIndex = 0; tokenTypeIndex < tokenTypes.length(); tokenTypeIndex++ ) {
			
			tokenTypeCfg.setTokenTypeById( tokenTypeIndex );
			
			if( tokenTypeCfg.getEnabled() ) {
			
				addBtn().
				configureTokenType().
				saveBtn();
				
			}
					
		}
		
		return this;
		
	}
	
	public TokenTypeForm configureTokenType() throws FormException {
		
		setName( tokenTypeCfg.getName() ).
		setDescription( tokenTypeCfg.getDescription() ).
		setImgUrl( tokenTypeCfg.getImageUrl() ).
		setFormat( tokenTypeCfg.getFormat() ).
		setValidityType( tokenTypeCfg.getValidityType() );
						
		if( tokenTypeCfg.getValidityType().equals( "Relative" ) ) {
			
			setValidityValue( tokenTypeCfg.getValidityValue() ).
			setValidityUnit( tokenTypeCfg.getValidityUnit() );
						
		} else /** Absolute */ {
			
			Calendar date = Calendar.getInstance();
			
			try {
				
				if( PlaceHolderDate.getInstance( tokenTypeCfg.getValidityValue() ).isPlaceHolderDate() ) {
				
					date = PlaceHolderDate.getInstance( tokenTypeCfg.getValidityValue() ).parse();
										
				} else {
									
					date.setTime( new SimpleDateFormat("yyyy-MM-dd").parse( tokenTypeCfg.getValidityValue() ) );
				
				}
				
				setAbsoluteDate( "validity.value", date );
					
			} catch ( ParseException e ) {
				
				throw new FormException( e.getMessage(), e );
				
			}
			
			
		}
				
		if( tokenTypeCfg.getUsageUnlimited() == true ) {
			
			clickId( "usageUnlimited-1" ); 
		
		} else {
			
			clickId( "usageUnlimited-0" ).
			clearByName( "usage" ).
			sendKeysByName( "usage", tokenTypeCfg.getUsage() );
			
		}
				
		return this;
		
	}

	public List<WebElement> getTokenTypeList() throws FormException {
		
		List<WebElement> tokenTypeList = super.searchListByXPath( "//div[@class='e4ol-list']", "//div[@class='e4ol-list']//div[contains(@class,'e4ol-list__row ng-scope')]//div[contains(@class, 'e4ol-list__cell e4ol-list__cell--text ng-binding')]" );

		return tokenTypeList;
		
	}
	
	public Boolean isTokenTypeInList( String tokenTypeName ) throws FormException {
		
		List<WebElement> tokenTypeList = getTokenTypeList();
				
		for( WebElement tokenTypeEl : tokenTypeList ) {
			
			if( tokenTypeEl.getText().trim().equals( tokenTypeName ) ) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	} 

	public String getTokenTypeNameByIndex( Integer tokenTypeIndex ) throws FormException {
		
		WebElement tokenType = getTokenTypeInListByIndex( tokenTypeIndex );
						
		return ( null != tokenType ? tokenType.getText() : "" );
		
	} 

	
	public WebElement getTokenTypeInListByIndex( Integer tokenTypeIndex ) throws FormException {
		
		List<WebElement> tokenTypeList = getTokenTypeList();
		
		if( tokenTypeList.size() > tokenTypeIndex ) {
		
			return tokenTypeList.get( tokenTypeIndex );
			
		}
				
		return null;
		
	} 
	
	public WebElement getTokenTypeInListByName( String tokenTypeName ) throws FormException {
		
		List<WebElement> tokenTypeList = getTokenTypeList();
		
		for( WebElement tokenTypeEl : tokenTypeList ) {
			
			if( tokenTypeEl.getText().trim().equals( tokenTypeName ) ) {
				
				return tokenTypeEl;
				
			}
			
		}
		
		return null;
		
	} 
	
	public TokenTypeForm addBtn() throws FormException {
		
		super.clickLink( "Add" );
		
		return this;
		
	}
	
	public TokenTypeForm editByName( String tokenTypeName ) throws FormException {
		
		List<WebElement> tokenTypeList = getTokenTypeList();
		
		for( int el = 0; el < tokenTypeList.size(); el++ ) {
						
			if( tokenTypeList.get( el ).getText().trim().equals( tokenTypeName ) ) {

				WebElement editBtn = super.search( SearchBy.XPATH , "//div[@class='e4ol-list']/div[" + ( el + 2 ) + "]//a[@class='gwt-Button']" );
				
				if( null != editBtn ) {
					
					editBtn.click();
					
					/** wait for loading data **/
					super.sleep( 2000L );
					
				}
				
			}
			
		}
			
		return this;
		
	}
	
	
	public TokenTypeForm setName( String name ) throws FormException {
		
		super.sendKeysByName( "name", name );
		
		return this;
		
	}
	
	public String getName() throws FormException {
		
		return super.getValueByName( "name" );
		
	}

	
	public TokenTypeForm setDescription( String description ) throws FormException {
		
		super.sendKeysByXPath( "//textarea[@ng-model='tokenType.description']", description );
		
		return this;
		
	}
	
	public String getDescription() throws FormException {
		
		return super.getValueByXPath( "//textarea[@ng-model='tokenType.description']" );
		
	}
	
	public TokenTypeForm setImgUrl( String imgUrl ) throws FormException {
		
		super.sendKeysByXPath( "//input[@ng-model='tokenType.imageUrl']", imgUrl );
		
		return this;
		
	}
	
	public String getImgUrl() throws FormException {
		
		return super.getValueByXPath( "//input[@ng-model='tokenType.imageUrl']" );
		
	}
	
	public TokenTypeForm setFormat( String format ) throws FormException {
		
		super.selectByNameAndVisibleText( "format", format );
		
		return this;
		
	}
	
	public String getFormat() throws FormException {
		
		return TokenFormat.values()[ Integer.valueOf( super.getValueByName( "format" ) ) ].value();
		
	}
	
	public TokenTypeForm setValidityType( String validityType ) throws FormException {
		
		super.selectByXPathAndVisibleText( "//select[@name='schedulingType']", validityType );
		
		return this;
		
	}
	
	public String getValidityType() throws FormException {
		
		return ( super.getValueByXPath( "//select[@name='schedulingType']" ).equals( "DAYS" ) ? TokenValidityType.Relative.name() : TokenValidityType.Absolute.name() );
		
	}
	
	public TokenTypeForm setValidityValue( String validityValue ) throws FormException {
		
		super.sendKeysByName( "validity.value", validityValue );
		
		return this;
		
	}
	
	public String getValidityValue() throws FormException {
		
		return super.getValueByName( "validity.value" );
		
	}
	
	public TokenTypeForm setValidityUnit( String validityUnit ) throws FormException {
		
		super.selectByNameAndVisibleText( "validity.unit", validityUnit );
		
		return this;
		
	}
	
	public String getValidityUnit() throws FormException {

		return TokenValidityUnit.values()[ Integer.valueOf( super.getValueByName( "validity.unit" ) )].name();
		
	}
	
	public TokenTypeForm setUnlimitedRedraw( Boolean unlimitedRedraw ) throws FormException {
		
		if( unlimitedRedraw == true ) {
			
			super.clickId( "usageUnlimited-1" ); 
		
		} else {
			
			super.clickId( "usageUnlimited-0" );
			
		}
		
		return this;
		
	}
	
	public Boolean getUnlimitedRedraw() throws FormException {
		
		return Boolean.valueOf( super.getValueById( "usageUnlimited-1" ) );
		
	}
	
	public TokenTypeForm setNumberOfRedraw( String numberOfRedraw ) throws FormException {
		
		super.clearByName( "usage" );
		super.sendKeysByName( "usage", numberOfRedraw );
		
		return this;
		
	}
	
	public String getNumberOfRedraw() throws FormException {
		
		return super.getValueByName( "usage" );
		
	}
	
	public TokenTypeForm setAbsoluteDate( String name, Calendar date ) throws FormException {
		
		AngularCalendarForm.
			create( selenium, Calendar.getInstance(), timeout, interval ).
			openByName( name ).
			setDate( date );
		
		return this;
		
	}
	
	public TokenTypeForm saveBtn() throws FormException {
		
		super.clickName( "btn-add" );
		
		return this;
		
	}
	
	public TokenTypeForm cancelBtn() throws FormException {
		
		super.clickXPath( "//a[@label='actrule-button-cancel']" );
		
		return this;
		
	}
	
	private Boolean isFieldValid( WebElement el ) {
		
		return !el.getAttribute( "is-server-valid" ).equals( "serverValidationErrors" );
		
	}
		
	public Boolean formIsValid() throws FormException {
		
		WebElement name = super.search( SeleniumUtils.SearchBy.NAME, "name" );
		WebElement format = super.search( SeleniumUtils.SearchBy.NAME, "format" );
		WebElement validityValue = super.search( SeleniumUtils.SearchBy.NAME, "validity.value" );
		WebElement validityUnit = super.search( SeleniumUtils.SearchBy.NAME, "validity.unit" );
		WebElement numberOfRedraws = super.search( SeleniumUtils.SearchBy.NAME, "usage" );
		
		return ( 	
			isFieldValid( name ) && 
			isFieldValid( format ) && 
			isFieldValid( validityValue ) && 
			isFieldValid( validityUnit ) &&
			isFieldValid( numberOfRedraws ) 
		);
		
	}
	
	public Boolean formIsNotValid() throws FormException {
		
		return !formIsValid();
		
	}
	
	public Boolean isTokenTypeDuplicated() throws FormException {
		
		closeAngularFrame();
		
		try { Thread.sleep( 1000 );  } catch( Exception e) {}
		
		Boolean isTokenDuplicated = false;
		
		final String DIALOG_XPATH = "//div[@class='gwt-DialogBox errorDialog']";
		final String DIALOG_ERROR_MESSAGE_XPATH = DIALOG_XPATH + "//div[contains(text(), 'This value already exists')]"; 
		final String DIALOG_BTN_XPATH = DIALOG_XPATH + "//button[@title='OK']"; 
				
		WebElement errorMessage = super.search( SeleniumUtils.SearchBy.XPATH, DIALOG_ERROR_MESSAGE_XPATH, 1000L, 200L );
		
		if( null != errorMessage ) { 
			
			isTokenDuplicated = true;
			
			super.clickXPath( DIALOG_BTN_XPATH ); 
			
		}
				
		openAngularFrame();
		
		try { Thread.sleep( 1000 );  } catch( Exception e) {}
				
		return isTokenDuplicated;
		
	}
	
	@Override
	public TokenTypeForm goToHome() throws FormException {
		
		close();
		
		super.goToHome();
		
		return this;
		
	}

}
