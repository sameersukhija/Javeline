package com.lumata.e4o.json.gui.catalogmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.ErrorModificableElement;
import com.lumata.common.testing.json.JsonConfigurationElement;
import com.lumata.common.testing.json.JsonConfigurationFile;
import com.lumata.e4o.json.gui.catalogmanager.JSONProductTypes.JsonCharacteristicElement;


public class JSONProducts extends JsonConfigurationFile {

	/**
	 * 
	 * @param folder
	 * @param file
	 * @throws JSONSException
	 */
	public JSONProducts( String folder, String file ) throws JSONSException {
		
		super( folder, file );			
	}

	@Override
	public String getElementsSectionLabel() {

		return "products";
	}
	
	/**
	 * This method returns the "type" of current element.
	 * The current element must be selected with "setCurrentElementById" method.
	 * 
     * @return
     */
	public String getType() {
		return getCurrentElement().getStringFromPath( "type" );
	}
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
		return getCurrentElement().getStringFromPath( "description" );
	}
	
	public String getSupplier() {
		return getCurrentElement().getStringFromPath( "supplier" );
	}
	public String getImageUrl() {
		return getCurrentElement().getStringFromPath( "image" );
	}
	
	public String getTermsAndCondition() {
		return getCurrentElement().getStringFromPath( "TnC" );
	}
	
	public String getCost() {
		return getCurrentElement().getStringFromPath( "cost" );
	}
	
	public String getPrice() {
		return getCurrentElement().getStringFromPath( "price" );
	}
	public String getStock() {
		return getCurrentElement().getStringFromPath( "stock" );
	}
	
	public String getStartDate() {
		return getCurrentElement().getStringFromPath( "startDate" );
	}
	
	public String getEndDate() {
		return getCurrentElement().getStringFromPath( "EndDate" );
	}
	/**
	 * This enum describes the possible characteristic type
	 */
	public enum CharacteristicType {
		
		/**
		 * The characteristic list type.
		 * When this type is selected, "list" and "default" fields are
		 * enabled into json file source.
		 */
		ProductType,
		
		/**
		 * The characteristic choice type.
		 * When this type is selected, "choice" and "default" fields are
		 * enabled into json file source.
		 */
		ProductSpecific,
		
		/**
		 * The characteristic text type.
		 * When this type is selected, "text" field is
		 * enabled into json file source.
		 */
		RelatedProducts,
		
	}
	@SuppressWarnings("unchecked")
	public List<JsonProdCharacteristicElement> getCharacteristicsList() throws JSONSException {
		
		List<JsonProdCharacteristicElement> resp = new ArrayList<JSONProducts.JsonProdCharacteristicElement>();
		
		List<Object> chList = getCurrentElement().getJsonListFromPath("characteristics");
		
		for (Object chraw : chList) 
			resp.add(new JsonProdCharacteristicElement((Map<String, Object>) chraw));

		return resp;
	}

	/**
	 * This object describes the single characteristic into product type
	 */
	public final class JsonProdCharacteristicElement extends ErrorModificableElement {

		public JsonProdCharacteristicElement(Map<String, Object> newObject) {
			super(newObject);
		}
		
	    /**
		 * This method returns the "name" of the product specific characteristic of current element.
		 * The current element must be selected with "setCurrentElementById" method.
		 * 
	     * @return
	     */
//		public String getName() {
//			return getStringFromPath("name");
//		}
		
	    /**
		 * This method returns the "type" of current element.
		 * The current element must be selected with "setCurrentElementById" method.
		 * 
	     * @return
	     */
		public CharacteristicType getType() {
			
			return CharacteristicType.valueOf(getStringFromPath("type"));
		}
		
		/**
		 * This method returns the "list" section of current element.
		 * The current element must be selected with "setCurrentElementById" method.
		 * 
		 * @return a <b>JsonList</b> object if current element is a "list", if not an exception is thrown
		 * @throws JSONSException
		 */
		public JsonProductType getProductTypes() throws JSONSException {
			
			if ( !getType().equals(CharacteristicType.ProductType) )
				throw new JSONSException("The characteristic is NOT a \"ProductType\"!");
			else
				return new JsonProductType(getJsonMapFromPath("ProductType"));
		}
		
		/**
		 * This method returns the "choice" section of current element.
		 * The current element must be selected with "setCurrentElementById" method.
		 * 
		 * @return a <b>JsonChoice</b> object if current element is a "choice", if not an exception is thrown
		 * @throws JSONSException
		 */
		public JsonRelatedProducts getRelatedProducts() throws JSONSException {
			
			if ( !getType().equals(CharacteristicType.RelatedProducts) )
				throw new JSONSException("The characteristic is NOT a \"Product\"!");
			else
				return new JsonRelatedProducts(getJsonMapFromPath("RelatedProducts"));
		}		
		
		/**
		 * This method returns the "text" of current element.
		 * The current element must be selected with "setCurrentElementById" method.
		 * 
		 * @return a String if current element is a "text", if not an exception is thrown
		 * @throws JSONSException
		 */
		public JsonSpecificChar getProductSpecific() throws JSONSException {
			
			if ( !getType().equals(CharacteristicType.ProductSpecific) )
				throw new JSONSException("The characteristic is NOT valid!");
			else
				return new JsonSpecificChar(getJsonMapFromPath("ProductSpecific"));
		}	
		
		
		@Override
		public JsonErrorActions getErrorActions() throws JSONSException {
			
			// the characteristic shares "errorActions" from of "ProductTypes" belong from
			return JSONProducts.this.getCurrentElement().getErrorActions();
		}
	}

	/**
	 * This element describes the "Product Type"
	 */
	public class JsonProductType extends JsonConfigurationElement {

		public JsonProductType(Map<String, Object> newObject) {
			super(newObject);
		}
		
		/**
		 * It returns the "Value" array for "product types"
		 * 
		 * @return List<String> of value
		 * 
		 * @throws JSONSException 
		 */
		public List<String> getValue() throws JSONSException {
			
			return getStringList("value");
		}

	}
	/**
	 * This element describes the "Related Products"
	 */
	public class JsonRelatedProducts extends JsonConfigurationElement {

		public JsonRelatedProducts(Map<String, Object> newObject) {
			super(newObject);
		}
		
		/**
		 * It returns the "Value" array for "products"
		 * 
		 * @return List<String> of value
		 * 
		 * @throws JSONSException 
		 */
		public List<String> getValue() throws JSONSException {
			
			return getStringList("value");
		}
	}

	/**
	 * This element describes the Product Specific Characters
	 */
	public class JsonSpecificChar extends JsonConfigurationElement {
		
		/**
		 * 
		 * @param newObject
		 */
		public JsonSpecificChar(Map<String, Object> newObject) {
			super(newObject);
		}
		
		/**
		 * It returns the "Unit" value
		 * 
		 * @return
		 */
		public String getName() {
			
			return getStringFromPath("name");
		}

		/**
		 * It returns the "Value" value
		 * 
		 * @return
		 */
		public String getValue() {
			
			return getStringFromPath("value");
		}
	}

}
