package com.lumata.e4o.json.gui.administrationmanager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.json.common.JsonConfig;

/**
 * @author <a href="mailto:sameer.sukhija@lumatagroup.com">Sameer Sukhija</a>
 * 
 */
public class JSONLMUser extends JsonConfig {

	
	private JsonConfig currentUser;
	
	public JSONLMUser( String folder, String file ) throws JSONSException {
		
		super( folder, file );
			
	}

	public JSONArray getList() throws JSONException {
		
		return (JSONArray)getJSONArrayFromPath( "Users" );
				
	}
	
	public Boolean getEnabled() throws JSONException {
		return currentUser.getBooleanFromPath( "enabled" );
	}
	
	
	public String getName() throws JSONException {
		if( null == this.currentUser ) { System.out.println( "NULL" ); }
		return currentUser.getStringFromPath( "name" );
	}

	public String getemail() throws JSONException {
		return currentUser.getStringFromPath( "email" );
	}
	
	public String getpassword() throws JSONException {
		return currentUser.getStringFromPath( "password" );
	}
	
	public String getconfirmpassword() throws JSONException {
		return currentUser.getStringFromPath( "confirmpassword" );
	}
	
	
	public String getAgencyName() throws JSONException {
		return currentUser.getStringFromPath( "agency" );
	}
	
	public String getgroupname() throws JSONException {
		return currentUser.getStringFromPath( "group" );
	}

	public String getpermission() throws JSONException {
		return currentUser.getStringFromPath( "permission" );
	}
	
	
	public JSONLMUser getUserById( Integer currentUserId ) throws JSONException, JSONSException {
		
		setUserById( currentUserId );
		
		return this;
						
	}

	public void setUserById( Integer currentUserId ) throws JSONException, JSONSException {
		
		this.currentUser = new JsonConfig( getList().getJSONObject( currentUserId ) );
		if( null != this.currentUser ) { System.out.println( "GOOODDDD" ); }				
	}
	
}
