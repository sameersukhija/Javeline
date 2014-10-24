package com.lumata.unit.json.catalogmanager;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorActionType;
import com.lumata.common.testing.json.HasErrorActions.ElementErrorConditionType;
import com.lumata.common.testing.json.HasErrorActions.JsonErrorActions;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers.JSONOfferContentElement;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers.JSONReservationElement;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers.OfferContentType;
import com.lumata.e4o.json.gui.catalogmanager.JSONOffers.VoucherType;

public class JSONOffersTest {
	
	private JSONOffers underTest = null;
	
	@Parameters({"offersFile"})
	@BeforeTest
	public void init(@Optional("offersTemplate") String offersFile) throws JSONSException {

		String resourcePath = "input/catalogmanager/offers";
		String resourceFile = offersFile;

		underTest = new JSONOffers(resourcePath, resourceFile);
	}
	
	@Test
	public void readVoucherOffers() throws JSONSException, IOException {
		
		underTest.setCurrentElementById(3);
		
		Assert.assertEquals(underTest.getCurrentElement().getEnabled(), Boolean.FALSE);
		Assert.assertEquals(underTest.getCurrentElement().getDelete(), Boolean.FALSE);
		Assert.assertEquals(underTest.getName(), "Voucher oneTimeUse");
		Assert.assertEquals(underTest.getDescription(), null);
		Assert.assertEquals(underTest.getVoucher(), VoucherType.oneTimeUse);
		Assert.assertEquals(underTest.getImageUrlOffer(), null);
		Assert.assertEquals(underTest.getTermsAndConditions(), null);
		
		File obtained = underTest.getVoucherFile();
		
		List<String> lines = Files.readAllLines( obtained.toPath(), Charset.forName("UTF-8"));
		
		Assert.assertEquals( lines.size(), 3);
		
		Assert.assertTrue(lines.contains("voucher1"));
		Assert.assertTrue(lines.contains("voucher2"));
		Assert.assertTrue(lines.contains("voucher3"));
		
		Assert.assertEquals(underTest.getVoucherFormat(), "plainText");
		Assert.assertEquals(underTest.getVoucherPartner(), "Lumata");
		Assert.assertEquals(underTest.getVoucherExpiryDate(), "@current+1month");
	}
	
	@Test
	public void readEntire() throws JSONSException, ParseException {
		
		Assert.assertEquals( underTest.getList().size(), 4, "The lenght does not match!");
		
		underTest.setCurrentElementById(1);
		
		Assert.assertEquals(underTest.getCurrentElement().getEnabled(), Boolean.FALSE);
		Assert.assertEquals(underTest.getCurrentElement().getDelete(), Boolean.FALSE);
		Assert.assertEquals(underTest.getName(), "Offer Test A");
		Assert.assertEquals(underTest.getDescription(), null);
		Assert.assertEquals(underTest.getVoucher(), VoucherType.none);
		Assert.assertEquals(underTest.getImageUrlOffer(), null);
		Assert.assertEquals(underTest.getTermsAndConditions(), null);
		
		JsonErrorActions errorActions = underTest.getCurrentElement().getErrorActions();

		Assert.assertNotNull(errorActions);
		
		Assert.assertEquals( errorActions.getAction(ElementErrorConditionType.ELEMENT_AREADY_EXISTS), ElementErrorActionType.ADD_TIMESTAMP_TO_FIELD);
		Assert.assertEquals( errorActions.getAction(ElementErrorConditionType.GENERAL_ERROR), ElementErrorActionType.RETURN_ERROR);
	
		
		List<JSONOfferContentElement> contentList = underTest.getOfferContents();
		
		Assert.assertEquals( contentList.size(), 3, "Content offers List size does not match!");
		
		for ( JSONOfferContentElement element : contentList ) {
	
			Integer index = contentList.indexOf(element); 
			OfferContentType type = element.getOfferContentType(); 
					
			// product 
			if ( index.equals(0) ) {
				
				Assert.assertEquals( type, OfferContentType.Products, "Unexpected position for \""+OfferContentType.Products+"\" ("+index+")!");
				
				Assert.assertEquals( element.getProduct(), "AfunnyProduct");
				Assert.assertEquals( element.getQuantity().intValue(), 100);				
			}
			
			// product type
			if ( index.equals(1) ) {

				Assert.assertEquals( type, OfferContentType.ProductTypes, "Unexpected position for \""+OfferContentType.ProductTypes+"\" ("+index+")!");
				
				Assert.assertEquals( element.getProductType(), "ProductTypeA");
//				Assert.assertEquals( element.getQuantity().intValue(), 100);				
			}

			// offer
			if ( index.equals(2) ) {

				Assert.assertEquals( type, OfferContentType.Offers, "Unexpected position for \""+OfferContentType.Offers+"\" ("+index+")!");
				
				Assert.assertEquals( element.getOffer(), "AoldOffer");			
			}			
		}
		
		List<JSONOffers.JSONPricesElement> prices = underTest.getOffersPrices(); 
		
		Assert.assertEquals( prices.size(), 3);
		
		Assert.assertEquals( prices.get(0).getChannels(), Arrays.asList("Ch A"));
		Assert.assertEquals( prices.get(1).getChannels(), Arrays.asList("Ch B"));
		Assert.assertEquals( prices.get(2).getChannels(), Arrays.asList("Ch C"));
		
		List<JSONReservationElement> reservationList = underTest.getReservations();
		
		Assert.assertEquals( reservationList.size(), 3, "Reservation List size does not match!");
			
		JSONReservationElement element = reservationList.get(0);
		Assert.assertEquals( element.getChannel(), "Ch A");
		Assert.assertEquals( element.getQuantity().intValue(), 50);
		
		element = reservationList.get(1);
		Assert.assertEquals( element.getChannel(), "Ch B");
		Assert.assertEquals( element.getQuantity().intValue(), 30);
		
		element = reservationList.get(2);
		Assert.assertEquals( element.getChannel(), "Ch C");
		Assert.assertEquals( element.getQuantity().intValue(), 20);
		
		Assert.assertEquals( underTest.getStock().intValue(), 100);
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-mm-dd" );
		
		Assert.assertEquals( underTest.getProvisioningStart(), sdf.parse("2014-05-01"));
		Assert.assertEquals( underTest.getProvisioningEnd(), sdf.parse("2014-05-01"));
		
		Assert.assertEquals( underTest.getLimitPerSubscribers().intValue(), 0 );
	}
}
