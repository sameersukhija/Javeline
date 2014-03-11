package com.lumata.expression.operators.system.cdr;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.network.SFTPClient;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.system.Environment.ServicesType;
import com.lumata.expression.operators.exceptions.CDRException;
import com.lumata.expression.operators.system.cdr.annotations.Date;
import com.lumata.expression.operators.system.cdr.annotations.Msisdn;

public class CDR {
	
	private static final Logger logger = LoggerFactory.getLogger( CDR.class );
	
	private enum GeneratingType { FIXED, SEQUENTIAL, RANDOM }
	
	private Map<Class<?>,CDR.GeneratingType> fieldGeneratingType = new HashMap<Class<?>,CDR.GeneratingType>();
	private StringBuilder file_content;
	private ArrayList<String> rows;
	//private CDR.Types cdrType;
	private String path;
	private String file_name;
	
	private Integer msisdn_prefix;
	private Long msisdn_current_value;
	private Long msisdn_left_value;
	private Long msisdn_right_value;
	private Long msisdn_next_value;
	private Integer msisdn_increment;
	private Integer msisdn_length;
	private CDRDate date;
	
	/*
	private Calendar date;
	private String date_format;
	private Calendar date_left;
	private Calendar date_right;
	private Calendar date_next;
	private int date_increment;
	private int date_increment_type;
	*/
	
	
	
	private String validity_date;
	private Calendar min_validity_date;
	private Calendar max_validity_date;
	private String deactivation_date;
	private Calendar min_deactivation_date;
	private Calendar max_deactivation_date;
	private Integer duration;
	private Integer amount;
	private Integer min_amount;
	private Integer max_amount;
	private Integer balance;
	private Integer min_balance;
	private Integer max_balance;
	private Integer delay;
	private Integer min_delay;
	private Integer max_delay;
	private String type;
	private Boolean terminating;
	private Integer tenant_id;	
	
	public CDR() {
		
		this.initMsisdn();
		
		this.file_content = new StringBuilder();
		this.rows = new ArrayList<String>();
	}
	
	private void initMsisdn() {
		
		msisdn_prefix = null;
		msisdn_current_value = null;
		msisdn_left_value = null;
		msisdn_right_value = null;
		msisdn_next_value = null;
		msisdn_increment = null;
		msisdn_length = null;
		
	}
	
	@Msisdn
	protected String getMsisdn() {
		
		if( this.msisdn_current_value == null && ( this.msisdn_left_value == null || this.msisdn_right_value == null )) { return ""; } 
		else {
			
			if( this.msisdn_next_value != null && this.msisdn_increment != null ) {
				
				this.msisdn_current_value = this.msisdn_next_value;
				
				this.msisdn_next_value = Long.valueOf( this.msisdn_next_value + this.msisdn_increment );
				
			} else {
				
				if( this.msisdn_increment != null ) {
					
					this.msisdn_next_value = Long.valueOf( this.msisdn_current_value + this.msisdn_increment );
				
				} else {
					
					if( this.msisdn_left_value != null && this.msisdn_right_value != null ) {
						
						this.msisdn_current_value = this.generateRandomLong( this.msisdn_left_value, this.msisdn_right_value );
						
					}
					
				}
				
			}			
			
		}
		
		return String.valueOf( this.generateMSISDN() ); 
		
	}
	
	@Msisdn
	protected void setMsisdnOptions( final Integer prefix, final Integer length ) throws CDRException {
		
		if( length != null && length > 19 ) { throw new CDRException( "The msisdn length must be less than 20." ); }
		
		this.msisdn_prefix = ( prefix != null ? Math.abs( prefix ) : null );
				
		this.msisdn_length = ( length != null ? Math.abs( length ) : null );
		
	}

	@Msisdn
	protected void setMsisdnStrategyFixed( final Long value ) throws CDRException {	
		
		this.msisdn_current_value = ( value != null ? Math.abs( value ) : null );
		
		this.cleanMsisdnStrategyIncrement();
		
		this.cleanMsisdnStrategyRandom();
		
	}
	
