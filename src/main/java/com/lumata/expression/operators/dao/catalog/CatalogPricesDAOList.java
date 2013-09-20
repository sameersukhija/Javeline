/*
+---------------+-------------+------+-----+---------+----------------+
| Field         | Type        | Null | Key | Default | Extra          |
+---------------+-------------+------+-----+---------+----------------+
| price_list_id | smallint(5) | NO   | PRI | NULL    | auto_increment |
| price_id      | smallint(5) | NO   |     | NULL    |                |
| criteria      | blob        | YES  |     | NULL    |                |
+---------------+-------------+------+-----+---------+----------------+
*/

package com.lumata.expression.operators.dao.catalog;

import java.util.ArrayList;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.system.Environment;

public class CatalogPricesDAOList {

	private ArrayList<CatalogPricesDAO> list;
	
	public CatalogPricesDAOList() {
		
		this.list = new ArrayList<CatalogPricesDAO>();
		
	}	
	
	public int delete( Environment env, String tenant, ArrayList<Integer> filteredIds, CatalogOfferContentDAO catalogOfferContentDAO ) {
		
		int index = 0;
				
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
				
		//String query = "DELETE FROM " + tenant + ".catalog_offer_content " + ( catalogOfferContentDAO != null ? "WHERE " + CatalogOfferContentDAO.Fields.offer_id.toString() + " = '" + catalogOfferContentDAO.getCharacteristicName() + "'": "" ) + ";";
				
		String query = "DELETE FROM " + tenant + ".catalog_prices;";
				
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
