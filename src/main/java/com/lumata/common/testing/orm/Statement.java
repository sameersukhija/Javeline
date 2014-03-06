package com.lumata.common.testing.orm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.annotations.mysql.Column;
import com.lumata.common.testing.database.MysqlColumn;

public class Statement {

	private static final Logger logger = LoggerFactory.getLogger( Statement.class );
	
	private StringBuilder content;
	private MysqlStatement mysqlStatementType;
	
	public Map<String, Object> entities;
	public Enum<?>[] fields;
	public Map<Enum<?>, String> place_holders;
	
	public enum MysqlStatement { 
		
		SELECT, UPDATE, DELETE,
		INSERT_INTO, INSERT_LOW_PRIORITY_INTO, INSERT_DELAYED_INTO, INSERT_HIGH_PRIORITY_INTO, INSERT_IGNORE_INTO,
		FROM, JOIN, ON, VALUES, WHERE, AND, OR, SET, ON_DUPLICATE_KEY_UPDATE, 
		GROUP_BY, HAVING, ORDER_BY, LIMIT;
		
		public StringBuilder getName() {
			
			StringBuilder tag = new StringBuilder();
			
			return tag.append( " " ).append( this.name().replaceAll( "_", " " ) ).append( " " );
			
		}
		
	}
	
	public enum MysqlSelectFuncType {
		
		MAX,
		MIN, 
		AVG,
		ALL,
		MID,
		NOW,
		SUM,
		ROUND,
		COUNT,
		UCASE,
		LCASE,
		CONCAT,
		DISTINCT;
		
		public enum ValueTypes { Single_Field, Single_Value, Multiple_Values, Enum }
		
		private Enum<?> field;
		private String value;
		private Object[] object_array;
		private ValueTypes value_type;
		
		public Enum<?> getField() {
			
			return this.field;
			
		}

		public String getValue() {
			
			return this.value;
			
		}
		
		public Object[] getObjectArray() {
			
			return this.object_array;
			
		}
		
		public ValueTypes getValueType() {
			
			return this.value_type;
			
		}
				
		public Enum<?> setField( Enum<?> field ) {
			
			this.field = field;
			
			return this;
			
		}

		public Enum<?> setValue( String value ) {
			
			this.value = value ;
			
			return this;
			
		}
		
		public Enum<?> setObjectArray( Object... object_array ) {
			
			this.object_array = object_array;
			
			return this;
			
		}
		
		public void setValueType( ValueTypes value_type ) {
			
			this.value_type = value_type;
			
		}
		
	}
	
	public Statement() {
		this.content = new StringBuilder();
		this.entities = new HashMap<String, Object>();
		this.place_holders = new HashMap<Enum<?>, String>();
	}

	public MysqlStatement getMysqlStatementType() {
		return this.mysqlStatementType;
	}

	public void setMysqlStatementType( MysqlStatement mysqlStatementType ) {
		this.mysqlStatementType = mysqlStatementType;
	}

	public Statement append( StringBuilder content ) {
		
		this.content.append( content );
		
		return this;
		
	}
	
	public Statement append( String content ) {
		
		this.content.append( content );
		
		return this;
		
	}
	
	public String build() {
	
		String query = this.content.append(";").toString().trim();
		
		for (Map.Entry<Enum<?>, String> place_holder : place_holders.entrySet()) {
		    
			try {
			
				IEnumFields field = new EnumFields<Enum<?>>( place_holder.getKey() );
			
				Object entity = this.entities.get( field.table() );
				
				Method method = entity.getClass().getDeclaredMethod( field.col().getMethod() );
			
				Object value = method.invoke( entity );
				
				if( value == null ) { query = query.replaceAll( "\"?" + place_holder.getValue() + "\"?", "NULL" ); }
				else { query = query.replace( place_holder.getValue(), String.valueOf( value ) ); }				
				//System.out.println( "QUERY: " + query );				
			} catch( NoSuchMethodException e ) {
				logger.error( e.getMessage(), e );
			} catch (IllegalAccessException e) {
				logger.error( e.getMessage(), e );
			} catch (InvocationTargetException e) {
				logger.error( e.getMessage(), e );
			}
			
		}
		
		return query;
	
	}
	
