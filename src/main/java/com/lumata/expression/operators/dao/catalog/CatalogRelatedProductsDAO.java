/*
+--------------------+-------------+------+-----+---------+-------+
| Field              | Type        | Null | Key | Default | Extra |
+--------------------+-------------+------+-----+---------+-------+
| product_id         | smallint(5) | NO   | PRI | NULL    |       |
| related_product_id | smallint(5) | NO   | PRI | NULL    |       |
+--------------------+-------------+------+-----+---------+-------+
*/

package com.lumata.expression.operators.dao.catalog;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.system.Environment;

public class CatalogRelatedProductsDAO {

private static final Logger logger = LoggerFactory.getLogger( CatalogRelatedProductsDAO.class );
	
	private int product_id;
	private int related_product_id;
	public enum CatalogRelatedProductsDAOFields { 
		
		product_id {
			public String getMethod() { return "ProductID"; }
		}, 
		related_product_id { 
			public String getMethod() { return "RelatedProductID"; }
		}; 
		
		public abstract String getMethod();
	
	};
	
	public CatalogRelatedProductsDAO() {
		
		this.set( null, null, -1, -1 );
		
	}
	
	public CatalogRelatedProductsDAO( Environment env, String tenant, int product_id, int related_product_id ) {
		
		this.set( null, null, product_id, related_product_id );
					
	}
	
	public CatalogRelatedProductsDAO( Environment env, String tenant, ResultSet rs ) {
		
		try {
					
			this.set( env, tenant, rs.getInt(CatalogRelatedProductsDAOFields.product_id.toString()), rs.getInt(CatalogRelatedProductsDAOFields.related_product_id.toString()) );
					
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}	
		
	}

	public CatalogRelatedProductsDAO( Environment env, String tenant, JSONObject catalogProductTypeCharacteristics ) {
		
		try {
		
			this.set(env, tenant,( catalogProductTypeCharacteristics.isNull( CatalogRelatedProductsDAOFields.product_id.toString() ) ? -1 : catalogProductTypeCharacteristics.getInt( CatalogRelatedProductsDAOFields.product_id.toString() ) ), catalogProductTypeCharacteristics.getInt( CatalogRelatedProductsDAOFields.related_product_id.toString() ) );
					
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}	
		
	}
	
	public int getProductID() {
		
		return this.product_id;
		
	}
	
	public int getRelatedProductID() {
		
		return this.related_product_id;
		
	}

	public void set( Environment env, String tenant, int product_id, int related_product_id ) {
		
		this.setProductID( product_id );
		this.setRelatedProductID( related_product_id );		
		
	}
	
	public void setProductID( int product_id ) {
		
		this.product_id = product_id;
		
	}
	
	public void setRelatedProductID( int related_product_id ) {
		
		this.related_product_id = related_product_id;
		
	}
	
}
