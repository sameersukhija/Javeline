package com.lumata.e4o.json.gui.catalogmanager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.json.common.JsonConfig;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class JSONSuppliers extends JsonConfig {

	private JsonConfig currentSupplier;
	
	public JSONSuppliers( String folder, String file ) throws JSONSException {
		
		super( folder, file );
			
	}

	public JSONArray getList() throws JSONException {		
		return (JSONArray)getJSONArrayFromPath("suppliers");				
	}
	
	public Boolean getEnabled() throws JSONException {
		return currentSupplier.getBooleanFromPath( "enabled" );
	}
	
	public String getName() {
		return currentSupplier.getStringFromPath( "name" );
	}
	
	public String getEmail() {
		return currentSupplier.getStringFromPath( "email" );
	}

	public String getPhone() {
		return currentSupplier.getStringFromPath( "phone" );
	}
	
	public String getWebsite() {
		return currentSupplier.getStringFromPath( "website" );		
	}

	public JSONObject getErrorActions() throws JSONException {
		return currentSupplier.getJSONObjectFromPath( "errorActions" );
	}
	
	public void setName( String name ) {
		setObjectFromPath( "name" , name );
	}
	
	public void setSupplierById( Integer currentSupplier ) throws JSONException {
		
		this.currentSupplier = new JsonConfig( getList().getJSONObject( currentSupplier ) );
				
	}

}
