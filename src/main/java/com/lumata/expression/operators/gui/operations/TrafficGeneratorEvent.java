package com.lumata.expression.operators.gui.operations;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.validating.Format;
import com.lumata.expression.operators.exceptions.TrafficGeneratorEventException;

public class TrafficGeneratorEvent extends Operations {

	private static final Logger logger = LoggerFactory.getLogger(TrafficGeneratorEvent.class);
	
	public enum TGELoadingType { FILE, RESOURCE }
	
	private JSONObject tgEventCfg;
	
	public TrafficGeneratorEvent( String environment, TGELoadingType loadingType ) throws TrafficGeneratorEventException {
		
		try {
			
			switch( loadingType ) {
			
				case FILE: { this.tgEventCfg = JSONUtils.loadJSONFile( environment.toLowerCase() + Format.JSON_EXTENSION ); break; }
				case RESOURCE: { this.tgEventCfg = JSONUtils.loadJSONResource( environment.toLowerCase() + Format.JSON_EXTENSION ); break;  }
				default: throw new TrafficGeneratorEventException( "You cannot load a traffic generator event from resources different by FILE or RESOURCE" );
			
			}		
									
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new TrafficGeneratorEventException( e.getMessage(), e );
			
		} catch( IOFileException e ) {			
			
			logger.error( e.getMessage(), e );
			
			throw new TrafficGeneratorEventException( e.getMessage(), e );
			
		} 			
			
	}
	
	public TrafficGeneratorEvent( String folder, String environment, TGELoadingType loadingType ) throws TrafficGeneratorEventException {
		
		try {
			
			switch( loadingType ) {
			
				case FILE: { this.tgEventCfg = JSONUtils.loadJSONFile( folder, environment.toLowerCase() + Format.JSON_EXTENSION ); break; }
				case RESOURCE: { this.tgEventCfg = JSONUtils.loadJSONResource( folder, environment.toLowerCase() + Format.JSON_EXTENSION ); break;  }
				default: throw new TrafficGeneratorEventException( "You cannot load a traffic generator event different by FILE or RESOURCE" );
			
			}
									
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new TrafficGeneratorEventException( e.getMessage(), e );
			
		} catch( IOFileException e ) {			
			
			logger.error( e.getMessage(), e );
			
			throw new TrafficGeneratorEventException( e.getMessage(), e );
			
		} 			
			
	}
	
	public String getSubscriberID() {
		
		try {
			
			if( !tgEventCfg.isNull("subscriber_id") ) { return tgEventCfg.getString("subscriber_id"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getSource() {
		
		try {
			
			if( !tgEventCfg.isNull("source") ) { return tgEventCfg.getString("source"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getInterpretor() {
		
		try {
			
			if( !tgEventCfg.isNull("interpretor") ) { return tgEventCfg.getString("interpretor"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getEventClass() {
		
		try {
			
			if( !tgEventCfg.isNull("event_class") ) { return tgEventCfg.getString("event_class"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
	public String getParameters() {
		
		try {
			
			if( !tgEventCfg.isNull("event_parameters") ) { return tgEventCfg.getString("event_parameters"); }
		
		} catch( Exception e ) {

			logger.error( e.getMessage(), e );
			
		}
		
		return null;
		
	}
	
}