	@Msisdn
	protected void setMsisdnStrategyIncrement( final Long value, final Integer increment ) throws CDRException {
		
		if( value == null ) { throw new CDRException( "The msisdn cannot be null." ); }
		
		if( increment == null ) { throw new CDRException( "The msisdn increment cannot be null." ); }
		
		this.msisdn_current_value = Math.abs( value );
		
		this.msisdn_increment = Math.abs( increment );
		
		this.cleanMsisdnStrategyRandom();
		
	}
	
	@Msisdn
	protected void setMsisdnStrategyRandom( final Long min_value, final Long max_value ) throws CDRException {
		
		if( min_value == null ) { throw new CDRException( "The min msisdn value cannot be null." ); }
		
		if( max_value == null ) { throw new CDRException( "The max msisdn value cannot be null." ); }
		
		this.msisdn_left_value = Math.abs( min_value );
		
		this.msisdn_right_value = Math.abs( max_value );
		
		this.cleanMsisdnStrategyIncrement();
		
	}
	
	@Msisdn
	protected void cleanMsisdn() {
		
		this.msisdn_current_value = null;
			
	}
	
	@Msisdn
	protected void cleanMsisdnOptions() {
				
		this.msisdn_prefix = null;
		
		this.msisdn_current_value = null;
		
		this.msisdn_length = null;
				
	}
	
	@Msisdn
	protected void cleanMsisdnStrategyIncrement() {
		
		this.msisdn_increment = null;
		
		this.msisdn_next_value = null;
		
	}
	
	@Msisdn
	protected void cleanMsisdnStrategyRandom() {
		
		this.msisdn_left_value = null;
		
		this.msisdn_right_value = null;
		
	}
	
	private Long generateMSISDN() {
		
		Long msisdn = this.msisdn_current_value;
				
		if( this.msisdn_prefix != null ) {
			
			final int SUBSCRIBERS_PREFIX_DIGITS = ( this.msisdn_prefix > 0 ? (int)( Math.log10( this.msisdn_prefix ) + 1 ) : 0 );
			
			final int SUBSCRIBERS_TO_GENERATE_DIGITS = ( this.msisdn_current_value > 0 ? (int)( Math.log10( this.msisdn_current_value ) + 1 ) : 0 );
			
			final int MSISDN_LENGTH = SUBSCRIBERS_PREFIX_DIGITS + SUBSCRIBERS_TO_GENERATE_DIGITS;
			
			if( this.msisdn_length == null || this.msisdn_length < MSISDN_LENGTH ) { this.msisdn_length = MSISDN_LENGTH; }
			
			StringBuilder msisdn_str = new StringBuilder();
			
			msisdn_str.append( this.msisdn_prefix ).append( String.format( "%0" + ( this.msisdn_length - MSISDN_LENGTH + SUBSCRIBERS_TO_GENERATE_DIGITS ) + "d" ,  this.msisdn_current_value ) );
						
			try {
				
				msisdn = Long.valueOf( msisdn_str.toString().trim() );
						
			} catch( NumberFormatException ne ) {
				
				logger.error( ne.getMessage(), ne );
				
			}
			
		} 
		
		return msisdn;
		
	}
	
	@Date
	protected String getDate() {
		
		return ( this.date != null ? this.date.getDate() : "" ); 
		
	}
	
	@Date
	protected void setDateStrategyFixed( final Calendar date ) throws CDRException {
		
		if( this.date != null ) { this.date.setDateStrategyFixed( date ); }
		
	}

	@Date
	protected void setDateFormat( String format ) throws CDRException {
		
		if( this.date != null ) { this.date.setDateFormat( format ); }
						
	}	
	
	@Date
	protected void setDateStrategyIncrement( final Calendar date, final CDRDateIncrement increment ) throws CDRException {
		
		if( this.date != null ) { this.date.setDateStrategyIncrement( date, increment ); }
		
	}
	
	@Date
	protected void setDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws CDRException {
		
		if( this.date != null ) { this.date.setDateStrategyRandom( date_left, date_right ); }
		
	}
	
	@Date
	protected void cleanDateStrategyFixed() {
		
		if( this.date != null ) { this.date.cleanDateStrategyFixed(); }
					
	}
	
	@Date
	protected void cleanDateStrategyIncrement() {
		
		if( this.date != null ) { this.date.cleanDateStrategyIncrement(); }
					
	}
	
