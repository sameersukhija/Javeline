package com.lumata.expression.operators.a;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.annotations.mysql.Column;
import com.lumata.common.testing.database.MysqlColumn;
import com.lumata.common.testing.exceptions.DataModelException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.model.DataModel;
import com.lumata.common.testing.system.Environment;


public class ClassGenerator {

	private static final Logger logger = LoggerFactory.getLogger( ClassGenerator.class );
	
	private final boolean add_annotation = true;
		
	private final String packageClass = "com.lumata.expression.operators.testing.pojo.autogenerator"; 
	
	private StringBuilder loggerClass;
	
	private StringBuilder pojoClassName;
	private StringBuilder pojoClass;
	
	private StringBuilder importClasses;
		
	private StringBuilder fieldsPojoClass;
	private StringBuilder fieldsEnumPojoClass;
	private StringBuilder fieldsEnumFieldsPojoClass;
	private StringBuilder fieldsToStringPojoClass;
	
	private StringBuilder methodsPojoClass;
	
	private StringBuilder resultSetConstructorBodyPojoClass;
	
	private StringBuilder jsonObjectConstructorBodyPojoClass;
	
	Map<String, Boolean> ImportTypes = new HashMap<String, Boolean>() {
		
		{
			put("String", false);			
		}
		
	};
	
	public enum ClassType {
		
		pojo,
		dao;
		
	}
	
	public enum FieldTypes { 
		
		STRING("String"), 
		INT("int"),
		FLOAT("float"),
		DOUBLE("double"),
		ENUM("enum"),
		SET("set"),
		TIMESTAMP("timestamp"),
		DATETIME("Date");  
		
		private String value;
		
		public String getValue() {
			
			return this.value;
			
		}
		
		FieldTypes( String value ) {
			
			this.value = value;
			
		}
	
	}
	
	public enum AnnotationColumn { 
			
		table, 
		field,
		type,
		mysqlType,
		javaType,
		unsigned,
		isNull,
		key,
		defaultValue,
		extra,
		length,
		getMethod,
		setMethod
		
	}
	
	public enum MethodTypes { get, set }
	
	public ClassGenerator() {}
	
