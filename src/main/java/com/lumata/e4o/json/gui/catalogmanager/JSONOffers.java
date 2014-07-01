package com.lumata.e4o.json.gui.catalogmanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.JsonConfigurationElement;
import com.lumata.common.testing.json.JsonConfigurationFile;

public class JSONOffers extends JsonConfigurationFile {

	/**
	 * 
	 */
	private static final Logger logger = LoggerFactory.getLogger( JSONOffers.class );
	
	/**
	 * 
	 * @param folder
	 * @param file
	 * @throws JSONSException
	 */
	public JSONOffers( String folder, String file ) throws JSONSException {
		
		super( folder, file );			
	}
	
	/** 
	 * definition 
	 */
	
    /**
	 * This method returns the "name" of current element.
	 * The current element must be selected with "setCurrentElementById" method.
	 * 
     * @return
     */
	public String getName() {
		
		return getCurrentElement().getStringFromPath( "name" );
	}
	
    /**
	 * This method returns the "description" of current element.
	 * The current element must be selected with "setCurrentElementById" method.
	 * 
     * @return
     */
	public String getDescription() {
		
		return getCurrentElement().getStringFromPath( "definition.description" );
	}

    /**
	 * This method returns the "voucher type" of current element.
	 * The current element must be selected with "setCurrentElementById" method.
	 * 
     * @return
     */
	public String getVoucher() {
		
		return getCurrentElement().getStringFromPath( "definition.voucher" );
	}
	
    /**
	 * This method returns the "Image Url Offer" of current element.
	 * The current element must be selected with "setCurrentElementById" method.
	 * 
     * @return
     */
	public String getImageUrlOffer() {
		
		return getCurrentElement().getStringFromPath( "definition.imageUrlOffer" );
	}
	
    /**
	 * This method returns the "Terms and Conditions" of current element.
	 * The current element must be selected with "setCurrentElementById" method.
	 * 
     * @return
     */
	public String getTermsAndConditions() {
		
		return getCurrentElement().getStringFromPath( "definition.termsAndConditions" );
	}
	
	/**
	 * voucher 
	 */

	/**
	 * offer 
	 */
	
	/**
	 * This enum describes the Offer Content type
	 */
	public enum OfferContentType {
		
		/**
		 * Product content type
		 */
		Products,
		
		/**
		 * Product type content type
		 */
		ProductTypes,
		
		/**
		 * Offers content type
		 */
		Offers;
	}
	
	/**
	 * This object maps the "Offer Content" element
	 */
	public class JSONOfferContentElement extends JsonConfigurationElement {

		public JSONOfferContentElement(Map<String, Object> newObject) {
			
			super(newObject);
		}
		
	    /**
		 * This method returns the "Offer Content Type" of current content.
		 * 
	     * @return
	     */		
		public OfferContentType getOfferContentType() {
			
			return OfferContentType.valueOf(getStringFromPath("type"));
		}
		
		/**
		 * This method returns the "Product name" for "Product" content type.
		 * Otherwise it returns an exception
		 * 
		 * @return
		 * @throws JSONSException
		 */
		public String getProduct() throws JSONSException {
			
			OfferContentType current = getOfferContentType();
			
			if ( !current.equals(OfferContentType.Products) )
				throw new JSONSException("This \"Offer Content Type\" is NOT a \"Products\" but a \""+current+"\"!");
			
			return getStringFromPath("product");
		}

		/**
		 * This method returns the "Product Type name" for "Product Type" content type.
		 * Otherwise it returns an exception
		 * 
		 * @return
		 * @throws JSONSException
		 */
		public String getProductType() throws JSONSException {
			
			OfferContentType current = getOfferContentType();
			
			if ( !current.equals(OfferContentType.ProductTypes) )
				throw new JSONSException("This \"Offer Content Type\" is NOT a \"ProductTypes\" but a \""+current+"\"!");
			
			return getStringFromPath("productType");
		}
		

		/**
		 * This method returns the "Quantity" for "Product" and "Product Type" content type.
		 * Otherwise it returns an exception.
		 * 
		 * @return
		 * @throws JSONSException
		 */
		public Integer getQuantity() throws JSONSException {
			
			OfferContentType current = getOfferContentType();
			
			if ( !current.equals(OfferContentType.Products) )
				throw new JSONSException("This \"Offer Content Type\" is a \""+current+"\" and cannot have \"Qauntity\"!");
			
			return getIntegerFromPath("quantity");
		}

		/**
		 * This method returns the "Offer name" for "Offer" content type.
		 * Otherwise it returns an exception
		 * 
		 * @return
		 * @throws JSONSException
		 */
		public String getOffer() throws JSONSException {
			
			OfferContentType current = getOfferContentType();
			
			if ( !current.equals(OfferContentType.Offers) )
				throw new JSONSException("This \"Offer Content Type\" is NOT an \"Offers\" but a \""+current+"\"!");
			
			return getStringFromPath("offers");
		}
	}
	
