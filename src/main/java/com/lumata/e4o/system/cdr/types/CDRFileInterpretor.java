package com.lumata.e4o.system.cdr.types;

import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.fields.*;
import com.lumata.e4o.exceptions.FieldException;

public class CDRFileInterpretor extends CDR { 

	private final int FIELDS = 1;

	public CDRFileInterpretor() {
		super();
	} 

	public int getFieldsCount() {
		return this.FIELDS;
	}

	@Text( position = 0 )
	public String getText() throws FieldException  {
		return this.text.getString();
	}

	public void setTextLength( final Integer length ) throws FieldException  {
		if( this.text != null ) { this.text.setStringLength( length ); }
	}

	public void setTextStrategyFixed( final String value ) throws FieldException  {
		if( this.text != null ) { this.text.setStringStrategyFixed( value ); }
	}

	public void cleanTextStrategyIncrement()  {
		if( this.text != null ) { this.text.cleanStringStrategyIncrement(); }
	}

	public void cleanTextStrategyRandom()  {
		if( this.text != null ) { this.text.cleanStringStrategyRandom(); }
	}

	public void setTextStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.text != null ) { this.text.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setTextStrategyRandom( final Integer length ) throws FieldException  {
		if( this.text != null ) { this.text.setStringStrategyRandom( length ); }
	}

	public void cleanText()  {
		if( this.text != null ) { this.text.cleanString(); }
	}

	public void cleanTextLength()  {
		if( this.text != null ) { this.text.cleanStringLength(); }
	}

}