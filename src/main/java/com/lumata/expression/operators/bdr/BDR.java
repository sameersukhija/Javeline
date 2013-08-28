package com.lumata.expression.operators.bdr;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BDR {

	private static final Logger logger = LoggerFactory.getLogger( BDR.class );
	
	private String format_id;
	private String msisdn;
	private String transaction_id;
	private String current_date;
	private String reward_name;
	private String reward_qty;
	private String operation_type;
	private String module;
	private String origin;
	private String originator;
	private String initial_reward_qty;
	private String reward_limitation_cause;
	private boolean _validation;
	
	private final String DELIMITER = "\\;";
	private final int N_FIELDS = 12;
	private final String[] PARAMETERS_ORDER = new String[]{ "FormatID", "MSISDN", "TransactionID", "CurrentDate", "RewardName", "RewardQty", "OperationType", "Module", "Origin", "Originator", "InitialRewardQty", "RewardLimitationCause" }; 
	
	public BDR( String bdrStr) {
				
		String[] bdrToken = bdrStr.split( DELIMITER, N_FIELDS );
					
		for( int i = 0; i < bdrToken.length; i++ ) {
			
			this.set( "set" + PARAMETERS_ORDER[ i ], bdrToken[ i ] );
		
		}
		
		for( int j = bdrToken.length; j < N_FIELDS; j++ ) {
			
			this.set( "set" + PARAMETERS_ORDER[ j ], "" );
			
		}
		
		this.setValidation( false );
		
	}
	
	public String get( String methodName ) {
		
		Method method; 
		
		try {
			
			method = this.getClass().getDeclaredMethod( methodName, String.class );
			return (String)method.invoke( this );
		
		} catch( NoSuchMethodException e ) {
			logger.error( e.getMessage(), e );
		} catch( IllegalAccessException e ) {
			logger.error( e.getMessage(), e );
		} catch( InvocationTargetException e ) {
			logger.error( e.getMessage(), e );
		}	
		
		return null;
		
	}

	public String getFormatID() {
		
		return this.format_id;
		
	}
	
	public String getMSISDN() {
		
		return this.msisdn;
		
	}

	public String getTransactionID() {
		
		return this.transaction_id;
		
	}

	public String getCurrentDate() {
		
		return this.current_date;
		
	}

	public String getRewardName() {
		
		return this.reward_name;
		
	}

	public String getRewardQty() {
		
		return this.reward_qty;
		
	}

	public String getOperationType() {
		
		return this.operation_type;
		
	}

	public String getModule() {
		
		return this.module;
		
	}

	public String getOrigin() {
		
		return this.origin;
		
	}

	public String getOriginator() {
		
		return this.originator;
		
	}

	public String getInitialRewardQty() {
		
		return this.initial_reward_qty;
		
	}
	
	public String getRewardLimitationCause() {
		
		return this.reward_limitation_cause;
		
	}
	
	public boolean getValidation() {
		
		return this._validation;
		
	}
	
	public void set( String methodName, String value ) {
		
		Method method; 
		
		try {
			
			method = this.getClass().getDeclaredMethod( methodName, String.class );
			method.invoke(this, value );
		
		} catch( NoSuchMethodException e ) {
			logger.error( e.getMessage(), e );
		} catch( IllegalAccessException e ) {
			logger.error( e.getMessage(), e );
		} catch( InvocationTargetException e ) {
			logger.error( e.getMessage(), e );
		}	
		
	}
	
	public void setFormatID( String format_id ) {
		
		this.format_id = format_id;
		
	}
	
	public void setMSISDN( String msisdn ) {
		
		this.msisdn = msisdn;
		
	}

	public void setTransactionID( String transaction_id ) {
		
		this.transaction_id = transaction_id;
		
	}

	public void setCurrentDate( String current_date ) {
		
		this.current_date = current_date;
		
	}

	public void setRewardName( String reward_name ) {
		
		this.reward_name = reward_name;
		
	}

	public void setRewardQty( String reward_qty ) {
		
		this.reward_qty = reward_qty;
		
	}

	public void setOperationType( String operation_type ) {
		
		this.operation_type = operation_type;
		
	}

	public void setModule( String module ) {
		
		this.module = module;
		
	}

	public void setOrigin( String origin ) {
		
		this.origin = origin;
		
	}

	public void setOriginator( String originator ) {
		
		this.originator = originator;
		
	}

	public void setInitialRewardQty( String initial_reward_qty ) {
		
		this.initial_reward_qty = initial_reward_qty;
		
	}
	
	public void setRewardLimitationCause( String reward_limitation_cause ) {
		
		this.reward_limitation_cause = reward_limitation_cause;
		
	}
	
	public void setValidation( boolean validation ) {
		
		this._validation = validation;
		
	}

}
