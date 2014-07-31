package com.lumata.e4o.system.cdr;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.network.SFTPClient;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.system.Environment.ServicesType;
import com.lumata.common.testing.system.NetworkEnvironment;
import com.lumata.common.testing.system.Service;
import com.lumata.e4o.exceptions.CDRException;
import com.lumata.e4o.schema.tenant.Profiles;
import com.lumata.e4o.schema.tenant.Statuses;
import com.lumata.e4o.schema.tenant.SupportedRatePlan;
import com.lumata.e4o.schema.tenant.VoucherCodes;
/** cdr field types */
import com.lumata.e4o.system.cdr.fields.Amount;
import com.lumata.e4o.system.cdr.fields.AmountInvoice;
import com.lumata.e4o.system.cdr.fields.AmountPayment;
import com.lumata.e4o.system.cdr.fields.Balance;
import com.lumata.e4o.system.cdr.fields.BundleBalance;
import com.lumata.e4o.system.cdr.fields.BundleName;
import com.lumata.e4o.system.cdr.fields.BundlePurchased;
import com.lumata.e4o.system.cdr.fields.Date;
import com.lumata.e4o.system.cdr.fields.DeactivationDate;
import com.lumata.e4o.system.cdr.fields.Delay;
import com.lumata.e4o.system.cdr.fields.Delete;
import com.lumata.e4o.system.cdr.fields.Download;
import com.lumata.e4o.system.cdr.fields.Duration;
import com.lumata.e4o.system.cdr.fields.Location;
import com.lumata.e4o.system.cdr.fields.Msisdn;
import com.lumata.e4o.system.cdr.fields.NewGender;
import com.lumata.e4o.system.cdr.fields.NewHobbies;
import com.lumata.e4o.system.cdr.fields.NewImei;
import com.lumata.e4o.system.cdr.fields.NewImsi;
import com.lumata.e4o.system.cdr.fields.NewInTag;
import com.lumata.e4o.system.cdr.fields.NewNetwork;
import com.lumata.e4o.system.cdr.fields.NewProfile;
import com.lumata.e4o.system.cdr.fields.NewRatePlan;
import com.lumata.e4o.system.cdr.fields.NewSalary;
import com.lumata.e4o.system.cdr.fields.NewStatus;
import com.lumata.e4o.system.cdr.fields.NewSubProfile;
import com.lumata.e4o.system.cdr.fields.NewSubscriptionDate;
import com.lumata.e4o.system.cdr.fields.NewTongue;
import com.lumata.e4o.system.cdr.fields.OldGender;
import com.lumata.e4o.system.cdr.fields.OldHobbies;
import com.lumata.e4o.system.cdr.fields.OldImei;
import com.lumata.e4o.system.cdr.fields.OldImsi;
import com.lumata.e4o.system.cdr.fields.OldInTag;
import com.lumata.e4o.system.cdr.fields.OldNetwork;
import com.lumata.e4o.system.cdr.fields.OldProfile;
import com.lumata.e4o.system.cdr.fields.OldRatePlan;
import com.lumata.e4o.system.cdr.fields.OldSalary;
import com.lumata.e4o.system.cdr.fields.OldStatus;
import com.lumata.e4o.system.cdr.fields.OldSubProfile;
import com.lumata.e4o.system.cdr.fields.OldSubscriptionDate;
import com.lumata.e4o.system.cdr.fields.OldTongue;
import com.lumata.e4o.system.cdr.fields.Partner;
import com.lumata.e4o.system.cdr.fields.RawData;
import com.lumata.e4o.system.cdr.fields.RechargeAmount;
import com.lumata.e4o.system.cdr.fields.Sms;
import com.lumata.e4o.system.cdr.fields.TenantId;
import com.lumata.e4o.system.cdr.fields.Terminating;
import com.lumata.e4o.system.cdr.fields.Type;
import com.lumata.e4o.system.cdr.fields.Upload;
import com.lumata.e4o.system.cdr.fields.ValidityDate;
import com.lumata.e4o.system.cdr.fields.VoucherCode;
/** field types */
import com.lumata.e4o.system.environment.ExpressionKernelCommands;
/** schema classes */
import com.lumata.e4o.system.fields.FieldBoolean;
import com.lumata.e4o.system.fields.FieldDate;
import com.lumata.e4o.system.fields.FieldEnum;
import com.lumata.e4o.system.fields.FieldLong;
import com.lumata.e4o.system.fields.FieldMsisdn;
import com.lumata.e4o.system.fields.FieldSchemaTable;
import com.lumata.e4o.system.fields.FieldSet;
import com.lumata.e4o.system.fields.FieldString;
import com.lumata.e4o.system.fields.IFieldEnum;

