package com.lumata.e4o.gui.administrationmanager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorConditionType;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.catalogmanager.OffersForm;
import com.lumata.e4o.gui.common.FormSaveConfigurationHandler;
import com.lumata.e4o.json.gui.administrationmanager.JSONCommodities;

public class BonusForm extends AdministrationForm {

	private static final Logger logger = LoggerFactory
			.getLogger(BonusForm.class);
	private JSONCommodities commoditiesCfg;

	/**
	 * 
	 * @param selenium
	 * @param commoditiesCfg
	 * @param timeout
	 * @param interval
	 */
	public BonusForm(SeleniumWebDriver selenium,
			JSONCommodities commoditiesCfg, long timeout, long interval) {

		super(selenium, timeout, interval);

		this.commoditiesCfg = commoditiesCfg;
	}

	public BonusForm open() throws FormException {

		super.open().clickId("gwt-debug-Enter-Commodities");

		return this;
	}

	public BonusForm clickAddBonus() throws FormException {
		super.clickId("gwt-debug-Add Bonus");
		return this;
	}

	public BonusForm selectType(String type) throws FormException {
		super.selectById("gwt-debug-Bonus Type", type);
		return this;
	}

	public BonusForm enterName(String name) throws FormException {
		super.typeById("gwt-debug-Bonus Name", name);
		return this;
	}

	public BonusForm selectAccountType(String strAccountType)
			throws FormException {
		super.selectById("gwt-debug-Bonus Account Type", strAccountType);
		return this;
	}

	public BonusForm selectAccount(String strAccountType) throws FormException {
		super.selectById("gwt-debug-Bonus Account", strAccountType);
		return this;
	}

	public BonusForm selectUnit(String strUnit) throws FormException {
		super.selectById("gwt-debug-Bonus Unit", strUnit);
		return this;
	}

	public BonusForm selectDefaultValidityType(String strDefaultValidityType)
			throws FormException {
		super.selectById("gwt-debug-Bonus Default Validity Type",
				strDefaultValidityType);
		return this;
	}

	public BonusForm selectDefaultPeriodStart(String strDefaultPeriodStart)
			throws FormException {
		super.selectById("gwt-debug-Bonus Default Period Start",
				strDefaultPeriodStart);
		return this;
	}

	public BonusForm selectPeriodType(String strDefaultPeriodType)
			throws FormException {
		super.selectById("gwt-debug-Bonus Default Period Type",
				strDefaultPeriodType);
		return this;
	}

	public BonusForm enterDefaultQuantityPeriod(String strDefaultQuantityPeriod)
			throws FormException {
		super.typeById("gwt-debug-Bonus Default Quantity Period",
				strDefaultQuantityPeriod);
		return this;
	}

	public BonusForm enterUnitaryCost(String strUnitaryCost)
			throws FormException {
		super.typeById("gwt-debug-Bonus Unitary Cost", strUnitaryCost);
		return this;
	}

	public BonusForm enterListPrice(String strListPrice) throws FormException {
		super.typeById("gwt-debug-Bonus List Price", strListPrice);
		return this;
	}

	public BonusForm clickButtonLimit(String strBonusName) throws FormException {
		super.clickXPath("//div[text()='"
				+ strBonusName
				+ "']//ancestor::tr[contains(@class,'contentRow cycle')]//td[6]//button[@name='btn-limit']");
		return this;
	}

	public Boolean isButtonLimitDisable(String strBonusName)
			throws FormException {
		Boolean status = false;
		String isDisabled = super
				.search(SeleniumUtils.SearchBy.XPATH,
						"//div[text()='"
								+ strBonusName
								+ "']//ancestor::tr[contains(@class,'contentRow cycle')]//td[6]//button[@name='btn-limit']")
				.getAttribute("disabled");
		if (isDisabled.equals("true")) {
			return true;
		}
		return status;

	}

	public BonusForm enterLimitValue(String strText) throws FormException {
		super.typeByXPath("//input[contains(@class,'gwt-TextBox')", strText);
		return this;
	}

	public BonusForm enterBalanclimitValue(String strValue)
			throws FormException {
		super.typeByXPath("//input[contains(@class,'gwt-TextBox')]", strValue);
		return this;
	}

	public String getBalancelimitValue() throws FormException {
		return super.getValueByXPath("//input[contains(@class,'gwt-TextBox')]");

	}

	public BonusForm clickEdit(String strBonusName) throws FormException {
		super.clickXPath("//div[text()='"
				+ strBonusName
				+ "']//ancestor::tr[contains(@class,'contentRow cycle')]//td[6]//button[@name='btn-edit']");
		return this;
	}

	public Boolean clickDeleteButton(String strBonusName) throws FormException {
		try {
			WebDriverWait wait=new WebDriverWait(selenium.getWrappedDriver(), 20);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='"
					+ strBonusName
					+ "']//ancestor::tr[contains(@class,'contentRow cycle')]//td[6]//button[@name='btn-delete']")));
			super.clickXPath("//div[text()='"
					+ strBonusName
					+ "']//ancestor::tr[contains(@class,'contentRow cycle')]//td[6]//button[@name='btn-delete']");
			handleJavascriptAlertAcceptDismiss(Boolean.TRUE);
			return true;
		} catch (FormException e) {
			logger.error("Error during deleting bonus" + e.getMessage());
			return false;

		}
	}

	public BonusForm getBonusTypeInForm() throws FormException {
		super.searchByXPath("//tr[contains(@class,'FlexListSubTitle')]");
		return this;
	}

	public List<WebElement> getBonusNameList() throws FormException {
		String rootXpath="//div[text()='Bonus']//ancestor::table[@class='tableList']//tr[contains(@class,'contentRow cycle')]";
		String subPath="//td[contains(@class,'column_description')][1]";
		//WebDriverWait wait=new WebDriverWait(selenium.getWrappedDriver(), 20);
		//wait.until(ExpectedConditions.(By.xpath(rootXpath)));
		List<WebElement> bonusList = super.getListByXPath(
				rootXpath,subPath);

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

	public Boolean isDefaultValidityInList(String strBonusName,
			String strDefaultyValidity) throws FormException {
		List<WebElement> bonusList = getBonusNameList();

		for (WebElement bonusNameE1 : bonusList) {
			if (bonusNameE1.getText().trim().equals(strBonusName)) {
				if (super
						.getTextByXPath(
								"//div[text()='"
										+ strBonusName
										+ "']//ancestor::tr[contains(@class,'contentRow cycle')]//td[3]//div[contains(@class,'gwt-Label')]")
						.equals(strDefaultyValidity))
					return true;
			}

		}
		return false;
	}

	public BonusForm clickCancel() throws FormException {
		super.clickName("btn-cancel");
		return this;
	}

	public BonusForm saveBtn() throws FormException {

		super.clickName("btn-save");
		handleJavascriptAlertAcceptDismiss(Boolean.TRUE);

		return this;
	}

}
