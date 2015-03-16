package com.lumata.e4o.gui.catalogmanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.openqa.selenium.WebElement;

import com.lumata.common.testing.selenium.SeleniumUtils;
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
	
	public TokenTypeForm open() throws FormException {
		
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
		
		List<WebElement> tokenTypeList = super.searchListByXPath( "//div[@class='e4ol-list']", "//div[@class='e4ol-list']//div[contains(@class,'e4ol-list__cell e4ol-list__cell--text')]" );
		
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
	
	public TokenTypeForm addBtn() throws FormException {
		
		super.clickLink( "Add" );
		
		return this;
		
	}
	
	public TokenTypeForm setName( String name ) throws FormException {
		
		super.sendKeysByName( "name", name );
		
		return this;
		
	}
	
	public TokenTypeForm setDescription( String description ) throws FormException {
		
		super.sendKeysByXPath( "//textarea[@ng-model='tokenType.description']", description );
		
		return this;
		
	}
	
	public TokenTypeForm setImgUrl( String imgUrl ) throws FormException {
		
		super.sendKeysByXPath( "//input[@ng-model='tokenType.imageUrl']", imgUrl );
		
		return this;
		
	}
	
	public TokenTypeForm setFormat( String format ) throws FormException {
		
		super.selectByNameAndVisibleText( "format", format );
		
		return this;
		
	}
	
	public TokenTypeForm setValidityType( String validityType ) throws FormException {
		
		super.selectByXPathAndVisibleText( "//select[@name='schedulingType']", validityType );
		
		return this;
		
	}
	
	public TokenTypeForm setValidityValue( String validityValue ) throws FormException {
		
		super.sendKeysByName( "validity.value", validityValue );
		
		return this;
		
	}
	
	public TokenTypeForm setValidityUnit( String validityUnit ) throws FormException {
		
		super.selectByNameAndVisibleText( "validity.unit", validityUnit );
		
		return this;
		
	}
	
	public TokenTypeForm setUnlimitedRedraw( Boolean unlimitedRedraw ) throws FormException {
		
		if( unlimitedRedraw == true ) {
			
			super.clickId( "usageUnlimited-1" ); 
		
		} else {
			
			super.clickId( "usageUnlimited-0" );
			
		}
		
		return this;
		
	}
	
	public TokenTypeForm setNumberOfRedraw( String numberOfRedraw ) throws FormException {
		
		super.clearByName( "usage" );
		super.sendKeysByName( "usage", numberOfRedraw );
		
		return this;
		
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
	
	@Override
	public TokenTypeForm goToHome() throws FormException {
		
		close();
		
		super.goToHome();
		
		return this;
		
	}

}
