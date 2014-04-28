package com.lumata.e4o.json.testplan;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.expression.operators.json.common.JsonConfig;


/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class JSONTestCase extends JsonConfig {

	private static final  Logger logger = LoggerFactory.getLogger( JSONTestCase.class );
	
	public JSONTestCase( JSONObject testCaseCfg ) {
		
		super( testCaseCfg );
			
	}

    public String getInternalId() { 		
    	return getStringFromPath("internalid"); 		
	}
    
    public String getExternalId() { 		
    	return getStringFromPath("externalid"); 		
	}
    
    public String getName() { 		
    	return getStringFromPath("name"); 		
	}

	public String getSummary() { 		
    	return getStringFromPath("summary"); 		
 	}
	
	public String getVersion() { 		
    	return getStringFromPath("version"); 		
 	}
	
	public String getPreconditions() { 		
    	return getStringFromPath("preconditions"); 		
 	}

	public String getExecutionType() { 		
    	return getStringFromPath("execution_type"); 		
 	}
	
	public String getNodeOrder() { 		
    	return getStringFromPath("node_order"); 		
 	}
	
	public String getImportance() { 		
    	return getStringFromPath("importance"); 		
 	}
	
    public void setInternalId( String value ) { 		
    	setObjectFromPath("internalid", value); 		
	}
    
    public void setExternalId( String value ) { 		
    	setObjectFromPath("externalid", value); 		
	}
    
    public void setName( String value ) { 		
    	setObjectFromPath("name", value); 		
	}

	public void setSummary( String value ) { 		
    	setObjectFromPath("summary", value); 		
 	}
	
	public void setVersion( String value ) { 		
    	setObjectFromPath("version", value); 		
 	}
	
	public void setPreconditions( String value ) { 		
    	setObjectFromPath("preconditions", value); 		
 	}

	public void setExecutionType( String value ) { 		
    	setObjectFromPath("execution_type", value); 		
 	}
	
	public void setNodeOrder( String value ) { 		
    	setObjectFromPath("node_order", value); 		
 	}
	
	public void setImportance( String value ) { 		
    	setObjectFromPath("importance", value); 		
 	}
	
}
