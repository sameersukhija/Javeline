/*
+----------+----------------------+------+-----+---------+----------------+
| Field    | Type                 | Null | Key | Default | Extra          |
+----------+----------------------+------+-----+---------+----------------+
| price_id | smallint(5)          | NO   | PRI | NULL    | auto_increment |
| amount   | int(11)              | YES  |     | NULL    |                |
| bonus_id | smallint(3) unsigned | NO   |     | NULL    |                |
+----------+----------------------+------+-----+---------+----------------+
*/

package com.lumata.expression.operators.dao.catalog;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.system.Environment;

public class CatalogPricesDAO {

	private static final Logger logger = LoggerFactory.getLogger(CatalogPricesDAO.class);
	
	private int price_id;
	private int amount;
	private int bonus_id;

	public CatalogPricesDAO() {
		
		this.set( null, null, -1, -1, -1 );
		
	}
	
	public CatalogPricesDAO( Environment env, String tenant, int price_id, int amount, int bonus_id ) {
		
		this.set( null, null, price_id, amount, bonus_id );
					
	}
	
	public CatalogPricesDAO( Environment env, String tenant, ResultSet rs ) {
		
		try {
					
			this.set( env, tenant, rs.getInt("price_id"), rs.getInt("amount"), rs.getInt("bonus_id") );
					
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}	
		
	}

	public CatalogPricesDAO( Environment env, String tenant, JSONObject catalogProductTypeCharacteristics ) {
		
		try {
		
			this.set(env, tenant,( catalogProductTypeCharacteristics.isNull( "price_id" ) ? -1 : catalogProductTypeCharacteristics.getInt( "price_id" ) ), catalogProductTypeCharacteristics.getInt( "amount" ), catalogProductTypeCharacteristics.getInt( "bonus_id" ) );
					
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}	
		
	}
	
	public int getPriceID() {
		
		return this.price_id;
		
	}
	
	public int getAmount() {
		
		return this.amount;
		
	}

	public int getBonusID() {
	
		return this.bonus_id;
		
	}

	public void set( Environment env, String tenant, int price_id, int amount, int bonus_id ) {
		
		this.setPriceID( price_id );
		this.setAmount( amount );
		this.setBonusID( bonus_id );		
		
	}
	
	public void setPriceID( int price_id ) {
		
		this.price_id = price_id;
		
	}
	
	public void setAmount( int amount ) {
		
		this.amount = amount;
		
	}

	public void setBonusID( int bonus_id ) {
	
		this.bonus_id = bonus_id;
		
	}
	
}
