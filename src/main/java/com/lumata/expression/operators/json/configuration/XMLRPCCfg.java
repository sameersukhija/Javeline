package com.lumata.expression.operators.json.configuration;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.system.Security;
import com.lumata.common.testing.validating.Format;
import com.lumata.e4o.exceptions.XMLRPCException;


/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class XMLRPCCfg {

	private static final  Logger logger = LoggerFactory.getLogger( XMLRPCCfg.class );
	
	private JSONObject xmlrpcCfg;
	public enum ParamType { LOGIN, STRING, OFFER_ID_LIST }
	
	/* Create a XMLRPC call configuration from a JSONObject */
	public XMLRPCCfg( JSONObject xmlrpcCfg ) {
		
		this.xmlrpcCfg = xmlrpcCfg;
				
	}
	
	/* Create a XMLRPC call configuration loading the JSONObject from the default folder ( <home of the project> ) or resource folder ( src/main/resources/lumata-common-testing ) */
	public XMLRPCCfg( String xmlrpcCfg, IOFileUtils.IOLoadingType loadingType ) throws XMLRPCException {
		
		try {
			
			switch( loadingType ) {
			
				case FILE: { this.xmlrpcCfg = JSONUtils.loadJSONFile( xmlrpcCfg.toLowerCase() + Format.JSON_EXTENSION ); break; }
				case RESOURCE: { this.xmlrpcCfg = JSONUtils.loadJSONResource( xmlrpcCfg.toLowerCase() + Format.JSON_EXTENSION ); break;  }
				default: throw new XMLRPCException( "You cannot load a XMLRPC call configuration from resources different by FILE or RESOURCE" );
			
			}		
									
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new XMLRPCException( e.getMessage(), e );
			
		} 
		
	}
	
	/* Create a XMLRPC call configuration loading the JSONObject from the default folder ( <home of the project> ) or resource folder ( src/main/resources/lumata-common-testing ) */
	public XMLRPCCfg( String folder, String xmlrpcCfg, IOFileUtils.IOLoadingType loadingType ) throws XMLRPCException {
		
		try {
			
			switch( loadingType ) {
			
			case FILE: { this.xmlrpcCfg = JSONUtils.loadJSONFile( folder, xmlrpcCfg + Format.JSON_EXTENSION ); break; }
			case RESOURCE: { this.xmlrpcCfg = JSONUtils.loadJSONResource( folder, xmlrpcCfg + Format.JSON_EXTENSION ); break;  }
			default: throw new XMLRPCException( "You cannot load a XMLRPC call configuration from resources different by FILE or RESOURCE" );
		
		}
									
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new XMLRPCException( e.getMessage(), e );
			
		} 		
			
	}
	
	public String getURL() {
		
		try {
			
			if( !xmlrpcCfg.isNull("url") ) { return xmlrpcCfg.getString("url"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getCall() {
		
		try {
			
			if( !xmlrpcCfg.isNull("call") ) { return xmlrpcCfg.getString("call").toUpperCase(); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public JSONArray getParameters() {
		
		try {
			
			if( !xmlrpcCfg.isNull("parameters") ) { return xmlrpcCfg.getJSONArray("parameters"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public JSONObject getParam( int index ) {
				
		try {
			
			if( !getParameters().isNull(index) ) { return getParameters().getJSONObject( index ); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getParamType( int index ) {
		
		try {
			
			if( !getParam( index ).isNull( "type" ) ) { return getParam( index ).getString( "type" ).toUpperCase(); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public JSONObject getParamData( int index ) {
		
		try {
			
			if( !getParam( index ).isNull( "data" ) ) { return getParam( index ).getJSONObject( "data" ); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public JSONObject createParamData( ParamType type ) {
		
		JSONObject data = null;
		
		try {
		
			data = new JSONObject( "{ \"type\": \"" + type.toString().toUpperCase() + "\", \"data\":\"\" }" );
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
			
		return data;
		
	}

	public JSONObject createParamDataLogin( String user, String password ) {
		
		JSONObject data = null;
		
		try {
		
			data = new JSONObject( "{ \"user\": \"" + user + "\", \"password\": \"" + Security.decrypt( password ) + "\" }" );
						
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
			
		return data;
		
	}
	
	public JSONObject createParamDataString( String value ) {
		
		JSONObject data = null;
		
		try {
		
			data = new JSONObject( "{ \"value\": \"" + value + "\" }" );
			
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
			
		return data;
		
	}
	
	public JSONObject createParamDataOfferIDList( ArrayList<Integer> values ) {
		
		JSONObject data = null;
		
		try {
		
			JSONArray offerIDList = new JSONArray( values );
			
			data = new JSONObject( "{ \"values\": " + offerIDList + " }" );
						
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
			
		return data;
		
	}
	
	public void setURL( String url ) {
		
		try {
			
			if( !xmlrpcCfg.isNull("url") ) { xmlrpcCfg.put( "url", url ); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
	}
	
	public void setCall( String call ) {
		
		try {
			
			if( !xmlrpcCfg.isNull("call") ) { xmlrpcCfg.put( "call", call.toUpperCase() ); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
	}
	
	public void setParameters( JSONArray parameters ) {
		
		try {
			
			if( !xmlrpcCfg.isNull("parameters") ) { xmlrpcCfg.put( "parameters", parameters ); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
	}
	
	public void setParamData( int index, JSONObject data ) {
				
		try {
			
			if( !this.getParameters().isNull( index ) ) { this.getParameters().getJSONObject( index ).put( "data", data ); }
			
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
	}
		
	public String toString() {
		
		return xmlrpcCfg.toString();
		
	}
	
}
