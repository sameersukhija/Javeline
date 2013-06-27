package com.lumata.common.testing.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.DataModelException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.JSONUtils;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class DataModel {

	private static final  Logger logger = LoggerFactory.getLogger( DataModel.class );
	public enum DMLoadingType { FILE, RESOURCE }
	private JSONObject model; 
			
	public DataModel( String datamodel, DMLoadingType dmLoadingType ) throws DataModelException  {
		
		this.model = null;
		
		try {
			
			switch( dmLoadingType ) {
			
				case FILE: { this.model = JSONUtils.loadJSONFile( datamodel ); break; }
				case RESOURCE: { this.model = JSONUtils.loadJSONResource( datamodel ); break;  }
				default: throw new DataModelException( "You cannot load a data model from resources different by FILE or RESOURCE" );
			
			}
			
		} catch( IOFileException ioe ) { 
			
			logger.error(ioe.getMessage());
			
			ioe.printStackTrace();
			
			throw new DataModelException( ioe.getMessage() );
			
		} catch( JSONSException je ) { 
			
			logger.error(je.getMessage());
			
			je.printStackTrace();			
			
			throw new DataModelException( je.getMessage() );
			
		}
		
	}
	
	public DataModel( String folder, String datamodel, DMLoadingType dmLoadingType ) throws DataModelException {
		
		this.model = null;
		
		try {
			
			switch( dmLoadingType ) {
			
				case FILE: { this.model = JSONUtils.loadJSONFile( folder, datamodel ); break; }
				case RESOURCE: { this.model = JSONUtils.loadJSONResource( folder, datamodel ); break;  }
				default: throw new DataModelException( "You cannot load an environment from resources different by FILE or RESOURCE" );
			
			}
			
		} catch( IOFileException ioe ) { 
			
			logger.error(ioe.getMessage());
			
			ioe.printStackTrace();
			
			throw new DataModelException( ioe.getMessage() );
			
		} catch( JSONSException je ) { 
			
			logger.error(je.getMessage());
			
			je.printStackTrace();			
			
			throw new DataModelException( je.getMessage() );
			
		}
		
	}
	
	public JSONObject getDataModel() {
		
		return this.model;
		
	}

	public JSONArray getModel( String tableName ) {
		
		try {
			
			if( this.model.isNull( tableName ) ) return null;
			else return this.model.getJSONArray( tableName );
		
		} catch( JSONException je ) {

			logger.error(je.getMessage());
			
			je.printStackTrace();
		
		}
		
		return null;
		
	}

	public String getModelLabel( String tableName, int index ) {
		
		try {
			
			if( this.getModel( tableName ).getJSONObject(index).isNull("label") ) return null;
			else return this.getModel(tableName).getJSONObject(index).getString("label");
		
		} catch( Exception e ) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
		}
		
		return null;
		
	}

	public JSONArray getModelValidator( String tableName, int index ) {
		
		try {
			
			if( this.getModel( tableName ).getJSONObject(index).isNull("validator") ) return null;
			else return this.getModel( tableName ).getJSONObject(index).getJSONArray("validator");
		
		} catch( Exception e ) {
			
			logger.error(e.getMessage());
			
			e.printStackTrace();
			
		}
		
		return null;
		
	}
	
	
}
