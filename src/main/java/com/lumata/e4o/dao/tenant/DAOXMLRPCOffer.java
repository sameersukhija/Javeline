package com.lumata.e4o.dao.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.database.Mysql;
import com.lumata.e4o.schema.xmlrpc.XMLRPCOffer;


public class DAOXMLRPCOffer extends DAO {

	private static final Logger logger = LoggerFactory.getLogger( DAOXMLRPCOffer.class );
	
	SimpleDateFormat sdf;
	
	public DAOXMLRPCOffer( Mysql mysql ) {
		super( mysql );
		sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
	}
	
	public static DAOXMLRPCOffer getInstance( Mysql mysql ) {
		return new DAOXMLRPCOffer( mysql );
	}
	
	private ArrayList<XMLRPCOffer> getXMLRPCOfferList( String query ) {
		
		ResultSet rs = this.getMysql().execQuery( query );
		
		ArrayList<XMLRPCOffer> xmlrpcOfferList = new ArrayList<XMLRPCOffer>();
		
		try {
			
			while( rs.next() ) {
				
				XMLRPCOffer xmlrpcOffer = new XMLRPCOffer( rs );
	
				xmlrpcOfferList.add( xmlrpcOffer );
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return xmlrpcOfferList;
		
	}
	
	public ArrayList<XMLRPCOffer> getXMLRPCOfferList( String msisdn, String token ) {
		
		return getXMLRPCOfferList( XMLRPCOffer.query( msisdn, token ) );		
		
	}
	
}
