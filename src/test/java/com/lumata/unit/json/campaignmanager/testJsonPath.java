package com.lumata.unit.json.campaignmanager;

import com.jayway.restassured.path.json.JsonPath;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.JSONUtils;

public class testJsonPath {

	
	public static void main(String [ ] args) throws JSONSException {
	
		JsonPath jp = new JsonPath ( JSONUtils.loadJSONResource( "input/campaignmanager/campaigns", "campaignCMTemplate.json" ).toString() );
		
		System.out.println( ( null != jp.get( "campaigns[0].limit" ) ) );
		
	}
	
}
