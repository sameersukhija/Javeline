package com.lumata.expression.operators.gui.xmlrpc;

import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.collect.Lists;

public class XMLRPCAllocate extends BaseXMLRPC<XMLRPCAllocate> {

	private List<Offer> offerList = null;
	private String msisdn;
	private String code;

	public XMLRPCAllocate(String xml) throws Exception {
		super(xml);
		offerList = Lists.newArrayList();
	}

	@Override
	public XMLRPCAllocate parse() throws Exception {
		Element offerPackElement = (Element) doc.getElementsByTagName("offerPack").item(0);
		Element tokenElement = (Element) offerPackElement.getElementsByTagName("token").item(0);
		this.msisdn = tokenElement.getElementsByTagName("msisdn").item(0).getTextContent();
		this.code = tokenElement.getElementsByTagName("code").item(0).getTextContent();
		NodeList offersElement = offerPackElement.getElementsByTagName("offer");
		for (int i = 0; i < offersElement.getLength(); i++) {
			Offer offer = getSingleOffer((Element) offersElement.item(i));
			offerList.add(offer);
		}
		return this;
	}

	private Offer getSingleOffer(Element element) throws Exception {
		Offer offer = new Offer();
		for (OfferFieldList offerTag : OfferFieldList.values()) {
			Node offerElement = element.getElementsByTagName(offerTag.name()).item(0);
			if (OfferFieldList.prices == offerTag) {
				getAllPrice(element, offer);
			} else if (OfferFieldList.contents == offerTag) {
				getAllContent(element, offer);
			} else {
				setValue(offerElement, offer);
			}
		}
		return offer;
	}

	private void getAllContent(Element element, Offer offer) throws Exception {
		List<Content> contentList = Lists.newArrayList();
		NodeList contentElement = element.getElementsByTagName("item");
		for (int i = 0; i < contentElement.getLength(); i++) {
			Content content = getContent((Element)contentElement.item(i));
			contentList.add(content);
		}
		offer.setContents(contentList);
	}

	private Content getContent(Element item) throws Exception {
		Content content = new Content();
		for (ContentFieldList contentTag : ContentFieldList.values()) {
			Node contentElement = item.getElementsByTagName(contentTag.name()).item(0);
			setValue(contentElement, content);
		}
		return content;
	}

	private void getAllPrice(Element element, Offer offer) throws Exception {
		Node pricesElement = element.getElementsByTagName("prices").item(0);
		if (pricesElement.hasChildNodes()) {
			List<Price> priceList = Lists.newArrayList();
			NodeList pricesListElement = pricesElement.getChildNodes();
			for (int i = 0; i < pricesListElement.getLength(); i++) {
				Price price = getPrice((Element) pricesListElement.item(i));
				priceList.add(price);
			}
			offer.setPriceList(priceList);
		}
	}

	private Price getPrice(Element offerElement) throws Exception {
		Price price = new Price();
		Node priceElement = ((Element) offerElement).getElementsByTagName("price").item(0);
		setValue(priceElement, price);
		Node currencyElement = ((Element) offerElement).getElementsByTagName("currency").item(0);
		setValue(currencyElement, price);
		return price;
	}

	public int size() {
		return offerList.size();
	}

	public List<Offer> getOfferList() {
		return offerList;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public String getCode() {
		return code;
	}

	private enum OfferFieldList {
		id, name, description, image_url, term_of_condition, status, allocated_date, released_date, accepted_date, refused_date, delivered_date, is_voucher, voucher, start_date, end_date, category, prices, reserved_quantity, contents
	}

	private enum ContentFieldList {
		id, name, description, image_url, term_of_condition, type
	}
	
	public class Price {
		private String price;
		private String currency;

		public void setPrice(String price) {
			this.price = price;
		}

		public void setCurrency(String currency) {
			this.currency = currency;
		}

		public String getPrice() {
			return price;
		}

		public String getCurrency() {
			return currency;
		}
	}

	public class Content {
		private String id;
		private String name;
		private String description;
		private String imageUrl;
		private String termOfCondition;
		private String type;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public String getTermOfCondition() {
			return termOfCondition;
		}

		public void setTermOfCondition(String termOfCondition) {
			this.termOfCondition = termOfCondition;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

	}

	public class Offer {
		private String id;
		private String name;
		private String description;
		private String imageUrl;
		private String termOfCondition;
		private String status;
		private String allocatedDate;
		private String releasedDate;
		private String acceptedDate;
		private String refusedDate;
		private String deliveredDate;
		private String isVoucher;
		private String voucher;
		private String startDate;
		private String endDate;
		private String category;
		private String reservedQuantity;
		private List<Content> contents = Lists.newArrayList();
		private List<Price> priceList = Lists.newArrayList();

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public String getTermOfCondition() {
			return termOfCondition;
		}

		public void setTermOfCondition(String termOfCondition) {
			this.termOfCondition = termOfCondition;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getAllocatedDate() {
			return allocatedDate;
		}

		public void setAllocatedDate(String allocatedDate) {
			this.allocatedDate = allocatedDate;
		}

		public String getReleasedDate() {
			return releasedDate;
		}

		public void setReleasedDate(String releasedDate) {
			this.releasedDate = releasedDate;
		}

		public String getAcceptedDate() {
			return acceptedDate;
		}

		public void setAcceptedDate(String acceptedDate) {
			this.acceptedDate = acceptedDate;
		}

		public String getRefusedDate() {
			return refusedDate;
		}

		public void setRefusedDate(String refusedDate) {
			this.refusedDate = refusedDate;
		}

		public String getDeliveredDate() {
			return deliveredDate;
		}

		public void setDeliveredDate(String deliveredDate) {
			this.deliveredDate = deliveredDate;
		}

		public String getIsVoucher() {
			return isVoucher;
		}

		public void setIsVoucher(String isVoucher) {
			this.isVoucher = isVoucher;
		}

		public String getVoucher() {
			return voucher;
		}

		public void setVoucher(String voucher) {
			this.voucher = voucher;
		}

		public String getStartDate() {
			return startDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getReservedQuantity() {
			return reservedQuantity;
		}

		public void setReservedQuantity(String reservedQuantity) {
			this.reservedQuantity = reservedQuantity;
		}

		public List<Content> getContentList() {
			return contents;
		}

		public void setContents(List<Content> contents) {
			this.contents = contents;
		}

		public List<Price> getPriceList() {
			return priceList;
		}

		public void setPriceList(List<Price> priceList) {
			this.priceList = priceList;
		}
	}
}
