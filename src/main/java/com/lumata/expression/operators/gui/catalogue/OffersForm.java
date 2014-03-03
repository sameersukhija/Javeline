package com.lumata.expression.operators.gui.catalogue;

import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.expression.operators.gui.common.AngularFrame;
import com.lumata.expression.operators.gui.common.ButtonImpl;
import com.lumata.expression.operators.gui.common.Buttons;
import com.lumata.expression.operators.gui.common.MenuBar;
import com.lumata.expression.operators.gui.common.SectionImpl;
import com.lumata.expression.operators.json.catalogue.OfferCfg;

public class OffersForm extends CatalogueForm {

	private static final Logger logger = LoggerFactory.getLogger(OffersForm.class);

	public enum OfferCreationSection {

		DEFINITION("Definition"), OFFER_CONTENT("Offer Content"), PRICES("Prices"), NOTIFICATION("Notification"), ELIGIBILITY_CRITERIA("Eligibility Criteria"), AVAILABILITY("Availability"), ACTIVATION(
				"Activation");

		private String value;

		public String getJsonID() {

			return this.value.replaceAll(" ", "_").toLowerCase();

		}

		OfferCreationSection(String value) {

			this.value = value;

		}

	}

	public enum OfferErrorAction {

		OFFER_ALREADY_EXISTS;

	};

	public enum OfferErrorActionType {

		RETURN_ERROR, ABORT, ADD_TIMESTAMP_TO_OFFER_NAME;

	};

	public static boolean open(SeleniumWebDriver selenium, long timeout, long interval) {

		if (!CatalogueForm.open(selenium, timeout, interval)) {
			return false;
		}

		if (!MenuBar.select(selenium, new SectionImpl<MenuBar.CatalogSections, String, String>(MenuBar.CatalogSections.OFFERS, MenuBar.CatalogSections.OFFERS.section_id_prefix,
				MenuBar.CatalogSections.OFFERS.section_type), timeout, interval)) {
			return false;
		}

		return true;

	}

	public static boolean add(SeleniumWebDriver selenium, long timeout, long interval) {

		return Buttons.click(selenium, new ButtonImpl<Buttons.Types>(Buttons.Types.BTN_ADD), "page-OfferPageView", timeout, interval);

	}

	public static boolean create(SeleniumWebDriver selenium, OfferCfg offerCfg, long timeout, long interval) {

		// select offer creation tab
		if (!OffersForm.add(selenium, timeout, interval)) {
			return false;
		}

		// configure offer definition
		OffersForm.setDefinition(selenium, offerCfg, timeout, interval);

		// configure offer prices
		OffersForm.setPrices(selenium, offerCfg, timeout, interval);

		// configure offer availability
		OffersForm.setAvailability(selenium, offerCfg, timeout, interval);

		// configure offer definition
		OffersForm.setActivation(selenium, offerCfg, timeout, interval);

		return OffersForm.manageErrorAction(selenium, offerCfg, timeout, interval);

	}

	public static boolean setDefinition(SeleniumWebDriver selenium, OfferCfg offerCfg, long timeout, long interval) {

		// select offer definition section
		WebElement offerDefinition = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-Anchor-actrule-button-definition", timeout, interval);
		if (offerDefinition == null) {
			logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot define a new Offer"));
			return false;
		}

		// set offer name
		WebElement offerName = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-TextBox-VPOfferEdit-offerNameTB", timeout, interval);
		if (offerName == null) {
			logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot define a new Offer Name"));
			return false;
		}
		offerName.clear();
		offerName.sendKeys(offerCfg.getOfferName());

		return true;

	}

	public static boolean setPrices(SeleniumWebDriver selenium, OfferCfg offerCfg, long timeout, long interval) {

		// select offer price section
		WebElement offerPrices = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-Anchor-actrule-catalog-offer-prices", timeout, interval);
		if (offerPrices == null) {
			logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot define some prices"));
			return false;
		}
		offerPrices.click();

		// add price
		WebElement offerPricesBtnAdd = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.CSS, "tr.cycle1.headers > td.column_description > button[name=\"btn-add\"]",
				timeout, interval);
		if (offerPricesBtnAdd == null) {
			logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new price"));
			return false;
		}
		offerPricesBtnAdd.click();

