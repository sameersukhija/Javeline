package com.lumata.unit.webservices.xmlrpc;

import org.json.JSONArray;
import org.json.JSONObject;

@JSONPath( path = "methodResponse.params.param.value.array.data" )
public class XMLRPCResponseProductTypes extends JSONEntity {
	
	public XMLRPCResponseProductTypes( JSONObject json ) {
		super( json );
	}
	
	public JSONArray getMsisdn() {
		return (JSONArray)getJSONArrayFromPath("value");
	}
		
}
