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
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.system.Environment;

public class CatalogProductTypeCharacteristicsDAOList {

	private static final Logger logger = LoggerFactory.getLogger( CatalogProductTypeCharacteristicsDAOList.class );
	
	private ArrayList<CatalogProductTypeCharacteristicsDAO> list;
	
	public enum Fields { catalog_product_type_characteristics }
	
	public CatalogProductTypeCharacteristicsDAOList() {
		
		this.load( null, null, null );
		
	}
	
	public CatalogProductTypeCharacteristicsDAOList( JSONObject catalogProductTypeCharacteristicsDAOList ) {
		
		try {
		
			JSONArray catalogProductTypeCharacteristics = catalogProductTypeCharacteristicsDAOList.getJSONArray( CatalogProductTypeCharacteristicsDAOList.Fields.catalog_product_type_characteristics.toString() ); 
			
			for( int i = 0; i < catalogProductTypeCharacteristics.length(); i++ ) {
				
				this.list.add( new CatalogProductTypeCharacteristicsDAO( catalogProductTypeCharacteristics.getJSONObject( i ) ) );
				
			}
			
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}	
				
	}
	
	public CatalogProductTypeCharacteristicsDAOList( Environment env, String tenant, ArrayList<Integer> filteredIds ) {
				
		this.load(env, tenant, filteredIds);
		
	}
		
	public CatalogProductTypeCharacteristicsDAOList( Environment env, String tenant, ArrayList<Integer> filteredIds, String path, String file, IOFileUtils.IOLoadingType loadingType ) {
		
		this( env, tenant, filteredIds, CatalogProductTypeCharacteristicsDAOList.loadFromJSON( path, file, loadingType ) );
						
	}
	
	public CatalogProductTypeCharacteristicsDAOList( Environment env, String tenant, ArrayList<Integer> filteredIds, JSONObject catalogProductTypeCharacteristicsDAOList ) {
						
		try {
		
			this.load( env, tenant, filteredIds);
						
			JSONArray catalogProductTypeCharacteristics = catalogProductTypeCharacteristicsDAOList.getJSONArray( CatalogProductTypeCharacteristicsDAOList.Fields.catalog_product_type_characteristics.toString() ); 
			
			for( int i = 0; i < catalogProductTypeCharacteristics.length(); i++ ) {
				
				this.insert( env, tenant, filteredIds, catalogProductTypeCharacteristics.getJSONObject( i ) );
				
			}
			
			this.load( env, tenant, filteredIds );
			
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}	
				
	}
	
	public CatalogProductTypeCharacteristicsDAO get( int catalogProductTypeCharacteristicID ) {
		
		return this.list.get( catalogProductTypeCharacteristicID );
				
	}
	
	public CatalogProductTypeCharacteristicsDAO get( String catalogProductTypeCharacteristicName ) {
		
		if( catalogProductTypeCharacteristicName.length() <= 0 ) { return null; }
		else {
			
			for( int i = 0; i < this.list.size(); i++ ) {
				
				if( this.list.get( i ).getCharacteristicName().equals( catalogProductTypeCharacteristicName ) ) { return this.list.get( i ); }
				
			}
			
		}
		
		return null;
		
	}

	public boolean isCatalogProductTypeCharacteristic( String catalogProductTypeCharacteristicName ) {
		
		if( this.get(catalogProductTypeCharacteristicName) != null ) { return true; }
		
		return false;
		
	}
	
	public int delete( Environment env, String tenant, ArrayList<Integer> filteredIds, CatalogProductTypeCharacteristicsDAO catalogProductTypeCharacteristicsDAO ) {
		
		int index = 0;
				
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
				
		String query = "DELETE FROM " + tenant + ".catalog_product_type_characteristics " + ( catalogProductTypeCharacteristicsDAO != null ? "WHERE " + SuppliersDAO.Fields.name.toString() + " = '" + catalogProductTypeCharacteristicsDAO.getCharacteristicName() + "'": "" ) + ";";
				
		index = mysql.execUpdate( query );
				
		mysql.close();
		
		this.load(env, tenant, filteredIds);
		
		return index;
		
	}
	
	public int deleteAll( Environment env, String tenant, ArrayList<Integer> filteredIds) {
				
		return this.delete( env, tenant, filteredIds, null );
		
	}
	
