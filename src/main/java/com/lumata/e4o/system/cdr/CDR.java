package com.lumata.e4o.system.cdr;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.e4o.system.cdr.annotations.Amount;
import com.lumata.e4o.system.cdr.annotations.Balance;
import com.lumata.e4o.system.cdr.annotations.Date;
import com.lumata.e4o.system.cdr.annotations.DeactivationDate;
import com.lumata.e4o.system.cdr.annotations.Delay;
import com.lumata.e4o.system.cdr.annotations.Download;
import com.lumata.e4o.system.cdr.annotations.Duration;
import com.lumata.e4o.system.cdr.annotations.Msisdn;
import com.lumata.e4o.system.cdr.annotations.NewSubscriptionDate;
import com.lumata.e4o.system.cdr.annotations.OldSubscriptionDate;
import com.lumata.e4o.system.cdr.annotations.Sms;
import com.lumata.e4o.system.cdr.annotations.TenantId;
import com.lumata.e4o.system.cdr.annotations.Terminating;
import com.lumata.e4o.system.cdr.annotations.Upload;
import com.lumata.e4o.system.cdr.annotations.ValidityDate;
import com.lumata.e4o.system.csv.types.CSVDate;
import com.lumata.e4o.system.csv.types.CSVDateIncrement;
import com.lumata.e4o.system.csv.types.CSVEnum;
import com.lumata.e4o.system.csv.types.CSVLong;
import com.lumata.e4o.system.csv.types.CSVMsisdn;
import com.lumata.e4o.system.csv.types.ICSVEnum;
import com.lumata.expression.operators.exceptions.CDRException;

public class CDR {
	
	private static final Logger logger = LoggerFactory.getLogger( CDR.class );
	
	private String path;
	private String file_name;
	private StringBuilder file_content;
	private ArrayList<String> rows;
		
	private CSVMsisdn msisdn;
	private CSVDate date;
	private CSVDate validity_date; 
	private CSVDate deactivation_date;
	private CSVLong duration; 
	private CSVLong amount; 
	private CSVLong balance; 
	private CSVEnum terminating; 
	//Type,
	private CSVLong delay;
	//BundleName, 
	//BundleBalance, 
	//BundlePurchased, 
	private CSVLong download;
	private CSVLong upload;
	//NewRatePlan,
	//OldRatePlan,
	//NewProfile,
	//OldProfile,
	//NewSubProfile,
	//OldSubProfile,
	//NewStatus,
	//OldStatus,
	//NewNetwork,
	//OldNetwork,
	private CSVDate new_subscription_date;
	private CSVDate old_subscription_date;
	private CSVEnum sms;
	//Code,
	//PointOfRedemption,
	private CSVLong tenant_id;
	
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
		this.new_subscription_date = new CSVDate();
		this.old_subscription_date = new CSVDate();
		this.sms = new CSVEnum( SMS.values() );
		
