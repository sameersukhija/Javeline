package com.lumata.common.testing.orm;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.annotations.mysql.Column;
import com.lumata.common.annotations.mysql.Table;
import com.lumata.common.testing.database.MysqlColumn;

public class Statement {

	private static final Logger logger = LoggerFactory.getLogger( Statement.class );
	
	private MysqlStatement mysqlStatement;
	
	private List<String> fields;
	private List<String> search;
	private List<String> order;
	
	private Table table;
	
	private StringBuilder field_list;
	private StringBuilder value_list;
	private StringBuilder search_list;
	private StringBuilder order_list;
	
	public enum MysqlStatement { 
		
		SELECT, INSERT_INTO, UPDATE, DELETE;
		
		public StringBuilder getName() {
			
			StringBuilder tag = new StringBuilder();
			
			return tag.append( " " ).append( this.name().replaceAll( "_", " " ) ).append( " " );
			
		}
		
	}

	public enum MysqlStatementOptions { 
		
		FROM, WHERE, AND, VALUES, SET, ORDER_BY;
		
		public StringBuilder getName() {
			
			StringBuilder tag = new StringBuilder();
			
			return tag.append( " " ).append( this.name().replaceAll( "_", " " ) ).append( " " );
			
		}
		
	}
		
	public Statement( MysqlStatement mysqlStatement ) {
		initialize( mysqlStatement );
	}
	
	private Statement initialize( MysqlStatement mysqlStatement ) {
		
		this.mysqlStatement = mysqlStatement;
		
		this.setFields( null );
		this.setSearch( null );
		this.setOrder( null );
		
		this.field_list = new StringBuilder();
		this.value_list = new StringBuilder();
		this.search_list = new StringBuilder();
		this.order_list = new StringBuilder();
		
		return this;
				
	}
	
	public String get( Object entity ) {
		
		StringBuilder query = new StringBuilder();
		
		this.parseEntity( entity, mysqlStatement );
		
		switch( this.mysqlStatement ) {
		
			case SELECT: {
				
				query.append( Statement.MysqlStatement.SELECT.getName() )
						.append( ( this.field_list != null && this.field_list.length() > 0 ? this.field_list.substring( 0, this.field_list.length() -2 ) : "" ) )
						.append( Statement.MysqlStatementOptions.FROM.getName() )
						.append( this.table.value() )
						.append( ( this.search_list != null && this.search_list.length() > 0 ? this.search_list : "" )  )
						.append( ( this.order_list != null && this.order_list.length() > 0 ? Statement.MysqlStatementOptions.ORDER_BY.getName() + this.order_list.substring( 0, this.order_list.length() -2 ) : "" ) )
						.append( ";" );
						
				break;
			}
			case INSERT_INTO: {
				
				query.append( Statement.MysqlStatement.INSERT_INTO.getName() )
						.append( this.table.value() )
						.append( " ( " )
						.append( ( this.field_list != null && this.field_list.length() > 0 ? this.field_list.substring( 0, this.field_list.length() -2 ) : "" ) )
						.append( " )" )
						.append( Statement.MysqlStatementOptions.VALUES.getName() )
						.append( "( " )
						.append( ( this.value_list != null && this.value_list.length() > 0 ? this.value_list.substring( 0, this.value_list.length() -2 ) : "" ) )
						.append( " )" )
						.append( ";" );
						
				break;
			}
			case UPDATE: {
				
				query.append( Statement.MysqlStatement.UPDATE.getName() )
						.append( this.table.value() )
						.append( Statement.MysqlStatementOptions.SET.getName() )
						.append( ( this.field_list != null && this.field_list.length() > 0 ? this.field_list.substring( 0, this.field_list.length() -2 ) : "" ) )
						.append( ( this.search_list != null && this.search_list.length() > 0 ? this.search_list : "" )  )
						.append( ( this.order_list != null && this.order_list.length() > 0 ? Statement.MysqlStatementOptions.ORDER_BY.getName() + this.order_list.substring( 0, this.order_list.length() -2 ) : "" ) )
						.append( ";" );
				
				break;
			}
			case DELETE: {
				
				query.append( Statement.MysqlStatement.DELETE.getName().toString().trim() )
						.append( Statement.MysqlStatementOptions.FROM.getName() )
						.append( this.table.value() )
						.append( ( this.search_list != null && this.search_list.length() > 0 ? this.search_list : "" )  )
						.append( ( this.order_list != null && this.order_list.length() > 0 ? Statement.MysqlStatementOptions.ORDER_BY.getName() + this.order_list.substring( 0, this.order_list.length() -2 ) : "" ) )
						.append( ";" );
						
				break;
			}
		
		}		
		
		return query.toString().trim();
		
	}
	
