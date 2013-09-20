package com.lumata.expression.operators.dao.catalog;

import java.util.ArrayList;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.system.Environment;

public class CatalogProductCharacteristicsDAOList {

private ArrayList<CatalogProductCharacteristicsDAO> list;
	
	public CatalogProductCharacteristicsDAOList() {
		
		this.list = new ArrayList<CatalogProductCharacteristicsDAO>();
		
	}	
	
	public int delete( Environment env, String tenant, ArrayList<Integer> filteredIds, CatalogOfferContentDAO catalogOfferContentDAO ) {
		
		int index = 0;
				
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
				
		//String query = "DELETE FROM " + tenant + ".catalog_offer_content " + ( catalogOfferContentDAO != null ? "WHERE " + CatalogOfferContentDAO.Fields.offer_id.toString() + " = '" + catalogOfferContentDAO.getCharacteristicName() + "'": "" ) + ";";
				
		String query = "DELETE FROM " + tenant + ".catalog_product_characteristics;";
				
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