		this.file_content = new StringBuilder();
		this.rows = new ArrayList<String>();
		
	}
	
	/** Msisdn field */
	@Msisdn
	protected String getMsisdn() throws CDRException {
				
		return ( this.msisdn != null ? this.msisdn.getMsisdn() : "" ); 
		
	}
	
	@Msisdn
	protected void setMsisdnOptions( final Integer prefix, final Integer length ) throws CDRException {
		
		if( this.msisdn != null ) { this.msisdn.setMsisdnOptions( prefix, length ); }
		
	}

	@Msisdn
	protected void setMsisdnStrategyFixed( final Long value ) throws CDRException {	
		
		if( this.msisdn != null ) { this.msisdn.setMsisdnStrategyFixed( value ); }
		
	}
	
	@Msisdn
	protected void setMsisdnStrategyIncrement( final Long value, final Integer increment ) throws CDRException {
		
		if( this.msisdn != null ) { this.msisdn.setMsisdnStrategyIncrement( value, increment ); }
		
	}
	
	@Msisdn
	protected void setMsisdnStrategyRandom( final Long min_value, final Long max_value ) throws CDRException {
		
		if( this.msisdn != null ) { this.msisdn.setMsisdnStrategyRandom( min_value, max_value ); }
		
	}
	
	@Msisdn
	protected void cleanMsisdn() {
		
		if( this.msisdn != null ) { this.msisdn.cleanMsisdn(); }
			
	}
	
	@Msisdn
	protected void cleanMsisdnOptions() {
				
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnOptions(); }
				
	}
	
	@Msisdn
	protected void cleanMsisdnStrategyIncrement() {
		
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnStrategyIncrement(); }
	}
	
	@Msisdn
	protected void cleanMsisdnStrategyRandom() {
		
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnStrategyRandom(); }
		
	}

	/** Date field */
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
	protected void setDateStrategyIncrement( final Calendar date, final CSVDateIncrement increment ) throws CDRException {
		
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

	/** Validity Date field */
	@ValidityDate
	protected String getValidityDate() {
		
		return ( this.validity_date != null ? this.validity_date.getDate() : "" ); 
		
	}
	
	@ValidityDate
	protected void setValidityDateStrategyFixed( final Calendar date ) throws CDRException {
		
		if( this.validity_date != null ) { this.validity_date.setDateStrategyFixed( date ); }
		
	}

	@ValidityDate
	protected void setValidityDateFormat( String format ) throws CDRException {
		
		if( this.validity_date != null ) { this.validity_date.setDateFormat( format ); }
						
	}	
	
	@ValidityDate
	protected void setValidityDateStrategyIncrement( final Calendar date, final CSVDateIncrement increment ) throws CDRException {
		
		if( this.validity_date != null ) { this.validity_date.setDateStrategyIncrement( date, increment ); }
		
	}
	
	@ValidityDate
	protected void setValidityDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws CDRException {
		
		if( this.validity_date != null ) { this.validity_date.setDateStrategyRandom( date_left, date_right ); }
		
	}
	
	@ValidityDate
	protected void cleanValidityDateStrategyFixed() {
		
		if( this.validity_date != null ) { this.validity_date.cleanDateStrategyFixed(); }
					
	}
	
	@ValidityDate
	protected void cleanValidityDateStrategyIncrement() {
		
		if( this.validity_date != null ) { this.validity_date.cleanDateStrategyIncrement(); }
					
	}
	
	@ValidityDate
	protected void cleanValidityDateStrategyRandom() {
		
		if( this.validity_date != null ) { this.validity_date.cleanDateStrategyRandom(); }
					
	}
	
	/** Deactivation Date field */
	@DeactivationDate
	protected String getDeactivationDate() {
		
		return ( this.deactivation_date != null ? this.deactivation_date.getDate() : "" ); 
		
	}
	
	@DeactivationDate
	protected void setDeactivationDateStrategyFixed( final Calendar date ) throws CDRException {
		
		if( this.deactivation_date != null ) { this.deactivation_date.setDateStrategyFixed( date ); }
		
	}

	@DeactivationDate
	protected void setDeactivationDateFormat( String format ) throws CDRException {
		
		if( this.deactivation_date != null ) { this.deactivation_date.setDateFormat( format ); }
						
	}	
	
	@DeactivationDate
	protected void setDeactivationDateStrategyIncrement( final Calendar date, final CSVDateIncrement increment ) throws CDRException {
		
		if( this.deactivation_date != null ) { this.deactivation_date.setDateStrategyIncrement( date, increment ); }
		
	}
	
	@DeactivationDate
	protected void setDeactivationDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws CDRException {
		
		if( this.deactivation_date != null ) { this.deactivation_date.setDateStrategyRandom( date_left, date_right ); }
		
	}
	
	@DeactivationDate
	protected void cleanDeactivationDateStrategyFixed() {
		
		if( this.deactivation_date != null ) { this.deactivation_date.cleanDateStrategyFixed(); }
					
	}
	
	@DeactivationDate
	protected void cleanDeactivationDateStrategyIncrement() {
		
		if( this.deactivation_date != null ) { this.deactivation_date.cleanDateStrategyIncrement(); }
					
	}
	
	@DeactivationDate
	protected void cleanDeactivationDateStrategyRandom() {
		
		if( this.deactivation_date != null ) { this.deactivation_date.cleanDateStrategyRandom(); }
					
	}
	
	/** Duration field */
	@Duration
	protected String getDuration() throws CDRException {
		
		return ( this.duration != null ? String.valueOf( this.duration.getLong() ) : "" ); 
		
	}
	
	@Duration
	protected void setDurationStrategyFixed( final Long current_value ) throws CDRException {
		
		if( this.duration != null ) { this.duration.setLongStrategyFixed( current_value ); }
		
	}

	@Duration
	protected void setDurationStrategyIncrement( final Long current_value, final Integer increment ) throws CDRException {
		
		if( this.duration != null ) { this.duration.setLongStrategyIncrement( current_value, increment ); }
		
	}
	
	@Duration
	protected void setDurationStrategyRandom( final Long long_left, final Long long_right ) throws CDRException {
		
		if( this.duration != null ) { this.duration.setLongStrategyRandom( long_left, long_right ); }
		
	}
	
	@Duration
	protected void cleanDurationStrategyFixed() {
		
		if( this.duration != null ) { this.duration.cleanLongStrategyFixed(); }
					
	}
	
	@Duration
	protected void cleanDurationStrategyIncrement() {
		
		if( this.duration != null ) { this.duration.cleanLongStrategyIncrement(); }
					
	}
	
	@Duration
	protected void cleanDurationStrategyRandom() {
		
		if( this.duration != null ) { this.duration.cleanLongStrategyRandom(); }
					
	}
	
	/** Amount field */
	@Amount
	protected String getAmount() throws CDRException {
		
		return ( this.amount != null ? String.valueOf( this.amount.getLong() ) : "" ); 
		
	}
	
	@Amount
	protected void setAmountStrategyFixed( final Long current_value ) throws CDRException {
		
		if( this.amount != null ) { this.amount.setLongStrategyFixed( current_value ); }
		
	}

	@Amount
	protected void setAmountStrategyIncrement( final Long current_value, final Integer increment ) throws CDRException {
		
		if( this.amount != null ) { this.amount.setLongStrategyIncrement( current_value, increment ); }
		
	}
	
	@Amount
	protected void setAmountStrategyRandom( final Long long_left, final Long long_right ) throws CDRException {
		
		if( this.amount != null ) { this.amount.setLongStrategyRandom( long_left, long_right ); }
		
	}
	
	@Amount
	protected void cleanAmountStrategyFixed() {
		
		if( this.amount != null ) { this.amount.cleanLongStrategyFixed(); }
					
	}
	
	@Amount
	protected void cleanAmountStrategyIncrement() {
		
		if( this.amount != null ) { this.amount.cleanLongStrategyIncrement(); }
					
	}
	
	@Amount
	protected void cleanAmountStrategyRandom() {
		
		if( this.amount != null ) { this.amount.cleanLongStrategyRandom(); }
					
	}

	/** Balance field */
	@Balance
	protected String getBalance() throws CDRException {
		
		return ( this.balance != null ? String.valueOf( this.balance.getLong() ) : "" ); 
		
	}
	
	@Balance
	protected void setBalanceStrategyFixed( final Long current_value ) throws CDRException {
		
		if( this.balance != null ) { this.balance.setLongStrategyFixed( current_value ); }
		
	}

	@Balance
	protected void setBalanceStrategyIncrement( final Long current_value, final Integer increment ) throws CDRException {
		
		if( this.balance != null ) { this.balance.setLongStrategyIncrement( current_value, increment ); }
		
	}
	
	@Balance
	protected void setBalanceStrategyRandom( final Long long_left, final Long long_right ) throws CDRException {
		
		if( this.balance != null ) { this.balance.setLongStrategyRandom( long_left, long_right ); }
		
	}
	
	@Balance
	protected void cleanBalanceStrategyFixed() {
		
		if( this.balance != null ) { this.balance.cleanLongStrategyFixed(); }
					
	}
	
	@Balance
	protected void cleanBalanceStrategyIncrement() {
		
		if( this.balance != null ) { this.balance.cleanLongStrategyIncrement(); }
					
	}
	
	@Balance
	protected void cleanBalanceStrategyRandom() {
		
		if( this.balance != null ) { this.balance.cleanLongStrategyRandom(); }
					
	}
	
	/** Terminating field */	
	@Terminating
	protected String getTerminating() throws CDRException {
		
		return ( this.terminating != null ? String.valueOf( this.terminating.getEnum() ) : "" ); 
		
	}
	
	@Terminating
	protected void setTerminatingStrategyFixed( final Enum<? extends ICSVEnum> current_value ) throws CDRException {
		
		if( this.terminating != null ) { this.terminating.setEnumStrategyFixed( current_value ); }
		
	}

	@Terminating
	protected void setTerminatingStrategyIncrement( final Enum<? extends ICSVEnum> current_value, final Integer increment ) throws CDRException {
		
		if( this.terminating != null ) { this.terminating.setEnumStrategyIncrement( current_value, increment ); }
		
	}
	
	@Terminating
	protected void setTerminatingStrategyRandom() throws CDRException {
		
		if( this.terminating != null ) { this.terminating.setEnumStrategyRandom(); }
		
	}
	
	@Terminating
	protected void cleanTerminatingStrategyFixed() {
		
		if( this.terminating != null ) { this.terminating.cleanEnumStrategyFixed(); }
					
	}
	
	@Terminating
	protected void cleanTerminatingStrategyIncrement() {
		
		if( this.terminating != null ) { this.terminating.cleanEnumStrategyIncrement(); }
					
	}
	
	@Terminating
	protected void cleanTerminatingStrategyRandom() {
		
		if( this.terminating != null ) { this.terminating.cleanEnumStrategyRandom(); }
					
	}
	
	/** Delay field */
	@Delay
	protected String getDelay() throws CDRException {
		
		return ( this.delay != null ? String.valueOf( this.delay.getLong() ) : "" ); 
		
	}
	
	@Delay
	protected void setDelayStrategyFixed( final Long current_value ) throws CDRException {
		
		if( this.delay != null ) { this.delay.setLongStrategyFixed( current_value ); }
		
	}

	@Delay
	protected void setDelayStrategyIncrement( final Long current_value, final Integer increment ) throws CDRException {
		
		if( this.delay != null ) { this.delay.setLongStrategyIncrement( current_value, increment ); }
		
	}
	
	@Delay
	protected void setDelayStrategyRandom( final Long long_left, final Long long_right ) throws CDRException {
		
		if( this.delay != null ) { this.delay.setLongStrategyRandom( long_left, long_right ); }
		
	}
	
	@Delay
	protected void cleanDelayStrategyFixed() {
		
		if( this.delay != null ) { this.delay.cleanLongStrategyFixed(); }
					
	}
	
	@Delay
	protected void cleanDelayStrategyIncrement() {
		
		if( this.delay != null ) { this.delay.cleanLongStrategyIncrement(); }
					
	}
	
	@Delay
	protected void cleanDelayStrategyRandom() {
		
		if( this.delay != null ) { this.delay.cleanLongStrategyRandom(); }
					
	}
	
	/** Download field */
	@Download
	protected String getDownload() throws CDRException {
		
		return ( this.download != null ? String.valueOf( this.download.getLong() ) : "" ); 
		
	}
	
	@Download
	protected void setDownloadStrategyFixed( final Long current_value ) throws CDRException {
		
		if( this.download != null ) { this.download.setLongStrategyFixed( current_value ); }
		
	}

	@Download
	protected void setDownloadStrategyIncrement( final Long current_value, final Integer increment ) throws CDRException {
		
		if( this.download != null ) { this.download.setLongStrategyIncrement( current_value, increment ); }
		
	}
	
	@Download
	protected void setDownloadStrategyRandom( final Long long_left, final Long long_right ) throws CDRException {
		
		if( this.download != null ) { this.download.setLongStrategyRandom( long_left, long_right ); }
		
	}
	
	@Download
	protected void cleanDownloadStrategyFixed() {
		
		if( this.download != null ) { this.download.cleanLongStrategyFixed(); }
					
	}
	
	@Download
	protected void cleanDownloadStrategyIncrement() {
		
		if( this.download != null ) { this.download.cleanLongStrategyIncrement(); }
					
	}
	
	@Download
	protected void cleanDownloadStrategyRandom() {
		
		if( this.download != null ) { this.download.cleanLongStrategyRandom(); }
					
	}
	
	/** Upload field */
	@Upload
	protected String getUpload() throws CDRException {
		
		return ( this.upload != null ? String.valueOf( this.upload.getLong() ) : "" ); 
		
	}
	
	@Upload
	protected void setUploadStrategyFixed( final Long current_value ) throws CDRException {
		
		if( this.upload != null ) { this.upload.setLongStrategyFixed( current_value ); }
		
	}

	@Upload
	protected void setUploadStrategyIncrement( final Long current_value, final Integer increment ) throws CDRException {
		
		if( this.upload != null ) { this.upload.setLongStrategyIncrement( current_value, increment ); }
		
	}
	
	@Upload
	protected void setUploadStrategyRandom( final Long long_left, final Long long_right ) throws CDRException {
		
		if( this.upload != null ) { this.upload.setLongStrategyRandom( long_left, long_right ); }
		
	}
	
	@Upload
	protected void cleanUploadStrategyFixed() {
		
		if( this.upload != null ) { this.upload.cleanLongStrategyFixed(); }
					
	}
	
	@Upload
	protected void cleanUploadStrategyIncrement() {
		
		if( this.upload != null ) { this.upload.cleanLongStrategyIncrement(); }
					
	}
	
	@Upload
	protected void cleanUploadStrategyRandom() {
		
		if( this.upload != null ) { this.upload.cleanLongStrategyRandom(); }
					
	}
	
	/** New Subscription Date field */
	@NewSubscriptionDate
	protected String getNewSubscriptionDate() {
		
		return ( this.new_subscription_date != null ? this.new_subscription_date.getDate() : "" ); 
		
	}
	
	@NewSubscriptionDate
	protected void setNewSubscriptionDateStrategyFixed( final Calendar new_subscription_date ) throws CDRException {
		
		if( this.new_subscription_date != null ) { this.new_subscription_date.setDateStrategyFixed( new_subscription_date ); }
		
	}

	@NewSubscriptionDate
	protected void setNewSubscriptionDateFormat( String format ) throws CDRException {
		
		if( this.new_subscription_date != null ) { this.new_subscription_date.setDateFormat( format ); }
						
	}	
	
	@NewSubscriptionDate
	protected void setNewSubscriptionDateStrategyIncrement( final Calendar new_subscription_date, final CSVDateIncrement increment ) throws CDRException {
		
		if( this.new_subscription_date != null ) { this.new_subscription_date.setDateStrategyIncrement( new_subscription_date, increment ); }
		
	}
	
	@NewSubscriptionDate
	protected void setNewSubscriptionDateStrategyRandom( final Calendar new_subscription_date_left, final Calendar new_subscription_date_right ) throws CDRException {
		
		if( this.new_subscription_date != null ) { this.new_subscription_date.setDateStrategyRandom( new_subscription_date_left, new_subscription_date_right ); }
		
	}
	
	@NewSubscriptionDate
	protected void cleanNewSubscriptionDateStrategyFixed() {
		
		if( this.new_subscription_date != null ) { this.new_subscription_date.cleanDateStrategyFixed(); }
					
	}
	
	@NewSubscriptionDate
	protected void cleanNewSubscriptionDateStrategyIncrement() {
		
		if( this.new_subscription_date != null ) { this.new_subscription_date.cleanDateStrategyIncrement(); }
					
	}
	
	@NewSubscriptionDate
	protected void cleanNewSubscriptionDateStrategyRandom() {
		
		if( this.new_subscription_date != null ) { this.new_subscription_date.cleanDateStrategyRandom(); }
					
	}
	
	/** Old Subscription Date */
	@OldSubscriptionDate
	protected String getOldSubscriptionDate() {
		
		return ( this.old_subscription_date != null ? this.old_subscription_date.getDate() : "" ); 
		
	}
	
	@OldSubscriptionDate
	protected void setOldSubscriptionDateStrategyFixed( final Calendar old_subscription_date ) throws CDRException {
		
		if( this.old_subscription_date != null ) { this.old_subscription_date.setDateStrategyFixed( old_subscription_date ); }
		
	}

	@OldSubscriptionDate
	protected void setOldSubscriptionDateFormat( String format ) throws CDRException {
		
		if( this.old_subscription_date != null ) { this.old_subscription_date.setDateFormat( format ); }
						
	}	
	
	@OldSubscriptionDate
	protected void setOldSubscriptionDateStrategyIncrement( final Calendar old_subscription_date, final CSVDateIncrement increment ) throws CDRException {
		
		if( this.old_subscription_date != null ) { this.old_subscription_date.setDateStrategyIncrement( old_subscription_date, increment ); }
		
	}
	
	@OldSubscriptionDate
	protected void setOldSubscriptionDateStrategyRandom( final Calendar old_subscription_date_left, final Calendar old_subscription_date_right ) throws CDRException {
		
		if( this.old_subscription_date != null ) { this.old_subscription_date.setDateStrategyRandom( old_subscription_date_left, old_subscription_date_right ); }
		
	}
	
	@OldSubscriptionDate
	protected void cleanOldSubscriptionDateStrategyFixed() {
		
		if( this.old_subscription_date != null ) { this.old_subscription_date.cleanDateStrategyFixed(); }
					
	}
	
	@OldSubscriptionDate
	protected void cleanOldSubscriptionDateStrategyIncrement() {
		
		if( this.old_subscription_date != null ) { this.old_subscription_date.cleanDateStrategyIncrement(); }
					
	}
	
	@OldSubscriptionDate
	protected void cleanOldSubscriptionDateStrategyRandom() {
		
		if( this.old_subscription_date != null ) { this.old_subscription_date.cleanDateStrategyRandom(); }
					
	}
	
	/** Sms field */	
	@Sms
	protected String getSms() throws CDRException {
		
		return ( this.sms != null ? String.valueOf( this.sms.getEnum() ) : "" ); 
		
	}
	
	@Sms
	protected void setSmsStrategyFixed( final Enum<? extends ICSVEnum> current_value ) throws CDRException {
		
		if( this.sms != null ) { this.sms.setEnumStrategyFixed( current_value ); }
		
	}

	@Sms
	protected void setSmsStrategyIncrement( final Enum<? extends ICSVEnum> current_value, final Integer increment ) throws CDRException {
		
		if( this.sms != null ) { this.sms.setEnumStrategyIncrement( current_value, increment ); }
		
	}
	
	@Sms
	protected void setSmsStrategyRandom() throws CDRException {
		
		if( this.sms != null ) { this.sms.setEnumStrategyRandom(); }
		
	}
	
	@Sms
	protected void cleanSmsStrategyFixed() {
		
		if( this.sms != null ) { this.sms.cleanEnumStrategyFixed(); }
					
	}
	
	@Sms
	protected void cleanSmsStrategyIncrement() {
		
		if( this.sms != null ) { this.sms.cleanEnumStrategyIncrement(); }
					
	}
	
	@Sms
	protected void cleanSmsStrategyRandom() {
		
		if( this.sms != null ) { this.sms.cleanEnumStrategyRandom(); }
					
	}
	
	/** Tenant Id field */
	@TenantId
	protected String getTenantId() throws CDRException {
		
		return ( this.tenant_id != null ? String.valueOf( this.tenant_id.getLong() ) : "" ); 
		
	}
	
	@TenantId
	protected void setTenantIdStrategyFixed( final Long current_value ) throws CDRException {
		
		if( this.tenant_id != null ) { this.tenant_id.setLongStrategyFixed( current_value ); }
		
	}

	@TenantId
	protected void setTenantIdStrategyIncrement( final Long current_value, final Integer increment ) throws CDRException {
		
		if( this.tenant_id != null ) { this.tenant_id.setLongStrategyIncrement( current_value, increment ); }
		
	}
	
	@TenantId
	protected void setTenantIdStrategyRandom( final Long long_left, final Long long_right ) throws CDRException {
		
		if( this.tenant_id != null ) { this.tenant_id.setLongStrategyRandom( long_left, long_right ); }
		
	}
	
	@TenantId
	protected void cleanTenantIdStrategyFixed() {
		
		if( this.tenant_id != null ) { this.tenant_id.cleanLongStrategyFixed(); }
					
	}
	
	@TenantId
	protected void cleanTenantIdStrategyIncrement() {
		
		if( this.tenant_id != null ) { this.tenant_id.cleanLongStrategyIncrement(); }
					
	}
	
	@TenantId
	protected void cleanTenantIdStrategyRandom() {
		
		if( this.tenant_id != null ) { this.tenant_id.cleanLongStrategyRandom(); }
					
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