	public void createDAO( Environment env, String tenant ) throws DataModelException, IOFileException {
		
		logger.info( "Get JSON Data Model" );
		
		DataModel dataModel = new DataModel( tenant, env.getDataSource( tenant ), null );
			
		JSONObject schema = dataModel.getDataModel();
		
		// Store Data Model
		//IOFileUtils.saveResource( schema.toString(), "output/data_model", "datamodel.json" );
		
		logger.info( "Get File Path" );
		
		StringBuilder filePath = new StringBuilder();
    	
		filePath.append( System.getProperty( "user.dir" ) ).append( "/src/main/java/" ).append( packageClass.toString().replaceAll( "[.]" , "/" ) );
    	    	
		try {
					
			Iterator<?> table_names = schema.keys();

			while( table_names.hasNext() ){
	           	
	        	loggerClass = new StringBuilder();
	        	
	        	pojoClass = new StringBuilder();
	        	        	
	        	fieldsPojoClass = new StringBuilder();
	        	fieldsEnumPojoClass = new StringBuilder();
	        	fieldsEnumFieldsPojoClass = new StringBuilder();
	        	fieldsToStringPojoClass = new StringBuilder();
	        	
	        	methodsPojoClass = new StringBuilder();
	        	
	        	resultSetConstructorBodyPojoClass = new StringBuilder();
	        	
	        	jsonObjectConstructorBodyPojoClass = new StringBuilder();
	        	
	        	boolean addImportDate = true;
	        		        	
	        	logger.info( "Get Table Name" );
	        	
	        	String tableName = (String)table_names.next();
	        	
	        	JSONObject table = schema.getJSONObject( tableName );
	        	
	        	JSONArray fields = table.getJSONArray( DataModel.TableAttributes.FIELDS.getValue() );
	        	
	        	
	        	logger.info( "Get Class Name" );
	        	     	
	        	pojoClassName = this.createClassName( tableName );
	        	
	        	
	        	logger.info( "Put Packages to import" );
	        	
	        	importClasses = new StringBuilder();
	        	//importClasses.append( "import org.slf4j.Logger;\n" );
	        	//importClasses.append( "import org.slf4j.LoggerFactory;\n" );
	        	importClasses.append( "import java.sql.ResultSet;\n" );
	        	importClasses.append( "import java.sql.SQLException;\n" );
	        	importClasses.append( "import org.json.JSONException;\n" );
	        	importClasses.append( "import org.json.JSONObject;\n" );
	        	if( add_annotation ) { 
	        		importClasses.append( "\nimport com.lumata.common.annotations.mysql.Tables;\n" );
	        		importClasses.append( "import com.lumata.common.annotations.mysql.Column;\n" );
	        	}
	        	
	        	
	        	logger.info( "Put Logger Class" );
					        	
	        	loggerClass.append( "\tprivate static final Logger logger = LoggerFactory.getLogger( " ).append( pojoClassName ).append( ".class );" );
				
	        	
	        	logger.info( "Put enum Fields" );
				
	        	fieldsEnumFieldsPojoClass = new StringBuilder();
	        	fieldsEnumFieldsPojoClass.append( "\tpublic enum Fields { " );
	        		
	        	System.out.println( tableName );
	        	
	        	for( int i = 0; i < fields.length(); i++ ) {
	        		
	        		JSONObject column = fields.getJSONObject( i );
	        		
	        		MysqlColumn mysqlColumn = new MysqlColumn( tableName, column );
	               	        		
	        		
	        		fieldsEnumFieldsPojoClass.append( mysqlColumn.getField() ).append( ", " );
	        			        			        		
	        		//StringBuilder methodName = this.createClassName( mysqlColumn.getField() );
	        		
	        		this.addToStringField( mysqlColumn );	        			        		
	        		
	        		String typeValue = this.replaceTypes( mysqlColumn.getType() ).toUpperCase();
	        		
	        		// Add field     
	        		this.addField( mysqlColumn );
	        		
	        		// Add method
	        		this.addMethods( mysqlColumn );
	        		
	        		// Add ResultSet field
	        		this.addResultSetField( mysqlColumn );	
	        		
	        		// Add Enum field
	        		//if( mysqlColumn.getMysqlType().equals( "enum" ) ) { this.addEnumField( mysqlColumn ); }
	        		
	        		switch( ClassGenerator.FieldTypes.valueOf( typeValue ) ) {
	        		
	        			case STRING: {
	        				
	        				//this.addField( ClassGenerator.FieldTypes.STRING, mysqlColumn.getField() );
	        				
	        				//this.addResultSetField( ClassGenerator.FieldTypes.STRING, mysqlColumn.getField() );	
	        				
	        				this.addJSONObjectField( ClassGenerator.FieldTypes.STRING, mysqlColumn.getField() );
	        				
	        				//this.addMethods( ClassGenerator.FieldTypes.STRING, methodName.toString(), mysqlColumn.getField() ); 
	        				
	        				break;
	        			}
	        			case INT: {
	        				
	        				//this.addField( ClassGenerator.FieldTypes.INT, mysqlColumn.getField() );
	        				
	        				//this.addResultSetField( ClassGenerator.FieldTypes.INT, mysqlColumn.getField() );	

	        				this.addJSONObjectField( ClassGenerator.FieldTypes.INT, mysqlColumn.getField() );
	        				
	        				//this.addMethods( ClassGenerator.FieldTypes.INT, methodName.toString(), mysqlColumn.getField() ); 
	        					        				
	        				break;
	        			}
	        			case FLOAT: {
	        				
	        				//this.addField( ClassGenerator.FieldTypes.FLOAT, mysqlColumn.getField() );
	        				
	        				//this.addResultSetField( ClassGenerator.FieldTypes.FLOAT, mysqlColumn.getField() );	
	        				
	        				this.addJSONObjectField( ClassGenerator.FieldTypes.FLOAT, mysqlColumn.getField() );
	        				
	        				//this.addMethods( ClassGenerator.FieldTypes.FLOAT, methodName.toString(), mysqlColumn.getField() ); 
	        				
	        				break;
	        			}
	        			case ENUM: {
	        				
	        				//this.addField( ClassGenerator.FieldTypes.STRING, mysqlColumn.getField() );
	        				
	        				//this.addEnumField( methodName.toString(), mysqlColumn.getType() );
	        				
	        				//this.addResultSetField( ClassGenerator.FieldTypes.STRING, mysqlColumn.getField() );
	        				
	        				this.addJSONObjectField( ClassGenerator.FieldTypes.STRING, mysqlColumn.getField() );
	        				
	        				//this.addMethods( ClassGenerator.FieldTypes.STRING, methodName.toString(), mysqlColumn.getField() ); 
	        					        				
	        				break;
	        			}
	        			case TIMESTAMP: {
	        				
	        				//this.addField( ClassGenerator.FieldTypes.STRING, mysqlColumn.getField() );
	        				
	        				//this.addResultSetField( ClassGenerator.FieldTypes.STRING, mysqlColumn.getField() );
	        				
	        				this.addJSONObjectField( ClassGenerator.FieldTypes.STRING, mysqlColumn.getField() );
	        				
	        				//this.addMethods( ClassGenerator.FieldTypes.STRING, methodName.toString(), mysqlColumn.getField() ); 
	        				
	        				break;
	        			}
	        			case DATETIME: {
	        				
	        				//this.addField( ClassGenerator.FieldTypes.DATETIME, mysqlColumn.getField() );
	        				
	        				//this.addResultSetField( ClassGenerator.FieldTypes.DATETIME, mysqlColumn.getField() );

	        				this.addJSONObjectField( ClassGenerator.FieldTypes.DATETIME, mysqlColumn.getField() );
	        				
	        				if( addImportDate ) { 
	        					
	        					importClasses.append( "import java.util.Date;\n" ); 
	        					importClasses.append( "import java.text.ParseException;\n" );
	        					importClasses.append( "import com.lumata.common.testing.validating.Format;\n" );
	        					
	        					addImportDate = false; 
	        					
	        				}
	        					        				
	        				//this.addMethods( ClassGenerator.FieldTypes.DATETIME, methodName.toString(), mysqlColumn.getField() ); 
	        				
	        				break;
	        			}
	        		
	        		}
	        			        		
	        	}
	        	
	        	fieldsEnumFieldsPojoClass.setLength( fieldsEnumFieldsPojoClass.length() - 2  );
	        	fieldsEnumFieldsPojoClass.append( " }" );
	        	
	        	pojoClass.append( "package " )
	        				.append( packageClass )
	        				.append( ";\n\n" )
	        				.append( importClasses )
	        				.append( "\n\n" )
				        	.append( "public class " )
							.append( pojoClassName )
							.append( " { \n\n" )
							//.append( loggerClass )
							//.append( "\n\n" )
							.append( fieldsEnumFieldsPojoClass )
							.append( "\n\n" )
							.append( fieldsEnumPojoClass )							
							.append( ( fieldsEnumPojoClass.length() > 0 ) ? "\n\n" : "" )
							.append( fieldsPojoClass )
							.append( "\n" )
							.append( this.createDefaultConstructor( pojoClassName.toString() ) )
							.append( this.createResultSetConstructor( pojoClassName.toString(), resultSetConstructorBodyPojoClass.toString() ))
							.append( this.createJSONObjectConstructor( pojoClassName.toString(), jsonObjectConstructorBodyPojoClass.toString(), addImportDate ) )
							.append( methodsPojoClass )
							.append( this.createToStringMethod() )
							.append( "\n }" );
				        		        	
	        	//IOFileUtils.saveResource( pojoClass.toString(), "output/classes", pojoClassName + ".java");
	        	
	        	logger.info( "Store Class" );
	        	
	        	IOFileUtils.saveFile( pojoClass.toString(), filePath.toString(), pojoClassName + ".java");
	      	                        	
	        }
	
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}  catch( IOFileException e ) {
			
			logger.error( e.getMessage(), e );
			
		}         
		
	}
	
