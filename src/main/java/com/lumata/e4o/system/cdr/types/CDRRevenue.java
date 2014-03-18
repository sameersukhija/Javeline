package com.lumata.e4o.system.cdr.types;

import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.annotations.*;
import com.lumata.expression.operators.exceptions.CDRException;
import java.util.Calendar;
import com.lumata.e4o.system.csv.types.CSVDateIncrement;
import com.lumata.e4o.system.csv.types.ICSVEnum;

public class CDRRevenue extends CDR { 

	private final int FIELDS = 8;

	public CDRRevenue() {
		super();
	} 

	public int getFieldsCount() {
		return this.FIELDS;
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

	public void setMsisdnOptions( final Integer prefix, final Integer length ) throws CDRException  {
		super.setMsisdnOptions( prefix, length );
	}

	@Msisdn( position = 0 )
	public String getMsisdn() throws CDRException  {
		return super.getMsisdn();
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

	public void setDateStrategyFixed( final Calendar date ) throws CDRException  {
		super.setDateStrategyFixed( date );
	}

	public void setDateStrategyIncrement( final Calendar date, final CSVDateIncrement increment ) throws CDRException  {
		super.setDateStrategyIncrement( date, increment );
	}

	public void setDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws CDRException  {
		super.setDateStrategyRandom( date_left, date_right );
	}

	public void setDateFormat( String format ) throws CDRException  {
		super.setDateFormat( format );
	}

	public void cleanDateStrategyIncrement()  {
		super.cleanDateStrategyIncrement() ;
	}

	public void cleanDateStrategyRandom()  {
		super.cleanDateStrategyRandom() ;
	}

	public void cleanDateStrategyFixed()  {
		super.cleanDateStrategyFixed() ;
	}

	@Date( position = 1 )
	public String getDate()  {
		return super.getDate() ;
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

	@Amount( position = 2 )
	public String getAmount() throws CDRException  {
		return super.getAmount();
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

	public void setBalanceStrategyFixed( final Long current_value ) throws CDRException  {
		super.setBalanceStrategyFixed( current_value );
	}

	public void setBalanceStrategyIncrement( final Long current_value, final Integer increment ) throws CDRException  {
		super.setBalanceStrategyIncrement( current_value, increment );
	}

	public void setBalanceStrategyRandom( final Long long_left, final Long long_right ) throws CDRException  {
		super.setBalanceStrategyRandom( long_left, long_right );
	}

	@Balance( position = 3 )
	public String getBalance() throws CDRException  {
		return super.getBalance();
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

	@ValidityDate( position = 4 )
	public String getValidityDate()  {
		return super.getValidityDate() ;
	}

	public void setValidityDateStrategyFixed( final Calendar date ) throws CDRException  {
		super.setValidityDateStrategyFixed( date );
	}

	public void setValidityDateFormat( String format ) throws CDRException  {
		super.setValidityDateFormat( format );
	}

	public void setValidityDateStrategyIncrement( final Calendar date, final CSVDateIncrement increment ) throws CDRException  {
		super.setValidityDateStrategyIncrement( date, increment );
	}

	public void setValidityDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws CDRException  {
		super.setValidityDateStrategyRandom( date_left, date_right );
	}

	public void cleanValidityDateStrategyFixed()  {
		super.cleanValidityDateStrategyFixed() ;
	}

	public void cleanValidityDateStrategyIncrement()  {
		super.cleanValidityDateStrategyIncrement() ;
	}

	public void cleanValidityDateStrategyRandom()  {
		super.cleanValidityDateStrategyRandom() ;
	}

	@DeactivationDate( position = 5 )
	public String getDeactivationDate()  {
		return super.getDeactivationDate() ;
	}

	public void setDeactivationDateStrategyFixed( final Calendar date ) throws CDRException  {
		super.setDeactivationDateStrategyFixed( date );
	}

	public void setDeactivationDateFormat( String format ) throws CDRException  {
		super.setDeactivationDateFormat( format );
	}

	public void setDeactivationDateStrategyIncrement( final Calendar date, final CSVDateIncrement increment ) throws CDRException  {
		super.setDeactivationDateStrategyIncrement( date, increment );
	}

	public void setDeactivationDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws CDRException  {
		super.setDeactivationDateStrategyRandom( date_left, date_right );
	}

	public void cleanDeactivationDateStrategyFixed()  {
		super.cleanDeactivationDateStrategyFixed() ;
	}

	public void cleanDeactivationDateStrategyIncrement()  {
		super.cleanDeactivationDateStrategyIncrement() ;
	}

	public void cleanDeactivationDateStrategyRandom()  {
		super.cleanDeactivationDateStrategyRandom() ;
	}

	public void setTypeStrategyFixed( final Enum<? extends ICSVEnum> current_value ) throws CDRException  {
		super.setTypeStrategyFixed( current_value );
	}

	public void setTypeStrategyIncrement( final Enum<? extends ICSVEnum> current_value, final Integer increment ) throws CDRException  {
		super.setTypeStrategyIncrement( current_value, increment );
	}

	public void setTypeStrategyRandom() throws CDRException  {
		super.setTypeStrategyRandom();
	}

	public void cleanTypeStrategyFixed()  {
		super.cleanTypeStrategyFixed() ;
	}

	public void cleanTypeStrategyIncrement()  {
		super.cleanTypeStrategyIncrement() ;
	}

	public void cleanTypeStrategyRandom()  {
		super.cleanTypeStrategyRandom() ;
	}

	@Type( position = 6 )
	public String getType() throws CDRException  {
		return super.getType();
	}

	public void setDelayStrategyFixed( final Long current_value ) throws CDRException  {
		super.setDelayStrategyFixed( current_value );
	}

	public void setDelayStrategyIncrement( final Long current_value, final Integer increment ) throws CDRException  {
		super.setDelayStrategyIncrement( current_value, increment );
	}

	public void setDelayStrategyRandom( final Long long_left, final Long long_right ) throws CDRException  {
		super.setDelayStrategyRandom( long_left, long_right );
	}

	@Delay( position = 7 )
	public String getDelay() throws CDRException  {
		return super.getDelay();
	}

	public void cleanDelayStrategyFixed()  {
		super.cleanDelayStrategyFixed() ;
	}

	public void cleanDelayStrategyIncrement()  {
		super.cleanDelayStrategyIncrement() ;
	}

	public void cleanDelayStrategyRandom()  {
		super.cleanDelayStrategyRandom() ;
	}


 }