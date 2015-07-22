package com.lumata.e4o.gui.catalogmanager;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.ErrorModificableElement;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorConditionType;
import com.lumata.common.testing.json.JsonConfigurationFile;
import com.lumata.common.testing.json.JsonConfigurationFile.JsonCurrentElement;
import com.lumata.common.testing.selenium.SeleniumUtils.SearchBy;
import com.lumata.common.testing.selenium.SeleniumUtils;
import com.lumata.common.testing.selenium.SeleniumWebDriver;
import com.lumata.common.testing.utils.TempFileHandling;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.FormException;
import com.lumata.e4o.gui.common.Form;
import com.lumata.e4o.gui.common.FormSaveConfigurationHandler;
import com.lumata.e4o.gui.common.FormSaveConfigurationHandler.SaveResult;
import com.lumata.e4o.json.gui.campaignmanager.JSONCriteria;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers.JSONPricesElement;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers.JSONReservationElement;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers.OfferContentType;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers.VoucherType;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes.CharacteristicType;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes.JsonCharacteristicElement;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes.JsonUnit;
import com.lumata.e4o.json.gui.catalogmanager.OfferCfg;
import com.lumata.e4o.schema.tenant.CatalogProductTypes;
import com.lumata.e4o.gui.catalogmanager.ProductTypesForm;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.lumata.common.testing.selenium.SeleniumWebDriver;
public class OffersForm<BrowseElement> extends CatalogueManagerForm {

	private static final Logger logger = LoggerFactory.getLogger(OffersForm.class);

	
	/**
	 * 
	 */
private OffersForm offersForm;
private JSONOffers offerCfg;

private Alert file_input;

	
	/* constructor for initializing without product type json configuration*/ 
	public OffersForm(SeleniumWebDriver selenium,long timeout, long interval) {
		
		super(selenium, timeout, interval);
	}
		
	
	
