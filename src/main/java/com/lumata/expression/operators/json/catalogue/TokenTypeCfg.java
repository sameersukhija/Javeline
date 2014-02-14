package com.lumata.expression.operators.json.catalogue;

import java.util.ArrayList;
import java.util.Iterator;

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
import com.lumata.expression.operators.exceptions.RuleException;
import com.lumata.expression.operators.exceptions.TokenTypeException;


/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class TokenTypeCfg {

	private static final  Logger logger = LoggerFactory.getLogger( TokenTypeCfg.class );
	
	public enum TokenTypeValidity { seconds, minutes, hours, days }
	
	private JSONObject ttCfg;
	
	public TokenTypeCfg( JSONObject tokenType ) {
		
		this.ttCfg = tokenType;
				
	}
	
	public TokenTypeCfg( String tokenType, IOLoadingType loadingType ) throws TokenTypeException {
		
		try {
			
			switch( loadingType ) {
			
				case FILE: { this.ttCfg = JSONUtils.loadJSONFile( tokenType.toLowerCase() + Format.JSON_EXTENSION ); break; }
				case RESOURCE: { this.ttCfg = JSONUtils.loadJSONResource( tokenType.toLowerCase() + Format.JSON_EXTENSION ); break;  }
				default: throw new TokenTypeException( "You cannot load an Campaign from resources different by FILE or RESOURCE" );
			
			}		
									
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new TokenTypeException( e.getMessage(), e );
			
		} catch( IOFileException e ) {			
			
			logger.error( e.getMessage(), e );
			
			throw new TokenTypeException( e.getMessage(), e );
			
		} 			
			
	}
	
	public TokenTypeCfg( String folder, String tokenType, IOLoadingType loadingType ) throws TokenTypeException {
		
		try {
			
			switch( loadingType ) {
			
				case FILE: { this.ttCfg = JSONUtils.loadJSONFile( folder, tokenType.toLowerCase() + Format.JSON_EXTENSION ); break; }
				case RESOURCE: { this.ttCfg = JSONUtils.loadJSONResource( folder, tokenType.toLowerCase() + Format.JSON_EXTENSION ); break;  }
				default: throw new TokenTypeException( "You cannot load an Campaign from resources different by FILE or RESOURCE" );
			
			}
									
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new TokenTypeException( e.getMessage(), e );
			
		} catch( IOFileException e ) {			
			
			logger.error( e.getMessage(), e );
			
			throw new TokenTypeException( e.getMessage(), e );
			
		} 			
			
	}
	
	public static ArrayList<TokenTypeCfg> createTokenTypeList(  String folder, String path, IOLoadingType loadingType ) throws TokenTypeException {
		
		ArrayList<TokenTypeCfg> list = new ArrayList<TokenTypeCfg>();
		
		try {
			
			JSONObject token_type_list;
			
			switch( loadingType ) {
			
				case FILE: { token_type_list = JSONUtils.loadJSONFile( folder, path.toLowerCase() + Format.JSON_EXTENSION ); break; }
				case RESOURCE: { token_type_list = JSONUtils.loadJSONResource( folder, path.toLowerCase() + Format.JSON_EXTENSION ); break;  }
				default: throw new TokenTypeException( "You cannot load an Campaign from resources different by FILE or RESOURCE" );
			
			}
			
			JSONArray token_types = token_type_list.getJSONArray( "token_type_list" );
			
			for( int i = 0; i < token_types.length(); i++ ) {
				
				list.add( new TokenTypeCfg( token_types.getJSONObject( i ) ) );
								
			}			
		
		} catch (JSONException e) {
			
			logger.error( e.getMessage(), e );
			
			throw new TokenTypeException( e.getMessage(), e );
			
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new TokenTypeException( e.getMessage(), e );
			
		} catch( IOFileException e ) {			
			
			logger.error( e.getMessage(), e );
			
			throw new TokenTypeException( e.getMessage(), e );
			
		} 
		
		return list;
		
	}
	
	public String getName() {
		
		try {
			
			if( !ttCfg.isNull("name") ) { return ttCfg.getString("name"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getFormat() {
		
		try {
			
			if( !ttCfg.isNull("format") ) { return ttCfg.getString("format"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

	public String getValidityUnit() {
		
		try {
			
			if( !ttCfg.isNull("validityUnit") ) { return ttCfg.getString("validityUnit"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

	public String getValidity() {
		
		try {
			
			if( !ttCfg.isNull("validity") ) { return ttCfg.getString("validity"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public boolean getUsageUnlimited() {
		
		try {
			
			if( !ttCfg.isNull("usageUnlimited") ) { return ttCfg.getBoolean("usageUnlimited"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return true;
		
	}
	
	public String getUsage() {
		
		try {
			
			if( !ttCfg.isNull("usage") ) { return ttCfg.getString("usage"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}

}
