package com.lumata.expression.operators.json.loyalty;

import org.json.JSONException;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.expression.operators.json.common.JsonConfig;

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
	
	public static void main(String[] args) throws Exception {
		
		LoyaltyManageCfg cfg = new LoyaltyManageCfg("input/loyalties", "loyalty_manage");
		
		System.out.println(cfg.getDefinitionName());
	}
}
