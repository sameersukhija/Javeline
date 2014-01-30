package com.lumata.expression.operators.generators;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.network.SFTPClient;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.system.Environment.ServicesType;

public class CDR {
	
	private static final Logger logger = LoggerFactory.getLogger( CDR.class );
	
	public enum Types {
		HISTORY {
			public List<CDR.Fields> fields() {
				return Arrays.asList( 
						CDR.Fields.Msisdn						
				);
			}
		}, 
		REVENUE {
			public List<CDR.Fields> fields() {
				return Arrays.asList( 
						CDR.Fields.Msisdn,
						CDR.Fields.Date,
						CDR.Fields.Amount,
						CDR.Fields.Balance,
						CDR.Fields.ValidityDate,
						CDR.Fields.DeactivationDate,
						CDR.Fields.Type,
						CDR.Fields.Delay
				);
			}
		},
		BUNDLE {
			public List<CDR.Fields> fields() {
				return Arrays.asList( 
						CDR.Fields.Msisdn, 
						CDR.Fields.Date, 
						CDR.Fields.BundleName, 
						CDR.Fields.BundleBalance, 
						CDR.Fields.BundlePurchased
				);
			}
		}, 
		CALL {
			public List<CDR.Fields> fields() {
				return Arrays.asList( 
						CDR.Fields.Msisdn, 
						CDR.Fields.Date, 
						CDR.Fields.Duration, 
						CDR.Fields.Amount,
						CDR.Fields.Balance,
						CDR.Fields.Terminating
				);
			}
		}, 
		DATA {
			public List<CDR.Fields> fields() {
				return Arrays.asList( 
						CDR.Fields.Msisdn, 
						CDR.Fields.Date, 
						CDR.Fields.Amount,
						CDR.Fields.Download,
						CDR.Fields.Upload,
						CDR.Fields.Balance
				);
			}
		}, 
		LIFECYCLE {
			public List<CDR.Fields> fields() {
				return Arrays.asList( 
						CDR.Fields.Msisdn, 
						CDR.Fields.Date, 
						CDR.Fields.NewRatePlan,
						CDR.Fields.OldRatePlan,
						CDR.Fields.NewProfile,
						CDR.Fields.OldProfile,
						CDR.Fields.NewSubProfile,
						CDR.Fields.OldSubProfile,
						CDR.Fields.NewStatus,
						CDR.Fields.OldStatus,
						CDR.Fields.NewNetwork,
						CDR.Fields.OldNetwork,
						CDR.Fields.NewSubscriptionDate,
						CDR.Fields.OldSubscriptionDate
				);
			}
		}, 
		MESSAGE {
			public List<CDR.Fields> fields() {
				return Arrays.asList( 
						CDR.Fields.Msisdn, 
						CDR.Fields.Date, 
						CDR.Fields.Amount,
						CDR.Fields.Balance,
						CDR.Fields.Sms
				);
			}
		}, 
		OTHERUSAGE {
			public List<CDR.Fields> fields() {
				return Arrays.asList( 
						CDR.Fields.Msisdn, 
						CDR.Fields.Date, 
						CDR.Fields.Amount,
						CDR.Fields.Balance
				);
			}
		}, 
		VOUCHER {
			public List<CDR.Fields> fields() {
				return Arrays.asList( 
						CDR.Fields.Msisdn, 
						CDR.Fields.Code, 
						CDR.Fields.Date,
						CDR.Fields.PointOfRedemption
				);
			}
		};
		
		public abstract List<CDR.Fields> fields();
		
	}
	
	final ArrayList<String> FIELD_TYPE_VALUES = new ArrayList<String>( Arrays.asList( "reload", "paiement", "invoice" ));
		
	public enum Fields { 
		Msisdn, 
		Date,
		ValidityDate,
		DeactivationDate,
		Duration, 
		Amount, 
		Balance, 
		Terminating, 
		Type,
		Delay,
		BundleName, 
		BundleBalance, 
		BundlePurchased, 
		Download, 
		Upload,
		NewRatePlan,
		OldRatePlan,
		NewProfile,
		OldProfile,
		NewSubProfile,
		OldSubProfile,
		NewStatus,
		OldStatus,
		NewNetwork,
		OldNetwork,
		NewSubscriptionDate,
		OldSubscriptionDate,
		Sms,
		Code,
		PointOfRedemption
	}
	
	private StringBuilder file_content;
	private ArrayList<String> rows;
	private String path;
	private String file_name;
	
	private String msisdn;
	private String date;
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
	
	public CDR() {
		
		this.file_content = new StringBuilder();
				
		this.rows = new ArrayList<String>();
		
		this.file_name = "";
		
	}
	
	public String getMsisdn() {
		
		return ( this.msisdn != null ? this.msisdn : "" );
		
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
	
	public String getPath() {
		
		return this.path;
		
	}
	
	public String getFileName() {
		
		return ( this.file_name != null ? this.file_name : "" );
		
	}
	
	public void setMsisdn( final String msisdn ) {
		
		this.msisdn = msisdn;
		
	}
	
	public void setMsisdn( final String prefix, final int number, int length ) {
		
		this.setMsisdn( this.generateMSISDN( prefix, number, length ) );
		
	}
	
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
	
	public String generateMSISDN( final String prefix, final int number, int length ) {
		
		final int SUBSCRIBERS_PREFIX_DIGITS = (int)( Math.log10( Integer.valueOf( prefix ) ) + 1 );
		final int SUBSCRIBERS_TO_GENERATE_DIGITS = (int)( Math.log10( number ) + 1 );
		
		final int MSISDN_LENGTH = SUBSCRIBERS_PREFIX_DIGITS + SUBSCRIBERS_TO_GENERATE_DIGITS;
		
		if( length < MSISDN_LENGTH ) { length = MSISDN_LENGTH; }
		
		String msisdn = prefix + String.format( "%0" + ( length - SUBSCRIBERS_PREFIX_DIGITS - (int)( Math.log10( number ) ) ) + "d" , number );
		
		return msisdn;
		
	}
	
	public void add( CDR.Types cdrType ) {
		
		List<CDR.Fields> cdrFields = cdrType.fields();
		
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
	
	private int generateRandomInt( final int min_value, final int max_value ) {
		
		return min_value + (int)( Math.random() * ( max_value - min_value ) );
				
	}
	
	public Calendar getRandomDate( final Calendar min_date, final Calendar max_date, final String format ) {
		
		Calendar random_date = Calendar.getInstance();
		
		int days = (int)(( max_date.getTime().getTime() - min_date.getTime().getTime() ) / (1000 * 60 * 60 * 24) );
						
		random_date.set( min_date.get( Calendar.YEAR ), min_date.get( Calendar.MONTH ), min_date.get( Calendar.DATE ) );
		
		random_date.add( Calendar.DATE, ( (int)(Math.random() * days) ) );
				
		return random_date;
		
	}
	
	public void print() {
		
		System.out.println( this.file_content.toString() );
		
	}
	
	
}
