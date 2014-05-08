package com.lumata.e4o.json.testplan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.e4o.json.common.JsonConfig;


/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class JSONTestSuite extends JsonConfig {

	private static final  Logger logger = LoggerFactory.getLogger( JSONTestSuite.class );
	
	public JSONTestSuite( JSONObject testCaseCfg ) {
		
		super( testCaseCfg );
			
	}

    public String getName() { 		
    	return getStringFromPath("name"); 		
	}

	public String getDetails() { 		
    	return getStringFromPath("details"); 		
 	}
	
	public String getNodeOrder() { 		
    	return getStringFromPath("node_order"); 		
 	}
	
	public JSONArray getTestSuite() throws JSONException { 		
    	return getJSONArrayFromPath("testsuite"); 		
 	}
	
	public JSONArray getTestCase() throws JSONException { 		
    	return getJSONArrayFromPath("testcase"); 		
 	}
	
   public void setName( String value ) { 		
    	setObjectFromPath("name", value); 		
	}

	public void setDetails( String value ) { 		
    	setObjectFromPath("details", value); 		
 	}
	
	public void setNodeOrder( String value ) { 		
    	setObjectFromPath("node_order", value); 		
 	}
	
	public void setTestSuite( JSONArray value ) { 		
    	setObjectFromPath("testsuite", value); 		
 	}
	
	public void setTestCase( JSONArray value ) { 		
    	setObjectFromPath("testcase", value); 		
 	}
	
}
