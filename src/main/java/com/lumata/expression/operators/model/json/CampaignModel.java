package com.lumata.expression.operators.model.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.validating.Format;
import com.lumata.expression.operators.exceptions.CampaignModelException;


/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class CampaignModel {

	private static final  Logger logger = LoggerFactory.getLogger( CampaignModel.class );
	
	private JSONObject cmCfg;
	
	public enum CMLoadingType { FILE, RESOURCE }
	
	/* Create an CampaignModel from a JSONObject */
	public CampaignModel( JSONObject CampaignModel ) {
		
		this.cmCfg = CampaignModel;
				
	}
	
	/* Create an CampaignModel loading the JSONObject from the default folder ( <home of the project> ) or resource folder ( src/main/resources/lumata-common-testing ) */
	public CampaignModel( String CampaignModel, CMLoadingType loadingType ) throws CampaignModelException {
		
		try {
			
			switch( loadingType ) {
			
				case FILE: { this.cmCfg = JSONUtils.loadJSONFile( CampaignModel.toLowerCase() + Format.JSON_EXTENSION ); break; }
				case RESOURCE: { this.cmCfg = JSONUtils.loadJSONResource( CampaignModel.toLowerCase() + Format.JSON_EXTENSION ); break;  }
				default: throw new CampaignModelException( "You cannot load an CampaignModel from resources different by FILE or RESOURCE" );
			
			}		
									
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new CampaignModelException( e.getMessage(), e );
			
		} catch( IOFileException e ) {			
			
			logger.error( e.getMessage(), e );
			
			throw new CampaignModelException( e.getMessage(), e );
			
		} 			
			
	}
	
	/* Create an CampaignModel loading the JSONObject from the file or resource folder ( src/main/resources/lumata-common-testing/folder ) */
	public CampaignModel( String folder, String CampaignModel, CMLoadingType loadingType ) throws CampaignModelException {
		
		try {
			
			switch( loadingType ) {
			
			case FILE: { this.cmCfg = JSONUtils.loadJSONFile( folder, CampaignModel.toLowerCase() + Format.JSON_EXTENSION ); break; }
			case RESOURCE: { this.cmCfg = JSONUtils.loadJSONResource( folder, CampaignModel.toLowerCase() + Format.JSON_EXTENSION ); break;  }
			default: throw new CampaignModelException( "You cannot load an CampaignModel from resources different by FILE or RESOURCE" );
		
		}
									
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new CampaignModelException( e.getMessage(), e );
			
		} catch( IOFileException e ) {			
			
			logger.error( e.getMessage(), e );
			
			throw new CampaignModelException( e.getMessage(), e );
			
		} 			
			
	}
	
	public static JSONObject getBasicCampaignModel() {
		
		JSONObject basicCM = new JSONObject();
		
		try {
		
			basicCM.put( "name", "" );
			basicCM.put( "description", "" );
			basicCM.put( "event_type", new JSONArray() );
		
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		return basicCM;
		
	}
	
	// CampaignModel ATTRIBUTES
	public String getName() {
		
		try {
			
			if( !cmCfg.isNull("name") ) { return cmCfg.getString("name"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

	public String getDescription() {
		
		try {
			
			if( !cmCfg.isNull("description") ) { return cmCfg.getString("description"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public JSONArray getEventsList() {
		
		try {
			
			if( !cmCfg.isNull("events_list") ) { return cmCfg.getJSONArray("events_list"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getEventType( int index ) {
		
		try {
			
			if( !getEventsList().isNull( index ) ) { return getEventsList().getJSONObject(index).getString("event_type"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getCriteria( int index ) {
		
		try {
			
			if( !getEventsList().isNull( index ) ) { return getEventsList().getJSONObject(index).getString("criteria"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getAction( int index ) {
		
		try {
			
			if( !getEventsList().isNull( index ) ) { return getEventsList().getJSONObject(index).getString("action"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

	public void setName( String value ) {
		
		try {
			
			cmCfg.put( "name", value );
		
		} catch( JSONException e ) {

			logger.error( e.getMessage(), e );
			
		}
				
	}

	/*
	public Properties getDataSourceAsProps( String dataSource ) {
		
		Properties props = new Properties();
		
		try {
			
			if( getDataSources().isNull(dataSource) ) { return null; }
			else {
				JSONObject ds = getDataSource( dataSource );
				if( ds.isNull("host")) { props.put("dbHost", "localhost"); } else { props.put("dbHost", ds.getString("host")); }
				if( ds.isNull("name")) { props.put("dbName", ""); } else { props.put("dbName", ds.getString("name")); }
				if( ds.isNull("port")) { props.put("dbPort", "3306"); } else { props.put("dbPort", ds.getString("port")); }
				if( ds.isNull("user")) { props.put("dbUser", ""); } else { props.put("dbUser", ds.getString("user")); }
				if( ds.isNull("password")) { props.put("dbPasswd", ""); } else { props.put("dbPasswd", ds.getString("password")); }
					
				return props;
			}
			
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	*/
}
