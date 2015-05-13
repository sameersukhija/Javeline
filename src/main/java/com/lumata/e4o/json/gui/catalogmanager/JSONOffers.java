package com.lumata.e4o.json.gui.catalogmanager;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.JsonConfigurationElement;
import com.lumata.common.testing.json.JsonConfigurationFile;
import com.lumata.common.testing.json.JsonOptionalElement;
import com.lumata.common.testing.utils.TempFileHandling;
import com.lumata.common.testing.utils.TempFileHandling.TempFileExtension;

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
	 * This method returns if the current element must be activated after a correct configuration.
	 * The current element must be selected with "setCurrentElementById" method.
	 * 
	 * In case of legacy json file without "activate" key this method returns FALSE.
	 * 
     * @return
     */
	
//	public String getoffer_description() {
//		return getCurrentElement().getStringFromPath( "offer_description" );
//	}
//
//	public String getTC() {
//		return getCurrentElement().getStringFromPath( "TC" );
//	}
//	
//	public String getPriceChannel() {
//		return getCurrentElement().getStringFromPath( "PriceChannel" );
//	}
//	
//	public String getstock() {
//		return getCurrentElement().getStringFromPath( "stock" );
//	}
	
	
	public Boolean getActivation() {
		
		Boolean resp = getCurrentElement().getBooleanFromPath( "activate" ); 
		
		return resp != null ? resp : Boolean.FALSE; 
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
	public VoucherType getVoucher() {
		
		String raw = getCurrentElement().getStringFromPath( "definition.voucher" ); 
		
		return VoucherType.valueOf(raw);
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
	
	public class JSONVoucherElement extends JsonOptionalElement {
		
		public JSONVoucherElement(Map<String, Object> newObject) {
			
				super(newObject);
		}

		/**
		 * 
		 * @return
		 */
		public String getVoucherListFile() {
			
			return getStringFromPath("voucherListFile");
		}
		
		/**
		 * 
		 * @return
		 * @throws JSONSException 
		 */
		public List<Object> getVoucherList() throws JSONSException {
			
			List<Object> resp = null;
			
			try {
				resp = getJsonListFromPath("voucherList");
			} catch ( Exception e ) {
				
			}
			
			return resp;
		}
		
		/**
		 * 
		 * @return
		 * @throws JSONSException 
		 */
		public Map<String,Object> getGenerateVoucher() throws JSONSException {
			
			Map<String,Object> resp = null;
			
			try {
				resp = getJsonMapFromPath("generateVoucher");
			} catch ( Exception e ) {
				
			}
			
			return resp;
		}	
		
		@Override
		public Map<String, Object> getDefaultValueMap() {
			
			Map<String,Object> h = new HashMap<String, Object>();
			
		    h.put("voucherListFile",null);
		    h.put("voucherList",null);
		    h.put("generateVoucher",null);
		    h.put("unlimitedVoucherCode",null);
		    h.put("format", "plainText");
		    h.put("partner", "Lumata");
		    h.put("expiryDate", "@current+3month");
		    h.put("expiryTime", null);		    
		    
			return h;
		}
	}
	
	/**
	 * This method checks into voucher section ad return a <b>File</b> object that contains
	 * the voucher codes to be assigned.
	 * 
	 * The JSON file can contains file absolute reference or list of voucher file lines.
	 * 
	 * "voucherList" parameter is preferred by "voucherListFile" @ run-time
	 * 
	 * @return
	 * @throws IOException
	 * @throws JSONSException 
	 */
	public File getVoucherFile() throws JSONSException {
		
		JSONVoucherElement localVoucher = new JSONVoucherElement(getCurrentElement().getJsonMapFromPath("voucher"));
		
		File resp = null;

		List<Object> voucherList = localVoucher.getVoucherList();
		String voucherListFile = localVoucher.getVoucherListFile();
		Map<String,Object> generateVoucherConf = localVoucher.getGenerateVoucher();
		
//		List<Object> voucherList = getCurrentElement().getJsonListFromPath("voucher.voucherList");
//		String voucherListFile = getCurrentElement().getStringFromPath("voucher.voucherListFile");
//		Map<String,Object> generateVoucherConf = getCurrentElement().getJsonMapFromPath("voucher.generateVoucher");
		
		if ( voucherList != null && voucherList.size() != 0 )
			resp = generateVoucherFileFromList(voucherList);
		else if ( voucherListFile != null && voucherListFile.length() != 0 )
			resp = fetchExistingVoucherFile(voucherListFile);
		else if ( generateVoucherConf != null && generateVoucherConf.size() != 0 )
			resp = generateVoucherFileFromCustom(generateVoucherConf);
		
		return resp;
	}
	
	/**
	 * Generate a voucher file from custom "generateVoucher" section :
	 * 
	 * <li> prefix is the prefix string common for each voucher code
	 * <li> randLength is optional and describe a random string to add to all voucher
	 * <li> numbers is the number of required voucher
	 * 
	 * @param generateVoucherConf
	 * @return
	 * @throws JSONSException
	 */
	private File generateVoucherFileFromCustom( Map<String, Object> generateVoucherConf) throws JSONSException {
		
		File resp = null;
		
		String prefix = (String) generateVoucherConf.get("prefix");
		Integer rand = 0;
		
		try {
			rand = generateVoucherConf.get("randLength") != null ? Integer.parseInt(generateVoucherConf.get("randLength").toString()) : 0;
		}
		catch ( NullPointerException | NumberFormatException e ) {
			// nothing to do
		}
		
		Integer numb = Integer.parseInt(generateVoucherConf.get("numbers").toString());
		
		if (rand > 0) {
			SecureRandom random = new SecureRandom();
			prefix += new BigInteger(130, random).toString(16).substring(0,rand) + "_";
		}
		
		try {
			List<String> lines = new ArrayList<String>(numb);
			
			for (Integer i = 0; i < numb; i++) 
				lines.add( prefix + StringUtils.leftPad( i.toString(), 5, '0')); 
			
			resp = TempFileHandling.createTempTestFile( lines, "tempVoucherCodeFile", TempFileExtension.CSV);
		}
		catch ( IOException e ) {
			
			throw new JSONSException("Error during voucher file creation : " + e.getMessage());
		}
		
		return resp;
	}

	/**
	 * This method fetches into local file system the voucher files
	 * 
	 * @param voucherListFile
	 * @return
	 * @throws JSONSException 
	 */
	private File fetchExistingVoucherFile(String voucherListFile) throws JSONSException {

		File resp = Paths.get(voucherListFile).toFile();

		if ( !resp.exists() )
			throw new JSONSException("Fail to retrive existing voucher code file!");
			
		return resp;
	}

	/**
	 * This method generates a temporary file that contains lines passed.
	 * 
	 * @param voucherList
	 * 
	 * @return File object
	 * @throws JSONSException 
	 */
	private File generateVoucherFileFromList(List<Object> voucherList) throws JSONSException {

		File resp = null;
		
		try {
			List<String> lines = new ArrayList<String>();
			
			for (Object single : voucherList) 
				lines.add(single.toString());

			resp = TempFileHandling.createTempTestFile( lines, "tempVoucherCodeFile", TempFileExtension.CSV);
		}
		catch ( IOException e ) {
			
			throw new JSONSException("Error during voucher file creation : " + e.getMessage());
		}
		
		return resp;
	}

	/**
	 * It returns the unlimited voucher code for current element.
	 * 
	 * @return
	 */
	public String getUnlimitedVoucherCode() {
		
		return getCurrentElement().getStringFromPath("voucher.unlimitedVoucherCode");
	}
	
	/**
	 * It returns the voucher code format
	 * 
	 * @return
	 */
	public String getVoucherFormat() {
		
		return getCurrentElement().getStringFromPath("voucher.format");
	}
	
	/**
	 * It returns the voucher partner
	 * 
	 * @return
	 */
	public String getVoucherPartner() {
		
		return getCurrentElement().getStringFromPath("voucher.partner");
	}	

	/**
	 * It returns the voucher expiration date to be inserted into UI panel 
	 * 
	 * @return
	 */
	public String getVoucherExpiryDate() {
		
		return getCurrentElement().getStringFromPath("voucher.expiryDate");
	}
	
	public String getVoucherExpiryTime() {
		
		return null;
	}	
	
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
	 * This enum describes the Voucher Type
	 */
	public enum VoucherType {
		
		/**
		 * no voucher
		 */
		none,
		
		/**
		 * One time use voucher
		 */
		oneTimeUse,
		
		/**
		 * Unlimited use voucher
		 */
		unlimitedUse;
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
		
		List<Object> raw = null;
		List<JSONReservationElement> resp = new ArrayList<JSONOffers.JSONReservationElement>();
		
		try {
			raw = getCurrentElement().getJsonListFromPath("availability.reservations");
		} catch ( JSONSException e ) {
			logger.info("Offer \""+getName()+"\" does NOT contains resertavion section.");
		}
		
		if ( raw != null)
			for (Object object : raw) 
				resp.add(new JSONReservationElement((Map<String, Object>) object));
		
		return resp;
	}	
	
	/**
	 * This method returns the stock associated to offer
	 * 
	 * @return
	 */
	public String getStock() {
		
		return getCurrentElement().getStringFromPath("availability.stock");
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
