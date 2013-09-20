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
import java.util.ArrayList;
import java.util.Arrays;

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

public class CatalogProductTypesDAOList {

	private static final Logger logger = LoggerFactory.getLogger( CatalogProductTypesDAOList.class );

	public enum Fields { catalog_product_types }
	
	private ArrayList<CatalogProductTypesDAO> list;
	
	public CatalogProductTypesDAOList() {
		
		this.list = new ArrayList<CatalogProductTypesDAO>();
		
	}
	
	public CatalogProductTypesDAOList( Environment env, String tenant, ArrayList<Integer> filteredIds ) {
				
		this.load(env, tenant, filteredIds);
		
	}
	
	public CatalogProductTypesDAOList( Environment env, String tenant, ArrayList<Integer> filteredIds, String path, String file, IOFileUtils.IOLoadingType loadingType ) {
		
		this( env, tenant, filteredIds, CatalogProductTypesDAOList.loadFromJSON( path, file, loadingType ) );			
		
	}
		
	public CatalogProductTypesDAOList( Environment env, String tenant, ArrayList<Integer> filteredIds, JSONObject catalogProductTypeDAOList ) {
						
		try {
		
			this.load( env, tenant, filteredIds);
						
			JSONArray catalogProductTypes = catalogProductTypeDAOList.getJSONArray( CatalogProductTypesDAOList.Fields.catalog_product_types.toString() ); 
			
			for( int i = 0; i < catalogProductTypes.length(); i++ ) {
				
				this.insert( env, tenant, filteredIds, catalogProductTypes.getJSONObject( i ) );
				
			}
			
			this.load( env, tenant, filteredIds );
			
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}	
				
	}

	public CatalogProductTypesDAO get( int catalogProductTypesIndex ) {
		
		return this.list.get( catalogProductTypesIndex );
				
	}
	
	public CatalogProductTypesDAO get( String catalogProductTypeName ) {
		
		if( catalogProductTypeName.length() <= 0 ) { return null; }
		else {
			
			for( int i = 0; i < this.list.size(); i++ ) {
				
				if( ( this.list.get( i ).getTypeName() ).equals( catalogProductTypeName ) ) { return this.list.get( i ); }
				
			}
			
		}
		
		return null;
		
	}
	
	public boolean isCatalogProductTypes( String catalogProductTypeName ) {
		
		if( this.get( catalogProductTypeName ) != null ) { return true; }
		
		return false;
		
	}
	
	public int delete( Environment env, String tenant, ArrayList<Integer> filteredIds, CatalogProductTypesDAO catalogProductTypes ) {
		
		int index = 0;
				
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
		
		String query = "DELETE FROM " + tenant + ".catalog_product_type_characteristics " + ( catalogProductTypes != null ? " WHERE " + CatalogProductTypeCharacteristicsDAO.Fields.product_type_id.toString() + " = " + catalogProductTypes.getProductTypeID() : "" ) + ";";
		
		index = mysql.execUpdate( query );	
				
		query = "DELETE FROM " + tenant + ".catalog_product_types " + ( catalogProductTypes != null ? " WHERE " + CatalogProductTypesDAO.Fields.type_name.toString() + " = '" + catalogProductTypes.getTypeName() + "'": "" ) + ";";
				
		index = mysql.execUpdate( query );	
		
		mysql.close();
		
		this.load(env, tenant, filteredIds);
		
		return index;
		
	}

	public int deleteAll( Environment env, String tenant, ArrayList<Integer> filteredIds) {
				
		return this.delete( env, tenant, filteredIds, null );
		
	}

	public int insert( Environment env, String tenant, ArrayList<Integer> filteredIds, JSONObject catalogProductTypes ) {
		
		int catalogProductTypeID = -1;
				
		catalogProductTypeID = this.insert(env, tenant, filteredIds, new CatalogProductTypesDAO( catalogProductTypes ));
		
		return catalogProductTypeID;
		
	}
	
	public int insert( Environment env, String tenant, ArrayList<Integer> filteredIds, CatalogProductTypesDAO catalogProductTypes ) {
		
		int catalogProductTypeID = -1; 
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
		
		CatalogProductTypesDAO cpt = this.get( catalogProductTypes.getTypeName() );
				
		if( cpt == null ) {
						
			String query = "INSERT INTO " + tenant + ".catalog_product_types " +
					"( " +
					" type_name, " +
					" description " +
					") " +
					"VALUES ( '" + cpt.getTypeName() + "'," +
					"		  '" + cpt.getDescription() + "' " +
					");";
			
			catalogProductTypeID = mysql.execUpdate( query );
			
			CatalogProductTypeCharacteristicsDAOList catalogProductTypeCharacteristicsDAOList = catalogProductTypes.getCatalogProductTypeCharacteristics();
			
			for( int i = 0; i < catalogProductTypeCharacteristicsDAOList.size(); i++ ) {
				
				catalogProductTypeCharacteristicsDAOList.insert( env, tenant, new ArrayList<Integer>( Arrays.asList( catalogProductTypeID ) ), catalogProductTypeCharacteristicsDAOList.get( i ) );
				
			}
						
		} else {
			
			catalogProductTypeID = cpt.getProductTypeID();
			
		}
		
		return catalogProductTypeID;
		
	}
		
	public void load( Environment env, String tenant, ArrayList<Integer> filteredIds ) {
				
		this.list = new ArrayList<CatalogProductTypesDAO>();
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
				
		String query = 	"SELECT 	 	cpt.product_type_id, " +
						"		 	 	cpt.type_name, " +
						"		 	 	cpt.description " +
						"FROM 			" + tenant + ".catalog_product_types cpt " +
						this.getQueryFilter(filteredIds) + ";";
		
		ResultSet rs = mysql.execQuery( query );
		
		try {
		
			CatalogProductTypesDAO catalogProductTypes = null;
			
			while( rs.next() ) {
						
				catalogProductTypes = new CatalogProductTypesDAO( rs );
				
				catalogProductTypes.setCatalogProductTypeCharacteristics( new CatalogProductTypeCharacteristicsDAOList( env, tenant, new ArrayList<Integer>(Arrays.asList( rs.getInt( CatalogProductTypesDAO.Fields.product_type_id.toString() ) ) ) ) );
				
				this.list.add( catalogProductTypes );							
				
			}
			
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		mysql.close();
		
	}
		
	public static JSONObject loadFromJSON( String path, String file, IOFileUtils.IOLoadingType loadingType ) {
		
		JSONObject catalogProductTypes = new JSONObject();
		
		try {
		
			switch( loadingType ) {
			
				case FILE: {  
					catalogProductTypes = JSONUtils.loadJSONFile( path, file );
					break;
				}
				case RESOURCE: {
					catalogProductTypes = JSONUtils.loadJSONResource( path, file );
					break;
				}
			
			}
			
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
		} catch( IOFileException e ) {
			
			logger.error( e.getMessage(), e );
			
		}	
		
		return catalogProductTypes;
		
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
				
				filter.append( "WHERE ").append( CatalogProductTypesDAO.Fields.product_type_id.toString() ).append( " IN ( " ).append( ids.substring( 0, ids.length() - 1 ) ).append( " )" );
				
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