	@Date
	protected void cleanDateStrategyRandom() {
		
		if( this.date != null ) { this.date.cleanDateStrategyRandom(); }
					
	}

	
	
	
	
	
	
	
	
	
	
	private int generateRandomInt( final int min_value, final int max_value ) {
		
		return min_value + (int)( Math.random() * ( max_value - min_value ) );
				
	}

	private Long generateRandomLong( final Long min_value, final Long max_value ) {
		
		return min_value + Long.valueOf( (int)( Math.random() * ( max_value - min_value ) ) );
				
	}	
	
	public void add() {
		
		String[] field_values = null; 	
		
		try {
			
			int fields_count = (int)this.getClass().getDeclaredMethod( "getFiledsCount" ).invoke( this );
						
			field_values = new String[ fields_count ];
						
		} catch (	IllegalAccessException | 
					IllegalArgumentException | 
					InvocationTargetException | 
					SecurityException | 
					NoSuchMethodException e 
				) { logger.error( e.getMessage(), e );
		}
		
		if( field_values != null ) { 
			
			for( Method method : this.getClass().getDeclaredMethods() ) {
				
				try {
					
					if( method.isAnnotationPresent( Msisdn.class ) ) { 
															
						field_values[ method.getAnnotation( Msisdn.class ).position() ] = (String)this.getClass().getMethod( "getMsisdn" ).invoke( this );	
						
					}
					
					if( method.isAnnotationPresent( Date.class ) ) { 
						
						field_values[ method.getAnnotation( Date.class ).position() ] = (String)this.getClass().getMethod( "getDate" ).invoke( this );	
						
					}
				
				} catch ( 	NoSuchMethodException | 
							SecurityException | 
							IllegalAccessException | 
							IllegalArgumentException | 
							InvocationTargetException e
						) { logger.error( e.getMessage(), e ); } 					
				
			}
		
		}
		
		StringBuilder row = new StringBuilder();
		
		for( int v = 0; v < field_values.length; v++ ) {
				
			row.append( field_values[ v ] ).append( "|" );
			
		}
		
		if( row.length() > 0 ) { 
			
			row.setLength( row.length() - 1 ); 
						
			row.append( "\n" );
			
			this.file_content.append( row );
			
			this.rows.add( row.toString() );
		
		}
		
	}
		
	public void add( int lines ) {
		
		for( int l = 0; l < lines; l++ ) { this.add(); }
		
	}
	
	public void clean() {
		
		this.file_content = new StringBuilder();
		
		this.rows = new ArrayList<String>();
		
	}
	
	public void print() {
		
		System.out.println( this.file_content.toString() );
		
	}
	
	@Override
	public String toString() {
		
		return this.file_content.toString();
		
	}
	
	
	
	
	