	public String fields( final Enum<?>... fields ) {
		
		Type typeFunc = MysqlSelectFuncType.class;
		
		StringBuilder content = new StringBuilder();
		
		if( fields != null ) {
			
			for( int i = 0; i < fields.length; i++ ) {
								
				StringBuilder field_value = new StringBuilder();
				boolean function = false;
				
				function = typeFunc.equals( fields[ i ].getClass() );
				
				if( function ) { 
					
					switch( MysqlSelectFuncType.valueOf( fields[ i ].name() ).getValueType() ) {
					
						case Single_Field: {
							field_value.append( Statement.field( MysqlSelectFuncType.valueOf( fields[ i ].name() ).getField() ) );
							break;
						}
						case Single_Value: {
							field_value.append( MysqlSelectFuncType.valueOf( fields[ i ].name() ).getValue() );
							break;
						}
						case Multiple_Values: {
							field_value.append( Statement.field( null, MysqlSelectFuncType.valueOf( fields[ i ].name() ).getObjectArray() ) );
							break;
						}
						
					}
										
				} else { 
					field_value = Statement.field( fields[ i ] );
				}
				
				content.append( ( function == true ? fields[ i ].name() + "( " : "" ) )
						.append( field_value )
						.append( ( function == true ? " )" : "" ) )
						.append( ", " );
						    					
			}
			
		} else { return ""; }
		
		return content.substring( 0, content.length() - 2 );
		
	}
		
	public static StringBuilder field( Enum<?> field ) {
		
		IEnumFields field_enum = new EnumFields<Enum<?>>( field );
		StringBuilder field_value = new StringBuilder();
	
		field_value.append( field_enum.col().table() )
					.append( "." )
					.append( field_enum.col().field() );
		
		return field_value;
	
	}
	
	public static String field( Column col, Object... values ) {
		
		StringBuilder field_value = new StringBuilder();
				
		if( values != null ) {
			
			for( int i = 0; i < values.length; i++ ) {
				
				if( values[ i ] != null && values[ i ].getClass().isEnum() ) { 
	
					field_value.append( Statement.field( (Enum<?>)values[ i ] ) ); 
				
				} else { 
									
					if( values[ i ] == null ) { field_value.append( "NULL" ); }
					else {
						
						String value = String.valueOf( values[ i ] );
										
						if( col != null && col.categoryType().equals( MysqlColumn.CategoryTypes.Number.name() ) ) { 
							field_value.append( value ); 
						} else { field_value.append( "\"" ).append( value ).append( "\"" ); }					 
				
					}
					
				}
				
				field_value.append( ", " );
				
			}
			
		}
				
		return field_value.substring( 0, ( field_value.length() > 2 ? field_value.length() - 2 : field_value.length() ));
				
	}
	
