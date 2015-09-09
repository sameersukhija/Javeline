package com.lumata.e4o.webservices.response.types;

import java.util.Date;

import org.hamcrest.Matcher;

import com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseObject;
import com.lumata.e4o.webservices.xmlrpc.response.XMLRPCResponseValidator;

public class XMLRPCResponseOffer extends XMLRPCResponseObject {
		
	private final String ID_TAG_ = ".id";
	private final String NAME_TAG_ = ".name";
	private final String DESCRIPTION_TAG_ = ".description";
	private final String IMAGE_URL_TAG_ = ".image_url";
	private final String TERM_OF_CONDITION_TAG_ = ".term_of_condition";
	private final String STATUS_TAG_ = ".status";
	private final String ALLOCATED_DATE_TAG_ = ".allocated_date";
	private final String RELEASED_DATE_TAG_ = ".released_date";
	private final String ACCEPTED_DATE_TAG_ = ".accepted_date";
	private final String REFUSED_DATE_TAG_ = ".refused_date";
	private final String DELIVERED_DATE_TAG_ = ".delivered_date";
	private final String IS_VOUCHER_TAG_ = ".is_voucher";
	private final String START_DATE_TAG_ = ".start_date";
	private final String END_DATE_TAG_ = ".end_date";
	private final String CATEGORY_TAG_ = ".category";
	private final String RESERVED_QUANTITY_TAG_ = ".reserved_quantity";
	private final String CONTENTS_TAG_ = ".contents";
			
	public XMLRPCResponseOffer() {}
	
	public XMLRPCResponseOffer( String relativeRoot ) {
		super( relativeRoot + ".offer" );
	}
	
	public XMLRPCResponseValidator id( Matcher<Short> matcher ) {
		
		return new XMLRPCResponseValidator( buildPath( ID_TAG_ ), matcher, Short.class, "offer id" );		
		
	}

	public XMLRPCResponseValidator name( Matcher<String> matcher ) {
		
		return new XMLRPCResponseValidator( buildPath( NAME_TAG_ ), matcher, String.class, "offer name" );		
		
	}
	
	public XMLRPCResponseValidator description( Matcher<String> matcher ) {
		
		return new XMLRPCResponseValidator( buildPath( DESCRIPTION_TAG_ ), matcher, String.class, "offer description" );		
		
	}
	
	public XMLRPCResponseValidator imageUrl( Matcher<String> matcher ) {
		
		return new XMLRPCResponseValidator( buildPath( IMAGE_URL_TAG_ ), matcher, String.class, "offer image url" );		
		
	}

	public XMLRPCResponseValidator termOfCondition( Matcher<String> matcher ) {
		
		return new XMLRPCResponseValidator( buildPath( TERM_OF_CONDITION_TAG_ ), matcher, String.class, "offer term of condition" );		
		
	}
	
	public XMLRPCResponseValidator status( Matcher<String> matcher ) {
		
		return new XMLRPCResponseValidator( buildPath( STATUS_TAG_ ), matcher, String.class, "offer status" );		
		
	}
	
	public XMLRPCResponseValidator allocatedDate( Matcher<Date> matcher ) {
		
		return new XMLRPCResponseValidator( buildPath( ALLOCATED_DATE_TAG_ ), matcher, Date.class, "offer allocated date" );		
		
	}
	
	public XMLRPCResponseValidator releasedDate( Matcher<Date> matcher ) {
		
		return new XMLRPCResponseValidator( buildPath( RELEASED_DATE_TAG_ ), matcher, Date.class, "offer released date" );		
		
	}
	
	public XMLRPCResponseValidator acceptedDate( Matcher<Date> matcher ) {
		
		return new XMLRPCResponseValidator( buildPath( ACCEPTED_DATE_TAG_ ), matcher, Date.class, "offer accepted date" );		
		
	}
	
	public XMLRPCResponseValidator refusedDate( Matcher<Date> matcher ) {
		
		return new XMLRPCResponseValidator( buildPath( REFUSED_DATE_TAG_ ), matcher, Date.class, "offer refused date" );		
		
	}

	public XMLRPCResponseValidator deliveredDate( Matcher<Date> matcher ) {
		
		return new XMLRPCResponseValidator( buildPath( DELIVERED_DATE_TAG_ ), matcher, Date.class, "offer delivered date" );		
		
	}

	public XMLRPCResponseValidator isVoucher( Matcher<Boolean> matcher ) {
		
		return new XMLRPCResponseValidator( buildPath( IS_VOUCHER_TAG_ ), matcher, Boolean.class, "offer is voucher" );		
		
	}

	public XMLRPCResponseVoucher voucher( Matcher<String> matcher ) {
		
		return new XMLRPCResponseVoucher( relativeRoot );		
		
	}
	
	public XMLRPCResponseValidator startDate( Matcher<Date> matcher ) {
		
		return new XMLRPCResponseValidator( buildPath( START_DATE_TAG_ ), matcher, Date.class, "offer start date" );		
		
	}

	public XMLRPCResponseValidator endDate( Matcher<Date> matcher ) {
		
		return new XMLRPCResponseValidator( buildPath( END_DATE_TAG_ ), matcher, Date.class, "offer end date" );		
		
	}
	
	public XMLRPCResponseValidator category( Matcher<String> matcher ) {
		
		return new XMLRPCResponseValidator( buildPath( CATEGORY_TAG_ ), matcher, String.class, "offer category" );		
		
	}
	
	public XMLRPCResponsePrice price( Matcher<String> matcher ) {
		
		return new XMLRPCResponsePrice( relativeRoot );		
		
	}
	
	public XMLRPCResponseValidator reservedQuantity( Matcher<Integer> matcher ) {
		
		return new XMLRPCResponseValidator( buildPath( RESERVED_QUANTITY_TAG_ ), matcher, Integer.class, "offer reserved quantity" );		
		
	}
	
	public XMLRPCResponseValidator contents( Matcher<Date> matcher ) {
		
		return new XMLRPCResponseValidator( buildPath( CONTENTS_TAG_ ), matcher, String.class, "offer contents" );		
		
	}
	
}
