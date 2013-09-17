package com.lumata.common.testing.model;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.database.Mysql.MysqlFieldType;
import com.lumata.common.testing.exceptions.DataModelException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.exceptions.JSONSException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.io.JSONUtils;
import com.lumata.common.testing.validating.Format;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DataModelCompare {

	private static final Logger logger = LoggerFactory.getLogger(DataModelCompare.class);
		
	JSONObject dataModelCompare;
	
	private String compareDMName;
	private String leftDMName;
	private String rightDMName;
    private final static String STATUS_FIELD = "_STATUS_";
    
    public enum TABLE_STATUSES { plus, minus, difference, unknown }
	
	public DataModelCompare(JSONObject dataModelCompare) {

        this.dataModelCompare = dataModelCompare;

        this.setDataModelNames();

	}
	
	public DataModelCompare(String compareDM, IOFileUtils.IOLoadingType loadingType ) throws DataModelException {
		
		if( compareDM != null ) { this.compareDMName = compareDM; }
		else { throw new DataModelException( "The datamodel compare name is not valid ( null )" ); }
		
		this.dataModelCompare = null;
		
		try {
			
			switch( loadingType ) {
			
				case FILE: { this.dataModelCompare = JSONUtils.loadJSONFile(compareDM + Format.JSON_EXTENSION); break; }
				case RESOURCE: { this.dataModelCompare = JSONUtils.loadJSONResource(compareDM + Format.JSON_EXTENSION); break;  }
				default: throw new DataModelException( "You cannot load a data model compare from resources different by FILE or RESOURCE" );
			
			}
			
			this.setDataModelNames();
			
		} catch( IOFileException ioe ) {
			
			logger.error(ioe.getMessage(), ioe);
			
			throw new DataModelException( ioe.getMessage(), ioe );
			
		} catch( JSONSException je ) {
			
			logger.error(je.getMessage(), je);
			
			throw new DataModelException( je.getMessage(), je );
			
		}
		
	}
	
	public DataModelCompare(String folder, String compareDM, IOFileUtils.IOLoadingType loadingType ) throws DataModelException {
		
		if( compareDM != null ) { this.compareDMName = compareDM; }
		else { throw new DataModelException( "The datamodel compare name is not valid ( null )" ); }
		
		this.dataModelCompare = null;
		
		try {
			
			switch( loadingType ) {
			
				case FILE: { this.dataModelCompare = JSONUtils.loadJSONFile(folder, compareDM + Format.JSON_EXTENSION); break; }
				case RESOURCE: { this.dataModelCompare = JSONUtils.loadJSONResource(folder, compareDM + Format.JSON_EXTENSION); break;  }
				default: throw new DataModelException( "You cannot load a data model compare from resources different by FILE or RESOURCE" );
			
			}
			
			this.setDataModelNames();
			
		} catch( IOFileException ioe ) {
			
			logger.error(ioe.getMessage(), ioe);
			
			throw new DataModelException( ioe.getMessage(), ioe );
			
		} catch( JSONSException je ) {
			
			logger.error(je.getMessage(), je);
			
			throw new DataModelException( je.getMessage(), je );
			
		}
		
	}

	public String getCompareDMName() {
		
		return this.compareDMName;
		
	}

	public String getLeftDataModelName() {
		
		return this.leftDMName;
		
	}
	
	public String getRightDataModelName() {
		
		return this.rightDMName;
		
	}
	
	public JSONObject getDataModel( String dataModelName ) {
		
		try {
			
			if( !this.dataModelCompare.isNull( dataModelName ) ) { return this.dataModelCompare.getJSONObject( dataModelName ); }
		
		} catch( JSONException je ) {

			logger.error(je.getMessage(), je);
		
		}
		
		return null;		
		
	}
	
	public List<String> getDataModelTables( String dataModelName ) {
		
		List<String> tables = new ArrayList<String>();
		
		@SuppressWarnings("unchecked")
		Iterator<String> tableNames = this.getDataModel(dataModelName).keys();
		while( tableNames.hasNext() ) {	            
			tables.add( (String)tableNames.next() );	        		        	        	
		}		
		
		return tables;
		
	}
	
	public JSONObject getDataModelTable( String dataModelName, String tableName ) {
		
		try {
			
			if( !this.getDataModel(dataModelName).isNull( tableName ) ) { return this.getDataModel(dataModelName).getJSONObject( tableName ); }
		
		} catch( JSONException je ) {

			logger.error(je.getMessage(), je);
		
		}	
		
		return null;
		
	}

    public String getDataModelTableStatus( String dataModelName, String tableName ) {

        try {

            if( !this.getDataModelTable( dataModelName, tableName ).isNull( STATUS_FIELD ) ) { return this.getDataModelTable( dataModelName, tableName ).getString( STATUS_FIELD ); }

        } catch( JSONException je ) {

            logger.error(je.getMessage(), je);

        }

        return null;

    }
	
	public List<String> getDataModelTableGenericFieldsHeader() {
		
		List<String> fieldsHeaderList = new ArrayList<String>();
		
		for( MysqlFieldType mysqlFieldType : MysqlFieldType.values() ) {
			
			final String FIELD_TYPE = mysqlFieldType.toString().toLowerCase();

            fieldsHeaderList.add( FIELD_TYPE );

		}

        fieldsHeaderList.add( "_STATUS_" );

		return fieldsHeaderList;
		
	}
	
	public List<Map<String, String>> getDataModelTableFields( String dataModelName, String tableName ) {
				
		List<Map<String, String>> fieldsList = new ArrayList<Map<String, String>>();
		
		try {
			
			if( !this.getDataModelTable( dataModelName, tableName).isNull( "fields" ) ) { 
				
				JSONArray fields = this.getDataModelTable( dataModelName, tableName).getJSONArray( "fields" );
				
				for( int i = 0; i < fields.length(); i++ ) {
					
					JSONObject field = fields.getJSONObject( i );
										
					Map<String, String> fieldMap = new HashMap<String, String>();
					
					for( MysqlFieldType mysqlFieldType : MysqlFieldType.values() ) {
						
						try {
							
							final String FIELD_TYPE = mysqlFieldType.toString().toLowerCase();
							
							fieldMap.put( FIELD_TYPE, ( !field.isNull( FIELD_TYPE ) ? field.getString( FIELD_TYPE ) : "" ) );
							
						} catch( JSONException je ) {
							
							logger.error( je.getMessage(), je );
						
						}
						
					}

                    fieldMap.put( STATUS_FIELD, ( !field.isNull( STATUS_FIELD ) ? field.getString( STATUS_FIELD ) : "" ) );

					fieldsList.add( fieldMap ); 
					
				}
			
			}
		
		} catch( JSONException je ) {

			logger.error(je.getMessage(), je);
		
		}
				
		return fieldsList;
		
	}

	public List<List<String>> getDataModelTableContent( String dataModelName, String tableName ) {
		
		List<List<String>> contentList = new ArrayList<List<String>>();
		
		try {
			
			if( !this.getDataModelTable( dataModelName, tableName).isNull( "content" ) ) { 
				
				JSONArray rows = this.getDataModelTable( dataModelName, tableName).getJSONArray( "content" );
				
				for( int i = 0; i < rows.length(); i++ ) {
					
					JSONArray row = rows.getJSONArray( i );
					
					List<String> rowList = new ArrayList<String>();
					
					
					for( int j = 0; j < row.length(); j++ ) { 
						
						rowList.add( ( !row.isNull( j ) ? row.getString( j ) : "null" ) );
						
					}
					
					contentList.add( rowList );
					
				}
						
			}
		
		} catch( JSONException je ) {

			logger.error(je.getMessage(), je);
		
		}
		
		return contentList;
	
	}
	
	
	public void setDataModelNames() {
		
		@SuppressWarnings("unchecked")
		Iterator<String> keys = this.dataModelCompare.keys();
		
		int count = 0;
		
        while( keys.hasNext() && count < 2 ){
            
        	switch( count ) {
        	
        		case 0: { this.setLeftDMName( (String)keys.next() ); break; }
        		case 1: { this.setRightDMName( (String)keys.next() ); break; }
        		default: { count = 2; }
        	}
        	
        	count++;
        	        	
        }
				
	}
	
	public void setLeftDMName( String leftDMName ) {
		
		this.leftDMName = leftDMName;
		
	}
	
	public void setRightDMName( String rightDMName ) {
		
		this.rightDMName = rightDMName;
		
	}

    public static JSONObject compare( DataModel dataModel1, DataModel dataModel2, JSONObject dataSourceDataModel1, JSONObject dataSourceDataModel2, JSONObject options ) {

        JSONObject dataModelDiff = new JSONObject();
        JSONObject dataModelDiffLeft = new JSONObject();
        JSONObject dataModelDiffRight = new JSONObject();

        try {

            JSONObject dataModelJson1 = new JSONObject( dataModel1.getDataModel().toString() );
            JSONObject dataModelJson2 = new JSONObject( dataModel2.getDataModel().toString() );

            // Compare the First Schema with the Second
            DataModelCompare.compareTableSchemas( dataModelJson1, dataModelJson2, dataModelDiffLeft, dataModelDiffRight );

            // Compare the Second Schema with the First
            DataModelCompare.compareTableSchemas( dataModelJson2, dataModelJson1, dataModelDiffRight, dataModelDiffLeft );

            // Compare the Content among the tables specified in the options
            DataModelCompare.compareTableContents( dataModelJson1, dataModelJson2, dataModelDiffLeft, dataModelDiffRight, dataSourceDataModel1, dataSourceDataModel2, options );

            // Create JSONObject represents the Schemas and Contents difference
            dataModelDiff.put( dataModel1.getDataModelName().toUpperCase(), dataModelDiffLeft );
            dataModelDiff.put( dataModel2.getDataModelName().toUpperCase(), dataModelDiffRight );

        } catch( JSONException e ) {

            logger.error(e.getMessage(), e);

        }

        return dataModelDiff;

    }

    public static void compareTableSchemas( JSONObject dmJsonLeft, JSONObject dmJsonRight, JSONObject dmDiffLeft, JSONObject dmDiffRight ) {

        try {

            @SuppressWarnings("unchecked")
            Iterator<String> keys1 = dmJsonLeft.keys();
            while( keys1.hasNext() ) {

                String tableName = keys1.next();

                if( dmJsonRight.isNull( tableName ) ) { // The Table is in the Left DataModel, but is not in the Right DataModel

                    dmDiffLeft.put( tableName, new JSONObject( "{\"_STATUS_\": " + TABLE_STATUSES.plus.toString() + "}" ) );
                    dmDiffRight.put( tableName, new JSONObject( "{\"_STATUS_\": " + TABLE_STATUSES.minus.toString() + "}" ) );

                } else { // The Table is both in the Left DataModel and in the Right DataModel

                    try {

                        // Get all fields of the Left Table
                        JSONArray tableLeftAllFields = dmJsonLeft.getJSONObject( tableName ).getJSONArray( "fields" );

                        // Get all fields of the Right Table
                        JSONArray tableRightAllFields = dmJsonRight.getJSONObject( tableName ).getJSONArray( "fields" );

                        // Theminimal subset of all common fields of the Left and the Right Table
                        JSONArray commonFields = new JSONArray();

                        for( int i = 0; i < tableLeftAllFields.length(); i++ ) {

                            // Get single field of the Left Table
                            JSONObject tableLeftField = tableLeftAllFields.getJSONObject( i );

                            // Check the field is present in the Right Table
                            boolean fieldNotFound = true;
                            boolean fieldAlreadyChecked = false;

                            if( !dmDiffLeft.isNull( tableName ) ) {

                                if( !dmDiffLeft.getJSONObject( tableName ).isNull( "fields" ) ) {

                                    JSONArray all_marked_fields = dmDiffLeft.getJSONObject( tableName ).getJSONArray( "fields" );

                                    for( int k = 0; k < all_marked_fields.length(); k++ ) {

                                        if( !all_marked_fields.getJSONObject( k ).isNull(  MysqlFieldType.Field.toString().toLowerCase() ) ) {

                                            if( tableLeftField.getString( MysqlFieldType.Field.toString().toLowerCase() ).equals( all_marked_fields.getJSONObject( k ).getString(  MysqlFieldType.Field.toString().toLowerCase() ) ) ) {
                                                fieldAlreadyChecked = true;
                                                break;
                                            }

                                        }

                                    }

                                }

                            }

                            if( !fieldAlreadyChecked ) {

                                for( int j = 0; j < tableRightAllFields.length(); j++ ) {

                                    // Get single field of the Right Table
                                    JSONObject tableRightField = tableRightAllFields.getJSONObject( j );

                                    if( tableLeftField.getString( MysqlFieldType.Field.toString().toLowerCase() ).equals( tableRightField.getString( MysqlFieldType.Field.toString().toLowerCase() ) ) ) {

                                        JSONObject tableLeftFieldDiff = new JSONObject();
                                        JSONObject tableRightFieldDiff = new JSONObject();

                                        commonFields.put( tableLeftField.getString( MysqlFieldType.Field.toString().toLowerCase() ) );

                                        // The field is present both in the Left and in the Right Tables, but there are some difference
                                        if( DataModelCompare.compareTableFields( tableLeftField, tableRightField, tableLeftFieldDiff, tableRightFieldDiff ) ) {

                                            if( dmDiffLeft.isNull( tableName ) || dmDiffLeft.getJSONObject( tableName ).isNull( "fields" ) ) dmDiffLeft.put( tableName, new JSONObject( "{ \"fields\": " + new JSONArray() + " }" ) );
                                            if( dmDiffRight.isNull( tableName ) || dmDiffRight.getJSONObject( tableName ).isNull( "fields" ) ) dmDiffRight.put( tableName, new JSONObject( "{ \"fields\": " + new JSONArray() + " }" ) );

                                            tableLeftFieldDiff.put( STATUS_FIELD, TABLE_STATUSES.difference.toString() );
                                            tableRightFieldDiff.put( STATUS_FIELD, TABLE_STATUSES.difference.toString() );

                                            dmDiffLeft.getJSONObject( tableName ).getJSONArray( "fields" ).put( tableLeftFieldDiff );
                                            dmDiffRight.getJSONObject( tableName ).getJSONArray( "fields" ).put( tableRightFieldDiff );

                                            dmDiffLeft.getJSONObject( tableName ).put( STATUS_FIELD, TABLE_STATUSES.difference.toString() );
                                            dmDiffRight.getJSONObject( tableName ).put( STATUS_FIELD, TABLE_STATUSES.difference.toString() );

                                        }

                                        fieldNotFound = false;

                                        break;

                                    }

                                }

                                // The field is present in the Left Table, but is not in the Right Table
                                if( fieldNotFound ) {

                                    if( dmDiffLeft.isNull( tableName ) || dmDiffLeft.getJSONObject( tableName ).isNull( "fields" ) ) dmDiffLeft.put( tableName, new JSONObject( "{ \"fields\": " + new JSONArray() + " }" ) );
                                    if( dmDiffRight.isNull( tableName ) || dmDiffRight.getJSONObject( tableName ).isNull( "fields" )  ) dmDiffRight.put( tableName, new JSONObject( "{ \"fields\": " + new JSONArray() + " }" ) );

                                    tableLeftField.put( STATUS_FIELD, TABLE_STATUSES.plus.toString() );
                                    dmDiffLeft.getJSONObject( tableName ).getJSONArray( "fields" ).put( tableLeftField );

                                    JSONObject tableRightField = new JSONObject( tableLeftField.toString() );
                                    tableRightField.put( STATUS_FIELD, TABLE_STATUSES.minus.toString() );
                                    dmDiffRight.getJSONObject( tableName ).getJSONArray( "fields" ).put( tableRightField );

                                    dmDiffLeft.getJSONObject( tableName ).put( STATUS_FIELD, TABLE_STATUSES.difference.toString() );
                                    dmDiffRight.getJSONObject( tableName ).put( STATUS_FIELD, TABLE_STATUSES.difference.toString() );

                                }

                            }
                        }

                    } catch( JSONException e ) {

                        logger.error( e.getMessage(), e);

                    }

                }
            }

        } catch( JSONException e ) {

            logger.error(e.getMessage(), e);

        }

    }

    public static boolean compareTableFields( JSONObject tableLeftField, JSONObject tableRightField, JSONObject tableLeftFieldDiff, JSONObject tableRightFieldDiff ) {

        for( MysqlFieldType mysqlFieldType : MysqlFieldType.values() ) {

            try {

                final String FIELD_TYPE = mysqlFieldType.toString().toLowerCase();

                if( !tableLeftField.get( FIELD_TYPE ).equals( tableRightField.get( FIELD_TYPE ) )) {

                    tableLeftFieldDiff.put( FIELD_TYPE, tableLeftField.getString( FIELD_TYPE ) != null ? tableLeftField.getString( FIELD_TYPE ) : JSONObject.NULL );
                    tableRightFieldDiff.put( FIELD_TYPE, tableRightField.getString( FIELD_TYPE ) != null ? tableRightField.getString( FIELD_TYPE ) : JSONObject.NULL );

                }

            } catch( JSONException je ) {

                logger.error( je.getMessage(), je );

            }

        }

        if( tableLeftFieldDiff.length() > 0 || tableRightFieldDiff.length() > 0 ) {

            try {

                final String FIELD = MysqlFieldType.Field.toString().toLowerCase();

                tableLeftFieldDiff.put( FIELD, tableLeftField.getString( FIELD ) != null ? tableLeftField.getString( FIELD ) : JSONObject.NULL );
                tableRightFieldDiff.put( FIELD, tableRightField.getString( FIELD ) != null ? tableRightField.getString( FIELD ) : JSONObject.NULL );

            } catch( JSONException je ) {

                logger.error( je.getMessage(), je );

            }

            return true;

        }

        return false;

    }

    public static void compareTableContents( JSONObject dmJsonLeft, JSONObject dmJsonRight, JSONObject dmDiffLeft, JSONObject dmDiffRight, JSONObject dsDataModelLeft, JSONObject dsDataModelRight, JSONObject options ) {

        try {

            if( options != null && !options.isNull( "compareContentTables" ) ) {

                @SuppressWarnings("unchecked")
                Iterator<String> keys1 = options.getJSONObject( "compareContentTables" ).keys();
                while( keys1.hasNext() ) {

                    String tableName = keys1.next();

                    logger.info( tableName );

                    if( !dmJsonLeft.isNull( tableName ) && !dmJsonRight.isNull( tableName ) ) {

                        JSONArray header = new JSONArray();

                        // Check if a filter is enabled about the fields to verify
                        if( !options.getJSONObject( "compareContentTables" ).getJSONObject( tableName ).isNull( "fields" ) ) {

                            header = options.getJSONObject( "compareContentTables" ).getJSONObject( tableName ).getJSONArray( "fields" );

                        } else {

                            if( !dmJsonLeft.getJSONObject( tableName ).isNull( "fields" ) && !dmJsonRight.getJSONObject( tableName ).isNull( "fields" ) ) {

                                // Get all fields of the Left Table
                                JSONArray tableLeftAllFields = dmJsonLeft.getJSONObject( tableName ).getJSONArray( "fields" );

                                // Get all fields of the Right Table
                                JSONArray tableRightAllFields = dmJsonRight.getJSONObject( tableName ).getJSONArray( "fields" );

                                for( int i = 0; i < tableLeftAllFields.length(); i++ ) {

                                    // Get single field of the Left Table
                                    JSONObject tableLeftField = tableLeftAllFields.getJSONObject( i );

                                    for( int j = 0; j < tableRightAllFields.length(); j++ ) {

                                        // Get single field of the Right Table
                                        JSONObject tableRightField = tableRightAllFields.getJSONObject( i );

                                        if( tableLeftField.getString( MysqlFieldType.Field.toString().toLowerCase() ).equals( tableRightField.getString( MysqlFieldType.Field.toString().toLowerCase() ) ) ) {

                                            header.put( tableLeftField.getString( MysqlFieldType.Field.toString().toLowerCase() ) );

                                        }

                                    }

                                }

                            }
                        }

                        // Create content of the Left Table
                        JSONArray tableLeftContent = new JSONArray();
                        DataModelCompare.createTableContent( dsDataModelLeft, header, tableLeftContent, tableName);

                        // Create content of the Right Table
                        JSONArray tableRightContent = new JSONArray();
                        DataModelCompare.createTableContent( dsDataModelRight, header, tableRightContent, tableName );

                        int rowLeftIndex;
                        int rowRightIndex;
                        int lastRowRightIndex = 1;

                        for( rowLeftIndex = 1; rowLeftIndex < tableLeftContent.length(); rowLeftIndex++ ) {

                            for( rowRightIndex = lastRowRightIndex; rowRightIndex < tableRightContent.length(); rowRightIndex++ ) {

                                if( ( tableLeftContent.getJSONArray( rowLeftIndex ).toString() ).equals( tableRightContent.getJSONArray( rowRightIndex ).toString() ) ) {

                                    tableLeftContent.remove( rowLeftIndex );
                                    tableRightContent.remove( rowRightIndex );

                                    rowLeftIndex--;
                                    lastRowRightIndex = rowRightIndex - 1;

                                    break;

                                }

                            }

                        }

                        int tableLeftSize = tableLeftContent.length();
                        int tableRightSize = tableRightContent.length();

                        int indexColStatus = header.length();

                        DataModelCompare.compareTableRowsContent( tableLeftContent, tableRightContent, tableLeftSize, tableRightSize, indexColStatus );

                        DataModelCompare.compareTableRowsContent( tableRightContent, tableLeftContent, tableRightSize, tableLeftSize, indexColStatus );

                        // Sort Tables content
                        tableLeftContent = DataModelCompare.sortTableContent( tableLeftContent, 0, tableLeftSize );

                        tableRightContent = DataModelCompare.sortTableContent( tableRightContent, 0, tableRightSize );

                        if( dmDiffLeft.isNull( tableName ) ) {

                            dmDiffLeft.put( tableName, new JSONObject( "{ \"content\": " + tableLeftContent + " }" ) );

                        } else {

                            dmDiffLeft.getJSONObject( tableName ).put( "content", tableLeftContent );

                        }

                        if( dmDiffRight.isNull( tableName ) ) {

                            dmDiffRight.put( tableName, new JSONObject( "{ \"content\": " + tableRightContent + " }" ) );

                        } else {

                            dmDiffRight.getJSONObject( tableName ).put( "content", tableRightContent );

                        }

                        dmDiffLeft.getJSONObject( tableName ).put( STATUS_FIELD, TABLE_STATUSES.difference.toString() );
                        dmDiffRight.getJSONObject( tableName ).put( STATUS_FIELD, TABLE_STATUSES.difference.toString() );

                        logger.info( "LEFT: " + tableLeftContent.length() + " - RIGHT: " + tableRightContent.length() );

                    } else {

                        if( dmJsonLeft.isNull( tableName ) ) { dmDiffLeft.put( tableName, new JSONObject( "{\"_STATUS_\": " + TABLE_STATUSES.minus.toString() + "}" ) ); }
                        else { dmDiffLeft.put( tableName, new JSONObject( "{\"_STATUS_\": " + TABLE_STATUSES.plus.toString() + "}" ) ); }

                        if( dmDiffRight.isNull( tableName ) ) { dmDiffRight.put( tableName, new JSONObject( "{\"_STATUS_\": " + TABLE_STATUSES.minus.toString() + "}" ) ); }
                        else { dmDiffRight.put( tableName, new JSONObject( "{\"_STATUS_\": " + TABLE_STATUSES.plus.toString() + "}" ) ); }

                    }

                }

            }

        } catch( JSONException je ) {

            logger.error( je.getMessage(), je );

        }

    }

    public static void createTableContent( JSONObject dataSource, JSONArray header, JSONArray table, String tableName ) {

        try {

            StringBuffer SELECT_FIELDS_BUFFER = new StringBuffer();

            for( int i = 0; i < header.length(); i++ ) { SELECT_FIELDS_BUFFER = SELECT_FIELDS_BUFFER.append( header.getString( i ) ).append( ", " ); }

            JSONArray table_header = new JSONArray( header.toString() );

            table_header.put( STATUS_FIELD );

            table.put( table_header );

            String SELECT_FIELDS = SELECT_FIELDS_BUFFER.substring( 0, SELECT_FIELDS_BUFFER.length() - 2 );

            StringBuffer query_buffer = new StringBuffer();

            query_buffer = query_buffer.append( "SELECT " ).append( SELECT_FIELDS ).append( " FROM " ).append( tableName ).append( " ORDER BY " ).append( header.getString( 0 ) ).append(";");

            Mysql mysql = new Mysql( dataSource );

            ResultSet rs = mysql.execQuery( query_buffer.toString() );

            while( rs.next() ) {

                JSONArray row = new JSONArray();

                for( int i = 0; i < header.length(); i++ ) {

                    row.put( rs.getString( header.getString( i ) ) );

                }

                row.put( TABLE_STATUSES.unknown.toString() );

                table.put( row );

            }

        } catch( JSONException e ) {

            logger.error( e.getMessage(), e );

        } catch( SQLException e ) {

            logger.error( e.getMessage(), e );

        }

    }

    public static void compareTableRowsContent( JSONArray tableLeftContent, JSONArray tableRightContent, int tableLeftSize , int tableRightSize, int indexColStatus ) {

        int rowLeftIndex;
        int rowRightIndex;
        int lastRowRightIndex = 1;

        for( rowLeftIndex = 1; rowLeftIndex < tableLeftSize; rowLeftIndex++ ) {

            JSONArray currentLeftRow = tableLeftContent.getJSONArray( rowLeftIndex );

            boolean keyFound = false;

            if( ( tableLeftContent.getJSONArray( rowLeftIndex ).getString( indexColStatus ) ).equals( TABLE_STATUSES.unknown.toString() ) ) {

                for( rowRightIndex = lastRowRightIndex; rowRightIndex < tableRightSize; rowRightIndex++ ) {

                    JSONArray currentRightRow = tableRightContent.getJSONArray( rowRightIndex );

                    int compare = currentLeftRow.getString( 0 ).compareToIgnoreCase( currentRightRow.getString( 0 ) );

                    if( compare < 0 ) {

                        lastRowRightIndex = rowRightIndex;

                        break;

                    } else {

                        if( compare == 0 ) {

                            currentLeftRow.put( indexColStatus, TABLE_STATUSES.difference.toString() );

                            currentRightRow.put( indexColStatus, TABLE_STATUSES.difference.toString() );

                            lastRowRightIndex = rowRightIndex;

                            keyFound = true;

                            break;

                        }

                    }

                }

                if( !keyFound ) {

                    currentLeftRow.put( indexColStatus, TABLE_STATUSES.plus.toString() );

                    tableRightContent.put( new JSONArray( currentLeftRow.toString() ) );
                    tableRightContent.getJSONArray( ( tableRightContent.length() - 1 ) ).put( indexColStatus, TABLE_STATUSES.minus.toString() );

                }

            };

        }

    }

    public static JSONArray sortTableContent( JSONArray table, int indexFieldToSort, int indexUnsortedContent) {

        JSONArray sortedTableContent = new JSONArray();

        int sizeFirstArray =  indexUnsortedContent;
        int sizeSecondArray = table.length();

        int i = 1, j = indexUnsortedContent;

        sortedTableContent.put( table.getJSONArray( 0 ) );

        while( i < sizeFirstArray && j < sizeSecondArray ) {

            String valueFirstArray = table.getJSONArray( i ).getString( indexFieldToSort ).toLowerCase();
            String valueSecondArray = table.getJSONArray( j ).getString( indexFieldToSort ).toLowerCase();

            if( valueFirstArray.compareToIgnoreCase( valueSecondArray ) <= 0 ) { sortedTableContent.put( table.getJSONArray( i ) ); i++; }
            else { sortedTableContent.put( table.getJSONArray( j ) ); j++; }

        }

        while( i < sizeFirstArray ) {

            sortedTableContent.put( table.getJSONArray( i ) ); i++;

        }

        while( j < sizeSecondArray ) {

            sortedTableContent.put( table.getJSONArray( j ) ); j++;

        }

        return sortedTableContent;

    }

}