/** CDR exception */
//import com.lumata.expression.operators.exceptions.CDRException;

public class CDR {
	
	private static final Logger logger = LoggerFactory.getLogger( CDR.class );
	
	private final String DEFAULT_SEPARETOR_ = "|";
	private String separator;
	
	private String output_dir;
	private String file_name;
	private StringBuilder file_content;
	private ArrayList<String> rows;
	private NetworkEnvironment env;
	private String tenant;
	
	@Msisdn 
	protected FieldMsisdn msisdn;	
	
	@Date 
	protected FieldDate date;
	
	@ValidityDate
	protected FieldDate validityDate; 
	
	@DeactivationDate
	protected FieldDate deactivationDate;
	
	@Duration
	protected FieldLong duration; 
	
	@Amount
	protected FieldLong amount; 	
	
	@AmountPayment
	protected FieldLong amountPayment; 

	@AmountInvoice
	protected FieldLong amountInvoice; 

	@Balance
	protected FieldLong balance; 
	
	@Terminating
	protected FieldEnum terminating; 
	
	@Delay
	protected FieldLong delay;
	
	@Type
	protected FieldEnum type;
	
	@VoucherCode
	protected FieldString voucherCode; 
	
	@Location
	//protected CSVSchemaTable location;
	protected FieldString location;
	
	@Partner
	//protected CSVSchemaTable location;
	protected FieldString partner;
		
	@BundleName
	protected FieldString bundleName; 
	
	@BundleBalance
	protected FieldLong bundleBalance;
	
	@BundlePurchased
	protected FieldBoolean bundlePurchased;
	
	@Download
	protected FieldLong download;
	
	@Upload
	protected FieldLong upload;
	
	@NewImei
	protected FieldLong newImei;
	
	@OldImei
	protected FieldLong oldImei;

	@NewImsi
	protected FieldLong newImsi;
	
	@OldImsi
	protected FieldLong oldImsi;
	
	@NewSubscriptionDate
	protected FieldDate newSubscriptionDate;
	
	@OldSubscriptionDate
	protected FieldDate oldSubscriptionDate;
	
	@NewProfile
	//protected CSVSchemaTable new_profile;
	protected FieldString newProfile;
	
	@OldProfile
	//protected CSVSchemaTable old_profile;
	protected FieldString oldProfile;
	
	@NewSubProfile
	protected FieldString newSubprofile;
	
	@OldSubProfile
	protected FieldString oldSubprofile;
	
	@NewRatePlan
	//protected CSVSchemaTable new_rate_plan;
	protected FieldString newRatePlan;
	
	@OldRatePlan
	//protected CSVSchemaTable old_rate_plan;
	protected FieldString oldRatePlan;
	@NewStatus
	//protected CSVSchemaTable new_status;
	//protected CSVString new_status;
	protected FieldEnum newStatus;
	
	@OldStatus
	//protected CSVSchemaTable old_status;
	//protected CSVString old_status;
	protected FieldEnum oldStatus;

	@NewNetwork
	protected FieldString newNetwork; 

	@OldNetwork
	protected FieldString oldNetwork; 

	@NewTongue
	protected FieldString newTongue; 

	@OldTongue
	protected FieldString oldTongue; 
	
	@NewInTag
	protected FieldString newInTag; 

	@OldInTag
	protected FieldString oldInTag; 
	
	@NewHobbies
	protected FieldSet newHobbies; 

	@OldHobbies
	protected FieldSet oldHobbies; 

	@NewGender
	protected FieldEnum newGender; 

	@OldGender
	protected FieldEnum oldGender; 

	@NewSalary
	protected FieldString newSalary; 

	@OldSalary
	protected FieldString oldSalary; 
	
	@Delete
	protected FieldEnum delete; 
		
	@Sms
	protected FieldEnum sms;
	
	@TenantId
	protected FieldLong tenant_id;
	
	@RawData
	protected FieldString rawData;
	
	@RechargeAmount
	protected FieldLong recharge_amount;
		
	public enum Parameters {
		env, 
		tenant,
		cfgDir,
		cfgFile,
		outputDir,
		store,
		send,
		depositPath
	}
	
