/*
+-------------+----------------------+------+-----+---------+----------------+
| Field       | Type                 | Null | Key | Default | Extra          |
+-------------+----------------------+------+-----+---------+----------------+
| supplier_id | smallint(5) unsigned | NO   | PRI | NULL    | auto_increment |
| name        | varchar(45)          | NO   | UNI | NULL    |                |
| email       | varchar(70)          | YES  |     | NULL    |                |                                                                                          
| phone       | varchar(20)          | YES  |     | NULL    |                |                                                                                          
| website     | varchar(70)          | YES  |     | NULL    |                |                                                                                          
+-------------+----------------------+------+-----+---------+----------------+ 
*/

package com.lumata.expression.operators.dao.catalog;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.system.Environment;

public class SuppliersDAO {

	private static final Logger logger = LoggerFactory.getLogger( SuppliersDAO.class );
	
	public enum Fields { supplier_id, name, email, phone, website }
	
	private int supplier_id;
	private String name ;
	private String email;
	private String phone;
	private String website;
	
	public SuppliersDAO() {
		
		this.set( -1, "", "", "", "" );
		
	}
	
	public SuppliersDAO( int supplier_id, String name, String email, String phone, String website  ) {
		
		this.set( supplier_id, name, email, phone, website );
					
	}
	
	public SuppliersDAO( ResultSet rs ) {
		
		try {
			
			this.set( rs.getInt( SuppliersDAO.Fields.supplier_id.toString() ), rs.getString( SuppliersDAO.Fields.name.toString() ), rs.getString( SuppliersDAO.Fields.email.toString() ), rs.getString( SuppliersDAO.Fields.phone.toString() ), rs.getString( SuppliersDAO.Fields.website.toString() ) );
					
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}	
		
	}

	public SuppliersDAO( JSONObject supplier ) throws JSONException {
		
		try {
		
			this.set(( supplier.isNull( SuppliersDAO.Fields.supplier_id.toString() ) ? -1 : supplier.getInt( SuppliersDAO.Fields.supplier_id.toString() ) ), supplier.getString( SuppliersDAO.Fields.name.toString() ), supplier.getString( SuppliersDAO.Fields.email.toString() ), supplier.getString( SuppliersDAO.Fields.phone.toString() ), supplier.getString( SuppliersDAO.Fields.website.toString() ) );
					
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}	
		
	}
	
	public int getSupplierID() {
		
		return this.supplier_id;
		
	}
	
	public String getName() {
		
		return this.name;
		
	}

	public String getEmail() {
		
		return this.email;
		
	}
	
	public String getPhone() {
		
		return this.phone;
		
	}

	public String getWebsite() {
		
		return this.website;
		
	}
	
	public void setSupplierID( int supplier_id) {
		
		this.supplier_id = supplier_id;
		
	}
	
	public void setName( String name ) {
		
		this.name = name;
		
	}

	public void setEmail( String email ) {
		
		this.email = email;
		
	}
	
	public void setPhone( String phone ) {
		
		this.phone = phone;
		
	}

	public void setWebsite( String website ) {
		
		this.website = website;
		
	}
	
	public void set( int supplier_id, String name, String email, String phone, String website ) {
		
		this.setSupplierID( supplier_id );
		this.setName( name );
		this.setEmail( email );
		this.setPhone( phone );
		this.setWebsite( website );
		
	}
	
}
