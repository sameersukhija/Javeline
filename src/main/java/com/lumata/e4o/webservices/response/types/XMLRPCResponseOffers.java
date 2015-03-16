package com.lumata.e4o.webservices.response.types;

import java.util.ArrayList;
import java.util.Date;

import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.*;

import com.lumata.e4o.schema.xmlrpc.XMLRPCOffer;
import com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseObject;
import com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidator;

public class XMLRPCResponseOffers extends XMLRPCResponseObject {
	
	private final String SIZE_TAG_ = ".offer.size()";
		
	public XMLRPCResponseOffers() {
		super( ".offers" );
	}

	public void offer() {
				
		//".offer.findAll {}"
		
	}
	
	public XMLRPCResponseValidator size( Matcher<Integer> matcher ) {
		
		return new XMLRPCResponseValidator( builPath( SIZE_TAG_ ), matcher, Integer.class, "offers size" );
		
	}
	
	public XMLRPCResponseOffer offer( Integer index ) {
		
		return new XMLRPCResponseOffer( this.relativeRoot );
		
	}

	public XMLRPCResponseValidator[] validate( ArrayList<XMLRPCOffer> offers ) {
		
		ArrayList<XMLRPCResponseValidator> offersValidatorArray = new ArrayList<XMLRPCResponseValidator>();
		
		offersValidatorArray.add( new XMLRPCResponseValidator(  builPath( SIZE_TAG_ ), equalTo( offers.size() ), Integer.class, "offers size"  ) );
		
		for( int offerIndex = 0; offerIndex < offers.size(); offerIndex++ ) {
			
			offersValidatorArray.add( this.offer( offerIndex + 1 ).id( equalTo( offers.get( offerIndex ).getOfferId() ) ) );
			offersValidatorArray.add( this.offer( offerIndex + 1 ).name( equalTo( offers.get( offerIndex ).getOfferName() ) ) );
			offersValidatorArray.add( this.offer( offerIndex + 1 ).description( equalTo( offers.get( offerIndex ).getDescription() ) ) );
			offersValidatorArray.add( this.offer( offerIndex + 1 ).imageUrl( equalTo( offers.get( offerIndex ).getUrlImage() ) ) );
			offersValidatorArray.add( this.offer( offerIndex + 1 ).termOfCondition( equalTo( offers.get( offerIndex ).getAgreement() ) ) );
			offersValidatorArray.add( this.offer( offerIndex + 1 ).startDate( equalTo( offers.get( offerIndex ).getStartDate() ) ) );
			offersValidatorArray.add( this.offer( offerIndex + 1 ).endDate( equalTo( offers.get( offerIndex ).getEndDate() ) ) );
						
		} 
		
		/*
		private String token_code;
		private Long customer_id;
		private Date allocation_date;
		private String offer_status;
		private Date decision_date;
		private Boolean has_voucher;
		*/
		XMLRPCResponseValidator[] offersValidator = new XMLRPCResponseValidator[ offersValidatorArray.size() ];
		
		offersValidator = offersValidatorArray.toArray(offersValidator);
				
		return offersValidator;
		
	}

}
