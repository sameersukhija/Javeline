package com.lumata.expression.operators.json.loyalty;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.validating.Format;

/*

{
	"accordion_name": "Badges",
	"program_name": "BadgesProgName",
	"program_desc": "BadgesProgDesc",
	"type_name": "Gold"
}

 */

public class LoyaltyCreateCfg {

	private JSONObject root;
	
	public LoyaltyCreateCfg(String path, String jsonFile) throws JSONSException, IOFileException {
		root = JSONUtils.loadJSONResource(path, jsonFile + Format.JSON_EXTENSION);
	}
	
	public String getAccordionName() throws JSONException {
		return root.getString("accordion_name");
	}

	public String getProgramName() throws JSONException {
		return root.getString("program_name");
	}

	public String getProgramDesc() throws JSONException {
		return root.getString("program_desc");
	}

	public List<String> getTypeNameList() throws JSONException {
		List<String> list = new ArrayList<String>();
		
		JSONArray jsonArray = root.getJSONArray("type_name_list");
		
		for (int i=0; i<jsonArray.length(); i++) {
			list.add( jsonArray.getString(i) );
		}
		
		return list;
	}
	
	public static void main(String[] args) throws Exception {
		
		LoyaltyCreateCfg cfg = new LoyaltyCreateCfg("input/loyalties", "loyalty_create");
		
		System.out.println(cfg.getAccordionName());
		System.out.println(cfg.getProgramName());
		System.out.println(cfg.getProgramDesc());
		System.out.println(cfg.getTypeNameList());
	}
}
