package com.lumata.e4o.system.cdr.types;

import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.annotations.*;
import com.lumata.e4o.system.csv.types.CSVDateIncrement;
import com.lumata.e4o.system.csv.types.ICSVEnum;
import com.lumata.expression.operators.exceptions.CDRException;

import java.util.Calendar;

public class CDRCallMultitenant extends CDR { 

	private final int FIELDS = 7;

	public CDRCallMultitenant() {
		super();
	} 

	public int getFieldsCount() {
		return this.FIELDS;
	}

	@Msisdn( position = 0 )
	public String getMsisdn() throws CDRException  {
		return super.getMsisdn();
	}

	public void setMsisdnOptions( final Integer prefix, final Integer length ) throws CDRException  {
		super.setMsisdnOptions( prefix, length );
	}

	public void setMsisdnStrategyFixed( final Long value ) throws CDRException  {
		super.setMsisdnStrategyFixed( value );
	}

	public void setMsisdnStrategyIncrement( final Long value, final Integer increment ) throws CDRException  {
		super.setMsisdnStrategyIncrement( value, increment );
	}

	public void setMsisdnStrategyRandom( final Long min_value, final Long max_value ) throws CDRException  {
		super.setMsisdnStrategyRandom( min_value, max_value );
	}

	public void cleanMsisdn()  {
		super.cleanMsisdn() ;
	}

	public void cleanMsisdnOptions()  {
		super.cleanMsisdnOptions() ;
	}

	public void cleanMsisdnStrategyIncrement()  {
		super.cleanMsisdnStrategyIncrement() ;
	}

	public void cleanMsisdnStrategyRandom()  {
		super.cleanMsisdnStrategyRandom() ;
	}

	@Date( position = 1 )
	public String getDate()  {
		return super.getDate() ;
	}

	public void setDateStrategyFixed( final Calendar date ) throws CDRException  {
		super.setDateStrategyFixed( date );
	}

	public void setDateFormat( String format ) throws CDRException  {
		super.setDateFormat( format );
	}

	public void setDateStrategyIncrement( final Calendar date, final CSVDateIncrement increment ) throws CDRException  {
		super.setDateStrategyIncrement( date, increment );
	}

	public void setDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws CDRException  {
		super.setDateStrategyRandom( date_left, date_right );
	}

	public void cleanDateStrategyFixed()  {
		super.cleanDateStrategyFixed() ;
	}

	public void cleanDateStrategyIncrement()  {
		super.cleanDateStrategyIncrement() ;
	}

	public void cleanDateStrategyRandom()  {
		super.cleanDateStrategyRandom() ;
	}

	@Duration( position = 2 )
	public String getDuration() throws CDRException  {
		return super.getDuration();
	}

	public void setDurationStrategyFixed( final Long current_value ) throws CDRException  {
		super.setDurationStrategyFixed( current_value );
	}

	public void setDurationStrategyIncrement( final Long current_value, final Integer increment ) throws CDRException  {
		super.setDurationStrategyIncrement( current_value, increment );
	}

	public void setDurationStrategyRandom( final Long long_left, final Long long_right ) throws CDRException  {
		super.setDurationStrategyRandom( long_left, long_right );
	}

	public void cleanDurationStrategyFixed()  {
		super.cleanDurationStrategyFixed() ;
	}

	public void cleanDurationStrategyIncrement()  {
		super.cleanDurationStrategyIncrement() ;
	}

	public void cleanDurationStrategyRandom()  {
		super.cleanDurationStrategyRandom() ;
	}

	@Amount( position = 3 )
	public String getAmount() throws CDRException  {
		return super.getAmount();
	}

	public void setAmountStrategyFixed( final Long current_value ) throws CDRException  {
		super.setAmountStrategyFixed( current_value );
	}

	public void setAmountStrategyIncrement( final Long current_value, final Integer increment ) throws CDRException  {
		super.setAmountStrategyIncrement( current_value, increment );
	}

	public void setAmountStrategyRandom( final Long long_left, final Long long_right ) throws CDRException  {
		super.setAmountStrategyRandom( long_left, long_right );
	}

	public void cleanAmountStrategyFixed()  {
		super.cleanAmountStrategyFixed() ;
	}

	public void cleanAmountStrategyIncrement()  {
		super.cleanAmountStrategyIncrement() ;
	}

	public void cleanAmountStrategyRandom()  {
		super.cleanAmountStrategyRandom() ;
	}

	@Balance( position = 4 )
	public String getBalance() throws CDRException  {
		return super.getBalance();
	}

	public void setBalanceStrategyFixed( final Long current_value ) throws CDRException  {
		super.setBalanceStrategyFixed( current_value );
	}

	public void setBalanceStrategyIncrement( final Long current_value, final Integer increment ) throws CDRException  {
		super.setBalanceStrategyIncrement( current_value, increment );
	}

	public void setBalanceStrategyRandom( final Long long_left, final Long long_right ) throws CDRException  {
		super.setBalanceStrategyRandom( long_left, long_right );
	}

	public void cleanBalanceStrategyFixed()  {
		super.cleanBalanceStrategyFixed() ;
	}

	public void cleanBalanceStrategyIncrement()  {
		super.cleanBalanceStrategyIncrement() ;
	}

	public void cleanBalanceStrategyRandom()  {
		super.cleanBalanceStrategyRandom() ;
	}

	@Terminating( position = 5 )
	public String getTerminating() throws CDRException  {
		return super.getTerminating();
	}

	public void setTerminatingStrategyFixed( final Enum<? extends ICSVEnum> current_value ) throws CDRException  {
		super.setTerminatingStrategyFixed( current_value );
	}

	public void setTerminatingStrategyIncrement( final Enum<? extends ICSVEnum> current_value, final Integer increment ) throws CDRException  {
		super.setTerminatingStrategyIncrement( current_value, increment );
	}

	public void setTerminatingStrategyRandom() throws CDRException  {
		super.setTerminatingStrategyRandom();
	}

	public void cleanTerminatingStrategyFixed()  {
		super.cleanTerminatingStrategyFixed() ;
	}

	public void cleanTerminatingStrategyIncrement()  {
		super.cleanTerminatingStrategyIncrement() ;
	}

	public void cleanTerminatingStrategyRandom()  {
		super.cleanTerminatingStrategyRandom() ;
	}

	@TenantId( position = 6 )
	public String getTenantId() throws CDRException  {
		return super.getTenantId();
	}

	public void setTenantIdStrategyFixed( final Long current_value ) throws CDRException  {
		super.setTenantIdStrategyFixed( current_value );
	}

	public void setTenantIdStrategyIncrement( final Long current_value, final Integer increment ) throws CDRException  {
		super.setTenantIdStrategyIncrement( current_value, increment );
	}

	public void setTenantIdStrategyRandom( final Long long_left, final Long long_right ) throws CDRException  {
		super.setTenantIdStrategyRandom( long_left, long_right );
	}

	public void cleanTenantIdStrategyFixed()  {
		super.cleanTenantIdStrategyFixed() ;
	}

	public void cleanTenantIdStrategyIncrement()  {
		super.cleanTenantIdStrategyIncrement() ;
	}

	public void cleanTenantIdStrategyRandom()  {
		super.cleanTenantIdStrategyRandom() ;
	}


 }