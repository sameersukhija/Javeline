package com.lumata.expression.operators.system.cdr.types;

import com.lumata.expression.operators.system.cdr.CDR;
import com.lumata.expression.operators.system.cdr.annotations.*;
import com.lumata.expression.operators.exceptions.CDRException;
import java.util.Calendar;
import com.lumata.expression.operators.system.cdr.CDRDateIncrement;

public class CDRRevenue extends CDR { 

	private final int FIELDS = 4;

	public CDRRevenue() {
		super();
	} 

	public int getFiledsCount() {
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
	public String getMsisdn()  {
		return super.getMsisdn() ;
	}

	public void cleanMsisdnStrategyIncrement()  {
		super.cleanMsisdnStrategyIncrement() ;
	}

	public void cleanMsisdnStrategyRandom()  {
		super.cleanMsisdnStrategyRandom() ;
	}

	public void cleanMsisdn()  {
		super.cleanMsisdn() ;
	}

	public void cleanMsisdnOptions()  {
		super.cleanMsisdnOptions() ;
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

	public void setDateStrategyIncrement( final Calendar date, final CDRDateIncrement increment ) throws CDRException  {
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


 }