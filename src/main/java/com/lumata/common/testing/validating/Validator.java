package com.lumata.common.testing.validating;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.validating.Format.Operators;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class Validator {

	private static final  Logger logger = LoggerFactory.getLogger( Validator.class );
	
	private JSONArray validatorCfg = null;
	
	public enum validatorType { NONE, DATE, MSISDN, LENGHT, ENUM, MAP, EMAIL; }
	
	public Validator( JSONArray validator ) {
		this.validatorCfg = validator;
	}
	
	public boolean validation( String value, Object obj ) {
		
		
		try {			
			
			for( int i = 0; i < validatorCfg.length(); i++ ) {
				
				switch( loadValidatorType( i ) ) {
				
					case NONE: { 
						return true;
					}
					case DATE: { 
						if( Format.isDate( value, loadValidatorFormat( i ), loadValidatorAllowBlank( i ) ) != loadValidatorExpected( i ) ) { return false; } 
						break;
					} 
					case MSISDN: { 
						if( Format.isMSISDN( value, loadValidatorFormat( i ), loadValidatorFilter( i ), loadValidatorAllowBlank( i ) ) != loadValidatorExpected( i ) ) { return false; } 
						break;  
					}
					case LENGHT: { 
						if( Format.isLength( value, Integer.valueOf(loadValidatorParam( i )), Operators.valueOf(loadValidatorOperator( i )), loadValidatorAllowBlank( i ) ) != loadValidatorExpected( i ) ) { return false; } 
						break; 
					}
					case ENUM: { 
						if( Format.isEnum( value, loadValidatorFormat( i ), loadValidatorAllowBlank( i ) ) != loadValidatorExpected( i ) ) { return false; } 
						break; 
					}
					case MAP: { 
						if( Format.isMapKey( value, loadValidatorFormat( i ), obj, loadValidatorAllowBlank( i ) ) != loadValidatorExpected( i ) ) { return false; }
						break; 
					}
					case EMAIL: { 
						if( Format.isEmail( value, loadValidatorFormat( i ), loadValidatorAllowBlank( i ) ) != loadValidatorExpected( i ) ) { return false; } 
						break; 
					}	
					default: return false;
					
				}
				
			}
			
			return true;
			
		} catch( Exception e ) { return false; }
				
	}
	
	private JSONObject loadValidator( int index ) {
		
		try {
			
			if( !validatorCfg.isNull(index) ) { return validatorCfg.getJSONObject(index); }
			
		} catch( Exception e ) {
			logger.error( e.getMessage(), e );
		} 
		
		return null;
		
	}

	private validatorType loadValidatorType( int index ) {
		
		try {
			
			if( !loadValidator( index ).isNull("validationType") ) { 
				return validatorType.valueOf(loadValidator( index ).getString("validationType"));
			}
			
		} catch( Exception e ) {
			logger.error( e.getMessage(), e );
		} 
		
		return null;
		
	}
	
	private String loadValidatorParam( int index ) {
		
		try {
			
			if( !loadValidator( index ).isNull("validationParam") ) {
				return loadValidator( index ).getString("validationParam");
			}
			
		} catch( Exception e ) {
			logger.error( e.getMessage(), e );
		}
		
		return null;
		
	}
	
	private String loadValidatorFormat( int index ) {
		
		try {
			
			if( !loadValidator( index ).isNull("validationFormat") ) {
				return loadValidator( index ).getString("validationFormat");
			}
			
		} catch( Exception e ) {
			logger.error( e.getMessage(), e );
		}
		
		return null;
		
	}
	
	private String loadValidatorFilter( int index ) {
		
		try {
			
			if( !loadValidator( index ).isNull("validationFilter") ) {
				return loadValidator( index ).getString("validationFilter");
			}
			
		} catch( Exception e ) {
			logger.error( e.getMessage(), e );
		}
		
		return null;
		
	}
	
	private String loadValidatorOperator( int index ) {
		
		try {
			
			if( !loadValidator( index ).isNull("validationOp") ) {
				return loadValidator( index ).getString("validationOp");
			}
			
		} catch( Exception e ) {
			logger.error( e.getMessage(), e );
		}
		
		return null;
		
	}
	
	private boolean loadValidatorAllowBlank( int index ) {

		try {
			
			if( !loadValidator( index ).isNull("validationAllowBlank") ) {
				return loadValidator( index ).getBoolean("validationAllowBlank");
			}
			
		} catch( Exception e ) {
			logger.error( e.getMessage(), e );
		}
		
		return false;
		
	}

	private boolean loadValidatorExpected( int index ) {
		
		try {
			
			if( !loadValidator( index ).isNull("validationExpected") ) {
				return loadValidator( index ).getBoolean("validationExpected");
			}
			
		} catch( Exception e ) {
			logger.error( e.getMessage(), e );
		}
		
		return false;
		
	}

}