	public OffersForm( SeleniumWebDriver selenium, JSONOffers jsonoffer, long timeout, long interval ) {
		
		super(selenium, timeout, interval);
		this.offerCfg=jsonoffer;
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
	public OffersForm clickAddOffer() throws FormException, JSONSException {
		
		clickXPath( "//button[@name='btn-add' and @title='Add Offer']" );
	
		return this;
	}
	
	public OffersForm clickEditSavedOffer() throws FormException, JSONSException {
			
		clickXPath("//table[@class='tableList']//tr[@class='contentRow cycle1 savedRow-cycle2'][2]//button[@name='btn-edit' and @title='Edit']" );
		
		return this;
		
		
	}
	
	public OffersForm clickOfferContentTab() throws FormException{
		super.clickLink("Offer Content");
		return this;
	}
	/**
	 * General offer configuration method
	 * 
	 * @return
	 * @throws FormException
	 * @throws JSONSException
	 */
	public OffersForm OfferForm() throws FormException, JSONSException {
		
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

		
	}

	/**
	 * Configure the "Availability" form into offer
	 * 
	 * @throws JSONSException
	 * @throws FormException
	 */
	public OffersForm configureAvailability() throws FormException, JSONSException {

		clickId("gwt-debug-Anchor-actrule-catalog-product-steps-stockValidity");
		
		clickXPath("//td[text()='Available Offers']//ancestor::tr[1]//button");
		
	return this;
	}
	

	/**
	 * Configure the "Prices" form into offer
	 * 
	 * @throws JSONSException
	 * @throws FormException
	 */
	public OffersForm  configurePrices() throws JSONSException, FormException {
		
		clickId("gwt-debug-Anchor-actrule-catalog-offer-prices");
		
		clickXPath("//div[contains(text(),'Offer Prices')]//ancestor::table//div[text()='Channel']//ancestor::table[1]//button[@title='Add']");
					
		clickXPath("//div[text()='Add channel']//ancestor::table[1]//button[@title='OK']");
		
		return this;
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
	public OffersForm configureDefinition() throws FormException {

		return this;
	}
	
	/**
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

	public OffersForm saveBtn() throws FormException {
		
		super.clickName( "btn-save" );
		handleJavascriptAlertAcceptDismiss(Boolean.TRUE);
	    
		return this;
	}
	
	public OffersForm setName(String OFFER_NAME) throws FormException {
		super.sendKeysByXPath("//input[@id='gwt-debug-TextBox-VPOfferEdit-offerNameTB']", OFFER_NAME );
		return this;
	}

	public String getName() throws FormException {
		
		return super.getValueByXPath("//input[@id='gwt-debug-TextBox-VPOfferEdit-offerNameTB']");
		
	}

	/*Duplicatte as SetDescription method*/
//	public OffersForm Offer_Description(String offer_description) throws FormException {
//		
//		clickXPath("//td[contains(text(),'Description')]//ancestor::tr[1]//button");
//		
//		super.sendKeysBycssSelector( "textarea.gwt-TextArea", offer_description);
//		//sendKeysByXPath( "//textarea",offer_description);
//		
//		clickXPath("//textarea//ancestor::div[1]//button[@title='Save']");
//				
//		return this;
//		
//	}
	
	
	
	public OffersForm setTerms( String TC ) throws FormException {
		if (null != TC)
		{
		super.clickXPath("//td[contains(text(),'Terms')]//ancestor::tr[1]//button");
		
		super.sendKeysByXPath( "//textarea", TC);
		
		super.clickXPath("//textarea//ancestor::div[1]//button[@title='Save']");
		}
		
		return this;
		
	}
	
	
	public String getTerms() throws FormException {
		
		return super.getValueByXPath("//textarea" );
		
		
	}
	
	
	public OffersForm clickVoucherDefinitionTab() throws FormException{
		super.clickId("gwt-debug-Anchor-actrule-customerCare-catalog-voucherDefinition");
		return this;
	}
	
	public OffersForm clickPriceTab() throws FormException{
		super.clickId("gwt-debug-Anchor-actrule-catalog-offer-prices");
		return this;
	}
	public OffersForm clickAddPriceButton() throws FormException{
		super.clickXPath("//div[contains(text(),'Create offer')]//ancestor::div[4]//button[@title='Add']");;
		return this;
	}
	
	public OffersForm clickEditPriceButton() throws FormException{
		super.clickXPath("//div[contains(text(),'Edit offer')]//ancestor::div[4]//button[@title='Add']");;
		return this;
	}
	
	
		public OffersForm setPriceChannel( String PriceChannel ) throws FormException {
		
		clickXPath("//div[contains(text(),'Offer Prices')]//ancestor::table//div[text()='Channel']//ancestor::table[1]//button[@title='Add']");

		selectById("gwt-debug-ListBox-PricesEditionPopUp-lChan", PriceChannel);
						
		clickXPath("//div[text()='Add channel']//ancestor::table[1]//button[@title='OK']");
					
		clickXPath("//button[@title='OK']");

		return this;
			
		}
		
		
		public String getPriceChannel() throws FormException {
			
		return super.getValueByXPath( "PriceChannel" );
		}	
		
		
		public OffersForm clickAvailabilityTab() throws FormException{
			super.clickId("gwt-debug-Anchor-actrule-catalog-product-steps-stockValidity");
			return this;
		}
		
		public OffersForm setStockAvailability( String stock ) throws FormException {
			
			super.clickXPath("//td[text()='Available Offers']//ancestor::tr[1]//button");
				
			super.clickXPath("//td[contains(text(),'Available Stock')]//ancestor::tr[1]//ancestor::table[3]//button[@title='Save']");
			
			super.clickXPath("//div[text()='Channel']//ancestor::table//button[@name='btn-add']");
			//super.clickXPath("//tr[3]/td/table/tbody/tr[3]/td/button");
			//super.sendKeysByXPath("//div[text()='Reservation']//ancestor::table//input", stock);
			
			super.sendKeysByXPath("//input[@id='gwt-debug-TextBox-VPOfferEdit-qtyTB']",stock);
			
			super.clickXPath("//div[text()='Reservation']//ancestor::table//button[@title='Save']");
			
			return this;
				
			}
			
			

			public String getStockAvailability() throws FormException {
				
				return super.getValueByXPath( "//div[text()='Reservation']//ancestor::table//input" );
				
				
			}
	
			public OffersForm clickActivationTab() throws FormException
			{
				super.clickId("gwt-debug-Anchor-actrule-campaign-creationEdition-steps-activation");
				return this;
			}
			
			
			public OffersForm ActivationBtn() throws FormException {
				    
			clickXPath("//table[@class='margin10px']/tbody/tr/td//table[@class='buttonPanel marginTop10px']/tbody/tr/td//button[@name='btn-activate']");
					
				    
			try {
						
				Alert confirmLogout = selenium.selectAlert();
						
				while( null != confirmLogout ) {
						
				confirmLogout.accept(); 
							
				confirmLogout = selenium.selectAlert();
							
							
				}
					
						
					
					} catch (NoAlertPresentException e) {}
					
							
				
					return this;
					
				}
			
			public OffersForm editByName(String OFFER_NAME) {
			 return this;
		
			}

			public OffersForm cancelBtn() {
				// TODO Auto-generated method stub
			return this;
			}


			public OffersForm setDescription(String offer_description) throws FormException, JSONSException {
				if(null!=offer_description)
				{
					clickXPath("//td[contains(text(),'Description')]//ancestor::tr[1]//button");
					super.sendKeysBycssSelector("textarea.gwt-TextArea", offer_description);
	
					clickXPath("//textarea//ancestor::div[1]//button[@title='Save']");
				}
				return this;
				}

				
				public String getDescription() throws FormException {
		
				return super.getValueByXPath( "//textarea");
		
				}
	
				public OffersForm setUnlimitedVoucher(String Voucher) throws FormException, JSONSException {
					
				super.clickId("gwt-debug-Anchor-actrule-customerCare-catalog-voucherDefinition");
				super.selectByXPathAndVisibleText("//select[@id='gwt-debug-ListBox-VPOfferEdit-voucherLB']",Voucher);
						
				return this;
				}

				public String getUnlimitedVoucher() throws FormException {
			
				return super.getValueByXPath( "//select[@id='gwt-debug-ListBox-VPOfferEdit-voucherLB']");
			
				}

				
				public OffersForm setUnlimitedVoucherCode(String VoucherCode) throws FormException, JSONSException {
					
					super.sendKeysById("gwt-debug-TextArea-VPOfferEdit-unlimitedUseVoucherCodeTextArea",VoucherCode);
							
				return this;
				}

				
				public String getUnlimitedVoucherCode() throws FormException {
				
					return super.getValueById( "gwt-debug-TextArea-VPOfferEdit-unlimitedUseVoucherCodeTextArea");
				
					}
				
				
					public OffersForm setOneTimeVoucher(String voucher) throws FormException, JSONSException {
					
					super.clickId("gwt-debug-Anchor-actrule-customerCare-catalog-voucherDefinition");
					super.selectByXPathAndVisibleText("//table[@class='tableList Form']/tbody/tr[5][@class='cycle1']/td/select",voucher);
							
					return this;
					}

					public String getOneTimeVoucher() throws FormException {
				
					return super.getValueByXPath( "//table[@class='tableList Form']/tbody/tr[5][@class='cycle1']/td/select");
				
					}
				
					
					public OffersForm setOneTimeBrowseFile(String CSVFILE) throws FormException, JSONSException, IOException {
												
						super.clickXPath("//table[@class='verticalPanelInternalMargin']/tbody//table[@class='tableList Form']/tbody/tr[1][@class='cycle1']//table[@class='importPanel']/tbody/tr/td[1]//input[@name='uploadFormElement']");
						Runtime.getRuntime().exec((System.getProperty( "user.dir" ) + "/src/test/resources/input/catalogmanager/Offers")+"/Auto_it_browsefile.bat");
						Runtime.getRuntime().exec((System.getProperty( "user.dir" ) + "/src/test/resources/input/catalogmanager/Offers")+"/AutoIt_ImportVouchers.exe");
						
					return this;
					}

					
					
					public OffersForm clickImportVoucherCodes() throws FormException, JSONSException {
						
						super.clickXPath("//table[@class='verticalPanelInternalMargin']/tbody//table[@class='tableList Form']/tbody/tr[1][@class='cycle1']//table[@class='importPanel']/tbody/tr/td[2]/button[@name='btn-importer']");
						
					return this;
					}
					
					
					
					public OffersForm<BrowseElement> AlertHandling() throws FormException, IOException {
						
						
						Runtime.getRuntime().exec((System.getProperty( "user.dir" ) + "/src/test/resources/input/catalogmanager/Offers")+"/AutoIt_VoucherAlertHandling.exe");
						
					return this;
							
					}
					
				
					public OffersForm<BrowseElement> UnlimitedVoucherAlertHandling() throws FormException, IOException {
						
						
						Runtime.getRuntime().exec((System.getProperty( "user.dir" ) + "/src/test/resources/input/catalogmanager/Offers")+"/AutoIt_UnlimitedVoucherAlertHandling.exe");
						
					return this;
							
					}
		
					public OffersForm setExternalSupplier(String ES) throws FormException, JSONSException {
					
					super.selectByXPathAndVisibleText("//select[@id='gwt-debug-ListBox-VPOfferEdit-supplierListBox']",ES);
							
				return this;
				}
				
				public String getExternalSupplier() throws FormException {
					
					return super.getValueByXPath( "//select[@id='gwt-debug-ListBox-VPOfferEdit-supplierListBox']");
				
				}


				public OffersForm setVoucherExpiryDate(String date) throws FormException, JSONSException {
					
					super.typeByXPath("//table[@class='verticalPanelInternalMargin']/tbody/tr[4]//table[@class='tableList Form']/tbody/tr[@class='cycle1']/td/input[@class='gwt-DateBox']",date);
									
				return this;
				}

				
				public String getVoucherExpiryDate() throws FormException {
				
				return super.getValueByXPath( "//table[@class='verticalPanelInternalMargin']/tbody/tr[4]//table[@class='tableList Form']/tbody/tr[@class='cycle1']/td/input[@class='gwt-DateBox']");
				
				}

				public OffersForm setOfferstartdate(String date) throws FormException, JSONSException {
					
					super.typeById("gwt-debug-DatePicker-VPOfferEdit-startDateDB",date);
									
				return this;
				}

				
				public String getOfferstartdate() throws FormException {
				
				return super.getValueById( "gwt-debug-DatePicker-VPOfferEdit-startDateDB");
				
				}

						
				public OffersForm setOfferenddate(String date) throws FormException, JSONSException {
					
					super.typeById("gwt-debug-DatePicker-VPOfferEdit-endDateDB",date);
									
				return this;
				}

				
				public String getOfferenddate() throws FormException {
				
				return super.getValueById( "gwt-debug-DatePicker-VPOfferEdit-endDateDB");
				
				}

							
				public OffersForm setImgUrl(String imgUrl) throws FormException { 
		
				super.sendKeysByXPath("//td[contains(text(),'Image URL of offer')]//ancestor::tr[1]//button",imgUrl);
		
				return this;
		
				}
	
				public String getImgUrl(String imgUrl ) throws FormException {
		
					return super.getValueByXPath( "//td[contains(text(),'Image URL of offer')]//ancestor::tr[1]//button");
		
				}

				public Boolean isOfferInList( String offerName ) throws FormException {
		
					List<WebElement> offerList = getOfferList();
		
					for( WebElement offerListE1 : offerList ) {
			
						if( offerListE1.getText().trim().equals( offerName ) ) {
					
							return true;
			
						}	
					}
		
					return false;	
				}
	
	
				public Boolean isOfferInSavedList( String offerName ) throws FormException {
		
					List<WebElement> offerList = getSaveOfferList();
		
					for( WebElement offerListE1 : offerList ) {
			
						if( offerListE1.getText().trim().equals( offerName ) ) {
					
							return true;
			
						}	
					}
		
					return false;	
					}
	
				
	
				public Boolean isOfferInUnlimitedSavedList( String offerName ) throws FormException {
					
					List<WebElement> offerList = getunlimitedvoucherSaveOfferList();
		
					for( WebElement offerListE1 : offerList ) {
			
						if( offerListE1.getText().trim().equals( offerName ) ) {
					
							return true;
			
						}	
					}
		
					return false;	
					}
	
				
				public Boolean isOfferInOnetimeSavedList( String offerName ) throws FormException {
					
					List<WebElement> offerList = getonetimevoucherSaveOfferList();
		
					for( WebElement offerListE1 : offerList ) {
			
						if( offerListE1.getText().trim().equals( offerName ) ) {
					
							return true;
			
						}	
					}
		
					return false;	
					}
	
	
				
				
				
				public List<WebElement> getOfferList()  throws FormException {
		
					String rootPath = "//table[contains(@class,'OfferPageView')]//tr[3]//table[contains(@class, 'tableList')]";
					String subPath = "//tr[contains(@class, 'activatedRow-cycle')]//td[@class='column_description']";
		
					List<WebElement> offerList = getListByXPath(rootPath, rootPath + subPath);
					System.out.println(offerList);
					return offerList;
				}
		
				public List<WebElement> getSaveOfferList()  throws FormException {
			
					String rootPath = "//table[contains(@class,'OfferPageView')]//tr[3]//table[contains(@class, 'tableList')]";
					String subPath = "//tr[contains(@class, 'savedRow-cycle2')]//td[@class='column_description']";
					List<WebElement> offerList = getListByXPath(rootPath, rootPath + subPath);
					System.out.println(offerList);
					return offerList;
				}
			
				
				public List<WebElement> getunlimitedvoucherSaveOfferList()  throws FormException {
					
					String rootPath = "//table[contains(@class,'OfferPageView')]//tr[3]//table[contains(@class, 'tableList')]";
					String subPath = "//tr[7]//td[@class='column_description']";
					List<WebElement> offerList = getListByXPath(rootPath, rootPath + subPath);
					System.out.println(offerList);
					return offerList;
				}
			
				public List<WebElement> getonetimevoucherSaveOfferList()  throws FormException {
					
					String rootPath = "//table[contains(@class,'OfferPageView')]//tr[3]//table[contains(@class, 'tableList')]";
					String subPath = "//tr[8]//td[@class='column_description']";
					List<WebElement> offerList = getListByXPath(rootPath, rootPath + subPath);
					System.out.println(offerList);
					return offerList;
				}
			
				
				
				public WebElement getOfferListByName( String OFFER_NAME) throws FormException {
		
					List<WebElement>OfferList = getOfferList();
					System.out.println(OfferList);
					for( WebElement offerListl : OfferList ) {
						System.out.println("The text from the webelement is:" +offerListl.getText());
			
						if( offerListl.getText().trim().equals(OFFER_NAME)) {
				
							return offerListl;
				
		
						}

					}
					return null;
	
				}
		
				public OffersForm addProductTypes(String productTypes) throws FormException {
		
					super.clickId("gwt-debug-actrule-catalog-productTypes");
					super.clickXPath("//div[3]/div/div/table/tbody/tr/td/table/tbody/tr/td[2]/button");
					super.sendKeysByXPath("//input[@id='gwt-debug-TextBox-ProductTypeDialogBox-nameTextBox']",productTypes);
					super.clickXPath("//div/table/tbody/tr[2]/td/table/tbody/tr/td[2]/button");
					return this;
				}
	
				public String getProductTypes() throws FormException
				{
					return super.getValueByXPath("//input[@id='gwt-debug-TextBox-ProductTypeDialogBox-nameTextBox']");
				}

	
	
				public OffersForm setProductType(String productTypes) throws FormException {
		
					super.clickBycssSelector("table.verticalPanelInternalMargin > tbody > tr > td > table.tableList > tbody > tr.cycle2.headers > td.column_description > button[name='btn-add']");
					super.selectByIdAndVisibleText("gwt-debug-ListBox-OfferContentPopUp-lbType", "Product Types");
					super.selectByXPathAndVisibleText("//table[@class='margin10px']/tbody/tr/td/table[@class='tableList Form']/tbody/tr[5][@class='cycle1']/td[2]/select", productTypes);
					super.clickName("btn-ok");
					return this;
				}
	
		
				public OffersForm setProductName(String productName) throws FormException {
					super.clickBycssSelector("table.verticalPanelInternalMargin > tbody > tr > td > table.tableList > tbody > tr.cycle2.headers > td.column_description > button[name='btn-add']");
					super.selectByXPathAndVisibleText(".//*[@id='gwt-debug-ListBox-OfferContentPopUp-lbProd']", productName);
					super.typeByXPath("//input[@id='gwt-debug-TextBox-OfferContentPopUp-prodStock']","12");
					super.clickName("btn-ok");
					return this;
				}
	
				
				public String getProductName() throws FormException
				{
					return super.getValueByXPath(".//*[@id='gwt-debug-ListBox-OfferContentPopUp-lbProd']");
				}

				
				public OffersForm setOfferName(String offerName) throws FormException {
					super.clickBycssSelector("table.verticalPanelInternalMargin > tbody > tr > td > table.tableList > tbody > tr.cycle2.headers > td.column_description > button[name='btn-add']");
					super.selectByIdAndVisibleText("gwt-debug-ListBox-OfferContentPopUp-lbType", "Offers");
					super.selectByXPathAndVisibleText("//table[@class='margin10px']/tbody/tr/td/table[@class='tableList Form']/tbody/tr[4][@class='cycle2']/td[2]/select", offerName);
			
					super.clickName("btn-ok");
			
					super.clickXPath("//td[@class='dialogMiddleCenter']//table[@class='buttonPanel']/tbody/tr/td/button[@name='btn-cancel']");
					
					return this;
				}
		
				
				public String getOfferName() throws FormException
				{
				return super.getValueByXPath(".//*[@id='gwt-debug-ListBox-OfferContentPopUp-lbProd']");
				}

				public String closeAlertAndGetItsText() {
				    boolean acceptNextAlert = true;
					try {
				      Alert alert = selenium.selectAlert();
				      String alertText = alert.getText();
				      if (acceptNextAlert) {
				        alert.accept();
				      } else {
				        alert.dismiss();
				      }
				      return alertText;
				    } finally {
				      acceptNextAlert = true;
				    }
				  }
	//These methodsfor product creation not required.
	
//	public OffersForm setProductTypeName(String name) throws FormException
//	{
//		super.sendKeysByXPath("//div[text()='Create product type']//ancestor::div[2]//input[@id='gwt-debug-TextBox-ProductTypeDialogBox-nameTextBox']");
//		return this;
//	}
//	
//	
//	public String getProductType(String name) throws FormException
//	{
//		return super.getValueByXPath("//td/table/tbody/tr/td/table/tbody/tr[5]/td[2]/select");
//	}
//	
//	public String getProductTypeName(String name) throws FormException
//	{
//		return super.getValueByXPath("//div[text()='Create product type']//ancestor::div[2]//input[@id='gwt-debug-TextBox-ProductTypeDialogBox-nameTextBox']");
//	}
//	
//	public OffersForm(SeleniumWebDriver selenium, OffersForm jsonOffers, long timeout, long interval) {
//		
//		super(selenium, timeout, interval);
//		
//		this.offersForm = jsonOffers;
//	}
//
//	public static void configureProductType(String productTypeName,
//			String description) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public OffersForm setProductTypeDescription(String description) throws FormException
//	{
//		super.sendKeysByXPath("//div[text()='Create product type']//ancestor::div[2]//input[@id='gwt-debug-TextBox-ProductTypeDialogBox-descriptionTextBox']", description);
//		return this;
//	}
//	public String getProductTypeDescription() throws FormException
//	{
//		return super.getValueByXPath("//div[text()='Create product type']//ancestor::div[2]//input[@id='gwt-debug-TextBox-ProductTypeDialogBox-descriptionTextBox']");
//	}
//	public OffersForm addCharacteristicButton() throws FormException
//	{
//		super.clickXPath("//div[text()='Create product type']//ancestor::div[2]//button[@title='Add']");
//		return this;
//	}
//	public OffersForm setCharacteristicName(String ch_name) throws FormException
//	{
//		super.sendKeysByXPath("//td[contains(text(),'Characteristic Name')]//ancestor::tr[1]//input", ch_name);
//		return this;
//	}
//	public String getCharacteristicName() throws FormException
//	{
//		return super.getValueByXPath("//td[contains(text(),'Characteristic Name')]//ancestor::tr[1]//input");
//	}
//	public OffersForm setCharacteristicType(String ch_type) throws FormException
//	{
//		super.selectByXPathAndVisibleText("//td[contains(text(),'Type')]//ancestor::tr[1]//select", ch_type);
//		return this;
//	}
//	public String getCharacteristicType() throws FormException
//	{
//		return super.getValueByXPath("//td[contains(text(),'Type')]//ancestor::tr[1]//select");
//	}
//	public OffersForm addCharacteristicValueButton() throws FormException
//	{
//		super.clickXPath("//div[contains(text(),'Adding a new characteritic type')]//ancestor::div[2]//button[@title='Add']");
//		return this;
//	}
//	public OffersForm setListCharacteristicValues(List<String> values,List<String> defaultValue) throws FormException
//	{
//		List<WebElement> inputElements = selenium.getWrappedDriver().findElements(By.xpath("//div[contains(text(),'Values')]//ancestor::table[1]//input[@type='text']"));
//		
//		// checkboxes
//		List<WebElement> checkElements = selenium.getWrappedDriver().findElements(By.xpath("//div[contains(text(),'Values')]//ancestor::table[1]//input[@type='checkbox']"));
//		
//		// fill
//		for (int index = 0; index < values.size(); index++) { 
//			
//			WebElement input = inputElements.get(index);
//			String value = values.get(index);
//			
//			input.sendKeys(value);
//			
//			if ( defaultValue != null && defaultValue.contains(values.get(index)) ) 
//				checkElements.get(index).click();
//		}
//		return this;
//	}
//	public OffersForm setChoiceCharacteristicValues(List<String> values,String defaultValue) throws FormException
//	{
//		// element to be filled
//					List<WebElement> inputElements = selenium.getWrappedDriver().findElements(By.xpath("//div[contains(text(),'Values')]//ancestor::table[1]//input[@type='text']"));
//					
//					// ratio
//					List<WebElement> radioElements = selenium.getWrappedDriver().findElements(By.xpath("//div[contains(text(),'Values')]//ancestor::table[1]//input[@type='radio']"));
//					
//					// fill
//					for (int index = 0; index < values.size(); index++) { 
//						
//						WebElement input = inputElements.get(index);
//						String value = values.get(index);
//						
//						input.sendKeys(value);
//						
//						if ( defaultValue != null && defaultValue.equals(values.get(index)) ) 
//							radioElements.get(index).click();
//					}
//		return this;
//	}
//	public OffersForm setTextCharTypeValue(String value) throws FormException
//	{
//		super.sendKeysByXPath("//div[contains(text(),'Adding a new characteritic type')]//ancestor::table[1]//input[@id='gwt-debug-TextBox-CharacteristicDialogBox-defaultValueTextBox']", value);
//		return this;
//	}
//	public String getTextCharTypeValue() throws FormException
//	{
//		return super.getValueByXPath("//div[contains(text(),'Adding a new characteritic type')]//ancestor::table[1]//input[@id='gwt-debug-TextBox-CharacteristicDialogBox-defaultValueTextBox']");
//	}
//	public OffersForm setUnitTypeName(String unitName) throws FormException
//	{
//		super.sendKeysByXPath("//div[contains(text(),'Adding a new characteritic type')]//ancestor::table[1]//input[@id='gwt-debug-TextBox-CharacteristicDialogBox-unitTextBox']", unitName);
//		return this;
//	}
//	public String getUnitTypeName() throws FormException
//	{
//		return super.getValueByXPath("//div[contains(text(),'Adding a new characteritic type')]//ancestor::table[1]//input[@id='gwt-debug-TextBox-CharacteristicDialogBox-unitTextBox']");
//	}
//	public OffersForm setUnitTypeValue(String unitValue) throws FormException
//	{
//		super.sendKeysByXPath("//div[contains(text(),'Adding a new characteritic type')]//ancestor::table[1]//input[@id='gwt-debug-TextBox-CharacteristicDialogBox-defaultValueTextBox']", unitValue);
//		return this;
//	}
//	public String getUnitTypeValue() throws FormException
//	{
//		return super.getValueByXPath("//div[contains(text(),'Adding a new characteritic type')]//ancestor::table[1]//input[@id='gwt-debug-TextBox-CharacteristicDialogBox-defaultValueTextBox']");
//	}
//	
//	
//	public OffersForm saveCharacteristic() throws FormException{
//		super.clickName( "btn-ok" );
//		return this;
//	}
//	public OffersForm saveProductType() throws FormException {
//		super.clickName( "btn-save" );
//		
//		return this;
//	}

	//public OffersForm ProductType() {
		
		//return this;
	//}
	/** get stored Product type **/
	
	private Mysql mysql;


	private Object criteriaXPathRow;
	WebElement file_input2;
	public OffersForm ProductType() throws FormException {
	//CatalogProductTypes ProductType = DAOProductType.getInstance( mysql ).getCatalogProductTypes(getName());
	return offersForm;
	}
	
	public Boolean formIsValid() throws FormException {
		
		//WebElement name = super.search( SeleniumUtils.SearchBy.NAME, "name" );
		WebElement description = super.search( SeleniumUtils.SearchBy.XPATH, "//td[contains(text(),'Description')]//ancestor::tr[1]//button");
		
		return 	(
			isFieldValid( description )
			);
			
		
	
}

	private Boolean isFieldValid( WebElement el ) {
		
		return !el.getAttribute( "is-server-valid" ).equals( "serverValidationErrors" );
		
	}
	public OffersForm clickNotificationTab() throws FormException{
		super.clickId("gwt-debug-Anchor-actrule-catalog-offer-offerNotification");
		return this;
	}
	public OffersForm addNotitification() throws FormException {
		
		
	    super.clickName("btn-notification");
	    super.clickId("gwt-debug-BtnCampaignModelCreationENENGEdit");
	    super.sendKeysById("gwt-debug-TextCampaignModelCreationENEValue", "test sms notification");
        super.clickId("gwt-debug-BtnCampaignModelCreationENESave");
	    super.clickId("gwt-debug-BtnCampaignModelCreationENOk");
        return this;
}
	
//		public OffersForm EligibilityCriteria(JSONProductTypes criteria, String eventXPathRow) throws JSONException, FormException {
//		
//        super.clickId("gwt-debug-Anchor-actrule-catalog-offer-setLimitingParam");
//	    super.clickXPath("//tr[3]/td/table/tbody/tr/td/table/tbody/tr/td/div/table/tbody/tr/td[3]/button");
//	    //super.clickXPath("//tr[3]/td/table/tbody/tr/td/table/tbody/tr/td/div/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td[2]/div/table/tbody/tr/td");
//	    //super.clickBycssSelector("gwt-debug-ListCampaignModelCreationECType-item3-item9.gwt-MenuItem");
//	    //super.clickXPath("//td[@id='gwt-uid-1097']");
//	    eventXPathRow = "//*[@id='gwt-debug-ListCampaignModelCreationECType'] and position() = " + eventXPathRow + " ]//td[@class='gwt-MenuItem']"; 
//		criteriaXPathRow = eventXPathRow + "//div[@class='menuPopupMiddleCenterInner menuPopupContent']//table/tbody/tr["+ criteriaXPathRow + "]";
//		String criteriaXPathRowAValue = criteriaXPathRow + "//*[@id='gwt-debug-TextCampaignModelCreationECValue']";
//		String criteriaXPathRowAType = criteriaXPathRow + "//*[@id='gwt-debug-ListCampaignModelCreationECType']";
//		String actionXPathRowAAutoAllocation = criteriaXPathRow + "//*[contains(text(), '::AUTO_ALLOCATE::') ]/parent::select";
//		
//	    //super.sendKeysById("gwt-debug-TextCampaignModelCreationECValue", "393886933857");
//		/** configure action */
//		
//		
//		clickXPath( criteriaXPathRowAType ).
//		selectDropDownListItem( criteria.getType());
//		
//		if( null != criteria.getValue()) 
//		{ typeByXPath( criteriaXPathRowAValue, criteria.getValue() ); 
//		}
//		
//				
//		return this;
//		
//	   
//}

	
		
}