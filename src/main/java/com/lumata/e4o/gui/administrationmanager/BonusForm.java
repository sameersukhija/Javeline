package com.lumata.e4o.gui.administrationmanager;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.json.gui.administrationmanager.JSONCommodities;

public class BonusForm extends AdministrationForm {

	private static final Logger logger = LoggerFactory.getLogger(BonusForm.class);
	
	private final String BONUS_FIELD_PREFIX_XPATH = "//{htmlTag}[@id='gwt-debug-Bonus {field}']";
	private final String BONUS_LIST_ROW_PREFIX_XPATH = "//div[text()='{bonusName}']//ancestor::tr[contains(@class,'contentRow cycle')]";
	
	private enum BonusListFields {
		Name, Type, DefaultValidity, DefaultPeriod, DefaultQtyPeriod, Options;
		
		public Integer getFieldColumnIdx() {
			return this.ordinal() + 1;
		}
		
	}
	
	@SuppressWarnings("unused")
	private JSONCommodities commoditiesCfg;
	/**
	 * 
	 * @param selenium
	 * @param commoditiesCfg
	 * @param timeout
	 * @param interval
	 */
	public BonusForm(SeleniumWebDriver selenium, JSONCommodities commoditiesCfg, long timeout, long interval) {
		super(selenium, timeout, interval);
		this.commoditiesCfg = commoditiesCfg;
	}

	public BonusForm open() throws FormException {
		super.open().clickId("gwt-debug-Enter-Commodities");
		return this;
	}

	public BonusForm addBtn() throws FormException {
		super.clickId("gwt-debug-Add Bonus");
		return this;
	}

	public BonusForm selectType(String type) throws FormException {
		super.selectByXPathAndVisibleText( BONUS_FIELD_PREFIX_XPATH.replace( "{htmlTag}", "select").replace("{field}", "Type"), type );
		return this;
	}

	public BonusForm setName(String name) throws FormException {
		super.typeByXPath( BONUS_FIELD_PREFIX_XPATH.replace( "{htmlTag}", "input").replace("{field}", "Name"), name );
		return this;
	}

	public BonusForm selectAccount(String account) throws FormException {
		super.selectByXPathAndVisibleText( BONUS_FIELD_PREFIX_XPATH.replace( "{htmlTag}", "select").replace("{field}", "Account"), account );
		return this;
	}

	public BonusForm selectAccountType(String accountType) throws FormException {
		super.selectByXPathAndVisibleText( BONUS_FIELD_PREFIX_XPATH.replace( "{htmlTag}", "select").replace("{field}", "Account Type"), accountType );
		return this;
	}

	public BonusForm selectUnit(String unit) throws FormException {
		super.selectByXPathAndVisibleText( BONUS_FIELD_PREFIX_XPATH.replace( "{htmlTag}", "select").replace("{field}", "Unit"), unit );
		return this;
	}

	public BonusForm selectDefaultValidityType(String defaultValidityType) throws FormException {
		super.selectByXPathAndVisibleText( BONUS_FIELD_PREFIX_XPATH.replace( "{htmlTag}", "select").replace("{field}", "Default Validity Type"), defaultValidityType );
		return this;
	}

	public BonusForm selectDefaultPeriodStart(String defaultPeriodStart) throws FormException {
		super.selectByXPathAndVisibleText( BONUS_FIELD_PREFIX_XPATH.replace( "{htmlTag}", "select").replace("{field}", "Default Period Start"), defaultPeriodStart );
		return this;
	}

	public BonusForm selectPeriodType(String defaultPeriodType) throws FormException {
		super.selectByXPathAndVisibleText( BONUS_FIELD_PREFIX_XPATH.replace( "{htmlTag}", "select").replace("{field}", "Default Period Type"), defaultPeriodType );
		return this;
	}

	public BonusForm setDefaultQuantityPeriod(String defaultQuantityPeriod) throws FormException {
		super.typeByXPath( BONUS_FIELD_PREFIX_XPATH.replace( "{htmlTag}", "input").replace("{field}", "Default Quantity Period"), defaultQuantityPeriod );
		return this;
	}

