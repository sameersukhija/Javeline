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
	
	public enum ValidatorType { NONE, DATE, MSISDN, LENGHT, ENUM, MAP, EMAIL; }
	
	public Validator( JSONArray validator ) {
		
		this.validatorCfg = validator;
	
	}
	
	public boolean validation( String value, Object obj ) {
		
		
		//try {			
			
			for( int i = 0; i < validatorCfg.length(); i++ ) {
				
				switch( getValidatorType( i ) ) {
				
					case NONE: { 
						return true;
					}
					case DATE: { 
						if( Format.isDate( value, getValidatorFormat( i ), getValidatorAllowBlank( i ) ) != getValidatorExpected( i ) ) { return false; } 
						break;
					} 
					case MSISDN: { 
						if( Format.isMSISDN( value, getValidatorFormat( i ), getValidatorFilter( i ), getValidatorAllowBlank( i ) ) != getValidatorExpected( i ) ) { return false; } 
						break;  
					}
					case LENGHT: { 
						if( Format.isLength( value, Integer.valueOf(getValidatorParam( i )), Operators.valueOf(getValidatorOperator( i )), getValidatorAllowBlank( i ) ) != getValidatorExpected( i ) ) { return false; } 
						break; 
					}
					case ENUM: { 
						if( Format.isEnum( value, getValidatorFormat( i ), getValidatorAllowBlank( i ) ) != getValidatorExpected( i ) ) { return false; } 
						break; 
					}
					case MAP: { 
						if( Format.isMapKey( value, getValidatorFormat( i ), obj, getValidatorAllowBlank( i ) ) != getValidatorExpected( i ) ) { return false; }
						break; 
					}
					case EMAIL: { 
						if( Format.isEmail( value, getValidatorFormat( i ), getValidatorAllowBlank( i ) ) != getValidatorExpected( i ) ) { return false; } 
						break; 
					}	
					default: return false;
					
				}
				
			}
			
			return true;
			
		//} catch( Exception e ) { return false; }
				
	}
	
	public JSONObject getValidator( int index ) {
		
		try {
			
			if( !validatorCfg.isNull(index) ) { return validatorCfg.getJSONObject(index); }
			
		} catch( Exception e ) {
			
			logger.error( e.getMessage(), e );
		
		} 
		
		return null;
		
	}

	public ValidatorType getValidatorType( int index ) {
		
		try {
			
			if( !getValidator( index ).isNull("validationType") ) { 
				return ValidatorType.valueOf(getValidator( index ).getString("validationType"));
			}
			
		} catch( Exception e ) {
			
			logger.error( e.getMessage(), e );
		
		} 
		
		return null;
		
	}
	
	public String getValidatorParam( int index ) {
		
		try {
			
			if( !getValidator( index ).isNull("validationParam") ) {
				return getValidator( index ).getString("validationParam");
			}
			
		} catch( Exception e ) {
			
			logger.error( e.getMessage(), e );
		
		}
		
		return null;
		
	}
	
	public String getValidatorFormat( int index ) {
		
		try {
			
			if( !getValidator( index ).isNull("validationFormat") ) {
				return getValidator( index ).getString("validationFormat");
			}
			
		} catch( Exception e ) {
			
			logger.error( e.getMessage(), e );
		
		}
		
		return null;
		
	}
	
	public String getValidatorFilter( int index ) {
		
		try {
			
			if( !getValidator( index ).isNull("validationFilter") ) {
				return getValidator( index ).getString("validationFilter");
			}
			
		} catch( Exception e ) {
			
			logger.error( e.getMessage(), e );
		
		}
		
		return null;
		
	}
	
	public String getValidatorOperator( int index ) {
		
		try {
			
			if( !getValidator( index ).isNull("validationOp") ) {
				return getValidator( index ).getString("validationOp");
			}
			
		} catch( Exception e ) {
			
			logger.error( e.getMessage(), e );
		
		}
		
		return null;
		
	}
	
	public boolean getValidatorAllowBlank( int index ) {

		try {
			
			if( !getValidator( index ).isNull("validationAllowBlank") ) {
				return getValidator( index ).getBoolean("validationAllowBlank");
			}
			
		} catch( Exception e ) {
			
			logger.error( e.getMessage(), e );
		
		}
		
		return false;
		
	}

	public boolean getValidatorExpected( int index ) {
		
		try {
			
			if( !getValidator( index ).isNull("validationExpected") ) {
				return getValidator( index ).getBoolean("validationExpected");
			}
			
		} catch( Exception e ) {
			logger.error( e.getMessage(), e );
		}
		
		return false;
		
	}
	
	public void setValidator( int index, JSONObject validator ) {
		
		try {
			
			this.validatorCfg.put( index, validator );
			
		} catch( Exception e ) {
			
			logger.error( e.getMessage(), e );
		
		} 
		
	}

	public void setValidatorType( int index, String validatorType ) {
		
		try {
			
			this.validatorCfg.put( index, ValidatorType.valueOf( validatorType ) );
						
		} catch( Exception e ) {
			
			logger.error( e.getMessage(), e );
		
		}
		
	}
	
	public void setValidatorParam( int index, String validatorParam ) {
		
		try {
			
			this.validatorCfg.put( index, validatorParam );
			
		} catch( Exception e ) {
			
			logger.error( e.getMessage(), e );
		
		}
		
	}
	
	public void setValidatorFormat( int index, String validatorFormat ) {
		
		try {
			
			this.validatorCfg.put( index, validatorFormat );
			
		} catch( Exception e ) {
			
			logger.error( e.getMessage(), e );
		
		}
		
	}
	
	public void setValidatorFilter( int index, String validatorFilter ) {
		
		try {
			
			this.validatorCfg.put( index, validatorFilter );
			
		} catch( Exception e ) {
			
			logger.error( e.getMessage(), e );
		
		}
		
	}
	
	public void setValidatorOperator( int index, String validationOp ) {
		
		try {
			
			this.validatorCfg.put( index, validationOp );
			
		} catch( Exception e ) {
			
			logger.error( e.getMessage(), e );
		
		}
		
	}
	
	public void setValidatorAllowBlank( int index, boolean validationAllowBlank ) {

		try {
			
			this.validatorCfg.put( index, validationAllowBlank );
			
		} catch( Exception e ) {
			
			logger.error( e.getMessage(), e );
		
		}
		
	}

	public void setValidatorExpected( int index, boolean validationExpected ) {
		
		try {
			
			this.validatorCfg.put( index, validationExpected );
			
		} catch( Exception e ) {
			
			logger.error( e.getMessage(), e );
		
		}
		
	}

}