	public int insert( Environment env, String tenant, ArrayList<Integer> filteredIds, JSONObject catalogProductTypeCharacteristicsDAO ) {
		
		int index = -1;
		
		
		try {
			
			this.insert(env, tenant, filteredIds, new CatalogProductTypeCharacteristicsDAO( catalogProductTypeCharacteristicsDAO ));
			
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		return index;
		
	}
	
	public int insert( Environment env, String tenant, ArrayList<Integer> filteredIds, CatalogProductTypeCharacteristicsDAO catalogProductTypeCharacteristicsDAO ) {
		
		int catalogProductTypeCharacteristicID = -1; 
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
		
		CatalogProductTypeCharacteristicsDAO isCatalogProductTypeCharacteristic = this.get( catalogProductTypeCharacteristicsDAO.getCharacteristicName() );
		
		if( isCatalogProductTypeCharacteristic == null ) {
			
			String query = "INSERT INTO " + tenant + ".catalog_product_type_characteristics ( " + 
								CatalogProductTypeCharacteristicsDAO.Fields.characteristic_name.toString() + ", " + 
								CatalogProductTypeCharacteristicsDAO.Fields.characteristic_details.toString() +								
							" ) VALUES ( '" + 
								catalogProductTypeCharacteristicsDAO.getCharacteristicName() + "', '" +
								catalogProductTypeCharacteristicsDAO.getCharacteristicDetails() +							
							"' );";
			
			catalogProductTypeCharacteristicID = mysql.execUpdate( query );
			
		} else {
			
			catalogProductTypeCharacteristicID = catalogProductTypeCharacteristicsDAO.getProductTypeID();
					
		}
				
		return catalogProductTypeCharacteristicID;
		
	}
	
	public void load( Environment env, String tenant, ArrayList<Integer> filteredIds ) {
				
		this.list = new ArrayList<CatalogProductTypeCharacteristicsDAO>();
		
		if( env != null && tenant != null ) {
			
			Mysql mysql = new Mysql( env.getDataSource( tenant ) );
					
			String query = 	"SELECT 	 	cptc." + CatalogProductTypeCharacteristicsDAO.Fields.product_type_id.toString() + ", " +
							"		 	 	cptc." + CatalogProductTypeCharacteristicsDAO.Fields.characteristic_name.toString() + ", " +
							"		 	 	cptc." + CatalogProductTypeCharacteristicsDAO.Fields.characteristic_details.toString() + " " +
							"FROM 	" + tenant + ".catalog_product_type_characteristics cptc " +
							this.getQueryFilter(filteredIds) + ";";
			
			ResultSet rs = mysql.execQuery( query );
			
			try {
			
				CatalogProductTypeCharacteristicsDAO catalogProductTypeCharacteristicsDAO = null;
				
				while( rs.next() ) {
								
					catalogProductTypeCharacteristicsDAO = new CatalogProductTypeCharacteristicsDAO( rs );
									
					this.list.add( catalogProductTypeCharacteristicsDAO );
					
				}
			
			} catch( SQLException e ) {
				
				logger.error( e.getMessage(), e );
				
			}
			
			mysql.close();
			
		}
				
	}
	
	public static JSONObject loadFromJSON( String path, String file, IOFileUtils.IOLoadingType loadingType ) {
		
		JSONObject catalogProductTypeCharacteristicsDAOList = new JSONObject();
		
		try {
					
			switch( loadingType ) {
			
				case FILE: {  
					catalogProductTypeCharacteristicsDAOList = JSONUtils.loadJSONFile( path, file );
					break;
				}
				case RESOURCE: {
					catalogProductTypeCharacteristicsDAOList = JSONUtils.loadJSONResource( path, file );
					break;
				}
			
			}
									
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
		} catch( IOFileException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		return catalogProductTypeCharacteristicsDAOList;
		
	}
		
	public void refresh( Environment env, String tenant, ArrayList<Integer> filteredIds ) {
		
		this.load(env, tenant, filteredIds);
		
	}
	
	public int size() {
		
		return this.list.size();
		
	}
	
	public String getQueryFilter( ArrayList<Integer> filteredIds ) {
		
		StringBuffer filter = new StringBuffer();
		StringBuffer ids = new StringBuffer();
				
		if( filteredIds != null ) {
			
			for( int i = 0; i < filteredIds.size(); i++ ) {
				
				ids.append( filteredIds.get( i ) ).append( "," );
				
			}
			
			if( ids.length() > 0 ) {
				
				filter.append( "WHERE ").append( CatalogProductTypeCharacteristicsDAO.Fields.product_type_id.toString() ).append( " IN ( " ).append( ids.substring( 0, ids.length() - 1 ) ).append( " )" );
				
			}
			
		}		
		
		return filter.toString();
		
	}
	
	@Override
	public String toString() {
		
		StringBuffer str = new StringBuffer();
		StringBuffer strValues = new StringBuffer();
		
		for( int i = 0; i < this.list.size(); i++ ) {
			
			strValues.append( this.get( i ).toString() ).append( ", " );
			
		}
		
		str.append( "[ " ).append( ( strValues.length() > 2 ? strValues.substring( 0 , strValues.length() - 2 ) : "" ) ).append( " ]" );		
		
		return str.toString();
		
	}
	
}
