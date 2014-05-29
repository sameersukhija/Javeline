package com.lumata.e4o.json.gui.administrationmanager;

import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.json.JsonConfigurationFile;

public class JSONComplements extends JsonConfigurationFile {
	
	public JSONComplements(String path, String jsonFile) throws JSONSException {
		super(path, jsonFile);
	}

	public String getName() {
		
		return getCurrentElement().getStringFromPath("name");
	}
	
	@Override
	public String getElementsSectionLabel() {

		return "complements";
	}
}
