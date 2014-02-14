package com.lumata.common.testing.database;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MysqlColumn {

	private static final  Logger logger = LoggerFactory.getLogger( MysqlColumn.class );
	
	private String table;
	private String field;
	private String type;
	private String[] type_values;
	private String mysql_type;
	private String java_type;
	private String category_type;
	private boolean is_null;
	private String key;
	private String default_value;
	private String extra;
	private int length;
	private int decimal;
	private boolean unsigned;
	private String get_method;
	private String set_method;
	
	public enum Fields { 
		
		FIELD, 
		TYPE, 
		NULL, 
		KEY, 
		DEFAULT, 
		EXTRA;
	
		public String getValue() {
		
			return this.name().toLowerCase();			
			
		}
		
	}
	
	public enum CategoryTypes {
		Boolean, Number, String, Collection, Date;
	}
	
	public enum JavaTypes {
		
		Boolean {
			
			public String[] getPackages() {
				
				String[] packages = { "java.lang.Boolean" };
				
				return packages;
				
			}
			
			public String getPrimitiveType() { return "boolean"; }

			public String getResultSetType() { return "Boolean"; }
			
			public String getJSONType() { return "Boolean"; }
			
			public boolean getJSONTypeCasting() { return false; }
			
			public String getCategoryTypes() { return CategoryTypes.Boolean.name(); }
			
		},
		Byte {
			
			public String[] getPackages() {
				
				String[] packages = { "java.lang.Byte" };
				
				return packages;
				
			}
			
			public String getPrimitiveType() { return "byte"; }

			public String getResultSetType() { return "Byte"; }
			
			public String getJSONType() { return "Int"; }
			
			public boolean getJSONTypeCasting() { return true; }
			
			public String getCategoryTypes() { return CategoryTypes.Number.name(); }
			
		},
		Short {
			
			public String[] getPackages() {
				
				String[] packages = { "java.lang.Short" };
				
				return packages;
				
			}
			
			public String getPrimitiveType() { return "short"; }

			public String getResultSetType() { return "Short"; }
			
			public String getJSONType() { return "Int"; }
			
			public boolean getJSONTypeCasting() { return true; }
			
			public String getCategoryTypes() { return CategoryTypes.Number.name(); }
			
		},
		Integer {
			
			public String[] getPackages() {
				
				String[] packages = { "java.lang.Integer" };
				
				return packages;
				
			}
			
			public String getPrimitiveType() { return "int"; }

			public String getResultSetType() { return "Int"; }
			
			public String getJSONType() { return "Int"; }
			
			public boolean getJSONTypeCasting() { return true; }
			
			public String getCategoryTypes() { return CategoryTypes.Number.name(); }
			
		},
		Long {
			
			public String[] getPackages() {
				
				String[] packages = { "java.lang.Long" };
				
				return packages;
				
			}
			
			public String getPrimitiveType() { return "long"; }

			public String getResultSetType() { return "Long"; }
			
			public String getJSONType() { return "Long"; }
			
			public boolean getJSONTypeCasting() { return true; }
			
			public String getCategoryTypes() { return CategoryTypes.Number.name(); }
			
		},
		Float {
			
			public String[] getPackages() {
				
				String[] packages = { "java.lang.Float" };
				
				return packages;
				
			}
			
			public String getPrimitiveType() { return "float"; }

			public String getResultSetType() { return "Float"; }
			
			public String getJSONType() { return "Double"; }
			
			public boolean getJSONTypeCasting() { return true; }
			
			public String getCategoryTypes() { return CategoryTypes.Number.name(); }
			
		},
		Double {
			
			public String[] getPackages() {
				
				String[] packages = { "java.lang.Double" };
				
				return packages;
				
			}
			
			public String getPrimitiveType() { return "double"; }

			public String getResultSetType() { return "Double"; }
			
			public String getJSONType() { return "Double"; }
			
			public boolean getJSONTypeCasting() { return true; }
			
			public String getCategoryTypes() { return CategoryTypes.Number.name(); }
			
		},
		String {
			
			public String[] getPackages() {
				
				String[] packages = { "java.lang.String" };
				
				return packages;
				
			}
			
			public String getPrimitiveType() { return "String"; }

			public String getResultSetType() { return "String"; }
			
			public String getJSONType() { return "String"; }
			
			public boolean getJSONTypeCasting() { return false; }
			
			public String getCategoryTypes() { return CategoryTypes.String.name(); }
			
		},
		Enum {
			
			public String[] getPackages() {
				
				String[] packages = { "java.util.Enum" };
				
				return packages;
				
			}
			
			public String getPrimitiveType() { return "String"; }

			public String getResultSetType() { return "String"; }
			
			public String getJSONType() { return "String"; }
			
			public boolean getJSONTypeCasting() { return false; }
			
			public String getCategoryTypes() { return CategoryTypes.Collection.name(); }
			
		},
		Set {
			
			public String[] getPackages() {
				
				String[] packages = { "java.util.Set", "java.util.TreeSet" };
				
				return packages;
				
			}
			
			public String getPrimitiveType() { return "String"; }

			public String getResultSetType() { return "String"; }
			
			public String getJSONType() { return "String"; }
			
			public boolean getJSONTypeCasting() { return false; }
			
			public String getCategoryTypes() { return CategoryTypes.Collection.name(); }
			
		},
		Timestamp {
			
			public String[] getPackages() {
				
				String[] packages = { 	"java.sql.Timestamp",
										"java.text.ParseException",
										"com.lumata.common.testing.validating.Format"
				};
				
				return packages;
				
			}
			
			public String getPrimitiveType() { return "Timestamp"; }

			public String getResultSetType() { return "Timestamp"; }
			
			public String getJSONType() { return "String"; }
			
			public boolean getJSONTypeCasting() { return false; }
			
			public String getCategoryTypes() { return CategoryTypes.Date.name(); }
			
		},
		Date {
			
			public String[] getPackages() {
				
				String[] packages = { 	"java.util.Date", 
										/*"java.text.SimpleDateFormat",*/  
										"java.text.ParseException",
										"com.lumata.common.testing.validating.Format"
				};
				
				return packages;
				
			}
			
			public String getPrimitiveType() { return "Date"; }

			public String getResultSetType() { return "Date"; }
			
			public String getJSONType() { return "String"; }
			
			public boolean getJSONTypeCasting() { return false; }
			
			public String getCategoryTypes() { return CategoryTypes.Date.name(); }
			
		};
			
		public abstract String[] getPackages();
		public abstract String getPrimitiveType();
		public abstract String getResultSetType();
		public abstract String getJSONType();
		public abstract boolean getJSONTypeCasting();
		public abstract String getCategoryTypes();
		
	}

	public enum MysqlTypes {
		
		TINYBOOLEAN(JavaTypes.Boolean),
		TINYINT(JavaTypes.Byte),
		SMALLINT(JavaTypes.Short),
		MEDIUMINT(JavaTypes.Integer),
		INT(JavaTypes.Integer),
		BIGINT(JavaTypes.Long),
		FLOAT(JavaTypes.Float),		
		DOUBLE(JavaTypes.Double),
		DECIMAL(JavaTypes.Float),
		CHAR(JavaTypes.String),
		VARCHAR(JavaTypes.String),
		BINARY(JavaTypes.String),
		VARBINARY(JavaTypes.String),
		TINYBLOB(JavaTypes.String),
		TINYTEXT(JavaTypes.String),
		BLOB(JavaTypes.String),
		TEXT(JavaTypes.String),
		MEDIUMBLOB(JavaTypes.String),
		MEDIUMTEXT(JavaTypes.String),
		LONGBLOB(JavaTypes.String),
		LONGTEXT(JavaTypes.String),
		ENUM(JavaTypes.Enum),
		SET(JavaTypes.Set),
		TIMESTAMP(JavaTypes.Timestamp),
		DATETIME(JavaTypes.Date),
		DATE(JavaTypes.Date),
		TIME(JavaTypes.Date);
		
		private JavaTypes value;
		
		public String get() {
			
			return this.name().toLowerCase();
			
		}

		public JavaTypes toJavaType() {
			
			return this.value;
			
		}
		
		MysqlTypes( JavaTypes value ) {
			
			this.value = value;
			
		}		
		
	}
	
	public MysqlColumn() {}
	
	public MysqlColumn( JSONObject column ) {
		
		try {
			
			this.set( "", column );
			
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
	}
	
	public MysqlColumn( String table, JSONObject column ) {
		
		try {
			
			this.set( table, column );
			
		} catch( JSONException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
	}
		
	public String getTable() {
		
		return this.table;
		
	}
	
	public String getField() {
	
		return this.field;
		
	}
	
	public String getType() {
	
		return this.type;
		
	}
	
	public String[] getTypeValues() {
		
		return this.type_values;
		
	}
	
	public String getMysqlType() {
		
		return this.mysql_type;
		
	}
	
	public String getJavaType() {
		
		return this.java_type;
		
	}
	
	public String getCategoryType() {
		
		return this.category_type;
		
	}
	
	public boolean getNull() {
	
		return this.is_null;
		
	}
	
	public String getKey() {
	
		return this.key; 
		
	}
	
	public String getDefaultValue() {
	
		return this.default_value;
		
	}
	
	public String getExtra() {
	
		return this.extra;
		
	}
	
	public int getLength() {
		
		return this.length;
		
	}
	
	public int getDecimal() {
		
		return this.decimal;
		
	}
		
	public boolean getUnsigned() {
		
		return this.unsigned;
		
	}
	
	public String getGetMethod() {
		
		return this.get_method;
		
	}

	public String getSetMethod() {
		
		return this.set_method;
		
	}
	
	public void set( String table, JSONObject column ) {
		
		this.setTable( table );
		this.setField( ( column.isNull( MysqlColumn.Fields.FIELD.getValue() ) ? null : column.getString( MysqlColumn.Fields.FIELD.getValue() ) ) );
		this.setType( ( column.isNull( MysqlColumn.Fields.TYPE.getValue() ) ? null : column.getString( MysqlColumn.Fields.TYPE.getValue() ) ) );
		this.generateTypeValues( this.getType() );
		this.setNull( ( column.getString( MysqlColumn.Fields.NULL.getValue() ).toUpperCase() == "YES" ? true : false  ) );
		this.setKey( ( column.isNull( MysqlColumn.Fields.KEY.getValue() ) ? null : column.getString( MysqlColumn.Fields.KEY.getValue() ) ) );
		this.setDefaultValue( ( column.isNull( MysqlColumn.Fields.DEFAULT.getValue() ) ? null : column.getString( MysqlColumn.Fields.DEFAULT.getValue() ) ) );
		this.setExtra( ( column.isNull( MysqlColumn.Fields.EXTRA.getValue() ) ? null : column.getString( MysqlColumn.Fields.EXTRA.getValue() ) ) );
		
	}
	
	public void setTable( String table ) {
		
		this.table = table;
		
	}

	public void setField( String field ) {
		
		this.field = field;
		
	}

	public void setType( String type ) {
		
		this.type = type;
		
	}
	
	public void setTypeValues( String[] type_values ) {
		
		this.type_values = type_values;
		
	}

	public void setMysqlType( String mysql_type ) {
		
		this.mysql_type = mysql_type;
		
	}

	public void setJavaType( String java_type ) {
		
		this.java_type = java_type;
		
	}
	
	public void setCategoryType( String category_type ) {
		
		this.category_type = category_type;
		
	}
	
	public void setNull( boolean is_null ) {
		
		this.is_null = is_null;
		
	}
	
	public void setKey( String key ) {
				
		this.key = key;
	}
	
	public void setDefaultValue( String default_value ) {
		
		this.default_value = default_value;
		
	}
	
	public void setExtra( String extra ) {
		
		this.extra = extra;
		
	}
	
	public void setLength( int length ) {
		
		this.length = length;
		
	}
	
	public void setUnsigned( boolean unsigned ) {
		
		this.unsigned = unsigned;
		
	}

	public void setGetMethod( String get_method ) {
		
		this.get_method = get_method;
		
	}

	public void setSetMethod( String set_method ) {
		
		this.set_method = set_method;
		
	}

	public void setDecimal( int decimal ) {
		
		this.decimal = decimal;
		
	}
	
	private void generateTypeValues( String type ) {
		
		 Pattern primitive_type = Pattern.compile("([a-z]+)[( ]*([0-9]+[,0-9]*|['0-9a-zA-Z,._ ]+)*[ )]*(unsigned)?([a-z) ]*)");
		 
		 Matcher values = primitive_type.matcher( type );
		 
		 if( values.matches() ) {
			 
			 this.setType( values.group( 0 ) );
			 this.setMysqlType( values.group( 1 ) );
			 this.setJavaType( MysqlColumn.MysqlTypes.valueOf( this.getMysqlType().toUpperCase() ).toJavaType().toString() );
			 this.setCategoryType( MysqlColumn.JavaTypes.valueOf( this.getJavaType() ).getCategoryTypes() );
			 
			 switch( MysqlColumn.JavaTypes.valueOf( this.getJavaType() ) ) {
			 
			 	case Byte: {
			 		// tinyint(1)
			 		if( Integer.valueOf( values.group( 2 ) ) == 1 ) {
			 			this.setJavaType( MysqlColumn.JavaTypes.Boolean.name() );
			 		}
			 		this.setLength( Integer.valueOf( ( values.group( 2 ) != null ? values.group( 2 ) : "0" ) ) );
			 		break;
			 	}
			 	case Short: {
			 		this.setLength( Integer.valueOf( ( values.group( 2 ) != null ? values.group( 2 ) : "0" ) ) );
			 		break;
			 	}
			 	case Integer: {
			 		this.setLength( Integer.valueOf( ( values.group( 2 ) != null ? values.group( 2 ) : "0" ) ) );
			 		break;
			 	}
			 	case Long: {
			 		this.setLength( Integer.valueOf( ( values.group( 2 ) != null ? values.group( 2 ) : "0" ) ) );
			 		break;
			 	}
			 	case Float: {
			 		this.setFloatingPointLength( values.group( 2 ), 4 );
			 		break;
			 	}
			 	case Double: {
			 		this.setFloatingPointLength( values.group( 2 ), 4 );
			 		break;
			 	}
			 	case String: {
			 		this.setLength( Integer.valueOf( ( values.group( 2 ) != null ? values.group( 2 ) : "0" ) ) ); 
			 		break;
			 	}
			 	case Enum: {
			 		this.setTypeValues( this.getCollectionValues( values.group( 2 ) ) );
			 		this.setLength( this.getTypeValues().length );
			 		break;
			 	}
			 	case Set: {
			 		this.setTypeValues( this.getCollectionValues( values.group( 2 ) ) );
			 		this.setLength( this.getTypeValues().length );
			 		break;
			 	}
			 	case Timestamp: {
			 		this.setLength( 0 );
			 		break;
			 	}
			 	case Date: {
			 		this.setLength( 0 );
			 		break;
			 	}
			 	
			 }
			 			 
			 if( values.group( 3 ) != null ) { this.setUnsigned( true ); }
			 else { this.setUnsigned( false ); }
			 
			 StringBuilder methodName = this.createMethodName( this.getField() );
			 
			 StringBuilder getMethodName = new StringBuilder();
			 getMethodName.append( "get" ).append( methodName );
			 this.setGetMethod( getMethodName.toString() );
			 
			 StringBuilder setMethodName = new StringBuilder();
			 setMethodName.append( "set" ).append( methodName );
			 this.setSetMethod( setMethodName.toString() );
			 		 			 
		 }
		 		
	}
	
	private String[] getCollectionValues( String values ) {
		
		String[] collection_values = values.split( "," );
		
		for( int i = 0; i < collection_values.length; i++ ) { collection_values[ i ] = collection_values[ i ].replaceAll( "'" , "\"" );  }
		
		return collection_values;
		
	}
	
	private void setFloatingPointLength( String value, int default_length ) {
		
		if( value != null && !value.equals( "unsigned" ) ) { 
			
			String[] digit = value.split( "," );
			
			this.setLength( Integer.valueOf( digit[ 0 ] ) );
			
			if( digit.length == 2 ) { this.setDecimal( Integer.valueOf( digit[ 1 ] ) ); }
			
			this.setUnsigned( false );
						
		} else {
		
			if( value != null && value.equals( "unsigned" ) ) { 
				
				this.setLength( Integer.valueOf( default_length ) );
				
				this.setDecimal( 0 );
				
				this.setUnsigned( true ); 
			
			} else {
				
				this.setLength( default_length ); 	
				
				this.setDecimal( 0 );
				
				this.setUnsigned( false );
				
			}
		
		}
			
	}
	
	public StringBuilder createMethodName( String field ) {
		
		StringBuilder methodName = new StringBuilder();
		
		String[] methodNameParts = field.split( "_" );
		
		for(int i = 0; i < methodNameParts.length; i++ ) {
			 
			methodName.append( Character.toUpperCase( methodNameParts[i].charAt(0)))
						.append(methodNameParts[i].substring(1));

		}
	
		return methodName;
		
	}

}
