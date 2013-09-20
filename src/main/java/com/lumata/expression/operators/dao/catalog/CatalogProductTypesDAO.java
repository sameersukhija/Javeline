/*
+-----------------+--------------+------+-----+---------+----------------+
| Field           | Type         | Null | Key | Default | Extra          |
+-----------------+--------------+------+-----+---------+----------------+
| product_type_id | smallint(5)  | NO   | PRI | NULL    | auto_increment |
| type_name       | varchar(45)  | YES  | UNI | NULL    |                |
| description     | varchar(512) | YES  |     | NULL    |                |
+-----------------+--------------+------+-----+---------+----------------+
*/

package com.lumata.expression.operators.dao.catalog;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatalogProductTypesDAO {

	private static final Logger logger = LoggerFactory.getLogger( CatalogProductTypesDAO.class );
	
	public enum Fields { product_type_id, type_name, description }
	
	private int product_type_id;
	private String type_name ;
	private String description;
	private CatalogProductTypeCharacteristicsDAOList characteristic_list;
	
	public CatalogProductTypesDAO() {
		
		this.set( -1, "", "", new CatalogProductTypeCharacteristicsDAOList() );
		
	}
	
	public CatalogProductTypesDAO( int product_type_id, String type_name, String description, CatalogProductTypeCharacteristicsDAOList catalogProductTypeCharacteristicsDAOList ) {
		
		this.set( product_type_id, type_name, description, catalogProductTypeCharacteristicsDAOList );
					
	}
	
	public CatalogProductTypesDAO( ResultSet rs ) {
		
		try {
					
			this.set( rs.getInt( CatalogProductTypesDAO.Fields.product_type_id.toString() ), rs.getString( CatalogProductTypesDAO.Fields.type_name.toString() ), rs.getString( CatalogProductTypesDAO.Fields.description.toString() ), new CatalogProductTypeCharacteristicsDAOList() );
					
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}	
		
	}

	public CatalogProductTypesDAO( JSONObject productType ) {
		
		try {
		
			int product_type_id = ( productType.isNull( CatalogProductTypesDAO.Fields.product_type_id.toString() ) ? -1 : productType.getInt( CatalogProductTypesDAO.Fields.product_type_id.toString() ) );
			
			this.set( product_type_id , productType.getString( CatalogProductTypesDAO.Fields.type_name.toString() ), productType.getString( CatalogProductTypesDAO.Fields.description.toString() ), new CatalogProductTypeCharacteristicsDAOList() );
					
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}	
		
	}
	
	public int getProductTypeID() {
		
		return this.product_type_id;
		
	}
	
	public String getTypeName() {
		
		return this.type_name;
		
	}

	public String getDescription() {
		
		return this.description;
		
	}
	
	public CatalogProductTypeCharacteristicsDAOList getCatalogProductTypeCharacteristics() {
		
		return this.characteristic_list;
		
	}
		
	public void setProductTypeID( int product_type_id) {
		
		this.product_type_id = product_type_id;
		
	}
	
	public void setTypeName( String type_name ) {
		
		this.type_name = type_name;
		
	}

	public void setDescription( String description ) {
		
		this.description = description;
		
	}
	
	public void setCatalogProductTypeCharacteristics( CatalogProductTypeCharacteristicsDAOList characteristic_list ) {
		
		this.characteristic_list = characteristic_list;
		
	}	

	public void set( int product_type_id, String type_name, String description, CatalogProductTypeCharacteristicsDAOList characteristic_list ) {
		
		this.setProductTypeID( product_type_id );
		this.setTypeName( type_name );
		this.setDescription( description );
		this.setCatalogProductTypeCharacteristics( characteristic_list );
		
	}
	
	@Override
	public String toString() {
		
		StringBuffer str = new StringBuffer();
		
		if( this != null ) {
			str.append( "{ " ).
				append("\"").append( CatalogProductTypesDAO.Fields.product_type_id.toString() ).append("\": \"").append( this.getProductTypeID() ).append( "\", " ).
				append("\"").append( CatalogProductTypesDAO.Fields.type_name.toString() ).append("\": \"").append( this.getTypeName() ).append( "\", " ).
				append("\"").append( CatalogProductTypesDAO.Fields.description.toString() ).append("\": \"").append( this.getDescription() ).append( "\", " ).
				append("\"").append( CatalogProductTypeCharacteristicsDAOList.Fields.catalog_product_type_characteristics.toString() ).append("\": ").append( this.getCatalogProductTypeCharacteristics().toString() ).
				append( " }" );	
		} else {
			str.append( "{}" );
		}	
		
		return str.toString();
		
	}
	
}