	/**
	 * This method returns the offer contents list for current element.
	 * The current element must be selected with "setCurrentElementById" method.
	 * 
	 * @return
	 * @throws JSONSException
	 */
	@SuppressWarnings("unchecked")
	public List<JSONOfferContentElement> getOfferContents() throws JSONSException {
		
		List<Object> raw = getCurrentElement().getJsonListFromPath("offer.content");
		List<JSONOfferContentElement> resp = new ArrayList<JSONOffers.JSONOfferContentElement>();
		
		for (Object object : raw) 
			resp.add(new JSONOfferContentElement((Map<String, Object>) object));
		
		return resp;
	}
	
	/**
	 * prices  
	 */
	
	public class JSONPricesElement extends JsonConfigurationElement {

		public JSONPricesElement(Map<String, Object> newObject) {
			
			super(newObject);
		}
		
		/**
		 * It returns the list of channels associated to current element
		 * 
		 * @return
		 * @throws JSONSException
		 */
		public List<String> getChannels() throws JSONSException {
			
			return getStringList("channels");
		}
	}

	/**
	 * This method returns the prices list for current element.
	 * The current element must be selected with "setCurrentElementById" method.
	 * 
	 * @return
	 * @throws JSONSException
	 */
	@SuppressWarnings("unchecked")
	public List<JSONPricesElement> getOffersPrices() throws JSONSException {
		
		List<Object> raw = getCurrentElement().getJsonListFromPath("prices");
		List<JSONPricesElement> resp = new ArrayList<JSONPricesElement>();
		
		for (Object elem : raw) 
			resp.add(new JSONPricesElement((Map<String, Object>) elem));
		
		return resp;
	}
	
	/**
	 * notification 
	 */
	
	/**
	 * eligibility 
	 */
	
	/**
	 * availability
	 */
	
	/**
	 * This object maps the stock reservation among selected channels
	 */
	public class JSONReservationElement extends JsonConfigurationElement {

		public JSONReservationElement(Map<String, Object> newObject) {
			
			super(newObject);
		}
		
		/**
		 * It returns channel name.
		 * 
		 * @return
		 */
		public String getChannel() {
			
			return getStringFromPath("channel");
		}
	
		/**
		 * It returns the reserved quantity.
		 * 
		 * @return
		 */
		public Integer getQuantity() {
			
			return getIntegerFromPath("quantity");
		}
	}
	
	/**
	 * This method returns the reservations list for current element.
	 * The current element must be selected with "setCurrentElementById" method.
	 * 
	 * @return
	 * @throws JSONSException
	 */	
	@SuppressWarnings("unchecked")
	public List<JSONReservationElement> getReservations() throws JSONSException {
		
		List<Object> raw = getCurrentElement().getJsonListFromPath("availability.reservations");
		List<JSONReservationElement> resp = new ArrayList<JSONOffers.JSONReservationElement>();
		
		for (Object object : raw) 
			resp.add(new JSONReservationElement((Map<String, Object>) object));
		
		return resp;
	}	
	
	/**
	 * This method returns the stock associated to offer
	 * 
	 * @return
	 */
	public Integer getStock() {
		
		return getCurrentElement().getIntegerFromPath("availability.stock");
	}
	
	/**
	 * It returns the provisioning start date for current offer.
	 * The current element must be selected with "setCurrentElementById" method.
	 * 
	 * @return
	 * @throws JSONSException
	 */
	public Date getProvisioningStart() throws JSONSException {
		
	    SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-mm-dd" );
	    String rawData = getCurrentElement().getStringFromPath("availability.provisioningStart");
	    Date resp = null;
	    
	    try {
			resp = sdf.parse(rawData);
		} catch (ParseException e) {

			logger.error("Unparseable date string : " + rawData);
			
			e.printStackTrace();
			
			throw new JSONSException(e.getMessage());
		}
	    
		return resp;
	}
	
	/**
	 * It returns the provisioning end date for current offer.
	 * The current element must be selected with "setCurrentElementById" method.
	 * 
	 * @return
	 * @throws JSONSException
	 */	
	public Date getProvisioningEnd() throws ParseException, JSONSException {
		
	    SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-mm-dd" );
	    String rawData = getCurrentElement().getStringFromPath("availability.provisioningEnd");	    
	    Date resp = null;
	    
	    try {
			resp = sdf.parse(rawData);
		} catch (ParseException e) {

			logger.error("Unparseable date string : " + rawData);
			
			e.printStackTrace();
			
			throw new JSONSException(e.getMessage());
		}
	    
		return resp;
	}	
	
	/**
	 * This method returns the limit for subscriber.
	 * 
	 * @return
	 */
	public Integer getLimitPerSubscribers() {
		
		Integer resp = null;
		
		resp = getCurrentElement().getIntegerFromPath("availability.limitPerSubscribers");
		
		return resp != null ? resp : 0 ;
	}
	
	@Override
	public String getElementsSectionLabel() {

		return "offers";
	}
}