	public String expr( final boolean use_place_holders, final Object... values ) {
		
		StringBuilder content = new StringBuilder();
						
		if( values != null ) {
			
			for( int i = 0; i < this.fields.length; i++ ) {
			
				boolean skip = false;
				
				IEnumFields field = new EnumFields<Enum<?>>( this.fields[ i ] );
				Column col = field.col();
				
				if( this.getMysqlStatementType().equals( MysqlStatement.INSERT_INTO ) && col.isAutoincrement() && use_place_holders ) {
					
					try {
						
						Object entity = this.entities.get( field.table() );
						
						Method method = entity.getClass().getDeclaredMethod( field.col().getMethod() );
					
						Object value = method.invoke( entity );
						
						if( value == null ) { String f = Statement.field( this.fields[ i ] ) + ", ";
							
							int index = this.content.indexOf( f );
							this.content.replace( index, index + f.length(), "" );
							skip = true;
							
						}
										
					} catch( NoSuchMethodException e ) {
						logger.error( e.getMessage(), e );
					} catch (IllegalAccessException e) {
						logger.error( e.getMessage(), e );
					} catch (InvocationTargetException e) {
						logger.error( e.getMessage(), e );
					}				
					
				} 
				
				if( !skip ) {
								
					if( use_place_holders ) {
						
						String place_holder = Statement.getPlaceHolder( this.fields[ i ] );
						
						this.addPlaceHolder( fields[ i ], place_holder.toString() );
					
						//content.append( place_holder ).append( ", " );
						
						if( col != null && col.categoryType().equals( MysqlColumn.CategoryTypes.Number.name() ) ) { 
							content.append( place_holder ); 
						} else { content.append( "\"" ).append( place_holder ).append( "\"" ); }
						
						content.append( ", " );
											
					} else {
						content.append( Statement.field( col, ( values.length <= i ) ? null : values[i] ) ).append( ", " );
					}
				
				}
							
			}
			
			content.setLength( content.length() - 2 );
			
		}
	
		return content.toString();		 
		
	}

	public String expr( final Row... rows ) {
		
		StringBuilder content = new StringBuilder();
						
		if( rows != null ) {			
			
			for( int i = 0; i < rows.length; i++ ) {
				
				content.append( "( " )
						.append( this.expr( false, rows[i].getValues() ) )
						.append( " ), " );
				
			}
			
			content.setLength( content.length() - 2 );
		
		}
	
		return content.toString();		 
		
	}
	
	public static String expr( IExprFV... expr ) {
		
		StringBuilder content = new StringBuilder();
		
		for( int i = 0; i < expr.length; i++ ) {
			
			content.append( Statement.expr( expr[i] ) )
					.append( ", " );
		
		}	
		
		if( content.length() > 0 ) { content.setLength( content.length() - 2 ); }
		
		return content.toString();
		
	}
	
	public static String expr( IExprFV expr ) {
		
		StringBuilder content = new StringBuilder();
				
		IEnumFields field = new EnumFields<Enum<?>>( expr.getField() );
		
		if( expr.getUsePlaceHolder() ) { 
			
			expr.setValue( Statement.getPlaceHolder( expr.getField() ) );
			
		}
		
		content.append( Statement.field( expr.getField() ) )
				.append( expr.getOp().value() );
		
		if( expr.getOp().equals( Op.Types.in ) ) { 
			
			content.append( "( " )
					.append( Statement.field( field.col(), expr.getValues() ) )
					.append( " )" );					
			
		} else { content.append( Statement.field( field.col(), expr.getValue() ) ); }
		
		return content.toString();
		
	}
	
	public static String expr( IExprFF expr ) {
		
		StringBuilder content = new StringBuilder();
		
		IEnumFields left_field = new EnumFields<Enum<?>>( expr.getLeftField() );
		IEnumFields right_field = new EnumFields<Enum<?>>( expr.getRightField() );
		
		content.append( left_field.col().table() )
				.append( "." )
				.append( left_field.col().field() )
				.append( expr.getOp().value() )
				.append( right_field.col().table() )
				.append( "." )
				.append( right_field.col().field() );
		
		return content.toString();
		
	}
	
	public static String getPlaceHolder( Enum<?> field ) {
				
		StringBuilder place_holder = new StringBuilder();
		
		place_holder.append( "::" ).append( Statement.field( field ) ).append( "::" );
		
		return place_holder.toString();
		
	}
	
	public void addEntity( Object entity, String table ) {
		this.entities.put( table, entity );
	}
	
	public void addFields( Enum<?>... fields ) {
		this.fields = fields;
	}
	
	public void addPlaceHolder( Enum<?> field, String place_holder ) {
		this.place_holders.put( field, place_holder );
	}
	
	public void addAllPlaceHolders( Map<Enum<?>, String> place_holders ) {
		this.place_holders.putAll( place_holders );
	}
	
}