	public enum TERMINATING implements IFieldEnum { 
		
		YES("YES"), 
		NO("NO"); 
		
		private String value;
		
		TERMINATING( String value ) { 
			this.value = value; 
		}
		
		public String value() {
			return this.value;
		}
		
	}
	
	public enum SMS implements IFieldEnum { 
		
		YES("0"), 
		NO("1"); 
		
		private String value;
		
		SMS( String value ) { 
			this.value = value; 
		}
		
		public String value() {
			return this.value;
		}
	
	}
	
	public enum DELETE implements IFieldEnum { 
		
		YES("YES"), 
		NO("NO"); 
		
		private String value;
		
		DELETE( String value ) { 
			this.value = value; 
		}
		
		public String value() {
			return this.value;
		}
		
	}
	
	@Type
	public enum TYPE implements IFieldEnum { 
		
		PAIEMENT("paiement"), 
		INVOICE("invoice"),
		RELOAD("reload"); 
		
		private String value;
		
		TYPE( String value ) { 
			this.value = value; 
		}
		
		public String value() {
			return this.value;
		}
		
	}

	@NewStatus 
	@OldStatus
	public enum SUBSTATUS implements IFieldEnum {
		
		ACTIVE("active"),
		INACTIVE("inactive"),
		SUSPENDED("suspended"),
		DEACTIVATED("deactivated"),
		TERMINATED("terminated");
		
		private String value;
		
		SUBSTATUS( String value ) { 
			this.value = value; 
		}
		
		public String value() {
			return this.value;
		}
		
	}
	
	@NewHobbies
	@OldHobbies
	public enum HOBBIES { 
		
		Football, Golfing, Cycling, Baseball, Volley, Dance, Juggling, BodyBuilding, Photography, Shopping, Swimming;
			
	};
	
	@NewGender
	@OldGender
	public enum GENDER implements IFieldEnum { 
		
		COMPANY("COMPANY"), 
		FEMALE("FEMALE"), 
		MALE("MALE"), 
		UNDEFINED("UNDEFINED");
		
		private String value;
		
		GENDER( String value ) { 
			this.value = value; 
		}
		
		public String value() {
			return this.value;
		}
		
	};
		
	public CDR() {
		
		this.separator = DEFAULT_SEPARETOR_;
		
		this.msisdn = new FieldMsisdn();		
		this.date = new FieldDate();
		this.validityDate = new FieldDate(); 
		this.deactivationDate = new FieldDate();
		this.duration = new FieldLong(); 
		this.amount = new FieldLong(); 
		this.balance = new FieldLong();
		this.terminating = new FieldEnum( TERMINATING.values() );
		this.delay = new FieldLong();
		this.type = new FieldEnum( TYPE.values() );
		this.voucherCode = new FieldString(); 
		this.partner = new FieldString();
		//this.location = new CSVSchemaTable( new VoucherCodes(), VoucherCodes.Fields.location_id );
		this.location = new FieldString(); 
		this.bundleName = new FieldString(); 
		this.bundleBalance = new FieldLong();
		this.bundlePurchased = new FieldBoolean();
		//this.new_rate_plan = new CSVSchemaTable( new SupportedRatePlan(), SupportedRatePlan.Fields.rate_plan );
		//this.old_rate_plan = new CSVSchemaTable( new SupportedRatePlan(), SupportedRatePlan.Fields.rate_plan );
		//this.new_profile = new CSVSchemaTable( new Profiles(), Profiles.Fields.profile );
		//this.old_profile = new CSVSchemaTable( new Profiles(), Profiles.Fields.profile );
		this.download = new FieldLong(); 
		this.upload = new FieldLong(); 
		
		this.newImei = new FieldLong();
		this.oldImei = new FieldLong();
		this.newImsi = new FieldLong();
		this.oldImsi = new FieldLong();
		this.newSubscriptionDate = new FieldDate();
		this.oldSubscriptionDate = new FieldDate();
		this.newProfile = new FieldString();
		this.oldProfile = new FieldString();
		this.newSubprofile = new FieldString();
		this.oldSubprofile = new FieldString();	
		this.newRatePlan = new FieldString();
		this.oldRatePlan = new FieldString();
		//this.new_status = new CSVSchemaTable( new Statuses(), Statuses.Fields.status );
		//this.old_status = new CSVSchemaTable( new Statuses(), Statuses.Fields.status );
		//this.new_status = new CSVString();
		//this.old_status = new CSVString();
		this.newStatus = new FieldEnum( SUBSTATUS.values() );
		this.oldStatus = new FieldEnum( SUBSTATUS.values() );		
		this.newNetwork = new FieldString(); 
		this.oldNetwork = new FieldString();
		this.newTongue = new FieldString();
		this.oldTongue = new FieldString();		
		this.newInTag = new FieldString();
		this.oldInTag = new FieldString();		
		this.newHobbies = new FieldSet( HOBBIES.values() );
		this.oldHobbies = new FieldSet( HOBBIES.values() );
		this.newGender = new FieldEnum( GENDER.values() ); 
		this.oldGender = new FieldEnum( GENDER.values() );
		this.newSalary = new FieldString();
		this.oldSalary = new FieldString(); 
		this.delete = new FieldEnum( DELETE.values() );
		
		this.sms = new FieldEnum( SMS.values() );
		
		this.file_content = new StringBuilder();
		this.rows = new ArrayList<String>();
		
		this.env = null;
		this.tenant = null;
		
		this.rawData = new FieldString();		
		this.recharge_amount = new FieldLong();
		
	}
	
