package com.lumata.expression.operators.gui.administration;

import java.util.ArrayList;

import org.openqa.selenium.StaleElementReferenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.json.administration.JSONCommodities;

/**
 * 
 * @deprecated use <b>CommoditiesForm</b> into package <b>com.lumata.e4o.gui.administration</b>
 *
 */
public class CommoditiesForm extends AdministrationForm {

	private static final Logger logger = LoggerFactory.getLogger(CommoditiesForm.class);
	
	private JSONCommodities commoditiesCfg;
	
	public CommoditiesForm( SeleniumWebDriver selenium, JSONCommodities commoditiesCfg, long timeout, long interval ) {
		
		super(selenium, timeout, interval);
		
		this.commoditiesCfg = commoditiesCfg;
		
	}
	
	public CommoditiesForm open() throws FormException {
		
		super.open().clickId( "gwt-debug-Enter-Commodities" );
		
		return this;
		
	}
	
	public CommoditiesForm addCommoditiesList() throws FormException {
		
		ArrayList<String> commodities = commoditiesCfg.list();
		
		for( int commodityIndex = 0; commodityIndex < commodities.size(); commodityIndex++ ) {
			
			commoditiesCfg.setCurrentCommodity( commodities.get( commodityIndex ) );
			
			clickId( "gwt-debug-Add Bonus" ).
			createCommodity().
			saveCommodity();
					
		}
		
		return this;
		
	}

	public CommoditiesForm createCommodity() throws FormException {
					
		selectById( "gwt-debug-Bonus Type", commoditiesCfg.getType() ).
		typeById( "gwt-debug-Bonus Name", commoditiesCfg.getName() ).
		selectById( "gwt-debug-Bonus Account", commoditiesCfg.getAccount() ).
		selectById( "gwt-debug-Bonus Account Type", commoditiesCfg.getAccountType() ).
		selectById( "gwt-debug-Bonus Unit", commoditiesCfg.getUnit() ).
		selectById( "gwt-debug-Bonus Default Validity Type", commoditiesCfg.getDefaultValidityType() ).
		selectById( "gwt-debug-Bonus Default Period Type", commoditiesCfg.getDefaultPeriodType() ).
		typeById( "gwt-debug-Bonus Default Quantity Period", commoditiesCfg.getDefaultQuantityPeriod() ).
		typeById( "gwt-debug-Bonus Unitary Cost", commoditiesCfg.getUnitaryCost() );
		typeById( "gwt-debug-Bonus List Price", commoditiesCfg.getListPrice() );
		
		return this;
		
	}
	
	public CommoditiesForm saveCommodity() throws FormException {
		
		clickId( "gwt-debug-Bonus Save" );
		
		try {
		
			if( isDisplayed() ) { 
				
				clickId( "gwt-debug-Bonus Cancel" );
				
			}
		
		} catch( StaleElementReferenceException e ) {
			
			System.out.println( "button not visible" );
			
		}	
		
		return this;
		
	}
	
	@Override
	public CommoditiesForm clickId( String id ) throws FormException {
		
		super.clickId( id );
		
		return this;
		
	}
	
	public JSONCommodities getCommodityFormCfg() {
		return this.commoditiesCfg;
	}
	
	public void setCommodityFormCfg( JSONCommodities commoditiesCfg ) {
		this.commoditiesCfg = commoditiesCfg;
	}
	
}
