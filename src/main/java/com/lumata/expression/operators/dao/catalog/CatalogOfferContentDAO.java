/*
 * +--------------+----------------------------------------+------+-----+---------+----------------+
| Field        | Type                                   | Null | Key | Default | Extra          |
+--------------+----------------------------------------+------+-----+---------+----------------+
| offer_id     | smallint(5)                            | NO   | PRI | NULL    |                |
| content_type | enum('OFFER','PRODUCT','PRODUCT_TYPE') | NO   | PRI | NULL    |                |
| content_id   | smallint(5)                            | NO   | PRI | NULL    | auto_increment |
| quantity     | int(11)                                | YES  |     | NULL    |                |
+--------------+----------------------------------------+------+-----+---------+----------------+
*/

package com.lumata.expression.operators.dao.catalog;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.system.Environment;

public class CatalogOfferContentDAO {

	private static final Logger logger = LoggerFactory.getLogger(CatalogOfferContentDAO.class);
	
	public enum Fields { offer_id, content_type, content_id, quantity }
	
	private int offer_id;
	private enum ContentType { OFFER,PRODUCT,PRODUCT_TYPE };
	private String content_type;
	private int content_id;
	private int quantity;
	
	public CatalogOfferContentDAO() {
		
		this.set( null, null, -1, "OFFER", -1, -1 );
		
	}
	
	public CatalogOfferContentDAO( Environment env, String tenant, int offer_id, String content_type, int content_id, int quantity ) {
		
		this.set( env, tenant, offer_id, content_type, content_id, quantity );
					
	}
	
	public CatalogOfferContentDAO( Environment env, String tenant, ResultSet rs ) {
		
		try {
					
			this.set( env, tenant, rs.getInt("offer_id"), rs.getString("content_type"), rs.getInt("content_id"), rs.getInt("quantity") );
					
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}	
		
	}

	public CatalogOfferContentDAO( Environment env, String tenant, JSONObject catalogOfferContent ) {
		
		try {
		
			this.set(env, tenant,( catalogOfferContent.isNull( "offer_id" ) ? -1 : catalogOfferContent.getInt( "offer_id" ) ), catalogOfferContent.getString( "content_type" ), catalogOfferContent.getInt( "content_id" ), catalogOfferContent.getInt( "quantity" ) );
					
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}	
		
	}
	public int getOfferID() {
		
		return this.offer_id;
		
	}
	
	public String getContentType() {
		
		return this.content_type;
		
	}

	public ContentType getContentTypeEnum( String content_type ) {
		
		return CatalogOfferContentDAO.ContentType.valueOf( content_type );
		
	}

	public int getContentID() {
		
		return this.content_id;
		
	}

	public int getQuantity() {
	
		return this.quantity;
		
	}
	
	public void setOfferID( int offer_id ) {
		
		this.offer_id = offer_id;
		
	}
	
	public void setContentType( String content_type ) {
		
		this.content_type = CatalogOfferContentDAO.ContentType.valueOf( content_type ).toString();
		
	}

	public void setContentID( int content_id ) {
		
		this.content_id = content_id;
		
	}

	public void setQuantity( int quantity ) {
	
		this.quantity = quantity;
		
	}
	
	public void set( Environment env, String tenant, int offer_id, String content_type, int content_id, int quantity ) {
		
		this.setContentID( content_id );
		this.setContentType( content_type );
		this.setOfferID( offer_id );
		this.setQuantity( quantity );
		
	}
	
}
