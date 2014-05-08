package com.lumata.e4o.json.gui.loyaltymanager;

import org.json.JSONException;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.json.common.JsonConfig;

/*

{
	"definition": {
		"name": "badgeDefinitionName",
		"description": "badgeDefinitionDesc"
	},

	"scheduling": {
	},
	
	"pending": {
	},

	"awarded": {
		"event_type": "Revenue",
		"action_plus": "1",
		"action_unit": "/recharge"
	},

	"redeemed": {
	},
	
	"activation": {
	}
}


 */

public class LoyaltyManageCfg extends JsonConfig {

	public LoyaltyManageCfg(String path, String jsonFile) throws JSONSException, IOFileException {
		super(path, jsonFile);
	}
	
	public String getDefinitionName() throws JSONException {
		return getStringFromPath("definition/name");
	}

	public String getDefinitionDescription() throws JSONException {
		return getStringFromPath("definition/description");
	}

	public String getAwardedEventType() throws JSONException {
		return getStringFromPath("awarded/event_type");
	}

	public String getAwardedActionPlus() throws JSONException {
		return getStringFromPath("awarded/action_plus");
	}

	public String getAwardedActionUnit() throws JSONException {
		return getStringFromPath("awarded/action_unit");
	}

	public static void main(String[] args) throws Exception {
		
		LoyaltyManageCfg cfg = new LoyaltyManageCfg("input/loyalties", "loyalty_manage");
		
		System.out.println(cfg.getDefinitionName());
		System.out.println(cfg.getDefinitionDescription());
		System.out.println(cfg.getAwardedEventType());
		System.out.println(cfg.getAwardedActionPlus());
		System.out.println(cfg.getAwardedActionUnit());
	}
}
