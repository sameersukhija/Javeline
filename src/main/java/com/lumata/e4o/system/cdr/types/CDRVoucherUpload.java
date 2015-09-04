package com.lumata.e4o.system.cdr.types;

import com.lumata.e4o.system.cdr.CDR;
import com.lumata.e4o.system.cdr.fields.*;
import com.lumata.e4o.exceptions.FieldException;
import java.util.Calendar;
import com.lumata.e4o.system.fields.FieldDateIncrement;
import com.lumata.e4o.system.fields.IFieldEnum;

public class CDRVoucherUpload extends CDR { 

	private final int FIELDS = 5;

	public CDRVoucherUpload() {
		super();
	} 

	public int getFieldsCount() {
		return this.FIELDS;
	}

	@Code( position = 0 )
	public String getCode() throws FieldException  {
		return this.code.getString();
	}

	public void setCodeLength( final Integer length ) throws FieldException  {
		if( this.code != null ) { this.code.setStringLength( length ); }
	}

	public void setCodeStrategyFixed( final String value ) throws FieldException  {
		if( this.code != null ) { this.code.setStringStrategyFixed( value ); }
	}

	public void cleanCodeStrategyIncrement()  {
		if( this.code != null ) { this.code.cleanStringStrategyIncrement(); }
	}

	public void cleanCodeStrategyRandom()  {
		if( this.code != null ) { this.code.cleanStringStrategyRandom(); }
	}

	public void setCodeStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.code != null ) { this.code.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setCodeStrategyRandom( final Integer length ) throws FieldException  {
		if( this.code != null ) { this.code.setStringStrategyRandom( length ); }
	}

	public void cleanCode()  {
		if( this.code != null ) { this.code.cleanString(); }
	}

	public void cleanCodeLength()  {
		if( this.code != null ) { this.code.cleanStringLength(); }
	}

	@PartnerName( position = 1 )
	public String getPartnerName() throws FieldException  {
		return this.partnerName.getString();
	}

	public void setPartnerNameLength( final Integer length ) throws FieldException  {
		if( this.partnerName != null ) { this.partnerName.setStringLength( length ); }
	}

	public void setPartnerNameStrategyFixed( final String value ) throws FieldException  {
		if( this.partnerName != null ) { this.partnerName.setStringStrategyFixed( value ); }
	}

	public void cleanPartnerNameStrategyIncrement()  {
		if( this.partnerName != null ) { this.partnerName.cleanStringStrategyIncrement(); }
	}

	public void cleanPartnerNameStrategyRandom()  {
		if( this.partnerName != null ) { this.partnerName.cleanStringStrategyRandom(); }
	}

	public void setPartnerNameStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.partnerName != null ) { this.partnerName.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setPartnerNameStrategyRandom( final Integer length ) throws FieldException  {
		if( this.partnerName != null ) { this.partnerName.setStringStrategyRandom( length ); }
	}

	public void cleanPartnerName()  {
		if( this.partnerName != null ) { this.partnerName.cleanString(); }
	}

	public void cleanPartnerNameLength()  {
		if( this.partnerName != null ) { this.partnerName.cleanStringLength(); }
	}

	@OfferName( position = 2 )
	public String getOfferName() throws FieldException  {
		return this.offerName.getString();
	}

	public void setOfferNameLength( final Integer length ) throws FieldException  {
		if( this.offerName != null ) { this.offerName.setStringLength( length ); }
	}

	public void setOfferNameStrategyFixed( final String value ) throws FieldException  {
		if( this.offerName != null ) { this.offerName.setStringStrategyFixed( value ); }
	}

	public void cleanOfferNameStrategyIncrement()  {
		if( this.offerName != null ) { this.offerName.cleanStringStrategyIncrement(); }
	}

	public void cleanOfferNameStrategyRandom()  {
		if( this.offerName != null ) { this.offerName.cleanStringStrategyRandom(); }
	}

	public void setOfferNameStrategyIncrement( final String value, final Integer start_value, final Integer increment ) throws FieldException  {
		if( this.offerName != null ) { this.offerName.setStringStrategyIncrement( value, start_value, increment ); }
	}

	public void setOfferNameStrategyRandom( final Integer length ) throws FieldException  {
		if( this.offerName != null ) { this.offerName.setStringStrategyRandom( length ); }
	}

	public void cleanOfferName()  {
		if( this.offerName != null ) { this.offerName.cleanString(); }
	}

	public void cleanOfferNameLength()  {
		if( this.offerName != null ) { this.offerName.cleanStringLength(); }
	}

	@ExpiryDate( position = 3 )
	public String getExpiryDate()  {
		return this.expiryDate.getDate();
	}

	public void setExpiryDateStrategyFixed( final Calendar date ) throws FieldException  {
		if( this.expiryDate != null ) { this.expiryDate.setDateStrategyFixed( date ); }
	}

	public void cleanExpiryDateStrategyIncrement()  {
		if( this.expiryDate != null ) { this.expiryDate.cleanDateStrategyIncrement(); }
	}

	public void cleanExpiryDateStrategyRandom()  {
		if( this.expiryDate != null ) { this.expiryDate.cleanDateStrategyRandom(); }
	}

	public void setExpiryDateFormat( String format ) throws FieldException  {
		if( this.expiryDate != null ) { this.expiryDate.setDateFormat( format ); }
	}

	public void setExpiryDateStrategyIncrement( final Calendar date, final FieldDateIncrement increment ) throws FieldException  {
		if( this.expiryDate != null ) { this.expiryDate.setDateStrategyIncrement( date, increment ); }
	}

	public void setExpiryDateStrategyRandom( final Calendar date_left, final Calendar date_right ) throws FieldException  {
		if( this.expiryDate != null ) { this.expiryDate.setDateStrategyRandom( date_left, date_right ); }
	}

	public void cleanExpiryDateStrategyFixed()  {
		if( this.expiryDate != null ) { this.expiryDate.cleanDateStrategyFixed(); }
	}

	@Format( position = 4 )
	public String getFormat() throws FieldException  {
		return this.format.getEnum();
	}

	public void setFormatStrategyFixed( final Enum<? extends IFieldEnum> value ) throws FieldException  {
		if( this.format != null ) { this.format.setEnumStrategyFixed( value ); }
	}

	public void cleanFormatStrategyIncrement()  {
		if( this.format != null ) { this.format.cleanEnumStrategyIncrement(); }
	}

	public void cleanFormatStrategyRandom()  {
		if( this.format != null ) { this.format.cleanEnumStrategyRandom(); }
	}

	public void setFormatStrategyIncrement( final Enum<? extends IFieldEnum> value, final Integer increment ) throws FieldException  {
		if( this.format != null ) { this.format.setEnumStrategyIncrement( value, increment ); }
	}

	public void setFormatStrategyRandom() throws FieldException  {
		if( this.format != null ) { this.format.setEnumStrategyRandom(); }
	}

	public void cleanFormatStrategyFixed()  {
		if( this.format != null ) { this.format.cleanEnumStrategyFixed(); }
	}

}