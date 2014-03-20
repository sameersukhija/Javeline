package com.lumata.e4o.system.cdr;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.network.SFTPClient;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.system.Environment.ServicesType;

/** cdr field types */
import com.lumata.e4o.system.cdr.annotations.Msisdn;
import com.lumata.e4o.system.cdr.annotations.Date;
import com.lumata.e4o.system.cdr.annotations.NewRatePlan;
import com.lumata.e4o.system.cdr.annotations.NewSubscriptionDate;
import com.lumata.e4o.system.cdr.annotations.OldSubscriptionDate;
import com.lumata.e4o.system.cdr.annotations.Sms;
import com.lumata.e4o.system.cdr.annotations.TenantId;
import com.lumata.e4o.system.cdr.annotations.Terminating;
import com.lumata.e4o.system.cdr.annotations.Type;
import com.lumata.e4o.system.cdr.annotations.Upload;
import com.lumata.e4o.system.cdr.annotations.ValidityDate;
import com.lumata.e4o.system.cdr.annotations.Amount;
import com.lumata.e4o.system.cdr.annotations.Balance;
import com.lumata.e4o.system.cdr.annotations.DeactivationDate;
import com.lumata.e4o.system.cdr.annotations.Delay;
import com.lumata.e4o.system.cdr.annotations.Download;
import com.lumata.e4o.system.cdr.annotations.Duration;

/** csv field types */
import com.lumata.e4o.system.csv.types.CSVMsisdn;
import com.lumata.e4o.system.csv.types.CSVDate;
import com.lumata.e4o.system.csv.types.CSVEnum;
import com.lumata.e4o.system.csv.types.CSVLong;
import com.lumata.e4o.system.csv.types.ICSVEnum;
import com.lumata.e4o.system.csv.types.CSVSchemaTable;

/** CDR exception */
import com.lumata.expression.operators.exceptions.CDRException;

public class CDR {
	
	private static final Logger logger = LoggerFactory.getLogger( CDR.class );
	
	private String dir;
	private String file_name;
	private StringBuilder file_content;
	private ArrayList<String> rows;
	
	@Msisdn 
	protected CSVMsisdn msisdn;	
	
	@Date 
	protected CSVDate date;
	
	@ValidityDate
	protected CSVDate validity_date; 
	
	@DeactivationDate
	protected CSVDate deactivation_date;
	
	@Duration
	protected CSVLong duration; 
	
	@Amount
	protected CSVLong amount; 
	
	@Balance
	protected CSVLong balance; 
	
	@Terminating
	protected CSVEnum terminating; 
	
	@Delay
	protected CSVLong delay;
	
	@Type
	protected CSVEnum type;
	//BundleName, 
	//BundleBalance, 
	//BundlePurchased,
	
	@Download
	protected CSVLong download;
	
	@Upload
	protected CSVLong upload;
	
	@NewRatePlan
	protected CSVSchemaTable new_rate_plan;
	//OldRatePlan,
	//NewProfile,
	//OldProfile,
	//NewSubProfile,
	//OldSubProfile,
	//NewStatus,
	//OldStatus,
	//NewNetwork,
	//OldNetwork,
	
	@NewSubscriptionDate
	protected CSVDate new_subscription_date;
	
	@OldSubscriptionDate
	protected CSVDate old_subscription_date;
	
	@Sms
	protected CSVEnum sms;
	
	//Code,
	//PointOfRedemption,
	
	@TenantId
	protected CSVLong tenant_id;
	
	public enum TERMINATING implements ICSVEnum { 
		
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
	
	public enum SMS implements ICSVEnum { 
		
		YES("YES"), 
		NO("NO"); 
		
		private String value;
		
		SMS( String value ) { 
			this.value = value; 
		}
		
		public String value() {
			return this.value;
		}
	
	}
	
	public enum TYPE implements ICSVEnum { 
		
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
	
	public CDR() {
		
		this.msisdn = new CSVMsisdn();		
		this.date = new CSVDate();
		this.validity_date = new CSVDate(); 
		this.deactivation_date = new CSVDate();
		this.duration = new CSVLong(); 
		this.amount = new CSVLong(); 
		this.balance = new CSVLong();
		this.terminating = new CSVEnum( TERMINATING.values() );
		this.delay = new CSVLong();
		this.type = new CSVEnum( TYPE.values() );
		this.new_subscription_date = new CSVDate();
		this.old_subscription_date = new CSVDate();
		this.sms = new CSVEnum( SMS.values() );
		
		this.file_content = new StringBuilder();
		this.rows = new ArrayList<String>();
		
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
				
			row.append( field_values[ v ] ).append( "|" );
			
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
		
		IOFileUtils.saveResource( this.file_content.toString(), this.dir, this.file_name );
		
	}
	
	public void send( Environment remote_host, String remote_path ) {
		
		try {
			
			JSONObject ssh = remote_host.getServiceType( ServicesType.SSH );
			
			SFTPClient sftp = new SFTPClient( ssh.getString( "host" ), ssh.getInt( "port" ), ssh.getString( "user" ), ssh.getString( "password" ) );
			
			if( sftp.isConnected() ) {
				
				String local_path = System.getProperty( "user.dir" ) + "/src/main/resources/" + this.getDir() + "/";
				
	            sftp.copyFile( local_path, this.getFileName(), remote_path , this.getFileName(), SFTPClient.CopyType.LOCAL_TO_REMOTE );
	        				
			}
						
		} catch( JSONException e ) {}
		
	}
	
	public void setPath( String dir, String file_name ) {
		
		this.dir = dir;
		
		this.file_name = file_name;
		
	}
	
	public String getDir() {
		
		return ( this.dir != null ? this.dir : "" );
		
	}
	
	public String getFileName() {
		
		return ( this.file_name != null ? this.file_name : "" );
		
	}
	
	public String generateFileName() {
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss" );
		
		return this.getClass().getSimpleName() + "_" + sdf.format( Calendar.getInstance().getTime() ) + ".csv" ;
		
	}
	
}
