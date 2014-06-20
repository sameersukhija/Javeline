package com.lumata.e4o.json.gui.loyaltymanager;

import java.util.List;

import org.json.JSONException;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.e4o.json.common.JsonConfig;

/*

{
	"accordion_name": "Badges",
	"program_name": "BadgesProgName",
	"program_desc": "BadgesProgDesc",
	"type_name_list": ["Gold", "Silver"]
}

 */

public class LoyaltyCreateCfg extends JsonConfig {

	public LoyaltyCreateCfg(String path, String jsonFile) throws JSONSException, IOFileException {
		super(path, jsonFile);
	}
	
	public String getAccordionName() throws JSONException {
		return getStringFromPath("accordion_name");
	}

	public String getProgramName() throws JSONException {
		return getStringFromPath("program_name");
	}

	public String getProgramDesc() throws JSONException {
		return getStringFromPath("program_desc");
	}

	public List<String> getTypeNameList() throws JSONException {
		return getStringList("type_name_list");
	}
	
//	public static void main(String[] args) throws Exception {
//		
//		LoyaltyCreateCfg cfg = new LoyaltyCreateCfg("input/loyalties", "loyalty_create");
//		
//		System.out.println(cfg.getAccordionName());
//		System.out.println(cfg.getProgramName());
//		System.out.println(cfg.getProgramDesc());
//		System.out.println(cfg.getTypeNameList());
//	}
}
