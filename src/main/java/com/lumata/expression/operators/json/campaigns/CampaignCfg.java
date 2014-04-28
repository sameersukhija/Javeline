package com.lumata.expression.operators.json.campaigns;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.IOFileUtils.IOLoadingType;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.CampaignException;
import com.lumata.e4o.exceptions.CampaignModelException;


/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class CampaignCfg {

	private static final  Logger logger = LoggerFactory.getLogger( CampaignCfg.class );
	
	private JSONObject cmCfg;
	
	public CampaignCfg( JSONObject CampaignModel ) {
		
		this.cmCfg = CampaignModel;
				
	}
	
	public CampaignCfg( String CampaignModel, IOLoadingType loadingType ) throws CampaignException {
		
		try {
			
			switch( loadingType ) {
			
				case FILE: { this.cmCfg = JSONUtils.loadJSONFile( CampaignModel.toLowerCase() + Format.JSON_EXTENSION ); break; }
				case RESOURCE: { this.cmCfg = JSONUtils.loadJSONResource( CampaignModel.toLowerCase() + Format.JSON_EXTENSION ); break;  }
				default: throw new CampaignException( "You cannot load an Campaign from resources different by FILE or RESOURCE" );
			
			}		
									
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new CampaignException( e.getMessage(), e );
			
		} 			
			
	}
	
	/* Create an CampaignModel loading the JSONObject from the file or resource folder ( src/main/resources/lumata-common-testing/folder ) */
	public CampaignCfg( String folder, String CampaignModel, IOLoadingType loadingType ) throws CampaignException {
		
		try {
			
			switch( loadingType ) {
			
			case FILE: { this.cmCfg = JSONUtils.loadJSONFile( folder, CampaignModel.toLowerCase() + Format.JSON_EXTENSION ); break; }
			case RESOURCE: { this.cmCfg = JSONUtils.loadJSONResource( folder, CampaignModel.toLowerCase() + Format.JSON_EXTENSION ); break;  }
			default: throw new CampaignException( "You cannot load an Campaign from resources different by FILE or RESOURCE" );
		
		}
									
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new CampaignException( e.getMessage(), e );
			
		} 			
			
	}
	
	/*
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
	*/
	// Campaign ATTRIBUTES
	public JSONObject getDefinition() {
		
		try {
			
			if( !cmCfg.isNull("definition") ) { return cmCfg.getJSONObject("definition"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getExecutionMode() {
		
		try {
			
			if( !getDefinition().isNull("execution_mode") ) { return getDefinition().getString("execution_mode"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

	public String getCampaignModel() {
		
		try {
			
			if( !getDefinition().isNull("campaign_model") ) { return getDefinition().getString("campaign_model"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

	public String getCampaignName() {
		
		try {
			
			if( !getDefinition().isNull("campaign_name") ) { return getDefinition().getString("campaign_name"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

	public String getCampaignDescription() {
		
		try {
			
			if( !getDefinition().isNull("campaign_description") ) { return getDefinition().getString("campaign_description"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public JSONObject getErrorActions() {
		
		try {
		
			if( !cmCfg.isNull("error_actions") ) { return cmCfg.getJSONObject("error_actions"); }
		
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	
	public void setCampaignName( String name ) {
		
		try {
			
			if( !getDefinition().isNull("campaign_name") ) { getDefinition().put( "campaign_name", name ); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
	}
	
	public void setCampaignDescription( String description ) {
		
		try {
			
			if( !getDefinition().isNull("campaign_description") ) { getDefinition().put( "campaign_description", description ); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
	}
	
	
	
	/*
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
