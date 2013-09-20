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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

public class SuppliersDAOList {

private static final Logger logger = LoggerFactory.getLogger( SuppliersDAOList.class );
	
	private Map<String, Map<String, String>> DEPENDENCY = new HashMap<String, Map<String,String>>() { 
		{
			put( "catalog_product_types", new HashMap<String,String>() { { put( "supplier_id", "supplier_id" ); } } );
		} 
	};
	
	private ArrayList<SuppliersDAO> list;
	
	public SuppliersDAOList( Environment env, String tenant, ArrayList<Object> filteredIds ) {
				
		this.load(env, tenant, filteredIds);
		
	}
		
	public SuppliersDAOList( Environment env, String tenant, ArrayList<Object> filteredIds, String path, String file, IOFileUtils.IOLoadingType loadingType ) {
		
		JSONObject suppliersDAOList = new JSONObject();
				
		try {
		
			this.load( env, tenant, filteredIds);
			
			switch( loadingType ) {
			
				case FILE: {  
					suppliersDAOList = JSONUtils.loadJSONFile( path, file );
					break;
				}
				case RESOURCE: {
					suppliersDAOList = JSONUtils.loadJSONResource( path, file );
					break;
				}
			
			}
			
			JSONArray suppliers = suppliersDAOList.getJSONArray( "suppliers" ); 
			
			for( int i = 0; i < suppliers.length(); i++ ) {
				
				this.insert( env, tenant, filteredIds, suppliers.getJSONObject( i ) );
				
			}
			
			this.load( env, tenant, filteredIds );
			
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		} catch( JSONSException e ) {
			
			logger.error( e.getMessage(), e );
			
		} catch( IOFileException e ) {
			
			logger.error( e.getMessage(), e );
			
		}	
				
	}
	
	public SuppliersDAO get( int supplierID ) {
		
		return this.list.get( supplierID );
				
	}
	
	public SuppliersDAO get( String supplier ) {
		
		if( supplier.length() <= 0 ) { return null; }
		else {
			
			for( int i = 0; i < this.list.size(); i++ ) {
				
				if( this.list.get( i ).getName().equals( supplier ) ) { return this.list.get( i ); }
				
			}
			
		}
		
		return null;
		
	}

	public boolean isSupplier( String supplier ) {
		
		if( this.get(supplier) != null ) { return true; }
		
		return false;
		
	}
	
	public int delete( Environment env, String tenant, ArrayList<Object> filteredIds, SuppliersDAO suppliersDAO ) {
		
		int index = 0;
				
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
				
		String query = "DELETE FROM " + tenant + ".suppliers " + ( suppliersDAO != null ? "WHERE " + SuppliersDAO.Fields.name.toString() + " = '" + suppliersDAO.getName() + "'": "" ) + ";";
				
		index = mysql.execUpdate( query );
				
		mysql.close();
		
		this.load(env, tenant, filteredIds);
		
		return index;
		
	}
	
	public int deleteAll( Environment env, String tenant, ArrayList<Object> filteredIds) {
				
		return this.delete( env, tenant, filteredIds, null );
		
	}
	
	public int insert( Environment env, String tenant, ArrayList<Object> filteredIds, JSONObject suppliersDAO ) {
		
		int index = -1;
		
		
		try {
			
			this.insert(env, tenant, filteredIds, new SuppliersDAO( suppliersDAO ));
			
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		return index;
		
	}
	
	public int insert( Environment env, String tenant, ArrayList<Object> filteredIds, SuppliersDAO suppliersDAO ) {
		
		int supplierID = -1; 
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
		
		SuppliersDAO supplier = this.get( suppliersDAO.getName() );
		
		if( supplier == null ) {
			
			String query = "INSERT INTO " + tenant + ".suppliers ( " + 
								SuppliersDAO.Fields.name.toString() + ", " + 
								SuppliersDAO.Fields.email.toString() + ", " + 
								SuppliersDAO.Fields.phone.toString() + ", " +
								SuppliersDAO.Fields.website.toString() +								
							" ) VALUES ( '" + 
								suppliersDAO.getName() + "', '" +
								suppliersDAO.getEmail() + "', '" +
								suppliersDAO.getPhone() + "', '" +
								suppliersDAO.getWebsite() +							
							"' );";
			
			supplierID = mysql.execUpdate( query );
			
		} else {
			
			supplierID = suppliersDAO.getSupplierID();
					
		}
				
		return supplierID;
		
	}
	
	public void load( Environment env, String tenant, ArrayList<Object> filteredIds ) {
				
		this.list = new ArrayList<SuppliersDAO>();
		
		Mysql mysql = new Mysql( env.getDataSource( tenant ) );
				
		String query = 	"SELECT 	 	sp.supplier_id, " +
						"		 	 	sp.name, " +
						"		 	 	sp.email, " +
						"		 	 	sp.phone, " +
						"		 	 	sp.website " +
						"FROM 	" + tenant + ".suppliers sp "+
						this.getQueryFilter( filteredIds ) + ";";
		
		ResultSet rs = mysql.execQuery( query );
		
		try {
		
			SuppliersDAO suppliersDAO = null;
			
			while( rs.next() ) {
							
				suppliersDAO = new SuppliersDAO( rs );
								
				this.list.add( suppliersDAO );
				
			}
		
		} catch( SQLException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
		mysql.close();
		
	}
		
	public void refresh( Environment env, String tenant, ArrayList<Object> filteredIds ) {
		
		this.load(env, tenant, filteredIds);
		
	}
	
	public int size() {
		
		return this.list.size();
		
	}		
	
	public String getQueryFilter( ArrayList<Object> filteredIds ) {
		
		StringBuffer filter = new StringBuffer();
		StringBuffer ids = new StringBuffer();
				
		if( filteredIds != null ) {
			
			for( int i = 0; i < filteredIds.size(); i++ ) {
				
				ids.append( filteredIds.get( i ) ).append( "," );
				
			}
			
			if( ids.length() > 0 ) {
				
				filter.append( "WHERE ").append( SuppliersDAO.Fields.supplier_id.toString() ).append( " IN ( " ).append( ids.substring( 0, ids.length() - 1 ) ).append( " )" );
				
			}
			
		}		
		
		return filter.toString();
		
	}
	
}