	public String replaceTypes( String type ) {
		
		return type.replaceAll( "^varchar.*", ClassGenerator.FieldTypes.STRING.name() )
					.replaceAll( "^char.*", ClassGenerator.FieldTypes.STRING.name() )
					.replaceAll( "^blob.*", ClassGenerator.FieldTypes.STRING.name() )
					.replaceAll( "^longblob.*", ClassGenerator.FieldTypes.STRING.name() )
					.replaceAll( "^text.*", ClassGenerator.FieldTypes.STRING.name() )
					.replaceAll( "^int.*", ClassGenerator.FieldTypes.INT.name() )
					.replaceAll( "^bigint.*", ClassGenerator.FieldTypes.INT.name() )
					.replaceAll( "^mediumint.*", ClassGenerator.FieldTypes.INT.name() )
					.replaceAll( "^smallint.*", ClassGenerator.FieldTypes.INT.name() )
					.replaceAll( "^tinyint.*", ClassGenerator.FieldTypes.INT.name() )
					.replaceAll( "^float.*", ClassGenerator.FieldTypes.FLOAT.name() )
					.replaceAll( "^decimal.*", ClassGenerator.FieldTypes.FLOAT.name() )
					.replaceAll( "^double.*", ClassGenerator.FieldTypes.FLOAT.name() )
					.replaceAll( "^enum.*", ClassGenerator.FieldTypes.ENUM.name() )
					.replaceAll( "^set.*", ClassGenerator.FieldTypes.ENUM.name() )
					.replaceAll( "^timestamp.*", ClassGenerator.FieldTypes.TIMESTAMP.name() )
					.replaceAll( "^datetime.*", ClassGenerator.FieldTypes.DATETIME.name() )
					.replaceAll( "^date.*", ClassGenerator.FieldTypes.DATETIME.name() )
					.replaceAll( "^time.*", ClassGenerator.FieldTypes.DATETIME.name() );
		
	}
	
