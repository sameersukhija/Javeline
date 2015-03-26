package com.lumata.e4o.gui.catalogmanager;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.ErrorModificableElement;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorConditionType;
import com.lumata.common.testing.selenium.SeleniumUtils.SearchBy;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.utils.TempFileHandling;
import com.lumata.e4o.common.PlaceHolderDate;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.FormSaveConfigurationHandler;
import com.lumata.e4o.gui.common.FormSaveConfigurationHandler.SaveResult;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers.JSONPricesElement;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers.JSONReservationElement;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers.VoucherType;

public class OffersForm extends CatalogueManagerForm {

	/**
	 * 
	 */
	private JSONOffers offerCfg;
	
	/**
	 * 
	 * @param selenium
	 * @param offerCfg
	 * @param timeout
	 * @param interval
	 */
	public OffersForm( SeleniumWebDriver selenium, JSONOffers offerCfg, long timeout, long interval ) {
		
		super(selenium, timeout, interval);

		this.offerCfg = offerCfg;
	}
	
	/**
	 * 
	 */
	public OffersForm openForm() throws FormException {
		
		super.openForm().clickId( "gwt-debug-actrule-catalog-offers" );
		
		return this;
		
	}
	
	/**
	 * 
	 * @return
	 * @throws FormException 
	 * @throws JSONSException
	 */
	public OffersForm addOffers() throws FormException, JSONSException {
		
		Integer numbOffers = offerCfg.getList().size();
		
		for( int offerIndex = 0 ; offerIndex < numbOffers ; offerIndex++ ) {
			
			offerCfg.setCurrentElementById( offerIndex );
			
			if( offerCfg.getCurrentElement().getEnabled() ) {
				
				clickXPath( "//button[@name='btn-add' and @title='Add Offer']" );
				configureOffer();
				
				// manage errors
				OffersSaveHandler handler = new OffersSaveHandler( 	selenium.getWrappedDriver(), 
																	offerCfg.getCurrentElement());
				
				SaveResult result = handler.saveAction();
				Boolean isActivable = result.equals(SaveResult.SavedCorrectly) || result.equals(SaveResult.SavedWithTimestamp);
				
				try {
					Thread.sleep(1_000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				
				// if saved correctly, check activation status
				if ( isActivable && offerCfg.getActivation() ) {
					String activationRule = "//div[contains(text(),'"+offerCfg.getName()+"')]//ancestor::tr[1]//button[@title='Activate']";
					clickXPath(activationRule);
					handleJavascriptAlertAcceptDismiss(Boolean.TRUE);
				}
				
				try {
					Thread.sleep(2_000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
					
		}
		
		return this;
	}
	
	/**
	 * General offer configuration method
	 * 
	 * @return
	 * @throws FormException
	 * @throws JSONSException
	 */
	public OffersForm configureOffer() throws FormException, JSONSException {
		
		configureDefinition();
		
		if ( offerCfg.getVoucher().equals(VoucherType.none) )
			configureOfferContent();
		else
			configureVoucher();
		
		configurePrices();
		
		configureAvailability();
		
		return this;
		
	}
	
	/**
	 * Configure the "Voucher" form into offer
	 * 
	 * @throws JSONSException
	 * @throws FormException
	 */
	private void configureVoucher() throws FormException, JSONSException {

		clickId("gwt-debug-Anchor-actrule-customerCare-catalog-voucherDefinition");
		
		// file upload
		if ( offerCfg.getVoucher().equals(VoucherType.oneTimeUse) ) {
			
//			File voucherFile = offerCfg.getVoucherFile();
			
			WebElement fileUpload = selenium.getWrappedDriver().findElement(By.xpath("//form[contains(@action,'voucherImport')]//input[@name='uploadFormElement']"));
//			fileUpload.sendKeys(voucherFile.getAbsolutePath());
			
			try {
				TempFileHandling.uploadFile( 	fileUpload,
												offerCfg.getVoucherFile().toPath());
			} catch (IOException e1) {
				e1.printStackTrace();
				throw new FormException(e1.getMessage());
			}

			// manage errors
			VoucherImporterHandler handler = new VoucherImporterHandler( 	selenium.getWrappedDriver(), 
																			offerCfg.getCurrentElement());
			
			handler.saveAction();			
			
			clickXPath("//span[text()='OK']");
		}
		else if ( offerCfg.getVoucher().equals(VoucherType.unlimitedUse) ) {
		
			throw new FormException("Not supported yet!");
		} 
		
		selectByIdAndVisibleText( "gwt-debug-ListBox-VPOfferEdit-supplierListBox", offerCfg.getVoucherPartner());

		String expiryDate = offerCfg.getVoucherExpiryDate();
		
		if ( expiryDate != null && expiryDate.length() != 0 ) {
			
			Calendar date = Calendar.getInstance();
			
			try {
				
				if( PlaceHolderDate.getInstance( expiryDate ).isPlaceHolderDate() ) {
				
					date = PlaceHolderDate.getInstance( expiryDate ).parse();
										
				} else {
									
					date.setTime( new SimpleDateFormat("yyyy-MM-dd").parse( expiryDate ) );
				
				}

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				sendKeysByXPath( "//td[text()='Expiry Date']//ancestor::tr[1]//input", dateFormat.format(date.getTime()));
				
			} catch ( ParseException e ) {
				
				throw new FormException( e.getMessage(), e );
				
			}
		}
	}

	/**
	 * Configure the "Availability" form into offer
	 * 
	 * @throws JSONSException
	 * @throws FormException
	 */
	private void configureAvailability() throws FormException, JSONSException {

		clickId("gwt-debug-Anchor-actrule-catalog-product-steps-stockValidity");
		
		clickXPath("//td[text()='Available Offers']//ancestor::tr[1]//button");
		
		Integer stock = offerCfg.getStock();
		
		if ( stock != null ) {

			sendKeysByXPath("//td[contains(text(),'Available Stock')]//ancestor::tr[1]//input", stock.toString());
			
			clickXPath("//td[contains(text(),'Available Stock')]//ancestor::tr[1]//ancestor::table[3]//button[@title='Save']");
		}
		
		for (JSONReservationElement reservation : offerCfg.getReservations()) {
			
			clickXPath("//div[text()='Channel']//ancestor::table[1]//button[@title='Add']");
			
			selectByXPathAndVisibleText("//div[text()='Reservation']//ancestor::table//select", reservation.getChannel());
			
			sendKeysByXPath("//div[text()='Reservation']//ancestor::table//input", reservation.getQuantity().toString());
			
			clickXPath("//div[text()='Reservation']//ancestor::table//button[@title='Save']");
		}
	}

	/**
	 * Configure the "Prices" form into offer
	 * 
	 * @throws JSONSException
	 * @throws FormException
	 */
	private void configurePrices() throws JSONSException, FormException {
		
		clickId("gwt-debug-Anchor-actrule-catalog-offer-prices");
		
		List<JSONPricesElement> channels = offerCfg.getOffersPrices();
		
		if ( channels != null && channels.size() != 0 ) {

			for (JSONPricesElement price : channels) {
			
				clickXPath("//div[contains(text(),'Create offer')]//ancestor::div[4]//button[@title='Add']");

				// technical debt, missing prices
				
				// technical debt, missing conditions
				
				for (String channel : price.getChannels()) {
				
					clickXPath("//div[contains(text(),'Offer Prices')]//ancestor::table//div[text()='Channel']//ancestor::table[1]//button[@title='Add']");

					// gwt-debug-ListBox-PricesEditionPopUp-lChan
					selectById("gwt-debug-ListBox-PricesEditionPopUp-lChan", channel);
					
					clickXPath("//div[text()='Add channel']//ancestor::table[1]//button[@title='OK']");
					
				}
				
				clickXPath("//button[@title='OK']");
			}
		}		
	}

	private void configureOfferContent() throws FormException {

		//throw new FormException("Not supported yet!");
	}

	/**
	 * Configure the "Definition" form into offer
	 *
	 * @return
	 * @throws FormException
	 */
	private OffersForm configureDefinition() throws FormException {

		// upper tab
		clickId( "gwt-debug-Anchor-actrule-button-definition" );
		
		//input[contains(@id,'VPOfferEdit-offerNameTB')]
		sendKeysByXPath("//input[contains(@id,'VPOfferEdit-offerNameTB')]", offerCfg.getName());
		
 		//td[contains(text(),'External ID')]//ancestor::tr[1]//input
		
		String description = offerCfg.getDescription();
		
		if ( description != null && description.length() != 0 ) {
			
	 		//td[contains(text(),'Offer Description')]//ancestor::tr[1]//button
			// TO BE FIXED BY INDIA TEAM
			clickXPath("//td[contains(text(),'Description')]//ancestor::tr[1]//button");
			
			sendKeysByXPath( "//textarea", description);
			
			clickXPath("//textarea//ancestor::div[1]//button[@title='Save']");
		}

 		// MANCA ELEMENTO PER SELEZIONALE OFFER LABELS
 		
		String url = offerCfg.getImageUrlOffer();
		
		if ( url != null && url.length() != 0 ) {
			//td[contains(text(),'Image URL of offer')]//ancestor::tr[1]//input
			sendKeysByXPath("//td[contains(text(),'Image URL of offer')]//ancestor::tr[1]//input", url);
			
	 		//td[contains(text(),'Image URL of offer')]//ancestor::tr[1]//button
			clickXPath("//td[contains(text(),'Image URL of offer')]//ancestor::tr[1]//button");
		}
 		
		String termsAndCondition = offerCfg.getTermsAndConditions();
		
		if ( termsAndCondition != null && termsAndCondition.length() != 0 ) {
			
			//td[contains(text(),'Terms and conditions')]//ancestor::tr[1]//button
			clickXPath("//td[contains(text(),'Terms and conditions')]//ancestor::tr[1]//button");
			
			sendKeysByXPath( "//textarea", termsAndCondition);
			
			clickXPath("//textarea//ancestor::div[1]//button[@title='Save']");
		}

		//*[@id='gwt-debug-ListBox-VPOfferEdit-voucherLB']
		WebElement we = search(SearchBy.XPATH, "//select[@id='gwt-debug-ListBox-VPOfferEdit-voucherLB']");
			
		Select select = new Select( we );
		select.selectByValue(offerCfg.getVoucher().toString());
		
		WebDriverWait wait = new WebDriverWait(selenium.getWrappedDriver(), 15);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='gwt-debug-ListBox-VPOfferEdit-voucherLB']//option[@value='"+offerCfg.getVoucher().toString()+"']"))).click();
		
		return this;
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	private class OffersSaveHandler extends FormSaveConfigurationHandler {

		protected OffersSaveHandler( WebDriver inDriver, ErrorModificableElement inCurrentElement) {
			
			super(inDriver, inCurrentElement);
		}

		@Override
		protected Boolean containsErrorElement() {

			Integer numbers = null;
			Boolean resp = Boolean.TRUE;
	
			// error condition
			//*[contains(@class,'errorBackground')]
			
			List<WebElement> elements = getWebDriver().findElements(By.xpath("//*[contains(@class,'errorBackground')]"));
	
			if ( elements == null )
				numbers = 0;
			else
				numbers = elements.size();
			
			if ( numbers != 0 )
				resp = true;
			else
				resp = false;
			
			return resp;
		}

		/**
		 * 
		 */
		private WebElement saveElement = null;
		
		@Override
		protected WebElement getSaveWebElement() {

			if ( saveElement == null )
				saveElement = getWebDriver().findElement(By.xpath("//div[text()='Create offer']//ancestor::div[2]//button[@name='btn-save']")); 
			
			return saveElement;
		}

		@Override
		protected ElementErrorConditionType defineErrorCondition() {

			ElementErrorConditionType condition = null;
			
			WebElement dialogBox = getWebDriver().findElement(By.xpath("//div[@class='gwt-DialogBox']"));
			
			// error condition	
			List<WebElement> element = dialogBox.findElements(By.xpath("//div[text()='Cannot add offer, name is already used.']"));
								
			if ( element.size() != 0 )
				condition = ElementErrorConditionType.ELEMENT_AREADY_EXISTS;
			else
				condition = ElementErrorConditionType.GENERAL_ERROR;

			return condition;
		}

		@Override
		protected Boolean cancelAction() {

			Boolean resp = Boolean.FALSE;

			try {

				getWebDriver().findElement(By.xpath("//div[text()='Create offer']//ancestor::div[2]//button[@title='Cancel']")).click();
					
				resp = Boolean.TRUE;
			}
			catch ( NoSuchElementException e ) {
			
				e.printStackTrace();
				
				resp = Boolean.FALSE;
			}
			
			return resp;
		}

		@Override
		protected Boolean addTimestampAction() {

			Boolean resp = Boolean.FALSE;
			
			try {
				
				WebElement nameElem = getWebDriver().findElement(By.xpath("//input[contains(@id,'VPOfferEdit-offerNameTB')]"));
				nameElem.click();
				nameElem.clear();
				
				String name = getCurrentElement().getStringFromPath("name");
				name += TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
				getCurrentElement().modifyStringFromPath("name", name);
				
				nameElem.sendKeys(getCurrentElement().getStringFromPath("name"));
				
				saveAction();
				
				resp = Boolean.TRUE;
				
			} catch ( NoSuchElementException | FormException | JSONSException e ) {
				
				e.printStackTrace();
				
				resp = Boolean.FALSE;
			}
			
			return resp;
		}
		
	}
	
	private class VoucherImporterHandler extends FormSaveConfigurationHandler {

		protected VoucherImporterHandler( WebDriver inDriver, ErrorModificableElement inCurrentElement) {
			
			super(inDriver, inCurrentElement);
		}

		@Override
		protected Boolean containsErrorElement() {
			// TODO Auto-generated method stub
			return Boolean.FALSE;
		}

		/**
		 * 
		 */
		private WebElement saveElement = null;
		
		@Override
		protected WebElement getSaveWebElement() {

			if ( saveElement == null )
				saveElement = getWebDriver().findElement(By.xpath("//td[text()='Import voucher list']//ancestor::td[1]//button")); 
			
			return saveElement;			
		}

		@Override
		protected ElementErrorConditionType defineErrorCondition() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected Boolean cancelAction() {

			throw new RuntimeException("Not supported yet!");
		}

		@Override
		protected Boolean addTimestampAction() {
			
			throw new RuntimeException("Not supported yet!");
		}

	}
	
}