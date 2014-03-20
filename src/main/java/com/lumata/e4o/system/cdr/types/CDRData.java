package com.lumata.e4o.system.cdr.types;

import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.annotations.*;
import com.lumata.expression.operators.exceptions.CDRException;
import java.util.Calendar;
import com.lumata.e4o.system.csv.types.CSVDateIncrement;

public class CDRData extends CDR { 

	private final int FIELDS = 6;

	public CDRData() {
		super();
	} 

	public int getFieldsCount() {
		return this.FIELDS;
	}

	public void cleanMsisdnStrategyIncrement()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnStrategyIncrement() ; }
	}

	public void cleanMsisdnStrategyRandom()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnStrategyRandom() ; }
	}

	public void setMsisdnStrategyIncrement( final Long value, final Integer increment ) throws CDRException  {
		if( this.msisdn != null ) { this.msisdn.setMsisdnStrategyIncrement( value, increment ); }
	}

	public void setMsisdnStrategyRandom( final Long min_value, final Long max_value ) throws CDRException  {
		if( this.msisdn != null ) { this.msisdn.setMsisdnStrategyRandom( min_value, max_value ); }
	}

	public void cleanMsisdnFixedStrategy()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnFixedStrategy() ; }
	}

	public void cleanMsisdnOptions()  {
		if( this.msisdn != null ) { this.msisdn.cleanMsisdnOptions() ; }
	}

	@Msisdn( position = 0 )
	public String getMsisdn() throws CDRException  {
		return this.msisdn.getMsisdn();
	}

	public void setMsisdnOptions( final Integer prefix, final Integer length ) throws CDRException  {
		if( this.msisdn != null ) { this.msisdn.setMsisdnOptions( prefix, length ); }
	}

	public void setMsisdnStrategyFixed( final Long value ) throws CDRException  {
		if( this.msisdn != null ) { this.msisdn.setMsisdnStrategyFixed( value ); }
	}

	public void setDateStrategyFixed( final Calendar date ) throws CDRException  {
		if( this.date != null ) { this.date.setDateStrategyFixed( date ); }
	}

	public void cleanDateStrategyIncrement()  {
		if( this.date != null ) { this.date.cleanDateStrategyIncrement() ; }
	}

	public void cleanDateStrategyRandom()  {
		if( this.date != null ) { this.date.cleanDateStrategyRandom() ; }
	}

	public void setDateFormat( String format ) throws CDRException  {
		if( this.date != null ) { this.date.setDateFormat( format ); }
	}

	public void setDateStrategyIncrement( final Calendar date, final CSVDateIncrement increment ) throws CDRException  {
		if( this.date != null ) { this.date.setDateStrategyIncrement( date, increment ); }
	}

	public void setDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws CDRException  {
		if( this.date != null ) { this.date.setDateStrategyRandom( date_left, date_right ); }
	}

	public void cleanDateStrategyFixed()  {
		if( this.date != null ) { this.date.cleanDateStrategyFixed() ; }
	}

	@Date( position = 1 )
	public String getDate()  {
		return this.date.getDate() ;
	}

	public void setAmountStrategyFixed( final Long value ) throws CDRException  {
		if( this.amount != null ) { this.amount.setLongStrategyFixed( value ); }
	}

	public void cleanAmountStrategyIncrement()  {
		if( this.amount != null ) { this.amount.cleanLongStrategyIncrement() ; }
	}

	public void cleanAmountStrategyRandom()  {
		if( this.amount != null ) { this.amount.cleanLongStrategyRandom() ; }
	}

	public void setAmountStrategyIncrement( final Long value, final Integer increment ) throws CDRException  {
		if( this.amount != null ) { this.amount.setLongStrategyIncrement( value, increment ); }
	}

	public void setAmountStrategyRandom( final Long min_value, final Long max_value ) throws CDRException  {
		if( this.amount != null ) { this.amount.setLongStrategyRandom( min_value, max_value ); }
	}

	public void cleanAmountStrategyFixed()  {
		if( this.amount != null ) { this.amount.cleanLongStrategyFixed() ; }
	}

	@Amount( position = 2 )
	public String getAmount() throws CDRException  {
		return this.amount.getLong();
	}

	public void setDownloadStrategyFixed( final Long value ) throws CDRException  {
		if( this.download != null ) { this.download.setLongStrategyFixed( value ); }
	}

	public void cleanDownloadStrategyIncrement()  {
		if( this.download != null ) { this.download.cleanLongStrategyIncrement() ; }
	}

	public void cleanDownloadStrategyRandom()  {
		if( this.download != null ) { this.download.cleanLongStrategyRandom() ; }
	}

	public void setDownloadStrategyIncrement( final Long value, final Integer increment ) throws CDRException  {
		if( this.download != null ) { this.download.setLongStrategyIncrement( value, increment ); }
	}

	public void setDownloadStrategyRandom( final Long min_value, final Long max_value ) throws CDRException  {
		if( this.download != null ) { this.download.setLongStrategyRandom( min_value, max_value ); }
	}

	public void cleanDownloadStrategyFixed()  {
		if( this.download != null ) { this.download.cleanLongStrategyFixed() ; }
	}

	@Download( position = 3 )
	public String getDownload() throws CDRException  {
		return this.download.getLong();
	}

	public void setUploadStrategyFixed( final Long value ) throws CDRException  {
		if( this.upload != null ) { this.upload.setLongStrategyFixed( value ); }
	}

	public void cleanUploadStrategyIncrement()  {
		if( this.upload != null ) { this.upload.cleanLongStrategyIncrement() ; }
	}

	public void cleanUploadStrategyRandom()  {
		if( this.upload != null ) { this.upload.cleanLongStrategyRandom() ; }
	}

	public void setUploadStrategyIncrement( final Long value, final Integer increment ) throws CDRException  {
		if( this.upload != null ) { this.upload.setLongStrategyIncrement( value, increment ); }
	}

	public void setUploadStrategyRandom( final Long min_value, final Long max_value ) throws CDRException  {
		if( this.upload != null ) { this.upload.setLongStrategyRandom( min_value, max_value ); }
	}

	public void cleanUploadStrategyFixed()  {
		if( this.upload != null ) { this.upload.cleanLongStrategyFixed() ; }
	}

	@Upload( position = 4 )
	public String getUpload() throws CDRException  {
		return this.upload.getLong();
	}

	public void setBalanceStrategyFixed( final Long value ) throws CDRException  {
		if( this.balance != null ) { this.balance.setLongStrategyFixed( value ); }
	}

	public void cleanBalanceStrategyIncrement()  {
		if( this.balance != null ) { this.balance.cleanLongStrategyIncrement() ; }
	}

	public void cleanBalanceStrategyRandom()  {
		if( this.balance != null ) { this.balance.cleanLongStrategyRandom() ; }
	}

	public void setBalanceStrategyIncrement( final Long value, final Integer increment ) throws CDRException  {
		if( this.balance != null ) { this.balance.setLongStrategyIncrement( value, increment ); }
	}

	public void setBalanceStrategyRandom( final Long min_value, final Long max_value ) throws CDRException  {
		if( this.balance != null ) { this.balance.setLongStrategyRandom( min_value, max_value ); }
	}

	public void cleanBalanceStrategyFixed()  {
		if( this.balance != null ) { this.balance.cleanLongStrategyFixed() ; }
	}

	@Balance( position = 5 )
	public String getBalance() throws CDRException  {
		return this.balance.getLong();
	}

}