	public Statement setFields( List<String> fields ) {
		
		this.fields = fields;

		return this;
		
	}
	
	public Statement setSearch( List<String> search ) {
		
		this.search = search;
		
		return this;
		
	}

	public Statement setOrder( List<String> order ) {
		
		this.order = order;
		
		return this;
		
	}

	private void parseEntity( Object entity, Statement.MysqlStatement mysqlStatement ) {
				
		boolean where = true;
		
		this.table = (Table)entity.getClass().getAnnotation( Table.class );
				
		if( this.table != null ) {
			
			for( Field field : entity.getClass().getDeclaredFields() ) {
			    
				Column col;
			    
				if( ( col = field.getAnnotation(Column.class) ) != null ) {
			        
					boolean addField = true;
					boolean addSearch = false;
					boolean addOrder = false;
										
			    	if( this.fields != null && this.fields.size() > 0 && !this.fields.contains( col.field() ) ) { 
			    		
			    		addField = false; 
			    		
			    	}
			    	
			    	if( this.search != null && this.search.size() > 0 && this.search.contains( col.field() ) ) { 
			    		
			    		addSearch = true; 
			    		
			    	}
			    	
			    	if( this.order != null && this.order.size() > 0 && this.order.contains( col.field() ) ) { 
			    		
			    		addOrder = true; 
			    		
			    	}
			    	
			    	switch( mysqlStatement ) {
			    	
			    		case SELECT: {
			    			
			    			if( addField ) { 
			    				this.field_list
			    					.append( this.table.value() )
			    					.append( "." )
			    					.append( col.field() )
			    					.append( ", " ); 
			    			}
			    			
			    			break;
			    		
			    		}
			    		case INSERT_INTO: {
			    			
			    			if( addField ) { 
			    				
			    				this.field_list.append( col.field() ).append( ", " ); 
			    				this.value_list.append( this.setValue( col, entity ) ).append( ", " );
			    			
			    			}
			    			
			    			break;
			    		
			    		}
			    		case UPDATE: {
				    		
			    			if( addField ) { 
			    				
			    				this.field_list.append( this.setFieldValue( col, entity) ).append( ", " ); 
			    				
			    			}
			    			
			    			break;
			    			
			    		}
			    		case DELETE: {
				    		
			    			if( addField ) { this.setFieldValue( col, entity).append( ", " ); }
			    			
			    			break;
			    			
			    		}
			    	
			    	}
			    	
			    	if( addSearch ) { 
			    		
			    		if( where ) {
			    			
			    			this.search_list.append( " WHERE " );
			    			
			    			where = false;
			    			
			    		} else {
			    			
			    			this.search_list.append( " AND " );
			    			
			    		}
			    		
			    		this.search_list.append( this.setFieldValue( col, entity ) );
			    		
			    	}
			    	
			    	if( addOrder ) { 
			    			this.order_list
			    					.append( this.table.value() )
			    					.append( "." )
			    					.append( col.field() )
			    					.append( ", " ); 
			    	}
			    	
				}
				
			}	
						
		}
		
	}
		
	private StringBuilder setFieldValue( Column col, Object entity ) {
		
		StringBuilder field = new StringBuilder();
		
		field.append( this.table.value() )
				.append( "." )
				.append( col.field() )
				.append( " = " )
				.append( this.setValue( col, entity) );
		
		return field;
		
	}
	
	private StringBuilder setValue( Column col, Object entity ) {
		
		StringBuilder value = new StringBuilder();
		
		try { 
			
			Method m = entity.getClass().getMethod( col.getMethod() );			
			
			boolean isNumber = false;
			
			if( 
				MysqlColumn.JavaTypes.valueOf( col.javaType() ).equals( MysqlColumn.JavaTypes.Short ) || 
				MysqlColumn.JavaTypes.valueOf( col.javaType() ).equals( MysqlColumn.JavaTypes.Integer ) 
			
			) {
				
				isNumber = true;
				
			}
			
			String returnedValue = String.valueOf( m.invoke(entity) );
						
			value.append( ( isNumber == true || returnedValue == "null" ? "" : "'" ) )
					.append( ( returnedValue == null ? "null" : returnedValue ) )
					.append( ( isNumber == true || returnedValue == "null" ? "" : "'" ) );			
					
		} catch( NoSuchMethodException e ) {
			logger.error( e.getMessage(), e );		
		} catch( InvocationTargetException e ) {
			logger.error( e.getMessage(), e );
		} catch( IllegalAccessException e ) {
			logger.error( e.getMessage(), e );
		}
		
		return value;
		
	}
	
}