	public StringBuilder createDefaultConstructor( String className ) {
		
		StringBuilder constructor = new StringBuilder();
		
		constructor.append( "\tpublic " ).append( className ).append( "() {} \n" );
		
		return constructor;
		
	}
	
	public StringBuilder createResultSetConstructor( String className, String body ) {
		
		StringBuilder constructor = new StringBuilder();
		
		constructor.append( "\n\tpublic " )
					.append( className )
					.append( "( " )
					.append( "ResultSet rs" )
					.append( " ) throws SQLException {\n\n" )
					.append( body )
					.append( "\n\t}\n" );
		
		return constructor;
		
	}
	
	public StringBuilder createJSONObjectConstructor( String className, String body, boolean addImportDate ) {
		
		StringBuilder constructor = new StringBuilder();
		
		constructor.append( "\n\tpublic " )
					.append( className )
					.append( "( " )
					.append( "JSONObject jo" )
					.append( " ) throws JSONException" )
					.append( ( addImportDate == false ) ? ", ParseException" : "" )
					.append( " {\n\n" )
					.append( body )
					.append( "\n\t}\n" );
		
		return constructor;
		
	}
	
	public StringBuilder createClassName( String field ) {
		
		StringBuilder className = new StringBuilder();
		
		String[] methodNameParts = field.split( "_" );
		
		for(int i = 0; i < methodNameParts.length; i++ ) {
			 
			className.append( Character.toUpperCase( methodNameParts[i].charAt(0)))
						.append(methodNameParts[i].substring(1));

		}
	
		return className;
		
	}
			
