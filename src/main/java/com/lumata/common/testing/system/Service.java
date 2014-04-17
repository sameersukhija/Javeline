package com.lumata.common.testing.system;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class Service extends Server {

	private static final  Logger logger = LoggerFactory.getLogger( Service.class );
	
	public enum Type { ssh, activemq } 
	
	private Enum<Type> type;
	
	public Service() {}
	
	public Service( JSONObject service, String type ) {
		
		this( service, Type.valueOf( type ) );
		
	}

	public Service( JSONObject service, Type type ) {
		
		super( service );	
		
		this.type = type; 
		
	}

	public Enum<Type> getType() {
		return this.type;
	}

	public void setType( String type ) {
		this.type = Type.valueOf( type );
	}

	public void setType( Enum<Type> type ) {
		this.type = type;
	}
		
}