	/*
	
	public CDR.GeneratingType getGeneratingType( CDR.Fields field ) {
		return this.fieldGeneratingType.get( field );
	}
	
	
	public void setGeneratingType( CDR.Fields field, CDR.GeneratingType type ) {
		this.fieldGeneratingType.put( field , type );
	}
	*/
	
	
	
	
/*	
	
	public CDR( CDR.Types type ) {
		
		this.cdrType = type;
		
		this.file_content = new StringBuilder();
				
		this.rows = new ArrayList<String>();
		
		this.file_name = "";
		
		for( CDR.Fields field : CDR.Fields.values()) {
			fieldGeneratingType.put( field, CDR.GeneratingType.FIXED );
		}
		
	}
	
	public String getMsisdn() {
		
		switch( this.fieldGeneratingType.get( CDR.Fields.Msisdn ) ) {
		
			case FIXED: { 
				return ( this.msisdn_current_value != null ? String.valueOf( this.msisdn_current_value ) : "" ); 
			}
			case SEQUENTIAL: { 
				
				this.msisdn_current_value = this.msisdn_next_value;
				
				if( this.msisdn_next_value != null && this.msisdnIncrement != null ) { 
					
					this.msisdn_next_value = Long.valueOf( this.msisdn_next_value + this.msisdnIncrement );
				
				}
				
				return ( this.msisdn_current_value != null ? String.valueOf( this.msisdn_current_value ) : "" );
				
			}
			case RANDOM: { 
				
				
				
				return ( this.msisdn_current_value != null ? String.valueOf( this.msisdn_current_value ) : "" ); 
				
			}
			
		}
		
		return "";
		
	}
	
	public String getDate() {
		
		return ( this.date != null ? this.date : "" );
		
	}
	
	public String getValidityDate() {
		
		return ( this.validity_date != null ? this.validity_date : "" );
		
	}
	
	public Calendar getMinValidityDate() {
		
		return ( this.min_validity_date != null ? this.min_validity_date : Calendar.getInstance() );
		
	}
	
	public Calendar getMaxValidityDate() {
		
		return ( this.max_validity_date != null ? this.max_validity_date : Calendar.getInstance() );
		
	}
	
	public String getDeactivationDate() {
		
		return ( this.deactivation_date != null ? this.deactivation_date : "" );
		
	}
	
	public Calendar getMinDeactivationDate() {
		
		return ( this.min_deactivation_date != null ? this.min_deactivation_date : Calendar.getInstance() );
		
	}
	
	public Calendar getMaxDeactivationDate() {
		
		return ( this.max_deactivation_date != null ? this.max_deactivation_date : Calendar.getInstance() );
		
	}

	public String getDuration() {
		
		return ( this.duration != null ? String.valueOf( this.duration ) : "" );
		
	}

	public String getAmount() {
		
		return ( this.amount != null ? String.valueOf( this.amount ) : "" );
		
	}
	
	public String getMinAmount() {
		
		return ( this.min_amount != null ? String.valueOf( this.min_amount ) : "" );
		
	}
	
	public String getMaxAmount() {
		
		return ( this.max_amount != null ? String.valueOf( this.max_amount ) : "" );
		
	}
	
	public String getBalance() {
		
		return ( this.balance != null ? String.valueOf( this.balance ) : "" );
		
	}
	
	public String getMinBalance() {
		
		return ( this.min_balance != null ? String.valueOf( this.min_balance ) : "" );
		
	}
	
	public String getMaxBalance() {
		
		return ( this.max_balance != null ? String.valueOf( this.max_balance ) : "" );
		
	}
	
	public String getDelay() {
		
		return ( this.delay != null ? String.valueOf( this.delay ) : "" );
		
	}
	
	public String getMinDelay() {
		
		return ( this.min_delay != null ? String.valueOf( this.min_delay ) : "" );
		
	}
	
	public String getMaxDelay() {
		
		return ( this.max_delay != null ? String.valueOf( this.max_delay ) : "" );
		
	}
	
	public String getTerminating() {
		
		return ( this.terminating != null ? String.valueOf( this.terminating ) : "" );
		
	}
	
	public String getType() {
		
		return ( this.type != null ? this.type : "" );
		
	}
	
	public String getTenantId() {
		
		return ( this.tenant_id != null ? String.valueOf( this.tenant_id ) : "" );
		
	}
	
	public String getPath() {
		
		return this.path;
		
	}
	
	public String getFileName() {
		
		return ( this.file_name != null ? this.file_name : "" );
		
	}
	
	//******************************
	
	public void setMsisdn( final Long value ) {
		
		this.resetMsisdn();
		
		this.msisdn_current_value = Math.abs( value );
		
		this.setGeneratingType( CDR.Fields.Msisdn, CDR.GeneratingType.FIXED );
		
	}
	
	public void setMsisdn( final Integer prefix, final Integer value, final Integer length ) {
		
		this.resetMsisdn();
		
		this.msisdn_current_value = this.generateMSISDN( Math.abs( prefix ), Math.abs( value ), Math.abs( length ) );
		
		this.setGeneratingType( CDR.Fields.Msisdn, CDR.GeneratingType.FIXED );
		
	}
	
	public void setMsisdnSequence( final Long value, final Integer increment ) {
		
		this.resetMsisdn();
		
		this.msisdn_current_value = value;
		
		this.msisdn_next_value = this.msisdn_current_value;
		
		this.msisdnIncrement = increment;
		
		this.setGeneratingType( CDR.Fields.Msisdn, CDR.GeneratingType.SEQUENTIAL );
		
	}
	
	public void setMsisdnSequence( final Integer prefix, final Integer value, final Integer length, final Integer increment ) {
		
		this.resetMsisdn();
		
		this.msisdnIncrement = increment;
				
		this.msisdn_current_value = this.generateMSISDN( prefix, value, length );
		
		this.msisdn_next_value = this.msisdn_current_value;
		
		this.setGeneratingType( CDR.Fields.Msisdn, CDR.GeneratingType.SEQUENTIAL );
			
	}
	
	public void setMsisdnRandom( final Long min_value, final Long max_value ) {
		
		this.resetMsisdn();
			
		this.msisdn_current_value = this.generateRandomLong( min_value, max_value );
		
		this.setGeneratingType( CDR.Fields.Msisdn, CDR.GeneratingType.RANDOM );
		
	}
	
	public void setMsisdnRandom( final Integer prefix, final Long min_value, final Integer max_value, final Integer length ) {
		
		this.resetMsisdn();
		
		this.msisdn_prefix = prefix;
		
		this.msisdn_left_value = min_value;
		
		this.setGeneratingType( CDR.Fields.Msisdn, CDR.GeneratingType.RANDOM );
		
		
		
		//this.generateMSISDN( prefix, this.generateRandomInt( min_value, max_value ), length );
			
	}
		
	public void resetMsisdn() {
		this.msisdn_prefix = null;
		this.msisdn_current_value = null;
		this.msisdn_left_value = null;
		this.msisdn_right_value = null;
		this.msisdn_next_value = null;
		this.msisdnIncrement = null;
		this.msisdn_length = null;
	}
	
	
	
	
	public CDR.GeneratingType getGeneratingType( CDR.Fields field ) {
		return this.fieldGeneratingType.get( field );
	}
	
	
	public void setGeneratingType( CDR.Fields field, CDR.GeneratingType type ) {
		this.fieldGeneratingType.put( field , type );
	}
	
	
	
	
	/*
	public void setMsisdn( final String msisdn ) {
		
		this.msisdn = msisdn;
		
	}
	
	public void setMsisdn( final String prefix, final int number, int length ) {
		
		this.setMsisdn( this.generateMSISDN( prefix, number, length ) );
		
	}
	
	public void setSequentialMsisdn( final String msisdn, final int increment ) {
		
		this.msisdn = msisdn;
		
	}
	
	public void setSequentialMsisdn( final String prefix, final int number, int length ) {
		
		this.setMsisdn( this.generateMSISDN( prefix, number, length ) );
		
	}

	
	*/
	
	
	// ***************************
/*	
	public void setDate( final String date ) {
		
		this.date = date;
		
	}
	
	public void setDate( final Calendar date, String format ) {
		
		this.setDate( new SimpleDateFormat( format ).format( date.getTime() ) );
						
	}	

	public void setDate( final Calendar date, String format, final int year, final int month, final int day ) {
		
		date.set( year, month, day );
		
		this.setDate( date, format );
		
	}
	
	public void setRandomDate( final Calendar date, String format, final int left_year, final int rigth_year, final int left_month, final int rigth_month, final int left_day, final int rigth_day ) {
		
		date.set( ( left_year + (int)( Math.random() * rigth_year ) ), ( left_month + (int)( Math.random() * rigth_month ) ), ( left_day + (int)( Math.random() * rigth_day ) ) );
		
		this.setDate( date, format );
		
	}
	
	public void setValidityDate( final String validity_date ) {
		
		this.validity_date = validity_date;
		
	}
	
	public void setValidityDate( final Calendar validity_date, String format ) {
		
		this.setValidityDate( new SimpleDateFormat( format ).format( validity_date.getTime() ) );
		
	}	

	public void setValidityDate( final Calendar validity_date, String format, final int year, final int month, final int day ) {
		
		validity_date.set( year, month, day );
		
		this.setValidityDate( validity_date, format );
		
	}
	
	public void setRandomValidityDate( String format ) {
		
		this.setValidityDate( this.getRandomDate( this.getMinValidityDate(), this.getMaxValidityDate(), format ), format );
		
	}

	public void setMinValidityDate( int year, int month, int day ) {
		
		this.min_validity_date = Calendar.getInstance();
		
		this.min_validity_date.set( year, month, day );
		
	}
	
	public void setMaxValidityDate( int year, int month, int day ) {
		
		this.max_validity_date = Calendar.getInstance();
		
		this.max_validity_date.set( year, month, day );
		
	}
	
	public void setDeactivationDate( final String deactivation_date ) {
		
		this.deactivation_date = deactivation_date;
		
	}
	
	public void setDeactivationDate( final Calendar deactivation_date, String format ) {
		
		this.setDeactivationDate( new SimpleDateFormat( format ).format( deactivation_date.getTime() ) );
		
	}	

	public void setDeactivationDate( final Calendar deactivation_date, String format, final int year, final int month, final int day ) {
		
		deactivation_date.set( year, month, day );
		
		this.setDeactivationDate( deactivation_date, format );
		
	}
	
	public void setRandomDeactivationDate( String format ) {
		
		this.setDeactivationDate( this.getRandomDate( this.getMinDeactivationDate(), this.getMaxDeactivationDate(), format ), format );
		
	}

	public void setMinDeactivationDate( int year, int month, int day ) {
		
		this.min_deactivation_date = Calendar.getInstance();
		
		this.min_deactivation_date.set( year, month, day );
		
	}
	
	public void setMaxDeactivationDate( int year, int month, int day ) {
		
		this.max_deactivation_date = Calendar.getInstance();
		
		this.max_deactivation_date.set( year, month, day );
		
	}
	
	public void setDuration( final int duration ) {
		
		this.duration = duration;
		
	}

	public void setRandomDuration( final int min_duration, final int max_duration ) {
		
		this.setDuration( this.generateRandomInt( min_duration, max_duration ) );
		
	}
	
	public void setAmount( final int amount ) {
		
		this.amount = amount;
		
	}
	
	public void setMinAmount( int min_amount ) {
		
		this.min_amount = min_amount;
		
	}
	
	public void setMaxAmount( int max_amount ) {
		
		this.max_amount = max_amount;
		
	}
	
	public void setRandomAmount() {
		
		this.setAmount( this.generateRandomInt( min_amount, max_amount ) );
		
	}
	
	public void setRandomAmount( final int min_amount, final int max_amount ) {
		
		this.setAmount( this.generateRandomInt( min_amount, max_amount ) );
		
	}
	
	public void setBalance( final int balance ) {
		
		this.balance = balance;
		
	}
	
	public void setMinBalance( int min_balance ) {
		
		this.min_balance = min_balance;
		
	}
	
	public void setMaxBalance( int max_balance ) {
		
		this.max_balance = max_balance;
		
	}
	
	public void setRandomBalance() {
		
		this.setBalance( this.generateRandomInt( min_balance, max_balance ) );
		
	}
	
	public void setRandomBalance( final int min_balance, final int max_balance ) {
		
		this.setBalance( this.generateRandomInt( min_balance, max_balance ) );
		
	}
	
	public void setDelay( int delay ) {
		
		this.delay = delay;
		
	}
	
	public void setMinDelay( int min_delay ) {
		
		this.min_delay = min_delay;
		
	}
	
	public void setMaxDelay( int max_delay ) {
		
		this.max_delay = max_delay;
		
	}
	
	public void setRandomDelay() {
		
		this.setDelay( this.generateRandomInt( min_delay, max_delay ) );
		
	}
	
	public void setRandomDelay( final int min_delay, final int max_delay ) {
		
		this.setDelay( this.generateRandomInt( min_delay, max_delay ) );
		
	}
	
	public void setTerminating( final boolean terminating ) {
		
		this.terminating = terminating ;
		
	}
	
	public void setType( String type ) {
		
		this.type = type;
		
	}

	public void setRandomType() {
		
		this.type = FIELD_TYPE_VALUES.get( (int)( Math.random() * FIELD_TYPE_VALUES.size() ) );
		
	}
	
	public void setTenantId( final int tenant_id ) {
		
		this.tenant_id = tenant_id;
		
	}
	
	public void setPath( String path ) {
		
		this.path = path;
		
	}
	
	public void setFileName( String file_name ) {
		
		this.file_name = file_name;
		
	}
	
	public void save() throws IOFileException {
		
		IOFileUtils.saveResource( this.file_content.toString(), this.path, this.file_name );
		
	}
	
	public void send( Environment remote_host, String remote_path ) {
		
		try {
			
			JSONObject ssh = remote_host.getServiceType( ServicesType.SSH );
			
			SFTPClient sftp = new SFTPClient( ssh.getString( "host" ), ssh.getInt( "port" ), ssh.getString( "user" ), ssh.getString( "password" ) );
			
			if( sftp.isConnected() ) {
				
				String local_path = System.getProperty( "user.dir" ) + "/src/main/resources/" + this.getPath();
				
	            sftp.copyFile( local_path, this.getFileName(), remote_path , this.getFileName(), SFTPClient.CopyType.LOCAL_TO_REMOTE );
	        				
			}
						
		} catch( JSONException e ) {}
		
	}
	
	private Long generateMSISDN( Integer prefix, Integer value, Integer length ) {
		
		Long msisdn = null;
		
		this.msisdn_prefix = prefix;
		this.msisdn_length = length;
		
		final int SUBSCRIBERS_PREFIX_DIGITS = ( this.msisdn_prefix > 0 ? (int)( Math.log10( this.msisdn_prefix ) + 1 ) : 0 );
		final int SUBSCRIBERS_TO_GENERATE_DIGITS = ( value > 0 ? (int)( Math.log10( value ) + 1 ) : 0 );
		
		final int MSISDN_LENGTH = SUBSCRIBERS_PREFIX_DIGITS + SUBSCRIBERS_TO_GENERATE_DIGITS;
		
		if( this.msisdn_length < MSISDN_LENGTH ) { this.msisdn_length = MSISDN_LENGTH; }
		
		StringBuilder msisdn_str = new StringBuilder();
		
		msisdn_str.append( this.msisdn_prefix ).append( String.format( "%0" + ( this.msisdn_length - MSISDN_LENGTH + SUBSCRIBERS_TO_GENERATE_DIGITS ) + "d" ,  value ) );
				
		try {
			
			msisdn = Long.valueOf( msisdn_str.toString().trim() );
					
		} catch( NumberFormatException ne ) {
			
			logger.error( ne.getMessage(), ne );
			
		}
		
		return msisdn;
		
	}
	
	private int generateRandomInt( final int min_value, final int max_value ) {
		
		return min_value + (int)( Math.random() * ( max_value - min_value ) );
				
	}

	private Long generateRandomLong( final Long min_value, final Long max_value ) {
		
		return min_value + Long.valueOf( (int)( Math.random() * ( max_value - min_value ) ) );
				
	}
	
	public Calendar getRandomDate( final Calendar min_date, final Calendar max_date, final String format ) {
		
		Calendar random_date = Calendar.getInstance();
		
		int days = (int)(( max_date.getTime().getTime() - min_date.getTime().getTime() ) / (1000 * 60 * 60 * 24) );
						
		random_date.set( min_date.get( Calendar.YEAR ), min_date.get( Calendar.MONTH ), min_date.get( Calendar.DATE ) );
		
		random_date.add( Calendar.DATE, ( (int)(Math.random() * days) ) );
				
		return random_date;
		
	}

	public void add() {
		
		List<CDR.Fields> cdrFields = this.cdrType.fields();
		
		StringBuilder row = new StringBuilder();
		
		for( int i = 0; i < cdrFields.size(); i++ ) {
			
			try {
			
				Method method = this.getClass().getDeclaredMethod( "get" + cdrFields.get( i ).name() );
			
				Object value = method.invoke( this );
				
				row.append( value ).append( "|" );
								
			} catch( NoSuchMethodException | InvocationTargetException | IllegalAccessException e ) {
				
				logger.error( e.getMessage(), e );
				
			}
			
		}
		
		row.setLength( row.length() - 1 );
		
		row.append( "\n" );
		
		this.file_content.append( row );
		
		this.rows.add( row.toString() );
		
	}
	
	public void clean() {
		
		this.file_content = new StringBuilder();
		
		this.rows = new ArrayList<String>();
		
	}
	
	public void print() {
		
		System.out.println( this.file_content.toString() );
		
	}
*/	
	
}
