package com.lumata.e4o.webservices.xmlrpc;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.lumata.e4o.gui.xmlrpc.XMLRPCAllocate;
import com.lumata.e4o.gui.xmlrpc.XMLRPCAllocate.Content;
import com.lumata.e4o.gui.xmlrpc.XMLRPCAllocate.Offer;
import com.lumata.e4o.gui.xmlrpc.XMLRPCAllocate.Price;

public class XMLRPCAllocateTest {

	@Test
	public void testResponse() throws Exception {
		String responseText = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><methodResponse><params><param><value><offerPack xmlns:off=\"com/act750/xmlrpc/offerOptimizer\"><id>1</id><token><msisdn>3399900001</msisdn><code>gl-0bdb6</code></token><offers><offer><id>1054</id><name>OFF</name><description/><image_url/><term_of_condition/><status>allocated</status><allocated_date>2013-04-14T10:07+0000</allocated_date><released_date/><accepted_date/><refused_date/><delivered_date/><is_voucher>false</is_voucher><voucher/><start_date/><end_date/><category/><prices/><reserved_quantity>10</reserved_quantity><contents/></offer><offer><id>1055</id><name>OFF</name><description/><image_url/><term_of_condition/><status>allocated</status><allocated_date>2013-04-14T10:07+0000</allocated_date><released_date/><accepted_date/><refused_date/><delivered_date/><is_voucher>false</is_voucher><voucher/><start_date/><end_date/><category/><prices><price><price>10</price><currency>EUR</currency></price><price><price>20</price><currency>DOLLAR</currency></price></prices><reserved_quantity>5</reserved_quantity><contents><item><id>1</id><name>iPhone</name><description>iPhone</description><image_url>http://www...</image_url><term_of_condition>...</term_of_condition><type>product</type></item><item><id>2</id><name>iPad</name><description>iPad</description><image_url>http://www...</image_url><term_of_condition>...</term_of_condition><type>product</type></item></contents></offer></offers></offerPack></value></param></params></methodResponse>";
		XMLRPCAllocate xmlrpcToken = new XMLRPCAllocate(responseText).parse();
		Assert.assertEquals(xmlrpcToken.size(), 2);
		Assert.assertEquals(xmlrpcToken.getCode(), "gl-0bdb6");
		Assert.assertEquals(xmlrpcToken.getMsisdn(), "3399900001");
		for (Offer offer : xmlrpcToken.getOfferList()) {
			Assert.assertNotNull(offer);
			Assert.assertNotNull(offer.getAllocatedDate());
			Assert.assertEquals(offer.getStatus(), "allocated");
			Assert.assertEquals(offer.getRefusedDate(), "");
			Assert.assertEquals(offer.getAcceptedDate(), "");
			Assert.assertEquals(offer.getReleasedDate(), "");
			Assert.assertEquals(offer.getIsVoucher(), "false");
			if (offer.getPriceList().size() > 0) {
				Assert.assertEquals(offer.getPriceList().size(), 2);
				for (Price price : offer.getPriceList()) {
					Assert.assertNotNull(price);
					Assert.assertNotNull(price.getCurrency());
					Assert.assertNotNull(price.getPrice());
					Assert.assertTrue(price.getPrice().equals("10") ||price.getPrice().equals("20"));
					Assert.assertTrue(price.getCurrency().equals("EUR") ||price.getCurrency().equals("DOLLAR"));
				}
			}
			if (offer.getContentList().size() > 0) {
				Assert.assertEquals(offer.getContentList().size(), 2);
				for (Content content : offer.getContentList()) {
					Assert.assertNotNull(content);
					Assert.assertNotNull(content.getId());
					Assert.assertNotNull(content.getDescription());
					Assert.assertNotNull(content.getImageUrl());
					Assert.assertNotNull(content.getName());
					Assert.assertNotNull(content.getTermOfCondition());					
					Assert.assertNotNull(content.getType());
					Assert.assertEquals(content.getType(), "product");
					Assert.assertTrue(content.getName().equals("iPhone") ||content.getName().equals("iPad"));
					Assert.assertTrue(content.getId().equals("1") ||content.getId().equals("2"));
				}
			}
		}
	}
}