	public BonusForm setUnitaryCost(String unitaryCost) throws FormException {
		super.typeByXPath( BONUS_FIELD_PREFIX_XPATH.replace( "{htmlTag}", "input").replace("{field}", "Unitary Cost"), unitaryCost );
		return this;
	}

	public BonusForm setListPrice(String listPrice) throws FormException {
		super.typeByXPath( BONUS_FIELD_PREFIX_XPATH.replace( "{htmlTag}", "input").replace("{field}", "List Price"), listPrice );
		return this;
	}

	public Boolean isBalanceLimitBtnDisable(String bonusName) throws FormException {
		lastWebElement =  search( SeleniumUtils.SearchBy.XPATH, BONUS_LIST_ROW_PREFIX_XPATH.replace( "{bonusName}", bonusName ) + "//button[@name='btn-limit']" );
		return Boolean.valueOf( lastWebElement.getAttribute( "disabled" ) );
	}

	public BonusForm setBalanceLimit(String bonusName, String balanceLimit) throws FormException {
		super.typeByXPath("//td[@class='headers' and contains(text(),'Balance limit value for bonus " + bonusName + "')]//parent::tr//input", balanceLimit );
		return this;
	}

	public String getBalanceLimit(String bonusName) throws FormException {
		return super.getValueByXPath("//td[@class='headers' and contains(text(),'Balance limit value for bonus " + bonusName + "')]//parent::tr//input");
	}

	public BonusForm balanceLimitBtn(String bonusName) throws FormException {
		super.clickXPath( BONUS_LIST_ROW_PREFIX_XPATH.replace( "{bonusName}", bonusName ) + "//button[@name='btn-limit']" );
		return this;
	}

	public BonusForm editBtn(String bonusName) throws FormException {
		super.clickXPath( BONUS_LIST_ROW_PREFIX_XPATH.replace( "{bonusName}", bonusName ) + "//button[@name='btn-edit']" );
		return this;
	}

	public Boolean deleteBtn(String bonusName) throws FormException {
		try {
			super.clickXPath( BONUS_LIST_ROW_PREFIX_XPATH.replace( "{bonusName}", bonusName ) + "//button[@name='btn-delete']" );
			handleJavascriptAlertAcceptDismiss(Boolean.TRUE);
			return true;
		} catch (FormException e) {
			logger.error("Error during deleting bonus" + e.getMessage());
			return false;
		}
	}

	public List<WebElement> getBonusNameList() throws FormException {
		final String ROOT_XPATH="//div[text()='Bonus']//ancestor::table[@class='tableList']//tr[contains(@class,'contentRow cycle')]";
		final String ROW_XPATH="//td[contains(@class,'column_description')][" + BonusListFields.Name.getFieldColumnIdx() + "]";
		List<WebElement> bonusList = super.getListByXPath(ROOT_XPATH,ROW_XPATH);
		return bonusList;
	}

	public Boolean isBonusNameInList(String strBonusName) throws FormException {

		List<WebElement> bonusList = getBonusNameList();

		for (WebElement bonusNameE1 : bonusList) {

			if (bonusNameE1.getText().trim().equals(strBonusName)) {

				return true;

			}

		}

		return false;

	}

	public Boolean isDefaultValidityInList(String bonusName, String strDefaultyValidity) throws FormException {

		List<WebElement> bonusList = getBonusNameList();

		for (WebElement bonusNameE1 : bonusList) {
			if (bonusNameE1.getText().trim().equals(bonusName)) {
				if(	super.getTextByXPath(BONUS_LIST_ROW_PREFIX_XPATH.replace("{bonusName}", bonusName ) + "//td[" + BonusListFields.DefaultValidity.getFieldColumnIdx() + "]//div[contains(@class,'gwt-Label')]").equals(strDefaultyValidity) ) {
					return true;
				}
			}

		}
		
		return false;
	
	}

	public BonusForm cancelBtn() throws FormException {
		super.clickName("btn-cancel");
		return this;
	}

	public BonusForm saveBtn() throws FormException {

		super.clickName("btn-save");
		handleJavascriptAlertAcceptDismiss(Boolean.TRUE);

		return this;
	}

}