		JSONArray price_channels = offerCfg.getPriceChannels();

		for (int i = 0; i < price_channels.length(); i++) {

			try {

				// add channel price 
				WebElement offerPricesChannelBtnAdd = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
						"html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[3]/td/table/tbody/tr[" + (i > 1 ? (3 + (i - 1)) : 3) + "]/td/button", timeout,
						interval);
				if (offerPricesChannelBtnAdd == null) {
					logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new channel price"));
					return false;
				}
				offerPricesChannelBtnAdd.click();

				// set channel price
				WebElement offerPricesChannelComboAdd = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-ListBox-PricesEditionPopUp-lChan", timeout,
						interval);
				if (offerPricesChannelComboAdd == null) {
					logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new channel price"));
					return false;
				}
				selenium.select("id=gwt-debug-ListBox-PricesEditionPopUp-lChan", "label=" + (String) price_channels.get(i));

				// save channel price
				WebElement offerPricesChannelAddBtnOk = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
						"html/body/div[9]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody/tr/td[2]/button", timeout, interval);
				if (offerPricesChannelAddBtnOk == null) {
					logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new channel price"));
					return false;
				}
				offerPricesChannelAddBtnOk.click();

			} catch (JSONException e) {
			}

		}

		// save prices
		WebElement offerPricesChannelAddConfirm = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody/tr/td[2]/button", timeout, interval);
		if (offerPricesChannelAddConfirm == null) {
			logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new channel price"));
			return false;
		}
		offerPricesChannelAddConfirm.click();

		return true;

	}

	public static boolean setAvailability(SeleniumWebDriver selenium, OfferCfg offerCfg, long timeout, long interval) {
		if (offerCfg.getAvailabilityGlobalStockValue() != null && offerCfg.getAvailabilityGlobalStockValue().length() > 0) {
			// open Availability tab
			WebElement offerAvailability = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-Anchor-actrule-catalog-product-steps-stockValidity",
					timeout, interval);
			if (offerAvailability == null) {
				logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot define availability"));
				return false;
			}
			offerAvailability.click();

			// edit availability
			WebElement offerAvailabilityEdit = SeleniumUtils
					.findForComponentDisplayed(
							selenium,
							SeleniumUtils.SearchBy.XPATH,
							"html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[4]/td/table/tbody/tr[2]/td[2]/table/tbody/tr/td[2]/button",
							timeout, interval);
			if (offerAvailabilityEdit == null) {
				logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot define availability"));
				return false;
			}
			offerAvailabilityEdit.click();

			// set availability
			WebElement offerAvailabilityEditField = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-TextBox-VPOfferEdit-qtyTB", timeout, interval);
			if (offerAvailabilityEditField == null) {
				logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot define availability"));
				return false;
			}
			selenium.type("id=gwt-debug-TextBox-VPOfferEdit-qtyTB", offerCfg.getAvailabilityGlobalStockValue());

			// close set availability dialog
			WebElement offerAvailabilityEditBtnSave = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
					"html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody/tr/td[2]/button", timeout, interval);
			if (offerAvailabilityEditBtnSave == null) {
				logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot define availability"));
				return false;
			}
			offerAvailabilityEditBtnSave.click();

			JSONArray channel_availability_list = offerCfg.getAvailabilityChannels();

			for (int i = 0; i < channel_availability_list.length(); i++) {

				try {

					JSONObject channel_availability = channel_availability_list.getJSONObject(i);

					// open set channel availability dialog																					   
					WebElement offerAvailabilityEditChannel = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
							"html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr["
									+ (i > 1 ? (3 + (i - 1)) : 3) + "]/td/button", timeout, interval);
					if (offerAvailabilityEditChannel == null) {
						logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot define channel availability"));
						return false;
					}
					offerAvailabilityEditChannel.click();

					// choose channel
					WebElement offerAvailabilityEditChannelList = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-ListBox-VPOfferEdit-channelsLB",
							timeout, interval);
					if (offerAvailabilityEditChannelList == null) {
						logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot define channel availability"));
						return false;
					}
					selenium.select("id=gwt-debug-ListBox-VPOfferEdit-channelsLB", "label=" + offerCfg.getAvailabilityChannelName(channel_availability));

					// set channel availability
					WebElement offerAvailabilityEditChannelAvailability = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-TextBox-VPOfferEdit-qtyTB",
							timeout, interval);
					if (offerAvailabilityEditChannelAvailability == null) {
						logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot define channel availability"));
						return false;
					}
					selenium.type("id=gwt-debug-TextBox-VPOfferEdit-qtyTB", offerCfg.getAvailabilityChannelValue(channel_availability));

					// close channel availability dialog
					WebElement offerAvailabilitySaveChannelAvailability = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
							"html/body/div[7]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody/tr/td[2]/button", timeout, interval);
					if (offerAvailabilitySaveChannelAvailability == null) {
						logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot define channel availability"));
						return false;
					}
					offerAvailabilitySaveChannelAvailability.click();

				} catch (JSONException e) {
				}

			}
		}
		return true;

	}

	public static boolean setActivation(SeleniumWebDriver selenium, OfferCfg offerCfg, long timeout, long interval) {

		// select offer definition section
		WebElement offerActivation = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.ID, "gwt-debug-Anchor-actrule-campaign-creationEdition-steps-activation",
				timeout, interval);
		if (offerActivation == null) {
			logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot activate the Offer"));
			return false;
		}
		offerActivation.click();

		// activate offer
		WebElement offerActivationConfirm = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH,
				"html/body/div[5]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr/td[5]/button", timeout, interval);
		if (offerActivationConfirm == null) {
			logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot activate the Offer"));
			return false;
		}
		offerActivationConfirm.click();

		return true;

	}

	public static boolean manageErrorAction(SeleniumWebDriver selenium, OfferCfg offerCfg, long timeout, long interval) {

		logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for error message"));

		WebElement messageError = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.XPATH, "html/body/div[6]/div/div", 5000, interval);

		if (messageError != null) {

			JSONObject error_actions = offerCfg.getErrorActions();

			if (error_actions == null) {

				logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new Offer ( Wrong json configuration )"));

				return false;

			} else {

				try {
					if (messageError.getText().equals("Cannot add offer, name is already used.") && !error_actions.isNull(OfferErrorAction.OFFER_ALREADY_EXISTS.name())) {

						switch (OfferErrorActionType.valueOf(error_actions.getString(OfferErrorAction.OFFER_ALREADY_EXISTS.name()))) {

						case RETURN_ERROR: {

							logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new Offer ( Offer name already exist )"));

							return false;

						}
						case ADD_TIMESTAMP_TO_OFFER_NAME: {

							String name_with_timestamp = offerCfg.getOfferName() + "_" + String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));

							offerCfg.setOfferName(name_with_timestamp);

							OffersForm.setDefinition(selenium, offerCfg, timeout, interval);

							OffersForm.setActivation(selenium, offerCfg, timeout, interval);

							return true;

						}
						case ABORT: {

							logger.info(Log.CHECKING.createMessage(selenium.getTestName(), "for id=btn-cancel"));

							WebElement offerCancel = SeleniumUtils.findForComponentDisplayed(selenium, SeleniumUtils.SearchBy.NAME, "btn-cancel", timeout, interval);
							if (offerCancel == null) {
								logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Abort offer creation"));
								return false;
							}
							offerCancel.click();

							return true;

						}

						}

					}

				} catch (Exception e) {
				}

			}

			logger.error(Log.FAILED.createMessage(selenium.getTestName(), "Cannot add a new Offer ( " + messageError.getText() + " )"));

			return false;

		}

		return true;

	}

}
