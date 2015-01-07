package com.lumata.common.testing.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import com.lumata.common.testing.validating.Format;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.database.MysqlColumn;
import com.lumata.common.testing.exceptions.DataModelException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.io.JSONUtils;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class DataModel {

	private static final  Logger logger = LoggerFactory.getLogger( DataModel.class );
	private JSONObject model; 
	private String name;
	
	public enum DataModelOptions { VALIDATOR }
	public enum TableAttributes { 
		
		FIELDS;
		
		public String getValue() { return this.name().toLowerCase(); }
		
	}
		
	public DataModel( String datamodel, IOFileUtils.IOLoadingType loadingType ) throws DataModelException  {
		
		if(  datamodel != null ) { this.name = datamodel; }
		else { throw new DataModelException( "The datamodel name is not valid ( null )" ); }
 		
		this.model = null;
		
		try {
			
			switch( loadingType ) {
			
				case FILE: { this.model = JSONUtils.loadJSONFile( datamodel + Format.JSON_EXTENSION ); break; }
				case RESOURCE: { this.model = JSONUtils.loadJSONResource( datamodel + Format.JSON_EXTENSION ); break;  }
				default: throw new DataModelException( "You cannot load a data model from resources different by FILE or RESOURCE" );
			
			}
			
		} catch( JSONSException je ) { 
			
			logger.error(je.getMessage(), je);
			
			throw new DataModelException( je.getMessage(), je );
			
		}
		
	}
	
	public DataModel( String folder, String datamodel, IOFileUtils.IOLoadingType loadingType ) throws DataModelException {
		
		if(  datamodel != null ) { this.name = datamodel; }
		else { throw new DataModelException( "The datamodel name is not valid ( null )" ); }
		
		this.model = null;
		
		try {
			
			switch( loadingType ) {
			
				case FILE: { this.model = JSONUtils.loadJSONFile( folder, datamodel + Format.JSON_EXTENSION ); break; }
				case RESOURCE: { this.model = JSONUtils.loadJSONResource( folder, datamodel + Format.JSON_EXTENSION ); break;  }
				default: throw new DataModelException( "You cannot load an environment from resources different by FILE or RESOURCE" );
			
			}
			
		} catch( JSONSException je ) { 
			
			logger.error(je.getMessage(), je);
			
			throw new DataModelException( je.getMessage(), je );
			
		}
		
	}
	
	public DataModel( String datamodel, JSONObject dataSource, Map<String, Object> options ) throws DataModelException {
		
		if(  datamodel != null ) { this.name = datamodel; }
		else { throw new DataModelException( "The datamodel name is not valid ( null )" ); }
		
		this.model = new JSONObject();
				
		try {
			
			Mysql mysql = new Mysql( dataSource );
			
			ResultSet rs_tables = mysql.execQuery( "SHOW TABLES;" );
						
			while (rs_tables.next()) { 
				
				String table = rs_tables.getString(1);
				
				this.model.put( table, new JSONObject() );
				
				this.model.getJSONObject( table ).put( "fields", new JSONArray() );
				
				if( options != null && options.containsKey( DataModelOptions.VALIDATOR.toString() ) ) {
				
					if( (boolean)options.get( DataModelOptions.VALIDATOR.toString() )  ) { 
						
						this.model.getJSONObject( table ).put( "validator", new JSONArray() );
					
					}
				
				}
				
				String query = "SELECT * FROM information_schema.COLUMNS WHERE TABLE_SCHEMA ='" + dataSource.getString( "hostName" ) + "' AND TABLE_NAME ='" + table + "';";
				
				ResultSet rs_fields = mysql.execQuery( query );
				
				while ( rs_fields.next() ) { 
									
					this.model.getJSONObject( table ).getJSONArray( "fields" ).put( this.createField( rs_fields ) );
					
				}
				
				this.model.getJSONObject( table ).put( "keys", new JSONArray() );
				
			}
			try {
				Thread.sleep( 5000 );
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch( SQLException e ) {
			
			logger.error(e.getMessage(), e);
			
		} catch( JSONException e ) {
			
			logger.error(e.getMessage(), e);
			
		}
		
	}
	
	private JSONObject createField( ResultSet rs_fields ) {
		
		JSONObject field = new JSONObject();
		
		for( MysqlColumn.Fields mysqlColumnType : MysqlColumn.Fields.values() ) {
			
			try {
			
				final String FIELD_TYPE = mysqlColumnType.toString().toLowerCase();
				
				field.put( FIELD_TYPE, ( rs_fields.getString( FIELD_TYPE ) != null ? rs_fields.getString( FIELD_TYPE ) : JSONObject.NULL ) );
				
			} catch( SQLException e ) {
				
				logger.error( e.getMessage(), e );
					
			} catch( JSONException je ) {
				
				logger.error( je.getMessage(), je );
			
			}
			
		}
		
		return field;
		
	}
	
	public static JSONObject compare( DataModel dataModel1, DataModel dataModel2) {
		
		JSONObject dataModelDiff = new JSONObject();
		JSONObject dataModelDiffLeft = new JSONObject();
		JSONObject dataModelDiffRight = new JSONObject();
		
		try {
					
			JSONObject dataModelJson1 = new JSONObject( dataModel1.getDataModel().toString() );
			JSONObject dataModelJson2 = new JSONObject( dataModel2.getDataModel().toString() );
			
			DataModel.compareTables( dataModelJson1, dataModelJson2, dataModelDiffLeft, dataModelDiffRight );
			DataModel.compareTables( dataModelJson2, dataModelJson1, dataModelDiffLeft, dataModelDiffRight );
			
			dataModelDiff.put( dataModel1.getDataModelName().toUpperCase(), dataModelDiffLeft );
			dataModelDiff.put( dataModel2.getDataModelName().toUpperCase(), dataModelDiffRight );
		
		} catch( JSONException e ) {
				
			logger.error(e.getMessage(), e);
				
		}
			
		return dataModelDiff;
		
	}
	
	public static void compareTables( JSONObject dmJsonLeft, JSONObject dmJsonRight, JSONObject dmDiffLeft, JSONObject dmDiffRight ) {
		
		try {
						
			@SuppressWarnings("unchecked")
			Iterator<String> keys1 = dmJsonLeft.keys();
			while( keys1.hasNext() ) {
				
				String tableName = keys1.next(); 
								
				if( dmJsonRight.isNull( tableName ) ) {
					
					dmDiffLeft.put( tableName, new JSONArray() );
				
				} else {
					
					try {
						
						JSONObject tableLeft = dmJsonLeft.getJSONObject( tableName );
						JSONObject tableRight = dmJsonRight.getJSONObject( tableName );
												
						for( int i = 0; i < tableLeft.length(); i++ ) {
							
							JSONObject tableLeftFields = tableLeft.getJSONArray( "fields").getJSONObject( i );
							
							boolean fieldNotFound = true;
							
							for( int j = 0; j < tableRight.length(); j++ ) {
								
								JSONObject tableRightFields = tableRight.getJSONArray( "fields").getJSONObject( j );
								
								if( tableLeftFields.getString( MysqlColumn.Fields.COLUMN_NAME.toString().toLowerCase() ).equals( tableRightFields.getString( MysqlColumn.Fields.COLUMN_NAME.toString().toLowerCase() ) ) ) {
									
									JSONObject tableLeftFieldDiff = new JSONObject();
									JSONObject tableRightFieldDiff = new JSONObject();
									
									if( DataModel.compareTableFields( tableLeftFields, tableLeftFieldDiff, tableRightFields, tableRightFieldDiff ) ) {
										
										dmDiffLeft.put( tableName, new JSONArray() );
										dmDiffRight.put( tableName, new JSONArray() );
										
										dmDiffLeft.getJSONArray( tableName ).put( tableLeftFieldDiff );
										dmDiffRight.getJSONArray( tableName ).put( tableRightFieldDiff );
										
									}																								
									
									fieldNotFound = false;
									
									break;
																		
								}
								
							}
							
							if( fieldNotFound ) {
								
								dmDiffLeft.put( tableName, new JSONArray() );
								dmDiffLeft.getJSONArray( tableName ).put( tableLeftFields );
								
							}
							
						}
						
						dmJsonRight.remove( tableName );
												
					} catch( JSONException e ) {
						
						logger.error( e.getMessage(), e);
					
					}	
				}
			}
		
		} catch( JSONException e ) {
				
			logger.error(e.getMessage(), e);
				
		}
		
	}
	
	public static boolean compareTableFields( JSONObject tableLeftField, JSONObject tableLeftFieldDiff, JSONObject tableRightField, JSONObject tableRightFieldDiff ) {
				
		for( MysqlColumn.Fields mysqlColumnType : MysqlColumn.Fields.values() ) {
			
			try {
			
				final String FIELD_TYPE = mysqlColumnType.toString().toLowerCase();
				
				if( !tableLeftField.getString( FIELD_TYPE ).equals( tableRightField.getString( FIELD_TYPE ) )) {

                    tableLeftFieldDiff.put( FIELD_TYPE, tableLeftField.getString( FIELD_TYPE ));
					tableRightFieldDiff.put( FIELD_TYPE, tableRightField.getString( FIELD_TYPE ));
				
				}
				
			} catch( JSONException je ) {
				
				logger.error( je.getMessage(), je );
			
			}
			
		}
		
		if( tableLeftFieldDiff.length() > 0 || tableRightFieldDiff.length() > 0 ) {
			
			try {
				
				final String FIELD = MysqlColumn.Fields.COLUMN_NAME.toString().toLowerCase();
				
				tableLeftFieldDiff.put( FIELD, tableLeftField.getString( FIELD ));
				tableRightFieldDiff.put( FIELD, tableRightField.getString( FIELD ));
			
			} catch( JSONException je ) {
				
				logger.error( je.getMessage(), je );
			
			}
				
			return true;			
													
		}	
		
		return false;
		
	} 
	
	public JSONObject getDataModel() {
		
		return this.model;
		
	}
	
	public String getDataModelName() {
		
		return this.name;
		
	}

	public JSONArray getModel( String tableName ) {
		
		try {
			
			if( !this.model.isNull( tableName ) ) { return this.model.getJSONArray( tableName ); }
		
		} catch( JSONException je ) {

			logger.error(je.getMessage(), je);
		
		}
		
		return null;
		
	}

	public String getModelLabel( String tableName, int index ) {
		
		try {
			
			if( !this.getModel( tableName ).getJSONObject(index).isNull("label") ) { 
				return this.getModel(tableName).getJSONObject(index).getString("label");
			}
		
		} catch( Exception e ) {
			
			logger.error(e.getMessage(), e);
			
		}
		
		return null;
		
	}

	public JSONArray getModelValidator( String tableName, int index ) {
		
		try {
			
			if( !this.getModel( tableName ).getJSONObject(index).isNull("validator") ) {
				return this.getModel( tableName ).getJSONObject(index).getJSONArray("validator");
			}
		
		} catch( Exception e ) {
			
			logger.error(e.getMessage(), e);
			
		}
		
		return null;
		
	}
	
	
	
	
}
