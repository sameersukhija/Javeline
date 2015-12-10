package com.lumata.e4o.gui.administrationmanager;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.e4o.exceptions.FormException;

public class CampaignSubstatesForm extends AdministrationForm {

	private static final Logger logger = LoggerFactory
			.getLogger(NotificationsForm.class);

	public CampaignSubstatesForm(SeleniumWebDriver selenium, long timeout,
			long interval) {
		super(selenium, timeout, interval);
		// TODO Auto-generated constructor stub
	}

	public CampaignSubstatesForm open() throws FormException {
		super.open().clickId("gwt-debug-actrule-catalog-substates");
		return null;

	}

	public CampaignSubstatesForm addCampaignSubstates() throws FormException {
		this.clickXPath("//button[@title='Add substate']");
		return this;
	}

	public CampaignSubstatesForm setName(String strName)
			throws FormException {
		waitForPageLoad();
		this.sendKeysByXPath(
				"//td[@class='dialogMiddleCenter']//ancestor::tr[contains(@class,'cycle1')]//input",
				strName);
		return this;
	}

	public CampaignSubstatesForm setDescription(String strDescription)
			throws FormException {
		this.sendKeysByXPath(
				"//td[@class='dialogMiddleCenter']//ancestor::tr[contains(@class,'cycle2')]//input",
				strDescription);
		return this;
	}

	public CampaignSubstatesForm clickSaveButton() throws FormException {
		this.clickXPath("//td[@class='dialogMiddleCenter']//ancestor::td[2]//button[@name='btn-save']");
		return this;
	}

	public CampaignSubstatesForm clickeditButton(String strCampaignSubstateName)
			throws FormException {
		this.clickXPath("//div[text()='"
				+ strCampaignSubstateName
				+ "']//ancestor::tr[contains(@class,'contentRow cycle')]//td[3]//button[@name='btn-edit']");
		return this;
	}

	public boolean clickDeleteButton(String strCampaignSubstateName)
			throws FormException {

		try {

			waitForPageLoad();
			this.clickXPath("//div[text()='"
					+ strCampaignSubstateName
					+ "']//ancestor::tr[contains(@class,'contentRow cycle')]//td[3]//button[@name='btn-delete']");
			handleJavascriptAlertAcceptDismiss(Boolean.TRUE);
			return true;
		} catch (FormException e) {
			logger.error("Error during deleting campaign substate"
					+ e.getMessage());
			return false;

		}
	}

	public List<WebElement> getSubstateNameList() throws FormException {
		String rootPath = "//table[contains(@class, 'page-CampaignSubstatesPageView')]//table[@class='tableList']";
		String subPath = "//tr[contains(@class, 'contentRow cycle')]//td[@class='column_name'][1]";

		List<WebElement> templateList = getListByXPath(rootPath, rootPath
				+ subPath);
		return templateList;
	}

	public boolean isSubstateinList(String strCampaignSubstateName)
			throws FormException {
		waitForPageLoad();
		List<WebElement> campaignSubstateList = getSubstateNameList();

		for (WebElement campaignSubstateElementEl : campaignSubstateList) {

			if (campaignSubstateElementEl.getText().trim()
					.equals(strCampaignSubstateName)) {

				return true;

			}

		}

		return false;

	}

}
