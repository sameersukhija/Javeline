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
	
	public Map<String, Object> entities;
	public Enum[] fields;
	public Map<Enum<?>, String> place_holders;
	
	public enum MysqlStatement { 
		
		SELECT, INSERT_INTO, UPDATE, DELETE,
		FROM, JOIN, ON, VALUES, WHERE, AND, OR, 
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
				
				query = query.replaceAll( "\"?" + place_holder.getValue() + "\"?", ( value == null ? "NULL" : String.valueOf( method.invoke( entity ) ) ) );
				
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
				
				IEnumFields field = new EnumFields<Enum<?>>( this.fields[ i ] );
				
				if( use_place_holders ) {
					
					StringBuilder table_field = new StringBuilder();
					
					table_field.append( field.col().table() )
								.append( "." )
								.append( field.col().field() );
					
					String place_holder = "::" + table_field + "::";
					
					this.addPlaceHolder( fields[ i ], place_holder );
				
					content.append( place_holder ).append( ", " );
				
				} else {
					content.append( Statement.field( field.col(), ( values.length <= i ) ? null : values[i] ) ).append( ", " );
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
	
	public static String expr( IExprFV expr ) {
		
		StringBuilder content = new StringBuilder();
		StringBuilder table_field = new StringBuilder();
		
		IEnumFields field = new EnumFields<Enum<?>>( expr.getField() );
		
		table_field.append( field.col().table() )
					.append( "." )
					.append( field.col().field() );
					
		if( expr.getUsePlaceHolder() ) { 
			
			StringBuilder place_holder = new StringBuilder();
			
			place_holder.append( "::" ).append( table_field ).append( "::" );
						
			expr.setValue( place_holder.toString() );
			
		}
		
		content.append( table_field )
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