	public StringBuilder createAnnotationColumn( MysqlColumn mysqlColumn ) {
		
		StringBuilder annotationColumnBody = new StringBuilder();
		
		annotationColumnBody.append( "\t@Column(\n" )
							.append("\t\t\t").append( AnnotationColumn.table.name() ).append( " = \"" ).append( mysqlColumn.getTable() ).append("\",\n")
							.append("\t\t\t").append( AnnotationColumn.field.name() ).append( " = \"" ).append( mysqlColumn.getField() ).append("\",\n")
							.append("\t\t\t").append( AnnotationColumn.type.name() ).append( " = \"" ).append( mysqlColumn.getType() ).append("\",\n")
							.append("\t\t\t").append( AnnotationColumn.mysqlType.name() ).append( " = \"" ).append( mysqlColumn.getMysqlType() ).append("\",\n")
							.append("\t\t\t").append( AnnotationColumn.javaType.name() ).append( " = \"" ).append( mysqlColumn.getJavaType() ).append("\",\n")
							.append("\t\t\t").append( AnnotationColumn.isNull.name() ).append( " = " ).append( mysqlColumn.getNull() ).append(",\n")
							.append("\t\t\t").append( AnnotationColumn.key.name() ).append( " = \"" ).append( mysqlColumn.getKey() ).append("\",\n")
							.append("\t\t\t").append( AnnotationColumn.defaultValue.name() ).append( " = \"" ).append( mysqlColumn.getDefaultValue() ).append("\",\n")
							.append("\t\t\t").append( AnnotationColumn.extra.name() ).append( " = \"" ).append( mysqlColumn.getExtra() ).append("\",\n")
							.append("\t\t\t").append( AnnotationColumn.length.name() ).append( " = " ).append( mysqlColumn.getLength() ).append(",\n")
							.append("\t\t\t").append( AnnotationColumn.getMethod.name() ).append( " = \"" ).append( mysqlColumn.getGetMethod() ).append("\",\n")
							.append("\t\t\t").append( AnnotationColumn.setMethod.name() ).append( " = \"" ).append( mysqlColumn.getSetMethod() ).append("\"\n\t)\n");
		
		return annotationColumnBody;		
		
	}
	
	public StringBuilder createMethod( ClassGenerator.MethodTypes methodType, MysqlColumn mysqlColumn ) {
		
		StringBuilder method = new StringBuilder();
		
		StringBuilder body = new StringBuilder();
		
		if( methodType.equals( ClassGenerator.MethodTypes.get ) ) {
			
			body.append( "return this." ).append( mysqlColumn.getField() ).append( ";"); 
		
		} else {
			
			body.append( "this." ).append( mysqlColumn.getField() ).append( " = " ).append( mysqlColumn.getField() ).append( ";" ); 
			
		}
				
		method.append( "\tpublic " )
				.append( ( methodType.equals( ClassGenerator.MethodTypes.set ) ? "void": mysqlColumn.getJavaType() ) )
				.append( " " )
				.append( ( methodType.equals( ClassGenerator.MethodTypes.set ) ? mysqlColumn.getSetMethod() : mysqlColumn.getGetMethod() ) )
				.append( "(")
				.append( ( methodType.equals( ClassGenerator.MethodTypes.set ) ? " " + mysqlColumn.getJavaType() + " " + mysqlColumn.getField() + " ": "" ) )
				.append(") {" )
				.append( "\n\n\t\t" )
				.append( body )
				.append( "\n\n\t}" );
		
		return method;
		
	}

	public StringBuilder createToStringMethod( ) {
		
		StringBuilder toStringMethod = new StringBuilder();
		
		StringBuilder body = new StringBuilder();
				
		toStringMethod.append( "\n\tpublic String toString() {\n\n\t\tStringBuilder str = new StringBuilder();\n\n\t\tstr.append( \"{ \" )" )
						.append( fieldsToStringPojoClass.substring( 0, fieldsToStringPojoClass.length() - 5 ) )
						.append( "\" )\n\t\t\t.append( \" }\" );\n\n\t\treturn str.toString();\n\n\t}\n" );
				
		return toStringMethod;
		
	}