	private void setEnvironment( NetworkEnvironment env ) throws CDRException {
		
		this.env = env;
		
		if( this.env != null && this.tenant != null ) {
			this.setDataSources();
		}
		
	}

	private void setTenant( String tenant ) throws CDRException {
		
		this.tenant = tenant;
		
		if( this.env != null && this.tenant != null ) {
			this.setDataSources();
		}
		
	}
	
	private void setDataSources() throws CDRException {
		
		//this.location.setSchemaTableValues( this.env.getDataSource( this.tenant ) );
		
	}

	public void setSeparator( String separator ) {
		this.separator = separator;
	}
	
	public void addLine() {
		
		String[] field_values = null; 	
		
		try {
			
			int fields_count = (int)this.getClass().getDeclaredMethod( "getFieldsCount" ).invoke( this );
						
			field_values = new String[ fields_count ];
						
		} catch (	IllegalAccessException | 
					IllegalArgumentException | 
					InvocationTargetException | 
					SecurityException | 
					NoSuchMethodException e 
				) { logger.error( e.getMessage(), e ); }
		
		if( field_values != null ) { 
			
			for( Method method : this.getClass().getDeclaredMethods() ) {
				
				try {
					
					for( Annotation field_type : method.getAnnotations() ) {
						
						int field_position = (int)field_type.getClass().getDeclaredMethod( "position" ).invoke( field_type ); 
						
						String getMethod = "get" + field_type.getClass().getInterfaces()[0].getSimpleName();
						
						field_values[ field_position ] = (String)this.getClass().getMethod( getMethod ).invoke( this );
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
				
			row.append( field_values[ v ] ).append( this.separator );
			
		}
		
		if( row.length() > 0 ) { 
			
			row.setLength( row.length() - 1 ); 
						
			row.append( "\n" );
			
			this.file_content.append( row );
			
			this.rows.add( row.toString() );
		
		}
				
	}
		
	public void addLines( int lines ) {
		
		for( int l = 0; l < lines; l++ ) { this.addLine(); }
		
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
	
	public void save() throws IOFileException {
		
		IOFileUtils.saveResource( this.file_content.toString(), this.output_dir, this.file_name );
		
	}
	
	public Boolean checkFile() throws IOFileException {
		
		File file = new File( "output/" + IOFileUtils.buildResourcePath( this.output_dir, this.file_name ) );
		
		return ( file.exists() && file.isFile() );
		
	}

	public void send( Service remote_host, String remote_path, String sftp_user ) {
		System.out.println( remote_host.getHostAddress() );		
		SFTPClient sftp = new SFTPClient( remote_host, sftp_user );
		
		if( sftp.isConnected() ) {
			
			String local_path = System.getProperty( "user.dir" ) + "/output/" + this.getOutputDir() + "/";
			
            sftp.copyFile( local_path, this.getFileName(), remote_path , this.getFileName(), SFTPClient.CopyType.LOCAL_TO_REMOTE );
            		
		}
				
	}

	public void send( Environment remote_host, String remote_path ) {
		
		try {
			
			JSONObject ssh = remote_host.getServiceType( ServicesType.SSH );
			
			SFTPClient sftp = new SFTPClient( ssh.getString( "host" ), ssh.getInt( "port" ), ssh.getString( "user" ), ssh.getString( "password" ) );
			
			if( sftp.isConnected() ) {
				
				String local_path = System.getProperty( "user.dir" ) + "/output/" + this.getOutputDir() + "/";
				
	            sftp.copyFile( local_path, this.getFileName(), remote_path , this.getFileName(), SFTPClient.CopyType.LOCAL_TO_REMOTE );
	            		
			}
						
		} catch( JSONException e ) {}
		
	}
	
	public void setOutputPath( String output_dir, String file_name ) {
		
		this.output_dir = output_dir;
		
		this.file_name = file_name;
		
	}
	
	public String getOutputDir() {
		
		return ( this.output_dir != null ? this.output_dir : "" );
		
	}
	
	public String getFileName() {
		
		return ( this.file_name != null ? this.file_name : "" );
		
	}
	
	public String generateFileName() {
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss" );
		
		return this.getClass().getSimpleName() + "_" + sdf.format( Calendar.getInstance().getTime() ) + ".csv";
		
	}
	
	public void feeder( Calendar startDate, Calendar endDate, Map<String, Object> parameters ) throws CDRException, JSONSException, IOFileException {
		
		if( startDate == null || endDate == null || parameters == null ) { throw new CDRException( "invalid parameters list" ); }
		
		/** to avoid infinite cycle with startDate = endDate = some passed object */
		Calendar untilDate = Calendar.getInstance();
		untilDate.setTime( endDate.getTime() );
		
		/** generate cdrs in the datetime interval */
		for( java.util.Date date = startDate.getTime(); !startDate.after( untilDate ); startDate.add(Calendar.DATE, 1), date = startDate.getTime() ) {
           
			Calendar elaborationDate = Calendar.getInstance();
            elaborationDate.setTime( date );
            
            this.feederByDate( elaborationDate, parameters );
           
        }
		
	}
	
	public void feeder( ArrayList<Calendar> days, Map<String, Object> parameters ) throws CDRException, JSONSException, IOFileException {
		
		if( parameters == null ) { throw new CDRException( "invalid parameters list" ); }
		
		/** generate cdrs with different days */
		for( int d = 0; d < days.size(); d++ ) {
           
			this.feederByDate( days.get( d ), parameters );
            
        }
		
	}
	
	public void feederByDate( Calendar date, Map<String, Object> parameters ) throws CDRException, JSONSException, IOFileException {
		
		if( parameters == null ) { throw new CDRException( "invalid parameters list" ); }
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		
		logger.info( Log.CHECKING.createMessage( "Start cdr feeder on " + sdf.format( date.getTime() ) ) );
		
		/** set environment */
		if( parameters.containsKey( CDR.Parameters.env.name() ) && parameters.containsKey( CDR.Parameters.tenant.name() ) ) {
			
			this.setEnvironment( (NetworkEnvironment)parameters.get( CDR.Parameters.env.name() ) );
			this.setTenant( (String)parameters.get( CDR.Parameters.tenant.name() ) );
		
		} else { 
			
			throw new CDRException( "the environment and tenant parameters are not present" );
		
		}
		
		/** update dynamic datetime field */
		for( Map.Entry<String, Object> parameter : parameters.entrySet() ) {
		   		
			Calendar newDate = Calendar.getInstance();
			newDate.setTime( date.getTime() );
			
			Pattern pattern_param_date_place_holder = Pattern.compile( "###[_0-9a-zA-Z]*date[_0-9a-zA-Z]*###" );
			Matcher matcher_param_date_place_holder = pattern_param_date_place_holder.matcher( parameter.getKey() );
						
			if( matcher_param_date_place_holder.find() ) {
				
				if( parameter.getValue() instanceof String ) {
					
					/** get dynamic date setting */
					Pattern pattern_param_current_date_place_holder = Pattern.compile( "@current|([+-][0-9])+(YEAR|MONTH|DAY|HOUR|MINUTE|SECOND)" );
					Matcher matcher_param_current_date_place_holder = pattern_param_current_date_place_holder.matcher( (String)parameter.getValue() );
				
					/** initialize date values */
					int YEAR = date.get( Calendar.YEAR ); 
					int MONTH = date.get( Calendar.MONTH ); 
					int DAY = date.get( Calendar.DATE ); 
					int HOUR = date.get( Calendar.HOUR ); 
					int MINUTE = date.get( Calendar.MINUTE ); 
					int SECOND = date.get( Calendar.SECOND );
					
					/** increment date values */
					while( matcher_param_current_date_place_holder.find() ) {
						
						try { 
						
							if( matcher_param_current_date_place_holder.group( 2 ) != null ) {
								
								switch( matcher_param_current_date_place_holder.group( 2 ) ) {
								
									case "YEAR": { 
										YEAR = YEAR + Integer.valueOf( Integer.parseInt( matcher_param_current_date_place_holder.group( 1 ) ) );
										break;
									}
									case "MONTH": { 
										MONTH = MONTH + Integer.valueOf( Integer.parseInt( matcher_param_current_date_place_holder.group( 1 ) ) );
										break;
									}
									case "DAY": { 
										DAY = DAY + Integer.valueOf( Integer.parseInt( matcher_param_current_date_place_holder.group( 1 ) ) );
										break;
									}									
									case "HOUR": { 
										HOUR = HOUR + Integer.valueOf( Integer.parseInt( matcher_param_current_date_place_holder.group( 1 ) ) );
										break;
									}									
									case "MINUTE": { 
										MINUTE = MINUTE + Integer.valueOf( Integer.parseInt( matcher_param_current_date_place_holder.group( 1 ) ) );
										break;
									}									
									case "SECOND": { 
										SECOND = SECOND + Integer.valueOf( Integer.parseInt( matcher_param_current_date_place_holder.group( 1 ) ) );
										break;
									}								
								
								}
								
							}
							
						} catch( NumberFormatException e ) {}
												
					}
					
					/** set incremented date */
					newDate.set( YEAR, MONTH, DAY, HOUR, MINUTE, SECOND );
					
					/** set new date parameter */
					parameters.put( parameter.getKey(), newDate );
					
				}
				
			}
			
		}
		
		/** stop collector daemon, collector process and cdrwriter process in the remote server via ssh*/
		/*ExpressionKernelCommands.collectorServiceStop( env );
		ExpressionKernelCommands.collectorStop( env );
    	ExpressionKernelCommands.cdrwriterStop( env );
		
		/** set datetime on remote server */
    	//ExpressionKernelCommands.setDatetime( env, date );
    	
    	/** generate cdrs from json file and put them in the remote server */
    	this.generateCDRFromJson( parameters );
		    	
    	/** start collector daemon, collector process and cdrwriter process in the remote server via ssh*/
    	/*ExpressionKernelCommands.cdrwriterStart( env );
    	ExpressionKernelCommands.collectorStart( env );
		ExpressionKernelCommands.collectorServiceStart( env );
			*/	
	}
	
	public void generateCDRFromJson( Map<String, Object> parameters ) throws JSONSException, IOFileException, CDRException {
		
		if( !parameters.containsKey( CDR.Parameters.cfgDir.name() ) || 
			!parameters.containsKey( CDR.Parameters.cfgFile.name() ) 		
		) {
			throw new CDRException ( "some needed parameters are not present in the json configuration file" );
		}
		
		/** get json configuration file path */
		String jsonSourceDir = (String)parameters.get( CDR.Parameters.cfgDir.name() );
		String jsonSourceFile = (String)parameters.get( CDR.Parameters.cfgFile.name() );
		//System.out.println( jsonSourceDir );
		//System.out.println( jsonSourceFile );
		/** get cdrs configuration json */
		JSONObject cdrCfg = JSONUtils.loadJSONResource( jsonSourceDir , jsonSourceFile );
	
		/** set environment */
		this.setEnvironment( (NetworkEnvironment)parameters.get( CDR.Parameters.env.name() ) );
		this.setTenant( (String)parameters.get( CDR.Parameters.tenant.name() ) );
		
		/** define cdr type package */
		String cdr_types_package = this.getClass().getPackage().getName() + ".types";
		
		/** set cdr type configuration */
		@SuppressWarnings("unchecked")
		Iterator<String> cdrTypes = cdrCfg.keys();
		
		while( cdrTypes.hasNext() ) {
						
			/** cdr type class name */
			String cdrTypeClassName = cdrTypes.next();
			//System.out.println( cdrTypeClassName );
			logger.info( Log.LOADING.createMessage( cdrTypeClassName + " file" ) );  
			
			try {
				
				if( 	
						!cdrCfg.getJSONObject( cdrTypeClassName ).has( "enabled" ) /** default enabled = true */|| 
						cdrCfg.getJSONObject( cdrTypeClassName ).getBoolean( "enabled" ) == true ) 
				{
				
					/** get cdr class instance */
					Class<?> cdrTypeClass = Class.forName( cdr_types_package + "." + cdrTypeClassName );				
					Object cdrTypeClassInstance = cdrTypeClass.newInstance();
					
					/** get cdr type fields configuration */
					JSONObject cdrTypeFieldsCfg = cdrCfg.getJSONObject( cdrTypeClassName );
					
					/** set field values */
					@SuppressWarnings("unchecked")
					Iterator<String> cdrTypeFields = cdrTypeFieldsCfg.keys();
					
					while( cdrTypeFields.hasNext() ) {
						
						/** get cdr type field name */
						String cdrTypeFieldName = cdrTypeFields.next();					
											
						if( cdrTypeFieldsCfg.get( cdrTypeFieldName ) instanceof JSONObject ) {
						
							/** get method to invoke */
							StringBuilder cdrTypeMethodName = new StringBuilder();
							
							cdrTypeMethodName.append( "set" )
												.append( cdrTypeFieldName )
												.append( "Strategy" )
												.append( cdrTypeFieldsCfg.getJSONObject( cdrTypeFieldName ).get( "strategy" ) );
							
							/** invoke method */
							for( Method cdrTypeMethod : cdrTypeClass.getMethods() ) {
														
								if( cdrTypeMethod.getName().equals( cdrTypeMethodName.toString() ) ) {
									//System.out.println( cdrTypeMethodName.toString() );	
									try {
										// TODO
										/*
										System.out.println( "----------" );
										for( Field cdrAttribute : this.getClass().getDeclaredFields() ) {
											
											for( Annotation cdrAttributeAnnotation : cdrAttribute.getAnnotations() ) {
												
												if( cdrAttributeAnnotation.getClass().getInterfaces()[0].getSimpleName().equals( cdrTypeFieldName ) ) {
													
													if( cdrAttribute.getType().getSimpleName().equals( "CSVSchemaTable" ) ) {
														
														System.out.println( "@@@@@@@@@@: " + cdrTypeFieldName );
														
														System.out.println( "TTTTTT: " + cdrAttribute.getType().getSimpleName() );
														
														try {
															Method m = this.getClass().getMethod( "setLocationValues" );
															cdrTypeMethod.invoke( "setLocationValues", this.env.getDataSource( this.tenant ) );
															
														} catch (
																NoSuchMethodException
																| SecurityException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
															System.out.println( "PIPPO, PIPPO" );
														}
														
																											
													}
													
												}
												
											}
											//if( cdrAttribute.isAnnotationPresent( cdrTypeAnnotation.newInstance() ) ) {
												
											//}
											//System.out.println( f.getName() );
										}*/
										//System.out.println( "----------" );
										/** get cdr type field paratemers */
										Object[] fieldParameters = this.getCDRTypeParameterValues( cdrTypeFieldName, cdrTypeMethod, cdrTypeFieldsCfg.getJSONObject( cdrTypeFieldName ), parameters );
																		
										/** set the field value using the strategy set in the json configuration file */
										cdrTypeMethod.invoke( cdrTypeClassInstance, fieldParameters );
																		
									} catch (	IllegalAccessException | 
											 	IllegalArgumentException | 
											 	InvocationTargetException e ) 
									{ 	logger.error( e.getMessage(), e ); 	}
									
								}							
								
							}
							
						}
						
					}
					
					/** get cdr instance class */
					CDR cdr = ((CDR)cdrTypeClassInstance);
					
					/** add lines to cdr file */
					if( cdrTypeFieldsCfg.has( "linesCount" ) ) { 
						
						cdr.addLines( cdrTypeFieldsCfg.getInt( "linesCount" ) ); 
					
						/** print the cdr in the console output */
						if( cdrTypeFieldsCfg.has( "print" ) && (Boolean)cdrTypeFieldsCfg.get( "print" ) ) { cdr.print(); }
										
						/** load output dir */
						if( cdrTypeFieldsCfg.has( "outputDir" ) ) { 
							
							try {
								
								parameters.put( CDR.Parameters.outputDir.name(), cdrTypeFieldsCfg.get( "outputDir" ) );				
					
								/** set cdr output path */
								long current_timestamp = Calendar.getInstance().getTimeInMillis();
								String file_name = cdrTypeClassName.toLowerCase() + "_" + current_timestamp + ".csv";
								
								cdr.setOutputPath( (String)parameters.get( CDR.Parameters.outputDir.name() ), file_name );
								
								/** store cdr file */
								cdr.save();
								
								/** send cdr file */
								if( cdrTypeFieldsCfg.has( CDR.Parameters.send.name() ) && cdrTypeFieldsCfg.has( CDR.Parameters.depositPath.name() )) {
									
									if( cdrTypeFieldsCfg.getBoolean( CDR.Parameters.send.name() ) ) { 
										
										cdr.send( this.env.getSSHService( "collector" ), cdrTypeFieldsCfg.getString( CDR.Parameters.depositPath.name() ), "root" );
																					
									}
									
								}
								
								/** delete stored file */
								if( 	!cdrTypeFieldsCfg.has( CDR.Parameters.store.name() ) ||
										!cdrTypeFieldsCfg.getBoolean( CDR.Parameters.store.name() )
								) {								
									IOFileUtils.deleteResource( (String)parameters.get( CDR.Parameters.outputDir.name() ), file_name );								
								}
																
							} catch (JSONException e) {
								logger.error( e.getMessage(), e );
							}
							
						} else {
							throw new CDRException ( "output dir not present in the " + cdrTypeClassInstance.getClass().getSimpleName() + " section of the json configuration file" );
						}
										
					}
								
				}				
				
			} catch ( 	JSONException | 
						ClassNotFoundException | 
						InstantiationException | 
						IllegalAccessException e ) 
			{ 	logger.error( e.getMessage(), e ); 	}
			
		}		
				
	}

	@SuppressWarnings("unchecked")
	private Object[] getCDRTypeParameterValues( String cdrTypeFieldName, Method cdrTypeMethod, JSONObject fieldCfg, Map<String, Object> parameters ) throws JSONException {
		
		/** get method parameters configuration */
		JSONArray paramsCfg = fieldCfg.getJSONArray( "parameters" );
				
		/** get cdr type field parameters */
		Object[] fieldParameters = new Object[ paramsCfg.length() ];
		
		/** get parameters type */
		Class<?>[] cdrMethodParamsTypes = cdrTypeMethod.getParameterTypes();
		
		for( int obj = 0; obj < paramsCfg.length(); obj++ ) {
						
			try {
				
				/** get parameter value */
				Object paramValue = paramsCfg.get( obj );
				
				Pattern pattern_param_place_holder = Pattern.compile( "###[_0-9a-zA-Z]+###" );
				Matcher matcher_param_place_holder = pattern_param_place_holder.matcher( (String)paramValue );
				
				if( matcher_param_place_holder.find() ) {
					paramValue = parameters.get( matcher_param_place_holder.group(0) );
				}
				//System.out.println( cdrMethodParamsTypes[obj].getSimpleName() );				
				/** cast parameters */
				switch( cdrMethodParamsTypes[obj].getSimpleName() ) {
				
					case "Boolean": {
						fieldParameters[ obj ] = Boolean.valueOf( (String)paramValue );
						break;
					}
					case "Calendar": {
						fieldParameters[ obj ] = paramValue;
						break;
					}
					case "Enum": {
						
						for( @SuppressWarnings("rawtypes") Class<Enum> cl : (Class<Enum>[])this.getClass().getClasses() ) {
							
							for( Annotation a : cl.getAnnotations() ) {
								
								/** get correct enum value associated to the current field via annotation */
								if( a.getClass().getInterfaces()[0].getSimpleName().equals( cdrTypeFieldName ) ) {
									try {
										fieldParameters[ obj ] = Enum.valueOf( cl, String.valueOf( paramValue ).toUpperCase() );
									} catch( IllegalArgumentException e ) {}
								}
								
							}
						}
						
						break;
					}
					case "Integer": {
						fieldParameters[ obj ] = Integer.valueOf( (String)paramValue );					
						break;
					}
					case "Long": {
						fieldParameters[ obj ] = Long.valueOf( (String)paramValue );					
						break;
					}
					case "String": {
						fieldParameters[ obj ] = String.valueOf( paramValue );					
						break;
					}
									
				}
								
			} catch ( NumberFormatException | JSONException e) { 
				logger.error( e.getMessage(), e );
			}
			
		}	
		
		return fieldParameters;
		
	}
	
}
