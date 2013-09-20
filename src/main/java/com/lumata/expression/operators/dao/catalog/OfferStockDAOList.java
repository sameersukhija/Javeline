/*
+-------------+----------------------+------+-----+-------------------+-----------------------------+
| Field       | Type                 | Null | Key | Default           | Extra                       |
+-------------+----------------------+------+-----+-------------------+-----------------------------+
| product_id  | smallint(5) unsigned | NO   | PRI | NULL              |                             |
| channel_id  | smallint(5) unsigned | NO   | PRI | 0                 |                             |
| reserved    | bigint(20) unsigned  | YES  |     | 0                 |                             |
| allocated   | bigint(20) unsigned  | YES  |     | 0                 |                             |
| accepted    | bigint(20) unsigned  | YES  |     | 0                 |                             |
| refused     | bigint(20) unsigned  | YES  |     | 0                 |                             |
| update_time | timestamp            | NO   |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
+-------------+----------------------+------+-----+-------------------+-----------------------------+
*/

package com.lumata.expression.operators.dao.catalog;

import java.util.ArrayList;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.system.Environment;

public class OfferStockDAOList {

	private ArrayList<CatalogProductsDAO> list;
	
	public OfferStockDAOList() {
		
		this.list = new ArrayList<CatalogProductsDAO>();
		
	}	
	
	public int delete( Environment env, String tenant, ArrayList<Integer> filteredIds, CatalogOfferContentDAO catalogOfferContentDAO ) {
		
		int index = 0;
				
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
				
		//String query = "DELETE FROM " + tenant + ".catalog_offer_content " + ( catalogOfferContentDAO != null ? "WHERE " + CatalogOfferContentDAO.Fields.offer_id.toString() + " = '" + catalogOfferContentDAO.getCharacteristicName() + "'": "" ) + ";";
				
		String query = "DELETE FROM " + tenant + ".offer_stock;";
				
		index = mysql.execUpdate( query );
				
		mysql.close();
		
		//this.load(env, tenant, filteredIds);
		
		return index;
		
	}
	
	public int deleteAll( Environment env, String tenant, ArrayList<Integer> filteredIds) {
				
		return this.delete( env, tenant, filteredIds, null );
		
	}
	
	public int size() {
		
		return this.list.size();
		
	}
	
}
