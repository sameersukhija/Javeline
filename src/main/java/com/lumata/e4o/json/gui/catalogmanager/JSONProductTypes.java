package com.lumata.e4o.json.gui.catalogmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.JsonConfigurationElement;
import com.lumata.common.testing.json.JsonConfigurationFile;

/**
 *
 */
public class JSONProductTypes extends JsonConfigurationFile {

	/**
	 * 
	 * @param folder
	 * @param file
	 * @throws JSONSException
	 */
	public JSONProductTypes( String folder, String file ) throws JSONSException {
		
		super( folder, file );			
	}

	@Override
	public String getElementsSectionLabel() {

		return "productTypes";
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

    /**
	 * This method returns the "characteristics" of current element.
	 * The current element must be selected with "setCurrentElementById" method.
	 * 
     * @return an object <b>JsonCharacteristicsList</b>
     * @throws JSONSException
     */
	@SuppressWarnings("unchecked")
	public List<JsonCharacteristicElement> getCharacteristicsList() throws JSONSException {
		
		List<JsonCharacteristicElement> resp = new ArrayList<JSONProductTypes.JsonCharacteristicElement>();
		
		List<Object> chList = getCurrentElement().getJsonListFromPath("characteristics");
		
		for (Object chraw : chList) 
			resp.add(new JsonCharacteristicElement((Map<String, Object>) chraw));

		return resp;
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
		List,
		
		/**
		 * The characteristic choice type.
		 * When this type is selected, "choice" and "default" fields are
		 * enabled into json file source.
		 */
		Choice,
		
		/**
		 * The characteristic text type.
		 * When this type is selected, "text" field is
		 * enabled into json file source.
		 */
		Text,
		
		/**
		 * The characteristic unit type.
		 * When this type is selected, "unit" field is
		 * enabled into json file source.
		 */
		Unit;
	}

	/**
	 * This object describes the single characteristic into product type
	 */
	public final class JsonCharacteristicElement extends JsonConfigurationElement {

		public JsonCharacteristicElement(Map<String, Object> newObject) {
			super(newObject);
		}
		
	    /**
		 * This method returns the "name" of current element.
		 * The current element must be selected with "setCurrentElementById" method.
		 * 
	     * @return
	     */
		public String getName() {
			return getStringFromPath("name");
		}
		
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
		 * This method returns the "list" of current element.
		 * The current element must be selected with "setCurrentElementById" method.
		 * 
		 * @return a list of String if current element is a "list", if not an exception is thrown
		 * @throws JSONSException
		 */
		public List<String> getList() throws JSONSException {
			
			if ( !getType().equals(CharacteristicType.List) )
				throw new JSONSException("The characteristic \""+this.getName()+"\" is NOT a \"List\"!");
			else
				return getStringList("list");
		}
		
		/**
		 * This method returns the "choice" of current element.
		 * The current element must be selected with "setCurrentElementById" method.
		 * 
		 * @return a list of String if current element is a "choice", if not an exception is thrown
		 * @throws JSONSException
		 */
		public List<String> getChoice() throws JSONSException {
			
			if ( !getType().equals(CharacteristicType.Choice) )
				throw new JSONSException("The characteristic \""+this.getName()+"\" is NOT a \"Choice\"!");
			else
				return getStringList("choice");
		}
		
		/**
		 * This method returns the "default" of current element.
		 * The current element must be selected with "setCurrentElementById" method.
		 * 
		 * @return a String if current element is a "choice" or a "list", if not an exception is thrown
		 * @throws JSONSException
		 */
		public String getDefault() throws JSONSException {
			
			if ( 	!getType().equals(CharacteristicType.Choice) && 
					!getType().equals(CharacteristicType.List) 
				)
				throw new JSONSException("The characteristic \""+this.getName()+"\" cannot have the \"default\", it is a \""+getType()+"\"!");
			else
				return getStringFromPath("default");
		}
		
		/**
		 * This method returns the "text" of current element.
		 * The current element must be selected with "setCurrentElementById" method.
		 * 
		 * @return a String if current element is a "text", if not an exception is thrown
		 * @throws JSONSException
		 */
		public String getText() throws JSONSException {
			
			if ( !getType().equals(CharacteristicType.Text) )
				throw new JSONSException("The characteristic \""+this.getName()+"\" cannot have the \"text\", it is a \""+getType()+"\"!");
			else
				return getStringFromPath("text");
		}
		
		/**
		 * This method returns the "unit" of current element.
		 * The current element must be selected with "setCurrentElementById" method.
		 * 
		 * @return a <b>JsonUnit</b> object if current element is a "unit", if not an exception is thrown
		 * @throws JSONSException
		 */
		public JsonUnit getUnit() throws JSONSException {
			
			if ( !getType().equals(CharacteristicType.Unit) )
				throw new JSONSException("The characteristic \""+this.getName()+"\" cannot have the \"unit\", it is a \""+getType()+"\"!");
			else
				return new JsonUnit(getJsonMapFromPath("unit"));
		}
		
		@Override
		public JsonErrorActions getErrorActions() throws JSONSException {
			
			// the characteristic shares "errorActions" from of "ProductTypes" belong from
			return JSONProductTypes.this.getCurrentElement().getErrorActions();
		}
	}

	/**
	 * This element describes the "Unit" Product Type
	 */
	public class JsonUnit extends JsonConfigurationElement {
		
		/**
		 * 
		 * @param newObject
		 */
		public JsonUnit(Map<String, Object> newObject) {
			super(newObject);
		}
		
		/**
		 * It returns the "Unit" value
		 * 
		 * @return
		 */
		public String getUnit() {
			
			return getStringFromPath("Unit");
		}

		/**
		 * It returns the "Value" value
		 * 
		 * @return
		 */
		public String getValue() {
			
			return getStringFromPath("Value");
		}
	}

}
