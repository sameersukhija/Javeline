/*
+------------------------+-------------+------+-----+---------+-------+
| Field                  | Type        | Null | Key | Default | Extra |
+------------------------+-------------+------+-----+---------+-------+
| product_type_id        | smallint(5) | NO   | PRI | NULL    |       |
| characteristic_name    | varchar(45) | NO   | PRI | NULL    |       |
| characteristic_details | blob        | YES  |     | NULL    |       |
+------------------------+-------------+------+-----+---------+-------+
*/

package com.lumata.expression.operators.dao.catalog;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatalogProductTypeCharacteristicsDAO {

	private static final Logger logger = LoggerFactory.getLogger(CatalogProductTypeCharacteristicsDAO.class);
	
	public enum Fields { product_type_id, characteristic_name, characteristic_details }
	
	private int product_type_id;
	private String characteristic_name;
	private String characteristic_details;

	public CatalogProductTypeCharacteristicsDAO() {
		
		this.set( -1, "", "" );
		
	}
	
	public CatalogProductTypeCharacteristicsDAO( int product_type_id, String characteristic_name, String characteristic_details ) {
		
		this.set( product_type_id, characteristic_name, characteristic_details );
					
	}
	
	public CatalogProductTypeCharacteristicsDAO( ResultSet rs ) {
		
		try {
					
			this.set( rs.getInt( CatalogProductTypeCharacteristicsDAO.Fields.product_type_id.toString() ), rs.getString( CatalogProductTypeCharacteristicsDAO.Fields.characteristic_name.toString() ), rs.getString( CatalogProductTypeCharacteristicsDAO.Fields.characteristic_details.toString() ) );
					
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}	
		
	}

	public CatalogProductTypeCharacteristicsDAO( JSONObject catalogProductTypeCharacteristics ) throws JSONException {
		
		try {
		
			this.set(( catalogProductTypeCharacteristics.isNull( CatalogProductTypeCharacteristicsDAO.Fields.product_type_id.toString() ) ? -1 : catalogProductTypeCharacteristics.getInt( CatalogProductTypeCharacteristicsDAO.Fields.product_type_id.toString() ) ), catalogProductTypeCharacteristics.getString( CatalogProductTypeCharacteristicsDAO.Fields.characteristic_name.toString() ), catalogProductTypeCharacteristics.getString( CatalogProductTypeCharacteristicsDAO.Fields.characteristic_details.toString() ) );
					
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}	
		
	}
	
	public int getProductTypeID() {
		
		return this.product_type_id;
		
	}
	
	public String getCharacteristicName() {
		
		return this.characteristic_name;
		
	}

	public String getCharacteristicDetails() {
	
		return this.characteristic_details;
		
	}

	public void set( int product_type_id, String characteristic_name, String characteristic_details ) {
		
		this.setProductTypeID( product_type_id );
		this.setCharacteristicName( characteristic_name );
		this.setCharacteristicDetails( characteristic_details );		
		
	}
	
	public void setProductTypeID( int product_type_id ) {
		
		this.product_type_id = product_type_id;
		
	}
	
	public void setCharacteristicName( String characteristic_name ) {
		
		this.characteristic_name = characteristic_name;
		
	}

	public void setCharacteristicDetails( String characteristic_details ) {
	
		this.characteristic_details = characteristic_details;
		
	}
	
	@Override
	public String toString() {
		
		StringBuffer str = new StringBuffer();
		
		if( this != null ) {
			str.append( "{ " ).
				append("\"").append( CatalogProductTypeCharacteristicsDAO.Fields.product_type_id.toString() ).append("\": \"").append( this.getProductTypeID() ).append( "\", " ).
				append("\"").append( CatalogProductTypeCharacteristicsDAO.Fields.characteristic_name.toString() ).append("\": \"").append( this.getCharacteristicName() ).append( "\", " ).
				append("\"").append( CatalogProductTypeCharacteristicsDAO.Fields.characteristic_details.toString() ).append("\": \"").append( this.getCharacteristicDetails() ).append( "\"" ).
				append( " }" );
		} else {
			str.append( "{}" );
		}
		
		return str.toString();
		
	}
	
}