	public void addField( MysqlColumn mysqlColumn ) {
		
		if( add_annotation ) {
			
			fieldsPojoClass.append( this.createAnnotationColumn( mysqlColumn ) );
			
		}
		
		fieldsPojoClass.append( "\tprivate " ).append( mysqlColumn.getJavaType() ).append( " " ).append( mysqlColumn.getField() ).append( ";\n" );
		
		if( add_annotation ) { fieldsPojoClass.append("\n"); }
				
	}
	
	public void addMethods( MysqlColumn mysqlColumn ) {
		
		methodsPojoClass.append( "\n" )
						/*.append( this.createMethod( ClassGenerator.MethodTypes.get, fieldType, methodName, field ) )*/
						.append( this.createMethod( ClassGenerator.MethodTypes.get, mysqlColumn ) )
						.append( "\n\n" )
						/*.append( this.createMethod( ClassGenerator.MethodTypes.set, fieldType, methodName, field ) )*/
						.append( this.createMethod( ClassGenerator.MethodTypes.set, mysqlColumn ) )
						.append( "\n" );
				
	}

	public void addResultSetField( MysqlColumn mysqlColumn/*ClassGenerator.FieldTypes fieldType, String fieldName*/ ) {
		System.out.println( "FIELD: " + mysqlColumn.getType()  + "JAVATYPE: " + mysqlColumn.getJavaType()  );
		resultSetConstructorBodyPojoClass.append( "\t\tthis." )
											.append( mysqlColumn.getField() )
											.append( " = rs.get" )
											/*.append( Character.toString( fieldType.getValue().charAt(0) ).toUpperCase() )
											.append( fieldType.getValue().substring(1) )*/
											.append( MysqlColumn.JavaTypes.valueOf( mysqlColumn.getJavaType() ).getResultSetType() )
											.append( "( " )
											.append( pojoClassName )
											.append( ".Fields." )
											.append( mysqlColumn.getField() )
											.append( ".name() );\n" );
				
	}
	
	public void addJSONObjectField( ClassGenerator.FieldTypes fieldType, String fieldName ) {
		
		boolean datetime_field = false;
		boolean float_field = false;
		
		if( fieldType.equals( ClassGenerator.FieldTypes.DATETIME ) ) {
			
			fieldType = ClassGenerator.FieldTypes.STRING;
			
			datetime_field = true;
			
		}
		
		if( fieldType.equals( ClassGenerator.FieldTypes.FLOAT ) ) {
			
			fieldType = ClassGenerator.FieldTypes.DOUBLE;
			
			float_field = true;
			
		}
			
		jsonObjectConstructorBodyPojoClass.append( "\t\tthis." )
											.append( fieldName )
											.append( " = " )
											.append( ( datetime_field == true ) ? "Format.getMysqlDateTime( " : "" )
											.append( ( float_field == true ) ? "(float)" : "" )
											.append( "jo.get" )
											.append( Character.toString( fieldType.getValue().charAt(0) ).toUpperCase() )
											.append( fieldType.getValue().substring(1) )
											.append( "( " )
											.append( pojoClassName )
											.append( ".Fields." )
											.append( fieldName )
											.append( ".name() )" )
											.append( ( datetime_field == true ) ? " )" : "" )		
											.append( ";\n" );
				
	}
	
	public void addToStringField( MysqlColumn mysqlColumn ) {
		
		fieldsToStringPojoClass.append( "\n\t\t\t.append( \"\\\"" )
								.append( mysqlColumn.getField() )
								.append( "\\\": \\\"\" ).append( this." )
								.append( mysqlColumn.getGetMethod() )
								.append( "() ).append( \"\\\", \" )" );
		
	}
	
	public void addEnumField( MysqlColumn mysqlColumn ) {
		//public final Set<String> Bearers = new HashSet<String>(Arrays.asList("1", "2", "5"));
		fieldsEnumPojoClass.append( "\tpublic enum " )
							.append( mysqlColumn.getField() )
							.append( " " )
							.append( mysqlColumn.getType().replaceAll( "enum|set", "" ).replaceAll( "\\(", "{ " ).replaceAll( "'", "" ).replaceAll( ",", ", " ).replaceAll( "\\)", " }" ) )
							.append( ";\n" );
				
	}
	
}
